---
sidebar_position: 0
title: Getting started with Docker Encryption HSM Plugin
sidebar_label: Quick Start
description: Getting started with Docker Encryption HSM Plugin
keywords: [hsm, cloud hsm]
---


import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Getting Started with Docker Encryption

This quick start guide provides a comprehensive task listing to download, setup and use the Securosys Docker Image Encryption plugin. For more detailled detailled instructions consult the [Installation](../category/installation) and [Tutorial](../category/tutorial) section.

1. Download and install [Skopeo](https://github.com/containers/skopeo)

<Tabs groupId="device-setup">
  <TabItem value="linux" label="Linux" default>
```sh
sudo apt-get -y update
sudo apt-get -y install skopeo
```
  </TabItem>
  <TabItem value="mac" label="MacOS" default>   
```sh
brew install skopeo
```
  </TabItem>
</Tabs>

2. Download Securosys Docker Image Encryption Plugin files

    Follow the instructions provided in section [Download](../Downloads/downloads.md).

3. Unzip Securosys Docker Image Encryption Plugin binary
```sh
unzip securosys_docker-encryption-skopeo-plugin-executable-latest.zip
```
4. Unzip Securosys Docker Image Encryption Plugin configuration file
```sh
unzip securosys_docker-encryption-skopeo-plugin-configuration-latest.zip
```
5. Read the Securosys Docker Image Encryption Plugin Release Notes
```sh
less securosys_docker-encryption-skopeo-plugin-releasenotes-latest.md
```

6. Create an encryption key on the HSM (unless a key has been generated before)

<Tabs groupId="device-setup">
  <TabItem value="linux" label="Linux / MacOS" default>
```sh
curl -X 'POST' \ 
# highlight-next-line-note
# highlight-next-line
    '<TSB_APIendpoint>/v1/key' \
    -H 'accept: application/json' \ 
# highlight-next-line-note
# highlight-next-line
    -H 'Authorization: Bearer ergq0ejgadjlfkgjaldfjgaodf9gjad0f9hgadfhgadhfogiah…'\
    -H 'Content-Type: application/json' \ 
    -d '{ 
# highlight-next-line-note
# highlight-next-line
    "label": "SecurosysEncKey01", 
    "algorithm": "RSA", 
    "keySize": 2048, 
    "attributes": {
# highlight-start
# highlight-note-start
     "encrypt": true,
     "decrypt": true,
# highlight-end     
# highlight-note-end
     "verify": false, 
     "sign": false, 
     "wrap": false, 
     "unwrap": false, 
     "derive": false, 
     "bip32": false, 
     "extractable": false,
     "modifiable": false,
     "destroyable": false,
     "sensitive": true,
     "copyable": false 
    } 
} '
``` 
  </TabItem>
  <TabItem value="Windows" label="Windows" default>   
curl command for Windows
  </TabItem>
</Tabs>

7. Copy the plugin binary `skopeo-securosys` and the `ocicrypt.conf` to `${HOME}/Securosys/skopeo` and adapt the parameters `pathToExecutable`, `-cipher-algorithm`, `tsb-api-endpoint`, `-auth`, `-token` , `-keyOperationToken`, `-publicKey`, `-privateKey` according to your environment in `ocicrypt.conf`: 

:::note NOTE
When utilizing [CloudHSM](../../cloudhsm/overview) service, refer to [Cloud Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) for accurate API-Endpoint URI. For on-premise deployments, verify API-Endpoint URI with your administrator. Contact your service administrator for authentication credentials in any setup (on-prem or cloud).
:::

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



8. Prepare a sample image for encryption or use an already-built image from any public/private registry by pulling it into your local repository. Ensure the image is OCI-compliant ([Skopeo](https://github.com/containers/skopeo)). As an example, we utilize the Alpine project's image from docker.io and copy it to the working directory:

```sh
skopeo copy docker://docker.io/amd64/alpine:latest oci:alpine
```

9. Encrypt the image as shown below:
```sh
[KEY_PASSWORD=password] OCICRYPT_KEYPROVIDER_CONFIG=<pathToConfig>/ocicrypt.conf skopeo --override-os linux copy --encryption-key provider:skopeo-securosys:<keyLabel> oci:alpine oci:apline-encrypted 
```

10. Decrypt the image as shown below:
```
[KEY_PASSWORD=<password>] OCICRYPT_KEYPROVIDER_CONFIG=/<pathToConfig>/ocicrypt.conf skopeo --override-os linux copy --decryption-key provider:skopeo-securosys:<keyLabel> oci:alpine-encrypted oci:alpine-decrypted
```