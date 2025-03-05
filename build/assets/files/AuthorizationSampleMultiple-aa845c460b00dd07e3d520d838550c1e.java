
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
import java.util.concurrent.TimeUnit;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessGroup;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusApprovalToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusAuthorizationException;
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
public class AuthorizationSampleMultiple {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// get the log timestamp, for later log retrieval start point
		final long[] logtimestamp = new long[1];
		PrimusDevice.getHsmLog(logtimestamp);

		final ECGenParameterSpec curve = new ECGenParameterSpec("secp256k1");
		final ECGenParameterSpec curve2 = new ECGenParameterSpec("secp256r1");
		final String signAlgorithm = "SHA256withECDSA";

		final String integrityKeyName = UUID.randomUUID().toString();
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
		final KeyPair a = localKeyPairGenerator.generateKeyPair();
		final KeyPair b = localKeyPairGenerator.generateKeyPair();
		final PublicKey[] pubs = new PublicKey[] {a.getPublic(), b.getPublic(),};

		final long delay = TimeUnit.MINUTES.toMillis(0);
		final long limit = TimeUnit.MINUTES.toMillis(10);
		final PrimusAccessGroup group = new PrimusAccessGroup(pubs, pubs.length / 2);
		final PrimusAccessBlob blob = new PrimusAccessBlob(new PrimusAccessToken(delay, limit, group));
		final PrimusAccess access = new PrimusAccess(blob, blob, blob, blob);

		final String signKeyName = UUID.randomUUID().toString();
		final char[] signKeyPassword = UUID.randomUUID().toString().toCharArray();
		final KeyPair signKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			curve,
			signKeyName,
			signKeyPassword,
			access
		);
		final PrivateKey signKey = signKeyPair.getPrivate();
		final PublicKey signKeyPublic = signKeyPair.getPublic();

		final byte[] payload = new byte[] {2, 3, 5, 7,};

		PrimusSignature.setWantSignatures(true);
		PrimusSignature.setSignatureKey(integrityKeyName);
		final byte[] timestamp = PrimusDevice.getHsmTimestamp(payload, integrityKeyName, (char[])null, signAlgorithm);
		final PrimusSignature timestampSignature = PrimusSignature.getSignature();

		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyName, timestamp, timestampSignature)).getEncoding();
		final PrimusAuthorizationToken token = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, a.getPrivate(), a.getPublic());
		final PrimusAuthorization primusAuthorization = new PrimusAuthorization(token);

		System.out.println();
		System.out.println("signing");
		PrimusAuthorization.setAuthorization(primusAuthorization);
		final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initSign(signKey);
		signature.update(payload);
		final byte[] sig = signature.sign();
		System.out.println("signed");

		final Signature verifyer = Signature.getInstance(signAlgorithm);
		verifyer.initVerify(signKeyPublic);
		verifyer.update(payload);
		final boolean signSignatureOk = verifyer.verify(sig);
		if (!signSignatureOk) {
			throw new RuntimeException("verify failed");
		}
		System.out.println("verifed");

		System.out.println();
		System.out.println("signing again");
		PrimusAuthorization.setAuthorization(primusAuthorization);
		signature.initSign(signKey);
		signature.update(payload);
		final byte[] sig2 = signature.sign();
		System.out.println("signed");

		verifyer.initVerify(signKeyPublic);
		verifyer.update(payload);
		final boolean signSignatureOk2 = verifyer.verify(sig2);
		if (!signSignatureOk2) {
			throw new RuntimeException("verify failed");
		}
		System.out.println("verifed");

		final byte[] payload2 = new byte[] {11, 13, 17, 19,};

		System.out.println();
		System.out.println("tying to sign something different");
		PrimusAuthorization.setAuthorization(primusAuthorization);
		signature.initSign(signKey);
		signature.update(payload2);
		try {
			signature.sign();
			throw new RuntimeException("sign unexpectedly succeeded");
		} catch (final PrimusAuthorizationException e) {
			System.out.println("got expected exception: " + e);
			// com.securosys.primus.jce.PrimusAuthorizationException: eka payload doesn't match approval token's
		}

		System.out.println();
		System.out.println("log:");
		System.out.println(PrimusDevice.getHsmLog(logtimestamp[0]));
		// JULIER info Crypto Process: Sign operation: User: JENKINS, Client: ..., Protocol: jce, Key: 1cb1abf0-8802-49d8-8a9a-2c107253ef22, Authorization: Token1 (Group1: Key1)

	}

}

