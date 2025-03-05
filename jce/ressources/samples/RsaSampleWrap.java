
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

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusWrap;

/**
 * RSA sign sample
 * with the Securosys Primus HSM JCE provider
 * using a wrapped key.
 */
public class RsaSampleWrap {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), primusProvider);
		keyStore.load(null);

		// setup

		final String aeskeyname = UUID.randomUUID().toString();

		final KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", primusProvider);
		keyGenerator.init(256);
		final SecretKey aes = keyGenerator.generateKey();
		keyStore.setKeyEntry(aeskeyname, aes, null, new Certificate[1]);

		// key creation

		final String keyname = UUID.randomUUID().toString();
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", primusProvider);
		keyPairGenerator.initialize(2048);
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		keyStore.setKeyEntry(keyname, keyPair.getPrivate(), null, new Certificate[1]);
		final byte[] wrapped = PrimusWrap.aesWrapPadRsa((SecretKey)keyStore.getKey(aeskeyname, null), (RSAPrivateKey)keyStore.getKey(keyname, null));
		keyStore.deleteEntry(keyname);

		// key use

		final byte[] message = UUID.randomUUID().toString().getBytes();

		final RSAPrivateKey key = PrimusWrap.aesUnwrapPadRsa((SecretKey)keyStore.getKey(aeskeyname, null), wrapped);

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

