---
sidebar_position: 5
title: CloudHSM Setup Instructions & Configuration
sidebar_label: 4. Connect HSM
description: Quickstart guides for Java Cryptography Extension, Microsoft CNG, and PKCS#11 setup in CloudHSM. Follow step-by-step for seamless integration.
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Connect to your HSM

:::info prerequisites

To connect to your HSM, you will need:
- **HSM Service Credentials**
- [Network details](../../connectivity-details/cloudhsm-connectivity-details) (IP address / URLs)
:::

### Summary 

1) Install the **Primus API Provider** to connect your application to your Hardware Security Module (HSM) in the cloud. 
1) Use the setup password to initiate your account, establish a first connection, and obtain the user secret for a permanent integration.
1) Apply the relevant network configuration. This was provided with the credentials. You can also find them [here](../../connectivity-details/cloudhsm-connectivity-details).

<Tabs groupId="api-provider">
<TabItem value="rest" label="Rest-API">

- Download the [REST API](/tsb/Download/downloads) configuration file

</TabItem>
<TabItem value="jce" label="JCE/JCA" default>

- Download the [Java Cryptography Extension (JCE/JCA)](/jce/Downloads) provider
- Initiate the CloudHSM User Account
    - Being with the [Quickstart](/jce/quickstart) section to set up your initial connection.
    - Continue with the [Tutorials](/jce/category/tutorial) to learn more about the library.

</TabItem>
<TabItem value="mcng" label="MCNG">
- Download the [Microsoft CNG](/mscng/downloads) provider
- Initiate the CloudHSM User Account
    - Being with the [Quickstart](/mscng/Quickstart) section to set up your initial connection.
    - Continue with the [Tutorials](/mscng/category/tutorial) to learn more about the library.

</TabItem>
<TabItem value="pkcs11" label="PKCS#11">
- Download the [PKCS#11 (Cryptoki)](/pkcs/downloads) provider
- Initiate the CloudHSM User Account
    - Being with the [Quickstart](/pkcs/quickstart) section to set up your initial connection.
    - Continue with the [Tutorials](/pkcs/category/tutorial) to learn more about the library.

</TabItem>
</Tabs>

:::danger Limited lifetime
Remember to retrieve the permanent secret within the **7-day lifespan** of the HSM Setup Password.
:::
---

Continue with the [step 5: Connect your application](./Connect_application.md).

:::tip need help ?

Check the [troublehsooting](./troubleshooting) section or contact our support team for further assistance:
+ [Create a ticket (login required)](https://support.securosys.com)
+ [Send an email](mailto:support@securosys.com)
:::