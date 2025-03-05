---
sidebar_position: 1
title: Configure your HSM with the XKS Proxy for AWS
sidebar_label: 3. Configure HSM
description: Configure your HSM for Securosys XKS Proxy integration with AWS. Ensure firmware is updated and network settings are correct for seamless operation.
tags: [AWS, AWS KMS, XKS, EC2]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# HSM Configuration
### AWS KMS & Securosys HSM - Integration Guide

Before installing and configuring the Securosys XKS Proxy, you need to configure your Hardware Security Module (HSM): 

<Tabs groupId="device-setup">
  <TabItem value="cloud" label="Cloud" default>
    The following CloudHSM services are **already configured**:
    - [CloudHSM Economy (ECO)](/cloudhsm/Packages/economy)
    - [CloudHSM Sandbox (SBX)](/cloudhsm/Packages/sandbox)

    No additional action is required. Continue with the [Installation Guide](./Installation.md)
  </TabItem>
    
  <TabItem value="on-premises" label="On-premises">

If you operate your own Primus HSM and have not yet configured it, please follow the instructions of the [initial wizard](/hsm/Installation/Setup/UserGuide/GetStarted/HSM-Setup-v2-11-1)
ensure that the Primus HSM is updated to the following firmware: 

- Primus HSM Firmware v2.8.21, v2.11 or higher. 

You can download the [Securosys Primus HSM firmware](https://support.securosys.com/external/knowledge-base/article/111) from the Securosys Support Portal (login required). 


:::tip CAUTION

The guide does not cover the initial setup of the Primus HSM. Follow the procedures outlined in [Primus HSM device setup with wizard 2.11+](/hsm/Installation/Setup/UserGuide/GetStarted/HSM-Setup-v2-11-1). Ensure that the settings align with the TSB requirements as specified in [Primus HSM device configuration for TSB](/tsb/Installation/hsm-device-setup-tsb).
:::

After completing the initial setup (running the initial wizard), ensure that your HSM has the correct network configuration and can be accessed from the host device where the XKS Proxy will be installed.

Your HSM can be reached through the default JCE port (port: 2300) unless it has been configured differently. Keep in mind that the service may be assigned to one of the four available network interfaces .

Use one of the following commands to ensure a valid network configuration:

```js
:~# telnet 10.10.10.10 2300
:~# ping 10.10.10.10
```
Continue with the [Installation Guide](./Installation.md)

  </TabItem>
</Tabs>

## More content

- [Download the Securosys XKS Proxy for AWS](../downloads) (login required)
- [Example - Creation of an XKS in AWS KMS](../Tutorials/Examples/Example-AWS-KMS.md)
- [Example - Generating a .jks domain file](/xks/Tutorials/Examples/Example-jks.md)