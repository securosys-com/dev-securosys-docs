
/*
 * Copyright (C) 2020, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.util.Base64;
import java.util.UUID;

import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs;
import com.securosys.primus.jce.PrimusX509Certificate;

/**
 * BLS key and sign sample for the Securosys Primus HSM JCE provider.
 */
public class BlsSample {

	public static void main(final String... args) throws Exception {

		System.setProperty("com.securosys.primus.jce.adhocPublicKeyImport", "true"); // for later Signature.initVerify with exported public key

		// HSM configuration

		PrimusHelper.setup(args);

		// generate a BLS key pair
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("BLS", PrimusProvider.getProviderName());
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		assert keyPair != null; // [generateKeyPair should return something]
		assert keyPair.getPublic() != null; // [the public part should be there]
		assert keyPair.getPublic().getEncoded() != null; // [the public key reference should contain the public key material]
		assert keyPair.getPublic().getEncoded().length > 0;
		assert keyPair.getPrivate() != null; // [the private part should be a reference on the HSM key]
		assert keyPair.getPrivate().getEncoded() == null; // [the private key reference should not contain any key material]

		System.out.println("public key: " + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
		System.out.println("public key raw part: " + Base64.getEncoder().encodeToString(((PrimusSpecs.BlsPublicKey)keyPair.getPublic()).getRaw()));
		// System.out.println("public key DER dump:");
		// System.out.println(PrimusEncoding.dumpDER(keyPair.getPublic().getEncoded()));

		// name/persist key pair
		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keyStore.load(null);
		final String keyName = UUID.randomUUID().toString(); // random name
		keyStore.setKeyEntry(keyName, keyPair.getPrivate(), null, new Certificate[] {new PrimusX509Certificate(keyPair.getPublic())});

		// get key pair from keystore
		final KeyPair keyPair2 = new KeyPair(keyStore.getCertificate(keyName).getPublicKey(), (PrivateKey)keyStore.getKey(keyName, null));

		assert keyPair2.getPublic().getEncoded().length > 0; // [retrieved public key should contain the public key material]
		assert keyPair2.getPrivate().getEncoded() == null; // [retrieved private key reference should not contain any key material]

		// sign
		final byte[] message = UUID.randomUUID().toString().getBytes(); // random payload to sign
		final Signature signature = Signature.getInstance("BLS", PrimusProvider.getProviderName());
		signature.initSign(keyPair2.getPrivate());
		signature.update(message);
		final byte[] signatureBytes = signature.sign();

		System.out.println("signature: " + Base64.getEncoder().encodeToString(signatureBytes));

		// verify
		signature.initVerify(keyPair2.getPublic());
		signature.update(message);
		final boolean verified = signature.verify(signatureBytes);

		System.out.println("signature verified: " + verified);
		assert verified;

	}

}

