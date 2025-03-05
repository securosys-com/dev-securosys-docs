
/*
 * Copyright (C) 2015-2017, Securosys SA
 */

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.spec.DSAParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusProvider;

/**
 * Simple DSA sample for the Securosys Primus HSM JCE provider.
 */
public class DsaSample {

	public static void main(final String... args) throws Exception {

		System.out.println("Securosys Primus HSM JCE provider DSA sample");

		PrimusHelper.setup(args);

		System.out.println("generating DSA algorithm parameters");
		final AlgorithmParameterGenerator parameterGenerator = AlgorithmParameterGenerator.getInstance("DSA", PrimusProvider.getProviderName());
		parameterGenerator.init(2048);
		final AlgorithmParameters algorithmParameters = parameterGenerator.generateParameters();
		final DSAParameterSpec dsaParameters = algorithmParameters.getParameterSpec(DSAParameterSpec.class);

		System.out.println("generating DSA key pair");
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA", PrimusProvider.getProviderName());
		keyPairGenerator.initialize(dsaParameters);
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		System.out.println("signing message");
		final byte[] message = UUID.randomUUID().toString().getBytes();
		final String signAlgorithm = "SHA256withDSA";
		final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initSign(keyPair.getPrivate());
		signature.update(message);
		final byte[] signatureBytes = signature.sign();

		// verify step here uses the JVM's default provider (e.g. Sun's)
		// instead of the Securosys Primus HSM JCE provider

		System.out.println("verifying signature");
		final Signature verifySignature = Signature.getInstance(signAlgorithm);
		verifySignature.initVerify(keyPair.getPublic());
		verifySignature.update(message);
		final boolean verified = verifySignature.verify(signatureBytes);

		System.out.println("verified: " + verified);

		System.out.println("sample done");

	}

}

