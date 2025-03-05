
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code illustrating flaws of java.security.Security.getAlgorithms(String serviceName).
 */
public class GetAlgorithmsSample {

	public static void main(final String... args) throws Exception {

		System.out.println(Arrays.toString(Security.getAlgorithms("KeyStore").toArray()));
		System.out.println(Arrays.toString(getAlgorithms("KeyStore").toArray()));

		Security.addProvider(new PrimusProvider());

		System.out.println(Arrays.toString(Security.getAlgorithms("KeyStore").toArray()));
		System.out.println(Arrays.toString(getAlgorithms("KeyStore").toArray()));

	}

	public static Set<String> getAlgorithms(String serviceName) {
		if (serviceName == null || serviceName.length() == 0 || serviceName.endsWith(".")) {
			return Collections.emptySet();
		}
		serviceName = serviceName.toUpperCase(Locale.ENGLISH);
		final int length = serviceName.length() + 1;
		final Set<String> result = new LinkedHashSet<String>();
		for (final Provider provider : Security.getProviders()) {
			for (final Object key : provider.keySet()) {
				final String currentKey = (String)key;
				if (currentKey.toUpperCase(Locale.ENGLISH).startsWith(serviceName)) {
					if (currentKey.indexOf(" ") < 0) {
						result.add(currentKey.substring(length));
					}
				}
			}
		}
		return Collections.unmodifiableSet(result);
	}

}

