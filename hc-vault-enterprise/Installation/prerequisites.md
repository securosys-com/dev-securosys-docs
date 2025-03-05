---
sidebar_position: 0
title: Prerequisites for HC Vault Enterprise
sidebar_label: Prerequisites
description: Prerequisites
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites

Before integrating Securosys [CloudHSM](../../cloudhsm/overview) or on-premises Primus HSM with **HashiCorp Vault Enterprise**, please make sure to fulfill all the necessary requirements listed below on a machine with an existing **HashiCorp Vault Enterprise** environment.
- Securosys [PKCS#11 Provider v1.8.6](../../pkcs/overview) or newer,
- Securosys Hardware Security Module (HSM)
  - [CloudHSM](../../cloudhsm/Packages/overview)  (HSM as a Service) or
  - On-premises Primus HSM, firmware v2.8.21 or newer, with PKCS#11 API.


## HashiCorp Vault Enterprise

This guide assumes that HashiCorp Vault with **Vault Enterprise Plus** license is already downloaded and installed on your host machine.

For more details on the installation and configuration, please consult the [HashiCorp Vault Documentation](https://developer.hashicorp.com/vault/docs).

## Hardware Security Module (HSM)

To meet your security and operational needs, you will require a Hardware Security Module (HSM), available either as a cloud service or as an on-premises device.

<Tabs groupId="device-setup">
  <TabItem value="ui" label="Cloud" default>
      [Securosys CloudHSM](../../cloudhsm/overview) offers near-instant Hardware Security Module (HSM) operation by allowing you to select and subscribe to a variety of services and options tailored to your needs.
- [Subscribe online](https://cloud.securosys.com/cloudhsm) (Free trial available)
- [Contact sales](https://www.securosys.com/en/contactus). 
</TabItem>
<TabItem value="cli" label="On-premises" default>
For on-premises **Primus HSM hardware**, including High Availability Cluster setup and operation in FIPS or Common Criteria certified modes:
- Browse the [Primus HSM User Guide](https://support.securosys.com/external/knowledge-base/article/63) (account required)
- or [contact sales](https://www.securosys.com/en/contactus). 

:::tip
Ensure the **PCKS#11 API** is licensed and activated on your HSM device.
:::
  </TabItem>
</Tabs>
