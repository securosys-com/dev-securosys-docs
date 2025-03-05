---
sidebar_position: 1
title: API Provider for Versasec vSEC:CMS & HSM
sidebar_label: API Provider
description: API Provider for Versasec Credential Management System (CMS) for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Primus HSM PKCS#11 Provider

See [Primus HSM PKCS#11 Provider - Downloads](/pkcs/downloads) for more information on how to obtain the Primus HSM PKCS#11 Provider package. 

:::caution
It is recommended to use the 64-bit version of vSEC:CMS, therefore you should install the 64-bit version of the Primus HSM PKCS#11 provider on the vSEC:CMS server. 
:::

Install the Primus HSM PKCS#11 Provider. After a successful installation, add optionally the provider folder location to your PATH environment variable. Versasec vSEC:CMS will search in the system path for the Primus HSM PKCS#11 module.

Configure the Primus HSM PKCS#11 provider configuration file to connect to your on-premises Primus HSM or CloudHSM partition. 

After successfully configuring your Primus PKCS#11 provider test the connection and fetch the permanent secret. See [Permanent Secret Fetching](/pkcs/Installation/permanent_secret_management).

:::info
Refer to the [Primus HSM PKCS#11 Provider](/pkcs/overview) documentation on how to download, install, configure the Primus PKCS#11 Provider on the server where Versasec vSEC:CMS is installed. 
:::