---
sidebar_position: 1
title: Getting Started with Microsoft SQL Always Encrypted & HSM
sidebar_label: Quickstart
description: Microsoft SQL Always Encrypted with Securosys Hardware Security Modules (HSMs)
keywords: [cloud hsm, hsm key management, hsm cloud]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Getting Started with Microsoft SQL AE

**Microsoft SQL Server Always Encrypted** is a security feature designed to protect sensitive data stored in SQL Server databases. It allows clients to encrypt sensitive data within client applications, ensuring that encryption keys are never revealed to the Database Engine. This separation of access prevents unauthorized users, such as on-premises database administrators, from viewing sensitive information.

This quickstart section provides a comprehensive guide outlining the steps necessary to integrate Microsoft SQL Server Always Encrypted with Securosys Hardware Security Modules, cloud and on-premises.

## 1. Prerequisites

Before starting the process of integrating the Securosys CloudHSM or on-premises Primus HSM with Microsoft SQL Server - Always Encrypted please ensure your Primus HSM is already configured and that MS SQL Server is already installed and configured with a database created.

:::note
Your CloudHSM partition comes preconfigured for use with MS SQL Server (subject to appropriate licensing).
:::

For more details visit the [Prerequisites](/ms-sql-ae/Installation/prerequisites) page.

## 2. Primus CNG/KSP Provider

Refer to the [Primus MS CNG Provider documentation](/mscng/overview) on how to download, install and configure the Primus CNG/KSP Provider.

:::note
Ensure that the CNG API is both licensed and activated on your HSM device or within your CloudHSM subscription.
:::

## 3. Creating Column Master Key 

Create a Column Master Key (CMK) using either CloudHSM or on-premises Primus HSM. This key will encrypt all subsequent Column Encryption Keys (CEK). 

:::info
Both Windows GUI and CLI can be used to create a CMK.
:::

For more details visit the [Creating Column Master Key](/ms-sql-ae/Installation/creating-cmk) page.

## 4. Creating Column Encryption Key

There are two approaches to generate the Column Encryption Key (CEK) in this document we describe the procedure of defining the CEKs explicitly within the `Security` folder where the the CMK was defined, having the advantage to name the CEKs manually.

:::info
Both Windows GUI and CLI can be used to create the CEKs.
:::

For more details visit the [Creating Column Encryption Key](/ms-sql-ae/Installation/creating-cek) page.

## 5. Enabling Column Encryption on the Database Table

Enable Column Encryption on the database table using the previously generated CMK and CEKs.

:::info
Both GUI and CLI can be used to enable Column Encryption.
:::

For more details visit the [Enable Column Encryption on the Database Table](/ms-sql-ae/Installation/enabling-column-encryption) page.

## 6. Decrypt a Column Encrypted Database

Additional database connection parameters are required to show encrypted columns in plaintext.

For more details visit the [Connecting to Column Encrypted Database](/ms-sql-ae/Tutorials/connecting-column-encrypted-db)

## 7. Concepts

For more information about Always Encrypted and references used in this document visit the [Concepts](/ms-sql-ae/Concepts/always_encrypted) section.

