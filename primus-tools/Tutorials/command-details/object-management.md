---
sidebar_position: 2
title: Object Management with Primus Tools
sidebar_label: Object Management
description: Object Management with Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Object Management

Object management commands are used for managing objects and keys. These commands are used when objects must be created, deleted, listed, the object flags listed or modified.

Commands will require an established `<HSM connection and credentials>` parameter to be able to execute properly. For further assistance on how to prepare your `<HSM connection and credentials>` parameter, see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider) section.

## List Partition Objects
The following command is able to list objects within the specified partition, assigned flags and the first 3 bytes of the SHA256 hash over the symmetric key objects to detect changes:
```bash
java -jar primus-tools.jar ListKeyStoreObjects <HSM connection and credentials> [-keysonly] [-prefixes] [-allflags] [-allobjects] [-kak <key attestation key name> [-kakpassword <key password>]]
```

Where:
- `keysonly` 	list only keys and no other objects 
- `prefixes`	list objects in a parsable format with keywords included
- `allflags`	list all key attributes/flags
- `allobjects`	list all objects (SecretKey, PublicKey, Certificate, …)
- `kak`         define the key attestation key
- `kakpassword` define the key attestation key password

For a parsable key listing including key attributes and hashes for symmetrical keys use the following command:
```bash
java -jar primus-tools.jar ListKeyStoreObjects <HSM connection and credentials> -keysonly -prefixes -allflags
```
Example output:
```bash
ONAME: MS-PrimusByokAES128 OTYPE: AES KSIZE: 128 OFLAGS: nonsensitive,extractable,modifiable,copyable,token,nonindestructible,private,nonpublic,nonneverextractable,nonalwayssensitive,local,encrypt,decrypt,sign,verify,wrap,unwrap,notderive OHASH: 2ECFC2
ONAME: MS-PrimusByokAES192 OTYPE: AES KSIZE: 192 OFLAGS: nonsensitive,extractable,modifiable,copyable,token,nonindestructible,private,nonpublic,nonneverextractable,nonalwayssensitive,local,encrypt,decrypt,sign,verify,wrap,unwrap,notderive OHASH: B4BC9D
ONAME: MS-PrimusByokAES256 OTYPE: AES KSIZE: 256 OFLAGS: nonsensitive,extractable,modifiable,copyable,token,nonindestructible,private,nonpublic,nonneverextractable,nonalwayssensitive,local,encrypt,decrypt,sign,verify,wrap,unwrap,notderive OHASH: 1B7B18
ONAME: MS-PrimusByokECP224 OTYPE: EC KSIZE: 224 OFLAGS: nonsensitive,extractable,modifiable,copyable,token,nonindestructible,private,nonpublic,nonneverextractable,nonalwayssensitive,local,notdecrypt,sign,notunwrap,derive,notintegrity,notsignrecover
…
```

## List Partition Object (Single Alias) 

The following command is able to list a single object within the specified partition, assigned flags and the first 3 bytes of the SHA256 hash over the symmetric key objects to detect changes:
```bash
java -jar primus-tools.jar ListKeyStoreObject <HSM connection and credentials> [-keysonly] [-prefixes] [-allflags] [-allobjects] -aliases <?>
```

Where:
- `keysonly` 	list only keys and no other objects 
- `prefixes`	list objects in a parsable format with keywords included
- `allflags`	list all key attributes/flags
- `allobjects`	list all objects (SecretKey, PublicKey, Certificate, …)
- `kak`         define the key attestation key
- `kakpassword` define the key attestation key password
- `aliases`     specify the key aliases to list

:::note
`aliases <?>` must be the last specified parameter.
:::

## List Key Entry
List key information:
```bash
java -jar primus-tools.jar ListKeyEntry <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>]
```

## List Key Flags

The following command allows to list the flags of a single key on the HSM:
```bash
java -jar primus-tools.jar ListKeyFlags <HSM connection and credentials> -keyname <keyname> [-keypassword <keypassword>]
```

## Create Key

