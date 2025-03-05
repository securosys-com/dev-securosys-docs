---
sidebar_position: 6
title: Download JCE/JCA Provider for Security HSM
sidebar_label: Download
description: Download Java Cryptography Extension (JCE) and Java Cryptography Architecture (JCA) API Provider for Securosys Hardware Security Modules.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Download JCE/JCA API Provider

:::warning Download access

To download the software, you must be a **registered support user**. <br />
If you are **new** to developing applications with a Securosys-HSM, you can obtain a **free developer account** by contacting us at info@securosys.com. 
:::

---

## Download Link & Credentials

To download the _PrimusJCE Provider_ retrieve the **Download-Link-File** from the Securosys Support Portal Knowledge Base article in the _Download_ section. This file contains the credentials necessary for downloading the software package.

<Icon icon="fa-solid fa-download" size="lg" /> [Support Portal: Download-Link-File](https://support.securosys.com/external/knowledge-base/article/125)

---
---

## Configuration Files

Replace the variables `CRED`, `VERSION` with the values from the **Download-Link-File**.

<Tabs groupId="os">
<TabItem value="unix" label="Unix" default>
    ```sh {2,3}
    USER=support.reader.jce
    CRED=REPLACE_ME
    VERSION=v2.4.3
    curl -L -XGET https://${USER}:${CRED}@securosys.jfrog.io/artifactory/jce-provider/PrimusAPI_JCE-X-${VERSION}.zip -o PrimusAPI_JCE-X-${VERSION}.zip
    ```
</TabItem>
<TabItem value="win" label="Windows" default>
    ```sh {2,3}
        set USER=support.reader.jce
        set CRED=REPLACE_ME
        set VERSION=v2.4.3
        curl -L -o PrimusAPI_JCE-X-%VERSION%.zip https://%USER%:%CRED%@securosys.jfrog.io/artifactory/jce-provider/PrimusAPI_JCE-X-%VERSION%.zip
    ```
</TabItem>
<TabItem value="browser" label="Browser" default>
    Username: `support.reader.jce`

    <Icon icon="fa-solid fa-download" size="lg" /> [Configuration-Files](https://securosys.jfrog.io/artifactory/jce-provider/PrimusAPI_JCE-X-v2.4.3.zip)
</TabItem>
</Tabs>

***SHA256 Sum***<br />
`9131800b6b3e63ba1c2e09adc2b33dd7d94ade68fa42ac7c02376935e764ef40  PrimusAPI_JCE-X-v2.4.3.zip`

The java.doc and samples can be obtained on Securosys Support Portal [here](https://support.securosys.com/external/knowledge-base/article/125).