---
sidebar_position: 2
title: Generating Certificates for Docker Signing
sidebar_label: Generate Certificate
description: Discover two methods for generating certificates with the Securosys Docker Signing Notation Plugin. Create a self-signed certificate on the HSM or request a certificate via CSR from a trusted provider.
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Generating a certificate
### Securosys Docker Signing Notation Plugin

There are two possible ways of generating a certificate:

1) [Self-signed certificate](./SelfSignedCert) on the HSM.

2) Requesting a [certificate with the Certificate Signing Request(CSR)](./SigningCsr) from a trusted certificate provider and importing the certificate to the HSM.