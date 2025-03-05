
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessGroup;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusApprovalToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusAuthorizationToken;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusKeyHelper;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusProviderException;
import com.securosys.primus.jce.PrimusSpecs;

public class ApprovalModifyCkdSample2 {

	public static void main(final String... args) throws Exception {
		test(false);
		test(true);
	}

	static void test(final boolean direct) throws Exception {

		// setup HSM configuration/login, and SKA access attributes
		PrimusHelper.setup();
		final PrimusAccess access = PrimusSkaHelper.getSampleAccessNoTimeLimit();
		assert access != null;

		// make a CKD-derivable EC key (CKD is e.g. secp256k1)
		final String baseKeyName = UUID.randomUUID().toString();
		final char[] baseKeyPassword = UUID.randomUUID().toString().toCharArray();
		assert baseKeyName != null;
		final KeyPair baseKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			new PrimusSpecs.CkdEnabled(new ECGenParameterSpec("secp256k1")),
			baseKeyName,
			baseKeyPassword,
			access,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_DERIVE, true),
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_SIGN, true),
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_DECRYPT, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_MODIFIABLE, false)
		);
		final PrivateKey baseKey = baseKeyPair.getPrivate();

		final byte[] payload = new byte[24];
		final String derivationPath = "0'/1'/2'";

		// pseudo-derived-key
		PrivateKey signKey = new PrimusSpecs.CkdDerivedPrivateKey(baseKey, derivationPath);
		final String signKeyName = PrimusKeyHelper.getPrimusKeyName(signKey);
		assert signKeyName != null;

		// real-derived-key
		if (direct) {
			final PrivateKey signKeyB = ((KeyPair)PrimusName.generate(
				KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
				new PrimusSpecs.CkdGenParameterSpec(baseKey, derivationPath)
			)).getPrivate();
			final String signKeyNameB = PrimusKeyHelper.getPrimusKeyName(signKeyB);
			assert signKeyNameB != null;
			assert signKeyNameB.equals(signKeyName);
			signKey = signKeyB;
		}

		// attempt to sign with insufficient quorum

		final Signature signature = Signature.getInstance("SHA256withECDSA", PrimusProvider.getProviderName());

		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyName)).getEncoding();
		final PrimusAuthorizationToken token = new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", PrimusSkaHelper.debby.getPrivate(), PrimusSkaHelper.debby.getPublic());
		final PrimusAuthorization primusAuthorization1 = new PrimusAuthorization(token);
		PrimusAuthorization.setAuthorization(primusAuthorization1);
		try {
			signature.initSign(signKey);
			signature.update(payload);
			signature.sign();
			System.out.println("UNEXPECTED");
			assert false;
		} catch (final PrimusProviderException e) {
			System.out.println("signature failed as expected: " + e);
		}

		// modify access

		PrivateKey key = baseKey;
		String keyName = baseKeyName;

		if (direct) {
			key = signKey;
			keyName = signKeyName;
		}

		// generate a changed access with the lower quorum
		final PrimusAccessBlob superblob = new PrimusAccessBlob(new PrimusAccessToken(new PrimusAccessGroup(new PublicKey[] {PrimusSkaHelper.helene.getPublic()}, 1)));
		final PrimusAccessBlob subsuperblob = new PrimusAccessBlob(new PrimusAccessToken(new PrimusAccessGroup(new PublicKey[] {PrimusSkaHelper.helene.getPublic(), PrimusSkaHelper.debby.getPublic(),}, 1)));
		final PrimusAccess superAccess = new PrimusAccess(subsuperblob, superblob, superblob, superblob);
		final byte[] payload2 = PrimusAccess.getModifyAccessPayload(key, superAccess);

		// approval for modify operation
		final byte[] tokenChallenge2 = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_MODIFY, payload2, keyName)).getEncoding();
		final PrimusAuthorization authorization2 = new PrimusAuthorization(new PrimusAuthorizationToken(tokenChallenge2, "SHA256withECDSA", PrimusSkaHelper.helene.getPrivate(), PrimusSkaHelper.helene.getPublic()));
		PrimusAuthorization.setAuthorization(authorization2);
		// modify
		PrimusAccess.modifyAccess(key, superAccess);
		System.out.println("modification successful");

		// repeat attempt to sign
		// this time the original signature attempt should go through

		PrimusAuthorization.setAuthorization(primusAuthorization1);
		signature.initSign(signKey);
		signature.update(payload);
		signature.sign();
		System.out.println("signature successful");

	}

}

