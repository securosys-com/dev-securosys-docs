---
sidebar_position: 0
title: Getting started AWS KMS & Securosys HSM
sidebar_label: Quick Start 🚀
description: Follow the step-by-step guide for setting up AWS KMS with Securosys HSM. Learn how to generate a TLS certificate, configure your environment, and deploy Securosys XKS Proxy to enable seamless integration for encryption and decryption.
tags: [AWS, AWS KMS, XKS, EC2]
---

# Quickstart - XKS Proxy for AWS KMS
### Securosys HSM - Integration Guide

1. Complete all the [prerequisites](/xks/Installation/Prerequisites.md)

2. **Generate** your TLS certificate

A prerequisite for this example is generating a certificate for your domain. For the next steps, you will need your ```*.ca``` and ```*.crt``` files, as well as a private key (or you can use a self-signed certificate).
    - Combine Certificate Files**: To generate a **.jks** from these files, you need to combine your ```*.crt``` and ```*.ca``` files. Manually copy all the data from the ```*.ca``` file into the ```*.crt``` file.
    - **Use OpenSSL Command**: After combining the files, you can run the OpenSSL command below to create a ```.p12``` file (PKCS#12 format) from the combined certificate and private key.
    - **Provide a Password**: When you run the command, you’ll be prompted to enter a password for the newly created ```.p12``` file, which will protect it.

```
openssl pkcs12 -export -in ca.crt -inkey ca.key -out keystore.p12
```

3. **Download** the Securosys XKS Proxy files
    
    Follow the instructions provided in the [Download](/xks/downloads.md) section (login required).

4. **Configure** ```application.yaml``` (example below.) Please adapt the parameters according to your environment:

:::info API Endpoint URI
- CloudHSM: refer to [CloudHSM Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) for accurate API-Endpoint URI.
- On-Premises HSM: Verify the API-Endpoint URI with your administrator.

For any setup (cloud or on-premises), contact your service administrator for authentication credentials.
:::

Default ```application.yaml``` file:

```yaml
# All necessary credentials from hsm
hsm:
  attestationKeyName: 'attestation-key'
  # Make sure you allowed an outbound firewall rule, to allow traffic to the HSM
  # Hosts should be entered sequentially using the list as in the example below
  host:
    - 'your-proxy_host'
    - 'another-proxy_host'
  #Ports should be entered sequentially using the list just like hosts
  port:
    - 'your-proxy_port'
    - 'another-proxy_port'
  user: 'replace-me_hsm-username'
  setupPassword: 'replace-me_hsm-setupPassword'
  proxyUser: 'replace-me_proxy-username'
  proxyPassword: 'replace-me_proxy-password'

# All necessary credentials from AWS
aws:
  host: 'replace-me_domain-name'
  regionName: 'replace-me_aws-region-name'
  accessKeyID: 'replace-me_keyId'
  secretAccessKey: 'replace-me_secretAccessKey'
  #These parameters should not be modified
  serviceName: 'kms-xks-proxy'
  httpMethodName: 'POST'
  debug: 'false'

# Your dns credentials
server:
  port: 443
  address: 0.0.0.0
  ssl:
    key-store: file:/etc/app/config/keystore.p12
    key-store-password: replace-me_keystore-password
    key-alias: replace-me_keystore-keyname

# Logging configuration
logging:
  config: /etc/app/config/log/logback.xml

#Do not modify!
spring:
  mvc:
    throw-exception-if-no-handler-found: true
```

5. **Run** the downloaded Securosys XKS Proxy image with the following command. **Replace** the variables according to your configuration:

```js
docker run [-d]-name <NameOfContainer> --add-host <YourHostDomain> :127.0.0.1 \
 --network=host -v /home/ec2-user/securosys\_xks\_1.0.0/config-files:/etc/app/config/ \
 securosys.jfrog.io/external-xks/securosys-xks:1.0.0.20230706T1936Z
```

If the command is successful, the Securosys XKS proxy will be started. If not already present, a new attestation key will be generated. The logs from the boot will output a healthy status.

Once the Securosys XKS Proxy is running, the connection between AWS Key Management Service (KMS) and your HSM is established. You can now use your custom key store to encrypt and decrypt data on the AWS platform with keys generated and stored on your HSM. Test the functionality by encrypting and decrypting your data.

### More content

- [Tutorial - How to encrypt/decrypt data in AWS KMS?](../Tutorials/Examples/Example-AWS-KMS.md)
- [Example - Creation of an XKS in AWS KMS](../Tutorials/Examples/Example-AWS-KMS.md)