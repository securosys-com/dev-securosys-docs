---
sidebar_position: 3
title: List of Error Codes - AWS KMS HSM Integration
sidebar_label: Error Codes
description: Review a detailed list of error codes for the AWS KMS HSM integration with Securosys XKS Proxy. Learn about error scenarios, names, and the related APIs for efficient troubleshooting.
tags: [AWS KMS, XKS]
---

import Link from '@docusaurus/Link';

# List of Error Codes
### AWS KMS & Securosys HSM - Integration Guide

The table below shows the HTTP error code and Error Name returned by the Securosys XKS Proxy to signal various error conditions for different APIs.

The column "Error scenario" indicates the scenario when the corresponding error is returned. The last column lists the applicable XKS proxy APIs:

| **Error Code** | **Error Name** | **Error Scenario** | **XKS Proxy APIs** |
| --- | --- | --- | --- |
| 400 | ValidationException | The request was rejected because one or more input parameters are invalid. | ALL except GetHealthStatus |
| 400 | InvalidStateException | The request was rejected because the specified external key or key store is disabled, deactivated, or blocked. | ALL |
| 400 | InvalidCiphertextException | The request was rejected because the specified ciphertext, initialization vector, additional authenticated data, or authentication tag is corrupted, missing, or otherwise invalid. | Decrypt |
| 400 | InvalidKeyUsageException | The request was rejected because the specified key does not support the requested operation. | Decrypt, Encrypt |
| 401 | AuthenticationFailedException | The request was rejected due to an invalid AWS SigV4 signature. | ALL |
| 403 | AccessDeniedException | The request was rejected because the operation is not authorized based on request metadata. | ALL except GetHealthStatus |
| 404 | KeyNotFoundException | The request was rejected because the specified external key is not found. | ALL except GetHealthStatus |
| 404 | InvalidUriPathException | The request was rejected because the specified URI path is not valid. | ALL |
| 429 | ThrottlingException | The request was rejected because the request rate is too high. The proxy may send this either because it is unable to keep up or the caller exceeded its request quota. | ALL |
| 500 | InternalException | This is a generic server error. For example, this exception is thrown due to failure of the backing key manager or failure of a dependency layer. | ALL |
| 501 | UnsupportedOperationException | The request was rejected because the specified cryptographic operation is not implemented, or if a parameter value exceeded the maximum size currently supported by a specific implementation beyond the minimum size required by this API specification. | ALL |
| 503 | DependencyTimeoutException | The XKS proxy timed out while trying to access a dependency layer to fulfill the request. | ALL |

## More content

- [Download the Securosys XKS Proxy for AWS](../../downloads.md) (login required)
- [Example - Creation of an XKS in AWS KMS](../Examples/Example-AWS-KMS.md)
- [Example - Generating a .jks domain file](../Examples/Example-jks.md)