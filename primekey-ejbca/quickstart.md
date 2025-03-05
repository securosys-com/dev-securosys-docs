---
sidebar_position: 1
title: Getting started with Keyfactor EJBCA and HSM
sidebar_label: Quickstart
description: "Discover how the Keyfactor and PrimeKey merger enhances machine identity management with end-to-end PKI solutions. Integrating Keyfactor's automation with PrimeKey’s EJBCA software, this robust platform supports IoT, DevOps, and enterprise environments. Secure your PKI with Securosys HSMs for strong compliance and key protection."
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

# Getting started with Keyfactor EJBCA

The quickstart section provides a comprehensive guide outlining the steps necessary to integrate [Keyfactor/PrimeKey EJBCA](https://www.keyfactor.com/) with Securosys [CloudHSM](../cloudhsm/overview) or on-premises Primus HSM.

## Installing & Configuring Primus PKCS#11 Provider

Install the latest version of Primus [PKCS#11 Provider](../pkcs/overview) on the device with the Keyfactor/PrimeKey EJBCA already installed.

Follow the instructions in [PKCS#11 Provider Installation](/primekey-ejbca/Installation/Provider-setup).

Configure the Primus PKCS#11 provider by adapting the configuration file **`primus.cfg`** according to your set-up. 

:::info
Consult [Primus PKCS#11 User Guide - Configuration](/pkcs/Installation/pkcs11_provider_configuration) for configuration file locations.
:::

## HSM Setup and Configuration 

Follow the instructions provided in [HSM Setup and Configuration](/primekey-ejbca/Installation/Prerequisites#hsm-setup-and-configuration).


## Configure EJBCA Settings

Configure the EJBCA settings to integrate with the Primus PKCS#11 provider. For more information visit [Installation - EJBCA Settings](/primekey-ejbca/Installation#ejbca-property-file)


## Configure New Crypto Token

Deploy the EJBCA with the newly configured settings and Integrate the HSM by [Configuring a New Crypto Token](/primekey-ejbca/Installation/#configure-new-crypto-token).

## Generate RSA Keys

Generate cryptographic objects which will be used by the new Certificate Authority (CA). For more information, see [Keyfactor/PrimeKey EJBCA Documentation](https://doc.primekey.com/ejbca/tutorials-and-guides).

## Create a New Certificate Authority (CA)

Create a new Certificate Authority and configure it with the previously created crypto token and keys.
For more information on EJBCA Certificate Authority setup and best practices, follow [Keyfactor/PrimeKey EJBCA Documentation](https://doc.primekey.com/ejbca/tutorials-and-guides).