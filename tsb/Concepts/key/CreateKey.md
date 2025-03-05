---
sidebar_position: 0
title: How to create RSA, EC, AES, PQC keys?
sidebar_label: How to create a key?
description: The Rest-API offers an easy to integrate HSM interface for fast, modern, flexible and secure development lifecycle.
keywords: [hsm, hardware security module, create key, REST API, RSA encryption, Elliptic Curve Cryptography, EC key, AES encryption, Advanced Encryption Standard, Crystals-Dilithium, PQC, key management, encryption attributes, key policy, key-size, cybersecurity, key security, cloud security, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service]
---

# Create Key

The `REST_API` license is required to create and use keys
 
:::tip Tip

Key's without policy are of different key-type and thus cannot be transformed to SKA-Key later on.

:::

## Endpoint
**POST**: [/v1/key](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

**Description**: Create key request.

## RSA (Rivest-Shamir-Adleman)

```js
{
    "label": "<keyname>",
    "password": [ "R","E","P","L","A","C","E" ],
    "algorithm": "RSA",
    "keySize": 2048,
    "attributes": {
        "sign": true,
        "extractable": false,
        "modifiable": true,
        "destroyable": true,
        "sensitive": true,
        "decrypt": false,
        "unwrap": false
    }
}
```


## EC (Elliptic Curve Cryptography)

`Slip-0010` derivation is supported upon HSM-Firmware v3.0.8 and TSB v2.1.0
```js
    {
        "label": "<keyname>",
        "password": [ "R","E","P","L","A","C","E" ],
        "algorithm": "EC",
        "curveOid": "1.3.132.0.10",
        "attributes": {
            "encrypt": true,
            "decrypt": true,
            "verify": true,
            "sign": true,
            "wrap": true,
            "unwrap": true,
            "derive": false,
            "bip32": false,
            "slip10": false,
            "extractable": false,
            "modifiable": true,
            "destroyable": true,
            "sensitive": true,
            "copyable": false
        }
    }
```

## ED (Edwards-curve Digital Signature Algorithm)

`Slip-0010` derivation is supported upon HSM-Firmware v3.0.8 and TSB v2.1.0
```js
    {
        "label": "<keyname>",
        "password": [ "R","E","P","L","A","C","E" ],
        "algorithm": "ED",
        "curveOid": "1.3.101.112",
        "attributes": {
            "encrypt": true,
            "decrypt": true,
            "verify": true,
            "sign": true,
            "wrap": true,
            "unwrap": true,
            "derive": false,
            "bip32": false,
            "slip10": false,
            "extractable": false,
            "modifiable": true,
            "destroyable": true,
            "sensitive": true,
            "copyable": false
        }
    }
```

## AES (Advanced Encryption Standard)

```js
{
    "label": "aes",
    "algorithm": "AES",
    "keySize": 256,
    "attributes": {
        "encrypt": true,
        "decrypt": true,
        "verify": true,
        "sign": true,
        "wrap": true,
        "unwrap": true,
        "derive": false,
        "bip32": false,
        "extractable": false,
        "modifiable": true,
        "destroyable": true,
        "sensitive": true,
        "copyable": false
    }
}
```

## Dilithium (Crystals-Dilithium, PQC)
```js
{
    "label": "pqc_dilithium",
    "password": null,
    "algorithm": "DILITHIUM_L5",
    "attributes": {
        "sign": true,
        "extractable": false,
        "modifiable": true,
        "destroyable": true,
        "sensitive": true,
        "decrypt": false,
        "unwrap": false
    }
}
```

## LMS (Leighton–Micali Signatures, PQC)
```js
{
    "label": "lms_dilithium",
    "password": null,
    "algorithm": "LMS",
    "attributes": {
        "sign": true,
        "extractable": false,
        "modifiable": true,
        "destroyable": true,
        "sensitive": true,
        "decrypt": false,
        "unwrap": false
    }
}
```

## BLS (Boneh-Lynn-Shacham)
```js
{
  "label": "bls_approver",
  "algorithm": "BLS",
  "attributes": {
    "encrypt": true,
    "decrypt": true,
    "verify": true,
    "sign": true,
    "wrap": true,
    "unwrap": true,
    "derive": false,
    "bip32": false,
    "slip10": false,
    "extractable": true,
    "modifiable": true,
    "destroyable": true,
    "sensitive": false,
    "copyable": false
  },
  "policy": null
}
```

## HMAC
```js
{
    "label": "hmacsha256",
    "password": null,
    "algorithm": "HMACSHA256",
    "attributes": {
        "sign": false,
        "extractable": false,
        "modifiable": false,
        "destroyable": false,
        "sensitive": true,
        "decrypt": false,
        "unwrap": false
    }
}
```