---
sidebar_position: 3
title: OpenSSL v3.x Command Line Interface (CLI)
sidebar_label: OpenSSL CLI
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL,PKCS#11,CLI]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

<!-- KEEP H1 EMPTY TO OVERWRITE THE TITLE WITH NEXT H2 -->
# Tutorial: OpenSSL v3.x CLI
## Introduction

The OpenSSL command line interface can be used to interact with the HSM using the pkcs11-provider. As of OpenSSL 3, it is possible to limit the selection of different algorithm implementations using a properties based concept. The [`propquery` parameter][1] is available for many of the commands that are accessible through the CLI. When setting `-propquery "provider=pkcs11"` only algorithms that are offered by the pkcs11-provider will be used.

:::info
Objects stored on the token (HSM User) are referenced using a [PKCS#11URI](https://www.rfc-editor.org/rfc/rfc7512).

For commands where the key is used as an input argument, it's URI can be entered in place of the file name. When generating a key, the URI can be passed as a `pkeyopt` (e.g. `-pkeyopt pkcs11_uri:"pkcs11:type=private?object=SomeLabel"`).
:::

:::warning
For generating, importing, and deleting objects directly on the HSM we recommend using `pkcs11-tool`.

Instructions on how to use `openssl` are also provided.
:::
<Tabs groupId="kmgt">
	<TabItem value="kmgt-pkcs11-tool" label="pkcs11-tool" default>
:::info
All commands connect to the HSM and execute there.
:::

## Generating Keys with pkcs11-tool

Define the following variables that will be used throughout the example below
```bash
export P11_TOKEN=<YOUR_USER_NAME>			#partition name
export P11_PIN=<YOUR_PKCS11_PIN>			#hsm pkcs11 pin (don't use for production)
export P11_KEY_NAME=<YOUR_PKCS11_KEY_LABEL_NAME>	#name of Key
export P11_LIB=/usr/local/primus/lib/libprimusP11.so	#library location
```
Generate an extractable private key pair
```bash
# create a key pair of type rsa
pkcs11-tool --module ${P11_LIB} --login --slot 0 \
	    --keypairgen  --key-type rsa:4096 --pin ${P11_PIN} \
	    --extractable --label ${P11_KEY_NAME}

# other parameters to consider
# --id <1234> to specify an ID; randomized otherwise
```
To view all your objects in the HSM
```bash
pkcs11-tool --module ${P11_LIB} --slot 0 --login --pin ${P11_PIN} --list-objects

# sample output
Public Key Object; RSA 4096 bits
  label:      <YOUR_PKCS11_KEY_LABEL_NAME>
  ID:         457bac3be2780e5a4ac122f81b601f0e
  Usage:      encrypt, verify, wrap
  Access:     local
Private Key Object; RSA 
  label:      <YOUR_PKCS11_KEY_LABEL_NAME>
  ID:         457bac3be2780e5a4ac122f81b601f0e
  Usage:      decrypt, sign, unwrap
  Access:     sensitive, always sensitive, never extractable, local
  Allowed mechanisms: RSA-X-509,RSA-PKCS,SHA1-RSA-PKCS,SHA224-RSA-PKCS,SHA256-RSA-PKCS,SHA384-RSA-PKCS,SHA384-RSA-PKCS,RSA-PKCS-PSS,SHA1-RSA-PKCS-PSS,SHA224-RSA-PKCS-PSS,SHA256-RSA-PKCS-PSS,SHA384-RSA-PKCS-PSS,SHA512-RSA-PKCS-PSS,RSA-PKCS-OAEP


```
#### Exporting public keys
Export the public key locally
```bash
pkcs11-tool --module ${P11_LIB} --login --login-type user \
	    --read-object --type pubkey --id 01 -o rsa01pub.key
```
#### Creating a self signed certificate
pkcs11-tool offers to sign a certificate directly on the HSM:

```bash
# generate a self-signed certificate using pkcs11-tool on the token
pkcs11-tool --module ${P11_LIB} --login --type=cert \
	    --write-object ${P11_KEY_NAME}_crt.pem --label ${P11_KEY_NAME}
```
</TabItem>
<TabItem value="kmgt-openssl" label="OpenSSL">
:::info
All commands connect to the HSM and execute there.
:::

## Generating Keys with OpenSSL

Define the following variables that will be used throughout the example below
```bash
export P11_TOKEN=<YOUR_USER_NAME>			#partition name
export P11_PIN=<YOUR_PKCS11_PIN>			#hsm pkcs11 pin (don't use for production)
export P11_KEY_NAME=<YOUR_PKCS11_KEY_LABEL_NAME>	#name of Key
export P11_LIB=/usr/local/primus/lib/libprimusP11.so	#library location
export ALGORITHM=<PREFERRED_ALGO>			#consult table below
export ALGORITHM_OPT=<PREFERRED_ALGO_OPTION>		#consult table below
```
The following table lists popular supported algorithms and algorithm options:
| `ALGORITHM` | `ALGORITHM_OPT`               |
|------------:|:------------------------------|
| `rsa`       | `rsa_keygen_bits:2048`        |
| `rsa`       | `rsa_keygen_bits:3072`        |
| `rsa`       | `rsa_keygen_bits:4096`        |
| `EC`        | `ec_paramgen_curve:prime256v1`|
| `EC`        | `ec_paramgen_curve:secp384r1` |
| `EC`        | `ec_paramgen_curve:secp521r1` |
| `ed25519`   |                               |

:::warning
For generating, importing, and deleting objects on the HSM there are tools more suitable than OpenSSL. Consider using the `pkcs11-tool` instead.

At the moment "Error writing key(s)" is displayed despite creating the key-pair successfully.
:::

```bash
openssl genpkey -propquery "provider=pkcs11" \
		-algorithm "${ALGORITHM}" ${ALGORITHM_OPT:+-pkeyopt} ${ALGORITHM_OPT} \
		-pkeyopt "pkcs11_uri:pkcs11:object=${P11_KEY_NAME}?pin-value=${P11_PIN}"
```
#### Exporting public keys
```bash
openssl pkeyutl -pubout -in "pkcs11:type=public;object=${P11_KEY_NAME}" \
		-out "${P11_KEY_NAME}_pub.pem"
```
#### Creating a self signed certificate

```bash
openssl req -new -x509 -key "pkcs11:object=${P11_KEY_NAME}" \
	    -sha256 -days 99 -out "${P11_KEY_NAME}_crt.pem"
```
:::note
The above certificate is **not** stored on the HSM. The certificate file needs to be written to the token explicitly. 
:::
</TabItem>
</Tabs>

[1]: https://www.openssl.org/docs/man3.0/man7/property.html#Queries
[2]: https://www.rfc-editor.org/rfc/rfc7512

## More resources

- [Generate a key with OpenSSL](/openssl/osslv3/Tutorial/openssl_cli)
- [Troubleshooting](../Tutorial/troubleshooting)
- [OpenSSL documentation](https://docs.openssl.org/master/).
