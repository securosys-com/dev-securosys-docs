
/*
 * Copyright (C) 2017-2023, Securosys SA
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.util.Enumeration;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusPrimitives;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code using Securosys Primus HSM and its JCE provider
 * for establishing TLS (SSL) secure sockets (secure transport layer),
 * client and server (in different processes/JVMs).
 *
 * <p>
 * The approach is to split things,
 * into setup (key generation by Sun/keytool, for interop, and avoiding keytool's provider use problems),
 * server, and client (separating into different JVMs, avoiding TLS singleton state interferences).
 *
 * <p>
 * sample use (setup, TLS server with HSM (in background), TLS client locally):
 * <pre>
 * rm ca.keystore client.keystore server.keystore
 * java -cp bin:primusX.jar TlsInteropSample setup
 * java -cp bin:primusX.jar TlsInteropSample hsm server &
 * java -cp bin:primusX.jar TlsInteropSample client
 * </pre>
 */
public class TlsInteropSample extends TlsSample {

	{
		// this interop version uses well-defined key/cert names
		caKey = "ca.key";
		serverKey = "server.key";
		clientKey = "client.key";
	}

	public static void main(final String... args) throws Exception {
		(new TlsInteropSample()).imain(args);
	}

	@Override
	void imain(final String... args) throws Exception {
		if (args != null) {
			config();
			bindPort = Integer.valueOf(6789);
			for (final String arg : args) {
				if (arg == null) {
					//

				} else if (arg.equals("setup")) { // setup mode
					setup(); // set up keys and certs locally, and transfer keys and certs from local to HSM

				} else if (arg.equals("create")) { // key creation mode
					create(); // set up keys and certs locally

				} else if (arg.equals("dump")) {
					dump(); // dump keys and certs

				} else if (arg.equals("transfer")) { // key transfer mode
					transfer(); // transfer keys and certs from local to HSM

				} else if (arg.equals("hsm")) {
					login(); // login step; rest will be done on HSM

				} else if (arg.equals("server")) { // server mode
					server(); // do locally or on HSM

				} else if (arg.equals("client")) { // client mode (start server first!)
					pipe.put(bindPort); // predefined port
					client(); // do locally or on HSM

				} else { // bind port step expected
					bindPort = Integer.valueOf(arg);
				}
			}
		}
	}

	@Override
	void setup() throws Exception {
		create();
		transfer();
	}

	// locally create keys and certificates
	void create() throws Exception {
		// cleanup: optionally remove local file keystores first (ignore failure return values)
		(new File(caKeyStore)).delete();
		(new File(serverKeyStore)).delete();
		(new File(clientKeyStore)).delete();

		super.setup(); // set up keys and certs locally
	}

	// dump the keystores
	void dump() throws Exception {
		dump(caKeyStore);
		dump(serverKeyStore);
		dump(clientKeyStore);
	}

	// transfer (copy) the keystores to HSM
	void transfer() throws Exception {
		login(); // log in to HSM
		try {

			// cleanup: optionally remove transferred keys on hsm (ignore failure exception)
			try {PrimusPrimitives.getKeyStore().deleteEntry(caKey);} catch (final KeyStoreException e) { /* */ }
			try {PrimusPrimitives.getKeyStore().deleteEntry(serverKey);} catch (final KeyStoreException e) { /* */ }
			try {PrimusPrimitives.getKeyStore().deleteEntry(clientKey);} catch (final KeyStoreException e) { /* */ }

			// transfer the keystores to the HSM too, for interop tests
			transfer(caKeyStore);
			transfer(serverKeyStore);
			transfer(clientKeyStore);

		} finally {
			logout();
		}
	}

	// invoke external key tool (KeyToolX is an adapter to Java's keytool command, -invokeplain option makes it run as is);
	// let it do the operations locally on file keystore
	@Override
	void keytool(final String keystore, final String... args) throws Exception {
		// do locally (plain, keystoretype pkcs12)
		final String[] head = new String[] {"-invokeplain", "-keystore", keystore, "-storetype", "pkcs12", "-storepass", String.valueOf(storePassword),};
		final String[] args2 = new String[head.length + args.length];
		System.arraycopy(head, 0, args2, 0, head.length);
		System.arraycopy(args, 0, args2, head.length, args.length);
		com.securosys.primus.tool2.KeyToolX.invokeThrow(args2);
	}

	// with seperate file keystores the imports are not optional
	@Override
	void keytoolOptional(final String keystore, final String... args) throws Exception {
		// optional imports to the separated keystores is exercised
		keytool(keystore, args);
	}

