
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.util.Enumeration;

import com.securosys.primus.jce.PrimusKeyStore;

/**
 * Sample code using PrimusKeyStore aliases enumeration
 * with the Securosys Primus HSM JCE provider.
 */
public class KeyStoreEnumerationSample2 {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final PrimusKeyStore primusKeyStore = new PrimusKeyStore();

		final Enumeration<PrimusKeyStore.Entry> keyEnum = primusKeyStore.aliases();
		while (keyEnum.hasMoreElements()) {
			final PrimusKeyStore.Entry entry = keyEnum.nextElement();
			String s = String.valueOf(entry);
			if (entry instanceof PrimusKeyStore.Entry3) {
				s += " " + ((PrimusKeyStore.Entry3)entry).flagsAsString();
				s += " " + ((PrimusKeyStore.Entry3)entry).timesAsString();
			}
			System.out.println(s);
		}
	}

}

