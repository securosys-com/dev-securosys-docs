---
sidebar_position: 1
title: Getting started with Microsoft Azure BYOK
sidebar_label: Quickstart
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Getting Started with Azure BYOK

This Quickstart section provides a comprehensive task listing of the Bring Your Own Key (BYOK) process for Azure Key Vault. For more detailed instructions please consult the [Installation](/microsoft-byok/Installation/Prerequisites) section. Visit [Prerequisites](/microsoft-byok/Installation/Prerequisites) for the necessary preparations beforehand.

:::note
Parameters in this document are shown as an example. Replace these parameters with your own.
:::

## Install and Configure Primus Tools

Download, install and configure the Primus Tools on the computer with an established Primus HSM or CloudHSM connection. For more information visit [Primus Tools Installation](/microsoft-byok/Installation/PrimusTools).

## Azure Portal Login

Login into Azure portal using Windows PowerShell. For more information visit [Sign into the Azure CLI](https://learn.microsoft.com/en-us/cli/azure/get-started-with-azure-cli).

:::note
The Azure command examples in this document are provided with Azure CLI. If preferred Azure portal GUI can also be used.
:::

## New Resource Group

On Azure portal, create a new resource group, for example `SecurosysPrimusGroup`, and specify the Azure location, for example  `switzerlandnorth`. For more information visit [Create resource Groups](https://learn.microsoft.com/en-us/azure/azure-resource-manager/management/manage-resource-groups-portal). 


## Create New Key Vault

On Azure portal, create a new Key Vault on your Azure portal or use an existing one.

For more information visit [Create Key Vault](https://learn.microsoft.com/en-us/azure/key-vault/general/quick-create-portal).


## Create New Key Exchange Key

In Key Vault, generate a key (referred to as a Key Exchange Key (KEK)). The KEK must be an `RSA-HSM` key that has only the `import key` operation. Only Key Vault Premium SKU supports RSA-HSM keys. Note down the `kid` URL for later usage.

For more information visit the section [Generate Azure Key Encryption Key](/microsoft-byok/Tutorials/Generate-KEK.md).

:::note
The KEK must be in the same Key Vault where the target key will be imported.
:::

## Download KEK public key 

In Key Vault, download the public part of the KEK as `.pem` file, it will be used to wrap the target key.
For more information visit [Generate Azure Key Encryption Key](/microsoft-byok/Tutorials/Generate-KEK.md).

:::note
The private part of the KEK is (generated inside the Azure Key Vault HSM) is not exportable).
:::

## Create Target Key

Using Primus Tools, create the target key on the Primus HSM or CloudHSM. For more information visit [Create Target Key](/microsoft-byok/Tutorials/Generate-Target-key#create-target-key).

:::note
To create a target key other key generation tools/utilities can be used.
:::

## Generate BYOK package

Using Primus Tools command `AzureByokExport` wrap the target key and generate the `.byok` package on the client computer. For more information visit [Wrap Target Key](/microsoft-byok/Tutorials/Generate-Target-key#wrap-target-key).

## Import BYOK package

Import the `.byok` package from the client computer into the Azure Key Vault. For more information visit [Transfer Target Key](/microsoft-byok/Tutorials/Transfer-Target-Key).

## Verify Imported Target Key

Check the existence of the imported target key on Azure portal. If the verification was successful the key is now in use. For more information visit [Verify Imported Target Key](/microsoft-byok/Tutorials/Transfer-Target-Key#verify-imported-target-key).