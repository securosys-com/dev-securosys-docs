---
sidebar_position: 1
title: Troubleshooting Microsoft S/MIME
sidebar_label: Troubleshooting
description: Troubleshooting S/MIME
keywords: [hsm, cloud hsm]
---
# Troubleshooting

Sometimes, the receiver has difficulty opening the encrypted message. The three most likely sources of problems in this area are:
- untrusted root CAs
- intermediate CAs that can't be validated
- CRLs that are not available or accessible

## Untrusted CAs
In case you have untrusted root or intermediate CAs, verify the certificate chain and import/distribute the required verified CA certificates.

## Verify Certificate Revocation List Chain

A certificate is by default invalid if the CRL (Delta-CRL) verification fails. This can happen due to:
- CRL not retrievable (e.g. wrong configuration or CRL server not reachable)
- CRL or Delta-CRL not renewed/updated within the defined time frame
- Check if you can retrieve the certificate revocation lists from the URL indicated in the known URL or the certificate itself with the following:

```
certutil -urlfetch -verify <FilenameOfCertificate> or 
certutil -URL <URL or FilenameOfCertificate>
```

:::tip need help ?
Contact our support team for further assistance:
+ [Create a ticket (login required)](https://support.securosys.com)
+ [Send an email](mailto:support@securosys.com)
:::