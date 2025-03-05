
/*
 * Copyright (C) 2019, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
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
import com.securosys.primus.jce.PrimusProviderException;
import com.securosys.primus.jce.PrimusSignature;
import com.securosys.primus.jce.PrimusTimestamp;

/**
 * Sample code using PrimusAccess/PrimusAuthorization
 * with the Securosys Primus HSM JCE provider.
 */
public class AuthorizationRsaSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final String deviceName = PrimusDevice.getDeviceName();
		System.out.println("HSM device name is " + deviceName);

		final int keySize = 2048;
		final String signAlgorithm = "SHA256withRSA";

		// create integrity key, used by HSM to sign attribute retrievals and time stamps
		final String integrityKeyName = "integrityKeyName-" + UUID.randomUUID().toString();
		final char[] integrityKeyPassword = null; // have no key password with this key; alternatively UUID.randomUUID().toString().toCharArray();
		// typically one wants to directly name generated keys (instead of later use setKeyEntry)
		final KeyPair integrityKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName()),
			keySize,
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
		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
		localKeyPairGenerator.initialize(keySize);
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
		// note: time delays/limits require timestamp based approvals
		final long authorizationDelay = TimeUnit.MINUTES.toMillis(0);
		final long authorizationTimelimit = TimeUnit.MINUTES.toMillis(5);
		final PrimusAccessToken accessToken = new PrimusAccessToken(authorizationDelay, authorizationTimelimit, accessGroup);
		final PrimusAccess primusAccess = new PrimusAccess(new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken));

		final String signKeyName = UUID.randomUUID().toString();
		final char[] signKeyPassword = UUID.randomUUID().toString().toCharArray();
		final KeyPair signKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName()),
			keySize,
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
		final byte[] attributeData = PrimusKeyAttributes.getAllKeyAttributes(signKeyName, signKeyPassword, integrityKeyName, integrityKeyPassword, signAlgorithm);
		final byte[] attributeSignature = PrimusSignature.getSignature().getEncoding();

		final Signature localVerifyer = Signature.getInstance(signAlgorithm);
		localVerifyer.initVerify(integrityKeyPublic);
		localVerifyer.update(attributeData);
		final boolean attributeSignatureOk = localVerifyer.verify(attributeSignature);
		System.out.println("attribute signature is " + (attributeSignatureOk ? "OK" : "NOT OK"));

		final PrimusAccess access = PrimusAccess.getAccess(signKey, integrityKeyName, integrityKeyPassword, signAlgorithm);
		System.out.println("read key access back: " + access);

		// some random payload, in reality some crypto currency transaction
		final byte[] payload = new byte[256];
		final SecureRandom localSecureRandom = new SecureRandom();
		localSecureRandom.nextBytes(payload);

		// get signed timestamp for the approved transaction
		PrimusSignature.setWantSignatures(true);
		PrimusSignature.setSignatureKey(integrityKeyName);
		final byte[] timestamp = PrimusDevice.getHsmTimestamp(payload, integrityKeyName, integrityKeyPassword, signAlgorithm);
		final PrimusSignature timestampSignature = PrimusSignature.getSignature();
		// the timestamp signature will be verified by the terminal, the timestamp will be used for the authorizationTokenData below

		// verify locally
		localVerifyer.initVerify(integrityKeyPublic);
		localVerifyer.update(timestamp);
		final boolean timestampSignatureOk = localVerifyer.verify(timestampSignature.getEncoding());
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
		PrimusAuthorizationToken token0 = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, authorizationKeys[0].getPrivate(), authorizationKeys[0].getPublic());
		PrimusAuthorizationToken token1 = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, authorizationKeys[1].getPrivate(), authorizationKeys[1].getPublic());

		localVerifyer.initVerify(token0.getPublicKey());
		localVerifyer.update(token0.getApprovalTokenBytes());
		final boolean signSignatureOk0 = localVerifyer.verify(token0.getVerifySignatureBytes());
		System.out.println("sign signature is " + (signSignatureOk0 ? "OK" : "NOT OK"));

		// mimic authorization tokens coming as byte arrays, as possibly done by non-Java approval devices
		token0 = new PrimusAuthorizationToken(token0.getEncoding());
		token1 = new PrimusAuthorizationToken(token1.getEncoding());
		final PrimusAuthorization primusAuthorization = new PrimusAuthorization();
		primusAuthorization.add(token0);
		primusAuthorization.add(token1);
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);
		final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initSign(signKey);
		signature.update(payload);
		final byte[] sig = signature.sign();

		// verify locally
		localVerifyer.initVerify(signKeyPublic);
		localVerifyer.update(payload);
		final boolean signSignatureOk = localVerifyer.verify(sig);
		System.out.println("sign signature is " + (signSignatureOk ? "OK" : "NOT OK"));

		// sign key block test
		System.out.println("sign key is blocked: " + PrimusKeyAttributes.getKeyAccessFlag(signKey, PrimusKeyAttributes.ACCESS_BLOCKED));
		final byte[] nopayload = null;
		PrimusAuthorization.setAuthorization(signKey, new PrimusAuthorization(
			new PrimusAuthorizationToken((new PrimusApprovalToken(PrimusApprovalToken.OPERATION_BLOCK, nopayload, signKeyName, timestamp, timestampSignature)).getEncoding(), signAlgorithm, authorizationKeys[0].getPrivate(), authorizationKeys[0].getPublic()),
			new PrimusAuthorizationToken((new PrimusApprovalToken(PrimusApprovalToken.OPERATION_BLOCK, nopayload, signKeyName, timestamp, timestampSignature)).getEncoding(), signAlgorithm, authorizationKeys[1].getPrivate(), authorizationKeys[1].getPublic())
		));
		PrimusKeyAttributes.setKeyAccessFlag(signKey, PrimusKeyAttributes.ACCESS_BLOCKED, true);
		System.out.println("sign key is blocked: " + PrimusKeyAttributes.getKeyAccessFlag(signKey, PrimusKeyAttributes.ACCESS_BLOCKED));

		System.out.println("trying to sign now ...");
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);
		signature.initSign(signKey);
		try {
			signature.sign();
			System.out.println("UNEXPECTED");
		} catch (PrimusProviderException e) {
			System.out.println("expected: " + e);
		}

		// sign key unblock test
		PrimusAuthorization.setAuthorization(signKey, new PrimusAuthorization(
			new PrimusAuthorizationToken((new PrimusApprovalToken(PrimusApprovalToken.OPERATION_UNBLOCK, nopayload, signKeyName, timestamp, timestampSignature)).getEncoding(), signAlgorithm, authorizationKeys[0].getPrivate(), authorizationKeys[0].getPublic()),
			new PrimusAuthorizationToken((new PrimusApprovalToken(PrimusApprovalToken.OPERATION_UNBLOCK, nopayload, signKeyName, timestamp, timestampSignature)).getEncoding(), signAlgorithm, authorizationKeys[1].getPrivate(), authorizationKeys[1].getPublic())
		));
		PrimusKeyAttributes.setKeyAccessFlag(signKey, PrimusKeyAttributes.ACCESS_BLOCKED, false);

		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);
		signature.initSign(signKey);
		signature.sign();

		localVerifyer.initVerify(signKeyPublic);
		localVerifyer.update(payload);
		System.out.println("sign signature is " + (localVerifyer.verify(sig) ? "OK" : "NOT OK"));

		// test with insufficient number of approvals
		final byte[] tokenChallenge1 = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyName, timestamp, timestampSignature)).getEncoding();
		final PrimusAuthorizationToken token00 = new PrimusAuthorizationToken(tokenChallenge1, signAlgorithm, authorizationKeys[0].getPrivate(), authorizationKeys[0].getPublic());
		final PrimusAuthorization primusAuthorization1 = new PrimusAuthorization(token00);
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization1);
		try {
			signature.initSign(signKey);
			signature.sign();
			System.out.println("UNEXPECTED");
		} catch (PrimusProviderException e) {
			System.out.println("expected: " + e);
		}

		// modify access
		// generate a changed access with the lower quorum
		final int quorum2 = 1;
		final PrimusAccessGroup accessGroup2 = new PrimusAccessGroup(authorizationPublicKeys, quorum2);
		final PrimusAccessToken accessToken2 = new PrimusAccessToken(accessGroup2);
		final PrimusAccessBlob blob2 = new PrimusAccessBlob(accessToken2);
		final PrimusAccess primusAccess2 = new PrimusAccess(blob2, blob2, blob2, blob2);
		final byte[] payload2 = PrimusAccess.getModifyAccessPayload(signKey, primusAccess2);
		final byte[] timestamp2 = PrimusDevice.getHsmTimestamp(PrimusApprovalToken.OPERATION_MODIFY, payload2, integrityKeyName, integrityKeyPassword, signAlgorithm);
		final PrimusSignature timestampSignature2 = PrimusSignature.getSignature();
		// wait for possible authorizationDelay to pass
		if (sleep > 0) {
			System.out.println("waiting for authorization delay to pass, " + TimeUnit.MILLISECONDS.toSeconds(sleep) + " seconds");
			Thread.sleep(sleep);
		}
		// approval for modify operation
		final byte[] tokenChallenge2 = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_MODIFY, payload2, signKeyName, timestamp2, timestampSignature2)).getEncoding();
		final PrimusAuthorizationToken token20 = new PrimusAuthorizationToken(tokenChallenge2, signAlgorithm, authorizationKeys[0].getPrivate(), authorizationKeys[0].getPublic());
		final PrimusAuthorizationToken token21 = new PrimusAuthorizationToken(tokenChallenge2, signAlgorithm, authorizationKeys[1].getPrivate(), authorizationKeys[1].getPublic());
		final PrimusAuthorization authorization2 = new PrimusAuthorization(token20, token21);
		PrimusAuthorization.setAuthorization(signKey, authorization2);
		// modify
		PrimusAccess.modifyAccess(signKey, primusAccess2);

		PrimusAuthorization.setAuthorization(signKey, primusAuthorization1);
		signature.initSign(signKey);
		signature.sign();

	}

}