	// note: for proper RSA parameter import, via cert below, primusX.jar 2.2.3 or newer is needed
	// otherwise an NPE in sun.security.util.KeyUtil.getKeySize can be expected
	void transfer(final String keystore) throws Exception {
		final KeyStore ks = KeyStore.getInstance("pkcs12");
		try (final InputStream is = new FileInputStream(keystore)) {
			ks.load(is, storePassword.clone());
		}
		final KeyStore kst = KeyStore.getInstance(PrimusProvider.getImportExportKeyStoreTypeName(), primusProvider);
		kst.load(null);

		for (final Enumeration<String> i = ks.aliases(); i.hasMoreElements();) {
			final String a = i.nextElement();
			final Key k = ks.getKey(a, keyPassword.clone());
			final Certificate c = ks.getCertificate(a);
			Certificate[] cc = ks.getCertificateChain(a);
			if (k != null) {
				if (cc != null && cc.length != 0) {
					// prefer cert chains over simple cert
				} else if (c != null) {
					cc = new Certificate[] {c};
				} else { // fallback (setKeyEntry requires a non-null non-empty chain)
					cc = new Certificate[] {null};
				}
				kst.setKeyEntry(a, k, keyPassword.clone(), cc);
			} else if (c != null) {
				kst.setCertificateEntry(a, c);
			} else if (cc != null && cc.length != 0) {
				kst.setCertificateEntry(a, cc[0]);
			}
		}
	}

	void dump(final String keystore) throws Exception {
		System.out.println();
		System.out.println("-- " + keystore + ":");

		final KeyStore ks = KeyStore.getInstance("pkcs12");
		try (final InputStream is = new FileInputStream(keystore)) {
			ks.load(is, storePassword.clone());
		}

		for (final Enumeration<String> i = ks.aliases(); i.hasMoreElements();) {
			final String a = i.nextElement();
			System.out.println("---- " + a + ":");

			final Key k = ks.getKey(a, keyPassword.clone());
			final Certificate c = ks.getCertificate(a);
			final Certificate[] cc = ks.getCertificateChain(a);

			System.out.println("------ key:");
			if (k != null) {
				System.out.println(k.getClass().getName());
				System.out.println(k);
				final byte[] ke = k.getEncoded();
				System.out.println(PrimusEncoding.hexEncode(ke));
				if (ke != null) {
					System.out.println(PrimusEncoding.dumpDERCompact(ke));
				}
			}

			System.out.println("------ certificate:");
			if (c != null) {
				System.out.println(c.getClass().getName());
				System.out.println(c);
				final byte[] ce = c.getEncoded();
				System.out.println(PrimusEncoding.hexEncode(ce));
				if (ce != null) {
					System.out.println(PrimusEncoding.dumpDERCompact(ce));
				}
			}

			System.out.println("------ certificate chain:");
			if (cc != null) {
				System.out.println(cc.getClass().getName());
				for (final Certificate ccc : cc) {
					System.out.println("-------- certificate:");
					if (ccc != null) {
						System.out.println(ccc.getClass().getName());
						System.out.println(ccc);
						final byte[] ccce = ccc.getEncoded();
						System.out.println(PrimusEncoding.hexEncode(ccce));
						if (ccce != null) {
							System.out.println(PrimusEncoding.dumpDERCompact(ccce));
						}
					}
				}
			}
		}
	}

	@Override
	void server() throws Exception {

		if (primusProvider != null) {
			super.server(); // do on HSM
			return;
		}

		// do locally

		final KeyStore serverTrustManagerKeyStore = KeyStore.getInstance("pkcs12");
		try (final InputStream is = new FileInputStream(caKeyStore)) {
			serverTrustManagerKeyStore.load(is, storePassword.clone());
		}
		final TrustManagerFactory serverTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		serverTrustManagerFactory.init(serverTrustManagerKeyStore);
		final TrustManager[] serverTrustManagers = serverTrustManagerFactory.getTrustManagers();

		final KeyStore serverKeyManagerKeyStore = KeyStore.getInstance("pkcs12");
		try (final InputStream is = new FileInputStream(serverKeyStore)) {
			serverKeyManagerKeyStore.load(is, storePassword.clone());
		}
		final KeyManagerFactory serverKeyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		serverKeyManagerFactory.init(serverKeyManagerKeyStore, keyPassword.clone());
		final KeyManager[] serverKeyManagers = serverKeyManagerFactory.getKeyManagers();

		final SSLContext sslServerContext = SSLContext.getInstance("TLS");
		sslServerContext.init(serverKeyManagers, serverTrustManagers, null);

		server(sslServerContext);
	}

	@Override
	void client() throws Exception {

		if (primusProvider != null) {
			super.client(); // do on HSM
			return;
		}

		// do locally

		final KeyStore clientTrustManagerKeyStore = KeyStore.getInstance("pkcs12");
		try (final InputStream is = new FileInputStream(caKeyStore)) {
			clientTrustManagerKeyStore.load(is, storePassword.clone());
		}
		final TrustManagerFactory clientTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		clientTrustManagerFactory.init(clientTrustManagerKeyStore);
		final TrustManager[] clientTrustManagers = clientTrustManagerFactory.getTrustManagers();

		final KeyStore clientKeyManagerKeyStore = KeyStore.getInstance("pkcs12");
		try (final InputStream is = new FileInputStream(clientKeyStore)) {
			clientKeyManagerKeyStore.load(is, storePassword.clone());
		}
		final KeyManagerFactory clientKeyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		clientKeyManagerFactory.init(clientKeyManagerKeyStore, keyPassword.clone());
		final KeyManager[] clientKeyManagers = clientKeyManagerFactory.getKeyManagers();

		final SSLContext sslClientContext = SSLContext.getInstance("TLS");
		sslClientContext.init(clientKeyManagers, clientTrustManagers, null);

		client(sslClientContext);
	}

}

