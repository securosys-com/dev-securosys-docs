---
sidebar_position: 3
title: AWS BYOK - How to create a key for AWS KMS
sidebar_label: Create Key on HSM
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Create AWS KMS Key

By default, AWS KMS creates key material when you create an AWS KMS key. To import your own key material instead, create a KMS key without key material. Then import the key material. To create a KMS key with no key material, it is possible to use the AWS Management Console or the create-key request with AWS KMS API.

To create a key with no key material, specify a key spec of `SYMMETRIC_DEFAULT` (the default value) and an origin of `EXTERNAL`. The key spec and origin of a KMS key are immutable values. 

:::note
Once you create a key designed for imported key material in KMS, it is not possible to convert it into a KMS key with key material from AWS KMS or any other source!
:::

The key state of a KMS key with an `EXTERNAL` origin and no key material is `PendingImport`. A KMS key can remain in `PendingImport` state indefinitely. However, it is not possible to use a KMS key in `PendingImport` state in cryptographic operations. When key material is imported, the key state of the KMS key changes to Enabled, and is ready to use in cryptographic operations.

AWS KMS records an entry in the AWS CloudTrail log when creating a KMS key, downloading the public key and import token, as well as importing the key material. AWS KMS also records an entry when you delete imported key material or when AWS KMS deletes expired key material.

For information about creating multi-Region keys with imported key material, see [Importing key material into multi-Region keys](https://docs.aws.amazon.com/kms/latest/developerguide/multi-region-keys-import.html).

<Tabs groupId="device-setup">
  <TabItem value="ui" label="Create KMS key (via AWS Console)" default>
  
  Use the AWS Management Console to create a symmetric encryption KMS key without key material. Imported keys have an Origin value of `External`. It is necessary to create a KMS key for the imported key material only once and reimport the same key material into an existing KMS key. 

Please follow the next steps to create a KMS key with no key material.
1.	Sign in to the AWS Management Console and open the AWS Key Management Service (AWS KMS) console.
2.	To change the AWS Region, use the Region selector in the upper-right corner of the page.
3.	In the navigation pane, choose **`Customer managed keys`**.
4.	Choose **`Create key`**.
5.	Choose **`Symmetric`**.
:::note
It is not possible to import key material into an asymmetric KMS key.
:::

6.	In **`Key usage`**, the **`Encrypt and decrypt`** option is already selected. Do not change it.
:::note
You cannot import key material into an HMAC KMS key.
:::
7.	Expand **`Advanced options`**.
8.	For **`Key material origin`**, select **`External`**.
:::note
You cannot import key material into a KMS key in a custom key store.
:::
9.	Confirm the check box next to **`I understand the security, availability, and durability implications of using an imported key`** to indicate that you understand the implications of using imported key material. 
10.	By default, this procedure creates a KMS key in the selected Region.
:::note
To create a multi-Region primary key with no key material, in the **`Regionality`** section, choose **`Multi-Region key`**.
:::
11.	Choose **`Next`**.
12.	Type an alias and (optionally) a description for the KMS key.
Choose **`Next`**.
13.	(Optional). On the **`Add tags`** page, add tags that identify or categorize the KMS key.
Choose **`Next`**.
14.	In the **`Key administrators`** section, select the IAM users and roles who can manage the KMS key.
:::note
IAM policies can give other IAM users and roles permission to manage the KMS key.
:::
15.	(Optional) To prevent the selected IAM users and roles from deleting this KMS key, in the **`Key deletion`** section at the bottom of the page, clear the **`Allow key administrators to delete this key`** check box.
Choose **`Next`**.
16.	In the **`This account`** section, select the IAM users and roles in this AWS account who can use the KMS key in crypto-graphic operations. 

:::note
IAM policies can give other IAM users and roles permission to use the KMS key.
:::

17.	(Optional) It is possible to allow other AWS accounts to use this KMS key for cryptographic operations. 

To do so, in the **`Other AWS accounts`** section at the bottom of the page, choose **`Add another AWS account`** and enter the AWS account ID of an external account. To add multiple external accounts, repeat this step.

:::note
To allow principals in the external accounts to use the KMS key, Administrators of the external account must create IAM policies that provide these permissions.
:::

Choose **`Next`**.

18.	Review the key settings. It is possible to return and adapt all settings before finishing.

19.	When done, choose **`Finish`** to create the key.

If the operation succeeds, you have created a KMS key with no key material. Its status is `Pending import`. To con-tinue the process now, see [Downloading the public key and import token (console)](/aws-byok/Tutorials/Download-Pub-key.md). To continue the process later, choose **`Cancel`**.
</TabItem>
  <TabItem value="console" label="Create a KMS Key (via AWS KMS API)" default>

Optionally you can use the AWS KMS API to create a symmetric encryption KMS key with no key material, send a **`create-key`** request with the Origin parameter set to `EXTERNAL`. The following example shows how to do so with the AWS Command Line Interface (AWS CLI).

```bash
$ aws kms create-key --origin EXTERNAL
```

If the command was successful, an output similar to the following should be printed out:

```bash
{
    "KeyMetadata": {
        "Origin": "EXTERNAL",
        "KeyId": "1234abcd-12ab-34cd-56ef-1234567890ab",
        "Description": "",
        "Enabled": false,
        "MultiRegion": false,
        "KeyUsage": "ENCRYPT_DECRYPT",
        "KeyState": "PendingImport",
        "CreationDate": 1568289600.0,
        "Arn": "arn:aws:kms:us-west-2:111122223333:key/1234abcd-12ab-34cd-56ef-1234567890ab",
        "AWSAccountId": "111122223333",
        "KeyManager": "CUSTOMER",
        "KeySpec": "SYMMETRIC_DEFAULT",
        "CustomerMasterKeySpec": "SYMMETRIC_DEFAULT",
        "EncryptionAlgorithms": [
            "SYMMETRIC_DEFAULT"
        ]
    }
}
```

The AWS KMS key's Origin is seen as `EXTERNAL` and its `KeyState` is `PendingImport`.

Ensure to copy the `KeyId` value from your command output as it will be used in later steps of the guide.
</TabItem>
</Tabs>