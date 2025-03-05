---
sidebar_position: 0
title: Prerequisites for Integrating CyberArk with HSM
sidebar_label: Prerequisites
description: Learn the prerequisites for integrating Securosys CloudHSM or on-premises Primus HSM with CyberArk Privileged Access Manager, including setup and configuration details.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites
### HSM integration guide

Before starting the process of integrating the Securosys CloudHSM or on-premises Primus HSM with CyberArk Privileged Access Manager – Self-Hosted, please make sure to fulfill all the necessary requirements listed below:
- Existing CyberArk Primary-DR environment. 
- Securosys PKCS#11 Provider v1.8.6 or newer
- Securosys CloudHSM Service (HSM as a Service) or <br />
    Securosys Primus HSM, firmware v2.8.21, v2.10.5 or newer <br />
    with PKCS#11 API and Session Object support enabled.


## Installing CyberArk Privileged Access Manager

This guide assumes that CyberArk PAM is already downloaded and installed.
- Successfully executed CyberArk Primary-DR environment pre-install tasks for using the Securosys HSM,
- The recovery private key (**`recprv.key`**) must be available. The recovery private key is used when a key to a Safe, encrypted with an external key, is forgotten.

Please consult the [CyberArk Privileged Access Manager](https://docs.cyberark.com/Product-Doc/OnlineHelp/PAS/12.6/en/Content/Resources/_TopNav/cc_Home.htm) for more details on the installation and configuration of CyberArk PAM.

## HSM Setup and Configuration

:::tip API Provider
Ensure the **PCKS#11 API** is included and activated in you subscription.
:::

<Tabs>
  <TabItem value="cloud" label="Cloud" default>
    
    [Securosys CloudHSM](/cloudhsm/overview/) is pre-configured and allows instant HSM operation for CyberArk Digital Vault:
    - [Subscribe online or try for free](https://cloud.securosys.com/cloudhsm), or
    - [Contact Sales](https://www.securosys.com/en/product/cloudshsm)

    Securosys CloudHSM is a Hardware Security Module (HSM) available as cloud service, without having to worry about time consuming things like evaluation, setup, operation, redundancy, and maintenance of the HSM in-frastructure, and is scalable according to your needs. The redundant cluster architecture, providing different redundant regions up to redundant world-wide cluster, fits perfectly in CyberArk’s Vaulting Technology®.

  </TabItem>
  
  <TabItem value="on-prem" label="On-premises">

    Consult [Primus HSM PKCS#11 Provider User Guide - Primus HSM Configuration](https://docs.securosys.ch/pkcs/Installation/primus_hsm_settings) to setup the Primus HSM for PKCS#11 usage.

    For further details on on-premises Primus HSM hardware, HA Cluster setup and operation in FIPS or Common Criteria certified modes, refer to the corresponding [Primus HSM User Guide](https://support.securosys.com/external/knowledge-base/article/63) (login required).

  </TabItem>
</Tabs>



