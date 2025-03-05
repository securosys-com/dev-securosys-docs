---
sidebar_position: 1
title: How to Generate a Vault Server Key on HSM for CyberArk
sidebar_label: Key Generation
description: Learn how to securely generate a CyberArk Vault Server Key on a Securosys CloudHSM or Primus HSM. Follow step-by-step instructions to ensure the key is safely stored and never exposed.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Generating a Vault Server Key on the HSM

:::warning CAUTION

The CyberArk Vault server key can be used with Securosys CloudHSM or on-premise Primus HSM using one of the two following methods:

- **Generating** the CyberArk Vault Server Key on the Securosys CloudHSM or Primus HSM
- **Importing** the CyberArk Vault Server Key onto the Securosys CloudHSM or Primus HSM

 It is heavily advisable to generate the Vault’s server key on the HSM as this way the private key is always kept in a secure environment and is never exposed.
 
 :::

New keys can be generated directly on the HSM device, without exposing the private key. 

- Stop the PrivateArk Server.
- Navigate to the Server directory. Within the directory open a command prompt with administrative privileges.
- (Optional) For a more verbose output when generating a new server key on the HSM use the following command:
```sh
Set CACryptoTrace=1
```
- Run the **`GenerateKeyOnHSM`** command to generate a new server key using **`CAVaultManager`**:
```sh
CAVaultManager.exe GenerateKeyOnHSM /ServerKey
```

Make sure that the result confirms that the server key was successfully generated on the HSM. You should see the following output:
```sh
ITADB399I Using encryption algorithms: Advanced Encryption Standard (AES), 256 bit, RSA (2048 bit), SHA2-512 (Protocol Integrity), SHA2-512 (Files Integrity).

ITADM114I Successfully connected to Database, Database id 0.

CAVLT187I Server Key was successfully generated on HSM device (KeyID=HSM#X).
```

Please write down the HSM Key Generation Number **`HSM#X`** returned in the command output, as it will be needed in the next steps.

- Mount the recovery private key (**`recprv.key`**) to the Vault server.
- Open the **`DBParm.ini`** file located in **`C:\Program Files (x86)\PrivateArk\Server\Conf`**

Set the **`RecoveryPrvKey`** parameter to point to the location of the recovery private key (path to recovery key) and save the file. Example:

```sh
[MAIN]
…
RecoveryPrvKey=D:\recprv.key
…
```

- Navigate to the Server directory. In the directory open a command prompt with administrative privileges.
- To change the existing server key to your newly generated key on the HSM device, run the below command with the following parameters:
```sh
ChangeServerKeys.exe <keys directory> <full path to VaultEmergency.pass> HSM#X
```
|Parameter|Description|
|---|---|
|\<keys directory> | Enter the directory where the Vault keys are located.|
|\<full path to VaultEmergency.pass>| Enter the full path to the Vault emergency password file.|
|HSM#X|	Replace the X with HSM Key Generation Number.|

Ensure the command output confirms that the server key change was successful, as seen in the following example of a successfully changed key:
```sh
CHSRVK054I ChangeServerKeys process was successful. DBParm.ini must be updated to point to new keys for Vault to start.

CHSRVK042I ChangeServerKeys process ended.
```
- Open the **`DBParm.ini`** file and set the **`ServerKey`** parameter. Replace **`HSM#X`** with your HSM Key Generation Number.

```sh
ServerKey=HSM#X
```

- Make sure to save the file **before** starting the PrivateArk Server.
- Start the PrivateArk Server and confirm that no errors are printed to the console.
- Verify that you can log on to the Vault using CyberArk authentication.
- Unmount the recovery private key from **`DBParm`** (revert to default value: **`d:\recprv.key`**).

The original server key is no longer in use, and a key safely secured in the HSM is now in operation.
