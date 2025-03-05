---
sidebar_position: 7
title: Troubleshooting Docker Image Signing Plugin
sidebar_label: Troubleshooting
description: Learn how to troubleshoot the Securosys Docker Image Signing Plugin with steps to test plugin, HSM, and TSB connectivity, as well as check communication paths. Access HSM logs and Notation troubleshooting for more insights.
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Troubleshooting
### Securosys Docker Signing Notation Plugin

## Testing Plugin connectivity

Use this command to see if the connection by the plugin to the Securosys [CloudHSM](/cloudhsm/overview/) / Primus HSM via Transaction Security Broker (TSB) can be established and to obtain statistic, with sample output: 

```sh
./notation-securosys check-connection 
```

### HSM Connectivity

:::note NOTE
When utilizing CloudHSM service, refer to [Cloud Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) for accurate API-Endpoint URI. For on-premise deployments, verify API-Endpoint URI with your administrator. Contact your service administrator for authentication credentials in any setup (on-prem or cloud).
:::

### Testing TSB connectivity

Connectivity to the Transaction Security Broker (TSB) can be tested using the curl command below or via swaggerUI. This test does not verify the connectiviity with the HSM infrastrustructure. Adapt **`<TSB_APIendpoint>`**, **`<yourBearerToken>`** for your environment:

```sh
curl -X
    'GET' '<TSB_APIendpoint>/v1/versionInfo' \   
    -H 'accept: application/json' \
    -H 'Authorization: Bearer <yourBearerToken>'
```

### Testing complete communication path

Connectivity through the Transaction Security Broker (TSB) to the HSM infrastructure can be tested using the curl command below or via swaggerUI. Adapt **`<TSB_APIendpoint>`**, **`<yourBearerToken>`** for your environment:

```sh
curl -X
    'GET' '<TSB_APIendpoint>/v1/licenseInfo' \   
    -H 'accept: application/json' \
    -H 'Authorization: Bearer <yourBearerToken>'
```

## Troubleshooting Primus HSM

Additional information can be obtained by reviewing the HSM logs. 

- To review the HSM logs of your on-premises Primus HSM please refer to the [Securosys Primus HSM User Guide](https://support.securosys.com/external/knowledge-base/article/63) chapter 4.7.


## More Troubleshooting

Further troubleshooting can be done via the _Notation_, or by reviewing the HSM logs. 

- To review the HSM logs of your on-premises Primus HSM please refer to [Securosys Primus HSM User Guide](https://support.securosys.com/external/knowledge-base/article/63), 

- To view how to troubleshoot _Notation_ please visit: [Notary Notation Troubleshooting](https://notaryproject.dev/docs/user-guides/troubleshooting/).
