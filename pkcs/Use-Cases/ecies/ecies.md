---
sidebar_position: 1
title: PKCS#11 - ECIES HSM implementation
sidebar_label: HSM implementation
---

# ECIES - HSM Implementation

:::info

This features of the Primus PKCS#11 provider is a vendor defined
extension and is not part of the standard PKCS#11 suite.

:::

ECIES is a hybrid encryption scheme which allows to encrypt data to an
ECC public key but using symmetric cryptography for encryption and
authentication. The symmetric keys are derived from the receiver's
public key and an ephemeral private key of the sender with an ECDH
agreement. The ephemeral key pair is used only for a single message:
its private key will be deleted after encryption and the ephemeral
public key is part of the cryptogram such that the receiver can
perform the same ECDH agreement and get the same shared symmetric
keys.

The Primus PKCS#11 provider implements a convenient mechanism that
allows to use ECIES with the common API of C_Encrypt\* and C_Decrypt\*
functions.

An example is shown below:
```cpp
CK_OBJECT_HANDLE hPublic = ...; // Public ECC key of the receiver.
CK_SESSION_HANDLE hSession = ...;

CK_ECIES_PARAMS params;
// Set parameters to default values.
memset((void*)&params, 0, sizeof(params));

params.kdfMethod = CKD_SHA256_KDF; // KDF method used to derive the key from the ECDH result.
params.cryptMethod = CKM_AES_CBC_PAD; // Cipher algorithm.
params.ulCipherKeyLen = 32; // Cipher key length in bytes.
params.macMethod = CKM_SHA256_HMAC; // MAC algorithm.
params.ulMacKeyLen = 32; // Length of the MAC key in bytes.
params.ulMacTagLen = 32; // Length of the MAC tag in bytes.

// Shared infos are optional.
// If they are not defined, the pointers must be NULL and the lengths 0.
params.pSharedInfo1 = "123";
params.ulSharedInfo1Len = 3;
params.pSharedInfo2 = "1234";
params.ulSharedInfo2Len = 4;

// The encryption of the data can optionally be done on the 
// local computer. This is much faster than sending all the data
// to the HSM but requires that the ephemeral encryption key and
// the MAC key are exported onto the local computer.
params.bLocalDataEncryption = false;

CK_MECHANISM mechanism = {
        CKM_ECIES, &params, sizeof(params)
};

// The API for encryption and decryption is the same as for standard mechanisms:

// Initialize the ECIES encryption.
CK_RV rv1 = C_EncryptInit(hSession, &mechanism, hPublic);
// Encrypt data.
CK_RV rv2 = C_Encrypt(hSession, plaintext.data(), plaintext.size(), ciphertext.data(), &ciphertextLen)
```

ECIES can also be [implemented using separate function
calls][custom-ecies-impl] for each of the necessary steps. This gives
finer grained control and allows to use algorithms that are otherwise
not supported for the symmetric cryptography operations.

### ECIES Mechanism Parameters

| Parameter                  | Description                                                                                                                                                                                                         |
|----------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| kdfMethod                  | The key derivation function to be used. Supported values: CKD\_SHA256\_KDF, CKD\_SHA512\_KDF                                                                                                                        |
| cryptMethod                | Cipher algorithm and mode for symmetric encryption.  Supported values: CKD_SHA256_KDF, CKD_SHA512_KDF                                                                                                               |
| ulCipherKeyLen             | Length of the symmetric cipher key in bytes.  Supported values for AES: 16, 24, 32                                                                                                                                  |
| macMethod                  | MAC algorithm to be used for the authentication of the cipher text. Supported values: CKD\_SHA256\_KDF, CKD\_SHA512\_KDF                                                                                            |
| ulMacKeyLen                | Length of the MAC key in bytes.                                                                                                                                                                                     |
| ulMacTagLen                | Length of the MAC tag in bytes.  Recommended value: 32                                                                                                                                                              |
| pSharedInfo1               | Optional. Pointer to the data to be used for "sharedInfo1". This value is optional. If the pointer is set to NULL, then ulSharedInfo1Len must be 0.                                                                 |
| ulSharedInfo1Len           | Length of pSharedInfo1.                                                                                                                                                                                             |
| pSharedInfo2               | Optional. Pointer to the data to be used for "sharedInfo2". This value is optional. If the pointer is set to NULL, then ulSharedInfo2Len must be 0.                                                                 |
| ulSharedInfo2Len           | Length of pSharedInfo2.                                                                                                                                                                                             |
| bLocalDataEncryption       | If set to true, then the symmetric keys are exported to the local client and used to process the payload data locally. This way the payload data does not need to be sent to the HSM.                               |
| bLocalKdf                  | When true: Compute the KDF locally (requires that the ECDH result is extractable). When false: Compute the KDF on the HSM.                                                                                          |
| pKdfParameter              | Mechanism parameters for the KDF function. May be necessary depending on the chosen KDF mechanism.                                                                                                                  |
| ulKdfParameterLen          | Byte length of the supplied KDF parameter data.                                                                                                                                                                     |
| hSenderPublicKey           | Optional. If the handle is non-zero then bypass the generation of an ephemeral key pair and use this public key instead. For encryption also hSenderPrivateKey must be set to the corresponding private key handle. |
| hSenderPrivateKey          | Optional. If the handle is non-zero then bypass the generation of an ephemeral key pair and use this private key instead.                                                                                           |
| bSkipSenderPublicKeyOutput | Optional. Disable the output of the public key in the ciphertext. For decryption requires that the sender public key is provided by hSenderPublicKey.                                                               |

:::warning

When setting the parameters *hSenderPublicKey* or
*hSenderPrivateKey* the generation of an ephemeral key pair is
bypassed. Only set these parameters when you know what you do! When
using non-ephemeral keys, a nonce must be fed into *sharedInfo1*  to
guarantee that the derived symmetric keys are not used multiple times.

:::

[custom-ecies-impl]: /pkcs/Use-Cases/ecies/custom_ecies_implementation.md
