
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
import java.util.Base64;
import java.util.Date;
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
import com.securosys.primus.jce.PrimusTimestamp;

/**
 * Sample code using PrimusAccess/PrimusAuthorization
 * with the Securosys Primus HSM JCE provider.
 */
public class AuthorizationEdSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final String deviceName = PrimusDevice.getDeviceName();
		System.out.println("HSM device name is " + deviceName);

		final ECGenParameterSpec curve0 = new ECGenParameterSpec("secp256k1");
		final ECGenParameterSpec curve1 = new ECGenParameterSpec("secp256r1");
		final ECGenParameterSpec curve = new ECGenParameterSpec("ed25519");
		final String signAlgorithm0 = "SHA256withECDSA";
		final String signAlgorithm = "EdDSA";

		// create integrity key, used by HSM to sign attribute retrievals and time stamps
		final String integrityKeyName = "integrityKeyName-" + UUID.randomUUID().toString();
		final char[] integrityKeyPassword = null; // have no key password with this key; alternatively UUID.randomUUID().toString().toCharArray();
		// typically one wants to directly name generated keys (instead of later use setKeyEntry)
		final KeyPair integrityKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			curve0,
			integrityKeyName,
			integrityKeyPassword,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_INTEGRITY, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true)
		);
		final PublicKey integrityKeyPublic = integrityKeyPair.getPublic();
		System.out.println("created integrity key " + integrityKeyName);

		// print the integrity public key part
		final byte[] integrityKeyEncoding = integrityKeyPublic.getEncoded();
		// try (final OutputStream outFile = new FileOutputStream(integrityKeyName + "-publickey")) {
		//	outFile.write(integrityKeyEncoding);
		// }
		System.out.println("public integrity key part is " + Base64.getEncoder().encodeToString(integrityKeyEncoding));

		// locally create authorization keys
		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(curve1);
		final int n = 4;
		final KeyPair[] authorizationKeys = new KeyPair[n];
		final PublicKey[] authorizationPublicKeys = new PublicKey[n];
		for (int i = 0; i < authorizationKeys.length; i++) {
			authorizationKeys[i] = localKeyPairGenerator.generateKeyPair();
			authorizationPublicKeys[i] = authorizationKeys[i].getPublic();
		}

		// key-pair creation with access and flags set:

		final int quorum = 2;
		final PrimusAccessGroup accessGroup = new PrimusAccessGroup(authorizationPublicKeys, quorum);
		// note: time delays/limits require a timestamp
		final long authorizationDelay = TimeUnit.MINUTES.toMillis(0);
		// final long authorizationDelay = TimeUnit.MINUTES.toMillis(1);
		// final long authorizationTimelimit = TimeUnit.MINUTES.toMillis(0);
		final long authorizationTimelimit = TimeUnit.MINUTES.toMillis(5);
		// final long authorizationDelay = TimeUnit.MINUTES.toMillis(20);
		// final long authorizationTimelimit = TimeUnit.MINUTES.toMillis(60);
		// final PrimusAccessToken accessToken = new PrimusAccessToken(authorizationDelay, authorizationTimelimit, accessGroup);
		final PrimusAccessToken accessToken = new PrimusAccessToken(authorizationDelay, authorizationTimelimit, accessGroup, accessGroup);
		final PrimusAccess primusAccess = new PrimusAccess(new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken));
		// final PrimusAccess primusAccess = new PrimusAccess(new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken), new PrimusAccessBlob());

		final String signKeyName = UUID.randomUUID().toString();
		final char[] signKeyPassword = UUID.randomUUID().toString().toCharArray();
		final KeyPair signKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			curve,
			signKeyName,
			signKeyPassword,
			primusAccess,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_SIGN, true),
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_DECRYPT, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_MODIFIABLE, false)
		);
		final PrivateKey signKey = signKeyPair.getPrivate();
		final PublicKey signKeyPublic = signKeyPair.getPublic();

		// retrieve attributes and verify their signature locally
		final byte[] attributeData = PrimusKeyAttributes.getAllKeyAttributes(signKeyName, signKeyPassword, integrityKeyName, integrityKeyPassword, signAlgorithm0);
		final byte[] attributeSignature = PrimusSignature.getSignature().getEncoding();

		final Signature localVerifyer0 = Signature.getInstance(signAlgorithm0);
		localVerifyer0.initVerify(integrityKeyPublic);
		localVerifyer0.update(attributeData);
		final boolean attributeSignatureOk = localVerifyer0.verify(attributeSignature);
		System.out.println("attribute signature is " + (attributeSignatureOk ? "OK" : "NOT OK"));

		final PrimusAccess access = PrimusAccess.getAccess(signKey, integrityKeyName, integrityKeyPassword, signAlgorithm0);
		System.out.println("read key access back: " + access);

		// some random payload, in reality some crypto currency transaction
		final byte[] payload = new byte[256];
		final SecureRandom localSecureRandom = new SecureRandom();
		localSecureRandom.nextBytes(payload);

		// get signed timestamp for the transaction
		PrimusSignature.setWantSignatures(true);
		PrimusSignature.setSignatureKey(integrityKeyName);
		final byte[] timestamp = PrimusDevice.getHsmTimestamp(payload, integrityKeyName, integrityKeyPassword, signAlgorithm0);
		final PrimusSignature timestampSignature = PrimusSignature.getSignature();
		// the timestamp signature will be verified by the terminal, the timestamp will be used for the authorizationTokenData below

		// verify locally
		localVerifyer0.initVerify(integrityKeyPublic);
		localVerifyer0.update(timestamp);
		final boolean timestampSignatureOk = localVerifyer0.verify(timestampSignature.getEncoding());
		System.out.println("timestamp signature is " + (timestampSignatureOk ? "OK" : "NOT OK"));

		// timestamp object representation:
		final PrimusTimestamp primusTimestamp = new PrimusTimestamp(timestamp, timestampSignature);
		System.out.println("timestamp's date is " + new Date(TimeUnit.SECONDS.toMillis(primusTimestamp.getSecondsSinceEpoch())));
		System.out.println("timestamp's signature key name is " + primusTimestamp.getSignatureKeyName());

		// wait for possible authorizationDelay to pass
		final long sleep = authorizationDelay + (authorizationDelay > 0 ? TimeUnit.SECONDS.toMillis(10) : 0);
		if (sleep > 0) {
			System.out.println("waiting for authorization delay to pass, " + TimeUnit.MILLISECONDS.toSeconds(sleep) + " seconds");
			Thread.sleep(sleep);
		}

		// invoke authorized signing operation:
		// operations will throw PrimusAuthorizationExceptions if authorization is insufficient/expired/etc

		// build a test authorization directly in this code
		// PrimusApprovalToken is build-by-Java only in test code
		// final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyName)).getEncoding(); // [no-timestamp version, requires delay/limit being zero]
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyName, timestamp, timestampSignature)).getEncoding();
		PrimusAuthorizationToken token0 = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm0, authorizationKeys[0].getPrivate(), authorizationKeys[0].getPublic());
		PrimusAuthorizationToken token1 = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm0, authorizationKeys[1].getPrivate(), authorizationKeys[1].getPublic());

		localVerifyer0.initVerify(token0.getPublicKey());
		localVerifyer0.update(token0.getApprovalTokenBytes());
		final boolean signSignatureOk0 = localVerifyer0.verify(token0.getVerifySignatureBytes());
		System.out.println("sign signature is " + (signSignatureOk0 ? "OK" : "NOT OK"));

		token0 = new PrimusAuthorizationToken(token0.getEncoding()); // mimic authorization tokens coming as byte arrays
		token1 = new PrimusAuthorizationToken(token1.getEncoding());
		final PrimusAuthorization primusAuthorization = new PrimusAuthorization();
		primusAuthorization.add(token0);
		primusAuthorization.add(token1);
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);
		// final Signature signature = Signature.getInstance(keccakSignAlgorithm, PrimusProvider.getProviderName());
		final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initSign(signKey);
		signature.update(payload);
		final byte[] sig = signature.sign();

		// verify
		final Signature verifyer = Signature.getInstance(signAlgorithm);
		verifyer.initVerify(signKeyPublic);
		verifyer.update(payload);
		final boolean signSignatureOk = verifyer.verify(sig);
		System.out.println("sign signature is " + (signSignatureOk ? "OK" : "NOT OK"));

	}

}

