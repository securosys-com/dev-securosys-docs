---
sidebar_position: 4
title: Import Certificate - Docker Signing Notation
sidebar_label: Import Certificate
description: Easily import certificates via the Securosys Docker Signing Notation Plugin. The command adds certificates to the notation truststore using Transaction Security Broker (TSB). Includes example inputs for certificate and CA paths.
toc_min_heading_level: 5
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Import Certificate Example
### Securosys Docker Signing _Notation_ Plugin

This command is used to automatically import the certificate via Transaction Security Broker (TSB) to the HSM and add it to the notation truststore in the appropriate place.

Command:

```sh
./notation-securosys import-certificate
```

Input(example):

```js
{ 
 "keyId": "SecurosysCertKey", 

 "pluginConfig" : { 

   "certPath":"/home/securosys/Downloads/Securosys/SecurosysImageSignKey01t.crt",
   "caPath":"/home/securosys/Downloads/Securosys/SecurosysImageSignCA.crt"
 } 
} 
```