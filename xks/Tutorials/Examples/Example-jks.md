---
sidebar_position: 3
title: Generate a .jks file for AWS KMS
sidebar_label: Generate .jks file 
description: Step-by-step guide to generate a .jks file for Securosys XKS Proxy integration with AWS KMS. Learn to configure with OpenSSL, Keytool, and application.yaml.
tags: [AWS, AWS KMS, XKS, EC2]
---

import Link from '@docusaurus/Link';

# Generating a .jks file for the XKS Proxy
### AWS KMS & Securosys HSM - Integration Guide

The ```.jks``` domain file is required for the Securosys XKS proxy to authenticate to your domain.

To correctly configure the Securosys XKS proxy, a path to the ```.jks``` file must be adapted in the ```application.yaml``` file. Check the parameter ```key-storessl: key-storekey-store``` in [this section](../../Installation/Installation.md#4-configure-the-securosys-xks-proxy).

There are many ways to generate a ```.jks``` file. In this example, we are using **openssl** and **keytool** utilities. It is required to have these utilities preinstalled on the device where the .jks file will be created.

A prerequired step for this example is to generate a certificate for your domain. For the next steps you will require your ```\*.ca```, ```\*.crt``` files and a private key.

To generate a ```.jks``` from these files, it is required to combine your ```\*.crt``` and ```\*.ca``` files. Manually copy all data from ```\*.ca``` into ```\*.crt```, and then you can use the following command.

When prompted provide a password for the newly generated ```.p12``` file, replace the file names ```exanple.crt``` and ```example.key``` with your own.

```js
openssl pkcs12 -export -in example.crt -inkey example.key -out abc.p12
```

To generate the ```.jks``` file, execute the following command with the java keytool utility. When prompted provide the same password used with the **openssl** command. Replace the ```example``` file names with your own:
```js
keytool -importkeystore -srckeystore example.p12 \
 srcstoretype PKCS12 \
 destkeystore example.jks \
 deststoretype JKS
```

:::tip Don't forget
to import your ```.jks``` file to the AWS EC2 instance where the Securosys XKS Proxy will be installed.
:::

## More content

- [Installing Docker on an EC2 instance](../../Installation/ec2-docker-installation.md)
- [Tutorial - How to encrypt/decrypt data in AWS KMS?](./Example-AWS-KMS.md)
- [Encrypting AWS S3 Bucket](../../Use-Cases/usecases)
- [AWS Bring Your Own Key (BYOK)](/aws-byok/overview)