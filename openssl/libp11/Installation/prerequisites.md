---
sidebar_position: 1
title: Step-by-Step Guide - Installing OpenSSL v1.x
sidebar_label: Prerequisites
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL,PKCS#11]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites

Before starting the process of integrating the Securosys [CloudHSM](/cloudhsm/overview/) or on-premises Primus HSM with the PKCS#11 Engine Plugin, please make sure to fulfill all the necessary requirements listed below:

- Securosys PKCS#11 Provider v1.8.6 or newer
- Securosys Hardware Security Modules (HSMs): 
  - On-Premises Primus HSM with firmware v2.8.21, v2.10.5 or newer, with PKCS#11 API and Session Object support enabled; or 
  - [CloudHSM](/cloudhsm/overview/) with PKCS#11 API and Session Object support enabled.


## Securosys Hardware Security Module (HSM)

**OpenSSL v1.x - OpenSC** is compatible with Securosys HSMs, both on-premises and in the cloud.

<Tabs groupId="device-setup">
  <TabItem value="device-cloud" label="Cloud" default>
      **Securosys CloudHSM** allows almost instant HSM operation by selecting and contracting the different services and options.
      - [Getting started with CloudHSM](/cloudhsm/category/getting-started)
      - [Subscribe to CloudHSM online](https://cloud.securosys.com/cloudhsm) (free trial available)
      - Contact [Securosys Sales](https://www.securosys.com/en/contact). 

  </TabItem>
  <TabItem value="device-onprem" label="On-premises">
      For general on-premises Primus HSM hardware, High-Availability Cluster setup and operation in FIPS or Common Criteria certified modes, refer to the corresponding [User Guide](https://support.securosys.com/external/knowledge-base/article/63)(account required).

      - Contact [Securosys Sales](https://www.securosys.com/en/contact). 

  </TabItem>
</Tabs>



## PKCS#11 Engine Plugin Essentials

To successfully integrate and configure the PKCS#11 Engine Plugin with Primus HSMs, several essential components and tools are required. Below is a list of the necessary software and resources you will need to set up the environment and ensure smooth operation.

<Tabs groupId="os">
<TabItem value="os-windows" label="Windows 11" default>

- **Primus PKCS#11 Provider**

  You can find the download link & credentials [here](../../../pkcs/downloads).

- **Microsoft Visual Studio**

  Download and install Microsoft Visual Studio from [Microsoft](https://visualstudio.microsoft.com/downloads/).

- **OpenSC PKCS11-tool**

  Download and install OpenSC from the [OpenSC GitHub repository](https://github.com/OpenSC/OpenSC/releases).

- **OpenSSL**

  Download and install the [OpenSSL for Windows installation package](https://wiki.openssl.org/index.php/Binaries).

- **OpenSC PKCS#11 Wrapper Library**

  Download the code of the PKCS#11 wrapper library from the [OpenSC/libp11 repository](https://github.com/OpenSC/libp11) on GitHub.

:::note

This guide is based on the following setup to ensure consistency and proper functionality. By using the same configuration, you can follow along smoothly and avoid potential issues during the integration process.

Here are the components and tools used:

| Software                       | Version                               |
|--------------------------------|---------------------------------------|
| Primus HSM                     | 2.11                                  |
| Primus PKCS#11 Provider        | 2.2.2 (Windows 64-bit MSI installer)  |
| Microsoft Visual Studio        | Visual Studio 2022                    |
| OpenSC PKCS11-tool             | 0.25.1 (Windows 64-bit MSI installer) |
| OpenSSL                        | 3.3.2 (Windows 64-bit MSI installer from [Shinning Light Productions](https://slproweb.com/products/Win32OpenSSL.html)) |
| OpenSC PKCS#11 Wrapper Library | libp11-0.4.12                         |

:::

</TabItem>

<TabItem value="os-linux" label="Linux">

The prerequisites for Linux-based systems will be added to this guide in the future. While we are working to provide these details, please note that it may take some time before they are fully available. We appreciate your patience and understanding as we continue to expand our documentation.

</TabItem>
</Tabs>
