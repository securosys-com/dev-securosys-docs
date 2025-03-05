---
sidebar_position: 6
title: Address Format for Cryptocurrencies
sidebar_label: Address Format
description: Learn about address formats for various cryptocurrencies supported by smart key attributes, enabling secure key management and cryptographic operations.
keywords: [address format, cryptocurrency, bitcoin, BTC, ETH, XLM, XRP, IOTA, BIP-32, smart key attributes, key management, cloud hsm, hsm services, cryptography, cybersecurity, data security, hsm, hardware security module]
---


# Address Format

Contains the crypto currency for which an address should be created. 
An address can only be created for keys with smart key attributes.

- BTC
- ETH
- XLM
- XRP
- IOTA

**POST:** [/v1/key/address](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/getKeyAddressWithKeyPassword)

Description: Returns the crypto currency address base (without checksum or network prefix) of a key in base64 format


Request:
```js
    {
        "label": "Bitcoin"
    }
```

Response:
```js
    {
        "address": "O+4DVWuyYakowbj6mvDSqDag3Zo="
    }
```