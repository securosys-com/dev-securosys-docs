---
sidebar_position: 2
title: Essential Windows Utilities for Certificate and Policy Management
sidebar_label: Helpful Utilities
description: Explore a list of useful Windows utilities for managing certificates, policies, and HSM integration. This guide covers tools like certlm.msc, certmgr.msc, certreq.exe, certutil.exe, and gpupdate.exe, helping IT professionals with certificate requests, policy updates, and security configurations.
keywords: [hsm, cloud hsm]
---

# Helpful Utilities
Below is a list of helpful Windows utilities:

|Tool|Usage, Comment|
|---|---|
|certlm.msc|	Certificate manager (local computer/machine)|
|certmgr.msc|	Certificate manager (current user)|
|certreq.exe|	Certreq can be used to request certificates from a certification authority (CA), to retrieve a response to a previous request from a CA, to create a new request from an .inf file, to accept and install a response to a request, to construct a cross-certification or qualified subordination request from an existing CA certificate or request, and to sign a cross-certification or qualified subordination request.|
|certutil.exe|	Certutil.exe is a command-line program that is installed as part of Certificate Services. You can use Certutil.exe to dump and display certification authority (CA) configuration information, configure Certificate Services, backup and restore CA components, and verify certificates, key pairs, and certificate chains. <br />E.g. to verify CRL, certificates: <br /> `certutil -URL “http://crl1.hsmdemo.test/certenroll/DEMO-CAR-CA.crl”` <br /> `certutil -URL C:\issdemokey.cer` <br />To dump requests and certificates: <br />`certutil -dump <certificate.req/cer>` <br /> `certutil -repairstore my *`|
|gpmp.msc |Change group policy management (e.g. password policy, …)|
|gpupdate.exe|Refreshes the local computers policy and any Active Directory-based Group policies (e.g. gpupdate /force)|
|hsmcons.exe|KSP/CNG test tool for the Primus HSM (within the Debug Build Folder … \SecurosysPrimusKsp_v1.xx.y\bin\Debug\x86\)|
|rsop.msc|Resultant Set of Policy utility, to check group policy|

