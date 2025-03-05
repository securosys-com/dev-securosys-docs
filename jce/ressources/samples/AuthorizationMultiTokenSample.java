
/*
 * Copyright (C) 2019, Securosys SA
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
import com.securosys.primus.jce.PrimusDevice;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusProviderException;
import com.securosys.primus.jce.PrimusSignature;

public class AuthorizationMultiTokenSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final ECGenParameterSpec curve = new ECGenParameterSpec("secp256k1");
		final ECGenParameterSpec curve2 = new ECGenParameterSpec("secp256r1");
		final String signAlgorithm = "SHA256withECDSA";

		final String integrityKeyName = "integrityKeyName-" + UUID.randomUUID().toString();
		PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			curve,
			integrityKeyName,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_INTEGRITY, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true)
		);

		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(curve2);

		final KeyPair andrea = localKeyPairGenerator.generateKeyPair();
		final KeyPair beryl = localKeyPairGenerator.generateKeyPair();
		final KeyPair chantal = localKeyPairGenerator.generateKeyPair();
		final KeyPair debby = localKeyPairGenerator.generateKeyPair();
		final KeyPair erin = localKeyPairGenerator.generateKeyPair();
		final PublicKey[] operationPublicKeys = new PublicKey[] {andrea.getPublic(), beryl.getPublic(), chantal.getPublic(), debby.getPublic(), erin.getPublic(),};

		final KeyPair florence = localKeyPairGenerator.generateKeyPair();
		final PublicKey[] fraudDetectionPublicKeys = new PublicKey[] {florence.getPublic(),};

		final KeyPair rebekah = localKeyPairGenerator.generateKeyPair();
		final KeyPair sara = localKeyPairGenerator.generateKeyPair();
		final KeyPair tanya = localKeyPairGenerator.generateKeyPair();
		final PublicKey[] riskOfficersPublicKeys = new PublicKey[] {rebekah.getPublic(), sara.getPublic(), tanya.getPublic(),};

		final KeyPair melissa = localKeyPairGenerator.generateKeyPair();
		final KeyPair nadine = localKeyPairGenerator.generateKeyPair();
		final PublicKey[] modifyerPublicKeys = new PublicKey[] {melissa.getPublic(), nadine.getPublic(),};

		final PrimusAccessBlob operationBlob = new PrimusAccessBlob(
			new PrimusAccessToken(new PrimusAccessGroup(operationPublicKeys, 2))
		);
		final PrimusAccessBlob blockBlob = new PrimusAccessBlob(
			new PrimusAccessToken(new PrimusAccessGroup(fraudDetectionPublicKeys, 1)),
			new PrimusAccessToken(new PrimusAccessGroup(riskOfficersPublicKeys, 1))
		);
		final PrimusAccessBlob unblockBlob = new PrimusAccessBlob(
			new PrimusAccessToken(new PrimusAccessGroup(riskOfficersPublicKeys, 3))
		);
		final PrimusAccessBlob modifyBlob = new PrimusAccessBlob(
			new PrimusAccessToken(new PrimusAccessGroup(modifyerPublicKeys, 2))
		);
		final PrimusAccess access = new PrimusAccess(operationBlob, blockBlob, unblockBlob, modifyBlob);

		final String signKeyName = UUID.randomUUID().toString();
		final char[] signKeyPassword = UUID.randomUUID().toString().toCharArray();
		final KeyPair signKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			curve,
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
		final PublicKey signKeyPublic = signKeyPair.getPublic();

		final byte[] payload = new byte[256];
		final SecureRandom localSecureRandom = new SecureRandom();
		localSecureRandom.nextBytes(payload);

		PrimusSignature.setWantSignatures(true);
		PrimusSignature.setSignatureKey(integrityKeyName);
		final byte[] timestamp = PrimusDevice.getHsmTimestamp(payload, integrityKeyName, signAlgorithm);
		final PrimusSignature timestampSignature = PrimusSignature.getSignature();

		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyName, timestamp, timestampSignature)).getEncoding();
		final PrimusAuthorizationToken token0 = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, beryl.getPrivate(), beryl.getPublic());
		final PrimusAuthorizationToken token1 = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, chantal.getPrivate(), chantal.getPublic());

		final PrimusAuthorization primusAuthorization = new PrimusAuthorization(token0, token1);
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);
		final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initSign(signKey);
		signature.update(payload);
		final byte[] sig = signature.sign();

		final Signature localVerifyer = Signature.getInstance(signAlgorithm);
		localVerifyer.initVerify(signKeyPublic);
		localVerifyer.update(payload);
		final boolean signSignatureOk = localVerifyer.verify(sig);
		System.out.println("sign signature is " + (signSignatureOk ? "OK" : "NOT OK"));

		final byte[] nopayload = null;
		PrimusAuthorization.setAuthorization(signKey, new PrimusAuthorization(
			new PrimusAuthorizationToken((new PrimusApprovalToken(PrimusApprovalToken.OPERATION_BLOCK, nopayload, signKeyName, timestamp, timestampSignature)).getEncoding(), signAlgorithm, florence.getPrivate(), florence.getPublic())
		));
		PrimusKeyAttributes.setKeyAccessFlag(signKey, PrimusKeyAttributes.ACCESS_BLOCKED, true);

		System.out.println("trying to sign now ...");
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);
		signature.initSign(signKey);
		try {
			signature.sign();
			System.out.println("UNEXPECTED");
		} catch (PrimusProviderException e) {
			System.out.println("expected: " + e);
		}

		final byte[] tok = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_UNBLOCK, nopayload, signKeyName, timestamp, timestampSignature)).getEncoding();
		PrimusAuthorization.setAuthorization(signKey, new PrimusAuthorization(
			new PrimusAuthorizationToken(tok, signAlgorithm, rebekah.getPrivate(), rebekah.getPublic()),
			new PrimusAuthorizationToken(tok, signAlgorithm, sara.getPrivate(), sara.getPublic()),
			new PrimusAuthorizationToken(tok, signAlgorithm, tanya.getPrivate(), tanya.getPublic())
		));
		PrimusKeyAttributes.setKeyAccessFlag(signKey, PrimusKeyAttributes.ACCESS_BLOCKED, false);

		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);
		signature.initSign(signKey);
		signature.sign();

		localVerifyer.initVerify(signKeyPublic);
		localVerifyer.update(payload);
		System.out.println("sign signature is " + (localVerifyer.verify(sig) ? "OK" : "NOT OK"));

	}

}

