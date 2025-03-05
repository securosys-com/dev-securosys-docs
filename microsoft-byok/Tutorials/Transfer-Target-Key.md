---
sidebar_position: 3
title: Azure BYOK - Transfer Target Key
sidebar_label: Transfer Target Key
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Transfer Target Key

To complete the key import, transfer the key transfer package (the `.byok` file) from your HSM Client (offline) computer to the Internet-connected computer. 

Use the `az keyvault key import` command to upload the `.byok` file to the Key Vault HSM. 

Example:

```bash
az keyvault key import --vault-name myKeyVaultHSM --name myPrimusByok --byok-file myPrimusRSA4k.byok
{
  "attributes": {
    "created": "2020-11-02T09:45:06+00:00",
    "enabled": true,
    "expires": null,
    "notBefore": null,
    "recoveryLevel": "Recoverable+Purgeable",
    "updated": "2020-11-02T09:45:06+00:00"
  },
  "key": {
    "crv": null,
    "d": null,
    "dp": null,
    "dq": null,
    "e": "AAEAAQ==",
    "k": null,
    "keyOps": [
      "encrypt",
      "decrypt",
      "sign",
      "verify",
      "wrapKey",
      "unwrapKey"
    ],
    "kid": "https://securosysprimuskeyvault.vault.azure.net/keys/SecurosysPrimusByokR4k/6c7eadd70c734e85928f58d60e83acd8",
    "kty": "RSA-HSM",
    "n": "zBnrPgwWrv9Q5y+/837rpWixWRgwTKy6pWRD9ba9HDA72ClbMqxzw/wpTbTioa187wu9jLa3sfACIycZDPYawF/DvaJfQQQdketG60gxDybqoBVBmmoHtbMjYDQMIFTcE0NEPRigL0FQNnKF0KC7zEVcPm7tVJL/FgX40TSiL6Sc
mYgOxqRICs3daSAW2Qg+hgfN4T5hSzKYji8cLHVGHCHI+7mdvwVdDevtWlL9nMQfoxbVIT+JFTOuUafz9TzqJpYLf2L2cUcXJJT/1H14xyhdsftP3HYgoQEzkdRBK/v/b9izafEFxKpkE82WBnHkADLjAaE6tKSfOFFNAaXNOj84rL4OwAcR7J
93WYNZRCBmXu/0tTFTwoiyxfMVK+hyUwyBeTyi/J5fmHS+vek84XKXxc+S9Zkw4EtyJwULopHwT58KUlEc9nVu+NNbJyxetx7ytSzVpKPp5Lf01EBwMQa+uZ4LpYaRAy3Rtw64Cvmq5yAFMAXOhtYA5yZ8xYjpy0Cldf+1k1SROThFFg5NQ4di
DpbSJJ1rvoO2Ub0Cy6t0z2X+S0yLFP91zn7bvmRg+z4pI5Tc+pKKYTlmFZbpnXuNqX7K8u3CMfildlRMfG9ELSImaR/mYrhy2kuUkkDzbHnETBfmjJOIhdVvNbd+7aB3VxsBGOzw1rGsP4C9qpc=",
    "p": null,
    "q": null,
    "qi": null,
    "t": null,
    "x": null,
    "y": null
  },
  "managed": null,
  "tags": null
}
```

If the upload is successful, Azure CLI displays the properties of the imported key. Alternatively, import can also be performed and controlled via Azure Portal.

## Verify Imported Target Key

Verify the existence of the imported target key with the command `az keyvault key show`.

Example:
```bash
az keyvault key show --vault-name SecurosysPrimusKeyVault --name SecurosysPrimusByokR4k
{
  "attributes": {
    "created": "2020-11-02T09:45:06+00:00",
    "enabled": true,
    "expires": null,
    "notBefore": null,
    "recoveryLevel": "Recoverable+Purgeable",
    "updated": "2020-11-02T09:45:06+00:00"
  },
  "key": {
    "crv": null,
    "d": null,
    "dp": null,
    "dq": null,
    "e": "AAEAAQ==",
    "k": null,
    "keyOps": [
      "encrypt",
      "decrypt",
      "sign",
      "verify",
      "wrapKey",
      "unwrapKey"
    ],
    "kid": "https://securosysprimuskeyvault.vault.azure.net/keys/SecurosysPrimusByokR4k/6c7eadd70c734e85928f58d60e83acd8",
    "kty": "RSA-HSM",
    "n": "zBnrPgwWrv9Q5y+/837rpWixWRgwTKy6pWRD9ba9HDA72ClbMqxzw/wpTbTioa187wu9jLa3sfACIycZDPYawF/DvaJfQQQdketG60gxDybqoBVBmmoHtbMjYDQMIFTcE0NEPRigL0FQNnKF0KC7zEVcPm7tVJL/FgX40TSiL6Sc
mYgOxqRICs3daSAW2Qg+hgfN4T5hSzKYji8cLHVGHCHI+7mdvwVdDevtWlL9nMQfoxbVIT+JFTOuUafz9TzqJpYLf2L2cUcXJJT/1H14xyhdsftP3HYgoQEzkdRBK/v/b9izafEFxKpkE82WBnHkADLjAaE6tKSfOFFNAaXNOj84rL4OwAcR7J
93WYNZRCBmXu/0tTFTwoiyxfMVK+hyUwyBeTyi/J5fmHS+vek84XKXxc+S9Zkw4EtyJwULopHwT58KUlEc9nVu+NNbJyxetx7ytSzVpKPp5Lf01EBwMQa+uZ4LpYaRAy3Rtw64Cvmq5yAFMAXOhtYA5yZ8xYjpy0Cldf+1k1SROThFFg5NQ4di
DpbSJJ1rvoO2Ub0Cy6t0z2X+S0yLFP91zn7bvmRg+z4pI5Tc+pKKYTlmFZbpnXuNqX7K8u3CMfildlRMfG9ELSImaR/mYrhy2kuUkkDzbHnETBfmjJOIhdVvNbd+7aB3VxsBGOzw1rGsP4C9qpc=",
    "p": null,
    "q": null,
    "qi": null,
    "t": null,
    "x": null,
    "y": null
  },
  "managed": null,
  "tags": null
}
```