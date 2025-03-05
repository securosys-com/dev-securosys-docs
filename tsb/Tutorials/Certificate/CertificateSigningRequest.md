---
sidebar_position: 1
title: Creating Certificate Signing Requests
sidebar_label: 2. CSR Creation
description: Guide on creating a Certificate Signing Request (CSR) using Securosys Primus HSM, including key generation and CSR creation for certificates.
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, CSR, certificate signing request, RSA, SHA256, certificate creation, key generation, Securosys Primus HSM, TSB, JSON Web Tokens, JWT, mTLS, Mutual TLS, eIDAS, qualified digital certificates, OpenSSL verification]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# 2. Certificate Signing Request

## Create key
**POST**: [/v1/key](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

**Description**: Create sub-key request.

<Tabs groupId="device-setup">
  <TabItem value="swagger" label="Swagger" default>
```js {14}
{
    "label": "sub_certificate_key",
    "password": null,
    "algorithm": "RSA",
    "keySize": 4096,
    "attributes": {
        "decrypt": false,
        "sign": true,
        "unwrap": false,        
        "extractable": false,
        "modifiable": true,
        "destroyable": true,        
    },
    "policy": null
}
```
  </TabItem>
  <TabItem value="curl" label="CURL" default>
```sh
curl -X POST -H "X-API-KEY: tsb-x-token_68..." -H "Content-Type: application/json" \
 https://integration-test.cloudshsm.com/v1/key -d '{
    "label": "sub_certificate_key",
    "password": null,
    "algorithm": "RSA",
    "keySize": 4096,
    "attributes": {
        "decrypt": false,
        "sign": true,
        "unwrap": false,                
        "extractable": false,
        "modifiable": true,
        "destroyable": true        
    },
    "policy": null
}'
```
  </TabItem>
</Tabs>



## Create a CSR
**POST**: [/v1/certificate/synchronous/request](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Certificate/signCsr)

**Description**: Creates a certificate signing request (CSR) to be signed by a Root or intermediate-CA.

<Tabs groupId="device-setup">
  <TabItem value="swagger" label="Swagger" default>
```js {4,5,7-12,22,25}
{
    "signKeyName": "sub_certificate_key",
    "keyPassword": null,
    "signatureAlgorithm": "SHA256_WITH_RSA",
    "validity": 365,
    "standardCertificateAttributes": {
        "commonName": "SUB-Securosys-001",
        "country": "CH",    
        "stateOrProvinceName": "Zurich",
        "locality": "Zurich",
        "organizationName": "Securosys SA",
        "organizationUnitName": "Operations",
        "email": null,
        "title": null,
        "surname": null,
        "givenName": null,
        "initials": null,
        "pseudonym": null,
        "generationQualifier": null
    },
    "keyUsage": [
        "DIGITAL_SIGNATURE"
    ],
    "extendedKeyUsage": [
        "ANY_EXTENDED_KEY_USAGE"
    ]
}
```
  </TabItem>
  <TabItem value="curl" label="CURL" default>
```sh
curl -X POST -H "X-API-KEY: tsb-x-token_03..." -H "Content-Type: application/json" \
 https://integration-test.cloudshsm.com/v1/certificate/synchronous/request -d '{
    "signKeyName": "sub_certificate_key",
    "keyPassword": null,
    "signatureAlgorithm": "SHA256_WITH_RSA",
    "validity": 365,
    "standardCertificateAttributes": {
        "commonName": "SUB-Securosys-001",
        "country": "CH",    
        "stateOrProvinceName": "Zurich",
        "locality": "Zurich",
        "organizationName": "Securosys SA",
        "organizationIdentifier": "CHE-464.234.583 MWST",
        "organizationUnitName": "Operations",
    },
    "keyUsage": [
        "DIGITAL_SIGNATURE"
    ],
    "extendedKeyUsage": [
        "ANY_EXTENDED_KEY_USAGE"
    ]
}'
```
  </TabItem>
</Tabs>



**Response**

```js
{
  "label": "sub_certificate_key",
  "certificateSigningRequest": "-----BEGIN NEW CERTIFICATE REQUEST-----\nMIIDEDCCAfgCAQAwdzELMAkGA1UEBhMCQ0gxDzANBgNVBAgTBlp1cmljaDEPMA0G\nA1UEBxMGWnVyaWNoMRUwEwYDVQQKEwxTZWN1cm9zeXMgU0ExEzARBgNVBAsTCk9w\nZXJhdGlvbnMxGjAYBgNVBAMTEVNVQi1TZWN1cm9zeXMtMDAxMIIBIjANBgkqhkiG\n9w0BAQEFAAOCAQ8AMIIBCgKCAQEAozSi0yjNuKIssL/AC7/VHTeUKMcaNPYzxvam\nRjUqAyEf2bs8N3peKCRP737Vlzzow/bN7IQIPdzQViY4ySbu3Hm3k/jCyguEL6+n\nEvtVD8X23x5diJRfP7fkp9Q9HPq6q3Hh2zqfU9Mb9vcyI1LWSymLpDa0whaS8l9r\nu9iJky2wE3ERhwACuaa4MkkyqEszdbq7TG+Tv5ye2vmH3SZfpgND05i/FyaN4KLY\n+jH670tpbdsSl2YQqgfkff+iLm+4du09g8ERPUs7kMhviA0AUsuiRl4mCu/uDZQb\nidIfLA0KHFzhpFWsk2roi398H27jOmQUxTmqV0V2LZFtUYExpQIDAQABoFQwUgYJ\nKoZIhvcNAQkOMUUwQzASBgNVHSUBAf8ECDAGBgRVHSUAMA4GA1UdDwEB/wQEAwIH\ngDAdBgNVHQ4EFgQUMTubYh4IdUuLGiPgzVNP2mevZ1cwDQYJKoZIhvcNAQELBQAD\nggEBABTMNe1DHC96MJoYP3mt5OpuwCxhEswCfnAgfpjsEG8DE2Cw1D1h4+pLbdXv\ncs8wwTlmr5jg/lu8NIyfCZowRO0OrG2hW8tWpTBro8Xj1eGfyDD9WbnkJNJ+1wfL\nmb13BUpVUKVBwFtu0OathJijmbxlkhifrw3b+uNEatwSpRI0jhjK7aAzOIxTPvqQ\nDLdtze9PwjCTICqqmOb3SdO3cgnu5iVWb5Ebv1OM3zHFR23lDiHS1oDPRNbB2b5W\nH3Y3/YEHs+8yLI+pz3pPAUXYZrbTwyfW6+1Zfx5IRqGclC8Ik4C3Wi2PG5SgI3kY\nKkIf5muXuXVAp1g3/PxbNUXfRFo=\n-----END NEW CERTIFICATE REQUEST-----\n"
}
```