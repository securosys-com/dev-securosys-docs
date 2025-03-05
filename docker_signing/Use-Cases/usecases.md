---
sidebar_position: 0
title: Docker Image Signing with HSM
sidebar_label: Signing Docker Image
description: Learn how Docker image signing strengthens supply-chain security. Use Notation CLI and Securosys HSM to verify trusted container images.
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Signing Docker Image 

With the growing use of containers, it’s essential to strengthen software supply-chain security by ensuring that the container images we run on Kubernetes clusters are trusted. We do this by **signing** the container images.

**Notation** is a Command Line Interface (CLI) project designed to add signatures as standard items in the Open Container Initiative (OCI) registry ecosystem. It provides simple tools for signing and verifying container image signatures, similar to how git commit signatures are verified. These signatures help ensure that the images are authentic and haven’t been tampered with.

For more, see [Signing Docker Image Scenarios](https://github.com/notaryproject/specifications/blob/main/requirements/scenarios.md#scenario-0-build-publish-consume-enforce-policy-deploy).