
/*
 * Copyright (C) 2019, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

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
import com.securosys.primus.jce.PrimusSignature;
import com.securosys.primus.jce.PrimusTimestamp;
import com.securosys.primus.jce.PrimusWrap;

/**
 * Sample code using PrimusAccess/PrimusAuthorization
 * with the Securosys Primus HSM JCE provider.
 */
public class AuthorizedUnwrapSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final String signAlgorithm = "SHA256withECDSA";

		// instantiate helper objects
		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC", "SunEC");
		localKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		final Signature localVerifyer = Signature.getInstance(signAlgorithm, "SunEC");

		// create integrity key
		final String integrityKeyName = "integrityKeyName-" + UUID.randomUUID().toString();
		final char[] integrityKeyPassword = integrityKeyName.toCharArray(); // key password just to illustrate
		final KeyPair integrityKeyPair;
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, true);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_INTEGRITY, true);
		try {
			final KeyPairGenerator primusKeyPairGenerator = KeyPairGenerator.getInstance("EC", "SecurosysPrimusXSeries");
			// primusKeyPairGenerator.initialize(new ECGenParameterSpec("secp256k1")); // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
			primusKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
			integrityKeyPair = PrimusName.generateKeyPair(primusKeyPairGenerator, integrityKeyName, integrityKeyPassword);
		} finally {
			PrimusKeyAttributes.clearKeyCapabilityFlags();
			PrimusKeyAttributes.clearKeyAccessFlags();
		}
		final PublicKey integrityKeyPublic = integrityKeyPair.getPublic();
		System.out.println("created integrity key " + integrityKeyName);

		// create authorization keys
		final int n = 4;
		final KeyPair[] authorizationKeys = new KeyPair[n];
		final PublicKey[] authorizationPublicKeys = new PublicKey[n];
		for (int i = 0; i < authorizationKeys.length; i++) {
			authorizationKeys[i] = localKeyPairGenerator.generateKeyPair();
			authorizationPublicKeys[i] = authorizationKeys[i].getPublic();
		}

		// define access
		final int quorum = 2;
		final PrimusAccessGroup accessGroup = new PrimusAccessGroup(authorizationPublicKeys, quorum);
		final long authorizationDelay = TimeUnit.MINUTES.toMillis(0);
		final long authorizationTimelimit = TimeUnit.MINUTES.toMillis(5);
		final PrimusAccessToken accessToken = new PrimusAccessToken(authorizationDelay, authorizationTimelimit, accessGroup, accessGroup);
		final PrimusAccess primusAccess = new PrimusAccess(new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken), new PrimusAccessBlob(accessToken));

		// create a non-extractable RSA unwrap key
		final String unwrapKeyName = UUID.randomUUID().toString();
		final KeyPair keyPair;
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_MODIFIABLE, false);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_SIGN, false);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_DECRYPT, false);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_UNWRAP, true);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_WRAP, true);
		PrimusAccess.setAccess(primusAccess);
		try {
			final KeyPairGenerator primusKeyPairGenerator = KeyPairGenerator.getInstance("RSA", "SecurosysPrimusXSeries");
			primusKeyPairGenerator.initialize(2048);
			keyPair = PrimusName.generateKeyPair(primusKeyPairGenerator, unwrapKeyName, unwrapKeyName.toCharArray());
		} finally {
			PrimusAccess.unsetAccess();
			PrimusKeyAttributes.clearKeyCapabilityFlags();
			PrimusKeyAttributes.clearKeyAccessFlags();
		}
		final PrivateKey unwrapKey = keyPair.getPrivate();
		final PublicKey unwrapKeyPublic = keyPair.getPublic();

		// create a wrapped-extractable AES key
		final String aesKeyName = "aesKeyName-" + UUID.randomUUID().toString();
		final SecretKey aesKey;
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_MODIFIABLE, false);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_SIGN, false);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_DECRYPT, false);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_UNWRAP, false);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_WRAP, false);
		try {
			final KeyGenerator primusKeyGenerator = KeyGenerator.getInstance("AES", "SecurosysPrimusXSeries");
			primusKeyGenerator.init(256);
			aesKey = PrimusName.generateKey(primusKeyGenerator, aesKeyName, aesKeyName.toCharArray());
		} finally {
			PrimusKeyAttributes.clearKeyCapabilityFlags();
			PrimusKeyAttributes.clearKeyAccessFlags();
		}

		// wrap-extract it
		final byte[] wrapped = PrimusWrap.rsaWrapPkcs(unwrapKeyPublic, aesKey);

		final String unwrappedAesKeyName = aesKeyName + "-unwrapped";

		final byte[] payload = PrimusWrap.getUnwrapPayload(wrapped, unwrappedAesKeyName, unwrappedAesKeyName.toCharArray());

		// get signed timestamp:
		PrimusSignature.setWantSignatures(true);
		PrimusSignature.setSignatureKey(integrityKeyName);
		final byte[] timestamp = PrimusDevice.getHsmTimestamp(payload, integrityKeyName, integrityKeyPassword, signAlgorithm);
		final PrimusSignature timestampSignature = PrimusSignature.getSignature();

		// verify timestamp locally
		localVerifyer.initVerify(integrityKeyPublic);
		localVerifyer.update(timestamp);
		final boolean timestampSignatureOk = localVerifyer.verify(timestampSignature.getEncoding());
		System.out.println("timestamp signature is " + (timestampSignatureOk ? "OK" : "not OK"));

		// timestamp object representation:
		final PrimusTimestamp primusTimestamp = new PrimusTimestamp(timestamp, timestampSignature);
		System.out.println("timestamp's date is " + new Date(TimeUnit.SECONDS.toMillis(primusTimestamp.getSecondsSinceEpoch())));
		System.out.println("timestamp's signature key name is " + primusTimestamp.getSignatureKeyName());

		// wait for authorizationDelay to pass
		final long sleep = authorizationDelay + (authorizationDelay > 0 ? TimeUnit.SECONDS.toMillis(10) : 0);
		if (sleep > 0) {
			System.out.println("waiting for authorization delay to pass, " + TimeUnit.MILLISECONDS.toSeconds(sleep) + " seconds");
			Thread.sleep(sleep);
		}

		// invoke authorized unwrap operation:
		// operations will throw PrimusAuthorizationExceptions if authorization is insufficient/expired/etc

		// build a test authorization directly in this code
		// PrimusApprovalToken is build-by-Java only in test code
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_UNWRAP, payload, unwrapKeyName, timestamp, timestampSignature)).getEncoding();
		PrimusAuthorizationToken token0 = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, authorizationKeys[0].getPrivate(), authorizationKeys[0].getPublic());
		PrimusAuthorizationToken token1 = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, authorizationKeys[1].getPrivate(), authorizationKeys[1].getPublic());

		localVerifyer.initVerify(token0.getPublicKey());
		localVerifyer.update(token0.getApprovalTokenBytes());
		final boolean signSignatureOk0 = localVerifyer.verify(token0.getVerifySignatureBytes());
		System.out.println("sign signature is " + (signSignatureOk0 ? "OK" : "not OK"));

		token0 = new PrimusAuthorizationToken(token0.getEncoding()); // mimic authorization tokens coming as byte arrays
		token1 = new PrimusAuthorizationToken(token1.getEncoding());
		final PrimusAuthorization primusAuthorization = new PrimusAuthorization();
		primusAuthorization.add(token0);
		primusAuthorization.add(token1);
		PrimusAuthorization.setAuthorization(unwrapKey, primusAuthorization);

		PrimusName.presetName(unwrappedAesKeyName, unwrappedAesKeyName.toCharArray());
		PrimusWrap.rsaUnwrapPkcs(unwrapKey, wrapped);
		System.out.println("AES key unwrapped");

	}

}

