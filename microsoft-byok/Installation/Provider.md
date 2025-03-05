---
sidebar_position: 2
title: Azure BYOK - Credentials & Connectivity
sidebar_label: HSM Credentials and Connectivity
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';


# HSM Credentials and Connectivity
For each command using the Primus Tools the following connectivity details must be provided:
- connection details (FQDN hostname or IP address, TCP/JCE port number of the HSM)
- user (partition) and credentials (setup password or permanent secret)
- for CloudHSM both service user (proxy) and credentials (service password)

For more details on how to prepare and configure the connection and access for Primus Tools please visit [Primus Tools](/primus-tools/Installation/primus-tools-installation) guide.
