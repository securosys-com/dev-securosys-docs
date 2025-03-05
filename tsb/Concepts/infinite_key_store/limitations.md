---
sidebar_position: 1
title: Limitations of Infinite Key Store (IKS)
sidebar_label: Limitations
description: Explore the limitations of the PrimusHSM Rest-API's infinite keystore, covering exclusions in certificate management, synchronous operations, approver management, and key-derivation functionalities.
keywords: [limitations, PrimusHSM Rest-API, infinite keystore, certificate management, synchronous operations, approver management, key-derivation, security limitations, key management, cryptographic operations, cryptography, cybersecurity, data security, hsm, hardware security module]
---


import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

## Limitations
The infinite keystore is a powerful tool, encompassing many of the capabilities of the PrimusHSM Rest-API with a few exceptions. 
- Notably, it does not support endpoints dedicated to certificate management, such as those found at /v1/certificate/*. 
- Additionally, when utilizing the SynchronousSign and SynchronousDecrypt endpoints, it's important to note that the `signedApprovals` field should remain empty. This field is specifically designed for presigning the approval challenge, bypassing the need for TSB's native Workflow Engine.
- Moreover, the infinite keystore does not provide endpoints for approver management. These functionalities, found at /v1/approverManagement/*, are not supported within the inifinite keystore context.


## Usage
- Key-Derivation is still possible through the /v1/derivedKey endpoint, temporary Key-derivation is disabled.

## Security Limitations:
- SKA policy modification using the /v1/modify endpoint is not possible, as the key could be changed and an old copy preserved.
- Changing Key-Passwords (if one is set) is not possible, as the key could be changed and an old copy preserved.
- Generating key's externally and then importing is not supported /v1/key/import/plain, however /v1/importedKey (import key from a  bip32 seed) is possible.

Despite these limitations, the infinite keystore remains a versatile solution for many cryptographic needs, offering a wide range of features and capabilities.