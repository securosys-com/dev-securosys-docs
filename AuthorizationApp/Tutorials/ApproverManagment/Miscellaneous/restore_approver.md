---
sidebar_position: 1
title: Restoring an Approver with Authorization App 
sidebar_label: Restore Approver
description: A guide on how to restore an approver in case he lost his phone or it got stolen.
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Restoring An Approver

If you have changed your mobile phone, reinstalled the Authorization App or the phone got lost, you can easily restore (re-register) the Approver on your new device.

### If Backup Password is known

1. Your Approver Manager should be informed of your loss.
2. The Approver Manager will issue a new `oneTimeCode`, please follow this guide: [Rest-API restore from backup](/tsb/Tutorials/TransactionSecurityBroker/PrimusAuthorizationApp/approver-mangement-api#renew-onetimecode)
3. On a new phone, download and install the Securosys Authorization App. See [Installation](/AuthorizationApp/downloads) for more information.
4. Register again with the new `oneTimeCode` & your`backupPassword` received from your Approver Manager.

please read [Restore Approver](/tsb/Tutorials/TransactionSecurityBroker/PrimusAuthorizationApp/approver-mangement-api#restore-from-backup), useful when the Approver still knows their backup password.

### If Backup Password is unknown (forgot password)

1. Your Approver Manager should be informed of your loss.
2. The Approver Manager will issue a new `oneTimeCode` and a new `backupPassword`, please follow this guide: [Rest-API forgot password](/tsb/Tutorials/TransactionSecurityBroker/PrimusAuthorizationApp/approver-mangement-api#restore-from-backup-if-p12-password-forgotten)
4. Register again with the new `oneTimeCode` & the new `backupPassword` received from your Approver Manager.
