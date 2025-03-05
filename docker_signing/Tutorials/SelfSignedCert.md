---
sidebar_position: 3
title: Generate a Self-Signed Certificate for Docker
sidebar_label: Self-Signed Certificate
description: Learn how to generate a self-signed certificate for Docker image signing using Securosys. Follow detailed steps, set parameters, and configure your truststore securely.
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

:::tip Important

If you [installed the plugin automatically](/docker_signing/Installation/Plugin-Installation/RecommendedOption), you have already created the first signing key, so you can skip this part of the tutorial. For a manual installation, please refer to [this section](/docker_signing/Installation/Plugin-Installation/AlternativeWays/Installation) or when you want to create another signing key.

:::

# Generating Self-Signed Certificate
### Securosys Docker Signing Notation Plugin

To generate a self-signed certificate, execute the command below. The command will automatically create a self-signed certificate and download the certificate from the HSM via REST API/ TSB and add it to the Notation truststore in the Notation configuration directory. 

The below command assumes that the plugin's `config.json` is stored in the `{BINARY_PATH}`, or that `$CONFIG_PATH` environment variable is set as well as that the command is run from directory where the `notation-securosys` binary is saved.

```sh
./notation-securosys generate-selfsign-crt
```

After running the command, provide the appropriate parameters in JSON format (example shown below). 
Go to section "generate-selfsign-crt" in the `input_example.json` file and prepare the parameters, accordingly to [this section](./CreateSigningKey#prepare-docker-image-signing-key-parameters) (use any editor you are familiar with): 

```js
{ 

  "keyId": "keyLabel", 

  "pluginConfig": { 

    "password": "optional" 

  }, 

  "certificate": { 

    "validity": 365, 

    "attributes": { 

      "commonName": "Securosys_CN"

    } 

  } 

} 
```

Command parameter and description:

| Variable parameters | Command and parameter description |
|---|---|
| `CONFIG_PATH=$PLUGIN_CONFIG_PATH/config.json ` | The path to the Securosys Docker Signing Notation Plugin configuration file. To be added in front of the command only if the config file is not stored in the `{BINARY_PATH}` or the environment variable is not set as described in [chapter](/docker_signing/Installation/Plugin-Installation/AlternativeWays/PluginConfiguration#create-securosys-plugin-configuration-environment-variable-and-directory-optional). |
| `"keyId": "pluginKeyId"` | Specify the key identifier (key name) associated with your signing key name. |
| `["password":"optional"]` | Optionally the password for the signing key. Omit the parameter completely if the key has no password. |
| `"certificate" {...}` | Specify your certificate parameters. These parameters are used for self-signed certificate. Set `commonName` and `validity` of certificate, by default set to 365 days. |

---

Example command generating a self-signed certificate based on a singing key without password:

```sh
./notation-securosys generate-selfsign-crt
```

```js
{ 

 "keyId": "SecurosysImageSignKey01", 

  "pluginConfig": {}, 

  "certificate": { 

    "validity": 365, 

    "attributes": { 

      "commonName": "DockerImageSign05"

    } 

  } 

}
```

Output upon successful certificate generation:

```js
{
    "label":"SecurosysImageSignKey01",
    "certificate":
    "-----BEGIN CERTIFICATE-----
        MIIEazCCA1OgAwIBAgIGAYwWAbffMA0GCSqGSIb3DQEBCwUAMIHhMR8wHQYDVQQDDBZTZWN1cm9zeXNEb2NrZXJTaWduaW5nMQswCQYDVQQLDAJJVDEVMBMGA1UECgwMU2VjdXJvc3lzIFNB…=
    -----END CERTIFICATE-----"
}
```

:::tip Note
The self-signed certificate is signed by the signing key `keyId` itself, resulting in the certificate sharing the identical name with the signing key. 

If you wish to sign the signing key with an own root/CA-key, follow the procedure using a certificate signing request (CSR) in the next [chapter](./SigningCsr).
:::

All created certificates are stored to the truststore of Notation in directory:

```sh
{NOTATION_CONFIG}/truststore/x509/ca/securosysTrustStore
```

Verify the creation and find all existing certificates by listing the directory content. Notation does not provide any specific command to list the certificates.

```sh
ls -al {NOTATION_CONFIG}/truststore/x509/ca/securosysTrustStore
```

After successfully creating the certificate, it is now possible to proceed to [signing Docker images](./SignImage).