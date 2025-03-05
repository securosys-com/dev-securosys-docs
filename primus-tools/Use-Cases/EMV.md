---
sidebar_position: 4
title: EMV and Primus Tools
sidebar_label: EMV
description: EMV with Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---


# EMV
EMV standards from the payment sector also define procedures and key ceremonies how to import, export and transfer keys between two entities. 

EMV is a trademark of EMVCo LLC, the regulator agency, originally formed by Europay International, Master-
Card International and Visa International, to manage, maintain and enhance the EMV Integrated Circuit Card
specification for Payment Systems


Commands will require an established HSM connection and credentials to be able to execute properly. For further assistance on how to configure your HSM connection and credentials please see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider.md).

:::note
TDEA and DDEA keys and algorithms are outdated and just supported for migration compatibility. 
:::

With EMV-related commands perform import or export of a split 3DES key into or out of the HSM.

## EMV Procedure Example

Prepare the credentials in blinded files stored on a USB drive `D:\` (Windows OS; path notations differ on Linux).

Having the temporary Setup Password noted in the below format, e.g.: 
```bash
g3z7Y-5knqc-hkQAs-9A4kE-Ppr6v 
```

Blinding and storing the temporary Setup Password to `D:\pwsetup`:
```bash
java -jar primus-tools.jar BlindPassword consoleinput: -outputfile d:\pwsetup
```

Results in having a file `D:\pwsetup`:
```bash
blinded-aes:c775dbe31d545d38dcb342ccadee236e3ec……6005d6766808e7fc522ef60d5a670b6e9c111837fe451041
```

Blinding and storing the Proxy Password to `D:\pwproxy`:
```bash
java -jar primus-tools.jar BlindPassword consoleinput: -outputfile d:\pwproxy
```
As the setup password will expire you may want to retrieve the permanent secret, which does not expire, blinded and stored to `D:\pwsecret`:
```bash
java -jar primus-tools.jar GetUserSecret /
-host a-api.cloudshsm.com -port 2300 /
-user DEMO-TEST -password consoleinput: /
-primusproxyuser DEMO-TESTP -primusproxypassword file:d:\pwproxy /
-blinded -outputfile d:\pwsecret
```
In the following examples, the file `pwsetup` should be swapped with the permanent secret blinded file `pwsecret`.

###	Import Split Key ZMK1

Import a split key named `myZMK1-2DES` as a 16 Byte DDEA Type (112-bit 2DES Key):
```bash
java -jar primus-tools.jar ImportKeySplit 
-host a-api.cloudshsm.com -port 2300
-user DEMO-TEST -password file:d:\pwsetup
-primusproxyuser DEMO-TESTP -primusproxypassword file:d:\pwproxy
-deskeyname myZMK1-2DES
```

Output of command - insert required components:
```bash
Please enter Component 1:   0CD215E5BD36125B732E40DD8165E779
Please enter KCV 1:         148887

Please enter Component 2:   107FC8809EE74017940E473B727201B7
Please enter KCV 2:         123BEC

Please enter Component 3:   B60777CF897BF8E65C9BBC5D48AC5D75
Please enter KCV 3:         497DAB

overall KCV: E1CC69

key myZMK-2DES successfully imported
```

:::note
The component and KCV values are not visible.
:::

DDEA plain key/KCV in this example: ``` AAAAAAAAAAAAAAAABBBBBBBBBBBBBBBB / E1CC69 ```

### Import Split Key ZMK2

Import a split key named `myZMK2-AES128` as a 16 Byte AES-128 Type:
```bash
java -jar primus-tools.jar ImportKeySplit 
-host a-api.cloudshsm.com -port 2300
-user DEMO-TEST -password file:d:\pwsetup
-primusproxyuser DEMO-TESTP -primusproxypassword file:d:\pwproxy
-aeskeyname myZMK2-AES128
``` 

Output of command - insert required components:
```bash
Please enter Component 1:   DF766747988EA9B5E898F6015BB122DD
Please enter KCV 1:         097915

