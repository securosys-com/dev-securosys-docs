---
sidebar_position: 1
title: Download - Secrets Engine plugin for HashiCorp Vault
sidebar_label: Download
description: Download Securosy' Secrets Engine plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Download the Secrets Engine plugin

:::warning Download access

To download the Secrets Engine plugin, you must be be a **registered support user**. <br />
If you are **new** to developing applications with a Securosys-HSM, you can obtain a **free developer account** by contacting us at info@securosys.com. 
:::

---

## Link & Credentials

To download the _HCVault Secrets Engine_ retrieve the **Download-Link-File** from the Securosys Support Portal Knowledge Base article in the _Download_ section. This file contains the credentials necessary for downloading the software package.

<Icon icon="fa-solid fa-download" size="lg" /> [Support Portal: Download-Link-File](https://support.securosys.com/external/knowledge-base/article/191)

<Icon icon="fa-brands fa-github" size="lg" /> [Github](https://github.com/securosys-com/hcvault-plugin-secrets-engine/releases)

---
---

## Software Download

Replace the variables `CRED`, `VERSION` with the values from the **Download-Link-File**.

<Tabs groupId="os">
<TabItem value="unix" label="Unix" default>
    ```sh {2,3}
    USER=robot.reader.hashicorpvault
    CRED=FTTGEcruzB_QUf3LBsq+KVV3wYuSx_
    VERSION=latest    
    curl -L -XGET https://${USER}:${CRED}@securosys.jfrog.io/artifactory/hcvault-plugin-secrets-engine/hcvault_plugin-secrets-engine-${VERSION}.zip -o hcvault_plugin-secrets-engine-${VERSION}.zip
    ```
</TabItem>
<TabItem value="win" label="Windows" default>
    ```sh {2,3}
    set USER=robot.reader.hashicorpvault
    set CRED=FTTGEcruzB_QUf3LBsq+KVV3wYuSx_
    set VERSION=latest
    curl -L -o "hcvault_plugin-secrets-engine-%VERSION%.zip" https://%USER%:%CRED%@securosys.jfrog.io/artifactory/hcvault-plugin-secrets-engine/hcvault_plugin-secrets-engine-%VERSION%.zip
    ```
</TabItem>
<TabItem value="browser" label="Browser" default>
    <Icon icon="fa-solid fa-download" size="lg" /> [Configuration-Files](https://securosys.jfrog.io/artifactory/hcvault-plugin-secrets-engine/hcvault_plugin-secrets-engine-latest.zip)
</TabItem>
</Tabs>
