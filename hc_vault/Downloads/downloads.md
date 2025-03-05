---
sidebar_position: 6
title: Download - HashiCorp Vault Community
sidebar_label: HashiCorp Vault Integration
description: Download - HashiCorp Vault Community Integration
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Download

---

## Credentials

Below is the link to download Securosys **REST-based Hardware Security Module (HSM) integration** for HashiCorp Vault Community, which contains the credentials to access the software and its configuration files.

<Icon icon="fa-solid fa-download" size="lg" /> [Download](https://support.securosys.com/external/knowledge-base/article/192) (account required)

<Icon icon="fa-brands fa-github" size="lg" /> [Github](https://github.com/securosys-com/hcvault-ce-rest-integration/releases)

---

## Configuration Files
<Tabs groupId="os">
<TabItem value="unix" label="Unix" default>
    ```sh {2,3}
    USER=robot.reader.hashicorpvault
    CRED=FTTGEcruzB_QUf3LBsq+KVV3wYuSx_
    VERSION=1.2.5
    curl -L -XGET https://${USER}:${CRED}@securosys.jfrog.io/artifactory/hcvault-ce-rest-integration/${VERSION}/config-files/HCVault_CE-Rest-Integration_${VERSION}.zip
    ```
</TabItem>
<TabItem value="win" label="Windows" default>
    ```sh {2,3}
    set USER=robot.reader.hashicorpvault
    set CRED=FTTGEcruzB_QUf3LBsq+KVV3wYuSx_
    set VERSION=1.2.5
    curl -L -o "HCVault_CE-Rest-Integration_%VERSION%.zip" https://%USER%:%CRED%@securosys.jfrog.io/artifactory/hcvault-ce-rest-integration/%VERSION%/config-files/HCVault_CE-Rest-Integration_%VERSION%.zip
    ```
</TabItem>
<TabItem value="browser" label="Browser" default>
    <Icon icon="fa-solid fa-download" size="lg" /> [Configuration-Files](https://securosys.jfrog.io/ui/native/hcvault-ce-rest-integration/1.2.5/config-files/HCVault_CE-Rest-Integration_1.2.5.zip)
</TabItem>
</Tabs>


## Binaries Files


<Tabs groupId="os">
<TabItem value="unix" label="Unix" default>
    ```sh {2,3}
    USER=robot.reader.hashicorpvault
    CRED=FTTGEcruzB_QUf3LBsq+KVV3wYuSx_
    VERSION=1.2.5
    curl -L -XGET https://${USER}:${CRED}@securosys.jfrog.io/artifactory/hcvault-ce-rest-integration/${VERSION}/executable-files/HCVault_CE-Rest-Integration_${VERSION}_darwin_amd64.zip
    ```
</TabItem>
<TabItem value="win" label="Windows" default>
    ```sh {2,3}
    set USER=robot.reader.hashicorpvault
    set CRED=FTTGEcruzB_QUf3LBsq+KVV3wYuSx_
    set VERSION=1.2.5
    curl -L -o "HCVault_CE-Rest-Integration_%VERSION%_windows_amd64.zip" https://%USER%:%CRED%@securosys.jfrog.io/artifactory/hcvault-ce-rest-integration/%VERSION%/executable-files/HCVault_CE-Rest-Integration_%VERSION%_windows_amd64.zip
   ```
</TabItem>
</Tabs>