Please enter Component 2:   B3D486605DEA808E075141AEB70C1605
Please enter KCV 2:         CD5452

Please enter Component 3:   75C329A701615AC7C76B525A11AA9493
Please enter KCV 3:         9BBE82

overall KCV: 9604F9

key myZMK-AES128 successfully imported
```

:::note
The component and KCV values are not visible.
:::

AES-128 plain key/KCV in this example: ` 1961C880C40573FC28A2E5F5FD17A04B / 9604F9 `

### Import Wrapped Key 

Import the wrapped key named `myZMK1-Imp-2DES` as DDEA wrapped with ZMK1/DDEA:
```bash
java -jar primus-tools.jar ImportKeyWrapped 
-host a-api.cloudshsm.com -port 2300
-user DEMO-TEST -password file:d:\pwsetup
-primusproxyuser DEMO-TESTP -primusproxypassword file:d:\pwproxy
-zmkname myZMK1-2DES
-key 79BD62F07F33FD7323DF0634ED50BE8D -kcv DBCDB0
-keyname myZMK1-Imp-2DES -keytype DDEA -ecb
```
Output of command:
```bash
key myZMK1-Imp-2DES successfully imported
```
2DES plain key/KCV in this example: `AAAAAAAAAAAAAAAACCCCCCCCCCCCCCCC / DBCDB0`

### Import Wrapped Key (DDEA wrapped with ZMK2/AES-128)
Import the wrapped key named `myZMK2-Imp-2DES` as DDEA wrapped with ZMK2/AES-128:
```bash
java -jar primus-tools.jar ImportKeyWrapped 
-host a-api.cloudshsm.com -port 2300
-user DEMO-TEST -password file:d:\pwsetup
-primusproxyuser DEMO-TESTP -primusproxypassword file:d:\pwproxy
-zmkname myZMK2-AES-128
-key 8CAA88DD20222C4529F83D228D70BC53 -kcv DBCDB0
-keyname myZMK2-Imp-2DES -keytype DDEA -ecb
```
Output of command:
```bash
key myZMK1-Imp-2DES successfully imported
```
2DES plain key/KCV in this example: `AAAAAAAAAAAAAAAACCCCCCCCCCCCCCCC / DBCDB0`

### Re-Export Imported Key 

Export a key named `ZMK1-Imp-2DES` wrapped with `ZMK2-AES128`:
```bash
java -jar primus-tools.jar ExportKeyWrapped 
-host a-api.cloudshsm.com -port 2300
-user DEMO-TEST -password file:d:\pwsetup
-primusproxyuser DEMO-TESTP -primusproxypassword file:d:\pwproxy
-zmkname myZMK2-AES128
-keyname myZMK1-Imp-2DES
-ecb

8CAA88DD20222C4529F83D228D70BC53

KCV: DBCDB0
```

2DES plain key/KCV in this example: `AAAAAAAAAAAAAAAACCCCCCCCCCCCCCCC / DBCDB0`

### List Keys  

List keys on the partition to verify changes:
```bash
java -jar primus-tools.jar ListKeyStoreObjects
-host a-api.cloudshsm.com -port 2300
-user DEMO-TEST -password file:d:\pwsetup
-primusproxyuser DEMO-TESTP -primusproxypassword file:d:\pwproxy
-keysonly -prefixes
```
Output:
```bash
ONAME: myZMK1-2DES OTYPE: DDEA KSIZE: 112 OFLAGS: unextractable OHASH: BB4AE3
ONAME: myZMK1-Imp-2DES OTYPE: DDEA KSIZE: 112 OFLAGS: encrypted-extractable OHASH: 77CF89
ONAME: myZMK2-AES128 OTYPE: AES KSIZE: 128 OFLAGS: unextractable OHASH: 0469C2
```
