---
sidebar_position: 0
title: Prerequisites for Primus Tools
sidebar_label: Prerequisites
description: Prerequisites before installing Securosys Primus Tools for Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites

Please adhere to the below-listed prerequisites as they are required for the correct installation, configuration and usage of the Primus Tools.

- [x] Securosys Primus HSM or [CloudHSM Service](/cloudhsm/overview/) with JCE license and JCE API enabled.
- [x] Java Runtime Environment (JRE) 8 or newer
- [x] “Unrestricted policy” files for Oracle Java (not mandatory for OpenJDK)
  - [x] e.g. [downloadable here](https://www.oracle.com/java/technologies/javase-jce8-downloads.html) (see ```README.txt``` how to install)

## Primus HSM Configuration

To set up the Primus HSM Hardware, please refer to the user guide available on the [Securosys Support Portal](https://support.securosys.com/external/knowledge-base/article/63) (account requried).

:::tip CloudHSM configuration
The CloudHSM partition comes **preconfigured** for the use of Primus Tools. Ensure the JCE API is included and activated in your subscription.
- [Getting started with CloudHSM](/cloudhsm/GettingStarted/activation_process)
:::

To configure the on-premises Primus HSM, continue with the following steps:

- The Primus Tools require the JCE interface enabled on device and user level (plus license):
<Tabs groupId="device-setup">
  <TabItem value="ui" label="HSM User Interface (LC Display) Primus X/S-Series" default>

  ```
 	SETUP → CONFIGURATION → SECURITY → DEVICE SECURITY → CRYPTO POLICY → JCE
 	SETUP → CONFIGURATION → SECURITY → USER SECURITY → JCE
```
</TabItem>
  <TabItem value="console" label="HSM Console Primus HSM, all Series" default> 

```bash
hsm_sec_set_config jce=true
hsm_sec_enter_user_config
hsm_user_set_config jce=true
```
  </TabItem>
</Tabs>

- The Primus Tools require a valid setup password, which can be renewed as follows:
<Tabs groupId="device-setup">
  <TabItem value="ui" label="HSM User Interface (LC Display) Primus X/S-Series" default>
  ``` 	
    ROLES → USER → NEW SETUP PASSWORD
  ```
</TabItem>
  <TabItem value="console" label="HSM Console Primus HSM, all Series" default>  
```bash
hsm_sec_new_setup_pass
```
  </TabItem>
</Tabs>

Continue to **[Installation](./primus-tools-installation.md)**.

:::caution
Since the temporary setup password will **expire**, you should retrieve the permanent secret (which does not expire).

- See [How to fetch the permanent secret?](./Provider.md).
:::

