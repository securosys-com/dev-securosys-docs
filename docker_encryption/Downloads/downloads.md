---
sidebar_position: 1
title: Docker Encryption Plugin - Download
sidebar_label: Download
description: Download
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Download

:::warning Download access

Access to the software download requires registration as a **registered support user**. <br />
If you are **new** to developing applications with a Securosys-HSM, you can obtain a **free developer account** by contacting us at info@securosys.com. 
:::

---

## Download Link & Credentials

To download the _Securosys Docker Image Encryption Plugin_ retrieve the **Download-Link-File** from the Securosys Support Portal Knowledge Base article in the _Download_ section. This file contains the credentials necessary for downloading the software package.

<Icon icon="fa-solid fa-download" size="lg" /> [Support Portal: Download-Link-File](https://support.securosys.com/external/knowledge-base/article/194)

---
---

## Software Download

Replace the variables `CRED`, `VERSION` with the values from the **Download-Link-File**.

<Tabs groupId="os">
<TabItem value="unix" label="Unix" default>
    ```sh {2,3}
    USER=robot.reader.docker-security
    CRED=REPLACE_ME
    VERSION=latest
    curl -L -XGET https://${USER}:${CRED}@securosys.jfrog.io/artifactory/docker-security/docker-encryption/${VERSION}/securosys_docker-encryption-skopeo-plugin-${VERSION}.zip -o securosys_docker-encryption-skopeo-plugin-${VERSION}.zip
    ```
</TabItem>
<TabItem value="win" label="Windows" default>
    ```sh {2,3}
    set USER=robot.reader.docker-security
    set CRED=REPLACE_ME
    set VERSION=latest
    curl -L -XGET https://%USER%:%CRED%@securosys.jfrog.io/artifactory/docker-security/docker-encryption/%VERSION%/securosys_docker-encryption-skopeo-plugin-%VERSION%.zip -o securosys_docker-encryption-skopeo-plugin-%VERSION%.zip
    ```
</TabItem>
<TabItem value="browser" label="Browser" default>
    <Icon icon="fa-solid fa-download" size="lg" /> [Configuration-Files](https://securosys.jfrog.io/artifactory/docker-security/docker-encryption/latest/securosys_docker-encryption-skopeo-plugin-latest.zip)
</TabItem>
</Tabs>
