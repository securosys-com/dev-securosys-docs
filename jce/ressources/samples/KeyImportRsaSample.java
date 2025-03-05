
/*
 * Copyright (C) 2017, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusX509Certificate;

/**
 * Sample RSA key import with Securosys Primus HSM JCE provider.
 */
public class KeyImportRsaSample {

	public static void main(final String... args) throws Exception {

		System.out.println("Securosys Primus HSM JCE provider RSA key import sample");

		// generate a local Java runtime RSA key pair
		System.out.println("generating a local RSA key pair");
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		final RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
		final RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();

		// HSM configuration
		PrimusHelper.setup(args);

		// import key pair
		System.out.println("importing key pair");
		final KeyStore importKeyStore = KeyStore.getInstance(PrimusProvider.getImportExportKeyStoreTypeName(), PrimusProvider.getProviderName());
		importKeyStore.load(null); // no-op, but mandatory for JCA
		final String keyName = UUID.randomUUID().toString(); // unique random name
		final char[] keyPassword = UUID.randomUUID().toString().toCharArray(); // unique random password
		importKeyStore.setKeyEntry(keyName, privateKey, keyPassword, new Certificate[] {new PrimusX509Certificate(publicKey)});

		System.out.println("sample done");

	}

}

