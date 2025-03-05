---
sidebar_position: 1
title: HSM Device Information with Primus Tools
sidebar_label: HSM Device Information
description: HSM Device Information with Securosys Primus Tools and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# HSM Device Information

Use the HSM device information commands for acquiring logs or the device information. 

Commands will require an established `<HSM connection and credentials>` parameter to be able to execute properly. For further assistance on how to prepare your `<HSM connection and credentials>` parameter, see [HSM Connection and Access Credentials](/primus-tools/Installation/Provider) section.

###	HSM User Log
Get the HSM user log for the last `<n>` days and write the output to a file called mylogfile.txt:
```bash
java -jar primus-tools.jar GetLog <HSM connection and credentials> -lastdays <n> [> mylogfile.txt]
```

:::note 
The log ring buffer could overwrite old entries. Consult the [Primus HSM User Guide](https://support.securosys.com/external/knowledge-base/article/63) for the meaning of the different log entries. 
:::

###	Get Device Info
Get HSM device information:
```bash
java -jar primus-tools.jar GetDeviceInfo <HSM connection and credentials>
```
Example output:
```bash
Device name: PRIMUS-HSM
Device version info: RX-2.11.2-T 
Provider version: 2.27020220408 (2.2.7 of 2022-04-08)
```