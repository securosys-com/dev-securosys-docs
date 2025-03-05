---
sidebar_position: 5
title: Distributing Root CA Certificate to Domain
sidebar_label: Distributing Root CA Certificate
description: Distributing Root CA Certificate to Domain for Microsoft AD CS Role for Enterprise Subordinate Certificate Authority (CA) with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Distributing Root CA Certificate to Domain
- At this time, distribute the root CA certificate to the domain by importing the root CA certificate into Trusted Root CA of Public Key Policies at an intended domain level GPO, and then the subordinate CA is in place. 
- Open **`Group Policy Management`**, then right-click **`Default Domain Policy`**, and then click **`Edit`**.

![](../../img/Group-policy.png)

- In the **`Computer Configuration`** node, expand **`Policies`**, expand **`Windows Settings`**, expand **`Security Settings`**, expand **`Public Key Policies`**, right-click **`Trusted Root Certification Authorities`**, and then click **`Import...`**

![](../../img/group-policy-import.png)

- In the Certificate Import Wizard, click **`Next…`**
- Click **`Browse`** and select your root CA certificate:

![](../../img/Open-root-cert.png)

- Click **`Open`** and continue with **`Next`**.

![](../../img/Certificate-Import-Wizard-root.png)
![](../../img/Certificate-Import-Wizard-import.png)
![](../../img/Certificate-Import-Wizard-root-complete.png)
![](../../img/default-domain-policy.png)

