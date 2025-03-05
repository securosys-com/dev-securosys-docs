---
sidebar_position: 2
title: Release Notes - Docker Signing Plugin
sidebar_label: Release Notes
description: Explore the latest updates for the Securosys Docker Signing Notation Plugin, including new features for connection checks, configuration changes, certificate generation, and bug fixes. Enhance your HSM integration with improved plugin functionality and installation options.
toc_max_heading_level: 2
---

# Release Notes
### Securosys Docker Signing Notation Plugin

## 1.5.0

**Release Date:** 16.09.2024

### New Features and Enhancements

- This version is compatible with TSB v2.0.0 or newer, it supports API-Key authentication.
- Generate metadata for SKA-Requests
- Update to support notation 1.1.0


### Configuration Change

- The configuration file now contains also parameters related to the API-Key authentication and signature, such as api keys(arrays) or public and private key which are used to calculate the signature.
- You can now onfigure the "config.json" like below(example):

```js {15-19}
    {
    "restApi":"YourApiEndpoint",
    "authType":"TOKEN",
    "token":"TSBtoken",
    "apiKeys": {
        "KeyManagementToken": ["tsb-x-token_1...", "tsb-x-token_2..."],
        "KeyOperationToken": ["tsb-x-token_3..", "tsb-x-token_4.."],
        "ServiceToken": ["tsb-x-token_5...", "tsb-x-token_6.."]
    },
    "applicationKeyPair": {
        "publicKey": "MIIBIjANBgkqhk...",
        "privateKey": "MIIEvgIBADANBgkqhkiG9w0BAQE..."
    },
    "metaData":{     
        "SignServer":"Securosys-BuildServer-Docker",	    
        "Status": "Nginx Image Signing",     
        "Project":"Production Nginx Webserver",
        "Branch": "main",
        "Tag":"v1.18.0"    
    }
}
```

## 1.4.0

**Release Date:** 30.01.2024

### API Changes

This version introduces an update in certificate generation and self sign certificate that is compatible with TSB v1.18.0 or newer, especially for SKA keys.

Command names have been changed to be more understandable and intuitive(the operation of the commands below remains the same):

- `generate-certificate` was changed into `generate-selfsign-crt`,
- `generate-certificate-request` was changed into `generate-csr`.

## 1.3.1

**Release Date:** 28.11.2023


### API Changes
- The configuration file now only contains parameters related to the connection to HSM/TSB, this means that parameters such as certificate attributes, metadata, etc. should be entered in the input after running the `generate-certificate` and `generate-certificate-request` commands, an example of a request is below(for `generate-certificate`):
```js
{
  "keyId": "keyLabel",
  "pluginConfig" :{
    "password":"optional"
  },
  "certificate":{
    "validity":365,
    "attributes":{
        "commonName":"Securosys_CN",
        "country":"CH",
        "stateOrProvinceName":"Zurich",
        "locality":"Zurich",
        "organizationName":"Securosys SA",
        "organizationUnitName":"IT"
      }
  }
}
```
### New Features and Enhancements
- This update introduces new functionality to check the connection between the plugin and the TSB/HSM. If you run below command:
```bash
    CONFIG_PATH="pathToPluginConfigFile" ./notation-securosys check-connection
```
the plugin will check the connection, in case of an error an error will be displayed, and in case of success a message with user stats will be displayed (example):
```bash
user userName, private keys ..., public keys ..., secret keys ..., certificates ..., data objects ..., invalidated keys ..., used size ..... bytes, max size ...... bytes
```

### Configuration Changes
- The configuration file now only contains parameters related to the connection to HSM/TSB, such as endpoint, authorization type, token/certificate, etc.
- You can now onfigure the "config.json" like below:

```js
    {
    "restApi":"YourApiEndpoint",
    "authType":"TOKEN",
    "token":"TSBtoken",
    "metaData":{
        "UserMetadata":"example of metaData"
    }
}
```
or if you would like to use MTLS connection:
```js
    {
    "restApi":"YourApiEndpoint",
    "authType":"CERT",
    "certpath": "PathToCrt",
    "keypath": "PathToKey",
    "metaData":{
        "UserMetadata":"example of metaData"
    }
}
```


