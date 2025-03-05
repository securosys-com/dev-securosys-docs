
/*
 * Copyright (C) 2018,2022, Securosys SA
 */

import java.security.KeyPairGenerator;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code generating a key [pair] directly
 * with the Securosys Primus HSM JCE provider.
 *
 * Generating a initially named key is done
 * by means of PrimusName.generate(Object...),
 * generateKeyPair(KeyPairGenerator,String),
 * etc.
 *
 * See GenerateWithRenameSample.java
 * for an indirect approach using KeyPairGenerator and KeyStore.
 */
public class GenerateDirectSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// generate a initially named key, by means of PrimusName.generate
		final String keyname = "ec-key-" + UUID.randomUUID().toString();
		PrimusName.generate(
			KeyPairGenerator.getInstance("EC", primusProvider),
			new ECGenParameterSpec("secp256r1"),
			keyname
		);

	}

}

