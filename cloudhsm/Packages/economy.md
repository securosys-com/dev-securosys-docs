---
sidebar_position: 2
title: CloudHSM Economy - All-purpose Cloud HSM
sidebar_label: Economy (ECO)
description: Securely manage encryption keys with FIPS 140-2 Level 3 validated HSMs in the cloud. Optimal performance and cost-effective storage solutions for small to medium businesses.
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

# Economy (ECO)

**CloudHSM Economy (ECO)** is an all-purpose package designed for small and medium businesses, offering a pay-as-you-go model that delivers optimal performance at a cost-effective price. Each user space (partition) provides a defined storage capacity within a cluster of synchronized HSMs, ensuring data security with additional backup to a secure data center. ECO also serves as an economical production-ready backup solution for on-premise HSMs.

## Service Description

This service provides access to Securosys Cloud HSM Service partitions with the following attributes:

| Attribute | Description |
| --- | --- |
| Client Connections | Not limited |
| Storage Capacity | 10MB (up to 200 RSA-4096 asymmetric key pairs, 5MB reserved for user audit logs). Additional Storage Capacity is available in increments of 100 MB  |
| Performance | Up to 600 operations (RSA-4096) per minute. Additional performance is available in increments of 600 operations (RSA-4096) per minute. |
| Key Generation | Max. 1 key per second |
| Cryptographic APIs | [PKCS#11](../../pkcs/overview), [Java (JCA/JCE)](../../jce/overview), [Microsoft CNG](../../mscng/overview) or [REST](../../tsb/overview) |
| Supported Functions | See the [Supported Algorithms and Functions](../Overview/Supported_Algorithms_and_Functions) list |
| Operational Mode | Normal Mode (Algorithm set not FIPS restricted) |

## Service Options

In addition to the service description provided above, the following table outlines the available options and indicates whether they are currently enabled, disabled, or can be optionally selected:

| Option | Availability |
| --- | --- |
| [Attestation and Partition Audit](../Overview/features.md#key-attestation) | Enabled |
| [Partition Administration](../Overview/features.md#hsm-partition-remote-administration) | Option. Requires purchase or rent of Decanus Terminal |
| [Smart Key Attributes (SKA)](../Overview/features.md#smart-key-attributes-ska) | Option |
| [Transaction Security Broker (TSB)](../Overview/features.md#transaction-security-broker-tsb) | Option |
| [Cryptocurrencies](../Overview/features.md#cryptocurrencies) | Option | 
| [Post-Quantum Cryptographic Algorithms](../Overview/features.md#post-quantum-cryptographic-pqc-algorithms) | Enabled |
| [Timestamp Service (RFC3161 compliant)](../Overview/features#timestamp-service) | Option |

## Regions

ECO is accessible through either a Global or Regional cluster, with options including Swiss, German, US, or Singapore clusters, ensuring optimal reach and performance tailored to specific geographic needs. This distribution is detailed in the table below.

| Service Package | Data Center locations | Active DC | Business Continuity DC |
| --- | --- | --- | ---|
| Economy (ECO), Switzerland |Switzerland | CH01, CH02 | CH03 |
| Economy (ECO), Germany | Germany, Switzerland | DE01, CH02 | CH03 |
| Economy (ECO), USA | USA, Switzerland | US01, US02 | CH03 |
| Economy (ECO), Singapore | Singapore, Switzerland | SG01, CH02 | CH03 |
| Economy (ECO), Global | Germany, USA, Singapore, Switzerland | DE01, US01, US02, SG01 | CH03 |

:::note
The active sites are located based on the configuration specified in the cluster definition. The business continuity site, designed for disaster recovery, is strategically located in Switzerland.
:::

## Partition Policy Settings

The following tables provide an overview of all partition policy settings, indicating whether they are enabled, disabled, or available for selection by the customer upon ordering and wether they can be modified afterwards.

## API Settings

| API Activation | Availability |
| --- | --- |
| [PKCS#11](../../pkcs/overview) | Included; can be enabled/disabled upon ordering  |
| [Java (JCA/JCE)](../../jce/overview) | Included; can be enabled/disabled upon ordering |
| [Microsoft CNG](../../mscng/overview) | Included; can be enabled/disabled upon ordering |
| [REST](../../tsb/overview) | Included; can be enabled/disabled upon ordering |
| [Client API Access](../Tutorial/parameter_descriptions#client-api-access) | Enabled. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration) to take partition completely offline. |

## Partition Settings

| Policy| Availability |
| --- | --- |
| [Key Import](../Tutorial/parameter_descriptions#key-import) | Selectable upon ordering. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |
| [Key Export](../Tutorial/parameter_descriptions#key-export) | Selectable upon ordering. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |
|[Key Invalidation](../Tutorial/parameter_descriptions#key-invalidation) | Selectable upon ordering. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |
| [Partition R/O](../Tutorial/parameter_descriptions#partition-read-only) | Disabled. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |
| [Session Objects](../Tutorial/parameter_descriptions#session-objects) | Enabled |
| [Object Destruction](../Tutorial/parameter_descriptions#object-destruction) | Selectable upon ordering. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |
| [Object Usage](../Tutorial/parameter_descriptions#object-usage) | Enabled. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |

## Service Management

The CloudHSM ECO partition offers versatile management options to make changes to the [partition policy setting](economy.md#partition-policy-settings). Users can utilize the Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration) or submit change requests on the [Support Portal](https://support.securosys.com/external).
