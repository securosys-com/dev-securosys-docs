---
sidebar_position: 3
title: Troubleshoot HSM Connectivity with CyberArk
sidebar_label: Troubleshooting
description: Ttroubleshoot HSM connectivity issues with CyberArk PAM. Use tools like ppin to test connections, list partitions, and resolve HSM-related problems in CloudHSM or on-premises setups.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Troubleshooting HSM Connectivity

## Test the HSM Connectivity

Now that you have established the connection with your CloudHSM or on-premises Primus HSM partition, you can utilize the ppin tool to verify your connectivity with the HSM by executing the following command:

```sh
ppin --test
Load config file: 'C:\Program Files\Securosys\Primus P11\primus.cfg'

hsm0: Connect to a-api.cloudshsm.com port 2310, firmware: RX-2.20.0-T
          slot0 (id=0), user=DEMO_PARTITION: OK
          slot1 (id=1), user=DEMO_PARTITION2: CKR_TOKEN_NOT_PRESENT
…
```

## Listing Partitions

Multiple different partitions are assigned to PKCS#11 slot ids and can be listed by the **`ppin`** tool:
```sh
ppin -l

********************
Primus Permanent PIN
********************
[01] slot-id 0:    user 'DEMO_PARTITION'     permanent secret: Configured
[02] slot-id 1:    user 'DEMO_PARTITION2'     permanent secret: Configured
[03] slot-id 5:    user 'CLOUDSHSMPAR'  permanent secret: MISSING
[01] service/proxy user 'PROXY_USER'  permanent secret: MISSING
```

:::tip
Find more helpful information in the [Primus PKCS#11 section](/pkcs/Tutorials/troubleshooting).
:::