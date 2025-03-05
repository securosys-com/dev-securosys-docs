
/*
 * Copyright (C) 2016-2022, Securosys SA
 */

import java.security.Security;

import com.securosys.primus.jce.PrimusAlreadyLoggedOutException;
import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusLogin;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample for PrimusLogin.isLoggedIn and PrimusLogin.getConnectionState methods use.
 */
public class IsLoggedInSample {

	public static void main(final String... args) throws Exception {

		final String host = "julier.securosys.ch"; // internal test/development HSM, not externally accessible
		final String port = "2300";
		final String user = "JENKINS";

		// setup password, will expire
		char[] password = "BmoFA-Q1gJC-zpnbd-CfdFg-GsDBK".toCharArray();
		// permanent secret
		// [here we uncomment the line, to allow the samples to still run when the setup password has been expired]
		// /*
		password = "694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825".toCharArray();
		// */

		// load PrimusProvider
		Security.addProvider(new PrimusProvider());

		// -- unconfigured state --

		assert PrimusLogin.getConnectionState().equals("UNCONFIGURED");

		// not logged in, isLoggedIn doesn't complain about being unconfigured
		assert !PrimusLogin.isLoggedIn();
		assert !PrimusLogin.isLoggedIn(host, port, user);

		// configure
		PrimusConfiguration.setHsmHost(host, port, user);

		// -- configured state --

		assert PrimusLogin.getConnectionState().equals("UNCONNECTED");

		// still not logged in
		assert !PrimusLogin.isLoggedIn();
		assert !PrimusLogin.isLoggedIn(host, port, user);

		// log in
		PrimusLogin.login(user, password);

		assert PrimusLogin.getConnectionState().equals("CONNECTED");

		// logged in, but not with the alternative configuration (host/port only)
		assert PrimusLogin.isLoggedIn();
		assert PrimusLogin.isLoggedIn(host, port, user);

		// log in again (just increases login reference count)
		PrimusLogin.login(user, password);
		assert PrimusLogin.isLoggedIn();
		assert PrimusLogin.isLoggedIn(host, port, user);

		// log out once (just decreases login reference count)
		PrimusLogin.logout();
		assert PrimusLogin.isLoggedIn();
		assert PrimusLogin.isLoggedIn(host, port, user);

		// log out
		PrimusLogin.logout();

		assert PrimusLogin.getConnectionState().equals("UNCONNECTED");

		// no longer logged in
		assert !PrimusLogin.isLoggedIn();
		assert !PrimusLogin.isLoggedIn(host, port, user);

		// log out once more, once too many
		try {
			PrimusLogin.logout();
			assert false;
		} catch (PrimusAlreadyLoggedOutException e) {
			// expected
		}

	}

}

