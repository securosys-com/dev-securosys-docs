---
sidebar_position: 1
title: Getting Started with Microsoft HGS & Security HSMs
sidebar_label: Quickstart
description: Getting Started with Microsoft Host Guardian Service with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Getting Started with Microsoft Host Guardian Service

This section provides a comprehensive guide outlining the steps necessary to integrate **Microsoft Host Guardian Service** with Securosys [CloudHSM](/cloudhsm/overview/) or on-premises Primus HSM.

## Install Host Guardian Service

Before installing the Host Guardian Service please check the [prerequisites](/ms-hgs/Installation/prerequisites)

Follow the [installation](./Installation/Installation.md) guide for an example installation.

:::tip
For more information about installing the Host Guardian Service on your Windows server, please see [Microsoft online documentation](https://learn.microsoft.com/en-us/windows-server/security/guarded-fabric-shielded-vm/guarded-fabric-manage-hgs).
:::

## Install the Primus CNG/KSP Provider

Refer to the [Primus MS CNG Provider documentation](/mscng/overview) on how to download, install and configure the Primus CNG/KSP Provider on your Host Guardian Service server.

:::note
Ensure the CNG API is licensed and activated on your HSM device.
:::

## Create CSRs and Obtain Certificates

For the HGS to operate, certificates must be initialized with it.
    - Prepare the CSR sample `request.inf` file,
    - Create CSRs for **signing** and **encryption** and have them signed by a verified CA or self-signed (see [MS AD CS](/ms-pki-adcs/overview) for more information on self-signed CAs)
    - Initialize the HGS with the signed certificates.

For more details see chapter [Installation - Certificates](/ms-hgs/Installation/certificate).
 
## Configuring HGS

After initializing the HGS with the certificates, it should be configured.
    - Configure DNS forwarding to your HGS and fabric domains,
    - Configure Domain Trust.

For more details see chapter [Installation - Configuration](/ms-hgs/Installation/configuration).
