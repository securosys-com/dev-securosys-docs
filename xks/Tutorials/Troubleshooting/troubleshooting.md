---
sidebar_position: 0
title: Troubleshooting - XKS Proxy for AWS KMS
sidebar_label: Troubleshooting
description: Troubleshoot the Securosys XKS Proxy by checking AWS connection, reviewing Primus HSM logs, and analyzing error codes in the proxy logs.
tags: [AWS KMS, XKS]
---

import Link from '@docusaurus/Link';

# Troubleshooting the XKS Proxy

To troubleshoot the Securosys XKS proxy, follow these steps:

- Check the AWS and HSM connection by using the command ```GetHealthStatus``` built into the AWS CLI. This command is by default periodically executed and logged in the Securosys XKS proxy logs.
- On-premises: If you have a Primus HSM, review the HSM logs for possible issues with the connection. To troubleshoot your Primus HSM, please review the [user manual](https://support.securosys.com/external/knowledge-base/article/63) (login required).
- Review the [Securosys XKS Proxy logs](./logging.md) for possible errors, as well as the [Log Error Codes](./Logging-Errors.md).

## More content

- [Download the Securosys XKS Proxy for AWS](../../downloads.md) (login required)
- [Example - Creation of an XKS in AWS KMS](../Examples/Example-AWS-KMS.md)
- [AWS Bring Your Own Key (BYOK)](/aws-byok/overview)