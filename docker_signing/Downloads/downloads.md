---
sidebar_position: 1
title: Docker Image Signing Plugin - Download Access
sidebar_label: Download
description: To download the Securosys Docker Image Signing Plugin, ensure you're a registered support user. New developers can obtain a free account by contacting Securosys. Get the credentials and download link from the Securosys Support Portal.
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

To download the [_Securosys Docker Image Signing Plugin_](../Concepts/DockerSigningConcept.md), visit the Securosys Support Portal and retrieve the **Download-Link-File** Knowledge Base article in the _Download_ section.

This file includes the credentials needed access the software package.

<Icon icon="fa-solid fa-download" size="lg" /> [Support Portal: Download-Link-File](https://support.securosys.com/external/knowledge-base/article/190)

---

## Software Download

Replace the variables `CRED`, `VERSION` with the values from the **Download-Link-File**.

<Tabs groupId="os">
<TabItem value="unix" label="Unix" default>
    ```sh {2,3}
    USER=robot.reader.docker-security
    CRED=REPLACE_ME
    VERSION=latest
    curl -L -XGET https://${USER}:${CRED}@securosys.jfrog.io/artifactory/docker-security/docker-signing/${VERSION}/securosys_docker-signing-notation-plugin-${VERSION}.zip -o securosys_docker-signing-notation-plugin-${VERSION}.zip
    ```
</TabItem>
<TabItem value="win" label="Windows" default>
    ```sh {2,3}
    set USER=robot.reader.docker-security
    set CRED=REPLACE_ME
    set VERSION=latest
    curl -L -XGET https://%USER%:%CRED%@securosys.jfrog.io/artifactory/docker-security/docker-signing/%VERSION%/securosys_docker-signing-notation-plugin-%VERSION%.zip -o securosys_docker-signing-notation-plugin-%VERSION%.zip
    ```
</TabItem>
<TabItem value="browser" label="Browser" default>
    <Icon icon="fa-solid fa-download" size="lg" /> [Configuration-Files](https://securosys.jfrog.io/artifactory/docker-security/docker-signing/latest/securosys_docker-signing-notation-plugin-latest.zip)
</TabItem>
</Tabs>