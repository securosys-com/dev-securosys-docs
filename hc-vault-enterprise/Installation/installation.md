---
sidebar_position: 2
title: Auto Unseal Integration for HC Vault Enterprise
sidebar_label: Auto Unseal Integration
description: Auto Unsealing using Wrapped Master Key from HSM
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Auto Unsealing using your HSM

A Vault server usually starts in a **sealed** state. Unsealing is the process of obtaining the master key to decrypt the vault data.

The default Vault configuration uses _Shamir’s Secret Sharing (SSS)_ to reconstruct the unseal key from multiple split key parts (shards). Auto Unseal with your Hardware Security Module (HSM) simplifies the management of the unseal key and enables an instant unsealing process using the wrapped master key stored in the HSM.

:::note
The command line extracts in this document are using **RHEL platform (Linux)** syntax.
:::

## Seal Migration to Auto Unseal

When a Vault server is started, it normally starts in a sealed state where a quorum of existing unseal keys is required to unseal it. By integrating Vault with an HSM, your Vault server can be automatically unsealed by the trusted HSM key provider.

1. Stop the Vault by executing the command:
```bash
systemctl stop vault
```

2. To integrate your Vault Enterprise server with your HSM cluster, the configuration file must define the [PKCS11 seal stanza](https://developer.hashicorp.com/vault/docs/configuration/seal/pkcs11).providing necessary connection information. Use below shown Vault configuration block example to enable initial generation of keys by the Vault. Replace your parameters according the parameter description.

```
seal "pkcs11" {
lib 	= "/usr/local/primus/lib/libprimusP11.so"
slot 	= "0"	# slot id
pin 	= "PRIMUSDEV"	# PKCS11 password
key_label 	= "Vault-01-20210622"
mechanism 	= "0x1087"	# CKM_AES_GCM
generate_key 	= "true"	# generate if not existing
}
```

Parameters description:
- `lib`          	Primus PKCS#11 library file including path,
- `slot`         	PKCS#11 partition slot id, 
- `token_label` 	PKCS#11 partition name (slot or token_label is required),
- `pin`         	PKCS#11 password,
- `key_label` 	    name of the key to use,
- `mechanism`	    cryptographic mechanism to use for encryption/decryption,
- `generate_key`	true: instructs Vault to generate a key “key_label” if none is found.

:::info Environment variables
Alternatively, the parameters can also be defined using environment variables. For further details and parameters, see [this documentation](https://www.vaultproject.io/docs/configuration/seal/pkcs11#pkcs11-parameters).
:::

3. Optionally, the wrapping key can be pre-generated using other tools. Example provided below uses `pkcs11-tools` utility with example parameters. Adapt these according your utility of choice and configured parameters.

```
pkcs11-tool --module /usr/local/prius/lib/libprimusP11.so --slot 0 -l -p PRIMUSDEV --keygen --key-type aes:32 --id 2 --label Vault-01-20210628 -y secrkey --sensitive 
```

4. Use the below commands to restart the vault:

```
systemctl start vault
```

and then unseal it, and migrate to auto unseal.

```
vault operator unseal -migrate
```

:::tip
For more details about seal migration to auto unseal consult the [HashiCorp Vault documentation](https://www.vaultproject.io/docs/concepts/seal#seal-migration).
:::
