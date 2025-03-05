---
sidebar_position: 0
title: Certificate Revocation List Troubleshooting
sidebar_label: Certificate Revocation List
description: Migrating a not-exportable private key for Microsoft AD CS Server instance and Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Certificate Revocation List (CRL) Chain

Be default, a certificate is **invalid** if the CRL (Delta-CRL) verification fails. This can happen due to:
- CRL not retrievable (e.g. wrong configuration or CRL server not reachable)
- CRL or Delta-CRL not renewed/updated within the defined time frame

![](../../img/MS-ADCS-cannot-verify.png)

In case you get the above message, check if you can retrieve the certificate revocation lists from the known URL or the certificate itself with:
```sh
certutil -URL C:\issdemokey.cer #(filename of the IIS certificate) 
```
or
```sh
certutil -URL http://crlserver.hsmdemo.test/folder/caname.crl
```

![](../../img/URL-retrieval-tool.png)
