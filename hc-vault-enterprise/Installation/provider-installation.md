---
sidebar_position: 1
title: Installing PKCS#11 for HC Vault Enterprise
sidebar_label: Install PKCS#11
description: Primus HSM PKCS#11 Provider
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

#  Primus HSM PKCS#11 Provider

To install and configure Primus HSM PKCS#11 Provider on the **HashiCorp Vault Enterprise** machines:

1. [Download it](../downloads.md)
2. Follow the [installation process](../../pkcs/Installation/pkcs11_provider_installation)

:::tip Alternative configuration file locations
Consult [this guide](../../pkcs/Installation/pkcs11_provider_configuration) for alternative configuration file locations.
:::

## Hardware Security Module (HSM) Connectivity

Ensure that you have HSM connectivity using the command `ppin --test`, and note the slot `id` (to be used in Vault configuration).

Example:
```
ppin --test

Load config file: '/etc/primus/primus.cfg'

hsm0: Connect to a-api.cloudshsm.com port 2310, firmware: RX-2.10.0-T
          slot0 (id=0), user=MYVAULT: OK
          slot1 (id=3), user=TESTPARTITION: CKR_TOKEN_NOT_PRESENT
…
```

:::info Troubleshooting

For PKCS#11 troubleshooting, see [this section](../../pkcs/Tutorials/troubleshooting).
:::