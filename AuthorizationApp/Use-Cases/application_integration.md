---
sidebar_position: 1
title: Application integrations for Authorization App
sidebar_label: Application Integration
description: Explore Docker security and HashiCorp Vault integration with Securosys HSM for robust application security and compliance workflows.
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, Docker Security, Docker Encryption, Docker Signing, HashiCorp Vault CE, Securosys HSM, multi-authorization, containerization, cloud-native technologies, application security]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Application Integrations

## Docker Security

In the ever-evolving landscape of containerization and cloud-native technologies, 
Docker has emerged as a cornerstone, revolutionizing how applications are developed, deployed, and managed. 
However, as the adoption of Docker containers continues to skyrocket, so too does the need for robust security measures. 
Docker image signing have emerged as essential components of this security posture, 
ensuring the integrity and confidentiality of containerized applications and is integrated into Secuorsys HSM Rest-API.
With the power of Securosys's MultiAuthorization Framework you can now leverage build-processes with approval functionalities. Read more about:
- [Docker Signing][docker-signing]
- [Docker Encryption][docker-encryption]

## Hashicorp Vault Community Edition
The [HashiCorp Vault Community Edition update][hc-vault] implements a platform-agnostic 
REST-based HSM interface with zero library installation. 
This facilitates the use and deployment in clustered and multi-cloud environments. 
Moreover, Securosys HSM innovations like hardware enforced multi-authorization are at one’s disposal.

- Unlock your Vault with the security of an HSM
- Make use of multi-authorization workflows for compliance applications
- [Securosys Secrets Engine][sse]

# Custom Integration
With the Authorization App, we support all types of key usage, including signing, decryption, and wrapping/unwrapping. This flexibility allows you to create custom workflows and implement robust security patterns tailored to your needs. It is particularly useful in blockchain applications, document signing, and PKI-based solutions.

[docker-signing]: /docker_signing/overview
[docker-encryption]: /docker_encryption/overview
[hc-vault]: /hc_vault/overview
[sse]: /hc_sse/overview
[sbe]: /tsb/Tutorials/Encryption/FileEncryption