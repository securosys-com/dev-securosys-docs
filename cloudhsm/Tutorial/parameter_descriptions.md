---
sidebar_position: 1
title: CloudHSM Partition Policies
sidebar_label: Partition Policies
description: Cloud-based hardware security modules (HSMs) for generating and using your own encryption keys with your applications. FIPS 140-2 Level 3 validated HSMs
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---
# Partition Policies

## Partition Security Policy Settings

### Key Import

Key Import allows/blocks on partition level to import keys in plain or wrapped format, e.g. import of existing subscriber key material or restoration of exported keys.

**Possible settings:** Allowed / Not allowed

:::note
It is recommended that key material is generated and hold inside HSM. Certain regulations require “never extractable” keys.
:::

### Key Export

Key Export allows/blocks on partition level to extract keys in plain or wrapped format, e.g. for backup.

**Possible settings:** Allowed / Not allowed

:::note
Exporting keys is a sensitive activity and requires cautious handling. The export and secure external storage of subscriber data is subscriber’s sole responsibility.
:::

### Key Invalidation

The Key Invalidation feature prevents from permanently deleting key objects via API, e.g. accidental deletion. It works as a bin. Key objects deleted via API are marked as invalidated and appear to be deleted to the API but can be restored or deleted permanently by the Security Officer or Partition Administration Security Officer only.

**Possible settings:** Enabled / Disabled

:::note
Invalidated key objects still consume partition space and key IDs remain used.
:::

### Partition Read-Only

Read-only partition only allow usage of keys via API (no creation, modification, or deletion of keys via API).

**Possible settings:** Enabled / Disabled

:::note
Read only access to the partition is disabled by default and can be subsequently modified through the [Support Portal](https://support.securosys.com/external), or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration).
:::

### Session Objects

Support of Session Objects. Ephemeral keys are used outside of HSM, encrypted with a per partition key (non-extractable, not accessible) and deleted at the end of the client session. These keys are not stored persistently on the HSM cluster.

**Possible settings:** Enabled / Disabled

### Object Destruction

Object Destruction allows or prevents from deletion via API calls. IF set to disabled (false), keys cannot be deleted (delete will always fail).

**Possible settings:** Enabled / Disabled

### Object Usage

The Object Usage setting controls the availability of secret and private keys. If it is disabled, these keys cannot be accessed or utilized for any cryptographic operations, ensuring they remain secure and inactive.

**Possible settings:** Enabled / Disabled

:::note Note 1
Cryptographic operations are sign, verify, encrypt, decrypt, derive.
:::

:::note Note 2
Wrap and unwrap are controlled by key import and export settings and not considered cryptographic operations..
:::

### Client API Access

The setting Client API Access enables access to the device key store for all client APIs (e.g., JCE, PKCS#11 and MSCNG).

**Possible settings:** Enabled / Disabled

:::note
Access to the Client API is enabled by default and can be subsequently modified through the [Support Portal](https://support.securosys.com/external), or Decanus Terminal via [Partition Administration](../Overview/features.md#hsm-partition-remote-administration) to take the partition offline.
:::
