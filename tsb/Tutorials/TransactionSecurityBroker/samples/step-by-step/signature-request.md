---
sidebar_position: 2
title: Create a Authorized Signature Request
sidebar_label: 2. Create a Authorized Signature-Request
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';


### Issue a Signature Request

:::tip Create RSA Approval Key

This example shows how to create a signature request that requires approval based on the key policy rule `ruleUse.` Since the key is an SKA key (a key with a policy), the request isn't executed immediately and must be **approved** by the designated approver `approverx.`
:::

**POST** [/v1/sign](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Requests/sign)

<Tabs groupId="key-provider">
  <TabItem value="Commented" label="Commented" default>
```js
{
  "signRequest": {
    "payload": "QXBwcm92ZVNpZ25UYXNrUGF5bG9hZA==",     # base64 encoded payload
    "payloadType": "UNSPECIFIED",
    "signKeyName": "TSB_TUTORIAL_1-RSA", # adjust the keyname to your previously created key defined as `label` in POST: /v1/key
    "signatureAlgorithm": "SHA256_WITH_RSA"
  }
}
```
  </TabItem>
    <TabItem value="Un-commented" label="Un-commented" default>
```js
{
  "signRequest": {
    "payload": "QXBwcm92ZVNpZ25UYXNrUGF5bG9hZA==",
    "payloadType": "UNSPECIFIED",
    "signKeyName": "TSB_TUTORIAL_1-RSA",
    "signatureAlgorithm": "SHA256_WITH_RSA"
  }
}
```
  </TabItem>
</Tabs>

**Response:** Status: 201

:::warning Interpreting the response

The response is not the actual signature, rather an **uuid** you will use in the final step, once the quorum is met and all approvals have been given.
The uuid should be stored in the business app.
:::
```js
{
  "signRequestId": "4e8731d8-0ae6-4444-8a8c-b73ab5f0ba18"    
}
```

### Fetch an approval task

<Icon icon="fa-solid fa-triangle-exclamation" size="lg" /> To approve the original sign request, the approver has to fetch and solve a challenge first. <Icon icon="fa-solid fa-triangle-exclamation" size="lg" />

:::tip Fetch Approval Task

The script [Sign Timestamp](../../../../resources/ska/rsa/sign_timestamp_rsa.sh "sign_timestamp_rsa.sh")  signs the current Timestamp of the system `"timestamp": "2023-10-30T17:30:24+00:00",` with the approvers private key and generates a request-body to be sent to TSB.

Run: `./sign_timestamp_rsa.sh approverx`

The output of the script should be sent to the endpoint: `/v1/filteredSignApprovalTask` to fetch the challenge<br />
:::

:::note Prove

In order to receive the challenge for a specific approver, one must **prove** that he possess the approver's private key. 
The prove is given by signing the _current timestamp_, doing this introduces a mitigation to prevent replay attacks.

:::

