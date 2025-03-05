---
sidebar_position: 1
title: Create a Signature Key in an HSM with JCE/JCA
sidebar_label: Sign & Verify
description: Creating a signature key with Java Cryptography Extension (JCE) and Java Cryptography Architecture (JCA) with Securosys Hardware Security Modules.
keywords: [hsm, cloud hsm]
---

# Sign & Verify

This sample code demonstrates the end-to-end process of creating a signature key in an HSM, signing a payload, and verifying the signature using the corresponding public key. The code is well-commented to provide clarity on each step.

- [KeySignatureAndVerificationSample.java](../ressources/samples/KeySignatureAndVerificationSample.java)

## Breakdown

```java
/*
 * Copyright (C) 2019, Securosys SA
 */

import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;

import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.UUID;

/**
 * Sample code showing how to create a key, perform a signature operation with it and verify the signature.
 * The sample goes through the following steps:
 * 1) Logs into the HSM
 * 2) Creates a signature key in the HSM
 * 3) Retrieves the key pair from the HSM
 * 4) Requests the HSM to sign a payload
 * 5) Verifies the signature using the public key
 */
public class KeySignatureAndVerificationSample {

    public static void main(final String... args) throws Exception {

        // 1 -- HSM AND PROVIDER CONFIGURATION

        // HSM configuration
        PrimusHelper.setup(args);

        // 2 -- CREATE A SIGNATURE KEY

        final int keySize = 2048; // keySize (in case of RSA)

        System.out.println("creating RSA key pair of " + keySize + " bits ...");

        // create an RSA key, in this case with randomly set name
        final String signKeyName = UUID.randomUUID().toString();
        PrimusName.generate(
            KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName()), // algorithm specification
            keySize, // keySize
            new Certificate[0], // necessary to be able to get public key from key store
            signKeyName // name to look the key up with from the key store
        );

        System.out.println("Signature key created");

        // 3 -- RETRIEVE THE SIGNATURE KEY

        final KeyStore primusKeyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
        primusKeyStore.load(null);

        // retrieve private key (HSM pointer), and public key for local verification
        final PrivateKey signKey = (PrivateKey)primusKeyStore.getKey(signKeyName, null);
        final PublicKey signKeyPublic = primusKeyStore.getCertificate(signKeyName).getPublicKey();

        // 4 -- SIGN A PAYLOAD

        // random payload - in real life it would be e.g. a cryptocurrency transaction
        final int payloadSize = 250;
        final byte[] payload = new byte[payloadSize];
        Arrays.fill(payload, (byte)'A');

        final String signAlgorithm = "SHA256withRSA";

        // request the payload signature
        final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
        signature.initSign(signKey);
        signature.update(payload);
        final byte[] sig = signature.sign();
        System.out.println("Signature successful");

        // 5 -- VERIFY THE SIGNATURE

        final Signature localVerifier = Signature.getInstance(signAlgorithm);
        localVerifier.initVerify(signKeyPublic);
        localVerifier.update(payload);
        final boolean signSignatureOk = localVerifier.verify(sig);
        System.out.println("Sign signature is " + (signSignatureOk ? "OK" : "NOT OK"));

    }

}
```

Explanation:

1. **HSM and Provider Configuration:**
   - The Primus HSM and provider are configured using `PrimusHelper.setup(args)`.

2. **Create a Signature Key:**
   - An RSA key pair is generated with a specified key size.
   - The key pair is assigned a unique name (`signKeyName`) using `PrimusName.generate`.

3. **Retrieve the Signature Key:**
   - The key pair is retrieved from the HSM using the key name (`signKeyName`).
   - The private key (`signKey`) and the corresponding public key (`signKeyPublic`) are obtained from the key store.

4. **Sign a Payload:**
   - A random payload of a specified size (`payloadSize`) is created.
   - The payload is signed using the private key (`signKey`) with the specified signing algorithm (`SHA256withRSA`).

5. **Verify the Signature:**
   - The signature is verified using the public key (`signKeyPublic`) and the same payload.
   - The result of the verification is printed, indicating whether the signature is valid.

:::note disclaimer

**Private Key Operations within HSM**

This sample code includes operations involving private keys, such as signing payloads, which are exclusively executed within the confines of the Hardware Security Module (HSM). The HSM is a secure and isolated environment designed to safeguard cryptographic operations.

It is crucial to emphasize that verification operations, specifically those utilizing the corresponding public key, are intended to be performed outside the HSM on the client side. The verification process ensures the integrity and authenticity of the signed data, and this separation is fundamental to maintaining the security principles of cryptographic systems.

Users should exercise caution and adhere to security best practices when handling cryptographic keys, ensuring that private key operations are consistently confined to the secure environment of the HSM, while public key verification occurs in trusted client-side environments.

This code serves as a sample and should be adapted and utilized with a thorough understanding of cryptographic principles and secure coding practices.

:::