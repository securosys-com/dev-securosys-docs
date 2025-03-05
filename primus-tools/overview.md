---
sidebar_position: 0
title: Primus Tools HSM Integration Guide
sidebar_label: Overview
description: What is Securosys Primus Tools for Hardware Security Modules (HSMs)?
keywords: [hsm, cloud hsm]
grid_title: Securosys Primus Tools
grid_search_tags: [KMS, "JCE/JCA", "Software Signing"]
grid_description: Java-based toolkit for managing cryptographic objects with Securosys HSMs, simplifying key management, certificate procedures, and more. It supports key generation, modification, migration, and CSR generation, with tools like KeytoolX and JarsignerX for easy
grid_categories: [PrimusTools, "Key Management System"]
grid_tile_logoUrl: '/img/securosys_logo.png'
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Securosys Primus Tools

There are many platform-specific tools to manage HSM key stores and certificate procedures (e.g. OpenSC/pkcs11-tool, p11tool, Java keytool, Windows certutil, etc.). 

**Primus Tools** serves as a versatile Java-based toolkit, designed to seamlessly integrate with your on-premises Primus HSM cluster or CloudHSM. With Primus Tools, complexity is streamlined, ensuring swift and efficient management of cryptographic objects while offering a robust foundation for secure operations.

With ongoing updates and maintenance, Primus Tools ensures optimal compatibility with HSMs, directly addressing the needs of security-conscious enterprises. Setting up the tool is a breeze - a simple process of downloading the `.zip` file, extracting it, and it's ready for immediate use.

The tool's functionality encompasses a broad spectrum of object management commands, allowing for key tasks such as generation, listing, deletion, modification, wrapping, and more. Moreover, it facilitates more intricate operations like key migration, certificate procedures such as certificate signing request generation, and the creation of self-signed certificates. 

Included within Primus Tools are utilities like KeytoolX and JarsignerX, utilities adapted from Sun Oracle's Keytool and Jarsigner, tailored to interact seamlessly with either on-premises Primus HSM or CloudHSM. 

This document describes the Primus Tools commands, their parameters and the use cases for Hardware Security Modules (HSMs), both Primus HSM and [CloudHSM services](/cloudhsm/overview/).


## Target Audience

This document is intended for Securosys Primus HSM or [CloudHSM](/cloudhsm/overview/) administrators and IT professionals familiar with cryptographic object and certificate management.

The snstallation of Primus Tools and JCE provider requires that you are already familiar with java.

For on-premises HSM deployed operation administrative skills are required for Securosys Primus HSMs.

## Support Contact

If you encounter a problem while installing/configuring the provider or
integrating the HSM with Primus Tools, make sure that you have read the
referenced documentation. If you cannot resolve the issue, please
contact Securosys Customer Support. For specific requests regarding
Primus Tools, the Securosys
Support Portal is reachable under https://support.securosys.com.

## Get started with Primus Tools

For a smooth start using Primus Tools:
- Consult the [Quickstart guide](/primus-tools/QuickStart) for a comprehensive task listing.
- For detailed instructions, read and follow the [Installation](/primus-tools/Installation/Prerequisites) chapter.
- For detailed command usage and explanation view the [Tutorials](/primus-tools/Tutorials/command-overview) chapter.
- See examples of [use cases](/primus-tools/Use-Cases/Azure-byok).