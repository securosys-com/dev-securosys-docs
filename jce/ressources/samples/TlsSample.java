
/*
 * Copyright (C) 2017-2023, Securosys SA
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusLogin;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.spi0.DerivedKeyStoreSpi;

/**
 * Sample code using Securosys Primus HSM and its JCE provider
 * for establishing TLS (SSL) secure sockets (secure transport layer),
 * client and server (separate threads, same JVM).
 * All keys/certs are on HSM, all on one HSM keystore.
 */
public class TlsSample {

	// HSM credentials
	String host;
	String port;
	String user;
	char[] password;

	// key passwords [Java runtime's keytool requires: a) key passwords, and b) with a minimal size [e.g.: 6 characters]]
	char[] keyPassword = "password".toCharArray();

	// store password (for derived interop code)
	// PKCS#12 store requires store password and key password equivalency
	char[] storePassword = keyPassword;

	// RSA parameters
	String RSA = "RSA";
	String RSAkeysize = "2048";
	String RSAsigalg = "SHA256withRSA";

	// EC parameters
	String EC = "EC";
	// String ECkeysize = "256";
	String ECkeysize = "384";
	String ECsigalg = "SHA256withECDSA";

	// DSA parameters
	String DSA = "DSA";
	String DSAkeysize = "2048";
	String DSAsigalg = "SHA256withDSA";

	// key algorithm used
	String keyAlgorithm = EC;

	// base name: random (to avoid duplicate-key collisions)
	String baseName = UUID.randomUUID().toString();

	// names of the keys and certs
	String caKey = baseName + "-ca-key";
	String serverKey = baseName + "-server-key";
	String clientKey = baseName + "-client-key";

	// names of the local/separated keystores (for derived interop code)
	String caKeyStore = "ca.keystore";
	String serverKeyStore = "server.keystore";
	String clientKeyStore = "client.keystore";

	String keysize(String keyalg) {return (keyalg == DSA ? DSAkeysize : keyalg == EC ? ECkeysize : keyalg == RSA ? RSAkeysize : null);}
	String sigalg(String keyalg) {return (keyalg == DSA ? DSAsigalg : keyalg == EC ? ECsigalg : keyalg == RSA ? RSAsigalg : null);}

	// Primus JCE provider
	PrimusProvider primusProvider;

	// defined TCP port for the TLS communication (for derived interop code)
	Integer bindPort;

	// IPC facility for client/server threads
	BlockingQueue<Integer> pipe = new LinkedBlockingQueue<Integer>();

	public static void main(final String... args) throws Exception {
		(new TlsSample()).imain(args);
	}

	// 'instance main', for polymorphism (i.e. derived test cases/samples)
	void imain(final String... args) throws Exception {
		assert args != null;
		for (final String arg : args) {
			if (arg == null) {
				//
			} else if (arg.equalsIgnoreCase(RSA)) {
				keyAlgorithm = RSA;
			} else if (arg.equalsIgnoreCase(EC)) {
				keyAlgorithm = EC;
			} else if (arg.equalsIgnoreCase(DSA)) {
				keyAlgorithm = DSA;
			} else {
				//
			}
		}

		// configure [JVM] for HSM and TLS
		config();

		// set up keys and certs
		System.out.println("setting up keys and certificates...");
		setup();
		System.out.println("setup done");
		System.out.println();

		// log in to HSM
		login();

		// server runs in separate thread
		final ExecutorService serverThread = Executors.newSingleThreadExecutor();
		serverThread.submit(new Callable<Void>() {
			@Override public Void call() throws Exception {
				// name the server (service) thread
				Thread.currentThread().setName("server");

				// unless PrimusConfiguration.setUseThreadLocalHsmConfiguration(false); was called:
				// need to configure per thread
				PrimusConfiguration.setHsmHost(host, port, user);

				try {

					// run server
					server();

				} catch (final Exception e) {
					System.err.println("server exception: " + e);
					e.printStackTrace(System.err);
				}
				return null;
			}
		});
		serverThread.shutdown();

		// rename the client (main) thread
		Thread.currentThread().setName("client");

		try {

			// run client
			client();

		} catch (final Exception e) {
			System.err.println("client exception: " + e);
			e.printStackTrace(System.err);
		}

		System.out.println("done");
		System.out.println();
	}

