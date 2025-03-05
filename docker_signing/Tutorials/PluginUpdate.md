---
sidebar_position: 6
title: How to Update Docker Signing Notation Plugin
sidebar_label: Updating
description: Learn the steps to safely update the Securosys Docker Signing Notation Plugin. Review release notes, deploy in test environments, and replace old versions with ease.
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# How to update the plugin?

### Securosys Docker Signing Notation Plugin

:::danger CAUTION
The update of the Securosys Docker Signing Notation Plugin may affect your **production** environment. We recommend always reading the release notes prior to the update, as well as deploying the update in a test environment.
:::

To update the **Securosys Docker Signing Notation Plugin**, please follow the below steps: 

- [ ] Download the latest version of the Securosys Docker Signing Notation Plugin.

- [ ] Make sure to review the release notes as they contain important information about the update. 

- [ ] Navigate to the directory where you downloaded the newest version and unpack the newest Securosys Docker Signing Notation Plugin. For more information on how to unzip the plugin please visit [chapter](../Installation/DonwloadPlugin#download-securosys-docker-signing-notation-plugin-files). 

- [ ] Replace your old version of the Securosys Docker Signing Notation Plugin with the new version by deleting the old version and copying the new version to the `{BINARY_PATH}`. 

- [ ] Update the plugin's `config.json` file, if necessary. 