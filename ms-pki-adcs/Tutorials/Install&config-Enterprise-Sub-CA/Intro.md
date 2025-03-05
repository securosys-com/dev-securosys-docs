---
sidebar_position: 0
title: Enterprise Subordinate Certificate Authority (CA)
sidebar_label: Setup Overview
description: Configuring Enterprise Subordinate Certificate Authority (CA) with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Setup Overview

In this section, we will add the previously created example AD CS role to the Subordinate Certificate Authority (CA) Server, which must be a member of the domain. Please adhere to the followin prerequisites as they are required for this section:
- Install the Active Directory Certificate Services Role as shown in section [Adding the Active Directory Certificate Server Role](/ms-pki-adcs/Tutorials/Standalone-Root-Ca/Setup-Overview)


:::caution
The following setup procedure is shown as an example and provides a mainly based straightforward integration process. Please take notice that there may be other ways to configure and setup Microsoft AD CS.  
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


