---
sidebar_position: 10
title: Signing with Primus Tools
sidebar_label: Signing
description: Signing with Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Signing

Use the signing commands for signing as well as verifying signatures.

## Sign

To perform a sign test execute the following command:
```bash
java -jar primus-tools.jar Sign <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>] -algorithm <algorithm> [-derivation <derivation>] [-message <message>] [-authorization <authorization>] [-verifylocal]
```

## Jar Signature Check

To check the code signature of the Primus JCE provider execute the following command:
```bash
java -jar primus-tools.jar JarSignatureCheck
```

The output of a signed JCE provider will be shown(e.g.):
```bash
Primus JCE provider: SecurosysPrimusXSeries version 2.41020240122: verified
```
