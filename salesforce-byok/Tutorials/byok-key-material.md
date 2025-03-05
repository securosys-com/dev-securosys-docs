---
sidebar_position: 1
title: Salesforce - Generate and Wrap BYOK Key Material
sidebar_label: Generate and Wrap BYOK Key Material
description: Description overview
keywords: [hsm, cloud hsm]
---

# Generate and Wrap BYOK Key Material

Before exporting and wrapping the BYOK key material, some interim steps must be made. 

## Convert Salesforce Certificate

The Primus Tools `SalesforceByokExport` command currently allows for either `.der` or `.pem` formats, therefore a conversion of the Salesforce BYOK-compatible certificate file must be done. 
Convert the `.crt` file, downloaded from Salesforce, to `.pem` format. There are different tools that allow this conversion.

In our example the conversion is done with the [OpenSSL library](https://www.openssl.org/). For more information on OpenSSL, please refer to [OpenSSL documentation](https://www.openssl.org/docs/). 

With the library installed, use OpenSSL to change the certificate from `.crt` to `.pem` format.

Example:
```bash
openssl x509 -in BYOK1.crt -out BYOK1.pem
```

## Create Tenant Secret Key

The tenant secret key is used as a base key from which the BYOK key material is derived. This tenant secret will be encrypted by the public key from the BYOK-compatible certificate downloaded in chapter [Generate Salesforce BYOK-Compatible Certificate](/salesforce-byok/Tutorials/salesforce-byok-certificate). To create the tenant secret key the `CreateKey` Primus Tools command can be used. Skip this step if you already have a key prepared with the required parameters.

Create an HMAC key with [Primus Tools](../../primus-tools/overview). CreateKey command:
```bash
java -jar primus-tools.jar CreateKey <HSM connection and credentials> -keyname SalesforceHMAC -type HMACSHA256
```
- Make sure to replace the `<HSM connection and credentials>` parameter. See [HSM Connection and Access Credentials](/primus-tools/Installation/Provider) for more details.
- See [Connectivity Details](../../connectivity-details/overview) for general connection parameters required for either [CloudHSM](../../cloudhsm/overview) or on-premises Primus HSM.
- With `-keyname` specify the name of the tenant key.
- Keep the `-type` parameter set to `HMACSHA256` as it is required for the Salesforce BYOK procedure.

:::note
For more information on the `CreateKey` command, see [Primus Tools - Create Key](../../primus-tools/Tutorials/command-details/object-management#create-key).
:::

:::note
Other API tools can be used to create this key. Refer to their respective guides on how to do so.
:::

## Export and Wrap the BYOK Key Material

The Primus Tools `SalesforceByokExport` command uses the Salesforce BYOK-compatible certificate public key to wrap a temporary extracted AES key, derived from the tenant secret key and a derivation string. 

Example use of the `SalesforceByokExport` command:
```bash
java -jar primus-tools.jar SalesforceByokExport <HSM connection and credentials> -kekfile BYOK1.pem -hmackey SalesforceHMAC -derivation key8 -shaOnHsm -outfile BYOK
```
Where:
- `-kekfile`, specify the converted Salesforce BYOK-compatible certificate file, see [Convert Salesforce Certificate](#convert-salesforce-certificate).
- `-hmackey`, specify the name of the tenant secret key (HMAC key), see [Create Tenant Secret Key](#create-tenant-secret-key)
- `-derivation`, specifiy the derivation string, this can be a random string (recommended to note down, in case the same BYOK Key Material must be exported again)
- Use the `-shaOnHSM` parameter to calculate the SHA-256 on the HSM (requires a primusX.jar version 2.4.2 or later).
- `outfile`, specify the file name for the exported and wrapped BYOK key material.
- For Salesforce CA signed BYOK certificate, validate certificate chain with `-validate` parameter

:::note
For more information about the command and the procedure, see [Primus Tools - Bring Your Own Key](../../primus-tools/Tutorials/command-details/Bring-Your-Own-Key).
:::

After a successful `SalesforceByokExport` command, 2 exported files are created. Both files are encoded in base64. In our example these files are `BYOK.b64` the wrapped tenant secret and `BYOK_hash.b64` the hashed tenant secret. They will be needed to import the tenant secret into Salesforce.

:::tip[need help ?]
Contact our support team for further assistance.
+ [Create a ticket (account required)](https://support.securosys.com)
+ [Send an email](mailto:support@securosys.com)
:::