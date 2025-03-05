---
sidebar_position: 5
title: Updating Securosys XKS Proxy for AWS KMS
sidebar_label: 5. Update proxy
description: Learn how to update the Securosys XKS Proxy with minimal downtime by downloading the latest file and using the Docker restart command.
tags: [AWS KMS, XKS]
---

# Updating Securosys XKS proxy
### AWS KMS & Securosys HSM - Integration Guide

To update the Securosys XKS proxy with as little downtime as possible, follow the next steps:

- Download the latest Securosys XKS proxy file and configure it. Refer to the [download section](../downloads.md) (login required).
- Run the latest image by running it with restart always docker command. Replace the blue marked variables with your environment parameters. Example command:

```js
docker run -d --restart always -name <NameOfLatestContainer> --add-host \
<YourHostDomain>:127.0.0.1 --network=host -v /home/ec2-user/securosys_xks_1.0.0/
--network=host -v **/home/ec-2user/securosys\_xks\_1.0.0/
config-files:/etc/app/config/ 
securosys.jfrog.io/external-xks/securosys-xks:1.0.0.20231007T130000Z
```
- Stop the previous version of the XKS proxy by running the command:

```js
docker stop <NameOfContainer>
```

## More content

- [Download the Securosys XKS Proxy for AWS](../downloads) (login required)
- [Example - Creation of an XKS in AWS KMS](../Tutorials/Examples/Example-AWS-KMS.md)
- [AWS Bring Your Own Key (BYOK)](/aws-byok/overview)