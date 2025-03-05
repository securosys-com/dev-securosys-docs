---
sidebar_position: 3
title: PKCS#11 - Permanent Secret Fetching
sidebar_label: Permanent Secret Fetching
---

# Permanent Secret Fetching

On initial setup and configuration of a Primus HSM partition, the
installation process (Security Officer of the HSMs) generates a
partition **setup password** for a given user. The setup password is a
29-alphanumeric dash separated string in the form of
"FXAJX-XWVQ3-DC0O5-3SLQF-LJ9L3" with **limited time validity**
starting after first usage (HSM default: 3 days; CloudHSM default:
1 week; developer account: 1 year).

This initial partition setup password is used to obtain or update a
permanent secret stored in a ciphered form in the '.secrets.cfg' file
using the 'ppin' command line utility. From then on, the permanent
secret is used to establish the secure connection between the PKCS#11
Provider and the user's HSM partition.

In case of **Service Proxy (CloudHSM)** the [Service Proxy password
must be configured][cloudhsm-service-proxy], before fetching the HSM
permanent secret.

:::warning

Ensure that you have read/write access to the '.secrets.cfg' file when
editing permanent secrets.

The API login is protected against brute force attacks (setup password
and permanent secret). After too many wrong trials the API login is
locked for some time. Wrong trials are reset after a defined
time. Restart of the device resets lockout and attempts.
                                                              
The PKCS#11 password is also protected against brute force attacks
similar to above secrets. After too many wrong trials PKCS#11
privileged access is locked (API login is still possible). The same
HSM timer configurations are shared.

:::

## Adding Permanent Secret

Assume you have configured a partition for user 'HSM_USERNAME'. To
fetch and add a permanent secret to your configuration, you execute
the following command:

```bash
ppin -a -e HSM_USERNAME
```
```text
********************
Primus Permanent PIN
********************
Provide setup password for 'HSM_USERNAME': <enter User Setup Password, no echo>
Provide PKCS11 password for 'HSM_USERNAME': <enter PKCS#11 PIN, no echo>
********************
Primus Permanent PIN
********************
[01] slot-id 0: user 'HSM_USERNAME' permanent secret: Configured
```

## Updating Permanent Secret

In case the permanent secret was changed, you can update it using a
new setup password:

```bash
ppin -u -e HSM_USERNAME
```
```text
********************
Primus Permanent PIN
********************
Provide setup password for 'HSM_USERNAME': <enter User Setup Password, no echo>
Provide PKCS11 password for 'HSM_USERNAME': <enter PKCS#11 PIN, no echo>
********************
Primus Permanent PIN
********************
[01] slot-id 0: user 'HSM_USERNAME' permanent secret: Configured
```

Note that update procedure failure removes the current permanent
secret entry. That means the next trial has to be done with "add
permanent secret" (`ppin -a -e HSM_USERNAME`).

## Removing Permanent Secret

```bash
ppin -r -e HSM_USERNAME
```
```text
********************
Primus Permanent PIN
********************
[01] slot-id 0: user 'HSM_USERNAME' permanent secret: MISSING
```

[cloudhsm-service-proxy]: /pkcs/Tutorials/CloudHSM/proxy_management
