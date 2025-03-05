---
sidebar_position: 1
title: Getting Started with Microsoft SignTool & Security HSMs
sidebar_label: Quickstart
description: Getting Started with Microsoft SignTool with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Getting Started with Microsoft SignTool

This section provides a comprehensive guide outlining the 3 steps necessary to integrate **Microsoft SignTool** with Securosys [CloudHSM](/cloudhsm/overview/) or on-premises Primus HSM.

## 1. Install the Primus CNG/KSP Provider

Refer to the [Primus MS CNG Provider documentation](../../mscng/overview) on how to download, install and configure the Primus CNG/KSP Provider.

:::note
Ensure the CNG API is licensed and activated on your HSM device.
:::


## 2. Install new Signing key & Certificate

- [x] Check the [prerequisites](/ms-signtool/Installation/Requirements)
- [x] Complete the [installation](/ms-signtool/Installation/Installation.md).
    - Prepare the signing key request sample `request.inf` file,
    - generate the signing key and self-signed certificate with the previously created request file,
    - generate the signing key and public signed certificate with the request file,
    - validate your signing certificate.

## 3. Start Signing Files

Now you are ready to sign your files or timestamp codes, certificates, etc. using the Microsoft `signtool.exe`.

Follow the tutorial to [sign files with Microsoft SignTool](/ms-signtool/Tutorials/Signing-files.md) and:
- Sign an example application,
- Verify the signed application (file) either via CLI or GUI.