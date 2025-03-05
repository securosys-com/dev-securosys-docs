
/*
 * Copyright (C) 2019,2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusX509Certificate;

import com.securosys.primus.jce.spec.EdPrivateKeyImpl;
import com.securosys.primus.jce.spec.EdPublicKeyImpl;

/**
 * Sample Ed key export
 * with Securosys Primus HSM JCE provider.
 */
public class KeyExportEdSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// set 'extractable' flags on to be generated keys
		// NOTE: remember that the Primus HSM needs that enabled [by SO; hsm_sec_set_config key_export=true]
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, Boolean.TRUE);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, Boolean.FALSE);

		// keystores: for naming/persisting, and for export/import
		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keyStore.load(null);
		final KeyStore exportKeyStore = KeyStore.getInstance(PrimusProvider.getImportExportKeyStoreTypeName(), PrimusProvider.getProviderName());
		exportKeyStore.load(null);

		// generate an Ed key [pair]
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		keyPairGenerator.initialize(new ECGenParameterSpec("ed25519"));
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// name/persist key pair
		final String keyName = "ec-ed25519-key-" + UUID.randomUUID().toString();
		keyStore.setKeyEntry(keyName, keyPair.getPrivate(), null, new Certificate[] {new PrimusX509Certificate(keyPair.getPublic())});

		// export key pair
		final KeyPair exportedKeyPair = new KeyPair(exportKeyStore.getCertificate(keyName).getPublicKey(), (PrivateKey)exportKeyStore.getKey(keyName, null));

		// delete key pair on HSM
		keyStore.deleteEntry(keyName);

		// typically: exported sun.security.ec.ECPrivateKeyImpl and sun.security.ec.ECPublicKeyImpl, plain old java objects, with key payload
		System.out.println("exported " + exportedKeyPair.getPrivate().getClass().getName() + " and " + exportedKeyPair.getPublic().getClass().getName());

		System.out.println("private key encoded: " + PrimusEncoding.base64Encode(exportedKeyPair.getPrivate().getEncoded()));
		System.out.println("public key encoded: " + PrimusEncoding.base64Encode(exportedKeyPair.getPublic().getEncoded()));

		// recreate from byte encoding
		final KeyPair recreatedKeyPair = new KeyPair(EdPublicKeyImpl.fromBytes(exportedKeyPair.getPublic().getEncoded()), EdPrivateKeyImpl.fromBytes(exportedKeyPair.getPrivate().getEncoded()));

		// import
		exportKeyStore.setKeyEntry(keyName, recreatedKeyPair.getPrivate(), null, new Certificate[] {new PrimusX509Certificate(recreatedKeyPair.getPublic())});

	}

}

