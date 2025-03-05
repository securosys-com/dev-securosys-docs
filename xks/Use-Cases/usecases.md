---
sidebar_position: 0
title: Encrypting AWS S3 Bucket with an HSM
sidebar_label: Encrypting AWS S3 Bucket 
description: Learn how to encrypt AWS S3 buckets with Securosys HSM integration. Use the XKS proxy for secure SSE-KMS configuration, key control, and enhanced data protection.
tags: [AWS, AWS KMS, XKS, EC2]
---

# Encrypting AWS S3 Bucket
### Securosys HSM - Integration Guide

The Securosys XKS proxy acts as a controllable kill switch. When you deactivate the XKS proxy, all ongoing encryption and decryption operations using XKS keys will cease.

However, AWS services that have already loaded a data key into memory for a resource will continue to function until you deactivate the resource or the service key cache expires. For example, Amazon S3 retains cached data keys for a short period when bucket keys are enabled.

### S3 Bucket Key Configuration with SSE-KMS

For more information, refer to the [AWS documentation](https://docs.aws.amazon.com/AmazonS3/latest/userguide/configuring-bucket-key.html).

### You might be interested in

- [Getting Started with XKS Proxy for AWS](../Quickstart/quickstart.md)
- [AWS Bring Your Own Key (BYOK)](/aws-byok/overview)