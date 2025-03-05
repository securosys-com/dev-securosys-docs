
/*
 * Copyright (C) 2020, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
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

/**
 * Sample code using PrimusAccess/PrimusAuthorization
 * with the Securosys Primus HSM JCE provider
 * and BLS signatures.
 */
public class AuthorizationBlsSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// create local authorization keys; each approval party would generate/have one
		final int n = 4;
		final KeyPair[] authorizationKeys = new KeyPair[n];
		final PublicKey[] authorizationPublicKeys = new PublicKey[n];
		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		for (int i = 0; i < authorizationKeys.length; i++) {
			authorizationKeys[i] = localKeyPairGenerator.generateKeyPair();
			authorizationPublicKeys[i] = authorizationKeys[i].getPublic();
		}

		// key-pair creation with access and flags set:
		final PrimusAccessToken accessToken = new PrimusAccessToken(new PrimusAccessGroup(authorizationPublicKeys, n));
		final PrimusAccessBlob primusAccessBlob = new PrimusAccessBlob(accessToken);
		final PrimusAccess primusAccess = new PrimusAccess(primusAccessBlob, primusAccessBlob, primusAccessBlob, primusAccessBlob);
		// create signing key, which needs approvals for signing
		final String signKeyName = UUID.randomUUID().toString();
		final KeyPair keyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("BLS", PrimusProvider.getProviderName()),
			signKeyName,
			primusAccess,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_SIGN, true),
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_DECRYPT, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_MODIFIABLE, false)
		);
		final PrivateKey signKey = keyPair.getPrivate();
		final PublicKey signKeyPublic = keyPair.getPublic();

		// generate a transaction payload, in real live e.g. a crypto currency transaction
		final int payloadsize = 250;
		final byte[] payload = new byte[payloadsize];
		Arrays.fill(payload, (byte)'A');

		// invoke authorized signing operation:
		// operations will throw PrimusAuthorizationExceptions if authorization is insufficient/expired/etc

		// build a test authorization directly in this code
		// PrimusApprovalToken is build-by-Java only in test code
		// else the tokenChallenge is signed by the approval parties
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload)).getEncoding();

		final PrimusAuthorizationToken[] tokens = new PrimusAuthorizationToken[n];
		for (int i = 0; i < n; i++) {
			tokens[i] = new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", authorizationKeys[i].getPrivate(), authorizationKeys[i].getPublic());
		}

		final PrimusAuthorization primusAuthorization = new PrimusAuthorization();
		for (int i = 0; i < n; i++) {
			primusAuthorization.add(tokens[i]);
		}
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);

		final Signature signature = Signature.getInstance("BLS", PrimusProvider.getProviderName());
		signature.initSign(signKey);
		signature.update(payload);
		final byte[] sig = signature.sign();

		// verify
		signature.initVerify(signKeyPublic);
		signature.update(payload);
		final boolean signSignatureOk = signature.verify(sig);
		System.out.println("sign signature is " + (signSignatureOk ? "OK" : "NOT OK"));

	}

}

