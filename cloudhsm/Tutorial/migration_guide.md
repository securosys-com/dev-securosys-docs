---
sidebar_position: 3
title: How to migrate from on-premise to CloudHSM?
sidebar_label: Migration Process
description: Step-by-step migration guide - Move your on-premises Primus HSM partition to CloudHSM. Prepare with firmware check, Decanus setup, and secure data transfer to Securosys
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

# Migration Process

This tutorial describes the process for migrating a partition from an **on-premises Primus HSM** to **CloudHSM** shared or dedicated cluster.

:::info
The process of migrating from **CloudsHSM** to **on-premises Primus HSM** is also possible. Please contact our [Sales team](https://www.securosys.com/en/contact) for more details.
:::

## Precondition

- Check firmware version of your on-premises Primus HSM (Device) and submit to Securosys via Support ticket.
- Decanus Terminal, Rel. v2.1.3 and later
- Decanus paired with Device (Device Administration)
- Decanus paired with Partition Administration (PA) of user to be migrated to CloudHSM - HSMaaS (shared or dedicated)
- Empty or already existing Partition-Backup Card “Yellow Card” (P-Backup Card)
- If Partition Administration is not setup: Empty Partition SO Card (PSO)
- Valid License with the following items licensed: (verify using:  SYSTEM > DIAG. DEVICE > LICENSE)
  - Partition Administration
  - Device Backup
  - Optional if working with Decanus: Decanus Remote Terminal
- Network connectivity of Decanus and on-prem Primus HSM (e.g. firewall rules)
- USB-thumbdrive to store partition backup file
- CloudHSM - HSMaaS shared service subscription or available partition on dedicate HSM service subscription

## On the on-premises Device proceed as follow with the Decanus

### Onboard Partition Administration (if you already have partition administration you can skip this step)

- SETUP > CONFIGURATION > SECURITY > USER > “Management access” > enabled
- Issue new Management pairing password
  - SO ACTIVATION > ENTER PIN of 1st SO > ENTER PIN of 2nd SO > ROLES > USER > MGMT Password > “PARTITION_NAME”
- Onboard Partition Administration
  - Switch Decanus Mode to "Partition Administration" 
  - ONBOARD > "Enter HSM URL/IP" > "Enter TCP Port; default 2300 JCE" > "Enter HSM Username" > "Enter Mgmt. Password" > INSERT empty Partition SO Card (PSO) on Slot 1 > ONBOARD

### Create Partition-Backup Card “Yellow Card”

- BACKUP > PARTITION BACKUP > P-BACKUP CARD > “PARTITION_NAME” > INSERT Yellow Card into Slot 2 > Enter new P-Backup Card Pin twice

### Create Partition Backup

- Switch Decanus Mode to "Partition Administration" (PA) > Login to Partition
- BACKUP > CREATE > Insert USB-stick, partition backup is stored to USB-memory, “Write down the Backup password”

## Sending Partition Backup, Partition-Backup Card "Yellow Card" and Backup password to Securosys Headquarter Switzerland

Transfer the just created items to Securosys:

- Send the Partition-Backup Card to Headquarter Securosys Switzerland: [https://www.securosys.com/en/contactus](https://www.securosys.com/en/contactus)
- Send the Partition Backup password to Securosys via secure Channel
- Send the Partition Backup to Securosys via secure Channel
