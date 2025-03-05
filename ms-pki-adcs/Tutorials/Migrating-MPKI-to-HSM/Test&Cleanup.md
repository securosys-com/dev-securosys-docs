---
sidebar_position: 5
title: Testing Microsoft AD CS after a key migration
sidebar_label: Testing
description: Reconfiguring Microsoft AD CS Registry for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Testing and Cleanup

Start the Microsoft AD CS services again and verify that everything works correctly.
- Start the AD CS service again:
```bash
net start certsvc

The Active Directory Certificate Services service is starting.
The Active Directory Certificate Services service was started successfully.
```
- Run the following command on AD CS to verify that the CA service is up and ready to receive requests:
```bash
certutil -ping

Connecting to WIN-E99A37QE5JP\myDemoCA ...
Server "myDemoCA" ICertRequest2 interface is alive (32ms)
CertUtil: -ping command completed successfully.
```
- Run the command `certutil -store my <Your CA Common Name>` on the CA to verify that the CA is configured for the correct key and provider:
```bash
certutil -store my myDemoCA

my "Personal"
================ Certificate 43 ================
Serial Number: 3aa837d2cf1289b241b9ee87a5474b4f
Issuer: CN=myDemoCA
 NotBefore: 10.08.2021 14:14
 NotAfter: 10.08.2026 14:22
Subject: CN=myDemoCA
CA Version: V0.0
Signature matches Public Key
Root Certificate: Subject matches Issuer
Cert Hash(sha1): 19f647a3f4e60eabc126d73ef1ef6e9f84224997
  Key Container = myDemoCA
  Provider = Securosys Primus HSM Key Storage Provider
Signature test passed
CertUtil: -store command completed successfully.
```
- Request and issue a certificate for a user or computer and inspect the resulting certificate details to ver-ify that the certificate shows the correct signature algorithm and signature hash algorithm.
- Verify that the certificate revocation list can be published and has the correct signature algorithm and signature hash algorithm. Publish the certificate revocation list (CRL) and check the correct signature algorithm by running the following commands on the CA. Please replace \<Your CA Common Name> with your CA Common Name.
```bash
certutil –crl

CertUtil: -CRL command completed successfully.

certutil C:\Windows\System32\CertSrv\CertEnroll\myDemoCA.crl
```
If everything works correctly, the migration is completed. Cleanup all temporary files created during these migration steps (backup, export, registry files). 

:::note
Ensure that you have removed (wiped) all the PKCS#12 backup or export files containing the CA key!
:::
