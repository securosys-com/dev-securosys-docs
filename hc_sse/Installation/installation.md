---
sidebar_position: 2
title: Installing Secrets Engine plugin for HashiCorp Vault
sidebar_label: Install the plugin
description: Installing Securosy' Secrets Engine plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---

# Installing Secrets Engine plugin

## 1. Install Hashicorp Vault

Install Hashicorp Vault:
- [Installation guide](../../hc_vault/category/installation)

## 2. Register the plugin
Add the following parameter in the configuration file `config.hcl`:
- `plugin_directory` - must contain the absolute path to the directory where the plugins are stored 

Use the following command to register the plugin:
```shell
$ vault plugin register -sha256={binary_checksum} secret securosys-hsm
```

## 3. Enable the plugin

After building the plugin, it must be **enabled** with the following command before running it on the test server:
```shell
$ vault secrets enable securosys-hsm
```
The result should be:
```shell
$ Success! Enabled the securosys-hsm secrets engine at: securosys-hsm/
```


## 4. Upgrade the plugin

To upgrade a binary of an existing working plugin, follow these steps:
1) Copy the new plugin binary to the plugin_directory.

1) Register a new version of the plugin.
    ```shell
    $ vault plugin register -sha256={binary_checksum} -version={new-version} secret securosys-hsm
    ```

1) Tune the existing mount to reconfigure it to use the newly registered version.
    ```shell
    $ vault secrets tune -plugin-version={new-version}  securosys-hsm
    ```

1) Reload the plugin
    ```shell
    $ vault plugin reload -plugin securosys-hsm
    ```

