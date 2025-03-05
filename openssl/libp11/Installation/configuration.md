---
sidebar_position: 3
title: Configuring PKCS#11 for OpenSSL v1.x
sidebar_label: Configuration
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL,PKCS#11]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Configuring PKCS#11 for OpenSSL v1.x

To use the PKCS#11 Engine Plugin, you must first configure OpenSSL to recognize and load the engine. This process involves setting up OpenSSL to work with the OpenSC PKCS#11 module through the PKCS#11 Engine Plugin, allowing OpenSSL to communicate effectively with your Primus HSM or CloudHSM instance via the PKCS#11 interface. To do this, locate your global OpenSSL configuration file and open it in your preferred text editor. The file is typically found at the following locations:

- **Windows:** `C:\Program Files\Common Files\SSL`
- **Linux:** `/etc/ssl/openssl.cnf`

Add the following line at the beginning of the file:

```ini
openssl_conf = openssl_init

…
```

Then add the following lines at the end of the file:

<Tabs groupId="os">
<TabItem value="os-windows" label="Windows 11" default>

```ini
…

[openssl_init]
engines = engine_section

[engine_section]
pkcs11 = pkcs11_section

[pkcs11_section]
engine_id = pkcs11
dynamic_path = "C:\\Users\\<Username>\\source\\repos\\libp11\\src\\pkcs11.dll"
MODULE_PATH = "C:\\Program Files\\Securosys\\Primus P11\\primusP11.dll"
PIN = <PKCS11 PIN>
init = 0 
```

</TabItem>

<TabItem value="os-linux" label="Linux">

```ini
…

[openssl_init]
engines = engine_section

[engine_section]
pkcs11 = pkcs11_section

[pkcs11_section]
engine_id = pkcs11
dynamic_path = "/usr/lib/ssl/engines/libpkcs11.so"
MODULE_PATH = "/usr/local/primus/lib/libprimusP11.so"
PIN = <PKCS11 PIN>
init = 0 
```

</TabItem>
</Tabs>

| Parameter | Description |
| --- | --- |
| `engine_id` | An arbitrary identifier for OpenSSL applications to select the engine by the identifier. |
| `dynamic_path` | The path to the PKCS#11 Engine Plugin. <br/><br/>Replace `dynamic_path` with the path to the OpenSC PKCS#11 Engine Plugin from the [Installation](installation.md#pkcs11-engine-plugin) section.<br/>- On **Windows**, this will be the designated folder on your PC that you selected upon installation, e.g. `C:\Users\<Username>\source\repos\libp11\src\pkcs11.dll`.<br/>- On **Linux**, this is typically: `/usr/lib/ssl/engines/libpkcs11.so`. |
| `MODULE_PATH` | The path to the Primus PKCS#11 Provider.<br/><br/>Replace `MODULE_PATH` with the path to the PKCS#11 provider module file.<br/>- On **Windows**, this is typically: `C:\Program Files\Securosys\Primus P11\primusP11.dll`<br/>- On **Linux**, this is typically: `/usr/local/primus/lib/libprimusP11.so`|
| `<PIN>` | The PKCS#11 pin code of your token.<br/><br/>Replace `<PIN>` with the [PKCS#11 PIN](../../../pkcs/Installation/primus_hsm_settings#preparing-the-pkcs11-password-pin) of your HSM partition. |

## Testing the engine operation

To verify that the PKCS#11 engine is operating correctly, you can run the following OpenSSL command:

```bash
openssl engine pkcs11 -t
```

If the engine is functioning as expected, OpenSSL will output the following message:

```bash
(pkcs11) pkcs11 engine
     [ available ]
```
