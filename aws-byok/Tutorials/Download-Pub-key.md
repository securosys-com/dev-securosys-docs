---
sidebar_position: 4
title: AWS BYOK - Public Key Download & Import Token
sidebar_label: Downloading Public Key & Import Token
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Downloading Public Key & Import Token

After creating a symmetric encryption AWS KMS key without key material it is necessary to download its public key and import token. Download both items in one step by using the AWS KMS console or the GetParametersForImport AWS API request. The public key that AWS KMS provides is a 2048-bit RSA public key that is unique to your AWS account.

:::note
The public key and import token are valid for only 24 hours. If you don't use them to import key material within 24 hours of downloading them, you must download new ones.
:::

To protect the key material during import, it is paramount to encrypt it using the downloaded public key and a supported wrapping algorithm. AWS offers RSA PKCS #1 encryption with one of three padding options, while the Primus Tools only support `RSAES_OAEP_SHA_256` and `RSAES_OAEP_SHA_1`. It is highly recommended to use the `RSAES_OAEP_SHA_256` wrapping algorithm to wrap your key material.

The import token includes metadata that ensures that the key material is imported correctly. When uploading the en-crypted key material to AWS KMS, the same import token that was downloaded in this chapter must be uploaded.

It is always possible to download these items again. Usually reimporting the same key material into a KMS key is used to change the expiration time for the key material, or to restore expired or deleted key material.

<Tabs groupId="device-setup">
  <TabItem value="ui" label="Downloading (via API)" default>
To download the public key and import token, use the `GetParametersForImport` API request. Specify the KMS key that will be associated with the imported key material. This KMS key must have an Origin value of `EXTERNAL`.

Example command using the AWS CLI:
```bash
$ aws kms get-parameters-for-import \
	--key-id 1234abcd-12ab-34cd-56ef-1234567890ab \
	--wrapping-algorithm RSAES_OAEP_SHA_256 \
	--wrapping-key-spec RSA_2048
```
|Parameter 			      |description|
|---					  |---|
|`--key-id`	              |Replace `1234abcd-12ab-34cd-56ef-1234567890ab` variable with the key ID of the KMS key for which to download the public key and import token.|
|`--wrapping-algorithm`	  |Specify the used wrapping algorithm as either RSAES_OAEP_SHA_1 with `RSAES_OAEP_SHA_256`|
|`--wrapping-key-spec`	  |Specify the wrapping (public) key algorithm. Only 2048-bit RSA public keys are supported.|

Example output:
```bash
{
	"ParametersValidTo": 1568290320.0,
	"PublicKey": "public key (base64 encoded)",
	"KeyId": "arn:aws:kms:us-west-2:111122223333:key/1234abcd-12ab-34cd-56ef-1234567890ab",
	"ImportToken": "import token (base64 encoded)"
}
```
</TabItem>
  <TabItem value="console" label="Downloading (via Console)" default>

Use the AWS KMS console to download the public key and import token.
1.	If you just completed the steps to create a KMS key with no key material and you are on the **`Download wrapping key and import token page`**, skip to Step 8.
2.	Sign in to the AWS Management Console and open the AWS Key Management Service (AWS KMS) console.
3.	To change the AWS Region, use the Region selector in the upper-right corner of the page.
4.	In the navigation pane, choose **`Customer managed keys`**.
5.	Choose the alias or key ID of the KMS key that is pending import.
6.	Choose the **`Cryptographic configuration`** tab and view its values. The tabs are below the **`General configuration`** section.
7.	Choose the **`Key material`** tab and then choose **`Download wrapping key and import token`**.

:::note
The **`Key material`** tab appears only for symmetric encryption KMS keys that have an Origin value of `EXTERNAL`.
:::

8.	For **`Select wrapping algorithm`**, choose the option that you will use to encrypt your key material.
9.	Choose **`Download wrapping key and import token`**, and then save the file.

If you have a **`Next`** option, to continue the process now, choose **`Next`**. To continue later, choose **`Cancel`**. Otherwise, to close the window, choose **`Cancel`** or click the **`X`**.

10.	Unzip the `.zip` file that was downloaded in the previous step (`ImportParameters.zip`).

The folder contains the following files:
|File|Description|
|---|---|
|`wrappingKey_KMS` `key_key_ID_timestamp`|	The 2048-bit RSA public key file.|
|`importToken_KMS` `key_key_ID_timestamp`|	The import token file.|
|`README_KMS` `key_key_ID_timestamp.txt`|	This file contains information about the public key, the wrapping algorithm to use to encrypt the import key material, and the date and time when the wrapping key (public key) and import token expire.|
</TabItem>
</Tabs>