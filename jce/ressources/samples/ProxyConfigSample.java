
/*
 * Copyright (C) 2017-2019, Securosys SA
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.KeyStore;
import java.security.Provider;
import java.security.Security;

/**
 * Simple sample configuring the Securosys Primus HSM JCE provider via KeyStore property stream.
 * Sample uses a Primus Proxy to access the CloudHSM.
 * This code sample does not link against primusX.jar during compile time, i.e. no com.securosys.primus.jce imports.
 */
public class ProxyConfigSample {

	public static void main(final String... args) throws Exception {

		// assemble a byte stream to feed into Primus' keystore;
		// adapt for your credentials wher '...' are found
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		(new PrintStream(baos)).println(
			"com.securosys.primus.jce.credentials.host=proxy-host..." + "\n" + // Primus Proxy
			"com.securosys.primus.jce.credentials.port=2300" + "\n" + // Primus Proxy TCP port
			"com.securosys.primus.jce.primusProxyUser=proxytestuser..." + "\n" + // Primus Proxy user
			"com.securosys.primus.jce.primusProxyPassword=password..." + "\n" + // Primus Proxy password
			"com.securosys.primus.jce.credentials.user=HSMTESTUSER..." + "\n" + // Primus HSM user
			"com.securosys.primus.jce.credentials.password=password..." + "\n" + // Primus HSM password
			""
		);
		final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

		// load and install Primus JCE Provider by Java reflection
		Security.addProvider((Provider)Class.forName("com.securosys.primus.jce.PrimusProvider").getDeclaredConstructor().newInstance());

		// instantiate a boot-key-store by type Primus and feed it the configuration
		final KeyStore keystore = KeyStore.getInstance("Primus");
		keystore.load(bais, null);
		keystore.size();

	}

}

