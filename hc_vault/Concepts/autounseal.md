---
sidebar_position: 2
title: Auto-Unseal - HashiCorp Vault Community
sidebar_label: Auto-Unseal
description: Auto-Unseal - HashiCorp Vault Community
keywords: [hsm, cloud hsm]
---

# Auto-Unseal with Hardware Security Module (HSM)

Auto-unseal can be achieved via REST (TSB) interface, connected to Securosys Primus HSM or CloudHSM. In the configuration file **config.hcl**, add the additional seal configuration section **seal "securosys-hsm"**:
```sh
  seal "securosys-hsm" {
    //Define the unseal key stored on the HSM. If the key does not exist, a new key is created with the attributes defined in this file.
    key_label = "replace-me_key_label"
    //Key password
    key_password = "password"
    //RestApi url for calling requests to Securosys HSM via REST(TSB)
    tsb_api_endpoint = "replace-me_TSB_Endpoint" //https://rest-api.cloudshsm.com, https://sbx-rest-api.cloudshsm.com, https://primusdev.cloudshsm.com
    //Define the authorization type (TOKEN, CERT, NONE)
    auth = "TOKEN"
    //auth = TOKEN: define the JWT token to authorize at TSB
    bearer_token = "replace-me_BearerToken"
    //auth = CERT: mTLS, define the certificate to authorize at TSB
    //cert_path = "replace-me_with_cert_path"
    //Approval checking frequency in seconds
    check_every = 5
    //Wait for user approvals in seconds
    approval_timeout = 30

    //The following section defines the simple ska policy (SKA, ruleUse), applied only in case a new unseal key is created on the HSM.

    //FORMAT OBJECT:
    //name - RSA public key (pem)
    policy = <<EOF
    {
      "replace-me_nameOfApprover":"replace-me_ApproverPublicKey"
    }
    EOF
  }

```

**policy**:

Define a simple Smart Key Attribute (SKA) **ruleUse** policy, used for initializing the Vault when creating a new unseal key on the Hardware Security Module (HSM).

To use a full (complex) SKA policy, the key and policy must be created outside of Vault (e.g. via REST interface, Securosys Secrets Engine, ...). Vault will gather an existing unseal key policy from the HSM. If a policy exists on the unseal key, Vault will wait for all necessary approvals before unsealing (HSM enforced).
:::note 
The configuration section **seal securosys-hsm** is only validated on startup of the **Hashicorp Vault Server**.
:::

**Important** After the ```operator init``` command Vault will print the Shamir **Unseal Keys** and the **Initial Root Token**:

```
Unseal Key 1: <unseal_key>
...
Initial Root Token: <root_key>

```

Note down these values, and store them in a safe place for disaster recovery.


##### Using Shamir Secrets

:::note 
This works only with normal Shamir secrets. Using seal "securosys-hsm" the Vault is automatically unsealed on startup.
:::
**Unseal** the server with the command ```vault operator unseal <unseal_key>``` and write system env with root token using this command ```vault login <root-token>```

Alternatively the **Web UI** can be used.
