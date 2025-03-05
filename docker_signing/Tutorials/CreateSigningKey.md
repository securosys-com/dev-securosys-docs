---
sidebar_position: 1
title: How to create a Signing Key for Docker?
sidebar_label: Create Signing Key 🗝️
description: Follow this guide to create a Docker image signing key using Securosys. Learn how to set key parameters, configure RSA types, and understand SKA policies for secure image signing.
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';


:::tip Important

If you [installed the plugin automatically](/docker_signing/Installation/Plugin-Installation/RecommendedOption), you have already created the first signing key, so you can skip this part of the tutorial. For a manual installation, please refer to [this section](/docker_signing/Installation/Plugin-Installation/AlternativeWays/Installation) or when you want to create another signing key.

:::

# Create a Signing Key
### Securosys Docker Signing _Notation_ Plugin

To proceed with docker signing it is important to first specify the signing key which will be used to sign the images.

## Prepare Docker Image Signing Key parameters

All parameters needed for any [Securosys Docker Signing Notation Plugin](../Concepts/DockerSigningConcept.md) command must be provided in the correct JSON format. We recommend preparing all parameters in a text editor before executing any command. Examples of properly formatted parameter input are available in `input_example.json`, as extracted and moved in [chapter](/docker_signing/Installation/DonwloadPlugin#unzip-securosys-docker-signing-notation-plugin-configuration-file).

:::tip Note
The `input_example.json` file isn't utilized directly by any command but assists in preparing necessary values to copy/past during command execution and indicates the correct JSON structure.
:::

Go to section "describe-key" in the `input_example.json` file and prepare the key parameters (use any editor you are familiar with):

```sh
vi ~/Downloads/Securosys/securosys_docker-signing-notation-plugin-configuration-latest/config-files/input_example.json 
```

Example parameters - "describe-key":
```js
{ 

  "contractVersion" : "1.0", 

  "keyId": "pluginKeyId", 

  "pluginConfig" : { 

    "keyType":"RSA-2048", 

    "password":"optional", 

    "keyWithPolicy": "No", 

    "policy":"absolutePath/policy.json" 

  } 

} 
```

Replace the variables with your specific parameters:

| Command parameters | Command and parameter description |
|---|---|
| `"contractVersion": "1.0"` | Notation supported-contract-version. The current plugin contract version is "1.0" |
| `"keyId": "keyLabel"` | Specify the key identifier (key name) associated with your signing key name. |
| `"keyType":"RSA-2048"` | Specify the key type for the command. Supported key types:<br /> `RSA-2048`, `RSA-3072`, `RSA-4096`. |
| `["password":"optional"]` | Optionally set the password for the signing key. Omit the parameter completely to generate a key without a password. |
| `"keyWithPolicy": "No"` | If a key without an SKA policy is to be created set the parameter to "No" and omit the `"policy"` parameters. If a key with an SKA policy will be created omit this whole parameter. |
| `"policy":"absolutePath/policy.json"` | Provide the path to a `.json` file with the SKA policy for multi-authorization. Omit this parameter if "keyWithPolicy": "No" parameter is used. |
| `"keyWithPolicy": "No"` and `"policy":"absolutePath/policy.json"` | If all two parameters are omitted the describe-key command creates a key with an empty (but not null) SKA policy. It is not recommended to use this option. |

---

The Smart Key Attribute (SKA) policy concept provides the flexibility to define policies of any complexity grade to approve the issuance of a signature. The SKA policies contain the rules for key usage, blocking of key usage, unblocking of key usage, modification of rule. For every rule individual quorum (AND, OR), time limits and time locks can be set.

[View more details on Smart Key Attribute](/tsb/Tutorials/TransactionSecurityBroker/smart-key-attributes#smart-key-attribute---policy)

If you intend to utilize one of our approval applications (e.g., mobile applications) in conjunction with the Docker Signing Notation Plugin, please reach out to Securosys for further assistance.


### Example of an SKA policy

In the provided example, the SKA policy for a Docker image signing key mandates that key usage necessitates approval from a single approver (DevOpsSigner1) within 1 hour of the signing request. However, policies for blocking, unblocking the signing key, or modifying the SKA policy are not established.

Example of a SKA policy (partial):

```js
{
    "ruleUse": {
        "tokens": [
            {
                "name": "string",
                "timelock": 0,
                "timeout": 0,
                "groups": [
                    {
                        "name": "string",
                        "quorum": 1,
                        "approvals": [
                            {
                                "type": "onboarded_approver_certificate",
                                "name": "devops-sebastianfernandez@securosys.com"
                            }
                        ]
                    }
                ]
            }
        ]
    },
    "ruleBlock": {
        "tokens": [
            {
                "name": "string",
                "timelock": 0,
                "timeout": 0,
                "groups": [
                    {
                        "name": "string",
                        "quorum": 1,
                        "approvals": [
                            {
                                "type": "onboarded_approver_certificate",
                                "name": "devops-sebastianfernandez@securosys.com"
                            }
                        ]
                    }
                ]
            }
        ]
    },
    "ruleUnblock": {
        "tokens": [
            {
                "name": "string",
                "timelock": 0,
                "timeout": 0,
                "groups": [
                    {
                        "name": "string",
                        "quorum": 1,
                        "approvals": [
                            {
                                "type": "onboarded_approver_certificate",
                                "name": "devops-sebastianfernandez@securosys.com"
                            }
                        ]
                    }
                ]
            }
        ]
    },
    "ruleModify": {
        "tokens": [
            {
                "name": "string",
                "timelock": 0,
                "timeout": 0,
                "groups": [
                    {
                        "name": "string",
                        "quorum": 1,
                        "approvals": [
                            {
                                "type": "onboarded_approver_certificate",
                                "name": "devops-sebastianfernandez@securosys.com"
                            }
                        ]
                    }
                ]
            }
        ]
    },
    "keyStatus": {
        "blocked": false
    }
}
```

---

SKA policy command parameters description:

| Command parameters | Command and parameter description |
|---|---|
| `"ruleUse"` | Usage e.g. for signing or decryption (private key cryptographic operations) |
| `"ruleBlock"` | Blocking a key from being used, prevents signing and decryption. |
| `"ruleUnblock"` | Unblocking a key if it is blocked. |
| `"ruleModify"` | Changing the authorization attributes (rules). |
| `"timeout"` | The maximum time between raising the signing request and its authorization. _Note: Time restrictions are defined in seconds and must be multiples of 60._ |
| `"quorum"` | N out of M authorizations is required for authorizing the request _Note: Since we have 1 Approver in this sample, we are defining the quorum to 1._ |
| `"approvals"` | **Type:** `public_key` or `certificate`<br /> **Name:**<br /> If type `public_key` is used this field is mandatory and describes the name of the approver.<br /> If type `certificate` is used the CN (Common Name) of the certificate is used, remove this field `name` in case you are using type certificate.<br /> **Value:**<br /> The `public_key` bytes or the `certificate` in pem format.<br /> _Note: if present remove `---BEGIN CERTIFICATE---` and `---END CERTIFICATE---`_ |

---

## Create Docker Image-Signing-Key using "describe-key" Command

The `describe-key` command is utilized to generate a key on the Securosys Primus HSM/CloudHSM with the provided parameters. 

You can use this command to modify the SKA policy as well. 

Navigate to the directory `{BINARY_PATH}` where the notation-securosys plugin binary is saved. 

Execute the following command to create the Image-Signing-Key:

```sh
./notation-securosys describe-key
```

Command parameter and description:

| Command parameters | Command and parameter description |
|---|---|
| `"CONFIG_PATH=PLUGIN_CONFIG_PATH/config.json"` | The path to the Securosys Docker Image Signing Plugin configuration file. To be added in front of the command only if the environment variable is not set as described in [chapter](/docker_signing/Installation/Plugin-Installation/AlternativeWays/PluginConfiguration#create-securosys-plugin-configuration-environment-variable-and-directory-optional). |
| `"./notation-securosys"` | Command to execute the notation plugin. If the user is not within the same directory where the plugin binary is saved an absolute path to the binary must be specified. Example of an absolute path to the plugin binary for Linux Ubuntu operating system: `/home/securosys/.config/notation/plugins/securosys/notation-securosys`. |
| `"describe-key"` | This command is used to get metadata for a given key. If the key does not exist, the key is created from the specified parameters. |

Example command to generate an image-signing-key, add the parameters in correct json format as prepared in `input_example.json`:

```sh
./notation-securosys describe-key
```

```js
{ 

  "contractVersion" : "1.0", 

  "keyId": "SecurosysImageSignKey01", 

  "pluginConfig" : { 

    "keyType":"RSA-2048", 

    "keyWithPolicy": "No" 

  } 

} 
```

:::danger CAUTION
Ensure the accuracy of your JSON command parameters by validating them with an online JSON format validator tool, such as [here](https://jsonformatter.org/).
:::

If the command is successful, the output should show:

```js
{ 
  "keyId" : "SecurosysImageSignKey01", 
  "keySpec" : "RSA-2048" 
} 
```

Optionally, view the key via the TSB swagger-UI by accessing the key store with GET /v1/key command.

## Adding the signing key

To allow _Notation_ to use the signing key specified in the previous chapter, it must be added to the list of keys used for signing. It also creates the relationship between signing key and the plugin within _Notation_.

To add a key, please execute the following command:

```sh
notation key add "notationKeyId" --id "pluginKeyId" --plugin securosys --plugin-config keyType="RSA-2048" [--plugin-config password="abcd"] 
```

Command parameter and description:

| Command parameters | Command and parameter description |
|---|---|
| `notation key add "notationKeyId"` | This command associates the plugin and notation signing key. Replace the variable with a friendly key name to refer to during signing operations from the Notation CLI. |
| `--id "pluginKeyId"` | Replace the variable with your image signing key identifier "keyID", specified in previous chapter. |
| `--plugin securosys` | Specify the directory name containing Securosys Docker Signing Notation Plugin binary, must remain securosys. |
| `--plugin-config` | Specify the key type and or password of your signing key.<br /> Supported configurations:<br /> - `keyType="RSA-2048"`, Supported key types: `"RSA-2048"`, `"RSA-3072"`, `"RSA-4096"`<br /> - `password="abcd"` Specify the password of the key. Exclude if no password is set for signing key. |

---

Example command, for simplicity the identical key name is used in Notation and the plugin (HSM):

```sh
notation key add "SecurosysImageSignKey01" --id "SecurosysImageSignKey01" --plugin securosys --plugin-config keyType="RSA-2048"
```

If the command is successful, the output should show:

```sh
SecurosysImageSignKey01
```

The response after successfully adding the key will return the signing `notationKeyId`. In case the key name already exists, an error message will be shown. By adding a key, the key parameters will be added to the `signingkeys.json` file in the `{NOTATION_CONFIG}` directory, as described in [this chapter](../Installation/InstallNotation#notation-directory-structure).
Example of (the newly created) `signingkeys.json` file:

```js
{ 
    "keys": { 
        { 
            "name": "SecurosysImageSignKey01", 
            "id": "SecurosysImageSignKey01", 
            "pluginName": "securosys", 
            "pluginConfig": { 
                "keyType": "RSA-2048" 
            } 
        }
    } 
} 
```

:::tip Note
The notation key list command of Notation offers a possibility to list all keys. Use this command to easily verify if the key has been created or to check if a certain key label has already been used.
:::

List all keys added to _Notation_:

```sh
notation key list
```

```js
NAME	                   KEY PATH   CERTIFICATE PATH   ID                        PLUGIN NAME  

SecurosysSignCert-RST01                                 SecurosysImageSignKey01   securosys  
```

After the key has been added proceed to generate a certificate.