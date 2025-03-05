---
sidebar_position: 4
title: FAQ - Secrets Engine plugin for HashiCorp Vault
sidebar_label: FAQ
description: Frequently Asked Questions for Securosy' Secrets Engine plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---

# Frequently Asked Questions

1) > **I got the error 'no handler for route "securosys-hsm/...". route entry found, but backend is nil.'**<br/> 
    In case of ```no handler for route "securosys-hsm/...". route entry found, but backend is nil.``` error, try to replace the secrets engine binary and to register the new upgraded plugin. See [How to run > Upgrade plugin](/hc_sse/Installation/#4-upgrade-the-plugin)

1) > **Why I don't get a public key and policy on some key types**<br/> 
    Some key types are symmetric, and therefore don't have a public key nor a SKA policy.

1) > **I have an error on unwrapping a key - status: 500, body: ```{"errorCode":701,"reason":"res.error.in.hsm","message":"Error unwrapping key"}```**<br/> 
    Probably the provided key label is already in use with another key on the HSM, or the request contains a policy for a symmetric key.
