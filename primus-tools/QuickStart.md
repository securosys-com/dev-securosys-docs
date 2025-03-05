---
sidebar_position: 1
title: Getting started with Primus Tools
sidebar_label: Quickstart
description: Get started with Securosys Primus Tools for Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Get started with Primus Tools

The process described in this document allows for maintaining ownership and management over your cryptographic keys and generating them using entropy in a safe environment within the Securosys Primus HSM or CloudHSM as well as maintaining a secure copy for disaster recovery scenarios.

This Quickstart section provides a comprehensive task listing of the Primus Tools installation and configuration process. For more detailed instructions please consult the [Installation](/primus-tools/Installation/Prerequisites) section. Visit [Prerequisites](/primus-tools/Installation/Prerequisites) for the necessary preparations beforehand.

## 1. HSM Setup and Configuration

Setting up the on-premises Primus HSM is not covered in this document. Please refer to the Primus HSM User Manual, downloadable from the [Securosys Support Portal](https://support.securosys.com/external/knowledge-base/article/63). 


The Securosys Primus HSM and CloudHSM need specific policy settings:
- JCE API enabled and appropriate license
- Crypto policy (and User policy) configuration to allow Key Export for the used partition

:::note
The CloudHSM partition already comes preconfigured for use.
:::

## 2. Install and Configure Primus Tools

Download the latest Primus Tools collection from the [Primus Tools Download](/primus-tools/downloads) section. Extract the files in a folder and configure your permanent secret.

For more details on Primus Tools installation see the [Installation](/primus-tools/Installation/Prerequisites) section.

## 3. HSM Access Credentials and connection

Prepare your connectivity parameters and access credentials to be able to use the HSM. Don't forget to fetch the permanent secret.

For more details, see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider).

## 4. Command Overview

See the [Tutorials](/primus-tools/Tutorials/command-overview) section for both a command overview and a deeper dive into Primus Tools commands.