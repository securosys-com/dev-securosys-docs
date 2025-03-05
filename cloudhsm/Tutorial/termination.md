---
sidebar_position: 4
title: Securosys CloudHSM Subscription Termination
sidebar_label: Termination
description: Terminate your CloudHSM subscription with options - transfer to on-premises HSM, export/delete keys via API, or delete partition data securely.
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

# Termination

To terminate a **CloudHSM** subscription, you may choose from four options:

- **Transfer data (partition) to an on-premises Primus HSM device.** Securosys will bill separately for the cost of the HSM and the transfer service.
- **Export (either wrapped or unwrapped) and delete keys via API.** The specific method used will depend on the key flags.
- **Migrate data (partition) to an on-premises Primus HSM device using a Partition Administration SO.** Securosys will not charge for this service.
- **Request that Securosys delete the partition.** Upon termination, Securosys will delete your HSM key data (partition). Backup data will be kept confidential and deleted during regular backup cycles.

If you initiate the termination, Securosys will bill separately for the cost of the HSM and the transfer service. If the termination is initiated by Securosys, the company will actively assist you to migrate your data to your own Primus HSM device without any additional charges.