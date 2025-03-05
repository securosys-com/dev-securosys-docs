---
sidebar_position: 2
title: Installing PKCS#11 API for OpenSSL v1.x
sidebar_label: Installation
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL,PKCS#11]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Installing PKCS#11 API for OpenSSL v1.x

This guide assumes familiarity with the Primus HSM, Windows and/or Linux operating system installation and configuration, OpenSSL setup, and related procedures.

It does not cover every step of the hardware and software setup in detail.

<Tabs groupId="os">
<TabItem value="os-windows" label="Windows 11" default>

The following installation and configuration instructions are based on Windows 11 and may vary slightly for other versions of Windows.

:::note

Please ensure that you meet all the listed [prerequisites](prerequisites.md) before proceeding. Adhering to these requirements is essential for a smooth setup and successful integration. Failure to do so may result in compatibility issues or functionality problems during the configuration process.

:::

## PKCS#11 Engine Plugin

The PKCS#11 engine plugin is part of the [OpenSC/libp11 repository](https://github.com/OpenSC/libp11) on GitHub. As there are no Windows binaries releases provided for this project, the engine must be built manually. You can find detailed build instructions for different Windows build environments [here](https://github.com/OpenSC/libp11/blob/master/INSTALL.md).

To compile the PKCS#11 engine on Windows, follow these steps:

1. **Download and Install Microsoft Visual Studio**

   Download [Microsoft Visual Studio](https://visualstudio.microsoft.com/downloads/). When you run the executable, it will open the Visual Studio Installer. Choose the **"C++ build tools"** workload to install only the necessary tools.

2. **Download/Clone the PKCS#11 Engine**

   You have two options to obtain the necessary files from the [OpenSC/libp11 repository](https://github.com/OpenSC/libp11) on GitHub. You can either create a clone of the repository directly onto your local machine using Git, or you can download the repository as a ZIP file. If you choose the ZIP option, simply extract the contents into a designated folder on your PC, e.g. `C:\Users\<Username>\source\repos\libp11`. Both methods will give you access to the code required for the build process.

3. **Compile a 64-bit Version**

   To open the **"x64 Native Tools Command Prompt"**, go to `Start > All Programs > Visual Studio 2022 > x64 Native Tools Command Prompt`. Once it's open, navigate to the folder where the files from the OpenSC/libp11 repository are stored. Then, run the `nmake` command, as shown below, thereby making sure to replace `OPENSSL_DIR` with the path to your OpenSSL installation.

   ```cmd
   **********************************************************************
   ** Visual Studio 2022 Developer Command Prompt v17.11.4
   ** Copyright (c) 2022 Microsoft Corporation
   **********************************************************************
   [vcvarsall.bat] Environment initialized for: 'x64'

   C:\Program Files\Microsoft Visual Studio\2022\Community>cd c:\Users\<Username>\source\repos\libp11

   c:\Users\<Username>\source\repos\libp11>nmake /f Makefile.mak OPENSSL_DIR="C:\Program Files\OpenSSL-Win64" BUILD_FOR=WIN64
   ```

   To confirm if the compilation was successful, look for messages like `Build succeeded` or `0 errors`. If the build failed, you will see messages such as `Build failed` or specific error messages. Always check the last lines of the output for a summary.

   If any of your builds fail for any reason, ensure you clean the `src` directory of `obj` files before re-making.

4. **Locate the compiled PKCS#11 Engine Plugin**
  
   If the compilation is successful, the `pkcs11.dll` file will be located in the `libp11\src` folder. This is the OpenSC PKCS#11 Engine Plugin file you need.

</TabItem>

<TabItem value="os-linux" label="Linux">

The installation instructions for Linux-based systems will be added to this guide in the future. While we are working to provide these details, please note that it may take some time before they are fully available. We appreciate your patience and understanding as we continue to expand our documentation.

</TabItem>
</Tabs>
