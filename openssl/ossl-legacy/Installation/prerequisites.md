---
sidebar_position: 1
title: Prerequisites - OpenSSL 1.1 for PKCS#11
sidebar_label: Prerequisites
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites
:::info

For the OpenSSL integration to work the PKCS#11 API needs to be configured
properly. You can find the instruction in the [PKCS#11 API Documentation][pkcs-overview].

:::

## Requirements

<Tabs groupId="distro">
<TabItem value="rhel8" label="RHEL 8" default>
- Primus HSM PKCS#11 Provider (1.5.6+)
- p11-kit (0.23+)
- opensc (0.19+)

For OpenSSL:
- openssl (1.1.1+)
- openssl-pkcs11 (0.4.10+)

For Apache/httpd
- httpd (2.4.37+)
- mod_ssl (2.4.31+)

For Nginx
- nginx (1.14.1+)

</TabItem>
<TabItem value="ubuntu20" label="Ubuntu 20">
- Primus HSM PKCS#11 Provider (1.5.6+)
- p11-kit (0.23+)
- opensc (0.19+)

For OpenSSL:
- openssl (1.1.1+)
- libengine-pkcs11-openssl (0.4.10+)

For Apache/httpd
- apache2 (2.4.42+)

For Nginx
- nginx (1.14.1+)
</TabItem>
<TabItem value="debian10" label="Debian 10">
- Primus HSM PKCS#11 Provider (1.5.6+)
- p11-kit (0.23+)
- opensc (0.19+)
- libccid
- opensc-pkcs11
- pcscd

For OpenSSL:
- openssl (1.1.1+)
- libengine-pkcs11-openssl (0.4.10+)

For Apache/httpd
- apache2 (2.4.42+)

For Nginx
- nginx (1.14.1+)
</TabItem>
</Tabs>

:::note

The following setup was used throughout this guide:
| Software           | Version                                          |
|--------------------|--------------------------------------------------|
| Linux distribution | Rocky Linux release 8.9 (Green Obsidian) [^1]    |
| Primus HSM PKCS#11 | v2.1.3                                           |
| p11-kit            | 0.23.22-1.el8.x86\_64                            |
| opensc             | 0.20.0-8.el8\_9.x86\_64                          |
| openssl            | 1:1.1.1k-12.el8\_9.x86\_64                       |
| openssl-pkcs11     | 0.4.10-3.el8.x86\_64                             |
| httpd              | 2.4.37-62.module+el8.9.0+1436+2b7d5021.x86\_64   |
| mod\_ssl           | 1:2.4.37-62.module+el8.9.0+1436+2b7d5021.x86\_64 |
| nginx              | 1:1.14.1-9.module+el8.4.0+542+81547229.x86\_64   |

:::

[^1]: A RHEL 8 compatible Linux distribution.

[pkcs-overview]: /pkcs/overview
