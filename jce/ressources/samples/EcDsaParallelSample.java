
/*
 * Copyright (C) 2020, Securosys SA
 * Author Eric Laroche <eric@securosys.ch>
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;

/**
 * EC-DSA sample with sone concurrency
 * for the Securosys Primus HSM JCE provider.
 */
public class EcDsaParallelSample {

	public static void main(final String... args) throws Exception {

		// test parameters

		final int testLengthSeconds = 40;
		final int concurrency = 64;

		final String curve = "secp256k1"; // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
		final String signAlgorithm = "SHA256withECDSA";

		// HSM configuration
		PrimusHelper.setup(args);

		// generate key
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		keyPairGenerator.initialize(new ECGenParameterSpec(curve));
		// key-password-less use is faster
		PrimusName.presetName(UUID.randomUUID().toString(), null);
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// operations counter, for performance measurement
		final AtomicLong count = new AtomicLong(0);

		final Runnable runnable = new Runnable() {
			@Override public void run() {

				try {

					// configure thread with HSM address/port/user
					// HSM configuration
					PrimusHelper.setup(args);

					final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());

					// sign loop
					for (;;) {

						// depending on the source of (local) randomness, this might artificially (client-inflicted) slow down tests
						final byte[] message = UUID.randomUUID().toString().getBytes();

						signature.initSign(keyPair.getPrivate());
						signature.update(message);
						final byte[] signatureBytes = signature.sign();
						assert signatureBytes != null && signatureBytes.length > 0;

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

