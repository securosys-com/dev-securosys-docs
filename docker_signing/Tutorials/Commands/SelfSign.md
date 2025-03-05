---
sidebar_position: 2
title: Self-Signed Certificate Generation for Securosys Docker Signing Notation Plugin
sidebar_label: Self-Signed Certificate
description: Learn how to generate and import a self-signed certificate into the notation trust store using the Securosys Docker Signing Notation Plugin. Includes example inputs for certificate validity and attributes.
toc_min_heading_level: 5
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Self Signed Example
### Securosys Docker Signing _Notation_ Plugin

This command generates a self-sign certificate and automatically import it into notation trustore in the appropriate location.


Command:

```sh
./notation-securosys generate-selfsign-crt
```

Input(example):

```js
{ 

 "keyId": "SecurosysImageSignKey01", 

  "pluginConfig": {}, 

  "certificate": { 

    "validity": 365, 

    "attributes": { 

      "commonName": "DockerImageSign05"

    } 
  } 
}
```