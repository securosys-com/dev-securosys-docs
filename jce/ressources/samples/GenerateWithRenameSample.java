
/*
 * Copyright (C) 2018,2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code generating a key [pair] indirectly
 * with the Securosys Primus HSM JCE provider.
 *
 * Generating a initially named key is done
 * by means of PrimusName.generate(Object...),
 * generateKeyPair(KeyPairGenerator,String), etc.
 *
 * See GenerateDirectSample.java
 * for a direct approach using PrimusName.generate.
 */
public class GenerateWithRenameSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// generate a initially unnamed key, by use of plain JCA/JCE API (KeyPairGenerator)
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", primusProvider);
		keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// name the key, by use of plain JCA/JCE API (KeyStore)
		final String keyname = "ec-key-" + UUID.randomUUID().toString();
		final KeyStore keyStore = KeyStore.getInstance("Primus", primusProvider);
		keyStore.load(null);
		keyStore.setKeyEntry(keyname, keyPair.getPrivate(), null, new Certificate[1]);

	}

}

