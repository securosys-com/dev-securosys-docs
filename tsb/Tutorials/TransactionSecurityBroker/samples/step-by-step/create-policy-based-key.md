---
sidebar_position: 1
title: Creating a key with Smart Key Attributes?
sidebar_label: 1. Create Policy based key
description: Learn how to create SKA-keys using the PrimusHSM Rest-API.
keywords: [Create Key, SKA-key, PrimusHSM Rest-API, cryptographic key management, key creation, smart key attributes, TSB_ENGINE, cryptography, cybersecurity, data security, HSM, hardware security module]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Create Policy based key

<Tabs groupId="key-provider">
<TabItem value="create-ska-key" label="Simple Policy" default>



:::info Rules

This example demonstrates how to create an RSA key with Policy enabled. It is simplified with a **quorum** of 1 and a single approver on **rule-use**.
You can expand the policy section with rule-modify, rule-block, rule-unblock. For futher information about SKA-Policy please check [here](/tsb/Tutorials/TransactionSecurityBroker/smart-key-attributes).

:::
<br/>

The `ruleUse` means that whenever the key is used for cryptographic operations, such as _signing, decrypting, unwrapping, or issuing certificates_, the request must be **authorized** by the designated **approver** in the policy.
<br/>


:::tip Create RSA Key with Policy

