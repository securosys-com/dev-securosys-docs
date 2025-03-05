---
sidebar_position: 3
title: HSM Key Enumeration with JCE/JCA
sidebar_label: Key Enumeration
description: Key Enumeration with Java Cryptography Extension (JCE) and Java Cryptography Architecture (JCA) with Securosys Hardware Security Modules.
keywords: [hsm, cloud hsm]
---

# Key Enumeration

This code provides a sample of how to enumerate and inspect keys and certificates stored in a KeyStore using the Securosys Primus HSM JCE provider.

- [KeyEnumartionSample.java](../ressources/samples/KeyStoreEnumerationSample.java)

```java
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

        // Obtain an instance of PrimusKeyStore from the PrimusProvider
        final KeyStore primusKeyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
        primusKeyStore.load(null);

        // Iterate through the aliases (keys and certificates) in the PrimusKeyStore
        final Enumeration<String> keyEnum = primusKeyStore.aliases();
        while (keyEnum.hasMoreElements()) {
            final String alias = keyEnum.nextElement();

            // Display the alias name
            System.out.print(alias);

            // Determine if the alias represents a key or a certificate
            if (primusKeyStore.isKeyEntry(alias)) {
                System.out.print(" key");
            }
            if (primusKeyStore.isCertificateEntry(alias)) {
                System.out.print(" cert");
            }

            // Display the creation date of the key or certificate
            final Date date = primusKeyStore.getCreationDate(alias);
            if (date != null) {
                System.out.print(" of " + date);
            }

            // Obtain more specific key types and display their flags/attributes
            final String[][] keyTypes = PrimusKeyTypes.getKeyTypes(alias);
            System.out.print(" " + Arrays.deepToString(keyTypes));
            for (final String[] keyType : keyTypes) {
                final String t = keyType[0]; // PrivateKey/PublicKey/SecretKey/etc

                // Display access flags for the key type
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

                // Display capability flags for the key type
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
```

Explanation:

1. **HSM and Provider Configuration:**
   - The Primus HSM and provider are configured using `PrimusHelper.setup(args)`.

2. **KeyStore Initialization:**
   - An instance of `PrimusKeyStore` is obtained from the `PrimusProvider` and loaded.

3. **KeyStore Aliases Enumeration:**
   - The code iterates through the aliases (keys and certificates) in the `PrimusKeyStore`.

4. **Alias Information Display:**
   - Information such as the alias name, whether it represents a key or certificate, and creation date are displayed.

5. **Key Types and Attributes Display:**
   - More specific key types (e.g., PrivateKey, PublicKey) are obtained, and their access and capability flags are displayed.

