---
sidebar_position: 1
title: Configure HSM Key Management - CyberArk PAM
sidebar_label: Configuring PKCS#11
description: Configure the Primus PKCS#11 provider on CyberArk PAM Vault with HSM integration, including setup details, user credentials, and configuration examples.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Configuring PKCS#11 for CyberArk PAM
### HSM integration guide

Configure the Primus PKCS#11 provider by adapting the configuration file **`primus.cfg`** according to your setup.
- Follow the instructions in **[Primus PKCS#11 Provider User Guide - Configuration](https://docs.securosys.ch/pkcs/Installation/pkcs11_provider_configuration).**

:::info
For the configuration of the Primus PKCS#11 Provider installed on the CyberArk PAM obtain the credentials from your HSM administrator or CloudHSM Support:
`user_name`, `host` URL/IP address of all HSMs, and additionally for CloudHSM `proxy_user`.
:::

:::note
If network hardening is already configured on the host device, please see [Configuring Primus HSM on CyberArk Primary Vault](/cyberark/Installation/Configuring-HSM-on-primary-Vault.md) on how to enable an outgoing connection to the Securosys HSM.
:::

## Example Configuration

The following example shows the configuration file **`primus.cfg`** in **`C:\Program Files\Securosys\Primus P11\primus.cfg`** on Windows Server 2019 platform for a redundant partition named **`DEMO_PARTITION`** residing on the CloudHSM service with the proxy user name **`SERVICE_USER`**. CloudHSM has geo-redundant active sites, thus two HSM partitions are defined in the configuration. In our example, **`hsm0`** prevails in priority over **`hsm1`**.

The **`SERVICE_USER`** parameter depends on your CloudHSM details.

```js
#-----------------------------
# Primus PKCS#11 configuration
#-----------------------------
version = "1.0";

/*--- GLOBAL CONFIGURATION SECTION ----------------------------------------*/
primus:
{
  wait_delay = 250; /* in ms*/
  wait_max_tries = 5;
  
  /*--- HSM CONFIGURATION SECTION -----------------------------------------*/
  hsms:
  {
     hsm0:
     {
      // highlight-next-line-info
        host = "a-api.cloudshsm.com";
         // highlight-next-line-info
        port = "2310";
        priority = 0; /* Optional priority. Default 0 = highest priority */
        slots:
        {
           slot0:
           {
             // highlight-info-start
             client_id  = "CyberArk_Server";
             user_name  = "DEMO_PARTITION";
             proxy_user = "SERVICE_USER";            /* if proxy in use */
             id = 0;
            // highlight-info-end
           }; /* end slot0 */   
        }; /* end slots */
     }; /* end hsm0 */
    
     hsm1:
     {
        // highlight-info-start    
        host = "b-api.cloudshsm.com";
        port = "2310";
        // highlight-info-end
        priority = 1; /* Optional priority. Default 0 = highest priority */
        slots:
        {
           slot0:
           {
             // highlight-info-start
             client_id  = "CyberArk_Server";
             user_name  = "DEMO_PARTITION";
             proxy_user = "SERVICE_USER";            /* if proxy in use */
             id = 0;
             // highlight-info-end
           }; /* end slot0 */   
        }; /* end slots */
     }; /* end hsm1 */
  }; /* end hsms */
  
  /*--- LOG CONFIGURATION SECTION -----------------------------------------*/
  log:
  {
    file = "%PUBLIC%\Securosys\Primus P11\primus.log";  /* for windows */
    trace_linenumber    = false;     /* true or false */
    trace_timestamp     = true;     /* true or false */ 
    trace_function      = true;     /* true or false */
    trace_inout         = false;    /* true or false */
    trace_pid           = true;     /* true or false */ 
    trace_filename      = false;    /* true or false */
    trace_mask          = 0x00;
    trace_level         = 4;        /* 0-7 log level details */
  }; /* end log */
}; /* end primus */
```

For configuration value details or variants consult [Primus PKCS#11 Provider User Guide -  Configuration](/pkcs/Installation/pkcs11_provider_configuration).