**POST** [/v1/filteredSignApprovalTask](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Approval%20Tasks/filterSignTasks)
```js
{
  "timestamp": "2023-10-30T17:30:24+00:00",
  "timestampSignature": "VuZ0f/VEM2iq0zGCAVFTgWWD5zLJGCtUs/Eh3ecz4lYXSU42TwTWjwL6EQ4SHXf2gYZoci5q5nwswxkd0i4gvQ5fKd3lM8RzleDUziWqicrak65guIKynYuOvvYE29yqkwQ20N4FTKHAoseokIch8ByhxTeURFAinWHJZ9Rhf9ShojGn5f5/sP6G8C7WHMOaOB7f5e+FrjRfQs660rVaVjQU6XKmRrtKHsQQcMTz2Npm4/ikBolVSUXV4ZWUofA8ZR3+/aBsINw/CALnNIp+9mU6WEbneRz6nK1/LWV/VMT8A9J4gEo8GIGGSCFWlbq2P6DxTxLSHByEiNelYxOkqA==",
  "approverCertificate": "MIIDUTCCAjmgAwIBAgIUVyyrH8t8ldAUzHaYAyavN7mK6lMwDQYJKoZIhvcNAQELBQAwODELMAkGA1UEBhMCQ0gxDTALBgNVBAoMBFRlc3QxGjAYBgNVBAMMEVNLQSBUZXN0IDIwMjMgUlNBMB4XDTIzMTAzMDE1MjIxMFoXDTI0MTAyOTE1MjIxMFowODELMAkGA1UEBhMCQ0gxDTALBgNVBAoMBFRlc3QxGjAYBgNVBAMMEVNLQSBUZXN0IDIwMjMgUlNBMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuUy1tRrrO5xf6576qAJlLHX3HLGP7AKLfHW0TRGXfLP+ngFiSF4qAQdeU7rDtoY0QbjPazqFr8E9wuvjZX4B8xDEPPlECEO+7ixmeb0CX1HtZrES4b9CJuYunBl/ENNujnXH0pE9yw5/SiIxujISyNZmorDIurMsi7JYreq3ZOj8IisMDHVNfJKPV4QMOWKGtUfj/s7EdQ9H6pEvZcmy/Z9PyYWSCDcID4lrMg/FfdZRwvp9OcouypKOoDKf0pK2K0eYZQtnLZzHA+KI3xTjrpRFHtfufnKd9hozndp3OkZv8ZJFfjJ55RXL1K/ZqHSNSsmOxcGzzfNwI0+S/BjYtQIDAQABo1MwUTAdBgNVHQ4EFgQU261gJf0Ta1kqLevzofEU/vqbY9MwHwYDVR0jBBgwFoAU261gJf0Ta1kqLevzofEU/vqbY9MwDwYDVR0TAQH/BAUwAwEB/zANBgkqhkiG9w0BAQsFAAOCAQEAbjTisq3rwCcomv4BD2j0DFm+pS3uUDFgGkOci93XSq89rJaWw4mUcQDhdU8rXjm5hNT+XoPND80sv/LwnCVUAXmhGiwwjXNcIORje+5z/Wys+jmPfOrfrzOaoOQEFw5ZQY3SgSQaEN+pmQr4HhorGnlY8aWIQ5XEgx7scebRQI6xx8zeQID20IJTElHby5haCdtaAO9BR+E3U6NnPyr9gFDn0YAf/OfadpaI1beu5TBBj5k9e7+Yzidp+BboolWhvPLSoMlmrTlgwl6Tub/ZQdAelUD0sks5hqZbCjO1/FPIrDgRP57pUw3+EKbDwfpaIOdQGMjr4I/+nAPuWd5s1A==",
  "timestampDigestAlgorithm": "SHA-256",
  "detailLevel": "level1",
  "paging": {
    "pageNumber": 0,
    "pageSize": 1,
    "sortOrder": "CREATION_DATE_ASC"
  }
}
```

<details>
    <summary>Request Parameters</summary>
|Parameter|Description|
|---|---|
| **timestamp**                 |The ISO-8601 formatted timestamp that has been signed by the approval client.|
| **timestampSignature**        |The signature **(base64 encoded)** for the timestamp (ISO-8601) that was done using the key of the approver. Format of the signature is depending on the algorithm used and as returned when using a JDK's native Signature.sign() method. <br/><br/> `TIMESTAMP=$(date -u "+%Y-%m-%dT%H:%M:%S+00:00")` <br/> `SIGNATURE=$(echo -n $TIMESTAMP \| openssl dgst -sha256 -sign $PRIVATE_KEY_NAME \| openssl enc -base64 \| tr -d '\n')`|
| **approverCertificate**       | Certificate of the approver in DER format **(base64 encoded)**. Either the `approverPublicKey` or the `approverCertificate` has to be provided if the `timestampSigningCertificate` is not set.|
| **timestampDigestAlgorithm**  |The message digest algorithm that was used for computing the timestamp signature. `SHA-224, SHA-256, SHA-384, SHA-512, SHA3-224, SHA3-256, SHA3-384, SHA3-512`|
| **id**                        |If specified filters for a specific task id. |
| **requestId**                 |If specified filters for a specific request id by approver Public Key. Request ID is ignored if task ID is set.|
| **detailLevel**               |The detail level of the response. `level1, level2, level3, level4, level5`|
| **pageNumber**                |The number of the pages of results to be returned.|
| **pageSize**                  |The number of results (approval tasks) to be returned per page|
| **sortOrder**                 |Sort order of the results. Note: initial value for LAST_FETCHED_DATE is the creation date of the task. LAST_FETCHED_DATE is updated every time a task is returned to the client using the appropriate REST service. `CREATION_DATE_ASC, CREATION_DATE_DESC, LAST_FETCHED_DATE_ASC, LAST_FETCHED_DATE_DESC`|
</details>

**Response:** Status: 200, with detail-level: `level1` contains a minimal challenge (approval task)

