---
sidebar_position: 1
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Installation
### Securosys Docker Signing Notation Plugin

:::tip Caution

In this guide we will use the Linux Ubuntu 22 (amd 64), for other operating systems and Linux distributions please refer to the referenced guides.

:::

:::danger Reminder

Note on some occasion's commands may require root permissions. Your system and docker user permissions should be configured beforehand to avoid any potential permission issues.

:::

---


## Install Securosys Docker Signing Notation Plugin by command

From within the same directory where you have [downloaded](../../../Downloads/downloads.md) the Securosys Docker Signing Notation Plugin binary file use the install command below. 
This will automatically create the required directory structure and install the plugin and config in the appropriate directory.

```sh
./notation-securosys install
```

Example output:

```
Installation 

Detected operating system: linux 

Installing plugin to ${HOME}/.config/notation/plugins/securosys 

Generate default plugin config in ${HOME}/.config/notation/plugins/securosys/config.json 


************************************ 


If you put the configuration file into the default directory which is: "${HOME}/.config/notation/plugins/securosys/config.json", all methods will work correct without any additional configuration. 

In other case, if you want to place the file somewhere else, you can use plugin config file with 3 options: 

1) Add this to temporary system environment:  

 export CONFIG_PATH=PathToConfigFile  

2) Add this to your system environment:  

 CONFIG_PATH=PathToConfigFile  

3) Use this before every command:  

 CONFIG_PATH=PathToConfigFile ./notation-securosys [command] 


************************************ 
```

---

## Manual installation of Securosys plugin (option)

Notation searches for available plugins within the plugins directory. 
Verify if Notation has automatically created these directories within the installation. 
If not (currently Notation with version 1.1.0 does not automatically create its directory structure), create the directory structure manually. 
Otherwise, proceed directly to the next chapter. 

Run the below command to create the directories for the [Securosys Docker Signing Notation Plugin](../../../Concepts/DockerSigningConcept.md). 

```sh
mkdir -p {BINARY_PATH}
```

Depending on your operating system, replace the variable with the respective path:

| Variable | Variable options |
|---|---|
| `{BINARY_PATH}`  | The Notary Notation Securosys Docker Trust Image Signing Plugin directory:<br /> Linux: `"${HOME}/.config/notation/plugins/securosys"`<br /> macOS: `"${HOME}/Library/Application\ Support/notation/plugins/securosys"`<br /> Windows: `"%USERPROFILE%/AppData/Roaming/notation/plugins/securosys"` |

Example command for Linux Ubuntu 22:
```sh
mkdir -p ${HOME}/.config/notation/plugins/securosys
```

[View more details on the Notation directory structure](../../../Installation/InstallNotation#notation-directory-structure).

Navigate to the directory where you [downloaded](../../../Downloads/downloads.md) and extracted the 
[Securosys Docker Signing plugin binary file](../../../Installation/DonwloadPlugin#unzip-securosys-docker-signing-notation-plugin-binary) 
and copy the notation-securosys plugin binary to the securosys directory with the following command:

```sh
cp notation-securosys {BINARY_PATH}
```

Example command and command output:

```sh
cp notation-securosys ${HOME}/.config/notation/plugins/securosys
```

Navigate to the directory where you downloaded and extracted the 
[Securosys Docker Signing plugin config file](../../../Installation/DonwloadPlugin#unzip-securosys-docker-signing-notation-plugin-configuration-file) 
and copy the `config.json` plugin binary to the securosys directory with the following command:

```sh
cp config.json {BINARY_PATH}
```

Example command and command output:

```sh
cp config.json ${HOME}/.config/notation/plugins/securosys
```