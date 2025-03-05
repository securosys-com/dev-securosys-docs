---
sidebar_position: 4
title: Configure Microsoft AD CS via PowerShell
sidebar_label: Setup via PowerShell 
description: Configuring Microsoft Active Directory Certificate Services (AD CS) via PowerShell with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Setup Microsoft AD CS via PowerShell

The following example shows the standalone root CA procedure done previously via GUI, now via Powershell.  
- Run PowerShell as admin user.
- Install AD CS feature including management tools 

:::note
The following command installs only AD CS binaries. Almost everything on Windows Server can be configured via PowerShell, for more information refer to Microsoft documentation.
:::

```sh
Install-WindowsFeature AD-Certificate
Add-WindowsFeature Adcs-Cert-Authority -IncludeManagementTools
```

Configure AD CS with the below command examples. There are three parameter sets for the CA PowerShell deployment cmdlet (refer to Microsoft documentation for further parameters and details): <br />
1. Using new certificate and private key. Run the following command in PowerShell:

```sh 
Install-AdcsCertificationAuthority –caType <CA-Type> -caCommonName "<CA-Name>" –CryptoProviderName "<Key Storage Provider Name>" –KeyLength <2048> –HashAlgorithm <SHA256> […]
```
Example:

```sh
Install-AdcsCertificationAuthority -CAType "StandaloneRootCa" -CryptoProviderName "RSA# Securosys Primus HSM Key Storage Provider" -KeyLength 4096 -HashAlgorithmName SHA256 -CACommonName myDemoCA -OverwriteExistingKey -Force
```
2. Using existing private key. Run the following command in PowerShell:

```sh
Install-AdcsCertificationAuthority –caType <CA-Type> –CryptoProviderName "<Key Storage Provider Name>" -caCommonName "<CA-Name>" –KeyContainerName <NAME_OF_KEY> –hashAlgorithm SHA256 […]
```

Example:

```sh
Install-AdcsCertificationAuthority -CAType "StandaloneRootCa" -CryptoProviderName "RSA# Securosys Primus HSM Key Storage Provider" -KeyContainerName myDemoCA -HashAlgorithmName SHA256 -CACommonName myDemoCA
```
3. Using existing certificate and private key. Using a certificate already present on local machine in MY store, run the command:
```sh
Install-AdcsCertificationAuthority –AllowAdministratorInteraction –caType <CA-Type> –certificateID [cert hash | cert serial number] […]
```
Example:

```sh
Install-AdcsCertificationAuthority -CAType "StandaloneRootCa" -CryptoProviderName "RSA# Securosys Primus HSM Key Storage Provider" -certificateID CertSerial
```
 