	// configure for HSM and TLS
	void config() throws Exception {

		// HSM configuration

		// host = "grimsel.securosys.ch"; // externally reachable test/development HSM
		// host = "nufenen.securosys.ch"; // externally reachable test/development HSM, clustered with grimsel
		// port = "2400"; // internal default port: "2300", external reachable ports: "2400" for grimsel, "2401" for nufenen (hostnames may point to the same IP)
		// user = "PRIMUSDEV999"; // HSM user
		// password = ...; // password, not in string pool, for security considerations

		host = "julier"; // an internal test system
		port = "2300";
		user = "JENKINS";
		password = "694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825".toCharArray();

		// make HSM configuration configurable
		host = System.getProperty("host", host);
		port = System.getProperty("port", port);
		user = System.getProperty("user", user);
		password = System.getProperty("password", new String(password)).toCharArray();

		// parameterization/configuration of Sun's TLS and Primus' JCE:

		// debug outputs:

		// Sun's TLS/SSL engine debugging [this lets you follow the TLS handshake]:
		System.setProperty("javax.net.debug", "all");

		// Primus' logging
		// logs likely too much
		// System.setProperty("com.securosys.primus.jce.logging", "true");

		// Primus' logging of operations to HSM
		System.setProperty("com.securosys.primus.jce.AsyncBroker.logCommandCallStack", "true");

		// Primus JCE service picking debugging [this shows what JCA askes of Primus, and what is returned]:
		System.setProperty("com.securosys.primus.jce.servicePeekerAll", "true");

		// SSL/TLS version specification:
		// [default picked up for Java 8 is "TLSv1.2", for Java 11 "TLSv1.3"]
		// System.setProperty("jdk.tls.client.protocols", "TLSv1.1");
		// System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
		System.setProperty("jdk.tls.client.protocols", "TLSv1.3");

		// EC group list to use
		// System.setProperty("jdk.tls.namedGroups", "secp256r1");
		// System.setProperty("jdk.tls.namedGroups", "secp384r1");
		// System.setProperty("jdk.tls.namedGroups", "secp521r1");

		// algorithm(s) not to use
		// [uses Security.setProperty instead of System.setProperty!]
		// String tda = Security.getProperty("jdk.tls.disabledAlgorithms");
		// tda = (tda == null ? "" : tda + ",");
		// tda += "RSASSA-PSS";
		// Security.setProperty("jdk.tls.disabledAlgorithms", tda);

		// following throws NPE in sun.security.rsa.RSAPSSSignature.engineSign, as JCA insists in PSS
		// havePss parameter requires JCE provider 2.2.3 or later
		// System.setProperty("com.securosys.primus.jce.PrimusJceServices.havePss", "false");

		// for curves larger than 256 and primusX.jar versions before 2.2.3:
		// System.setProperty("com.securosys.primus.jce.defaultUnknownAlgorithmKeySizeIsAllMaterialBits", "true");

		// [following parameters are not needed when below suppressDefault* parameters are set]
		// Sun TLS insists into initiating it with SecretKeySpec(salt,"HKDF-Salt")
		// System.setProperty("com.securosys.primus.jce.PrimusJceServices.haveMac", "false");
		// System.setProperty("com.securosys.primus.jce.PrimusJceServices.haveMessageDigest", "false");
		// System.setProperty("com.securosys.primus.jce.dhCreateHsmBasedSecretKeys", "true");
		// avoid CA certificate ephemeral public key import into HSM
		// System.setProperty("com.securosys.primus.jce.PrimusJceServices.haveKeyFactoryForImportExport", "false");

		// suppressDefault* parameters requires JCE provider 2.0.2 or later
		System.setProperty("com.securosys.primus.jce.suppressDefaultMacService", "true");
		System.setProperty("com.securosys.primus.jce.suppressDefaultKeyFactoryService", "true");
		System.setProperty("com.securosys.primus.jce.suppressDefaultMessageDigestService", "true");

	}

