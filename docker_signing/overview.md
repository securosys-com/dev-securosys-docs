---
sidebar_position: 0
title: Container Image Signing - HSM Integration
sidebar_label: Overview
description: Sign Docker images with Securosys HSM integration. Enhance security and meet organizational policies with SKA and TSB.
keywords: [hsm, cloud hsm]
grid_title: Container Image Signing
grid_search_tags: [Docker, "Software Signing", Rest-API, "Smart Key Attributes"]
grid_description: Manage private keys and enforce advanced authorization workflows with Securosys Primus HSMs. Apply policies like quorums and time-restrictions, and simplify Smart Key Attribute (SKA) implementation through the Securosys Transaction Security Broker (TSB) with a REST API, ensuring secure operations within the HSM.
grid_categories: [Docker, Rest-API, Signing]
grid_tile_logoUrl: '/img/company_logo/docker.png'
toc_min_heading_level: 4
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# <Icon icon="fa-brands fa-docker" size="lg" />  Docker Image Signing
### HSM Integration guide

In this guide, we will explore the foundations of Docker image signing and describe the key concepts and benefits. We will delve into the integration with Securosys products and explain why their integration into any Docker security ecosystem is critical.  

In the ever-evolving landscape of containerization and cloud-native technologies, Docker has emerged as a cornerstone, revolutionizing how applications are developed, deployed, and managed. However, as the adoption of Docker containers continues to skyrocket, so too does the need for robust security measures. Docker image signing have emerged as essential components of this security posture, ensuring the integrity and confidentiality of containerized applications.  

Where there's signing, there's a key. The question of how that key is handled, managed, and distributed becomes paramount.  

Securosys Hardware Security Modules (HSM) are not only optimized for the physical protection of private key material like most general-purpose HSMs; In addition, Securosys Primus HSMs provide control of the keys usage with specific and sophisticated authorizations, which is essential for signing Docker images.  

Requirements of modern digital identity applications go beyond the traditional HSM capabilities. In many cases, organizational policies require multiple approvals before a signature is finally authorized. General-purpose HSMs typically don't allow for such workflows. With [Securosys Primus HSMs Smart Key Attributes (SKA)](https://www.securosys.com/securosys-smart-key-attributes-enabling-true-multi-authorization-rules-and-more-for-private-key-usage), it is possible to define rules such as quorums and time-restrictions for each key individually, thus meeting even the most complex organization policies. The policies are enforced within the same secure boundaries that protects the key itself. 

Securosys Transaction Security Broker (TSB) makes the implementation of SKAs much easier thanks to its REST API and internal approval orchestration. It runs as a standalone engine, connects to an external database instance, and integrates the SKA-enabled Securosys HSM - and is thus uncritical for security, since all security relevant operations are carried out inside the HSM. 

[Securosys plugin for Docker Signing](./Concepts/DockerSigningConcept.md) communicates by REST API trough Securosys Transaction Security Broker (TSB) with on-premises Primus HSM or [Securosys PrimusHSM - REST API](https://primusdev.cloudshsm.com/swagger-ui/index.html#/) - HSM as a Service and take optionally advantage of the multi-authorization workflow provided by SKA. 

This application note aims to provide guidance on the integration process of Docker image signing using "Notation" (aka Notary v2 / Docker Content Trust notary v2) with keys protected in the HSM with on-premises Primus HSM or Securosys CloudHSM.


## Target Audience

This document is intended for Securosys Primus HSM or [CloudHSM](/cloudhsm/overview/) administrators and DevOps professionals. Running the Securosys Plugins requires that you are already familiar with using Docker Engine, Docker Compose, as well as _Notation_. 

For on-premises HSM deployed operation administrative skills are required for Securosys Transaction Security Broker (TSB) and Securosys Primus HSMs.


## Getting started with Docker Image Signing and HSMs

- Read the [QuickStart Guide](/docker_signing/quickstart)
- Request Samples (Rest-API) [Samples](/docker_signing/Tutorials/Commands/DescribeKey)
- Read the [Prerequisites](/docker_signing/Installation/prerequisites)
- Read the [official Deployment Guide](/docker_signing/Installation/InstallNotation)