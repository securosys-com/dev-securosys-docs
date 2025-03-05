---
sidebar_position: 1
title: List of Key Attributes - HSM
sidebar_label: What are Key Attributes?
description: The Rest-API offers an easy to integrate HSM interface for fast, modern, flexible and secure development lifecycle.
keywords: [hsm, hardware security module, key attributes, encryption, decryption, digital signatures, verify signatures, key wrapping, key unwrapping, key derivation, BIP32, extractable keys, modifiable keys, destroyable keys, sensitive keys, copyable keys, cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service]
---

# Key-Attributes

|Attribute|Description|
|--|--|
|Encrypt|The key can be used for encryption, meaning it can transform plaintext into ciphertext.|
|Decrypt|The key can be used for decryption, allowing it to transform ciphertext back into plaintext.|
|Sign|The key can be used for creating digital signatures.|
|Verify|The key can be used for verifying digital signatures.|
|Wrap|The key can be used to wrap (encrypt) other keys for secure transmission or storage.|
|Unwrap|The key can be used to unwrap (decrypt) encrypted keys.|
|Derive|The key can be used to derive other keys.|
|BIP32|The key can be used for hierarchical deterministic key derivation (BIP32).|
|SLIP10|The key can be used for hierarchical deterministic key derivation (SLIP10).|
|Extractable|The key can be extracted from the device or context where it's stored.|
|Modifiable|The key can be modified once it's generated. The attributes can only get harder, not weaker (e.g. sensitive was `false`, can be set to `true` but not vice-versa). The 'modifiable' attribute applies exclusively to the key attribute and not to SKA-Policy.|
|Destroyable|The key can be intentionally destroyed (deleted).|
|Sensitive|The key contains sensitive information and is treated as confidential.|
|Copyable|The key can be copied or duplicated.|