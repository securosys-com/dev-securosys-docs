---
sidebar_position: 4
title: CloudHSM Sandbox - Development & testing
sidebar_label: Sandbox (SBX)
description: CloudHSM Sandbox - Fully managed service for development. Integrate with APIs, enhance workflows with debugging, and optimize performance with Securosys Engineering expertise.
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]

---

# Sandbox (SBX)

The **CloudHSM Sandbox (SBX)** service offers a specific environment for seamless integration and testing with CloudHSM. It offers an equivalent user space to [CloudHSM Economy](./economy.md), featuring dual HSM synchronization but without performance guarantees.

It provides detailed device log access for both users and Securosys Support, facilitating efficient troubleshooting. CloudHSM Sandbox is perfect for preparing any **Primus HSM** or **CloudHSM** integration and receives HSM firmware updates first, enabling both [Economy](./economy.md) and [Platinum](./platinum.md) users to conduct pre-tests before general rollouts.

:::warning
- CloudHSM Sandbox (SBX) is designed exclusively for integration and testing purposes. It offers a secure and controlled environment where developers and subscribers can experiment with new features, configurations, and workflows without affecting production systems.
- CloudHSM Sandbox (SBX) **is not intended for productive use**; it is strictly for non-productive usage, operating with a simplified segregation of duties.
  :::

## Service Description

This service provides access to Securosys Cloud HSM Service partitions with the following attributes:

| Attribute | Description |
| --- | --- |
| Client Connections | Not limited |
| Storage Capacity | 10MB (up to 200 RSA-4096 asymmetric key pairs, 5MB reserved for user audit logs). Additional Storage Capacity is available in increments of 100 MB  |
| Performance | Best Effort |
| Key Generation | Best Effort |
| Cryptographic APIs | [PKCS#11](../../pkcs/overview), [Java (JCA/JCE)](../../jce/overview), [Microsoft CNG](../../mscng/overview) or [REST](../../tsb/overview) |
| Supported Functions | See the [Supported Algorithms and Functions](../Overview/Supported_Algorithms_and_Functions) list |
| Operational Mode | non-FIPS |

## Service Options

In addition to the service description provided above, the following table outlines the available options and indicates whether they are currently enabled, disabled, or can be optionally selected:

| Option | Availability |
| --- | --- |
| [Attestation and Partition Audit](../Overview/features.md#key-attestation) | Enabled |
| [Partition Administration](../Overview/features.md#hsm-partition-remote-administration) | Option. Requires purchase or rent of Decanus Terminal |
| [Smart Key Attributes (SKA)](../Overview/features.md#smart-key-attributes-ska) | Enabled |
| [Transaction Security Broker (TSB)](../Overview/features.md#transaction-security-broker-tsb) | Enabled |
| [Cryptocurrencies](../Overview/features.md#cryptocurrencies) | Enabled | 
| [Post-Quantum Cryptographic Algorithms](../Overview/features.md#post-quantum-cryptographic-pqc-algorithms) | Enabled |

## Regions

SBX is accessible through either a Regional Swiss (expanding soon to a global) cluster, ensuring optimal reach and performance tailored to specific geographic needs. This distribution is detailed in the table below.

| Service Package | Data Center locations | Active DC | Business Continuity DC |
| --- | --- | --- | ---|
| Sandbox (SBX), Switzerland |Switzerland, USA, Singapore | CH01, CH02, US02, SG01 | - |

## Partition Policy Settings

The following tables provide an overview of all partition policy settings, indicating whether they are enabled, disabled, or available for selection by the customer upon ordering and wether they can be modified afterwards.

## API Settings

| API Activation | Availability |
| --- | --- |
| [PKCS#11](../../pkcs/overview) | Selectable upon ordering |
| [Java (JCA/JCE)](../../jce/overview) | Included; can be enabled/disabled upon ordering |
| [Microsoft CNG](../../mscng/overview) | Included; can be enabled/disabled upon ordering |
| [REST](../../tsb/overview) | Included; can be enabled/disabled upon ordering |

## Partition Settings

| Policy| Availability |
| --- | --- |
| [Key Import](../Tutorial/parameter_descriptions#key-import) | Selectable upon ordering. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |
| [Key Export](../Tutorial/parameter_descriptions#key-export) | Selectable upon ordering. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |
|[Key Invalidation](../Tutorial/parameter_descriptions#key-invalidation) | Selectable upon ordering. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |
| [Partition R/O](../Tutorial/parameter_descriptions#partition-read-only) | Disabled. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |
| [Session Objects](../Tutorial/parameter_descriptions#session-objects) | Enabled |
| [Object Usage](../Tutorial/parameter_descriptions#object-usage) | Enabled. Modifiable via [Support Portal](https://support.securosys.com/external) or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration). |

## Service Management

The CloudHSM SBX partition offers versatile management options to make changes to the [partition policy setting](sandbox.md#partition-policy-settings). Users can utilize the Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration) or submit change requests on the [Support Portal](https://support.securosys.com/external).
