---
title: PKCS#11 - Provider Connection Setup
sidebar_label: PKCS#11 Provider Connection Setup
---

# PKCS#11 Provider Connection Setup
## "ppin" Command Line Tool

The ppin[^1] command line tool allows

- to manage the [connection credentials][permanent-secret] for secure
  HSM communication (including service proxies)

- to show the installed [provider version][provider-version]

- to [fetch the partition log][partition-log] from configured HSMs and
  run connectivity tests

Below is an overview of the different command parameters:
```text noinline
********************
Primus Permanent PIN
********************
parameters:
	-c: optional path to the primus.cfg configuration file.
	-s: optional path to the .secrets.cfg file.
commands:
	ppin -h: 	this message.
	ppin -v: 	PKCS#11 provider version.
	ppin -l: 	list all Primus HSM hosts.
	ppin -a -e <user name> [<setup pwd> <pkcs11 pwd>]: 
 		add permanent secret for user <user name>.
	ppin -u -e <user name> [<setup pwd> <pkcs11 pwd>]: 
 		update permanent secret for user <user name>
	ppin -r -e <user name>: 	remove permanent secret for user <user name>.
	ppin -p -e <user name> [proxy pwd]: 	add proxy password for user <user name>.
	ppin -x -e <user name>: 	remove proxy password for user <user name>.
 	ppin --fetchlog --user <user name> --hsm <hsm or host name> [--reverse] 
 	     [--since YYYY-MM-DD] [--out <file>]: 
 		Dump the HSM log to stdout or a file.
 	ppin {-t | --test} [--user <user name>] [--hsm <hsm or host name>] 
 	     [--slot <slot name>] [--slotid <slot ID>]:
		Test connectivity to all HSMs and partitions 
		in the config file (performs a login).
		Optionally filter by HSM name, user name, 
		slot name, slot ID.
	ppin --fortinet --user <user> [<setup pwd> [<pkcs11 pwd>]] 
		 [--proxyuser <proxy user> [--proxypwd <proxy pwd>]]: 
		 encode secrets config for Fortinet product in base64
```

The list of possible [ppin error codes][error-codes] can be found in the testing
and trouble shooting section.

## Show Installed Provider Version

The following command lists the currently installed provider version:

```bash
ppin -v
```
```text
Primus HSM PKCS#11 2.2.4
```

## Overview of Configured HSM Slots, Service Proxies and Status of their Credentials

The following command lists the defined HSM slots and service proxies,
together with their credential status. The example shows

- two redundant HSM clusters (2 HSMs per Partition-1 and Partition-2),
  already having fetched the permanent secret and

- one redundant CloudHSM partition (CLOUDHSMPAR, Service User
  'eqabxrfnqqos'), not yet configured at all.

```bash
ppin -l
```
```text
********************
Primus Permanent PIN
********************
[01] slot-id 0:    user 'Partition-1'   permanent secret: Configured
[02] slot-id 1:    user 'Partition-2'   permanent secret: Configured
[03] slot-id 5:    user 'CLOUDHSMPAR'  permanent secret: MISSING
[04] slot-id 0:    user 'Partition-1'   permanent secret: Configured
[05] slot-id 1:    user 'Partition-2'   permanent secret: Configured
[06] slot-id 5:    user 'CLOUDHSMPAR'  permanent secret: MISSING
[01] service/proxy user 'eqabxrfnqqos'  permanent secret: MISSING
[02] service/proxy user 'eqabxrfnqqos'  permanent secret: MISSING
```

## Fetch Partition Log

Get the HSM user log of a specific partition and HSM as configured in
primus.cfg configuration file since specified date:

