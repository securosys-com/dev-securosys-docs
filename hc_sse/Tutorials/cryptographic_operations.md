---
sidebar_position: 2
title: Cryptographic Operations - Secrets Engine plugin for HashiCorp Vault
sidebar_label: Cryptographic Operations
description: Cryptographic Operations for Securosy' Secrets Engine plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---


# Cryptographic Operations

Below are the cryptographic operations that can be performed using keys on your Hardware Security Module (HSM).

## Encrypt 
Encrypt a payload

    ```shell
    $ vault write securosys-hsm/operation/encrypt/{key-name} 
    payload={base64-encoded-string}
    password={password-of-the-key}
    cipherAlgorithm={cipher-algorithm}
    tagLength={tag-length}
    additionalAuthenticationData={additional-authentication-data}
    ```

    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/operation/encrypt/{key-name}' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'payload={base64-encoded-string}' \
    --data-urlencode 'password={password-for-a-key}' \
    --data-urlencode 'cipherAlgorithm={cipher-algorithm}' \
    --data-urlencode 'tagLength={tag-length}' \
    --data-urlencode 'additionalAuthenticationData={additional-authentication-data}'
    ```

## Decrypt
Decrypt an encrypted payload.

In case the referenced key has a policy attached, then a request-id is returned indicating the required approvals to collect.

More information in [this section](/hc_sse/Tutorials/requests).

:::tip Tip

The **keyVersion** has to be provided in this request, either by adding it in the url (e.g. `securosys-hsm/operation/decrypt/{key-name}/{key-version}`), or by passing it as parameter (e.g.`keyVersion={key-version}`).

:::

```shell
    $ vault write securosys-hsm/operation/decrypt/{key-name} 
    password={password-for-a-key}
    keyVersion={key-version}
    encryptedPayload={base64-encoded-string}
    cipherAlgorithm={cipher-algorithm}
    initializationVector={initialization-vector}
    tagLength={tag-length}
    additionalAuthenticationData={additional-authentication-data}
    ```
    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/operation/decrypt/{key-name}' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'encryptedPayload={base64-encoded-string}' \
    --data-urlencode 'keyVersion={key-version}' \
    --data-urlencode 'cipherAlgorithm={cipher-algorithm}' \
    --data-urlencode 'password={password-for-a-key}' \
    --data-urlencode 'tagLength={tag-length}' \
    --data-urlencode 'initializationVector={initialization-vector}' \
    --data-urlencode 'additionalAuthenticationData={additional-authentication-data}'
```
## Sign
Sign a payload

In case the referenced key has a policy attached, then a request-id is returned, indicating the required approvals to be collected.

More information in [this section](/hc_sse/Tutorials/requests).

    ```shell
    $ vault write securosys-hsm/operation/sign/{key-name}
    password={password-for-the-key}
    signatureAlgorithm={algorithm}
    payload={payload-base64}
    payloadType={payload-type}
    metaData={meta-data-base64}
    metaDataSignature={meta-data-signature}
    ```

    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/operation/sign/{key-name}' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'signatureAlgorithm={algorithm}' \
    --data-urlencode 'payload={payload-base64}' \
    --data-urlencode 'payloadType={payload-type}' \
    --data-urlencode 'password={password-for-a-key}' \
    --data-urlencode 'metaData={meta-data-base64}' \
    --data-urlencode 'metaDataSignature={meta-data-signature}'
    ```

## Verify    
Verify the signature of a signed payload
:::tip Tip
    The **keyVersion** has to be provided in this request, either by adding it in the url (e.g. `securosys-hsm/operation/verify/{key-name}/{key-version}`), or by passing it as parameter (e.g.`keyVersion={key-version}`).
:::

    ```shell
    $ vault write securosys-hsm/operation/verify/{key-name}
    password={password-for-the-key}
    signatureAlgorithm={algorithm}
    payload={payload-base64}
    signature={signature}
    ```

    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/operation/verify/{key-name}' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'signatureAlgorithm={algorithm}' \
    --data-urlencode 'payload={payload-base64}' \
    --data-urlencode 'password={password-for-a-key}' \
    --data-urlencode 'signature={meta-data-signature}'
    ```

## Wrap    
Wrap a key with another (wrapper) key

    ```shell
    $ vault write securosys-hsm/operation/wrap/{key-to-be-wrapped}/{wrap-key-name}
    keyToBeWrappedPassword={password-for-first-key}
    wrapKeyPassword={password-for-second-key}
    wrapMethod={wrap-method}
    ```

    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/operation/wrap/{key-to-be-wrapped}/{wrap-key-name}' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'keyToBeWrappedPassword={password-for-first-key}' \
    --data-urlencode 'wrapKeyPassword={password-for-second-key}' \
    --data-urlencode 'wrapMethod={wrap-method}'
    ```
    
## Unwrap
Unwrap a key using a wrapper key
In case the referenced key has a policy attached, then a request-id is returned, indicating the required approvals to collect.  

More information in [this section](/hc_sse/Tutorials/requests).

    > **Note:** The **keyVersion** has to be provided in this request, either by adding it in the url (e.g. `securosys-hsm/operation/unwrap/{new-unwrapped-key-name}/{wrap-key-name}/{key-version}`), or by passing it as parameter (e.g.`keyVersion={key-version}`).

    ```shell
    $ vault write securosys-hsm/operation/unwrap/{new-unwrapped-key-name}/{wrap-key-name}
    keyLabel={key-label-for-new-key}
    keyVersion={key-version}
    wrappedKey={wrapped-key-base64-encoded}
    password={password-for-wrap-key}
    wrapMethod={wrap-method}
    simplePolicy={policy} or policy={full-policy} or policy=@policy-file.json
    attributes={attributes}
    ```

    ```shell
    curl --location --request PUT '<server_addr>/v1/securosys-hsm/operation/unwrap/{new-unwrapped-key-name}/{wrap-key-name}' \
    --header 'X-Vault-Token: <vault_access_token>'
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'keyLabel={key-label-for-new-key}' \
    --data-urlencode 'keyVersion={key-version}' \
    --data-urlencode 'wrappedKey={wrapped-key-base64-encoded}' \
    --data-urlencode 'wrapMethod={wrap-method}' \
    --data-urlencode 'simplePolicy={policy}' or --data-urlencode 'policy={full-policy}' \
    --data-urlencode 'password={password-for-wrap-key}'
    --data-urlencode 'attributes={attributes}' \
    ```