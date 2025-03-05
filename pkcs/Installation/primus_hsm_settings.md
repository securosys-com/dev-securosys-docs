---
sidebar_position: 0
title: PKCS#11 - Primus HSM Configuration
sidebar_label: Primus HSM Configuration
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Primus HSM Configuration

To setup the Primus HSM please follow the steps in the [Primus HSM User
Guide][hsm-guide] chapter 3.

This text refers to global device security settings. In case you
have individual user/partition settings, use them analogous per
partition (hsm\_sec\_enter\_user\_config, hsm_user\_\...).\
The console commands have to be adapted to

-   list parameters: hsm_net\_**list**\_config,
    hsm_sec\_**list**\_config, hsm_user\_**list**\_config
-   change parameters: hsm_net\_**set**\_config,
    hsm_sec\_**set**\_config, hsm_user\_**set**\_config

Next assert that the HSM has a proper network configuration and can be
reached from the computer having the PKCS#11 library installed. The API
will be reachable under the default port unless configured differently.
Note that the service may be assigned freely to a specific network
interface.

<Tabs groupId="ui">
<TabItem value="device" label="Device user interface" default>
- SETUP <IconNavNext/> CONFIGURATION <IconNavNext/> NETWORK <IconNavNext/> SERVICES <IconNavNext/> PKCS#11 <IconNavNext/> INTERFACES: 1  
- SETUP <IconNavNext/> CONFIGURATION <IconNavNext/> NETWORK <IconNavNext/> SERVICES <IconNavNext/> PKCS#11 <IconNavNext/> TCP PORT: 2310
</TabItem>
<TabItem value="console" label="Console interface">
- `hsm_net_list_config serv=2 serv_if`  
- `hsm_net_list_config serv=2 serv_port`
</TabItem>
</Tabs>

Make sure you have SO privileges for the steps below (security
configuration):

<Tabs groupId="ui">
<TabItem value="device" label="Device user interface" default>
SO ACTIVATE  
(or in some older releases: ROLE ACTIVATION <IconNavNext/> SO ACTIVATE)
</TabItem>
<TabItem value="console" label="Console interface">
`hsm_so_activation`
</TabItem>
</Tabs>

If a new user is setup, note down the user's **setup password**. It is
required to setup the HSM connection and retrieving the [permanent user
secret with the ppin command][permanent-secret].

## Generate a New Setup Password

In case you need a new setup password for the ppin command, on the HSM
acquire SO privileges and execute the following command (the setup
password has a limited lifetime, default 3 days from first usage
onwards):

<Tabs groupId="ui">
<TabItem value="device" label="Device user interface" default>
ROLES <IconNavNext/> USER <IconNavNext/> NEW SETUP PW
</TabItem>
<TabItem value="console" label="Console interface">
`hsm_sec_new_setup_pass`
</TabItem>
</Tabs>

## Enable PKCS#11 API

In order to use the PKCS#11 Provider, the Client API and PKCS#11 access
needs to be enabled on the HSM. Set the respective security
configuration in

<Tabs groupId="ui">
<TabItem value="device" label="Device user interface" default>
- SETUP <IconNavNext/> CONFIGURATION <IconNavNext/> SECURITY <IconNavNext/> DEVICE
  SECURITY <IconNavNext/> CRYPTO POLICY <IconNavNext/> CLIENT API ACCESS

- SETUP <IconNavNext/> CONFIGURATION <IconNavNext/> SECURITY <IconNavNext/> DEVICE
  SECURITY <IconNavNext/> CRYPTO POLICY <IconNavNext/> PKCS#11
</TabItem>
<TabItem value="console" label="Console interface">
- `hsm_sec_set_config crypto_access=true`
- `hsm_sec_set_config pkcs11=true`
</TabItem>
</Tabs>

## Preparing the PKCS#11 Password (PIN)

PKCS#11 operates in two modes: Public object mode and logged-in mode,
where private key operations can be performed. On the Primus HSM,
access to the HSM is granted with permanent user secret. The
additional password for the PKCS#11 login command must be set[^1]
using the SO role per device or partition specific.

