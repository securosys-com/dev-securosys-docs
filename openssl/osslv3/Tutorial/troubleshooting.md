---
sidebar_position: 4
title: Troubleshooting - PKCS#11 API for OpenSSL
sidebar_label: Troubleshooting
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL,PKCS#11]
---

<!-- KEEP H1 EMPTY TO OVERWRITE THE TITLE WITH NEXT H2 -->
# Troubleshooting PKCS#11 for OpenSSL

1. To verify that the provider is loaded correctly, use the command `openssl list -providers` to list the active providers. If the OpenSSL PKCS#11 provider has been activated successfully, it should appear in the list.

    Additionally, you can check the available signature algorithms with the command `openssl list -signature-algorithms`, which should display algorithms with `@pkcs11` appended.

    ```bash
    openssl list -signature-algorithms
    ```
    ```text
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
    # highlight-next-line-tip
    { 1.2.840.113549.1.1.1, 2.5.8.1.1, RSA, rsaEncryption } @ pkcs11
    { 1.3.101.112, ED25519 } @ pkcs11
    { 1.3.101.113, ED448 } @ pkcs11
    ECDSA @ pkcs11
    ```

2. The pkcs11-provider implements a lazy load behavior by default. At the moment we recommend to change the load behavior by setting `pkcs11-module-load-behavior = early` in the [pkcs11_sect] section of your OpenSSL configuration file.

3. Check the pkcs11-provider logs. The logs can be controlled with the `PKCS11_PROVIDER_DEBUG` environment variable. For instance `PKCS11_PROVIDER_DEBUG=file:/tmp/p11prov-debug.log,level=5` will send the logs to _/tmp/p11prov-debug.log_. The logs will tell you if `pkcs11-module-path` points to a valid provider library and if the interaction with it is successful. Below, the (abbreviated) content of _p11prov-debug.log_ provider logs just after starting openssl is shown. The lines to look out for when analyzing the logs are highlighted. The table contains a brief description of the action that is related to the log entry. Don't forget to disable the logging after troubleshooting.

   | Line-# | Corresponding action                                         |
   | -----: | :----------------------------------------------------------- |
   | 1-2    | Dump of the configuration the pkcs11-provider received.      |
   | 4      | Initializing of the Securosys PKCS#11 provider library.      |
   | 9      | The Securosys PKCS#11 provider was successfully loaded.      |
   | 18-30  | Dump of the slot/token information **received from the HSM**.|
   
   ```text showLineNumbers
   // highlight-start
   [../../../../src/provider.c:1347] OSSL_provider_init(): Provided config params:
   [../../../../src/provider.c:1357] OSSL_provider_init(): pkcs11-module-path: /usr/local/primus/lib/libprimusP11.so
   // highlight-end
   ...
   // highlight-next-line-info
   [../../../../src/interface.c:291] p11prov_module_init(): PKCS#11: Initializing the module: /usr/local/primus/lib/libprimusP11.so
   [../../../../src/interface.c:175] p11prov_interface_init(): C_GetInterface() not available. Falling back to C_GetFunctionList():  usr/local/primus/lib/libprimusP11.so: undefined symbol: C_GetInterface
   [../../../../src/interface.c:102] populate_interface(): Populating Interfaces with 'Internal defaults', version 2.20
   [../../../../src/interface.gen.c:13] p11prov_Initialize(): Calling C_Initialize
   [../../../../src/interface.gen.c:51] p11prov_GetInfo(): Calling C_GetInfo
   // highlight-next-line-info
   [../../../../src/interface.c:327] p11prov_module_init(): Module Info: ck_ver:3.0 lib: 'Securosys SA' 'PKCS#11 Library' ver:2.1
   ...
   [../../../../src/interface.gen.c:468] p11prov_FindObjects(): Calling C_FindObjects
   [../../../../src/interface.gen.c:488] p11prov_FindObjectsFinal(): Calling C_FindObjectsFinal
   [../../../../src/slot.c:68] get_slot_profiles(): No profiles for slot 1
   [../../../../src/interface.gen.c:234] p11prov_CloseSession(): Calling C_CloseSession
   [../../../../src/interface.gen.c:173] p11prov_GetMechanismList(): Calling C_GetMechanismList
   [../../../../src/slot.c:100] get_slot_mechanisms(): Slot(1) mechs found: 105
   [../../../../src/interface.gen.c:173] p11prov_GetMechanismList(): Calling C_GetMechanismList
   // highlight-tip-start
   Slot Info:
     ID: 1
     Description:      [OPENSSL_PKCS11_INT_TEST]
     Manufacturer ID:  [Securosys SA]
     Flags (0x000005):
   
       CKF_TOKEN_PRESENT         (0x000001)
       CKF_HW_SLOT               (0x000004)
     Hardware Version: 2.11
     Firmware Version: 2.1
   
   Token Info:
     Label:            [OPENSSL_PKCS11_INT_TEST]
     Manufacturer ID:  [Securosys SA]
     Model:            [Primus HSM]
   // highlight-tip-end
   ...
   ```

4. If OpenSSL still only lists the provider but no algorithms it is
   possible that the token is not set-up correctly. Have a look at the
   Troubleshooting section in the [PKCS#11 Documentation][pkcs-user-guide].

[pkcs-user-guide]: /pkcs/overview

## More resources

- [Generate a key with OpenSSL](/openssl/osslv3/Tutorial/openssl_cli)
- [Troubleshooting](../Tutorial/troubleshooting)
- [OpenSSL documentation](https://docs.openssl.org/master/).
