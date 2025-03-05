---
sidebar_position: 2
title: Generating Certificate for Microsoft IIS
sidebar_label: 3. Generate Certificate
description: Generating and Installing Certificate for Microsoft Internet Information Services (IIS) & Securosys Hardware Security Modules.
keywords: [hsm clusters, primus hsm, microsoft, iis, securosys]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# IIS Generating and Installing Certificate

:::note
This guide assumes that you are familiar with the **Primus HSM**, the **Microsoft Internet Information Services** and certificates. For the sake of simplicity only the domain administrator role is used instead of the IIS management roles defined by Microsoft.
:::

This procedure provides a straightforward integration process, which has been tested. Please take notice that there may be other ways to achieve interoperability. 

## Generating Certificate
As the IIS 10 Manager does not yet support creation of certificates protected by CNG keys on the graphical user interface, these keys and certificates need to be created using the command line utility `certreq.exe`

There are different approaches to the cert generation, depending on the used *Certification Authority* (CA) (e.g. MS ADCS) and the validity of range of the certificates (internal or public). The page below differentiates between the following two scenario:
- using the internal available [Microsoft ADCS](/ms-pki-adcs/Tutorials/Standalone-Root-Ca/Setup-Overview) documentation (ADCS, requires template definitions)
- using a public certificate, signed by an official public CA.

:::tip
This page will go over all the necessary requirements of creating a certificate using **MS ADCS** or a **public certificate** signed by a trusted public CA.
:::

### Prepare Certificate Signing Request File
To generate a request for an SSL certificate linked to the appropriate private key stored on the Primus HSM, create and prepare the input file for the `certreq.exe` tool.

The sample `.inf` request file below is intended to issue a certificate for a secure web server, serving `https://test.demo-iss.hsmdemo.test`.

<details>
    <summary>Click to reveal sample config</summary>
```
[Version]
Signature= "$Windows NT$"

[NewRequest]
Subject = "CN=iis-hsm-demo.securosys,O=hsmdemo,OU=IT,L=Zurich,S=ZH,C=CH"
FriendlyName = "IIS Demo Certificate on Primus HSM"
MachineKeySet = True
Exportable = FALSE
HashAlgorithm = SHA256
KeyAlgorithm = RSA
KeyLength = 4096
KeyUsage = 0xa0
ProviderName = "Securosys Primus HSM Key Storage Provider"
KeyContainer = "iisdemokey"
;RequestType = PKCS10
;ValidityPeriod = Years
;ValidityPeriodUnits = 1

[EnhancedKeyUsageExtension]
OID=1.3.6.1.5.5.7.3.1

[RequestAttributes]
CertificateTemplateName = WebServerHSM3
```
</details>
Update the following values:
- **Subject**, replace with your real data
- **KeyContainer** - define your key container ID (visible key name on the HSM) or remove the line for default values
- Adapt the algorithms according to your requirements
- Define additional extensions if required

<Tabs>
<TabItem value="ui" label="MS ADCS" default>
- Adapt the `iis-certrequest.inf` file
  - comment-out or delete the line `;RequestType = PKCS10`
  - add the section `[RequestAttributes]` and the line `CertificateTemplateName = WebServerHSM3` (you can create your own template, for more information, please follow [Modifying the Certificate Request Templates](/ms-pki-adcs/Tutorials/Deploying-cert-template#modifying-the-certificate-request-templates) page)
- Generate the Certificate Signing Request (CSR) to be signed by the MS ADCS SubCA
```powershell
certreq.exe –new iis-certrequest.inf iis-certrequest.req
CertReq: Request Created
```
- Submit the certificate signing request to the MS SubCA with the following command

```powershell
certreq.exe -submit -attrib “CertificateTemplate:WebServerHSM3” iis-certrequest.req
Active Directory Enrollment Policy
 {5DA464A9-C243-4515-BA8D-7137CFEC7B17}
 ldap:
RequestId: 42
```
</TabItem>
<TabItem value="pub" label="Public CA">
- Adapt the `iis-certrequest.inf` file
  - uncomment/add the line `RequestType = PKCS10`
  - comment-out/delete the line with `CertificateTemplateName = WebServerHSM3`
- Generate the Certificate Signing Request (CSR) to be signed by a public certification authority:
```powershell
certreq.exe -new iis-certrequest.inf iis-certrequest.csr
```
- Sign the resulting CSR file using your public certification authority of choice
</TabItem>
</Tabs>

## Installing CA Signed Web Server Certificate
Once you have the singed certificate, install it on machine level context by running
```powershell
certreq.exe –accept -machine <certificateFilename.cer>
```
This makes the new certificate visible to all applications on the server and can therefore be used by multiple users/applications running on it.