
/*
 * Copyright (C) 2015-2022, Securosys SA
 */

import java.security.Security;
import java.util.Arrays;

import com.securosys.primus.jce.PrimusBlinding;
import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusDevice;
import com.securosys.primus.jce.PrimusLogin;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Login sample for the Securosys Primus HSM JCE provider.
 */
public class LoginSample {

	public static void main(final String... args) {

		System.out.println("Securosys Primus HSM JCE provider login sample");

		// HSM configuration

		String host; // HSM host name, or address, or cluster HSM list
		String port; // HSM TCP port
		String user; // HSM partition/user
		char[] password; // HSM partition/user login password, setup password, or permanent secret, or blinded version

		String proxyuser = null; // CloudHSM service username, optional
		char[] proxypassword = null; // CloudHSM service password, optional

//		host = "grimsel.securosys.ch"; // externally reachable test/development HSM
//		// host = "nufenen.securosys.ch"; // externally reachable test/development HSM, clustered with grimsel
//		// hostnames grimsel.securosys.ch and nufenen.securosys.ch may resolve/point to the same IPv4 network address
//		port = "2400"; // TCP port for external grimsel access; default HSM's JCE port is 2300; development program system grimsel is at 2400, nufenen at 2401
//		// port = "2401"; // nufenen port

//		// HSM credentials, provided by HSM operator; in case of development program or CloudHSM by Securosys support
//		user = "PRIMUSDEV999"; // user/partition
//		// password not as a String, as not in string pool, as that can't be cleared after use
//		password = ...;

//		// cluster configurations
//		// host = "nufenen.securosys.ch,grimsel.securosys.ch"; // loadbalancing: random, unweighted
//		// host = "nufenen.securosys.ch(.8),grimsel.securosys.ch(.2)"; // loadbalancing: random, weighted
//		// host = "nufenen.securosys.ch;grimsel.securosys.ch"; // no loadbalancing, only failover, prioritized
//		// port = "2401,2400";

		host = "julier.securosys.ch"; // internal test/development HSM, not externally accessible
		port = "2300";
		user = "JENKINS";

		// setup password, will expire
		password = "BmoFA-Q1gJC-zpnbd-CfdFg-GsDBK".toCharArray();

		// permanent secret, as also fetched further below
		/*
		password = "694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825".toCharArray();
		*/

		// for test setup convenience: be configurable
		host = System.getProperty("host", host);
		port = System.getProperty("port", port);
		user = System.getProperty("user", user);
		password = System.getProperty("password", (password == null ? "null" : new String(password))).toCharArray();
		proxyuser = System.getProperty("proxyuser", proxyuser);
		proxypassword = System.getProperty("proxypassword", (proxypassword == null ? "null" : new String(proxypassword))).toCharArray();

		// install (load) Primus JCE provider (PrimusProvider) [JVM wide], and configure it with HSM address/port/user [thread local], and log in to HSM [JVM wide]

		System.out.println("installing the Securosys Primus HSM JCE provider");
		// instantiate Primus JCE provider
		final PrimusProvider primusProvider = new PrimusProvider();
		// install/register it
		Security.addProvider(primusProvider);

		System.out.println("Primus HSM JCE provider information: " + Security.getProvider(PrimusProvider.getProviderName()));
		System.out.println("Primus HSM JCE provider version: " + Security.getProvider(PrimusProvider.getProviderName()).getVersion());

		System.out.println("configuring HSM host and port: " + host + "/" + port + "/" + user);
		// configure HSM to use
		// PrimusConfiguration.setUseThreadLocalHsmConfiguration(false); // have the config on all threads
		PrimusConfiguration.setHsmHost(host, port, user);

		// optionally configure CloudHSM Primus proxy credentials
		if (proxyuser != null) {
			PrimusConfiguration.setProxyCredentials(proxyuser, proxypassword);
			Arrays.fill(proxypassword, '*');
		}

		System.out.println("login state: " + PrimusLogin.isLoggedIn());

		System.out.println("logging in with setup password");
		// login with setup password (that will eventually expire)
		PrimusLogin.login(user, password);
		Arrays.fill(password, '*');

		System.out.println("login state: " + PrimusLogin.isLoggedIn());

		String deviceName = PrimusDevice.getDeviceName(); // e.g. "GRIMSEL"
		String versionInfo = PrimusDevice.getVersionInfo(); // e.g. "RX-2.5.0-D"
		System.out.println("device name: " + deviceName);
		System.out.println("version info: " + versionInfo);

		System.out.println("fetching and blinding permanent secret");
		// fetch and blind permanent secret
		final char[] usersecret = PrimusLogin.getUserSecretChars();
		// blind the usersecret; each blinding will be different, as there's a random nonce involved
		final char[] blindedusersecret = PrimusBlinding.blindChars(usersecret, PrimusBlinding.BlindingAlgorithm.AES);
		Arrays.fill(usersecret, '*'); // clear usersecret memory

		System.out.println("logging out");
		PrimusLogin.logout();

		System.out.println("login state: " + PrimusLogin.isLoggedIn());

		System.out.println("logging in with blinded user secret");
		// login with [blinded] permanent secret
		PrimusLogin.login(user, blindedusersecret);
		Arrays.fill(blindedusersecret, '*');
		try { // guaranteed logout

			System.out.println("login state: " + PrimusLogin.isLoggedIn());

			deviceName = PrimusDevice.getDeviceName(); // e.g. "GRIMSEL"
			versionInfo = PrimusDevice.getVersionInfo(); // e.g. "RX-2.5.0-D"
			System.out.println("device name: " + deviceName);
			System.out.println("version info: " + versionInfo);

		} finally { // guaranteed logout
			System.out.println("logging out");
			PrimusLogin.logout();
		}

		System.out.println("login state: " + PrimusLogin.isLoggedIn());

		System.out.println("sample done");
	}

}

