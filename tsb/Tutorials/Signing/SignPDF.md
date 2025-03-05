---
sidebar_position: 0
title: Sign a PDF with an Elliptic Curve (EC) Key
sidebar_label: Sign a PDF
description: Learn how to sign a PDF using REST API and EC keys on Cloud HSM.
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, PDF signing, REST API, create EC key, elliptic curve, cloud security, digital signatures, cryptographic operations, PDF signature, secure PDF signing]
---

# Sign a PDF

The `REST_API` license is required to create and use keys without policy

## Create EC Key (Elliptic Curve)
**POST**: [/v1/key](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

**Description**: Create key request.

Replace `<keyname>` and `password`. The password is optional and can be deleted completely.


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

:::tip Tip

The size limit for payload is 64 KB (total request size to the HSM). Larger payloads can be hashed by the caller
and the hash is used as payload.

:::

### Attributes regarded by the service:

`signRequest.payload` = the hash of the PDF to be signed using any hash algorithm preferred by the caller. In this example SHA-256 is used <br />
`signRequest.signKeyName` = the key to be used for signing <br />
`signRequest.signatureAlgorithm` = NONE_WITH_ECDSA, indicates that the payload has already been hashed by the caller and is in proper size. No hashing will be done on the HSM. This example is for EC keys. Corresponding algorithms also available for other key types


Replace `<keyname>` and `password`.
```js
{
    "signRequest": {
        "payload": "V9XQHTAVDqEypvi82Gf/IV1o2BrLQDRZMqKjjWpNxjM=",
        "payloadType": "PDF",
        "keyPassword": [ "R","E","P","L","A","C","E" ],
        "signKeyName": "<keyname>",
        "metaData": "
        "metaDataSignature": "MEUCIF+jC2zyMeONKK2zArTgn4pWbFqlxNJg2DnTwgqlSK1oAiEApFpnXQhdJmFidwOnc5rDPCiex67+pot2cbb07mDqU4I=",
        "signatureAlgorithm": "NONE_WITH_ECDSA"
    }
}

```

**Response**
```js
{
  "signature": "MEQCIGvSZ9Fylbf5ofsbXxDnpjWfr+IOPVL3qSUzlO7cDK88AiAzdZ7h5eXI9zo4UauFoVG3NEB9U99+leODNuJXGMnjvg=="
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
    "signatureAlgorithm": "NONE_WITH_ECDSA",
    "payload": "V9XQHTAVDqEypvi82Gf/IV1o2BrLQDRZMqKjjWpNxjM=",
    "signature": "MEQCIGvSZ9Fylbf5ofsbXxDnpjWfr+IOPVL3qSUzlO7cDK88AiAzdZ7h5eXI9zo4UauFoVG3NEB9U99+leODNuJXGMnjvg=="
  }
}
```

**Response**
```js
{
  "signatureValid": true
}
```