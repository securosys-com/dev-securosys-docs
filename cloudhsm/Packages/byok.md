---
sidebar_position: 7
title: CloudHSM Bring Your Own Key (BYOK)
sidebar_label: Bring Your Own Key (BYOK)
description: CloudHSM BYOK enhances cloud security with dedicated key storage in CloudHSM ECO. Ensure compliance and control over encryption keys.
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

# Bring Your Own Key (BYOK)

**CloudHSM BYOK** is designed for businesses with strict regulatory compliance requirements or a desire for enhanced security in cloud deployments. It leverages a dedicated partition within the [CloudHSM Economy](./economy.md) service, allowing subscribers to store their encryption keys outside of their cloud service provider's environment. This ensures complete control and visibility over their key management process, while still benefiting from the robust security features of CloudHSM.

Key features include:

- A dedicated partition within CloudHSM ECO for secure key storage.
- Support for either up to 3, 10 or 200 key objects (RSA 2048 bit).
- All the tools and application notes neccesary to seamlessly generate and import keys into your chosen cloud service provider (e.g., Azure, AWS or Salesforce).

## Service Description

This service provides access to Securosys Cloud HSM Service partitions with the following attributes:

| Attribute | Description |
| --- | --- |
| Client Connections | Not limited |
| Storage Capacity | 3-200 key objects |
| Performance | N/A |
| Key Generation | Max. 1 key per second |
| Cryptographic APIs | [Java (JCA/JCE)](../../jce/overview) |
| Supported Functions | See the [Supported Algorithms and Functions](../Overview/Supported_Algorithms_and_Functions) list |
| Operational Mode | Normal Mode (Algorithm set not FIPS restricted) |

## Service Options

In addition to the service description provided above, the following table outlines the available options and indicates whether they are currently enabled, disabled, or can be optionally selected:

| Option | Availability |
| --- | --- |
| [Attestation and Partition Audit](../Overview/features.md#key-attestation) | Enabled |
| [Partition Administration](../Overview/features.md#hsm-partition-remote-administration) | Option. Requires purchase or rent of Decanus Terminal |
| [Smart Key Attributes (SKA)](../Overview/features.md#smart-key-attributes-ska) | Disabled |
| [Transaction Security Broker (TSB)](../Overview/features.md#transaction-security-broker-tsb) | Disabled |
| [Cryptocurrencies](../Overview/features.md#cryptocurrencies) | Disabled | 
| [Post-Quantum Cryptographic Algorithms](../Overview/features.md#post-quantum-cryptographic-pqc-algorithms) | Disabled |
| [Timestamp Service (RFC3161 compliant)](../Overview/features#timestamp-service) | Disabled |

## Regions

BYOK is accessible through either a Regional Swiss, German, US, or Singapore cluster, ensuring optimal reach and performance tailored to specific geographic needs. This distribution is detailed in the table below.

| Service Package | Data Center locations | Active DC | Business Continuity DC |
| --- | --- | --- | ---|
| Bring Your Own Key (BYOK), Switzerland |Switzerland | CH01, CH02 | CH03 |
| Bring Your Own Key (BYOK), Germany | Germany, Switzerland | DE01, CH02 | CH03 |
| Bring Your Own Key (BYOK), USA | USA, Switzerland | US01, US02 | CH03 |
| Bring Your Own Key (BYOK), Singapore | Singapore, Switzerland | SG01, CH02 | CH03 |

:::note
The active sites are located based on the configuration specified in the cluster definition. The business continuity site, designed for disaster recovery, is strategically located in Switzerland.
:::

## Partition Policy Settings

The following tables provide an overview of all partition policy settings, indicating whether they are enabled, disabled, or available for selection by the customer upon ordering and wether they can be modified afterwards.

## API Settings

| API Activation | Availability |
| --- | --- |
| [PKCS#11](../../pkcs/overview) | Disabled |
| [Java (JCA/JCE)](../../jce/overview) | Enabled |
| [Microsoft CNG](../../mscng/overview) | Disabled |
| [REST](../../tsb/overview) | Disabled |
| [Client API Access](../Tutorial/parameter_descriptions#client-api-access) | Enabled. Modifiable via [Support Portal](https://support.securosys.com/external), or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration)i to take partition completely offline. |

## Partition Settings

| Policy| Availability |
| --- | --- |
| [Key Import](../Tutorial/parameter_descriptions#key-import) | Disabled. |
| [Key Export](../Tutorial/parameter_descriptions#key-export) | Enabled. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |
|[Key Invalidation](../Tutorial/parameter_descriptions#key-invalidation) | Enabled. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |
| [Partition R/O](../Tutorial/parameter_descriptions#partition-read-only) | Disabled. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |
| [Session Objects](../Tutorial/parameter_descriptions#session-objects) | Enabled. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |
| [Object Destruction](../Tutorial/parameter_descriptions#object-destruction) | Enabled. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration).  |
| [Object Usage](../Tutorial/parameter_descriptions#object-usage) | Disabled. |

## Service Management

The CloudHSM BYOK partition offers versatile management options to make changes to the [partition policy setting](byok.md#partition-policy-settings). Users can utilize the Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration) or submit change requests on the [Support Portal](https://support.securosys.com/external).
