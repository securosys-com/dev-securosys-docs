---
sidebar_position: 0
title: Importing Keys from Bip32 Seed with Cloud HSM
sidebar_label: Import from Bip32 Seed
description: Learn how to import keys from a BIP32 seed using Cloud Hardware Security Modules (HSM), with BTC address generation and signing capabilities.
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, BIP32 seed, BTC address generation, key import, HSM key import, ECC cryptography, elliptic curve cryptography, key attributes, HSM policies]
---

# Import from Bip32 Seed

The example below shows how to use the Rest-API endpoint to import a key from a BIP32 seed, with BTC address generation and signing capabilities.

The `TSB_ENGINE` and `KEY_AUTH` license is required to create SKA-Keys and generate BTC-Addresses.

:::note note

    If you do not have a SmartKeyAttributes subscription, you must remove the `policy` and the `addressFormat` from the request below. Be careful, you    have to generate the Wallet-Address by yourself!

:::

**POST**: [/v1/importedKey](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/importKey)

**Description**: Import key from a BIP32 seed.

Replace `<keyname>` and `seed` with your own values. Note, the seed chosen here is an example 32 byte array.

```js
{
    "label": "<keyname>",
    "addressFormat": {
        "format": "BTC"
    },
    "curveOid": "1.3.132.0.10",
    "seed": "AAECAwQFBgcICQoLDA0ODxAREhMUFRYXGBkaGxwdHh8=",
    "attributes": {
        "encrypt": false,
        "decrypt": false,
        "verify": true,
        "sign": true,
        "wrap": false,
        "unwrap": false,
        "derive": true,
        "bip32": true,
        "extractable": false,
        "modifiable": true,
        "destroyable": true,
        "sensitive": true,
        "copyable": false
    },
    "policy": {
        "ruleUse": null,
        "ruleBlock": null,
        "ruleUnblock": null,
        "ruleModify": null,
        "keyStatus": {
            "blocked": false
        }
    }
}
```