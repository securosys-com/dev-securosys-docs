---
sidebar_position: 1
title: Approver creation with the Authorization App
sidebar_label: 1. Create Approver
description: Smart Key Attributes enables a Multi-Authorization Workflow for your highly secure key-operations.
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Creating An Approver

In this article, we provide a comprehensive guide on using the [Rest-API Approver Management API](/tsb/Tutorials/TransactionSecurityBroker/PrimusAuthorizationApp/approver-mangement-api) to create approvers and prepare for a successful user onboarding with the _Securosys Authorization App_ on your mobile device.

## Create Approver-Key

The **Approver Manager** initiates the creation of `approver-key(s)` using the Rest-API by following the steps below (either swagger or by CURL).

:::note GUI

Currently, there is no user interface available for creating or managing Approver Keys. For now, please use CURL or Swagger for these tasks.

:::

**POST**: [/v1/approverManagement/create](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Approver%20Management/create)

<Tabs groupId="device-setup">
  <TabItem value="swagger" label="Swagger">
    ```js
{
  "approverName": "finance-officer@securosys.com",
  "algorithm": "RSA",
  "keySize": 2048,
  "backupPassword": "6se1Qbsi3bJshe",
  "validity": 3650
}
```

<details>
    <summary>Parameter Description</summary>
|Parameter|Description|
|---|---|
| **approverName**  | The email address of the Approver, e.g., `officer1@securosys.com`.|
| **algorithm**     | The key algorithm to be used by the Approver. Supported algorithms: **RSA**.|
| **keySize**       | The size of the Approver's key. Supported sizes: **2048, 3072, 4096** bits.|
| **backupPassword**| The password for the P12 container, used during registration and restoration processes. This password must be sent to the Approver (Mobile Application User) and should be kept securely. It is required if the Mobile User needs to re-register the Approver (e.g., due to a phone change or loss). |
| **validity**      | The validity period of the Approver's certificate, in days. Certificates are generated on the fly. For example, **3650** days equals **10 years**.|
</details>

**Response:** Response is a `oneTimeCode` to be sent to the **approver** to fetch the approver-key
```js
{
  "oneTimeCode": "410447"
}
```
  </TabItem>
  <TabItem value="curl" label="CURL" default>
```js
curl -X PUT  -H "Content-Type: application/json" \
 https://tsb-demo.cloudshsm.com/v1/approverManagement/create -d \
'{
  "approverName": "officer1@securosys.com",
  "algorithm": "RSA",
  "keySize": 2048,
  "backupPassword": "6se1Qbsi3bJshe",
  "validity": 3650
}'
```

<details>
    <summary>Parameter Description</summary>
|Parameter|Description|
|---|---|
| **approverName**  | The email address of the Approver, e.g., `officer1@securosys.com`.|
| **algorithm**     | The key algorithm to be used by the Approver. Supported algorithms: **RSA**.|
| **keySize**       | The size of the Approver's key. Supported sizes: **2048, 3072, 4096** bits.|
| **backupPassword**| The password for the P12 container, used during registration and restoration processes. This password must be sent to the Approver (Mobile Application User) and should be kept securely. It is required if the Mobile User needs to re-register the Approver (e.g., due to a phone change or loss). |
| **validity**      | The validity period of the Approver's certificate, in days. Certificates are generated on the fly. For example, **3650** days equals **10 years**.|
</details>

**Response:** Response is a `oneTimeCode` to be sent to the **approver** to fetch the approver-key
```js
{
  "oneTimeCode": "410447"
}
```

:::note API-KEY
In case of using API-KEY's add the following header to the CURL-Command: `-H "X-API-KEY: tsb-x-token_07...`"
:::
    </TabItem>
</Tabs>


To register a user within the Securosys Authorization App, the following details must be sent to the Approver:
- `oneTimeCode`, 
- `backupPassword`,
- `REST API URL`, and
- `approverName`

:::tip One-Time-Code

The `oneTimeCode` can only be used once. If re-registration is required, follow this guide [Restore Approver](/AuthorizationApp/Tutorials/ApproverManagment/Miscellaneous/restore_approver).

:::


# Whats next?
- [Register Approver on the Securosys Authorization App](/AuthorizationApp/Installation/auth-user-register)
- [Verify Onboarding Status](/AuthorizationApp/Tutorials/ApproverManagment/verify_onboarding_status)
- [Create a Policy based Key](/AuthorizationApp/Tutorials/create-and-approve-requests.md) that specifies which approvers need to sign off on operations using this key.