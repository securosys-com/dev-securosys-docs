---
sidebar_position: 9
title: Troubleshooting Securosys CloudHSM
sidebar_label: Troubleshooting
description: Resolve issues promptly with Securosys CloudHSM troubleshooting tips. Ensure reliable performance through correct setup and proactive system monitoring.
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

import Link from '@docusaurus/Link';

# Troubleshooting

Troubleshooting steps are provided for resolving connectivity issues, server errors, and credential problems when using the CloudHSM service, including checking system status, network settings, and login credentials.


For optimal performance and trouble-free operation, it's essential to follow the [setup instructions](setup_instructions.md), especially when initiating your user account. These instructions are outlined in the quickstart section of the respective Primus API Provider's documentation, or the [Use Cases](/integrations) application notes relevant to your system. Adhering to these guidelines ensures that your account is configured correctly, setting the stage for seamless cryptographic operations.

If you encounter any issues despite following the setup instructions and selecting the appropriate network configuration parameters, there are further steps you can take to troubleshoot the problem. Firstly, we recommend checking the [System Status Page](https://status.cloudshsm.com/), which provides updates on the operational status of all CloudHSM systems. This can help identify any potential issues affecting your service.

## Connectivity Issues

### Connectivity with CloudHSM service cannot be established
**Checks:**

* [ ] Check [System Status Page](https://status.cloudshsm.com/) for the operational status.
* [ ] Ensure your network layer and firewall settings allow traffic on required endpoints, protocols, and ports per [CloudHSM Connectivity Details](../../connectivity-details/cloudhsm-connectivity-details).
* [ ] Verify your Primus API Provider is configured with correct connectivity parameters per [CloudHSM Connectivity Details](../../connectivity-details/cloudhsm-connectivity-details).
* [ ] Use `ping 'service-url'` to check network layer connectivity; if ping fails, consult your network administrator about firewall issues.
* [ ] Use `telnet 'service-url' 'service-api-provider-port'` to check network layer connectivity; if telnet fails, consult your network administrator about firewall issues.
* [ ] Ensure your source IP has not changed and matches the IP Whitelisting.
* [ ] If credentials are expired, open a ticket on the Securosys [Support Portal](https://support.securosys.com/) to request a new HSM Setup Password (note: generating a new Setup Password incurs costs).

## Provider Troubleshooting

<div className='about__cards'>
    <Link to='/pkcs/Tutorials/troubleshooting' className='about__card'>
        <div className='about__section_card'>
            <h3 className='about__header'>PKCS#11 Provider</h3>
            <p className='about__description'>
                Check out the PKCS#11-Provider troubleshooting section.
            </p>
        </div>
    </Link>
    <Link to='/jce/Tutorials/AllSamples' className='about__card'>
        <div className='about__section_card'>
            <h3 className='about__header'>JCE Provider</h3>
            <p className='about__description'>
            Check out the JCE-Provider troubleshooting section.
            </p>
        </div>
    </Link>
    <Link to='/mscng/Tutorial/KSP-utils' className='about__card'>
        <div className='about__section_card'>
            <h3 className='about__header'>MSCNG Provider</h3>
            <p className='about__description'>
            Check out the MSCNG-Provider troubleshooting section.
            </p>
        </div>
    </Link>
    <Link to='/tsb/category/tutorial' className='about__card'>
        <div className='about__section_card'>
            <h3 className='about__header'>Rest-API / TSB</h3>
            <p className='about__description'>
                Follow the samples of the Rest-API to ensure correct request bodies.
            </p>
        </div>
    </Link>
</div>

---
---

If the issue persists even after checking the system status, following the troubleshooting section of the providers, setup instructions, and performing the tests, we encourage you to submit a support ticket on our [Support Portal](https://support.securosys.com/). Our dedicated support team is equipped to assist you in resolving any technical challenges you may encounter, ensuring a smooth and uninterrupted experience with our services.


## I don't receive some emails

Please check your spam folder.

:::info need help ?
Contact our support team for further assistance:
+ [Create a ticket (login required)](https://support.securosys.com)
+ [Send an email](mailto:support@securosys.com)
:::