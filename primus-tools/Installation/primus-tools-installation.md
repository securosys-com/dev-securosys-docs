---
sidebar_position: 1
title: Installing Primus Tools
sidebar_label: Installation
description: Installing Securosys Primus Tools for Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Installing Primus Tools

To install the **Primus Tools**, please follow the below steps:

1. [Download](/primus-tools/downloads.md) the latest Primus Tools.
2. Unzip the archive to your work folder, containing following files:
    - `primus-tools.jar` 	Java-based Primus Tool Set,
    - `primusX.jar` 	    Primus JCE Provider,
    - `kt.jar`            Primus JCE Provider's adapter to Java's keytool (required for jarsignerX and keytoolX),


An already installed JCE provider can be used instead (adapt Java CLASSPATH), the JCE provider must be included in the `Primus-tools` directory with `primus-tools.ja`r and `kt.jar`.

:::info features not supported
JCE provider version **v2.3.6 and older** do not support:
- `AzureByokExport` and
- `AWSKMSByokExport` command structures
JCE provider versions below **v2.4.4** do not support:
- PQC supported Algorithms, see [Tutorials - Object Management](/primus-tools/Tutorials/command-details/object-management#create-key).
:::

Continue to **[connect the HSM](./Provider.md)**.