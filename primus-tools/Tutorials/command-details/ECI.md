---
sidebar_position: 8
title: Elliptic Curve (ECIES) with Primus Tools
sidebar_label: Elliptic Curve (ECIES)
description: Elliptic Curve Integrated Encryption Scheme (ECIES) with Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Elliptic Curve Integrated Encryption Scheme

Elliptic Curve Integrated Encryption Scheme, or ECIES, is a hybrid encryption system proposed by Victor Shoup in 2001.

Commands will require an established `<HSM connection and credentials>` parameter to be able to execute properly. For further assistance on how to prepare your `<HSM connection and credentials>` parameter, see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider) section.

## ECC IES Chunking Decrypt

Elliptic Curve Integrated Encryption Scheme chunking file decryption:
```bash
 java -jar primus-tools.jar IesChunkingDecrypt  <HSM connection and credentials> /
 -keyname <keyname> [-keypassword <keypassword>] [-infile <input file>] [-outfile <output file>]
 ```

## ECC IES Chunking Encrypt

Elliptic Curve Integrated Encryption Scheme chunking file encryption:
```bash
java -jar primus-tools.jar IesChunkingEncrypt <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>] [-infile <input file>] [-outfile <output file>]
```

## IES Decryption

Elliptic Curve Integrated Encryption Scheme file decryption:
```bash
java -jar primus-tools.jar IesDecrypt <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>] [-infile <input file>] [-outfile <output file>]
```

## IES Encrypt

Elliptic Curve Integrated Encryption Scheme file encryption

```bash
java -jar primus-tools.jar IesEncrypt <HSM connection and credentials> / -keyname <keyname> [-keypassword <keypassword>] [-infile <input file>] [-outfile <output file>]
```
