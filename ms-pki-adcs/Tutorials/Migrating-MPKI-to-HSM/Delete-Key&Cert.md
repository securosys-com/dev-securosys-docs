---
sidebar_position: 2
title: Deletion of Microsoft AD CS keys and certificates
sidebar_label: Delete Key/Certificate
description: Deleting the Key and Certificate  for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Deleting the Key and Certificate

Locate and remove the key(s) and certificate(s) from the old key storage provider and certificate store.

Please note that there may be several keys and certificates involved in case the CA certificate was already renewed.
- Locate the old key(s) and certificate(s)
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
  Unique container name: f7717552e9ac0bf2063dc0c18c29c08c_92adcd87-1855-4c11-89be-680e114a784c
  Provider = Microsoft Software Key Storage Provider
Private key is NOT plain text exportable
Signature test passed
CertUtil: -store command completed successfully.
```
- Stop the CA service
```bash
net stop certsvc

The Active Directory Certificate Services service is stopping.
The Active Directory Certificate Services service was stopped successfully.
```
- Delete the old certificate(s) from the personal and CA store. Make sure that you reference the right store and use the certificate hash:
```bash
certutil -delstore my 19f647a3f4e60eabc126d73ef1ef6e9f84224997

my "Personal"
Deleting Certificate 43: CN=myDemoCA:19f647a3f4e60eabc126d73ef1ef6e9f84224997
CertUtil: -delstore command completed successfully.
```
```bash
certutil -delstore CA 19f647a3f4e60eabc126d73ef1ef6e9f84224997
CA "Intermediate Certification Authorities"
Deleting Certificate 54: CN=myDemoCA:19f647a3f4e60eabc126d73ef1ef6e9f84224997
CertUtil: -delstore command completed successfully.
```
- Delete the old private key(s). Make sure that you reference the right CSP/KSP store:
```bash
certutil -csp "Microsoft Software Key Storage Provider" -delkey myDemoCA

  myDemoCA
CertUtil: -delkey command completed successfully.
```
