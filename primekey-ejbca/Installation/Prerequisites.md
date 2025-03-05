---
sidebar_position: 0
title: Prerequisites - Keyfactor EJBCA and HSMs
sidebar_label: Prerequisites
description: "Discover how the Keyfactor and PrimeKey merger enhances machine identity management with end-to-end PKI solutions. Integrating Keyfactor's automation with PrimeKey’s EJBCA software, this robust platform supports IoT, DevOps, and enterprise environments. Secure your PKI with Securosys HSMs for strong compliance and key protection."
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Prerequisites

Make sure to adhere to the below listed prerequisites as they are paramount to continuing with the guide.

- OpenJDK 8
- [EJBCA Enterprise](https://www.keyfactor.com/products/ejbca-enterprise/) 7.x, 8.x or [EJBCA community edition](https://www.ejbca.org/) 6.x, 7.x, 8.x
- JbossEAP 7.2 or newer
- Securosys [PKCS#11 Provider](/pkcs/overview) v1.8.x or newer
- [CloudHSM](/cloudhsm/overview) Service (HSM as a Service) or Primus HSM, firmware v2.8.21 or newer
- PKCS#11 API licensed

## HSM Setup and Configuration

<Tabs groupId="HSM Setup & Configuration">
  <TabItem value="HSMaas" label="Securosys CloudHSM" default>

Securosys [CloudHSM](/cloudhsm/overview) allows almost instant HSM operation by selecting and contracting the different services and options for your Keyfactor/PrimeKey EJBCA.
For available service packages and options consult our website [Securosys CloudHSM Service](https://www.securosys.com/en/product/cloudshsm) and [contact the sales team](https://www.securosys.com/en/contact). 

:::warning
Ensure the PCKS#11 API is included and activated in your subscription.
:::
  </TabItem>
    <TabItem value="on-premises" label="Securosys Primus HSM (on-premises)" default>

Setup the Primus HSM for PKCS#11 usage with the [User Guide](/pkcs/Installation/primus_hsm_settings).

:::warning
Ensure the PCKS#11 API is licensed and activated on your device.
:::

For general or on-premises Primus HSM hardware, HA Cluster setup and operation in FIPS or Common Criteria certified modes, refer to the corresponding [User Guide](https://support.securosys.com/external/knowledge-base/article/63) (account required).
  </TabItem>
</Tabs>