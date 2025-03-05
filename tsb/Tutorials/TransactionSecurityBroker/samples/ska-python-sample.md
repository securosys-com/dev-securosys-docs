---
sidebar_position: 0
title: Sample Approval Script (Python)
sidebar_label: Sample Approval Script
description: Learn how to work with SKA-keys using the PrimusHSM Rest-API.
keywords: [Create Key, SKA-key, PrimusHSM Rest-API, cryptographic key management, key creation, smart key attributes, TSB_ENGINE, cryptography, cybersecurity, data security, HSM, hardware security module]
---


## Overview

The [Python script below](#script) showes how to facilitate Smart Key Attributes workflow using ED25519 approver keys. It integrates with the PrimusHSM REST API provided by `primusdev.cloudshsm.com` to create keys, sign payloads, and manage approval tasks programmatically.

## Functionality

The script performs the following tasks:

1. **Create SKA EC Key:**
   - Generates a EC-Key (secp256k1) with SmartKeyAttributes and onboards the ED-Approver to the `ruleUse` policy.
   - **POST** [/v1/key](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

2. **Sign Payload:**
   - Creates a signing request for a payload, specifying key attributes and metadata.
   - **POST** [/v1/sign](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Requests/sign)

3. **Sign Timestamp with Approver Private Key:**
   - Signs a timestamp locally using an ED25519 private key.   

4. **Retrieve Approval Tasks:**
   - Fetches pending approval tasks for a specific approver.
   - **POST** [/v1/filteredSignApprovalTask](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Approval%20Tasks/filterSignTasks)

5. **Send Approval:**
   - Submits signed approvals to finalize tasks.
   - **POST** [/v1/approval](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Approval%20Tasks/approveTask)

6. **Check Request Status:**
   - Retrieves the status of a specific signing request.
   - **GET** [`/v1/request/{id}`](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Requests/getRequestStatus_1)

---

## **Preparation**

### **1. Install Required Libraries**
Ensure Python 3.6+ is installed. Install dependencies with:
```bash
pip install requests cryptography
```

## 2. Generate ED25519 Keys

Use OpenSSL to generate the required ED25519 private and public keys:

```sh
openssl genpkey -algorithm ED25519 -out ed25519_private_key.pem
openssl pkey -in ed25519_private_key.pem -pubout -out ed25519_public_key.pem
```


## 3. Configure the Script

- Replace the placeholder `YOUR_BEARER_TOKEN` in the script with a valid API bearer token.
- Update the `ED_PRIVATE_KEY` and `ED_PUBLIC_KEY` variables with the versions of your generated Approver keys.


## Execution

Execute the script (Replace script_name.py with the actual file name):
```sh
python script_name.py
```

## Monitor Logs
The script provides log messages to track progress and debug errors.

## Script


```py
import base64
import requests
import json
from datetime import datetime, timezone
from cryptography.hazmat.primitives.asymmetric.ed25519 import Ed25519PrivateKey
from cryptography.hazmat.primitives import serialization

# Bearer token for authorization
bearer_token = "YOUR_BEARER_TOKEN"  # Replace with actual token

# openssl genpkey -algorithm ED25519 -out ed25519_private_key.pem
ED_PRIVATE_KEY = [
    'MC4CAQAwBQYDK2VwBCIEIGxvVetMFO9RzrSSq7Vmsv8XIpL+Bn995T5UT1RZEgpS'
]
approver1_sanitized_private_key = f"""
-----BEGIN PRIVATE KEY-----
{ED_PRIVATE_KEY[0]}
-----END PRIVATE KEY-----
"""

# openssl pkey -in ed25519_private_key.pem -pubout -out ed25519_public_key.pem
ED_PUBLIC_KEY = [
    'MCowBQYDK2VwAyEAyAeEQG4954XML1azLbiBdJuqRSc46dNSKY2ntfIHj1o='
]

# Utility functions
def log(message):
    print(f"[LOG] {message}")

def error_log(message):
    print(f"[ERROR] {message}")

# Functions for operations
def create_ska_ec_key(key_name, key_type, curve_oid, approver_public_key):
    url = "https://primusdev.cloudshsm.com/v1/key"
    payload = {
        "label": key_name,
        "algorithm": key_type,
        "curveOid": curve_oid,
        "addressFormat": {"format": "BTC"},
        "attributes": {
            "encrypt": False,
            "decrypt": False,
            "verify": True,
            "sign": True,
            "wrap": False,
            "unwrap": False,
            "derive": True,
            "bip32": True,
            "slip10": True,
            "extractable": False,
            "modifiable": True,
            "destroyable": True,
            "sensitive": True,
            "copyable": False,
        },
        "policy": {
            "ruleUse": {
                "tokens": [
                    {
                        "name": "Token1",
                        "timelock": 0,
                        "timeout": 0,
                        "groups": [
                            {
                                "name": "Group1",
                                "quorum": 1,
                                "approvals": [
                                    {
                                        "type": "public_key",
                                        "name": "ApproverEDTestKeys_Approver1",
                                        "value": approver_public_key,
                                    }
                                ],
                            }
                        ],
                    }
                ]
            },
            "keyStatus": {"blocked": False},
        },
    }
    headers = {
        "Content-Type": "application/json",
        "Authorization": f"Bearer {bearer_token}",
    }

    try:
        response = requests.post(url, headers=headers, json=payload)
        if response.status_code == 201:
            log(f"Create SKA EC Key Response: {response.status_code}")
        else:
            error_log(f"Could not create SKA EC Key: {response.json()}")
            log("Continuing with SKA-Workflow")
    except Exception as e:
        error_log(f"Failed to create SKA EC key: {e}")

def sign_payload(payload, payload_type, sign_key_name, meta_data, signature_algorithm, signature_type):
    url = "https://primusdev.cloudshsm.com/v1/sign"
    payload = {
        "signRequest": {
            "payload": payload,
            "payloadType": payload_type,
            "signKeyName": sign_key_name,
            "metaData": meta_data,
            "signatureAlgorithm": signature_algorithm,
            "signatureType": signature_type,
        }
    }
    headers = {
        "Content-Type": "application/json",
        "Authorization": f"Bearer {bearer_token}",
    }

    try:
        log("Creating Sign-Request for key " + sign_key_name)
        response = requests.post(url, headers=headers, json=payload)
        if response.status_code == 201:
            response_json = response.json()
            log(f"Sign Request ID: {response_json.get('signRequestId')}")
            return response_json.get("signRequestId")
        else:
            error_log(f"Failed to sign payload: {response.status_code}")
    except Exception as e:
        error_log(f"Error in sign payload: {e}")

def sign_with_ed_private_key(private_key_pem, payload):
    private_key = serialization.load_pem_private_key(private_key_pem.encode(), password=None)
    if not isinstance(private_key, Ed25519PrivateKey):
        raise ValueError("Provided key is not an Ed25519 private key.")
    b64_signature = base64.b64encode(private_key.sign(payload)).decode()
    log("Signed Payload " + str(payload) + " with Approver PrivateKey")
    return b64_signature

def filtered_sign_approval_task(current_timestamp, timestamp_signature, approver_public_key, timestamp_digest_algorithm, request_id, detail_level, page_number, page_size, sort_order):
    url = "https://primusdev.cloudshsm.com/v1/filteredSignApprovalTask"
    payload = {
        "timestamp": current_timestamp,
        "timestampSignature": timestamp_signature,
        "approverPublicKey": approver_public_key,
        "timestampDigestAlgorithm": timestamp_digest_algorithm,
        "requestId": request_id,
        "detailLevel": detail_level,
        "paging": {
            "pageNumber": page_number,
            "pageSize": page_size,
            "sortOrder": sort_order,
        },
    }
    headers = {"Content-Type": "application/json"}
    log("Going to fetch approval Task for Approver: " + approver_public_key)
    response = requests.post(url, headers=headers, json=payload)
    return response.json() if response.status_code == 200 else {}

def send_approval(task_id, approval_to_be_signed, signature, approval_digest_algorithm, approver_public_key):
    url = "https://primusdev.cloudshsm.com/v1/approval"
    payload = {
        "id": task_id,
        "approvalToBeSigned": approval_to_be_signed,
        "signature": signature,
        "approvalDigestAlgorithm": approval_digest_algorithm,
        "approverPublicKey": approver_public_key,
    }
    headers = {"Content-Type": "application/json"}
    response = requests.post(url, headers=headers, json=payload)
    log(f"Approval sent for Task ID: {task_id} - Status Code: {response.status_code}")

def get_request_status(sign_request_id):
    url = f"https://primusdev.cloudshsm.com/v1/request/{sign_request_id}"
    headers = {"Authorization": f"Bearer {bearer_token}"}
    response = requests.get(url, headers=headers)
    if response.status_code == 200:
        log(f"Request Status: {response.json().get('status')}, Approved By: {response.json().get('approvedBy', [])}, Result: {response.json().get('result')}")

# Main Function
def main():
    # Validate Bearer token
    if not bearer_token or bearer_token == "YOUR_BEARER_TOKEN":
        error_log("Bearer token is missing or not replaced. Please provide a valid token; Line 23.")
        return
    
    sign_key_name = "SKA_EC_KEY_With_ED_Approvers_1"
    create_ska_ec_key(sign_key_name, "EC", "1.3.132.0.10", ED_PUBLIC_KEY[0])
    sign_request_id = sign_payload("U29tZSBQYXlsb2Fk", "UNSPECIFIED", sign_key_name, "Metadata", "SHA256_WITH_ECDSA", "DER")
    current_timestamp = datetime.now(timezone.utc).isoformat(timespec='milliseconds').replace("+00:00", "Z")
    timestamp_signature = sign_with_ed_private_key(approver1_sanitized_private_key, current_timestamp.encode())
    token_challenge = filtered_sign_approval_task(current_timestamp, timestamp_signature, ED_PUBLIC_KEY[0], "EdDSA", sign_request_id, "level1", 0, 1, "CREATION_DATE_ASC")

    for task in token_challenge.get("tasks", []):
        task_id = task.get("id", "")
        approval_to_be_signed = base64.b64decode(task.get("approvalToBeSigned", ""))
        signed_approval = sign_with_ed_private_key(approver1_sanitized_private_key, approval_to_be_signed)
        send_approval(task_id, task.get("approvalToBeSigned", ""), signed_approval, "EdDSA", task.get("approverPublicKey", ""))

    get_request_status(sign_request_id)

if __name__ == "__main__":
    main()

```