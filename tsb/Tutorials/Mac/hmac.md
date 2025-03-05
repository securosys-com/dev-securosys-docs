---
sidebar_position: 0
title: Sign a Payload with HMAC
sidebar_label: HMAC Signing
description: Learn how to sign a payload using REST API and HMAC keys.
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hmac signing, REST API, create HMAC key, cloud security, digital signatures, cryptographic operations, secure signing]
---

# Sign a Payload with HMAC

The `REST_API` license is required to create and use keys without policy.

HMAC (Hash-based Message Authentication Code) is a mechanism that uses a cryptographic hash function and a secret cryptographic key to verify both the data integrity and the authenticity of a message. HMAC can be used with any iterative cryptographic hash function, such as SHA-256, in combination with a secret shared key.

## Create HMAC Key
**POST**: [/v1/key](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

**Description**: Create key request.

Replace `<keyname>` as needed. The password is optional and can be deleted completely.

```js {4}
{
    "label": "<keyname>",
    "password": null,
    "algorithm": "HMACSHA256",
    "attributes": {
        "sign": true,
        "extractable": false,
        "modifiable": false,
        "destroyable": false,
        "sensitive": true,
        "decrypt": false,
        "unwrap": false
    }
}
```

## Sign a Payload
**POST**: [/v1/synchronousHmac](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/hmac)

**Description**: Contains a sign request without metadata.

`hmacRequest.payload` = the data to be signed. It must be Base64-encoded.<br />
`hmacRequest.keyName` = the key to be used for signing.

Replace `<keyname>` with the name of your key.
```js
{
    "hmacRequest": {
        "keyName": "<keyname>",
        "payload": "YXNk"
    }
}
```

**Response**
```js
{
  "keyedHash": "D8xCI255hjQf+x4cfO3l97WC8K+Lg+ARiXHMkvGAGOU="
}
```

## Verify HMAC Signature
**POST:** [/v1/verify](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/verifyHmac)

**Description** Verify the HMAC signature of a payload.

Replace `hmac` from the previous response and set the initial `payload`.

```js
{
  "verifyHmacRequest": {
    "keyName": "<keyname>",
    "payload": "YXNk",
    "signature": "D8xCI255hjQf+x4cfO3l97WC8K+Lg+ARiXHMkvGAGOU="
  }
}
```

**Response**
```js
{
  "signatureValid": true
}
```

This documentation provides step-by-step instructions for creating an HMAC key, signing a payload using the HMAC key, and verifying the HMAC signature using REST API endpoints.