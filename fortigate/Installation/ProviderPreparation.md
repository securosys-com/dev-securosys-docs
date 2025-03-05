---
sidebar_position: 1
title: Configuring PKCS#11 for FortiGate & Security HSM
sidebar_label: 2. API Provider
description: Prerequisites for Fortinet FortiGate Next Gen Firewall (NGFW) with Securosys Hardware Security Modules (HSMs).
keywords: [fortinet, fortigate, firewall, hsm clusters, integration, primus hsm, securosys]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Configuring the PKCS#11 Provider

The Securosys PKCS#11 provider v2.2.4 or later is already integrated into FortiGate (no installation needed). 

However a valid configuration file and secrets have to be prepared and tested in advance, to be loaded then to the FortiGate via CLI or GUI.

Therefore the PKCS#11 provider installation, configuration and connectivity setup including permanent secret retrieval has to be prepared on a separate client machine (preferably Linux) before the configuration and secrets can be loaded to FortiGate. 

:::warning
The FortiGate assumes that the configuration file primus.cfg and secrets are consistent and correct! 
:::

## PKCS#11 Provider Installation on Client Machine
Install the PKCS#11 provider on a Linux machine according [pkcs#11 Provider Installation Guide](/pkcs/Installation/pkcs11_provider_installation)
- For FortiGate with 
  - FortiOS v7.2.8 Special Build 9127 and newer install the Securosys PKCS#11 Provider v2.2.4 or newer


## Prepare Provider Configuration

Currently, the FortiGate implementation has the following configuration restrictions: 
- Logging must be disabled (write_log_file = false; write_syslog = false;)
- Establish all connections on initialization for latency optimization (connect_on_init = true;)
- **DNS is currently not supported** in the configuration file (*host = "a.b.c.d"*), use IP addresses instead

Configure the file /etc/primus.cfg with your HSM connectivity parameters. <br/>
For details consult [pkcs#11 Provider Configuration Guide](/pkcs/Installation/pkcs11_provider_configuration).

1) Adjust the global and log configuration section according to the highlighted lines:

```text
…
/*--- GLOBAL CONFIGURATION SECTION ----------------------------------------*/
primus:
{
  wait_delay = 250; /* in ms*/
  wait_max_tries = 5;
// highlight-start
  connect_on_init = true;
// highlight-end
  
  /*--- HSM CONFIGURATION SECTION -----------------------------------------*/
…
  /*--- LOG CONFIGURATION SECTION -----------------------------------------*/
  log:
  {
// highlight-start    
    file                = "/tmp/primus.log"; 
    write_log_file      = false;
    write_syslog        = false;
// highlight-end    
    trace_linenumber    = false;
    trace_timestamp     = true;
    trace_function      = true;
    trace_inout         = false;
    trace_pid           = true;
    trace_filename      = false;
// highlight-start    
    trace_mask          = 0x00;
    trace_level         = 0; /* 0-7 log level details */
// highlight-end
  }; /* end log */
```

## Retrieve the Permanent Secret
Retrieve the blinded permanent secret with ppin tool: <br/>
For details see [Permanent Secret Management](/pkcs/Installation/permanent_secret_management)
```bash
ppin -ae <HSM_USERNAME> <setupPassword> <PKCS11Password>
```
In case of failure see the section [Troubleshooting](/pkcs/Tutorials/troubleshooting)

In case of failure, see the [troubleshooting section](/pkcs/Tutorials/troubleshooting).

## Testing the Cluster Connectivity

1) Ensure propere testing of the connectivity to all clustered devices with the ppin tool:
```bash
ppin -t
```
```text
Load config file: '/etc/primus/primus.cfg'

hsm0: Connect to '82.197.162.10' on port 2410, firmware: RX-3.1.0-T 
    slot0 (id=0), user=PRIMUSDEV368: OK

hsm1: Connect to '82.197.162.10' on port 2411, firmware: RP-3.1.0-T 
    slot0 (id=0), user=PRIMUSDEV368: OK

Number of tested HSMs: 2 (number of partitions: 2)

// highlight-start
Number of failures: 0
// highlight-end
```
Continue with **[step 3: FortiGate Configuration](./FortiGate_Configuration.md)**.