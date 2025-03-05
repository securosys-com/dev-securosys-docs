
/*
 * Copyright (C) 2015-2020, Securosys SA
 */

import java.security.Security;

import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusLogin;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Simple sample how to retrieve a permanent user secret from Securosys Primus HSM.
 */
public class UserSecretSample {

	public static void main(final String... args) throws Exception {

		final String host = "grimsel.securosys.ch"; // externally reachable test/development HSM
		final String port = "2400"; // Default HSM's JCE port is 2300. Dev program partition is accessible at 2400

		// credentials received from the HSM operator (our support in case of dev program)
		final String user = "TESTUSER";
		final char[] password = "FDgKb-zxsPR-ud6EN-GK6Jz-adrvB".toCharArray();

		// install Primus HSM JCE provider
		Security.addProvider(new PrimusProvider());

		// configure
		PrimusConfiguration.setHsmHost(host, port, user);

		// login to the HSM
		PrimusLogin.login(user, password);
		try {

			// extract permanent user secret
			final char[] usersecret = PrimusLogin.getUserSecretChars();
			System.out.println("Permanent user secret: " + String.valueOf(usersecret));
			System.out.println();
			System.out.println("Use the user secret to log in to the HSM in all the following samples");

		} finally {
			PrimusLogin.logout();
		}
	}

}

