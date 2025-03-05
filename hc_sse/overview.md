---
sidebar_position: 0
title: Secrets Engine Plugin for HashiCorp Vault
sidebar_label: Overview
description: Securosys' Secrets Engine Plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
grid_title: Securosys' Secrets Engine
grid_search_tags: [KMS, Rest-API, "Smart Key Attributes"]
grid_description: Enables secure key management and cryptographic operations with a platform-agnostic REST interface using TLS. It simplifies deployment in multi-cloud environments and enhances security with features like hardware-enforced multi-authorization, supporting encryption and compliance workflows.
grid_categories: [HashiCorp Vault, Rest-API, Key Management System]
grid_tile_logoUrl: '/img/company_logo/hashicorp.png'
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Securosys' Secrets Engine

### Plugin for HashiCorp Vault

[Securosys' Secret Engine](./Concepts/secrets_engine_concept.md) is a plugin that integrates **Hardware Security Modules (HSMs)** with Vault, allowing users to generate, manage, and protect secrets using the strong cryptographic capabilities of Securosys' HSMs.

This [plugin](./Concepts/secrets_engine_concept.md) implements a platform-agnostic REST-based HSM interface with zero library installation, while eliminating connectivity hurdles by using secure web connections (TLS). This facilitates the use and deployment in clustered and multi-cloud environments.

Additionally, Securosys HSM innovations, such as hardware-enforced multi-authorization, are available for enhanced security for [HashiCorp Vault Enterprise](../hc-vault-enterprise/overview) and [Community Edition](../hc_vault/overview). 
 - Manage keys securely stored on the Hardware Security Module (HSM)
 - Perform cryptographic operations on the Hardware Security Module (HSM)
 - Use enhanced _Primus HSM_ features such as high-performance encryption (ECIES, AES-GCM), or hardware-enforced multi-authorization workflows for compliance, signature services, or blockchain transactions.

 ## Target Audience

This documentation is intended for Securosys Primus HSM or CloudHSM administrators and DevOps professionals. Running the Securosys Plugins requires that you are already familiar with using Docker Engine, Docker Compose, as well as Notation. 

For on-premises HSM deployed operation administrative skills are required for Securosys Transaction Security Broker (TSB) and Securosys Primus HSMs.


## What's next?

- Read the [QuickStart Guide](/hc_sse/Quickstart/)
- Read the usage [Tutorials](/hc_sse/category/tutorial)
- Read the [Prerequisites](/hc_sse/Installation/prerequisites)
- Read the [official Deployment Guide](/hc_sse/category/installation/)