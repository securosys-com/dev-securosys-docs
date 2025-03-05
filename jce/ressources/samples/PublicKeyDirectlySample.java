
/*
 * Copyright (C) 2023, Securosys SA
 */

import java.security.KeyPairGenerator;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusPrimitives;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code using getPublicKeyDirectly.
 */
public class PublicKeyDirectlySample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// random name (avoid test collisions)
		final String name = UUID.randomUUID().toString();

		// generate a directly named RSA key pair
		PrimusName.generate(
			KeyPairGenerator.getInstance("RSA", primusProvider),
			new RSAKeyGenParameterSpec(3072, RSAKeyGenParameterSpec.F4),
			name
		);

		// show types and flags, e.g. '[[PrivateKey, RSA], [PublicKey, RSA]]'
		KeyFlagsSample.printFlags(name);

		// no certificate has been persisted yet
		assert PrimusPrimitives.getCertificate(name) == null;

		// PrimusPrimitives.getPublicKey fetches PublicKey via certificate, so null as well
		assert PrimusPrimitives.getPublicKey(name) == null;

		// PrimusPrimitives.getPublicKeyDirectly fetches PublicKey directly
		assert PrimusPrimitives.getPublicKeyDirectly(name) != null;

		// the property returnConveniencePrimusCerts lets PrimusPrimitives.getCertificate/getPublicKey generate a pseudo-certificate
		System.setProperty("com.securosys.primus.jce.returnConveniencePrimusCerts", "true");
		assert PrimusPrimitives.getPublicKey(name) != null;

	}

}

