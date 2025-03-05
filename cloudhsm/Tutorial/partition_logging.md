---
sidebar_position: 2
title: CloudHSM Partition Logs
sidebar_label: Logs
description: Cloud-based hardware security modules (HSMs) for generating and using your own encryption keys with your applications. FIPS 140-2 Level 3 validated HSMs
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

# HSM Partition Logging

Partition-specific logs can be retrieved using either the Client API or from the [Partition Administration](../Overview/features#hsm-partition-remote-administration) application on the [Decanus Terminal](https://www.securosys.com/en/product/decanus-remote-control-terminal).

## PKCS#11

For instructions on retrieving the partition log using the [PKCS#11](../../pkcs/overview) API, please refer to the [Fetch Partition Log](../../pkcs/Tutorials/ppin_tool#fetch-partition-log) section in the documentation.

## Microsoft CNG

Partition logs can be obtained using Primus Tools. For instructions on retrieving the user log, please see the [HSM User Log](../../primus-tools/Tutorials/command-details/HSM-information#hsm-user-log) section under Primus Tools.

## JCE/JCA

Partition logs can be obtained by posting the  using Primus Tools. For instructions on retrieving the user log, please see the [HSM User Log](../../primus-tools/Tutorials/command-details/HSM-information#hsm-user-log) section under Primus Tools.

## REST API

The partition log can be directly requested through the [REST API](../../tsb/overview) by accessing the endpoint: [/v1/hsm/log](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Service%20Information/getHsmLog).

### Decanus Partition Administration

Instructions on how to retrieve the partition log via the Partition Administration application on the Decanus Terminal can be found in the [Decanus User Guide](https://support.securosys.com/external/knowledge-base/article/71), available for download form the [Support Portal](https://support.securosys.com).
