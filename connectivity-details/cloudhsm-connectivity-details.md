---
sidebar_position: 1
title: CloudHSM Connectivity Details
sidebar_label: CloudHSM (HSMaaS)
description: Explore on-premise HSM cluster configurations, including Primus HSM, Decanus Terminal, and Transaction Security Broker. Discover hostnames and TCP ports for secure integration.
keywords: [hsm clusters, hsm hostnames, hsm tcp ports, hsm key management, hsm as a service, hsm digital signature, hsm services, hsm service, what is hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, code signing, encryption key management, hardware security module, hsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws hsm documentation, hsm pricing]
grid_title: CloudHSM Connectivity Details
grid_search_tags: [Setup]
grid_description: Quick and accessible information regarding the connection parameters required when connecting to different Securosys Hardware Security Module (HSM) solutions.
grid_categories: [Setup]
grid_tile_logoUrl: '/img/company_logo/HSM.webp'
---

# CloudHSM Connectivity Details

This document provides all connectivity details for Securosys CloudHSM services. **Choose the appropriate configuration based on your deployment environment** (Sandbox/Testing vs. Production) and any specific requirements such as FIPS certification.

## Overview

Securosys offers connectivity for three primary service lines:
- **CloudHSM - HSMaaS:** Hardware Security Module as a Service.
- **CloudHSM - TSBaaS:** REST API as a Service.
- **CloudHSM - DKEaaS:** Double Key Encryption as a Service.

### Onboarding Information

Connectivity details specific to your CloudHSM service are provided during the onboarding process and included in your _Welcome Support Ticket_. Please refer to that ticket for any additional guidance.

---

## Network Configuration Parameters

For all CloudHSM services, configure your API provider with the following TCP ports:

<small>

| TCP Port **(JCE/JCA)** | TCP Port **(PKCS#11)** | TCP Port **(MS CNG)** | TCP Port **(REST API)** | TCP Port **(Partition Administration)** |
|------------------------|------------------------|-----------------------|-------------------------|-------------------------------------------|
| <center>**2300**</center>               | <center>**2310**</center>               | <center>**2320**</center>              | <center>**443**</center>                 | <center>**2380** * </center>                                |

</small>

*Configure Partition Administration only on the master device of a cluster.

---

## Connectivity Details

:::info
- For HSMaaS, ensure you include **both** the master and all clone hostnames in your provider configuration for redundancy and failover.  
- The RESTaaS is redundantly deployed and has automated failover within the HSM-Cluster.
:::

<small>

| **Cluster / Service** | **Environment**                | **API - Master**                                     | **API - Clones**                                                                                                                                  | **REST API**                             |
|-----------------------|--------------------------------|------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------|
| **[ECO01 / ECO02](/cloudhsm/Packages/economy)**    | Production (Switzerland)           | _ch01-api.cloudshsm.com_ | ch02-api.cloudshsm.com                                                                                            | rest-api.cloudshsm.com/v1/              |
| **[PLAXX](/cloudhsm/Packages/platinum)**           | Platinum (International)| _plaXX-api.cloudshsm.com_ | plaXX-api.cloudshsm.com                                                                                                     | plaXX-rest-api.cloudshsm.com/v1/        |
| **[CCM01](/cloudhsm/Packages/economy_cc)**         | CC Certified, FIPS (Switzerland)   | _ch01-api.cloudshsm.com_ | ch02-api.cloudshsm.com                                                                                            | rest-api.cloudshsm.com/v1/              |
| **[CCMUS01](/cloudhsm/Packages/economy_cc)**       | CC Certified, FIPS (United States) | _us01-api.cloudshsm.com_ | us02-api.cloudshsm.com                                                                                            | rest-api.cloudshsm.com/v1/              |
| **[ECOWR01](/cloudhsm/Packages/economy)**          | Production (International)         | _de01-api.cloudshsm.com_ | ch01-api.cloudshsm.com <br/> us01-api.cloudshsm.com <br/> us02-api.cloudshsm.com <br/> sg01-api.cloudshsm.com     | rest-api.cloudshsm.com/v1/              |
| **[ECODE01](/cloudhsm/Packages/economy)**          | Production (Germany)               | _de01-api.cloudshsm.com_ | ch01-api.cloudshsm.com                                                                                            | rest-api.cloudshsm.com/v1/              |
| **[ECOUS01](/cloudhsm/Packages/economy)**          | Production (United States)         | _us01-api.cloudshsm.com_ | us02-api.cloudshsm.com                                                                                            | rest-api.cloudshsm.com/v1/              |
| **[ECOSG01](/cloudhsm/Packages/economy)**          | Production (Singapore)             | _sg01-api.cloudshsm.com_ | ch01-api.cloudshsm.com                                                                                            | rest-api.cloudshsm.com/v1/              |
| **[SBX01](/cloudhsm/Packages/sandbox)**            | Sandbox / Testing                  | _ch01-api.cloudshsm.com_ | ch02-api.cloudshsm.com <br/> us02-api.cloudshsm.com <br/> sg01-api.cloudshsm.com                                  | sbx-rest-api.cloudshsm.com/v1/          |

</small>

---

## CloudHSM - DKEaaS Connectivity Details

For CloudHSM Double Key Encryption as a Service (DKEaaS), use the following endpoints:

- **KMS Endpoint:** [https://cockpit.securosys365.com/](https://cockpit.securosys365.com/)
- **DKEaaS Apps Endpoints:** Use the wildcard URL https://*uuid*.securosys365.com/ for DKEaaS applications.

---

## Quick Summary

1. **Environment Selection:**   
   - **Testing/Integration (Sandbox):**  
     - **Cluster:** SBX01  
     - **RESTaaS Endpoint:** sbx-rest-api.cloudshsm.com/v1/
   - **Production:**  
     - **Switzerland:** ECO01 and ECO02  
     - **FIPS Compliance:**  
       - **Switzerland:** CCM01  
       - **United States:** CCMUS01  
     - **International:** ECOWR01, ECODE01, ECOUS01, or ECOSG01  
     - **RESTaaS Endpoint:** rest-api.cloudshsm.com/v1/ for all production environments.
2. **Redundancy & Failover:**
   - Always include both master and clone hostnames in your HSMaaS API provider configuration.

3. **DKEaaS:**
   - **KMS Endpoint:** https://cockpit.securosys365.com/
   - **DKEaaS Apps Endpoints:** https://*uuid*.securosys365.com/

4. **Reference:**  
   - For any uncertainties or environment-specific configurations, refer to your _Welcome Support Ticket_.

---

For further assistance or clarifications, please contact your support representative or refer to the onboarding documentation included in your Welcome Support Ticket.
