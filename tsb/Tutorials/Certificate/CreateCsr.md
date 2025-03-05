---
sidebar_position: 3
title: Create & download a CSR certificate
sidebar_label: Create CSR Download
description: Endpoint to generate a certificate signing request (CSR) for digital signatures using a hardware security module (HSM).
keywords: [create CSR download, CSR generation, certificate signing request, digital signature, SHA256 with RSA, JSON format CSR, HSM, hardware security module, cybersecurity, data security, key management, cloud HSM, HSM key management, HSM cloud, HSM as a service, cloud based HSM, HSM digital signature, HSM services, HSM service]
---

# Create CSR Download

**POST:** [/v1/certificate/synchronous/request/download](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Certificate/synchronousCreateCertificateRequestFileDownload)

**Description**: Create a certificate request.

Replace `<keyname>` and `password`. The password is optional and can be deleted completely.
```js {2,3,4,5,7-16,19,22}
{
    "signKeyName": "<keyname>",
    "keyPassword": [ "R","E","P","L","A","C","E" ],
    "signatureAlgorithm": "SHA256_WITH_RSA",
    "validity": 365,
    "standardCertificateAttributes": {
      "commonName": "securosys",
      "country": "CH",
      "stateOrProvinceName": "Zurich",
      "locality": "ZH",
      "organizationName": "Securosys SA",
      "organizationUnitName": "Clouds Operations",
      "email": "office@securosys.com",
      "title": "Office",
      "surname": "Office",
      "givenName": "Securosys"
    },
    "keyUsage": [
      "DIGITAL_SIGNATURE"
    ],
    "extendedKeyUsage": [
      "ANY_EXTENDED_KEY_USAGE"
    ]
  }
```

**Response**

:::tip Tip

If you want the CSR in JSON format instead of file-download use the endpoint: POST `/v1/certificate/synchronous/request`

:::

Click the "Download file" link and verify the CSR file was downloaded.