### Bugfix
- Binary and configuration files had names like: "docker_trust_1.3.0-docker_trust.zip", now this has been corrected to the correct naming: "securosys_docker_signing_notation_plugin-1.3.1.zip"
- After running the "installation" command for the plugin, the user was given three options on how to configure the "config.json" file, regardless of the operating system. The first option was: `export CONFIG_PATH="/Users/reto/Library/Application Support/notation/plugins/securosys/config.json"` this has been corrected for Windows, now instead of `export` it is `set`
- The second option After running the "installation" command for the plugin looked like this: `CONFIG_PATH=/Users/reto/Library/Application Support/notation/plugins/securosys/config.json` has now been corrected by adding `""` to the defined path



## 1.3.0

**Release Date:** 16.11.2023

### New Features and Enhancements
This update introduces new functionality to facilitate installation and configuration of the plugin. New command are available: `install`. If you run below command:
```bash
    ./notation-securosys install
```
the program will automatically detect the operating system, insert the plugin into the appropriate directory in "notation". Additionally, after invoking the command, possible options for personalizing the configuration file and specific commands that should be run will be displayed.

## 1.2.0

**Release Date:** 07.11.2023

### New Features and Enhancements

New possibilities have been added for the "describe-key" method. Now, in addition to creating non SKA keys, you can also create SKA keys(with policy) provide the new("keyWithPolicy", "policy"), appropriate parameters in the form of json. <br/>

If none of the new parameters appear, the plugin will create a key with empty(but not null) policy by default. Now it is recommended not to use this default option. If the user wants to create a key without policy, then the "keyWithPolicy" parameter should be added and its value set to "no", if the user wants to create an SKA key, then this parameter should not appear. <br/><br/>

When creating SKA keys, but not with the default empty policy, the user can provide the path in the "policy" parameter to a json file with the structure as the policy described on TSB should have, then a key will be created with the policy values found in this file. <br/>

**WARNING**
When creating SKA keys, only one of the "policy" and "keyWithPolicy" parameters should be provided, the other should not be included in the request we enter.

### Improvements

#### 1. Generating and Importing Certificates

This version introduces an update in certificate generation that is compatible with TSB v1.17.0 or newer, especially for SKA keys.

#### 1.1 Generating Certificate and Upload into Keystore (For Self-Signed Certificate)

In this section the procedure remains the same. Currently there is no support for self-signed certificates with SKA keys.

#### 1.2 Generating Certificate, Import via TSB to HSM, and Upload into Keystore

In this section the procedure remains the same, but there is now support for both SKA keys and non SKA keys.


## 1.1.0

**Release Date:** 29.09.2023

### New Features and Enhancements

#### 1. Generating and Importing Certificates

This update introduces new functionality for generating and importing certificates into the keystore, especially for self-signed certificates. Two new commands are available: `generate-certificate`, `generate-certificate-request` and `import-certificate`.

#### 1.1 Generating Certificate and Upload into Keystore (For Self-Signed Certificate)

To generate a certificate and upload it into the keystore using a self-signed certificate, follow these steps:

- Run the following command:

    ```bash
    CONFIG_PATH="pathToPluginConfigFile" ./notation-securosys generate-certificate
    ```

- Provide the appropriate parameters in the form of a JSON file, as shown below:

    ```js
    {
      "keyId": "keyLabel",
      "pluginConfig": {
        "password": "optional"
      }
    }
    ```

The plugin will automatically create a certificate signing request (CSR), download the certificate from TSB/HSM, and add it to the notation truststore in the appropriate location.

#### 1.2 Generating Certificate, Import via TSB to HSM, and Upload into Keystore

This feature allows you to generate a certificate, import it via TSB to HSM, and upload it into the keystore. Follow these steps:

- First, execute the following command to generate a certificate request:

    ```bash
    CONFIG_PATH="pathToPluginConfigFile" ./notation-securosys generate-certificate-request
    ```

- Provide the appropriate parameters in the form of a JSON file, as shown below:

    ```js
    {
      "keyId": "keyLabel",
      "pluginConfig": {
        "password": "optional"
      }
    }
    ```

A CSR will be returned, and you need to manually send the CSR file to the PKI provider. After this step, follow the command below:

- Run the following command to import the certificate:

    ```bash
    CONFIG_PATH="pathToPluginConfigFile" ./notation-securosys import-certificate
    ```

- Provide the necessary information in a JSON file, including the certificate path, as shown below:

    ```js
    {
      "keyId": "keyId",
      "pluginConfig": {
        "password": "optional",
        "certPath":"pathToCertificate",
        "caPath":"pathToCaCert"
      }
    }
    ```

The plugin will automatically import the certificate via TSB to HSM and add it to the notation truststore in the appropriate location.

### Bug Fixes

- When you enter an empty password in the key instead of adding an empty object, the password will not be created

## 1.0.0
Issued: September, 7, 2023
