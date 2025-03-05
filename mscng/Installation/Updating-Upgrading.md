---
sidebar_position: 6
title: Updating Microsoft CNG for Security HSM
sidebar_label: Updating
description: Updating Microsoft CNG for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Updating CNG/KSP Provider

In certain cases, the **CNG/KSP Provider** may need to be updated to benefit from new HSM functionalities.

The update procedure depends on the currently installed versions and the versions to be installed:

| Procedure when updating to from … | V1.2x (Legacy Installer) | V1.3x or newer (MSI Package) |
| --- | --- | --- |
| V1.2x or older | Update procedure (see [Updating from CNG/KSP Provider V1.2x](/mscng/Installation/Updating-Upgrading.md#updating-from-cngksp-provider-v12x)): - uninstall old version <br/> - install new version <br/> - **configure KSP again** | Upgrade procedure (see [Updating from CNG/KSP Provider V1.2x](/mscng/Installation/Updating-Upgrading.md#updating-from-cngksp-provider-v12x)): <br/> - uninstall old version <br/> - install new version <br/> - configure KSP again |
| V1.3x or newer | *(downgrade requires deinstallation of the installed provider)* | Update procedure (see [Updating from CNG/KSP Provider V1.3x](/mscng/Installation/Updating-Upgrading.md#updating-from-cngksp-provider-v13x)): <br/> - apply new MSI package (keeps current configuration) |


 ## General Prerequisites

- [x] [Download](../downloads) the latest (or required) CNG/KSP Provider and corresponding release notes..
- [x] Carefully consult the CNG (and HSM) release notes concerning changes, dependencies and incompatibility issues.
- [x] If upgrading from a version V1.32, consult the [Key Accessibility](/mscng/Tutorial/Key-Access.md) section.
- [x] Validate the update for application compatibility in a **non-productive test** environment.
- [x] Get the necessary administrator rights for the update procedure.
- [x] Collect any necessary credentials (or people) in case of Windows server restart.
- [x] Allocate a maintenance slot for the update procedure.

## Updating from CNG/KSP Provider V1.3x

- Apply the new MSI package (interactive or via MS group policy) to update an existing CNG/KSP Provider V1.3x or newer. By default, the existing configuration will be retained.
- In case of **Registry Access Hardening**, check/reapply the hardening steps according to
 [this section](/mscng/Tutorial/Hardening.md).

## Updating from CNG/KSP Provider V1.2x

### Additional Prerequisites

- Take note of the existing CNG/KSP configuration values of the Securosys "Key Storage Provider Configuration" tool: Identifier, Hostname, Port Number, Priority and Global Parameters.
- Obtain the HSM (and Proxy) Credentials from your responsible Security Officer (he will eventually generate a new setup password as this credential has limited validity).

### Update Procedure

Updating an existing installation requires uninstalling the current CNG/KSP Provider, which may result in the loss of configuration settings and could necessitate a Windows restart.

After a successful uninstall, please proceed with a new installation as shown in the [Installation chapter](../category/installation).
