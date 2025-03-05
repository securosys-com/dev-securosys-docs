
/*
 * Copyright (C) 2017-2021, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Simple EC-DSA sample
 * for the Securosys Primus HSM JCE provider.
 */
public class EcDsaSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// EC curve and ECDSA algorithm to use
		// final String curve = "secp256k1"; // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
		final String curve = "secp256r1";
		final String signAlgorithm = "SHA256withECDSA";

		// generate a EC key pair
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		keyPairGenerator.initialize(new ECGenParameterSpec(curve));
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		assert keyPair != null; // pair
		assert keyPair.getPrivate() != null; // private key
		assert keyPair.getPrivate().getEncoded() == null; // no private key payload availab;e (rests on HSM)
		assert keyPair.getPublic() != null; // public key
		assert keyPair.getPublic().getEncoded() != null; // public key payload available

		System.out.println("EC key pair generated");
		System.out.println("public key: " + PrimusEncoding.base64Encode(keyPair.getPublic().getEncoded()));
		// System.out.println(PrimusEncoding.dumpDER(keyPair.getPublic().getEncoded())); // since @2.2.3
		// System.out.println(DERObject.valueOf(keyPair.getPublic().getEncoded()).toString(true, true, true)); // internal

		final byte[] message = UUID.randomUUID().toString().getBytes();

		final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initSign(keyPair.getPrivate());
		signature.update(message);
		final byte[] signatureBytes = signature.sign();

		System.out.println("signed");
		System.out.println("signature: " + PrimusEncoding.base64Encode(signatureBytes));
		// System.out.println(PrimusEncoding.dumpDER(signatureBytes)); // since @2.2.3
		// System.out.println(DERObject.valueOf(signatureBytes).toString(true, true, true)); // internal

		final Signature verifySignature = Signature.getInstance(signAlgorithm);
		verifySignature.initVerify(keyPair.getPublic());
		verifySignature.update(message);
		final boolean verified = verifySignature.verify(signatureBytes);

		assert verified;

	}

}

