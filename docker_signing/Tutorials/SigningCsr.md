---
sidebar_position: 4
title: Generate CSR & Import Certificates for Docker
sidebar_label: Generate CSR 🔐
description: Learn to generate a Certificate Signing Request (CSR), obtain CA-signed certificates, and import them into the truststore for Docker image signing with Securosys.
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Certificate Signing Request (CSR) 
### Securosys Docker Signing Notation Plugin

**Generate a Certificate Signing Request (CSR) and signing by trusted provider**

The procedure below assumes that the command is run from the directory where the notation-securosys binary is stored, `{BINARY_PATH}`.

Follow this procedure if you would like to use a certificate signed by your certificate authority (CA) or for a publicly trusted certificate from a trust service provider (TSP):

- Create certificate signing request (CSR), 

- Request certificate based on CSR from CA / TSP, 

- Import certificate to 'Notation'. 

To create a **Certificate Signing Request**, execute the following command:

```sh
./notation-securosys generate-csr
```

After running the command, provide the appropriate parameters in JSON format (example shown below).
Go to section "generate-certificate-request" in the `input_example.json` file and prepare the parameters according to [chapter](./CreateSigningKey#prepare-docker-image-signing-key-parameters) (use any editor you are familiar with):

```js
{ 

  "keyId": "keyLabel", 

  "pluginConfig": { 

    "password": "optional" 

  }, 

  "certificate": { 

    "validity": 365, 

    "attributes": { 

      "commonName": "Securosys_CN", 

      "country": "CH", 

      "stateOrProvinceName": "Zurich", 

      "locality": "Zurich", 

      "organizationName": "Securosys SA", 

      "organizationUnitName": "IT" 

    } 

  } 

}
```

Command parameters and description:

| Variable parameters | Command and parameter description |
|---|---|
| `CONFIG_PATH=$PLUGIN_CONFIG_PATH/config.json ` | The path to the Securosys Docker Signing Notation Plugin configuration file. To be added in front of the command only if the config file is not stored in the `{BINARY_PATH}` or the environment variable is not set as described in [chapter](/docker_signing/Installation/Plugin-Installation/AlternativeWays/PluginConfiguration#create-securosys-plugin-configuration-environment-variable-and-directory-optional). |
| `"keyId": "pluginKeyId"` | Specify the key identifier (key name) associated with your signing key name. |
| `["password":"optional"]` | Optionally the password for the signing key. Omit the parameter completely if the key has no password. |
| `"certificate" {...}` | Specify your certificate parameters. These parameters are used for your certificate signature request (CSR). Set validity of certificate, by default set to 365 days. Adapt the attributes variables shown to best suit your certificate. |

---
Example command generating a certificate signing request based on a singing key without password:

```sh
./notation-securosys generate-csr
```

```js
{ 

 "keyId": "SecurosysImageSignKey02", 

  "pluginConfig": {}, 

  "certificate": { 

    "validity": 365, 

    "attributes": { 

      "commonName": "DockerImageSign05", 

      "country": "CH", 

      "stateOrProvinceName": "Zurich", 

      "locality": "Zurich", 

      "organizationName": "Securosys SA", 

      "organizationUnitName": "IT" 

    } 
  } 
} 
```

A certificate signing request will be returned. Example response of successful CSR generation:

```js
-----BEGIN NEW CERTIFICATE REQUEST----- 
MIIDDDCCAfQCAQAwbzELMAkGA1UEBhMCQ0gxDzANBgNVBAgTBlp1cmljaDEPMA0G 
A1UEBxMGWnVyaWNoMRUwEwYDVQQKEwxTZWN1cm9zeXMgU0ExCzAJBgNVBAsTAklU 
… 
-----END NEW CERTIFICATE REQUEST----- 
```

Afterwards send the certificate signing request file to the trusted certificate provider manually. Submit the certificate signing request (CSR) to your Certificate Authority / Trust Service Provider to obtain the certificate.

## Import Certificate into 'Notation' Truststore

After obtaining the certificate, save the certificate to directory `${HOME}/Downloads/Securosys/`:

```sh
cp ./certificatename.crt ~/Downloads/Securosys/
```

Then, execute the command below to import the certificate:

```sh
./notation-securosys import-certificate
```

After running the command, provide the appropriate parameters in JSON format including the full path to the certificate:

```js
{ 
 "keyId": "SecurosysCertKey", 

 "pluginConfig" : { 

   "password":"optional", 

   "certPath":"pathToCertificate",
   "caPath":"pathtoCA"
 } 
}
```

Example command:

```sh
./notation-securosys import-certificate
```

```js
{ 
 "keyId": "SecurosysCertKey", 

 "pluginConfig" : { 

   "certPath":"/home/securosys/Downloads/Securosys/SecurosysImageSignKey01t.crt",
   "caPath":"/home/securosys/Downloads/Securosys/SecurosysImageSignCA.crt"
 } 
} 
```

All created certificates are stored to the truststore of Notation in directory: 

```sh
{NOTATION_CONFIG}/truststore/x509/ca/securosysTrustStore
```

Verify the creation and find all existing certificates by listing the directory content. Notation does not provide any specific command to list the certificates.

```sh
ls -al {NOTATION_CONFIG}/truststore/x509/ca/securosysTrustStore
```

After successfully creating the certificate, it is now possible to proceed to [sign Docker Images](./SignImage).