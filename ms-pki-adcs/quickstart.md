---
sidebar_position: 1
title: Getting started with Microsoft AD CS & HSMs
sidebar_label: Quickstart
description: Getting started with Microsoft Active Directory Certificate Services (AD CS) for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Get started with Microsoft AD CS

The page provides a comprehensive guide outlining the steps necessary to integrate Microsoft AD CS Securosys Hardware Security Modules (HSMs), cloud and on-premises.

# 1. Prerequisites

Make sure to adhere to the prerequisites needed. For more details visit [Prerequisites](/ms-pki-adcs/Installation/Prerequisites).

# 2. Install the CNG Provider

Download, install and configure the [Primus CNG/KSP HSM Provider](/mscng/overview).

# 3. Update the Cryptographic Provider

To integrate the Securosys HSM with Microsoft Active Directory Certificate Services, the Primus KSP/CNG Provider must be specified.

This is usually done during installation of the Certificate Authority and can be done either via Graphical User Interface (GUI) or the Command Line Interface (CLI). For more information, continue to [Installation](/ms-pki-adcs/Installation/Installation-Overview).

An example selection is shown within the Standalone Root CA setup:
- Select the **`RSA#Securosys Primus HSM Key Storage Provider`** along with the key type, key length and suitable hash algorithm. Please consider that some older devices and applications only support key lengths up to 2048 bit. 

# Example AD CS Setup

Within this guide we provide an example setup on how to install and configure a [Standalone Root CA](/ms-pki-adcs/category/standalone-root-ca) as well as an [Enterprise Subordinate CA](/ms-pki-adcs/Tutorials/Install&config-Enterprise-Sub-CA/Intro), please see their respective sections for a step by step guide.

:::caution
These procedures are an example to showcase the integration of Securosys CloudHSM or on-premises Primus HSM with Microsoft AD CS. The steps shown are not the only way to install and configure your Microsoft AD CS. Please consult with Microsoft for more information and other possible procedures.
:::

For more detailed instructions, continue to the [Installation](/ms-pki-adcs/category/installation) and [Tutorial](/ms-pki-adcs/category/tutorials) sections.