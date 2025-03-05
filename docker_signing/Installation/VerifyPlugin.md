---
sidebar_position: 6
title: Verifying the Docker Signing Plugin Setup
sidebar_label: 5. Verify Setup
description: Verifying the Docker Signing Plugin Setup
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Plugin Setup Verifications

Upon configuring the plugin's configuration file, ensure that:
- the plugin is listed as **available** within _Notation_ 
- a successful connection to the CloudHSM/Primus HSM can be established

## List Notation plugins

To verify that the _Notation_ plugin is available, execute the following command:

```sh
notation plugin list
```

Example output of successfully listed Securosys plugin:

```js
NAME        DESCRIPTION                           VERSION   CAPABILITIES                ERROR 

securosys   Securosys HSM provider for notation   1.4.0     [SIGNATURE_GENERATOR.RAW]   <nil>
```

## Test the HSM connectivity

The connectivity with your [CloudHSM](/cloudhsm/overview/) or Primus HSM can be tested using the command below. 
Upon successful communication with the HSM, a statistics report will be displayed:

```sh
./notation-securosys check-connection
```

Example output of successfully listed Securosys plugin:

```js
user SECUROSYS, private keys 0, public keys 0, secret keys 0, certificates 0, data objects 0, invalidated keys 0, used size 0 bytes, max size 1048576 bytes"
```