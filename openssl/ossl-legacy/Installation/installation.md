---
sidebar_position: 2
title: Installing OpenSSL 1.1 for PKCS#11
sidebar_label: Installation
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Installation

This procedure provides a straightforward integration process, which
has been tested. Please take notice that there may be other ways to
achieve it. This guide assumes that you are familiar with the Primus
HSM, Linux operating system installation and configuration procedures,
OpenSSL configuration etc. and does not cover every step of the
hardware and software setup process. The application note does not
cover firewall nor SELinux configuration.  The following installation
and configuration description are based on Rocky Linux 8 and may
slightly differ for other Linux distributions.


:::note

Versions of the Securosys PKCS#11 Provider before v2.1 were shipped
including a version of OpenSSL and httpd. It is not recommended to use
these versions anymore. Make sure that they are no longer part of the
`PATH` or `LD_LIBRARY_PATH` variables. Use the following variable
definitions instead:

```bash
export PRIMUS_HOME=/usr/local/primus
export PATH=$PRIMUS_HOME/bin:$PATH
export LD_LIBRARY_PATH=$PRIMUS_HOME/lib:$LD_LIBRARY_PATH
```

:::

Install the packages for your distribution according to the
[prerequisites](/openssl/ossl-legacy/Installation/prerequisites). On
Rocky Linux 8 the opensc and p11-kit package are necessary for all
scenarios.

```bash
sudo yum install opensc p11-kit
```

Depending on the use case additional packages need to be installed.
<Tabs groupId="scenario">
<TabItem value="ossl" label="OpenSSL" default>
To use the p11-kit in combination with OpenSSL additionally install
the openssl-pkcs11 package.

```bash
sudo yum install openssl-pkcs11
```

Ensure that the correct version of OpenSSL from your OS is used and
not the one included in the Primus provider package with the following
command:

```bash
openssl version
```
```text
OpenSSL 1.1.1k  FIPS 25 Mar 2021
```
</TabItem>
<TabItem value="apache" label="Apache/httpd">
The httpd webserver requires the **P11-Kit and OpenSSL modules and configuration** with Primus 
integrated plus following modules:
```bash
sudo yum install httpd mod_ssl
```

Ensure that the Apache/httpd from your OS is used and not the
one included in the Primus provider package with the following
command:

```bash
httpd -v
```
```text
Server version: Apache/2.4.37 (rocky)
Server built:   Aug 17 2023 23:57:25
```
</TabItem>
<TabItem value="nginx" label="Nginx">
Nginx (Engine X) is a popular, powerful and high-performance
open-source HTTP web server and reverse proxy server with a scalable
event-drive (asynchronous) architecture. It can also be used as a load
balancer, mail proxy, and HTTP cache due to its speed, stability,
feature-rich set, easy configuration, and low resource utilization.

Because Nginx also uses the OpenSSL for cryptographic operations,
support for PKCS #11 must go through the openssl-pkcs11 engine. Nginx
currently supports only loading private keys from an HSM, and a
certificate must be provided separately as a regular file.

The Nginx webserver requires above P11-Kit and OpenSSL modules and
configuration with Primus integrated plus following modules
```bash
sudo yum install nginx
```

</TabItem>
</Tabs>
