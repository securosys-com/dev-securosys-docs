---
sidebar_position: 0
title: OpenSSL HSM Integration Guide
sidebar_label: Overview
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL, PKCS11, Webserver]
grid_title: OpenSSL v3.x & v1.x
grid_search_tags: [OpenSSL, PKCS#11, Provider, Network security, Webserver, Docker]
grid_description: Secure key management, certificate generation, file encryption, and digital signing by leveraging HSM-protected keys, ensuring high security and compliance with industry standards.
grid_categories: [OpenSSL, PKCS#11, Provider, Webserver]
grid_tile_logoUrl: '/img/company_logo/openssl.png'
---

import Link from '@docusaurus/Link';

# OpenSSL <!-- KEEP H1 EMPTY TO OVERWRITE THE TITLE WITH NEXT H2 -->
## Introduction 

This guide describes how to use the `Primus PKCS #11` library to integrate `Securosys Hardware Security Modules` (HSMs) with `OpenSSL` to provide secure solutions.
Some of the scenarios where OpenSSL can be used with an HSM:
- Generating key pairs protected by the HSM
- Generating certificates protected by keys protected by the HSM
- Importing of certificates so they can be protected by the HSM
- Encrypting and decrypting files and documents using keys protected by the
HSM
- Signing applications and files with keys protected by the HSM

<div className='about__cards'>
    <Link to='osslv3/overview' className='about__card'>
        <div className='about__section_card'>
            <h3 className='about__header'>OpenSSL v3.x</h3>
            <p className='about__description'>Provider API</p>
        </div>
    </Link>
    <Link to='libp11/overview' className='about__card'>
        <div className='about__section_card'>
            <h3 className='about__header'>OpenSSL v1.x (OpenSC/libp11)</h3>
            <p className='about__description'>
                OpenSC Engine API (***Deprecated***)
            </p>
        </div>
    </Link>    <Link to='ossl-legacy/overview' className='about__card'>
        <div className='about__section_card'>
            <h3 className='about__header'>OpenSSL v1.x (p11-kit)</h3>
            <p className='about__description'>Engine API (***Deprecated***)</p>
        </div>
    </Link>
</div>

## Differences Between OpenSSL APIs for HSM Integration

This section below outlines the differences between the three OpenSSL API solutions that enable integration with `Hardware Security Modules` (HSMs):

### 1. OpenSSL v3.x Provider API

The [**OpenSSL v3.x Provider API**](/openssl/osslv3/overview) is an OpenSSL 3.x provider that offers native support for the PKCS#11 API within the OpenSSL 3.0 framework. Unlike engine-based plugins, this provider fully integrates with OpenSSL’s new provider architecture, allowing it to manage cryptographic operations directly within OpenSSL’s provider model.

:::info
The below two examples utilize OpenSSL Engines. In version v3.x, OpenSSL continues to support Engines, however they are considered deprecated and will be dropped from support in future version. Users should consider upgrading to v3.x as a migration step to ensure that their systems to continue to work in the long-term.
:::

### 2. OpenSSL v1.x OpenSC Engine API

The [**OpenSSL v1.x OpenSC Engine API**](/openssl/libp11/overview) provides an engine that allows OpenSSL to access PKCS#11 modules, such as HSMs, in OpenSSL versions prior to 3.0 (or in environments still using engines). This engine works within OpenSSL’s older "engine" framework, which predates the provider model introduced in OpenSSL 3.0.

### 3. OpenSSL v1.x Engine API

The [**OpenSSL v1.x Engine API**](/openssl/ossl-legacy/overview) is a broader toolkit that provides centralized management and access to PKCS#11 modules. It simplifies the use of multiple PKCS#11 modules across applications by offering a consistent interface for discovering and loading modules.

### Summary of Differences

- **OpenSSL v3.x Provider API**: A native OpenSSL 3.0 provider that integrates directly into OpenSSL's modern architecture, bypassing the need for older engine-based mechanisms.
- **OpenSSL v1.x OpenSC Engine API**: An engine plugin designed for compatibility with OpenSSL's older engine framework (pre-3.0), often used in legacy systems.
- **OpenSSL v1.x Engine API**: A toolkit for managing multiple PKCS#11 modules across the entire system, offering a more flexible and general solution that can be used by many applications, including OpenSSL.

In summary, if you're using OpenSSL 3.0 or newer, the **Latchset PKCS#11 provider** offers the most streamlined integration. For legacy systems or older OpenSSL versions, the **OpenSC/libp11 engine** is more appropriate. **p11-kit** is the best option if you require centralized management of multiple PKCS#11 modules across different applications.

### You might be interested in

- [Create a self-signed root certificate with OpenSSL](/openssl/osslv3/Use-Cases/self_signed_certificate)
- [Generate a key with OpenSSL](/openssl/osslv3/Tutorial/openssl_cli.md)
- [What is Securosys CloudHSM?](/cloudhsm/overview/)
