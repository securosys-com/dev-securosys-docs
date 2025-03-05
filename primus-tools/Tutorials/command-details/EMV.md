---
sidebar_position: 9
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# EMV Related Commands

EMV is a trademark of EMVCo LLC, the regulator agency, originally formed by Europay International, Master-Card International and Visa International, to manage, maintain and enhance the EMV Integrated Circuit Card specification for Payment Systems.

With EMV commands import or export a split 3DES key into or out of the HSM.

:::note
TDEA and DDEA keys and algorithms are outdated and just supported for migration compatibility. 
:::

Commands will require an established `<HSM connection and credentials>` parameter to be able to execute properly. For further assistance on how to prepare your `<HSM connection and credentials>` parameter, see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider) section.

## Import Wrapped Key

Import a wrapped (encrypted) key into the HSM.

:::note
HSM must allow `key import` and key must not exist prior import. Setting key passwords is not recommended. ZMK must have the `unwrap` attribute. In case of wrong KCV the key is already stored it should be deleted manually, see [Delete Key](/primus-tools/Tutorials/command-details/object-management#delete-key)
:::

```bash
java -jar primus-tools.jar ImportKeyWrappedZmk <HSM connection and credentials> /
-zmkname <name of the ZMK> [-zmkpassword <ZMK password>] /
-key <hexadecimal wrapped/encrypted key> -kcv <KCV of the key> /
-keyname <name of the key to be imported> [-keypassword <key password>] /
[-keytype <AES/TDEA/DDEA>] [-ecb] [-keysize]
```

Where:
- zmkname 	name of the ZMK on the HSM, having the unwrap attribute set
- key	    heydecimal value of the wrapped key to import
- kcv	    KCV of the key
- keyname	name of the imported key to be stored on the HSM
- keytype	key type within the encrypted container
- ecb	    unwrapping in Electronic Code Book mode (required for EMV procedures)
- keysize	used for keytype AES only

## Import Split Key

Key ceremony to import a plain key (ZMK: `AES`, `TDEA`, `DDEA` type), split into 3 parts, entered by 3 security officers including Key Check Value (KCV) according to EMV procedures.

:::note
HSM must allow `key import`. The key must not exist before import. Setting key passwords is not recommended.
:::

Import of plain key split into 3 parts (EMV):
```bash
java -jar primus-tools.jar ImportKeySplit <HSM connection and credentials> /
[-deskeyname <name of the TDEA/DDEA key on the HSM> [-deskeypassword <key password>]] /
[-aeskeyname <name of the AES key on the HSM> [-aeskeypassword <key password>]] 
```

- Use `-deskeyname` to import a TDEA/DDEA key and `-aeskeyname` to import an AES key.
- The `zmk` can only be used for wrap/unwrap functionality and cannot be exported:

|Key flag type|True|False|
|---|---|---|
|Export & Modification|sensitive, private, token|extractable, modifiable, public, indestructible|
|Functionality|wrap, unwrap|attestation, encrypt, decrypt, sign, derive|


## Export Wrapped Key
Export a wrapped (encrypted) key.

:::note
The key must exist prior export and have the `extractable` and `nonsensitive` flags set. HSM must allow `key export`. Setting key passwords is not recommended. ZMK must have `wrap` attribute.
:::

```bash
java -jar primus-tools.jar ExportKeyWrappedZmk <HSM connection and credentials> /
-zmkname <name of the ZMK> [-zmkpassword <ZMK password>]
-keyname <name of the key to be exported> [-keypassword <key password>]
[-ecb]
```

## Export Split Key

Key ceremony to export a plain key (ZMK: `AES`, `TDEA`, `DDEA` type), split into 3 parts including Key Check Value (KCV), to be noted by 3 security officers, according to EMV procedures.

:::note
The key must exist prior export and have the `extractable` and `nonsensitive` flags set. HSM must allow `key export`. Setting key passwords is not recommended. ZMK must have `wrap` attribute.
:::

Export of plain key split into 3 parts (EMV):

```bash
java -jar primus-tools.jar ExportKeySplit <HSM connection and credentials> /
[-deskeyname <name of the TDEA/DDEA key> [-deskeypassword <key password>]] /
[-aeskeyname <name of the AES key> [-aeskeypassword <key password>]]
```
