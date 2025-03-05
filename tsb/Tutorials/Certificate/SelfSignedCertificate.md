---
sidebar_position: 0
title: Creating Self-Signed Certificates
sidebar_label: 1. Self-Signed Certificate
description: Learn how to create a self-signed certificate using Cloud HSM APIs.
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, create key, self-sign certificate, RSA key, SHA256 with RSA, certificate authority]
---


# 1. Self Signed Certificate

## Create key
**POST**: [/v1/key](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

**Description**: Create key request.

```js {14}
{
    "label": "certificate_key",
    "password": null,
    "algorithm": "RSA",
    "keySize": 2048,
    "attributes": {
        "decrypt": false,
        "sign": true,
        "unwrap": false,
        "derive": false,
        "bip32": false,
        "extractable": false,
        "modifiable": true,
        "destroyable": true,
        "copyable": false
    },
    "policy": null
}
```

## Create a self-signed certificate
**POST**: [/v1/certificate/synchronous/selfsign](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Certificate/selfSignCertificate)

**Description**: Creates and attaches a self-signed certificate to the keypair.

Setting the `certificateAuthority` to `true` adds the BasicConstraint `BC:CA:TRUE`, should be set for intermediate and CA Authorities.
```js {7}
{
  "signKeyName": "certificate_key",
  "keyPassword": null,
  "validity": 3650,
  "signatureAlgorithm": "SHA256_WITH_RSA",
  "commonName": "CA-Securosys-001",
  "certificateAuthority": true,
  "keyUsage": [
    "DIGITAL_SIGNATURE",
    "KEY_CERT_SIGN"
  ],
  "extendedKeyUsage": [
    "ANY_EXTENDED_KEY_USAGE"
  ]
}
```

