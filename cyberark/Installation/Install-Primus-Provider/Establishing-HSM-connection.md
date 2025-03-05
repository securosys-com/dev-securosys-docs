---
sidebar_position: 2
title: Connecting your HSM to CyberArk PAM
sidebar_label: Connect HSM
description: Establish HSM connectivity for CyberArk PAM using Primus PKCS#11, including steps for on-premises and CloudHSM setup, retrieving permanent secrets, and using the ppin tool.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Connecting HSM with CyberArk PAM

Open a command shell and utilize the command line tool **`ppin`** to:
* manage the connection credentials for secure HSM communication,
    * set the proxy password (only for CloudHSM),
    * fetch the permanent secret from HSM,
* test HSM and partition connectivity (assuming the permanent secret was already fetched),
* list the connected partitions
* retrieve the partition log from configured HSMs (if allowed by HSM configuration),
* show the installed provider version.

:::info
To connect the Primus PKCS#11 Provider installed on the CyberArk PAM with your HSM, on-premises or Cloud, obtain the credentials from your HSM administrator or CloudHSM Support:
- `pkcs11-password`
- `setup-password`
- and additionally for CloudHSM `proxy-password`.
:::

## HSM Connection Setup

<Tabs>
  <TabItem value="cloud" label="Cloud" default>
    Follow the instructions in the **[Primus PKCS#11 Provider User Guide - Setting up CloudHSM Access](/pkcs/Tutorials/CloudHSM/cloud_hsm_setup)** to fetch the permanent secret using the `ppin` tool and establish a connection with the HSM.
  </TabItem>
  <TabItem value="on-prem" label="On-premises">
        Follow the instructions in the **[Primus PKCS#11 Provider User Guide - Permanent Secrets Fetching](/pkcs/Installation/permanent_secret_management)** to setup the credentials to establish a connection with the HSM.
  </TabItem>
</Tabs>

## Example Configuration

Following our example, the connection establishment steps illustrate connecting to the partition named **`DEMO_PARTITION`** residing on the CloudHSM service with the proxy user name **`SERVICE_USER`**.

### Set Proxy Password

For CloudHSM, all connections undergo authentication on a proxy before being forwarded to the HSM. Therefore, the Service Proxy Password must be configured prior to retrieving the HSM permanent secret. Execute the below `ppin` tool command in a command shell.

:::tip
If you're setting up the connection with an on-premises Primus HSM, you can skip this step.
:::

```sh
ppin -p -e SERVICE_USER

********************
Primus Permanent PIN
********************
Provide proxy password for 'SERVICE_USER' : <enter Service Proxy Password, no echo>

********************
Primus Permanent PIN
********************
[01] slot-id 0:    user 'DEMO_PARTITION'    permanent secret: MISSING
[02] slot-id 0:    user 'DEMO_PARTITION'    permanent secret: MISSING
[01] service/proxy user 'SERVICE_USER' permanent secret: Configured
[02] service/proxy user 'SERVICE_USER' permanent secret: Configured
```

### Retrieving Permanent Secret

Retrieve the permanent secret for **`DEMO_PARTITION`** via the service proxy, by using the **`ppin`** tool with **User Setup password** and **PKCS#11 password**:

```sh
ppin -a -e DEMO_PARTITION

********************
Primus Permanent PIN
********************
Provide setup password for 'DEMO_PARTITION':  <enter User Setup Password, no echo>
Provide PKCS11 password for 'DEMO_PARTITION': <enter PKCS#11 PIN/Password, no echo>
********************
Primus Permanent PIN
********************
[01] slot-id 0:    user 'DEMO_PARTITION'    permanent secret: Configured
[02] slot-id 0:    user 'DEMO_PARTITION'    permanent secret: Configured
[01] service/proxy user 'SERVICE_USER' permanent secret: Configured
[02] service/proxy user 'SERVICE_USER' permanent secret: Configured
```