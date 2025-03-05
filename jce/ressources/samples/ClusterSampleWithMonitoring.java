
/*
 * Copyright (C) 2018-2022, Securosys SA
 */

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import com.securosys.primus.jce.PrimusCertificate;
import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusDevice;
import com.securosys.primus.jce.PrimusLogin;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusProviderException;

public class ClusterSampleWithMonitoring {

	public static void main(final String... args) throws Exception {

		final String clusterhosts = "nufenen.securosys.ch,grimsel.securosys.ch"; // random, unweighted
		// final String clusterhosts = "nufenen.securosys.ch(.8),grimsel.securosys.ch(.2)"; // random, weighted
		// final String clusterhosts = "nufenen.securosys.ch;grimsel.securosys.ch"; // prioritized

		final String clusterports = "2300,2300";
		final String user = "...";
		final char[] password = "...".toCharArray();

		Security.addProvider(new PrimusProvider());
		PrimusConfiguration.setHsmHost(clusterhosts, clusterports, user);

		System.out.println();
		System.out.println("log in to " + clusterhosts + " / " + clusterports + " / " + user);
		PrimusLogin.login(user, password);

		System.out.println("on HSM device " + PrimusDevice.getDeviceName());

		System.out.println("generating key");
		final KeyPairGenerator primusKeyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		// primusKeyPairGenerator.initialize(new ECGenParameterSpec("secp256k1")); // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
		primusKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		KeyPair keyPair = primusKeyPairGenerator.generateKeyPair();

		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keyStore.load(null);
		final String keyName = UUID.randomUUID().toString();
		keyStore.setKeyEntry(keyName, keyPair.getPrivate(), null, new Certificate[] {new PrimusCertificate(keyPair.getPublic())});
		keyPair = new KeyPair(keyStore.getCertificate(keyName).getPublicKey(), (PrivateKey)keyStore.getKey(keyName, null));

		final Runnable monitorRunnable = new Runnable() {
			@Override public void run() {

				StringTokenizer t = new StringTokenizer(clusterhosts, ",");
				final String[] hosts = new String[t.countTokens()];
				int i;
				for (i = 0; t.hasMoreTokens(); i++) {
					hosts[i] = t.nextToken();
				}
				final String[] ports = new String[hosts.length];
				t = new StringTokenizer(clusterports, ",");
				for (i = 0; t.hasMoreTokens() && i < ports.length; i++) {
					ports[i] = t.nextToken();
				}
				for (; i < ports.length; i++) {
					ports[i] = ports[0];
				}

				for (i = 0; i < hosts.length; i++) {
					final String host = hosts[i];
					final String port = ports[i];

					PrimusConfiguration.setHsmHost(host, port, user);
					PrimusConfiguration.setCallTimeoutNanos(TimeUnit.SECONDS.toNanos(10));

					try {
						PrimusLogin.login(user, password);
						PrimusDevice.getDeviceName();
					} catch (final RuntimeException e) {
						System.err.println(host + ":" + port + ": " + e);
					}
				}
			}
		};
		Executors.newSingleThreadScheduledExecutor(
			new ThreadFactory() {
				@Override public Thread newThread(final Runnable r) {
					final Thread thread = Executors.defaultThreadFactory().newThread(r);
					thread.setName("HSM-Connectivity-Monitoring");
					thread.setDaemon(true);
					return thread;
				}
			}
		).scheduleAtFixedRate(monitorRunnable, 30, 30, TimeUnit.SECONDS);

		final long timeToSleep = 5;
		final long timeToBreak = 5;

		for (long i = 1;; i++) {

			try {

				System.out.println();
				System.out.println("on HSM device " + PrimusDevice.getDeviceName());

				System.out.println("signing");
				final byte[] msg = UUID.randomUUID().toString().getBytes();

				Signature signature = Signature.getInstance("SHA256withECDSA", PrimusProvider.getProviderName());
				signature.initSign(keyPair.getPrivate());
				signature.update(msg);
				final byte[] sig = signature.sign();

				signature = Signature.getInstance(signature.getAlgorithm());
				signature.initVerify(keyPair.getPublic());
				signature.update(msg);
				final boolean ver = signature.verify(sig);

				System.out.println("signature " + (ver ? "OK" : "not OK"));

				if (i % timeToBreak == 0) {
					System.out.println();
					System.out.println("interrupt TCP connection");
					// NOTE: internal API, only for illustration here
					com.securosys.primus.jce.transport.SharedTransport.getSharedTransport().close(new IOException("ClusterSample test interruption"));
				}

			} catch (final PrimusProviderException e) {
				System.out.println(e);
			}

			Thread.sleep(TimeUnit.SECONDS.toMillis(timeToSleep));
		}
	}

}

