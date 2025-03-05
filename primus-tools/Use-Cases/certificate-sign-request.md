---
sidebar_position: 7
title: Self-Signed Certificates and Primus Tools
sidebar_label: Self-Signed Certificate
description: Self-Signed Certificates with Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Self-Signed Certificate

**Self-signed certificates** are certificates where the issuer and subject are the same entity. They are used for various purposes, primarily in scenarios where a trusted third-party certificate authority (CA) is not available or not necessary, such as internal testing, development environments, or isolated network environments.

:::note
The process of self-signed certificate is not exclusive to JCE API and Primus Tools and can be done with other APIs and tools/utilities.
:::

To generate a self-signed certificate it is possible to use the KeytoolX utility within the Primus Tools set. For more information about the commands and their parameters please refer to [KeytoolX](/primus-tools/Tutorials/command-details/KeytoolX&JarsignerX).

General procedure to create a self-signed certificate:
- Generate key pair for the CSR creation,
- Generate a certificate signing request (CSR),
- Sign the CSR,
- Import the signed CSR into HSM.

Commands will require an established HSM connection and credentials to be able to execute properly. For further assistance on how to configure your HSM connection and credentials please see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider.md).

:::note
A sample request to create a self-signed certificate with an EC algorithm key is shown below. Please change the algorithm if you want to create an elliptic-curve key. Please make sure to change the parameters to your required ones. 
:::

## Create Key pair
- Create another key pair named `cs1`, which will be used to create a certification signing request (CSR):
```bash
java -jar primus-tools.jar KeytoolX <HSM connection and credentials> -genkeypair -v -alias cs1 -keypass
password -keyalg EC -keysize 256 -sigalg SHA256withECDSA -dname CN=CS1 -validity 3650
```
## Create CSR
- Create a certification signing request (CSR) with the previously created key pair `cs1`:
```bash
java -jar primus-tools.jar KeytoolX <HSM connection and credentials> -certreq -v -alias cs1 -keypass
password -file cs1.csr
```
## Sign CSR
- Sign the CSR with it's private key `cs1` and create a certificate:
```bash
java -jar primus-tools.jar KeytoolX <HSM connection and credentials> -gencert -v -alias cs1 -keypass
password -infile cs1.csr -outfile cs1.crt
```
## Import certificate
- Import the signed certificate `cs1.crt`:
```bash
java -jar primus-tools.jar KeytoolX <HSM connection and credentials> -importcert -v -alias cs1c -file cs1.crt
```

After a successful imported certificate, the self-signed certificate process is complete.
