---
sidebar_position: 0
title: Venafi HSM Integration
sidebar_label: Overview
description: Description Overview
keywords: [hsm clusters, cloud hsm, venafi, primus hsm, securosys, tls]
grid_title: Venafi Machine Identity Management
grid_description: Venafi’s TLS Protect and CodeSign Protect enhance machine identity management. Integrate with Securosys CloudHSM for secure key generation, storage, and compliance with top regulatory standards.
grid_search_tags: [PKI, PKCS#11]
grid_categories: [PKCS#11, PKI]
grid_tile_logoUrl: '/img/company_logo/venafi.png'
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Venafi Machine Identity Management

[Venafi](https://venafi.com/)'s [“TLS Protect Datacenter”](https://marketplace.venafi.com/xchange/620d2d6ed419fb06a5c5bd36/solution/64fb179dd839d497bfa350e9) (formerly Trust Protection Platform) and “CodeSign Protect” are leading products in Machine Identity Management (IoT). Integration with Securosys Primus HSM (on-premises) or CloudHSM (HSM as a Service) enables to increase security and comply with strongest regulatory requirements by securely generating, storing, and using the sensitive key materials for machine identities on FIPS 140-2 Level 3 and CC EAL4+ certified Hardware Security Modules.

Integrating **Securosys CloudHSM** or **Primus HSM** with Venafi’s TLS Protect Datacenter provides an array of benefits including:

* Protect identities with
  * Highest-grade secure hardware key storage and protection
  * Securely generate keys from physical true random number generator
* Largest built-in key store, or infinite key store on CloudHSM
* Fast regional access, load-balancing, and automatic redundancy failover, thanks to built-in large geo-redundant HA cluster mechanisms
* Proof digital identities with built-in key attestation and audit features
* Decanus terminal allows for easy and cost-efficient remote management of HSM clusters and CloudHSM partitions (2-of-n, 2FA) without compromising security.
* All HSMs are developed and manufactured in Switzerland using a trusted supply chain and are certified according FIPS140-2 level 3 and CC EAL4+ (EN419221-5) to fulfill strongest compliance regulations.

Securosys CloudHSM is a Hardware Security Module (**HSM**) available as cloud service, without having to worry about time consuming things like evaluation, setup, operation, redundancy, and maintenance of the HSM infrastructure, and is scalable according to your needs. Redundant cluster architecture, providing different redundant regions up to redundant world-wide cluster.

## Additional resources

* For further information on the **Venafi** setup, please go to the [Venfi Advanced Key Protect](https://docs.venafi.com/Docs/current/TopNav/Content/AdvancedKeyProtect/cco-AKP-Venafi-Advanced-Key-Protect.php), [Creating a HSM (Cryptoki) connector](https://docs.venafi.com/Docs/current/TopNav/Content/Configuration/PlatformConfig/t-connector-HSM-create.php), [Hardware Central key generation with Venafi Advanced Key Protect](https://docs.venafi.com/Docs/current/TopNav/Content/AdvancedKeyProtect/c-AKP-central-key-generation.php) pages.
* [Securosys PKCS#11](/pkcs/overview) provides a detailed installation and configuration instructions.
  * Go straight to our [PKCS#11 Download Page](/pkcs/downloads) to begin your integrations.
