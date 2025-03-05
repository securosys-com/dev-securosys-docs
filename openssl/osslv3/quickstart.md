---
sidebar_position: 1
title: Quickstart PKCS#11 Provider API OpenSSL 3.x
sidebar_label: Quickstart
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL,PKCS#11]
---

# Getting started with OpenSSL v3.x

:::info
For the OpenSSL integration to work, the <a href="/pkcs/overview" target="_blank" rel="noopener noreferrer">Securosys Primus PKCS#11 Provider API</a> needs to be installed and configured properly. 
Verify proper installation with successful execution of the `ppin -t` command. 
:::

1. Download the Securosys OpenSSL pkcs11-provider bundle and extract its content to `/tmp/securosys`. 
    Follow the <a href="/openssl/osslv3/downloads" target="_blank" rel="noopener noreferrer">download instructions</a> to obtain the credentials.
   ```bash {2}
   P11_PROV_VERSION=latest
   CRED=<robot.reader.openssl:PASSWORD>   

   curl -L -XGET "https://${CRED}@securosys.jfrog.io/artifactory/opensslv3-pkcs11/${P11_PROV_VERSION}/Securosys_PrimusAPI_OSSLv3-Provider-PKCS11-${P11_PROV_VERSION}.zip" -o Securosys_PrimusAPI_OSSLv3-Provider-PKCS11-${P11_PROV_VERSION}.zip
   unzip Securosys_PrimusAPI_OSSLv3-Provider-PKCS11-${P11_PROV_VERSION}.zip -d /tmp/securosys
   ```

2. Extract the library files to `/usr/local/lib/ossl-modules/`, and change the owner and set the permissions.
   ```bash
   unzip /tmp/securosys/securosys_primusapi_osslv3-provider-pkcs11-executable-${P11_PROV_VERSION}.zip -d /tmp/securosys/
   sudo mkdir -p /usr/local/lib/ossl-modules
   sudo unzip -j /tmp/securosys/PrimusAPI_OSSLv3-Provider-PKCS11-${P11_PROV_VERSION}-rhel8_amd64.zip -d /usr/local/lib/ossl-modules/

   sudo chown root:primus /usr/local/lib/ossl-modules/pkcs11.{so,la}
   sudo chmod 444 /usr/local/lib/ossl-modules/pkcs11.{so,la}
   ```

    :::tip
    When using the version label "latest", the final unzip command will fail if the files in the first archive include a different version tag. 
    Replace "-rhel8_amd64.zip" with your platform and architecture specific archive.

    Avoid using the packages, as they will install to `/usr/lib/osslmodules` and may be replaced by an incompatible "latchset" update!
    :::

<!-- 3. Get an OpenSSL configuration where the pkcs11-provider is enabled.
   ```bash
   sudo unzip /tmp/securosys/securosys_primusapi_osslv3-provider-pkcs11-configuration-${P11_PROV_VERSION}.zip
   export OPENSSL_CONF="$(pwd)/openssl.cnf"
   # use a permanent location
   ```
-->
3. Adapt the OpenSSL configuration to enable the OpenSSL pkcs11-provider, see <a href="/openssl/osslv3/Installation/configuration" target="_blank" rel="noopener noreferrer">Configuration</a>.
   <details>
     <summary> Click to see a configuration example</summary>
   ```bash {19-35}
   HOME            = .
   
   # Use this in order to automatically load providers.
   openssl_conf = openssl_init
   
   # Comment out the next line to ignore configuration errors
   config_diagnostics = 1
   
   oid_section = new_oids
   
   [ new_oids ]
   
   [openssl_init]
   providers = provider_sect
   
   [random_sect]
   random = PKCS11-RAND
   
   [provider_sect]
   default = default_sect
   base = base_sect
   pkcs11 = pkcs11_section
   
   [base_sect]
   activate = 1
   
   [default_sect]
   activate = 1
   
   [pkcs11_section]
   module = /opt/openssl-3.3.2/lib/ossl-modules/pkcs11.so
   pkcs11-module-path = /usr/local/primus/lib/libprimusP11.so
   pkcs11-module-load-behavior = early
   pkcs11-module-quirks = no-deinit no-operation-state
   activate = 1
   
   ####################################################################
   [ ca ]
   default_ca    = CA_default        # The default ca section
   
   ...   
   ```
   </details>


4. Test that the provider is enabled: 
   ```bash
   openssl list -providers
   ```
   ```text {10-13} 
   Providers:
     base
       name: OpenSSL Base Provider
       version: 3.3.1
       status: active
     default
       name: OpenSSL Default Provider
       version: 3.3.1
       status: active
     pkcs11
       name: PKCS#11 Provider
       version: 3.1.4
       status: active
   ```
   And offers "@ pkcs11" algorithms:
   ```bash
   openssl list -signature-algorithms
   ```
   ```text {11-14}
   { 1.2.840.113549.1.1.1, 2.5.8.1.1, RSA, rsaEncryption } @ default
   { 1.2.840.10040.4.1, 1.2.840.10040.4.3, 1.3.14.3.2.12, 1.3.14.3.2.13, 1.3.14.3.2.27, DSA, DSA-old, DSA-SHA, DSA-SHA1, DSA-SHA1-old, dsaEncryption, dsaEncryption-old, dsaWithSHA, dsaWithSHA1, dsaWithSHA1-old } @ default
   { 1.3.101.112, ED25519 } @ default
   { 1.3.101.113, ED448 } @ default
   { 1.2.156.10197.1.301, SM2 } @ default
   ECDSA @ default
   HMAC @ default
   SIPHASH @ default
   POLY1305 @ default
   CMAC @ default
   { 1.2.840.113549.1.1.1, 2.5.8.1.1, RSA, rsaEncryption } @ pkcs11
   { 1.3.101.112, ED25519 } @ pkcs11
   { 1.3.101.113, ED448 } @ pkcs11
   ECDSA @ pkcs11
   ```

   You can use the OpenSSL `storeutl` command to list the objects stored on your token (HSM):
   ```bash
   openssl storeutl "pkcs11:token=<YOUR_USER_NAME>;pin-value=<YOUR_PKCS11_PIN>"
   ```

**Further content**

- [Prerequisites](/openssl/osslv3/Installation/prerequisites)
- [Complete installation guide](/openssl/osslv3/Installation/installation.md)
- [Generate a key with OpenSSL](/openssl/osslv3/Tutorial/openssl_cli)