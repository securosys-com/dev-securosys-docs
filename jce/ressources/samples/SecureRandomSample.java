
/*
 * Copyright (C) 2020,2022, Securosys SA
 * Author Eric Laroche <eric@securosys.ch>
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.RSAKeyGenParameterSpec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code illustrating the use of Primus JCE's SecureRandom,
 * using the Primus HSM as random bytes source.
 */
public class SecureRandomSample {

	@SuppressWarnings("unused")
	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// get a Primus SecureRandom
		final SecureRandom secureRandom = SecureRandom.getInstance(PrimusProvider.getSecureRandomTypeName(), PrimusProvider.getProviderName());

		// retrieve Primus random bits
		final byte[] randomBytes = new byte[256];
		secureRandom.nextBytes(randomBytes);

		// generate a local AES key backed by Primus random bits
		final KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("AES");
		aesKeyGenerator.init(256, secureRandom);
		final SecretKey aesKey = aesKeyGenerator.generateKey();
		// e.g.: javax.crypto.KeyGenerator.generateKey -> com.sun.crypto.provider.AESKeyGenerator.engineGenerateKey
		// -> java.security.SecureRandom.nextBytes -> com.securosys.primus.jce.spi.PrimusSecureRandomSpi.engineNextBytes

		// generate a local RSA key pair backed by Primus random bits
		final KeyPairGenerator rsaKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
		rsaKeyPairGenerator.initialize(new RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F4), secureRandom);
		final KeyPair rsaKeyPair = rsaKeyPairGenerator.generateKeyPair();
		// e.g.: java.security.KeyPairGenerator.Delegate.generateKeyPair -> sun.security.rsa.RSAKeyPairGenerator.generateKeyPair
		// -> java.math.BigInteger.probablePrime -> java.math.BigInteger.largePrime -> java.math.BigInteger.BigInteger
		// -> java.math.BigInteger.randomBits -> java.security.SecureRandom.nextBytes
		// -> com.securosys.primus.jce.spi.PrimusSecureRandomSpi.engineNextBytes

		// generate a local EC key pair backed by Primus random bits
		final KeyPairGenerator ecKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		ecKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"), secureRandom);
		final KeyPair ecKeyPair = ecKeyPairGenerator.generateKeyPair();
		// e.g.: java.security.KeyPairGenerator.Delegate.generateKeyPair -> sun.security.ec.ECKeyPairGenerator.generateKeyPair
		// -> sun.security.ec.ECKeyPairGenerator.generateKeyPairImpl -> sun.security.ec.ECKeyPairGenerator.generatePrivateScalar
		// -> java.security.SecureRandom.nextBytes -> com.securosys.primus.jce.spi.PrimusSecureRandomSpi.engineNextBytes

	}

}

