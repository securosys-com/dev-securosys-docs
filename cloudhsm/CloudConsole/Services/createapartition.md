---
sidebar_position: 2
title: Create a partition - Cloud Console
sidebar_label: Create a partition
description: Create a partition on Cloud Console
keywords: [hsm clusters, hsm hostnames, primus hsm, securosys]
tags: [Cloud Console, CloudHSM]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Create a partition

Securosys [CloudHSM Services](../../Packages/overview) provide users with access to HSM partitions.

Securosys [CloudHSM Services](../../Packages/overview) partitions are available in both FIPS and non-FIPS modes.

- **FIPS** mode partitions provide access to a limited set of FIPS approved cryptographic algorithms that adhere to the standards and requirements of the NIST. FIPS mode partitions use the latest FIPS 140-2 level 3 certified firmware.

- **Non-FIPS** mode partitions provide access to an unrestricted set of cryptographic algorithms that includes the algorithms from the FIPS list as well as additional algorithms such as elliptic curves. Non-FIPS mode partitions use the latest firmware which includes updates, bug fixes and enhancements. As a result, non-FIPS partitions may be using a firmware which is not FIPS certified.

Refer to the list of [**supported Algorithms & Functions**](../../Overview/Supported_Algorithms_and_Functions)  for a list of available FIPS and non-FIPS algorithms.

<Tabs groupId="partition-channel">
<TabItem value="cloudconsole" label="Cloud Console" default>
Securosys [**CloudHSM Services**](../../Packages/overview) provisioned through [Cloud Console](https://cloud.securosys.com) provide access to a single partition per service. The partition is automatically generated and registered on service creation.

</TabItem>
<TabItem value="offline" label="Offline">

For now, the Securosys [**CloudHSM Services**](../../Packages/overview) provisioned through other channels, such as Sales, cannot be managed through [Cloud Console](https://cloud.securosys.com).

If you want to initiate a CloudHSM service after talking to Sales, please follow [this guide](../../GettingStarted/activation_process)

</TabItem>
</Tabs>
