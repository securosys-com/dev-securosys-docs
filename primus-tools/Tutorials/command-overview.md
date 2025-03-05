---
sidebar_position: 0
title: Primus Tools Command Overview
sidebar_label: Command Overview
description: Overview of the commands for Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Primus Tools Commands

:::note
Usage of this tool requires previous installation of Java Runtime Environment (see [Prerequisites](/primus-tools/Installation/Prerequisites)).
:::

The `primus-tools.jar` file contains a set of different Java command line commands for the Primus HSM or CloudHSM as well as other utilities.

The general call structure is as follows:
```bash
java -jar primus-tools.jar <ToolName> <HSM connection and credentials> [further tool parameters] [-help]
```

Commands will require an established `<HSM connection and credentials>` parameter to be able to execute properly. To prepare your `<HSM connection and credentials>` parameter, go back to the [HSM Connection](/primus-tools/Installation/Provider) section.

## Commands Overview

The table below gives an overview of current Primus Tools commands.

Options and more information for a specific command can be listed by calling without parameters or with parameter `-help`, e.g.: 
```bash
java -jar primus-tools.jar CreateKey <HSM connection and credentials> -help
usage: CreateKey -host <host> [-port <port>] -user <user> [-password <password>] 
-keyname <keyname> [-keypassword <keypassword>] -type <type> [-size <size>] 
[-curve <curve>] [-flags <flags>] [-access <access>]
```

The detailed description of the commands is found under [Command Details](/primus-tools/Tutorials/command-details/credential-management).

## [Credential Management](/primus-tools/Tutorials/command-details/credential-management)
Used for credential management for the HSM and the connection to it

