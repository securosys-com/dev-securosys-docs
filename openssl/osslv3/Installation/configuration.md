---
sidebar_position: 3
title: Configuring PKCS#11 for OpenSSL v3 and HSM
sidebar_label: Configuration
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL,PKCS#11]
---


<!-- KEEP H1 EMPTY TO OVERWRITE THE TITLE WITH NEXT H2 -->
# Configuring PKCS#11 for OpenSSL v3

The **OpenSSL PKCS#11 Provider API** tries to be compatible with a wide range of tokens and offers some parameters to adapt to the different Cryptoki implementations.

The full range of configuration options can be found in the provider's [HOWTO.md][latchset-howto] and the [GitHub manual][latchset-manual]. In the following the options that are relevant for the Securosys HSM are presented.

For a system wide configuration of the PKCS#11 API Provider, edit the ```openssl.cnf```-file in the ```OPENSSLDIR```. You can find the ```OPENSSLDIR``` location using `openssl version -a`.

To restrict the HSM usage to specific applications, you can create a copy of the configuration file in a chosen location and set the `OPENSSL_CONF` environment variable to point to it. This ensures OpenSSL uses the new configuration. For additional ways to customize OpenSSL configurations, please refer to the [OpenSSL documentation](https://docs.openssl.org/master/).

The OpenSSL configuration is a ini-like configuration file. The
example below is a minimal configuration file.

```ini
HOME = .
openssl_conf = openssl_init
config_diagnostics = 1

[openssl_init]
providers = provider_sect
// highlight-next-line-info
random = random_sect

// highlight-info-start
[random_sect]
random = PKCS11-RAND
// highlight-info-end

[provider_sect]
default = default_sect
// highlight-next-line-warning
base = base_sect
// highlight-next-line-tip
pkcs11 = pkcs11_sect

// highlight-warning-start
[base_sect]
activate = 1
// highlight-warning-end

// highlight-warning-start
[default_sect]
activate = 1
// highlight-warning-end

// highlight-tip-start
[pkcs11_sect]
module = /usr/local/lib/ossl-modules/pkcs11.so
pkcs11-module-path = /usr/local/primus/lib/libprimusP11.so
#pkcs11-module-token-pin = file://$ENV::OSSL_PKCS11_PIN_FILE
pkcs11-module-load-behavior = early
pkcs11-module-quirks = no-deinit no-operation-state
activate = 1
// highlight-tip-end
```

:::info `PKCS11-RAND`

Enabling the `PKCS11-RAND` to use the HSM as source for randomness is
**optional**. It is disabled in the configuration obtained by
following the [quick-start guide](/openssl/osslv3/quickstart).

:::

:::warning Important

The base-provider and default-provider need to be enabled
explicitly. Otherwise the encoding function as well as the majority of
algorithms implemented by OpenSSL will not be accessible.

:::

### pkcs11-provider options
The actual configuration of the provider is placed in `[pkcs11_sect]`.
It needs to be listed in the `[provider_sect]`.

* `module` and the `pkcs11-module-path` options are mandatory. They
have to point to the installed _pkcs11.so_ file and the
_libprimusP11.so_ file you installed when setting up the HSM's PKCS#11
interface.

* `pkcs11-module-token-pin` can be used to point to a file
containing the PKCS#11 PIN. When multiple tokens are accessible, the
PIN for the specific token should be provided in the pkcs11-uri.

* `pkcs11-module-load-behavior` can be used to change the behavior
on the application start. By setting it to `early`, the
pkcs11-provider gets a list of supported operations when it is
loaded. Without this option, the connection to the HSM is only
established when requested. The `early` load behavior is needed when
working with the OpenSSL on the command line.

* `pkcs11-module-quirks` are used to inform the pkcs11-provider
about certain properties of the token that might be considered
unusual.
* The option `no-deinit` prevents the de-initialization of the Primus
  pkcs11-provider. As part of the exit routine the OpenSSL command
  line interface tries to do a clean up. However, the deallocation on
  exit is done in reverse order of allocation, which means that the
  C++ objects in the Primus pkcs11 provider are already
  freed. Applications that call `OPENSSL_cleanup` before the exiting
  do not need that flag. Applications that re-load the OpenSSL library
  should not use this flag to avoid memory leaks.
* The HSM does not support getting and setting the operation
  state. Thus, the context cannot be duplicated during digest and sign
  operations. By setting `no-operation-state`, the duplication is not
  performed.

[quickstart]: /openssl/osslv3/quickstart
[latchset-howto]: https://github.com/latchset/pkcs11-provider/blob/main/HOWTO.md
[latchset-manual]: https://github.com/latchset/pkcs11-provider/blob/main/docs/provider-pkcs11.7.md


## More resources

- [Generate a key with OpenSSL](/openssl/osslv3/Tutorial/openssl_cli)
- [Troubleshooting](../Tutorial/troubleshooting)
- [HOWTO.md][latchset-howto]
- [GitHub manual][latchset-manual]
- [OpenSSL documentation](https://docs.openssl.org/master/).
