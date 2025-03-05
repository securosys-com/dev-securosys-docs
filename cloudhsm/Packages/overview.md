---
sidebar_position: 1
title: Security HSM - Securosys CloudHSM
sidebar_label: Overview
description: Cloud-based hardware security modules (HSMs) for generating and using your own encryption keys with your applications. FIPS 140-2 Level 3 validated HSMs
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
pagination_prev: null
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Service Packages

**Securosys CloudHSM** offers a variety of services tailored to different needs, ensuring flexibility and scalability for your organization. Choose from **dedicated** or **shared** HSM options, tailored to your requirements, including flexible solutions for production, testing, and hosted environments.

### HSM as a Service (HSMaaS)

**Shared HSMs**

Your own **partition** on a physical Hardware Security Module (HSM) in the cloud. A cluster consists of two active HSMs located in two active datacenters and a third HSM in a NATO Zone 2 Electromagnetic Pulse-protected bunker in the Alps, which serves as a [backup](../Architecture/backups.md) and disaster recovery facility.

- [CloudHSM Economy (ECO)](./economy), all-purpose service
- [CloudHSM Economy Certified (ECO CC)](./economy_cc), operated in certified mode
- [CloudHSM Sandbox (SBX)](./sandbox), for integration and pre-production testing (No backup)
- [CloudHSM BYOK](./byok), for Bring Your Own Key use cases

**Dedicated HSMs**

- [CloudHSM Platinum](platinum.md) exclusively owned and operated by Securosys, ensuring your keys and data remain isolated.

**Customer-owner HSMs**
- [HSM Operation Service (HOS)](hos.md) Maintain full custody by owning the HSM while it’s operated by Securosys.

:::tip What is a HSM partition?
A **partition** is defined as the amount of user space in megabytes (MB) allocated on each HSM in the cluster for storing objects and partition logs.
:::

### Service Package Comparison

| | [Economy<br/>(ECO)](economy.md) | [Economy Certified <br/>(ECO CC)](economy_cc.md) | [Sandbox<br/>(SBX)](sandbox.md)| [Platinum](platinum.md) | [HSM Operation Service (HOS)](hos.md) | [Bring Your Own Key<br/>(BYOK)](byok.md) |
| --- | --- | --- | --- | --- | --- | --- |
| **Subscription Type** | <center>Multi-tenant HSM subscription</center> | <center>Multi-tenant HSM subscription</center> | <center>Multi-tenant HSM subscription</center> | <center>Dedicated HSM subscription</center> | <center>Dedicated HSM purchased (customer owned)</center> | <center>Multi-tenant HSM subscription</center> |
| **Platform** | <center>2x1 +1<br/>3 HSM in 3 data centers</center> | <center>2x1 +1<br/>3 HSM in 3 data centers</center> | <center>2x1<br/>2 HSM in 2 data centers<br/> (Testing)</center> | <center>Dedicated HSMs hosted in data centers</center> | <center>Dedicated HSMs hosted in data centers</center> | <center>2x1 +1<br/>3 HSM in 3 data centers</center> |
| **Performance** (Sig./Min) | <center>Up to 600</center> | <center>Up to 600</center> | <center>Best available<br/></center> | <center>Up to 12`000</center> | <center>Up to 120`000</center> | <center>-</center> |
| **Capacity** | <center>10 MB</center> | <center>10 MB</center> | <center>10 MB</center> | <center>120 MB</center> | <center>30 GB</center> | <center>3/10/200<br/>key objects</center> |
| **Support**<br/>Availability<br/>Response time<br/>(critical/major/minor) | <center>24 x 7 x 365<br/>2/8/24h</center> | <center>24 x 7 x 365<br/>2/8/24h</center> | <center>24 x 7 x 365<br/>8/12/24h</center> | <center>24 x 7 x 365<br/>2/8/24h</center> | <center>24 x 7 x 365<br/>2/8/24h</center> | <center>24 x 7 x 365<br/>2/8/24h</center> |

:::note Platform
High Availability (HA) cluster with synchronized data available in active/active mode and in case of [ECO](economy.md), [ECO CC](economy_cc.md) or [BYOK](byok.md), a 3<sup>rd</sup> HSM that is located in a Business Continutity Data Center.
:::

:::note Performance
A consistent performance on [ECO](economy.md) and [ECO CC](economy_cc.md) packages is garanteed, measured as the average number of RSA4096/ECC512 signatures processed per minute over a 24-hour window. No hard rate limit is imposed. Performance fluctuations may be observed in short intervals.
:::

