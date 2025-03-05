---
sidebar_position: 0
title: Introducing OpenSSL v1.1 for PKCS#11
sidebar_label: Introduction
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
---

# Introduction

:::note

The p11-kit was designed to work with OpenSSL v1.1. When running OpenSSL 3.x we recommend using the [OpenSSL pkcs11-provider](/openssl/osslv3/overview) instead.

:::

This application note describes the integration of Primus Hardware
Security Module with PKCS#11 provider as p11-kit module, to be used by
applications like OpenSSL, Apache, Nginx supporting such modules.

RedHat Enterprise Linux 8 onwards improves support for storing secrets
on external hardware like smartcards and HSMs via PKCS#11. Additional
libraries (p11-kit, openssl-pkcs11), application extensions and
standardization of key references (URI) provide the necessary
mechanisms to integrate HSMs based on manufacturer PKCS#11 providers.

<figure className="image">
  ![](./resources/img/ossl_p11_architecture.png)

  <figcaption>
  Architecture graphics from RHEL 8
  </figcaption>
</figure>

