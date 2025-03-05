---
sidebar_position: 1
title: Installing Microsoft HGS
sidebar_label: 3. Installation
description: Configuring Microsoft HGS with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Installation

This procedure provides a straightforward integration process. 

:::note
This guide provides an example installation and architecture. Please adapt the parameters and variables according your environment requirements and details. Additionally there are other ways to install HGS. For more details see [HGS documentation](https://learn.microsoft.com/en-us/windows-server/security/guarded-fabric-shielded-vm/guarded-fabric-manage-hgs).
:::

## Host Guardian Service

For more detail about Microsoft HGS features, Shielded VMs, guarded fabric, guarded hosts, pre-requisites and
deployment please read [Microsoft online documentation](https://learn.microsoft.com/en-us/windows-server/security/guarded-fabric-shielded-vm/guarded-fabric-manage-hgs).

This guide provides steps required to integrate Microsoft HGS with Securosys HSM.

### Arhitecture

Within this documentation the following virtual machines (VMs) were installed and configured as an example HGS domain.

- Create a Windows Server 2019 machine named **CONTROL_AD**. It becomes your fabric AD domain controller with
domain name `control_am.com`.
- Create a Windows Server 2019 machine named **HOST_GS_AD**. It becomes your HGS Server with domain name
`relecloud.com`.
- Create a Windows Server 2019 machine named **HOST_1**. Configure it as a Hyper-V server that will join the
`control_am` domain to become the guarded host.
- Create a Windows Server 2019 machine named **HOST_2**. Configure it as a Hyper-V server that is used to
deploy the VM and this VM is migrated to the guarded host to demonstrate the shielded VM protected by
HGS server.

Microsoft HGS supports two modes of attestation:
- Admin-Trusted Attestation based on Active Directory
- TPM Attestation

:::note
This guide is used to setup the HGS in Admin Trusted Attestation only. In case you require TPM Attestation, please
follow the [Microsoft online documentation](https://learn.microsoft.com/en-us/windows-server/security/guarded-fabric-shielded-vm/guarded-fabric-manage-hgs) for prerequisites, hardware requirements and deployment information.
:::

### Microsoft HGS installation

:::note
This guide provides a shortened process on how to install Microsoft HGS. For a more detailed guide please see, [Deploying the Host Guardian Service](https://learn.microsoft.com/en-us/windows-server/security/guarded-fabric-shielded-vm/guarded-fabric-deploying-hgs-overview)
:::

To set up Microsoft HGS with Securosys HSM, please follow the next steps:
1. Install Active Directory Domain Services on **CONTROL_AD** and promote this server to Domain Controller.
2. Add **HOST_1** into the **control_am** domain.
3. Log on to the **HOST_GS_AD** as a user with administrative privileges.
4. On the HGS Server run the following command in Windows PowerShell console to add the HGS Role.
```
Install-WindowsFeature -Name HostGuardianServiceRole -IncludeManagementTools –Restart
```
The server will automatically restart after installing the role.
5. Log on to the **HOST_GS_AD** again as a user with administrative privileges.
6. In an elevated Windows PowerShell console, run the following commands to install the Host Guardian
Service and configure its domain.
```
$adminPassword = ConvertTo-SecureString -string "replace_me" -AsPlainText -force

Install-HgsServer -HgsDomainName 'relecloud.com' -SafeModeAdministratorPassword $adminPassword -Restart
```

The password specified here will only apply to the **Directory Services Restore Mode password** and will not change the password you log in for **Active Directory**.

After the sever restarts, log in as the **RELECLOUD** domain administrator using the same password you
previously used as the local administrator (regardless of the password you specified in the previous step).

