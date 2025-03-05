---
sidebar_position: 0
title: Security HSM Integration with Rest-API
sidebar_label: Introduction
description: Explore the integration of Securosys REST API within Docker containers for seamless interaction with Primus HSM clusters and CloudHSM, ensuring robust hardware security module (HSM) capabilities.
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, Rest-API, Docker integration, cryptographic operations, RSA key generation, synchronous signing, OpenSSL commands, scalable architecture, JWT tokens, system management]
grid_title: Rest-API
grid_search_tags: [Docker, Rest-API, Provider, "JCE/JCA", "Smart Key Attributes", "PQC"]
grid_description: Get started with REST-API for Hardware Security Modules (HSMs), Smart Key Attributes (SKA), and Transaction Security Broker (TSB).
grid_categories: [Rest-API, "Smart Key Attributes", Provider, PQC]
grid_tile_logoUrl: '/img/securosys_logo.png'
---


import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# REST-API & Transaction Security Broker
### HSM Integration Guide

## Rest-API
The Securosys REST API, encapsulated within a Docker container, seamlessly interfaces with Primus HSM clusters and CloudHSM, providing a versatile, language-agnostic solution for robust hardware security module (HSM) integration. This API serves as a bridge for secure key management, enabling cryptographic operations such as signing and encryption with the highest level of protection. Leveraging the power of HSMs, it ensures the confidentiality and integrity of sensitive data, making it an indispensable tool for applications demanding advanced security measures.

### How it works
The Rest-API, deployed as a Docker container, establishes a direct connection to a single HSM partition (User), enabling secure cryptographic operations. To enhance scalability and distribute the workload, multiple Docker containers can communicate with a shared HSM partition, facilitating effective load balancing. Additionally, the Rest-API's flexible architecture supports a 1:n configuration, allowing one API interface (one Docker container) to connect with multiple HSMs or partitions using JWT-Token's for authentication, while a second container manages states and credential information in a dedicated database for comprehensive system management.

### Try it out

On this page it is shown how to:
  - create an RSA Key
  - sign a payload with the previously created key

### Create an RSA Key

