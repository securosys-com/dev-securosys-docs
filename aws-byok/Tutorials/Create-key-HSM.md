---
sidebar_position: 5
title: AWS BYOK - How to create a key on the HSM
sidebar_label: Create Key on HSM
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Create Key on HSM

It is necessary to generate a key on the HSM which will be imported to the AWS KMS. In the below example we create a key using Primus Tools, but a different tool can be used. If you will be using different tools please ensure to create the key with the proper parameters.

## Create Key using Primus Tools

To create a key on the Securosys CloudHSM or PrimusHSM `primus-tools.jar` can be used. It is paramount to set the flag of the import key to extractable. To import the key to AWS KMS the import key algorithm must be symmetric AES with a recommended size of 256 bits. If there is a symmetric AES key with the necessary parameters already prepared, please skip this step.

:::note
Ensure that `primusX.jar` is in the same directory as `primus-tools.jar` or the `PATH` or `CLASSPATH` environment variable is set correctly.
:::

:::warning
The Key with the same key name must not exist prior creation!
:::

The following command sample creates a key on the HSM:
```bash
java -jar primus-tools.jar CreateKey <HSM connection and credentials> \
-keyname <keyname> -type AES [-size <size>] \
-flags <flags> [-access <access>]
```
|Parameter |Description|
|---|---|
|`-keyname` |Replace the `\<keyname\>` variable with the name of the key to store on the HSM.|
|`-type`|	The key type must be AES! (other HSM known key types: RSA, TDEA, DDEA, EC)|
|`-size`|	Replace the `\<size\>` variable with the key size of the AES key. The recommended key size is 256. |
|`-flags`| Replace the `\<flags\>` variable with the possible flag variables (separated by a comma): `sensitive`, `extractable`, `modifiable`, `encrypt`, `decrypt`, `sign`, `verify`, `wrap`, `unwrap`. <br/> The key must be flagged as `extractable`! <br/> Flags correspond to the key flags and capabilities described in [Primus HSM User Guide Appendix chapters](https://support.securosys.com/external/knowledge-base/article/63).|
|`-access`| Used for EKA/SKA only|

Example command: 
```bash
java -jar primus-tools.jar CreateKey --host a-api.cloudshsm.com -port 2300 \
-user DEMO-TEST -password file:pwsetup \
-primusproxyuser FQO...QQOS -primusproxypassword file:pwproxy \
 -keyname AWS-ByokAES256 -type AES -size 256 -flags extractable,wrap,unwrap
```
The `CreateKey` command does not give any feedback that the key was created. To verify the key creation either check the HSM logs or list the keys.

## List Keys on HSM

It is possible to use Primus Tools to list all objects, assigned flags and the first 3 bytes of the SHA256 hash over the symmetric key objects to detect changes on the user partition. The following command sample shows how to do so:
```bash
java -jar primus-tools.jar ListKeyStoreObjcets <HSM connection and credentials> 
[-keysonly] [-prefixes] [-allflags] [-allobjects]
```

|Parameter| Description|
|---|---|
|`-keysonly` 	| list only keys and no other objects |
|`-prefixes`	| list objects in a parsable format with keywords included|
|`-allflags`|	list all key attributes/flags|
|`-allobjects`| list all objects (SecretKey, PublicKey, Certificate, …)|


Example command of a parsable key listing, including key attributes and hashes for symmetrical keys:
```bash
java -jar primus-tools.jar ListKeyStoreObjects --host a-api.cloudshsm.com -port 2300 \
-user DEMO-TEST -password file:pwsetup \
-primusproxyuser FQO...QQOS -primusproxypassword file:pwproxy
-keysonly -prefixes -allflags
```
Example output:
```bash
ONAME: AWS-ByokAES256 OTYPE: AES KSIZE: 256 OFLAGS: nonsensi-tive,extractable,modifiable,copyable,token,nonindestructible,private,nonpublic,nonneverextracta-ble,nonalwayssensitive,local,encrypt,decrypt,sign,verify,wrap,unwrap,notderive OHASH: 2ECFC2
```