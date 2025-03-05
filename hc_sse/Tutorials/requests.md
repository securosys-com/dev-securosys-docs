---
sidebar_position: 3
title: Requests - Secrets Engine plugin for HashiCorp Vault
sidebar_label: Requests
description: Requests for Securosy' Secrets Engine plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---

# Requests
In case a key has a **Smart Key Attribute (SKA)** policy attached, a request object is returned instead of an instant result response, indicating the required approvals to be collected to process this request. 

For example:

```shell
Key                 Value
---                 -----
approvedBy          map[]
executionTime       n/a
id                  a0d1dc5c-3c0a-415f-a184-6eaffcb9fd07
notYetApprovedBy    map[NameOfApprover:MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAouyYMgsCbxdr6cN7EDIR4ZcB82/fAIZusqyNXpX6gcRTwnrxZfIlyATdAm7ascxgYIK+L2M9UFVKTPUxus/Hzfuq0Fro5tdH+DWwfYQtcB5vap3UTw3yNpi6/MUe1x1Odwwi3no3jE2OuF1k6wjyrbWBkyktF3g8BmOD0DFpGl4IIlE8u1NgOMyAzlIuzAiyl4aCHrddhfV6gFtrRqfpUMz0twXYYoHlK0khzVEVn757WZZcIQFZmjKMfp/Yl/CAkBrTGVnFHMmNOYq7L9vhoR71rPmU9u2sy6IaT97qox/e7HSW47N2YNSiuJeq08I3Tn/kyw6+pSjAMu4A48PrfQIDAQAB]
rejectedBy          map[]
request             map[key:custom_rsa3 keyLabel:CUSTOM_RSA_4]
result              n/a
status              PENDING
type                UnBlock
...
etc.
```

To handle such requests there are the following additional commands:
## List
List all requests initialized in **Secrets Engine**
    ```shell
    $ vault list securosys-hsm/requests
    ```
    or for a more detailed list
    ```shell
    $ vault list -detailed securosys-hsm/requests
    ```
    
    ```shell
    curl --location --request LIST '<server_addr>/v1/securosys-hsm/requests' \
    --header 'X-Vault-Token: <vault_access_token>'
    ```
## Read
Show detailed request information
    
    ```shell
    $ vault read securosys-hsm/requests/{id} 
    ```
    ```shell
    curl --location --request GET '<server_addr>/v1/securosys-hsm/requests/{id}' \
    --header 'X-Vault-Token: <vault_access_token>'
    ```
## Delete
Remove a request from **Secrets Engine** and **HSM**
    ```shell
    $ vault delete securosys-hsm/requests/{id} 
    ```
    ```shell
    curl --location --request DELETE '<server_addr>/v1/securosys-hsm/requests/{id}' \
    --header 'X-Vault-Token: <vault_access_token>'
    ```
