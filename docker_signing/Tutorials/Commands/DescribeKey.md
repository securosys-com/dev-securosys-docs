---
sidebar_position: 1
title: Describe Key Command - Docker Signing Notation
sidebar_label: Describe Key
description: Learn how to use the describe-key command in the Securosys Docker Signing Notation Plugin to retrieve metadata for an existing key or create a new one. Example input and configuration details included.
toc_min_heading_level: 5
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Describe Key Example
### Securosys Docker Signing _Notation_ Plugin

This command is used to get metadata for a given key or, if it not exist, create a new one.

Command:

```sh
./notation-securosys describe-key
```

Input(example):

```js
{ 
 
  "contractVersion" : "1.0", 

  "keyId": "SecurosysImageSignKey01", 

  "pluginConfig" : { 

    "keyType":"RSA-2048", 

    "keyWithPolicy": "No" 

  } 

} 
```