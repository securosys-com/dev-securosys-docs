
/*
 * Copyright (C) 2019,2020, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.Certificate;
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
import com.securosys.primus.tool2.KeyToolX;

/**
 * Sample code using PrimusAccess/PrimusAuthorization
 * with the Securosys Primus HSM JCE provider.
 */
public class AuthorizationCertificatesMixedSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.keepPasswords(true);
		PrimusHelper.setup(args);

		// elliptic curve and signature algorithm used with crypto currencies
		// note: in this sample all 3 types of keys (integrity key, approval keys, sign key) use the same key type (EC) and curve (secp256k1)
		// final ECGenParameterSpec curve = new ECGenParameterSpec("secp256k1"); // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
		final ECGenParameterSpec curve = new ECGenParameterSpec("secp256r1");
		final String signAlgorithm = "SHA256withECDSA";

		// create integrity key, used by HSM to sign attribute retrievals and time stamps
		final String integrityKeyName = "integrityKeyName-" + UUID.randomUUID().toString(); // (unique name to avoid test environment clashes)
		final char[] integrityKeyPassword = null; // have no key password with this key; alternatively UUID.randomUUID().toString().toCharArray();
		// typically one wants to directly name generated keys (instead of later use setKeyEntry)
		final KeyPair integrityKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			curve,
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
		// [write to file]
		// try (final OutputStream outFile = new FileOutputStream(integrityKeyName + "-publickey")) {
		//	outFile.write(integrityKeyEncoding);
		// }
		System.out.println("public integrity key part is " + Base64.getEncoder().encodeToString(integrityKeyEncoding));
		// [mimic integrity public key coming as byte array]
		// final PublicKey integrityKeyPublic2 = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(integrityKeyEncoding));
		// System.out.println("public integrity key part is " + Base64.getEncoder().encodeToString(integrityKeyPublic2.getEncoded()));

		// create authorization keys and self signed certificates
		final KeyStore keystore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keystore.load(null);
		final String namebase = UUID.randomUUID().toString();
		final String andrea = "andrea" + "-" + namebase;
		KeyToolX.invokeThrow(
			"-host", PrimusHelper.host, "-port", PrimusHelper.port,
			"-user", PrimusHelper.user, "-password", String.valueOf(PrimusHelper.password),
			"-genkeypair", "-alias", andrea, "-keypass", andrea,
			"-keyalg", "EC", "-keysize", "256", "-sigalg", "SHA256withECDSA",
			"-dname", "CN=" + andrea, "-validity", "3650"
		);
		final PrivateKey akey = (PrivateKey)keystore.getKey(andrea, andrea.toCharArray());
		final Certificate acert = keystore.getCertificate(andrea);
		final String beryl = "beryl" + "-" + namebase;
		KeyToolX.invokeThrow(
			"-host", PrimusHelper.host, "-port", PrimusHelper.port,
			"-user", PrimusHelper.user, "-password", String.valueOf(PrimusHelper.password),
			"-genkeypair", "-alias", beryl, "-keypass", beryl,
			"-keyalg", "EC", "-keysize", "256", "-sigalg", "SHA256withECDSA",
			"-dname", "CN=" + beryl, "-validity", "3650"
		);
		final Certificate bcert = keystore.getCertificate(beryl);

		@SuppressWarnings("deprecation")
		final PublicKey[] operationCertificates = new PublicKey[] {
			(PublicKey)PrimusAccessGroup.getNamedCertificate("andrea", acert),
			PrimusAccessGroup.getNamedPublicKey("beryl", bcert.getPublicKey()),
		};

		final long operationAuthorizationDelay = TimeUnit.MINUTES.toMillis(0);
		final long operationAuthorizationTimelimit = TimeUnit.MINUTES.toMillis(10);
		final PrimusAccessGroup operationGroup = new PrimusAccessGroup(operationCertificates, operationCertificates.length / 2);
		final PrimusAccessBlob operationBlob = new PrimusAccessBlob(new PrimusAccessToken(operationAuthorizationDelay, operationAuthorizationTimelimit, operationGroup));
		final PrimusAccessBlob blockBlob = operationBlob;
		final PrimusAccessBlob unblockBlob = operationBlob;
		final PrimusAccessBlob modifyBlob = operationBlob;
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

		// retrieve attributes and verify their signature locally
		final byte[] attributeData = PrimusKeyAttributes.getAllKeyAttributes(signKeyName, signKeyPassword, integrityKeyName, integrityKeyPassword, signAlgorithm);
		final byte[] attributeSignature = PrimusSignature.getSignature().getEncoding();

		final Signature localVerifyer = Signature.getInstance(signAlgorithm);
		localVerifyer.initVerify(integrityKeyPublic);
		localVerifyer.update(attributeData);
		final boolean attributeSignatureOk = localVerifyer.verify(attributeSignature);
		System.out.println("attribute signature is " + (attributeSignatureOk ? "OK" : "NOT OK"));

		final PrimusAccess access2 = PrimusAccess.getAccess(signKey, integrityKeyName, integrityKeyPassword, signAlgorithm);
		System.out.println("read key access back: " + access2);

		// some random payload, in reality some crypto currency transaction
		final byte[] payload = new byte[256];
		final SecureRandom localSecureRandom = new SecureRandom();
		localSecureRandom.nextBytes(payload);

		// get signed timestamp for the transaction
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

		final PrimusTimestamp primusTimestamp = new PrimusTimestamp(timestamp, timestampSignature);
		System.out.println("timestamp's date is " + new Date(TimeUnit.SECONDS.toMillis(primusTimestamp.getSecondsSinceEpoch())));
		System.out.println("timestamp's signature key name is " + primusTimestamp.getSignatureKeyName());

		// wait for possible authorizationDelay to pass
		final long sleep = operationAuthorizationDelay + (operationAuthorizationDelay > 0 ? TimeUnit.SECONDS.toMillis(10) : 0);
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
		PrimusAuthorizationToken token0 = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, akey, acert.getPublicKey());

		localVerifyer.initVerify(token0.getPublicKey());
		localVerifyer.update(token0.getApprovalTokenBytes());
		final boolean signSignatureOk0 = localVerifyer.verify(token0.getVerifySignatureBytes());
		System.out.println("sign signature is " + (signSignatureOk0 ? "OK" : "NOT OK"));

		// mimic authorization tokens coming as byte arrays
		token0 = new PrimusAuthorizationToken(token0.getEncoding());
		final PrimusAuthorization primusAuthorization = new PrimusAuthorization();
		primusAuthorization.add(token0);
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

	}

}

