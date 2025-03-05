
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
 *
 * The sample goes through the following steps
 * 1) Logs into the HSM
 * 2) Creates an integrity key
 * 3) Defines rules including timelock and timeout
 * 4) Generates an asset
 * 5) Retrieves access attributes
 * 6) Attempts at signature before the timelock expires
 * 7) Waits for the timelock to expire and attempts at signature again
 */
public class ApprovalTimelockSample {

	public static void main(final String... args) throws Exception {

		// 1 -- HSM AND PROVIDER CONFIGURATION

		// HSM configuration
		PrimusHelper.setup(args);

		// 2 -- CREATE AN INTEGRITY KEY

		// elliptic curve and signature algorithm used with crypto currencies
		final ECGenParameterSpec curve = new ECGenParameterSpec("secp256k1");
		final ECGenParameterSpec curve2 = new ECGenParameterSpec("secp256r1");
		final String signAlgorithm = "SHA256withECDSA";

		// create an integrity key, used by HSM to sign attribute retrievals and time stamps
		// This is something typically done only once or ocasionally
		// and when needed the key is retrieved by its name (as shown below)
		final String integrityKeyName = "integrityKeyName-" + UUID.randomUUID().toString(); // (unique name to avoid test environment clashes)
		final char[] integrityKeyPassword = null; // have no key password with this key; alternatively UUID.randomUUID().toString().toCharArray();
		// typically one wants to directly name generated keys (instead of later use setKeyEntry)
		PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			curve,
			integrityKeyName,
			integrityKeyPassword,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_INTEGRITY, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true)
		);

		// 3 -- DEFINE APPROVAL RULES INCLUDING TIMELOCK AND TIMEOUT

		// Timelock - minimum time between asking for a transaction request (payload) timestamp
		// and submitting an approval
		final long operationTimelock = TimeUnit.MINUTES.toMillis(1);

		// Timeout - maximum time between asking for payload timestamp and submitting the approval
		final long operationTimeout = TimeUnit.MINUTES.toMillis(10);

		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(curve2);
		final KeyPair andrea = localKeyPairGenerator.generateKeyPair();
		final KeyPair beryl = localKeyPairGenerator.generateKeyPair();

		// rules definition similar to previous examples with one major difference - adding timelock
		// and timeout to the access token
		final PrimusAccessBlob operationBlob = new PrimusAccessBlob(new PrimusAccessToken(
				operationTimelock, operationTimeout, new PrimusAccessGroup(new PublicKey[] {andrea.getPublic(), beryl.getPublic(),}, 1)));

		final PrimusAccessBlob blockBlob = new PrimusAccessBlob(new PrimusAccessToken(new PrimusAccessGroup(new PublicKey[] {localKeyPairGenerator.generateKeyPair().getPublic(),}, 1)));
		final PrimusAccessBlob unblockBlob = new PrimusAccessBlob(new PrimusAccessToken(new PrimusAccessGroup(new PublicKey[] {localKeyPairGenerator.generateKeyPair().getPublic(),}, 1)));
		final PrimusAccessBlob modifyBlob = new PrimusAccessBlob(new PrimusAccessToken(new PrimusAccessGroup(new PublicKey[] {localKeyPairGenerator.generateKeyPair().getPublic(),}, 1)));
		final PrimusAccess access = new PrimusAccess(operationBlob, blockBlob, unblockBlob, modifyBlob);

		// 4 -- GENERATE A SIGNATURE KEY USING THESE RULES

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
		// final PublicKey signKeyPublic = signKeyPair.getPublic();

		// 5 -- RETRIEVE ACCESS DATA FROM ATTRIBUTES

		final byte[] attributeData = PrimusKeyAttributes.getAllKeyAttributes(signKeyName, signKeyPassword, integrityKeyName, integrityKeyPassword, signAlgorithm);

		final PrimusAccess access2 = PrimusAccess.getAccessFromKeyAttributes(attributeData);
		final long timelockMinutes = access2.getBlob(PrimusAccess.standardBlobNames()[0]).getTokens()[0].getDelay(TimeUnit.MINUTES);
		System.out.println("timelock is " + timelockMinutes + " minutes");

		// 6 -- UNSUCCESSFUL ATTEMPT AT SIGNATURE

		// some random payload, in reality some crypto currency transaction
		final byte[] payload = new byte[256];
		final SecureRandom localSecureRandom = new SecureRandom();
		localSecureRandom.nextBytes(payload);

		// Prepare authorization token with approvals' private keys
		final byte[] tokenChallengeNoTimeLock = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyName)).getEncoding();
		final PrimusAuthorizationToken tokenNoTimelock = new PrimusAuthorizationToken(tokenChallengeNoTimeLock, signAlgorithm, beryl.getPrivate(), beryl.getPublic());

		final PrimusAuthorization primusAuthorizationNoTimelock = new PrimusAuthorization(tokenNoTimelock);
		PrimusAuthorization.setAuthorization(signKey, primusAuthorizationNoTimelock);

		try {
			final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
			signature.initSign(signKey);
			signature.update(payload);
			signature.sign();

			System.out.println("UNEXPECTED - signature should have thrown error");
		} catch (PrimusProviderException e) {
			System.out.println("signature failed as expected: " + e);
		}

		// 7 - REPEAT ATTEMPT AFTER THE TIMELOCK EXPIRES

		// get signed timestamp for the transaction.
		// This is how the HSM will compare the final approval request with Timelock and Timeout rules
		PrimusSignature.setWantSignatures(true);
		PrimusSignature.setSignatureKey(integrityKeyName);
		final byte[] timestamp = PrimusDevice.getHsmTimestamp(payload, integrityKeyName, integrityKeyPassword, signAlgorithm);
		final PrimusSignature timestampSignature = PrimusSignature.getSignature();
		// the timestamp signature will be verified by the terminal, the timestamp will be used for the authorizationTokenData below

		final PrimusTimestamp primusTimestamp = new PrimusTimestamp(timestamp, timestampSignature);
		System.out.println("timestamp's date is " + new Date(TimeUnit.SECONDS.toMillis(primusTimestamp.getSecondsSinceEpoch())));
		System.out.println("timestamp's signature key name is " + primusTimestamp.getSignatureKeyName());

		// wait for authorizationDelay to pass
		final long sleep = operationTimelock + (operationTimelock > 0 ? TimeUnit.SECONDS.toMillis(1) : 0);
		System.out.println("waiting for authorization delay to pass, " + TimeUnit.MILLISECONDS.toSeconds(sleep) + " seconds");
		Thread.sleep(sleep);

		// Prepare authorization token with approvals' private keys and with specified timelock
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyName, timestamp, timestampSignature)).getEncoding();
		final PrimusAuthorizationToken token = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, beryl.getPrivate(), beryl.getPublic());

		final PrimusAuthorization primusAuthorization = new PrimusAuthorization(token);
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);

		// this time the operation should be permitted
		final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initSign(signKey);
		signature.update(payload);
		signature.sign();
		System.out.println("signature authorization successful");
	}

}
