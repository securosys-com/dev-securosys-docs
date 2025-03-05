---
sidebar_position: 2
title: Approver Restoration with Authorization App
sidebar_label: Approver Restoration
description: A guide on how to restore an approver in case he lost his phone or it got stolen.
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Restoring an Approver

In cases where you no longer have access to your mobile phone either due to loss, breakage or other circumstances it's possible to restore (newly register) an existing Approver on a newly installed Securosys Authorization App.

Restoration of an Approver can be done on the same or a new mobile device.

The following procedures also apply when a device is fully operational but the Approver must be changed, see [Settings - New Registration](/AuthorizationApp/Installation/Settings#new-registration).

:::info Role Required
The restoration procedure is performed by an [Approver](/AuthorizationApp/Concepts/role_description) as well as an [Approver Manager](/AuthorizationApp/Concepts/role_description).
:::

## Backup Password is Known

Follow this procedure to restore the Approver when the `backup password` is still known.

1. Inform your Approver Manager of your loss of access and refer to Your company policies.
2. The Approver Manager will have to issue a new `oneTimeCode`. 
3. On a new phone, download and install the Securosys Authorization App. See [Installation](/AuthorizationApp/downloads) for more information and a step-by-step guide.
4. Register again with the new `oneTimeCode` & your `backupPassword` received from your Approver Manager.

:::note Approver Manager Required
Approver Manager, to issue a new `oneTimeCode`, please follow the prodedure described in [Rest-API - Restore from Backup](/tsb/Tutorials/TransactionSecurityBroker/PrimusAuthorizationApp/approver-mangement-api#renew-onetimecode).
:::

## Backup Password is Forgotten

Follow this procedure to restore the Approver when the `backup password` was forgotten.

1. Inform your Approver Manager of your loss of access and refer to your company policies.
2. The Approver Manager will have to issue a new `oneTimeCode` and a new `backupPassword`. 
3. Register again with the new `oneTimeCode` & the new `backupPassword` received from your Approver Manager.

:::note Approver Manager Required
Approver Manager, to issue a new `oneTimeCode` and `backupPassword`, please follow the prodedure described in [Rest-API - Forgot Password](/tsb/Tutorials/TransactionSecurityBroker/PrimusAuthorizationApp/approver-mangement-api#restore-from-backup-if-p12-password-forgotten).
:::