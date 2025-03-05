
/*
 * Copyright (C) 2015-2017, Securosys SA
 */

import java.security.Security;

import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusLogin;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Simple sample how to retrieve a user secret
 * from a Securosys Primus HSM.
 */
public class UserSecret {

	public static void main(final String... args) throws Exception {

		String host = null;
		String port = "2300";
		String user = null;
		char[] password = null;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-host")) {
				host = args[++i];
			} else if (args[i].equals("-port")) {
				port = args[++i];
			} else if (args[i].equals("-user")) {
				user = args[++i];
			} else if (args[i].equals("-password")) {
				password = args[++i].toCharArray();
			}
		}

		Security.addProvider(new PrimusProvider());

		PrimusConfiguration.setHsmHost(host, port, user);

		PrimusLogin.login(user, password);
		try {

			final char[] usersecret = PrimusLogin.getUserSecretChars();
			System.out.println("user secret: " + String.valueOf(usersecret));

		} finally {
			PrimusLogin.logout();
		}
	}

}

