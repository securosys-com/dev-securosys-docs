---
sidebar_position: 6
title: AWS BYOK - Download Microsoft CNG/KSP Provider
sidebar_label: Download
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Download

:::warning Download access

To download the software, you must be a **registered support user**. <br />
If you are **new** to developing applications with a Securosys-HSM, you can obtain a **free developer account** by contacting us at info@securosys.com. 
:::

---

## Download Link & Credentials

To download the _Microsoft CNG/KSP Provider_ retrieve the **Download-Link-File** from the Securosys Support Portal Knowledge Base article in the _Download_ section. This file contains the credentials necessary for downloading the software package.

<Icon icon="fa-solid fa-download" size="lg" /> [Support Portal: Download-Link-File](https://support.securosys.com/external/knowledge-base/article/116)

---

## Software Download

Replace the variables `CRED`, `VERSION` with the values from the **Download-Link-File**.

<Tabs groupId="os">
<TabItem value="unix" label="Unix" default>
    ```sh {2,3}
    USER=support.reader.cng
    CRED=REPLACE_ME
    VERSION=latest
    curl -L -XGET https://${USER}:${CRED}@securosys.jfrog.io/artifactory/cng/PrimusAPI_CNG-KSP-${VERSION}.zip -o PrimusAPI_CNG-KSP-${VERSION}.zip
    ```
</TabItem>
<TabItem value="win" label="Windows" default>
    ```sh {2,3}
    set USER=support.reader.cng
    set CRED=REPLACE_ME
    set VERSION=latest
    curl -L -o "PrimusAPI_CNG-KSP-%VERSION%.zip" https://%USER%:%CRED%@securosys.jfrog.io/artifactory/cng/PrimusAPI_CNG-KSP-%VERSION%.zip
    ```
</TabItem>
<TabItem value="browser" label="Browser" default>
    Username: `support.reader.cng`

    <Icon icon="fa-solid fa-download" size="lg" /> [Configuration-Files](https://support.securosys.com/external/knowledge-base/article/116)
</TabItem>
</Tabs>