---
sidebar_position: 0
title: Migrating an AD CS private key to the HSM
sidebar_label: Overview
description: Migrating a Microsoft AD CS private key to Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# How to migrate a private key to the HSM?

There are several ways to migrate an existing Microsoft Certification Authority (AD CS) to use the private keys from the Primus HSM or CloudHSM service, either:
1. Migrating the existing private key(s) and certificate from the Microsoft CSP or CNG/KSP to the Primus HSM CNG/KSP key storage provider. Requirement: private key must be exportable in wrapped format, e.g. PKCS#8.
2. Using the existing private key and renew the certificate and private key, storing them on Primus HSM CNG/KSP.
3. Setting up a new AD CS instance (side-by-side), e.g. to use newer algorithms.

Migrating existing AD CS key material from CSP or CNG/KSP to Securosys Primus HSM CNG/KSP requires the following steps (valid for Windows Server 2012R2/2016/2019):
- Install Securosys Primus HSM and CNG/KSP Provider Software (see [Installing CNG Provider](/ms-pki-adcs/Installation/Provider-Setup.md))
- Backup AD CS including private key and configuration (see [Backup AD CS](./Backup-ADCS.md))
- Delete the key(s) and certificate(s) from the old key store (see [Delete the Key and Certificate](./Delete-Key&Cert.md))
- Import the private key to your Primus HSM or CloudHSM (see [Import Private Key to Primus HSM/CloudHSM](./Import-PrivKey-toHSM.md))
- Reconfigure the AD CS to use the key from the new location (see [Reconfigure AD CS Registry](/ms-pki-adcs/Tutorials/Migrating-MPKI-to-HSM/Reconfigure-ADCS-Registry.md))
- Test and cleanup procedures (see [Test and Cleanup](./Test&Cleanup.md))

For further information refer to the guidelines provided by Microsoft, e.g.:
- [Step by Step Migrating AD CS](https://techcommunity.microsoft.com/t5/itops-talk-blog/step-by-step-migrating-the-active-directory-certificate-service/ba-p/697674)

:::warning
The following examples are related to the example **Standalone root CA** setup in this guide. Migrations of operational CAs should be tested thoroughly in a lab environment and are not covered in this guide.
:::
