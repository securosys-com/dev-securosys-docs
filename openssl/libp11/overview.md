---
sidebar_position: 1
title: OpenSSL v1.x (OpenSC/libp11) HSM Integration
sidebar_label: Introduction
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL,PKCS#11]
---

# OpenSSL v1.x - OpenSC/libp11

The open-source [OpenSC/libp11 - PKCS#11 Wrapper Library](https://github.com/OpenSC/libp11) provides a **PKCS#11 Engine Plugin** for OpenSSL, allowing seamless integration with Primus Hardware Security Modules (HSMs). This plugin enables OpenSSL to access PKCS#11 modules, including Primus HSMs, using the familiar OpenSSL API, providing a semi-transparent interface for cryptographic operations.

This guide outlines the steps for integrating and configuring the **PKCS#11 Engine Plugin** to work with Primus HSMs. By following these instructions, you will be able to leverage OpenSSL to perform cryptographic functions securely using Primus HSMs.
