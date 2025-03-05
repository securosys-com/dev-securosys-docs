---
sidebar_position: 3
title: Installing Microsoft Azure CLI
sidebar_label: Azure CLI Installation
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Azure CLI Installation

## Installing Microsoft Azure CLI

To run the commands mentioned in this guide, install the Microsoft Azure CLI on a computer connected to the Internet, according to [Microsoft Install Azure CLI documentation](https://docs.microsoft.com/cli/azure/install-azure-cli). 

Alternatively, the procedure can be performed via Azure Portal GUI or Key Vault SDK.

## Prepare Azure for BYOK

Before moving onto the BYOK procedure the Microsoft Azure portal must have an existing:
- Azure resource group,
- Key vault.

Below is an example on how to prepare your Azure portal for the BYOK procedure. Please change the parameters according to your environment. For more information about Azure resource groups and key vaults, please visit [Microsoft Azure Documentation](https://learn.microsoft.com/en-us/azure/key-vault/).

:::note
This procedure can be skipped if you have already created a `Key Vault`and `Resource Group` beforehand. The parameters below are set and marked as an `example`, make sure to replace them with your own parameters.
:::

Login into azure portal using Windows PowerShell:
```bash
PS C:\Users\Public\byok> az login
```

Create a new resource group `SecurosysPrimusGroup` in Azure `switzerlandnorth` location:
```bash
az group create --name "SecurosysPrimusGroup" --location "switzerlandnorth"
```
Successful output:
```bash
{
  "id": "/subscriptions/a5082f52-9078-430f-9dab-a7df63921aad/resourceGroups/SecurosysPrimusGroup",
  "location": "switzerlandnorth",
  "managedBy": null,
  "name": "SecurosysPrimusGroup",
  "properties": {
    "provisioningState": "Succeeded"
  },
  "tags": null,
  "type": "Microsoft.Resources/resourceGroups"
}
```

Create a new key vault named `SecurosysPrimusKeyVault`:

```bash
az keyvault create --location switzerlandnorth --name SecurosysPrimusKeyVault --resource-group SecurosysPrimusGroup --sku premium
```
Successful output:
```bash

{
  "id": "/subscriptions/a5082f52-9078-430f-9dab-a7df63921aad/resourceGroups/SecurosysPrimusGroup/providers/Microsoft.KeyVault/vaults/SecurosysPrimusKeyVault",
  "location": "switzerlandnorth",
  "name": "SecurosysPrimusKeyVault",
  "properties": {
    "accessPolicies": [
      {
        "applicationId": null,
        "objectId": "545b8465-9410-47f6-89d9-34fcc091e1ab",
        "permissions": {
          "certificates": [
            "get",
            "list",
            "delete",
            "create",
            "import",
            "update",
            "managecontacts",
            "getissuers",
            "listissuers",
            "setissuers",
            "deleteissuers",
            "manageissuers",
            "recover"
          ],
          "keys": [
            "get",
            "create",
            "delete",
            "list",
            "update",
            "import",
            "backup",
            "restore",
            "recover"
          ],
          "secrets": [
            "get",
            "list",
            "set",
            "delete",
            "backup",
            "restore",
            "recover"
          ],
          "storage": [
            "get",
            "list",
            "delete",
            "set",
            "update",
            "regeneratekey",
            "setsas",
            "listsas",
            "getsas",
            "deletesas"
          ]
        },
        "tenantId": "5c75641c-0efa-4412-842a-7a6bb552cbe1"
      }
    ],
    "createMode": null,
    "enablePurgeProtection": null,
    "enableRbacAuthorization": null,
    "enableSoftDelete": true,
    "enabledForDeployment": false,
    "enabledForDiskEncryption": null,
    "enabledForTemplateDeployment": null,
    "networkAcls": null,
    "privateEndpointConnections": null,
    "provisioningState": "Succeeded",
    "sku": {
      "name": "premium"
    },
    "softDeleteRetentionInDays": 90,
    "tenantId": "5c75641c-0efa-4412-842a-7a6bb552cbe1",
    "vaultUri": "https://securosysprimuskeyvault.vault.azure.net/"
  },
  "resourceGroup": "SecurosysPrimusGroup",
  "tags": {},
  "type": "Microsoft.KeyVault/vaults"
}
```

Alternatively, the procedure can be performed via Azure Portal GUI or Key Vault SDK. 

With a successful installation please proceed to the [Tutorial](/microsoft-byok/Tutorials/Generate-KEK) section to continue the guide.