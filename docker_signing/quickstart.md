---
sidebar_position: 0
title: Getting started with Docker Signing & HSM
sidebar_label: Quick Start 💡
description: Follow this step-by-step guide to integrate Docker image signing with Securosys HSM. Secure your containerized applications with advanced key management and signing.
keywords: [hsm, cloud hsm]
toc_min_heading_level: 4
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Getting started with Docker Signing
### HSM Integration guide

:::tip Linux Ubuntu 22 (amd 64)

In this guide, we will use the Linux Ubuntu 22 (amd 64), for other operating systems and Linux distributions please refer to the referenced guides.

:::

:::danger Reminder

Note on some occasion's commands may require root permissions. Your system and docker user permissions should be configured beforehand to avoid any potential permission issues.

:::

This quick start guide provides a comprehensive task listing to download, setup and use the [Securosys Docker Image Signing plugin](/docker_signing/Concepts/DockerSigningConcept).

**1.** Download and install [Notation](https://notaryproject.dev/docs/user-guides/installation/cli/)

```sh
brew install notation
```

Set the `NOTATION_VERSION` environment variable to the version of notation you want to download. The latest version is `1.1.0`:

```sh
export NOTATION_VERSION=1.1.0
```

Confirm that the shasum command succeeds for the archive file (`<ARCHIVE_FILE>`) you downloaded.

Navigate to the directory where you have downloaded the archive file. Expand the downloaded archive file directly into `/usr/bin/`. Replace the variable with your Notation archive file you have downloaded. 

```sh
tar xvzf notation_1.1.0_linux_amd64.tar.gz OK -C  /usr/bin/ notation 
```

Alternatively, you can expand the archive file to a different directory and add it to your path. The command below extracts `<ARCHIVE_FILE>` to an `<EXAMPLE_PATH>/notation-cli/` directory and adds the PATH variable to `~/.bashrc`.  

```sh
tar xvzf home/securosys/notation_1.1.0_linux_amd64.tar.gz -C home/securosys/notation-cli/ notation 

echo 'export PATH="$PATH: home/securosys/notation-cli/"' >> ~/.bashrc
```

**2.** Download Unzip Securosys Docker Signing Plugin files

Follow the instructions provided in the [Download section](./Downloads/).

**3.** Unzip Securosys Docker Signing Plugin binary

```sh
unzip securosys_docker-signing-notation-plugin-executable-latest.zip
```

**4.** Unzip Securosys Docker Signing Plugin configuration file

```sh
unzip securosys_docker-signing-notation-plugin-configuration-latest.zip
```

**5.** Read the Securosys Docker Image Signing Plugin Release Notes

```sh
less securosys_docker-signing-notation-plugin-releasenotes-latest.md
```

**6.** Install Securosys Docker Signing Plugin automatically

```sh
./notation-securosys install
```

**7.** Configure Securosys Docker Signing Plugin configuration file

```sh
cp ./config.json BINARY_PATH/config.json 

vi {BINARY_PATH}/config.json
```

The default `config.json` configuration file must be adapted according to your parameters. 
Replace the variables on the right side with your parameters.

:::note

The configuration settings highlighted below are optional and depend on your REST API setup.

:::

```js {5-13}
{ 
  "restApi": "https://primusdev.cloudshsm.com", 
  "authType": "TOKEN", 
  "token": "TSBtoken",
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

**8.** Verify plugin setup

```sh
notation plugin list
```

**9.** Check plugin connectivity with HSM

```sh
./notation-securosys check-connection
```

**10.** Create a signing key

```sh
./notation-securosys describe-key
```

with input example

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

**11.** Add Adding the signing key

```sh
notation key add "notationKeyId" --id "pluginKeyId" --plugin securosys --plugin-config keyType="RSA-2048" [--plugin-config password="abcd"]
```

**12.** Generate certificate(two options)

- Generate a self-signed certificate

```sh
./notation-securosys generate-selfsign-crt
```

with input example

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

- Generate Certificate Signing Request(CSR) and signing by trusted provider

```sh
./notation-securosys generate-csr
```

with input example

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

Afterwards send the certificate signing request file to the trusted certificate provider manually. Submit the certificate signing request (CSR) to your Certificate Authority / Trust Service Provider to obtain the certificate. After obtaining the certificate, import certificate into truststore 

```sh
./notation-securosys import-certificate
```

with input example

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

**13.** Prepare a sample image for signing or use an already-built image from any public/private registry by pulling it into your local repository. For example

```sh
docker run -d -p 5000:5000 ghcr.io/oras-project/registry:v1.0.0-rc.4
```

Clone from github repository, build the docker image, add image tag (`net-monitor:v1`). Then push it to your local registry: 

```sh
docker buildx build -t localhost:5000/net-monitor:v1 https://github.com/wabbit-networks/net-monitor.git#main

docker push localhost:5000/net-monitor:v1
```

Save the digest value of the image from the output of `docker push`

Set the value of `$IMAGE` environment variable to the name of the image and its digest value to facilitate the use of subsequent commands:

```sh
IMAGE=localhost:5000/net-monitor@sha256:9bba24af535af5ce658c5cd78c865d574cb65443c643d8538b73ea7d3e9bb64e
```

**14.** Change the working directory to `$CONFIG_PATH`:

```sh
cd $CONIFG_PATH
```

**15.** Sign the image:

```sh
notation sign $IMAGE --key "keyId"
```

**16.** Create or edit the notation trust policy `trustpolicy.json` file in the `{NOTATION_CONFIG}` directory. Create the file by executing the command: 

```sh
vi ${HOME}/.config/notation/trustpolicy.json 
```

Add a trust policy with the following structure(example)
```js
{
    "version": "1.0",
    "trustPolicies": [
        {
            "name": "trust-policy-example",
            "registryScopes": [ "*" ],
            "signatureVerification": {
                "level" : "strict" 
            },
            "trustStores": [ "ca:valid-example" ],
            "trustedIdentities": [
                "*"
            ]
        }
    ]
}
```

**17.** Verify image

```sh
notation verify $IMAGE
```