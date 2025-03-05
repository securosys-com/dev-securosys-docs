---
sidebar_position: 3
title: Configure OpenSSL v1.1 for PKCS#11
sidebar_label: Configuration
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Configuration

## p11-kit configuration
Each configured PKCS#11 module has its own configuration
file. Depending on the OS, these configuration files may reside at the
following locations:

- the p11-kit installation folder (modules folder)
- /etc/pkcs11/ folder (modules folder)
- .pkcs11/modules folder.

Each user can optionally provide additional configuration or override
the system configuration. Note that user configuration files are not
loaded from the home directory if running inside a setuid or setgid
program


Create the file primus.module for your OS in

| RHEL                                                         | Ubuntu/Debian                 |
|--------------------------------------------------------------|-------------------------------|
| _/etc/pkcs11/modules/_ or<br />_/usr/share/p11-kit/modules/_ | _/usr/share/p11-kit/modules/_ |

containing the following lines:

```text
# This file describes how to load the primus pkcs11 provider module
# For details see p11-kit and pkcs11.conf 
# and https://p11-glue.github.io/p11-glue/p11-kit/manual/
# This is a relative path, which means it will be loaded from
# the p11-kit default path which is usually $(libdir)/pkcs11.
# Doing it this way allows for packagers to package primus for
# 32-bit and 64-bit and make them parallel installable
# highlight-info-start
module: libprimusP11.so
managed: true
priority: 2
# highlight-info-end
#critical: true
# A comma and/or space separated list of names of programs that this module should only
# be loaded in. The module will not be loaded for other programs using p11-kit. The base
# name of the process executable should be used here, for example seahorse, ssh.
# do not use enable-in and disable-in simultaneously
# highlight-next-line-info
enable-in: p11-kit, openssl, httpd, nginx
#disable-in:
#remote:
#log-calls: true
```

Add a symbolic link for libprimusP11.so module in
| RHEL                                      | Ubuntu/Debian                                                              |
|-------------------------------------------|----------------------------------------------------------------------------|
| _/usr/lib64/pkcs11/_ <br /> _/usr/lib64/_ | _/usr/lib/x86\_64-linux-gnu/pkcs11/_ <br />  _/usr/lib/x86\_64-linux-gnu/_ |


e.g. for RHEL 8:
```bash
sudo ln -s /usr/local/primus/lib/libprimusP11.so /usr/lib64/pkcs11/libprimusP11.so
sudo ln -s /usr/local/primus/lib/libprimusP11.so /usr/lib64/libprimusP11.so
```

Use the following command to check that Primus HSM module is registered:
```bash
p11-kit list-modules
```
```text
// highlight-start
primus: libprimusP11.so
    library-description: PKCS#11 Library
    library-manufacturer: Securosys SA
    library-version: 2.1
    token: DEMO-TEST
        manufacturer: Securosys SA
        model: Primus HSM
        serial-number: a1973d4952c5addd
        hardware-version: 2.11
        firmware-version: 2.1
        flags:
               rng
               login-required
               user-pin-initialized
               restore-key-not-needed
               clock-on-token
               token-initialized
// highlight-end
p11-kit-trust: p11-kit-trust.so
    library-description: PKCS#11 Kit Trust Module
    library-manufacturer: PKCS#11 Kit
    library-version: 0.23
...
opensc: opensc-pkcs11.so
    library-description: OpenSC smartcard framework
    library-manufacturer: OpenSC Project
    library-version: 0.20
...
```

## Application configuration

<Tabs groupId="scenario">
<TabItem value="ossl" label="OpenSSL" default>

To use another engine, OpenSSL requires engine settings in the
openssl.cnf file. Some OpenSSL commands allow specifying a dedicated
configuration file (-config myssl.cnf ) and some do not.  Setting the
environment variable OPENSSL_CONF is a workaround, but be sometimes
the default openssl.cnf file contains entries that are needed by some
commands (e.g. openssl req).

You may add the engine entries to your default OpenSSL config file or
add other requirements for your OpenSSL command into the config file.
We suggest that you create a separate config file for interactions
with the HSM in order to prevent conflicts with previous settings or
defaults.

