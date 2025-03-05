---
sidebar_position: 1
title: What is Securosys CloudHSM?
sidebar_label: What is CloudHSM?
description: Securosys CloudHSM is a managed service offering FIPS 140-2 Level 3 validated hardware security modules (HSMs) for secure encryption key management in the cloud
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
slug: '/overview'
pagination_prev: null
---

# What is CloudHSM?

**Securosys CloudHSM** is a managed Hardware Security Module (HSM) service. It provides secure, cloud-based HSMs for generating and managing encryption keys used by your applications. Built on proprietary hardware and software, CloudHSM offers end-to-end control without intermediaries.

## Key Features

- [**Use Cases**](../use_cases.md): Ideal for Public Key Infrastructures, Key Management, Identity and Access Management, Data Encryption, TLS-Termination, Document and Code Signing, Crypto Custody, Blockchain, and more.
- [**Deployment Options**](../Packages/overview.md): Choose between dedicated HSMs or a multi-tenant setup. Available in Global, Swiss, European, North American, and Asia-Pacific clusters.
- [**High Availability**](../Architecture/high_availability.md): Clusters are synchronized across multiple HSMs for redundancy and low latency, offering local, regional, and global access points.

## Cryptographic Features

**Securosys CloudHSM** offers a range of [cryptographic features](./features.md):

- Secure random data generation.
- Key management (generation, storage, import, export).
- Data encryption and decryption (symmetric/asymmetric).
- HSM-backed certificate generation.
- Implement file encryption based on [ECIES](/tsb/Tutorials/Encryption/FileEncryption) for secure data handling.
- [Key Attestation](features.md#key-attestation) and [Smart Key Attributes](features.md#smart-key-attributes-ska).
- [Cryptocurrencies](features.md#cryptocurrencies) and [Post-Quantum Cryptography](/tsb/Tutorials/Post-Quantum-Cryptography/pqc).

**Securosys CloudHSM** service can be tailored to your needs. Explore our [service packages](./Packages/overview) and [options & features](./Overview/features) to find the perfect fit for your needs.

## Integrations

**Securosys CloudHSM** is meant to work with a wide range of applications such as [OpenSSL](/openssl/overview), [Amazon Web Services (AWS)](/xks/overview), [Docker Signing](/docker_signing/overview), [Salesforce](/salesforce-byok/overview), [HasiCorp](/hc-vault-enterprise/overview) and many more.

[Browse all the use cases](/integrations)

:::tip Getting started

+ [Try CloudHSM for free for 90 days](https://cloud.securosys.com/cloudhsm)
+ [Contact sales](https://www.securosys.com/en/contact)
:::