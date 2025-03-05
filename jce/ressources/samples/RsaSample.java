
/*
 * Copyright (C) 2015-2019, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.util.UUID;

import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusX509Certificate;

/**
 * Simple RSA sample for the Securosys Primus HSM JCE provider.
 */
public class RsaSample {

	public static void main(final String... args) throws Exception {

		System.out.println("Securosys Primus HSM JCE provider RSA sample");

		// HSM configuration
		PrimusHelper.setup(args);

		System.out.println("generating RSA key pair");
		// [Primus JCE provider is either "SecurosysPrimusXSeries" (X-Series) or "SecurosysPrimus" (S-Series)]
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName());
		keyPairGenerator.initialize(2048); // RSA key size used for this test, 2048 bits (minimum allowed by policy on S500)
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		assert keyPair != null; // [generateKeyPair should return something]
		assert keyPair.getPublic() != null; // [the public part should be there]
		assert keyPair.getPublic().getEncoded() != null; // [the public key reference should contain the public key material]
		assert keyPair.getPublic().getEncoded().length > 0;
		assert keyPair.getPrivate() != null; // [the private part should be a reference on the HSM key]
		assert keyPair.getPrivate().getEncoded() == null; // [the private key reference should not contain any key material]

		System.out.println("naming/persisting RSA key pair");
		// [Primus JCE keystore type is "Primus"]
		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keyStore.load(null); // no-op, but mandatory for JCA
		final String keyName = UUID.randomUUID().toString(); // random name for persisted private key
		keyStore.setKeyEntry(keyName, keyPair.getPrivate(), null, new Certificate[] {new PrimusX509Certificate(keyPair.getPublic())});

		System.out.println("retrieving RSA key pair");
		final KeyPair keyPair2 = new KeyPair(keyStore.getCertificate(keyName).getPublicKey(), (PrivateKey)keyStore.getKey(keyName, null));

		assert keyPair2.getPublic().getEncoded().length > 0; // [retrieved public key should contain the public key material]
		assert keyPair2.getPrivate().getEncoded() == null; // [retrieved private key reference should not contain any key material]

		System.out.println("signing message");
		final byte[] message = UUID.randomUUID().toString().getBytes(); // random payload to sign
		final String signAlgorithm = "SHA512withRSA";
		final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initSign(keyPair2.getPrivate());
		signature.update(message);
		final byte[] signatureBytes = signature.sign();

		// verify step here uses the JVM's default provider (e.g. Sun's)
		// instead of the Securosys Primus HSM JCE provider

		System.out.println("verifying signature");
		final Signature verifySignature = Signature.getInstance(signAlgorithm);
		verifySignature.initVerify(keyPair2.getPublic());
		verifySignature.update(message);
		final boolean verified = verifySignature.verify(signatureBytes);

		System.out.println("verified: " + verified);

		System.out.println("sample done");
	}

}

