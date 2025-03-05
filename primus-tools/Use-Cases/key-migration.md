---
sidebar_position: 8
title: Key Migration and Primus Tools
sidebar_label: Key Migration
description: Key Migration with Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Key Migration

Key migration refers to the process of securely transferring cryptographic keys with the wrapping mechanism from one HSM to another (same or different vendor) or a safe key injection into an IOT device. The process is generally used when cloning itself is not either available or viable (only a single or small amount of keys need to be migrated). 

Key migration can be done for both symmetric and asymmetric keys when using the Primus Tools.

:::note
The process of key Migration is not exclusive to JCE API and Primus Tools and can be done with other APIs and tools/utilities.
:::

## Migration - Symmetric Key

The symmetric key migration from origin HSM to target HSM (or other device) process is generally done as such:
- Create an asymmetric key on the target HSM,
- Export the public key from the target HSM,
- Import the public key into the origin HSM,
- Wrap and export the symmetric key on the origin HSM with the public key from the target HSM,
- Import the wrapped symmetric key into the target HSM.
- Verify the key was successfully imported.

:::note
As an example below we showcase a symmetric key migration done between two Securosys HSMs. It is assumed that the symmetric key to be migrated is already created. 
:::

Commands will require an established HSM connection and credentials to be able to execute properly. For further assistance on how to configure your HSM connection and credentials please see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider.md).

Make sure to read through and follow the instructions in the [Installation](/primus-tools/Installation/Prerequisites) section before continuing with the process. For more information about the commands and their parameters see [Object Management](/primus-tools/Tutorials/command-details/object-management).

:::warning
The keys to be exported from an HSM must have the flag `extractable` set.
:::

1. Create an Asymmetric key on the **target** HSM:
```bash
java -jar primus-tools.jar CreateKey <HSM connection and credentials> /
-keyname zmk -type RSA -size 2048
```

2. Export the public part of the asymmetric key `zmk` which was created in the previous step and write it into the file `zmk.der`:
```bash
java -jar primus-tools.jar GetPublicKey <HSM connection and credentials> -keyname zmk > zmk.der
```

3. Import the public key `zmk.der` into the **origin** HSM:
```bash
java -jar primus-tools.jar ImportPublicKey <HSM connection and credentials> /
-keyname zmk -keytype RSA /
-inputfile zmk.der
```

4. Wrap and export the symmetric key prepared for migration (in our case `mig`) from the origin HSM. The key is wrapped with the imported public key `zmk`.
```bash
java -jar primus-tools.jar ExportKeyWrapped <HSM connection and credentials> /
-wrapkeyname zmk  /
-keyname mig -keypassword pass-mig /
-outfile WrappedKey 
```

5. Import the `WrappedKey` file from the previous step into the target HSM:

```bash
java -jar primus-tools.jar ImportKeyWrapped <HSM connection and credentials> 
-wrapkeyname zmk /
-infile WrappedKey /
-keyname mig -keypassword pass-mig
```

6. Verify that the import was successful by listing the key or inspecting the HSM logs:
```bash
java -jar primus-tools.jar ListKeyEntry <HSM connection and credentials> /
-keyname mig -keypassword pass-mig
```

With the import successful the symmetric key migration is concluded.

## Migration - Asymmetric Key

The symmetric key migration from origin HSM to target HSM (or other device) process is generally done as such:

- Create an asymmetric key on the target HSM,
- Export the public key from the target HSM,
- Import the public key into the origin HSM,
- Wrap and export the asymmetric key on the origin HSM with the public key from the target HSM,
- Import the wrapped asymmetric key into the target HSM.
- Verify the key was successfully imported.

Make sure to read through and follow the instructions in the [Installation](/primus-tools/Installation/Prerequisites) section before continuing with the process. For more information about the commands and their parameters see [General Usage](/primus-tools/Tutorials/command-details/object-management).

:::note
As an example below we showcase a migration of an asymmetric key done between two Securosys Primus HSMs. It is assumed that the asymmetric key to be migrated is already created. 
:::

:::warning
The keys to be exported from an HSM must have the flag `extractable` set.
:::

1. Create an asymmetric key on the **target** HSM:
```bash
java -jar primus-tools.jar CreateKey <HSM connection and credentials> /
-keyname zmk2 -type RSA -size 2048
```

2. Export the public part of the asymmetric key `zmk2` which was created in the previous step and write it into the file `zmk2.der`:
```bash
java -jar primus-tools.jar GetPublicKey <HSM connection and credentials> -keyname zmk2 > zmk2.der
```

3. Import the public key `zmk2.der` into the **origin** HSM:
```bash
java -jar primus-tools.jar ImportPublicKey <HSM connection and credentials> /
-keyname zmk2 -keytype RSA /
-inputfile zmk2.der
```

4. Wrap and export the asymmetric key prepared for migration (in our case `RSAmig`) from the origin HSM. The key is wrapped with the imported public key `zmk2`.
```bash
java -jar primus-tools.jar ExportKeyWrapped <HSM connection and credentials> /
-wrapkeyname zmk2  /
-keyname RSAmig -keypassword pass-mig /
-outfile RSAWrappedKey 
```

5. Import the `WrappedKey` file from the previous step into the target HSM:

```bash
java -jar primus-tools.jar ImportKeyWrapped <HSM connection and credentials> 
-wrapkeyname zmk2 /
-infile RSAWrappedKey /
-keyname RSAmig -keypassword pass-mig
```

6. Verify that the import was successful by listing the key or inspecting the HSM logs:
```bash
java -jar primus-tools.jar ListKeyEntry <HSM connection and credentials> /
-keyname RSAmig -keypassword pass-mig
```

With the import successful the migration of the asymmetric key is concluded.

