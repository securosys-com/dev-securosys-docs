---
sidebar_position: 3
title: HSM Connection Parameters with Microsoft CNG
sidebar_label: HSM Connection Info
description: Default Connection Parameters for Microsoft CNG and Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Default HSM Connection Parameters

The following table shows the default configuration values for Security Hardware Security Modules (HSMs), whether deployed in the cloud or on-premises, unless specified otherwise:


|                       | **Hostname**           | **Port Number**        | **Using Proxy** |
| ---                   | ---                    | ---                    | --- |
| **Standard Hardware Security Module (HSM)**      | _as setup by your Security Office (SO)_ | 2320                   | no    |
| **Developer program** | grimsel.securosys.ch <br/> nufenen.securosys.ch | 2420 <br/> 2421 | no <br/> no |
| **CloudHSM**<br/> [See all the clusters](/connectivity-details/cloudhsm-connectivity-details)        | a-api.cloudshsm.com <br/> b-api.cloudshsm.com   | 2320 <br/>  2320 | yes <br/> yes |