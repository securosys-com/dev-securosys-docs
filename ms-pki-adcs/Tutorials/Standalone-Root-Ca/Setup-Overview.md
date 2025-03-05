---
sidebar_position: 0
title: Standalone Root CA setup for Microsoft AD CS
sidebar_label: Setup Overview
description: Standalone Root CA for Microsoft Active Directory Certificate Services (AD CS) with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Setting up a Standalone Root CA

This is the first AD CS role to be installed in an enterprise PKI. It is a trust anchor and establishes the root of a trust hierarchy. To secure the root CA, a common practice is to keep it offline to minimize the exposure. Bring it online only when issuing a subordinate CA certificate. The process is to simply add and configure AD CS role as a Certificate Authority (CA) on a non-domain joined server.

In case you want to migrate an operational CA to use the keys from Securosys Primus HSM or CloudHSM, refer to [this chapter](/ms-pki-adcs/Tutorials/Migrating-MPKI-to-HSM/Migrating-MPKI-toHSM.md).

:::caution
The following Standalone Root CA setup procedure is shown as an example and provides a mainly based straightforward integration process. Please take notice that there may be other ways to configure and setup Microsoft AD CS.  
Before moving forward with the example setup, please read through the [Prerequisites](/ms-pki-adcs/Installation/Prerequisites) and the [Installing CNG Provider](/ms-pki-adcs/Installation/Provider-Setup) sections as they are required for further steps.
:::


The following table lists the details used for this setup according the figure shown in section [Installation](/ms-pki-adcs/Installation/Installation-Overview):


|VM/Name/Domain|Role(s)|OS Type|IP Address/Mask | HSM Partition|
|---           |---    |---    |---             |---           |
|Demo-CAR (workgroup)         |Standalone Offline Root CA AD CS    |Windows Server 2016    |Offline (10.250.100.20/24)             |DEMO-CAR           |
|Demo-DC01.hsmtest.demo          | Domain Controller AD, DNS, LDAP, CDP/AIA  |Windows Server 2016    |10.250.100.10/24             |DEMO -DC01           |
|Demo-CAS.hsmdemo.test           |Enterprise Subordinate CA AD CS   |Windows Server 2016    |10.250.100.25/24            |DEMO -CAS           |
|Demo-IIS.hsmdemo.test           |IIS Web Server    |Windows Server 2016    |10.250.100.30/24             |DEMO -IIS           |
|HSM Primus X 18376142 V2.8.43, DNS: hsm142.hsmdemo.test       |HSM Internal    |---    |10.250.100.100/24 CNG Provider: Port 2320             |---           |