**POST**: [/v1/key](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

<details>
    <summary>**Key** Parameters</summary>
|Parameter|Description|
|---|---|
| **label**  | The email address of the Approver, e.g., `officer1@securosys.com`.|
| **algorithm**     | The algorithm of the key. Supported algorithms: [/Algorithms](/tsb/Concepts/key/Algorithms.md)|
| **keySize**       | The size of the Key. Applicable for RSA, DSA, ISS and Symmetric algorithms. |
| **curveOid**       | Needed if Alrogithm==EC || ED, check [supported curveOid's](/tsb/Concepts/key/Curve-Oid)|
| **decrypt**       | check [Key-Attributes](/tsb/Concepts/key/Attributes)|
| **sign**       | check [Key-Attributes](/tsb/Concepts/key/Attributes)|
| **unwrap**       | check [Key-Attributes](/tsb/Concepts/key/Attributes)|
| **destroyable**       | check [Key-Attributes](/tsb/Concepts/key/Attributes)|
</details>

<Tabs groupId="device-setup">
  <TabItem value="swagger" label="Swagger" default>
  :::tip Connectivity Details
    You may replace the endpoint `https://tsb-demo.cloudshsm.com/` _below_  by a [TSBaaS - Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) for accurate API-Endpoint URI.
  :::

  1. **Open** the Swagger documentation to interact directly with the API using your browser: [https://tsb-demo.cloudshsm.com/swagger-ui/index.html](https://tsb-demo.cloudshsm.com/swagger-ui/index.html)

   2. **Request:** Replace `<keyname>`
        ```js {2}
        {
          "label": "<keyname>",
          "algorithm": "RSA",
          "keySize": 2048,
          "attributes": {
              "sign": true,
              "extractable": false,
              "modifiable": true,
              "destroyable": true,
              "sensitive": true,
              "decrypt": false,
              "unwrap": false
          }
        }
        ```
        **Response:**
        ```js
        {
        "xml": "<xml-formatted attributes of the key>",
        "json": {
            "label": "...",
            "id": null,
            "algorithm": "RSA",
            "algorithmOid": "1.2.840.113549.1.1.1",
            "curveOid": null,
            "keySize": 2048,
            "createTime": "2021-02-24T15:10:03Z",
            "attestTime": "2021-02-24T15:10:03Z",
            "publicKey": "MIIBIj...AB",
            "addressTruncated": null,
            "attributes": {
            "decrypt": false,
            "sign": true,
            "ekaSign": null,
            "unwrap": false,
            "derive": false,
            "sensitive": true,
            "alwaysSensitive": true,
            "extractable": false,
            "neverExtractable": true,
            "modifiable": true,
            "copyable": true,
            "destroyable": true
            },
            "policy": null
        },
        "xmlSignature": "<base64-encoded-signature>",
        "attestationKeyName": "attestation-key"
        }
        ```
  </TabItem>
  <TabItem value="curl" label="CURL" default>
  :::tip Connectivity Details
    You may replace the endpoint `https://tsb-demo.cloudshsm.com/` _below_  by a [TSBaaS - Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) for accurate API-Endpoint URI.
  :::

    **Request:** Replace `<keyname>` and `<bearer_token>`.
        ```sh {2,7}
        curl --request POST \
        --url "https://tsb-demo.cloudshsm.com/v1/key" \
        --header 'accept: application/json' \
        --header 'Content-Type: application/json' \
        --data '{
            "label": "<keyname>",
            "algorithm": "RSA",
            "keySize": 2048,
            "attributes": {
                "sign": true,
                "extractable": false,
                "modifiable": true,
                "destroyable": true,
                "sensitive": true,
                "decrypt": false,
                "unwrap": false
            }
        }'
        ```
    **Response:**
        ```js
        {
        "xml": "<xml-formatted attributes of the key>",
        "json": {
            "label": "...",
            "id": null,
            "algorithm": "RSA",
            "algorithmOid": "1.2.840.113549.1.1.1",
            "curveOid": null,
            "keySize": 2048,
            "createTime": "2021-02-24T15:10:03Z",
            "attestTime": "2021-02-24T15:10:03Z",
            "publicKey": "MIIBIj...AB",
            "addressTruncated": null,
            "attributes": {
            "decrypt": false,
            "sign": true,
            "ekaSign": null,
            "unwrap": false,
            "derive": false,
            "sensitive": true,
            "alwaysSensitive": true,
            "extractable": false,
            "neverExtractable": true,
            "modifiable": true,
            "copyable": true,
            "destroyable": true
            },
            "policy": null
        },
        "xmlSignature": "<base64-encoded-signature>",
        "attestationKeyName": "attestation-key"
        }
        ```
  </TabItem>
</Tabs>

---
---

### Sign a Payload with the Key

**POST**: [/v1/synchronousSign](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/synchronousSign)

**Description:**


<Tabs groupId="device-setup">
  <TabItem value="swagger" label="Swagger" default>
    **Request:** Replace `<keyname>`
    ```js {3-5}
    {
        "signRequest": {
            "payload": "UGF5bG9hZA==",
            "signKeyName": "<keyname>",
            "signatureAlgorithm": "SHA224_WITH_RSA_PSS"
        }
    }
    ```

|||
|---|---|
|`payload`|The data for which you want to create a digital signature. It should be encoded using [base64](https://www.base64encode.org/).|
|`signKeyName`|The label or identifier of the key stored within the HSM. This key is used for generating the digital signature.|
|`signatureAlgorithm`| Specifies the algorithm used for generating the digital signature. In this case, it's SHA224_WITH_RSA_PSS, indicating that SHA224 is used for hashing the payload and RSA with PSS padding scheme is used for signing.|

    **Response:**
    ```
    {
    "signature": "A4Jw63iLqG4gj6lyxi+BxDA4QtlN3PHQGk6BeCmd0zrt4OFn56v0XKQqa4sZ73ukeNSa0c1VcMPT0U6fe1Lt7DCNK9CTeerbanwtEkvGnmTFXt8FyOMABppeNCbLdcDzr7u7in+9jDdQzw0/Q+lVF/4lLFA9QykTtYNh7p+7a9QSv2ZucNCjAK0ief95Kb9KuJ6SjyV4jeiI8yIsliH/TLjfMswCa+Bmyq53c3QdcHkJDypm3riUHOCXAPn1YpwjwNPy9KFR+0Hyhf2MI2ar051J4F+/zooYjYJHYggYBnJ6LyOShudXOIH4UKjsgt2tkrMHSCHcnsappRgq4oRQ+Q=="
    }
    ```
  </TabItem>
  <TabItem value="curl" label="CURL">
    **Request:** Replace `<keyname>` and `<bearer_token>`.
    ```sh {2,7-9}
    curl --request POST \
    --url "https://tsb-demo.cloudshsm.com/v1/synchronousSign" \
    --header 'accept: application/json' \
    --header 'Content-Type: application/json' \
    --data '{
        "signRequest": {
            "payload": "UGF5bG9hZA==",
            "signKeyName": "REST-API_Tutorial123",
            "signatureAlgorithm": "SHA224_WITH_RSA_PSS"
        }
    }'
    ```
    **Response:**
    ```
    {"signature":"rF43P3iGgCvUZnoFY3+Qmyc8zxdydJ7RKcrQ+eQM4eM+7FmlAMAIOw9h2ae+/On4hyRY/VT/9SLMVe1UU/J1gIp9giX9zr3ycYBZ8r5l9wYuh1HVv5J9YX478ppLI6DoHQSw/8cxEPFzB2dZLmov5g51nJI9qvd8qJjgwYd/iYhAN9Vf7XlSa9LhoL/73ZOR3JSb68pWlfXVXIhN0ShaIoduE9ba+2Z7QkNG0bSXjS1j8tI9LiYLfBX2yEYyJgBaNFlBGI5EdlblxXMgNzBk3IMpQIPMYZ06N/3amIL+x46bb74ERZMGYcg0TXlEMd4KQTFD6wtUhkmOFCbRnDaq2w=="}
    ```
  </TabItem>
</Tabs>


---


## What's next?

**Crypto Operations**
- Encrypt & Decrypt a Payload [Sample](/tsb/Tutorials/Encryption/EncryptDecrypt.md)
- File Encryption [Sample](/tsb/Tutorials/Encryption/FileEncryption.md)

**Deployment Guides**
- Read the [Deployment Guide - TSB as a Service](/tsb/Quickstart/cloud-quickstart.md)
- Read the [Deployment Guide - on Premise Installation (Docker)](/tsb/Installation/On-Premise-Installation.md)

**Transaction Security Broker**
- [Tutorial and Sample](/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker) of TSB

**More**
- [Concepts](/tsb/category/concepts)
- More [Tutorials](/tsb/category/tutorial)

