---
sidebar_position: 0
title: Prerequisites for Microsoft IIS & HSMs
sidebar_label: 1. Prerequisites
description: Prerequisites for Microsoft Internet Information Services (IIS) & Securosys Hardware Security Modules.
keywords: [hsm clusters, primus hsm, microsoft, iis, securosys]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites
Make sure to adhere to the below listed hardware and software prerequisites before continuing with the IIS configuration.
- Windows Server 2008R2, Windows 7 or higher.
  - Latest testing done on **Windows Server 2022**.
- Microsoft Internet Information Services 7.5 or higher (testing done up to IIS 10)
- Securosys Primus HSM V2.6 or higher or Cloud HSM with CNG/KSP Provider V1.21.4 or higher, installed and configured (refer to [Download](../downloads) page).
  - Latest testing done on **Primus HSM V3.0/Cloud HSM** and **CNG/KSP Provider V1.51.1**.

## Add Windows Web Server IIS Role on Windows Server

For this guide, we assume that you have successfully installed Windows Server. Ensure that the Web Server (IIS) role is added; if not, please download and install it before proceeding.

Download the **Microsoft IIS Manager** via PowerShell (execute with admin privileges).
```powershell
Install-WindowsFeature -name Web-Server -IncludeManagementTools
```

## HSM Setup and Configuration

<Tabs groupId="purchase-channel">
<TabItem value="cloud" label="Cloud" default>

With Securosys CloudHSM, the HSM are already configured, no further action needed.
- [CloudHSM Economy (ECO)](/cloudhsm/Packages/economy)
- [CloudHSM Sandbox (SBX)](/cloudhsm/Packages/sandbox)

</TabItem>
<TabItem value="on-premises" label="On-premises">

Before we proceed with the installation and configuration of the Microsoft AD CS, it is required to configure the Securosys on-premises Primus HSM. 

This guide does not cover the initial setup of the Primus HSM. Follow the procedures outlined in [Primus HSM device setup with wizard](/hsm/Installation/Setup/UserGuide/GetStarted/HSM-Setup-v2-11-1).
Ensure that the Primus HSM is updated to the following firmware: 

- Primus HSM Firmware v2.6 or higher.

You can download the [Securosys Primus HSM firmware](https://support.securosys.com/external/knowledge-base/article/111) from the Securosys Support Portal. 

After completing the initial setup (running the initial wizard), ensure that the HSM has the correct network configuration and can be accessed from the host device. 

</TabItem>
</Tabs>
