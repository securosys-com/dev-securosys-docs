---
sidebar_position: 1
title: Manage Keys - Secrets Engine plugin for HashiCorp Vault
sidebar_label: Manage Keys
description: Manage Keys for Securosy' Secrets Engine plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---

# Manage Keys

The **Secrets Engine** plugin allows to create, modify, and manage keys on your Securosys Hardware Security Modules (HSMs), Cloud & On-premises, with the following command sets:

## List all the keys
List all the keys stored in the **Secrets Engine** plugin:
    ```shell
    $ vault list securosys-hsm/keys
    ```
    or for more a more detailed list:
    ```shell
    $ vault list -detailed securosys-hsm/keys
    ```
    
    ```shell
    curl --location --request LIST '<server_addr>/v1/securosys-hsm/keys' \
    --header 'X-Vault-Token: <vault_access_token>'
    ```
## List the key versions
List all the key versions stored in the **Secrets Engine** plugin:
    ```shell
    $ vault list securosys-hsm/keys/{key-name}
    ```
    or for a more detailed list
    ```shell
    $ vault list -detailed securosys-hsm/keys/{key-name}
    ```
    
    ```shell
    curl --location --request LIST '<server_addr>/v1/securosys-hsm/keys' \
    --header 'X-Vault-Token: <vault_access_token>'
    ```

## Read key information

Read the stored key informaton such as **key label**, **policy** or **public key**:
    ```shell
    $ vault read securosys-hsm/keys/{key-name} 
    ```
    ```shell
    curl --location --request GET '<server_addr>/v1/securosys-hsm/keys/{key-name}' \
    --header 'X-Vault-Token: <vault_access_token>'
    ```

    The result of this command will be
    ```
    Key           Value
    ---           ---
    algorithm     {key-type}            //For example: RSA, AES etc.
    attributes    {key-attributes}
    key_size      {key-size}
    keyLabel      {key-label-hsm}
    policy        {policy}              //If exists
    public_key    {public-key-from-hsm} //If exists. Only in asymetric key
    curveOid      {cureveoid}           //If exists. Only in EC or ED algorithms
    ...
    ```

## Write
Create or update a key on your **Hardware Security Module (HSM)** and store the reference in **Secrets Engine**.

Key types available:
    - **aes**
        > *Required:* **keyLabel**, **attributes** and **keySize**[128,192,256]
        > *Optionally:* **password**

    - **bls**         
        > *Required:* **keyLabel** and **attributes**
        > *Optionally:* **policy** and **password**

    - **camellia**
        > *Required:* **keyLabel**, **attributes** and **keySize**[128,192,256]
        > *Optionally:*  **password**
 
    - **chacha20**
        > *Required:* **keyLabel** and **attributes**
        > *Optionally:* **password**

    - **dsa**
        > *Required:* **keyLabel**, **attributes** and **keySize**[512,1024,2048]
        > *Optionally:* **policy** and **password**
        
    - **ec**
        > *Required:* **keyLabel**, **attributes** and **curveOid**
        > *Optionally:* **policy** and **password**
        
    - **ed**
        > *Required:* **keyLabel**, **attributes** and **curveOid**
        > *Optionally:* **policy** and **password**
        
    - **rsa**
        > *Required:* **keyLabel**, **attributes** and **keySize**[1024,2048,3072,4096]
        > *Optionally:* **policy** and **password**
        
    - **tdea**
        > *Required:* **keyLabel**, **attributes**
        > *Optionally:* **password**
:::info Fields description 
    All the fields are described in [this section](key_arguments.md).
