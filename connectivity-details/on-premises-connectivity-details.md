---
sidebar_position: 2
title: HSM Cluster Hostnames & TCP Ports
sidebar_label: On-premises HSM
description: Discover on-premise HSM cluster hostnames and TCP ports for secure integration. Explore configurations including Primus HSM, Decanus Terminal, and Transaction Security Broker.
keywords: [hsm clusters, hsm hostnames, hsm tcp ports, hsm key management, hsm as a service, hsm based hsm, hsm digital signature, hsm services, hsm service, what is hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, code signing, encryption key management, hardware security module, hsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws hsm documentation, hsm pricing]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# On-premises Hardware Security Modules

On-premises HSMs connectivity details showcase general details for:
- [Primus HSM](#securosys-primus-hsm) X or E or CyberVault series
- [Transaction Security Broker (TSB)](#transaction-security-broker)

## Securosys Primus HSM

To setup and configure the Primus HSM hardware, please refer to the [Primus HSM User Guides](https://support.securosys.com/external/knowledge-base/article/63)(account required).

:::info Required API licences
Ensure the APIs to be used are included in your HSM license. For license upgrades please contact [Securosys](https://www.securosys.com/en/contactus).
:::

### Default Configuration

The on-premises Primus HSM can be reached through the default ports (listed in the table below) unless they have been configured differently by your HSM administrator. 

For more details, pelase refer to the [Primus HSM User Guides](https://support.securosys.com/external/knowledge-base/article/63)(account required).


| HSM URL/IP | TCP Port<br/> JCE/JCA | TCP Port<br/> PKCS#11 | TCP Port<br/> MS CNG | TCP Port<br/> [High Availability](#high-availability) | TCP Port <br/> [Decanus](#decanus-terminal)|[Partition Decanus](#decanus-terminal)|
|---|---|---|---|---|---|---|
|<center> Provided by your HSM administrator. </center>| <center>2300</center> | <center>2310</center> | <center>2320</center> |<center>2330</center> | <center>2340</center>|<center>If enabled uses JCE, PKSC11, MSCNG ports.</center>|

:::note JCE API port
The Transaction Security Broker (TSB) and REST API are using the JCE API port.
:::


### Setup Password & Permanent Secret

To establish a valid connection to the HSM, an application will require a valid setup password, which can be issued as follows:
<Tabs groupId="device-setup">
  <TabItem value="ui" label="Primus HSM User Interface and Decanus" default>
  ``` 	
    ROLES → USER → NEW SETUP PASSWORD
  ```
</TabItem>
  <TabItem value="console" label="Primus HSM Console" default>  
```bash
hsm_sec_new_setup_pass
```
  </TabItem>
</Tabs>

:::warning
The setup password has limited time validity and should be used to obtain or update a permanent secret as soon as possible, not as a permanent solution.
:::

As the Setup password will expire (by default in 72 hours), you should fetch the permanent secret. See the respective documentation for each API on how fetch the permanent secret:

- [Java Cryptography Extension (JCE) - Login Sample](/jce/Tutorials/LoginSample), 
- [PKCS#11 (Crpytoki) - Permanent Secret Fetching](/pkcs/Installation/permanent_secret_management),
  - PKCS#11 password (configured by your HSM administrator) is required to fetch the permanent secret, see [PKCS#11 (Crpytoki) - Preparing the PKCS#11 Password (PIN)](/pkcs/Installation/primus_hsm_settings#preparing-the-pkcs11-password-pin) for more details.
- [MSCNG - Configuring CNG/KSP Provider](/mscng/Installation/Configuring).

### Decanus Terminal

Decanus is the tamper-protected remote administration terminal for the Primus HSM.

:::warning
The Decanus Terminal must be enabled in the HSM configuration before use. The Decanus Terminal must be paired initially with the HSM, to establish a secure connection.
:::

Decanus may comprise different firmware variants and applications, e.g.:

- Primus HSM Device Administration
    - Enabling remote administration of up to 64 Primus HSM devices, by extending the user interface, card slots, and USB slot in a secure manner.
    - Connects over an IP network to the configured HSM management interface and TCP port, see [Default Configuration](#default-configuration) for default values.

- Primus HSM Partition Administration and Auditing
    - Enabling remote administration and audit of up to 64 single Primus HSM partitions (Partition SO)
    - Connects to one of the configured Primus HSM API interfaces and port (on HA Master device), see [Default Configuration](#default-configuration) for default values.

For more details refer to [Decanus Terminal User Guide](https://support.securosys.com/external/knowledge-base/article/71), downloadable from the [Securosys Support Portal](https://support.securosys.com/external).


### High Availability 

:::note
High availability is configured by HSM administrators and requires multiple Primus HSM devices.
:::

Devices of a cluster, for which the high-availability option “HA” is enabled, are synchronized in a timely manner to ensure load balancing without the need for manual cloning each time a user key or object is generated or modified.

By default, a Clone tries to establish a connection with the Master using the configured Master URLs and tries to synchronize with the Master. After pairing, these devices will synchronize themselves via Ethernet as long as they are able to connect to the network.

For more details on High Availability, please refer to the [Primus User Guide - High-Availability Remote Cloning](https://support.securosys.com/external/knowledge-base/article/63) (account required).

## Transaction Security Broker

Connectivity details for on-premises [Transaction Security Broker (TSB)](/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker) with different deployment versions of Securosys Hardware Security Modules (HSMs).

| TSB Service | Description | Authentication | Endpoint(s) |
|---|---|---|---|
| HSMaaS | **HSMaaS** with onPremise TSB-Deployment, **`hsm.host=HSMaaS-Hostname`** and **`hsm.port=2300`** _default (JCE/JCA) Port_ | any | [HSMaaS - Hostname(s)](/connectivity-details/cloudhsm-connectivity-details) |
| Dedicated **(Platinum)** | TSB bound to CloudHSM PLA partition | mutualTLS | _dedicated_ domain-name as `<dedicated>.cloudshsm.com` |
| OnPremise (HSM) | **`hsm.host=<IP>`** _of HSM (Data-Interface)_, <br /> **`hsm.port=2300`** _default (JCE/JCA) Port_ | any | http://localhost:8080 |

## You might be interested in

- [Java Cryptography Extension (JCE)](/jce/overview)
- [Microsoft CNG](/mscng/overview)
- [Transaction Security Broker (TSB)](/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker)