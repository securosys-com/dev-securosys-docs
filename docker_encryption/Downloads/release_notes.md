---
sidebar_position: 1
title: Docker Encryption Plugin - Release Notes
sidebar_label: Release Notes
description: Release Notes
keywords: [hsm, cloud hsm]
---

# Release Notes

## Securosys Docker Encryption Skopeo Plugin 1.1.0
Issued: April, 4, 2024

This version is compatible with TSB v2.0.0 or newer, it supports API-Key authentication.

### Configuration Change

- The configuration file now contains also parameters related to the API-Key authentication and signature, such as api key or public and private key which are used to calculate the signature.
- You can now onfigure the "ocirypt.conf" like below(example):

```js
    {
        "key-providers": {
            "skopeo-securosys": {
                "cmd": {
                    "path":"pathToExecutable/skopeo-securosys",
                    "args":  [
                        "-cipher-algorithm <yourCipherAlgorithm>", 
                        "-tsb-api-endpoint <TSB_APIendpoint>", 
                        "-auth TOKEN", 
                        "-token <yourToken>",
                        "-keyOperationToken <TSB-TOKEN>",
                        "-publicKey <PUBLIC_KEY>",
                        "-privateKey <PRIVATE_KEY>"
                        ]
                }
            }
        }
    }
```

## Securosys Docker Encryption Skopeo Plugin 1.0.0

**Release Date:** January, 23 2024
