---
sidebar_position: 0
title: Getting Started with Securden Unified PAM
sidebar_label: Quickstart
description: Getting started with Securden Unified Privileged Access Management (PAM) for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Get Started with Securden Unified PAM
This Quickstart section provides a comprehensive guide outlining the steps necessary to integrate Securden PAM with Securosys CloudHSM or on-premises Primus HSM.

Make sure to adhere to the prerequisites needed. For more details visit [Prerequisites](/securden-pam/Installation/prerequisites).

## 1. Primus HSM configuration

The HSM or CloudHSM must be configured to work with PKCS#11 API and restricted according to [Securden Unified PAM HSM Configuration](/securden-pam/Installation/hsm-config). 

## 2. Installing Primus PKCS#11 Provider
Download, install and configure the Primus PKCS#11 provider on the device(s) with Securden PAM installed.

To download and for more details please visit [PKCS#11 API](/pkcs/overview).


## 3. Configuring Securden PAM to use Primus HSM

Use the tool `ConfigureHSM.exe` in the Securden PAM `bin` folder to specify the HSM connection details. 

For more information consult the [Securden Unified PAM Administrator Guide](/securden-pam/Installation/secpam-integration.md).
