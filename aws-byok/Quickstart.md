---
sidebar_position: 1
title: Getting started with BYOK for AWS
sidebar_label: Quickstart
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Getting started with BYOK for AWS

The process described in this document allows maintaining ownership and management over your KMS keys and to generate them using entropy in a safe environment within the Securosys Hardware Security Module as well as maintain a secure copy for disaster recovery scenarios.

This Quickstart section provides a comprehensive task listing of the Bring Your Own Key process for AWS Key management service (KMS). For more detailed instructions please consult the [Installation](/aws-byok/Installation/Prerequisites) section. Visit [Prerequisites](/aws-byok/Installation/Prerequisites) for the necessary preparations beforehand.

## HSM Setup and Configuration

Setting-up the on-premises Primus HSM is not covered of this document. Please refer to Primus HSM User Manual, downloadable from the [Securosys Support Portal](https://support.securosys.com/external/knowledge-base/article/63). 

:::note
The CloudHSM partition already comes preconfigured for AWS BYOK.
:::

Primus HSM needs specific policy settings:
- JCE API enabled and appropriate license
- Crypto policy (and User policy) configuration to allow Key Export for the used partition

## Install and Configure Primus Tools

Download the latest Primus Tools collection from the [Primus Tools Download](/primus-tools/downloads) section. For more details on Primus Tools see [Primus Tools](/primus-tools/overview).

:::note
HSM Provider Connection and Access must be prepared for the use of Primus Tools commands.
:::

## Create AWS KMS Key

Begin the BYOK procedure by creating an AWS Key management service key with no key material associated. For more details see [Create AWS KMS Key](/aws-byok/Tutorials/Create-KMS-key).


## Download Public Key & Import Token

Download the public key and import token and specify the KMS key that will be associated with the imported key material. This KMS key must have an Origin value of `EXTERNAL`.

For more information on the step by step procedure please see [Downloading Public Key & Import Token](/aws-byok/Tutorials/Download-Pub-key).

## Create Key using Primus Tools

Create a key on the Securosys CloudHSM or PrimusHSM which is going to be used in the BYOK procedure. Securosys Primus Tools offers the possibility to do so, but other tools can be used. <br />
It is paramount to set the flag of the import key to `extractable`. To import the key to AWS KMS the import key algorithm must be symmetric AES with a recommended size of 256 bits. If you already have a symmetric AES key with the necessary parameters previously created which you would like to use, please skip this step.

For more information on the step by step procedure please see [Create Key on HSM](/aws-byok/Tutorials/Create-key-HSM) section.

## Export & Wrap Key Material

Use the Securosys Primus Tools tool set command `AwsKmsByokExport` to wrap and export your specified key material on the HSM using the public key downloaded from AWS KMS. 
For more information on the step by step procedure please see [Export & Wrap Key Material](/aws-byok/Tutorials/Export&Wrap).


## Import Wrapped Key

Import the wrapped key material from the previous section into the AWS KMS. 
For more information on the step by step procedure please see [Import Key Material](/aws-byok/Tutorials/Import-Key-Material).

After a successful import of the encrypted key material to the AWS KMS, it uses your corresponding account private key to decrypt the encrypted key material.