**Body:**
```js {6}
{
  "tasks": [
    {
      "detailLevel": "level1",
      "id": "6947856a-e3a1-44cf-bb99-bbd2995642ca",     # the id of the challenge
      "approvalToBeSigned": "4AAAADsABAABAAAAAhASAFRTQl9UVVRPUklBTF8xLVJTQQAAVBBAADwAAABXEBYAQXBwcm92ZVNpZ25UYXNrUGF5bG9hZAAABwEIANcU814AAAAAAhANAHRpbWVzdGFtcC1rZXkAAABWEFkAMFcwDAYIKoZIzj0EAwIFAANHADBEAiADPniQDGELMUTqJCJ17kW3bUInXIf8dy9mwsseJ7voJAIgK3Kh4JjkR+RDugYKlp4x3M6UQIXkyQ5OuavkL0V5b5sAAABXEBYAQXBwcm92ZVNpZ25UYXNrUGF5bG9hZAAA"
                                                        # the challenge payload (challenge to be signed)
    }
  ]
}
```

<details>
    <summary>Response Parameters</summary>
|Parameter|Description|
|---|---|
| **id**                 |The id of the challenge|
| **approvalToBeSigned**        |The challenge (payload) to be signed with the approvers private key|
</details>

### Send Approval

This step signs the challenge (`approvalToBeSigned` from the previous step) and herewith authorizes the signature-request.

:::tip Fetch Approval Task

The script [Sign Approval Challenge](../../../../resources/ska/rsa/sign_rsa.sh "sign_rsa.sh")  signs the `approvalToBeSigned` from the previous step, with the approvers private key. Again use the following script to generate the next request body.


Run: `./sign_rsa.sh approverx 6947856a-e3a1-44cf-bb99-bbd2995642ca 4AAAADsA....F5bG9hZAAA`

The output of the script should be sent to the endpoint: `/v1/approval` to submit the approval.

:::

**POST** [/v1/approval](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Approval%20Tasks/approveTask)

Example request-body, make sure you are replacing the `id`, `approvalToBeSigned` and the `signature` according to above scripts output.
```js {2,3,4}
{
  "id": "6947856a-e3a1-44cf-bb99-bbd2995642ca",                                 
  "approvalToBeSigned": "4AAAADsABAABAAAAAhASAFRTQl9UVVRPUklBTF8xLVJTQQAAVBBAADwAAABXEBYAQXBwcm92ZVNpZ25UYXNrUGF5bG9hZAAABwEIANcU814AAAAAAhANAHRpbWVzdGFtcC1rZXkAAABWEFkAMFcwDAYIKoZIzj0EAwIFAANHADBEAiADPniQDGELMUTqJCJ17kW3bUInXIf8dy9mwsseJ7voJAIgK3Kh4JjkR+RDugYKlp4x3M6UQIXkyQ5OuavkL0V5b5sAAABXEBYAQXBwcm92ZVNpZ25UYXNrUGF5bG9hZAAA",                                
  "signature": "Mn/DQgm2yQbmFgcsEB+v3ACJgIbVo6IFSa7bDfMXmQXJZU21lVE0Bv46dTN6D4oWU/LYTuUYcIXmmRMChVgvRQvF254CctvDww99/ZUB+RrJ5LKE4t4gbHMgORntY/u3lM1CtmD5LwxONcB+3FwIyiODGPiDtfabgAucoGQ1X3Gdvn6KuESfFxtQfr3yn/FcMHBCGZDPPCIDDRaql3u9rI81emhyh7Oi1iXEypHAi9jzAUWRjnFCFtbOOs5MERN+54HxZF8wfY58aZLlHPF0GIhpC9+92NS7GRe0oO+gwlYX43NvewHwitTUMBE0ctd5xoNDkhuu8zMPTJJwiHqbmA==",
  "approvalDigestAlgorithm": "SHA-256",
  "approverCertificate":"MIIDAjCCAeoCCQCcSLgNCjDsRzANBgkqhkiG9w0BAQsFADBDMQswCQYDVQQGEwJDSDEPMA0GA1UECAwGWnVyaWNoMQ8wDQYDVQQHDAZadXJpY2gxEjAQBgNVBAoMCVNlY3Vyb3N5czAeFw0yMDA1MTExNDI5MDdaFw0yMTA1MTExNDI5MDdaMEMxCzAJBgNVBAYTAkNIMQ8wDQYDVQQIDAZadXJpY2gxDzANBgNVBAcMBlp1cmljaDESMBAGA1UECgwJU2VjdXJvc3lzMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArA0bxSqhL7xfvcHbKKa8wMTMsIeJfYRdIgPxp5cU9JcmV86kyfpyRcSNSi44LVeNmAi94F3OZrXXi6CZvWrFL+VcewUtUSu+kG5oLJ9T4O6R2I5GO2Ev1HJnK3WfHOsFKFxLGzmKyjEkSLGgopY+Nh74K8Q6yxsvQPETOs9TzQiUXFYlfEZnbjUWG4eAgW9WWEopmK/X295ToOuTHFzmzO00btkjAy6vwWOabCE4kaJg+bCNW1snZz84uonr60rB9H0Mj98RbTfbDyMh6cIkaj8WrXeaYh4fxQYXApYu3nzhe3Q1bNCzV5M68rCsgVrmWcK/xUhM9BK6QHSwS/l76wIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQBNmg+gx2mH+fkU/dtM+tDvMIj2SY4pNU8H144aRY9I5kARN7Uwp+zRfJC+rCxrrYxXmx/OD+mIrTAHxPd5WuUWgULB6DXPho5Tyl4Czt6qOuzl7Qp7n1G9R/evZCPyEHflcGVEko/uCL5N8Ch9YboW5QwTrdftnL+zLLC5nON7KUCqbtVrDSdeMKF+dHKTX4Z90gdbv1C8x1fMWrsaoNw194DNBZCTVe4Di69xz3lHNEWVZ460mqKg0n5010VfEQqA92ceNJhjl4hgNMH9+asdBVAWmt0gk4PJUbqtuOKGKyxqi2k9QX8N2tjlsuMJmwRIw2YsZN4EKqQZ+0NAn1N7"                                
}
```

