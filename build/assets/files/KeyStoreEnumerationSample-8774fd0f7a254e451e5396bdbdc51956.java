
/*
 * Copyright (C) 2015-2022, Securosys SA
 */

import java.security.KeyStore;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusKeyTypes;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code using KeyStore aliases enumeration
 * with the Securosys Primus HSM JCE provider.
 */
public class KeyStoreEnumerationSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final KeyStore primusKeyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		primusKeyStore.load(null);

		// JCE API
		final Enumeration<String> keyEnum = primusKeyStore.aliases();
		while (keyEnum.hasMoreElements()) {
			final String alias = keyEnum.nextElement();

			// JCE API: key/alias name
			System.out.print(alias);

			// JCE APIs: key/cert, date
			if (primusKeyStore.isKeyEntry(alias)) {
				System.out.print(" key");
			}
			if (primusKeyStore.isCertificateEntry(alias)) {
				System.out.print(" cert");
			}
			final Date date = primusKeyStore.getCreationDate(alias);
			if (date != null) {
				System.out.print(" of " + date);
			}

			// Primus API: more specific key types, flags/attributes
			final String[][] keyTypes = PrimusKeyTypes.getKeyTypes(alias);
			System.out.print(" " + Arrays.deepToString(keyTypes));
			for (final String[] keyType : keyTypes) {
				final String t = keyType[0]; // PrivateKey/PublicKey/SecretKey/etc
				final Integer accessFlags = PrimusKeyAttributes.getKeyAccessFlags(alias, t);
				if (accessFlags != null) {
					final int flags = accessFlags.intValue();
					System.out.print(" 0x" + Integer.toHexString(flags));
					for (int f = 1; f <= flags; f <<= 1) {
						if ((flags & f) != 0) {
							System.out.print(" " + PrimusKeyAttributes.getAccessFlagName(f));
						}
					}
				}
				final Integer capabilityFlags = PrimusKeyAttributes.getKeyCapabilityFlags(alias, t);
				if (capabilityFlags != null) {
					final int flags = capabilityFlags.intValue();
					System.out.print(" 0x" + Integer.toHexString(flags));
					for (int f = 1; f <= flags; f <<= 1) {
						if ((flags & f) != 0) {
							System.out.print(" " + PrimusKeyAttributes.getCapabilityFlagName(f));
						}
					}
				}
			}

			System.out.println();
		}
	}

}

