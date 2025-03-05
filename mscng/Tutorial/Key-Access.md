---
sidebar_position: 1
title: HSM Key Accessibility - Microsoft CNG
sidebar_label: Key Accessibility
description: Key Accessibility Microsoft CNG for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Key Accessibility

## Key Scope Machine Key

Administrator rights are required to access keys in the key scope machine key [^1], i.e. when the **`IsMachineKey`** flag is set.

## Key Accessibility (HSM Internal Key Naming)

As network attached HSM, the Primus HSM CNG/KSP Provider has to deal with keys used for different key scopes from different Windows machines organized in workgroups or domains, and from different user accounts.

Accessibility of the different key spaces is managed using a **key prefix**, which is described below.

The Primus CNG/KSP provider uses internally the following key naming scheme:
```bash
<keyPrefix>__<keyName>
```

- ``` <keyPrefix> ```
 a value based on Security Identifier (SID) and Relative Identifier (RID) of the machine or user account, depending on the key scope (machine or user key), the account type, and if the machine is standalone or domain-joined
- ``` <keyName> ```corresponds to the key name passed via the CNG/KSP API

The following table shows the assigned ```<keyPrefix>```, depending on key scope, user account and domain membership:

 |**Key Scope**| **Account type** | **Standalone Machine** | **Domain-joined Machine** |
|:----|:----|:----|:----|
|**Machine**|Flag **`IsMachineKey`** set, <br /> any account | LocalMachineSID | LocalMachineSID |
|**User**| Domain User Account [^2]| n.a.| DomainSID-UserRID|
| |Local User Account| LocalMachineSID-UserRID | LocalMachineSID-UserRID |
| |Special System Accounts, e.g. <br /> System/LocalSystem <br /> NT Authority/LocalService <br /> NetworkService|Well-known SID [^3], e.g. <br /> S-1-5-18 <br /> S-1-5-19 <br /> S-1-5-20 | DomainSID-MachineRID [^4] | | S-1-5-18| |
| | When key name suffix **`.global`**[^5] is set, the key is accessible by any authenticated user on this device, without administrative privileges. Global keys can be created with the `ksputilcons` utility, where the key must have the suffix `.global` added within the name. See [Key Management - Create Key](./KSP-utils) for more information on how to create a global key.| S-1-5-11 | S-1-5-11 |
 
Application programmers using Primus CNG/KSP should carefully consider above table regarding key accessibility from different user accounts and machines.

:::danger Key Inaccessibility Risks
The following procedures can make specific keys inaccessible for an application:
- Domain changes, such as:
  - joining the machine to a network domain
  - removing the machine from a network domain
  - moving the machine from one domain to another domain
- any other SID changes
:::

[^1]: CNG V1.30.0 and later. Before that, the machine keys were accessible by all users.
[^2]: In case of identical domain and local user accounts, the domain account is preferred. Microsoft CNG versions before V1.21.4, the local user account was preferred.
[^3]: Not supported by Microsoft CNG versions V1.21.4 ... 1.31, as system accounts can only use machine key scope
[^4]: Microsoft CNG version before V1.21.4, these accounts used the well-known SID as on standalone machines.
[^5]: Global keys are supported with Primus CNG/KSP provider version v1.50.2 or newer.