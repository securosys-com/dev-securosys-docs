---
sidebar_position: 2
title: CNG/KSP Provider - Testing & Key Management 
sidebar_label: Testing & Key Management 
description: Key Management Microsoft CNG for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

:::info Note
This tutorial applies to `ksputilcons.exe` **V1.50.2 or newer**.
:::

In addition to the Microsoft Windows certificate utilities (e.g. CertMgr, CertUtil) which are usefull for testing and managing CNG objects, Securosys has developed `ksputilcons.exe`, a command line utility aiming to provide Securosys Primus CNG/KSP provider users with the possibility to: 
- Independently test the Securosys CNG/KSP provider,
- Create and delete specific keys,
- Resolve [Key Accessibility](#change-key-owner-accessibility) issues, such as upgrade incompatibility or transferring accounts between domains (by changing the key owner).

The `ksputilcons.exe` is automatically installed on the device when the Primus CNG/KSP provider is installed. By default the utility is installed within the `%ProgramFiles%` folder: `"%ProgramFiles%\Securosys\PrimusHsmKsp\ksputilcons.exe"`
 
 See chapter [Installation](/mscng/Installation/installation-GUI) for more information about the Primus CNG/KSP provider installation.

The utility provides the following commands[^1]:
|Command|Description|
|---|---|
|[`ksputilcons help`](#help)|Display help text|
|[`ksputilcons hsmstatus [-v -p <provider>]`](#hsm-connection-status-and-details)|HSM connection status|
|[`ksputilcons enumprovs [-v]`](#enumerate-cng-providers)|Enumerate all CNG providers|
|[`ksputilcons enumalgs [-v -p <provider>]`](#enumerate-algorithms)|Enumerate algorithms|
|[`ksputilcons enumalgops [-v -p <provider>]`](#enumerate-algorithm-operations)|Enumerate algorithm operations|
|[`ksputilcons enumkeys [-v -m -a -p <provider>]`](#enumerate-keys)|Enumerate keys|
|[`ksputilcons chkeysowner <name> <oldowner> <newowner> [-v -p <provider> --passwd <password>]`](#change-key-owner-accessibility)|Change the key owner (KeyPrefix)|
|[`ksputilcons createkey [-v -m -p <provider>]`](#create-key)|Create key|
|[`ksputilcons deletekey[-v -m -p <provider>]`](#delete-key)|Delete key|
 

[^1]: Provider instance parameter [-p \<provider>] is required only if multiple instances are installed (CNG V1.40 or newer)



## Help

Display the help text and exit.

```
ksputilcons help
```

## HSM Connection Status and Details

The command ```ksputilcons hsmstatus``` offers a convenient method to verify whether the CNG/KSP configuration is complete, ensure connectivity to the HSMs, and check the HSM firmware version.
Issue a status report about the connectivity and properties of all configured HSMs of the Primus HSM provider.

```
ksputilcons hsmstatus [-v -p <provider>]
```
Where:
|Parameter|Description|
|---|---|
|`-v`|Optional. Increase the verbosity of the command|
|`-p <provider>`|Optional. If more than one HSM provider exists, specify it with the `-p` option and replace `<provider>` with the provider name. If only one Primus HSM provider exists, the command refers to it by default.|


Example:
```
ksputilcons hsmstatus

Getting status. Please wait .....

-----------------------------------
HSM Identifier: DevSystem DEMO-TEST
----------------------------------

+ Configuration
Is complete:                                YES
Host address:                               grimsel.securosys.ch::2320
Device name:                                GRIMSEL
Is sufficient to connect:                   YES

+ HSM info
Device name:                                GRIMSEL
Firmware version:                           RX-3.2.3-T

+ Connection
Succeeded:                                  YES
Protocol version:                           2.5
FIPS compliant login:                       YES
Elliptic curve cryptography:                YES
HASH/HMAC key derivation functions:         YES
SP800-56A key derivation function:          YES
------------------------------------
…
```

## Enumerate CNG Providers

Enumerate all CNG providers and output available information about each provider.

```
ksputilcons enumprovs [-v]
```

Where:
|Parameter|Description|
|---|---|
|`-v`|Optional. Increase the verbosity of the command|

Example:
```
ksputilcons enumprovs

5 providers available:

+ Microsoft Software Key Storage Provider
  Primus HSM provider:          NO
  Version:                      1.0
  Implementation:               software based
  Max Key Name Length:          260
  Comment:                      ---

+ Securosys Primus HSM Key Storage Provider
  Primus HSM provider:          YES
  Version:                      1.50
  Implementation:               hardware based
  Max Key Name Length:          16383
  Comment:                      ---
...
```

## Enumerate Algorithms

Output the available support of the Primus HSM provider for the most common algorithms.

```
ksputilcons enumalgs [-v -p <provider>]
```

Where:
|Parameter|Description|
|---|---|
|`-v`|Optional. Increase the verbosity of the command|
|`-p <provider>`|Optional. If more than one HSM provider exists, specify it with the `-p` option and replace `<provider>` with the provider name. If only one Primus HSM provider exists, the command refers to it by default.|

Example:
```
ksputilcons enumalgs

Algorithms:

- RSA:                          Supported
- RSA_SIGN:                     ---
- DH:                           ---
- DSA:                          ---
- RC2:                          ---
- RC4:                          ---
- AES:                          Supported
- DES:                          ---
- DESX:                         ---
- 3DES:                         ---
- 3DES_112:                     ---
- MD2:                          ---
- MD4:                          ---
- MD5:                          ---
- SHA1:                         ---
- SHA256:                       ---
- SHA384:                       ---
- SHA512:                       ---
- AES-GMAC:                     Supported
- AES-CMAC:                     ---
- ECDSA_P256:                   Supported
- ECDSA_P384:                   Supported
- ECDSA_P521:                   Supported
- ECDH_P256:                    Supported
- ECDH_P384:                    Supported
- ECDH_P521:                    Supported
- RNG:                          ---
- FIPS186DSARNG:                ---
- DUALECRNG:                    ---
- SP800_108_CTR_HMAC:           ---
- SP800_56A_CONCAT:             Supported
- PBKDF2:                       ---
- CAPI_KDF:                     ---
```

## Enumerate Algorithm Operations

Output information about all algorithm operations supported by the Primus HSM provider.

```
ksputilcons enumalgops [-v -p <provider>].
```
Where:
|Parameter|Description|
|---|---|
|`-v`|Optional. Increase the verbosity of the command|
|`-p <provider>`|Optional. If more than one HSM provider exists, specify it with the `-p` option and replace `<provider>` with the provider name. If only one Primus HSM provider exists, the command refers to it by default.|

Example:
```
ksputilcons enumalgops 

- Cipher (symmetric encryption) algorithms:
  + Algorithm ID: AES
     Algorithm class:     symmetric encryption
     Operational classes: symmetric encryption, signature
     Flags:               ---

- Hashing algorithms:
  ---

- Asymmetric encryption algorithms:
  + Algorithm ID: RSA
     Algorithm class:     asymmetric encryption
     Operational classes: asymmetric encryption, signature
     Flags:               ---

- Secret agreement algorithms:
  + Algorithm ID: ECDH_P256
     Algorithm class:     secret agreement (Diffie-Hellman)
     Operational classes: secret agreement (Diffie-Hellman), signature
     Flags:               ---
 ...
```
## Enumerate Keys

Enumerate the keys managed by the Primus HSM provider for the current user, the machine or any user. For each key, the available information is output. By default the keys of the current Windows user are enumerated.

:::tip Enumerate All CNG Keys
The command option `-a` enables enumeration of all CNG keys, regardless of the user scope (`option , administrator rights required), as well as key capabilities and flags. See more about the parameters below.
:::

```
ksputilcons enumkeys [-v -m -a -p <provider>]
```
Where:
|Parameter|Description|
|---|---|
|`-v`|Optional. Increase the verbosity of the command|
|`-m`|Optional. Requires administrative privileges. The keys of the machine are enumerated. Cannot be used in conjunction with parameter `-a`.|
|`-a`|Optional. Requires administrative privileges. All user keys are enumerated. Cannot be used in conjunction with parameter `-m`.|
|`-p <provider>`|Optional. If more than one HSM provider exists, specify it with the `-p` option and replace `<provider>` with the provider name. If only one Primus HSM provider exists, the command refers to it by default.|

Example:
```sh
ksputilcons enumkeys -a -v

Enumerating. Please wait .................................................

------------------------
Owner: S-1-5-11 (Shared)
------------------------

+ Key name:                                 myTestKey.global
  Algorithm, size, type:                    RSA, 4096 bit, PublicKey
  Capabilities:                             Verify
  Access:                                   Modifiable, Copyable, Pkcs11Public
  Creation date/time:                       2025-02-18  14:37:05

+ Key name:                                 myTestKey.global
  Algorithm, size, type:                    RSA, 4096 bit, PrivateKey
  Capabilities:                             Sign
  Access:                                   Sensitive, Modifiable, Copyable, Pkcs11Private, NeverExtractable, AlwaysSensitive
  Creation date/time:                       2025-02-18  14:37:05

--------------------------------------------------------------------
Owner: S-1-5-21-3913189663-3851414020-2755702806-1111 (Current User)
--------------------------------------------------------------------

+ Key name:                                 ExportCertx2.hsmdemo.test-fbaed98e-2ec2-434f-be62-7aa57bbd40b3
Algorithm, size, type:                      RSA, 2048 bit, PublicKey
Capabilities:                               Encrypt, Verify, Wrap
Access:                                     Modifiable, Copyable, Pkcs11Public
Creation date/time:                         2019-02-19 17:16:14

+ Key name:                                 ExportCertx2.hsmdemo.test-fbaed98e-2ec2-434f-be62-7aa57bbd40b3
Algorithm, size, type:                      RSA, 2048 bit, PrivateKey
Capabilities:                               Decrypt, Sign, Unwrap
Access:                                     Extractable, Modifiable, Copyable, Pkcs11Private
Creation date/time:                         2019-02-19 17:16:14
…

-----------------------------------------------------
Owner: S-1-5-21-3913189663-3851414020-2755702806-1112
-----------------------------------------------------

+ Key name:                                 ExportCertx2.hsmdemo.test-fbaed98e-2ec2-434f-be62-7aa57bbd40b3

…
```

## Change Key Owner (Accessibility)

Change the owner of all keys with the given name and owner managed by the Primus HSM provider. An owner is a Windows user or the Windows system (machine). It is specified by an owner identification string that can be retrieved via key enumeration, see more about [Key Accessibility](./Key-Access).


The following command option allows to rename the **keyPrefix** of a specific key to resolve key accessibility issues ([learn more](/mscng/Tutorial/Key-Access)). This may be necessary due to upgrade incompatibility or moving accounts into or out of domains.


:::note
The command requires administrator privileges. <br/> Only keys having the flag **"Modifiable"** set can be renamed.
:::

The procedure is as follows:

1. Open a command shell (cmd) with administrator rights
2. Determine the existing SID (`<oldowner>`) of the key, using key enumeration option (see [Enumerate Keys](#enumerate-keys)) and check that the `Modifiable` flag is set: <br/> `ksputilcons.exe enumkeys -a -v`
3. Determine the SID (`<newowner>`) of the new account, e.g.:
  - of the current user: ``` wmic useraccount where name='%username%' get caption,sid ``` 
  - of all users: ``` wmic useraccount get caption, sid```  
  - of well-known accounts (see chapter [Key Access](/mscng/Tutorial/Key-Access) or [Windows SID documentation](https://docs.microsoft.com/en-us/windows/security/identity-protection/access-control/security-identifiers))
  - of the machine using psgetsid.exe from SysInternal tools:
    - of local machine: ``` psgetsid ```
    - of machine in Active Directory ``` psgetsid %computername%$```
- Rename the key prefix with the following command option (see **help** option for details):

```
 ksputilcons chkeysowner <keyname> <oldowner> <newowner> [-v -p <provider> --passwd <password>] 
 ```

Example:

```
ksputilcons chkeysowner CNGTestKey S-1-5-21-3913189663-3851414020-2755702806-1111
 S-1-5-21-3913189663-3851414020-2755702806-1104
 ```

Where:
|parameter|description|
|---|---|
|`<keyname>`|Specify the name of the key(s).|
|`<oldowner>`|Identification string of the current owner of the key(s).|
|`<newowner>`|Identification string of the new owner of the key(s).|
|`-v`|Optional. Increase the verbosity of the command|
|`-p <provider>`|Optional. If more than one HSM provider exists, specify it with the `-p` option and replace `<provider>` with the provider name. If only one Primus HSM provider exists, as the command refers to it by default.|
|`--passwd <password>`| Optional. Replace `<password>` with a key password, if required|

## Create Key

:::note
The `createkey` command is only included in the `ksputilcons.exe` utilities with Primus CNG/KSP provider version 1.50.2 or newer.
:::

Create a key for the Primus HSM provider. If no key creation parameter is specified then the key will be created with the default values. 

```
ksputilcons createkey [--name <keyname> --algorithm <keyAlgorithm> --size <keySize> --exportable --sign --decrypt --agreement -v -m -p <provider>]
```

Where:
|Parameter|Description|
|---|---|
|`--name`|Optional. Specify the name of the generated key (For global keys add the suffix ".global")|
|`--algorithm`|Optional. Specify the key algorithm as either: `AES`, `ECDSA`, `RSA`|
|`--size`|Optional. Specify the size of the key. Supported: AES (128, 192, 256), ECDSA (256, 384, 521, (NIST curves)), RSA (2048, 3072, 4096)|
|`--exportable`|Optional. Specify if the generated key can be exported, if omitted the key will not be exportable.|
|`--sign`|Optional. Specify if the generated key has sign usage. If no specific usage is defined, all key usages are assigned by default.|
|`--decrypt`|Optional. Specify if the generated key has decrypt usage. If no specific usage is defined, all key usages are assigned by default.|
|`--agreement`|Optional. Specify if the generated key has agreement usage. If no specific usage is defined, all key usages are assigned by default.|
|`-v`|Optional. Increase the verbosity of the command|
|`-m`|Optional. Specify if the key to be created is a machine key. Requires administrator privileges.|
|`-p <provider>`|Optional. If more than one HSM provider exists, specify it with the `-p` option and replace `<provider>` with the provider name. If only one Primus HSM provider exists, the command refers to it by default.|

Default Values for command `ksputilcons createkey`:
`SecurosysPrimusHSM_CNGTestKey, RSA, 4096, not exportable, sign, decrypt, agreement`

Example to create a key shared by all authenticated device users (globally shared):
```
ksputilcons createkey --name myTestKey.global --algorithm RSA --size 4096 --sign
```
```
ksputilcons enumkeys -v

Enumerating. Please wait .............

------------------------
Owner: S-1-5-11 (Shared)
------------------------

+ Key name:                     myTestKey.global
  Algorithm, size, type:        RSA, 4096 bit, PublicKey
  Capabilities:                 Verify
  Access:                       Modifiable, Copyable, Pkcs11Public
  Creation date/time:           2025-02-18  14:37:05

+ Key name:                     myTestKey.global
  Algorithm, size, type:        RSA, 4096 bit, PrivateKey
  Capabilities:                 Sign
  Access:                       Sensitive, Modifiable, Copyable, Pkcs11Private, NeverExtractable, AlwaysSensitive
  Creation date/time:           2025-02-18  14:37:05
...
```

## Delete Key

:::note
The `deletekey` command is only included in the `ksputilcons.exe` utilities with Primus CNG/KSP provider version 1.50.2 or newer.
:::

Delete a key of the Primus HSM provider. 

```
ksputilcons deletekey [--name -v -m -p <provider>]
```

Where:
|Parameter|Description|
|---|---|
|`--name`|Specify the name of the key to be deleted. If parameter is omitted the default value will be: `SecurosysPrimusHSM_CNGTestKey`|
|`-v`|Optional. Increase the verbosity of the command|
|`-m`|Optional. Specify if the key to be deleted is a machine key. Requires administrator privileges.|
|`-p <provider>`|Optional. If more than one HSM provider exists, specify it with the `-p` option and replace `<provider>` with the provider name. If only one Primus HSM provider exists, the command refers to it by default.|



