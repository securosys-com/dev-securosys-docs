---
sidebar_position: 0
title: Getting Started with Secrets Engine Plugin for HashiCorp Vault
sidebar_label: Quick Start Guide
description: Getting Started with Secrets Engine Plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---

# Quick Start

This quick start guide provides a comprehensive task listing to download, setup and use [Securosys' Secret Engine plugin](../Concepts/secrets_engine_concept.md) for HashiCorp Vault.

1) Download and install [Hashicorp Vault](https://developer.hashicorp.com/vault/install)

1) Download the [Secrets Engine plugin](https://securosys.jfrog.io/artifactory/hcvault-plugin-secrets-engine/) from Securosys' jfrog repository:<br/>
Login: ```robot.reader.hashicorpvault```<br/>
Password: ```REPLACE_ME_WITH_PASSWORD```
1) Add the ```plugin_directory``` parameter to ```config.hcl```, if it does not already exit.
For example:
```plugin_directory=/home/test/vault/plugins```
1) Copy the appropriate plugin binary to the plugin directory.
1) Run the command<br/>
```$ vault plugin register -sha256={binary_checksum} secret securosys-hsm ```<br/>
where ```{binary_checksum}``` is checksum of the plugin binary

:::info Binary checksum

The **binary checksum** is pre-generated and stored within each build version of this plugin.

:::

6) After successful registration, run command to enable the plugin<br/>
```$ vault secrets enable securosys-hsm```

7) The final step is to setup the configuration to connect with Transaction Security Broker (TSB).
For example, using a Bearer Token: 
```
$ vault write securosys-hsm/config 
auth="TOKEN" 
bearertoken="jwt token string"
restapi="https://primusdev.cloudshsm.com"
```
:::note tip
More examples of the plugin configuration can be found here: **[Configure the plugin](/hc_sse/Installation/configuration)**
:::

For more detailed instructions, please refer to:
- [Installation](/hc_sse/category/installation)
- [Tutorials](/hc_sse/category/tutorial)