---
sidebar_position: 0
title: Prerequisites for FortiGate & Security HSM
sidebar_label: 1. Prerequisites
description: Prerequisites for Fortinet FortiGate Next Gen Firewall (NGFW) with Securosys Hardware Security Modules (HSMs).
keywords: [fortinet, fortigate, firewall, hsm clusters, integration, primus hsm, securosys]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites

Please ensure the following requirements are met  
- [x] FortiGate with FortOS v7.2.8 Special Build 9127 and newer that incorporates the Securosys PKCS#11 Provider (v2.2.4 and newer)
- [x] Securosys Hardware Security Module, with PKCS#11 API enabled:
  - [x] [CloudHSM partition (HSM as a Service)](/cloudhsm/overview/) or
  - [x] Primus HSM, firmware v2.8.21 or newer. [Contact sales](https://www.securosys.com/en/contact)


## HSM Setup and Configuration

<Tabs groupId="purchase-channel">
<TabItem value="cloud" label="Cloud" default>


Securosys CloudHSM allows almost instant HSM operation by selecting and contracting the different services and options for your FortiGate.
- Getting started with [CloudHSM](/cloudhsm/overview/).
- [Contact sales](https://www.securosys.com/en/contact).

The HSM must be initialized, and a specific user (partition) must be set up. 

Verify that 
- the PKCS#11 API is licensed and enabled, and the PKCS#11 password is defined
- a fresh valid user setup password is retrieved for client onboarding
- the Security Policy is restricted (disable Import/Export/Extract)

</TabItem>
<TabItem value="on-premises" label="On-premises">

For general on-premises Primus HSM hardware, High-Availibility Cluster setup and operation in FIPS or Common Criteria certified modes, refer to the corresponding [Primus HSM User Guide](https://support.securosys.com/external/knowledge-base/article/63) for details.
</TabItem>
</Tabs>


### Enable PKCS#11 API
The API must be enabled on device and user level.

<Tabs groupId="ui">
<TabItem value="device" label="Device user interface" default>
- Setup <IconNavNext/> Configuration <IconNavNext/> Security <IconNavNext/> Device Security <IconNavNext/> Crypto Policy <IconNavNext/> PKCS#11: enabled  
- Setup <IconNavNext/> Configuration <IconNavNext/> Security <IconNavNext/> User Security <IconNavNext/> (user name) <IconNavNext/> PKCS#11: enabled  
</TabItem>
<TabItem value="console" label="Console interface">
- `hsm_sec_set_config pkcs11=true`  
- `hsm_user_set_config pkcs11=true`  
</TabItem>
<TabItem value="xml" label="XML configuration File">
```xml
  ...
  <pkcs_process>
    // highlight-start
    <active>enabled</active>
    // highlight-end
    ...
  </pkcs_process>
  ...
  <crypto_process>
    ...
     <crypto_user state="enabled">                   <!-- enabled=user config, disabled=device config -->
        <user_name>PART001</user_name>               <!-- partition name -->
    // highlight-start
        <pkcs_allowed>enabled</pkcs_allowed>         <!-- enable PKCS#11, interface on partition -->
    // highlight-end
     </crypto_user>
    ... 
```
</TabItem>
</Tabs>

### Set PKCS#11 Password
Assuming that user level configuration is used.

<Tabs groupId="ui">
<TabItem value="device" label="Device user interface" default>
- Setup <IconNavNext/> Configuration <IconNavNext/> Security <IconNavNext/> User Security <IconNavNext/> (user name) <IconNavNext/> PKCS#11 password  
</TabItem>
<TabItem value="console" label="Console interface">
- `hsm_user_set_config pkcs11_pwd`  
</TabItem>
<TabItem value="xml" label="XML configuration File">
```xml
  ...
  <crypto_process>
    ...
     <crypto_user state="enabled">                   <!-- enabled=user config, disabled=device config -->
        <user_name>PART001</user_name>               <!-- partition name -->
    // highlight-start
        <pkcs_password>PRIMUSDEV</pkcs_password>     <!-- set partition pwd for PKCS#11 -->
    // highlight-end
        ...
     </crypto_user>
    ...

```
</TabItem>

</Tabs>

### Retrieve a New Setup Password
To generate a new setup password for the specific partition

<Tabs groupId="ui">
<TabItem value="device" label="Device user interface" default>
- Roles <IconNavNext/> User <IconNavNext/> New Setup Pw 
</TabItem>
<TabItem value="console" label="Console interface">
- `hsm_user_new_setup_pass`  
</TabItem>
</Tabs>

### Disable Wrapped Key Export, Key Extract, and Key Import

<Tabs groupId="ui">
<TabItem value="device" label="Device user interface" default>
- Setup <IconNavNext/> Configuration <IconNavNext/> Security <IconNavNext/> User Security <IconNavNext/> (user name) <IconNavNext/> Key export: disabled 
- Setup <IconNavNext/> Configuration <IconNavNext/> Security <IconNavNext/> User Security <IconNavNext/> (user name) <IconNavNext/> Key extract: disabled 
- Setup <IconNavNext/> Configuration <IconNavNext/> Security <IconNavNext/> User Security <IconNavNext/> (user name) <IconNavNext/> Key import: disabled 
</TabItem>
<TabItem value="console" label="Console interface">
- `hsm_user_set_config key_export=false`  
- `hsm_user_set_config key_extract=false`  
- `hsm_user_set_config key_import=false`  
</TabItem>
<TabItem value="xml" label="XML configuration File">
```xml
  ...
  </pkcs_process>
  
     <crypto_user state="enabled">                   <!-- enabled=user config, disabled=device config -->
        <user_name>PART001</user_name>               <!-- partition name -->
        ...
     // highlight-start
        <import_keys>disabled</import_keys>          <!-- disable key import on user -->
        <export_keys>disabled</export_keys>          <!-- disable key export on user -->
        <extract_keys>disabled</extract_keys>        <!-- disable wrapped key export on user -->
     // highlight-end
        ...
     </crypto_user>
     ...
```
</TabItem>
</Tabs>



