
/*
 * Copyright (C) 2017-2019, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
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
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusProviderException;

/**
 * Sample code using PrimusAccess/PrimusAuthorization
 * with the Securosys Primus HSM JCE provider.
 *
 * The sample:
 *
 * 1) logs into the HSM
 * 2) creates local approval keys
 * 3) assembles an access
 * 4) creates a sign key that needs approvals
 * 5) attempts to use the key with insufficient quorum
 * 6) modifies access
 * 7) tests modification
 */
public class ApprovalModifySample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final PrimusAccess access = PrimusSkaHelper.getSampleAccessNoTimeLimit();

		// 4 - CREATE A KEY WITH RULES USING APPROVAL PUBLIC KEY GROUPS

		// create a signature (asset) key using the rules in the access definition
		final String signKeyName = UUID.randomUUID().toString();
		final char[] signKeyPassword = UUID.randomUUID().toString().toCharArray();
		final KeyPair signKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			// new ECGenParameterSpec("secp256k1"), // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
			new ECGenParameterSpec("secp256r1"),
			signKeyName,
			signKeyPassword,
			access,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_SIGN, true),
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_DECRYPT, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_MODIFIABLE, false)
		);
		final PrivateKey signKey = signKeyPair.getPrivate();

		// 4 - PREPARE PAYLOAD

		// this would normally be a crypto transaction - here its just random data
		final byte[] payload = new byte[256];
		final SecureRandom localSecureRandom = new SecureRandom();
		localSecureRandom.nextBytes(payload);

		// 5 - ATTEMPT TO SIGN WITH INSUFFICIENT QUORUM

		final Signature signature = Signature.getInstance("SHA256withECDSA", PrimusProvider.getProviderName());

		// create an approval locally - this would normally be done at approval terminals using their local private keys
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyName)).getEncoding();
		final PrimusAuthorizationToken token = new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", PrimusSkaHelper.debby.getPrivate(), PrimusSkaHelper.debby.getPublic());
		final PrimusAuthorization primusAuthorization1 = new PrimusAuthorization(token);
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization1);
		try {
			signature.initSign(signKey);
			signature.update(payload);
			signature.sign();
			System.out.println("UNEXPECTED");
		} catch (PrimusProviderException e) {
			System.out.println("signature failed as expected: " + e);
		}

		// 6 - MODIFY ACCESS

		// generate a changed access with the lower quorum
		final PrimusAccessBlob superblob = new PrimusAccessBlob(new PrimusAccessToken(new PrimusAccessGroup(new PublicKey[] {PrimusSkaHelper.helene.getPublic()}, 1)));
		final PrimusAccessBlob subsuperblob = new PrimusAccessBlob(new PrimusAccessToken(new PrimusAccessGroup(new PublicKey[] {PrimusSkaHelper.helene.getPublic(), PrimusSkaHelper.debby.getPublic(),}, 1)));
		final PrimusAccess superAccess = new PrimusAccess(subsuperblob, superblob, superblob, superblob);
		final byte[] payload2 = PrimusAccess.getModifyAccessPayload(signKey, superAccess);

		// approval for modify operation
		final byte[] tokenChallenge2 = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_MODIFY, payload2, signKeyName)).getEncoding();
		final PrimusAuthorization authorization2 = new PrimusAuthorization(new PrimusAuthorizationToken(tokenChallenge2, "SHA256withECDSA", PrimusSkaHelper.helene.getPrivate(), PrimusSkaHelper.helene.getPublic()));
		PrimusAuthorization.setAuthorization(signKey, authorization2);
		// modify
		PrimusAccess.modifyAccess(signKey, superAccess);
		System.out.println("modification successful");

		// 7 - REPEAT ATTEMPT TO SIGN

		// this time the original signature attempt should go through
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization1);
		signature.initSign(signKey);
		signature.update(payload);
		signature.sign();
		System.out.println("signature successful");

	}

}

