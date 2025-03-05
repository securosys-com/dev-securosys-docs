---
sidebar_position: 1
title: File Encryption Using ECIES and Cloud HSM
sidebar_label: File Encryption (ECIES)
description: Learn how to encrypt and decrypt files using ECIES with Cloud Hardware Security Modules (HSM).
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, ECIES, file encryption, file decryption, elliptic curve cryptography, ECC encryption, symmetric encryption, HSM encryption, HSM decryption, HSM integration]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# File Encryption (ECIES)

ECIES is a hybrid encryption scheme which allows to encrypt data to an ECC public key but using symmetric cryptography for encryption and authentication. The symmetric keys are derived from the receiver's public key and an ephemeral private key of the sender with an ECDH agreement. The ephemeral key pair is used only for a single message: its private key will be deleted after encryption and the ephemeral public key is part of the cryptogram such that the receiver can perform the same ECDH agreement and get the same shared symmetric keys.

The `REST_API` license is required to use ECIES.

## Create EC Key

**POST**: [/v1/key](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

Description: Create a Ec Key request

Replace `<keyname>`.

```js
{
  "label": "<keyname>",  
  "algorithm": "EC",
  "curveOid": "1.3.132.0.34",
  "attributes": {
    "decrypt": true,
    "verify": false,
    "sign": false,
    "wrap": false,
    "unwrap": false,
    "derive": true,
    "bip32": false,
    "extractable": false,
    "modifiable": true,
    "destroyable": true,
    "sensitive": true
  },
  "policy": null
}
```

## Encrypt a file

**POST:** [/v1/fileEncrypt](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/fileEncryption)

<Tabs groupId="device-setup">
  <TabItem value="swagger" label="Swagger" default>
    Replace `<keyname>`
    ```js
    {
      "fileEncryptRequest": {
        "encryptKeyName": "<keyName>"
      }
    }
    ```
  </TabItem>
  <TabItem value="curl" label="CURL">
    _If you want to use the HSM inside your CI-CD pipeline for encrypting sensitive content you can do with CURL_
    
    Replace `<bearer_token>`, `<keyname>` and `<filename.txt>` for the file to be encrypted.
    ```sh {2,4,8,11}
    curl --request POST \
        --url "https://primusdev.cloudshsm.com/v1/fileEncrypt" \
        --header 'accept: application/octet-stream' \
        --header "Authorization: Bearer <bearer_token>" \
        --header 'Content-Type: multipart/form-data' \
        --form 'SignedFileEncryptRequestDto={
                "fileEncryptRequest": {
                    "encryptKeyName": "<keyname>"
                }
            }' \
        --form 'file=@<filename.txt>' --output test-2022-09-08_17-59-14.primus.encrypted
    ```
  </TabItem>
</Tabs>


## Decrypt a file

**POST:** [/v1/synchronousFileDecrypt](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/fileDecryption)

<Tabs groupId="device-setup">
  <TabItem value="swagger" label="Swagger" default>
    Replace `<keyname>`
    ```js
    {
      "fileDecryptRequest": {
        "decryptKeyName": "<keyname>"
      }
    }
    ```
  </TabItem>
  <TabItem value="curl" label="CURL">
    _If you want to use the HSM inside your CI-CD pipeline for encrypting sensitive content you can do with CURL_
    
    Replace `<bearer_token>`, `<keyname>` and `<encrypted_filename.txt>` for the file to be encrypted.
    ```sh {2,4,8,11}
    curl --request POST \
        --url "https://primusdev.cloudshsm.com/v1/synchronousFileDecrypt" \
        --header 'accept: application/octet-stream' \
        --header "Authorization: Bearer <bearer_token>" \
        --header 'Content-Type: multipart/form-data' \
        --form 'SignedFileDecryptRequestDto={ 
                "fileDecryptRequest": { 
                    "decryptKeyName": "<keyname>" 
                } 
            }' \
        --form 'file=@<encrypted_filename.txt>;type=text/json' --output test.txt
    ```
  </TabItem>
</Tabs>
