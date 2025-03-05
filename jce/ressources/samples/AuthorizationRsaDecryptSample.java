
/*
 * Copyright (C) 2020, Securosys SA
 */

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;

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
public class AuthorizationRsaDecryptSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final int keySize = 2048;
		final String signAlgorithm = "SHA256withRSA";
		// final String encypherAlgorithm = "RSA/ECB/PKCS1Padding";
		final String encypherAlgorithm = "RSA/ECB/OAEPwith256andMGF1padding";

		// create integrity key, used by HSM to sign attribute retrievals and time stamps
		final String integrityKeyName = "integrityKeyName-" + UUID.randomUUID().toString();
		final char[] integrityKeyPassword = null; // have no key password with this key; alternatively UUID.randomUUID().toString().toCharArray();
		// typically one wants to directly name generated keys (instead of later use setKeyEntry)
		PrimusName.generate(
			KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName()),
			keySize,
			integrityKeyName,
			integrityKeyPassword,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_INTEGRITY, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true)
		);

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

		final String keyName = UUID.randomUUID().toString();
		final char[] keyPassword = UUID.randomUUID().toString().toCharArray();
		final KeyPair keyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName()),
			keySize,
			keyName,
			keyPassword,
			primusAccess,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_SIGN, true),
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_DECRYPT, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_MODIFIABLE, false)
		);
		final PrivateKey key = keyPair.getPrivate();
		final PublicKey keyPublic = keyPair.getPublic();

		final byte[] message = "hi, there\n".getBytes(StandardCharsets.UTF_8);

		final Cipher localCipher = Cipher.getInstance(encypherAlgorithm);
		localCipher.init(Cipher.ENCRYPT_MODE, keyPublic);
		final byte[] encrypted = localCipher.doFinal(message);

		final byte[] payload = encrypted;

		// get signed timestamp for the approved transaction
		PrimusSignature.setWantSignatures(true);
		PrimusSignature.setSignatureKey(integrityKeyName);
		final byte[] timestamp = PrimusDevice.getHsmTimestamp(payload, integrityKeyName, integrityKeyPassword, signAlgorithm);
		final PrimusSignature timestampSignature = PrimusSignature.getSignature();
		// the timestamp signature will be verified by the terminal, the timestamp will be used for the authorizationTokenData below

		// wait for possible authorizationDelay to pass
		final long sleep = authorizationDelay + (authorizationDelay > 0 ? TimeUnit.SECONDS.toMillis(10) : 0);
		if (sleep > 0) {
			System.out.println("waiting for authorization delay to pass, " + TimeUnit.MILLISECONDS.toSeconds(sleep) + " seconds");
			Thread.sleep(sleep);
		}

		// invoke authorized signing operation:
		// operations will throw PrimusAuthorizationExceptions if authorization is insufficient/expired/etc

		// build a test authorization directly in this code
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_OPERATION, payload, keyName, timestamp, timestampSignature)).getEncoding();
		final PrimusAuthorization primusAuthorization = new PrimusAuthorization(
				new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, authorizationKeys[0].getPrivate(), authorizationKeys[0].getPublic()),
				new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, authorizationKeys[1].getPrivate(), authorizationKeys[1].getPublic())
		);
		PrimusAuthorization.setAuthorization(key, primusAuthorization);

		final Cipher cipher = Cipher.getInstance(encypherAlgorithm, PrimusProvider.getProviderName());
		cipher.init(Cipher.DECRYPT_MODE, key);
		final byte[] decrypted = cipher.doFinal(encrypted);
		assert Arrays.equals(decrypted, message);

	}

}