:::
:::warning Important 
By default, all the keys created via **Secrets Engine** have set the key attributes [destroyable] and [modifiable]. These attributes can be changed or extended by defining them in the `attributes` argument.
:::
    ```shell
    $ vault write securosys-hsm/keys/{key-type}/{key-name} 
    keyLabel="{key-label-hsm}"
    keySize={key-size} 
    #{key-attriute}:{true/false}
    attributes='{"decrypt": true,"sign": false,"unwrap": true,"derive": true,"sensitive": true,"extractable": false,"modifiable": true,"copyable": false,"destroyable": true}'
    ```
    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/keys/{key-type}/{key-name}' \
    --header 'X-Vault-Token: <vault_access_token>' \
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'keyLabel={key-label-hsm}' \
    --data-urlencode 'keySize={key-size}' \
    --data-urlencode 'attributes={
        #{key-attriute}:{true/false}
        "decrypt": true,
        "sign": false,
        "unwrap": true,
        "derive": true,
        "sensitive": true,
        "alwaysSensitive": true,
        "extractable": false,
        "neverExtractable": true,
        "modifiable": true,
        "copyable": false,
        "destroyable": true
    }'
    ```

    Or here an example creating a key with attached simple ska policy:
 
    ```shell
    $ vault write securosys-hsm/keys/{key-type}/{key-name} 
    keyLabel="{key-label-hsm}"
    keySize={key-size} 
    #{key-attriute}:{true/false}
    attributes='{"decrypt": true,"sign": false,"unwrap": true,"derive": true,"sensitive": true,"extractable": false,"modifiable": true,"copyable": false,"destroyable": true}' 
    simplePolicy=-<<EOF
        {
        "NameOfApprover": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArBohRHhXXjQMNlxWMmCX0fxbpcMyu3bwBerkfeTl8QoOZbDV003t1n9drCuGOJJP16sZRBkYa5C7QkFCyb10Lbp1sp8jqWVu5PQy9qEaLl4y2BW+AOs0pURv1nlyo+gFgJD6lX0QmtZDjaD98C/wC5RVXipr4nJmT5XvwCPmgz9TpgVgFMwrflPJK9mHgYKwvmPODLYSLbohkj4TWKAoL417URhPazNWJBC7fKRui3EA7a8yzuzOSVgGxjY3aeqitmZyCTJtWa2U2/UwLZRT2ISwXv0zvsBhRSbXXcFdCApgKiy9uL1tPq40DnT8cesZzKd8hDYJ5S34wwmSZKbtGwIDAQAB"
        }
    EOF
    ```
    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/keys/{key-type}/{key-name}' \
    --header 'X-Vault-Token: <vault_access_token>' \
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'keyLabel={key-label-hsm}' \
    --data-urlencode 'keySize={key-size}' \
    --data-urlencode 'attributes={
        #{key-attriute}:{true/false}
        "decrypt": true,
        "sign": false,
        "unwrap": true,
        "derive": true,
        "sensitive": true,
        "alwaysSensitive": true,
        "extractable": false,
        "neverExtractable": true,
        "modifiable": true,
        "copyable": false,
        "destroyable": true
    }' \
    --data-urlencode 'simplePolicy={
        #{name}:{public_key}
        "NameOfApprover": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArBohRHhXXjQMNlxWMmCX0fxbpcMyu3bwBerkfeTl8QoOZbDV003t1n9drCuGOJJP16sZRBkYa5C7QkFCyb10Lbp1sp8jqWVu5PQy9qEaLl4y2BW+AOs0pURv1nlyo+gFgJD6lX0QmtZDjaD98C/wC5RVXipr4nJmT5XvwCPmgz9TpgVgFMwrflPJK9mHgYKwvmPODLYSLbohkj4TWKAoL417URhPazNWJBC7fKRui3EA7a8yzuzOSVgGxjY3aeqitmZyCTJtWa2U2/UwLZRT2ISwXv0zvsBhRSbXXcFdCApgKiy9uL1tPq40DnT8cesZzKd8hDYJ5S34wwmSZKbtGwIDAQAB"
    }'
    ```
 
    Where:
    - `simplePolicy` has to be a **JSON** object in which **Key** is the name of the approval (or the approver) and
    - **Value** has to be a valid **RSA public key** (without the "-- Begin..." and "-- End..." lines nor line breaks).

    The result of these commands will show information about the created key.

