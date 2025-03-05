---
title: PKCS#11 - Testing and Troubleshooting
sidebar_label: Testing and Troubleshooting
---

# Testing and Troubleshooting

Before running any test tools or scripts, configure the HSM connection
settings (primus.cfg) and fetch the permanent secret (ppin tool).

## Guidance
* [ ] Ensure the PKCS#11 library is correctly installed and configured.
* [ ] Verify that the PKCS#11 provider configuration file is correctly set up with the proper parameters.
* [ ] Check for any library dependencies that might be missing or outdated.
* [ ] Confirm that the HSM slot and token labels match the configuration settings.
* [ ] In case `telnet` is working but a [Connectivity Test](../../pkcs/Tutorials/troubleshooting#connectivity-test) with `ppin -t` reports an error, clarify the validity of login credentials.
* [ ] Update to the latest version of the PKCS#11 provider to ensure compatibility and bug fixes.
* [ ] Check the [Error Codes](/pkcs/Tutorials/troubleshooting#error-codes) below to narrow down the problem.
* [ ] Consult the PKCS#11 provider documentation for specific troubleshooting steps.

## Connectivity Test

The ppin tool allows to test connectivity to all defined HSMs and
partitions (version 1.8.1+):

```bash
ppin --test [--hsm hsm] [--user HSM_USERNAME]
```
```text
Load config file: '/etc/primus/primus.cfg'

hsm0: Connect to '82.197.162.10' on port 2411, firmware: RP-2.11.2-T 
    slot0 (id=2), user=DEMO-TEST: OK

Number of tested HSMs: 1 (number of partitions: 1)
Number of failures: 0
```

## "pkcs11-tool" (from OpenSC package)

The pkcs11-tool from the OpenSC package (v0.19 or newer) allows to
list PKCS#11 slots, manage keys and many other operations on the HSM
partition (see man pages).

The following example lists all PKCS#11 slots, showing partition
slot/token information:

```bash
pkcs11-tool --module /usr/local/primus/lib/libprimusP11.so -L
```
```text
Available slots:
Slot 0 (0x2): DEMO-TEST				Slot number (index), Partition name
  token label        : DEMO-TEST			Partition name
  token manufacturer : Securosys SA
  token model        : PRIMUS HSM			
  token flags        : login required, rng, token initialized, PIN initialized, 
                        other flags=0x860
  hardware version   : 2.11				HSM version
  firmware version   : 2.1				Provider version
  serial num         : a7176edc3c192207		Partition serial number (within cluster)
  pin min/max        : 0/32
```

Login and list objects in slot 0:

```bash
pkcs11-tool --module /usr/local/primus/lib/libprimusP11.so --slot 0 -l -p '<pkcs#11_pwd>' -O
```
```text
Certificate Object; type = X.509 cert
  label:      MS-PrimusByokECP384
Secret Key Object; AES length 16
  VALUE:      1d84346b4daa61c15bc040892aaaa79e
  label:      MS-PrimusByokAES128
  Usage:      encrypt, decrypt, verify, wrap, unwrap
Public Key Object; EC  EC_POINT 384 bits
...
```

:::info

The pkcs11-tool test function (-t) is not recommended and may fail due to incompatibilities.

:::

## "p11tool" (GnuTLS package)

The p11tool from the GnuTLS package also allows similar operations as
pkcs11-tool on PKCS#11 tokens (see man pages). The following example
lists the available tokens:

```bash
p11tool --list-tokens
```
```text
Token 0:
	URL: pkcs11:model=Primus%20HSM;manufacturer=Securosys%20SA;serial=a7176edc3c192207;
            token=DEMO-TEST
	Label: DEMO-TEST
	Type: Hardware token
	Manufacturer: Securosys SA
	Model: Primus HSM
	Serial: a7176edc3c192207
...
```

The following example lists all objects on partition DEMO-TEST:

```bash
p11tool --login --list-all "pkcs11:token=DEMO-TEST"
```
```text
Token 'DEMO-TEST' with URL 'pkcs11:model=Primus%20HSM;manufacturer=Securosys%20SA;serial=a7176edc3c192207;token=DEMO-TEST' requires user PIN
Enter PIN: <PKCS#11 password>
Object 0:
	URL: pkcs11:model=Primus%20HSM;manufacturer=Securosys%20SA;serial=a7176edc3c192207;
            token=DEMO-TEST;object=MS-PrimusByokECP384;type=cert
	Type: X.509 Certificate
	Label: MS-PrimusByokECP384
	Flags: CKA_TRUSTED; 
	ID: 35:69

Object 1:
	URL: pkcs11:model=Primus%20HSM;manufacturer=Securosys%20SA;serial=a7176edc3c192207;
            token=DEMO-TEST;object=MS-PrimusByokAES128;type=secret-key
	Type: Secret key
	Label: MS-PrimusByokAES128
	Flags: CKA_WRAP/UNWRAP; CKA_PRIVATE; 
	ID: 81:15
...
```

## Log File Analysis

The provider log file can be found (as configured in primus.cfg) at
following default locations

-   Linux: /tmp/primus.log
-   Windows: %PUBLIC%\\Securosys\\Primus P11\\primus.log

For troubleshooting we recommend the following temporary log settings
in primus.cfg log section:

```libconfig
    trace_linenumber    = false;
    trace_timestamp     = true;
    trace_function      = true;
    trace_inout         = false;
    trace_pid           = true;
    trace_filename      = false;
    trace_mask          = 0x01;
    trace_level         = 6;
```

Note, that the configuration is loaded only when initializing the
library (C\_Initialize()).

Below is an example of a log file snippet, showing a failure on
C\_GetSlotInfo(), using pkcs-11tool, due to missing HSM connectivity:

```text
// highlight-next-line-note
Time-stamp              - Called from      trace_level - function       - log comment

// highlight-next-line-tip
2021-04-07 21:30:20.936 - pkcs11-tool[74481:74481] - 5 - C_Initialize() - rv: 0x0
2021-04-07 21:30:20.936 - pkcs11-tool[74481:74481] - 6 - C_GetSlotInfo() - slotId: 0
2021-04-07 21:30:20.936 - pkcs11-tool[74481:74481] - 6 - C_GetSlotInfo() - pInfo: 0x5509B3C0
2021-04-07 21:30:20.936 - pkcs11-tool[74481:74481] - 6 - connectHSM() - Connecting to HSM 'hsm0'
// highlight-next-line-warning
2021-04-07 21:30:20.972 - pkcs11-tool[74481:74481] - 3 - HSM_Hello() - rv: 0x8000004e
2021-04-07 21:30:20.972 - pkcs11-tool[74481:74481] - 4 - connectHSM() - Failed to login on HSM 82.197.162.10:2415 (slot: 0)
2021-04-07 21:30:20.972 - pkcs11-tool[74481:74481] - 6 - connectHSM() - Connecting to HSM 'hsm0'
2021-04-07 21:30:31.838 - pkcs11-tool[74481:74481] - 4 - receive() - receive failed errno 104l
2021-04-07 21:30:31.839 - pkcs11-tool[74481:74481] - 4 - receiveAnswer() - rcv failed errno 104l
// highlight-next-line-warning
2021-04-07 21:30:31.839 - pkcs11-tool[74481:74481] - 3 - HSM_Hello() - rv: 0x5
2021-04-07 21:30:31.839 - pkcs11-tool[74481:74481] - 4 - connectHSM() - Failed to login on HSM 82.197.162.10:2415 (slot: 0)
// highlight-next-line-warning
2021-04-07 21:30:31.839 - pkcs11-tool[74481:74481] - 3 - C_GetSlotInfo() - rv: 0xe0
```

The errors are logged with trace\_level 3. The PKCS#11 library
functions start with 'C\_'. The library internal HSM functions start
with 'HSM\_'. Return value of the functions are logged in the comment
with 'rv: ...'.

In addition, you can also consult the [HSM user log][hsm-logs].

## Error Codes

The table below lists some error codes of the Securosys provider (in
the log file) or the ppin tool:

| Error Code   | Description                                                                         |
|--------------|-------------------------------------------------------------------------------------|
| *0x0nnnnnnn* | *Default PKCS#11 failure (see OASIS PKCS#11 standard or  pkcs11.h file)*            |
| *0x8nnnnnnn* | *Securosys specific errors*                                                         |
| 0x80000001   | Unexpected data type                                                                |
| 0x80000002   | Buffer too small                                                                    |
| 0x80000003   | Duplicate entry                                                                     |
| 0x80000004   | No more keys                                                                        |
| 0x80000005   | Public key not found                                                                |
| 0x80000006   | Private key not found                                                               |
| 0x80000008   | Invalid block cipher                                                                |
| 0x80000009   | Invalid mechanism                                                                   |
| 0x8000000A   | Object not found                                                                    |
| 0x8000000B   | Login failed, wrong PKCS#11 PIN                                                     |
| 0x8000000C   | User not logged in                                                                  |
| 0x8000000D   | User not found                                                                      |
| 0x8000000E   | Wrong password for the key (e.g. keys created with password by JCE or CNG provider) |
| 0x80000012   | Key not found                                                                       |
| 0x80000018   | Max Connections exceeded                                                            |
| 0x8000002A   | Key import not allowed                                                              |
| 0x80000037   | Password expired                                                                    |
| 0x8000003B   | Connect Timeout                                                                     |
| 0x80000058   | EC Curve Unknown                                                                    |
| 0x8000005F   | Session objects disabled by HSM configuration                                       |
| 0x80000069   | Access to partition disabled by HSM configuration                                   |
| 0x80000084   | DL Group unknown                                                                    |
| 0x80000085   | PQC mode unknown                                                                    |
| 0x80005001   | No HSM/User configured                                                              |
| 0x80005002   | HSM/User configuration already exists                                               |
| 0x80005003   | Primus configuration file not found                                                 |
| 0x80005004   | Primus secrets file not found                                                       |
| 0x80005005   | Invalid Primus configuration file                                                   |
| 0x80005006   | Invalid Primus secrets file                                                         |
| 0x80005007   | No write access to Primus secrets file                                              |
| 0x80005008   | Failed to add permanent secret to secrets file                                      |
| 0x80005009   | Invalid secrets file configuration element                                          |
| 0x80005010   | Failed to update Primus secrets file                                                |
| 0x80005011   | Failed to remove permanent secret from Primus secrets file                          |
| 0x80005012   | User not found in Primus configuration file                                         |
| 0x80005013   | Unsupported secret types                                                            |
| 0x80005014   | NULL list pointer                                                                   |
| 0x80005015   | Connection to HSM failed (network level, IP not reachable)                          |
| 0x80005017   | Login to HSM failed with provided credentials (Setup Password)                      |
| 0x80005018   | Setup password expired                                                              |
| 0x80005019   | Access to partition disabled by HSM configuration                                   |
| *0xCnnnnnnn* | *HSM system errors*                                                                 |

[hsm-logs]: /pkcs/Tutorials/ppin_tool#fetch-partition-log
