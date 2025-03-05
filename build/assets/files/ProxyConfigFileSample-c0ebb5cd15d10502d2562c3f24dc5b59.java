
/*
 * Copyright (C) 2017,2018, Securosys SA
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.KeyStore;
import java.security.Provider;
import java.security.Security;

/**
 * Simple sample configuring the Securosys Primus HSM JCE provider via KeyStore property file.
 * Sample uses a Primus Proxy to access the Cloud HSM.
 * This code sample does not link against primusX.jar during compile time, i.e. no com.securosys.primus.jce imports.
 */
public class ProxyConfigFileSample {

	public static void main(final String... args) throws Exception {

		final String configurationFileName = ".primus.config";

		try (final PrintStream ps = new PrintStream(new FileOutputStream(configurationFileName))) {
			ps.println(
				"com.securosys.primus.jce.credentials.host=oberalp.securosys.ch" + "\n" + // Primus Proxy
				"com.securosys.primus.jce.credentials.port=2300" + "\n" + // Primus Proxy TCP port
				"com.securosys.primus.jce.primusProxyUser=testuser" + "\n" + // Primus Proxy user
				"com.securosys.primus.jce.primusProxyPassword=..." + "\n" + // Primus Proxy password
				"com.securosys.primus.jce.credentials.user=TESTUSER" + "\n" + // Primus HSM user
				"com.securosys.primus.jce.credentials.password=..." + "\n" + // Primus HSM password
				""
			);
		}

		Security.addProvider((Provider)Class.forName("com.securosys.primus.jce.PrimusProvider").getDeclaredConstructor().newInstance());
		final KeyStore keystore = KeyStore.getInstance("Primus");
		try (final InputStream is = new FileInputStream(configurationFileName)) {
			keystore.load(is, null);
		}
		keystore.size();

	}

}

