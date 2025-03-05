---
sidebar_position: 4
title: Reconfiguring Microsoft AD CS Registry
sidebar_label: Reconfigure registry
description: Reconfiguring Microsoft AD CS Registry for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Reconfiguring the Microsoft AD CS Registry

Depending on the “Source” key store, the procedure differs slightly (CNG 2.8.4.1, CSP 2.8.4.2).

## Migrating from Microsoft CNG/KSP

This step applies only in case you migrated from a CNG-based key store (e.g. MS Software KSP).
- Open a Command Shell with administrator rights
- Edit the previously exported registry file **`myCACSPregistry.reg`** by replacing the Provider value with the Primus HSM CNG/KSP provider name (default **`Securosys Primus HSM Key Storage Provider`**; can be shown with the command **`certutil -csplist`**):
```bash
Windows Registry Editor Version 5.00

[HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\services\CertSvc\Configuration\myDemoCA\CSP]
"ProviderType"=dword:00000000
"Provider"="Securosys Primus HSM Key Storage Provider"
"HashAlgorithm"=dword:ffffffff
"CNGPublicKeyAlgorithm"="RSA"
"CNGHashAlgorithm"="SHA256"
"MachineKeyset"=dword:00000001
```
- Save the file and run it to import the registry values.
- Repeat the same procedure with the other exported registry file **`myCAEncryptionCSPregistry.reg`**.


## Migrating from CSP

This step applies only in case you migrate from a CSP-based key store (e.g. Microsoft Strong Cryptographic Provider). 
- Open a Command Shell with administrator rights
- Edit the previously exported registry file **`myCACSPregistry.reg`** by replacing the Provider value with the Primus HSM CNG/KSP provider name and adapt/add all the other values marked in red:
```bash
Windows Registry Editor Version 5.00

HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\services\CertSvc\Configuration\myDemoCA\CSP]
"ProviderType"=dword:00000000
"Provider"="Securosys Primus HSM Key Storage Provider"
"CNGPublicKeyAlgorithm"="RSA"
"CNGHashAlgorithm"="SHA1"
"MachineKeyset"=dword:00000001
```
- Execute the following command to evaluate the previously used CSP hash algorithm:
```bash
certutil -v -getreg ca\csp\HashAlgorithm

HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\CertSvc\Configuration\myDemoCA\csp:

  HashAlgorithm REG_DWORD = 8004 (32772)
    CALG_SHA1
    Algorithm Class: 0x8000(4) ALG_CLASS_HASH
    Algorithm Type: 0x0(0) ALG_TYPE_ANY
    Algorithm Sub-id: 0x4(4) ALG_SID_SHA1
CertUtil: -getreg command completed successfully.
```
- And adapt the above file **`myCACSPregistry.reg`** on the line with the CNGHashAlgorithm inserting the proper hash algorithm for CNG (in this case “SHA1”).
- Save the file and run it to import the registry values.
- Edit the previously exported registry file **`myCAEncryptionCSPregistry.reg`** by replacing the Provider value with the Primus HSM CNG/KSP provider name and adapt/add all the other values marked in red:
```bash
Windows Registry Editor Version 5.00

[HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\services\CertSvc\Configuration\myDemoCA\EncryptionCSP]
"ProviderType"=dword:00000000
"Provider"="Securosys Primus HSM Key Storage Provider"
"CNGPublicKeyAlgorithm"="RSA"
"CNGEncryptionAlgorithm"="3DES"
"MachineKeyset"=dword:00000001
"SymmetricKeySize"=dword:000000a8
```
- Execute the following command to evaluate the previously used CSP encryption algorithm:
```bash
certutil -v -getreg ca\encryptioncsp\EncryptionAlgorithm

HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\CertSvc\Configuration\myDemoCA\encryptioncsp:

  EncryptionAlgorithm REG_DWORD = 6603 (26115)
    CALG_3DES
    Algorithm Class: 0x6000(3) ALG_CLASS_DATA_ENCRYPT
    Algorithm Type: 0x600(3) ALG_TYPE_BLOCK
    Algorithm Sub-id: 0x3(3) ALG_SID_3DES
CertUtil: -getreg command completed successfully.
```
- And adapt the above file **`myCAEncryptionCSPregistry.reg`** on the line with **`CNGEncryptionAlgorithm`**, inserting the proper encryption algorithm for CNG (in this case *3DES*), and the proper value for SymmetricKeySize (hexadecimal value, in this case is **`000000a8 = 168`**).
- Save the file and run it to import the registry values.
In case you want to change the hash algorithm from **`SHA1`** to **`SHA256`**, consult the Microsoft documentation mentioned at [Migrating Microsoft PKI](./Migrating-MPKI-toHSM.md)
