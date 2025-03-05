---
sidebar_position: 1
title: MariaDB Integration - Secrets Engine plugin for HashiCorp Vault
sidebar_label: MariaDB Integration
description: MariaDB Integration for Securosy' Secrets Engine plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---

# MariaDB Integration usage example

This example uses the default configuration for **Hashicorp Vault dev server**.
| Data | Value |
|----------|:-------------:|
| **vault address** | https://localhost:8200 |
| **vault access token** | root |
1) **Create key** *MariaDBEncryptionKey* with key size *4096* with attributes at last "decrypt" equals *true* on HSM and store it as *mariadb_encryption_key* on **Secrets engine**
    ```shell
    $ vault write securosys-hsm/keys/rsa/mariadb_encryption_key 
    keyLabel="MariaDBEncryptionKey"
    keySize=4096 
    attributes='{"decrypt": true,"sign": false,"unwrap": false,"derive": true,"sensitive": true,"extractable": false,"modifiable": false,"copyable": false,"destroyable": true}'
    ```
    or
    ```shell
    curl --location --request PUT 'https://localhost:8200/v1/securosys-hsm/keys/rsa/mariadb_encryption_key' \
    --header 'X-Vault-Token: root' \
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'keyLabel=MariaDBEncryptionKey' \
    --data-urlencode 'keySize=4096' \
    --data-urlencode 'attributes={
        "decrypt": true,
        "sign": false,
        "unwrap": false,
        "derive": true,
        "sensitive": true,
        "extractable": false,
        "neverExtractable": true,
        "modifiable": false,
        "copyable": false,
        "destroyable": true
    }'
    ```
1) Generate new **secret** called *mariadb_secret* and **encrypt it** using cipher algorithm *RSA* and stored key *mariadb_encryption_key* in **Secrets engine** 
    ```shell
    $ vault write securosys-hsm/integrations/mariadb/mariadb_secret   
    keyName=mariadb_encryption_key
    cipherAlgorithm=RSA
    ```
    or
    ```shell
    curl --location --request PUT 'https://localhost:8200/v1/securosys-hsm/integrations/mariadb/mariadb_secret ' \
    --header 'X-Vault-Token: root'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'keyName=mariadb_encryption_key' \
    --data-urlencode 'cipherAlgorithm=RSA'
    ```
3. Configure **MariaDB plugin** "Hashicorp Key Management" in database configuration in **my.cnf**

    ```ini
    [mariadb]
    plugin-load-add=hashicorp_key_management.so
    loose-hashicorp-key-management
    loose-hashicorp-key-management-vault-url="https://localhost:8200/v1/securosys-hsm/integrations/mariadb/mariadb_secret/?key_name=mariadb_encryption_key&cipher_algorithm=RSA&version="
    loose-hashicorp-key-management-token="root"
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

