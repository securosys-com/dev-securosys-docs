---
sidebar_position: 0
title: Prerequisites for Microsoft AD CS & HSMs
sidebar_label: 1. Prerequisites
description: Prerequisites to use Microsoft Active Directory Certificate Services (AD CS) with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites for Microsoft AD CS

- [x] Windows Server 2016 or higher,
- [x] Securosys Primus HSM or [CloudHSM](/cloudhsm/overview/) firmware v2.8.21 or higher,
- [x] Securosys CNG/KSP Provider v1.45.2 or higher installed and configured

## HSM setup and configuration

<Tabs groupId="purchase-channel">
<TabItem value="cloud" label="Cloud" default>

With Securosys CloudHSM, the HSM are already configured, no further action needed.
- [CloudHSM Economy (ECO)](/cloudhsm/Packages/economy)
- [CloudHSM Sandbox (SBX)](/cloudhsm/Packages/sandbox)

</TabItem>
<TabItem value="on-premises" label="On-premises" default>

Before we proceed with the installation and configuration of the Microsoft AD CS, it is required to configure the Securosys on-premises Primus HSM. 

This guide does not cover the initial setup of the Primus HSM. Follow the procedures outlined in [Primus HSM device setup with wizard](/hsm/Installation/Setup/UserGuide/GetStarted/HSM-Setup-v2-11-1).
Ensure that the Primus HSM is updated to the following firmware: 

- Primus HSM Firmware v2.8.43 or higher.

You can download the [Securosys Primus HSM firmware](https://support.securosys.com/external/knowledge-base/article/111) from the Securosys Support Portal. 

After completing the initial setup (running the initial wizard), ensure that the HSM has the correct network configuration and can be accessed from the host device. 

</TabItem>
</Tabs>

Continue to **[install the CNG provider](./Provider-Setup.md)**.