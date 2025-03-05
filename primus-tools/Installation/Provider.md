---
sidebar_position: 2
title: Connect Primus Tools with the HSM
sidebar_label: Connect the HSM
description: Connecting Securosys Primus Tools to the Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Connecting to the HSM

For each Primus Tools **command**, the following connectivity details have to be provided:
- [x] Connection details (FQDN hostname or IP address, TCP/JCE port number of the HSM)
- [x] User (partition) and credentials (setup password or permanent secret)
- [x] For [CloudHSM](/cloudhsm/overview/): service user (proxy) and credentials (service password) 

You can retrieve the required passwords or secrets with:
- on command line (caution: history traces have to be cleared manually) `password <password|permanent-secret>`
- interactive via keyboard: `-password consoleinput:`
- from file (e.g. from USB drive): `-password file:<path/name> ` containing either:
    - the setup password in plain or blinded format,
    - the permanent secret in blinded format.

Preview of the `<HSM connection and credentials>` parameters:
```bash
-host <HSM hostname/IP-address> [-port <HSM TCP port number>] /
-user <user> -password [<password>|consoleinput:|file:<path/name>]] /
[-primusproxyuser <primusproxyuser>] /
[-primusproxypassword [<primusproxypassword>|consoleinput:|file:<path/name>]]
```
:::tip `<HSM connection and credentials>`
To keep the documentation consice, the `<HSM connection and credentials>` parameters are used across most Primus Tools commands.

It's recommended to note down these parameters ahead of time for easier reference.
:::

## Fetching the Permanent Secret 

:::danger Retrieve Permanent Secret

The setup password has a limited lifespan and serves as a **temporary** credential to retrieve the **permanent** secret. The access to Primus HSM or CloudHSM will be **lost** if the permanent secret is not retrieved before the setup password expires.
:::

In this example, we will fetch the permanent secret for a connection to a **CloudHSM partition**.
- See more details on [command parameters and options](/primus-tools/Tutorials/command-details/credential-management).

For **on-premises Primus HSM**, go back to the [Prerequisites](/primus-tools/Installation/Prerequisites#primus-hsm-configuration) to see how to issue a new setup password.
- See more details on [Setting up passwords and permanent secrets](https://support.securosys.com/external/knowledge-base/article/63)(account required).

CloudHSM credentials are provided during the onboarding procedure. To issue a new valid setup password for CloudHSM, please open a ticket in the [Securosys Support Portal](https://support.securosys.com/external/service-catalogue/18), make sure to provide your CloudHSM partition name (user).

:::warning
Requests for issuing a new setup password for CloudHSM are subject to service charges.
:::

**Step-by-Step Guide:**

1. Note down your setup password in the below format, e.g.: 
```bash
g3z7Y-5knqc-hkQAs-9A4kE-Ppr6v 
```

2. Blind and store the temporary setup password to a file with `BlindPassword` command. When prompted by the console input, insert the setup password. 
Example command:
```bash
java -jar primus-tools.jar BlindPassword consoleinput: -outputfile d:\pwsetup
```

:::warning
Secret blinding might impact operation in case of tool migration. For more blinding options, see [Credential Management](/primus-tools/Tutorials/command-details/credential-management).
:::

Results in having a file `D:\pwsetup`:
```bash
blinded-aes:c775dbe31d545d38dcb342ccadee236e3ec……6005d6766808e7fc522ef60d5a670b6e9c111837fe451041
```

3.  Set and blind the CloudHSM proxy password.

:::caution
The proxy user and password are only applicable when working with CloudHSM. For on-premises Primus HSM you can skip this step.
:::

Example command:
```bash
java -jar primus-tools.jar BlindPassword consoleinput: -outputfile d:\pwproxy
```

4. Use the newly generated blinded setup password file `pwsetup` and proxy password file `pwproxy` with the `GetUserSecret` command to fetch the permanent secret to a blinded file:
```bash
java -jar primus-tools.jar GetUserSecret /
-host a-api.cloudshsm.com -port 2300 /
-user DEMO-TEST -password file:d:\pwsetup /
-primusproxyuser DEMO-TESTP -primusproxypassword file:d:\pwproxy /
-blinded -outputfile d:\pwsecret
```

:::info
Replace the host URL with the relevant connectivity details for general on-premises Primus HSM and CloudHSM connectivity, see [HSM - Connectivity Details](/connectivity-details/overview).

Please ensure that there are no firewall components blocking communication between your system and the Primus HSM or CloudHSM.
:::

5. Test the connection to the CloudHSM by using the `Login` command. Use the permanent secret file `pwsecret`:
```bash
java -jar primus-tools.jar Login 
-host a-api.cloudshsm.com -port 2300
-user DEMO-TEST -password file:d:\pwsecret
```

Continue with the various [command tutorials](../Tutorials/command-overview.md).