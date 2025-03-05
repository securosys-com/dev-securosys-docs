---
sidebar_position: 5
title: Additional Commands - Docker Signing Notation
sidebar_label: Additional Commands
description: Explore additional commands for the Securosys Docker Signing Notation Plugin, including connection tests and plugin metadata retrieval. Learn how to check connection status and access plugin version details with sample outputs.
toc_min_heading_level: 5
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Additional Commands
### Securosys Docker Signing _Notation_ Plugin

Here are the commands supported by the plugin that may be useful.

## Test connection

Use this command to see if the connection by the plugin to the Securosys [CloudHSM](/cloudhsm/overview/) / Primus HSM via Transaction Security Broker (TSB) can be established and to obtain statistic, with sample output: 

```sh
./notation-securosys check-connection 
```

```js
user SECUROSYS, private keys 1, public keys 1, secret keys 0, certificates 1, data objects 0, invalidated keys 0, used size 4868 bytes, max size 1048576 bytes" 
```

---

## Plugin metadata 

Use this command to see the plugin version and metadata, with a sample output: 

```sh
./notation-securosys get-plugin-metadata 
```

```js
{"name":"securosys","description":"Securosys HSM provider for notation","version":"1.3.1","url":"https://www.securosys.com/","supportedContractVersions":["1.0"],"capabilities":["SIGNATURE_GENERATOR.RAW"]}
```