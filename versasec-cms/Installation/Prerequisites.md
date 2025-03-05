---
sidebar_position: 0
title: Prerequisites to Versasec vSEC:CMS & HSM
sidebar_label: Prerequisites
description: Prerequisites to install Versasec Credential Management System (CMS) for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites

Please ensure the following requirements are met on the machine with a fully operational Versasec vSEC:CMS. 

- Securosys PKCS#11 Provider v1.8.6 or newer,
requires Visual C++ Redistributable Packages for Visual Studio 2015
- CloudHSM partition (HSM as a Service) or
Primus HSM, firmware v2.8.21 or newer with PKCS#11 API enabled.


## 1. Versasec vSEC:CMS 

A fully operational and licensed Versasec vSEC:CMS is required for this document. Installing and configuring the Versasec vSEC:CMS is not part of this document.

Refer to [Versasec - vSEC:CMS Documentation](https://support.versasec.com/hc/en-us/categories/18439879409554) on how to install and configure the Versasec vSEC:CMS.


## 2. HSM User Configuration

To allow all vSEC:CMS master keys to be copied to the HSM it is necessary to change the Security Configuration of the
User Partition to allow the user to import the keys into the Primus HSM, instead of exclusively
generating them on the HSM.

To change the Key Import Security Parameter to `YES` for the User Configuration, please refer to
the [Primus HSM User Guide](https://support.securosys.com/external/knowledge-base/article/63).

:::note
Your CloudHSM partition comes preconfigured and does not require any additional configuration (subject to subscription).
:::

## 3. HSM Setup and Configuration

<Tabs groupId="device-setup">
  <TabItem value="ui" label="Cloud" default>
      Securosys CloudHSM allows almost instant HSM operation by selecting and contracting the different services and options.
For available service packages and options consult our website [Securosys CloudHSM Service](https://www.securosys.com/en/product/cloudshsm) and contact Securosys sales. 


:::note
Ensure the PKCS#11 API is included and activated in your subscription.
:::

  </TabItem>
  <TabItem value="cli" label="On-premises" default>
      For general on-premises Primus HSM hardware, HA Cluster setup and operation in FIPS or Common Criteria certified modes, refer to the corresponding [Primus HSM User Guide](https://support.securosys.com/external/knowledge-base/article/63) for details.
    
:::note
Ensure the PKCS#11 API is licensed and activated on your HSM device.
:::
  </TabItem>
</Tabs>

