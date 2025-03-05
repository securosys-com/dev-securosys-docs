---
sidebar_position: 2
title: Getting Started with FortiGate & Security HSM
sidebar_label: Quickstart
description: Fortinet FortiGate Next Gen Firewall (NGFW) with Securosys Hardware Security Modules (HSMs).
keywords: [fortinet, fortigate, firewall, hsm clusters, integration, primus hsm, securosys]
---

# Getting Started with Fortinet FortiGate

The quickstart section provides a comprehensive guide outlining the steps necessary to integrate FortiGate with Securosys on-premises Primus HSM or [CloudHSM](/cloudhsm/overview/).

## 1. Prerequisites

Make sure to adhere to the [Prerequisites](/fortigate/Installation/prerequisites) before continuing with the procedure.

## 2. Installing the Primus PKCS#11 Provider

:::tip
The Securosys PKCS#11 provider v2.2.4 or later is already integrated into FortiGate (no installation needed). 

However a valid configuration file and secrets have to be prepared and tested in advance on a separate client machine, to be loaded then to the FortiGate via CLI or GUI. 
:::

1) Install and configure on a client PC the version of Primus PKCS#11 provider corresponding to the FortiGate integrated version.
2) Check for connectivity with your HSM.
3) Use the configuration file, PKCS#11 password and secret to be configured on the FortiGate.

More details on [Securosys PKCS#11 Provider Preparations](/fortigate/Installation/ProviderPreparation).

## 3. Configuring FortiGate with Securosys HSM

Configure the FortiGate firewall to use the on-premises Primus HSM or [CloudHSM](/cloudhsm/overview/) cluster. 

Follow the instructions provided in [FortiGate Configuration](/fortigate/Installation/FortiGate_Configuration).

## 4. Configuring additional Fortigate components

To configure additional components to use the HSM key (CA Certificate Generation, Certificate usage, WAD Deep Inspection in Explicit Proxy Policy, HTTPs Administrative Access), consult the [FortiGate documentation](https://docs.fortinet.com/). 
