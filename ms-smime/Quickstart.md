---
sidebar_position: 1
title: Getting Started with Microsoft S/MIME & Security HSMs
sidebar_label: Quickstart
description: Getting Started with Microsoft S/MIME with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Getting Started with Microsoft S/MIME
### HSM Integration guide

This section provides a comprehensive guide outlining the 3 major steps necessary to integrate **Microsoft S/MIME** with Securosys [CloudHSM](/cloudhsm/overview/) or on-premises Primus HSM.

## 1. Install the Primus CNG/KSP Provider

Refer to the [Primus MS CNG Provider documentation](../../mscng/overview) on how to download, install and configure the Primus CNG/KSP Provider.

:::note
Ensure the CNG API is licensed and activated on your HSM device.
:::

## 2. Preparations and Obtaining a Certificate

- Ensure your [prerequisites](/ms-smime/Installation/prerequisites) are in order.
- Make sure to have valid certificates issued. Follow the [Get your Digital Certificate](/ms-smime/Installation/digital-certificate) on how to do so.

## 3. Configure your Outlook Client

After acquiring the Certificates and installing the Securosys CNG Provider it's possible to configure the Outlook Client to use these certificates.

- Configure Outlook Client to use your certificates for signing and encryption
- Exchange Public keys if necessary.

For more information follow the tutorial to [Configure the Outlook Client](/ms-smime/Installation/using-certificates).