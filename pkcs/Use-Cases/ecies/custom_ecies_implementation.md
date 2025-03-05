---
sidebar_position: 2
title: PKCS#11 - ECIES Custom Implementation
sidebar_label: Custom Implementation
---

# Custom ECIES Implementation

The following code example shows how to derive the symmetric
encryption key and MAC key according to ECIES. Only standard PKCS#11
mechanisms are used except CKM_KEY_SPLIT. The latter is a proprietary
mechanism used to split the KDF result into the encryption key and the
MAC key. Encryption and authentication of the payload data can be done
the normal way and is therefore not included in this example.

```cpp
/**
 * Example of how the Primus PKCS11 provider can be used to implement
 * the key agreement and key derivation steps of the elliptic-curve
 * integrated encryption scheme (ECIES).  *
 * This example uses standard PKCS#11 calls and mechanisms, except
 * CKM_KEY_SPLIT for splitting the output of the KDF into encryption
 * and MAC keys.  *
 * In the `main()` function initializes the connection to the HSM
   partition
 * and then calls the actual ECIES example.  */

#include <pkcs11.h>
#include <string.h>
#include <stdio.h>
#include <stdint.h>
#include <vector>
#include <iostream>
#include <iomanip>


/**
 * Copy a substring from a GENERIC_SECRET and store it as a new key
   object.
 * The operation on the key material is done on the HSM.
 * This function relies on a proprietary mechanism for C_DeriveKey.  *
 * Arguments:
 * byteOffset: The start index of the substring to be extracted from
   the input key.
 * byteLength: The byte length of the derived key.
 * extractable: Set the CKA_EXTRACTABLE flag of the generated key. If
   the input key is not extractable this MUST be set to false.
 * keyType: The value of the CKA_KEY_TYPE attribute of the derived
   key. For an AES key this should be set to CKK_AES, for example.
 * inputKey: The base key from which a substring is copied.
 * newKey: Handle of the resulting key object.  *
 * Requirements:
 * The base key must be long enough to be splitted in the required
   size at the given offset,
 * otherwise CKR_GENERAL_ERROR is returned from the HSM.  */ static
 CK_RV HSM_splitKey( CK_SESSION_HANDLE sessionHandle, uint32_t
 byteOffset, CK_ULONG byteLength, CK_BBOOL extractable, CK_KEY_TYPE
 keyType, CK_OBJECT_HANDLE inputKey, CK_OBJECT_HANDLE& newKey ) {
 CK_RV rv = CKR_OK;

        // Setup data to be used in mechanism parameters.
        CK_ULONG derivedSecretLen = byteLength;

        CK_BBOOL bFalse = CK_FALSE;
        CK_BBOOL bTrue = CK_TRUE;
        CK_OBJECT_CLASS secretKeyClass = CKO_SECRET_KEY;

        CK_BBOOL bExtractable = extractable;
        CK_BBOOL bSensitive = !bExtractable;

        // Set mechanism parameters.
        CK_MECHANISM mechanism;
        memset(&mechanism, 0, sizeof(CK_MECHANISM));
        mechanism.mechanism = CKM_KEY_SPLIT;

    // Make sure the byte offset has little endian encoding.  uint8_t
    byteOffsetBytes[4]; for(int i = 0; i < 4; ++i) {
    byteOffsetBytes[i] = (byteOffset>>8*i) & 0xff; }
    mechanism.pParameter = (void*)byteOffsetBytes;
    mechanism.ulParameterLen = sizeof(byteOffsetBytes);

        CK_ATTRIBUTE keyAttributes[] = {
                { CKA_TOKEN, &bFalse, sizeof(CK_BBOOL) }, // Generate a temporary session key that is not stored on the token.
                { CKA_SENSITIVE, &bSensitive, sizeof(CK_BBOOL) }, // Allow the derived secret to be extracted for further ing.
                { CKA_MODIFIABLE, &bFalse, sizeof(CK_BBOOL) },
                { CKA_EXTRACTABLE, &bExtractable, sizeof(CK_BBOOL) },
                { CKA_COPYABLE, &bFalse, sizeof(CK_BBOOL) }, // No more copying/splitting needed.
                { CKA_PRIVATE, &bTrue, sizeof(CK_BBOOL) },
                { CKA_ENCRYPT, &bTrue, sizeof(CK_BBOOL) },
                { CKA_DECRYPT, &bTrue, sizeof(CK_BBOOL) },
                { CKA_SIGN, &bTrue, sizeof(CK_BBOOL) },
                { CKA_VERIFY, &bTrue, sizeof(CK_BBOOL) },
                { CKA_WRAP, &bFalse, sizeof(CK_BBOOL) },
                { CKA_UNWRAP, &bFalse, sizeof(CK_BBOOL) },
                { CKA_DERIVE, &bTrue, sizeof(CK_BBOOL) },
                { CKA_KEY_TYPE, &keyType, sizeof(keyType) },
                { CKA_VALUE_LEN, &derivedSecretLen, sizeof(derivedSecretLen) },
                { CKA_CLASS, &secretKeyClass, sizeof(secretKeyClass) },
        };

        rv = C_DeriveKey(
                sessionHandle,
                &mechanism,
                inputKey,
                keyAttributes,
                sizeof(keyAttributes) / sizeof(CK_ATTRIBUTE),
                &newKey);

        return rv; }

/**
 * Generate a elliptic curve keypair that is suitable for ECIES.  */
 static CK_RV generate_ec_keypair( CK_SESSION_HANDLE hSession,
 CK_OBJECT_HANDLE &hPublic, CK_OBJECT_HANDLE &hPrivate,
 std::vector<uint8_t> &namedCurve) {

        CK_MECHANISM mechanism;
        memset(&mechanism, 0, sizeof(CK_MECHANISM));
        mechanism.mechanism = CKM_ECDSA_KEY_PAIR_GEN;

        CK_OBJECT_CLASS m_ecPublicKeyClass = CKO_PUBLIC_KEY;
        CK_OBJECT_CLASS m_ecPrivateKeyClass = CKO_PRIVATE_KEY;
        CK_KEY_TYPE m_ecKey = CKK_ECDSA;
        CK_BBOOL bFalse = CK_FALSE;
        CK_BBOOL bTrue = CK_TRUE;

        CK_ATTRIBUTE ecKeyPairPublicGenAttributes[] = {
                { CKA_CLASS, &m_ecPublicKeyClass, sizeof(m_ecPublicKeyClass) },
                { CKA_KEY_TYPE, &m_ecKey, sizeof(m_ecKey) },
                { CKA_TOKEN, &bFalse, sizeof(CK_BBOOL) }, // Don't store the key on the token.
                { CKA_PRIVATE, &bFalse, sizeof(CK_BBOOL) },
                { CKA_VERIFY, &bFalse, sizeof(CK_BBOOL) },
                { CKA_EC_PARAMS, namedCurve.data(), namedCurve.size() } };

        CK_ATTRIBUTE ecKeyPairPrivateGenAttributes[] = {
                { CKA_CLASS, &m_ecPrivateKeyClass, sizeof(m_ecPrivateKeyClass) },
                { CKA_KEY_TYPE, &m_ecKey, sizeof(m_ecKey) },
                { CKA_TOKEN, &bFalse, sizeof(CK_BBOOL) }, // Don't store the key on the token.
                { CKA_PRIVATE, &bTrue, sizeof(CK_BBOOL) },
                { CKA_EXTRACTABLE, &bFalse, sizeof(CK_BBOOL) },
                { CKA_SENSITIVE, &bTrue, sizeof(CK_BBOOL) },
                { CKA_SIGN, &bFalse, sizeof(CK_BBOOL) },
                { CKA_DERIVE, &bTrue, sizeof(CK_BBOOL) } };

        // Get number of attributes.
        const unsigned long numPublicKeyAttributes = sizeof(ecKeyPairPublicGenAttributes) / sizeof(CK_ATTRIBUTE);
        const unsigned long numPrivateKeyAttributes = sizeof(ecKeyPairPrivateGenAttributes) / sizeof(CK_ATTRIBUTE);

        // Generate key pair.
        const CK_RV rv = C_GenerateKeyPair(
                hSession,
                &mechanism,
                ecKeyPairPublicGenAttributes,
                numPublicKeyAttributes,
                ecKeyPairPrivateGenAttributes,
                numPrivateKeyAttributes,
                &hPublic,
                &hPrivate);

        return rv; }

static CK_RV run_example(CK_SESSION_HANDLE hSession) {

    // ECIES key generation and derivation with the standard P11 API:
        // Only one proprietary mechanism is used: CKM_KEY_SPLIT.

    // Type of the encryption key. This must match the chosen symmetric encryption algorithm.
    CK_KEY_TYPE encryptionKeyType = CKK_AES;

    // Length of the derived keys in bytes.
    CK_ULONG ulEncryptionKeyLength = 32;
    CK_ULONG ulMacKeyLength = 32;

    // DER encoded OID of the curve to be use.
        std::vector<uint8_t> namedCurve = { 0x06, 0x05, 0x2B, 0x81, 0x04, 0x00, 0x23 };

        // Generate receiver key pair.
    std::cout << "Generate receiver key pair." << std::endl;
    // In a real application this is a persistent key. Instead of generating it here it would
    // be looked up by name or ID.
        CK_OBJECT_HANDLE hPublic = CK_INVALID_HANDLE;
        CK_OBJECT_HANDLE hPrivate = CK_INVALID_HANDLE;

        { // Generate the receiver key pair.
                CK_RV rv = generate_ec_keypair(hSession, hPublic, hPrivate, namedCurve);

        if (rv != CKR_OK) {
            std::cout << "Failed to generate receiver key pair." << std::endl;
            return rv;
        }
        }

        // 1) Generate ephemeral sender key pair.
    std::cout << "Generate ephemeral sender key pair." << std::endl;
        CK_OBJECT_HANDLE hEphemeralPublic = CK_INVALID_HANDLE;
        CK_OBJECT_HANDLE hEphemeralPrivate = CK_INVALID_HANDLE;

        {
                // Generate ephemeral key pair.
        CK_RV rv = generate_ec_keypair(hSession, hEphemeralPublic, hEphemeralPrivate, namedCurve);

        if (rv != CKR_OK) {
            std::cout << "Failed to generate sender key pair." << std::endl;
            return rv;
        }
        }

        // Extract the bytes of the receiver public key.
    std::cout << "Extract the bytes of the receiver public key." << std::endl;
        std::vector<uint8_t> publicKeyBytes;
        {
                CK_RV rv = CKR_OK;
                CK_OBJECT_HANDLE hPubKey = hEphemeralPublic; // Key to be exported.

                // Get public value size ( DER encoded EC_POINT)
                CK_ATTRIBUTE attribute[] = {
                        { CKA_EC_POINT, NULL_PTR, 0 }
                };

                rv = C_GetAttributeValue(
                        hSession,
                        hPubKey,
                        attribute,
                        sizeof(attribute) / sizeof(CK_ATTRIBUTE));

                if (rv != CKR_OK) {
            std::cout << "Failed to query the length of the public key bytes." << std::endl;
            return rv;
        }

                //Allocate space for parameter attributes
                publicKeyBytes.resize(attribute[0].ulValueLen);
                attribute[0].pValue = publicKeyBytes.data();

                // Get the EC point of the public key.
                rv = C_GetAttributeValue(
                        hSession,
                        hPubKey,
                        attribute,
                        sizeof(attribute) / sizeof(CK_ATTRIBUTE));

                if (rv != CKR_OK) {
            std::cout << "Failed to extract the public key bytes." << std::endl;
            return rv;
        }
        }


        // 2) Do ECDH agreement with KDF and split the result into encryption key and MAC key.
    std::cout << "Do ECDH agreement." << std::endl;
        CK_OBJECT_HANDLE hSharedSecret = CK_INVALID_HANDLE;
        {
                CK_RV rv = CKR_OK;

                CK_BBOOL extractable = false; // Make the shared secret non-extractable.

                CK_ULONG derivedSecretLen = ulEncryptionKeyLength + ulMacKeyLength;

                // Set ECDH parameters.
                CK_ECDH1_DERIVE_PARAMS ecdh1DeriveParams;
                memset(&ecdh1DeriveParams, 0, sizeof(CK_ECDH1_DERIVE_PARAMS));
                ecdh1DeriveParams.kdf = CKD_SHA256_KDF;

                // Don't pass any salt (could be omitted because this are the default values set by `memset` above).
                ecdh1DeriveParams.ulSharedDataLen = 0;
                ecdh1DeriveParams.pSharedData = NULL;

                // Pass the bytes of the receiver's public key.
                ecdh1DeriveParams.pPublicData = publicKeyBytes.data();
                ecdh1DeriveParams.ulPublicDataLen = publicKeyBytes.size();

                // Assemble the mechanism struct.
                CK_MECHANISM mechanism;
                memset(&mechanism, 0, sizeof(CK_MECHANISM));
                mechanism.mechanism = CKM_ECDH1_DERIVE;
                mechanism.pParameter = &ecdh1DeriveParams;
                mechanism.ulParameterLen = sizeof(ecdh1DeriveParams);

                CK_BBOOL bFalse = CK_FALSE;
                CK_BBOOL bTrue = CK_TRUE;
                CK_KEY_TYPE     genericKey = CKK_GENERIC_SECRET;
                CK_OBJECT_CLASS secretKeyClass = CKO_SECRET_KEY;

                CK_BBOOL bSensitive = !extractable;

                CK_ATTRIBUTE keyAttributes[] = {
                        { CKA_TOKEN, &bFalse, sizeof(CK_BBOOL) }, // Generate a temporary session key that is not stored on the token.
                        { CKA_SENSITIVE, &bSensitive, sizeof(CK_BBOOL) },
                        { CKA_MODIFIABLE, &bFalse, sizeof(CK_BBOOL) },
                        { CKA_EXTRACTABLE, &extractable, sizeof(CK_BBOOL) },
                        { CKA_COPYABLE, &bTrue, sizeof(CK_BBOOL) }, // Need to mark as COPYABLE for key split function.
                        { CKA_PRIVATE, &bTrue, sizeof(CK_BBOOL) },
                        // This key is used only for DERIVE.
                        { CKA_ENCRYPT, &bFalse, sizeof(CK_BBOOL) },
                        { CKA_DECRYPT, &bFalse, sizeof(CK_BBOOL) },
                        { CKA_SIGN, &bFalse, sizeof(CK_BBOOL) },
                        { CKA_VERIFY, &bFalse, sizeof(CK_BBOOL) },
                        { CKA_WRAP, &bFalse, sizeof(CK_BBOOL) },
                        { CKA_UNWRAP, &bFalse, sizeof(CK_BBOOL) },
                        { CKA_DERIVE, &bTrue, sizeof(CK_BBOOL) },

                        { CKA_KEY_TYPE, &genericKey, sizeof(genericKey) },
                        { CKA_VALUE_LEN, &derivedSecretLen, sizeof(derivedSecretLen) },
                        { CKA_CLASS, &secretKeyClass, sizeof(secretKeyClass) },
                };

                // Run the key agreement.
                rv = C_DeriveKey(
                        hSession,
                        &mechanism,
                        hPrivate,
                        keyAttributes,
                        sizeof(keyAttributes) / sizeof(CK_ATTRIBUTE),
                        &hSharedSecret);

                if (rv != CKR_OK) {
            std::cout << "Failed to compute the ECDH agreement." << std::endl;
            return rv;
        }
        }

        // 3.1) Derive encryption and MAC key by splitting the result of the KDF.
    std::cout << "Derive encryption and MAC key." << std::endl;
        CK_OBJECT_HANDLE hEncKey = CK_INVALID_HANDLE;
        CK_OBJECT_HANDLE hMacKey = CK_INVALID_HANDLE;
        {

        // Specify whether the encryption key and MAC key are extractable or not.
        // To perform a local encryption they can be made exportable and used
        // externally.
        CK_BBOOL bExtractable = false;


                CK_RV rv = CKR_OK;
                { // Generate encryption and MAC key on the HSM.

                        rv = HSM_splitKey(hSession, 0, ulEncryptionKeyLength, bExtractable, encryptionKeyType, hSharedSecret, hEncKey);
                        if (rv != CKR_OK) {
                std::cout << "Failed to split the KDF output to create the encryption key: " << rv << std::endl;
                return rv;
            }

                        rv = HSM_splitKey(hSession, ulEncryptionKeyLength, ulMacKeyLength, bExtractable, CKK_GENERIC_SECRET, hSharedSecret, hMacKey);
                        if (rv != CKR_OK) {
                std::cout << "Failed to split the KDF output to create the MAC key: " << rv << std::endl;
                return rv;
            }
                }

                { // Destroy shared secret object on the HSM.
                        CK_RV destroy_rv = C_DestroyObject(hSession, hSharedSecret);
                        if (rv != CKR_OK) {
                std::cout << "Failed to destroy the shared secret result of the ECDH agreement." << std::endl;
            }
                }
        }

    // Check that the key handles have actually been set.
    if(hEncKey == 0 || hMacKey == 0) {
        std::cout << "Something went wrong." << std::endl;
        return CKR_GENERAL_ERROR;
    }

    // Now hEncKey and hMacKey can be used to encrypt and authenticate the payload data.
    // ...

    std::cout << "Successfully derived the encryption and MAC keys." << std::endl;

    return CKR_OK;
}


int main(int argc, char **argv) {

    // Connect to the HSM and log in.
    // Initialize PKCS#11 token.

    // Partition PIN.
    // In practice this should not be hardcoded.
    unsigned char* pPin = (unsigned char*)"PRIMUS";
    CK_ULONG ulPinLen = strlen((char*)pPin);

        { // Initialize the PKCS#11 connection.
                CK_C_INITIALIZE_ARGS args;
                memset(&args, 0, sizeof(CK_C_INITIALIZE_ARGS));
                args.flags = CKF_OS_LOCKING_OK;

                std::cerr << "Initialize PKCS#11 token." << std::endl;
                const CK_RV retCode = C_Initialize((CK_VOID_PTR)&args);
                if(CKR_OK != retCode){
                        std::cerr << "C_Initialize failed : 0x" << std::setfill('0') << std::setw(8) << std::hex << retCode << std::endl;
                        return retCode;
                }
        }

        // Get number of slots.
        CK_ULONG slotCount = 0;
        const bool allSlots = true;
        {
                CK_RV rv = C_GetSlotList(allSlots, NULL_PTR, &slotCount);
                if (CKR_OK != rv){
                        std::cerr << "C_GetSlotList failed with error code: 0x" << std::setfill('0') << std::setw(8) << std::hex << rv << std::endl;
                        return rv;
                }
        }

        std::cerr << "Number of slots: " << slotCount << std::endl;

        if (slotCount == 0) {
                std::cerr << "No slot found." << std::endl
                                << "Check correct user and HSM/host name (as defined in the configuration file)."
                                << std::endl;
                return CKR_GENERAL_ERROR;
        }

        // Fetch slot IDs.
        std::vector<CK_SLOT_ID> slotIDs(slotCount);
        { // Fetch slot IDs.
                const CK_RV rv = C_GetSlotList(allSlots, slotIDs.data(), &slotCount);
                if (CKR_OK != rv){
                        std::cerr << "C_GetSlotList failed with error code: 0x" << std::setfill('0') << std::setw(8) << std::hex << rv << std::endl;
                        return rv;
                }
        }

        // Connect to the first slot.
        std::cerr << "Connect to first slot." << std::endl;
        const CK_SLOT_ID slotID = slotIDs[0];


        CK_SESSION_HANDLE hSession = CK_INVALID_HANDLE;
        { // Open a session.
                std::cerr << "Open session... ";
                const CK_RV rv = C_OpenSession(slotID, CKF_SERIAL_SESSION | CKF_RW_SESSION, NULL_PTR, NULL_PTR, &hSession);
                if (CKR_OK != rv){
                        std::cerr << "C_OpenSession failed with error code: 0x" << std::setfill('0') << std::setw(8) << std::hex << rv << std::endl;
                        return rv;
                }
                std::cerr << "OK" << std::endl;
        }



        { // User login.
                std::cerr << "Login... ";

                CK_ULONG userType = CKU_USER;
                const CK_RV rv = C_Login(hSession, userType, pPin, ulPinLen);
                if (CKR_OK != rv){

                        if(rv == CKR_PIN_INCORRECT) {
                                std::cerr << "Incorrect pin." << std::endl;
                        } else {
                                std::cerr << "C_Login failed with error code: 0x" << std::setfill('0') << std::setw(8) << std::hex << rv << std::endl;
                        }
                        return rv;
                }
                std::cerr << "OK" << std::endl;
        }

    // Start the example code.
    CK_RV rv = run_example(hSession);

    return rv;
}
```
