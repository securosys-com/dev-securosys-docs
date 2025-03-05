---
sidebar_position: 1
title: Getting started with HC Vault Enterprise 
sidebar_label: Quickstart
description: Getting started with HC Vault Enterprise and HSM
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Getting started with HC Vault Enterprise

The quickstart section provides a comprehensive guide outlining the steps necessary to integrate **HashiCorp Vault Enterprise** with your Securosys [CloudHSM](../../cloudhsm/overview) or on-premises Primus HSM.

## 1. Install the Primus PKCS#11 Provider

Install and configure the latest version of Primus PKCS#11 provider on the device with **HashiCorp Vault Enterprise** already installed.
Check for connectivity with your Hardware Security Module (HSM).
Follow the instructions in [Primus HSM PKCS#11 Provider](/hc-vault-enterprise/Installation/provider-installation) for more details.

## 2. Configure your Hardware Security Module (HSM)

Migrate to Auto Unseal on **HashiCorp Vault Enterprise** using on-premises Primus HSM or [CloudHSM](../../cloudhsm/overview). 

Follow the instructions provided [in this section](/hc-vault-enterprise/Installation/installation.md).

## 3. Enable Further Functionalities

Consult the **HashiCorp Vault** documentation for additional optional functionalities with on-premises Primus HSM or [CloudHSM](../../cloudhsm/overview), including:

- [Entropy Augmentation](https://learn.hashicorp.com/tutorials/vault/hsm-entropy?in=vault/enterprise)
- [Seal Wrapping](https://learn.hashicorp.com/tutorials/vault/seal-wrap?in=vault/enterprise)
- [Key Rotation](https://learn.hashicorp.com/tutorials/vault/rekeying-and-rotating)