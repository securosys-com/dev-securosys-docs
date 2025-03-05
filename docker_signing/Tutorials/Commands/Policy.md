---
sidebar_position: 6
title: JSON Policy Example - Docker Signing 
sidebar_label: Key Policy
description: View a full JSON policy example for the Securosys Docker Signing Notation Plugin, including rules for use, block, unblock, and modify actions. Understand how to define token groups, quorum, and approval processes for secure signing operations.
toc_min_heading_level: 5
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Policy Example
### Securosys Docker Signing _Notation_ Plugin

Example of full policy in JSON format:

```js
{
    "ruleUse": {
        "tokens": [
            {
                "name": "string",
                "timelock": 0,
                "timeout": 0,
                "groups": [
                    {
                        "name": "string",
                        "quorum": 1,
                        "approvals": [
                            {
                                "type": "onboarded_approver_certificate",
                                "name": "devops-sebastianfernandez@securosys.com"
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
                "name": "string",
                "timelock": 0,
                "timeout": 0,
                "groups": [
                    {
                        "name": "string",
                        "quorum": 1,
                        "approvals": [
                            {
                                "type": "onboarded_approver_certificate",
                                "name": "devops-sebastianfernandez@securosys.com"
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
                "name": "string",
                "timelock": 0,
                "timeout": 0,
                "groups": [
                    {
                        "name": "string",
                        "quorum": 1,
                        "approvals": [
                            {
                                "type": "onboarded_approver_certificate",
                                "name": "devops-sebastianfernandez@securosys.com"
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
                "name": "string",
                "timelock": 0,
                "timeout": 0,
                "groups": [
                    {
                        "name": "string",
                        "quorum": 1,
                        "approvals": [
                            {
                                "type": "onboarded_approver_certificate",
                                "name": "devops-sebastianfernandez@securosys.com"
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