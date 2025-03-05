---
sidebar_position: 2
title: Salesforce BYOK - HSM Connectivity
sidebar_label: HSM Credentials and Connectivity
description: Description overview
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';


# HSM Credentials and Connectivity

For each command using the [Primus Tools](../../primus-tools/overview). the following connectivity details must be provided:
- Connection details (FQDN hostname or IP address, TCP/JCE port number of the HSM),
- user (partition) and credentials (setup password or permanent secret),
- for [CloudHSM](../../cloudhsm/overview), both service user (proxy) and credentials (service password).

General connectivity details for on-premises Primus HSM and [CloudHSM](../../cloudhsm/overview) can be found under [Connectivity Details](../../connectivity-details/overview).

:::info
Keep your HSM credentials at hand, whether acquired from your on-premises Primus HSM administrator or from the Securosys support team for [CloudHSM](../../cloudhsm/overview).
:::


For more details on how to prepare and configure the connection and access for Primus Tools please visit [Primus Tools - Installation](../../primus-tools/Installation/primus-tools-installation) guide.
