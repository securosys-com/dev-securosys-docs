---
sidebar_position: 0
title: HashiCorp Vault Enterprise - HSM Integration
sidebar_label: Overview
description: Overview HashiCorp Vault Enterprise with HSM
keywords: [hsm, cloud hsm]
grid_title: HashiCorp Vault Enterprise
grid_search_tags: [KMS, PKCS#11]
grid_description: Enabling secure master key wrapping, automatic unsealing, seal wrapping, and entropy augmentation. This integration ensures compliance with regulatory requirements while enhancing security through the use of hardware-based key management.
grid_categories: [HashiCorp Vault, PKCS#11, Key Management System]
grid_tile_logoUrl: '/img/company_logo/Vault-Enterprise_onLight.svg'
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# HashiCorp Vault Entreprise & Hardware Security Module (HSM)

This document describes how to easily integrate Securosys [CloudHSM](../../cloudhsm/overview) (HSM as a service) or on-premises Primus HSM cluster with **HashiCorp Vault Enterprise**, enabling the advantages of Automatic Unsealing using the wrapped master key stored on the HSM, and to comply with regulatory requirements. 

HashiCorp Vault Enterprise HSM support allows: 
- Master key wrapping
- Automatic unsealing
- Seal wrapping
- Entropy augmentation

![](./img/HC-vault-E-diagram.png)

## Target Audience

This document is intended for Securosys Primus HSM or [CloudHSM](../../cloudhsm/overview)
administrators and IT professionals in charge of the **HashiCorp Vault Enterprise** administration.

The installation of the Securosys [Primus PKCS#11 Provider](../pkcs/overview) requires that you are already familiar with Windows or Linux administration.

For on-premises HSM deployed operation, administrative skills are required for Securosys Primus HSMs.

## Support Contact

If you encounter a problem while installing/configuring the PKCS#11 provider or
integrating the HSM with the plugins, make sure that you have read the
referenced documentation. If you cannot resolve the issue, please
contact Securosys Customer Support. For specific requests regarding
HashiCorp Vault Enterprise, the Securosys
Support Portal is reachable under https://support.securosys.com.

## What's Next

For a smooth start integrating your **HashiCorp Vault Enterprise** using the [Primus PKCS#11 Provider](../pkcs/overview):
- Consult the [Quickstart guide](./quickstart) for a comprehensive task listing.
- For detailed installation and configuration instructions, follow the [Installation](/hc-vault-enterprise/category/installation) section.
