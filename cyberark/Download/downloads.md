---
sidebar_position: 7
title: Download HSM PKCS#11 Provider - CyberArk PAM
sidebar_label: Download
description: Download the PrimusAPI PKCS#11 provider for CyberArk PAM from the Securosys Support Portal. Access requires registration, and a free developer account is available for new users.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Download PKCS#11 for CyberArk PAM

:::warning Download access

Access to the software download requires registration as a
**registered support user**. <br /> If you are **new** to developing
applications with a Securosys-HSM, you can obtain a **free developer
account** by contacting us at info@securosys.com.

:::

---

## Download Link & Credentials

To download the _PrimusAPI PKCS#11_ retrieve the
**Download-Link-File** from the Securosys Support Portal Knowledge
Base article in the _Download_ section. This file contains the
credentials necessary for downloading the software package.


<Icon icon="fa-solid fa-download" size="lg" /> [Support Portal: Download-Link-File][download-p11-provider]

---
---


## Configuration Files

Replace the variables `CRED`, `VERSION` with the values from the **Download-Link-File**.

<Tabs groupId="os">
    <TabItem value="all" label="Unix" default>
        ```sh {2,3}
        USER=support.reader.p11
        CRED=REPLACE_ME
        VERSION=v2.1.1
        curl -L -XGET https://${USER}:${CRED}@securosys.jfrog.io/artifactory/p11/PrimusAPI_PKCS11-${VERSION}.zip -o PrimusAPI_PKCS11-${VERSION}.zip
        ```
    </TabItem>
    <TabItem value="win" label="Windows" default>
    ```sh {2,3}
    set USER=support.reader.p11
    set CRED=REPLACE_ME
    set VERSION=v2.1.1
    curl -L -o "PrimusAPI_PKCS11-%VERSION%.zip" https://%USER%:%CRED%@securosys.jfrog.io/artifactory/p11/PrimusAPI_PKCS11-%VERSION%.zip
    ```
    </TabItem>
    <TabItem value="browser" label="Browser" default>
        Username: `support.reader.p11`

        <Icon icon="fa-solid fa-download" size="lg" /> [Configuration-Files](https://securosys.jfrog.io/artifactory/p11/PrimusAPI_PKCS11-v2.1.1.zip)
    </TabItem>
</Tabs>

[download-p11-provider]: https://support.securosys.com/external/knowledge-base/article/115

