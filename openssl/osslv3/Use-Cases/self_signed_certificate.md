---
sidebar_position: 1
title: OpenSSL - Self-Signed Root CA Certificate
sidebar_label: Self-Signed Root Certificate
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL,PKCS#11]
---

<!-- KEEP H1 EMPTY TO OVERWRITE THE TITLE WITH NEXT H2 -->
# Creating a Self-Signed Root CA Certificate with OpenSSL
## Introduction

The following example shows how to use OpenSSL to:
- create a Self-Signed Root Certificate for a Root Certificate Authority (CA)
- Use the root certificate to create a certificate for an intermediate CA
- issue a server certificate.

:::danger

The OpenSSL commands and configurations are examples to demonstrate
the possibility of using OpenSSL in combination with a Securosys HSM
for PKI applications. Some of the tools are not considered to have
"production quality" by OpenSSL itself:

> This command was originally meant as an example of how to do things
> in a CA. Its code does not have production quality. It was not
> supposed to be used as a full blown CA itself
-- [openssl-ca man page](https://www.openssl.org/docs/man3.2/man1/openssl-ca.html#WARNINGS)

:::

## Preparation

1. Create the directories and initial files
   ```bash
   mkdir -p myCA/{rootCA,intermediateCA}/{certs,crl,newcerts,private,csr}

   touch myCA/rootCA/index.txt
   echo 1000 > myCA/rootCA/serial
   echo 1000 > myCA/rootCA/crlnumber
   
   touch myCA/intermediateCA/index.txt
   echo 1000 > myCA/intermediateCA/serial
   echo 1000 > myCA/intermediateCA/crlnumber
   ```

1. Download the configuration for the root CA
   [openssl\_root.cnf](../resources/samples/self_signed_certificate/openssl_root.cnf),
   and the intermediate CA
   [openssl\_intermediate.cnf](../resources/samples/self_signed_certificate/openssl_intermediate.cnf). These
   two configurations specify constraints, policies and extensions
   that are applied to the certificates they create and sign.

## Root CA certificate

1. Create a key.
   ```bash
   openssl genpkey -propquery "provider=pkcs11" \
       -algorithm rsa -pkeyopt rsa_keygen_bits:4096 \
       -pkeyopt pkcs11_uri:"pkcs11:object=MyRootCA"
   ```
   :::note

   If your pkcs11-provider is configured to access to multiple
   partitions on the HSM the pkcs11-uris used in these examples are
   ambiguous. Additionally specify the partition by adding the `token`
   attribute to the URI. E.g.: Use
   `pkcs11:object=MyRootCA;token=<USER_PARTITION_NAME>` instead of
   `pkcs11:object=MyRootCA`.

   :::


1. Create a self-signed certificate. The v3\_ca extensions defined in
   the openssl\_root.cnf configuration are applied which enable the
   "CA:true" property.

   ```bash
   openssl req -config openssl_root.cnf \
       -key "pkcs11:object=MyRootCA" \
       -new -x509 -days 7300 -sha256 -extensions v3_ca \
       -out myCA/rootCA/certs/ca.cert.pem \
       -subj "/C=CH/ST=Zurich/L=Zurich/O=My Example Organisation/OU=IT Department/CN=Root CA"
   ```

## Intermediate CA certificate
1. Create a key.

   ```bash
   openssl genpkey -propquery "provider=pkcs11" \
       -algorithm rsa -pkeyopt rsa_keygen_bits:4096 \
       -pkeyopt pkcs11_uri:"pkcs11:object=MyIntermediateCA"
   ```

1. Create a certificate signing request (CSR).
   
   ```bash
   openssl req -config openssl_intermediate.cnf \
       -key "pkcs11:object=MyIntermediateCA" \
       -new -sha256 \
       -out myCA/intermediateCA/certs/intermediate.csr.pem \
       -subj "/C=CH/ST=Zurich/L=Zurich/O=My Example Organisation/OU=IT Department/CN=Intermediate CA"
   ```

1. As root CA, sign the CSR for the intermediate CA with the root
   CA key.The v3\_intermediate\_ca extensions defined in the
   openssl\_root.cnf configuration are applied.

   ```bash
   openssl ca -batch -config openssl_root.cnf \
       -keyfile "pkcs11:object=MyRootCA" \
       -extensions v3_intermediate_ca -days 750 -notext -md sha256 \
       -in myCA/intermediateCA/certs/intermediate.csr.pem \
       -out myCA/intermediateCA/certs/intermediate.cert.pem
   ```

1. Verify the signature on the intermediate certificate

   ```bash
   openssl verify -CAfile myCA/rootCA/certs/ca.cert.pem \
       myCA/intermediateCA/certs/intermediate.cert.pem
   ```

## Server certificate

1. Create a key.
   ```bash
   openssl genpkey -propquery "provider=pkcs11" \
       -algorithm rsa -pkeyopt rsa_keygen_bits:4096 \
       -pkeyopt pkcs11_uri:"pkcs11:object=server-www.example.com"
   ```

1. Create a certificate signing request (CSR).
   ```bash
   openssl req -copy_extensions=copyall \
       -key "pkcs11:object=server-www.example.com" \
       -new -sha256 \
       -out server-www.example.com.csr.pem \
       -subj "/C=CH/ST=Bern/L=Bern/O=My Example Organisation/OU=IT Department/CN=www.example.com" \
       -addext "subjectAltName = DNS:www.example.com, DNS:*.www.example.com"
   ```

1. As intermediate CA, sign the server CSR with the intermediate CA key.
   ```bash
   openssl ca -batch -config openssl_intermediate.cnf \
       -extensions v3_server_cert \
       -keyfile "pkcs11:object=MyIntermediateCA" \
       -days 40 -notext -md sha256 \
       -in server-www.example.com.csr.pem \
       -out server-www.example.com.cert.pem
   ```

1. Verify the certificate by checking the signatures using OpenSSL.

   ```bash
   cat myCA/intermediateCA/certs/intermediate.cert.pem \
       myCA/rootCA/certs/ca.cert.pem \
       > chain.pem

   openssl verify -show_chain \
       -trusted myCA/rootCA/certs/ca.cert.pem \
       --untrusted chain.pem \
       server-www.example.com.cert.pem
   ```


   Additionally test the certificate by starting a https server
   ```bash
   openssl s_server \
       -cert server-www.example.com.cert.pem \
       -key 'pkcs11:object=server-www.example.com' \
       -chainCAfile chain.pem -naccept +1
   ```
   and connecting to it
   ```bash
   curl -v --cacert myCA/rootCA/certs/ca.cert.pem \
       --connect-to www.example.com:443:localhost:4433 \
       https://www.example.com
   ```
   
## More resources

- [Generate a key with OpenSSL](/openssl/osslv3/Tutorial/openssl_cli)
- [Troubleshooting](../Tutorial/troubleshooting)
- [OpenSSL documentation](https://docs.openssl.org/master/).