---
sidebar_position: 1
title: OpenSSL v1.x Command Line Interface (CLI)
sidebar_label: OpenSSL CLI
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL,PKCS#11,CLI]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Tutorial: OpenSSL v1.x CLI
## Introduction

You can use the OpenSSL command-line interface (CLI) to interact with the Primus HSM through the PKCS#11 Engine Plugin, enabling secure communication between your applications and the HSM. The OpenSSL CLI serves as a versatile tool for various cryptographic operations, including key management and certificate generation, while leveraging the security features of the Primus HSM. For a comprehensive guide on available commands and options, refer to the [OpenSSL manpage](https://docs.openssl.org/master/man1/openssl/), which provides a detailed overview of all supported functionalities and usage instructions.

## Generating Key Pair, Certificate Signing Request (CSR), and Importing Certificate

In this section, we will guide you through the steps of generating a key pair, creating a Certificate Signing Request (CSR), and importing a certificate into the Primus HSM. First, you will use the `pkcs11-tool` to generate an RSA or EC key pair within the HSM, ensuring secure key generation and storage. Next, we’ll demonstrate how to create a CSR using OpenSSL, allowing you to request a certificate from a Certificate Authority (CA) based on the generated key. Finally, you will show how to import the issued certificate back into the HSM using the `pkcs11-tool`, completing the process of securing your key pair and certificate within the HSM environment.

:::note
The following examples use the OpenSSL command-line interface with a customized configuration for the Primus HSM PKCS#11 provider, as described [here](../Installation/configuration.md). The HSM partition is mounted as slot 0.
:::

### Generate a Key Pair

<Tabs groupId="key">
  <TabItem value="rsa" label="RSA" default>
    Generating a new key pair using `pkcs11-tool`: 2048-bit RSA, CKA_SENSITIVE set, using slot 0:

    <Tabs groupId="os">
      <TabItem value="os-windows" label="Windows" default>
        ```bash
        pkcs11-tool --module "C:\Program Files\Securosys\Primus P11\primusP11.dll" --slot=0 \
           -l -p <PKCS11 PIN> --keypairgen --key-type RSA:2048 \
           --id 1001 --label myrsakey --sensitive
        ```
      </TabItem>
      <TabItem value="os-linux" label="Linux">
        ```bash
        pkcs11-tool --module /usr/local/primus/lib/libprimusP11.so --slot=0 \
           --login --pin <PKCS11 PIN> --keypairgen --key-type RSA:2048 \
           --id 1001 --label myrsakey --sensitive           
        ```
      </TabItem>
    </Tabs>

:::note
Replace `<PKCS11 PIN>` with the PKCS#11 PIN of your HSM.
:::

    After successfully executing the command, the output will look like as follows:

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
  </TabItem>

  <TabItem value="ec" label="EC">
    Generating a new key pair using `pkcs11-tool`: Elliptic Curve secp384r1, using slot 0:

    <Tabs groupId="os">
      <TabItem value="os-windows" label="Windows" default>
        ```bash
        pkcs11-tool --module "C:\Program Files\Securosys\Primus P11\primusP11.dll" \
          --slot=0 -l -p <PKCS11 PIN> --keypairgen \
          --key-type EC:secp384r1 --id 1002 --label myeckey
        ```
      </TabItem>
      <TabItem value="os-linux" label="Linux">
        ```bash
        pkcs11-tool --module /usr/local/primus/lib/libprimusP11.so \
           -slot=0 --login --pin <PKCS11 PIN> --keypairgen \
           --key-type EC:secp384r1 --id 1002 --label myeckey
        ```
      </TabItem>
    </Tabs>

:::note
Replace `<PKCS11 PIN>` with the PKCS#11 PIN of your HSM.
:::

    After successfully executing the command, the output will look like as follows:

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
  </TabItem>
</Tabs>

### Create a CSR

Generate the CSR interactively, using `openssl` by referencing the key ID on the HSM by the key ID:

<Tabs groupId="key">
  <TabItem value="rsa" label="RSA" default>
    ```bash
    openssl req -engine pkcs11 -new \
       -key "pkcs11:token=<partition-name>;id=%10%01;type=private" \
       -keyform engine -out rsa.hsm.csr -sha256 -verify
    ```

    Replace `<partition-name>` with the name of your partition on your HSM.

