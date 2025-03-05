---
sidebar_position: 0
title: Azure Bring Your Own Key (BYOK) - Prerequisites
sidebar_label: Prerequisites
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites

Make sure to adhere to the below prerequisites before continuing with the BYOK procedure:

- Azure subscription with Key Vault Premium SKU,
- Azure CLI version 2.1.0 or later,
- Securosys Primus HSM or Clouds HSM Service with JCE license and JCE API enabled Elliptic Curve (EC) support requires HSM firmware 2.8.21 or newer
- Securosys Primus Tools v2.2.7 or newer, visit [Primus Tools - Prerequisites](/primus-tools/downloads) section for Primus Tools prerequisites.

## Primus HSM Configuration
Setting up the Primus HSM hardware or your CloudHSM partition is not described in this guide. Please refer to the corresponding User Guides downloadable from the [Securosys Support Portal](https://support.securosys.com/external/knowledge-base/article/63).

The Securosys Primus HSM or Securosys CloudHSM partition needs the Crypto policy (and User policy) configuration to allow `Key Export` and `Key Extract` for the used partition.

:::note
The CloudHSM partition is preconfigured for Azure BYOK. Ensure the JCE API is included and activated in you subscription. For available service packages and options please consult our website [Securosys CloudHSM Service](https://www.securosys.com/en/cloudhsm) and contact Securosys sales.
:::

Follow the below shown steps to configure the on-premises Primus HSM:
- Enable `Key Export` on user/partition level (SO activation required):
<Tabs groupId="device-setup">
  <TabItem value="ui" label="HSM User Interface (LC Display) Primus X/S-Series" default>

 ```
 SETUP → CONFIGURATION → SECURITY → USER SECURITY → KEY EXPORT
```
</TabItem>
  <TabItem value="console" label="HSM Console Primus HSM, all Series" default> 

```bash
hsm_sec_enter_user_config
hsm_user_set_config key_export=true
```
  </TabItem>
</Tabs>

- Enable `Key Extract` on user/partition level (SO activation required):
<Tabs groupId="device-setup">
  <TabItem value="ui" label="HSM User Interface (LC Display) Primus X/S-Series" default>

```
 	SETUP → CONFIGURATION → SECURITY → USER SECURITY → KEY EXTRACT
```
</TabItem>
  <TabItem value="console" label="HSM Console Primus HSM, all Series" default> 

```bash
hsm_sec_enter_user_config
hsm_user_set_config key_extract=true
```
  </TabItem>
</Tabs>

- The **`primus-tools`** commands require the JCE interface enabled on device and user level (plus license):
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

- The **`primus-tools`** commands require a valid setup password, which can be renewed as follows:
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
