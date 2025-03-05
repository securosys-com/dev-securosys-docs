
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
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
import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSignature;
import com.securosys.primus.jce.PrimusTimestamp;

// [internal APIs]
import com.securosys.primus.jce.encoding.DERObject;
import com.securosys.primus.jce.serialization.PayloadPart;
import com.securosys.primus.jce.spi2.Access;

/**
 * Illustrate PrimusAuthorization dissection
 * with the Securosys Primus HSM JCE provider.
 */
public class AuthorizationSerializationSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// elliptic curve and signature algorithm used with crypto currencies
		final ECGenParameterSpec curve = new ECGenParameterSpec("secp256k1");
		final ECGenParameterSpec curve2 = new ECGenParameterSpec("secp256r1");
		final String signAlgorithm = "SHA256withECDSA";

		// create integrity key, used by HSM to sign time stamps
		final String integrityKeyName = "integrity-key-" + UUID.randomUUID().toString(); // (unique name to avoid test environment clashes)
		final KeyPair integrityKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			curve2,
			integrityKeyName,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_INTEGRITY, true)
		);
		final PublicKey integrityKeyPublic = integrityKeyPair.getPublic();

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

		// access attributes
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

		// generate a SKA key, that needs approvals for the sign operation
		final String signKeyName = "sign-key-" + UUID.randomUUID().toString(); // (unique name to avoid test environment clashes)
		final KeyPair signKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			curve,
			signKeyName,
			access
		);
		final PrivateKey signKey = signKeyPair.getPrivate();
		final PublicKey signKeyPublic = signKeyPair.getPublic();

		// some random payload, in reality some crypto currency transaction
		final byte[] payload = new byte[] {'A', 'B', 'C', 'D',};

		// [diagnostics]
		System.out.println("payload is " + PrimusEncoding.hexEncode(payload));

		// get signed timestamp for the signature/transaction
		PrimusSignature.setWantSignatures(true);
		PrimusSignature.setSignatureKey(integrityKeyName);
		final byte[] timestamp = PrimusDevice.getHsmTimestamp(payload, integrityKeyName, (char[])null, signAlgorithm);
		final PrimusSignature timestampSignature = PrimusSignature.getSignature();
		assert timestamp != null;
		assert timestampSignature != null;

		// verify the timestamp signature locally
		final Signature localVerifyer = Signature.getInstance(signAlgorithm);
		localVerifyer.initVerify(integrityKeyPublic);
		localVerifyer.update(timestamp);
		assert localVerifyer.verify(timestampSignature.getEncoding());

		// [dissecting diagnostics, uses internal APIs]
		System.out.println("timestamp is " + PrimusEncoding.hexEncode(timestamp));
		final byte[] ts0 = Access.cutlengthheader(timestamp);
		System.out.println("without length header: " + PrimusEncoding.hexEncode(ts0));
		final ByteBuffer bb0 = ByteBuffer.wrap(ts0);
		final PayloadPart payload0 = PayloadPart.deserializeFrom(bb0);
		System.out.println(payload0 + " " + PrimusEncoding.hexEncode(payload0.getData())); // EkaSignPayload, payload
		assert Arrays.equals(payload0.getData(), payload);
		final PayloadPart epoch0 = PayloadPart.deserializeFrom(bb0);
		System.out.println(epoch0 + " " + PrimusEncoding.hexEncode(epoch0.getData())); // SecondsSinceEpoch
		System.out.println(new Date(TimeUnit.SECONDS.toMillis(ByteBuffer.wrap(epoch0.getData()).order(ByteOrder.LITTLE_ENDIAN).getLong())));
		final PayloadPart timestampkey0 = PayloadPart.deserializeFrom(bb0);
		System.out.println(timestampkey0 + " " + (new String(timestampkey0.getData(), StandardCharsets.UTF_8))); // LabelUtf8String, integrityKeyName
		assert (new String(timestampkey0.getData(), StandardCharsets.UTF_8)).equals(integrityKeyName);
		// assert !bb0.hasRemaining();

		// [diagnostics]
		final PrimusTimestamp primusTimestamp = new PrimusTimestamp(timestamp, timestampSignature);
		System.out.println("timestamp's date is " + new Date(TimeUnit.SECONDS.toMillis(primusTimestamp.getSecondsSinceEpoch())));
		System.out.println("timestamp's signature key name is " + primusTimestamp.getSignatureKeyName());
		System.out.println("timestamp's payload is " + PrimusEncoding.hexEncode(primusTimestamp.getPayload()));

		// [dissecting diagnostics, uses internal APIs]
		System.out.println("timestamp signature is " + PrimusEncoding.hexEncode(timestampSignature.getEncoding()));
		System.out.println("timestamp signature DER/ASN.1 encoding is " + DERObject.valueOf(timestampSignature.getEncoding()));

		// wait for possible authorizationDelay to pass
		final long sleep = operationAuthorizationDelay + (operationAuthorizationDelay > 0 ? TimeUnit.SECONDS.toMillis(10) : 0);
		if (sleep > 0) {
			System.out.println("waiting for authorization delay to pass, " + TimeUnit.MILLISECONDS.toSeconds(sleep) + " seconds");
			Thread.sleep(sleep);
		}

		// authorization
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyName, timestamp, timestampSignature)).getEncoding();

		// [diagnostics]
		System.out.println();
		System.out.println("token challenge is " + PrimusEncoding.hexEncode(tokenChallenge));

		// [dissecting diagnostics, uses internal APIs]
		final byte[] tc2 = Access.cutlengthheader(tokenChallenge);
		System.out.println("without length header: " + PrimusEncoding.hexEncode(tc2));
		final ByteBuffer bb = ByteBuffer.wrap(tc2);
		//
		final PayloadPart operationEnc = PayloadPart.deserializeFrom(bb);
		System.out.println(operationEnc + " " + ByteBuffer.wrap(operationEnc.getData()).order(ByteOrder.LITTLE_ENDIAN).getInt()); // EkaOperation, PrimusApprovalToken.OPERATION_SIGN
		assert ByteBuffer.wrap(operationEnc.getData()).order(ByteOrder.LITTLE_ENDIAN).getInt() == PrimusApprovalToken.OPERATION_SIGN;
		final PayloadPart signKeyNameEnc = PayloadPart.deserializeFrom(bb);
		System.out.println(signKeyNameEnc + " " + (new String(signKeyNameEnc.getData(), StandardCharsets.UTF_8))); // LabelUtf8String, signKeyName
		assert (new String(signKeyNameEnc.getData(), StandardCharsets.UTF_8)).equals(signKeyName);
		final PayloadPart timestampEnc = PayloadPart.deserializeFrom(bb);
		System.out.println(timestampEnc + " " + PrimusEncoding.hexEncode(timestampEnc.getData())); // EkaTimeStamp
		assert Arrays.equals(timestampEnc.getData(), timestamp);
		//
		final byte[] ts2 = Access.cutlengthheader(timestampEnc.getData());
		System.out.println("without length header: " + PrimusEncoding.hexEncode(ts2));
		final ByteBuffer bb2 = ByteBuffer.wrap(ts2);
		final PayloadPart payload2 = PayloadPart.deserializeFrom(bb2);
		System.out.println(payload2 + " " + PrimusEncoding.hexEncode(payload2.getData())); // EkaSignPayload
		assert Arrays.equals(payload2.getData(), payload);
		final PayloadPart epoch2 = PayloadPart.deserializeFrom(bb2);
		System.out.println(epoch2 + " " + PrimusEncoding.hexEncode(epoch2.getData())); // SecondsSinceEpoch
		System.out.println(new Date(TimeUnit.SECONDS.toMillis(ByteBuffer.wrap(epoch2.getData()).order(ByteOrder.LITTLE_ENDIAN).getLong())));
		final PayloadPart timestampkey2 = PayloadPart.deserializeFrom(bb2);
		System.out.println(timestampkey2 + " " + (new String(timestampkey2.getData(), StandardCharsets.UTF_8))); // LabelUtf8String, integrityKeyName
		//
		final PayloadPart signatureEnc = PayloadPart.deserializeFrom(bb);
		System.out.println(signatureEnc + " " + PrimusEncoding.hexEncode(signatureEnc.getData())); // DerSignature
		final PayloadPart payloadEnc = PayloadPart.deserializeFrom(bb);
		System.out.println(payloadEnc + " " + PrimusEncoding.hexEncode(payloadEnc.getData())); // EkaSignPayload, payload
		assert Arrays.equals(payloadEnc.getData(), payload);
		// assert !bb.hasRemaining();

		// [dissecting diagnostics, uses internal APIs]
		System.out.println("signature DER/ASN.1 encoding is " + DERObject.valueOf(signatureEnc.getData()));
		assert Arrays.equals(signatureEnc.getData(), PrimusAuthorizationToken.derifyOidAndSig(signAlgorithm, timestampSignature.getEncoding()));

		// sign the tokenChallenge, twice, locally
		final Signature localSigner = Signature.getInstance(signAlgorithm);
		localSigner.initSign(beryl.getPrivate());
		localSigner.update(tokenChallenge);
		final byte[] s0 = PrimusAuthorizationToken.derifyOidAndSig(signAlgorithm, localSigner.sign());
		final PrimusAuthorizationToken token0 = new PrimusAuthorizationToken(tokenChallenge, s0, beryl.getPublic().getEncoded());
		localSigner.initSign(chantal.getPrivate());
		localSigner.update(tokenChallenge);
		final byte[] s1 = PrimusAuthorizationToken.derifyOidAndSig(signAlgorithm, localSigner.sign());
		final PrimusAuthorizationToken token1 = new PrimusAuthorizationToken(tokenChallenge, s1, chantal.getPublic().getEncoded());

		// verify locally
		localVerifyer.initVerify(token0.getPublicKey());
		localVerifyer.update(token0.getApprovalTokenBytes());
		assert localVerifyer.verify(token0.getVerifySignatureBytes());
		localVerifyer.initVerify(token1.getPublicKey());
		localVerifyer.update(token1.getApprovalTokenBytes());
		assert localVerifyer.verify(token1.getVerifySignatureBytes());

		// [diagnostics]
		System.out.println();
		System.out.println("signed token is " + PrimusEncoding.hexEncode(token1.getEncoding()));

		// [dissecting diagnostics, uses internal APIs]
		final byte[] st2 = Access.cutlengthheader(token1.getEncoding());
		System.out.println("without length header: " + PrimusEncoding.hexEncode(st2));
		final ByteBuffer bb3 = ByteBuffer.wrap(st2);
		final PayloadPart tokenChallenge2 = PayloadPart.deserializeFrom(bb3);
		System.out.println(tokenChallenge2 + " " + PrimusEncoding.hexEncode(tokenChallenge2.getData())); // ApprovalToken, tokenChallenge
		assert Arrays.equals(tokenChallenge2.getData(), tokenChallenge);
		final PayloadPart s3 = PayloadPart.deserializeFrom(bb3);
		System.out.println(s3 + " " + PrimusEncoding.hexEncode(s3.getData())); // DerSignature, s1
		assert Arrays.equals(s3.getData(), s1);
		final PayloadPart pubk = PayloadPart.deserializeFrom(bb3);
		System.out.println(pubk + " " + PrimusEncoding.hexEncode(pubk.getData())); // PublicKeyEncoded
		assert Arrays.equals(pubk.getData(), chantal.getPublic().getEncoded());
		// assert !bb3.hasRemaining();

		// authorization and sign
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
		assert localVerifyer.verify(sig);

	}

}

