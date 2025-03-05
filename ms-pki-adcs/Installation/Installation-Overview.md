---
sidebar_position: 2
title: Configuring the Cryptographic Provider for Microsoft AD CS
sidebar_label: 3. Cryptographic Provider
description: Configuring the Cryptographic Provider to use Microsoft Active Directory Certificate Services (AD CS) with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Configuring the Cryptographic Provider

:::info
This procedure offers a primarily straightforward integration process. However, please note that there are additional methods available for configuring and setting up Microsoft AD CS.
:::

This guide assumes you have a basic understanding of the Primus HSM and Microsoft Server AD CS. It does not detail every step of the hardware and software setup process.

For the sake of simplicity, only the **domain administrator** role is used instead of the PKI management roles defined by Microsoft.

**How to migrate from Microsoft AD CS to Primus HSM?**

- [Tutorial - Migrate an **existing** instance](/ms-pki-adcs/Tutorials/Migrating-MPKI-to-HSM/Migrating-MPKI-toHSM),
- [Tutorial - Migrate a **new** instance](/ms-pki-adcs/Tutorials/Migrating-MSPKI/Setup-Overview).

## Select Microsoft AD CS Cryptographic Provider

To integrate the Securosys HSM with Microsoft Active Directory Certificate Services the Primus KSP/CNG Provider must be specified. This is usually done during installation of the Certificate Authority and can be done either via Graphical User Interface (GUI) or the Command Line Interface (CLI).

:::note
Please read through the [Prerequisites](./Prerequisites.md) and the [Installing CNG Provider](./Provider-Setup.md) sections as they are required for further steps.
:::

<Tabs groupId="device-setup">
  <TabItem value="ui" label="GUI" default>

  When selecting the Provider on the **`Cryptography for CA window`**, select the **`RSA#Securosys Primus HSM Key Storage Provider`** along with the key type, key length and suitable hash algorithm. Please consider that some older devices and applications do only support key lengths up to 2048 bit.
:::note
When using an existing private key you will be required to specify your existing key before selecting the Key Storage Provider.
:::
Example of selecting the Securosys Primus Key Storage Provider:
![](../img/Cryptography-for-CA.png)



  </TabItem>
  <TabItem value="console" label="CLI" default>  

- Using new certificate and private key. Run the following command in PowerShell:

```sh 
Install-AdcsCertificationAuthority –caType <CA-Type> -caCommonName "<CA-Name>" –CryptoProviderName "<Key Storage Provider Name>" –KeyLength <2048> –HashAlgorithm <SHA256> […]
```
Example:

```sh
Install-AdcsCertificationAuthority -CAType "StandaloneRootCa" -CryptoProviderName "RSA# Securosys Primus HSM Key Storage Provider" -KeyLength 4096 -HashAlgorithmName SHA256 -CACommonName myDemoCA -OverwriteExistingKey -Force
```
- Using existing private key. Run the following command in PowerShell:

```sh
Install-AdcsCertificationAuthority –caType <CA-Type> –CryptoProviderName "<Key Storage Provider Name>" -caCommonName "<CA-Name>" –KeyContainerName <NAME_OF_KEY> –hashAlgorithm SHA256 […]
```

Example:

```sh
Install-AdcsCertificationAuthority -CAType "StandaloneRootCa" -CryptoProviderName "RSA# Securosys Primus HSM Key Storage Provider" -KeyContainerName myDemoCA -HashAlgorithmName SHA256 -CACommonName myDemoCA
``` 
  </TabItem>
</Tabs>



