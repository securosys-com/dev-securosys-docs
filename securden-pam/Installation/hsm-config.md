---
sidebar_position: 2
title: Configuring the HSM for Securden Unified PAM
sidebar_label: HSM Configuration
description: Configuring the Hardware Security Module (HSM) for Securden Unified Privileged Access Management (PAM).
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# HSM Configuration
The sections below highlight the HSM configuration changes required for Securden PAM application, after the initial HSM setup (initial wizard, role setup, network settings, etc.). 

:::tip Reminder

Changing the HSM Security Policy requires Security Officer (SO) role privileges: (SO) Cards m of n ![](/img/TSB/so_card.png)

:::

For CloudHSM customers request the necessary configuration changes highlighted below.


## Basic configuration

    <Tabs groupId="ConfigVariant">
    <TabItem value="xml" label="XML Configuration File (export/import)" default>
    After initial on-prem HSM setup
    - Export the HSMs Security Configuration 
    - Adapt the exported XML file *.sconfig according the highlighted sections below
    - Apply the changes according to the **Securden Integration Method** (1-3)
    - Import the modified Security Configuration

    Activate the PKCS#11 process (if not already enabled):
        ```xml
        <pkcs_process>
// highlight-start        
            <active>enabled</active>
// highlight-end            
            <port>2310</port>
            <interface>1</interface>
        </pkcs_process>
        ```
    HSM Security Policy can be defined on the device or user specific, to provide different settings per partition. The example below references to the user specific configuration. 
    Adapt the user configuration for this specific user:
        ```xml
        ...
        <crypto_user state="enabled">                   <!-- enabled=user config, disabled=device config -->
            <user_name>YourPartitionName</user_name>               
             ...
// highlight-start            
            <import_keys>disabled</import_keys>          <!-- disable key import on user -->
            <export_keys>disabled</export_keys>          <!-- disable key export on user -->
            <extract_keys>disabled</extract_keys>        <!-- disable wrapped key export on user -->
// highlight-end            
            ...
// highlight-start            
            <session_objects>enabled</session_objects>   <!-- enable session objects -->
// highlight-end            
            <destroy_objects>enabled</destroy_objects>   <!-- enable deletion of keystore objects -->
            <use_objects>enabled</use_objects>           <!-- enable usage of objects -->
            ...
            <pkcs_password state="value"/>               <!-- set partition pwd for PKCS#11, default=none -->
// highlight-start            
            <client_api_access>enabled</client_api_access> <!-- allow access to user/partition -->
// highlight-end            
            ...
// highlight-start            
            <pkcs_allowed>enabled</pkcs_allowed>         <!-- enable PKCS#11, interface on partition -->
// highlight-end            
            ...
     </crypto_user>
     ...
     ```
    </TabItem>
    <TabItem value="cli" label="HSM Console">
        Adapt according to the XML changes. For details consult the corresponding HSM User Guide chapter 4.     
    </TabItem>
    <TabItem value="ui" label="HSM User Interface">
        Adapt according to the XML changes. For details consult the corresponding HSM User Guide chapter 4.
    </TabItem>
    </Tabs>

:::tip Reminder

When enabling user configuration the user specific values might still be on default and differ from the device settings (e.g. empty PKCS#11 password). 

:::


