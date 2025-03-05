
/*
 * Copyright (C) 2016-2020 Securosys SA
 */

import com.securosys.primus.jce.PrimusDevice;

/**
 * Fetch HSM logs sample.
 */
public class LogFetchSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// fetch HSM logs

		System.out.println();
		System.out.println("HSM [crypto] log:");
		System.out.println(PrimusDevice.getHsmLog());

		// [syslog fetching no longer allowed]
		//
		// System.out.println();
		// System.out.println("HSM syslog:");
		// System.out.println(PrimusDevice.getHsmSyslog());

	}

}