<details>
    <summary>Request Parameters</summary>
|Parameter|Description|
|---|---|
| **id**                        |Id of the task for which the approval is being submitted.|
| **approvalToBeSigned**        |The approval token **(base64 encoded)** as received from the previous request **or constructed by the client** using additional data received in the task|
| **signature**                 |The signature of the approvalToBeSigned **(base64 encoded)**<br/><br/> `SIGNATURE=$(echo -n $APPROVAL_TO_BE_SIGNED \| openssl enc -base64 -d -A \| openssl dgst -sha256 -sign rsa-private-key.pem \| openssl enc -base64 \| tr -d '\n')`|
| **approvalDigestAlgorithm**   |The digest algorithm used for signing the approvalToBeSigned. The signature algorithm is given by the approver's private key. `SHA-224, SHA-256, SHA-384, SHA-512, SHA3-224, SHA3-256, SHA3-384, SHA3-512`|
| **approverPublicKey**         |If approver is public key based: The public key of the approver in the same format as provided during key creation **(base64 encoded)**|
| **approverCertificate**       |If approver is certificate based: The certificate of the approver in the same format as provided during key creation (**base64 encoded)**|
</details>

**Response** Status: 200


### Get the approved signature

:::tip Fetch Approval Task

Replace the `{id}` with the id returned in the first request, e.g. `4e8731d8-0ae6-4444-8a8c-b73ab5f0ba18`

:::


**GET:** [`/v1/request/{id}`](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Requests/getRequestStatus_1)

**Response** Status: 200

```js
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

<details>
    <summary>Response Parameters</summary>
|Parameter|Description|
|---|---|
| **id**                        |Id of the task for which the approval is being submitted.|
| **status**                    |The current status of the request. `PENDING, APPROVED, EXECUTED, FAILED, EXPIRED, REJECTED, CANCELLED`|
| **executionTime**             |Date and time when the request is sent to the hsm in ISO-8601 format.|
| **approvedBy**                |Public keys of approvers that have approved the request.|
| **notYetApprovedBy**          |Public keys of approvers that have not yet approved the request but still could approve.|
| **rejectedBy**                |Public keys from approvers that have rejected to approve the request (i.e. they deleted the task related to this request.|
| **result**                    |The result of the request after the request was executed on the hsm. In case of a sign request this field contains the signature. In case of a decrypt request it contains the decrypted payload. Otherwise this field is empty.|
</details>

# What's next?

- [Try out other Multi Authorization Requests](/tsb/Tutorials/TransactionSecurityBroker/samples/multi-authorization-samples)