---
sidebar_position: 1
title: Encryption of data using AWS KMS
sidebar_label: Encrypt data
description: Learn how to encrypt and decrypt data using AWS KMS and Securosys HSM. This guide covers the necessary steps, including connecting to an EC2 instance, deploying the XKS Proxy, and executing encryption and decryption commands with base64-encoded plaintext.
tags: [AWS, AWS KMS, XKS, EC2]
---

# Encrypting data
### AWS KMS & Securosys HSM - Integration Guide

This example demonstrates the encryption and decryption operations detailed in the AWS documentation.

To execute these operations, you must first establish a connection with your EC2 instance and deploy the Securosys XKS Proxy. The following commands are recommended for this purpose:


```js
service xksproxy start|stop|restart
```
In the example command below, we use a specified key generated from the HSM to encrypt a base64-encoded plaintext string.

```js
aws kms encrypt --key-id arn:aws:kms:eu-central-1:123456789:key/b111ab11-d1ee-11c1-bd11-111e11f111a1
--plaintext SGVsbG8h --output text --query CiphertextBlob | base64 --decode \> /home/ec2-user/EncryptedFile
```
The below command uses the previously specified key to decrypt the plaintext.
```js
aws kms decrypt --ciphertext-blob fileb:///home/ec2-user/EncryptedFile --key-id
arn:aws:kms:eu-central-1:123456789:key/b111ab11-d1ee-11c1-bd11-111e11f111a1 --output text
--query Plaintext | base64 --decode \> /home/ec2-user/PlaintextFile
```

:::info Base64 Encoding
 
 Both the plaintext input for encryption and the resulting plaintext after decryption must be **base64-encoded**. If you encounter any errors in the console, check the application logs in the "xks_proxy.log" file. For more information on Securosys XKS Proxy logging, refer to the [Troubleshooting chapter](../Troubleshooting/logging.md).
:::

### More content

- [XKS proxy Log Error Codes](../Troubleshooting/Logging-Errors.md)
- [Encrypting AWS S3 Bucket](../../Use-Cases/usecases)
- [AWS Bring Your Own Key (BYOK)](/aws-byok/overview)