---
sidebar_position: 1
title: REST API - HSM Application integrations
sidebar_label: Application Integration
description: Explore Docker security and HashiCorp Vault integration with Securosys HSM for robust application security and compliance workflows.
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, Docker Security, Docker Encryption, Docker Signing, HashiCorp Vault CE, Securosys HSM, multi-authorization, containerization, cloud-native technologies, application security]
---

# Application Integration

## Docker Security

In the ever-evolving landscape of containerization and cloud-native technologies, 
Docker has emerged as a cornerstone, revolutionizing how applications are developed, deployed, and managed. 
However, as the adoption of Docker containers continues to skyrocket, so too does the need for robust security measures. 
Docker image signing have emerged as essential components of this security posture, 
ensuring the integrity and confidentiality of containerized applications and is integrated into Securosys HSM Rest-API.

- [Docker Signing][docker-signing]
- [Docker Encryption][docker-encryption]

## Hashicorp Vault CE
The [HashiCorp Vault Community Edition update][hc-vault] implements a platform-agnostic 
REST-based HSM interface with zero library installation. 
This facilitates the use and deployment in clustered and multi-cloud environments. 
Moreover, Securosys HSM innovations like hardware enforced multi-authorization are at one’s disposal.

- Unlock your Vault with the security of an HSM
- Make use of multi-authorization workflows for compliance applications
- [Securosys Secrets Engine][sse]

## Managing Cloud Secrets with Advanced Encryption

As more businesses transition to cloud-based environments, the protection of sensitive data becomes a paramount concern. While cloud providers offer solutions like Secret Manager to store your secrets safely, securing your backups is often overlooked and a common attack vector. What happens if access to your cloud environment is compromised? How can you ensure the safety of your secrets even when they are backed up? In today’s interconnected digital landscape, the need to secure not just live secrets, but also backups, is more critical than ever.

- [Secure Backup Encryption with Securosys REST-API][sbe]

**Why Backups Need Encryption**
Many organizations rely on their cloud provider’s Secret Manager for safekeeping of API keys, credentials, and other sensitive information. While this is an excellent first step, simply backing up your secrets isn’t enough. Unencrypted backups expose critical data to significant risks, including accidental leaks or unauthorized access.

Encrypting your backups ensures that even if these backup files are accessed by unauthorized people, they are unusable to anyone without the appropriate decryption key. This added layer of security provides peace of mind and protects your business from costly breaches.

**Benefits of Our Encryption Solution**

1. Large File Encryption Support: Capable of encrypting relatively large files, ensuring that even sizable backup files can be secured without compromising performance.
2. Seamless Integration: Easily integrated via HTTPS requests and compatible with CURL, making it straightforward to automate within CI/CD pipelines and other automation workflows.
3. Scalable and Flexible: Our REST API can handle various secret types and is built to scale alongside your cloud infrastructure.
4. Compliance Ready: Protects your business from regulatory risks by ensuring sensitive information is encrypted at all stages of backup storage and recovery processes.


Check out the [Tutorial][sbe] on how to secure your backups using Securosys PrimusHSM Rest-API or ask our experts via [Customer Portal](https://support.securosys.com/external).


[docker-signing]: /docker_signing/overview
[docker-encryption]: /docker_encryption/overview
[hc-vault]: /hc_vault/overview
[sse]: /hc_sse/overview
[sbe]: /tsb/Tutorials/Encryption/FileEncryption