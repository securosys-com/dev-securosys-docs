---
sidebar_position: 98
title: Best practises - Cloud Console
sidebar_label: Best practises
description: Best practises in Cloud Console
keywords: [hsm clusters, hsm hostnames, primus hsm, securosys]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Best practises

This document provides best practices for managing user accounts in your [Cloud Console](https://cloud.securosys.com) tenant.

## Platform role password policy

Improper password management and implementation imposes risks to the security of Cloud Console tenants and services. To ensure the security and confidentiality of your Cloud Console user account, the password requirements for a Cloud Console role are:

- Must be a minimum of 10 characters
- Cannot exceed 255 characters

Cloud Console accounts are temporarily disabled after 10 consecutive failed password attempts. If your Cloud Console account is temporarily disabled retry the login at a later time.

To increase password protection, we recommend being aware of and adhering to the following password management practices:

- We recommend using a combination of alphanumeric and special characters in your password to increase resilience to brute force attacks.
- Do not use dictionary words inside of your password.
- Passwords must not be shared with anyone. Your password is to be treated as sensitive, confidential organizational information.
- Passwords must not be inserted into any forms of electronic communication.
- Passwords must not be revealed over the phone to anyone.
- Do not share Cloud Console passwords with anyone, including administrative assistance, secretaries, managers, co-workers while on vacation, and family members.
- Do not write passwords and store them anywhere in your office. Do not store passwords in a file on a computer system or mobile devices (phone, tablet) without encryption.
- Do not save your password into a web browser, use a password manager.
- Any user suspecting that their password may have been compromised should report the incident to their tenant administrator as soon as possible and change their password.
- The Securosys operations team will never ask you for your login details.

## Include listing platform communications

Cloud Console announcements and communications are made over:

- Securosys [Status Page](https://status.cloudshsm.com/)
- [Support Portal](https://support.securosys.com)
- Email

You can subscribe to the Securosys [Status Page](https://status.cloudshsm.com/), and the [Support Portal](https://support.securosys.com) to receive the latest updates and news about Cloud Console.

If you are not receiving emails from Securosys, please add the following addresses to your approved senders list:

- news@securosys.com for HSM & APIs release notifications
- notifications@securosys.com - account notifications and alerts
- support@securosys.com - support email address