|Command|Description|
|---|---|
|[GetUserSecret](/primus-tools/Tutorials/command-details/credential-management#configuring-permanent-secret)| Get (optionally blinded) permanent user secret|
|[GenerateBlindingKeyFile](/primus-tools/Tutorials/command-details/credential-management#generate-blinding-key-file)| Generate a blinding key file|
|[BlindPassword](/primus-tools/Tutorials/command-details/credential-management#blind-password)| Blinding of passwords, setup passwords, user secrets|
|[Login](/primus-tools/Tutorials/command-details/credential-management#login)| Login test (to check credentials and connectivity)|


## [HSM Device Information](/primus-tools/Tutorials/command-details/HSM-information)
Used for acquiring HSM device information and logs

|Command|Description|
|---|---|
|[GetLog](/primus-tools/Tutorials/command-details/HSM-information#hsm-user-log)| Get the HSM user log|
|[GetDeviceInfo](/primus-tools/Tutorials/command-details/HSM-information)| Get device name, firmware version and used provider version|

## [Object Management](/primus-tools/Tutorials/command-details/object-management)

Commands used for managing HSM objects.

|Command|Description|
|---|---|
|[ListKeyStoreObject](/primus-tools/Tutorials/command-details/object-management#list-partition-objects)| List partition objects (type, size, flags, hash) for single aliases|
|[ListKeyStoreObjects](/primus-tools/Tutorials/command-details/object-management#list-partition-object-single-alias)| List partition objects (type, size, flags, hash)|
|[ListKeyEntry](/primus-tools/Tutorials/command-details/object-management#list-key-entry)| List key information|
|[ListKeyFlags](/primus-tools/Tutorials/command-details/object-management#list-key-flags)| List key flags|
|[CreateKey](/primus-tools/Tutorials/command-details/object-management#create-key)| Create key|
|[DeleteKey](/primus-tools/Tutorials/command-details/object-management#delete-key)| Delete key|
|[GetKeyFlag](/primus-tools/Tutorials/command-details/object-management#get-key-flag)| Get a single key flag for a key|
|[SetKeyFlag](/primus-tools/Tutorials/command-details/object-management#set-key-flags)| Set key flag|
|[SetKeyId](/primus-tools/Tutorials/command-details/object-management#set-key-id)| Set key id|
|[RenameKey](/primus-tools/Tutorials/command-details/object-management#rename-key)| Rename a key or change a key password|
|[ImportCertificate](/primus-tools/Tutorials/command-details/object-management#import-certificate)| Certificate Import|
|[ImportPublicKey](/primus-tools/Tutorials/command-details/object-management#import-public-key)| Import a public key|
|[ImportKeyWrapped](/primus-tools/Tutorials/command-details/object-management#import-wrapped-key)| Import a wrapped key|
|[GetPublicKey](/primus-tools/Tutorials/command-details/object-management#export-public-key)| Export a public key|
|[ExportKeyWrapped ](/primus-tools/Tutorials/command-details/object-management#export-wrapped-key)| Export a wrapped key|


## [Partition Management](/primus-tools/Tutorials/command-details/keystore_management)

Commands used for managing HSM partitions.

|Command|Description|
|---|---|
|[GetKeyStoreStatistics](/primus-tools/Tutorials/command-details/keystore_management#get-partition-statistics)|Get number of objects (type, number) and show used/free size|
|[ListKeyStore](/primus-tools/Tutorials/command-details/keystore_management#list-partition)| List partition information (as visible to JCE API)|
|[ClearKeyStore](/primus-tools/Tutorials/command-details/keystore_management#clear-partition)| Clear the partition (delete all objects/keys)|

## [Smart Key Attributes](/primus-tools/Tutorials/command-details/SKA)

Commands used for Smart Key Attribute key management.

|Command|Description|
|---|---|
|[CreateAttestationKey](/primus-tools/Tutorials/command-details/SKA#create-attestation-key)| Create attestation key (for signed attestations and timestamps, needs RKS)|
|[ListEkaAccess](/primus-tools/Tutorials/command-details/SKA#list-ska-access)| List smart key (SKA/eka) access information|
|[CreateEkaKey](/primus-tools/Tutorials/command-details/SKA#create-ska-key)| Create smart (EKA/SKA) key|
|[CreateIntegrityKey](/primus-tools/Tutorials/command-details/SKA#create-integrity-key)| Create integrity key (for SKA use)|
|[GetAttestation](/primus-tools/Tutorials/command-details/SKA#get-attestation)| Get key attributes (attested/signed)|
|[ModifyEka](/primus-tools/Tutorials/command-details/SKA#modify-ska)| Modify smart key (SKA/EKA) attributes|
|[SetKeyFlagEka](/primus-tools/Tutorials/command-details/SKA#set-ska-key-flag)| Set key flag on SKA/EKA key|
|[SignEka](/primus-tools/Tutorials/command-details/SKA#sign-ska)| Sign test with SKA/EKA|

## [KeytoolX & JarsignerX](/primus-tools/Tutorials/command-details/KeytoolX&JarsignerX)

Commands used for subcommands of KeytoolX and JarsignerX.

|Command|Description|
|---|---|
|[KeytoolX](/primus-tools/Tutorials/command-details/KeytoolX&JarsignerX#keytoolx)| Adapter to keytoolX|
|[JarsignerX](/primus-tools/Tutorials/command-details/KeytoolX&JarsignerX#jarsignerx)| Adapter to jarsignerX|

## [Bring Your Own Key](/primus-tools/Tutorials/command-details/Bring-Your-Own-Key)

Commands used for different BYOK procedures.

|Command|Description|
|---|---|
|[AzureByokExport](/primus-tools/Tutorials/command-details/Bring-Your-Own-Key#microsoft-azure-key-vault-byok)| Wrap-export RSA, EC, or AES key, for Azure BYOK|
|[AwsKmsByokExport](/primus-tools/Tutorials/command-details/Bring-Your-Own-Key#amazon-web-services-kms-byok)| Wrap-export a AES key for AWS KMS BYOK|

|SalesforceByokExport| Wrap-export a AES/HMAC key derivation for Salesforce BYOK (currently in testing)|

## [Elliptic Curve Integrated Encryption Scheme](/primus-tools/Tutorials/command-details/ECI)

Commands used for ECIES procedures.

|Command|Description|
|---|---|
|[IesChunkingEncrypt](/primus-tools/Tutorials/command-details/ECI#ecc-ies-chunking-encrypt)| Elliptic Curve Integrated Encryption Scheme chunking file encryption|
|[IesChunkingDecrypt](/primus-tools/Tutorials/command-details/ECI#ecc-ies-chunking-decrypt)| Elliptic Curve Integrated Encryption Scheme chunking file decryption|
|[IesEncrypt](/primus-tools/Tutorials/command-details/ECI#ies-encrypt)| Elliptic Curve Integrated Encryption Scheme file encryption|
|[IesDecrypt](/primus-tools/Tutorials/command-details/ECI#ies-decryption)| Elliptic Curve Integrated Encryption Scheme file decryption|


## [EMV](/primus-tools/Tutorials/command-details/EMV)

Commands used for EMV procedures.

|Command|Description|
|---|---|
|[ImportKeySplit](/primus-tools/Tutorials/command-details/EMV#import-split-key)| Import of plain key split into 3 parts (EMV)|
|[ImportKeyWrappedZmk](/primus-tools/Tutorials/command-details/EMV#import-wrapped-key)| Import of key encrypted (wrapped)|
|[ExportKeyWrappedZmk](/primus-tools/Tutorials/command-details/EMV#export-wrapped-key)| Export of key encrypted (wrapped)|
|[ExportKeySplit](/primus-tools/Tutorials/command-details/EMV#export-split-key)| Export of plain key split into 3 parts (EMV)|

## [Signing](/primus-tools/Tutorials/command-details/signing)

Commands used for signing and signature verification.

|Command|Description|
|---|---|
|[Sign](/primus-tools/Tutorials/command-details/signing#sign)| Sign test|
|[JarSignatureCheck](/primus-tools/Tutorials/command-details/signing#jar-signature-check)| Check Primus JCE provider (primusX.jar) code signature|


