---
sidebar_position: 3
title: Generate CSR - Docker Signing Notation Plugin
sidebar_label: Certificate Signing Request (CSR)
description: Generate a Certificate Signing Request (CSR) using the Securosys Docker Signing Notation Plugin. Includes example inputs for certificate validity and detailed attributes such as common name, organization, and locality.
toc_min_heading_level: 5
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Generate CSR Example
### Securosys Docker Signing _Notation_ Plugin

This command generates a Certificate Signing Request (CSR) and returns it in _output_.
Then you need to manually send the CSR file to the PKI provider and use [import-certificate command](/docker_signing/Tutorials/Commands/ImportCertificate).

Command:

```sh
./notation-securosys generate-csr
```

Input(example):

```js
{ 

 "keyId": "SecurosysImageSignKey02", 

  "pluginConfig": {}, 

  "certificate": { 

    "validity": 365, 

    "attributes": { 

      "commonName": "DockerImageSign05", 

      "country": "CH", 

      "stateOrProvinceName": "Zurich", 

      "locality": "Zurich", 

      "organizationName": "Securosys SA", 

      "organizationUnitName": "IT" 

    } 
  } 
} 
```