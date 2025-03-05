
/*
 * Copyright (C) 2018,2020, Securosys SA
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.securosys.primus.jce.PrimusCertificate;
import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusDevice;
import com.securosys.primus.jce.PrimusLogin;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusProviderException;

public class ClusterSample {

	public static void main(final String... args) throws Exception {

		final String clusterhosts = "nufenen.securosys.ch,grimsel.securosys.ch"; // random, unweighted
		// final String clusterhosts = "nufenen.securosys.ch(.8),grimsel.securosys.ch(.2)"; // random, weighted
		// final String clusterhosts = "nufenen.securosys.ch;grimsel.securosys.ch"; // prioritized
		final String clusterports = "2401,2400";
		final String user = "...";
		final char[] password = "...".toCharArray();

		final PrimusProvider primusProvider = new PrimusProvider();
		Security.addProvider(primusProvider);
		PrimusConfiguration.setHsmHost(clusterhosts, clusterports, user);

		System.out.println();
		System.out.println("log in to " + clusterhosts + " / " + clusterports + " / " + user);
		PrimusLogin.login(user, password);

		System.out.println("on HSM device " + PrimusDevice.getDeviceName());

		System.out.println("generating key");
		final KeyPairGenerator primusKeyPairGenerator = KeyPairGenerator.getInstance("EC", primusProvider);
		primusKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		KeyPair keyPair = primusKeyPairGenerator.generateKeyPair();

		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), primusProvider);
		keyStore.load(null);
		final String keyName = UUID.randomUUID().toString();
		keyStore.setKeyEntry(keyName, keyPair.getPrivate(), null, new Certificate[] {new PrimusCertificate(keyPair.getPublic())});
		keyPair = new KeyPair(keyStore.getCertificate(keyName).getPublicKey(), (PrivateKey)keyStore.getKey(keyName, null));

		final String msgBase = UUID.randomUUID().toString();
		final Signature signature = Signature.getInstance("SHA256withECDSA", primusProvider);
		final Signature signature2 = Signature.getInstance(signature.getAlgorithm());

		final long timeToSleep = 5;
		final long timeToBreak = 5;

		for (long i = 1;; i++) {

			try {

				System.out.println();
				System.out.println("on HSM device " + PrimusDevice.getDeviceName());

				System.out.println("signing");
				final byte[] msg = (msgBase + "-" + i).getBytes(StandardCharsets.UTF_8);

				signature.initSign(keyPair.getPrivate());
				signature.update(msg);
				final byte[] sig = signature.sign();

				signature2.initVerify(keyPair.getPublic());
				signature2.update(msg);
				final boolean verified = signature2.verify(sig);
				System.out.println("signature " + (verified ? "OK" : "not OK"));

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

