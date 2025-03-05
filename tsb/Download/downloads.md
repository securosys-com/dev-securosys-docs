---
sidebar_position: 1
title: Download - REST-API
sidebar_label: Download
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Download


## Releases

#### v2.x Branch
To fully leverage the features introduced in TSB version 2.0, Primus/CloudsHSM must be upgraded to at least version 3.x. 
Note that all Securosys CloudHSM Clusters are supported, except for CommonCriteria Clusters.

Artifactory Credentials to pull the docker-image are available here: [Download-Link-File](https://support.securosys.com/external/knowledge-base/article/114)

|Version|Release Date|Image|Release Notes|
|---|---|---|---|
|v2.2.0|2024.12.18 (latest)|securosys.jfrog.io/external-tsb/securosys-tsb:2.2.0|[Release Notes - v2.2.0](../Download/release_notes#securosys-tsb-220)|
|v2.1.1|2024.09.23|securosys.jfrog.io/external-tsb/securosys-tsb:2.1.1|[Release Notes - v2.1.1](../Download/release_notes#securosys-tsb-211)|
|v2.1.0|2024.08.16|securosys.jfrog.io/external-tsb/securosys-tsb:2.1.0|[Release Notes - v2.1.0](../Download/release_notes#securosys-tsb-210)|
|v2.0.1|2024.07.25|securosys.jfrog.io/external-tsb/securosys-tsb:2.0.1|[Release Notes - v2.0.1](../Download/release_notes#securosys-tsb-201)|
|v2.0.0|2024.04.04|securosys.jfrog.io/external-tsb/securosys-tsb:2.0.0|[Release Notes - v2.0.0](../Download/release_notes#securosys-tsb-200)|


----

## Download Artifactory Credentials



To get the Docker-Artifactory Credentials for the _Securosys Transaction Security Broker_ retrieve the **Download-Link-File** from the Securosys Support Portal in the _Download_ section.

<Icon icon="fa-solid fa-download" size="lg" /> [Support Portal: Download-Link-File](https://support.securosys.com/external/knowledge-base/article/114)

---

## Download Configuration Files

Download the configuration files for the initial setup or whenever application parameters change. These files include all relevant parameters and Docker-compose templates necessary to run the application.

Currently the latest version is: **`VERSION=v2.2.0`** - Note the **v** in front of the version-number.

<Tabs groupId="os">
<TabItem value="win" label="Windows" default>
    ```sh {2,3}
    set USER=robot.reader.tsb
    set CREDENTIAL=REPLACE_ME
    set VERSION=latest
    curl -L -o "securosys_TSB-%VERSION%.zip" https://%USER%:%CREDENTIAL%@securosys.jfrog.io/artifactory/external-tsb/%VERSION%/Securosys_TSB-%VERSION%.zip
    ```
</TabItem>
<TabItem value="browser" label="Browser" default>
    Username: `robot.reader.tsb`

    <Icon icon="fa-solid fa-download" size="lg" /> [Configuration-Files](https://securosys.jfrog.io/artifactory/external-tsb/latest/Securosys_TSB-latest.zip)
</TabItem>
</Tabs>

---

## Software Download (Docker pull)

To launch the multi-container application, execute the following command in the directory where the docker-compose.yml file is located.

```sh
:~$ docker login securosys.jfrog.io -u robot.reader.tsb
```
Password available on [Support Portal: Download-Link-File](https://support.securosys.com/external/knowledge-base/article/114)

```sh
:~$ docker pull securosys.jfrog.io/external-tsb/securosys-tsb:latest
```

## Release History

#### v1.x Branch

Artifactory Credentials to pull the docker-image are available here: [Download-Link-File](https://support.securosys.com/external/knowledge-base/article/114)

|Version|Release Date|Image|Release Notes|
|---|---|---|---|
|v1.18.0|2024.03.07|securosys.jfrog.io/external-tsb/securosys-tsb:1.18.0|[Release Notes - v1.18.0](../Download/release_notes#securosys-tsb-1180)|
|v1.17.1|2023.12.11|securosys.jfrog.io/external-tsb/securosys-tsb:1.17.1|[Release Notes - v1.17.1](../Download/release_notes#securosys-tsb-1171)|
|v1.17.0|2023.11.06|securosys.jfrog.io/external-tsb/securosys-tsb:1.17.0|[Release Notes - v1.17.0](../Download/release_notes#securosys-tsb-1170)|
|v1.16.1|2023.10.10|securosys.jfrog.io/external-tsb/securosys-tsb:1.16.1|[Release Notes - v1.16.1](../Download/release_notes#securosys-tsb-1161)|
|v1.16.0|2023.07.28|securosys.jfrog.io/external-tsb/securosys-tsb:1.16.0|[Release Notes - v1.16.0](../Download/release_notes#securosys-tsb-1160)|
|v1.15.2|2023.07.19|securosys.jfrog.io/external-tsb/securosys-tsb:1.15.2|[Release Notes - v1.15.2](../Download/release_notes#securosys-tsb-1152)|
|v1.15.1|2023.04.12|securosys.jfrog.io/external-tsb/securosys-tsb:1.15.1|[Release Notes - v1.15.1](../Download/release_notes#securosys-tsb-1151)|
|v1.14.1|2022.12.07|securosys.jfrog.io/external-tsb/securosys-tsb:1.14.1|[Release Notes - v1.14.1](../Download/release_notes#securosys-tsb-1141)|
|v1.14.0|2022.10.31|securosys.jfrog.io/external-tsb/securosys-tsb:1.14.0|[Release Notes - v1.14.0](../Download/release_notes#securosys-tsb-1140)|
|v1.11.0|2022.04.21|securosys.jfrog.io/external-tsb/securosys-tsb:1.11.0|[Release Notes - v1.11.0](../Download/release_notes#securosys-tsb-1110)|
