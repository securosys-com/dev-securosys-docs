---
sidebar_position: 6
title: Download PKCS#11
sidebar_label: Download
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Download the PKCS#11 API Provider

:::info Login required
This resource requires an account to the [Securosys Support Portal](https://support.securosys.com/external/knowledge-base/article/115). If you don't have an account yet:

    - **On-premises** - Primus HSM: [Contact Sales](https://www.securosys.com/en/contact)
    - **Cloud** - Securosys CloudHSM: [Create an account](https://cloud.securosys.com/sign-up) (free trial available).
:::

## 1. Retrieve the download credentials

- **Login** to the [Securosys Support Portal](https://support.securosys.com/external/knowledge-base/article/115).
- **Download** the ```PrimusAPI_PKCS11-DownloadLink-v#.txt``` (Several versions available).

## 2. Download the software package

- **Open** ```PrimusAPI_PKCS11-DownloadLink-v####.txt```. The file contains:
    - username
    - password
- **Download** the software package:

<Tabs groupId="os">
    <TabItem value="all" label="Unix" default>
        Replace the variables `CRED`, `VERSION` with the values from the ```PrimusAPI_PKCS11-DownloadLink-v####.txt```:

        ```sh {2,3}
        USER=support.reader.p11
        CRED=REPLACE_ME
        VERSION=v2.1.1
        curl -L -XGET https://${USER}:${CRED}@securosys.jfrog.io/artifactory/p11/PrimusAPI_PKCS11-${VERSION}.zip -o PrimusAPI_PKCS11-${VERSION}.zip
        ```
    </TabItem>
    <TabItem value="os-windows" label="Windows" default>
            Replace the variables `CRED`, `VERSION` with the values from the ```PrimusAPI_PKCS11-DownloadLink-v####.txt```:
    ```sh {2,3}
    set USER=support.reader.p11
    set CRED=REPLACE_ME
    set VERSION=v2.1.1
    curl -L -o "PrimusAPI_PKCS11-%VERSION%.zip" https://%USER%:%CRED%@securosys.jfrog.io/artifactory/p11/PrimusAPI_PKCS11-%VERSION%.zip
    ```
    </TabItem>
    <TabItem value="browser" label="Browser" default>
        - Username: `support.reader.p11`
        - Password: copy from ```PrimusAPI_PKCS11-DownloadLink-v####.txt```
        
        <Icon icon="fa-solid fa-download" size="lg" /> [Configuration-Files](https://securosys.jfrog.io/artifactory/p11/PrimusAPI_PKCS11-v2.1.1.zip)
    </TabItem>
</Tabs>

[download-p11-provider]: https://support.securosys.com/external/knowledge-base/article/115

### You might be interested in
- [What is CloudHSM?](/cloudhsm/overview/)
- [Getting Started with the PKCS#11 API Provider](/pkcs/Installation/pkcs11_provider_installation)
- [Application Integrations with PKCS#11](/pkcs/Use-Cases/application_integration)