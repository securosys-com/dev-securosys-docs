---
sidebar_position: 6
title: KeytoolX & JarsignerX with Primus Tools
sidebar_label: KeytoolX & JarsignerX
description: KeytoolX & JarsignerX with Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# KeytoolX & JarsignerX

## KeytoolX

`KeytoolX` is an adapted utility within the Primus Tools. It's adapted from Sun Oracles keytool utility to work with HSMs. Has many options, list other commands with command `KeytoolX`:

:::note
`KeytoolX` requires `kt.jar` in the same folder as `primus-tools.jar`.
:::

```bash
java -jar primus-tools.jar KeytoolX
Key and Certificate Management Tool

Commands:
 -certreq            Generates a certificate request
 -changealias        Changes an entry's alias
 -delete             Deletes an entry
 -exportcert         Exports certificate
 -genkeypair         Generates a key pair
 -genseckey          Generates a secret key
 -gencert            Generates certificate from a certificate request
 -importcert         Imports a certificate or a certificate chain
 -importpass         Imports a password
 -importkeystore     Imports one or all entries from another keystore
 -keypasswd          Changes the key password of an entry
 -list               Lists entries in a keystore
 -printcert          Prints the content of a certificate
 -printcertreq       Prints the content of a certificate request
 -printcrl           Prints the content of a CRL file
 -storepasswd        Changes the store password of a keystore
 -keyclone           Clones a key entry
 -selfcert           Generates a self-signed certificate
 -gencrl             Generates CRL
 -identitydb         Imports entries from a JDK 1.1.x-style identity database
```
Sample command with KeytoolX: 
```bash
java -jar primus-tools.jar KeytoolX <HSM connection and credentials> -certreq
```
 
:::note
The KeytoolX commands will require the `<HSM connection and credentials>` parameter (see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider.md)) to specify the connection and credentials of the HSM. For more information about the keytool utility and its command and parameter description visit [Sun Oracle Keytool Utility](https://docs.oracle.com/en/java/javase/11/tools/keytool.html).
:::

## JarsignerX

JarsignerX is a utility within the Primus Tools used to sign and verify Java Archive (JAR) files. It's adapted from Sun Oracles jarsigner utility to work with HSMs:

:::note
`JarsignerX` requires `kt.jar` in the same folder as `primus-tools.jar`.
:::

Sample command:
```bash
java -jar primus-tools.jar JarsignerX <HSM connection and credentials> [-logging (default false)] 
```
:::note
The JarsignerX commands will require the `<HSM connection and credentials>` parameter (see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider.md)) to specify the connection and credentials of the HSM. For more information about the utility and its command and parameter description visit [Sun Oracle Jarsigner Utility](https://docs.oracle.com/en/java/javase/11/tools/jarsigner.html#GUID-925E7A1B-B3F3-44D2-8B49-0B3FA2C54864__CCHIFIAD).
:::
