
/*
 * Copyright (C) 2017, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import com.securosys.primus.jce.PrimusCertificate;
import com.securosys.primus.jce.PrimusProvider;

/**
 * RSA public key export with Securosys Primus HSM JCE provider.
 */
public class CertSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration

		PrimusHelper.setup(args);

		// generate an RSA key pair
		System.out.println("generating RSA key pair");
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName());
		keyPairGenerator.initialize(2048);
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		final RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
		final RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();

		// name/persist the key pair
		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keyStore.load(null); // no-op, but mandatory for JCA
		final String keyName = UUID.randomUUID().toString(); // unique random name
		final char[] keyPassword = UUID.randomUUID().toString().toCharArray(); // unique random password
		keyStore.setKeyEntry(keyName, privateKey, keyPassword, new Certificate[] {new PrimusCertificate(publicKey)});

		System.out.println("fetching certificate [and public key]");
		final Certificate cert = keyStore.getCertificate(keyName);
		System.out.println(cert);
		final PublicKey pk = cert.getPublicKey();
		System.out.println(pk);

	}

}

