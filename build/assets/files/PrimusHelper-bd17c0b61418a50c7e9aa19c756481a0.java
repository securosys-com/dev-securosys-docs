
/*
 * Copyright (C) 2015-2022, Securosys SA
 */

import java.security.Security;
import java.util.Arrays;

import com.securosys.primus.jce.PrimusBlinding;
import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusLogin;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Base functionality for Securosys Primus test code:
 * login, configuration.
 */
public class PrimusHelper {

	// don't instantiate; no use to do so
	private PrimusHelper() {
		//
	}

	/** HSM host name, or address, or cluster HSM list */
	public static String host = null;

	/** HSM TCP port */
	public static String port = null;

	/** HSM partition/user */
	public static String user = null;

	/** HSM partition/user login password, setup password, or permanent secret, or blinded version */
	public static char[] password = null;

	/** CloudHSM service username, optional */
	public static String proxyuser = null;

	/** CloudHSM service password, optional */
	public static char[] proxypassword = null;

	/** installed PrimusProvider instance */
	public static PrimusProvider primusProvider = null;

	/** keep passwords, for later use */
	public static boolean keepPasswords = false;

	/** setup completed */
	public static boolean setupDone = false;

	/** keep passwords, for later use */
	public static void keepPasswords(final boolean keep) {
		keepPasswords = keep;
	}

	/** setup configuration and log in */
	public static void setup(final String... args) {
		if (setupDone) {
			// just configure HSM to use, typically for each thread
			PrimusConfiguration.setHsmHost(host, port, user);
		} else {
			initialSetup(args);
		}
	}

	/** setup configuration and log in, initial approach */
	public static void initialSetup(@SuppressWarnings("unused") final String... args) {

		// HSM configuration

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
		// [here we uncomment the line, to allow the samples to still run when the setup password has been expired]
		// /*
		password = "694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825".toCharArray();
		// */

		// for test setup convenience: be configurable
		host = System.getProperty("host", host);
		port = System.getProperty("port", port);
		user = System.getProperty("user", user);
		password = System.getProperty("password", (password == null ? "null" : new String(password))).toCharArray();
		proxyuser = System.getProperty("proxyuser", proxyuser);
		proxypassword = System.getProperty("proxypassword", (proxypassword == null ? "null" : new String(proxypassword))).toCharArray();

		// install (load) Primus JCE provider (PrimusProvider) [JVM wide], and configure it with HSM address/port/user [thread local], and log in to HSM [JVM wide]

		// instantiate Primus JCE provider
		primusProvider = new PrimusProvider();
		// install/register it
		Security.addProvider(primusProvider);

		// configure HSM to use
		// PrimusConfiguration.setUseThreadLocalHsmConfiguration(false); // have the config on all threads
		PrimusConfiguration.setHsmHost(host, port, user);

		// optionally configure CloudHSM Primus proxy credentials
		if (proxyuser != null) {
			PrimusConfiguration.setProxyCredentials(proxyuser, proxypassword);
			if (!keepPasswords) {
				Arrays.fill(proxypassword, '*');
			}
		}

		// login with setup password (that will eventually expire)
		PrimusLogin.login(user, password);
		if (!keepPasswords) {
			Arrays.fill(password, '*');
		}

		// fetch and blind permanent secret
		final char[] usersecret = PrimusLogin.getUserSecretChars();
		// blind the usersecret; each blinding will be different, as there's a random nonce involved
		final char[] blindedusersecret = PrimusBlinding.blindChars(usersecret, PrimusBlinding.BlindingAlgorithm.AES);
		Arrays.fill(usersecret, '*'); // clear usersecret memory
		PrimusLogin.logout();

		// login with [blinded] permanent secret
		PrimusLogin.login(user, blindedusersecret);
		Arrays.fill(blindedusersecret, '*');

		setupDone = true;

	}

}