:::note
Notice that pkcs11-tool writes the key id in hexadecimal notation to the HSM (e.g. `%10%01`)
:::
  </TabItem>

  <TabItem value="ec" label="EC">
    ```bash
    openssl req -engine pkcs11 -new \
       -key "pkcs11:token=<partition-name>;object=myeckey;type=private" \
       -keyform engine -out ec.hsm.csr -sha384 -verify
    ```
  </TabItem>
</Tabs>

After successfully executing the command, the output will look like as follows:

```text
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
…
verify OK
```

Alternatively, you can issue a self signed certificate using `openssl`:

<Tabs groupId="key">
  <TabItem value="rsa" label="RSA" default>
    ```bash
    openssl req -engine pkcs11 -new -x509 \
       -key "pkcs11:token=<partition-name>;id=%10%01;type=private" \
       -keyform engine -out rsa.hsm.crt -sha256 -days 365 -nodes
    ```

:::note
Notice that pkcs11-tool writes the key id in hexadecimal notation to the HSM (e.g. `%10%01`)
:::
  </TabItem>

  <TabItem value="ec" label="EC">
    ```bash
    openssl req -engine pkcs11 -new -x509 \
       -key "pkcs11:token=<partition-name>;object=myeckey;type=private" \
       -keyform engine -out ec.hsm.crt -sha384 -days 365 -nodes
    ```
  </TabItem>
</Tabs>

### Import the Certificate

If required, import the signed certificate *.hsm.crt (`der` format), using `pkcs11-tool`:

<Tabs groupId="key">
  <TabItem value="rsa" label="RSA" default>
    <Tabs groupId="os">
      <TabItem value="os-windows" label="Windows" default>
        ```bash
        pkcs11-tool --module "C:\Program Files\Securosys\Primus P11\primusP11.dll" -l \
           -p <PKCS11 PIN> --slot=0 --write-object rsa.hsm.crt \
           --type cert --id 1001 --label myrsakey
        ```
      </TabItem>
      <TabItem value="os-linux" label="Linux">
        ```bash
        pkcs11-tool --module /usr/local/primus/lib/libprimusP11.so -l \
           -p <PKCS11 PIN> --slot 0 --write-object rsa.hsm.crt \
           --type cert --id 1001 --label myrsakey
        ```
      </TabItem>
    </Tabs>

:::note
Replace `<PKCS11 PIN>` with the PKCS#11 PIN of your HSM.
:::

    After successfully executing the command, the output will look like as follows:

    ```text
    Using slot with ID 0x0
    Created certificate:
    Certificate Object; type = X.509 cert
     label: myrsakey
     subject: DN: C=CH, O=Securosys SA, OU=IT, CN=cos8.securosys.ch
     ID: 1001
     ```
  </TabItem>

  <TabItem value="ec" label="EC">
    <Tabs groupId="os">
      <TabItem value="os-windows" label="Windows" default>
        ```bash
        pkcs11-tool --module "C:\Program Files\Securosys\Primus P11\primusP11.dll" -l \
        -p <PKCS11 PIN> --slot=0 --write-object ec.hsm.crt \
        --type cert --id 1002 --label myeckey
        ```
      </TabItem>
      <TabItem value="os-linux" label="Linux">
        ```bash
        pkcs11-tool --module /usr/local/primus/lib/libprimusP11.so -l \
           -p <PKCS\#11 PIN> --slot 0 --write-object ec.hsm.crt \
           --type cert --id 1002 --label myeckey
        ```
      </TabItem>
    </Tabs>

:::note
Replace `<PKCS11 PIN>` with the PKCS#11 PIN of your HSM.
:::

    After successfully executing the command, the output will look like as follows:

    ```text
    Using slot with ID 0x0
    Created certificate:
    Certificate Object; type = X.509 cert
     label: myeckey
     subject: DN: C=CH, O=Securosys SA, OU=IT, CN=cos8.securosys.ch
     ID: 1002
     ```    
  </TabItem>
</Tabs>
