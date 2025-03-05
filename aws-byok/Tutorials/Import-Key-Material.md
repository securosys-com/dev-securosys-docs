---
sidebar_position: 7
title: AWS BYOK - Import Key Material
sidebar_label: Import Key Material
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Import Key Material

After wrapping the import key material, import the key material to use within AWS KMS. To import key material, upload the wrapped import key material from the chapter [Export and Wrap Key Material](/aws-byok/Tutorials/Export&Wrap.md) and the import token that was downloaded in chapter [Download the Public key and Import Token](/aws-byok/Tutorials/Download-Pub-key.md). It is important to import key material into the same KMS key that was specified when downloading the public key and import token, otherwise the import will fail. 

When key material is imported, the key state of the KMS key changes to Enabled. The key is now available to use in cryptographic operations.

When importing key material, it is possible to set an optional expiration date for the key material. When the key material expires, AWS KMS deletes the key material and the KMS key becomes unusable. To use the KMS key in cryptographic operations, reimport the same key material. After importing the key material, it is not possible to set, change, or cancel the expiration date for the current import. To change these values, delete and reimport the same key material.

To import key material, use either the AWS KMS console or the `ImportKeyMaterial` API request. 

When importing the key material, an `ImportKeyMaterial` entry is added to the AWS CloudTrail log to record the `ImportKeyMaterial` operation. The CloudTrail entry is the same whether using the AWS KMS console or the AWS KMS API.

After a successful import of the encrypted key material to the AWS KMS, it uses your corresponding account private key to decrypt the encrypted key material. 
<Tabs groupId="device-setup">
  <TabItem value="ui" label="Import (via API)" default>

To import key material, use the `ImportKeyMaterial` request. The following example uses the AWS CLI.
To import the key material please use this example, please replace the variables with your own:
```bash
$ aws kms import-key-material --key-id 1234abcd-12ab-34cd-56ef-1234567890ab \
    --encrypted-key-material EncryptedKeyMaterial.bin \
    --import-token ImportToken.bin \
    --expiration-model KEY_MATERIAL_EXPIRES \
    --valid-to 2022-09-17T12:00:00-08:00
```
|Parameter |Description|
|---|---|
|`--key-id` |Replace `1234abcd-12ab-34cd-56ef-1234567890ab` with a key ID of the KMS key that you specified when you downloaded the public key and import token. To identify the KMS key, use its key ID or key ARN. |
|`--encrypted-key-material`|	Replace `EncryptedKeyMaterial.bin` with the name of the file that contains the encrypted key material.|
|`--import-token`| Replace `ImportToken.bin` with the name of the file that contains the import token.|
|`--expiration-model`	| To enable expiration of the imported key material, insert the value of the` --expiration-model` parameter to its default value, `KEY_MATERIAL_EXPIRES`, or omit the parameter. To set the imported key material so it does not expire, insert the value of the parameter to `KEY_MATERIAL_DOES_NOT_EXPIRE`.|
|`--valid-to`	|If the `--expiration-model` parameter was set to `KEY_MATERIAL_EXPIRES` then introduce the `--valid-to` parameter to the command and replace the example date to the desired key expiration date and time. The date and time can be up to a maximum of 365 days from the time of the request. The parameter value corresponds to ISO 8601. Omit the `--valid-to` parameter from the command, if parameter `--expiration-model` wasn’t added.|

If the key import was successful the key state of the KMS key changes to `Enabled`, and the KMS key is now available to use in cryptographic operations. An `ImportKeyMaterial` entry has been added to the AWS CloudTrail log to record the `ImportKeyMaterial` operation.

</TabItem>
  <TabItem value="console" label="Import (via Console)" default>

To import key material with the AWS Management Console please follow these next steps:
1.	If you are on the **`Download wrapping key and import token`** page, skip to Step 8.
2.	Sign in to the AWS Management Console and open the AWS Key Management Service (AWS KMS) console.
3.	To change the AWS Region, use the Region selector in the upper-right corner of the page.
4.	In the navigation pane, choose **`Customer managed keys`**.
5.	Choose the key ID or alias of the KMS key for which you downloaded the public key and import token.
6.	Choose the **`Cryptographic configuration`** tab and view its values. The tabs are on the detail page for a KMS key below the **`General configuration`** section. 
7.	Choose the **`Key material`** tab and then choose **`Upload key material`**.
    - In the **`Encrypted key material and import token`** section, under **`Wrapped key material`**, select **`Choose file`**. Then upload the file that contains your wrapped (encrypted) key material.
8.	In the **`Encrypted key material and import token`** section, under **`Import token`**, select **`Choose file`**. Upload the file that contains the import token that was downloaded in chapter [Download the Public key and Import Token](/aws-byok/Tutorials/Download-Pub-key.md).
9.	In the **`Expiration option`** section, determine whether the key material should expire. To set an expiration date and time, choose **`Key material expires`**, and use the calendar to select a date and time. It is possible to specify a date up to 365 days from the current date and time.
10.	Choose **`Finish`** or **`Upload key material`**.

If the key import was successful the key state of the KMS key changes to `Enabled`, and the KMS key is now available to use in cryptographic operations. An `ImportKeyMaterial` entry has been added to the AWS CloudTrail log to record the `ImportKeyMaterial` operation.

</TabItem>
</Tabs>