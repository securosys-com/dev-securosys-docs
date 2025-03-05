---
sidebar_position: 2
title: MariaDB - Secrets Engine plugin for HashiCorp Vault
sidebar_label: MariaDB
description: MariaDB Integration for Securosy' Secrets Engine plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---


# MariaDB

Encryption on **MariaDB** can be enabled using the existing plugin [Hashicorp Key Management Plugin](https://mariadb.com/kb/en/hashicorp-key-management-plugin/).

This integration stores generated secret in **Secrets Engine**, encrypted by the provided key.

**Supported Key Types**/**Algorithm** combinations:

| Key Type | Algorithm |
|----------|:-------------:|
| **RSA** | RSA_PADDING_OAEP_WITH_SHA512<br/>RSA<br/> RSA_PADDING_OAEP_WITH_SHA224<br/> RSA_PADDING_OAEP_WITH_SHA256<br/>RSA_PADDING_OAEP_WITH_SHA1<br/>RSA_PADDING_OAEP<br/> RSA_PADDING_OAEP_WITH_SHA384<br/> RSA_NO_PADDING|
|**AES**|AES_GCM<br/>AES_CTR<br/>AES_ECB<br/>AES_CBC_NO_PADDING<br/>AES |
| **CHACHA20** | CHACHA20<br/>CHACHA20_AEAD|
| **CAMELLIA** | CAMELLIA<br/>CAMELLIA_CBC_NO_PADDING<br/>CAMELLIA_ECB |
|**TDEA**| TDEA_CBC<br/>TDEA_ECB<br/>TDEA_CBC_NO_PADDING |
|**TDEA**| TDEA_CBC<br/>TDEA_ECB<br/>TDEA_CBC_NO_PADDING |

:::note Note

- The Secrets Engine plugin supports **asynchronous decrypt operation** using key type **RSA** with **policy** with setup **ruleUse**.
- Using the key with policy will **stop** the decrypt operation and **wait for approvals** to be collected.

:::
The following actions must be performed before setting up encryption on MariaDB:

1) [Create / Register key](/hc_sse/Tutorials/manage_keys) into **Secrets Engine**
1) Generate new **secret** and encrypt it using stored key 
    ```shell
    $ vault write securosys-hsm/integrations/mariadb/{secret-name}     
    keyName={key-name-from-secret-engine}
    cipherAlgorithm={cipher-algorithm}
    [additionalAuthenticationData={additional-authentication-data}]
    [tagLength={tag-length}]
    [password={password-for-a-key}]
    ```

    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/integrations/mariadb/{secret-name} ' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'keyName={key-name-from-secret-engine}' \
    --data-urlencode 'cipherAlgorithm={cipher-algorithm}' \
    --data-urlencode 'tagLength={tag-length}' \
    --data-urlencode 'additionalAuthenticationData={additional-authentication-data}' \
    --data-urlencode 'password={password-for-a-key}'
    ```
:::tip Tip

Every request to this endpoint using same **key name** and **secret name** will rotate the secret.

:::

3) The final step is add the following configuration to **my.cfg**:
    ```ini
    [mariadb]
    plugin-load-add=hashicorp_key_management.so
    loose-hashicorp-key-management
    loose-hashicorp-key-management-vault-url="{vault_address}/v1/securosys-hsm/integrations/mariadb/{secret_name}/?key_name={key-name}&cipher_algorithm={cipher_algorithm}&tag_length={tag_length}&aad={additional_authentication_data}&password={password}&version="
    loose-hashicorp-key-management-token="{vault_access_token}"
    loose-hashicorp-key-management-check-kv-version="off"
    #max timeout is 86400 seconds
    loose-hashicorp-key-management-timeout=3000 
    loose-hashicorp-key-management-retries=0
    loose-hashicorp-key-management-use-cache-on-timeout="on"
    loose-hashicorp-key-management-caching-enabled="on"
    #1 year in miliseconds
    loose-hashicorp-key-management-cache-timeout=31556952000 
    #1 year in miliseconds
    loose-hashicorp-key-management-cache-version-timeout=31556952000

    #Example of innodb config
    innodb_encrypt_tables = ON
    innodb_encrypt_temporary_tables = ON
    innodb_encrypt_log = ON
    innodb_encryption_threads = 4
    innodb_encryption_rotate_key_age = 1
    ```

:::note Note

- In **loose-hashicorp-key-management-vault-url**, the URL must end with **&version=**.
- The plugin from **MariaDB** will automatically add **number of secret version** to the end of the URL.

:::
    