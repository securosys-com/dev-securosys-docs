---
sidebar_position: 4
title: Installing Multiple Microsoft CNG Provider Instances
sidebar_label: Installing Multiple Provider Instances
description: Installing Microsoft CNG via Active Directory Group Policy for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Installing Multiple CNG/KSP Provider Instances

Starting from CNG/KSP Provider **version 1.40**, multiple provider instances can be installed.

These can only be installed using procedures based on the **```KspCfg.ini``` configuration file** and the number of provider instances can only be changed via the installation procedure.

## Installation Steps

1) Adapt the **```KspCfg.ini```** sample file, located within the setup folder.
 The section **```PROVIDERS```** of the **```KspCfg.ini```** configuration file defines how many provider instances will be installed including their naming:
```js
[PROVIDERS]

PROVIDER_1 = Securosys Primus HSM Key Storage Provider 1

PROVIDER_2 = Securosys Primus HSM Key Storage Provider 2

PROVIDER_3 = Securosys Primus HSM MyPartition

PROVIDER_4 = Customer ABC
```

2) Delete further sections in case you configure the provider instances via the graphical Securosys "Key Storage Provider Configuration" tool.

3) To configure the provider during the installation procedure (e.g. Active Directory), define the other sections with the necessary configuration parameters per provider instance. See **```KspCfg.ini```** sample file for explanations.

4) The following example shows the configuration for a provider using a developer account and a second provider using a [CloudHSM Sandbox (SBX)](/cloudhsm/Packages/sandbox) partition:
```js
[PROVIDERS]

PROVIDER_1 = Primus Dev MY-PARTITION

PROVIDER_2 = Primus Sandbox TEST-PARTITION

[PROVIDER_1.HSM_1]

ID = Grimsel_MY-Partition

HOSTNAME = grimsel.securosys.ch

PORTNUMBER = 2320

PRIORITY = 0

HSM_USERNAME = MY-PARTITION

HSM_PASSWORD = KC5b2-VX5NZ-…-8CbGZ-1NCre

[PROVIDER_2.HSM_1]

ID = CloudHSM-Sandbox_TEST-PARTITION

HOSTNAME = a-api.cloudshsm.com

PORTNUMBER = 2320

PRIORITY = 0

HSM_USERNAME = TEST-PARTITION

HSM_PASSWORD = KC5b2-VX5NZ-…-8CbGZ-1NCre

PROXY_USERNAME = myServiceUserName

PROXY_PASSWORD = ivd0rra…s4mpgb7

[PROVIDER_2.HSM_2]

ID = CloudHSM-Sandbox_TEST-PARTITION

HOSTNAME = b-api.cloudshsm.com

PORTNUMBER = 2320

PRIORITY = 0

HSM_USERNAME = TEST-PARTITION

HSM_PASSWORD = KC5b2-VX5NZ-…-8CbGZ-1NCre

PROXY_USERNAME = myServiceUserName

PROXY_PASSWORD = ivd0rra…s4mpgb7

```

## Steps to Increase Provider Instances

The number of provider instances can only be changed by reinstalling the providers. We suggest the following procedure (requiring administrator rights, but no new setup password):

1) First, update to CNG/KSP Provider **V1.41+**, the regular way (if an older version is installed).
1) Open a CMD shell with administrator rights and export the current configuration with the following command (contains blinded secrets!):
```js
"%ProgramFiles%\Securosys\PrimusHsmKsp\kspcfgcons.exe" ExportCfg OldConfig.ini
```

3) Copy this configuration file OldConfig.ini to **```KspCfg.ini```** within your setup folder.
4) Adapt the configuration, section **```PROVIDERS```**, by adding the providers according to your needs (quantity and provider naming). Add the new configuration parameters as shown in [this section](/mscng/Installation/installation-multi.md#installation-steps) or configure them later via the GUI.

Example:
```js
[PROVIDERS]

PROVIDER_1 = Securosys Primus HSM Key Storage Provider

PROVIDER_2 = Securosys Primus HSM New Partition

[PROVIDER_1.HSM_1]

ID = Grimsel Internal

DEVICENAME = GRIMSEL

HOSTNAME = grimsel.securosys.ch

PORTNUMBER = 2320

PRIORITY = 0

CLI = C91C506A5C34BDE8889372FB332A…EC81BD43033915DA486D7C549C261CA79CF6D9F137

CLS = C61C563490E894930BFB202A29F879…0FC42DC39D1798E653C13E57F7983ECE4E12FF99

CLC = 2077E78A123096DA4F588BEAD395…1B691D095E2C9B606215762D8AF2A5CA20F4FD52A5

…
```
5) Uninstall the current CNG/KSP Provider
6) Install the CNG/KSP Provider again by applying the following command, using the configuration file **```KspCfg.ini```** **located within your setup folder** :
```js
MsiExec /quiet /i setup_x64.msi INSTALLCFG=1
```

:::caution Error Warning
Please note that any errors in the **```KspCfg.ini```** file will result in a failed installation of the providers.
:::