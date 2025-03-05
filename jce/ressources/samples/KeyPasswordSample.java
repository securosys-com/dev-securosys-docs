
/*
 * Copyright (C) 2015-2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.util.UUID;

import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusX509Certificate;

/**
 * Sample having and having not key passwords
 * with the Securosys Primus HSM JCE provider.
 */
public class KeyPasswordSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// generating a key pair
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", primusProvider);
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		assert keyPair != null; // [generateKeyPair should return something]
		assert keyPair.getPublic() != null; // [the public part should be there]
		assert keyPair.getPublic().getEncoded() != null; // [the public key reference should contain the public key material]
		assert keyPair.getPublic().getEncoded().length > 0;
		assert keyPair.getPrivate() != null; // [the private part should be a reference on the HSM key]
		assert keyPair.getPrivate().getEncoded() == null; // [the private key reference should not contain any key material]

		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), primusProvider);
		keyStore.load(null); // no-op, but mandatory for JCA

		// naming/persisting the key pair, with a key password
		final String keyName = UUID.randomUUID().toString(); // random name
		final char[] keyPassword = UUID.randomUUID().toString().toCharArray(); // random password
		final char[] keyPassword2 = UUID.randomUUID().toString().toCharArray(); // another random password
		final Certificate[] certificateChain = new Certificate[] {new PrimusX509Certificate(keyPair.getPublic())};
		keyStore.setKeyEntry(keyName, keyPair.getPrivate(), keyPassword, certificateChain);

		PrivateKey key = null;

		// retrieving key references:

		// no key password given: will fail
		try {
			key = (PrivateKey)keyStore.getKey(keyName, null);
		} catch (final UnrecoverableKeyException e) {
			// expected
			System.out.println("expected: " + e);
		}
		assert key == null;

		// wrong key password given: will fail
		try {
			key = (PrivateKey)keyStore.getKey(keyName, keyPassword2);
		} catch (final UnrecoverableKeyException e) {
			// expected
			System.out.println("expected: " + e);
		}
		assert key == null;

		// right key password given: will succeed
		key = (PrivateKey)keyStore.getKey(keyName, keyPassword);
		assert key != null;

		// give the the key another password
		keyStore.setKeyEntry(keyName, key, keyPassword2, certificateChain);
		key = null;

		// old, now wrong key password given: will fail
		try {
			key = (PrivateKey)keyStore.getKey(keyName, keyPassword);
		} catch (final UnrecoverableKeyException e) {
			// expected
			System.out.println("expected: " + e);
		}
		assert key == null;

		// no key password given: will fail too/still/again
		try {
			key = (PrivateKey)keyStore.getKey(keyName, null);
		} catch (final UnrecoverableKeyException e) {
			// expected
			System.out.println("expected: " + e);
		}
		assert key == null;

		// new, now right key password given: will succeed
		key = (PrivateKey)keyStore.getKey(keyName, keyPassword2);
		assert key != null;

		// give the the key no password
		keyStore.setKeyEntry(keyName, key, null, certificateChain);
		key = null;

		// key password given: may or may not now fail
		try {
			key = (PrivateKey)keyStore.getKey(keyName, keyPassword);
		} catch (final UnrecoverableKeyException e) {
			// expected
			System.out.println("expected: " + e);
		}
		assert key == null || key != null;
		key = null;
		try {
			key = (PrivateKey)keyStore.getKey(keyName, keyPassword2);
		} catch (final UnrecoverableKeyException e) {
			// expected
			System.out.println("expected: " + e);
		}
		assert key == null || key != null;
		key = null;

		// no key password given: now succeeds
		key = (PrivateKey)keyStore.getKey(keyName, null);
		assert key != null;

		// give it a password again
		keyStore.setKeyEntry(keyName, key, keyPassword, certificateChain);
		key = null;

		// now same behavior as before expected:

		// no key password given: will fail
		try {
			key = (PrivateKey)keyStore.getKey(keyName, null);
		} catch (final UnrecoverableKeyException e) {
			// expected
			System.out.println("expected: " + e);
		}
		assert key == null;

		// wrong key password given: will fail
		try {
			key = (PrivateKey)keyStore.getKey(keyName, keyPassword2);
		} catch (final UnrecoverableKeyException e) {
			// expected
			System.out.println("expected: " + e);
		}
		assert key == null;

		// right key password given: will succeed
		key = (PrivateKey)keyStore.getKey(keyName, keyPassword);
		assert key != null;

	}

}

