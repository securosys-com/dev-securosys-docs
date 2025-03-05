
/*
 * Copyright (C) 2017-2023, Securosys SA
 */

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusProvider;

/**
 * Simple EC-DSA sample
 * with adhoc import of public key material
 * for the Securosys Primus HSM JCE provider.
 */
public class AdhocImportSample {

	public static void main(final String... args) throws Exception {

		// for adhoc-public-key-import
		System.setProperty("com.securosys.primus.jce.adhocPublicKeyImport", "true");

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// EC curve and ECDSA algorithm to use
		final String curve = "secp256r1";
		final String signAlgorithm = "SHA256withECDSA";

		// generate a local EC key pair
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
		keyPairGenerator.initialize(new ECGenParameterSpec(curve));
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		final byte[] message = UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8);

		final Signature signature = Signature.getInstance(signAlgorithm);
		signature.initSign(keyPair.getPrivate());
		signature.update(message);
		final byte[] signatureBytes = signature.sign();

		final Signature verifySignature = Signature.getInstance(signAlgorithm, primusProvider);
		verifySignature.initVerify(keyPair.getPublic());
		verifySignature.update(message);
		final boolean verified = verifySignature.verify(signatureBytes);

		assert verified;

	}

}

