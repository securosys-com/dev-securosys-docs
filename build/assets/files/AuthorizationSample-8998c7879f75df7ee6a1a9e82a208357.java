
/*
 * Copyright (C) 2017-2020, Securosys SA
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
 *
 * The sample:
 *
 * - logs into the HSM
 * - creates an integrity key
 * - creates local approval keys
 * - assembles an access
 * - creates a sign key that needs approvals
 * - gets a timestamp
 * - does an approved signature
 */
public class AuthorizationSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// elliptic curve and signature algorithm used with crypto currencies
		// note: in this sample all 3 types of keys (integrity key, approval keys, sign key) use the same key type (EC) and curve (secp256k1)
		final ECGenParameterSpec curve = new ECGenParameterSpec("secp256k1");
		final ECGenParameterSpec curve2 = new ECGenParameterSpec("secp256r1");
		final String signAlgorithm = "SHA256withECDSA";
		// final String keccakSignAlgorithm = "KECCAK256withECDSA"; // alternative sign algorithm

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

		// locally create authorization keys, different persons, for different roles
		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(curve2);
		final KeyPair andrea = localKeyPairGenerator.generateKeyPair();
		final KeyPair beryl = localKeyPairGenerator.generateKeyPair();
		final KeyPair chantal = localKeyPairGenerator.generateKeyPair();
		final KeyPair debby = localKeyPairGenerator.generateKeyPair();
		// operation role: sign/unwrap
		final PublicKey[] operationPublicKeys = new PublicKey[] {andrea.getPublic(), beryl.getPublic(), chantal.getPublic(), debby.getPublic(),};
		final KeyPair erin = localKeyPairGenerator.generateKeyPair();
		final KeyPair florence = localKeyPairGenerator.generateKeyPair();
		final KeyPair gabrielle = localKeyPairGenerator.generateKeyPair();
		// block/unblock role (see other sample for use)
		final PublicKey[] riskOfficersPublicKeys = new PublicKey[] {erin.getPublic(), florence.getPublic(), gabrielle.getPublic(),};
		final KeyPair helene = localKeyPairGenerator.generateKeyPair();
		// modify role (see other sample for use)
		final PublicKey[] superBossPublicKey = new PublicKey[] {helene.getPublic(),};

		final long operationAuthorizationDelay = TimeUnit.MINUTES.toMillis(0);
		final long operationAuthorizationTimelimit = TimeUnit.MINUTES.toMillis(10);
		final PrimusAccessGroup operationGroup = new PrimusAccessGroup(operationPublicKeys, operationPublicKeys.length / 2);
		final PrimusAccessBlob operationBlob = new PrimusAccessBlob(new PrimusAccessToken(operationAuthorizationDelay, operationAuthorizationTimelimit, operationGroup));
		final PrimusAccessGroup blockGroup = new PrimusAccessGroup(riskOfficersPublicKeys, 1);
		final PrimusAccessBlob blockBlob = new PrimusAccessBlob(new PrimusAccessToken(blockGroup));
		final PrimusAccessGroup unblockGroup = new PrimusAccessGroup(riskOfficersPublicKeys, riskOfficersPublicKeys.length);
		final PrimusAccessBlob unblockBlob = new PrimusAccessBlob(new PrimusAccessToken(unblockGroup));
		final PrimusAccessGroup modifyGroup = new PrimusAccessGroup(superBossPublicKey, 1);
		final PrimusAccessBlob modifyBlob = new PrimusAccessBlob(new PrimusAccessToken(modifyGroup));
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
		// at least quorum out of operation role
		PrimusAuthorizationToken token0 = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, beryl.getPrivate(), beryl.getPublic());
		PrimusAuthorizationToken token1 = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, chantal.getPrivate(), chantal.getPublic());

		localVerifyer.initVerify(token0.getPublicKey());
		localVerifyer.update(token0.getApprovalTokenBytes());
		final boolean signSignatureOk0 = localVerifyer.verify(token0.getVerifySignatureBytes());
		System.out.println("sign signature is " + (signSignatureOk0 ? "OK" : "NOT OK"));

		// mimic authorization tokens coming as byte arrays
		token0 = new PrimusAuthorizationToken(token0.getEncoding());
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

		// verify locally
		// [can't do that with keccakSignAlgorithm]
		localVerifyer.initVerify(signKeyPublic);
		localVerifyer.update(payload);
		final boolean signSignatureOk = localVerifyer.verify(sig);
		System.out.println("sign signature is " + (signSignatureOk ? "OK" : "NOT OK"));

	}

}