	// set up keys and certs
	void setup() throws Exception {

		// the object (keys, certs) space is split among 3 keystores, which, in the HSM case, can be the same, so some of the imports are optional

		// create [self signed] server CA [key pair and certificate]
		keytool(caKeyStore, "-genkeypair", "-alias", caKey, "-keypass", String.valueOf(keyPassword), "-keyalg", keyAlgorithm, "-keysize", keysize(keyAlgorithm), "-sigalg", sigalg(keyAlgorithm), "-dname", "CN=" + caKey, "-validity", "3650", "-ext", "BC:ca:true");
		// export the certificate
		final String caFile = File.createTempFile("tmp.", ".ca").getPath();
		keytool(caKeyStore, "-exportcert", "-alias", caKey, "-file", caFile);

		// create server key [pair]
		keytool(serverKeyStore, "-genkeypair", "-alias", serverKey, "-keypass", String.valueOf(keyPassword), "-keyalg", keyAlgorithm, "-keysize", keysize(keyAlgorithm), "-sigalg", sigalg(keyAlgorithm), "-dname", "CN=" + serverKey, "-validity", "3650");
		// create a cert request
		final String csrFile = File.createTempFile("tmp.", ".csr").getPath();
		keytool(serverKeyStore, "-certreq", "-alias", serverKey, "-keypass", String.valueOf(keyPassword), "-file", csrFile);

		// sign the certification request, create a certificate
		final String crtFile = File.createTempFile("tmp.", ".crt").getPath();
		keytool(caKeyStore, "-gencert", "-alias", caKey, "-keypass", String.valueOf(keyPassword), "-infile", csrFile, "-outfile", crtFile);
		(new File(csrFile)).delete();

		// import ca
		keytoolOptional(serverKeyStore, "-importcert", "-noprompt", "-alias", caKey, "-file", caFile);
		// import the created certificate
		keytool(serverKeyStore, "-importcert", "-alias", serverKey, "-keypass", String.valueOf(keyPassword), "-file", crtFile);
		(new File(crtFile)).delete();

		// create client key [pair]
		keytool(clientKeyStore, "-genkeypair", "-alias", clientKey, "-keypass", String.valueOf(keyPassword), "-keyalg", keyAlgorithm, "-keysize", keysize(keyAlgorithm), "-sigalg", sigalg(keyAlgorithm), "-dname", "CN=" + clientKey, "-validity", "3650");
		// create a cert request
		keytool(clientKeyStore, "-certreq", "-alias", clientKey, "-keypass", String.valueOf(keyPassword), "-file", csrFile);

		// sign the certification request, create a certificate
		keytool(caKeyStore, "-gencert", "-alias", caKey, "-keypass", String.valueOf(keyPassword), "-infile", csrFile, "-outfile", crtFile);
		(new File(csrFile)).delete();

		// import ca
		keytoolOptional(clientKeyStore, "-importcert", "-noprompt", "-alias", caKey, "-file", caFile);
		// import the created certificate
		keytool(clientKeyStore, "-importcert", "-alias", clientKey, "-keypass", String.valueOf(keyPassword), "-file", crtFile);
		(new File(crtFile)).delete();

		(new File(caFile)).delete();

		// here is the above as command line calls:

		/*

		// create [self signed] server CA [key pair and certificate]
		java -jar keytoolX.jar -host julier -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -genkeypair -alias ca -keypass password -keyalg RSA -keysize 2048 -sigalg SHA256withRSA -dname CN=CA -validity 3650 -ext BC:ca:true
		// export the certificate
		java -jar keytoolX.jar -host julier -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -exportcert -alias ca -file ca.crt
		// create key pair
		java -jar keytoolX.jar -host julier -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -genkeypair -alias cs -keypass password -keyalg RSA -keysize 2048 -sigalg SHA256withRSA -dname CN=CS -validity 3650
		// create a cert request
		java -jar keytoolX.jar -host julier -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -certreq -alias cs -keypass password -file cs.csr
		// sign the certification request, create a certificate
		java -jar keytoolX.jar -host julier -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -gencert -alias ca -keypass password -infile cs.csr -outfile cs.crt
		// if the ca wasn't on there: import ca
		// java -jar keytoolX.jar -host julier -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -importcert -noprompt -alias ca -keypass password -file ca.crt
		// import the created certificate
		java -jar keytoolX.jar -host julier -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -importcert -alias cs -keypass password -file cs.crt

		*/

	}