<Tabs groupId="ui">
<TabItem value="device" label="Device user interface" default>
SETUP <IconNavNext/> CONFIGURATION <IconNavNext/> SECURITY <IconNavNext/> DEVICE
SECURITY <IconNavNext/> CRYPO POLICY <IconNavNext/> PKCS#11 PASSWORD
</TabItem>
<TabItem value="console" label="Console interface">
`hsm_sec_set_config pkcs_pwd=<password>`
</TabItem>
</Tabs>

## Using HSM Session Objects

Use of session objects (CKA_TOKEN = FALSE) requires HSM firmware
v2.8.20/v2.9.2 or later and session objects enabled.  
(Up to version 2.10 the parameter was called "External Storage").
:::tip Recommendation
Most applications require session objects enabled.
:::

<Tabs groupId="ui">
<TabItem value="device" label="Device user interface" default>
SETUP <IconNavNext/> CONFIGURATION <IconNavNext/> SECURITY <IconNavNext/> DEVICE
SECURITY <IconNavNext/> CRYPO POLICY <IconNavNext/> SESSION OBJECTS
</TabItem>
<TabItem value="console" label="Console interface">
`hsm_sec_set_config session_objects=true`
</TabItem>
</Tabs>

## Export / Import Settings
:::caution
Secure operation requires import, export, and extract to be disabled.
:::

Following policies define key import/export allowance (see [HSM User
Guide][hsm-guide] for details):

<Tabs groupId="ui">
<TabItem value="device" label="Device user interface" default>
- SETUP <IconNavNext/> CONFIGURATION <IconNavNext/> SECURITY
  <IconNavNext/> DEVICE SECURITY <IconNavNext/> CRYPO POLICY
  <IconNavNext/> KEY IMPORT

- SETUP <IconNavNext/> CONFIGURATION <IconNavNext/> SECURITY
  <IconNavNext/> DEVICE SECURITY <IconNavNext/> CRYPO POLICY
  <IconNavNext/> KEY EXPORT

- SETUP <IconNavNext/> CONFIGURATION <IconNavNext/> SECURITY
  <IconNavNext/> DEVICE SECURITY <IconNavNext/> CRYPO POLICY
  <IconNavNext/> KEY EXTRACT
</TabItem>
<TabItem value="console" label="Console interface">
- `hsm_sec_set_config key_import=true`
- `hsm_sec_set_config key_export=true`
- `hsm_sec_set_config key_extract=true`
</TabItem>
</Tabs>

## Key Invalidation

Activated Key Invalidation creates a shadow copy of the key when it is
deleted. This may prevent creation of a new key with the same key name
and key id and some later mentioned tests may fail. To check if Key
Invalidation is active:

<Tabs groupId="ui">
<TabItem value="device" label="Device user interface" default>
SETUP <IconNavNext/> CONFIGURATION <IconNavNext/> SECURITY
<IconNavNext/> DEVICE SECURITY <IconNavNext/> CRYPO POLICY
<IconNavNext/> KEY INVALIDATION
</TabItem>
<TabItem value="console" label="Console interface">
`hsm_sec_list_config inval_keys`
</TabItem>
</Tabs>

## User Log via PKCS#11 API

To fetch the user log from the HSM via PKCS#11 API or ppin tool, enable
log fetching:

<Tabs groupId="ui">
<TabItem value="device" label="Device user interface" default>
SETUP <IconNavNext/> CONFIGURATION <IconNavNext/> SECURITY
<IconNavNext/> DEVICE SECURITY <IconNavNext/> MANAGEMENT POLICY
<IconNavNext/> CLIENT API USER LOG
</TabItem>
<TabItem value="console" label="Console interface">
To check the parameter: `hsm_sec_list_config client_log`  
To enable log fetching: `hsm_sec_set_config client_log=true`
</TabItem>
</Tabs>

:::info Device and User specific configuration
Above configuration parameters are shown for device level. If user specific configuration is activated, they have to be configured on the specific user partition! 
:::

:::info

- Primus HSM in FIPS mode requires PKCS#11 Provider 2.0 or newer to connect.
- It is recommended to use HSM firmware v2.8 or later.

:::


[^1]: https://support.securosys.com/external/knowledge-base/article/22

[hsm-guide]: https://support.securosys.com/external/knowledge-base/article/63
[permanent-secret]: /pkcs/Installation/permanent_secret_management#adding-permanent-secret
