---
sidebar_position: 3
title: Authentication Methods
sidebar_label: Authentication Methods
description: Secure your interactions using multiple authentication methods, JSON Web Tokens (JWT) and mTLS.
keywords: [JSON Web Tokens, JWT, mTLS, Mutual TLS, authentication methods, CloudHSM, on-prem deployments, bidirectional authentication, cybersecurity, data security]
---

Implement robust security for your REST API using a combination of authentication methods, including JWT (JSON Web Tokens), mutual TLS (mTLS), and HTTP-based API keys. 

## JSON Web Tokens (JWT):
  - **Enhanced API Security:** JWTs add an extra layer of security by ensuring that each request is properly authenticated.
  - **CloudHSM Requirement:** JWT authentication is **mandatory** when integrating with CloudHSM, ensuring secure communication and access control. (It is possible to add mutualTLS authentication, [Contact Support](https://support.securosys.com/external).
  - **Flexible Implementation:** For on-premise deployments, JWTs are **optional** but recommended when not using mTLS to bolster security and maintain consistency across environments.

## Mutual TLS (mTLS):
  - **Bidirectional Authentication:** mTLS offers a robust security mechanism by requiring both the client and server to authenticate each other, ensuring only trusted entities can interact with your API.
  - **Comprehensive Client Validation:** Optionally, an **OCSP-Responder** can be configured to validate *client certificates in real-time*, granting you full control over client authentications and enhancing the integrity of your API ecosystem.
  - **Implementation Guide:** For a detailed setup process, please refer to our [mTLS configuration guide](/tsb/Installation/https-setup/mtls-configuration). For additional support, reach out to Securosys Support.


## API Keys:
API keys provide a role-based approach to manage access to various functionalities, ensuring that operations related to key management, key usage, Smart Key Attribute approvals, and service endpoints are properly segregated. This helps maintain security and control over who can perform different actions within the system.

### How API Keys Work
Each API key corresponds to a specific role, determining the set of permissions and access rights associated with that key. To authenticate, the API key is sent as part of the HTTP header using the `X-API-KEY` field.

:::info API-Keys

API keys can be used alongside other authentication methods, such as mutual TLS (mTLS) and JWT Authentication, offering flexibility in securing your API interactions.

:::

 
### API Key Roles Overview
The API employs different roles, each tailored to specific tasks. Below is a detailed explanation of each role and the endpoints they protect:

**Key Management Token (`keyManagementToken`)**

- **Purpose:** Provides access to create, modify, or delete HSM keys and manage their lifecycle. This token is required for all operations related to key management.
- **Protected Endpoints:** The following endpoints can only be accessed using the keyManagementToken:
<details>
  <summary>Key Management Endpoints</summary>
  - /v1/key
  - /v1/key/**
  - /v1/key/**/attributes
  - /v1/importedKey
  - /v1/derivedKey
  - /v1/dataObject
  - /v1/dataObject/**
  - /v1/attestation/**
  - /v1/modify
  - /v1/synchronousModify
</details>

---

**Key Operation Token (`keyOperationToken`)**
- **Purpose:** Allows execution of cryptographic functions, including encryption, decryption, signing, and certificate issuance. This token is necessary for performing key-based operations.
- **Protected Endpoints:** The following endpoints require a keyOperationToken for access:  
<details>
  <summary>Key Operation Endpoints</summary>
  - /v1/certificate/**
  - /v1/synchronousUnwrap
  - /v1/synchronousUnblock
  - /v1/synchronousSign
  - /v1/synchronousFileDecrypt
  - /v1/synchronousDecrypt
  - /v1/synchronousBlock
  - /v1/synchronousHmac
  - /v1/fileEncrypt
  - /v1/wrap
  - /v1/verify
  - /v1/encrypt
  - /v1/fileEncrypt
  - /v1/createRfcTimestamp
  - /v1/generateRandom/**
  - /v1/unwrap
  - /v1/unblock
  - /v1/sign
  - /v1/decrypt
  - /v1/block
  - /v1/request/**
  - /v1/filteredRequests
</details>

---

**Approver Token (`approverToken`)**

- **Purpose:** Required for actions that involve approvals for [Multi Authorization](/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker). It ensures that only authorized approvers can confirm sensitive operations.
- **Protected Endpoints:** The following endpoints require the approverToken for access:
  
<details>
  <summary>Approval Endpoints</summary>
  - /v1/filtered**ApprovalTask
  - /v1/approval
  - /v1/task
  - /v1/approver/onboard  
</details>

---

**Service Token (`serviceToken`)**
- **Purpose:** Provides access to general service-level information and statistics that do not fall under key management or cryptographic operations. Can also be used as health-check endpoints.
- **Protected Endpoints:** The following endpoints are accessible with the serviceToken:

<details>
  <summary>Service Endpoints</summary>
  - /v1/versionInfo
  - /v1/licenseInfo
  - /v1/keystore/statistics
  - /v1/hsm/log
  - /v1/hsm/log/**  
</details>

---

**Approver Key Management Token (`approverKeyManagementToken`)**

- **Purpose:** Provides access to approverManagement endpoints to manage approver(s) in conjunction with the [Securosys Authorization App](/AuthorizationApp/overview)
- **Protected Endpoints:** The following endpoints are accessible with the approverKeyManagementToken:

<details>
  <summary>Approver Management Endpoints</summary>
  - /v1/approverManagement/**
</details>
  