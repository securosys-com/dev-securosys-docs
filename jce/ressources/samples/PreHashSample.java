
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Signature;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;

import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.rand.LocalSecureRandom;

/**
 * Sample illustrating local, manual, prehashing input for a Signature.
 */
public class PreHashSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// generate an RSA key pair on Primus
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", primusProvider);
		keyPairGenerator.initialize(2048);
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// test prehashing "SHA256withRSA"
		test(primusProvider, keyPair, LocalSecureRandom.randomBytes(200000), "SHA-256", "NONESHA256withRSA", "SHA256withRSA", null);

		// test prehashing "SHA256withRSA/PSS"
		test(primusProvider, keyPair, LocalSecureRandom.randomBytes(200000), "SHA-256", "NONESHA256withRSA/PSS", "SHA256withRSA/PSS", null);

		// test prehashing "SHA384withRSA/PSS" with PSSParameterSpec
		test(primusProvider, keyPair, LocalSecureRandom.randomBytes(200000), "SHA-384", "NONESHA384withRSA/PSS", "SHA384withRSA/PSS", new PSSParameterSpec("SHA-384", "MGF1", new MGF1ParameterSpec("SHA-384"), (384 / Byte.SIZE), PSSParameterSpec.TRAILER_FIELD_BC));

		// generate an EC key pair on Primus
		final KeyPairGenerator keyPairGenerator2 = KeyPairGenerator.getInstance("EC", primusProvider);
		keyPairGenerator2.initialize(256);
		final KeyPair keyPair2 = keyPairGenerator2.generateKeyPair();

		// test prehashing "SHA256withECDSA"
		test(primusProvider, keyPair2, LocalSecureRandom.randomBytes(200000), "SHA-256", "NONEwithECDSA", "SHA256withECDSA", null);

	}

	static void test(final Provider provider, final KeyPair keyPair, final byte[] message, final String hashAlgorithm, final String prehashedSignAlgorithm, final String signAlgorithm, final AlgorithmParameterSpec parameterSpec) throws Exception {

		// pre-hash
		final MessageDigest messageDigest = MessageDigest.getInstance(hashAlgorithm);
		messageDigest.update(message);
		messageDigest.update(message);
		messageDigest.update(message);
		messageDigest.update(message);
		final byte[] message2 = messageDigest.digest();

		// sign prehashed
		final Signature signature = Signature.getInstance(prehashedSignAlgorithm, provider);
		signature.initSign(keyPair.getPrivate());
		if (parameterSpec != null) {
			signature.setParameter(parameterSpec);
		}
		signature.update(message2);
		final byte[] signatureBytes = signature.sign();

		// verify directly
		final Signature verifySignature = Signature.getInstance(signAlgorithm);
		verifySignature.initVerify(keyPair.getPublic());
		if (parameterSpec != null) {
			signature.setParameter(parameterSpec);
		}
		verifySignature.update(message);
		verifySignature.update(message);
		verifySignature.update(message);
		verifySignature.update(message);
		final boolean verified = verifySignature.verify(signatureBytes);

		// assert result
		if (!verified) {
			throw new AssertionError();
		}

	}

}

