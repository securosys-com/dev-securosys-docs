---
sidebar_position: 0
title: Prerequisites to install Docker Encryption Plugin
sidebar_label: 1. Prerequisites
description: Prerequisites to install Docker Encryption
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';


# Prerequisites

:::tip Caution

In this guide, we will use the **Linux Ubuntu 22 (amd 64)**. For other operating systems and Linux distributions, please refer to the referenced guides.

:::

Before starting the process of integrating the Securosys [CloudHSM](../../cloudhsm/overview) or on-premises Primus HSM with the Securosys Docker Image Encryption Plugin, please meet all the necessary prerequisites listed here. Also, ensure you have access to the [Securosys Support Portal](https://support.securosys.com/external). 

:::danger Caution

Skopeo does **not** support Windows operating systems. For more information on supported operating systems for Skopeo, please visit: [Skopeo Github project](https://github.com/containers/skopeo/blob/main/install.md)

:::

## Installed and configured Transaction Security Broker (TSB)

Ensure that you have access to [Transaction Security Broker](../../tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker), installed, configured and and updated to: 

- TSB Software v.1.16.1 or higher. 

TSB is available both as on-premises or as-a-service with [CloudHSM](../../cloudhsm/overview). For more information on how to configure and install TSB on-premises, follow [Securosys TSB On-Prem Installation Guide](/tsb/Installation/On-Premise-Installation).

You can download the [Securosys TSB Software ](https://support.securosys.com/external/knowledge-base/article/114)from the Securosys Support Portal. 

## Configured Securosys Primus HSM

If you have configured the TSB with on-premises Primus HSM security architecture, ensure that the Primus HSM is updated to the following firmware: 

- Primus HSM Firmware v2.8.21, v2.11 or higher. 

You can download the [Securosys Primus HSM firmware](https://support.securosys.com/external/knowledge-base/article/111) from the Securosys Support Portal (account required). 

In the [CloudHSM Economy (ECO)](../../cloudhsm/Packages/economy) and [Sandbox (SBX)](../../cloudhsm/Packages/sandbox) services, this requirement is met and therefore no additional action is required.

:::danger CAUTION

The guide does not cover the initial setup of the Primus HSM. Follow the procedures outlined in [Primus HSM device setup with wizard 2.11+](../../hsm/Installation/Setup/UserGuide/GetStarted/HSM-Setup-v2-11-1). Ensure that the settings align with the TSB requirements as specified in [Primus HSM device configuration for TSB](../../tsb/Installation/hsm-device-setup-tsb).

:::

## Required Licenses from Securosys

According to your security architecture, you will require the following licenses: 

<Tabs groupId="architecture">
<TabItem value="on-premises" label="On-premises HSM" default>

- With Multi-Authorization Workflow: 
  - TSB Server Software License 
  - Primus HSM with: 
    -  Attestation License 
    - [Smart Key Attributes (SKA)](/tsb/Tutorials/TransactionSecurityBroker/smart-key-attributes) License 
- Without Multi-Authorization Workflow: 
  - TSB Server Software License 
  - Primus HSM with: 
    - Attestation License 

</TabItem>

<TabItem value="cloud" label="Cloud HSM" default>

- With Multi-Authorization Workflow: 
  - TSB Server as a Service ECO, or 
  - TSB Server as a Service SBX 
- Without Multi-Authorization Workflow: 
- [CloudHSM Economy (ECO)](../../cloudhsm/Packages/economy) and CloudHSM RESTful API ECO, or
- [CloudHSM Sandbox (SBX)](../../cloudhsm/Packages/sandbox) and CloudHSM RESTful API SBX

:::tip TIP

CloudHSM TSBaaS is bound to [CloudHSM Economy (ECO)](../../cloudhsm/Packages/economy) or [Sandbox (SBX)](../../cloudhsm/Packages/sandbox) services.

:::

</TabItem>
</Tabs>


## Docker installation

Before proceeding, ensure that Docker is installed and running on your system. 

If Docker is not yet installed, follow the [Install Docker Engine on Ubuntu](https://docs.docker.com/engine/install/ubuntu/) guide. 

For other operating system docker installations please see [Get Docker](https://docs.docker.com/get-docker/).

:::danger Warning
 
On some occasions, commands may require root permissions. Your system and Docker user permissions should be configured beforehand to avoid any potential permission issues.
 
:::