:::tip Tip 

Full Smart Key Attribute (SKA) policy **json** can be provided by using the **policy** attribute in place of **simplePolicy**.

Since a policy JSON statement can be quite large, it may be challenging to edit it on the command line. In such case, it is recommended to attach a file with the json, using the attribute **"policy=@file.json"**.

[View an example of a policy json file](./examples/policy_example.md).

:::

## Register a key
Register an existing key stored on the Hardware Security Module (HSM) to Secrets Engine
    ```shell
    $ vault write securosys-hsm/keys/{key-name}/register keyLabel={label-of-key-on-hsm}
    ```
    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/keys/{key-name}/register' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'keyLabel={label-of-key-on-hsm}'
    ```
:::tip Tip 

This ```key``` will be registered in Secrets Engine with the name ```{key-name}```

:::

## Create Key by Type Name
Create a key using key types compatible with HashiCorp Key Management. (https://developer.hashicorp.com/vault/api-docs/secret/key-management)

    **Available key types:**
    | Key Type  |      Description      |
    |----------|-------------|
    | aes256-gcm96 |  AES-GCM with a 256-bit AES key and a 96-bit nonce (**symmetric**) |
    | rsa-2048 |  RSA with bit size of 2048 (**asymmetric**) |
    | rsa-3072 |  RSA with bit size of 3072 (**asymmetric**) |
    | rsa-4096 |  RSA with bit size of 4096 (**asymmetric**) |
    | ecdsa-p256 |  ECDSA using the P-256 elliptic curve (**asymmetric**) |
    | ecdsa-p384 |  ECDSA using the P-384 elliptic curve (**asymmetric**) |
    | ecdsa-p521 |  ECDSA using the P-521 elliptic curve (**asymmetric**) |

    ```shell
    $ vault write securosys-hsm/keys/type/{key-type-name}/{key-name} keyLabel={label-of-key-on-hsm}
    algorithm={key-algorithm}
    attributes={key-attributes}
    password={password}
    simplePolicy={policy} or policy={full-policy} or policy=@policy-file.json
    ```
    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/keys/type/{key-type-name}/{key-name}' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'keyLabel={label-of-key-on-hsm}' \
    --data-urlencode 'algorithm={key-algorithm}' \
    --data-urlencode 'attributes={key-attributes}' \
    --data-urlencode 'password={password}' \
    --data-urlencode 'simplePolicy={policy}' or --data-urlencode 'policy={full-policy}'
    ```
:::tip Tip 

This key will be generated in Secrets Engine with the name ```{key-name}```

:::
## Import a key
Import a new key into the HSM
    ```shell
    $ vault write securosys-hsm/keys/{key-name}/import 
    keyLabel={label-of-key-on-hsm}
    privateKey={private-key-base64}
    publicKey={public-key-base64}
    secretKey={secret-key-base64}
    certificate={certificate-base64}
    algorithm={key-algorithm}
    attributes={key-attributes}
    simplePolicy={policy} or policy={full-policy} or policy=@policy-file.json
    ```
    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/keys/{key-name}/import' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'keyLabel={label-of-key-on-hsm}' \
    --data-urlencode 'privateKey={private-key-base64}' \
    --data-urlencode 'publicKey={public-key-base64}' \
    --data-urlencode 'secretKey={secret-key-base64}' \
    --data-urlencode 'certificate={certificate-base64}' \
    --data-urlencode 'algorithm={key-algorithm}' \
    --data-urlencode 'attributes={key-attributes}' \
    --data-urlencode 'simplePolicy={policy}' or --data-urlencode 'policy={full-policy}'
    ```
:::tip Tip 

This key will be labeled in Secrets Engine with ```{key-name}```

:::
    
## Export a key
Export public_key, private_key, or secret from a key stored on the HSM
    ```shell
    $ vault write securosys-hsm/keys/{key-name}/export [password={password-for-a-key}]
    ```
    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/keys/{key-name}/export' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'password={password-for-a-key}'
    ```
