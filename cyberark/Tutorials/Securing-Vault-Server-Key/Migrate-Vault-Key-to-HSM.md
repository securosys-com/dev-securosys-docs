---
sidebar_position: 2
title: How to Migrate an Vault Server Key to HSM for CyberArk
sidebar_label: Key Migration
description: Migrate your existing CyberArk Vault server key to a Securosys CloudHSM or Primus HSM, ensuring secure key management.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Migrate a Vault Server Key to the HSM

:::warning CAUTION

The CyberArk Vault server key can be used with Securosys CloudHSM or on-premise Primus HSM using one of the two following methods:

- Generating the CyberArk Vault Server Key on the Securosys CloudHSM or Primus HSM
- Importing the CyberArk Vault Server Key onto the Securosys CloudHSM or Primus HSM

 It is heavily advisable to generate the Vault’s server key on the HSM as this way the private key is always kept in a secure environment and is never exposed.
 
 :::

Although not as secure as generating the key directy on the HSM, there might be situations where existing keys must be loaded onto HSM. In such cases proceed as below:

- Stop the PrivateArk Server.
- Navigate to the Server directory. In the directory open a command prompt with administrative privileges.
- (Optional) For a more verbose output when generating a new server key on the HSM use the following command:
```sh
Set CACryptoTrace=1
```
- Using **`CAVaultManager`**, run the **`LoadServerKeyToHSM`** command to upload the server key to store in the HSM server:
```sh
CAVaultManager.exe LoadServerKeyToHSM /WrapKey
```
The command generates a new key pair. The public key is used to encrypt the server key, and the private key decrypts it on the HSM device. The private key is deleted from HSM when the server key is un-wrapped.
The output result must inform you that the server key has been successfully uploaded to the HSM, as seen in the example below:
```sh
CAVLT143I Server Key was successfully uploaded to HSM device
```
- Open the **`DBParm.ini`** file located in **`Server\Conf`**.
Set the **`ServerKey`** parameter to use the Securosys CloudHSM:
```sh
ServerKey=HSM
```
- Make sure to save the file before starting the PrivateArk Server.
- Start the PrivateArk Server and verify that there are no errors in the console.
- Verify that you can log on to the Vault using CyberArk authentication.

This completes the migration of existing server key to Securosys CloudHSM or on-premise PrimusHSM.
