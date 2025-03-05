---
sidebar_position: 0
title: Wrapping with Hardware Security Module (HSM)
sidebar_label: Wrap & Unwrap
description: Learn about managing keys securely with Hardware Security Modules (HSMs) for key wrapping and unwrapping.
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, REST API license, EC Key, Elliptic Curve, create key request, sign request, verify signature, synchronous sign, signature algorithm, supported EC-Curves]
---

# Wrap & Unwrap

The `REST_API` license is required to create and use keys without policy. 

In this example we are using an Symmetric AES-Key to Wrap an EC-Key, using the wrapping method `AES_WRAP_EC`. Different algorithm requires different wrapping methods such as: 

* AES_WRAP, AES_WRAP_**DSA**, AES_WRAP_**EC**, AES_WRAP_**ED**, AES_WRAP_**RSA**, AES_WRAP_**BLS**, AES_WRAP_PAD, AES_WRAP_**PAD_DSA**, AES_WRAP_**PAD_EC**, AES_WRAP_**PAD_ED**, AES_WRAP_**PAD_RSA**, AES_WRAP_**PAD_BLS**, 
* RSA_WRAP_**PAD**, RSA_WRAP_**OAEP**

## Create AES Wrapping Key
**POST**: [/v1/key](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

**Description**: Create key request.

:::note

Instead of creating an AES-Key, the wrapping-key can be imported. [/v1/import/plain](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/importPlainKey)
:::

```js
{
    "label": "kms-wrapping-key-aes",
    "algorithm": "AES",
    "keySize": 256,
    "attributes": {
        "encrypt": true,
        "decrypt": true,
        "verify": true,
        "sign": false,
        "wrap": true,
        "unwrap": true,
        "derive": false,
        "bip32": false,
        "slip10": false,
        "extractable": true,
        "modifiable": true,
        "destroyable": true,
        "sensitive": true,
        "copyable": true
    }
}
```

## Create Key to be Wrapped (EC-Key)
**POST**: [/v1/key](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

**Description**: Create key request.

```js
{
    "label": "test-ec-key",
    "algorithm": "EC",
    "curveOid": "1.3.132.0.10",
        "attributes": {
        "encrypt": true,
        "decrypt": true,
        "verify": true,
        "sign": true,
        "wrap": true,
        "unwrap": true,
        "derive": true,
        "bip32": true,
        "slip10": false,
        "extractable": true,
        "modifiable": true,
        "destroyable": true,
        "sensitive": false,
        "copyable": true,
        "neverExtractable": false,
        "alwaysSensitive": false
        }
    }
```

## Create Wrap Request (AES Wraps EC)
**POST**: [/v1/wrap](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/wrap)

**Description**: Create wrap request

```js
{
    "wrapKeyRequest": {
        "keyToBeWrapped": "test-ec-key",
        "wrapKeyName": "kms-wrapping-key-aes",
        "wrapMethod": "AES_WRAP_EC"
    }
}
```

**Response:**

```js
{
  "wrappedKey": "FgqsoaNa7nA2RoHnUUWUpnZXz6RvkPNma5bLOFwU4+xSGt7O3+WKmq3SSWe9BEHSvdhKE7cTGyvaJIt2QX0XpXh6eYKXmgSwGqfsPxuAFXXAyZg+PXdrpF5waqvTVLjG/UufzRGiEiJmQ2vLsxtkFgqNy8SwPeWi7IZ9jC62QZQ0psH0EuYBCG/bzx/FaVRRozmK4SlgvVzcwGDA5JdpOQJwCg9jKAkIiDrnN8uzfnm3vpkpoV0kgg=="
}
```

## Create Unwrap Request (AES Unwraps EC)
**POST**: [/v1/unwrap](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/synchronousUnwrap)

**Description**: Create unwrap request

```js
{
  "unwrapKeyRequest": {
    "wrappedKey": "FgqsoaNa7nA2RoHnUUWUpnZXz6RvkPNma5bLOFwU4+xSGt7O3+WKmq3SSWe9BEHSvdhKE7cTGyvaJIt2QX0XpXh6eYKXmgSwGqfsPxuAFXXAyZg+PXdrpF5waqvTVLjG/UufzRGiEiJmQ2vLsxtkFgqNy8SwPeWi7IZ9jC62QZQ0psH0EuYBCG/bzx/FaVRRozmK4SlgvVzcwGDA5JdpOQJwCg9jKAkIiDrnN8uzfnm3vpkpoV0kgg==",
    "label": "unwrapped-ec-key",
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
        "destroyable": false,
        "sensitive": true,
        "copyable": false
    },
    "unwrapKeyName": "kms-wrapping-key-aes",
    "wrapMethod": "AES_WRAP_EC"
  }
}
```

## Fetch Attributes of Unwrap Key
**POST**: [/v1/key/attributes](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/getKeyAttributesWithKeyPassword)

**Description**: Create unwrap request

```js
{
  "label": "unwrapped-ec-key"
}
```

**Response:**

```js
{
    "label": "unwrapped-ec-key",
    "id": null,
    "uuid": null,
    "algorithm": "EC",
    "algorithmOid": "1.2.840.10045.2.1",
    "curveOid": "1.3.132.0.10",
    "derivedAttributes": null,
    "keySize": null,
    "keyUsageCount": 0,
    "createTime": "2024-11-21T12:05:20Z",
    "attestTime": "2024-11-21T12:06:46Z",
    "publicKey": "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAE1JcyDgnYVd+GMis78x6ZkPMPBbAjJsgt7vDZKo8GtkZoN9lnR1ZyvVqGYk/iyS6DB2A4SA5JEnKil29LQsGH0g==",
    "addressTruncated": null,
    "attributes": {
      "decrypt": true,
      "sign": true,
      "ekaSign": null,
      "unwrap": true,
      "derive": false,
      "sensitive": true,
      "alwaysSensitive": false,
      "extractable": false,
      "neverExtractable": false,
      "modifiable": true,
      "copyable": false,
      "destroyable": false
    },
    "policy": null
  },
  "xmlSignature": "cXC7BlSUlKy+v28BsUeYw301nhioBCxsq2CBAkGvppIcjQV6gxl4f786uiZLSDAGxwwlXD/hr2GyTut5xOn7i+79xMUckiWKCfoab/YSBqS/Cu6AY2dAUFQA0suxh8v1ptbpTdv1oJwie+x3vU/ABfgARbb4LLkSa9iP5OVTypH9RTu0Ayhk2un6YlIcNuL0n0TJcKN3dSIDbIcOdIWzr9pKsq+ahzRAWKMRjzHLQKwbz/yVQ9vH6TMycCb+xKlVPyaFQFU/xZIimaMyGos4ORNTll3DgF4pGak/YPku9KnpkW+/uzvGuGYMgu40iccm74Rc5a1Wj4DmATSMon7myg==",
  "attestationKeyName": "attestation-key"
}
```