---
sidebar_position: 0
title: Unsealing with Securosys Authorization App
sidebar_label: Unsealing with Authorization App
description: Unsealing with Securosys Authorization App
keywords: [hsm, cloud hsm]
---

Vault utilizes Hardware Security Modules (HSM) to encrypt the unsealing key.
Vault generates a decrypt request to the HSM when an SKA policy is in place. This request is then sent to the [Authorization App](/AuthorizationApp/overview).
The Authorization App utilizes a decrypting request to seal the process.
The Authorization App checks for any assigned tasks (decrypting requests) linked to the public key of the Authorization App.
While waiting for approval, Vault remains in a pending state, anticipating the response from the Authorization App.
Upon successful approval of the decrypting request, Vault proceeds with the next steps.

:::tip
For more information on how to deploy, configure and use the Authorization App, please follow our [Authorization App documentation](/AuthorizationApp/overview).
:::

**HashiCorp Vault** uses Hardware Security Modules (HSMs) to encrypt the unsealing key.

When an approval policy is in place, a decrypt request is generated and sent to the **Authorization App**.

The **Authorization App** processes the decrypt request by checking for any pending tasks linked to its public key. While the approval is pending, Vault remains in a waiting state, awaiting the response from the **Authorization App**. Once the decrypt request is approved, Vault proceeds with the next steps in the process.

The Securosys **Authorization App** can be downloaded [here](/AuthorizationApp/downloads).

```yml {9-12}
seal "securosys-hsm" {
  key_label          = ""
  key_password       = ""
  tsb_api_endpoint   = "securosys_tsb_api_url"
  auth               = "TOKEN"
  bearer_token       = "tsb_api_token"
  check_every        = 5
  approval_timeout   = 200
  policy = <<EOF
  {
    "the_name_of_the_approver": "the_public_key_from_Securosys_authorization_app"
  }
EOF
}

```


If ```key_label``` is not defined, then the Authorization App will generate the key to the Hardware Security Module (HSM).

:::tip
To start using the Authorization App, it is necessary to modify your _config.hcl_ file, adjusting both its policy and sealing fields as illustrated in the provided code snippet.
:::

:::note note
Upon initiating the HashiCorp Vault Community Edition for the first time, you will receive the approval request on your Securosys Authorization App. To initiate the vault, SSH into your Docker container and execute the command: ```vault operator init```
:::