	// invoke external key tool (KeyToolX is an adapter to Java's keytool command);
	// let it do the operations on the HSM
	void keytool(final String keystore, final String... args) throws Exception {
		assert keystore != null;

		final String[] head = new String[] {"-host", host, "-port", port, "-user", user, "-password", String.valueOf(password),};
		final String[] args2 = new String[head.length + args.length];
		System.arraycopy(head, 0, args2, 0, head.length);
		System.arraycopy(args, 0, args2, head.length, args.length);
		com.securosys.primus.tool2.KeyToolX.invokeThrow(args2);
	}

	// optional are the import operations in the HSM common keystore case, of certs that are already there;
	// with seperate file keystores (as in the derived sample) this is not the case
	void keytoolOptional(final String keystore, final String... args) throws Exception {
		assert keystore != null;
		assert args != null;
	}

	// login to the HSM; this will also set and install primusProvider,
	// which is later used to distinguish whether party should be run locally or on HSM
	void login() throws Exception {

		// configure for HSM address, global configuration, to have worker/helper threads configured already
		PrimusConfiguration.setUseThreadLocalHsmConfiguration(false);
		PrimusConfiguration.setHsmHost(host, port, user);

		// log in to HSM
		PrimusLogin.login(user, password);

		// load PrimusProvider
		primusProvider = new PrimusProvider();

		assert primusProvider != null;
		// for ssl engine's mapping to the Primus provider: add at head
		Security.insertProviderAt(primusProvider, 1);
		// alternatively could set system property com.securosys.primus.jce.pushProviderToFrontInitially=true
		// alternatively could set process environment variable com_securosys_primus_jce_pushProviderToFrontInitially=true
		// [using -invokedirect leaves us with different PrimusProvider instance at first place]
		// assert Security.getProviders()[0] == primusProvider;
		assert Security.getProviders()[0].getName() == primusProvider.getName();

		// if we chose to add the PrimusProvider at tail,
		// Security.addProvider(primusProvider);
		// SSL handshake will run into NPE, as it attempts signing with Sun's JCE and Primus' key
		// (and doesn't fail in an early signInit step, that would allow for retry with Primus' JCE):
		// Caused by: java.lang.NullPointerException
		// at sun.security.ec.ECDSASignature.signDigestNative(ECDSASignature.java:327)
		// at sun.security.ec.ECDSASignature.engineSign(ECDSASignature.java:366)
		// at java.security.Signature$Delegate.engineSign(Signature.java:1210)
		// at java.security.Signature.sign(Signature.java:582)
		// at sun.security.ssl.HandshakeMessage$ECDH_ServerKeyExchange.<init>(HandshakeMessage.java:1036)
		// at sun.security.ssl.ServerHandshaker.clientHello(ServerHandshaker.java:980)

	}

	// logout from HSM; this will also unset and uninstall primusProvider
	void logout() throws Exception {

		if (primusProvider != null) {
			Security.removeProvider(primusProvider.getName());
			primusProvider = null;

			PrimusLogin.logout();
			PrimusConfiguration.unsetHsmHost();
		}

	}

