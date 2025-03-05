---
sidebar_position: 5
title: Key Arguments- Secrets Engine plugin for HashiCorp Vault
sidebar_label: Key Arguments
description: Key Arguments for Securosy' Secrets Engine plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---

# Key Arguments

> **keyLabel:** The created key will be stored on your Hardware Security Module (HSM) with this name. This parameter is **required**. 

> **attributes:** The attributes of the key that should be created. At least one cryptographic operation (**decrypt**, **sign**, **unwrap**) must be allowed (**true**). This parameter is **required**. 

**Available key attributes:**
1) **encrypt** - The key can be used to encrypt data. 
1) **decrypt** - The key can be used to decrypt data.
1) **verify** - The key can be used to verify signatures. 
1) **sign** - The key can be used to create signatures.
1) **wrap** - The key can be used to wrap another key.
1) **unwrap** - The key can be used to unwrap keys.
1) **derive** - The key can be derivable. **default**: *false*
1) **bip32** - Key derivation is done using BIP32. This option can only be true if the key's algorithm is EC and the derive attribute is true. **default**: *false*
1) **extractable** - The key is extractable. This option can only be true for keys without smart key attributes. **default**: *false*
1) **modifiable** - The key can be modified. **default**: *true*
1) **destroyable** - The key can be deleted. **default**: *true*
1) **sensitive** - The key is sensitive. To export a key sensitive must be false
1) **copyable** - The encrypted key is stored in an external memory. **default**: *false*

**Structure** Allows to define the attributes as a **JSON object**. Key = Value structure.
For example:
```js
{
  "decrypt": true,
  "sign": true,
  "unwrap": true,
  "derive": true,
  "modifiable": true,
  "copyable": false,
  "destroyable": true
}
```
> **curveOid:** The oid of the curve used for the EC or ED algorithm. Mandatory if the chosen algorithm is set to EC or ED. Sample OID's: secp256k1=1.3.132.0.10, Ed25519=1.3.101.112, secp384r1=1.3.132.0.34, (prime256v1 / secp256r1): 1.2.840.10045.3.1.7 

> **keySize:** The length of the key. Only applicable for AES, Camellia, RSA , DSA.

> **policy:** Defines the Smart Key Attribute (SKA) policy of a key. Contains the rules to use this key for signing a payload in a sign request, the rules to block and unblock this key, and the rules to modify the policy of this key. If a rule is empty the associated operation can be performed without any approvals. If the policy is empty the key does not use smart key attributes, and it is not possible to add them later. If a policy is used with the key, the key cannot be exported.
**Structure** Allows to define all required approvals as a **JSON object**. Key = Value structure.

For example:
```js
{
  "TM": public_key_1,
  "WK": public_key_2,
}
