---
sidebar_position: 0
title: Credential Management with Primus Tools
sidebar_label: Credential Management
description: Credential Management with Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Credential Management

The credential management commands are required for managing the credentials of the HSM and the connection to it.

Commands will require an established `<HSM connection and credentials>` parameter to be able to execute properly. For further assistance on how to prepare your `<HSM connection and credentials>` parameter, see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider) section.

## Generate Blinding Key File

Generates a blinded key file generally used for blinding HSM connection and credential details.

:::warning
Secret blinding might impact operation in case of tool or vendor migration.
:::

Execute the command:
```bash
java -jar primus-tools.jar GenerateBlindingKeyFile <HSM connection and credentials> [-force]
```

## Configuring Permanent Secret 

As the temporary setup password will expire, you should retrieve the permanent secret (which does not expire) which is blinded to a file with the following command:

```bash
java -jar primus-tools.jar GetUserSecret <HSM connection and credentials> [-blinded] [-outputfile <output file name>] [-blindingkeyfile] (use the blinding key file "primus.blindingkeyfile")
```

## Blind Password

Passwords (e.g. proxy password, setup password, permanent secret) can be written blinded to files with the following command:

:::warning
Secret blinding might impact operation in case of tool or vendor migration.
:::

```bash
java -jar primus-tools.jar BlindPassword [options] <password>|consoleinput:|file:<path/name> [-outputfile <output file name>]/
 [-blindingkeyfile] (use the blinding key file "primus.blindingkeyfile")
```

## Login

Use the command `Login` to test out the HSM credentials and connectivity. 
```bash
java -jar primus-tools.jar Login <HSM connection and credentials> [-times <times>] [-delay <seconds>] /
[-timeout <seconds>] [-progress] [-verbose]
```