Use the following command to create a key on the HSM:
```bash
java -jar primus-tools.jar CreateKey <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>] /
-type <RSA|AES|TDEA|DDEA|EC> [-size <size>] [-curve <curve>] /
[-flags <flags>] [-ckd] [-access <access>]
```

:::note
PQC supported Algorithms require HSM firmware version 3.2.2. or above and Primus Tools version 2.4.1 
:::

Where:
|parameter|description|
|---|---|
|`keyname`|	Specify name of the key to store on the HSM (must not exist before creation)|
|`type`|	Define key type of `RSA`, `AES`, `TDEA`, `DDEA`, `EC` <br /> or PQC Algorithms: <br /> **ML-DSA:** <br />`ML-DSA-44`, `ML-DSA-65`, `ML-DSA-87`<br />**SLH-DSA:**<br />`SLH-DSA-SHAKE-128s`, `SLH-DSA-SHAKE-128f`, `SLH-DSA-SHAKE-192s`, `SLH-DSA-SHAKE-192f`, `SLH-DSA-SHAKE-256s`, `SLH-DSA-SHAKE-256f`, `SLH-DSA-SHA2-128s`, `SLH-DSA-SHA2-128f`, `SLH-DSA-SHA2-192s`, `SLH-DSA-SHA2-192f`, `SLH-DSA-SHA2-256s`, `SLH-DSA-SHA2-256f` <br />**Kyber:** <br />`ML-KEM-512`, `ML-KEM-768`, `ML-KEM-1024` <br /> **XMSS:** <br />`Xmss10Sha256`, `Xmss16Sha256`, `Xmss20Sha256`, `Xmss10Shake256`, `Xmss16Shake256`, `Xmss20Shake256` <br />**LMS:** <br />`{SHA256 or SHAKE}_{M32 or M24}_{H5 or H10 or H15 or H20}_{W1 or W2 or W4 or W8}` <br />e.g. `SHA256_M32_H5_W1`, `SHAKE256_M24_H20_W8` |
|`size`|	Specify key size of `RSA`, `AES` or `EC`|
|`curve`|	Specify elliptic curve name, e.g. `secp224k1`, `secp224r1`, `secp256k1`, `secp256r1`, `secp384r1`, `secp521r1`, `x962p239v1`, `x962p239v2`, `x962p239v3`, `brainpool224r1`, `brainpool256r1`, `brainpool320r1`, `brainpool384r1`, `brainpool512r1`, `frp256v1`, (User-defined curves are currently not supported)|
|`flags`|	Define the key flags `sensitive`, `extractable`, `modifiable`, `encrypt`, `decrypt`, `sign`, `verify`, `wrap`, `unwrap` <br /> To negate a flag insert "non" in front e.g. `nonextractable`, `nonencrypt`, … <br /> If left undefined, default keyflags will be assigned.|
|`access`|	Define access to the SKA key. Used for EKA/SKA only|

:::note
The key must not exist before creation. Flags corresponds to the key flags and capabilities described in Primus HSM User Guide Appendix chapters. By default, keys are not extractable. If you require extractable keys, the flags must be set when creating the key!
:::

##	Delete Key

The following command allows to delete a single key on the HSM:
```bash
java -jar primus-tools.jar DeleteKey <HSM connection and credentials> -keyname <keyname> [-keypassword <keypassword>]
```
:::note
Key must exist. If Key Invalidation is active, keys are only marked for deletion, they are not really deleted. 
:::

## Get Key Flag

To list a single specific flag of a single specific key, execute the following command:
```bash
java -jar primus-tools.jar GetKeyFlag <HSM connection and credentials> -keyname <keyname> [-keypassword <keypassword>] -flag <flagname>
```

## Set Key Flags
The following command allows to modify multiple key flags of a key on the HSM:
```bash
java -jar primus-tools.jar SetKeyFlag <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>] /
-flag <flagname>[,<flagname>,…] [-value <flagvalue>]
```

Where:
- `keyname`	name of the key to modify on the HSM 
- `flag`	sensitive,extractable,modifiable, 
 	encrypt,decrypt,sign,verify,wrap,unwrap
 	To negate a flag insert "non" in front e.g. nonextractable, nonencrypt, …