### API Integration Options

**CloudHSM** offers a [REST API](/cloudhsm/Architecture/client_access.md#transaction-security-broker-as-a-service-tsbaas-and-rest-as-a-service-restaas) and a selection of [Primus API Providers](/cloudhsm/Architecture/client_access.md#primus-api-providers) (client API software / libraries), installed on your application server. These ensure secure communication with the HSM, along with automatic failover and load balancing.

<Tabs>
  <TabItem value="rest" label="REST API (HTTPS)" default>
  - Best suited for complex architectures (micro-services) with different software stacks and languages.
  - Utilize the [Swagger-UI](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/) for a comprehensive API documentation, which helps in understanding the API structure and functionality, significantly reducing development time.
  - Upgradeable to [Transaction Security Broker (TSB)](../Overview/features.md#transaction-security-broker-tsb) for [Smart Key Attributes (SKA)](../Overview/features.md#smart-key-attributes-ska), [Cryptocurrencies](../Overview/features.md#cryptocurrencies)

  **[Learn more about REST-API](/tsb/overview/)**
  </TabItem>
  <TabItem value="JCE/JCA" label="JCE/JCA">
  - Most flexible solution for [Java](https://www.java.com/) integration.
  - Enhanced feature support for [Smart Key Attributes (SKA)](../Overview/features.md#smart-key-attributes-ska), [Cryptocurrencies](../Overview/features.md#cryptocurrencies), [Key Attestation](../Overview/features.md#key-attestation) and more.

  **[Learn more about JCE/JCA](/jce/overview/)**
  </TabItem>
  <TabItem value="PKCS#11" label="PKCS#11">
  - Best for applications that use the [PKCS#11 standard interface](https://docs.oasis-open.org/pkcs11/pkcs11-base/v3.0/pkcs11-base-v3.0.html), e.g. [OpenSSL](/openssl/overview), [Apache](https://dlarea.securosys.com/document/PrimusAPI_P11-OpenSslApacheProv_AN-E03.pdf), [NGINX](/openssl/osslv3/Use-Cases/nginx), Public Key Infrastructures, Key Management Systems and many programming language libraries.

  **[Learn more about PKCS#11](/pkcs/overview/)**
  </TabItem>
    <TabItem value="MicrosoftCNG" label="Microsoft CNG">
  - Best for [Microsoft Windows](https://www.microsoft.com/en-us/windows) operating systems.
  - Native integration for many applications using Microsofts [Cryptography Next Generation interface (CNG)](https://learn.microsoft.com/en-us/windows/win32/seccng/cng-portal).

  **[Learn more about Microsoft CNG](/mscng/overview/)**
  </TabItem>
</Tabs>

## Configuration Options

All CloudHSM service packages can be individually configured with regards to the required [API integration](/cloudhsm/Packages/overview#api-integration-options) and [optional packages](../Overview/features#options) for [Cryptocurrencies](/cloudhsm/Overview/features.md#cryptocurrencies), [Smart Key Attributes (SKA)](/cloudhsm/Overview/features#smart-key-attributes-ska), [Post-Quantum Cryptographic (PQC) Algorithms](/cloudhsm/Overview/features#post-quantum-cryptographic-pqc-algorithms) and [Transaction Security Broker (TSB)](/cloudhsm/Overview/features#transaction-security-broker-tsb).

Furthermore, in the [Partition Security Policy](../Tutorial/parameter_descriptions#partition-security-policy-settings), you can configure policy settings for [Key Import](../Tutorial/parameter_descriptions#key-import), [Key Export](../Tutorial/parameter_descriptions#key-export) and [Key Invalidation](../Tutorial/parameter_descriptions#key-invalidation). Additionally, access to the CloudHSM partition can be restricted to a list of whitelisted source IP addresses.

## Partition Remote Administration

By default, Securosys provides support to perform any changes you request on your HSM.

However, with our [Decanus Terminal’s Partition Administration](../Overview/features.md#hsm-partition-remote-administration) you also have the option to fully control access to your HSM partition. This includes making configuration changes, downloading backups, and even disabling HSM administrators' access to your partition. This way, you benefit from the security advantages of your own HSM without the usual headaches and costs.

## More content

- [Terms and Conditions](../category/terms-and-conditions/)