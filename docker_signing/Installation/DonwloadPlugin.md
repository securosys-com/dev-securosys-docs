---
sidebar_position: 3
title: Install Docker Signing Notation Plugin
sidebar_label: 3. Download Plugin
description: Download and install the Securosys Docker Signing Notation Plugin on Linux Ubuntu 22 (amd64). This detailed tutorial covers downloading, unzipping, and configuring the plugin, along with verifying the installation with checksum commands. Ensure smooth setup by following platform-specific instructions.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Download _Docker Signing_ Plugin
### HSM Integration guide

:::tip Linux Ubuntu 22 (amd 64)

In this guide, we will use the Linux Ubuntu 22 (amd 64), for other operating systems and Linux distributions please refer to the referenced guides.

:::

:::danger Reminder

Note that on some occasions, commands may require root permissions. Your system and docker user permissions should be configured beforehand to avoid any potential permission issues.

:::

---

## Download Securosys Docker Signing Notation Plugin files

[Download](../Downloads/downloads.md) the latest zip file, `securosys_docker-signing-notation-plugin-latest.zip`.

The Securosys **Docker Signing Notation Plugin** files are packaged as a single archive which includes: 

- Release Notes 
- Config file 
- Binary file (platform specific) 
- Download-Link file

:::tip Note

In the examples below, replace the `<Username>` and `<Password>` variables with the credentials provided in the **Download-Link txt-file**. 

:::

:::tip Note

The examples in this guide are given for Securosys Docker Signing Notation Plugin version latest.  
For specific versions, adjust the version variable in the file name and subfolder, e.g. `https://securosys.jfrog.io/artifactory/docker-security/docker-signing/v1.3.1/securosys_docker-signing-notation-plugin-v1.3.1.zip/`

:::

When downloading artefacts from the Securosys repository, it is assumed that the files will be downloaded to a "Securosys" directory within the "Downloads" directory in the user's home directory.

:::tip Note

All commands executed during the download, unzip and installation procedures presume that the path to the working directory is: `"${HOME}/Downloads/Securosys/"`. 

:::

Navigate to the download folder: 

```sh
cd {HOME}/Downloads/Securosys 
```

Operating system dependent parameter:

| Variable | Variable Options |
|---|---|
| `{HOME}/`  | Linux: `"/home/USERNAME/"`<br /> macOS: `"/Users/USERNAME/"`<br /> Windows: `"C:\Users\USERNAME\"` |

---

## Unzip Securosys Docker Signing Notation Plugin binary

Unzip the [downloaded](../Downloads/downloads.md) `securosys_docker-signing-notation-plugin-executable-latest.zip` file by executing the below command: 

```sh
unzip securosys_docker-signing-notation-plugin-executable-latest.zip  

Archive:  securosys_docker-signing-notation-plugin-executable-latest.zip 

 extracting: securosys_docker-signing-notation-plugin_freebsd_386.zip   

 extracting: securosys_docker-signing-notation-plugin_openbsd_amd64.zip   

 extracting: securosys_docker-signing-notation-plugin_darwin_arm64.zip   

 extracting: securosys_docker-signing-notation-plugin_linux_amd64.zip   

 extracting: securosys_docker-signing-notation-plugin_freebsd_arm.zip   

 extracting: securosys_docker-signing-notation-plugin_linux_arm64.zip   

 extracting: securosys_docker-signing-notation-plugin_netbsd_amd64.zip   

 extracting: securosys_docker-signing-notation-plugin_netbsd_386.zip   

 extracting: securosys_docker-signing-notation-plugin_openbsd_386.zip   

 extracting: securosys_docker-signing-notation-plugin_freebsd_amd64.zip   

 extracting: securosys_docker-signing-notation-plugin_solaris_amd64.zip   

 extracting: securosys_docker-signing-notation-plugin_netbsd_arm.zip   

 extracting: securosys_docker-signing-notation-plugin_windows_amd64.zip   

 extracting: securosys_docker-signing-notation-plugin_linux_386.zip   

 extracting: securosys_docker-signing-notation-plugin_darwin_amd64.zip   

 extracting: securosys_docker-signing-notation-plugin_openbsd_arm.zip   

 extracting: securosys_docker-signing-notation-plugin_windows_386.zip   

  inflating: securosys_docker-signing-notation-plugin_SHA256SUMS 
```

Unzip the binary file for your platform (e.g. for Ubuntu 22):

```sh
unzip securosys_docker-signing-notation-plugin_linux_amd64.zip 

Archive:  securosys_docker-signing-notation-plugin_linux_amd64.zip 

  inflating: notation-securosys       

  inflating: notation-securosys_SHA256SUM 
```

The unzipped file contains the following content:

| Extraction directory and files | File description |
|---|---|
| `./notation-securosys`  | Securosys Docker Signing Notation Plugin binary |
| `./notation-securosys_SHA265SUM`  | SHA256 check sum for the notation-securosys binary |


Confirm that the shasum command succeeds for the extracted binary file with the following command: 
```sh
shasum --check notation-securosys_SHA256SUM
```

The output should result OK. Example output: 
```sh
notation-securosys: OK
```

---

## Unzip Securosys Docker Signing Notation Plugin configuration file

Unzip the downloaded .zip file by executing the below command:
```sh
unzip securosys_docker-signing-notation-plugin-configuration-latest.zip  

Archive:  securosys_docker-signing-notation-plugin-configuration-latest.zip 

  inflating: config.json              

   creating: config-files/ 

   creating: config-files/log/ 

  inflating: input_example.json
```

The unzipped file contains the following content:

| Extraction directory and files | File description |
|---|---|
| `./config.json`  | Securosys Docker Signing Notation Plugin connection configuration file |
| `./input_example.json`  | JSON formatted input examples of Secursosys Docker Signing Notation Plugin command parameters |
| `./config-files/log`  | Folder create by default, not used in this project |


:::tip Note
Refer to the [release notes](../Downloads/release_notes.md) for precise instructions pertaining to the applicable release:
`less securosys_docker-signing-notation-plugin-releasenotes-latest.md`
:::

