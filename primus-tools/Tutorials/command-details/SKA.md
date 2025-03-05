---
sidebar_position: 4
title: Smart Key Attributes with Primus Tools
sidebar_label: Smart Key Attributes
description: Partition Management with Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Smart Key Attributes
Securosys [Smart Key Attributes (SKA)](https://www.securosys.com/securosys-smart-key-attributes-enabling-true-multi-authorization-rules-and-more-for-private-key-usage) enable true multi-authorization, rules and more for private key usage.

Attestation is used for signed attestations and timestamps, it requires an initialized Root Key Store. More information see [Setup and Install Root Key Store](/hsm/Installation/Setup/UserGuide/Commands/commands#setup-and-install-root-key-store-) and [Key Attestation](/tsb/Tutorials/Attestation/KeyAttestation). 

Commands will require an established `<HSM connection and credentials>` parameter to be able to execute properly. For further assistance on how to prepare your `<HSM connection and credentials>` parameter, see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider) section.

## Create Attestation Key 

Before creating any SKA key, an attestation key is required (as well as an initialized Root Key Store).
Use command `CreateAttestationKey` to create an attestation key:

```bash
java -jar primus-tools.jar CreateAttestationKey <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>] [-size <size>] [-curve <curve>] [-type <type>]
```

## List SKA Access

List smart key access information (SKA/EKA):
```bash
java -jar primus-tools.jar ListEkaAccess <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>]
```

## Create SKA Key

Create smart (SKA/EKA) key:
```bash
java -jar primus-tools.jar CreateEkaKey <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>] -type <type> [-size <size>] [-curve <curve>] [-flags <flags>] -authorizationkeys <k>,<k>,... -quorum <quorum> [-ekadelay <minutes>] [-ekalimit <minutes>] [-authorizationkeysblock <k>,<k>,...] [-quorumblock <quorum>] [-ekadelayblock <minutes>] [-ekalimitblock <minutes>] [-authorizationkeysunblock <k>,<k>,...] [-quorumunblock <quorum>] [-ekadelayunblock <minutes>] [-ekalimitunblock <minutes>] [-authorizationkeysmodify <k>,<k>,...] [-quorummodify <quorum>] [-ekadelaymodify <minutes>] [-ekalimitmodify <minutes>]
```

## Create Integrity Key

Create integrity key (for SKA use):
```bash
java -jar primus-tools.jar CreateIntegrityKey <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>] /
-type <type> [-size <size>] [-curve <curve>] /
-flags [non]<flagname>[,[non]<flagname>,...] /
[-ckd] /
[-access <access>] /
```

- possible key flags: sensitive,extractable,modifiable,copyable,preload,token,indestructible,private,public,blocked,neverextractable,nopublickey,alwayssensitive,externalobject,local,trusted,wrapwithtrusted,unique,encrypt,decrypt,sign,verify,wrap,unwrap,derive,integrity,attestation,timestamp,verifyrecover,signrecover

## Get Attestation

Get key attributes (attested/signed):
```bash
java -jar primus-tools.jar GetAttestation <HSM connection and credentials> / 
-keyname <keyname> [-keypassword <keypassword>] -attestationkeyname <attestationkeyname> [-attestationkeypassword <attestationkeypassword>] [-ca <CA PEM file>] [-out <output file name base>]
```

## Modify SKA

Modify smart key attributes (SKA/EKA):
```bash
java -jar primus-tools.jar ModifyEka <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>] -operationauthorizationkeys <k>,<k>,... -integritykey <integritykey> [-timestamp] [-modifydelay <modifydelay>] -authorizationkeys <k>,<k>,... -quorum <quorum> [-ekadelay <minutes>] [-ekalimit <minutes>] [-authorizationkeysblock <k>,<k>,...] [-quorumblock <quorum>] [-ekadelayblock <minutes>] [-ekalimitblock <minutes>] [-authorizationkeysunblock <k>,<k>,...] [-quorumunblock <quorum>] [-ekadelayunblock <minutes>] [-ekalimitunblock <minutes>] [-authorizationkeysmodify <k>,<k>,...] [-quorummodify <quorum>] [-ekadelaymodify <minutes>] [-ekalimitmodify <minutes>]
```

## Set SKA Key Flag
Set a key flag on SKA/EKA key:
```bash
java -jar primus-tools.jar SetKeyFlagEka <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>] -algorithm <algorithm> -authorizationkeys <k>,<k>,... -integritykey <integritykey> -flag <flagname> [-value <flagvalue>] [-timestamp] [-ekadelay <ekadelay>]
```

## Sign SKA

Sign test with SKA/EKA key:
```bash
java -jar primus-tools.jar SignEka <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>] -algorithm <algorithm> [-message <message>] -authorizationkeys <k>,<k>,... -integritykey <integritykey> [-timestamp] [-signdelay <signdelay>]
```