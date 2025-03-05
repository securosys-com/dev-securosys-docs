---
sidebar_position: 1
title: Prerequisites - Secrets Engine for HashiCorp Vault
sidebar_label: Prerequisites
description: Prerequisites to install Securosy' Secrets Engine plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites

To install and use [Securosys' Secret Engine plugin](../Concepts/secrets_engine_concept.md) for HashiCorp Vault, you will need the following elements.

## 1. Hardware Security Module (HSM)

Depending on your security architecture, you will require the following products/licenses:

<Tabs groupId="purchase-channel">
<TabItem value="cloud" label="Cloud" default>

[Securosys CloudHSM](/cloudhsm/overview/) subscription:

With Multi-Authorization Workflow: 

- Transaction Security Broker (TSB) Server as a Service Economy (ECO), or 
- Transaction Security Broker (TSB) Server as a Service Sandbox (SBX) 

Without Multi-Authorization Workflow: 

- [CloudHSM Economy (ECO)](/cloudhsm/Packages/economy) and CloudHSM RESTful API Economy (ECO), or 
- [CloudHSM Sandbox (SBX)](/cloudhsm/Packages/sandbox) and CloudHSM RESTful API Sandbox (SBX)

:::tip

CloudHSM TSBaaS is included in CloudHSM Economy (ECO) or Sandbox (SBX) services.
:::

---
</TabItem>
<TabItem value="on-premises" label="On-premises">

With Multi-Authorization Workflow: 

- Transaction Security Broker (TSB) Server Software License 
- Securosys Primus HSM with: 
    - Attestation License 
    - [Smart Key Attribute (SKA)](/tsb/Tutorials/TransactionSecurityBroker/smart-key-attributes) License 

Without Multi-Authorization Workflow: 

- Transaction Security Broker (TSB) Server Software License 
- Primus HSM with: 
    - Attestation License

</TabItem>
</Tabs>

## 2. Transaction Security Broker (TSB)

Ensure that Transaction Security Broker (TSB) is installed, configured and updated to version **TSB Software v.1.18.0** or higher. 

<Tabs groupId="purchase-channel">
<TabItem value="cloud" label="Cloud" default>
- [Download Transaction Security Broker (TSB) Software](/tsb/Download/downloads) (account required).

</TabItem>
<TabItem value="on-premises" label="On-premises">
- [How to install and configure TSB on-premises?](../../tsb/Installation/On-Premise-Installation)
- Download [TSB Software](/tsb/Download/downloads) (account required).

Ensure that your Securosys Primus HSM is updated to **Primus HSM Firmware v2.8.21, v2.11 or higher**. 
- Download the [latest Securosys Primus HSM firmware](https://support.securosys.com/external/knowledge-base/article/111) (account required)

:::info Primus HSM initial setup

For the initial setup of your Securoys Primus HSM:
- please follow the procedures outlined in [this section](../../hsm/Installation/Setup/UserGuide/GetStarted/HSM-Setup-v2-11-1).
- Ensure that the settings align with the Transaction Security Broker (TSB) requirements specified in [this section](../../tsb/Installation/hsm-device-setup-tsb).

:::

</TabItem>
</Tabs>

## 3. Docker Engine

To use Securosys's Secret Engin plugin for HashiCorp Vault, Docker Engine must be installed and running on your system. Install Docker Engine on:

- [Linux Ubuntu 22 (amd 64)](https://docs.docker.com/engine/install/ubuntu/)
- [Other operating systems](https://docs.docker.com/get-docker/).

:::warning Warning

In this guide, we will use **Linux Ubuntu 22 (amd 64)**. For other operating systems and Linux distributions, please refer to the referenced guides.

:::

 
## Support Contacts

If you encounter a problem while installing/configuring the provider or integrating the HSM with the Securosys Secrets Engine plugin, make sure that you have read the referenced documentation. If you cannot resolve the issue, please contact Securosys Customer Support. For specific requests regarding Securosys Secrets Engine plugin, the Securosys Support Portal is reachable under [link](https://support.securosys.com/).  

---