
/*
 * Copyright (C) 2017, Securosys SA
 */

import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.UUID;

import javax.crypto.KeyGenerator;

import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample AES key import with Securosys Primus HSM JCE provider.
 */
public class KeyImportAesSample {

	public static void main(final String... args) throws Exception {

		// generate a local Java AES key
		final KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		final Key key = keyGenerator.generateKey();

		// configure and log in

		// HSM configuration
		PrimusHelper.setup(args);

		final String keyName = UUID.randomUUID().toString();

		// import key
		final KeyStore importKeyStore = KeyStore.getInstance(PrimusProvider.getImportExportKeyStoreTypeName(), PrimusProvider.getProviderName());
		importKeyStore.load(null);
		importKeyStore.setKeyEntry(keyName, key, keyName.toCharArray(), new Certificate[] {null});

		// get a reference to imported key
		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keyStore.load(null);
		final Key key2 = keyStore.getKey(keyName, keyName.toCharArray());
		key2.getClass();

	}

}