	// run the server part
	void server() throws Exception {

		final KeyStore serverTrustManagerKeyStore = KeyStore.getInstance(PrimusProvider.getTrustManagerKeyStoreTypeName(), primusProvider);
		serverTrustManagerKeyStore.load(new DerivedKeyStoreSpi.LoadStoreFilterParameter(new String[] {caKey}));
		final TrustManagerFactory serverTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		serverTrustManagerFactory.init(serverTrustManagerKeyStore);
		final TrustManager[] serverTrustManagers = serverTrustManagerFactory.getTrustManagers();

		final KeyStore serverKeyManagerKeyStore = KeyStore.getInstance(PrimusProvider.getKeyManagerKeyStoreTypeName(), primusProvider);
		serverKeyManagerKeyStore.load(new DerivedKeyStoreSpi.LoadStoreFilterParameter(new String[] {serverKey}));
		final KeyManagerFactory serverKeyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		serverKeyManagerFactory.init(serverKeyManagerKeyStore, keyPassword.clone());
		final KeyManager[] serverKeyManagers = serverKeyManagerFactory.getKeyManagers();

		final SSLContext sslServerContext = SSLContext.getInstance("TLS");
		sslServerContext.init(serverKeyManagers, serverTrustManagers, null);

		server(sslServerContext);
	}

	// run the server part; sslServerContext had been built already
	void server(final SSLContext sslServerContext) throws Exception {

		final ServerSocketFactory serverSocketFactory =
		sslServerContext.getServerSocketFactory();
		// ServerSocketFactory.getDefault(); // non-TLS-version
		final ServerSocket serverSocket = serverSocketFactory.createServerSocket();
		((SSLServerSocket)serverSocket).setNeedClientAuth(true);

		server(serverSocket);
	}

	// run the server part; sslServerContext and serverSocket had been built already
	void server(final ServerSocket serverSocket) throws Exception {

		final SocketAddress endpoint = (bindPort == null ? null : new InetSocketAddress(bindPort.intValue()));
		serverSocket.bind(endpoint);
		pipe.put(serverSocket.getLocalPort());

		System.out.println("server: accepting");
		try (final Socket socket = serverSocket.accept()) {
			server(socket);
		}
	}

	// run the server part; socket is TCP-connected already
	void server(final Socket socket) throws Exception {

		final DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		final DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

		// read an int from input, write another to output
		System.out.println("server: reading");
		final int in = inputStream.readInt();
		final int out = Math.multiplyExact(in, in);
		System.out.println("server: writing");
		outputStream.writeInt(out);
	}

	// run the client part
	void client() throws Exception {

		final KeyStore clientTrustManagerKeyStore = KeyStore.getInstance(PrimusProvider.getTrustManagerKeyStoreTypeName(), primusProvider);
		clientTrustManagerKeyStore.load(new DerivedKeyStoreSpi.LoadStoreFilterParameter(new String[] {caKey}));
		final TrustManagerFactory clientTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		clientTrustManagerFactory.init(clientTrustManagerKeyStore);
		final TrustManager[] clientTrustManagers = clientTrustManagerFactory.getTrustManagers();

		final KeyStore clientKeyManagerKeyStore = KeyStore.getInstance(PrimusProvider.getKeyManagerKeyStoreTypeName(), primusProvider);
		clientKeyManagerKeyStore.load(new DerivedKeyStoreSpi.LoadStoreFilterParameter(new String[] {clientKey}));
		final KeyManagerFactory clientKeyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		clientKeyManagerFactory.init(clientKeyManagerKeyStore, keyPassword.clone());
		final KeyManager[] clientKeyManagers = clientKeyManagerFactory.getKeyManagers();

		final SSLContext sslClientContext = SSLContext.getInstance("TLS");
		sslClientContext.init(clientKeyManagers, clientTrustManagers, null);

		client(sslClientContext);
	}

	// run the client part; sslClientContext had been built already
	void client(final SSLContext sslClientContext) throws Exception {

		final int p = pipe.take();

		final SocketFactory socketFactory =
		sslClientContext.getSocketFactory();
		// SocketFactory.getDefault(); // non-TLS-version

		System.out.println("client: connecting");
		try (final Socket socket = socketFactory.createSocket("localhost", p)) {
			client(socket);
		}
	}

	// run the client part; socket is TCP-connected already
	void client(final Socket socket) throws Exception {

		final DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		final DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

		// write an int to output, read another from input
		final int i = (new SecureRandom()).nextInt(1000);
		System.out.println("client: writing");
		outputStream.writeInt(i);
		final int j = inputStream.readInt();
		System.out.println("client: reading");

		// check result
		assert j == i * i;

	}

}

