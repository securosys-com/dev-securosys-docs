---
sidebar_position: 7
title: Download Primus Tools for Security HSM
sidebar_label: Download
description: Download Securosys Primus Tools for Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Download

:::warning Download access

To download the software, you must be a **registered support user**. <br />
If you are **new** to developing applications with a Securosys-HSM, you can obtain a **free developer account** by contacting us at info@securosys.com. 
:::

---

## Credentials

To download the _Primus Tools_ retrieve the **Download-Link-File** from the Securosys Support Portal Knowledge Base article in the _Download_ section. This file contains the credentials necessary for downloading the software package.

<Icon icon="fa-solid fa-download" size="lg" /> [Support Portal: Download-Link-File](https://support.securosys.com/external/knowledge-base/article/127)


## Primus Tools Bundle

Replace the variables `CRED`, `VERSION` with the values from the **Download-Link-File**.

<Tabs groupId="os">
<TabItem value="unix" label="Unix" default>
    ```sh {2,3}
    USER=support.reader.jce
    CRED=REPLACE_ME
    VERSION=latest
    curl -L -XGET https://${USER}:${CRED}@securosys.jfrog.io/artifactory/jce-provider/PrimusTools_JCE-${VERSION}.zip -o PrimusTools_JCE-${VERSION}.zip
    ```
</TabItem>
<TabItem value="win" label="Windows CMD" default>
    ```sh {2,3}
    set USER=support.reader.jce
    set CRED=REPLACE_ME
    set VERSION=latest
    curl -L -o "PrimusTools_JCE-%VERSION%.zip" https://%USER%:%CRED%@securosys.jfrog.io/artifactory/jce-provider/PrimusTools_JCE-%VERSION%.zip
    ```
</TabItem>
<TabItem value="browser" label="Browser" default>
    Username: `support.reader.jce`

    <Icon icon="fa-solid fa-download" size="lg" /> [Primus Tools Bundle](https://securosys.jfrog.io/artifactory/jce-provider/PrimusTools_JCE-latest.zip)
    
</TabItem>
</Tabs>