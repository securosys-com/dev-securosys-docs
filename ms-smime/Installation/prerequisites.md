---
sidebar_position: 0
title: Prerequisites for Microsoft S/MIME & Security HSMs
sidebar_label: 1. Prerequisites
description: Prerequisites to install Microsoft S/MIME with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites

Please ensure the following requirements are met:
- Windows operating system:
    - Windows Server 2019 or higher,
    - Windows 11 and 10.
- Securosys Primus-X HSM V2.8.54 or later,
- Securosys CNG/KSP Provider V1.45.2 or later,
- Windows integrated tools (certreq.exe, certutil.exe),
- Microsoft Outlook 2019 or higher,
- [Microsoft .NET framework](https://learn.microsoft.com/en-us/dotnet/framework/get-started/overview) 6 or later,
- Securosys Hardware Security Modules (HSMs):
  - on-premise Primus HSM with CNG/KSP API license, or 
  - [CloudHSM](/cloudhsm/overview/) with CNG/KSP API license.

:::info
The host computers which will have the Microsoft S/MIME configured must be a part of a domain.
:::

## Securosys Hardware Security Module (HSM)

**Microsoft S/MIME** is compatible with Securosys HSMs, both on-premises and in the cloud.

<Tabs groupId="device-setup">
  <TabItem value="ui" label="Cloud" default>
      **Securosys CloudHSM** allows almost instant HSM operation by selecting and contracting the different services and options.
      - [Getting started with CloudHSM](/cloudhsm/category/getting-started)
      - [Subscribe to CloudHSM online](https://cloud.securosys.com/cloudhsm) (free trial available)
      - Contact [Securosys Sales](https://www.securosys.com/en/contact). 

  </TabItem>
  <TabItem value="cli" label="On-premises" default>
      For general on-premises Primus HSM hardware, High-Availability Cluster setup and operation in FIPS or Common Criteria certified modes, refer to the corresponding [User Guide](https://support.securosys.com/external/knowledge-base/article/63)(account required).

      - Contact [Securosys Sales](https://www.securosys.com/en/contact). 

  </TabItem>
</Tabs>


