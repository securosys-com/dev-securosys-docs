---
sidebar_position: 1
title: Docker Signing Prerequisites & HSM Setup Guide
sidebar_label: 1. Prerequisites
description: Ensure a smooth setup of the Securosys Docker Signing Plugin with required prerequisites, licenses, and TSB configurations. Follow installation instructions for Docker, Primus HSM, and CloudHSM.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites
### HSM Integration guide

:::tip Reminder

This symbol contains helpful or important information for setting up **Securosys Docker Signing Plugin**

:::

:::danger Take care

This symbol means to be careful and obey all instructions. You might do something that could result in data loss

:::

Feature or action requires role activation using

- Genesis Card ![](../img/TSB/genesis_card.png)
- Security Officer (SO) Cards 2 of n ![](../img/TSB/so_card.png)

---
 
## Transaction Security Broker (TSB)

Ensure that [Transaction Security Broker (TSB)](/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker) is installed, configured and updated to: 

- TSB Software v.1.18.0 or higher. 

Transaction Security Broker (TSB) is available both as on-premises or as-a-service with [CloudHSM](../../cloudhsm/overview). For more information on how to configure and install TSB on-premises, follow [this guide](../../tsb/Installation/On-Premise-Installation). 

You can download the **Transaction Security Broker (TSB)** from the [Securosys Support Portal.](https://support.securosys.com/external/knowledge-base/article/114) (login required).

## HSM Configuration

<Tabs groupId="securosyshsm">
 
  <TabItem value="on-prem" label="On-Premises" default>
    If you have configured the TSB with on-premises Primus HSM security architecture, ensure that the Primus HSM is updated to the following firmware: 
    - Primus HSM Firmware v2.8.21, v2.11 or higher. 
    You can download the [Securosys Primus HSM firmware](https://support.securosys.com/external/knowledge-base/article/111) from the Securosys Support Portal (login required). 

:::tip CAUTION

The guide does not cover the initial setup of the Primus HSM. Follow the procedures outlined [here](/hsm/Installation/Setup/UserGuide/GetStarted/HSM-Setup-v2-11-1). Ensure that the settings align with the Transaction Security Broker (TSB) requirements as specified [here](/tsb/Installation/hsm-device-setup-tsb).

:::
  </TabItem>
    <TabItem value="cloud" label="Cloud">

[CloudHSM Economy (ECO)](/cloudhsm/Packages/economy) and [Sandbox (SBX)](/cloudhsm/Packages/sandbox) are already pre-configured for Docker Signing.

No further action needed.
  </TabItem>
</Tabs>

---

## Securosys Licences

Depending on your security architecture, you will require the following licenses:

<Tabs groupId="securosyshsm">
 
  <TabItem value="on-prem" label="On-Premises" default>
    Primus HSM:

    **With** Multi-Authorization Workflow: 
    - Transaction Security Broker (TSB) Server Software License 
    - Primus HSM with: 
        - Attestation License 
        - Smart Key Attribute (SKA) License 
    
    **Without** Multi-Authorization Workflow: 
    - Transaction Security Broker (TSB) Server Software License 
    - Primus HSM with: 
    - Attestation License
  </TabItem>
    <TabItem value="cloud" label="Cloud">

    **CloudHSM subscription**
    
    (CloudHSM TSBaaS is bound to CloudHSM ECO or SBX partition)
    
    **With** Multi-Authorization Workflow: 
        - Transaction Security Broker (TSB) Server as a Service Economy (ECO), **or** 
        - Transaction Security Broker (TSB) Server as a Service Sandbox (SBX) 
    
    **Without** Multi-Authorization Workflow: 
        - [CloudHSM Economy (ECO)](../../cloudhsm/Packages/economy) and CloudHSM RESTful API ECO, **or** 
        - [CloudHSM Sandbox (SBX)](../../cloudhsm/Packages/sandbox) and CloudHSM RESTful API SBX
  </TabItem>
</Tabs>


---

## Docker installation

Before proceeding, ensure that **Docker** is installed and running on your system.

:::danger Warning

In this guide, we will use the _Linux Ubuntu 22 (amd 64)_, for other operating systems and Linux distributions, please refer to the referenced guides.

:::

If Docker is not yet installed, please follow the Linux Ubuntu [installation guide](https://docs.docker.com/engine/install/ubuntu/).

For other operating system docker installations, please see [this guide](https://docs.docker.com/get-docker/).

---


## Support Contacts

If you encounter a problem while installing/configuring the provider or integrating the HSM with the [Securosys Docker Signing Notation plugin](../Concepts/DockerSigningConcept.md), make sure that you have read the referenced documentation. If you cannot resolve the issue, please contact Securosys Customer Support.

For more specific requests regarding [Securosys Docker Signing Notation plugin](../Concepts/DockerSigningConcept.md), please use the Securosys [Support Portal](https://support.securosys.com/).  

## Abbreviations

| **Acronym** | **Definition** |
| --- | --- |
| **API** | Application Programming Interface |
| **CA** | Certificate Authority |
| **CISO** | Chief Information Security Officer |
| **CLI** | Command Line Interface |
| **CloudHSM** | HSM as a service, operated by Securosys. [View more](../../cloudhsm/overview)|
| **ECC** | Elliptic-Curve Cryptography |
| **FIPS 140-2** | Federal Information Processing Standard 140-2 |
| **FW** | Firmware |
| **HA** | High Availability |
| **HSM** | Hardware Security Module (physical or as a service) |
| **mTLS** | Mutual Transport Layer Security |
| **RSA** | Rivest-Shamir-Adleman asymmetric encryption algorithm |
| **SBX** | CloudHSM HSM as a Service SBX (Sandbox) offering, pre-production/test environment. [View more](../../cloudhsm/Packages/sandbox) |
| **TSB** | Transaction Security Broker (TSB), middleware to Primus HSM / CloudHSM providing REST API and multi-authorization (approval) workflow engine. TSB can be operated as workflow engine or solely as REST API, depending on Primus HSM or CloudHSM license / subscription |
| **ECO** | CloudHSM HSM as a Service ECO (Economy) offering, production environment. [View more](../../cloudhsm/Packages/economy) |
<br />