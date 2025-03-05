---
sidebar_position: 3
title: Docker Encryption Plugin - Troubleshooting
sidebar_label: Troubleshooting
description: Docker Encryption - Troubleshooting
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Troubleshooting

## Troubleshoot the Securosys Docker Image Encryption Plugin

### Skopeo

The troubleshooting capabilities of the plugin and Skopeo is limited. Please review any error messages carefully upon command execution. 

### HSM Connectivity

:::note NOTE
When utilizing CloudHSM service, refer to [Cloud Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) for accurate API-Endpoint URI. For on-premise deployments, verify API-Endpoint URI with your administrator. Contact your service administrator for authentication credentials in any setup (on-prem or cloud).
:::

### Testing TSB connectivity

Connectivity to the TSB can be tested using the curl command below or via swaggerUI. This test does not verify the connectiviity with the HSM infrastrustructure. Adapt **`<TSB_APIendpoint>`**, **`<yourBearerToken>`** for your environment:

```sh
curl -X
    'GET' '<TSB_APIendpoint>/v1/versionInfo' \   
    -H 'accept: application/json' \
    -H 'Authorization: Bearer <yourBearerToken>'
```

### Testing complete communication path

Connectivity through the TSB to the HSM infrastructure can be tested using the curl command below or via swaggerUI. Adapt **`<TSB_APIendpoint>`**, **`<yourBearerToken>`** for your environment:

```sh
curl -X
    'GET' '<TSB_APIendpoint>/v1/licenseInfo' \   
    -H 'accept: application/json' \
    -H 'Authorization: Bearer <yourBearerToken>'
```

## Troubleshooting Primus HSM

Additional information can be obtained by reviewing the HSM logs. 

- To review the HSM logs of your on-premises Primus HSM please refer to the [Securosys Primus HSM User Guide](https://support.securosys.com/external/knowledge-base/article/63) (account required) chapter 4.7.