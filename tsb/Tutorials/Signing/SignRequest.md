---
sidebar_position: 0
title: Signing with Hardware Security Module (HSM)
sidebar_label: Sign & Verify
description: Learn about managing keys securely with Hardware Security Modules (HSMs) for data encryption and digital signatures.
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, REST API license, EC Key, Elliptic Curve, create key request, sign request, verify signature, synchronous sign, signature algorithm, supported EC-Curves]
---

# Sign & Verify

The `REST_API` license is required to create and use keys without policy

:::tip Tip

Key's without policy are of different key-type and thus cannot be transformed to SKA-Key later on.

:::

## Create EC Key (Elliptic Curve)
**POST**: [/v1/key](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

**Description**: Create key request.

Replace `<keyname>` and `password`. The password is optional and can be deleted completely.

:::tip Tip

For more information about supported EC-Curves check: [Supported Curve-Oid's](/tsb/Concepts/key/Curve-Oid.md)

:::

```js
{
    "label": "<keyname>",
    "password": [ "R","E","P","L","A","C","E" ],
    "algorithm": "EC",
    "curveOid": "1.3.132.0.10",
    "attributes": {
        "encrypt": false,
        "decrypt": false,
        "verify": true,
        "sign": true,
        "wrap": false,
        "unwrap": false,
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

## Sign a Payload
**POST**: [/v1/synchronousSign](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/synchronousSign)

**Description**: Contains an sign request without metadata.

Replace `<keyname>` and `password`.
```js
{
  "signRequest": {
    "payload": "U2lnblBheWxvYWREZW1v",
    "payloadType": "UNSPECIFIED",
    "signKeyName": "<keyname>",
    "keyPassword": [ "R","E","P","L","A","C","E" ],
    "signatureAlgorithm": "SHA256_WITH_ECDSA"
  }
}
```

**Response**
```js
{
  "signature": "MEUCIQD6085OQP6nrwvDWDDyYFtjIOIXJ1OpY5CIeiAiXU6tCwIgZNNM7KEtpk5vy+GupNhNdyLa4M+humtlLsgZQdJ9fcc="
}
```

## Verify
**POST:** [/v1/verify](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/verifySignature)

**Description** Verify signature of a payload


Replace `signature` from the previous response and set the initial `payload`
```js
{
  "verifySignatureRequest": {
    "signKeyName": "<keyname>",
    "masterKeyPassword": [ "R","E","P","L","A","C","E" ],
    "signatureAlgorithm": "SHA256_WITH_ECDSA",
    "payload": "U2lnblBheWxvYWREZW1v",
    "signature": "MEUCIQD6085OQP6nrwvDWDDyYFtjIOIXJ1OpY5CIeiAiXU6tCwIgZNNM7KEtpk5vy+GupNhNdyLa4M+humtlLsgZQdJ9fcc="
  }
}
```

**Response**
```js
{
  "signatureValid": true
}
```