---
sidebar_position: 4
title: Configuration
sidebar_label: 5. HGS Configuration
description: Prerequisites to install Microsoft HGS with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Configure DNS Forwarding and Domain Trust

:::warning
This section aplies only for Admin-trusted attestation only.
:::

Use the following steps to set up the necessary DNS forwarding from the HGS domain to the `control_am` domain, and to establish a one-way forest trust to the control_am domain. These steps allow the HGS to locate the `control_am` domain's domain controllers and validate group membership of the `Hyper-V` hosts.

1. To configure a DNS forwarder that allows HGS to resolve resources located in the fabric (host) domain, run
the following command in an elevated PowerShell session.
```
Add-DnsServerConditionalForwarderZone -Name "control_am.com" -ReplicationScope "Forest" -MasterServers <DNSserverIP>
```

Replace `control_am.com` with the name of your environment fabric domain and type the IP addresses of DNS servers in the fabric domain.

2. To create a one-way forest trust from the HGS domain to the `control_am` domain, run the following command in an elevated PowerShell session:
```
netdom trust relecloud.com /domain:control_am.com /userD:control_am.com\Administrator /passwordD:<password> /add
```

Replace `relecloud.com` with the name of your environment HGS domain and `control_am.com` with the name of your environment. fabric domain. Make sure to provide the password for and admin of the fabric domain.

3. Create a one-way forest trust from the HGS domain to the `control_am` domain, run the following command in an elevated PowerShell session:
```powershell
netdom trust relecloud.com /domain:control_am.com /userD:control_am.com\Administrator /passwordD:<password> /add
```

Replace `relecloud.com` with the name of your environment HGS domain and `control_am.com` with the name of your environment. fabric domain. Make sure to provide the password and admin of the fabric domain and log on to the `control_am` domain as an administrative privileges.

4. Open the PowerShell and add the `relecloud` domain.
```
Add-DnsServerConditionalForwarderZone -Name "relecloud.com" -ReplicationScope "Forest" –MasterServers <DNSserverAddress>
```
5. Log on to the CONTROL_AD as a domain administrator and create a Global Security group "Guarded Hosts" in the Active Directory.

6. Add the **HOST_1** into the created group "**Guarded Hosts**".

7. Restart the **HOST_1** machine to enable the security policies.

8. On CONTROL_AD, use `Get-ADGroup` command to obtain the security identifier (SID) of the security group and provide it to the HGS administrator.
```
Get-ADGroup "Guarded Hosts"
```
9. Log on to the **HOST_GS_AD** as a domain administrator and open the PowerShell and run the command to add HGS Attestation group.
```
Add-HgsAttestationHostGroup -Name "Guarded Hosts" -Identifier "S-1-5-21-1755809051-1406045892-3533544011-1105"
```

10. Now log on to the **HOST_1** as domain administrator.

11. If the required role is not already installed, install the `Hyper-V` role and `Host Guardian Hyper-V` Support feature with the following command:
```
Install-WindowsFeature Hyper-V, HostGuardian -IncludeManagementTools -Restart
```

12. Configure the host's Key Protection and Attestation URLs on **HOST_1**. Use the `Fully Qualified Domain Name (FQDN)` of your HGS cluster to get the HGS Cluster ask the HGS administrator to run the `Get-HgsServer` cmdlet on the HGS server to retrieve the URLs.

13. On **HOST_1**, configure the Key Protection and Attestation URLs by executing the following command in
an elevated Windows PowerShell console.
```
Set-HgsClientConfiguration -AttestationServerUrl 'http://<FQDN>/Attestation' -KeyProtectionServerUrl 'http://<FQDN>/KeyProtection'
```

14. Run the `Get-HgsClientConfiguration` cmdlet to see the status. Ensure that `IsHostGuarded` is `True` and `AttestationStatus` is `Passed`.

15. Log on to the **HOST_GS_AD** as a domain administrator.
16. Run the following command on PowerShell to confirm the status of HGS Diagnostic.

```
Get-HgsTrace –RunDiagnostics
```

A warning message indicates that there is an error receiving the permissions for the certificate. This error is expected for certificates backed by an HSM, meaning the integration with the Securosys Primus HSM was successful.

:::info
To create a shielded VM using an existing VM please see [Microsoft Guarded fabric and shielded VMs](https://learn.microsoft.com/en-us/windows-server/security/guarded-fabric-shielded-vm/guarded-fabric-and-shielded-vms-top-node).
:::