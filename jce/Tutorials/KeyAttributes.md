---
sidebar_position: 2
title: HSM Key Attributes with JCE/JCA
sidebar_label: Key Attributes
description: Access Key Attributes with Java Cryptography Extension (JCE) and Java Cryptography Architecture (JCA) with Securosys Hardware Security Modules.
keywords: [hsm, cloud hsm]
---

# Key Attributes

This code provides a comprehensive demonstration of manipulating key attributes, specifically focusing on capability and access flags within the Securosys Primus HSM JCE provider. It helps illustrate how key capabilities can be controlled and how attempts to perform operations outside the specified capabilities are handled.

- [KeyAttributesSample.java](../ressources/samples/KeyAttributesSample.java)

```java
/*
 * Copyright (C) 2016-2019, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.UUID;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusProviderException;

/**
 * Sample code manipulating key attributes
 * with the Securosys Primus HSM JCE provider.
 *
 * The sample goes through the following steps:
 * 1) HSM Login
 * 2) Create a key
 * 3) Display default attributes
 * 4) Sign and verify with the key
 * 5) Disable signature and verification with the key
 * 6) Attempt at signature and verification
 * 7) Re-enable the capabilities
 */
public class KeyAttributesSample {

    public static void main(final String... args) throws Exception {

        // 1 -- HSM AND PROVIDER CONFIGURATION

        // HSM configuration
        PrimusHelper.setup(args);

        // 2 -- CREATE A KEY WITH DEFAULT ATTRIBUTES

        System.out.println("creating RSA key pair of 2048 bits ...");
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName());
        keyPairGenerator.initialize(2048);
        final KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 3 -- DISPLAY THE DEFAULT ATTRIBUTES

        // Display default capability and access flags for the private and public keys
        displayKeyAttributes("default private capability flags:", PrimusKeyAttributes.getKeyCapabilityFlags(keyPair.getPrivate()));
        displayKeyAttributes("default public capability flags:", PrimusKeyAttributes.getKeyCapabilityFlags(keyPair.getPublic()));
        displayKeyAttributes("default private access flags:", PrimusKeyAttributes.getKeyAccessFlags(keyPair.getPrivate()));
        displayKeyAttributes("default public access flags:", PrimusKeyAttributes.getKeyAccessFlags(keyPair.getPublic()));

        // 4 -- USE THE KEY TO SIGN A PAYLOAD AND VERIFY THE SIGNATURE

        System.out.print("signing ...");
        final Signature signature = Signature.getInstance("SHA256withRSA", PrimusProvider.getProviderName());
        signature.initSign(keyPair.getPrivate());
        final byte[] msg = UUID.randomUUID().toString().getBytes();
        signature.update(msg);
        byte[] sig = signature.sign();
        System.out.println(" OK");

        System.out.print("verifying ...");
        signature.initVerify(keyPair.getPublic());
        signature.update(msg);
        System.out.println(signature.verify(sig));

        // 5 -- MODIFY CAPABILITY FLAGS TO DISABLE SIGNATURE AND VERIFICATION WITH THE KEY

        // Disable verification capability for the public key
        System.out.println("disable verify ...");
        PrimusKeyAttributes.setKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_VERIFY, false);

        // Attempt signature with the disabled capability
        signature.initSign(keyPair.getPrivate());
        signature.update(msg);
        sig = signature.sign();

        // 6 -- ATTEMPT AT SIGNATURE AND VERIFICATION

        // Attempt verification with the disabled capability, expect an exception
        try {
            signature.initVerify(keyPair.getPublic());
            signature.update(msg);
            signature.verify(sig);
            System.out.println("UNEXPECTED");
            throw new AssertionError();
        } catch (PrimusProviderException e) {
            System.out.println("error verifying as expected: " + e);
        }

        // Disable signing capability for the private key
        System.out.println("disable sign ...");
        PrimusKeyAttributes.setKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_SIGN, false);

        // Attempt signature with the disabled capability, expect an exception
        try {
            signature.initSign(keyPair.getPrivate());
            signature.update(msg);
            sig = signature.sign();
            System.out.println("UNEXPECTED");
            throw new AssertionError();
        } catch (PrimusProviderException e) {
            System.out.println("error signing as expected: " + e);
        }

        // 7 - RE-ENABLE THE CAPABILITIES

        // Re-enable signing and verification capabilities for both private and public keys
        System.out.println("reenable signing and verification capability...");
        PrimusKeyAttributes.setKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_VERIFY, true);
        PrimusKeyAttributes.setKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_SIGN, true);

        // Perform signing and verification again
        signature.initSign(keyPair.getPrivate());
        signature.update(msg);
        sig = signature.sign();

        signature.initVerify(keyPair.getPublic());
        signature.update(msg);
        signature.verify(sig);

        System.out.println("signature and verification successful");
    }

    // Helper method to display key attributes
    private static void displayKeyAttributes(String label, int flags) {
        System.out.print(label);
        for (int f = 1; f <= flags; f <<= 1) {
            if ((flags & f) != 0) {
                System.out.print(" " + PrimusKeyAttributes.getCapabilityFlagName(f));
            }
        }
        System.out.println();
    }
}
```

Explanation:

1. **HSM and Provider Configuration:**
   - The Primus HSM and provider are configured using `PrimusHelper.setup(args)`.

2. **Create a Key with Default Attributes:**
   - An RSA key pair is generated with a key size of 2048 bits.

3. **Display Default Attributes:**
   - The default capability and access flags for both private and public keys are displayed.

4. **Use the Key to Sign a Payload and Verify the Signature:**
   - A random payload is signed using the private key, and the signature is verified using the public key.

5. **Modify Capability Flags to Disable Signature and Verification:**
   - Capability flags are modified to disable signature and verification for the key.

6. **Attempt at Signature and Verification:**
   - An attempt is made to sign and verify using the disabled capabilities, resulting in expected exceptions.

7. **Re-enable the Capabilities:**
   - The capabilities for signing and verification are re-enabled for both private and public keys.
   - Signing and verification operations are performed again, demonstrating the re-enabled capabilities.

