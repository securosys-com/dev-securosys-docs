---
sidebar_position: 0
title: Installing PKCS#11 for CyberArk - HSM
sidebar_label: Installing PKCS#11
description: Install the Primus PKCS#11 provider on CyberArk PAM Vault Server, enabling secure HSM integration for privileged access management and enhanced security.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Installing PKCS#11 for CyberArk PAM
### HSM integration guide

Install the latest version of Primus PKCS#11 provider on the device with the CyberArk Privileged Access Manager Vault Server already installed.

:::info
The examples in this guide refer to a Windows Server platform.
:::

## Temporarily disable/enable Server Hardening

Please follow the next steps to temporarily disable the CyberArk hardening to install the Primus HSM PKCS#11 provider:
- Navigate to **`Services Management`**.
- Enable and start the **`Windows Module Installer`** service.
- Start the windows **`Registry Editor`**.
- Locate the **`HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\msiserver`** entry.
- Back up the **`Start`** entry.
- Change the value of the **`Start`** entry to **`3`**.
- Restart the **`CyberArk Vault Server`**.
- Navigate to **`Services Management`** and start the **`Windows Installer service`**.

You can now Install the Primus HSM PKCS#11 provider. To reenable hardening please follow the next steps:
- Navigate to **`Services Management`**.
- Stop and disable the **`Windows Module Installer service`**.
- Stop and disable the **`Windows Installer service`**.
- Start the windows **`Registry Editor`**.
- Locate the **`HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\msiserver`** entry.
- Change the value of the Start entry to **`4`**.

The device is now ready for the Primus PKCS#11 provider to be installed.

## Installation of Primus PKCS#11 Provider

Follow the [Primus HSM PKCS#11 Provider Installation](/pkcs/Installation/pkcs11_provider_installation) process to install the **`Primus PKCS#11 Provide`** on the CyberArk PAM Digital Vault machines.

:::note
Ensure that the Primus HSM PKCS#11 provider is installed on all the CyberArk PAM Digital Vault machines.
:::

