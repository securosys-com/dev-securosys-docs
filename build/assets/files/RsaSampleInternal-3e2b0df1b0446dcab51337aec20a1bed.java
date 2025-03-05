
/*
 * Copyright (C) 2020, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.util.UUID;

import com.securosys.primus.jce.PrimusProvider;

/**
 * RSA sign sample
 * with the Securosys Primus HSM JCE provider
 * using a normal internal key.
 */
public class RsaSampleInternal {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), primusProvider);
		keyStore.load(null);

		// key creation

		final String keyname = UUID.randomUUID().toString();

		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", primusProvider);
		keyPairGenerator.initialize(2048);
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		keyStore.setKeyEntry(keyname, keyPair.getPrivate(), null, new Certificate[1]);

		// key use

		final byte[] message = UUID.randomUUID().toString().getBytes();

		final RSAPrivateKey key = (RSAPrivateKey)keyStore.getKey(keyname, null);

		final Signature signature = Signature.getInstance("SHA256withRSA", primusProvider);
		signature.initSign(key);
		signature.update(message);
		final byte[] sig = signature.sign();

		// check

		final Signature verify = Signature.getInstance("SHA256withRSA");
		verify.initVerify(keyPair.getPublic());
		verify.update(message);
		assert verify.verify(sig);

	}

}

