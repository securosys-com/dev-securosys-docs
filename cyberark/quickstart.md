---
sidebar_position: 2
title: Getting started with CyberArk & Security HSM
sidebar_label: Quickstart
description: Get started with integrating Securosys CloudHSM or Primus HSM with CyberArk Privileged Access Manager for secure key management and compliance-ready solutions.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Quickstart

The quickstart section provides a comprehensive guide outlining the steps necessary to integrate CyberArk PAM with Securosys CloudHSM or on-premises Primus HSM.

## Installing Primus PKCS#11 Provider

Install the latest version of Primus PKCS#11 provider on the device with the CyberArk Privileged Access Manager Vault Server already installed.

Follow the instructions in [Installing Primus PKCS#11 Provider on CyberArk Vault](/cyberark/Installation/Install-Primus-Provider/Installing-PKCS-Provider.md).

## Configuring Primus PKCS#11 Provider

Configure the Primus PKCS#11 provider by adapting the configuration file **`primus.cfg`** according to your set-up. 

:::note
If network hardening is already configured on the host device, please see [Configuring Primus HSM on CyberArk Primary Vault](/cyberark/Installation/Configuring-HSM-on-primary-Vault.md) on how to enable an outgoing connection to the Securosys HSM.
:::

Depending on your platform, the configuration file is located by default under:

<Tabs groupId="os">
<TabItem value="unix" label="Unix" default>
On CyberArk PAM the `primus.cfg`file is best stored in:

`/etc/primus/primus.cfg`

</TabItem>
<TabItem value="windows" label="Microsoft Windows">
On CyberArk PAM the `primus.cfg`file is best stored in:

`C:\Program Files\Securosys\Primus P11\primus.cfg`

</TabItem>
</Tabs>

:::info
Consult [Primus PKCS#11 User Guide - Configuration](/pkcs/Installation/pkcs11_provider_configuration) for alternative configuration file locations.
:::

Follow the example in the [Installing Primus PKCS#11 Provider on CyberArk Vault](/cyberark/Installation/Install-Primus-Provider/Installing-PKCS-Provider.md) section.



## Configuring Primus HSM on CyberArk Primary Vault

Follow the instruction provided in [Configuring CyberArk Primary Vault with HSM](/cyberark/Installation/Configuring-HSM-on-primary-Vault).

- Allow traffic between CyberArk Primary Vault and the HSM in the **`DBParm.ini`** file.
- Set the **`pkcs11-password`** running command: ```sh CAVaultManager.exe SecureSecretFiles /SecretType HSM /Secret Password ```

## Generate CyberArk Vault Server Key on HSM

Now you are ready to protect your Vault Server Keys using the HSM infrastructure.

Follow the instructions provided in the [Tutorial - Securing the Vault Server Key](/cyberark/category/tutorial) to:
- Generate Vault Server Key on HSM
- Migrate Existing Vault Server Key to HSM
