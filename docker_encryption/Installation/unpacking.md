---
sidebar_position: 1
title: Docker Encryption Plugin - Download & Unpacking Skopeo
sidebar_label: 2. Download & Unpacking
description: Download & Unpacking
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Download & Unpacking

:::tip Caution

In this guide we will use the **Linux Ubuntu 22 (amd 64)**. For other operating systems and Linux distributions, please refer to the referenced guides.

:::

## Download and install Skopeo

To successfully encrypt and decrypt Docker images, it is required to install **Skopeo**.  

To install **Skopeo** on Linux Ubuntu 22, run the following commands: 

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

## Download Securosys Docker Image Encryption Plugin files

Follow the instructions provided in section [Download](/docker_encryption/Downloads/downloads.md) regarding the download of the *Securosys Docker Image Encryption Plugin*.

:::tip TIP

The examples in this guide are given for Securosys Docker Image Encryption Plugin version **`latest`**.
For specific versions, adjust the version variable in the file name and subfolder, e.g. https://securosys.jfrog.io/artifactory/docker-security/docker- encryption/**`v1.0.0`**/securosys\_docker-encryption-skopeo-plugin-**`v1.0.0`**.zip/

:::

The Securosys Docker Image Encryption Plugin files are packaged as a single compressed file that includes: 

- Release Notes 
- Config file 
- Binary file (platform specific) 
- Download-Link file 



When downloading artefacts from the Securosys repository, it is assumed that the files will be downloaded to a **`Securosys`** directory within the **`Downloads`** directory in the user’s home directory.

:::note NOTE

All commands executed during the download, unzip and installation procedures presume that the path to the working directory is:  **`${HOME}/Downloads/Securosys/`**.  



Operating system dependent parameter: 

|**Variable**  |**Variable options** |
| - | - |
|**`${HOME}/`**|**Linux**:  "/home/USERNAME/"<br/> **macOS**:  "/Users/USERNAME/"<br/> **Windows**:  "C:\Users\USERNAME\" |

:::

### Unzip Securosys Docker Image Encryption Plugin binary 

Navigate to the download folder:

```cd ${HOME}/Downloads/Securosys```

Unzip the downloaded `securosys_docker-encryption-skopeo-plugin-executable-latest.zip` file by executing the below command: 

```sh
unzip securosys_docker-encryption-skopeo-plugin-executable-latest.zip
```
```sh
Archive:  securosys_docker-encryption-skopeo-plugin-executable-latest.zip
    extracting: securosys_docker-encryption-skopeo-plugin_darwin_arm64.zip
    extracting:securosys_docker-encryption-skopeo-plugin_netbsd_amd64.zip
    extracting: securosys_docker-encryption-skopeo-plugin_linux_arm64.zip
    extracting: securosys_docker-encryption-skopeo-plugin_freebsd_arm.zip
    extracting: securosys_docker-encryption-skopeo-plugin_linux_amd64.zip
    extracting: securosys_docker-encryption-skopeo-plugin_freebsd_amd64.zip
    extracting: securosys_docker-encryption-skopeo-plugin_linux_386.zip
    extracting: securosys_docker-encryption-skopeo-plugin_solaris_amd64.zip
    extracting: securosys_docker-encryption-skopeo-plugin_openbsd_386.zip
    extracting: securosys_docker-encryption-skopeo-plugin_darwin_amd64.zip
    extracting: securosys_docker-encryption-skopeo-plugin_netbsd_386.zip
    extracting: securosys_docker-encryption-skopeo-plugin_netbsd_arm.zip
    extracting: securosys_docker-encryption-skopeo-plugin_openbsd_amd64.zip
    extracting: securosys_docker-encryption-skopeo-plugin_openbsd_arm.zip
        inflating: securosys_docker-encryption-skopeo-plugin_SHA256SUMS
    extracting: securosys_docker-encryption-skopeo-plugin_freebsd_386.zip 
```

Unzip the binary file for your platform (e.g. for Ubuntu 22): 

```
unzip securosys_docker-encryption-skopeo-plugin_linux_amd64.zip
```
```
Archive:  securosys_ docker-encryption-skopeo-plugin_darwin_arm64.zip
    inflating: skopeo-securosys
    inflating: skopeo-securosys_SHA256SUM
```

The unzipped file contains the following content: 

|**Extraction directory & files** |**File description** |
| - | - |
|./skopeo-securosys |Securosys Docker Image Signing Plugin binary |
|./skopeo-securosys\_SHA265SUM |SHA256 check sum for the notation-securosys binary  |

Confirm that the shasum command succeeds for the extracted binary file with the following command: 

```
shasum --check skopeo_securosys_SHA256SUM
```

The output should result in OK. Example output: 

```
skopeo_securosys: OK
```

### Unzip Securosys Docker Image Encryption Plugin configuration file

Unzip the downloaded `securosys_docker-encryption-skopeo-plugin-configuration-latest.zip` file by executing the below command: 

```sh
unzip securosys_docker-encryption-skopeo-plugin-configuration-latest.zip
```
```
Archive:  securosys_docker-encryption-skopeo-plugin-configuration-latest.zip
    inflating: ocicrypt.conf
        creating: config-files/ 
        creating: config-files/log/
```

The unzipped file contains the following content: 

|**Extraction directory and files** |**File Description** |
| - | - |
|./config.json |Securosys Docker Image Signing Plugin connectionconfiguration file |
|./config-files/log |Folder create by default, not used in this project |

### Read the Securosys Docker Image Encryption Plugin Release Notes

Refer to the release notes for precise instructions pertaining to the applicable release: 

```sh
less securosys_docker-encryption-skopeo-plugin-releasenotes-latest.md
```
