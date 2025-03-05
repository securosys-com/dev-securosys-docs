---
sidebar_position: 3
title: Authorization Request Samples
sidebar_label: More Request Samples
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';


Refer to the example of an SKA-enabled key with a signing approval policy (ruleUse) below:

:::note SKA-Key

The following examples are performed on keys configured with a policy. <br/>If you have not yet set up such a key, please follow [this chapter](/tsb/Tutorials/TransactionSecurityBroker/samples/step-by-step/create-policy-based-key) for guidance.

:::

<details>
<summary>Create a multi-authorization **signature** request</summary>

This sample shows how to create a multi-authorization task (defined by the Key's policy) to issue a **sign** request.

**POST**: [/v1/sign](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Requests/sign)
```js {3,5,7}
{
  "signRequest": {
    "payload": "ewogICJ0cmFuc2FjdGlvbl9pZCI6ICIzZjJjNGUwN2JjNGY4YjAwZThlNzMxOWUzMDdmZmIwNGQzZTRjM2U5IiwKICAiYmxvY2tfbnVtYmVyIjogNzM1ODQyLAogICJ0aW1lc3RhbXAiOiAiMjAyNC0wOS0xNVQxNDoyODozMFoiLAogICJmcm9tX2FkZHJlc3MiOiAiMHhBN0Q2YzNFM0U1QTQ0QzlhOTc4M0M0RjdmNkM2M0EzNDdGMWFCMWQxIiwKICAidG9fYWRkcmVzcyI6ICIweEU0RjIzQzk1QTE2QTk4MzcyRkNkMmIyMzNEOUU5NDVCN0E3RTU2MjUiLAogICJ2YWx1ZSI6IDEuNSwKICAiY3VycmVuY3kiOiAiRVRIIiwKICAiZ2FzX2xpbWl0IjogMjEwMDAsCiAgImdhc19wcmljZSI6ICIxMDAgZ3dlaSIsCiAgInRyYW5zYWN0aW9uX2ZlZSI6ICIwLjAwMjEgRVRIIiwKICAic3RhdHVzIjogIk5vdFlldEFwcHJvdmVkIiwKICAic2lnbmF0dXJlIjogIm51bGwiCn0K",
    "payloadType": "UNSPECIFIED",
    "signKeyName": "rsa_authorization_app_signing",
    "metaData": "ewogICJ0cmFuc2FjdGlvbl9pZCI6ICIzZjJjNGUwN2JjNGY4YjAwZThlNzMxOWUzMDdmZmIwNGQzZTRjM2U5IiwKICAiYmxvY2tfbnVtYmVyIjogNzM1ODQyLAogICJ0aW1lc3RhbXAiOiAiMjAyNC0wOS0xNVQxNDoyODozMFoiLAogICJmcm9tX2FkZHJlc3MiOiAiMHhBN0Q2YzNFM0U1QTQ0QzlhOTc4M0M0RjdmNkM2M0EzNDdGMWFCMWQxIiwKICAidG9fYWRkcmVzcyI6ICIweEU0RjIzQzk1QTE2QTk4MzcyRkNkMmIyMzNEOUU5NDVCN0E3RTU2MjUiLAogICJ2YWx1ZSI6IDEuNSwKICAiY3VycmVuY3kiOiAiRVRIIiwKICAiZ2FzX2xpbWl0IjogMjEwMDAsCiAgImdhc19wcmljZSI6ICIxMDAgZ3dlaSIsCiAgInRyYW5zYWN0aW9uX2ZlZSI6ICIwLjAwMjEgRVRIIiwKICAic3RhdHVzIjogIk5vdFlldEFwcHJvdmVkIiwKICAic2lnbmF0dXJlIjogIm51bGwiCn0K",
    "signatureAlgorithm": "SHA256_WITH_RSA_PSS",
    "signatureType": "DER"
  }
}
```
</details>






<details>
<summary>Create a multi-authorization **decrypt** request</summary>

This sample shows how to create a multi-authorization task (defined by the Key's policy) to issue a **decrypt** request.

**POST**: [/v1/encrypt](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/encrypt)
```js
{
  "encryptRequest": {
    "payload": "YXNk",
    "encryptKeyName": "rsa_authorization_app_signing",
    "cipherAlgorithm": "RSA_PADDING_OAEP_WITH_SHA512"
  }
}
```

**Response:**

```js

{
  "encryptedPayload": "SQWjvZObJcfzThb590e/wpk0fMj/lWAnu+eyh9XxPmeBp8p1+GdntV3hRl9u5A/jbHxuzcbN8qYHq0tHnznnUeIbzK8Qq+sk8izul5B/pZwVhlI3ABvlOpPgRSFv22adSlihPrEMXeqWWoaCoDOK8oabztt5uT77+NrNka66GQjTrrR87KjgwLfr0IFldGwX0g2iacTyyUAdLNCT0X6jmWUJvb+eq0i+FpgsjnNKVYPwvM0l1MF9Q9GO0ijmQ+m7VCP4UTwHvuN1cYXGp8tXmWCXSICLZQD7ccR5syUyiHlvwzpuP4oUhARd3TOz+bfyTdS04bBRDwEZ2N+wVQOjQQ==",
  "encryptedPayloadWithoutMessageAuthenticationCode": null,
  "initializationVector": null,
  "messageAuthenticationCode": null
}
```


**POST**: [/v1/decrypt](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Requests/decrypt)
```js
{
  "decryptRequest": {
    "encryptedPayload": "SQWjvZObJcfzThb590e/wpk0fMj/lWAnu+eyh9XxPmeBp8p1+GdntV3hRl9u5A/jbHxuzcbN8qYHq0tHnznnUeIbzK8Qq+sk8izul5B/pZwVhlI3ABvlOpPgRSFv22adSlihPrEMXeqWWoaCoDOK8oabztt5uT77+NrNka66GQjTrrR87KjgwLfr0IFldGwX0g2iacTyyUAdLNCT0X6jmWUJvb+eq0i+FpgsjnNKVYPwvM0l1MF9Q9GO0ijmQ+m7VCP4UTwHvuN1cYXGp8tXmWCXSICLZQD7ccR5syUyiHlvwzpuP4oUhARd3TOz+bfyTdS04bBRDwEZ2N+wVQOjQQ==",
    "decryptKeyName": "rsa_authorization_app_signing",
    "cipherAlgorithm": "RSA_PADDING_OAEP_WITH_SHA512",
"metaData": "RmluYW5jZSBPZmZpY2VyICJIYW5zIE11c3RlciIgcmVxdWVzdHMgdG8gZGVjcnlwdCBGaWxlICdzYWxhcnkuZG9jeCcgZm9yIHByb2Nlc3NpbmcgYW5kIGZ1cnRoZXIgYW5hbHlzaXMgaW4gY29tcGxpYW5jZSB3aXRoIGNvbXBhbnkgZGF0YSBzZWN1cml0eSBwcm90b2NvbHMu"
}
}
```
</details>







<details>
<summary>Create a multi-authorization **self-signed certificate request**</summary>

This sample shows how to create a multi-authorization task (defined by the Key's policy) to issue a self-signed certificate.

**POST**: [/v1/certificate/selfsign](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Certificate/selfSignCertificateWithSkaKey)

```js
{
  "selfSignCertificateRequest": {
    "signKeyName": "rsa_authorization_app_signing",
    "validity": 365,
    "signatureAlgorithm": "SHA256_WITH_RSA",
    "commonName": "securosys.com",
    "keyUsage": [
      "DIGITAL_SIGNATURE"
    ],
    "extendedKeyUsage": [
      "ANY_EXTENDED_KEY_USAGE"
    ],
    "metaData": "ewogICJjZXJ0aWZpY2F0ZVJlcXVlc3QiOiB7CiAgICAiY29tbW9uTmFtZSI6ICJleGFtcGxlLmNvbSIKICB9Cn0=",
    "certificateAuthority": true
  }
}
```
</details>
















<details>
<summary>Create a multi-authorization **Key Management** Task</summary>

This sample shows how to create a multi-authorization task (defined by the Key's policy) to **block** a key to be used for cryptographic operations (sign, decrypt,..).

**POST**: [/v1/block](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Requests/block)
```js {}
{
  "blockRequest": {
    "blockKeyName": "rsa_authorization_app_signing",
    "metaData": "WW91ciBDRU8gcmVxdWVzdGVkIHRvIGJsb2NrIHRoZSBrZXkgJ3JzYV9hdXRob3JpemF0aW9uX2FwcF9zaWduaW5nJyBmb3IgZnVydGhlciB1c2FnZQ=="
  }
}
```

This sample shows how to create a multi-authorization task (defined by the Key's policy) to **unblock** a key to be used for cryptographic operations (sign, decrypt,..).

**POST**: [/v1/unblock](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Requests/unblock)

```js {}
{
  "unblockRequest": {
    "unblockKeyName": "rsa_authorization_app_signing",
    "metaData": "WW91ciBDRU8gcmVxdWVzdGVkIHRvIHVuYmxvY2sgdGhlIGtleSAncnNhX2F1dGhvcml6YXRpb25fYXBwX3NpZ25pbmcnIGZvciBmdXJ0aGVyIHVzYWdl"
  }
}
```



</details>









<details>
<summary>Create a multi-authorization **unwrap/export** key-request</summary>

This sample shows how to create a multi-authorization task (defined by the Key's policy) to **wrap/export** a key securely.

**POST**: [/v1/wrap](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/wrap)

```js {3,4}
{
  "wrapKeyRequest": {
    "keyToBeWrapped": "tsb-demo-aes-extractable",
    "wrapKeyName": "rsa_authorization_app_signing",
    "wrapMethod": "RSA_WRAP_OAEP"
  }
}
```
**Response:**
```js
{
  "wrappedKey": "jfU9mbgZAasjLqhvULUWr2x+iN1U6a5Sm6Otj3ANEZPcGyph5bDF3vCPPVrzlMIbZtzUQH5O1Vcvla8DISKbzn3LqpXhVshHFmDibOD7nA3a5PegjMUfTo+/YHLA/AZtGOpoZEJhuoI4UThfXdXs0wvoim245q+Gf/GDUCLZdGD0VFyiiPZ8RBiEhDMt1AL5vUBclBiqvTn0ci/ElGjIZewxtEDz3ixqxoku7oChST1SCypWhkcbiTV8Sv8R4UjDxELqM1pcC65tMR0nS+YGuehX08KhHprOiCsg1DXuFqjqfdKPB8wOVbgkZlFx24G+5aSTPogk91rPD6h0qu4sxw=="
}
```

**POST**: [/v1/unwrap](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Requests/unwrap)
```js {3,4,21}
{
  "unwrapKeyRequest": {
    "wrappedKey": "jfU9mbgZAasjLqhvULUWr2x+iN1U6a5Sm6Otj3ANEZPcGyph5bDF3vCPPVrzlMIbZtzUQH5O1Vcvla8DISKbzn3LqpXhVshHFmDibOD7nA3a5PegjMUfTo+/YHLA/AZtGOpoZEJhuoI4UThfXdXs0wvoim245q+Gf/GDUCLZdGD0VFyiiPZ8RBiEhDMt1AL5vUBclBiqvTn0ci/ElGjIZewxtEDz3ixqxoku7oChST1SCypWhkcbiTV8Sv8R4UjDxELqM1pcC65tMR0nS+YGuehX08KhHprOiCsg1DXuFqjqfdKPB8wOVbgkZlFx24G+5aSTPogk91rPD6h0qu4sxw==",
    "label": "tsb-demo-extracted",
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
    "unwrapKeyName": "rsa_authorization_app_signing",
    "metaData": "VW53cmFwIEtleSAndHNiLWRlbW8tZXh0cmFjdGVkJyB3aXRoICd0c2ItZGVtby1za2EnIFJTQV9XUkFQX09BRVA=",
    "wrapMethod": "RSA_WRAP_OAEP"
  }
}
```
</details>