---
sidebar_position: 1
title: Quickstart Guide - Cloud Console
sidebar_label: Quickstart Guide 🚀
description: Getting Started with Cloud Console and Securosys CloudHSM. Subscribe online and activate your HSM within 10 minutes.
keywords: [hsm clusters, hsm hostnames, primus hsm, securosys]
tags: [Cloud Console, CloudHSM]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Quickstart Guide

This one-page guide outlines the necessary steps to subscribe to a new [CloudHSM](/cloudhsm/overview/) service and activate the HSM partition within **10 minutes** ⏱. For additional details on each procedure, refer to the linked articles provided in each section.

**Prerequisites:**
* [ ] Basic knowledge of [CloudHSM](/cloudhsm/overview)
* [ ] A digital payment method: Credit Card, PayPal, Apple Pay, etc.
* [ ] Your legal entity information: company data, VAT number (optional)

### Step 1: Create a new tenant

[Create a new tenant](https://cloud.securosys.com/sign-up) following these steps:
1. Verify your email address
2. Choose a strong password
3. Complete your profile with legal & contact information

### Step 2: Create a new service

1. **Log** into [Cloud Console](https://cloud.securosys.com/login).
1. **Select** a service.
2. **Select** a cluster
3. **Click** on ```Checkout```.
4. Adjust the legal/VAT information
4. **Confirm** the subscription

For more information, see [Create a service](./Services/subscribeservice.md).

You will receive 2 emails:
- A confirmation email from MyCommerce with the subscription detail - This email can be forwarded for your finance department
- A confirmation email from Securosys with the PIN code for the HSM credentials - Do not forward this email

### Step 3: Activate your account for the Support Portal

Upon subscription, an account is pre-created for your for the [Support Portal](https://support.securosys.com) with the same email address.
- Choose a new password to activate the account.

:::tip Support & Maintenance included
All online services include support & maintenance. The [Support Portal](https://support.securosys.com) is the place to go to receive assistance and access downloadable resources. 
:::

### Step 4: Create an HSM partition

Upon subscription, the HSM partition is immediatly created. You will receive several emails:
- A confirmation email from MyCommerce with the invoice

### Step 5: Download the HSM credentials

Upon subscription, your HSM credentials are immediatly available in Cloud Console > MyServices

- Go to [Cloud Console > MyServices](https://cloud.securosys.com/my-services) - Your service appears at the top of the list
- Once the status of the service appears "available", a **Download** button appears in the last column
- In your mailbox, **search** for the email from Securosys with a one-time PIN code.
- **Copy** your one-time PIN code
- In [Cloud Console > MyServices](https://cloud.securosys.com/my-services), **click** on Download and **paste** the PIN code to download the HSM credentials (.txt format)

### Step 6: Install the Primus API provider

The **Primus API Provider** allows you app to connect with the HSM.

Use your account from [Step 4](./quickstartguide#step-3-activate-your-account-for-the-support-portal) to download and install
- [REST API](/tsb/Download/downloads)
- [Java Cryptography Extension](/jce/Downloads)
- [Microsoft CNG](/mscng/downloads)
- [PKCS#11 (Cryptoki)](/pkcs/downloads)

### Step 7: Initialize the service partition

Retrieve permanent secret

### Step 8: Connect to your application (optional)

If your CloudHSM service is meant to be used with other applications such as [AWS BYOK](/aws-byok/overview), [CyberArk PAM](/cyberark/overview), [HashiCorp Vault](/hc-vault-enterprise/overview) or others, follow the instructions of each application provided by email or in the [Use Cases](/integrations/).
