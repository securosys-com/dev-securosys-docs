
/*
 * Copyright (C) 2019, Securosys SA
 */

import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code using ChaCha20 symmetric cipher, Poly1305 MAC, and ChaCha20 AEAD,
 * with the Securosys Primus HSM JCE provider.
 *
 * Requires Primus JCE Provider version 1.8.4 or later.
 */
public class ChaChaSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration

		PrimusHelper.setup(args);

		// create a ChaCha20 key on the HSM

		KeyGenerator keyGenerator = KeyGenerator.getInstance("ChaCha20", PrimusProvider.getProviderName());
		keyGenerator.init(256);
		Key key = keyGenerator.generateKey();
		assert key != null;

		// encrypt/decrypt with ChaCha20

		final byte[] msg = UUID.randomUUID().toString().getBytes();

		Cipher cipher = Cipher.getInstance("ChaCha20", PrimusProvider.getProviderName());
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] enc = cipher.doFinal(msg);
		AlgorithmParameters parameters = cipher.getParameters();
		cipher.init(Cipher.DECRYPT_MODE, key, parameters);
		byte[] dec = cipher.doFinal(enc);
		assert Arrays.equals(msg, dec);

		// encrypt/decrypt with ChaCha20AEAD and AAD

		final byte[] aad = UUID.randomUUID().toString().getBytes();

		cipher = Cipher.getInstance("ChaCha20AEAD", PrimusProvider.getProviderName());
		cipher.init(Cipher.ENCRYPT_MODE, key);
		cipher.updateAAD(aad);
		enc = cipher.doFinal(msg);
		parameters = cipher.getParameters();
		cipher.init(Cipher.DECRYPT_MODE, key, parameters);
		cipher.updateAAD(aad);
		dec = cipher.doFinal(enc);
		assert Arrays.equals(msg, dec);

		// MAC with Poly1305

		keyGenerator = KeyGenerator.getInstance("Poly1305", PrimusProvider.getProviderName());
		keyGenerator.init(256);
		key = keyGenerator.generateKey();
		assert key != null;

		final Mac mac = Mac.getInstance("Poly1305", PrimusProvider.getProviderName());
		mac.init(key);
		mac.doFinal(msg);

		// local ChaCha20 key import to HSM

		byte[] bytes = new byte[256 / 8];
		(new SecureRandom()).nextBytes(bytes);
		key = new SecretKeySpec(bytes, "ChaCha20");

		// import- and normal keystores
		KeyStore exportImportKeyStore = KeyStore.getInstance(PrimusProvider.getImportExportKeyStoreTypeName(), PrimusProvider.getProviderName());
		exportImportKeyStore.load(null);
		KeyStore normalKeyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		normalKeyStore.load(null);

		String importname = UUID.randomUUID().toString();
		exportImportKeyStore.setKeyEntry(importname, key, null, null);
		key = normalKeyStore.getKey(importname, null);
		assert key != null;

		// encrypt/decrypt test with it
		cipher = Cipher.getInstance("ChaCha20", PrimusProvider.getProviderName());
		cipher.init(Cipher.ENCRYPT_MODE, key);
		enc = cipher.doFinal(msg);
		parameters = cipher.getParameters();
		cipher.init(Cipher.DECRYPT_MODE, key, parameters);
		dec = cipher.doFinal(enc);
		assert Arrays.equals(msg, dec);

	}

}