Locate were OpenSSL and it's default configuration file is
installed
```bash
openssl version -d
```
```text
OPENSSLDIR: "/etc/pki/tls"
```

Create a new configuration file /etc/pki/tls/primusopenssl.cnf with
the engine section and other necessary controls:

```ini
openssl_conf = openssl_init

[openssl_init]
engines = engine_section
[engine_section]
pkcs11 = pkcs11_section
[pkcs11_section]
engine_id = pkcs11
# dynamic_path is not required if you have installed
# the appropriate pkcs11 engines to your openssl directory
# dynamic_path = /usr/local/primus/lib/libprimusP11.so
MODULE_PATH = /usr/local/primus/lib/libprimusP11.so
# Insert your PKCS11 password, otherwise interactively or command line option
// highlight-next-line-info
PIN = <PKCS#11 PIN>
# it is not recommended to use "debug" for production use
# INIT_ARGS = connector=http://127.0.0.1:12345 debug
init = 0 
```

Include above file in /etc/pki/tls/openssl.cnf, the main OpenSSL configuration file:
```ini
# Note that you can include other files from the main configuration
# file using the .include directive.
.include /etc/pki/tls/primusopenssl.cnf
```

Alternatively, you may also create an alias to use a dedicated config file:
```bash
alias primusssl='OPENSSL_CONF=/etc/pki/tls/myprimusopenssl.cnf openssl'
```

The key can be referenced by URI by placing it after the -key option. \
E.g: Generating a CSR using the private key "myeckey" on partition "DEMO-TEST" in slot 12:
```bash 
openssl req -engine pkcs11 -new -key "pkcs11:token=DEMO-TEST;object=myeckey;type=private;pin-value=MYPIN" \
    -keyform engine -out ec.hsm.csr -sha384 -verify
```


</TabItem>
<TabItem value="apache" label="Apache/httpd">
:::note

The httpd mod_ssl module leverages OpenSSL for TLS
connections. OpenSSL needs to be configured.

:::

Add at the end of the main httpd configuration file
/etc/httpd/conf/httpd.conf the following lines:
```xml
<IfModule ssl_module>
 SSLRandomSeed startup builtin
 SSLRandomSeed connect builtin
</IfModule>
```

Adapt the mod_ssl configuration file /etc/httpd/conf.d/ssl.conf

```
SSLPassPhraseDialog builtin
```

And reference the used Certificate and Private Key within the
VirtualHost section using the object (=label) reference:

```
SSLCertificateFile "pkcs11:token=<partition-name>;object=myrsakey;type=cert"
SSLCertificateKeyFile "pkcs11: token=<partition-name>;object=myrsakey;type=private"
```

</TabItem>
<TabItem value="nginx" label="Nginx">
:::note

Nginx uses OpenSSL for cryptographic operations. OpenSSL needs to be
configured.

:::


Nginx currently supports only loading private keys from an HSM, and a certificate must be 
provided separately as a regular file. Modify the nginx configuration file /etc/nginx/nginx.conf
by activating TLS and referencing the used Certificate as regular file and Private Key within the 
server section using the object (=label) reference:

```
ssl_certificate "/path/to/cert.pem"
ssl_certificate_key "engine:pkcs11:pkcs11:token=<partition-name>;pin-value=<PKCS#11 PIN>;object=myrsakey;type=private
```

</TabItem>
</Tabs>

## HMS Key Reference

HSM objects are referenced in general according RFC7512 URI scheme.
Currently only the following URI specifier and forms are supported:

| Attribute                      | Description                                                                                        |
|--------------------------------|----------------------------------------------------------------------------------------------------|
| `token=<token label>`          | Token Label (partition name)                                                                       |
| `serial=<serial number>`       | Serial Number of the partition                                                                     |
| `object=<object label>`        | Key Label                                                                                          |
| `id=<key id>`                  | Key ID, note that pkcs11-tool writes the key id in hexadecimal notation to the HSM (e.g. `%10%01`) |
| `type=<cert\|public\|private>` | Key type                                                                                           |
| `pin-value=<PKCS#11 PIN>`      | to pass the PKCS11 PIN via command line                                                            |

