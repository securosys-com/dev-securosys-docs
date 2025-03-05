---
sidebar_position: 3
title: Partition Management with Primus Tools
sidebar_label: Partition Management
description: Partition Management with Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Partition Management

The following commands are used for managing partitions (named `keystore` in commands) of the HSM. For managing objects within a partition see detailed commands in [Object Management](/primus-tools/Tutorials/command-details/object-management) section.

Commands will require an established `<HSM connection and credentials>` parameter to be able to execute properly. For further assistance on how to configure your HSM connection and credentials please see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider) section.

##	Get Partition Statistics

Get the number of objects and size of a specific partition:
```bash
java -jar primus-tools.jar GetKeyStoreStatistics <HSM connection and credentials>
```

Example output for partition `DEMO-TEST`:
```bash
user DEMO-TEST, private keys 10, public keys 10, secret keys 19, certificates 7, data objects 0, invalidated keys 0, used size 71680 bytes, max size 140712704 bytes, remaining objects 203236
```

## List Partition

Use the command to list the information of the specified partition:
```bash
java -jar primus-tools.jar ListKeyStore <HSM connection and credentials> [-times <times>] [-delay <seconds>] [-timeout <seconds>] [-progress] [-verbose]
```

## Clear Partition

:::warning
It is not possible to undo the clearing of the Key Store. Use the command with extreme caution.
:::

To clear the partition of all objects and keys use the following command:
```bash
java -jar primus-tools.jar ClearKeyStore <HSM connection and credentials> [-times <times>] [-delay <seconds>] [-timeout <seconds>] [-progress] [-verbose]
```

Confirm or cancel the clearing of the partition with `[Yes/No]`.




