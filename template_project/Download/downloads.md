---
sidebar_position: 7
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

To download the _Securosys Transaction Security Broker_ retrieve the **Download-Link-File** from the Securosys Support Portal Knowledge Base article in the _Download_ section. This file contains the credentials necessary for downloading the software package.

<Icon icon="fa-solid fa-download" size="lg" /> [Support Portal: Download-Link-File](https://support.securosys.com/external/knowledge-base/article/114)

---

## Software Download

To launch the multi-container application, execute the following command in the directory where the docker-compose.yml file is located. This command initiates the startup process for the two containers: `securosys_sql` and `securosys_tsb`

```sh
:~$ docker login securosys.jfrog.io -u robot.reader.tsb
```

```sh
:~$ docker pull securosys.jfrog.io/external-tsb/securosys-tsb:latest
```

---

## Configuration Files

Replace the variables `CRED`, `VERSION` with the values from the **Download-Link-File**.

<Tabs groupId="os">
<TabItem value="unix" label="Unix" default>
    ```sh {2,3}
    USER=robot.reader.tsb
    CRED=REPLACE_ME
    VERSION=latest
    curl -L -XGET https://${USER}:${CRED}@securosys.jfrog.io/artifactory/external-tsb/${VERSION}/securosys_TSB-${VERSION}.zip -o securosys_TSB-${VERSION}.zip
    ```
</TabItem>
<TabItem value="win" label="Windows" default>
    ```sh {2,3}
    set USER=robot.reader.tsb
    set CRED=REPLACE_ME
    set VERSION=latest
    curl -L -o "securosys_TSB-%VERSION%.zip" https://%USER%:%CRED%@securosys.jfrog.io/artifactory/external-tsb/%VERSION%/securosys_TSB-%VERSION%.zip
    ```
</TabItem>
<TabItem value="browser" label="Browser" default>
    Username: `robot.reader.tsb`

    <Icon icon="fa-solid fa-download" size="lg" /> [Configuration-Files](https://securosys.jfrog.io/artifactory/external-tsb/latest/securosys_TSB-latest.zip)
</TabItem>
</Tabs>