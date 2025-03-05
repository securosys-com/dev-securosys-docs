---
sidebar_position: 7
title: Bring Your Own Key (BYOK) with Primus Tools
sidebar_label: Bring Your Own Key (BYOK)
description: Bring Your Own Key with Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Bring Your Own Key

This command section describes the Bring Your Own Key (BYOK) commands. These commands are used for different BYOK procedures. 

For more details on the command usage and procedures visit their respective documentation listed with each command.

Commands will require an established `<HSM connection and credentials>` parameter to be able to execute properly. For further assistance on how to prepare your `<HSM connection and credentials>` parameter, see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider.md) section.

## Microsoft Azure Key Vault BYOK
Used for Azure Bring Your Own Key (BYOK) procedure in combination with Primus HSM or CloudHSM.

Use the `AzureByokExport` command to create the `.byok` file to be imported into MS Azure Key Vault:

```bash
java -jar primus-tools.jar AzureByokExport <HSM connection and credentials> /
-kekidentifier <kek identifier name/etc, goes into the json> /
-kekfile <kek public key file, DER or PEM format> /
[-outfile <byok JSON output file>] / 
[-rsakey <HSM RSA key to wrap> [-rsakeypassword <HSM RSA key password>]] /
[-eckey <HSM EC key to wrap> [-eckeypassword <HSM EC key password>]] /
[-aeskey <HSM AES key to wrap> [-aeskeypassword <HSM AES key password>]] /
```

More info on [Azure - Bring Your Own Key](/microsoft-byok/overview).

## Amazon Web Services KMS BYOK

Used for AWS Key management service (KMS) Bring Your Own key (BYOK) in combination with Primus HSM or CloudHSM.

Use the `AwsKmsByokExport` command to wrap a specified key on the HSM by using the public key downloaded from AWS KMS. 
```bash
java -jar primus-tools.jar AwsKmsByokExport <HSM connection and credentials>
-aeskey AES-KEY-NAME [-aeskeypassword <HSM AES key password>] 
-kekfile <kek public key file, DER or PEM format> 
[-kekdata <kek public key data, base64 of DER>] 
-outfile <AWS KMS BYOK encrypted key output file>
```
More info on [AWS - Bring Your Own Key](/aws-byok/overview).
