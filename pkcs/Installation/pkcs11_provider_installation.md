---
sidebar_position: 1
title: PKCS#11 - Installation
sidebar_label: Installation
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Installation

## Supported Platforms

-   Linux
    -   RHEL 9/8/7 x64, CentOS (8)/7 x64, Oracle Linux 9/8/7 x64
    -   Ubuntu 24/22/20/18/16 x64
    -   Debian 12/11/10/9/8 x64
    -   SUSE Linux Enterprise Server SLES15/12 (x64)
    -   other OS available upon request

-   Microsoft Windows
    -   Windows 7 x64/x32 or newer
    -   Windows Server 2012R2 or newer

### Library Requirements

-   Linux (GLIBC/GLIBCXX): latest gcc-runtime and g++ runtime
    libraries of the distribution

-   Windows: Visual C++ Redistributable Packages for Visual Studio
    2015

### Packages

The following table shows which package is to be used for the different
OS platforms:

| Package                                             | for Platform (all 64-bit except Win32)                                        |
|-----------------------------------------------------|-------------------------------------------------------------------------------|
| PrimusAPI\_PKCS11-X-2.x.y-**debian10-aarch64**.deb | Ubuntu 22/20, Debian 11/10 **(ARM-64)**                                       |
| PrimusAPI\_PKCS11-X-2.x.y-**debian10-x86\_64**.deb | Ubuntu 22/20, Debian 11/10 **(x86\_64)**                                      |
| PrimusAPI\_PKCS11-X-2.x.y-**rhel7-aarch64**.rpm    | RHEL/CentOS 7 **(ARM-64)**                                                    |
| PrimusAPI\_PKCS11-X-2.x.y-**rhel7-x86\_64**.rpm    | RHEL/CentOS 7 **(x86\_64)**                                                   |
| PrimusAPI\_PKCS11-X-2.x.y-**rhel8-aarch64**.rpm    | RHEL/CentOS 8 **(ARM-64)**                                                    |
| PrimusAPI\_PKCS11-X-2.x.y-**rhel8-x86_64**.rpm     | RHEL/CentOS 8 **(x86\_64)**                                                   |
| PrimusAPI\_PKCS11-X-2.x.y-**rhel7-aarch64**.tar.gz | RHEL/CentOS 7, Ubuntu 16, Debian 8, SLES12 **(ARM-64)**                       |
| PrimusAPI\_PKCS11-X-2.x.y-**rhel7-x86\_64**.tar.gz | RHEL/CentOS 7, Ubuntu 16, Debian 8, SLES12 **(x86\_64)**                      |
| PrimusAPI\_PKCS11-X-2.x.y-**rhel8-aarch64**.tar.gz | RHEL/CentOS 8, Ubuntu 22/20/18, Debian 11/10/9, SLES15 or newer **(ARM-64)**  |
| PrimusAPI\_PKCS11-X-2.x.y-**rhel8-x86\_64**.tar.gz | RHEL/CentOS 8, Ubuntu 22/20/18, Debian 11/10/9, SLES15 or newer **(x86\_64)** |
| PrimusAPI\_PKCS11-X-2.x.y-**win64**.msi            | Windows 7 and newer, Windows Server 2012R2 and newer                          |
| PrimusAPI\_PKCS11-X-2.x.y-**win32**.msi            | Windows 7 and Windows Server 2012R2 and newer                                 |


<Tabs groupId="os">
<TabItem value="unix" label="Linux - Package Installation" default>

Choose the package that fits your distribution and architecture. Then use the system's package manager to install it.

**RHEL/CentOS**
```bash
rpm install ./PrimusAPI_PKCS11-X-*.rpm
```

**Ubuntu/Debian**
```bash
apt install ./PrimusAPI_PKCS11-X-*.deb
```

If the configuration files in _/etc/primus_ exist and they are
different from the ones shipped with the package, the package manager
will ask you if you want to keep your current configuration.

The $PRIMUS\_HOME is located at _/usr/local/primus_. The `ppin` tool
and the test scripts can be found in _\$PRIMUS\_HOME\/bin_.

The installer created a group primus. Add all users using the PKCS#11 provider to this group primus (`sudo usermod -a -G primus user`).
Note: Adding a user to a new group may require logout/login or reboot to update permissions. (as temporary workaround you may use `newgrp primus` or `sudo login -f <user>`)


:::tip

While setting up the provider you need to access the `ppin` tool and
the test scripts (possibly multiple times). Typing the full path to
the tool can be tedious. You can avoid this by adding the
_\$PRIMUS\_HOME\/bin_ directory to your PATH variable.

```bash
export PRIMUS_HOME=/usr/local/primus
export PATH=$PRIMUS_HOME/bin:$PATH
```
The above export statements are only valid for the current shell. Assign the variables at startup to the necessary user(s), which varies depending on used shell and operating system.

:::

</TabItem>
<TabItem value="unix-manual" label="Linux - Manual Provider Installation">
:::info

For updating an existing provider, change the tab to "Linux - Provider Upgrade"

