---
sidebar_position: 2
title: Configuring Microsoft SignTool & Security HSM
sidebar_label: 3. Configuration
description: Configuring Microsoft SignTool with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Installation

This procedure provides a straightforward integration process. Please take notice that there may be other ways to achieve interoperability.

This guide assumes that you are familiar with the Primus HSM and the Microsoft Windows SDK signing tool and does not cover every step of the hardware and software setup process.

## Preparing Signing Key Request File

Prepare the input file for the certreq.exe tool (sample `request.inf` file):
```js
[Version]
Signature= "$Windows NT$"
[NewRequest]
ValidityPeriod = Years
ValidityPeriodUnits = 1
Subject = "CN=signtooldemo.hsmdemo.test"
FriendlyName = "Signtool Demo Key"
MachineKeySet = True
RequestType = Cert
Exportable = FALSE
HashAlgorithm = SHA256
KeyAlgorithm = RSA
KeyLength = 4096
KeyUsage = 0xf0
ProviderName = "Securosys Primus HSM Key Storage Provider"
KeyContainer = "signtooldemokey"
;[EnhancedKeyUsageExtension]
;OID=1.3.6.1.5.5.7.3.1
;[RequestAttributes]
;CertificateTemplateName = <yourADCSTemplate>
```

Update the following values:
- `Subject`, replace with your real data
- `ValidityPeriod`, ValidityPeriodUnits – replace according to your requirements
- `KeyContainer`, define your key container ID or remove the line for default values
- Adapt the algorithms according to your requirements
- Define additional extensions and CertificateTemplateName if required
- `RequestType`, adapt e.g. if you want to use a publicly signed certificate (PKCS10)

## Generating Signing Key and Self-Signed Certificate

Use the below command to generate a new signing key and self-signed certificate according to the previously prepared sample `request.inf` file.
```bash
certreq -new request.inf SigningCertificate.cer

Installed Certificate:
  Serial Number: 1aaa8bbcd7c98cb74c9dff9f9e40cf10
  Subject: CN=signtooldemo.hsmdemo.test
  NotBefore: 14/02/2024 16:21
  NotAfter: 14/02/2025 16:31
  Thumbprint: cb1a55f6ab8ccacedab3fcd9de48bd69be16b88d
  Friendly Name: Signtool Demo Key
  Securosys Primus HSM Key Storage Provider
  signtooldemokey


CertReq: Certificate Created and Installed
```

## Generating Signing Key and Public Signed Certificate
When a publicly signed certificate is required, adapt the `RequestType` to **“PKCS10”** and generate the Certificate Signing Request (CSR) to be signed by a public Certification Authority (CA):

```bash
certreq -new request.inf SigningCertificate.csr
```

To make the public signed certificate available for use, execute the following command:
```bash
certreq.exe –accept -machine <certificateFilename.cer>
```
Where `<certificateFilename.cer>` is the binary signed certificate received from the CA/SubCA.

## Validate the Certificate

Verify your signing certificate:
```bash
certutil -store -v my "<Certificate_serial_number_or_thumbprint>"
```

Example of the self-signed certificate:
```bash
certutil -store -v my "1aaa8bbcd7c98cb74c9dff9f9e40cf10"

my "Personal"
================ Certificate 0 ================
X509 Certificate:
Version: 3
Serial Number: 1aaa8bbcd7c98cb74c9dff9f9e40cf10
Signature Algorithm:
    Algorithm ObjectId: 1.2.840.113549.1.1.11 sha256RSA
    Algorithm Parameters:
    05 00
Issuer:
    CN=signtooldemo.hsmdemo.test
  Name Hash(sha1): e0f35912a4d53f78e8443f219d77c57b79c65759
  Name Hash(md5): e066872e7bef87abba6bb1f1a4512d94

 NotBefore: 14/02/2024 16:21
 NotAfter: 14/02/2025 16:31

Subject:
    CN=signtooldemo.hsmdemo.test
  Name Hash(sha1): e0f35912a4d53f78e8443f219d77c57b79c65759
  Name Hash(md5): e066872e7bef87abba6bb1f1a4512d94

Public Key Algorithm:
…
```

## Next steps

- [Tutorial - How to sign files with Microsoft SignTool?](../Tutorials/Signing-files)