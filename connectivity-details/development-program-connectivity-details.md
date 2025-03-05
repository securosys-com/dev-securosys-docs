---
sidebar_position: 3
title: Development HSM Cluster Connectivity Guide
sidebar_label: Developer Program
description: Discover connectivity details for Securosys' Developer Program services, including the HSM Developer Program Infrastructure and Transaction Security Broker. Access credentials and parameters provided for development and integration testing with Primus HSM and TSB services.
keywords: [hsm clusters, hsm hostnames, hsm tcp ports, hsm key management, hsm as a service, hsm based hsm, hsm digital signature, hsm services, hsm service, what is hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, code signing, encryption key management, hardware security module, hsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws hsm documentation, hsm pricing]
---

# Securosys HSM Developer Program

General connectivity details for development program services:
- [HSM Developer Program Infrastructure](#hsm-developer-program)
- [Transaction Security Broker - Developer Program](#transaction-security-broker---developer-program)

Your access credentials to the Securosys Developer Program HSM Infrastructure are provided via the [Support Portal](https://support.securosys.com/external/) (account required). 

:::tip New to the developer program ?
You can obtain a free developer account with Securosys. For more information, please contact [info@securosys.com](mailto:info@securosys.com) and request registration in the Developer Program.
:::

:::warning
The Securosys Developer Program is designed solely for testing and application development/integration. The HSMs operate in debug mode, providing us with deeper insights into development activities. The Securosys Developer Program comes with no SLA and is not intended for productive use.

Securosys neither encourages nor supports the migration of Developer Program data to productive systems, whether CloudHSM or on-premises.

Migration of Developer Program data to CloudHSM Sandbox (SBX) is possible upon special request but is subject to charges.
:::

## HSM Developer Program 

The Developer Program for Primus HSM is composed of HSMs in a HA-cluster configuration, please use below parameters to connect (also sent with the initial invitation mail).

| Hostname / IP | TCP Port<br/> JCE/JCA | TCP Port<br/> PKCS#11 | TCP Port<br/> MS CNG |
|---|---|---|---|
| grimsel.securosys.ch / 82.197.162.10 | <center>2400</center> | <center>2410</center> | <center>2420</center> |
| nufenen.securosys.ch / 82.197.162.10 | <center>2401</center> | <center>2411</center> | <center>2421</center> | 


For further details consult the [Provider User Guides](https://docs-dev.securosys.com) and [Primus HSM User Guide](https://support.securosys.com/external/knowledge-base/article/63), downloadable from the support portal.

## Transaction Security Broker - Developer Program

General connectivity details for development program Transaction Security Broker (TSB).

| TSB Service | Description | Authentication | Swagger-UI | API-Endpoint |
|---|---|---|---|---|
| Developer Program **(DEV)** |	TSB bound to Developer Program HSM partition | JWT | [Launch](https://primusdev.cloudshsm.com/swagger-ui/index.html) | primusdev.cloudshsm.com/v1/ |

Details of the RESTful-API and Transaction Security Broker can be found on the [Swagger-UI](https://primusdev.cloudshsm.com/swagger-ui/index.html).

## You might be interested in

- [CloudHSM Sandbox (SBX)](/cloudhsm/Packages/sandbox) for testing
- [CloudHSM free trial](https://cloud.securosys.com/cloudhsm/)