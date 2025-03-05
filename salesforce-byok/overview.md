---
sidebar_position: 0
title: What is Salesforce BYOK?
sidebar_label: Overview
description: Description overview
keywords: [hsm, cloud hsm]
grid_title: Salesforce Bring Your Own Key (BYOK)
grid_search_tags: [BYOK, "JCE/JCA"]
grid_description: Salesforce Shield Platform Encryption BYOK lets you manage your encryption keys for enhanced data protection. Integrate with Securosys CloudHSM for secure key generation, storage, and compliance.
grid_categories: [Salesforce, BYOK, PrimusTools]
grid_tile_logoUrl: '/img/company_logo/salesforce.png'
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Salesforce Bring Your Own Key (BYOK)

Salesforce is a leading cloud-based customer relationship management (CRM) platform that offers a comprehensive suite of applications for sales, service, marketing, and more. It enables businesses to manage customer interactions, data, and analytics in a unified environment, enhancing operational efficiency and customer engagement. Salesforce provides robust solutions for various industries, leveraging cloud computing, artificial intelligence, and data analytics to drive business growth and innovation.

**Bring Your Own Key (BYOK)** is a security procedure that allows organizations to manage their encryption keys independently, enhancing control over data protection. Salesforce Shield Platform Encryption BYOK enables customers to use their own encryption keys to safeguard sensitive data stored in Salesforce. This procedure ensures that organizations maintain compliance with regulatory requirements and internal security policies, offering an additional layer of data security. By allowing customers to import, rotate, and revoke their encryption keys, [Salesforce Shield Platform Encryption with BYOK](https://developer.salesforce.com/docs/atlas.en-us.securityImplGuide.meta/securityImplGuide/security_pe_byok.htm) <svg width="16" height="16" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M14 3H21V10H19V6.41L12.41 13L11 11.59L17.59 5H14V3ZM5 5H11V7H6V18H17V13H19V19C19 19.5304 18.7893 20.0391 18.4142 20.4142C18.0391 20.7893 17.5304 21 17 21H5C4.46957 21 3.96086 20.7893 3.58579 20.4142C3.21071 20.0391 3 19.5304 3 19V7C3 6.46957 3.21071 5.96086 3.58579 5.58579C3.96086 5.21071 4.46957 5 5 5Z" fill="currentColor"/></svg> provides a flexible and secure approach to data encryption, meeting the stringent needs of businesses dealing with sensitive information.

Securosys [CloudHSM](../cloudhsm/overview) is a Hardware Security Module (HSM) available as cloud service, without having to worry about time consuming things like evaluation, setup, operation, redundancy, and maintenance of the HSM infrastructure, and is scalable according to your needs. The redundant cluster architecture, providing different redundant regions up to redundant worldwide cluster, integrates perfectly to bring your own key to Salesforce Shield Platform Encryption.

This document describes how to easily integrate Securosys [CloudHSM](../cloudhsm/overview) (HSM as a Service) or on-premises Primus HSM cluster with Salesforce BYOK, enabling the advantages of secure key generation and storage on the HSM, and to comply with regulatory requirements.

![](./img/Salesforce_Diagram_v3.png)
## Target Audience

This document is intended for Securosys Primus HSM or [CloudHSM](../cloudhsm/overview)
users and IT professionals. The Salesforce BYOK Procedure 
requires that you are already familiar with Salesforce.

For on-premises HSM deployed operation administrative skills are
required for Securosys Primus HSMs.

## Support Contact

If you encounter a problem while installing/configuring the provider or integration, make sure that you have read the
referenced documentation. If you cannot resolve the issue, please
contact Securosys Customer Support. For specific requests regarding
Securosys Salesforce BYOK, the Securosys
Support Portal is reachable under https://support.securosys.com (account required).

## What's Next

For a smooth start with Salesforce Bring Your Own Key procedure:
- Consult the [Quickstart](./quickstart.md) page for a comprehensive task listing.
- For detailed instructions, read and follow the [Installation](/salesforce-byok/Installation/prerequisites) guide.
- Initiate the usage by reading and following the [Tutorial](/salesforce-byok/Tutorials/salesforce-byok-certificate) section.

:::tip Ready to try ?

Enjoy a **3-month** free trial of CloudHSM Sandbox, compatible with Salesforce BYOK.

- [Sign-up](https://cloud.securosys.com/sign-up) and download the HSM credentials within minutes.
- Browse [CloudHSM Sandbox service description](../cloudhsm/Packages/sandbox)

:::