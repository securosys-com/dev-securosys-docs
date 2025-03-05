---
sidebar_position: 0
title: Migrating a Microsoft AD CS Server instance
sidebar_label: Overview
description: Migrating Microsoft AD CS Server instance for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# How to migrate a Microsoft AD CS server instance?

In case you have to re-install a server or to upgrade to a newer Windows Server Version, you have to consider a few details. Please consult Microsoft documentation for AD CS migration.

We differentiate between two use cases:
1. Private Key on Primus HSM is exportable (can be included in backup), as shown in [this section](./Migrating-MSPKI-Exportable.md).
2. Private Key on Primus HSM is not exportable (backup of private key fails), as shown in [this section](./Migrating-MSPKI-NonExportable.md)

:::warning
The use cases below are related to the example **Standalone root CA** setup in this guide. The migrations of operational CAs should be tested thoroughly in a lab environment and are not covered in this guide.
:::