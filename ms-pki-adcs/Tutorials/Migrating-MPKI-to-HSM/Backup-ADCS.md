---
sidebar_position: 1
title: Backing up Microsoft AD CS database
sidebar_label: Backup
description: Backing up Microsoft AD CS database for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Backing up Microsoft AD CS database
It is recommended to create a backup of the AD CS database, registry settings and the AD CS certificate including the private key (if exportable).
- Open a Command Shell with administrator rights
- Check that AD CS is alive (and note the CA name)

```bash
certutil -ping

Connecting to WIN-E99A37QE5JP\myDemoCA ...
Server "myDemoCA" ICertRequest2 interface is alive (15ms)
CertUtil: -ping command completed successfully.
```
- Backup AD CS database, logs and private key on command line using 
```bash
certutil -backup <myBackupFolder>.[KeepLog] 

Enter a secure password to protect the PKCS#12 file.
```
```bash
certutil -backup myDemoCA

Enter new password:
Confirm new password:
Backed up keys and certificates for WIN-E99A37QE5JP\myDemoCA to myDemoCA\myDemoCA.p12.
Full database backup for WIN-E99A37QE5JP\myDemoCA.
Backing up Database files: 100%
Backing up Log files: 100%
Truncating Logs: 100%
Backed up database to myDemoCA.
Database logs successfully truncated.
CertUtil: -backup command completed successfully.
```
- Backup the AD CS registry settings:
```bash
reg export HKLM\SYSTEM\CurrentControlSet\services\CertSvc myDemoCA\myCAregistry.reg

The operation completed successfully.
```
- Get configuration part of the AD CS CSP configuration to modify later:
```bash
reg export HKLM\SYSTEM\CurrentControlSet\services\CertSvc\Configuration\myDemoCA\CSP myDemoCA\myCACSPregistry.reg

The operation completed successfully.
```
- Get configuration part of the AD CS EncryptionCSP to modify later:
```bash
reg export HKLM\SYSTEM\CurrentControlSet\services\CertSvc\Configuration\myDemoCA\EncryptionCSP myDemoCA\myCAEncryptionCSPregistry.reg

The operation completed successfully.
```
