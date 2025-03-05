---
sidebar_position: 1
title: Docker Encryption Plugin - Encrypt an Image
sidebar_label: Encrypt an Image
description: Encrypt an Image
keywords: [hsm, cloud hsm]
---


# Encrypt an Image

:::tip CAUTION
This guide assumes the operating system used is **Linux Ubuntu 22**.
:::

Build your image for encryption or use an already built image from any public/private registry by pulling it into your local repository. Make sure that the image is OCI-complaint.

In the subsequent steps, we set up a registry on the local machine using the ORAS CLI project registry. We proceed to build and tag a Docker image from the Alpine sources on the GitHub project, and subsequently we build and push it to the local registry. Following this, we encrypt this image. 

For the sake of simplicity, the procedure does not differentiate between the roles of the signature issuer and verifier.

:::tip CAUTION

The following example creates a registry with [oras-project/registry](https://github.com/oras-project/distribution/pkgs/container/registry) in your local Docker environment. This registry should only be used for development purposes. When using other registries, ensure the registry is compatible with OCI Image specification v1.1.0.

:::

Create a local registry based on the oras-project: 

```
docker run -d -p 5000:5000 ghcr.io/oras-project/registry:v1.0.0-rc.4
```

:::note Note

For Apple silicon, add the --platform linux/arm64 parameter.

:::

:::tip Caution

For the example remember to install and run Docker. Follow the instructions provided in [Prerequisites - Docker installation](/docker_encryption/Installation/prerequisites#docker-installation), start docker using command: **`sudo systemctl start docker`**.

:::

## Encrypt Docker image using local registry

To create your custom Docker image, start by creating a Dockerfile in an empty directory (the root directory of your project), e.g. `~/Downloads/Securosy`: 

```sh
vi ~/Downloads/Securosys/Dockerfile
```

The Dockerfile contains the instructions on how to build your Docker image. Edit the *Dockerfile* and add below content, then save: 

```sh
FROM alpine:latest
```

Build and tag your Docker image. Tagging the image as `localhost:5000/alpine:myownimage`

```sh
docker build . -t localhost:5000/alpine:myownimage
```

Since the image is currently only available locally, push it to the local registry using the following command: 

```sh
docker push localhost:5000/alpine:myownimage
```

    Use skopeo to copy and encrypt the image from the local registry to another repository, with TLS verification disabled. Adapt the values `<password>`, `<pathToConfig>`, `<keyLabel>`, `<myownimage>`to your needs. 

    ```sh
    [KEY_PASSWORD=password] OCICRYPT_KEYPROVIDER_CONFIG=/<pathToConfi>/ocicrypt.conf skopeo --override-os linux copy --encryption-key provider:skopeo-securosys:<keyLabel> --src-tls- verify=false --dest-tls-verify=false docker://localhost:5000/alpine:<myownimage> docker://localhost:5000/alpine:latest-encrypted-<keyLabel>
    ```
    Command variables:

    |**Command Parameters:** |**Description** |
    | - | - |
    |KEY_PASSWORD=**`<password>`**|Replace the **`<password>`** variable with the -key- password parameter in the configuration file. Omit if key has no password. |
    |**`<keyLabel>`**|Replace the variable with the label of your encryption key already generated on the HSM. |
    |OCICRYPT_KEYPROVIDER_CONFIG=/**`<pathToConfig>`**/ocicrypt.conf |Replace the **`<pathToConfig>`** with your path to the Securosys Docker Image Encryption plugin config, e.g. **`${BINARY_PAT}`**|
    |--src-tls-verify=false <br/> --dest-tls-verify=false |Remove the tls-verify options if you use a remote registry. |

    :::warning CAUTION

    In our example, TLS is disabled because we are working with a local. If you use a remote registry, TLS will be used, and the tls-verify option --**dest-tls-verify=false** must be removed. 
    :::

    ### Example

    Example commands and sample output of above sequence.

    Build and tag:

    ```sh
    docker build . -t localhost:5000/alpine:myownimage 
    ```
    ```
    [+] Building 0.0s (5/5) FINISHED                                docker:desktop-linux
    => [internal] load build definition from Dockerfile             0.0s  
=> => transferring dockerfile: 57B                              0.0s
=> [internal] load .dockerignore                                0.0s
=> => transferring context: 2B                                  0.0s
=> [internal] load metadata for docker.io/library/alpine:latest 0.0s
=> CACHED [1/1] FROM docker.io/library/alpine:latest            0.0s
=> exporting to image                                           0.0s
=> => exporting layers                                          0.0s
=> => writing image sha256:38f483c2ec62748751a5096708f9633c581f93454e202da84e9d7951a63a4a22     0.0s
=> => naming to localhost:5000/alpine:myownimage                0.0s
View build details: docker-desktop://dashboard/build/desktop-linux/desktop- linux/n2jop7yjpp8ipm4e8zqcb51yr
```

Push image to local registry:

```sh
docker push localhost:5000/alpine:myownimage 
```
```
The push refers to repository [localhost:5000/alpine]
3ce819cc4970: Pushed
myownimage: digest: 
sha256:0dfd193b4c325c6158aa178c3b46a34c060e23a0f6f3f87480b9b9131b707f30 
size: 527 
```

Encrypt image: 

```sh
OCICRYPT_KEYPROVIDER_CONFIG=/home/Securosys/Securosys/skopeo/ocicrypt.conf skopeo -- override-os mac copy --encryption-key provider:skopeo-securosys:SecurosysEncKey21 --src- tls-verify=false --dest-tls-verify=false docker://localhost:5000/alpine:myownimage docker://localhost:5000/alpine:latest-encrypted-SecurosysEncKey01
```
```
Getting image source signatures 
Copying blob c30352492317 done   |  
Copying config 1021c26281 done   |  
Writing manifest to image destination 
```

## Simplified Docker Image Encryption Example (optional)

Skopeo is part of the tools commonly used in container orchestration and deployment workflows. The primary purpose of skopeo is to work with container images across different container image registries. It is a command-line utility used in the container ecosystem to perform various operations related to container image copying, moving, inspection, and it allows transferring images without running a Docker daemon.  

:::note NOTE

For a quick verification of the encryption functionality, you can bypass above steps and use Skopeo to directly copy the image from docker.io using the following command: `skopeo copy docker:imageExample` 
:::

In accordance with [Skopeo](https://github.com/containers/skopeo), you have the option to utilize skopeo directly for copying and encrypting Docker images, eliminating the necessity for a registry. Although this approach may not precisely mirror real-world scenarios, it facilitates a prompt verification of the encrypt/decrypt functionality. 

Navigate to the skopeo working directory **`${BINARY_PATH}`**, as defined in chapter [Installation - Install the Securosys Docker Image Encryption Plugin binary](/docker_encryption/Installation/configuration#install-the-securosys-docker-image-encryption-plugin-binary).

We are using image of the Alpine project from docker.io as a sample and copy it to the working directory: 

```sh
skopeo copy docker://docker.io/amd64/alpine:latest oci:alpine
```

:::note NOTE

For Apple silicon use alpine image arm64v8.
:::

Next, encrypt the image as shown below:

```sh
[KEY_PASSWORD=password] OCICRYPT_KEYPROVIDER_CONFIG=pathToConfig/ocicrypt.conf skopeo --override-os linux copy --encryption-key provider:skopeo-securosys:keyLabel oci:alpine oci:apline-encrypted 
```

Command variables: 

|**Command Parameters:** |**Description** |
| - | - |
|KEY_PASSWORD=**`<password>`**|Replace the **`<password>`** variable with the -key- password parameter in the configuration file. Omit if key has no password. |
|**`<keyLabel>`**|Replace the variable with the label of your encryption key already generated on the HSM. |
|OCICRYPT_KEYPROVIDER_CONFIG=/**`<pathToConfig>`**/ocicrypt.conf |Replace the **`<pathToConfig>`** with your path to the Securosys Docker Image Encryption plugin config, e.g. **`<BINARY_PATH>`**|

### Example

Example command and its output. Encryption of image `alpine` and storing it as `alpine-encrypted`. For a key without password: 

```
OCICRYPT_KEYPROVIDER_CONFIG=/home/Securosys/Securosys/skopeo/ocicrypt.conf skopeo -- override-os linux copy --encryption-key provider:skopeo-securosys:SecurosysEncKey01 oci:alpine oci:alpine-encrypted
```
```
Getting image source signatures Copying blob 05e7bc50f07f done Copying config 5c41fd95ee done Writing manifest to image destination 
```

The encrypted image will be created in directory `alpine-encrypted`` under the directory where you run the command.
