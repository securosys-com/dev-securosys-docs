
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
import com.securosys.primus.jce.PrimusSignature;

/**
 * Sample code using PrimusAccess/PrimusAuthorization
 * with the Securosys Primus HSM JCE provider.
 */
public class AuthorizationRsaAndEcSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// create integrity key, used by HSM to sign attribute retrievals and time stamps
		final String integrityKeyName = UUID.randomUUID().toString();
		PrimusName.generate(
			KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName()),
			2048,
			integrityKeyName,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_INTEGRITY, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true)
		);

		// locally create authorization keys
		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
		localKeyPairGenerator.initialize(2048);
		final KeyPairGenerator localKeyPairGenerator2 = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator2.initialize(256);
		final int n = 4;
		final KeyPair[] authorizationKeys = new KeyPair[n];
		final PublicKey[] authorizationPublicKeys = new PublicKey[n];
		for (int i = 0; i < authorizationKeys.length; i++) {
			authorizationKeys[i] = (i % 2 == 0 ? localKeyPairGenerator : localKeyPairGenerator2).generateKeyPair();
			authorizationPublicKeys[i] = authorizationKeys[i].getPublic();
		}

		// key-pair creation with access and flags set:

		final PrimusAccessGroup accessGroup = new PrimusAccessGroup(authorizationPublicKeys, 2);
		// note: time delays/limits require timestamp based approvals
		final PrimusAccessToken accessToken = new PrimusAccessToken(TimeUnit.MINUTES.toMillis(0), TimeUnit.MINUTES.toMillis(5), accessGroup);
		final PrimusAccess primusAccess = new PrimusAccess(new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken));

		final String signKeyName = UUID.randomUUID().toString();
		final KeyPair signKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName()),
			2048,
			signKeyName,
			primusAccess,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_SIGN, true),
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_DECRYPT, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_MODIFIABLE, false)
		);
		final PrivateKey signKey = signKeyPair.getPrivate();
		final PublicKey signKeyPublic = signKeyPair.getPublic();

		// some random payload, in reality some crypto currency transaction
		final byte[] payload = new byte[256];
		final SecureRandom localSecureRandom = new SecureRandom();
		localSecureRandom.nextBytes(payload);

		// get signed timestamp for the approved transaction
		PrimusSignature.setWantSignatures(true);
		PrimusSignature.setSignatureKey(integrityKeyName);
		final byte[] timestamp = PrimusDevice.getHsmTimestamp(payload, integrityKeyName, null, "SHA256withRSA");
		final PrimusSignature timestampSignature = PrimusSignature.getSignature();
		// the timestamp signature will be verified by the terminal, the timestamp will be used for the authorizationTokenData below

		// build a test authorization directly in this code
		// PrimusApprovalToken is build-by-Java only in test code
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyName, timestamp, timestampSignature)).getEncoding();
		final PrimusAuthorizationToken token0 = new PrimusAuthorizationToken(tokenChallenge, "SHA256withRSA", authorizationKeys[0].getPrivate(), authorizationKeys[0].getPublic());
		final PrimusAuthorizationToken token1 = new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", authorizationKeys[1].getPrivate(), authorizationKeys[1].getPublic());

		final PrimusAuthorization primusAuthorization = new PrimusAuthorization();
		primusAuthorization.add(token0);
		primusAuthorization.add(token1);
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);
		final Signature signature = Signature.getInstance("SHA256withRSA", PrimusProvider.getProviderName());
		signature.initSign(signKey);
		signature.update(payload);
		final byte[] sig = signature.sign();

		// verify locally
		final Signature localVerifyer = Signature.getInstance("SHA256withRSA");
		localVerifyer.initVerify(signKeyPublic);
		localVerifyer.update(payload);
		assert localVerifyer.verify(sig);

	}

}

