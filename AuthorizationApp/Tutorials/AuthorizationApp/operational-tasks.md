---
sidebar_position: 0
title: Securosys Authorization App Operational Tasks
sidebar_label: Operational Tasks
description: Securosys Authorization App is a self managed applition offering autorization capabilities for the Approver.
keywords: [cloud hsm, hsm key management, hsm cloud]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Operational Tasks

After a successful registration, the Operational Tasks List is shown as the main page. In this menu, the approver will have a list of operational tasks to approve or cancel.

![ImageFloat_Right](../../img/new_operational_task_list.png)

## Operational Task List

The Operational Task List shows a list of tasks in queue to be approved or denied by the approver user. Tasks are fetched every 15 minutes and notified in the standard notification bar. When an approval task is fetched, it appears in the operational task list which includes:
- Sign Approval
- Encrypt Approval
- Decrypt Approval
- Wrap Approval
- Unwrap Approval
- Generate Self-Signed Certificate Approval
- Sign a Certificate Request

Click on the a **task** to proceed to the **[Operational Task](#operational-task-menu)** menu.

<br/><br/><br/><br/><br/><br/><br/><br/>

![ImageFloat_Right](../../img/new_sign_metadata_tab.png)

## Operational Task Menu 


When opening the desired operational task, the **META DATA** tab will open at first. On the **task menu**, there are two different tabs: **META DATA** and **DETAILS**. You can leave the task menu by clicking the ![](../../img/close-circle-outline.jpg) **button** in the top right corner.

### Metadata Tab
The **META DATA** tab shows the metadata - the description of the authorization task - which was added to the task.  The metadata is specified by the [Business Application](/AuthorizationApp/Concepts/role_description), specifiying the meta-data in `.json` format.  If no metadata is provided with the task, the tab will be grayed out.

<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

![ImageFloat_Right](../../img/new_sign_details_tab.png)

### Details Tab

Clicking on the **DETAILS** tab will show the details provided with the operation task. Depending on the detail level, details about the operation task are shown. By default the [standard setting](/AuthorizationApp/Installation/Settings#details-level-setting) is chosen and will show the `payload`, `payloadType`, `signKeyName`, `signatureAlgorithm`, `signatureType` and more operational task details. 

With the `With Key Attributes` [Details Level Setting](/AuthorizationApp/Installation/Settings#details-level-setting) enabled, the key in the operational task details, will have a ![](../../img/eye-outline.jpg) **button** present. Clicking the ![](../../img/eye-outline.jpg) **button** will open the Key Attributes of the key used for the task Clicking in `xml` format. 

You can leave the Key Attributes details by clicking the ![](../../img/close-circle-outline.jpg) in the top right.

The detail level can be configured in the settings menu of the Securosys Authorization App. For more information visit the [Settings - Details Level Setting](/AuthorizationApp/Installation/Settings#details-level-setting) section. 


<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

## Approve or Cancel

After reviewing the Metadata and the Details of the operational task, the Approver can choose to either **Approve** or **Cancel** the operational task. To do so, follow the next steps:

1. Click on the ![](../../img/approve_button2.jpg) **button** to approve the operational task or on the ![](../../img/cancel_button2.jpg) **button** to cancel it. 
2. After clicking on either button, a confirmation window will open to confirm with the Approver of the choice. 

![](../../img/confirm_approval.jpg)

3. Select **Yes** to confirm or **No** to cancel the approval or denial of the operational task.

4. A successful approval or denial the App will prompt the Approver with a notification. 

![](../../img/confirm_approval_notification.png)

## Refresh or Fetch
Tasks are automatically fetched in the background every 15 minutes, even if the app is not open.

To manually refresh the operational task list, drag down the blank space in the middle of the **operational task list**. This will prompt a loading process which checks for all approval tasks. 

![](../../img/Loading_box_box.jpg)
