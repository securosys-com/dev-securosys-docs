---
sidebar_position: 5
title: How to Sign a Docker Image Step-by-Step Guide
sidebar_label: Sign Image
description: Learn the complete process for signing Docker images, setting up a local registry, using the ORAS CLI, and creating trust policies for secure artifact verification. Perfect for developers and security-conscious teams.
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Signing a Docker Image
### Securosys Docker Signing Notation Plugin

The Docker image signing procedure below is partly aligned with the actors defined in the [Notary Notation Project Signing Scenario](https://github.com/notaryproject/specifications/blob/main/requirements/scenarios.md#scenario-0-build-publish-consume-enforce-policy-deploy) and the example given in [Notary Notation Create Trust Policy and Verify Artifacts](https://notaryproject.dev/docs/user-guides/tutorials/trust-policy/), where:

- Wabbit Networks company builds, signs and distributes their net-monitor software though public registries, 

- ACME Rockets consumes the net-monitor software from a public registry importing the artifacts and reference artifacts (signatures, SBoMs) into their private registry. The private registry also contains additional artifacts that ACME Rockets sign themselves. 

In the subsequent steps, we set up a registry on the local machine using the ORAS CLI project registry. We proceed to build and tag a Docker image from the net-monitor sources on the GitHub project, and subsequently push it to the local registry. Following this, the image undergoes signing using the key established in [chapter](/docker_signing/Tutorials/CreateSigningKey) and the certificate introduced in [chapter](./GeneratingCertificates).

For the sake of simplicity, the procedure does not differentiate between the roles of the signature issuer and verifier.

:::danger Local Registry Setup
The following example creates a registry with `oras-project/registry` in your local Docker environment. This registry should only be used for development purposes. When using other registries, ensure the registry is compatible with OCI Image specification v1.1.0. Starting with v1.0.0-rc.1 of notation, by default, signatures are stored using OCI Artifact Manifest, which is defined in [OCI Image spec v1.1.0](https://github.com/opencontainers/image-spec/tree/v1.1.0-rc2).
:::

Create a local registry based on the oras-project:

```sh
docker run -d -p 5000:5000 ghcr.io/oras-project/registry:v1.0.0-rc.4
```

:::tip Note
 For Apple silicon, add the `--platform linux/arm64` parameter.
:::

:::danger Registry Version Note
Use `oras-project/registry:v1.0.0-rc.4`. The version `v1.0.0-rc.3` as describe [here](https://notaryproject.dev/docs/user-guides/tutorials/trust-policy/) will fail at the signing command.
:::

Clone from github repository, build the docker image, add image tag (`net-monitor:v1`). Then push it to your local registry: 

```sh
docker buildx build -t localhost:5000/net-monitor:v1 https://github.com/wabbit-networks/net-monitor.git#main

docker push localhost:5000/net-monitor:v1
```

:::danger Use Immutable Digests
Always use the digest value of an image when signing since they are immutable. Tag values are mutable and can reference a different container image than the original signed container image.
:::

Save the digest value of the image from the output of `docker push`. Example:

```js
The push refers to repository [localhost:5000/net-monitor] 

2556c54bfdf3: Pushed 

fb6ca4f9c8d3: Pushed 

ded7a220bb05: Pushed 

v1: digest: sha256: 9bba24af535af5ce658c5cd78c865d574cb65443c643d8538b73ea7d3e9bb64e size: 942
```

Alternatively, you can find the image digest using the `docker inspect` or `docker images --digests` command.
In the above example, the digest value of the image is:

```sh
sha256:9bba24af535af5ce658c5cd78c865d574cb65443c643d8538b73ea7d3e9bb64e
```

Set the value of `$IMAGE` environment variable to the name of the image and its digest value to facilitate the use of subsequent commands:

```sh
IMAGE=localhost:5000/net-monitor@sha256:9bba24af535af5ce658c5cd78c865d574cb65443c643d8538b73ea7d3e9bb64e
```

Use the below command to list the current signatures for the image and confirm there are no signatures listed: 

```sh
notation ls $IMAGE 

localhost:5000/net-monitor@sha256: 9bba24af535af5ce658c5cd78c865d574cb65443c643d8538b73ea7d3e9bb64e has no associated signature 
```

Change the working directory to `$CONFIG_PATH`:

```sh
cd $CONIFG_PATH
```

Use notation sign to sign the image:

```sh
notation sign $IMAGE --key "keyId"
```

Notation sign command parameters:

| Command parameters | Command and parameter description |
|---|---|
| `"keyId": "pluginKeyId"` | Replace the variable with your signing key identifier. |

---

## SKA Keys and Multi-Authorization 

If you have configured your key with multi-authorization policies based on [Smart Key Attributes (SKA)](/tsb/Tutorials/TransactionSecurityBroker/smart-key-attributes), the signing operation will only be executed if the approvals are provided in a timely manner and in the quantity specified by the "usage" policy.

---

## Create a Trust Policy 

Users who consume a signed artifact from a registry use the trust policy to specify trusted identities which will sign the artifacts, and level of signature verification to use. The trust policy must be defined manually in a JSON document. 

Create or edit the notation trust policy `trustpolicy.json` file in the `{NOTATION_CONFIG}` directory. Create the file by executing the command: 

```sh
vi ${HOME}/.config/notation/trustpolicy.json 
```

Add a trust policy with the following structure: 
Go to section "policy" in the `input_example.json` file and prepare the parameters, according to [chapter](./CreateSigningKey#prepare-docker-image-signing-key-parameters) (use any editor you are familiar with):

```js
{
    "version": "1.0",
    "trustPolicies": [
        {
            "name": "trust-policy-example",
            "registryScopes": [ "*" ],
            "signatureVerification": {
                "level" : "strict" 
            },
            "trustStores": [ "ca:valid-example" ],
            "trustedIdentities": [
                "*"
            ]
        }
    ]
}
```

Replace the variables according to your parameters: 

| Policy parameters | Command and parameter description |
|---|---|
| `"version": "1.0"` | The parameter is the version of the trust policy. The supported value is 1.0. |
| `"trustPolicies"` | The parameter represents a collection of trust policies. |
| `"name": "trust-policy-name"` | Replace the variable with the name of your trust policy. |
| `"registryScopes": [ "*" ]` | The parameter determines the registry artifacts to which the trust policy applies. The scope field supports filtering based on fully qualified repository URI<br /> `${registry-name}/${namespace}/${repository-name}`. |
| `"signatureVerification": {...}` | The parameter dictates how signature verification is performed. An _object_ that specifies a predefined verification level. Parameter values:<br /> `strict`, `permissive`, `audit`.<br /> Detailed explanation of each level can be found [here](https://github.com/notaryproject/specifications/blob/main/specs/trust-store-trust-policy.md#signatureverification-details). Keep the level at strict for this guide. |
| `"trustStores": [ "ca:trustStoreName" ]` | The parameter specifies a set of one or more named trust stores, each of which contain the trusted roots against which signatures are verified. Each named trust store uses the format `{trust-store-type}:{named-store}`.Replace the variable with your named store. Currently supported values for trust store types are:<br /> `CA`, `Signing Authority`, `TSA`. |
| `"trustIdentities": "[ "*" ]"` | Identities that are trusted to sign the artifact, supported values:<br /> - E.g. x509.subject: C=US, ST=WA, L=Seattle, O=acme-rockets.io, OU=Finance, CN=SecureBuilder |

---

Example trustpolicy.json file: 

```js
{ 
  "version": "1.0", 

  "trustPolicies": [ 

    { 

      "name": "securosys-trust-policy", 

      "registryScopes": [ "*" ], 

      "signatureVerification": { 

        "level": "strict" 

      }, 

      "trustStores": [ "ca:securosysTrustStore" ], 

      "trustedIdentities": [ "*" ] 
    } 
  ] 
} 
```

For more information on the trust policies for Notation check the following documents: 

- [Notary Notation Trust Policy](https://notaryproject.dev/docs/user-guides/tutorials/trust-policy/) 

- [Notary Notation Trust Policy Specs](https://github.com/notaryproject/specifications/blob/main/specs/trust-store-trust-policy.md#signatureverification-details).

---

## Verify Image

Use `notation verify` to validate the image:

```sh
notation verify $IMAGE
```

Example of successful image signature verification response:

```js
Successfully verified signature for localhost:5000/net-monitor@sha256:9bba24af535af5ce658c5cd78c865d574cb65443c643d8538b73ea7d3e9bb64e 
```