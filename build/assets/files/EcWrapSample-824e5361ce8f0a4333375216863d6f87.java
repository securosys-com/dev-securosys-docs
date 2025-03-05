
/*
 * Copyright (C) 2022,2023, Securosys SA
 */

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusPrimitives;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusWrap;

/**
 * Wrap/unwrap an EC private key,
 * wrapping by AES and RSA public key.
 */
public class EcWrapSample {

	public static void main(final String... args) throws Exception {

		// configure / log in to HSM
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;
		// to make test artifacts unlikely, to make sure non-explicit getInstance invocations below are finding Sun/Oracle implementations, not Primus'
		Security.removeProvider(primusProvider.getName());

		// generate a [EC] key [pair] on the HSM
		final String hsmKeyName = UUID.randomUUID().toString();
		PrimusName.generate(
			hsmKeyName,
			KeyPairGenerator.getInstance("EC", primusProvider),
			// new ECGenParameterSpec("secp256k1"),
			new ECGenParameterSpec("secp256r1"),
			// key must be wrappable (extractable)
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true)
		);
		final PrivateKey hsmKey = PrimusPrimitives.getPrivateKey(hsmKeyName);
		final PublicKey hsmPublicKey = PrimusPrimitives.getPublicKeyDirectly(hsmKeyName);
		final String publicKey = PrimusEncoding.base64Encode(hsmPublicKey.getEncoded());

		// generate a [2048 bit] RSA local key to wrap/unwrap with
		final KeyPairGenerator localRsaKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
		localRsaKeyPairGenerator.initialize(2048);
		final KeyPair localRsaKeyPair = localRsaKeyPairGenerator.generateKeyPair();
		final PrivateKey localRsaKey = localRsaKeyPair.getPrivate();
		final PublicKey localRsaPublicKey = localRsaKeyPair.getPublic();
		final String rsaPublicKey = PrimusEncoding.base64Encode(localRsaPublicKey.getEncoded());

		// generate an ephemeral AES wrap key on HSM
		final SecretKey hsmAesWrapKey = (SecretKey)PrimusName.generate(
			KeyGenerator.getInstance("AES", primusProvider),
			256,
			// key must be wrappable (extractable)
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true)
		);

		// reconstruct and import public RSA wrapping key from base64 input
		final PublicKey hsmRsaWrapKey = (PublicKey)KeyFactory.getInstance("RSA", primusProvider).translateKey(KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(PrimusEncoding.base64Decode(rsaPublicKey))));

		// wrap AES key with RSA wrap key
		final String wrappedAesWrapKey = PrimusEncoding.base64Encode(PrimusWrap.rsaWrapPkcs(hsmRsaWrapKey, hsmAesWrapKey));

		// wrap EC key with AES wrap key
		final String wrappedKey = PrimusEncoding.base64Encode(PrimusWrap.aesWrapEc(hsmAesWrapKey, (ECPrivateKey)hsmKey));

		// locally unwrap AES key
		final Cipher localRsaUnwrapCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		localRsaUnwrapCipher.init(Cipher.UNWRAP_MODE, localRsaKey);
		final SecretKey localAesWrapKey = (SecretKey)localRsaUnwrapCipher.unwrap(PrimusEncoding.base64Decode(wrappedAesWrapKey), "AES", Cipher.SECRET_KEY);

		// locally unwrap EC
		final Cipher localAesUnwrapCipher = Cipher.getInstance("AESWrap");
		localAesUnwrapCipher.init(Cipher.UNWRAP_MODE, localAesWrapKey);
		final PrivateKey localKey = (PrivateKey)localAesUnwrapCipher.unwrap(PrimusEncoding.base64Decode(wrappedKey), "EC", Cipher.PRIVATE_KEY);
		final PublicKey localPublicKey = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(PrimusEncoding.base64Decode(publicKey)));

		// test message for sign/verify tests
		final byte[] testmessage = "testmessage".getBytes(StandardCharsets.UTF_8);

		// sign on HSM
		final Signature hsmSignature = Signature.getInstance("SHA256withECDSA", primusProvider);
		hsmSignature.initSign(hsmKey);
		hsmSignature.update(testmessage.clone());
		final byte[] hsmSignedDer = hsmSignature.sign();
		final byte[] hsmSigned = hsmSignedDer;

		// sign locally
		final Signature localSignature = Signature.getInstance("SHA256withECDSA");
		localSignature.initSign(localKey);
		localSignature.update(testmessage.clone());
		final byte[] localSignedDer = localSignature.sign();
		final byte[] localSigned = localSignedDer;

		boolean verified;

		// verify on HSM (needs DER signature): HSM signature
		hsmSignature.initVerify(hsmPublicKey);
		hsmSignature.update(testmessage.clone());
		verified = hsmSignature.verify(hsmSignedDer);
		assert verified;

		// verify on HSM (needs DER signature): local signature
		hsmSignature.initVerify(hsmPublicKey);
		hsmSignature.update(testmessage.clone());
		verified = hsmSignature.verify(localSignedDer);
		assert verified;

		// verify locally (needs raw signature): local signature
		localSignature.initVerify(localPublicKey);
		localSignature.update(testmessage.clone());
		verified = localSignature.verify(localSigned);
		assert verified;

		// verify locally (needs raw signature): HSM signature
		localSignature.initVerify(localPublicKey);
		localSignature.update(testmessage.clone());
		verified = localSignature.verify(hsmSigned);
		assert verified;

	}

}

