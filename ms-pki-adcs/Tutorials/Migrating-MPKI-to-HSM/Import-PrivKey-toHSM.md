---
sidebar_position: 3
title: Importing a Microsoft AD CS key to the HSM
sidebar_label: Import a private key
description: Importing a Microsoft AD CS key to Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Importing a Private Key to HSM

To import the private key (from AD CS backup) into the Securosys HSM, perform the following steps:
- Open a Command Shell with administrator rights
- Import the private key by using **`certutil.exe`**. 

:::note
You will be asked for the passphrase to decrypt the PKCS#12 file (set during the backup procedure). Consult the command **`help certutil -importpfx -?`** for further options and modifiers, e.g. if the private key may be exported or not.
:::

```bash
certutil -f -v [-p "<passphrase>"] -csp "Securosys Primus HSM Key Storage Provider" -importpfx <myBackupFolder>\<myCAPrivateKeyBackup>.p12 [FriendlyName="<myCAName>",KeyFriendlyName="<myCAName>",NoExport|ExportEncrypted]
```

Example:
```bash
certutil -f -v -csp "Securosys Primus HSM Key Storage Provider" -importpfx myDemoCA\myDemoCA.p12

    CRYPT_IMPL_HARDWARE -- 1
      (CRYPT_IMPL_SOFTWARE -- 2)
      (CRYPT_IMPL_MIXED -- 3)
      (CRYPT_IMPL_UNKNOWN -- 4)
      (CRYPT_IMPL_REMOVABLE -- 8)
Enter PFX password:
Certificate "myDemoCA" added to store.

CertUtil: -importPFX command completed successfully.
```

- Re-export the resulting AD CS certificate and private key to a **`.pfx`** file:
```bash
certutil -exportpfx my myDemoCA myDemoCAPrimus.p12

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
  Key Container = myDemoCA-5727d8fe-7fce-4c02-9af0-7d0b4de67a2f
  Provider = Securosys Primus HSM Key Storage Provider
Private key is NOT plain text exportable
Signature test passed
Enter new password for output file myDemoCAPrimus.p12:
Enter new password:
Confirm new password:
CertUtil: -exportPFX command completed successfully.
```
- Remove the previously imported AD CS key:
```bash
certutil -delkey -csp "Securosys Primus HSM Key Storage Provider" myDemoCA-5727d8fe-7fce-4c02-9af0-7d0b4de67a2f

  myDemoCA-5727d8fe-7fce-4c02-9af0-7d0b4de67a2f
CertUtil: -delkey command completed successfully.
```
- Restore the exported **`.pfx`** file on AD CS to restore it under the original private key name by running the following command:
```bash
certutil -restorekey myDemoCAPrimus.p12

Enter PFX password:
Restored keys and certificates for WIN-E99A37QE5JP\myDemoCA from myDemoCAPrimus.p12.
CertUtil: -restoreKey command completed successfully.
The CertSvc service may need to be restarted for changes to take effect.
```

- Link the existing CA certificate with the private key in the new key storage provider by using the Microsoft command line tool:
```bash
certutil –f -repairstore -csp "Securosys Primus HSM Key Storage Provider" my <OriginalKeyName>|<Cert Hash>|<Serialnumber>

certutil -f -repairstore -csp "Securosys Primus HSM Key Storage Provider" my myDemoCA

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
Securosys Primus HSM Key Storage Provider: KeySpec=0
AES256+RSAES_OAEP(RSA:CNG) test skipped
Signature test passed
CertUtil: -repairstore command completed successfully.
```
