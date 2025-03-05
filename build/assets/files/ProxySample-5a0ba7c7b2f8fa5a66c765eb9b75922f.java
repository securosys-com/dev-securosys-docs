/*
 * Copyright (C) 2017-2020, Securosys SA
 */

import java.security.Security;

import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusLogin;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample logging in into the Securosys CloudHSM via a Primus Proxy.
 *
 * Please run this sample immediately after receiving your CloudHSM credentials,
 * before the setup password expires (typically 1 week).
 */
public class ProxySample {

	public static void main(final String... args) throws Exception {

		// proxy connection info and credentials as received during the onboarding process
		final String proxyhost = "<CloudsHSM.hostname>";
		final String proxyport = "2300";
		final String proxyuser = "<proxyuser>";
		final char[] proxypassword = "<proxypassword>".toCharArray();

		// hsm credentials as received during the onboarding process
		final String hsmuser = "<USERNAME>";
		final char[] hsmpassword = "<setup-password>".toCharArray();

		// install Primus JCE Provider
		Security.addProvider(new PrimusProvider());

		// configure the proxy host
		PrimusConfiguration.setHsmHost(proxyhost, proxyport, hsmuser);

		// configure proxy credentials
		PrimusConfiguration.setProxyCredentials(proxyuser, proxypassword);

		// log in with HSM credentials
		PrimusLogin.login(hsmuser, hsmpassword);

		// any test call - in this case, retrieving HSM permanent secret
		final char[] usersecret = PrimusLogin.getUserSecretChars();

		// store the permanent secret securely. The setup password expires after a few days.
		System.out.println("Permanent user secret: " + String.valueOf(usersecret));
		System.out.println();
		System.out.println("Use the user secret to log in to the HSM in all the following samples");
	}
}

