---
title: PKCS#11 - Key split mechanism
sidebar_label: Key split mechanism
---

# Key split mechanism

:::info

This features of the Primus PKCS#11 provider is a vendor defined
extension and is not part of the standard PKCS#11 suite.

:::

CKM\_KEY\_SPLIT is a mechanism for C\_DeriveKey which allows to create
a new key from a substring of a GENERIC\_SECRET value. The base key
must be marked as CKA\_COPYABLE. The start of the substring must be
provided as the mechanism parameter.

The following example creates a 256-bit AES key from a 32-byte substring
of "inputKey" which is a GENERIC_SECRET.

```C
// Set mechanism parameters.
CK_MECHANISM mechanism;
memset(&mechanism, 0, sizeof(CK_MECHANISM));
mechanism.mechanism = CKM_KEY_SPLIT;

// Pass the substring length as mechanism parameter.
uint32_t byteOffset = 16;
mechanism.pParameter = (void*)&byteOffset;
mechanism.ulParameterLen = sizeof(byteOffset);

// Define the byte length of the substring.
CK_ULONG derivedSecretLen = 32;

// Define the type of the new key.
CK_KEY_TYPE keyType = CKK_AES;
CK_OBJECT_CLASS secretKeyClass = CKO_SECRET_KEY;

CK_BBOOL bFalse = CK_FALSE;
CK_BBOOL bTrue = CK_TRUE;
CK_ATTRIBUTE keyAttributes[] = {
        { CKA_TOKEN, &bFalse, sizeof(CK_BBOOL) },
        { CKA_PRIVATE, &bTrue, sizeof(CK_BBOOL) },
        { CKA_ENCRYPT, &bTrue, sizeof(CK_BBOOL) },
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
```
