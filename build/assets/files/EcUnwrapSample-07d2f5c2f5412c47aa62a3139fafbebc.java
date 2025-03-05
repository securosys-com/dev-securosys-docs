
/*
 * Copyright (C) 2022,2023, Securosys SA
 */

import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;
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
 * Wrap/unwrap a secp256k1 private key identified by its 256 bit s value,
 * wrapping by AES and RSA public key.
 */
public class EcUnwrapSample {

	public static void main(final String... args) throws Exception {

		// configure / log in to HSM
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// generate a 2048 bit RSA key on the HSM, to wrap/unwrap with
		final String rsaKeyName = "RSA-wrap-key-" + UUID.randomUUID().toString();
		PrimusName.generate(rsaKeyName, KeyPairGenerator.getInstance("RSA", primusProvider), 2048);
		final String wrapInput = PrimusEncoding.base64Encode(PrimusPrimitives.getPublicKeyDirectly(rsaKeyName).getEncoded());
		System.out.println("RSA wrap input: " + wrapInput);

		// generate a local AES wrap key
		final KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("AES");
		aesKeyGenerator.init(256);
		final SecretKey aesWrapKey = aesKeyGenerator.generateKey();

		// reconstruct public RSA wrapping key from base64 input
		final PublicKey rsaWrapKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(PrimusEncoding.base64Decode(wrapInput)));

		// locally wrap AES with RSA
		final Cipher asymCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		asymCipher.init(Cipher.WRAP_MODE, rsaWrapKey);
		final byte[] wrappedAes = asymCipher.wrap(aesWrapKey);
		final String aes = PrimusEncoding.base64Encode(wrappedAes);
		System.out.println("wrapped AES: " + aes);

		// generate random 256 bit random s (EC private key value) value
		final byte[] randomBytes = new byte[256 / 8];
		(new SecureRandom()).nextBytes(randomBytes);
		// populate border bits
		randomBytes[0] |= 0x81;
		randomBytes[randomBytes.length - 1] |= 0x81;
		final String privateInput = PrimusEncoding.hexEncode(randomBytes);
		System.out.println("random s: " + privateInput);

		// translate to local secp256k1 private key
		// (hex-decode, to big-integer, to private-key-spec, to local key, import)
		final AlgorithmParameters ecAlgorithmParameters = AlgorithmParameters.getInstance("EC");
		// (the bitcoin curve)
		ecAlgorithmParameters.init(new ECGenParameterSpec("secp256k1"));
		final ECParameterSpec ecParameterSpec = ecAlgorithmParameters.getParameterSpec(ECParameterSpec.class);
		// (make it unsigned (i.e. positive))
		final BigInteger s = new BigInteger(1, PrimusEncoding.hexDecode(privateInput));
		final ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(s, ecParameterSpec);
		final ECPrivateKey localEc = (ECPrivateKey)KeyFactory.getInstance("EC").generatePrivate(privateKeySpec);

		// wrap EC key with AES
		final Cipher symWrapCipher = Cipher.getInstance("AESWrap");
		symWrapCipher.init(Cipher.WRAP_MODE, aesWrapKey);
		final byte[] wrappedEc = symWrapCipher.wrap(localEc);
		final String ec = PrimusEncoding.base64Encode(wrappedEc);
		System.out.println("wrapped EC: " + ec);

		// unwrap AES on HSM
		final SecretKey hsmAes = PrimusWrap.rsaUnwrapPkcs(PrimusPrimitives.getPrivateKey(rsaKeyName), PrimusEncoding.base64Decode(aes));

		// for illustration/test purposes: in in this sample only: make the unwrapped key exportable
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, false);

		// unwrap EC on HSM
		final ECPrivateKey hsmEc = PrimusWrap.aesUnwrapEc(hsmAes, PrimusEncoding.base64Decode(ec));

		// to illustrate/test: export and compare
		final ECPrivateKey ec2 = (ECPrivateKey)KeyFactory.getInstance("EC", primusProvider).translateKey(hsmEc);
		assert ec2.getS().equals(s);

	}

}

