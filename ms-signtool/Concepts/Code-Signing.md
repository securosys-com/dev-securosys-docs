---
sidebar_position: 1
title: What is Code Signing?
sidebar_label: Code Signing
description: Code signing is a security technique used in software development to ensure the authenticity and integrity of code. It involves digitally signing executable files and scripts to verify their origin and detect any tampering or unauthorized modifications.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# What is Code Signing?

**Code Signing** is a security technique used in software development to ensure the authenticity and integrity of code. It involves digitally signing executable files and scripts to verify their origin and detect any tampering or unauthorized modifications.

Key Components of Code Signing:

- **Digital Certificates**: Code signing relies on digital certificates issued by trusted certificate authorities (CAs). These certificates contain cryptographic keys that are used to sign the code.

- **Private Key**: The developer uses their private key to digitally sign the code. This key should be kept secure to prevent unauthorized signing.

- **Public Key**: The public key is embedded in the digital certificate and is used to verify the signature. It can be freely distributed.

## Why Code Signing?

Obtaining digital certificates from reputable Certificate Authorities (CAs) can be expensive, especially for small developers or open-source projects. Managing keys and certificates, as well as integrating code signing into the development workflow, can be complex and require specialized knowledge.

Code signing provides means to verify the identity of the software publisher. Users can trust that the code comes from a legitimate source. By signing code, developers ensure that it hasn't been altered or corrupted since it was signed. Any changes to the code will invalidate the signature.
Once the code is signed, the developer cannot deny responsibility for it. The signature serves as proof of authorship.

Code signing helps prevent malware and unauthorized software from being executed on users' systems. Users are more likely to trust signed software, leading to higher adoption rates and better user experiences. In some industries, code signing is required by regulations or standards to ensure the security and integrity of software.


## Implementing code signing

Developers use code signing tools provided by software platforms (e.g., Microsoft SignTool) to sign their code. The tool generates a cryptographic hash of the code and encrypts it using the developer's private key. For enhanced security, the private key can be securely stored and managed within a **Hardware Security Module (HSM)**, ensuring it is protected against unauthorized access.

When users run signed code, their systems check the signature against the embedded public key. If the signature is valid and the certificate is trusted, the code is executed. Otherwise, the system may display a warning or block execution.

## What's next?

- [What is Microsoft SignTool ?](../overview)
- [Getting started with Microsoft SignTool](../Quickstart)
- What is [Cloud Hardware Security Module](/cloudhsm/overview/)?