---
sidebar_position: 3
title: Configuring Secrets Engine plugin for HashiCorp Vault
sidebar_label: Configure the plugin
description: Configuring Securosy' Secrets Engine plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---

# Configure the plugin

To access your Securosys Hardware Security Module (HSM), configure the plugin with the following attributes (required:)

| **Attribute** | **Description** |
| - | - |
| **`auth`** | Attribute defines the authorization type to TSB. The value for this attribute can be `TOKEN`, `CERT` or `NONE` |
| **`restapi`** | REST API URL to access the REST/TSB endpoint (available from your Security Officer). For CloudHSM service see [Connectivity Details](/connectivity-details/cloudhsm-connectivity-details).|

Define additional attributes based on the selected authorization type `auth`: 

| **Authorization Type** | **Additional Attributes** |
| -| -|
| **`TOKEN`** | Add the attribute `bearertoken` with the JWT token |
| **`CERT`** |     Setup `certpath` with local PATH to the certificate and `keypath` with local PATH to the key. |
| **`NONE`** | No additional attributes required. |

Use the  following command to write configuration attributes for your HSM:
```shell
$ vault write securosys-hsm/config {config_attributes}
```
```shell
curl --location --request PUT '<server_addr>/v1/securosys-hsm/config' \
--header 'X-Vault-Token: <vault_access_token>' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode '{config_attribute_key}={config_attribute_value}' \
```
## Example for disabled authorization
```shell
$ vault write securosys-hsm/config 
auth="NONE" 
restapi="https://primusdev.cloudshsm.com"
```

```shell
curl --location --request PUT '<server_addr>/v1/securosys-hsm/config' \
--header 'X-Vault-Token: <vault_access_token>' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'auth=NONE' \
--data-urlencode 'restapi=https://primusdev.cloudshsm.com'
```
## Example for JWT token authorization
```shell
$ vault write securosys-hsm/config 
auth="TOKEN" 
bearertoken="jwt token string"
restapi="https://primusdev.cloudshsm.com"
```

```shell
curl --location --request PUT '<server_addr>/v1/securosys-hsm/config' \
--header 'X-Vault-Token: <vault_access_token>' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'auth=TOKEN' \
--data-urlencode 'bearertoken=jwt token string' \
--data-urlencode 'restapi=https://primusdev.cloudshsm.com'
```
## Example for Certificate authorization
```shell
$ vault write securosys-hsm/config 
auth="CERT" 
certpath="local_absolute_path_to_certificate.pem"
keypath="local_absolute_path_to_private.key"
restapi="https://primusdev.cloudshsm.com"
```

```shell
curl --location --request PUT '<server_addr>/v1/securosys-hsm/config' \
--header 'X-Vault-Token: <vault_access_token>' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'auth=CERT' \
--data-urlencode 'certpath=local_absolute_path_to_certificate.pem' \
--data-urlencode 'keypath=local_absolute_path_to_private.pem' \
--data-urlencode 'restapi=https://primusdev.cloudshsm.com'
```
:::tip Configuration update

Whenever there are changes to the configuration, the plugin will attempt to connect to the defined **Transaction Security Broker (TSB)** using the provided settings:
- If the connection is successful, the plugin will **write** or **overwrite** the configuration
- otherwise, the previous configuration will remain **unchanged**.

:::
