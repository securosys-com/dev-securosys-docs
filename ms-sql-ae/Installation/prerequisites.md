---
sidebar_position: 0
title: Prerequisites for Microsoft SQL AE & HSM
sidebar_label: 1. Prerequisites
description: Prerequisites for Microsoft SQL Always Encrypted with Securosys Hardware Security Modules (HSMs)
keywords: [cloud hsm, hsm key management, hsm cloud]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites

Before starting the process of integrating the Securosys HSMs with Microsoft SQL Server - Always Encrypted,  please ensure the following is met on a client machine with MS SQL installed, hosting your client applications:
  - Windows operating system with Crypto API Next Generation (CNG) (Server 2016 or higher, Windows 10 or higher).
  - Always Encrypted-enabled driver
    - Microsoft .NET Framework 4.6.2 RC or later (e.g. for applications developed with Microsoft Visual Studio)
    - JDBC 7 or later
    - ODBC 13.1 or later
  - Securosys Primus CNG/KSP Provider installed and configured.


Always Encrypted imposes some restrictions. For details consult the [Microsoft Always Encrypted](https://docs.microsoft.com/en-us/sql/relational-databases/security/encryption/always-encrypted-database-engine) documentation. 

## Microsoft SQL Installation

This guide assumes that Microsoft SQL Server is already installed and configured. The following prerequisites must be met for the MS SQL installation:

- Either Microsoft SQL Server 2016 SP1 (all editions) or later is installed and configured. See [Microsoft SQL documentation - Operating System Support](https://learn.microsoft.com/en-us/sql/sql-server/install/hardware-and-software-requirements-for-installing-sql-server?view=sql-server-ver16#operating-system-support-for-sql-server-2016) for your SQL server edition,
  - or Microsoft SQL Server 2016 Enterprise Edition,
  - or Azure SQL Database V12.
- A database has to be created and preconfigured beforehand


## HSM Setup and Configuration

<Tabs groupId="device-setup">
  <TabItem value="ui" label="Cloud" default>
      Securosys CloudHSM allows almost instant HSM operation by selecting and contracting the different services and options.
For available service packages and options consult our website [Securosys CloudHSM Service](https://www.securosys.com/en/product/cloudshsm) and contact Securosys sales. 

:::note
Ensure the CNG API is included and activated in your subscription.
:::

  </TabItem>
  <TabItem value="cli" label="On-premises">
      For general on-premises Primus HSM hardware, HA Cluster setup and operation in FIPS or Common Criteria certified modes, refer to the corresponding [Primus HSM User Guide](https://support.securosys.com/external/knowledge-base/article/63) for details.
    
:::note
Ensure the CNG API is licensed and activated on your HSM device.
:::
  </TabItem>
</Tabs>


