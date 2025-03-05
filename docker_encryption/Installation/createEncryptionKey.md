---
sidebar_position: 2
title: Docker Encryption Plugin- Create A Key
sidebar_label: 3. Create A Key
description: Create A Key
keywords: [hsm, cloud hsm]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Create A Key

Optionally, in case you haven’t created a Docker image encryption key before, create the encryption key via the TSB swagger-UI by accessing the key store with the /v1/key POST command.

:::note NOTE
When utilizing [CloudHSM service](../../cloudhsm/overview), refer to [Cloud Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) for accurate API-Endpoint URI. For on-premise deployments, verify API-Endpoint URI with your administrator. Contact your service administrator for authentication credentials in any setup (on-prem or cloud).
:::

Add the [TSB](../../tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker) connection parameters provided by the Securosys [CloudHSM](../../cloudhsm/overview) operations team or by your HSM/TSB administrator and adapt the marked parameters according your needs.

Example of a `curl` command for key creation: 

<Tabs groupId="device-setup">
  <TabItem value="linux" label="Linux / MacOS" default>
```sh
curl -X 'POST' \ 
# highlight-next-line-note
# highlight-next-line
    '<TSB_APIendpoint>/v1/key' \
    -H 'accept: application/json' \ 
# highlight-next-line-note
# highlight-next-line
    -H 'Authorization: Bearer <bearer_token>'\
    -H 'Content-Type: application/json' \ 
    -d '{ 
# highlight-next-line-note
# highlight-next-line
    "label": "SecurosysEncKey01", 
    "algorithm": "RSA", 
    "keySize": 2048, 
    "attributes": {
# highlight-start
# highlight-note-start
     "encrypt": true,
     "decrypt": true,
# highlight-end     
# highlight-note-end
     "verify": false, 
     "sign": false, 
     "wrap": false, 
     "unwrap": false, 
     "derive": false, 
     "bip32": false, 
     "extractable": false,
     "modifiable": false,
     "destroyable": false,
     "sensitive": true,
     "copyable": false 
    } 
} '
``` 
  </TabItem>
  <TabItem value="Windows" label="Windows" default>
```sh
curl --request POST ^
# highlight-next-line-note
# highlight-next-line
     --url "https://<TSB_APIendpoint>/v1/key" ^
     --header "accept: application/json" ^
# highlight-next-line-note
# highlight-next-line
     --header "Authorization: Bearer <bearer_token>" ^
     --header "Content-Type: application/json" ^
# highlight-next-line-note
# highlight-next-line
     --data "{\"label\":\"SecurosysEncKey03\",\"algorithm\":\"RSA\",\"keySize\":2048,\"attributes\":{\"encrypt\":true,\"decrypt\":true,\"sign\":false,\"unwrap\":false,\"extractable\":false,\"modifiable\":false,\"destroyable\":false,\"sensitive\":true,\"copyable\":false}}"
```
  </TabItem>
</Tabs>

:::danger WARNING

To enable support for the Securosys Docker Image Encryption plugin, ensure that the key flags `encrypt` and `decrypt` are configured to **`true.`**

:::