---
sidebar_position: 3
title: Tutorial - OpenSSL 1.1 Command Line Tool (CLI)
sidebar_label: OpenSSL CLI
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates, OpenSC, pkcs11-tool, CSR, key generation, certificate import]
---

# OpenSSL CLI

The examples below are using customized configuration for Primus HSM PKCS#11 provider as shown previously. HSM partition is mounted as slot 0. For your requests you probably have to
adapt the openssl configuration file or define all your CSR parameters
on the command line.

## Generating Key Pair and Certificate Signing Request (CSR) and Import Certificate

This chapter describes how to generate a keypair using OpenSC/pkcs11-tool,
generating a CSR with openssl and importing the certificate using
pkcs11-tool (you cannot create an RSA key inside the HSM with
genrsa/genpkey).

:::tip
It is important to use the same HSM API for the generation, import and usage of objects (keys, certificates), as the APIs may support different metadata.
:::

### RSA Key Pair, CSR and Certificate Import

Generating a new key pair using pkcs11-tool: 2048-bit RSA,
CKA_SENSITIVE set, using slot 0:

```bash
pkcs11-tool --module /usr/local/primus/lib/libprimusP11.so --slot=0 \
	--login --pin <PKCS\#11 PIN> --keypairgen --key-type RSA:2048 \
	--id 1001 --label myrsakey --sensitive
```
```text
Using slot 0 with a present token (0x0)
Key pair generated:
Private Key Object; RSA 
 label: myrsakey
 ID: 1001
 Usage: decrypt, sign, unwrap
Public Key Object; RSA 2048 bits
 label: myrsakey
 ID: 1001
 Usage: encrypt, verify, wrap
```

Generate the CSR interactively, using OpenSSL by referencing the key
ID on the HSM by the key ID:

```bash
openssl req -engine pkcs11 -new \
	-key "pkcs11:token=<partition-name>;id=%10%01;type=private" \
	-keyform engine -out rsa.hsm.csr -sha256 -verify
```
```text
engine "pkcs11" set.
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [XX]:CH
State or Province Name (full name) []:ZH
Locality Name (eg, city) [Default City]:Zuerich
Organization Name (eg, company) [Default Company Ltd]:Securosys SA
Organizational Unit Name (eg, section) []:IT
Common Name (eg, your name or your server's hostname) []:cos8.securosys.ch
Email Address []:cos8@securosys.ch
...
verify OK
```

Alternatively, you can issue a self signed certificate using OpenSSL:

```bash
openssl req -engine pkcs11 -new -x509 \
	-key "pkcs11:token=<partition-name>;id=%10%01;type=private" \
	-keyform engine -out rsa.hsm.crt -sha256 -days 365 -nodes
```

If required, import the signed certificate rsa.hsm.crt (DER format),
using pkcs-11 tool:

```bash
pkcs11-tool --module /usr/local/primus/lib/libprimusP11.so -l \
	-p <PKCS\#11 PIN> --slot 0 --write-object rsa.hsm.crt \
	--type cert --id 1001 --label myrsakey
```
```text
Using slot with ID 0x0
Created certificate:
Certificate Object; type = X.509 cert
 label: myrsakey
 subject: DN: C=CH, O=Securosys SA, OU=IT, CN=cos8.securosys.ch
 ID: 1001
 ```

### EC Key Pair, CSR and Certificate Import

Generating a new key pair using pkcs11-tool: Elliptic Curve secp384r1,
using slot 0:

```bash
pkcs11-tool --module /usr/local/primus/lib/libprimusP11.so \
	-slot=0 --login --pin <PKCS\#11 PIN> --keypairgen \
	--key-type EC:secp384r1 --id 1002 --label myeckey
```
```text
Using slot 0 with a present token (0x0)
Key pair generated:
Private Key Object; EC
 label: myeckey
 ID: 1002
 Usage: sign, derive
Public Key Object; EC EC_POINT 384 bits
 EC_POINT: 
046104fb05c1978b6d700e4adb629f81194f5a9c4a06ba4ffe50c41c9165630021fbecd5e91a72db8e48f5d0
5c81a8dd5d55eca1722587f1f13b9fc4e622eb54b07a15d18f77d774326c3f35abe69ca3a7de53a8e92e2cf2
c44e1a9a34f52fdbacf71b
 EC_PARAMS: 06052b81040022
 label: myeckey
 ID: 1002
 Usage: verify, derive
```

Generate the CSR interactively, using OpenSSL by referencing the key
on the HSM by the key Label (id\_\<id-number\>) or label:

```bash
openssl req -engine pkcs11 -new \
	-key "pkcs11:token=<partition-name>;object=myeckey;type=private" \
	-keyform engine -out ec.hsm.csr -sha384 -verify
```
```text
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [XX]:CH
$State or Province Name (full name) []:ZH
Locality Name (eg, city) [Default City]:Zuerich
Organization Name (eg, company) [Default Company Ltd]:Securosys SA
Organizational Unit Name (eg, section) []:IT
Common Name (eg, your name or your server's hostname) []:cos8.securosys.ch
Email Address []:cos8@securosys.ch
...
verify OK
```

Alternatively, you can issue a self signed certificate using OpenSSL:
```bash
openssl req -engine pkcs11 -new -x509 \
	-key "pkcs11:token=<partition-name>;object=myeckey;type=private" \
	-keyform engine -out ec.hsm.crt -sha384 -days 365 -nodes
```

If required, import the signed certificate ec.hsm.cer (DER format), using pkcs-11 tool:
```bash
pkcs11-tool --module /usr/local/primus/lib/libprimusP11.so -l \
	-p <PKCS\#11 PIN> --slot 0 --write-object ec.hsm.crt \
	--type cert --id 1002 --label myeckey
```
