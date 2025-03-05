---
sidebar_position: 0
title: Installing XKS Proxy for AWS - Prerequisites
sidebar_label: 1. Prerequisites
description: Ensure you meet prerequisites before integrating Securosys CloudHSM or Primus HSM with XKS Proxy and AWS KMS including AWS account, VPC, XKS Proxy, and more.
tags: [AWS, AWS KMS, XKS, EC2]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites
### AWS KMS & Securosys HSM - Integration Guide

Before integrating your HSM with Securosys External Key Store (XKS) Proxy and AWS KMS External Key Store, please make sure to fulfill all the necessary requirements listed below:

  - **AWS**:
    - Existing AWS account, 
    - Configured AWS VPC, AWS KMS external key store successfully connected (Example basic VPC configuration guide shown in the document annex)
  - **Docker**: Latest version installed on your host device and configured user permissions. [View installation guide](./ec2-docker-installation.md)
  - **Securosys**:
    - [Support Portal](https://support.securosys.com/external) account (free), to download the XKS Proxy,
    - Securosys XKS Proxy v1 or newer - [Download (login required)](../downloads),

#### Securosys Hardware Security Module (HSM):

<Tabs groupId="device-setup">
  <TabItem value="cloud" label="Cloud" default>
      [Securosys CloudHSM](../../cloudhsm/overview) offers near-instant Hardware Security Module (HSM) operation by allowing you to select and subscribe to a variety of services and options tailored to your needs.
    - [Subscribe online](https://cloud.securosys.com/cloudhsm) (Free trial available)
    - [Contact sales](https://www.securosys.com/en/contactus). 
  </TabItem>
  
  <TabItem value="on-premises" label="On-premises">
    Securosys Primus HSM, firmware v2.8.21, v2.10.5 or newer with JCE API license:
  - [Securosys Primus HSM](https://www.securosys.com/en/hsm/hsm-overview)
  - [Contact Sales](https://www.securosys.com/en/contact)
  </TabItem>
</Tabs>

:::tip Load Balancing

It is recommended to establish redundancy in your environment. For more information on Securosys XKS proxy redundancy please refer to AWS documentation [Creating a network load balancer](https://docs.aws.amazon.com/kms/latest/developerguide/vpc-connectivity.html#xks-nlb).

:::

## More content

- [Download the Securosys XKS Proxy for AWS](../downloads) (login required)
- [Installing Docker on an EC2 instance](./ec2-docker-installation)
- [Example - Creation of an XKS in AWS KMS](../Tutorials/Examples/Example-AWS-KMS.md)
- [Example - Generating a .jks domain file](/xks/Tutorials/Examples/Example-jks.md)