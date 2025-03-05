---
sidebar_position: 1
title: Automated installation Docker Signing Plugin
sidebar_label: Automated (recommended)
description: Follow this guide to easily install the Securosys Docker Signing Notation Plugin automatically on Linux Ubuntu 22. This simple, automated process handles directory creation, plugin installation, key setup, and self-signed certificate generation for development.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Automated Installation (recommended)
### Securosys Docker Signing Notation Plugin

The [Securosys Docker Signing Notation Plugin](../../Concepts/DockerSigningConcept.md) binary is required for the connection between _Notation: and the Securosys Transaction Security Broker (TSB). 
The TSB in turn communicates to the Securosys HSMs and allows for use of more intricate key policies.

:::tip Caution

In this guide we will use the Linux Ubuntu 22 (amd 64), for other operating systems and Linux distributions please refer to the referenced guides.

:::

:::danger Reminder

Note on some occasion's commands may require root permissions. Your system and docker user permissions should be configured beforehand to avoid any potential permission issues.

:::

:::danger Important

The plugin binary must exist in the same path as install script.

:::

## Installation steps

From within the same directory where you have downloaded the Securosys Docker Signing Notation Plugin binary file use the script `install.sh` which is located in path `./etc/example/install.sh`. Script, in just a few steps, will automatically create the required directory structure, install the plugin and config in the appropriate directory and will also create initial signing key and selfsigned certificate(example for Development purposes). Run:

```sh
./install.sh
```

After running the command, 4 questions will appear one by one, which, depending on the option selected, will configure the plugin appropriately. First question:

```
1) Do You have notation-securosys plugin installed already? (Yes/No):
```
If this is the first run and we want to install the plugin, enter "No". In this step, the plugin will be installed(in the correct place depending on your operating system) along with the appropriate `trustpolicy.json` file.

In the next step, a question will appear:

```
2) Do You want to prepare configuration file? (Yes/No):
```

If you want the configuration file to be created automatically, enter "Yes". 
Then you must enter all the necessary parameters needed to connect to the HSM, such as RestApiUrl, authorization type, etc. For more details for each parameter, see [chapter](/docker_signing/Installation/Plugin-Installation/AlternativeWays/PluginConfiguration#configure-securosys-plugin-configuration-file).

In the third step, the topic of creating keys will appear:

```
3) Do You want to add new Key to notation? (Yes/No):
```

In this step after entering "Yes", enter the required parameters to create the signing key, such as key label, key type, password (optional) and key policy (optional). In addition to this, the key will also be automatically added to notation. For more details, see [chapter](/docker_signing/Tutorials/CreateSigningKey).

The last step will ask you about generating a self-signed certificate:

```
4) Do you want to create a selfsigned certificate (For Development purposes): [Yes]/No:
```

To test the correct operation of the plugin, you can enter "Yes", then after entering the necessary parameters, a self-signed certificate will be generated. For more details, see [chapter](/docker_signing/Tutorials/SelfSignedCert).
