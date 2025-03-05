---
sidebar_position: 0
title: Azure BYOK - Generate Key Exchange Key
sidebar_label: Generate Key Exchange Key
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Generate Key Exchange Key

The KEK is an RSA key that’s generated in a Microsoft Key Vault HSM. The KEK is used to encrypt the key you
want to import (target key). The KEK must be:
- An RSA-HSM key (2’048-bit, 3’072-bit or 4’096-bit),
- Generated in the same key vault where you intend to import the target key,
- Created with allowed key operations set to `import`.

:::note 
Make sure to change the parameters in the example commands to your own.
:::

To generate the KEK, open the PowerShell and logon to Azure portal with the required subscription
for Azure Key Vault:

```bash
PS C:\Users\Public\byok> az login
```

In case you need to create a new resource group and key vault, refer to [Installation](/microsoft-byok/Installation/Installation.md).

Use az keyvault key create command to create a Key Exchange Key (KEK) that has key operations set to `import`,
for example:
```bash
az keyvault key create --kty RSA-HSM --size 4096 --name KEKforBYOK --ops import --vault-name myKeyVaultHSM
```

When the KEK is created, note down the key identifier (kid) for the generated key as it will be used
later on, for example:
```bash
https://myKeyVaultHSM.vault.azure.net/keys/KEKforBYOK/d0dfd74d5bfc494abc572867b20e4260
```
## Download KEK

Use `az keyvault key download` command to download the KEK public key as `.pem` file.
For example:
```bash
az keyvault key download --name KEKforBYOK --vault-name myKeyVaultHSM --file KEKforBYOK.publickey.pem
```
Transfer the `KEKforBYOK.publickey.pem` file to your HSM Client computer. You will need this file
in the next step. The target key to import will be encrypted by using this KEK public key.
