---
sidebar_position: 2
title: Installing Notation for Docker Image Signing
sidebar_label: 2. Install 'Notation'
description: Learn how to install Notation on a Linux Ubuntu 22 (amd64) environment. This guide covers installation steps, configuration, and setting environment variables for the latest Notation version. Ensure smooth setup with detailed instructions and optional checksum verification.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Install 'Notation'
### HSM Integration guide

:::info Caution

In this guide, we will use the _Linux Ubuntu 22 (amd 64)_. For other operating systems and Linux distributions, please refer to the referenced guides.

:::

:::danger Reminder

Please note that on some occasions, commands may require **root permissions**. Your system and docker user permissions should be configured beforehand to avoid any potential permission issues.

:::

---

## Install _Notation_ on Linux Ubuntu 22

To install _Notation_ on:
- a Linux amd64 environment, follow the steps below.
- other supported operating systems, follow the [Notary Notation Installation Guide](https://notaryproject.dev/docs/user-guides/installation/cli/).

:::tip Note

This guide refers to **Notation version 1.1.0**. For the latest versions, refer to the Notary [project documentation](https://notaryproject.dev/docs/user-guides/installation/) and adjust the installation procedure accordingly.

:::

Set the download environment variable to the latest Notation version. Example:

```sh
export NOTATION_VERSION=1.1.0
```

Download Notation for the Linux amd64 environment by using the following command: 

```sh
curl -LO https://github.com/notaryproject/notation/releases/download/v$NOTATION_VERSION/notation_$NOTATION_VERSION\_linux_amd64.tar.gz
``` 

or (on Linux):

```sh
brew install notation
```
Optionally download the Notation checksum file: 

```sh
curl -LO https://github.com/notaryproject/notation/releases/download/v$NOTATION_VERSION/notation_$NOTATION_VERSION\_checksums.txt
```

Confirm that the shasum command succeeds for the archive file (`<ARCHIVE_FILE>`) you downloaded.

Navigate to the directory where you have downloaded the archive file. Expand the downloaded archive file directly into `/usr/bin/`. Replace the variable with your Notation archive file you have downloaded. 

```sh
Sudo tar xvzf <ARCHIVE_FILE> -C /usr/bin/ notation 
```

Example command: 

```sh
tar xvzf notation_1.1.0_linux_amd64.tar.gz OK -C  /usr/bin/ notation 
```

Alternatively, you can expand the archive file to a different directory and add it to your path. The command below extracts `<ARCHIVE_FILE>` to an `<EXAMPLE_PATH>/notation-cli/` directory and adds the PATH variable to `~/.bashrc`.  

```sh
tar xvzf <ARCHIVE_FILE> -C <EXAMPLE_PATH>/notation-cli/ notation 


echo 'export PATH="$PATH:<EXAMPLE_PATH>/notation-cli/"' >> ~/.bashrc 
```

Example command: 

```sh
tar xvzf home/securosys/notation_1.1.0_linux_amd64.tar.gz -C home/securosys/notation-cli/ notation 

echo 'export PATH="$PATH: home/securosys/notation-cli/"' >> ~/.bashrc
```

---

### Notation Directory Structure

| Directory variable | Description |
|---|---|
| `{NOTATION_BIN}`  | The path for the notation binary. There is no default `NOTATION_BIN` path at user level since the notation binary can be put anywhere as long as it in the PATH environment variable.  | 
| `{NOTATION_CONFIG}`  | Directory for configurations, e.g. `config.json`, `signingkeys.json`, `trustpolicy.json`, `truststore`<br /> Linux: `"${HOME}/.config/notation/"`<br />  macOS: `"${HOME}/Library/Application\ Support/notation/"`<br /> Windows: `"%USERPROFILE%/AppData/Roaming/notation/"`.  |
| `{NOTATION_LIBEXEC}` | Directory for binaries not meant to be executed directly by users' shell or scripts. The plugin binary and its config.json is installed under the `{NOTATION_LIBEXEC}` directory following the pattern: `{NOTATION_LIBEXEC}/plugins/securosys/notation-securosys`<br /> Linux: `"${HOME}/.config/notation/"`<br /> macOS: `"${HOME}/Library/Application\ Support/notation/"`<br /> Windows: `"%USERPROFILE%/AppData/Roaming/notation/"`|
