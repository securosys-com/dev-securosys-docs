---
sidebar_position: 0
title: Prerequisites to install Authorization App
sidebar_label: Prerequisites
description: Prerequisites to install the Securosys Authorization Mobile App
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites

The Securosys Authorization App is tailored to work in combination with [Rest-API Transaction Security Broker (TSB)](/tsb/overview) and Securosys Hardware Security Modules (HSMs), either:
- On-premises, Primus HSM, or
- [CloudHSM](/cloudhsm/overview)

To be able to use the _Securosys Authorization App_ functionalities, please adhere to the prerequirements listed below.
You must meet **one** of the following architectural requirements:

<Tabs groupId="purchase-channel">
<TabItem value="cloud" label="Cloud">    
- **CloudHSM - TSBaaS:** 
  - Rest-API access with `TSB_ENGINE` license installed (available in [CloudHSM ECO, SBX and PLATINUM](/cloudhsm/Packages/overview#service-package-comparison) services)
<details>
<summary>Check `TSB_ENGINE` License Information</summary>

If your subscription supports the Securosys Authorization App, the following license flags should appear:

**GET** /v1/licenseInfo
```js {5,6}	
{
  "clientFlags": [    
    ...
    "KEY_AUTH",
    "REST_API",
    "TSB_ENGINE",    
    ...
  ]
}
```
</details>
</TabItem>
<TabItem value="on-premises" label="On-premises" default>
- **Primus HSM:** [Set up and configured](/tsb/Installation/hsm-device-setup-tsb) successfully, including:
  - Device setup completed with the initial wizard
  - `Root Key Store` installed and configured
  - Enabled security features: `Client API access`, `Key Auth`, `JCE`, `Rest-API`, `TSB Workflow Engine`
  - Created at least one user
- **Transaction Security Broker (REST API):** Successfully [deployed and configured](/tsb/Quickstart/docker-quickstart) the TSB Docker container with the `TSB_ENGINE` license
</TabItem>
<TabItem value="testing" label="Testing Environment">
For testing purposes, you can create a 90-day free trial partition on the Securosys Cloud platform by visiting [https://cloud.securosys.com/](https://cloud.securosys.com/). <br/>
This trial partition provides a secure, isolated environment where you can explore and test various functionalities of Securosys products without committing to a paid license. The cloud-based environment replicates the full feature set available in production, allowing you to evaluate API integration capabilities. This trial setup is ideal for development, experimentation, or simply gaining hands-on experience with Securosys services.
</TabItem>
</Tabs>