---
sidebar_position: 0
title: Getting started with JCE/JCA API
sidebar_label: Quickstart
description: Getting started with Java Cryptography Extension (JCE) and Java Cryptography Architecture (JCA) with Securosys Hardware Security Modules.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Getting Started with JCE/JCA API Provider

:::warning User Secrets

The initial partition `setup password` is used to obtain or update a **permanent secret** as shown in the samples below. Once the permanent secret has been fetched it should be used from then on. The permanent secret is used to establish the secure connection between the JCE Provider and the user's HSM partition.

:::

## Download

Head to the **[Downloads](/jce/Downloads/downloads.md)** page to get instructions on how to get the software.

## Install
Extract the Securosys JCE-Provider and import the JAR into your IDE.

```bash
mkdir ${JCE_PROV_VERSION}
unzip -j ${JCE_PROV_VERSION}.zip -d ${JCE_PROV_VERSION}
```

## First Steps

On initial setup and configuration of a Primus HSM partition, the installation process (Security Officer of the HSMs) generates a partition **setup password** for a given user. The setup password is a 29-alphanumeric dash separated string in the form of `FXAJX-XWVQ3-DC0O5-3SLQF-LJ9L3` with **limited time validity** starting after first usage (HSM default: 3 days; CloudHSM default: 1 week; developer account: 1 year).

_Note: In case of Service Proxy (CloudHSM) the Service Proxy password must be configured, before fetching the HSM permanent secret._


<Tabs groupId="fetch-secret">
  <TabItem value="CloudHSM" label="CloudHSM" default>
    If you are using CloudHSM or your own reverse proxy, you will need to learn how to configure a proxy connection first. The sample shows how to **establish a connection** and **extract Permanent User Secret** using a temporary secret and how to set up redundant connection.

    - [CloudsHsmSample.java](./ressources/samples/ProxySample.java)

    Change the following properties in the `CloudsHSMSample.java` based on the information provided in your support ticket.

:::tip HSM Connectivity

When utilizing CloudHSM service, refer to [CloudHSM Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) for API-Endpoint URI `proxyhost` and `proxy-port`. 

:::

    ```java
      final String proxyhost = "<cloudsHSM.hostname>";
      final String proxyport = "2300";
      final String proxyuser = "<proxyuser>";
      final char[] proxypassword = "<proxypassword>".toCharArray();
      final String hsmuser = "<USERNAME>";
      final char[] hsmpassword = "<setup-password>".toCharArray();
    ```
  </TabItem>
  <TabItem value="on-premise" label="On Premise" default>
    If you're connecting to the Dev program or to your on-prem HSM directly (without reverse proxy), this sample shows how to establish the connection to retrieve permanent secret by using a temporary secret provided by your HSM operator (for your own Primus HSM) or by us (for Dev Program).

    - [UserSecretSample.java](./ressources/samples/UserSecretSample.java)

:::tip HSM Connectivity

For on-premise deployments, verify API-Endpoint URI with your administrator. Contact your service administrator for authentication credentials.

:::

        Change the following properties in the `UserSecretSample.java` based on the information provided in your support ticket.
    ```java
      final String host = "grimsel.securosys.ch"; // externally reachable test/development HSM
      final String port = "2400"; // Default HSM's JCE port is 2300. Dev program partition is accessible at 2400
      final String user = "TESTUSER";
      final char[] password = "FDgKb-zxsPR-ud6EN-GK6Jz-adrvB".toCharArray();
    ```
  </TabItem>
</Tabs>


:::warning warning

The API login is protected against brute force attacks (setup password and permanent secret). After too many wrong trials the API login is locked for some time. Wrong trials are reset after a defined time. Restart of the device resets lockout and attempts.

:::

## Further Samples

Learn more about the Securosys JCE-Provider by following the samples,
 Log in to the HSM using the secret key retrieved in the previous step:
- [Login and password blinding sample](/jce/Tutorials/LoginSample.md)
- [Create a key, sign a payload, and verify the signature](/jce/Tutorials/SignVerify.md)
- [Use Key Attributes (Flags) to set properties of a key.](/jce/Tutorials/KeyAttributes.md)
- [All Samples](/jce/Tutorials/AllSamples.md)