---
sidebar_position: 3
title: Docker Encryption Plugin - Installation & Configuration
sidebar_label: 4. Configuration
description: Installation & Configuration
keywords: [hsm, cloud hsm]
---

# Installation & Configuration

## Securosys Docker Image Encryption Plugin configuration

Create a directory in which the `ocicrypt.conf` configuration file will be stored. 

```
mkdir ${HOME}/Securosys/skopeo
```

:::note Note

There is no default **`${BINARY_PATH}`** for the skopeo plugin at user level since the skopeo-securosys binary can be put anywhere. In the context of this guide, the plugin `${BINARY_PATH}` is set to be under directory **`${HOME}/Securosys/skopeo/`**.
:::

### Install the Securosys Docker Image Encryption Plugin binary

Navigate to the directory where the default Securosys Docker Image Encryption plugin configuration file was downloaded and extracted in [Unpacking - Download Securosys Docker Image Encryption Plugin files](/docker_encryption/Installation/unpacking#download-securosys-docker-image-encryption-plugin-files).


Copy the skopeo-securosys binary file to the previously created directory.  

```
cp skopeo-securosys ${BINARY_PATH}
```

### Install the Securosys Docker Image Encryption Plugin config file

The `ocicrypt.config` file, extracted from the downloaded Securosys Docker Image Encryption configuration files in [Unpacking - Unzip Securosys Docker Image Encryption Plugin configuration file](http://localhost:3000/docker_encryption/Installation/unpacking#download-and-install-skopeo) is used to configure the Securosys Docker Image Encryption Plugin connection between the OCIcrypt CLI and Securosys [Transaction Security Broker](/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker) as linking element to the [CloudHSM](/cloudhsm/overview) or on-prem Primus HSM. 


The `ocicrypt.config` file contains: 

- Transaction Security Broker (TSB) connection parameters 
  - URL 
  - authentication method 
  - authentication token / TLS parameters 
- Encryption key reference (label, password) 
- Path to `skopeo-securosy` plugin 

:::tip TIP

Add the [TSB](/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker) connection parameters provided by the Securosys CloudHSM operations team or by your HSM/TSB administrator.

Please replace the link `<TSB_APIendpoint>` _below_  by a [TSBaaS - Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) for accurate API-Endpoint URI.

:::

Navigate to the directory where the default Securosys Docker Image Encryption plugin configuration file was downloaded and extracted in [Unpacking - Unzip Securosys Docker Image Encryption Plugin configuration file](/docker_encryption/Installation/unpacking#unzip-securosys-docker-image-encryption-plugin-configuration-file).  

:::note NOTE

There is no default **`${CONFIG_PATH}`** for the skopeo plugin at user level since the skopeo- securosys binary can be put anywhere. In the context of this guide, the plugin config `${CONFIG_PATH}` is to be under directory **`${HOME}/Securosys/skopeo/`**.

:::

Copy the ocicrypt.conf file to the previously created directory.  

```
cp ocicrypt.conf ${CONFIG_PATH}
```

The ocicrypt.conf file is used to configure the Securosys Docker Image Encryption Plugin.

The default ocicrypt.conf file can be seen below, please adapt the parameters `pathToExecutable`, `-cipher-algorithm`, `tsb-api-endpoint`, `-auth`, `-token`, `-keyOperationToken`, `-publicKey`, `-privateKey` according to your environment: 

```sh {5,7,8,9,10,11,12,13,14,15}
{ 
    "key-providers": { 
        "securosys_encryption": { 
            "cmd": { 
            "path":"/<pathToExecutable>/Skopeo-securosys",
            "args":  [ 
                "-cipher-algorithm <yourCipherAlgorithm>",
                "-tsb-api-endpoint <TSB_APIendpoint>",
                "-auth <TOKEN>",
                "-token <yourToken>",
                "-certpath <PathToCrt>",
                "-keypath <PathToKey>",
                "-keyOperationToken <TSB-TOKEN>",
                "-publicKey <PUBLIC_KEY>",
                "-privateKey <PRIVATE_KEY>"
                ] 
            } 
        } 
    } 
} 
```

Please see below the configuration parameters:

| **Configuration Parameters:** | **Description** |
| - | - |
| "path":"**`<pathToExecutable>`**/skopeo-securosys", | Replace the variable with your path to the `securosys-encryption` plugin binary, i.e. **`{BINARY_PATH}`**. The naming of the plugin must stay the same! |
| "-cipher-algorithm **`<yourCipherAlgorithm>`**", | Replace the variable with the Cipher Algorithm of your encryption key. Possible values: <br/> - RSA |
| "-tsb-api-endpoint **`<TSB_APIendpoint>`**", |Replace the variable with your TSB endpoint URI.  Visit [Securosys CloudHSM Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) for CloudHSM TSB as a Service deployments.|
| "-auth **`<TOKEN>`**", | Specifies the authentication type. Keep the value as: <br/> - TOKEN for JWT based authentication <br/> - CERT for mTLS  |
| "-token **`<yourToken>`**" | Replace the variable with your TSB JWT authentication token.  For "-auth **`TOKEN`**": Replace the variable with your own TSB JWT token. Omit entirely in case of "auth **`CERT`**" (mTLS) |
| "-certpath **`<PathToCrt>`**" | For "auth **`CERT`**": <br/> Provide the full path of the server certificate file. <br/> Omit entirely in case of "auth **`TOKEN`**" |
| "-keypath **`<PathToKey>`**" | For "auth **`CERT`**": <br/>Provide the full path of the client key file. <br/>Omit entirely in case of "auth **`TOKEN`**". |
| "-keyOperationToken **`<TSB-TOKEN>`**" | Additional authentication for TSB, set correct api key value for specific operation. This is optional and will not break existing deployments. For more info visit [page](/tsb/Tutorials/TransactionSecurityBroker/PrimusAuthorizationApp/approver-mangement-api) |
| "-publicKey **`<PUBLIC_KEY>`**" | The public key (base64 encoded) that belongs to the private key used to calculate the signature. |
| "-privateKey **`<PRIVATE_KEY>`**" | Private key (base64 encoded) used to calculate the signature. |

:::note NOTE
When utilizing [CloudHSM service](../../cloudhsm/overview), refer to [Cloud Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) for accurate API-Endpoint URI. For on-premise deployments, verify API-Endpoint URI with your administrator, ontact your service administrator for authentication credentials in any setup (on-prem or cloud).
:::

Example using JWT-based authentication, for key without password: 

```js
{ 
    "key-providers": { 
        "securosys\_encryption": { 
            "cmd": { 
                "path":"/home/securosys/skopeo/securosys\_encryption", 
                "args": [ 
                    "-key-label SecurosysEncKey01", 
                    `"-cipher-algorithm RSA",  
                    "-tsb-api-endpoint https://primusdev.cloudshsm.com",  
                    "-auth TOKEN",  
                    "-token ergq0ejgadjlfkgjaldfjgaodf9gjad0f9hgadfhgadhfogiah…" ],
                    "-keyOperationToken <tsb-x-token_...>",
                    "-publicKey <MIIBIjANBgkqhk...>",
                    "-privateKey <MIIEvgIBADANBgkqhkiG9w0BAQE...>" 
            }
        } 
    } 
} 
```