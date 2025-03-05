
/*
 * Copyright (C) 2020, Securosys SA
 * Author Eric Laroche <eric@securosys.ch>
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusKeyHelper;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusWrap;

/**
 * Sample code showing wrapping of HSM keys with HSM keys
 * with the Securosys Primus HSM JCE provider.
 *
 * Uses different sets of flags for keys used with different operations (wrapping, or being wrapped),
 * gives the keys names, and retrieves them, runs sign tests with the unwrapped keys.
 *
 * This sample shows sone concurrency,
 * is, apart from that, however similar to UnwrapSignSample.java.
 */
public class UnwrapSignParallelSample {

	public static void main(final String... args) throws Exception {

		// test parameters

		final int testLengthSeconds = 40;
		final int concurrency = 64;

		// HSM configuration
		PrimusHelper.setup(args);

		// test unwrap/sign with EC wrapped by AES

		final String wrapName = "aes-wrap-key-" + UUID.randomUUID().toString();

		// 1 -- generate keys

		// generate AES key to wrap with, 256 bit size
		// wrap key does not need to be extractable (sensitive or not)
		final SecretKey aesWrapKey = (SecretKey)PrimusName.generate(
			KeyGenerator.getInstance("AES", PrimusProvider.getProviderName()),
			256,
			wrapName,
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true)
		);

		assert aesWrapKey != null; // obviously we want a key generated
		assert PrimusKeyHelper.isPrimusKey(aesWrapKey); // it should be HSM based
		assert aesWrapKey.getEncoded() == null; // no key bits available on the client (only on HSM)

		// retrieve it
		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keyStore.load(null);
		final SecretKey aesWrapKey2 = (SecretKey)keyStore.getKey(wrapName, null);

		assert aesWrapKey2 != null; // key should be retrievable
		assert PrimusKeyHelper.isPrimusKey(aesWrapKey2); // it should still be HSM based
		assert aesWrapKey2.getEncoded() == null; // no key bits available on the client (only on HSM)

		final String ecName = "ec-sign-key-" + UUID.randomUUID().toString();

		// generate EC key to be wrapped, secp256k1
		// key to be wrapped needs to be extractable (sensitive or not)
		final KeyPair ecKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			// new ECGenParameterSpec("secp256k1"), // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
			new ECGenParameterSpec("secp256r1"),
			ecName,
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true)
		);
		final ECPrivateKey ecKey = (ECPrivateKey)ecKeyPair.getPrivate();

		assert ecKey != null; // obviously we want a key [pair] generated
		assert PrimusKeyHelper.isPrimusKey(ecKey); // private key should be HSM based
		assert ecKey.getEncoded() == null; // no private key bits available on the client (only on HSM)

		// retrieve it
		final ECPrivateKey ecKey2 = (ECPrivateKey)keyStore.getKey(ecName, null);

		assert ecKey2 != null; // private key not null
		assert PrimusKeyHelper.isPrimusKey(ecKey2); // private key should still be HSM based
		assert ecKey2.getEncoded() == null; // no private key bits available on the client (only on HSM)

		// 2 -- wrap

		// wrap EC by AES
		final byte[] wrapped = PrimusWrap.aesWrapPadEc(aesWrapKey2, ecKey2);
		assert wrapped != null; // expecting bytes
		assert wrapped.length > 0; // with contents

		// delete it
		keyStore.deleteEntry(ecName);

		assert keyStore.getKey(ecName, null) == null;

		// 3 -- use for unwrap/sign

		// operations counter, for performance measurement
		final AtomicLong count = new AtomicLong(0);

		final Runnable runnable = new Runnable() {
			@Override public void run() {

				try {

					// configure thread with HSM address/port/user
					// PrimusConfiguration.setHsmHost(host0, port0, user0);
					// HSM configuration
					PrimusHelper.setup(args);

					// unwrap loop
					for (;;) {

						// depending on the source of (local) randomness, this might artificially (client-inflicted) slow down tests
						final byte[] message = UUID.randomUUID().toString().getBytes();

						final byte[] signed = PrimusWrap.aesUnwrapPadEcSign("SHA256withECDSA", message, aesWrapKey2, wrapped);

						assert signed != null; // a signature
						assert signed.length > 0; // with contents

						count.incrementAndGet();

					}

				} catch (final Exception e) {
					throw new RuntimeException(e);
				}

			}
		};

		// create deamon threads (will be terminated at main thread exit time)
		for (int i = 0; i < concurrency; i++) {
			final String threadName = "worker#" + i;
			final Thread thread = new Thread(runnable, threadName);
			thread.setDaemon(true);
			thread.setUncaughtExceptionHandler(Thread.currentThread().getUncaughtExceptionHandler());
			thread.start();
		}

		// main thread pauses for test length
		// (simple test setup of this code sample does not take into account early started threads running ahead already)
		// (one would run in a staged manner, e.g. with a CyclicBarrier)
		// expect performance measurement differences below 1% though
		Thread.sleep(TimeUnit.SECONDS.toMillis(testLengthSeconds));

		// print result

		final long signatureOperations = count.get();
		final long perSecond = signatureOperations / testLengthSeconds;

		System.out.println(
			signatureOperations + " signature operations in " + testLengthSeconds + " seconds"
			+ ", " + perSecond + " per second"
		);

	}

}

