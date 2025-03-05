---
sidebar_position: 4
title: Configuring CyberArk with Security HSM
sidebar_label: Configuration
description: Step-by-step guide to configure CyberArk Primary Vault with Securosys CloudHSM or Primus HSM, including firewall settings, PKCS#11 provider setup, and HSM connection.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Configuring CyberArk Vault & HSM

## Configuring the CyberArk Vault firewall

The CyberArk Vault firewall must be configured to allow outgoing traffic to the HSM.

- Open the `DBParm.in` file located in **`C:\Program Files (x86)\PrivateArk\Server\Conf`**

- To open the firewall for traffic to your Securosys CloudHSM or on-premise HSM, add the **`AllowNonStandardFWAddresses`** parameter. 

:::tip
If you use a cluster of HSM devices, all their network connections must  be configured in the **`DBParm.ini`** file. For each HSM connected to your environment another **`AllowNonStandard-FWAddresses`** parameter should be added.
:::

```sh
AllowNonStandardFWAddresses=[hsm0-ip-url], Yes,2410:outbound/tcp
AllowNonStandardFWAddresses=[hsm1-ip-url], Yes,2410:outbound/tcp
```

|Parameter|Description|
|---|---|
|[hsm-ip-url] | Enter the destination URL or IP of your [CloudHSM](/connectivity-details/cloudhsm-connectivity-details) service or Primus HSM. "[]" Brackets must remain.|
|2410 |Enter the configured HSM PKCS#11 port (default for CloudHSM is 2410).|

## Configure PKCS#11 Provider Path

Set the PKCS#11 Provider path for the Primus PKCS#11 Provider installed before.

- Ensure the Securosys PKCS#11 provider configuration file **`Primus.cfg`** is configured correctly. Specify the Securosys PKCS#11 provider library with the **`PKCS11ProviderPath`** parameter in the **`DBParm.ini`** file, as shown in the following example:
```sh
PKCS11ProviderPath=C:\Program Files\Securosys\Primus P11\primus.dll
```
- Restart the PrivateArk Server.

## Configuring CyberArk Vault and Primus HSM Connection

Next, the PKCS#11 password used in the Primus PKCS#11 connection establishment must be configured on CyberArk Vault.

- Navigate to the PrivateArk Server directory. Within the directory open a command prompt with administrative privileges.
- Run the following command, replace the **`Password`** variable with the **`pkcs11-password`** (set when configuring the HSM):

```sh
CAVaultManager.exe SecureSecretFiles /SecretType HSM /Secret Password
```
- Open the **`DBParm.ini `** file located at **`C:\Program Files (x86)\PrivateArk\Server\Conf`**
- Confirm that the **`HSMPinCode`** parameter with the encrypted value of the passcode can now be seen. Example:
```js
…
[SYSLOG]
…
HSMPinCode=22B537EB552OADHG92FF89C0AB35027381FD40204HJVPDNWDFO3IR2CE15C6D55751583C4E66190F6BC6D38DFD16E3EC6455DIHF29EHF50485ED6797C293C04200AB2F
```
:::note
Now the setup is complete and you can [use CyberArk Vault with the HSM](/cyberark/Tutorials/Securing-Vault-Server-Key/Generate-Vault-Key-on-HSM.md).
:::