:::

-   Extract `PrimusAPI_PKCS11-version-platform.tar.gz` file with
    privileged user to `/usr/local/`. All the files will be extracted to
    the subdirectory `./primus`. Further documentation assumes the
    provider in `/usr/local/primus/`. If installed to another location
    adapt environment variables etc. accordingly.

-   Add a group and system user `primus`; assign all files to user and group primus (recursive); remove rights for others; add all users using the PKCS#11 provider to the group primus (`sudo usermod -a -G primus user`).

-   Add `/usr/local/primus/bin` to search PATH and `/usr/local/primus/lib` to LD_LIBRARY_PATH.

```bash
sudo tar -xvzf PrimusAPI_PKCS11-version-platform.tar.gz -C /usr/local/

sudo groupadd  primus
sudo useradd --no-create-home --system primus --gid primus
sudo chown -R primus:primus /usr/local/primus
sudo chmod -R o-rwxst /usr/local/primus
sudo usermod -a -G primus user

export PRIMUS_HOME=/usr/local/primus
export PATH=$PRIMUS_HOME/bin:$PATH
export LD_LIBRARY_PATH=$PRIMUS_HOME/lib:$LD_LIBRARY_PATH
```

Note:

- Adding a user to a new group may require logout/login or reboot to
  update permissions. (as temporary workaround you may use `newgrp primus` or `sudo login -f <user>`)

- The above export statements are only valid for the current
  shell. Assign the variables at startup to the necessary user(s),
  which varies depending on used shell and operating system.

For **first time installations**, copy the original configuration
files from `/usr/local/primus/etc` to `/etc/primus` so they don't get
overwritten by further updates:

```bash
sudo mkdir /etc/primus
sudo cp -vp /usr/local/primus/etc/primus.cfg /etc/primus/
sudo cp -vp /usr/local/primus/etc/.secrets.cfg /etc/primus/
```

The following table lists the default installation directory structure
and their content:

| Installation Directory    | Files                                                                            |
|---------------------------|----------------------------------------------------------------------------------|
| /usr/local/primus         | PRIMUS\_HOME, Primus home directory                                              |
| /usr/local/primus/bin     | ppin (Primus permanent PIN utility) |
| /usr/local/primus/lib     | libprimusP11.so (PKCS#11 Provider)                                               |
| /usr/local/primus/etc     | primus.cfg configuration and .secrets.cfg file templates                         |
| /usr/local/primus/include | PKCS#11 header files for integrations                                            |

</TabItem>
<TabItem value="unix-update" label="Linux - Manual Provider Upgrade">
:::info

In case your active configuration is still located under
`/usr/local/primus/etc/` you should move it to `/etc/primus/` as an
upgrade may overwrite configuration files within the folder
/usr/local/primus/etc/ (revisit the tab "Linux - Fresh Provider Installation")

:::

-   (Re)move the current folder `/usr/local/primus`
-   Extract `PrimusAPI_PKCS11-version-platform.tar.gz` file with a
    privileged user to `/usr/local/primus`
-   Assign all files recursively to the previously defined group and user
    (default: primus:primus) and remove rights for others
-   The commands have to be adjusted in case you are using a different
    user group or installed the provider at a different location

```bash
sudo mv -v /usr/local/primus /usr/local/primus.backup
sudo tar -xvzf PrimusAPI_PKCS11-version-platform.tar.gz -C /usr/local/
sudo chown -R primus:primus /usr/local/primus
sudo chmod -R o-rwxst /usr/local/primus
```
</TabItem>
<TabItem value="windows" label="Windows - Provider Installation">

Installing `PrimusAPI_PKCS11-version-platform.msi`, requires admin
privileges to install the required files and registry entries.

:::info

**Backup any active configuration files** `primus.cfg` and `.secrets.cfg`
before updating as they will be overwritten!

:::

Install the provider by double-clicking the
PrimusAPI_PKCS11-1.7.xx-**win64**.msi file or via Active Directory group
policy. If desired, add the program location
(%ProgramFiles%\\Securosys\\Primus P11) to the PATH environment
variable. The following table lists the default installation directory
structure and their content:

| Installation Directory                            | Files                                 |
|---------------------------------------------------|---------------------------------------|
| C:\\Program Files\\Securosys\\Primus P11          | PRIMUS_HOME, Primus directory         |
| %PRIMUS\_HOME%\\primusP11.dll                     | PKCS#11 x64 interface                 |
| %PRIMUS\_HOME%\\primusP11.lib                     | PKCS#11 x64 library                   |
| %PRIMUS\_HOME%\\primus.cfg                        | PKCS#11 configuration file            |
| %PRIMUS\_HOME%\\.secrets.cfg                      | PKCS#11 secrets file                  |
| %PRIMUS\_HOME%\\ppin.exe                          | Primus permanent PIN utility ppin     |
| C:\\Program Files\\Securosys\\Primus P11\\include | PKCS#11 header files for integrations |
</TabItem>
</Tabs>