- `value`	optional, true or false, can be used instead of above negation

**possible key flags**: sensitive,extractable,modifiable,copyable,preload,token,indestructible,private,public,blocked,neverextractable,nopublickey,alwayssensitive,externalobject,local,trusted,wrapwithtrusted,unique,encrypt,decrypt,sign,verify,wrap,unwrap,derive,integrity,attestation,timestamp,verifyrecover,signrecover

:::note
Key must be modifiable. Policy can only be set stricter and not looser! Device/Partition policy must allow it. Flags correspond to the key flags and capabilities described in Primus HSM User Guide chapter Appendix. 
:::

## Set Key Id

Set the ID of a key.
```bash
java -jar primus-tools.jar SetKeyId <HSM connection and credentials> -keyname <keyname> [-keypassword <keypassword>] /
[-keyid <[hex:|base64:]keyid>] (omiting clears id) [-objecttype <Certificate|PublicKey>]
```

## Rename Key

To rename (not to be mistaken for ID change) a key, use the following command:
```bash
java -jar primus-tools.jar RenameKey <HSM connection and credentials> /
 -keyname <keyname> [-keypassword <keypassword>] -newname <newname> [-newpassword <newpassword>]
```

## Import Certificate
Import a certificate to the HSM.
```bash
java -jar primus-tools.jar ImportCertificate <HSM connection and credentials> /
 -certificatename <name of the certificate to be imported> /
 -inputfile <certficate input file, DER or PEM format> | -inputdata <certficate data, base64 or DER> /
 [-force (force certificate overwrite and unmatching certificate installation)] / 
 [-check (check certificate corresponds to possibly present public key)]
```

## Import Public Key

Import a public key with the following command:
```bash
java -jar primus-tools.jar ImportPublicKey <HSM connection and credentials> /
-keyname <name of the public key to be imported> /
[-keytype <type of the public key>] /
-inputfile <public key input file, DER or PEM format> | -inputdata <public key data, base64 of DER>
```

## Import Wrapped Key
Import a wrapped key with the following command:
```bash
java -jar primus-tools.jar ImportKeyWrapped  <HSM connection and credentials> /
-wrapkeyname <name of the unwrapping key> [-wrapkeypassword <unwrapping key password>]
-keydata <hexadecimal wrapped/encrypted key(s)>
[-infile <wrapped/encrypted key input file(s)>]
-keyname <name of the key to be imported> [-keypassword <key password>]
```

Where:
- `-wrapkeyname`  specify the wrapping key (AES or RSA),
- `-wrapkeypassword` specify the password of the wrapping key, RSA key mustn't have a password
- `-keydata` insert the hexadecimal keydata output from the `ExportKeyWrapped` command,
- `-infile` optionally specify the wrapped key file, omit the `-keydata` parameter
- `-keyname` specify the key to be exported (AES, RSA or EC)
- `-keypassword` specify the password of the key to be exported

## Export Public Key
To export a public key from the HSM use the command `GetPublicKey`:
```bash
java -jar primus-tools.jar GetPublicKey <HSM connection and credentials> /
-keyname <keyname> [-keypassword <keypassword>]
```

:::note
`GetPublicKey` encodes the (DER/ASN.1) encoding of the public key with base64.
:::

## Export Wrapped Key

Export a wrapped key with the following command:
```bash
java -jar primus-tools.jar ExportKeyWrapped <HSM connection and credentials> /
-wrapkeyname <name of the wrapping key> [-wrapkeypassword <wrapping key password>] /
-keyname <name of the key to be exported, a symmetric key> [-keypassword <key password>] /
[-outfile <wrapped/encrypted key output file(s)>] /
```

Where:
- `-wrapkeyname`  specify the wrapping key (AES or RSA),
- `-wrapkeypassword` specify the password of the wrapping key, RSA key mustn't have a password
- `-keyname` specify the key to be exported (AES, RSA or EC)
- `-keypassword` specify the password of the key to be exported
- `-outfile` the command outputs the wrapped key in hexadecimal, add this optional command to output the data to a file
