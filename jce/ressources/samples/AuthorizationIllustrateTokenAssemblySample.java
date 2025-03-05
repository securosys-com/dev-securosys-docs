
/*
 * Copyright (C) 2017-2022, Securosys SA
 */

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;
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
 * with the Securosys Primus HSM JCE provider.
 *
 * Simpler sample: not integrity key used, nor timestamps.
 * Illustrates how to build tokenchallenges without Primus JCE provider,
 * translatable from Java to other languages, for use on other devices.
 */
public class AuthorizationIllustrateTokenAssemblySample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// create local (non-HSM) authorization keys; each approval party (device) would generate/have one
		final int n = 4;
		final KeyPair[] authorizationKeys = new KeyPair[n];
		final PublicKey[] authorizationPublicKeys = new PublicKey[authorizationKeys.length];
		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		for (int i = 0; i < authorizationKeys.length; i++) {
			authorizationKeys[i] = localKeyPairGenerator.generateKeyPair();
			authorizationPublicKeys[i] = authorizationKeys[i].getPublic();

			// mimic/illustrate public keys coming as encoded byte arrays from devices
			authorizationPublicKeys[i] = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(authorizationPublicKeys[i].getEncoded()));
		}

		// key-pair creation with access and flags set:
		final int quorum = n / 2;
		final PrimusAccessGroup accessGroup = new PrimusAccessGroup(authorizationPublicKeys, quorum);
		final PrimusAccessToken accessToken = new PrimusAccessToken(accessGroup);
		final PrimusAccessBlob primusAccessBlob = new PrimusAccessBlob(accessToken);
		final PrimusAccess primusAccess = new PrimusAccess(primusAccessBlob, primusAccessBlob, primusAccessBlob, primusAccessBlob);
		// create signing key, which needs approvals for signing
		final String signKeyName = UUID.randomUUID().toString();
		final KeyPair keyPair = (KeyPair)PrimusName.generate(
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
		final PublicKey signKeyPublic = keyPair.getPublic();

		// generate a transaction payload, in real live e.g. a crypto currency transaction
		final int payloadsize = 250; // to test below code: use something of length %4!=0
		final byte[] payload = new byte[payloadsize];
		Arrays.fill(payload, (byte)'A');

		// invoke authorized signing operation:
		// operations will throw PrimusAuthorizationExceptions if authorization is insufficient/expired/etc

		// build a test authorization directly in this code
		// PrimusApprovalToken is build-by-Java only in test code
		// else the tokenChallenge is signed by the approval parties
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload)).getEncoding();

		// following illustrates how to serialize a token challenge out of a payload
		// (the required additional partially empty information (operation, key name) is more relevant in more complex modes)
		byte[] tokenChallenge2 = payload;
		// make it type/length/value-encoded
		tokenChallenge2 = cat(marshall((short)tokenChallenge2.length), tokenChallenge2); // short length header, e.g. 0001
		tokenChallenge2 = cat(new byte[] {0x57, 0x10}, tokenChallenge2); // short type header, 5710 (EkaSignPayload)
		// align payload to 4 bytes
		while (tokenChallenge2.length % 4 != 0) {
			tokenChallenge2 = cat(tokenChallenge2, new byte[] {0});
		}
		// more (partially empty) information prepended
		tokenChallenge2 = cat(new byte[] {0x02, 0x10, 0x00, 0x00}, tokenChallenge2); // empty key name element, 0210 (LabelUtf8String) 0000 (zero length)
		tokenChallenge2 = cat(new byte[] {0x3b, 0x00, 0x04, 0x00, 0x01, 0x00, 0x00, 0x00}, tokenChallenge2); // operation, 3b00 (EkaOperation) 0400 (4 bytes) 01000000 (#1, OPERATION_SIGN)
		// overall 4 bytes length header, e.g. 10010000
		tokenChallenge2 = cat(marshall((int)tokenChallenge2.length), tokenChallenge2);

		// same as (new PrimusApprovalToken(tokenChallenge2)).getPayload()
		byte[] payload2 = tokenChallenge2;
		int l = ((int)payload2[18] & 0xff) | (((int)payload2[19] & 0xff) << 8);
		payload2 = Arrays.copyOfRange(payload2, 20, l);

		System.out.println("tokenChallenge " + (Arrays.equals(tokenChallenge2, tokenChallenge) ? "" : "NOT ") + "as expected");
		System.out.println("payload " + (Arrays.equals(payload2, payload) ? "" : "NOT ") + "as expected");

		final PrimusAuthorizationToken[] tokens = new PrimusAuthorizationToken[n];
		for (int i = 0; i < n; i++) {
			// let the approval parties sign
			final Signature localSignature = Signature.getInstance("SHA256withECDSA");
			localSignature.initSign(authorizationKeys[i].getPrivate());
			localSignature.update(tokenChallenge2);
			byte[] s = localSignature.sign();

			// postprocess (derify, to add required signature algorithm information)
			s = PrimusAuthorizationToken.derifyOidAndSig("SHA256withECDSA", s);
			// serialize signature, challenge, and public key
			tokens[i] = new PrimusAuthorizationToken(tokenChallenge2, s, authorizationKeys[i].getPublic().getEncoded());

			// mimic authorization tokens coming as byte arrays from devices
			tokens[i] = new PrimusAuthorizationToken(tokens[i].getEncoding());
		}

		// verify approvals locally
		Signature localVerifyer = Signature.getInstance("SHA256withECDSA");
		for (int i = 0; i < n; i++) {
			localVerifyer.initVerify(tokens[i].getPublicKey());
			localVerifyer.update(tokens[i].getApprovalTokenBytes());
			final boolean signSignatureOk = localVerifyer.verify(tokens[i].getVerifySignatureBytes());
			System.out.println("sign signature #" + i + " is " + (signSignatureOk ? "OK" : "NOT OK"));
		}

		final PrimusAuthorization primusAuthorization = new PrimusAuthorization();
		for (int i = 0; i < n; i++) {
			primusAuthorization.add(tokens[i]);
		}
		PrimusAuthorization.setAuthorization(primusAuthorization);

		final Signature signature = Signature.getInstance("SHA256withECDSA", PrimusProvider.getProviderName());
		signature.initSign(signKey);
		signature.update(payload);
		final byte[] sig = signature.sign();

		// verify locally
		localVerifyer = Signature.getInstance("SHA256withECDSA");
		localVerifyer.initVerify(signKeyPublic);
		localVerifyer.update(payload);
		final boolean signSignatureOk = localVerifyer.verify(sig);
		System.out.println("sign signature is " + (signSignatureOk ? "OK" : "NOT OK"));

	}

	/** marshall 64 bit integer to little-endian */
	static byte[] marshall(int i) {
		final ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.SIZE / Byte.SIZE);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		byteBuffer.putInt(i);
		return byteBuffer.array();
	}

	/** marshall 32 bit integer to little-endian */
	static byte[] marshall(short s) {
		final ByteBuffer byteBuffer = ByteBuffer.allocate(Short.SIZE / Byte.SIZE);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		byteBuffer.putShort(s);
		return byteBuffer.array();
	}

	/** concatenate byte arrays */
	static byte[] cat(byte[] a, byte[] b) {
		final byte[] bytes = new byte[a.length + b.length];
		System.arraycopy(a, 0, bytes, 0, a.length);
		System.arraycopy(b, 0, bytes, a.length, b.length);
		return bytes;
	}

}

