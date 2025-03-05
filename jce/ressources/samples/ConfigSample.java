
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
import java.util.UUID;

/**
 * Simple sample configuring the Securosys Primus HSM JCE provider via KeyStore property file.
 * This code sample does not directly link against primusX.jar, i.e. no com.securosys.primus.jce imports.
 */
public class ConfigSample {

	public static void main(final String... args) throws Exception {

		final String configurationFileName = "primus-configuration-" + UUID.randomUUID();

		// assemble configuration file
		try (final PrintStream ps = new PrintStream(new FileOutputStream(configurationFileName))) {
			ps.println("com.securosys.primus.jce.credentials.host=grimsel.securosys.ch");
			ps.println("com.securosys.primus.jce.credentials.port=2300");
			ps.println("com.securosys.primus.jce.credentials.user=TESTUSER");
		}

		// Primus JCE provider class name, provider name, keystore type name
		final String providerClassName = "com.securosys.primus.jce.PrimusProvider";
		final String providerName = "SecurosysPrimusXSeries";
		final String keyStoreTypeName = "Primus";

		// install provider; avoid directly linking against primusX.jar
		Security.addProvider((Provider)Class.forName(providerClassName).getDeclaredConstructor().newInstance());

		// login by keystore instantiation
		final KeyStore loaderKeystore = KeyStore.getInstance(keyStoreTypeName, providerName);
		try (final InputStream is = new FileInputStream(configurationFileName)) {
			loaderKeystore.load(is, "OZ0I0-ZS0QE-U14IS-WAG1V-TYTOP".toCharArray());
		}

		// sample calls
		loaderKeystore.size();

	}

}

