---
sidebar_position: 2
title: Docker Signing Plugin configuration
sidebar_label: Configuration
description: Docker Signing Plugin configuration
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Plugin configuration
### Securosys Docker Signing Notation Plugin

:::tip Caution

In this guide we will use the Linux Ubuntu 22 (amd 64), for other operating systems and Linux distributions please refer to the referenced guides.

:::

:::danger Reminder

Note on some occasion's commands may require root permissions. Your system and docker user permissions should be configured beforehand to avoid any potential permission issues.

:::

---

## Plugin configuration

The `config.json` file, extracted from the downloaded **Securosys Docker Signing** configuration files in [chapter](../../../Installation/DonwloadPlugin#unzip-securosys-docker-signing-notation-plugin-configuration-file), is used to configure the [Securosys Docker Signing Notation Plugin](../../../Concepts/DockerSigningConcept.md) connection between:
- the _Notation CLI_
- Securosys Transaction Security Broker (TSB), as linking element to the [CloudHSM](/cloudhsm/overview/) or on-prem Primus HSM.

The `config.json` file contains the Transaction Security Broker (TSB) connection parameters:
- URL, 
- authentication method, 
- authentication token / TLS parameters.

Add the Transaction Security Broker (TSB) connection parameters provided by the Securosys [CloudHSM](/cloudhsm/overview/) operations team or by your HSM/TSB administrator.

---

### Configure Securosys plugin configuration file 

After completing the [automatic **notation-securosys** install](/docker_signing/Installation/Plugin-Installation/RecommendedOption), 
or manually creating and copying of the necessary files and setting the environment variables, copy the Docker Signing Notation Plugin `config.json` configuration file to the `{BINARY_PATH}` and edit using your preferred editor.

:::danger CAUTION
The notation-securosys plugin defaults to searching for the `config.json` file in the `{BINARY_PATH}`. To use a different directory, set the `CONFIG_PATH` environment variable.
:::

Copy `config.json` to `BINARY_PATH` and start editing:
```sh
cp ./config.json BINARY_PATH/config.json 

vi {BINARY_PATH}/config.json
```

The default `config.json` configuration file must be adapted according to your parameters. 
Replace the variables on the right side with your parameters.

You may replace the link `https://primusdev.cloudshsm.com/` _below_  by a [TSBaaS - Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) for accurate API-Endpoint URI.

Example default `config.json` file:

```js
{ 

  "restApi": "https://primusdev.cloudshsm.com", 

  "authType": "TOKEN", 

  "token": "TSBtoken", 

  "apiKeys": {
        "KeyManagementToken": ["TSB-TOKEN_1...", "TSB-TOKEN_2..."],
        "KeyOperationToken": ["TSB-TOKEN_3..", "TSB-TOKEN_4.."],
        "ServiceToken": ["TSB-TOKEN_5...", "TSB-TOKEN_6.."]
    },
    "applicationKeyPair": {
        "publicKey": "PUBLIC_KEY",
        "privateKey": "PRIVATE_KEY"
    },

  "metaData": { 

    "UserMetadata": "example of metaData" 

  } 

} 
```

`Config.json` configuration parameters:

| Configuration Parameters | Description |
|---|---|
| `"restApi":"https://primusdev.cloudshsm.com"`  | Replace the variable with your connection parameter for CloudsHSM's REST API service or for the customer's on-prem deployment. |
| `"authType":"authType"` | `"authType":"TOKEN"`:<br /> Authentication based on JWT token, standard value for CloudHSM's REST/TSB API service.<br /> `"authType":"CERT"`:<br /> Authentication based on mutual TLS. Used for CloudHSM Platinum REST/TSB API service or on-premises deployments. |
| `"token":"TSBtoken"`  | For `"authType":"TOKEN"`: Replace the variable with your own TSB JWT token. Omit in case of `"authType":"CERT"`. |
| `"certpath": "PathToCrt"`  | For `"authType":"CERT"`: Provide the full path of the server certificate file. Omit in case of `"authType":"TOKEN"`. |
| `"keypath": "PathToKey"`  | For `"authType":"CERT"`: Provide the full path of the client key file. Omit in case of `"authType":"TOKEN"`. |
| `"apiKeys": {...}`  | Additional authentication for TSB, set correct api keys values for specific operations, more than one token can be provided for each field. This is optional and will not break existing deployments. For more info visit [page](/tsb/Tutorials/TransactionSecurityBroker/PrimusAuthorizationApp/approver-mangement-api)|
| `"applicationKeyPair":{...}` | Pair of public and private key (base64 encoded) used to calculate the signature. |
| `"metaData":{"UserMetadata":"example of metaData"}` | Optional, add any user metadata, e.g. description. |

---

Example configured `config.json` file for shared (token-based) CloudHSM setup:
```js
{ 

  "restApi": "https://primusdev.cloudshsm.com", 

  "authType": "TOKEN", 

  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJwcmltdXNkZXYiLCJ2ZXIiOjEsIm5iZiI6MTY4MDUxNT…", 

  "apiKeys": {
        "KeyManagementToken": ["tsb-x-token_1...", "tsb-x-token_2..."],
        "KeyOperationToken": ["tsb-x-token_3..", "tsb-x-token_4.."],
        "ServiceToken": ["tsb-x-token_5...", "tsb-x-token_6.."]
    },
    "applicationKeyPair": {
        "publicKey": "MIIBIjANBgkqhk...",
        "privateKey": "MIIEvgIBADANBgkqhkiG9w0BAQE..."
    },

  "metaData": { 

    "UserMetadata": "example of metaData" 

  } 

} 
```

---

### Create Securosys plugin configuration environment variable and directory (optional)

:::tip Note
In case you want to store the `config.json` file in a different directory than `{BINARY_PATH}`, the environment variable `CONIFG_PATH` must be set.  
You may use this to set up communication with different HSM partitions.
:::


Choose one of the options to set the `CONFIG_PATH` variable:

1) Set an environment variable pointing to the configuration path and add it to `~/.bashrc`, then reload in the shell:

```sh
echo 'export CONFIG_PATH=PLUGIN_CONFIG_PATH' >> ~/.bashrc 

source ~/.bashrc
``` 

Refresh the terminal window or source `~/.bashrc` to confirm the changes to the file and set the environment variable. 
Example:

```sh
Echo 'export CONFIG_PATH="${HOME}/.config/notation/plugins/securosys/config.json"' >> ~/.bashrc 

source ~/.bashrc
```

2) Alternatively, add this variable temporarily to the system environment with the following command: 

```sh
export CONFIG_PATH="${HOME}/.config/notation/plugins/securosys/config.json" 
```

:::danger Warning
3) In case the variable is not set in the system environment, `CONFIG_PATH={PLUGIN_CONFIG_PATH}` must be applied before every command, e.g. 
```sh
CONFIG_PATH=$CONFIG_PATH/config.json ./notation-securosys describe-key
```
:::