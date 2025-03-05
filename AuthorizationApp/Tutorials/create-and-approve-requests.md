---
sidebar_position: 3
title: Creating requests with the Authorization App
sidebar_label: 3. Create and Approve Requests
description: This page is about creating a Smart Key Attributes Task, which has to be signed by approvers.
---

# Create and Approve Requests

## 1. Create Policy Based Key

:::info Business Application Required
The key creation procedure is performed by the [Business Application](/AuthorizationApp/Concepts/role_description) role.
:::

- [Create a Policy based Key](/tsb/Tutorials/TransactionSecurityBroker/samples/step-by-step/create-policy-based-key) that requires approvals from the Securosys Authorization App. 

## 2. Create the Authorization Request

:::info Business Application Required
The request creation procedure is performed by the [Business Application](/AuthorizationApp/Concepts/role_description) role.
:::

- [Authorization Request Samples](/tsb/Tutorials/TransactionSecurityBroker/samples/multi-authorization-samples), which has to be approved by the Approver based on the policy of the key.



## 3. Approve the Sign-Request

:::info Approver Required
The approval procedure is performed by an [Approver](/AuthorizationApp/Concepts/role_description).
:::

The **Securosys Authorization App** user `officer1@securosys.com` must approve the signature request. See [Tutorials - Authorization App - Operational Tasks](/AuthorizationApp/Tutorials/AuthorizationApp/operational-tasks).

## 4. Fetch the Approved Signature

:::info Business Application Required
The result fetching procedure is performed by the [Business Application](/AuthorizationApp/Concepts/role_description) role.
:::

After the required approvals are completed, you can retrieve the signed data. This step involves using the unique request ID from the initial sign request to obtain the status and the resulting signature. The signature will only be available if the quorum has been met and the request status is marked as `EXECUTED`.

:::tip Fetch Approval Task

Replace the `{id}` with the id returned in the first request, e.g. `4e8731d8-0ae6-4444-8a8c-b73ab5f0ba18`

:::


**POST**: [`/v1/request/{id}`](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Requests/getRequestStatus_1)


**Response** Status: 200

```js {10}
{
  "id": "4e8731d8-0ae6-4444-8a8c-b73ab5f0ba18",
  "status": "EXECUTED",
  "executionTime": "2020-06-24T08:54:47Z",
  "approvedBy": [
    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArA0bxSqhL7xfvcHbKKa8wMTMsIeJfYRdIgPxp5cU9JcmV86kyfpyRcSNSi44LVeNmAi94F3OZrXXi6CZvWrFL+VcewUtUSu+kG5oLJ9T4O6R2I5GO2Ev1HJnK3WfHOsFKFxLGzmKyjEkSLGgopY+Nh74K8Q6yxsvQPETOs9TzQiUXFYlfEZnbjUWG4eAgW9WWEopmK/X295ToOuTHFzmzO00btkjAy6vwWOabCE4kaJg+bCNW1snZz84uonr60rB9H0Mj98RbTfbDyMh6cIkaj8WrXeaYh4fxQYXApYu3nzhe3Q1bNCzV5M68rCsgVrmWcK/xUhM9BK6QHSwS/l76wIDAQAB"
  ],
  "notYetApprovedBy": [],
  "rejectedBy": [],
  "result": "H3bq8PdgSiAlhB0kt1RSD6a3JYXZoj/dz3Nb/MHlgISnmh6x3TPtnV+9mUnw8PV2Ss1pq8txdMOBg9SF8uaKyUvFtLl/QFHIgllm/Q/uvrjaM205Cdz1uaSLePXaNXeC012l1sqlhnyqGKxKTKejMngzNHAnOpwU7kGCEpKPFWL5ltaBRYd3Q/I/F9IufAsKHj+3ky/p6tYN5VPhJSKiSE2YuVyOzGPHY40ipVo/7deBtUUjZmaZAgVQNC1mC79LOhIHLj8Ce4i41CbFZA+ZSMe+nx5bP/7uPA+kbGAjvNS3KLOFeZJ2OJCkTuThsXVh7rTp9tVqYqHU1LZm8f9bYA=="
}
```

<Icon icon="fa-solid fa-triangle-exclamation" size="lg"/> If the quorum has been filled the request status is `EXECUTED` and the `result` contains the actual signature <Icon icon="fa-solid fa-triangle-exclamation" size="lg" />

