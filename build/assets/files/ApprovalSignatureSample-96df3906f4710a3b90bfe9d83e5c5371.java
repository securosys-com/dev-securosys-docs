
/*
 * Copyright (C) 2017-2019, Securosys SA
 */

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessGroup;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusApprovalToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusAuthorizationException;
import com.securosys.primus.jce.PrimusAuthorizationToken;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code using PrimusAccess/PrimusAuthorization
 * with the Securosys Primus HSM JCE provider.
 *
 * The sample goes through the following steps:
 * 1) log in to the hsm
 * 2) create approval keys
 * 3) create a signature key with rules using the approval keys
 * 5) get a payload
 * 6) verify approvals
 * 7) attempt a signature with insufficient quorum
 * 8) attempt a signature with sufficient quorum
 */
public class ApprovalSignatureSample {

	public static void main(final String... args) throws Exception {

		// 1 - HSM AND PROVIDER CONFIGURATION

		// HSM configuration
		PrimusHelper.setup(args);

		// 2 - CREATE APPROVAL KEYS

		// create local approval keys (at least 3 for this test to make sense)
		// Note: This would typically be done elsewhere and only public keys would be used to create and validate rules

		final int numberOfApprovalKeys = 3;
		final KeyPair[] approvalKeys = new KeyPair[numberOfApprovalKeys];
		final PublicKey[] approvalPublicKeys = new PublicKey[numberOfApprovalKeys];
		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		for (int i = 0; i < approvalKeys.length; i++) {
			approvalKeys[i] = localKeyPairGenerator.generateKeyPair();
			approvalPublicKeys[i] = approvalKeys[i].getPublic();
			// mimic public keys coming as byte array
			approvalPublicKeys[i] = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(approvalPublicKeys[i].getEncoded()));
			// give them a name
			approvalPublicKeys[i] = PrimusAccessGroup.getNamedPublicKey("pub#" + i, approvalPublicKeys[i]);
		}

		// 3 - CREATE A KEY WITH APPROVAL RULES

		// An approval rule can have more tokens (with OR relationship)
		// and each token can have more groups (with AND relationship)
		final int quorum = numberOfApprovalKeys - 1;
		final PrimusAccessGroup accessGroup = new PrimusAccessGroup("group#0", approvalPublicKeys, quorum);
		final PrimusAccessToken accessToken = new PrimusAccessToken("token#0", accessGroup);
		final PrimusAccessBlob primusAccessBlob = new PrimusAccessBlob(accessToken);
		final PrimusAccess primusAccess = new PrimusAccess(primusAccessBlob, primusAccessBlob, primusAccessBlob, primusAccessBlob);

		// create a signing (asset) key, which needs approvals for signing
		final String signKeyName = UUID.randomUUID().toString();
		final KeyPair keyPair = (KeyPair)PrimusName.generate(
			// using typical Bitcoin EC algorithm here
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			// new ECGenParameterSpec("secp256k1"), // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
			new ECGenParameterSpec("secp256r1"),
			signKeyName,
			primusAccess,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_SIGN, true),
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_DECRYPT, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_MODIFIABLE, false)
		);
		final PrivateKey signKey = keyPair.getPrivate();
		// final PublicKey signKeyPublic = keyPair.getPublic();

		// 4 - CREATE A DUMMY PAYLOAD

		// Generate a transaction payload, in real live e.g. a crypto currency transaction
		final int payloadsize = 250; // to test below code: use something %4!=0
		final byte[] payload = new byte[payloadsize];
		Arrays.fill(payload, (byte)'A');

		// 5 - BUILD AN AUTHORIZATION

		// This would normally be done by the approval terminals who would only
		// submit authorization tokens probably as byte streams (done as the last step of this section)

		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload)).getEncoding();

		final PrimusAuthorizationToken[] tokens = new PrimusAuthorizationToken[numberOfApprovalKeys];
		for (int i = 0; i < numberOfApprovalKeys; i++) {
			tokens[i] = new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", approvalKeys[i].getPrivate(), approvalKeys[i].getPublic());
			// mimic authorization tokens coming as byte arrays
			tokens[i] = new PrimusAuthorizationToken(tokens[i].getEncoding());
		}

		// 6 - VERIFY APPROVALS LOCALLY
		// this is optional, but best practice

		Signature localVerifier = Signature.getInstance("SHA256withECDSA");

		for (int i = 0; i < numberOfApprovalKeys; i++) {
			// feed the local verifier with approval bytes (normally provided from the terminals) and public key
			localVerifier.initVerify(tokens[i].getPublicKey());
			localVerifier.update(tokens[i].getApprovalTokenBytes());

			// verify the approval's signature
			final boolean signSignatureOk = localVerifier.verify(tokens[i].getVerifySignatureBytes());
			System.out.println("sign signature #" + i + " is " + (signSignatureOk ? "OK" : "NOT OK"));
		}

		// 7 - ATTEMPT KEY OPERATION WITH INSUFFICIENT QUORUM

		final PrimusAuthorization unsuccessfulAuthorization = new PrimusAuthorization();

		// pick a single random approval key and add it to the authorization
		Random randomGenerator = new Random();
		int keyIndex = randomGenerator.nextInt(numberOfApprovalKeys);
		unsuccessfulAuthorization.add(tokens[keyIndex]);

		// add the signature (asset) key to the authorization
		PrimusAuthorization.setAuthorization(signKey, unsuccessfulAuthorization);

		try {
			// a typical cryptocurrency EC algorithm is used here
			Signature signature = Signature.getInstance("SHA256withECDSA", PrimusProvider.getProviderName());
			signature.initSign(signKey);
			signature.update(payload);
			// this will throw an exception because we used only one key, which is less than quorum
			signature.sign();
		} catch (PrimusAuthorizationException e) {
			System.out.println(e.getMessage());
		}

		// 8 - PERFORM SUCCESSFULLY AUTHORIZED SIGNATURE

		final PrimusAuthorization successfulAuthorization = new PrimusAuthorization();

		// randomly select n out of m keys for approval
		final boolean[] usedKeyIds = new boolean[numberOfApprovalKeys];
		int usedKeysCount = 0;
		do {
			keyIndex = randomGenerator.nextInt(numberOfApprovalKeys);
			if (!usedKeyIds[keyIndex]) {
				successfulAuthorization.add(tokens[keyIndex]);
				usedKeyIds[keyIndex] = true;
				usedKeysCount++;
			}
		} while (usedKeysCount < quorum);

		PrimusAuthorization.setAuthorization(signKey, successfulAuthorization);

		final Signature signature = Signature.getInstance("SHA256withECDSA", PrimusProvider.getProviderName());
		signature.initSign(signKey);
		signature.update(payload);
		signature.sign();
		System.out.println("Success!");
	}

}

