---
sidebar_position: 0
title: Creating a Bitcoin Master Key via REST-API
sidebar_label: Create Master Key (Bitcoin)
---

# Bitcoin Master Key

:::tip Tip

If you are not familiar with SKA, please read the [SmartKeyAttributes and Approval Workflow](/tsb/Tutorials/TransactionSecurityBroker/smart-key-attributes.md) first.

:::


#### Request
POST: /v1/key

The rule for using the key will be defined as 1 of 2 Finance Officers AND 1 of 2 Risk Officers

```js {2,3,4,6,21,23,27,32,37,42,47,52}
{
    "label": "TSB_TUTORIAL_3-BITCOIN",
    "algorithm": "EC",
    "curveOid": "1.3.132.0.10",
    "addressFormat": {
        "format": "BTC"
    },
    "attributes": {
        "decrypt": false,
        "sign": true,
        "unwrap": false,
        "destroyable": true,
        "modifiable": true,
        "derive": true,
        "bip32": true
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
                            "name": "FinanceOfficers",
                            "quorum": 2,
                            "approvals": [
                                {
                                    "type": "public_key",
                                    "name": "FinanceOfficer1",
                                    "value": "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEvYPsJeuck/SOotORgxN6Kih9EbfZGTrFht8gv0ASBRjoD3Jlh5w4+QQAZMeBLIy23rCxwYYouN6x5xiOZCH2/g=="
                                },
                                {
                                    "type": "public_key",
                                    "name": "FinanceOfficer2",
                                    "value": "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEJsYPk79qiD2mc6obIhvVj76LU14z022M4aogxxUdrQSSNJrSsJqpiT+tnVF66q5h4B3NHaLc3nOm8yBY0jJZew=="
                                },
                                {
                                    "type": "public_key",
                                    "name": "FinanceOfficer3",
                                    "value": "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEpoiulRerdrbKqmRMmR/udw9FqsrlsBVz4dptoESQdwvy+Xi0jOaoikRd7weYeRtZoRw9xAqiOME+P7XWnuh1Pg=="
                                },
                                {
                                    "type": "public_key",
                                    "name": "FinanceOfficer4",
                                    "value": "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEpeeOY7L02jwxJi+z40BMKjD2xf7md8wRQSna3JvfYYPUejRtxDX0tRQWmg9r0PEj6h4aenNAoo+2pXiKRdsInA=="
                                },
                                {
                                    "type": "public_key",
                                    "name": "FinanceOfficer5",
                                    "value": "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEBIV/KkJzFD0zJuPEl1UkEO/qh7fQ+MsXm1jvuOaXyg3LlZSQC1l1OQJM804x1At8oXZnr/0U2SHiOtyfpBD1xQ=="
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

**Response**
```js
1.2 Response
------------
{
    "label": "TSB_TUTORIAL_3-BITCOIN",
    "algorithm": "EC",
    "curveOid": "1.3.132.0.10",
    "addressFormat": {
        "format": "BTC"
    },
.
.
.
}
```

### Get a temporarily (not persisted) derived Key's Bitcoin Address Base
The child key is now available under its derived key label: `TSB_TUTORIAL_3-BITCOIN/44'/0'/0'/0`
- More about key-derivation [Key-Derivation](/tsb/Concepts/key/Derivation/KeyDerivation.md)

