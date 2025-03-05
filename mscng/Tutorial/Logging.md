---
sidebar_position: 2
title: HSM Logging Capabilities - Microsoft CNG
sidebar_label: Logging
description: Logging Capabilities Microsoft CNG for Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Logging Capabilities

Logging to a file requires CNG/KSP version V1.35 onwards.

The default _Log Level_ should always be set to **"Error"** or **"Off"** due to **performance impact**. Since the Key Storage Provider (KSP) is run in the context of the applications using it, the log file should be accessible publicly.

The Key Storage Provider (KSP) version V1.41 onwards assigns a dedicated log file per provider instance.

 ![](../img/KSP-Configuration-Logging.png)

