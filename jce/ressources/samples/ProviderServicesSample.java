
/*
 * Copyright (C) 2015-2021, Securosys SA
 */

import java.security.Provider;

import com.securosys.primus.jce.PrimusProvider;

public class ProviderServicesSample {

	public static void main(final String... args) throws Exception {

		final PrimusProvider primusProvider = new PrimusProvider();

		for (final Provider.Service s : primusProvider.getServices()) {
			System.out.println(s.getType() + " " + s.getAlgorithm());
			// Provider.Service.toString contains attributes and aliases too, which are not directly available by API
			System.out.println(s);
		}

	}

}