```bash
ppin --fetchlog --user HSM_USERNAME --hsm hsm1 --since 2021-04-01
```
```text
Dump log starting from 2021-04-01 00:00 UTC
Fetching log from HSM.
Number of slots: 1
2021 Apr  1 09:31:28 UTC NUFENEN notice MSCNG Process: Login request: User: DEMO-TEST Client: SecurosysKspCfg@W10C-TESTCLIENT@192.168.76.76@00:0c:29:73:e6:7b with setup password over TCP connection 536870926 (IP 178.198.217.194:31550)
2021 Apr  1 09:31:28 UTC NUFENEN info MSCNG Process: Login successful: User: DEMO-TEST Client: SecurosysKspCfg@W10C-TESTCLIENT@192.168.76.76@00:0c:29:73:e6:7b over TCP connection 0x2000000e (IP 178.198.217.194:31550)
2021 Apr  1 09:31:29 UTC NUFENEN notice Crypto Process: Interaction: User: DEMO-TEST, Client: SecurosysKspCfg@W10C-TESTCLIENT@192.168.76.76@00:0c:29:73:e6:7b, Protocol: mscng, Retrieved permanent login credentials
2021 Apr  1 09:31:29 UTC NUFENEN notice MSCNG Process: Disconnect: User: DEMO-TEST Client: SecurosysKspCfg@W10C-TESTCLIENT@192.168.76.76@00:0c:29:73:e6:7b over TCP connection 536870926 (IP 178.198.217.194:31550)
2021 Apr  1 09:31:29 UTC NUFENEN info Crypto Process: Usage statistics: User: DEMO-TEST, Client: SecurosysKspCfg@W10C-TESTCLIENT@192.168.76.76@00:0c:29:73:e6:7b, Protocol: mscng, Total key usage count: 0
...
2021 Apr  1 09:35:47 UTC NUFENEN err Crypto Process: Error: Command: CommandGetKeyFlag, User: DEMO-TEST, Client: hsmcons@W10C-TESTCLIENT@192.168.76.76@00:0c:29:73:e6:7b, Protocol: mscng, 0x12 (C_GetAttributeValue: Requested attribute type is not valid)
2021 Apr  1 09:35:48 UTC NUFENEN err Crypto message repeated 11 times: [ Process: Error: Command: CommandGetKeyFlag, User: DEMO-TEST, Client: hsmcons@W10C-TESTCLIENT@192.168.76.76@00:0c:29:73:e6:7b, Protocol: mscng, 0x12 (C_GetAttributeValue: Requested attribute type is not valid)]
```

As `--hsm <hsm or host name>` parameter you can use hsm**x**
(**x**=0...n) reference, or the host name, exactly as configured in
primus.cfg. The HSM configuration must [allow log fetching via
API][hsm-pkcs11-log-settings]. Please note that **the log ring buffer
could overwrite old entries**. Consult the [HSM User Guide][hsm-user-guide] for
description of the different log entries.

## Connection Test

The ppin tool allows to test connectivity to all defined HSMs and
partitions, and optional arguments help to select a specific HSM or
partition (provider version 1.8.1+):

```bash
ppin --test [--hsm hsm] [--user HSM_USERNAME]
```
```text
Load config file: '/etc/primus/primus.cfg'

hsm0: Connect to '82.197.162.10' on port 2411, firmware: RP-2.10.0-T 
    slot0 (id=2), user=DEMO-TEST: OK

Number of tested HSMs: 1 (number of partitions: 1)
Number of failures: 0
```

## FortiGate Secret String
The FortiGate firewall with built-in PKCS#11 provider v2.2.4+ requires the secrets file as base-64 encoded string, fetched on a client PC using the following command:
``` bash
ppin --fortinet --user <username> [<setupPassword> <PKCS11Password>] [--proxyuser <proxyUserName> [--proxypassword <proxyPassword>]]
```
``` text
# Fortinet secret to be loaded:
dmVyc2lvbiA9ICIxLjAiOwpwcmltdXMgOiAKewogIHVzZXJzIDogCiAgewogICAgdXNlcjAgOiAKICAgIHsKICAgICAgbmFtZSA9ICJQUklNVVNERVYzNjgiOwogICAgICBdpY3MgPSAiMzcwYzJj
... 
GUwY2Y4ZjNhNTkwMzE2ZjE4MGI4YWZlNDdiMzY1Nzg1ZWQ3NyI7CiAgICB9OwogIH07Cn07Cg==
```
For details consult the [FortiGate Integration Guide][fortigate].



[^1]: On Windows platforms called ppin.exe


[error-codes]: /pkcs/Tutorials/troubleshooting#error-codes
[hsm-pkcs11-log-settings]: /pkcs/Installation/primus_hsm_settings#user-log-via-pkcs11-api
[hsm-user-guide]: https://support.securosys.com/external/knowledge-base/article/63
[partition-log]: #fetch-partition-log
[permanent-secret]: /pkcs/Installation/permanent_secret_management
[provider-version]: #show-installed-provider-version
[fortigate]: /fortigate/overview
