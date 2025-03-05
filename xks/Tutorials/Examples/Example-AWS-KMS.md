---
sidebar_position: 1
title: Create a key in AWS KMS
sidebar_label: Create External Key Store 🗝️
description: Follow this guide to create a key in AWS KMS using an external key store. Learn about key types, key usage, and IAM configuration for secure key management.
tags: [AWS, AWS KMS, XKS, EC2]
---

import Link from '@docusaurus/Link';

# Create an External Key Store in AWS KMS
### AWS KMS & Securosys HSM - Integration Guide

To perform the following steps, please refer to the [AWS Key Management Service (AWS KMS) console](https://console.aws.amazon.com/kms).

Ensure that you have selected the appropriate region in which you previously created the VPC. For example, choose ```eu-central-1``` (Frankfurt) in the top-right corner.

Follow these steps to create an external key store in the AWS KMS:

1) Open the [AWS Key Management Service (AWS KMS) console](https://console.aws.amazon.com/kms).

1) In the navigation pane, choose "Custom key stores", and then select "External key stores".

1) Click "Create external key store".

1) Provide a friendly name for the External Key Store (XKS). Ensure that the name you choose is unique among all external key stores in your account.

1) Select the proxy connectivity type as ```VPC endpoint service```.

1) Choose or enter the name of the VPC endpoint service associated with this external key store.

1) Enter the proxy URI endpoint.
    - The protocol should be HTTPS, and AWS KMS communicates on **port 443**.
    - Do not include the port in the proxy URI endpoint value.
    - If the VPC endpoint service specified in the previous step is recognized by AWS KMS, this field will be automatically populated.

1) To configure the proxy URI path prefix and proxy authentication credential, you have two options:

- If you **have** a proxy configuration file that contains the necessary values:
    
    - Choose "Upload configuration file" and follow the instructions to upload the file.
    - Once uploaded, the console will display the values from the file in editable fields. You can modify these values before or after creating the external key store.
    - To reveal the secret access key value, select "Show secret access key".
- If you **don't have** a proxy configuration file:

    - You can manually enter the proxy URI path and proxy authentication credential values.
    - For the proxy URI path, you can enter it manually, and the console will automatically include the required "/kms/xks/v1" value.
    - If your proxy URI path includes an optional prefix, such as "/example-prefix/kms/xks/v1", enter the prefix in the Proxy URI path prefix field.
    - Leave the field empty if there is no prefix. For the proxy authentication credential, provide both the "access key ID" and "secret access key".
    - To display the secret access key value, select "Show secret access key".

:::info note
This procedure does not set or change the authentication credential you established on your external key store proxy. It only associates the provided values with your external key store. Refer to the documentation for your external key store proxy or key management software for information on setting, changing, and rotating your proxy authentication credential ([12]).
:::

Once you have entered the necessary values for the proxy URI path prefix and proxy authentication credential, or uploaded a proxy configuration file, choose "Create external key store".

:::caution Connect External Key Store
New external key stores are not automatically connected. To create AWS KMS keys in an external key store, ensure it is connected to its corresponding external key store proxy.
:::

### Example: creating a key in AWS KMS

To create keys in AWS Key Management Service (AWS KMS), follow these steps:

Open the [AWS Key Management Service (AWS KMS) console](https://console.aws.amazon.com/kms).

In the navigation pane, choose "Customer managed keys".

Choose "Create key".

Step 1:

- Key type: Symmetric.
- Key usage: Encrypt and decrypt.
- Advanced options: External key store.
- Click "Next".

Step 2:

- External key stores: Select the external key store you created in the previous part of the instructions.
- External key: Enter the name that will identify the created key.
- Click "Next".

Step 3:

- Enter an alias for your key. It can be a shorthand for the external key ID.
- Click "Next".

Step 4:

- Key administrators: Choose the IAM users and roles who can administer this key through the KMS API.
- Click "Next".

Step 5:

- Key users: Select the IAM users and roles that can use the KMS key in cryptographic operations.

Step 6:

- Check if all the data matches what you entered and ensure that the values are suitable for your needs.
- Click the "Finish" button to complete the process.

### More content

- [Getting Started with XKS Proxy for AWS](../../Quickstart/quickstart.md)
- [Example - Creation of an XKS in AWS KMS](./Example-AWS-KMS.md)
- [Tutorial - How to encrypt/decrypt keys in AWS KMS?](./Example-Encrypt-Decrypt.md)
