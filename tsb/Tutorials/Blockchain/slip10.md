
http://127.0.0.1:38215/v1/key

```
{
    "label": "TESTKEY2024-08-14_20-02-48.72168016257020",
    "password": null,
    "id": null,
    "algorithm": "ED",
    "algorithmOid": null,
    "curveOid": "1.3.101.112",
    "keySize": null,
    "addressFormat": null,
    "attributes": {
        "encrypt": null,
        "decrypt": true,
        "verify": null,
        "sign": true,
        "wrap": null,
        "unwrap": true,
        "derive": true,
        "bip32": true,
        "slip10": true,
        "extractable": false,
        "modifiable": true,
        "destroyable": true,
        "sensitive": null,
        "copyable": false
    },
    "policy": {
        "ruleUse": {
            "tokens": [
                {
                    "name": "Token1",
                    "timelock": 0,
                    "timeout": 600,
                    "groups": [
                        {
                            "name": "Group1",
                            "quorum": 1,
                            "approvals": [
                                {
                                    "type": "public_key",
                                    "name": "ApproverKey1",
                                    "value": "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+"
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
                    "name": "Token1",
                    "timelock": 0,
                    "timeout": 600,
                    "groups": [
                        {
                            "name": "Group1",
                            "quorum": 1,
                            "approvals": [
                                {
                                    "type": "public_key",
                                    "name": "ApproverKey1",
                                    "value": "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+"
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
                    "name": "Token1",
                    "timelock": 0,
                    "timeout": 600,
                    "groups": [
                        {
                            "name": "Group1",
                            "quorum": 1,
                            "approvals": [
                                {
                                    "type": "public_key",
                                    "name": "ApproverKey1",
                                    "value": "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+"
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
                    "name": "Token1",
                    "timelock": 0,
                    "timeout": 600,
                    "groups": [
                        {
                            "name": "Group1",
                            "quorum": 1,
                            "approvals": [
                                {
                                    "type": "public_key",
                                    "name": "ApproverKey1",
                                    "value": "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+"
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




http://127.0.0.1:38215/v1/sign

```
{
    "signRequest": {
        "payload": "U29tZVBheWxvYWQ=",
        "payloadType": "UNSPECIFIED",
        "signKeyName": "TESTKEY2024-08-14_20-02-48.72168016257020/44'/0'/0'/0'",
        "keyPassword": null,
        "metaData": "U29tZU1ldGFEYXRh",
        "metaDataSignature": "TWV0YURhdGFTaWduYXR1cmU=",
        "signatureAlgorithm": "EDDSA",
        "signatureType": null
    },
    "requestSignature": null
}
```






http://127.0.0.1:38215/v1/modify

```
{
    "modifyRequest": {
        "modifyKeyName": "TESTKEY2024-08-14_20-02-48.72168016257020",
        "keyPassword": null,
        "metaData": "U29tZU1ldGFEYXRh",
        "metaDataSignature": "TWV0YURhdGFTaWduYXR1cmU=",
        "policy": {
            "ruleUse": {
                "tokens": [
                    {
                        "name": "Token1",
                        "timelock": 0,
                        "timeout": 600,
                        "groups": [
                            {
                                "name": "Group1",
                                "quorum": 1,
                                "approvals": [
                                    {
                                        "type": "public_key",
                                        "name": "ApproverKey1",
                                        "value": "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAExj9TiTebXvwks88DGbBq0MPcGwpZ8yxKZOfEyDMHE3KXTQXadp6z2fVFNFcDHszl9fU9ilLqtcNxx3lGJE1eqA=="
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
                        "name": "Token1",
                        "timelock": 180,
                        "timeout": 180,
                        "groups": [
                            {
                                "name": "Group1",
                                "quorum": 1,
                                "approvals": [
                                    {
                                        "type": "public_key",
                                        "name": "ApproverKey1",
                                        "value": "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+"
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
                        "name": "Token1",
                        "timelock": 180,
                        "timeout": 180,
                        "groups": [
                            {
                                "name": "Group1",
                                "quorum": 1,
                                "approvals": [
                                    {
                                        "type": "public_key",
                                        "name": "ApproverKey1",
                                        "value": "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+"
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
                        "name": "Token1",
                        "timelock": 180,
                        "timeout": 180,
                        "groups": [
                            {
                                "name": "Group1",
                                "quorum": 1,
                                "approvals": [
                                    {
                                        "type": "public_key",
                                        "name": "ApproverKey1",
                                        "value": "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+"
                                    }
                                ]
                            }
                        ]
                    }
                ]
            }
        }
    },
    "requestSignature": null
}
```



```
{
    "modifyKeyRequestId": "6db61033-40c0-4ee1-97c3-6686d61353c7"
}
```


http://127.0.0.1:38215/v1/filteredModifyKeyApprovalTask
```
{
    "timestamp": "2024-08-14T20:02:58+02:00",
    "timestampSignature": "MGYCMQCPF4GoQB9RxqaiRBSqx+BWkE8KeKw/50f7BLdzuimjenZj1n4LRGzvUDoTK9T94eACMQDqIC5kFOaQvyYOvATmw954f06SORdwtlrnMrbv/8fGiJl3Zo85sb6xlWbLnFOoEIs=",
    "timestampSigningCertificate": null,
    "approverPublicKey": "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+",
    "approverCertificate": null,
    "timestampDigestAlgorithm": "SHA-256",
    "id": null,
    "requestId": null,
    "detailLevel": "level5",
    "paging": null
}
```



```
{
    "tasks": [
        {
            "detailLevel": "level5",
            "id": "45151fec-42f2-44f1-999d-c80101a3335c",
            "approvalToBeSigned": "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",
            "approverPublicKey": "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+",
            "metaData": "U29tZU1ldGFEYXRh",
            "metaDataSignature": "TWV0YURhdGFTaWduYXR1cmU=",
            "payload": "UAMAAE4QuAC0AAAAWQAEAAEAAAACEAYAVG9rZW4xAABVAAQAAAAAAFUABAAKAAAAWgAEAAEAAAACEAYAR3JvdXAxAABbAAQAAQAAAAMABAABAAAAAhAMAEFwcHJvdmVyS2V5MVIQWAAwVjAQBgcqhkjOPQIBBgUrgQQACgNCAATGP1OJN5te/CSzzwMZsGrQw9wbClnzLEpk58TIMwcTcpdNBdp2nrPZ9UU0VwMezOX19T2KUuq1w3HHeUYkTV6oTxDYANQAAABZAAQAAQAAAAIQBgBUb2tlbjEAAFUABAADAAAAVQAEAAMAAABaAAQAAQAAAAIQBgBHcm91cDEAAFsABAABAAAAAwAEAAEAAAACEAwAQXBwcm92ZXJLZXkxUhB4ADB2MBAGByqGSM49AgEGBSuBBAAiA2IABBJH5LSj20EdBB0s0zhJmvUpVkcXpx+ar9bkwFKYR+JathtFnWElWHiwBRRVmQqwIpLMcZHuYhZ1tvqwP33OtfUajqCq16SvwRsRHYb5MNa2KWTg3tQTRKPaKsktPpRWvlAQ2ADUAAAAWQAEAAEAAAACEAYAVG9rZW4xAABVAAQAAwAAAFUABAADAAAAWgAEAAEAAAACEAYAR3JvdXAxAABbAAQAAQAAAAMABAABAAAAAhAMAEFwcHJvdmVyS2V5MVIQeAAwdjAQBgcqhkjOPQIBBgUrgQQAIgNiAAQSR+S0o9tBHQQdLNM4SZr1KVZHF6cfmq/W5MBSmEfiWrYbRZ1hJVh4sAUUVZkKsCKSzHGR7mIWdbb6sD99zrX1Go6gqtekr8EbER2G+TDWtilk4N7UE0Sj2irJLT6UVr5RENgA1AAAAFkABAABAAAAAhAGAFRva2VuMQAAVQAEAAMAAABVAAQAAwAAAFoABAABAAAAAhAGAEdyb3VwMQAAWwAEAAEAAAADAAQAAQAAAAIQDABBcHByb3ZlcktleTFSEHgAMHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+",
            "payloadType": null,
            "requestBase64": "eyJtb2RpZnlSZXF1ZXN0Ijp7Im1vZGlmeUtleU5hbWUiOiJURVNUS0VZMjAyNC0wOC0xNF8yMC0wMi00OC43MjE2ODAxNjI1NzAyMCIsImtleVBhc3N3b3JkIjpudWxsLCJtZXRhRGF0YSI6IlUyOXRaVTFsZEdGRVlYUmgiLCJtZXRhRGF0YVNpZ25hdHVyZSI6IlRXVjBZVVJoZEdGVGFXZHVZWFIxY21VPSIsInBvbGljeSI6eyJydWxlVXNlIjp7InRva2VucyI6W3sibmFtZSI6IlRva2VuMSIsInRpbWVsb2NrIjowLCJ0aW1lb3V0Ijo2MDAsImdyb3VwcyI6W3sibmFtZSI6Ikdyb3VwMSIsInF1b3J1bSI6MSwiYXBwcm92YWxzIjpbeyJ0eXBlIjoicHVibGljX2tleSIsIm5hbWUiOiJBcHByb3ZlcktleTEiLCJ2YWx1ZSI6Ik1GWXdFQVlIS29aSXpqMENBUVlGSzRFRUFBb0RRZ0FFeGo5VGlUZWJYdndrczg4REdiQnEwTVBjR3dwWjh5eEtaT2ZFeURNSEUzS1hUUVhhZHA2ejJmVkZORmNESHN6bDlmVTlpbExxdGNOeHgzbEdKRTFlcUE9PSJ9XX1dfV19LCJydWxlQmxvY2siOnsidG9rZW5zIjpbeyJuYW1lIjoiVG9rZW4xIiwidGltZWxvY2siOjE4MCwidGltZW91dCI6MTgwLCJncm91cHMiOlt7Im5hbWUiOiJHcm91cDEiLCJxdW9ydW0iOjEsImFwcHJvdmFscyI6W3sidHlwZSI6InB1YmxpY19rZXkiLCJuYW1lIjoiQXBwcm92ZXJLZXkxIiwidmFsdWUiOiJNSFl3RUFZSEtvWkl6ajBDQVFZRks0RUVBQ0lEWWdBRUVrZmt0S1BiUVIwRUhTelRPRW1hOVNsV1J4ZW5INXF2MXVUQVVwaEg0bHEyRzBXZFlTVlllTEFGRkZXWkNyQWlrc3h4a2U1aUZuVzIrckEvZmM2MTlScU9vS3JYcEsvQkd4RWRodmt3MXJZcFpPRGUxQk5FbzlvcXlTMCtsRmErIn1dfV19XX0sInJ1bGVVbmJsb2NrIjp7InRva2VucyI6W3sibmFtZSI6IlRva2VuMSIsInRpbWVsb2NrIjoxODAsInRpbWVvdXQiOjE4MCwiZ3JvdXBzIjpbeyJuYW1lIjoiR3JvdXAxIiwicXVvcnVtIjoxLCJhcHByb3ZhbHMiOlt7InR5cGUiOiJwdWJsaWNfa2V5IiwibmFtZSI6IkFwcHJvdmVyS2V5MSIsInZhbHVlIjoiTUhZd0VBWUhLb1pJemowQ0FRWUZLNEVFQUNJRFlnQUVFa2ZrdEtQYlFSMEVIU3pUT0VtYTlTbFdSeGVuSDVxdjF1VEFVcGhINGxxMkcwV2RZU1ZZZUxBRkZGV1pDckFpa3N4eGtlNWlGblcyK3JBL2ZjNjE5UnFPb0tyWHBLL0JHeEVkaHZrdzFyWXBaT0RlMUJORW85b3F5UzArbEZhKyJ9XX1dfV19LCJydWxlTW9kaWZ5Ijp7InRva2VucyI6W3sibmFtZSI6IlRva2VuMSIsInRpbWVsb2NrIjoxODAsInRpbWVvdXQiOjE4MCwiZ3JvdXBzIjpbeyJuYW1lIjoiR3JvdXAxIiwicXVvcnVtIjoxLCJhcHByb3ZhbHMiOlt7InR5cGUiOiJwdWJsaWNfa2V5IiwibmFtZSI6IkFwcHJvdmVyS2V5MSIsInZhbHVlIjoiTUhZd0VBWUhLb1pJemowQ0FRWUZLNEVFQUNJRFlnQUVFa2ZrdEtQYlFSMEVIU3pUT0VtYTlTbFdSeGVuSDVxdjF1VEFVcGhINGxxMkcwV2RZU1ZZZUxBRkZGV1pDckFpa3N4eGtlNWlGblcyK3JBL2ZjNjE5UnFPb0tyWHBLL0JHeEVkaHZrdzFyWXBaT0RlMUJORW85b3F5UzArbEZhKyJ9XX1dfV19fX0sInJlcXVlc3RTaWduYXR1cmUiOm51bGx9",
            "signedSignRequest": null,
            "signedModifyKeyRequest": {
                "modifyRequest": {
                    "modifyKeyName": "TESTKEY2024-08-14_20-02-48.72168016257020",
                    "keyPassword": null,
                    "metaData": "U29tZU1ldGFEYXRh",
                    "metaDataSignature": "TWV0YURhdGFTaWduYXR1cmU=",
                    "policy": {
                        "ruleUse": {
                            "tokens": [
                                {
                                    "name": "Token1",
                                    "timelock": 0,
                                    "timeout": 600,
                                    "groups": [
                                        {
                                            "name": "Group1",
                                            "quorum": 1,
                                            "approvals": [
                                                {
                                                    "type": "public_key",
                                                    "name": "ApproverKey1",
                                                    "value": "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAExj9TiTebXvwks88DGbBq0MPcGwpZ8yxKZOfEyDMHE3KXTQXadp6z2fVFNFcDHszl9fU9ilLqtcNxx3lGJE1eqA=="
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
                                    "name": "Token1",
                                    "timelock": 180,
                                    "timeout": 180,
                                    "groups": [
                                        {
                                            "name": "Group1",
                                            "quorum": 1,
                                            "approvals": [
                                                {
                                                    "type": "public_key",
                                                    "name": "ApproverKey1",
                                                    "value": "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+"
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
                                    "name": "Token1",
                                    "timelock": 180,
                                    "timeout": 180,
                                    "groups": [
                                        {
                                            "name": "Group1",
                                            "quorum": 1,
                                            "approvals": [
                                                {
                                                    "type": "public_key",
                                                    "name": "ApproverKey1",
                                                    "value": "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+"
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
                                    "name": "Token1",
                                    "timelock": 180,
                                    "timeout": 180,
                                    "groups": [
                                        {
                                            "name": "Group1",
                                            "quorum": 1,
                                            "approvals": [
                                                {
                                                    "type": "public_key",
                                                    "name": "ApproverKey1",
                                                    "value": "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+"
                                                }
                                            ]
                                        }
                                    ]
                                }
                            ]
                        }
                    }
                },
                "requestSignature": null
            },
            "signedBlockKeyRequest": null,
            "signedUnblockKeyRequest": null,
            "signedDecryptRequest": null,
            "signedUnwrapKeyRequest": null,
            "signedCertificateRequestRequest": null,
            "signedSignCertificateRequest": null,
            "signedSelfSignCertificateRequestDto": null,
            "timestamp": "fAMAAFgQVANQAwAAThC4ALQAAABZAAQAAQAAAAIQBgBUb2tlbjEAAFUABAAAAAAAVQAEAAoAAABaAAQAAQAAAAIQBgBHcm91cDEAAFsABAABAAAAAwAEAAEAAAACEAwAQXBwcm92ZXJLZXkxUhBYADBWMBAGByqGSM49AgEGBSuBBAAKA0IABMY/U4k3m178JLPPAxmwatDD3BsKWfMsSmTnxMgzBxNyl00F2naes9n1RTRXAx7M5fX1PYpS6rXDccd5RiRNXqhPENgA1AAAAFkABAABAAAAAhAGAFRva2VuMQAAVQAEAAMAAABVAAQAAwAAAFoABAABAAAAAhAGAEdyb3VwMQAAWwAEAAEAAAADAAQAAQAAAAIQDABBcHByb3ZlcktleTFSEHgAMHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+UBDYANQAAABZAAQAAQAAAAIQBgBUb2tlbjEAAFUABAADAAAAVQAEAAMAAABaAAQAAQAAAAIQBgBHcm91cDEAAFsABAABAAAAAwAEAAEAAAACEAwAQXBwcm92ZXJLZXkxUhB4ADB2MBAGByqGSM49AgEGBSuBBAAiA2IABBJH5LSj20EdBB0s0zhJmvUpVkcXpx+ar9bkwFKYR+JathtFnWElWHiwBRRVmQqwIpLMcZHuYhZ1tvqwP33OtfUajqCq16SvwRsRHYb5MNa2KWTg3tQTRKPaKsktPpRWvlEQ2ADUAAAAWQAEAAEAAAACEAYAVG9rZW4xAABVAAQAAwAAAFUABAADAAAAWgAEAAEAAAACEAYAR3JvdXAxAABbAAQAAQAAAAMABAABAAAAAhAMAEFwcHJvdmVyS2V5MVIQeAAwdjAQBgcqhkjOPQIBBgUrgQQAIgNiAAQSR+S0o9tBHQQdLNM4SZr1KVZHF6cfmq/W5MBSmEfiWrYbRZ1hJVh4sAUUVZkKsCKSzHGR7mIWdbb6sD99zrX1Go6gqtekr8EbER2G+TDWtilk4N7UE0Sj2irJLT6UVr4HAQgAUvG8ZgAAAAACEBIAdGVzdC10aW1lc3RhbXAta2V5AAA=",
            "timestampSignature": "MEUCIDYyH3c0pHEmwkz9FuT9SwqCRBlefhEw9K6dknHsaK7SAiEA37clhpGDWirf9L6ALC8tgmw/gI1g9A9A4LQfFpmYkr4=",
            "timestampSignatureAlgorithm": "SHA256_WITH_ECDSA",
            "timestampSignatureWithAlgorithm": "MIIDkzAMBggqhkjOPQQDAgUAA4IDgQB8AwAAWBBUA1ADAABOELgAtAAAAFkABAABAAAAAhAGAFRva2VuMQAAVQAEAAAAAABVAAQACgAAAFoABAABAAAAAhAGAEdyb3VwMQAAWwAEAAEAAAADAAQAAQAAAAIQDABBcHByb3ZlcktleTFSEFgAMFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAExj9TiTebXvwks88DGbBq0MPcGwpZ8yxKZOfEyDMHE3KXTQXadp6z2fVFNFcDHszl9fU9ilLqtcNxx3lGJE1eqE8Q2ADUAAAAWQAEAAEAAAACEAYAVG9rZW4xAABVAAQAAwAAAFUABAADAAAAWgAEAAEAAAACEAYAR3JvdXAxAABbAAQAAQAAAAMABAABAAAAAhAMAEFwcHJvdmVyS2V5MVIQeAAwdjAQBgcqhkjOPQIBBgUrgQQAIgNiAAQSR+S0o9tBHQQdLNM4SZr1KVZHF6cfmq/W5MBSmEfiWrYbRZ1hJVh4sAUUVZkKsCKSzHGR7mIWdbb6sD99zrX1Go6gqtekr8EbER2G+TDWtilk4N7UE0Sj2irJLT6UVr5QENgA1AAAAFkABAABAAAAAhAGAFRva2VuMQAAVQAEAAMAAABVAAQAAwAAAFoABAABAAAAAhAGAEdyb3VwMQAAWwAEAAEAAAADAAQAAQAAAAIQDABBcHByb3ZlcktleTFSEHgAMHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+URDYANQAAABZAAQAAQAAAAIQBgBUb2tlbjEAAFUABAADAAAAVQAEAAMAAABaAAQAAQAAAAIQBgBHcm91cDEAAFsABAABAAAAAwAEAAEAAAACEAwAQXBwcm92ZXJLZXkxUhB4ADB2MBAGByqGSM49AgEGBSuBBAAiA2IABBJH5LSj20EdBB0s0zhJmvUpVkcXpx+ar9bkwFKYR+JathtFnWElWHiwBRRVmQqwIpLMcZHuYhZ1tvqwP33OtfUajqCq16SvwRsRHYb5MNa2KWTg3tQTRKPaKsktPpRWvgcBCABS8bxmAAAAAAIQEgB0ZXN0LXRpbWVzdGFtcC1rZXkAAA==",
            "keyAttributes": "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<private_key creation=\"generated\">\n\t<label>TESTKEY2024-08-14_20-02-48.72168016257020</label>\n\t<create_time>2024-08-14T18:02:50Z</create_time>\n\t<attest_time>2024-08-14T18:02:59Z</attest_time>\n\t<key_use_count>1</key_use_count>\n\t<algorithm>ED</algorithm>\n\t<algorithm_oid>1.3.101.112</algorithm_oid>\n\t<public_key>MCowBQYDK2VwAyEA/GpVPSJzhsGgH4mGLxRK3h1XFh9t90hwn7NKf8RKCNA=</public_key>\n\t<derivation_value type=\"ADA/SLIP-10\">5B8998B9F905F5337730ABC38131A1B0573EC799925D40F6B68369A1F3363AA9</derivation_value>\n\t<attributes>\n\t\t<decrypt>true</decrypt>\n\t\t<sign>true</sign>\n\t\t<unwrap>true</unwrap>\n\t\t<derive>true</derive>\n\t\t<sensitive>true</sensitive>\n\t\t<always_sensitive>true</always_sensitive>\n\t\t<extractable>false</extractable>\n\t\t<never_extractable>true</never_extractable>\n\t\t<modifiable>true</modifiable>\n\t\t<copyable>false</copyable>\n\t\t<destroyable>true</destroyable>\n\t\t<unique>true</unique>\n\t</attributes>\n\t<policy>\n\t\t<key_status>\n\t\t\t<blocked>false</blocked>\n\t\t</key_status>\n\t\t<rule_use>\n\t\t\t<token name=\"Token1\">\n\t\t\t\t<timelock>0</timelock>\n\t\t\t\t<timeout>600</timeout>\n\t\t\t\t<group name=\"Group1\">\n\t\t\t\t\t<quorum>1</quorum>\n\t\t\t\t\t<approval type=\"public_key\" name=\"ApproverKey1\">MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+</approval>\n\t\t\t\t</group>\n\t\t\t</token>\n\t\t</rule_use>\n\t\t<rule_block>\n\t\t\t<token name=\"Token1\">\n\t\t\t\t<timelock>0</timelock>\n\t\t\t\t<timeout>600</timeout>\n\t\t\t\t<group name=\"Group1\">\n\t\t\t\t\t<quorum>1</quorum>\n\t\t\t\t\t<approval type=\"public_key\" name=\"ApproverKey1\">MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+</approval>\n\t\t\t\t</group>\n\t\t\t</token>\n\t\t</rule_block>\n\t\t<rule_unblock>\n\t\t\t<token name=\"Token1\">\n\t\t\t\t<timelock>0</timelock>\n\t\t\t\t<timeout>600</timeout>\n\t\t\t\t<group name=\"Group1\">\n\t\t\t\t\t<quorum>1</quorum>\n\t\t\t\t\t<approval type=\"public_key\" name=\"ApproverKey1\">MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+</approval>\n\t\t\t\t</group>\n\t\t\t</token>\n\t\t</rule_unblock>\n\t\t<rule_modify>\n\t\t\t<token name=\"Token1\">\n\t\t\t\t<timelock>0</timelock>\n\t\t\t\t<timeout>600</timeout>\n\t\t\t\t<group name=\"Group1\">\n\t\t\t\t\t<quorum>1</quorum>\n\t\t\t\t\t<approval type=\"public_key\" name=\"ApproverKey1\">MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+</approval>\n\t\t\t\t</group>\n\t\t\t</token>\n\t\t</rule_modify>\n\t</policy>\n</private_key>\n\n",
            "keyAttributesSignature": "UfDYpzX9cYjiAIZrvTgxwPgRnAQ9izSlipeMlI0cbZK3uUQBc/0OqemITEEmxb3o3YZ//791NBIBYmoTpO7b5XmBTZdFsOUknXyZAFzZYaTqiKFqi1s5sZjVqdFZS81WdpVDHi4XEmuMvPghFMJ/lrn6AgJAO6I4e2+eRPtJRqMEqKEcvoLFJsxvz3nh9o4skBZIiQaUj3UiouC5pWffZFMHCU3xUxovwVf7xbOWP9X8TcCIhURzjTn3r8zZHO6J9QXNi8lZ8NjL6nYemi6MzfiaM463/zpv2R0pXRFY3DGeaYsD3kIoDNfkqfSYAJAYPGSkzpDLUeXQv8Kb2+Bnc2I1dFKbOoruBZYQ1J/Fwo0tUVa3avv1S4+ixmE8sF/QTt81q/jVkjhWV8ssU3y/DeofVtv01sefSXa4REdWwOkr4AytJ46h0yCq5xStRKSQFL0A2gP1r3UkA4AREIl1x5AEcNVIoH+R77GgdSHGCidtMaxiYCvv4h+fGdKwhdH4"
        }
    ]
}
```




http://127.0.0.1:38215/v1/approval



```
{
    "id": "45151fec-42f2-44f1-999d-c80101a3335c",
    "approvalToBeSigned": "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",
    "signature": "MGYCMQCNxLqPBhH/MfLTo0WBoes7JwrsCDIScROinm31X8AKx1hYagnCJONWFdRXFaq0afMCMQCRgKsMpmnKlBdzg6nNzaz/YRPKBKHD86B9KdIxXGQSceBbf2sREYh1pn2MTpAWmEY=",
    "approvalDigestAlgorithm": "SHA-256",
    "approverPublicKey": "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+",
    "approverCertificate": null
}
```



http://127.0.0.1:38215/v1/sign

```
{
    "signRequest": {
        "payload": "SGFsbG9hc2Q=",
        "payloadType": "UNSPECIFIED",
        "signKeyName": "TESTKEY2024-08-14_20-02-48.72168016257020/44'/0'/0'/0'",
        "keyPassword": null,
        "metaData": "U29tZU1ldGFEYXRh",
        "metaDataSignature": "TWV0YURhdGFTaWduYXR1cmU=",
        "signatureAlgorithm": "EDDSA",
        "signatureType": null
    },
    "requestSignature": null
}
```

```
{
    "signRequestId": "1c5bc570-e41c-47c2-bc1a-82ecf4376d08"
}
```


http://127.0.0.1:38215/v1/filteredSignApprovalTask

```
{
    "timestamp": "2024-08-14T20:03:02+02:00",
    "timestampSignature": "MEQCICyBxty37ZpPPzub83bSZsbVkUqf6n4z+0/UXSo4FIqOAiBOVDHpq2K0wKTJTfBWu3fGZViDZ0tWI08rG5CTKCl4Rg==",
    "timestampSigningCertificate": null,
    "approverPublicKey": "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAExj9TiTebXvwks88DGbBq0MPcGwpZ8yxKZOfEyDMHE3KXTQXadp6z2fVFNFcDHszl9fU9ilLqtcNxx3lGJE1eqA==",
    "approverCertificate": null,
    "timestampDigestAlgorithm": "SHA-256",
    "id": null,
    "requestId": null,
    "detailLevel": "level5",
    "paging": null
}
```

```
{
    "tasks": [
        {
            "detailLevel": "level5",
            "id": "7c768f4d-d221-4b88-b32b-a7a932108013",
            "approvalToBeSigned": "6AAAADsABAABAAAAAhA2AFRFU1RLRVkyMDI0LTA4LTE0XzIwLTAyLTQ4LjcyMTY4MDE2MjU3MDIwLzQ0Jy8wJy8wJy8wJwAAVBA0ADAAAABXEAgASGFsbG9hc2QHAQgAVvG8ZgAAAAACEBIAdGVzdC10aW1lc3RhbXAta2V5AABWEFkAMFcwDAYIKoZIzj0EAwIFAANHADBEAiAFNEFP55QP+X+QZoLm1rwCbkfO4gfwRZTPLrJicJtTowIgP0GZsXDbDQOsMNDhnyEF1KMpciImARKKMoDcqLdAVBQAAABXEAgASGFsbG9hc2Q=",
            "approverPublicKey": "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAExj9TiTebXvwks88DGbBq0MPcGwpZ8yxKZOfEyDMHE3KXTQXadp6z2fVFNFcDHszl9fU9ilLqtcNxx3lGJE1eqA==",
            "metaData": "U29tZU1ldGFEYXRh",
            "metaDataSignature": "TWV0YURhdGFTaWduYXR1cmU=",
            "payload": "SGFsbG9hc2Q=",
            "payloadType": "UNSPECIFIED",
            "requestBase64": "eyJzaWduUmVxdWVzdCI6eyJwYXlsb2FkIjoiU0dGc2JHOWhjMlE9IiwicGF5bG9hZFR5cGUiOiJVTlNQRUNJRklFRCIsInNpZ25LZXlOYW1lIjoiVEVTVEtFWTIwMjQtMDgtMTRfMjAtMDItNDguNzIxNjgwMTYyNTcwMjAvNDQnLzAnLzAnLzAnIiwia2V5UGFzc3dvcmQiOm51bGwsIm1ldGFEYXRhIjoiVTI5dFpVMWxkR0ZFWVhSaCIsIm1ldGFEYXRhU2lnbmF0dXJlIjoiVFdWMFlVUmhkR0ZUYVdkdVlYUjFjbVU9Iiwic2lnbmF0dXJlQWxnb3JpdGhtIjoiRUREU0EiLCJzaWduYXR1cmVUeXBlIjpudWxsfSwicmVxdWVzdFNpZ25hdHVyZSI6bnVsbH0=",
            "signedSignRequest": {
                "signRequest": {
                    "payload": "SGFsbG9hc2Q=",
                    "payloadType": "UNSPECIFIED",
                    "signKeyName": "TESTKEY2024-08-14_20-02-48.72168016257020/44'/0'/0'/0'",
                    "keyPassword": null,
                    "metaData": "U29tZU1ldGFEYXRh",
                    "metaDataSignature": "TWV0YURhdGFTaWduYXR1cmU=",
                    "signatureAlgorithm": "EDDSA",
                    "signatureType": null
                },
                "requestSignature": null
            },
            "signedModifyKeyRequest": null,
            "signedBlockKeyRequest": null,
            "signedUnblockKeyRequest": null,
            "signedDecryptRequest": null,
            "signedUnwrapKeyRequest": null,
            "signedCertificateRequestRequest": null,
            "signedSignCertificateRequest": null,
            "signedSelfSignCertificateRequestDto": null,
            "timestamp": "MAAAAFcQCABIYWxsb2FzZAcBCABW8bxmAAAAAAIQEgB0ZXN0LXRpbWVzdGFtcC1rZXkAAA==",
            "timestampSignature": "MEQCIAU0QU/nlA/5f5BmgubWvAJuR87iB/BFlM8usmJwm1OjAiA/QZmxcNsNA6ww0OGfIQXUoylyIiYBEooygNyot0BUFA==",
            "timestampSignatureAlgorithm": "SHA256_WITH_ECDSA",
            "timestampSignatureWithAlgorithm": "MEUwDAYIKoZIzj0EAwIFAAM1ADAAAABXEAgASGFsbG9hc2QHAQgAVvG8ZgAAAAACEBIAdGVzdC10aW1lc3RhbXAta2V5AAA=",
            "keyAttributes": "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<private_key creation=\"imported\" storage=\"external\">\n\t<uuid>F6474FFA-F68EC585-383412B7-41EDAA40</uuid>\n\t<label>TESTKEY2024-08-14_20-02-48.72168016257020/44&apos;/0&apos;/0&apos;/0&apos;</label>\n\t<create_time>2024-08-14T18:03:03Z</create_time>\n\t<attest_time>2024-08-14T18:03:04Z</attest_time>\n\t<algorithm>ED</algorithm>\n\t<algorithm_oid>1.3.101.112</algorithm_oid>\n\t<public_key>MCowBQYDK2VwAyEATF9NOmF1F+54cx6pNwh4TuHZp8QR5kJ1F47/vytYIO0=</public_key>\n\t<derivation_value type=\"ADA/SLIP-10\">44CB4957F0271F4AC1EAFE5E30ED34FCDAE1467ABEBD714D58DFB4FEE6367C6F</derivation_value>\n\t<attributes>\n\t\t<decrypt>true</decrypt>\n\t\t<sign>true</sign>\n\t\t<unwrap>true</unwrap>\n\t\t<derive>true</derive>\n\t\t<sensitive>true</sensitive>\n\t\t<always_sensitive>true</always_sensitive>\n\t\t<extractable>false</extractable>\n\t\t<never_extractable>true</never_extractable>\n\t\t<modifiable>true</modifiable>\n\t\t<copyable>false</copyable>\n\t\t<destroyable>true</destroyable>\n\t\t<unique>false</unique>\n\t</attributes>\n\t<policy>\n\t\t<key_status>\n\t\t\t<blocked>false</blocked>\n\t\t</key_status>\n\t\t<rule_use>\n\t\t\t<token name=\"Token1\">\n\t\t\t\t<timelock>0</timelock>\n\t\t\t\t<timeout>600</timeout>\n\t\t\t\t<group name=\"Group1\">\n\t\t\t\t\t<quorum>1</quorum>\n\t\t\t\t\t<approval type=\"public_key\" name=\"ApproverKey1\">MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAExj9TiTebXvwks88DGbBq0MPcGwpZ8yxKZOfEyDMHE3KXTQXadp6z2fVFNFcDHszl9fU9ilLqtcNxx3lGJE1eqA==</approval>\n\t\t\t\t</group>\n\t\t\t</token>\n\t\t</rule_use>\n\t\t<rule_block>\n\t\t\t<token name=\"Token1\">\n\t\t\t\t<timelock>180</timelock>\n\t\t\t\t<timeout>180</timeout>\n\t\t\t\t<group name=\"Group1\">\n\t\t\t\t\t<quorum>1</quorum>\n\t\t\t\t\t<approval type=\"public_key\" name=\"ApproverKey1\">MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+</approval>\n\t\t\t\t</group>\n\t\t\t</token>\n\t\t</rule_block>\n\t\t<rule_unblock>\n\t\t\t<token name=\"Token1\">\n\t\t\t\t<timelock>180</timelock>\n\t\t\t\t<timeout>180</timeout>\n\t\t\t\t<group name=\"Group1\">\n\t\t\t\t\t<quorum>1</quorum>\n\t\t\t\t\t<approval type=\"public_key\" name=\"ApproverKey1\">MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+</approval>\n\t\t\t\t</group>\n\t\t\t</token>\n\t\t</rule_unblock>\n\t\t<rule_modify>\n\t\t\t<token name=\"Token1\">\n\t\t\t\t<timelock>180</timelock>\n\t\t\t\t<timeout>180</timeout>\n\t\t\t\t<group name=\"Group1\">\n\t\t\t\t\t<quorum>1</quorum>\n\t\t\t\t\t<approval type=\"public_key\" name=\"ApproverKey1\">MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEkfktKPbQR0EHSzTOEma9SlWRxenH5qv1uTAUphH4lq2G0WdYSVYeLAFFFWZCrAiksxxke5iFnW2+rA/fc619RqOoKrXpK/BGxEdhvkw1rYpZODe1BNEo9oqyS0+lFa+</approval>\n\t\t\t\t</group>\n\t\t\t</token>\n\t\t</rule_modify>\n\t</policy>\n</private_key>\n\n",
            "keyAttributesSignature": "g57p3BWHwaRihZhfea5Ba8S+AhW+Y/QUUcjrhcosvDpONFlYMmMYGD3M5IBO8tNV5NN5gX98AzpNC8nTysBOePZkB9fth8x4ulagzdWh0gukJVNaMlqQk+BJHh0X86a92zGZm7//Ubp8wPbuByK4IooJN6zDb5+o9KexlF4v78x1/6yrSd+Oy37VjfxdU/96/rzxK92xGohhJCz7pZm1jEB5UkNQebdGoQRzpFj9PCpb7yGSdDYqIc1Jhgrn82cV2zB8mlbt9+lx6jDAbnhJ2NK0Qs6CRZvxjtoo6FxxeJ/vBdGqkd6y8RF/Y+noC0RCRhrB5vJ62RXD/FS0vlHPtL6ROsl/bGDeD9kisMv5FIYwrKdeG44DCPQTmFQI7GrAbIDkBOqi+A6+7OeyfaqfgIKX/PWV00m0cj7UNu1eJJsI3CQCT+lmWsR7sRQ06eiB+1INE/w/8PZrrue+hY2mVehhtXK3ZwJkIJ6IOM1nUd2rE77OYUxwxdK8PeZCihXc"
        }
    ]
}
```


http://127.0.0.1:38215/v1/approval
```
{
    "id": "7c768f4d-d221-4b88-b32b-a7a932108013",
    "approvalToBeSigned": "6AAAADsABAABAAAAAhA2AFRFU1RLRVkyMDI0LTA4LTE0XzIwLTAyLTQ4LjcyMTY4MDE2MjU3MDIwLzQ0Jy8wJy8wJy8wJwAAVBA0ADAAAABXEAgASGFsbG9hc2QHAQgAVvG8ZgAAAAACEBIAdGVzdC10aW1lc3RhbXAta2V5AABWEFkAMFcwDAYIKoZIzj0EAwIFAANHADBEAiAFNEFP55QP+X+QZoLm1rwCbkfO4gfwRZTPLrJicJtTowIgP0GZsXDbDQOsMNDhnyEF1KMpciImARKKMoDcqLdAVBQAAABXEAgASGFsbG9hc2Q=",
    "signature": "MEYCIQDeoI7iRXVzwK/bbgXPK0qTERt5+eIK+uzQQqBC/+egogIhAMp4d5dnJ6qUHeBNybiNkiSgZETJ3Owpuoos6yLpXFDA",
    "approvalDigestAlgorithm": "SHA-256",
    "approverPublicKey": "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAExj9TiTebXvwks88DGbBq0MPcGwpZ8yxKZOfEyDMHE3KXTQXadp6z2fVFNFcDHszl9fU9ilLqtcNxx3lGJE1eqA==",
    "approverCertificate": null
}
```