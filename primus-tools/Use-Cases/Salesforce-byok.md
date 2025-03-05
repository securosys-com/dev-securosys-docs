---
sidebar_position: 3
title: Salesforce BYOK and Primus Tools
sidebar_label: Salesforce - BYOK
description: Salesforce Bring Your Own Key (BYOK) with Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Salesforce - Bring Your Own Key

Bring Your Own Key (BYOK) within Salesforce Shield Platform Encryption allows users to supply and manage their own tenant secrets, enhancing data security and regulatory compliance. Users generate and store their key material outside of Salesforce, using preferred crypto libraries or hardware security modules (HSMs), and grant access to Salesforce's encryption mechanisms.

Salesforce generates a 4096-bit RSA certificate to encrypt data, with the private key protected by the tenant secret. This provides an extra layer of security and ensures sensitive data is handled according to regulatory requirements.

More information on **[Salesforce - Bring Your Own Key](/salesforce-byok/overview)**.
