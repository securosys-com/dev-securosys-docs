---
sidebar_position: 0
title: Prerequisites - Microsoft CNG & Security HSM
sidebar_label: Prerequisites
description: Prerequisites for installing the Primus CNG/KSP Provider on Microsoft Windows Server or Client, enabling integration with Securosys Hardware Security Modules (HSMs), both cloud and on-premises.
keywords: [hsm, cloud hsm]
---

# Prerequisites

Prerequisites for installing Primus CNG/KSP Provider on Microsoft Windows Server or Client to integrate Securosys HSMs (cloud and on-premises):

- [x] Windows operating system with:
    - [x] Crypto API Next Generation (CNG),
    - [x] Microsoft Windows Server 2012R2 or higher, Windows 7 or higher,
    - [x] Windows Installer 2.0 or higher.
- [x] Securosys Primus CNG/KSP Provider software V1.30 or newer (MSI package).
- [x] Depending on your Windows installation, choose the appropriate setup package:
    - [x] 64-bit Windows installation: `setup_x64.msi`
    - [x] 32-bit Windows installation: `setup.msi`
- [x] Administrator rights for installation/update and configuration

:::note Note
The Primus CNG/KSP Provider files (installer and DLLs) **below v1.21.3** are not digitally signed and can result in false positives using antivirus software (e.g. Symantec). Please check how to [update an existing CNG/KSP Provider](/mscng/Installation/Updating-Upgrading.md).
:::