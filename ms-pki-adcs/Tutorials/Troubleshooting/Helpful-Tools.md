---
sidebar_position: 1
title: Helpful Toolbox for Microsoft AD CS
sidebar_label: Helpful toolbox
description: List of helpful tools to troubleshoot Microsoft AD CS and Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Helpful Toolbox
Below is a list of helpful Windows tools:

<Tabs groupId="Tools">
<TabItem value="certlm.msc" label="certlm.msc" default>
Certificate manager (local computer/machine)
</TabItem>
<TabItem value="certmgr.msc" label="certmgr.msc" default>
Certificate manager (current user)
</TabItem>
<TabItem value="certreq.exe" label="certreq.exe" default>
Certreq can be used to request certificates from a certification authority (CA), to retrieve a response to a previous request from a CA, to create a new request from an .inf file, to accept and install a response to a request, to construct a cross-certification or qualified subordination request from an existing CA certificate or request, and to sign a cross-certification or qualified subordination request.
</TabItem>
<TabItem value="certsrv.msc" label="certsrv.msc" default>
Microsoft AD CS (Certification Authority)
</TabItem>
<TabItem value="Certutil.exe" label="Certutil.exe" default>
Certutil.exe is a command-line program that is installed as part of Certificate Services. You can use Certutil.exe to dump and display certification authority (CA) configuration information, configure Certificate Services, backup and restore CA components, and verify certificates, key pairs, and certificate chains. E.g. to verify CRL, certificates:

```sh
certutil -URL “http://crl1.hsmdemo.test/certenroll/DEMO-CAR-CA.crl” 
certutil -URL C:\issdemokey.cer To dump requests and certificates: 
certutil -dump <certificate.req/cer>
certutil -repairstore my *
```
</TabItem>
<TabItem value="gpmp.msc" label="gpmp.msc" default>
Change group policy management (e.g. password policy, …)
</TabItem>
<TabItem value="gpupdate.exe" label="gpupdate.exe" default>
Refreshes the local computers policy and any Active Directory based Group policies (e.g. gpupdate /force)
</TabItem>
<TabItem value="hsmcons.exe" label="hsmcons.exe" default>
KSP/CNG test tool for the Primus HSM (within the Debug Build Folder … \SecurosysPrimusKsp_v1.xx.y\bin\Debug\x86\)
</TabItem>
<TabItem value="pkiview.msc" label="pkiview.msc" default>
Enterprise PKI MMC snap-in allows to assess and manage the health of a Windows Enterprise CA hierarchy
</TabItem>
<TabItem value="rsop.msc" label="rsop.msc" default>
Resultant Set of Policy utility, to check group policy
</TabItem>
<TabItem value="sc query <svcname>" label="sc query <svcname>" default>
Service Control query
</TabItem>
</Tabs>