:::note Note

Omit the **password** parameter in case no password is set.

:::
    
## Modify a key
Modify the Smart Key Attreibute (SKA) policy of a key stored on the Hardware Security Module (HSM).
In case the key has a policy attached, then a request-id is returned indicating the required approvals to collect. See section [Requests](/hc_sse/Tutorials/requests).
    ```shell
    $ vault write securosys-hsm/keys/{key-name}/modify     
    [simplePolicy={policy} | policy={full-policy} | policy=@policy-file.json]
    [password={password-for-a-key}]
    ```
    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/keys/{key-name}/modify' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'simplePolicy={policy}' or  --data-urlencode 'policy={full-policy}' \
    --data-urlencode 'password={password-for-a-key}'
    ```
:::note Note

Omit the **password** parameter in case no password is set.

:::
    
## Update the key password
Modify the password of a key on the HSM

    ```shell
    $ vault write securosys-hsm/keys/{key-name}/update-password password={current-password} newPassword="{new-password}"
    ```
    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/keys/{key-name}/update-password' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'password={current-password}' \
    --data-urlencode 'newPassword={new-password}' \
    ```
## Rotate a Key    
Rotate a key. A new key will be generated on the HSM with the same base name as the original key with an incremented version tag at the end of the original key name (_v2, _v3, ...). The previous key will remain on the HSM. 

    ```shell
    $ vault write securosys-hsm/keys/{key-name}/rotate
    ```
    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/keys/{key-name}/rotate' \
    --header 'X-Vault-Token: <vault_access_token>' \
    --header 'Content-Type: application/x-www-form-urlencoded' 
    ```
:::tip Tip 

Decrypt, verify, unwrap etc. is still possible by providing the parameter  **keyVersion** in the request. All other operations like encrypt, sign, wrap, block, unblock, password etc. will always use the last key version.

:::

## Block a Key
Block a key stored on the HSM
In case the key has a policy attached, then a request-id is returned indicating the required approvals to collect. See section [Requests](/hc_sse/Tutorials/requests).
    ```shell
    $ vault write securosys-hsm/keys/{key-name}/block [password={password-for-a-key}]
    ```
    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/keys/{key-name}/block' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'password={password-for-a-key}'
    ```
:::note Note

Omit the **password** parameter in case no password is set.

:::

## Unblock a key
Unblock a key stored on the HSM 
In case the key has a policy attached, then a request-id is returned indicating the required approvals to collect. See section [Requests](/hc_sse/Tutorials/requests).
    ```shell
    $ vault write securosys-hsm/keys/{key-name}/unblock [password={password-for-a-key}]
    ```
    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/keys/{key-name}/unblock' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'password={password-for-a-key}'
    ```
:::note Note

Omit the **password** parameter in case no password is set.

:::
    
## Delete a Key
Remove a key from the **HSM** and **Secrets Engine**
    ```shell
    $ vault delete securosys-hsm/keys/{key-name} [removeFromHSM=true]
    ```
    ```shell
    curl --location --request DELETE '<server_addr>/v1/securosys-hsm/keys/{key-name}' \
    --header 'X-Vault-Token: <vault_access_token>'
    ```
:::tip Tip 
This operation removes the key only from the **Secrets Engine**. It does not remove the key from the **HSM**. To remove all key versions from the HSM as well, then add the property **removeFromHSM** with **_true_** value.
::: 
    
## Key attestation

Fetch a key attestation from the HSM in XML format, signed with the HSMs attestation key:
    ```shell
    $ vault read securosys-hsm/keys/{key-name}/xml
    ```
    ```shell
    curl --location --request GET '<server_addr>/v1/securosys-hsm/keys/{key-name}/xml' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    ```
