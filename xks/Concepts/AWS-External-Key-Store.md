---
sidebar_position: 1
title: What is External Key Store (XKS) for AWS?
sidebar_label: AWS KMS XKS
description: Manage your cryptographic keys with a custom external key store, where your external key manager—physical or virtual—handles encryption and decryption, offering full control (HYOK).
tags: [AWS, AWS KMS, XKS, EC2]
---

# AWS KMS External Key Store


A custom key store external to AWS is managed by your external key manager, which can be a physical or virtual hardware security module (HSM) or any hardware or software system capable of generating and managing cryptographic keys.

The encryption and decryption of KMS keys in an external key store are handled by your external key manager using your cryptographic key material. This approach is known as "hold your own keys" (HYOK).

## More content

- [AWS Custom key stores](https://docs.aws.amazon.com/kms/latest/developerguide/custom-key-store-overview.html)
- [AWS External key stores](https://docs.aws.amazon.com/kms/latest/developerguide/keystore-external.html)
- [AWS Bring Your Own Key (BYOK)](/aws-byok/overview)