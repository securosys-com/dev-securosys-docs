---
sidebar_position: 0
title: Connecting to Column Encrypted Database
sidebar_label: Connect Encrypted Database
description: Connecting to Column Encrypted Database for Microsoft SQL Always Encrypted with Securosys Hardware Security Modules (HSMs)
keywords: [cloud hsm, hsm key management, hsm cloud]
---

# Connecting to Column Encrypted Database

Additional database connection parameters are required to show encrypted columns in plaintext.

The parameter is entered at the `Connect to Database Engine` logon screen. Select the required server name and click on `Options>>`. Select the `Additional Connection Parameters` tab then add the connection string `Column Encryption Setting = enabled` and then click `Connect`.

![](../img/connect-to-server.png)

The first time you query the database you may get the `Parameterization for Always Encrypted` prompt. Click on `Enable` to proceed.

![](../img/parameterization-for-always-encrypted.png)

Running queries on the table will now show the original values decrypted by the CEK.