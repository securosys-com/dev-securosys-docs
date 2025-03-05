---
sidebar_position: 6
title: Download Securosys HSM XKS Proxy for AWS KMS
sidebar_label: Download
description: Access the Securosys XKS Proxy for AWS KMS by downloading the software from the Securosys Support Portal. Get the required credentials and instructions for Unix, Windows, and Browser systems.
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Download XKS Proxy for AWS KMS

:::warning Download access

To download the software, you must be a **registered support user**. <br />
If you are **new** to developing applications with a Securosys-HSM, you can obtain a **free developer account** by contacting us at info@securosys.com. 
:::

---

## Download Link & Credentials

To download the _Securosys External Key Store (XKS) Proxy for AWS KMS_ retrieve the **Download-Link-File** from the Securosys Support Portal Knowledge Base article in the _Download_ section. This file contains the credentials necessary for downloading the software package.

<Icon icon="fa-solid fa-download" size="lg" /> [Support Portal: Download-Link-File](https://support.securosys.com/external/knowledge-base/article/173)

---

## Software Package

Replace the variables `CRED`, `VERSION` with the values from the **Download-Link-File**.

<Tabs groupId="os">
<TabItem value="unix" label="Unix" default>
    ```sh {2,3}
    USER=robot.reader.xks
    CRED=REPLACE_ME
    VERSION=latest
    curl -L -XGET https://${USER}:${CRED}@securosys.jfrog.io/artifactory/aws-xks/aws_xks-${VERSION}.zip -o aws_xks-${VERSION}.zip
    ```
</TabItem>
<TabItem value="win" label="Windows" default>
    ```sh {2,3}
    set USER=robot.reader.xks
    set CRED=REPLACE_ME
    set VERSION=latest
    curl -L -o "aws_xks-%VERSION%.zip" https://%USER%:%CRED%@securosys.jfrog.io/artifactory/aws-xks/%VERSION%/aws_xks-%VERSION%.zip
    ```
</TabItem>
<TabItem value="browser" label="Browser" default>
    Username: `robot.reader.xks`

    <Icon icon="fa-solid fa-download" size="lg" /> [Configuration-Files](https://securosys.jfrog.io/artifactory/aws-xks/aws_xks-latest.zip)
</TabItem>
</Tabs>

### More content

- [Getting Started with XKS Proxy for AWS](./Quickstart/quickstart.md)
- [Example - Creation of an XKS in AWS KMS](./Tutorials/Examples/Example-AWS-KMS.md)
- [Tutorial - How to encrypt/decrypt keys in AWS KMS?](./Tutorials/Examples/Example-AWS-KMS.md)
