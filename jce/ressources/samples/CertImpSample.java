
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusLogin;
import com.securosys.primus.jce.PrimusProvider;

public class CertImpSample {

	public static void main(final String... args) throws Exception {

		String host = null;
		String port = null;
		String user = null;
		String password = null;
		String file = null;
		String name = null;
		String keypassword = null;

		for (int i = 0; args != null && i < args.length - 1; i++) {
			final String arg = args[i];
			if (arg == null) {
				//
			} else if (arg.equals("-host")) {
				host = args[++i];
			} else if (arg.equals("-port")) {
				port = args[++i];
			} else if (arg.equals("-user")) {
				user = args[++i];
			} else if (arg.equals("-password")) {
				password = args[++i];
			} else if (arg.equals("-file")) {
				file = args[++i];
			} else if (arg.equals("-name")) {
				name = args[++i];
			} else if (arg.equals("-keyname")) {
				name = args[++i];
			} else if (arg.equals("-keypassword")) {
				keypassword = args[++i];
			}
		}

		if (host == null || port == null || user == null || password == null || name == null || file == null) {
			throw new IllegalArgumentException("must specify all of the arguments:"
				+ " -host <HSM host name/address> -port <HSM TCP port> -user <HSM user/partition> -password <HSM user password>"
				+ " -file <cert file> -name <certificate/key object name>"
			);
		}

		final Certificate certificate;
		try (final FileInputStream in = new FileInputStream(file)) {
			final CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
			certificate = certificateFactory.generateCertificate(in);
		}

		final PrimusProvider primusProvider = new PrimusProvider();
		Security.addProvider(primusProvider);
		PrimusConfiguration.setHsmHost(host, port, user);
		PrimusLogin.login(user, password.toCharArray());

		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), primusProvider);
		keyStore.load(null);
		final char[] keypasswordarg = (keypassword == null ? null : keypassword.toCharArray());
		keyStore.setKeyEntry(name, keyStore.getKey(name, keypasswordarg), keypasswordarg, new Certificate[] {certificate});

	}

}

