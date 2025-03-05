
/*
 * Copyright (C) 2017-2023, Securosys SA
 */

import java.lang.reflect.InvocationTargetException;
import java.security.Provider;
import java.security.Security;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Sample code using Securosys Primus HSM and its JCE provider
 * for establishing TLS (SSL) secure sockets, client and server.
 *
 * The approach is to split things,
 * into setup (key generation by Sun/keytool, for interop, and avoiding keytool's provider use problems),
 * server, and client (separating into different JVMs, avoiding TLS singleton state interferences).
 *
 * <p>
 * sample use (setup, TLS server with HSM (in background), TLS client locally):
 * <pre>
 * rm ca.keystore client.keystore server.keystore
 * java -cp ${classpath} TlsInteropBcSample setup
 * java -cp ${classpath} TlsInteropBcSample hsm server &
 * java -cp ${classpath} TlsInteropBcSample client
 * </pre>
 * with e.g.
 * <pre>
 * classpath="bin:primusX.jar:bcprov-jdk18on-xxx.jar:bctls-jdk18on-xxx.jar:bcutil-jdk18on-xxx.jar:bcpkix-jdk18on-xxx.jar"
 * </pre>
 */
public class TlsInteropBcSample extends TlsInteropSample {

	static {
		// log more
		final Level level = Level.ALL;
		final Logger logger = LogManager.getLogManager().getLogger("");
		logger.setLevel(level);
		for (final Handler handler : logger.getHandlers()) {
			handler.setLevel(level);
		}
	}

	static Provider bouncyCastleProvider = null;
	static Provider bouncyCastleJsseProvider = null;

	static {

		// make this sample not compile-time dependant on BouncyCastle

		// typically following bc jars are needed in the classpath:
		// (xxx being the version number, it's probably a good idea to use matching versions):
		// bcprov-debug-jdk18on-xxx.jar (debug version contains the -g line number debug info),
		// bctls-debug-jdk18on-xxx.jar (self-built, for the debug info) or bctls-jdk18on-xxx.jar,
		// bcpkix-debug-jdk18on-xxx.jar (self-built, for the debug info) or bcpkix-jdk18on-xxx.jar,
		// bcutil-jdk18on-xxx.jar (referenced by bctls),

		// (not used (?): bcmail-jdk18on-xxx.jar, bcpg-jdk18on-xxx.jar)

		// repackaging the bctls jar (the deliveries are otherwise stripped of the line numbers which is pretty ugly to analyze):
		// javac -cp ../lib3/bcprov-debug-jdk18on-172.jar:../lib3/bcutil-jdk18on-172.jar -d bin -g `find src/src -name '*.java' -print | grep -v '/test/'`
		// jar cf bctls-debug-jdk18on-172.jar -C bin org

		try {
			bouncyCastleProvider = (Provider)Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider").getConstructor().newInstance();
			bouncyCastleJsseProvider = (Provider)Class.forName("org.bouncycastle.jsse.provider.BouncyCastleJsseProvider").getConstructor().newInstance();
		} catch (final ClassNotFoundException | ClassCastException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
			bouncyCastleProvider = null;
			bouncyCastleJsseProvider = null;
		}
	}

	public static void main(final String... args) throws Exception {
		(new TlsInteropBcSample()).imain(args);
	}

	@Override
	void imain(final String... args) throws Exception {
		if (bouncyCastleProvider != null) {
			Security.insertProviderAt(bouncyCastleProvider, 1);
			if (bouncyCastleJsseProvider != null) {
				Security.insertProviderAt(bouncyCastleJsseProvider, 2);
			}
		}
		super.imain(args);
	}

	@Override
	void config() throws Exception {
		super.config();

		// KeyManagerFactory type to use; not Sun specific SunX509
		// SunX509 KeyManagerFactorySpi and BC are not compatible,
		// would result in 'org.bouncycastle.tls.TlsFatalAlert: handshake_failure(40); No selectable cipher suite'
		// [uses Security.setProperty instead of System.setProperty!]
		Security.setProperty("ssl.KeyManagerFactory.algorithm", "X509");

	}

}

