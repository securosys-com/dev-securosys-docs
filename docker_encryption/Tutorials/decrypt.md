---
sidebar_position: 2
title: Docker Encryption Plugin - Decrypt an Image
sidebar_label: Decrypt an Image
description: Decrypt an Image
keywords: [hsm, cloud hsm]
---

# Decrypt an Image

After successfully encrypting an image, proceed with the decryption.  

:::tip CAUTION
This guide assumes the operating system used is **Linux Ubuntu 22**.
:::

## Decrypt Docker image using local registry

Decrypt the formerly created encrypted docker image and tag it to alpine:latest-decrypted using below command. Adapt the values `<password`, `<pathToConfig>`, `<keyLabel>` to your needs. 

```sh
[KEY_PASSWORD=password] OCICRYPT_KEYPROVIDER_CONFIG=/<pathToConfig>/ocicrypt.conf skopeo --override-os mac copy --decryption-key provider:skopeo-securosys:<keyLabel> --src-tls-verify=false -- dest-tls-verify=false docker://localhost:5000/alpine:latest-encrypted-<keyLabel> docker://localhost:5000/alpine:latest-decrypted 
```
### Example

Example command and its output: 

```sh
OCICRYPT_KEYPROVIDER_CONFIG=/home/Securosys/Securosys/skopeo/ocicrypt.conf skopeo -- override-os mac copy --decryption-key provider:skopeo-securosys:SecurosysEncKey21 --src- tls-verify=false --dest-tls-verify=false docker://localhost:5000/alpine:latest- encrypted-SecurosysEncKey01 docker://localhost:5000/alpine:latest-decrypted
```
```
Getting image source signatures 
Copying blob d1a9fbe0d395 done   |  
Copying config 1021c26281 done   |  
Writing manifest to image destination
```

## Simplified Docker Image Decryption Example (optional)

Decrypt the previously encrypted image as shown below, following the example of using skopeo directly without the registry: 

```
[KEY_PASSWORD=<password>] OCICRYPT_KEYPROVIDER_CONFIG=/<pathToConfig>/ocicrypt.conf skopeo --override-os linux copy --decryption-key provider:skopeo-securosys:<keyLabel> oci:alpine-encrypted oci:alpine-decrypted
```

Command parameters: 

|**Command parameters:** |**Description** |
| - | - |
|KEY_PASSWORD=**`<password>`**|Replace the **`<password>`** variable with the -key- password parameter in the configuration file. |
|||
|OCICRYPT_KEYPROVIDER_CONFIG=/**`<pathToConfig>`**/ocicrypt.conf |Replace the **`<pathToConfig>`** with your path to the Securosys Docker Image Encryption plugin config, **`${CONFIG_PATH}`** as set in chapter [Installation - Install the Securosys Docker Image Encryption Plugin config file ](/docker_encryption/Installation/configuration#install-the-securosys-docker-image-encryption-plugin-config-file)|


### Example

Example command and its output for the decryption of an image “alpine-encrypted” and storing it as “alpine-decrypted”. For a key without password: 

```sh
OCICRYPT\_KEYPROVIDER\_CONFIG=/home/Securosys/Securosys/skopeo/ocicrypt.conf skopeo -- override-os linux copy --decryption-key provider:skopeo-securosys:SecurosysEncKey01 oci:alpine-encrypted oci:alpine-decrypted 
```
```
Getting image source signatures
Copying blob 4029b2314db9 done
Copying config 5c41fd95ee done
Writing manifest to image destination 
```

After successfully decryption the image is stored in directory *decrypted* and can be used normally.