---
sidebar_position: 0
title: Prerequisites for Securden Unified PAM
sidebar_label: Prerequisites
description: Prerequisites to install Securden Unified Privileged Access Management (PAM) for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites

Before starting the process of integrating the Securosys CloudHSM or on-premises Primus HSM with Securden Privileged Access Manager (Self-Hosted), please make sure to fulfill all the necessary requirements listed below:
- Existing Securden Installation in your environment
- Securosys PKCS#11 Provider v1.8.6 or newer installed on Securden device(s)
- Securosys CloudHSM Service (HSM as a Service) or <br/>
Securosys Primus HSM, firmware v2.8.22, v2.11.3, v3.0.8 or newer <br/>
with PKCS#11 API and Session Object support enabled.

## Securden Unified Privileged Access Management 

This guide assumes that Securden Unified Privileged Access Management  is already downloaded and installed.
- Successfully executed Securden Unified Privileged Access Management environment pre-install tasks for using the Securosys HSM,

Please consult the [Securden Unified PAM Administrator Guide](/securden-pam/Installation/secpam-integration.md) for more details on the installation and configuration of Securden Unified PAM.

## CloudHSM or Primus HSM Setup and Configuration

Installing and configuring the CloudHSM partition or on-premises Primus HSM is not part of this document.

<Tabs groupId="device-setup">
  <TabItem value="ui" label="Securosys CloudHSM" default>
      Securosys CloudHSM allows almost instant HSM operation by selecting and contracting the different services and options.
For available service packages and options consult our website [Securosys CloudHSM Service](https://www.securosys.com/en/product/cloudshsm) and contact Securosys sales. 

:::note
Ensure the PKCS#11 API is included and activated in your subscription.
:::

  </TabItem>
  <TabItem value="cli" label="Securosys Primus HSM (on-premises)" default>
      For general on-premises Primus HSM hardware, HA Cluster setup and operation in FIPS or Common Criteria certified modes, refer to the corresponding [Primus HSM User Guide](https://support.securosys.com/external/knowledge-base/article/63) for details.
    
:::note
Ensure the PKCS#11 API is licensed and activated on your HSM device.
:::
  </TabItem>
</Tabs>
