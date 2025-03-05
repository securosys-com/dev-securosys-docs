---
sidebar_position: 0
title: Using Certutil with an HSM
sidebar_label: Certutil
description: Securing Microsoft Certutil with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

Using `certutil` it is possible to manage existing keys, certificates, signing, encryption, and HSM-backed operations securely. This utility helps integrate HSM-based security into Windows environments, ensuring cryptographic integrity and compliance.

Below we provide certain use cases for the certutil. For more information on the commands and their parameters please see [Microsoft Certutil Documentation](https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/certutil).

## List Keys 

Retrieves all keys stored inside the HSM.

```
certutil -csp "Securosys Primus HSM Key Storage Provider" -key -user
```

## Delete a Key 

Securely removes a cryptographic key from the HSM.

```
certutil -delkey -csp "Securosys Primus HSM Key Storage Provider" <KeyName>
```

## Restore a key 

Restore a exported **`.pfx`** file to restore it under the original private key name by running the following command:
```
certutil -restorekey <myDemoCAPrimus>.p12
```
- replace the example key name with your own.

## Sign Data Using an HSM Key

Digitally signs data with a private key stored inside the Securosys HSM.

```
certutil -csp "Securosys Primus HSM Key Storage Provider" -sign -user <InputFile> <SignatureFile> "<CertificateName>"
```

## Import a Certificate into the HSM

To import a certificate into the Securosys HSM, execute the command:

```
certutil -f -v [-p "<passphrase>"] -csp "Securosys Primus HSM Key Storage Provider" -importpfx <myBackupFolder>\<myCAPrivateKeyBackup>.p12 [FriendlyName="<myCAName>",KeyFriendlyName="<myCAName>",NoExport|ExportEncrypted]
```
- replace the example parameters and the brackets (`<>`) with your own.

## Export a Certificate and Private Key from the HSM

Use the following command to export the certificate and its private key:
```
certutil -exportpfx <myStore> <myDemoCA> <myDemoCAPrimus>.p12
```

- replace the example parameters and with your own.

## Testing CA service

Run the following command to verify the CA service is up and ready to receive requests:
```
certutil -ping

Connecting to WIN\<myDemoCA> ...
Server "<myDemoCA>" ICertRequest2 interface is alive (32ms)
```