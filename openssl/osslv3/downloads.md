---
sidebar_position: 6
title: Download - PKCS#11 Provider API for OpenSSL
sidebar_label: Download
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Download OpenSSL PKCS#11-Provider

:::warning Download access

Access to the software download requires registration as a **registered support user**. <br />
If you are **new** to developing applications with a Securosys-HSM, you can obtain a **free developer account** by contacting us at info@securosys.com. 
:::

---

## Download Link & Credentials

To download the _OpenSSL pkcs11-provider_ retrieve the **Download-Link-File** from the Securosys Support Portal Knowledge Base article in the _Download_ section. This file contains the credentials necessary for downloading the software package.

<Icon icon="fa-solid fa-download" size="lg" /> [Support Portal: Download-Link-File][download-openssl-p11-provider]

---
---

## Software Download

Replace the variables `CRED`, `P11_PROV_VERSION` with the values from the **Download-Link-File**.

<Tabs groupId="os">
    <TabItem value="all" label="Unix amd64" default>
        ```sh {2,3}
        USER=robot.reader.openssl
        CRED=REPLACE_ME
        P11_PROV_VERSION=latest
        curl -L -XGET "https://${USER}:${CRED}@securosys.jfrog.io/artifactory/opensslv3-pkcs11/${P11_PROV_VERSION}/Securosys_PrimusAPI_OSSLv3-Provider-PKCS11-${P11_PROV_VERSION}.zip" -o Securosys_PrimusAPI_OSSLv3-Provider-PKCS11-${P11_PROV_VERSION}.zip
        ```
    </TabItem>
    <TabItem value="browser" label="Browser">
        Username: `robot.reader.openssl`

        <Icon icon="fa-solid fa-download" size="lg" /> [Configuration-Files](https://securosys.jfrog.io/artifactory/opensslv3-pkcs11/latest/Securosys_PrimusAPI_OSSLv3-Provider-PKCS11-latest.zip)
    </TabItem>
</Tabs>

[download-openssl-p11-provider]: https://support.securosys.com/external/knowledge-base/article/198
