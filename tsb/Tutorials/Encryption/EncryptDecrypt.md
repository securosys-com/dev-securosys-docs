---
sidebar_position: 0
title: Encryption and Decryption Using Cloud HSM
sidebar_label: Encryption & Decryption
description: Learn how to encrypt and decrypt data using Cloud Hardware Security Modules (HSM).
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, AES encryption, AES decryption, AES key creation, symmetric encryption, AES_GCM]
---

# Encrypt & Decrypt

The `REST_API` license is required to create and use keys without policy

:::tip Tip

Key's without policy are of different key-type and thus cannot be transformed to SKA-Key later on.

:::


## Create AES Key
**POST**: [/v1/key](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

**Description**: Create key request.

Replace `<keyname>` and `password`. The password is optional and can be deleted completely.

```js
{
    "label": "<keyname>",
    "password": [ "R","E","P","L","A","C","E" ],
    "algorithm": "AES",
    "keySize": 256,
    "attributes": {
        "sign": false,
        "extractable": false,
        "modifiable": true,
        "destroyable": true,
        "sensitive": true,
        "decrypt": true,
        "unwrap": false
    }
}
```

## Encrypt a payload
POST: [/v1/encrypt](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/encrypt)

**Description**: Contains an encrypt request.

Replace `<keyname>` and `password`. The password is optional and can be deleted completely.
```js
{
  "encryptRequest": {
    "payload": "RW5jcnlwdERlY3J5cHREZW1v",
    "encryptKeyName": "<keyname>",
    "keyPassword": [ "R","E","P","L","A","C","E" ],
    "cipherAlgorithm": "AES_GCM",
    "tagLength": 128
  }
}
```


**Response**

```js
{
  "encryptedPayload": "ZK/UOxbOPW9rYWe8vZqCmDzfP5r6kv2zpdsE8hnbqg2MCA==",
  "encryptedPayloadWithoutMessageAuthenticationCode": "ZK/UOxbOPW9rYWe8vZqCmDzf",
  "initializationVector": "C73HyZuY5nnKyChF",
  "messageAuthenticationCode": "P5r6kv2zpdsE8hnbqg2MCA=="
}
```

## Decrypt the `encryptedPayload`

POST: [/v1/synchronousDecrypt](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/synchronousDecrypt)

**Description**: Contains an decrypt request and initializationVector.

Replace `<keyname>`, `password`, `encryptedPayload` and `initializationVector` from the previous encrypt response.

```js
{
  "decryptRequest": {
    "encryptedPayload": "ZK/UOxbOPW9rYWe8vZqCmDzfP5r6kv2zpdsE8hnbqg2MCA==",
    "decryptKeyName": "<keyname>",
    "keyPassword": [ "R","E","P","L","A","C","E" ],
    "cipherAlgorithm": "AES_GCM",
    "initializationVector": "C73HyZuY5nnKyChF",
    "tagLength": 128
  }
}
```

**Response**

```js
{
  "payload": "RW5jcnlwdERlY3J5cHREZW1v"
}
```