---
sidebar_position: 1
title: Create Approver (Manual)
sidebar_label: Create Approver (Manual)
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Create Approver (OpenSSL)




<Tabs groupId="key-provider">
    <TabItem value="OpenSSL" label="OpenSSL" default>
        To generate a secure key pair for an "approver" using OpenSSL, follow these steps. Note that it's important to ensure a high-quality entropy source to generate truly random keys, which is crucial for cryptographic security.
        ```sh
            openssl req -new -x509 -nodes -sha256 -newkey ec -days 3650 -subj '/CN=FinanceOfficer1' -keyout finance-officer-1.key -out finance-officer-1.crt
        ```
        The OpenSSL command above generated two files:
        - finance-officer-1.key
        - finance-officer-1.crt

        Use this command to extract the public key and then add it to SKA-Key's policy:
        ```
        openssl x509 -pubkey -noout -in finance-officer-1.crt
        ```
    </TabItem>
    <TabItem value="Approver Management - API" label="Approver Management - API">
        Use the [Approver Management API Endpoints](https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Approver%20Management/create) to create Key's within the HSM.

        When considering using the [Securosys Authorization App](https://play.google.com/store/apps/details?id=com.securosys.authorization.app&hl=en-US&pli=1) (Available on Google Playstore) this option is the easiest for you. Tutorial on how to setup the App is shown [here](./create-approver-securosys-authorization-app).       
    </TabItem>
    <TabItem value="HSM" label="HSM">
        Create and persist the approval key's within the HSM.

        **POST**: [/v1/key](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

        ```js {12}
        {
            "label": "finance-officer-1",
            "password": null,
            "algorithm": "EC",
            "curveOid": "1.2.840.10045.3.1.7",
            "attributes": {
                "decrypt": false,
                "sign": true,
                "unwrap": false,
                "extractable": false,
                "modifiable": true,
                "destroyable": true,
                "copyable": false
            },
            "policy": null
        }
           ```
        **Response:** Extract the `public-key` from the create key response and then add it to SKA-Key's policy:

        ```js {4}
        "json": {
            "label": "finance-officer-1",
            ...
            "publicKey": "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEBSBisLXR8Lsdpsc9JFeFYzMegPqufyNjk6ncWOFbb9bPMuM3I6SSDzwFNX+XvVO1EkfavxaXQcH43sEuKeYGKw==",
            "addressTruncated": null,
            "attributes": {
            ...
        }
        ```
    </TabItem>
</Tabs>