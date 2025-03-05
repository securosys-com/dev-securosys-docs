---
sidebar_position: 2
title: Installing Microsoft CNG via Command Line
sidebar_label: Installing via Command Line
description: Installing Microsoft CNG via Command Line for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Installing Microsoft CNG via Command Line

The **CNG/KSP Provider** can be installed and configured by calling the appropriate commands within the CMD window or PowerShell (requires administrator rights).

Since the MSI package is installed via the native Windows Installer, please refer to that documentation for standard command line parameters (e.g. ```msiexec /quiet```).

The installation package also provides the following command line Properties:
|                       |            |                                                                                                                                           
| ---                   | ---           |                                                                                                                                                   
| INSTALLCFG= **0**     | **0**: Don't touch the CNG/KSP provider configuration <br/> **1**: Configure the CNG/KSP provider using the file **```KspCfg.ini```** (located within the same directory as ```setup\_x64.msi```) |         



```INSTALLDIR="xxx" ``` Perform the installation into a specific destination folder
 (default: ```C:\Program Files\Securosys\PrimusHsmKsp\```)

Below are some command line examples:

- Install/configure the CNG/KSP Provider silently, using the configuration stored in the file **```KspCfg.ini```**:

```sh
MsiExec /quiet /i setup\_x64.msi INSTALLCFG=1
```

- Install or update an existing CNG/KSP provider silently, without touching the current configuration:

```sh
MsiExec /quiet /i setup\_x64.msi
```

- Starting the interactive Securosys "Key Storage Provider Configuration" tool located by default at ```%ProgramFiles%\Securosys\PrimusHsmKsp\SecurosysKspCfg.exe```

The tool ```kspcfgcons.exe``` allows to import a pre-prepared KSP configuration file or a previously exported KSP configuration file (administrator rights required; no change of installed instances):
```sh
"%ProgramFiles%\Securosys\PrimusHsmKsp\kspcfgcons.exe" ImportCfg <cfgfile.ini>
```

:::warning Connection Secrets Notice
Please note that connection secrets are encrypted and specific to each machine. Restoring a backup on a different machine will require new setup passwords. Additionally, the number of defined provider instances must match the number of installed providers.
:::