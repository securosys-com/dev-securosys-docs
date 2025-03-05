---
sidebar_position: 4
title: Securosys CloudHSM - Quickstart
sidebar_label: Quickstart
description: Learn how to get started with CloudHSM.
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

# Quick Start Guide - CloudHSM

This Quickstart section provides a comprehensive task listing of how to setup your CloudHSM environemnt. For more detailed instructions please consult the [Getting Started](/cloudhsm/GettingStarted/setup_instructions) section.


## Obtain User Credentials

You receive two SecureSafe links to your CloudsHSM user credentials per e-mail. The files are protected by a password (Secure Code) sent via text message to your phone.
_Note: Please also check your spam folder for these messages._


The files contain two sets of credentials in case of **JCE/JCA**, **Microsoft CNG**, or **PKCS#11**:
- Service User name and password to authenticate with reverse proxies (see below)
- HSM User name and setup password to access the HSM partition

:::warning 
The permanent secret must be retrieved within the lifespan (168h) of the setup password after first use.
:::

Please consult [CloudHSM - Activation Process](/cloudhsm/GettingStarted/activation_process.md) if you are looking for more details on the credentials provisioning process.

## Connectivity Parameters for API Provider Setup

Select the Network Configuration Parameters for the API Provider Setup from [CloudHSM - Connectivity](/connectivity-details/cloudhsm-connectivity-details) relevant to your subscription.

## API Provider Setup

### Download API Provider Software

:::note
If you are using the Rest-API, please refer to either the [TSBaaS Quickstart](/tsb/Quickstart/cloud-quickstart) or the [Docker Installation](/tsb/Quickstart/docker-quickstart) guide.
:::

Download the provider API software as per your CloudHSM order. The following providers are avaiable:
- JCE/JCA
- Microsoft CNG
- PKCS#11

Follow the instructions [CloudHSM - API Provider Setup Instructions](/cloudhsm/GettingStarted/setup_instructions#summary).


### Initiate the user account and Setup the API Provider

Use the setup password and connectivity details to setup your API Provider and to obtain the user secret for permanent integration following the instruction [Initiate the CloudHSM User Account](/cloudhsm/GettingStarted/setup_instructions.md#summary).


