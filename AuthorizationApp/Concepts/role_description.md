---
sidebar_position: 1
title: Authorization App Role Description
sidebar_label: Role Description
description: Prerequisites to install the Securosys Authorization Mobile App
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Role Description

The following role-based structure is used in this guide:

|Role|Description|
|--|--|
|Apporver Manager| Personnel responsible for creating and managing the entire pool of Approver's. Utilizes the Transaction Security Broker with Rest-API commands to create SKA enabled keys. Has access to the HSM keystore.|
|Approver|The user of the Securosys Authorization App approves or denies approval tasks.|
|Business Application| Application connected to the Transaction Security Broker which sends in requests for cryptographic operations such as signing, decryption, unwrap as well as SKA Key creation.|
|HSM Administrator| Personnel in charge of the Primus HSM administration.|
