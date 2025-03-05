---
sidebar_position: 5
title: CloudHSM Platinum - Dedicated HSM in the cloud
sidebar_label: Platinum
description: CloudHSM Platinum - Customize your HSM redundancy with exclusive Primus HSM. Secure your data with customizable partitions in the CloudHSM environment.
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

# Platinum

**CloudHSM Platinum** offers dedicated and expandable Hardware Security Modules (HSMs) with a default cluster sizes of two, and deployment locations at your choice in our datacenters. Every subscription comes with a single partition, and extra partitions can be obtained.

For more information, please [contact sales](https://www.securosys.com/en/contact).

## Service Description

This service provides access to Securosys Cloud HSM Service partitions with the following attributes:

| Attribute | Description |
| --- | --- |
| Client Connections | Not limited |
| Storage Capacity | Platinum: 120MB <br/> *Additional Storage Capacity is available in increments of 120MB* |
| Performance | Platinum: Up to 12000 operations (RSA-4096) per minutes |
| Key Generation | Max. 1 key per second |
| Cryptographic APIs | [PKCS#11](../../pkcs/overview), [Java (JCA/JCE)](../../jce/overview), [Microsoft CNG](../../mscng/overview) or [REST](../../tsb/overview) |
| Supported Functions | See the [Supported Algorithms and Functions](../Overview/Supported_Algorithms_and_Functions) list |
| Operational Mode | Normal mode (non-FIPS) *or* Strict FIPS mode and Common Criteria compliant at choice |

## Service Options

In addition to the service description provided above, the following table outlines the available options and indicates whether they are currently enabled, disabled, or can be optionally selected:

| Option | Availability |
| --- | --- |
| [Attestation and Partition Audit](../Overview/features.md#key-attestation) | Enabled |
| [Partition Administration](../Overview/features.md#hsm-partition-remote-administration) | Option. Requires purchase or rent of Decanus Terminal |
| [Smart Key Attributes (SKA)](../Overview/features.md#smart-key-attributes-ska) | Enabled |
| [Transaction Security Broker (TSB)](../Overview/features.md#transaction-security-broker-tsb) | Option |
| [Cryptocurrencies](../Overview/features.md#cryptocurrencies) | Option | 
| [Post-Quantum Cryptographic Algorithms](../Overview/features.md#post-quantum-cryptographic-pqc-algorithms) | Enabled |
| [Timestamp Service (RFC3161 compliant)](../Overview/features#timestamp-service) | Enabled |

## Regions

PLATINUM is accessible according user order through either a Global or Regional cluster, with options including Swiss, German, US, or Singapore clusters, ensuring optimal reach and performance tailored to specific geographic needs.

## Partition Policy Settings

The following tables provide an overview of all partition policy settings, indicating whether they are enabled, disabled, or available for selection by the customer upon ordering and wether they can be modified afterwards.

## API Settings

| API Activation | Availability |
| --- | --- |
| [PKCS#11](../../pkcs/overview) | Included; can be enabled/disabled upon ordering |
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

The CloudHSM PLATINUM partition offers versatile management options to make changes to the [partition policy setting](economy.md#partition-policy-settings). Users can utilize the Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration) or submit change requests on the [Support Portal](https://support.securosys.com/external).
