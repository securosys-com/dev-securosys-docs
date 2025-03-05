---
sidebar_position: 0
title: Docker Encryption HSM Integration Guide
sidebar_label: Overview
description: Overview of Docker Encryption HSM Plugin
keywords: [hsm, cloud hsm]
grid_title: Docker Encryption
grid_search_tags: [Docker, Encryption, Rest-API, "Smart Key Attributes"]
grid_description: Ensure the integrity and confidentiality of Docker images with Securosys HSMs for encryption. Securosys Primus HSMs protect private key material, while Securosys plugins for Docker Image Signing and Encryption integrate with HSMs through REST API via the Securosys Transaction Security Broker (TSB). This enables secure key management for Docker images with both on-premises and cloud-based HSM solutions.
grid_categories: [Docker, Rest-API, Encryption]
grid_tile_logoUrl: '/img/company_logo/docker.png'
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Docker Encryption
### HSM Integration Guide

In this guide, we will explore the foundations of **Docker image encryption** and describe the key concepts and benefits. We will delve
into the integration with Securosys products and explain why their
integration into any Docker security ecosystem is critical.

In the ever-evolving landscape of containerization and cloud-native
technologies, Docker has emerged as a cornerstone, revolutionizing how
applications are developed, deployed, and managed. However, as the
adoption of Docker containers continues to skyrocket, so too does the
need for robust security measures. [Docker image signing](/docker_signing/overview) and encryption
have emerged as essential components of this security posture, ensuring
the integrity and confidentiality of containerized applications.

Where there’s signing or encryption, there’s a key. The question of how
that key is handled, managed, and distributed becomes paramount.

[Securosys Primus
HSMs](https://www.securosys.com/en/products/primus-hardware-security-modules-hsm) (*Hardware Security Modules)* are optimized for
the physical protection of private key material and FIPS 140-2 Level 3 and Common Criteria certified.

Securosys plugins for Docker Image Signing and Encryption communicate by
REST API trough Securosys [Transaction Security Broker (TSB)](/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker) with
on-premises Primus HSM or Securosys [CloudHSM](/cloudhsm/overview) – HSM as a Service.

This application note aims to provide guidance on the integration
process of Docker image encryption using “Skopeo” and OCIcrypt with
keys protected in the HSM with on-premises Primus HSM or Securosys
[CloudHSM](/cloudhsm/overview).

## Target Audience

This document is intended for Securosys Primus HSM or [CloudHSM](/cloudhsm/overview)
administrators and DevOps professionals. Running the Securosys Docker Encryption Plugin
requires that you are already familiar with using Docker Engine, Docker
Compose, as well as Skopeo.

For on-premises HSM deployed operation administrative skills are
required for Securosys [Transaction Security Broker (TSB)](/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker) and Securosys
Primus HSMs.

## Support Contact

If you encounter a problem while installing/configuring the provider or
integrating the HSM with the plugins, make sure that you have read the
referenced documentation. If you cannot resolve the issue, please
contact Securosys Customer Support. For specific requests regarding
Securosys Docker Image Signing and Encryption plugins, the Securosys
Support Portal is reachable under https://support.securosys.com.

## What's Next

For a smooth start with the **Docker Image Encryption** plugin:
- Consult the [Quick Start Guide](/docker_encryption/Quickstart/quickstart.md) for a comprehensive task listing.
- For detailed instructions, read and follow the [Securosys Docker Image Encryption plugin for Skopeo - Installation guide](./Installation/prerequisites).
- Initiate your first image processing by reading and following the [Tutorials on Encrypting and Decrypting Docker Images](./Tutorials/encrypt).
- Delve into the [Concepts](./Concepts/concepts.md) section for additional background information.
