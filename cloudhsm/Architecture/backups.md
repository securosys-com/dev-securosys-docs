---
sidebar_position: 4
title: Securosys CloudHSM Backups explained
sidebar_label: Backups
description: CloudHSM guarantees data integrity with automated cluster synchronization, key invalidation, daily backups, and secure, encrypted offline storage.
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

# Backups

The synchronization of subscriber key data within the HSM cluster operates seamlessly, ensuring both redundancy and data integrity. This automatic process guarantees that vital cryptographic assets are consistently mirrored across multiple HSMs in the cluster, enhancing resilience against potential system failures or data loss scenarios.

Additionally, to safeguard against inadvertent deletion or loss of keys, [key invalidation](/cloudhsm/Overview/features.md#key-invalidation) functionality is enforced within the partitions by default, unless specifically opted out of by the client. This measure serves as a protective barrier, preventing customers from accidentally erasing keys via the API, thereby enhancing data security and mitigating operational risks.

### Automated HSM backup

For our CloudHSM [Economy (ECO)](../Packages/economy), [Certified](../Packages/economy_cc) and, where applicable, [Platinum](../Packages/platinum) service packages, an automated backup of the HSMs located in our [backup data center](./overview.md) is performed daily via WebDAV. This ensures that all critical data is securely backed up on a regular basis. The backups are encrypted and can only be restored to the same HSM cluster. They are retained for a period of 10 days, providing a reliable and efficient way to recover data in the event of any issues or failures. This daily backup process is a key component of our comprehensive disaster recovery strategy, ensuring the integrity and availability of your cryptographic keys and sensitive information.

:::tip Important
Securosys does not create other backups of HSM partition data, including key objects, outside the confines of the HSM cluster, except in certain circumstances necessitated by specific maintenance activities. In such cases, backups are encrypted and securely stored on USB storage devices, rendering them inaccessible to unauthorized entities. These encrypted backups are stored offline in a vault and are deleted after 180 days, at the latest, to adhere to data retention policies and uphold stringent security standards.
::: 

### Partition Data Restoration Service

Partition data of tenants can be restored upon request through our [Support Portal](https://support.securosys.com). Restoration requests can be made within the aforementioned retention period and will be processed at an additional cost. This service provides an added layer of security and peace of mind, knowing that your data can be retrieved quickly and efficiently when needed.

### Self-Service Backup and Key Export Options

For additional flexibility and control, subscribers can back up partition data outside the CloudHSM platform using either the [partition management interface](/cloudhsm/Overview/features.md#hsm-partition-remote-administration) or programmatically via the API. However, it is the subscriber’s responsibility to implement appropriate security measures to safeguard these backups effectively. Importantly, data export via the API is possible if the keys are explicitly designated as exportable during their creation and if the [partition security policy setting](../Tutorial/parameter_descriptions#partition-security-policy-settings) permits [key export](../Tutorial/parameter_descriptions#key-export).