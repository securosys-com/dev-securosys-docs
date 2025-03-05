---
sidebar_position: 2
title: policy.json - Secrets Engine plugin for HashiCorp Vault
sidebar_label: Example - policy.json
description: MariaDB Integration for Securosy' Secrets Engine plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---


# Example: policy.json

```js
{
    "ruleUse": {
      "tokens": [
        {
          "name": "MAIN1",
          "timelock": 0,
          "timeout": 0,
          "groups": [
            {
              "name": "MAIN1",
              "quorum": 1,
              "approvals": [
                {
                  "type": "public_key",
                  "name": "replace_me_with_approval_name",
                  "value":"replace_me_with_approval_key"
                }
              ]
            }
          ]
        }
      ]
    },
    "ruleBlock": {
      "tokens": [
        {
          "name": "MAIN1",
          "timelock": 0,
          "timeout": 0,
          "groups": [
            {
              "name": "MAIN1",
              "quorum": 1,
              "approvals": [
                {
                  "type": "public_key",
                  "name": "replace_me_with_approval_name",
                  "value":"replace_me_with_approval_key"
                }
              ]
            }
          ]
        }
      ]
    },
    "ruleUnblock": {
      "tokens": [
        {
          "name": "MAIN1",
          "timelock": 0,
          "timeout": 0,
          "groups": [
            {
              "name": "MAIN1",
              "quorum": 1,
              "approvals": [
                {
                  "type": "public_key",
                  "name": "replace_me_with_approval_name",
                  "value":"replace_me_with_approval_key"
                }
              ]
            }
          ]
        }
      ]
    },
    "ruleModify": {
      "tokens": [
        {
          "name": "MAIN1",
          "timelock": 0,
          "timeout": 0,
          "groups": [
            {
              "name": "MAIN1",
              "quorum": 1,
              "approvals": [
                {
                  "type": "public_key",
                  "name": "replace_me_with_approval_name",
                  "value":"replace_me_with_approval_key"
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


```