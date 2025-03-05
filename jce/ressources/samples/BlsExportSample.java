
/*
 * Copyright (C) 2020,2021, Securosys SA
 */

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusProviderException;
import com.securosys.primus.jce.PrimusSpecs;
import com.securosys.primus.jce.PrimusX509Certificate;

/**
 * BLS key and sign sample for the Securosys Primus HSM JCE provider,
 * including export and import.
 */
public class BlsExportSample {

	public static void main(final String... args) throws Exception {

		System.setProperty("com.securosys.primus.jce.adhocPublicKeyImport", "true"); // for later Signature.initVerify with exported

		// HSM configuration

		PrimusHelper.setup(args);

		// have the generated keys plain-exportable, for later export tests, and private key contents dumping
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, false);

		// generate a BLS key pair
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("BLS", PrimusProvider.getProviderName());
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		System.out.println("extractable BLS key pair generated");

		assert keyPair != null; // [generateKeyPair should return something]
		assert keyPair.getPublic() != null; // [the public part should be there]
		assert keyPair.getPublic().getEncoded() != null; // [the public key reference should contain the public key material]
		assert keyPair.getPublic().getEncoded().length > 0;
		assert keyPair.getPrivate() != null; // [the private part should be a reference on the HSM key]
		assert keyPair.getPrivate().getEncoded() == null; // [the private key reference should not contain any key material]

		// the BLS public key encoding is a DER (ASN.1) encoding, such as: [[OID 2b0601040182dc7c05030101 OID 2b0601040182dc7c05030201] BITSTRING 00...]
		System.out.println("public key: " + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
		System.out.println("public key raw part: " + Base64.getEncoder().encodeToString(((PrimusSpecs.BlsPublicKey)keyPair.getPublic()).getRaw()));
		// System.out.println("public key DER structure:");
		// System.out.println(com.securosys.primus.jce.PrimusEncoding.dumpDER(keyPair.getPublic().getEncoded()));

		// name/persist key pair
		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keyStore.load(null);
		final String keyName = UUID.randomUUID().toString(); // random key name
		keyStore.setKeyEntry(keyName, keyPair.getPrivate(), null, new Certificate[] {new PrimusX509Certificate(keyPair.getPublic())});

		// get key pair references from keystore
		final KeyPair keyPair2 = new KeyPair(keyStore.getCertificate(keyName).getPublicKey(), (PrivateKey)keyStore.getKey(keyName, null));

		assert keyPair2.getPublic().getEncoded().length > 0; // [retrieved public key should contain the public key material]
		assert keyPair2.getPrivate().getEncoded() == null; // [retrieved private key reference should not contain any key material]

		// sign
		final byte[] message = UUID.randomUUID().toString().getBytes(); // random payload to sign
		final Signature signature = Signature.getInstance("BLS", PrimusProvider.getProviderName());
		signature.initSign(keyPair2.getPrivate());
		signature.update(message);
		final byte[] signatureBytes = signature.sign();

		// the BLS signature is not a DER (ASN.1) encoding
		System.out.println("signature: " + Base64.getEncoder().encodeToString(signatureBytes));

		// verify signature
		signature.initVerify(keyPair2.getPublic());
		signature.update(message);
		final boolean verified = signature.verify(signatureBytes);

		System.out.println("signature verified: " + verified);
		assert verified;

		// export private and public key, as local pseudo-keys

		final KeyFactory keyFactory = KeyFactory.getInstance("BLS", PrimusProvider.getProviderName());
		final Key k2 = keyFactory.translateKey(keyPair.getPrivate());
		assert k2 != null;
		System.out.println("private key: " + Base64.getEncoder().encodeToString(k2.getEncoded()));

		final Key pk2 = keyFactory.translateKey(keyPair.getPublic());
		assert pk2 != null;
		System.out.println("public key: " + Base64.getEncoder().encodeToString(pk2.getEncoded()));

		// import private and public key

		// [X509EncodedKeySpec support requites Primus JCE version 2.2.4 or later]

		PrivateKey k3;
		PublicKey pk3;

		try {

			k3 = keyFactory.generatePrivate(new X509EncodedKeySpec(k2.getEncoded()));
			assert k3 != null;
			pk3 = keyFactory.generatePublic(new X509EncodedKeySpec(pk2.getEncoded()));
			assert pk3 != null;

		} catch (final PrimusProviderException e) {

			// [workaround for before 2.2.4]
			k3 = (PrivateKey)keyFactory.translateKey(new com.securosys.primus.jce.spec.BlsPrivateKeyImpl(k2.getEncoded()));
			pk3 = (PublicKey)keyFactory.translateKey(com.securosys.primus.jce.spec.BlsPublicKeyImpl.fromBytes(pk2.getEncoded()));

		}

		// test sign with imported
		signature.initSign(k3);
		signature.update(message);
		final byte[] signatureBytes3 = signature.sign();
		signature.initVerify(keyPair2.getPublic());
		signature.update(message);
		final boolean verified3 = signature.verify(signatureBytes3);
		assert verified3;

	}

}

