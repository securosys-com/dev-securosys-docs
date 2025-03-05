---
sidebar_position: 0
title: Salesforce BYOK - Prerequisites
sidebar_label: Prerequisites
description: Description overview
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites

Make sure to adhere to the below prerequisites before continuing with the BYOK procedure:

- Salesforce account with Enterprise, Performance, or Unlimited Edition subscription with Salesforce Shield enabled,
- Either Salesforce Classic or Lightning Experience,
- Salesforce User Permissions `Manage Encryption Keys`, `Manage Certificates` and `Customize Application`, for more info visit [Salesforce documentation - Bring Your Own Key (BYOK)](https://developer.salesforce.com/docs/atlas.en-us.securityImplGuide.meta/securityImplGuide/security_pe_byok_setup.htm).
- Securosys Primus HSM or Cloud HSM Service with JCE license and JCE API enabled with HSM firmware 2.8.45 or newer
- Securosys [Primus Tools](/primus-tools/overview) v2.3.1 or newer, visit [Primus Tools - Prerequisites](/primus-tools/Installation/Prerequisites) section for Primus Tools prerequisites.

:::info
Please review the Salesforce prerequisites in the [Salesforce - Bring Your Own Key](https://developer.salesforce.com/docs/atlas.en-us.securityImplGuide.meta/securityImplGuide/security_pe_byok_setup.htm)
 section of the Salesforce Developers documentation.
:::

## Primus HSM Configuration
Setting up the Primus HSM hardware or your [CloudHSM](/cloudhsm/overview) partition is not described in this guide. Please refer to the corresponding User Guides downloadable from the [Securosys Support Portal](https://support.securosys.com/external/knowledge-base/article/63) 
 (account required).

The Securosys on-premises Primus HSM or Securosys [CloudHSM](/cloudhsm/overview) partition needs the Crypto policy (and User policy) configuration to allow `Key Export` and `Key Extract` for the used partition.

:::tip
The [CloudHSM](/cloudhsm/overview) services are preconfigured for Salesforce BYOK. Ensure the [JCE API](/jce/overview) is included and activated in your subscription. For available service packages and options, please go to [Cloud Console](https://cloud.securosys.com/cloudhsm) or [contact sales](https://www.securosys.com/en/cloudshsm)
.
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

- The **`Primus Tools`** commands require the JCE interface enabled on device and user level (plus license):
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
