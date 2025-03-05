---
sidebar_position: 0
title: Microsoft Internet Information Services (IIS)
sidebar_label: Overview
description: Microsoft Internet Information Services (IIS) with Securosys Hardware Security Modules.
keywords: [hsm clusters, primus hsm, microsoft, iis, securosys]
grid_title: Microsoft Internet Information Services (IIS)
grid_search_tags: [PKCS#11, Webserver]
grid_description: Secures private keys for Microsoft IIS with Securosys HSMs, ensuring secure key management and compliance with standards like GDPR.
grid_categories: [MSCNG, WebServer]
grid_tile_logoUrl: '/img/company_logo/microsoft.png'
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Microsoft Internet Information Services
### HSM Integration Guide

This page describes how to secure the private keys used for the *Microsoft Internet Information Services* (IIS) by using the Securosys Hardware Security Modules (HSMs), on-premies and cloud.

The IIS for Windows Server is a flexible, secure and manageable Web server for hosting anything on the Web. IIS can communicate with **MS SharePoint**, **Visual Studio .NET**, **ASP.NET and Web Distributed Authoring** and **Versioning (WebDAV)**.

The *MS Cryptography Next Generation* (CNG) API supports Cryptographic Algorithm Providers and *Key Storage Providers* (KSP) in software and hardware. This allows the Internet Information Services to create and handle private keys and related cryptographic functions on Hardware Security Modules, thereby fulfilling new compliance requirements (e.g. GDPR).

![](../ms-iis\img\Architecture-Diagram.png)

*The Primus Hardware Security Modules* (HSMs) from Securosys improve drastically the security of MS IIS, and all applications based on Microsoft CNG API.

The Primus HSMs are built to securely generate and store true random cryptographic keys, providing a central, certified secure storage. They also control and regulate access to the keys and the related cryptographic functionality. The **Primus HSM** combined with IIS meets or exceeds the best practice security requirements and is one step ahead of fulfilling your compliance demands by providing:

- Hardware-based secure generation of true random cryptographic keys
- Central and highly secure storage of cryptographic keys
- Load balancing and fail-over by clustering the HSMs
- Controlled and regulated access to the keys
- Hardware acceleration of cryptographic operations such as encryption, authentication, and digital signatures, relieving the host server of processor intensive computations
- Scalable performance at manageable cost

All certificate issuance and validation processes occur within the protected confines of the HSM. Private keys are never accessible outside the HSM.

The Primus HSM can easily be integrated in a Microsoft Windows system by installing the Primus CNG Provider. This enables all Windows servers and clients to generate and store their private keys and certificates securely in the HSMs, and perform all related cryptographic functionality, like signing or certificate validation, hardware accelerated on the Primus HSM.

## Target Audience
This document is intended for Securosys Primus HSM or CloudHSM users and administrators and IT professionals in charge of Microsoft administration and software implementations.

For on-premises HSM deployed operation administrative skills are required for Securosys Primus HSMs.

## Support Contact
If you encounter a problem while installing/configuring the CNG provider or integrating the HSM with MS IIS, make sure that you have read the referenced documentation. If you cannot resolve the issue, please contact Securosys Customer Support. For specific requests regarding Securosys integration with MS IIS, the Securosys Support Portal is reachable under https://support.securosys.com.

## What's Next
For a smooth start integrating your Primus HSM with Microsoft IIS:

- Consult the [Quickstart](/ms-iis/quickstart) section for a comprehensive task listing
- For detailed installation and configuration instructions, follow the [Installation](/ms-iis/category/installation) guide
- Integrate your application with Securosys using IIS, by following the  [Tutorial](/ms-iis/Tutorials/Binding-app) page