For simplicity you can use the script to create your approver key-pair locally [Create Approver Key-Pair](../../../../resources/ska/rsa/create_rsa.sh "create_rsa.sh") <br />
Run: `./create_rsa.sh approverx`
(Don't forget to allow exec of script `chmod +x create_rsa.sh`)
:::

**POST** [/v1/key](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

<Tabs groupId="key-provider">
<TabItem value="Commented" label="Commented" default>

```bash
{
#highlight-tip-start
    "label": "TSB_TUTORIAL_1-RSA",       # Label must be unique and is used for any request of the key action
    "algorithm": "RSA",
    "keySize": 2048,                    # keySize is required for RSA
    #highlight-tip-end
    "attributes": {                      # For this example, we'll assume the key will be used only for signing. We also rely on defaults for most attributes (see in response)
        "decrypt": false,
        "sign": true,
        "unwrap": false,
        "destroyable": true 
    },
    "policy": {                          # To better understand structure of the policies, refer to the concept diagram
    #highlight-warning-start
        "ruleUse": {                     # We'll set a very simple policy - 1/1 approval with no timelock and a 10 minute timeout
    #highlight-warning-end
            "tokens": [
                {
                    "name": "Token1",
                    "timelock": 0,
                    "timeout": 3600,     # Time restrictions are defined in seconds and must be multiples of 60
                    "groups": [
                        {
                            "name": "Group1",
    #highlight-tip-start
                            "quorum": 1,  # Quorum of 1 means that only 1 approver needs to sign in order to get a request EXECUTED
    #highlight-tip-end
                            "approvals": [
                                {
    #highlight-tip-start
                                    "type": "certificate",   # the type can vary based on your preference: certificate, public-key or onboarded_approver_certificate
                                    "value": "MIIDAjCCAeoCCQCcSLgNCjDsRzANBgkqhkiG9w0BAQsFADBDMQswCQYDVQQGEwJDSDEPMA0GA1UECAwGWnVyaWNoMQ8wDQYDVQQHDAZadXJpY2gxEjAQBgNVBAoMCVNlY3Vyb3N5czAeFw0yMDA1MTExNDI5MDdaFw0yMTA1MTExNDI5MDdaMEMxCzAJBgNVBAYTAkNIMQ8wDQYDVQQIDAZadXJpY2gxDzANBgNVBAcMBlp1cmljaDESMBAGA1UECgwJU2VjdXJvc3lzMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArA0bxSqhL7xfvcHbKKa8wMTMsIeJfYRdIgPxp5cU9JcmV86kyfpyRcSNSi44LVeNmAi94F3OZrXXi6CZvWrFL+VcewUtUSu+kG5oLJ9T4O6R2I5GO2Ev1HJnK3WfHOsFKFxLGzmKyjEkSLGgopY+Nh74K8Q6yxsvQPETOs9TzQiUXFYlfEZnbjUWG4eAgW9WWEopmK/X295ToOuTHFzmzO00btkjAy6vwWOabCE4kaJg+bCNW1snZz84uonr60rB9H0Mj98RbTfbDyMh6cIkaj8WrXeaYh4fxQYXApYu3nzhe3Q1bNCzV5M68rCsgVrmWcK/xUhM9BK6QHSwS/l76wIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQBNmg+gx2mH+fkU/dtM+tDvMIj2SY4pNU8H144aRY9I5kARN7Uwp+zRfJC+rCxrrYxXmx/OD+mIrTAHxPd5WuUWgULB6DXPho5Tyl4Czt6qOuzl7Qp7n1G9R/evZCPyEHflcGVEko/uCL5N8Ch9YboW5QwTrdftnL+zLLC5nON7KUCqbtVrDSdeMKF+dHKTX4Z90gdbv1C8x1fMWrsaoNw194DNBZCTVe4Di69xz3lHNEWVZ460mqKg0n5010VfEQqA92ceNJhjl4hgNMH9+asdBVAWmt0gk4PJUbqtuOKGKyxqi2k9QX8N2tjlsuMJmwRIw2YsZN4EKqQZ+0NAn1N7"
                                    #highlight-tip-end   
                                                            # Certificate or PublicKey values must be provided without new lines
                                }
                            ]
                        }
                    ]
                }
            ]
        },
    "keyStatus": {                     # Make sure this is inside of policy object
        "blocked": false               # If setting this to true, make sure ruleUnblock is defined
    }
    }
}
```
</TabItem>
<TabItem value="Un-commented" label="Un-commented" default>
```js {24,25}
{
    "label": "TSB_TUTORIAL_1-RSA",
    "algorithm": "RSA",
    "keySize": 2048,
    "attributes": {
        "decrypt": false,
        "sign": true,
        "unwrap": false,
        "destroyable": true
    },
    "policy": {
        "ruleUse": {
            "tokens": [
                {
                    "name": "Token1",
                    "timelock": 0,
                    "timeout": 3600,
                    "groups": [
                        {
                            "name": "Group1",
                            "quorum": 1,
                            "approvals": [
                                {
                                    "type": "certificate",
                                    "value": "MIIDAjCCAeoCCQCcSLgNCjDsRzANBgkqhkiG9w0BAQsFADBDMQswCQYDVQQGEwJDSDEPMA0GA1UECAwGWnVyaWNoMQ8wDQYDVQQHDAZadXJpY2gxEjAQBgNVBAoMCVNlY3Vyb3N5czAeFw0yMDA1MTExNDI5MDdaFw0yMTA1MTExNDI5MDdaMEMxCzAJBgNVBAYTAkNIMQ8wDQYDVQQIDAZadXJpY2gxDzANBgNVBAcMBlp1cmljaDESMBAGA1UECgwJU2VjdXJvc3lzMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArA0bxSqhL7xfvcHbKKa8wMTMsIeJfYRdIgPxp5cU9JcmV86kyfpyRcSNSi44LVeNmAi94F3OZrXXi6CZvWrFL+VcewUtUSu+kG5oLJ9T4O6R2I5GO2Ev1HJnK3WfHOsFKFxLGzmKyjEkSLGgopY+Nh74K8Q6yxsvQPETOs9TzQiUXFYlfEZnbjUWG4eAgW9WWEopmK/X295ToOuTHFzmzO00btkjAy6vwWOabCE4kaJg+bCNW1snZz84uonr60rB9H0Mj98RbTfbDyMh6cIkaj8WrXeaYh4fxQYXApYu3nzhe3Q1bNCzV5M68rCsgVrmWcK/xUhM9BK6QHSwS/l76wIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQBNmg+gx2mH+fkU/dtM+tDvMIj2SY4pNU8H144aRY9I5kARN7Uwp+zRfJC+rCxrrYxXmx/OD+mIrTAHxPd5WuUWgULB6DXPho5Tyl4Czt6qOuzl7Qp7n1G9R/evZCPyEHflcGVEko/uCL5N8Ch9YboW5QwTrdftnL+zLLC5nON7KUCqbtVrDSdeMKF+dHKTX4Z90gdbv1C8x1fMWrsaoNw194DNBZCTVe4Di69xz3lHNEWVZ460mqKg0n5010VfEQqA92ceNJhjl4hgNMH9+asdBVAWmt0gk4PJUbqtuOKGKyxqi2k9QX8N2tjlsuMJmwRIw2YsZN4EKqQZ+0NAn1N7"
                                }
                            ]
                        }
                    ]
                }
            ]
        },
        "keyStatus": {
            "blocked": false
        }   
    }
}
```
</TabItem>
</Tabs>
</TabItem>
<TabItem value="authorization_app" label="Securosys Authorization App Policy" default>
    Use the sample below if you want to create an SKA-Key with an **onboarded approver**. The key difference when using the [Approver Management API via REST](/tsb/Tutorials/TransactionSecurityBroker/PrimusAuthorizationApp/approver-mangement-api) is that you can specify the `onboarded_approver_certificate` type in the Key Policy.

:::info Rules

The sample below is simplified with a **quorum** of 1 and a single approver on **rule-use**.
You can expand the policy section with rule-modify, rule-block, rule-unblock. For futher information about SKA-Policy please check [here](/tsb/Tutorials/TransactionSecurityBroker/smart-key-attributes).

:::

<Tabs groupId="key-provider">
<TabItem value="Commented" label="Commented" default>
```bash
{
    "label": "TSB_TUTORIAL_1-RSA",       # Label must be unique and is used for any request of the key action
    "algorithm": "RSA",
    "keySize": 2048,                    # keySize is required for RSA
    "attributes": {                      # For this example, we'll assume the key will be used only for signing. We also rely on defaults for most attributes (see in response)
        "decrypt": false,
        "sign": true,
        "unwrap": false,
        "destroyable": true 
    },
    "policy": {                          # To better understand structure of the policies, refer to the concept diagram
    #highlight-warning-start
        "ruleUse": {                     # We'll set a very simple policy - 1/1 approval with no timelock and a 10 minute timeout
    #highlight-warning-end
            "tokens": [
                {
                    "name": "Token1",
                    "timelock": 0,
                    "timeout": 3600,     # Time restrictions are defined in seconds and must be multiples of 60
                    "groups": [
                        {                        
                            "name": "Group1",
    #highlight-warning-start
                            "quorum": 1,  # Quorum of 1 means that only 1 approver needs to sign in order to get a request EXECUTED
    #highlight-warning-end
                            "approvals": [
                                {
    #highlight-warning-start
                                    "type": "onboarded_approver_certificate",   # the type can vary based on your preference: certificate, public-key or onboarded_approver_certificate
                                    "name": "officer1@securosys.com"
    #highlight-warning-end                                  
                                }
                            ]
                        }
                    ]
                }
            ]
        },
        "keyStatus": {                     # Make sure this is inside of policy object
            "blocked": false               # If setting this to true, make sure ruleUnblock is defined
        }
    }
}
```
</TabItem>
<TabItem value="Un-commented" label="Un-commented" default>
```js {24,25}
{
    "label": "TSB_TUTORIAL_1-RSA",
    "algorithm": "RSA",
    "keySize": 2048,
    "attributes": {
        "decrypt": false,
        "sign": true,
        "unwrap": false,
        "destroyable": true
    },
    "policy": {
        "ruleUse": {
            "tokens": [
                {
                    "name": "Token1",
                    "timelock": 0,
                    "timeout": 3600,
                    "groups": [
                        {
                            "name": "Group1",
                            "quorum": 1,
                            "approvals": [
                                {
                                    "type": "onboarded_approver_certificate",
                                    "name": "officer1@securosys.com"
                                }
                            ]
                        }
                    ]
                }
            ]
        },
        "keyStatus": {
            "blocked": false
        }
    }
}
```
</TabItem>
</Tabs>

This article provides samples to create a key with onboarded **Approver** to an HSM-Key with SmartKeyAttributes (Policy), to enable true Multi-Authorization.

:::note

Status of onboarded approvers can be retrieved by the **Approver Manager** utilizing **POST** [`/v1/approverManagement/onboarding/status`](/AuthorizationApp/Tutorials/ApproverManagment/verify_onboarding_status)

:::
</TabItem>
<TabItem value="empty_policy" label="Empty Policy" default>

:::danger Take care

Key with **empty policy** does not enforce true multiauthorization, but enables the key to be used with multiauthorization later.
An empty policy means that the request is executed immediately without authorization.

:::

**POST** [/v1/key](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

```js {15-18}
{
   "label": "<keyname>",
   "algorithm": "EC",
   "curveOid": "1.3.132.0.10",   
   "attributes": {
      "decrypt": false,
      "sign": true,
      "unwrap": false,
      "destroyable": true
   },
   "policy": {
      "ruleUse": null,
      "ruleBlock": null,
      "ruleUnblock": null,
      "ruleModify": null,
      "keyStatus": {
         "blocked": false
      }
   }
}
```
</TabItem>
</Tabs>

<details>
    <summary>**Key** Parameters</summary>
|Parameter|Description|
|---|---|
| **label**  | The keyname e.g., `TSB_TUTORIAL_1-RSA`.|
| **algorithm**     | The key algorithm. _[Supported algorithms](/tsb/Concepts/key/Algorithms)_.|
| **keySize**       | The size of key. _[Supported sizes](/tsb/Concepts/key/CreateKey#rsa-rivest-shamir-adleman)_|
| **curveOid**       | The curveOid (for EC/ED only). _[supported curveOid's](/tsb/Concepts/key/Curve-Oid)_|
| **decrypt**       | Key decrypt capability. _[Key-Attributes](/tsb/Concepts/key/Attributes)_|
| **sign**       | Key sign capability. _[Key-Attributes](/tsb/Concepts/key/Attributes)_|
| **unwrap**       | Key unwrap capability. _[Key-Attributes](/tsb/Concepts/key/Attributes)_|
| **destroyable**       | Key access. _[Key-Attributes](/tsb/Concepts/key/Attributes)_|
</details>

<details>
<summary>**Attributes**</summary>

- All Key Attributes are described _**[here](/tsb/Concepts/key/Attributes)**_.
- Minimal attributes:

|Attribute|Description|
|--|--|
|Decrypt|The key can be used for decryption, allowing it to transform ciphertext back into plaintext.|
|Sign|The key can be used for creating digital signatures.|
|Unwrap|The key can be used to unwrap (decrypt) encrypted keys.|
|Destroyable|The key can be intentionally destroyed (deleted).|


</details>

<details>
<summary>**Policy** Parameters</summary>
|Parameter|Description|
|---|---|
| **ruleUse**    | The ruleUse for **private-key Operations** such as: `v1/sign`, `/v1/decrypt`, `/v1/unwrap`|
| **ruleModify** | The ruleModify to modify the policy: `v1/modify`|
| **ruleBlock**  | The ruleBlock to block usage of the private-key: `v1/block`|
| **ruleUnblock**| The ruleUnblock to unblock a blocked key: `v1/unblock`|
| **tokens**     | A Token array, which are **OR** associated, if multiple tokens are specified, either token1 or token2 has the be satisfied.|
| **timelock**   | The timelock before the approval is accepted, in seconds, a multiple of 60|
| **timeout**    | The timeout after which no approvals are accepted, in seconds, a multiple of 60|
| **groups**     | An Group array, which are **AND** associated, if multiple groups with quorum 1 is specified, each group has to fullfill the quorum.|
| **quorum**     | Quorum of 1 means that only 1 approver needs to sign in order to get a request EXECUTED |
| **approvals**  | The approvers (mobile applications) onboarded to the policy. In order to use the key, the Approver has to approve the request, before it gets executed.|
| **type**       | The type can vary based on your preference: _certificate_, _public-key_ or _onboarded_approver_certificate_ (for approverManagement API only!)
|
| **type**       | The type, for the use of Securosys Authorization App, it is always  `onboarded_approver_certificate`|
| **value**      | The name of the onboarded approver.|

:::note Policy

- For more infromation about SmartKeyAttributes and the Approval Workflow: [TransactionSecurityBroker](/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker)
- For more information about the Policy: [SKA-Policy](/tsb/Tutorials/TransactionSecurityBroker/smart-key-attributes)

:::

</details>
<br/>

# What's next?

- [Create Multi Authorization Request](/tsb/Tutorials/TransactionSecurityBroker/samples/step-by-step/signature-request)