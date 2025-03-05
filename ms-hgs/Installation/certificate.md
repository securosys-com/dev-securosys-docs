---
sidebar_position: 3
title: Configuration
sidebar_label: 4. Certificates
description: Preparing certificates to install in Microsoft HGS with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---
# Preparing Certificates

The Microsoft HGS requires certificates for signing and encryption as well as HGS associated keys to operate. 

Keys are used for “attestation”, one of the two services that run as part of HGS, to affirm the health of the Guarded Hosts and the associated Hyper-V virtual machines.

Other keys called **Transport Keys** (TKs) are used for **Key Protection Service** (KPS), to unlock and run the **Shielded VMs** on positively attested Guarded Hosts.

1. Create and configure the `request.inf` file are as follows:
```
[Version]
Signature="$Windows NT$"
[NewRequest]
// highlight-next-line
Subject = "CN=HGSSigning.relecloud.com"
HashAlgorithm = SHA256
KeyAlgorithm = RSA
KeyLength = 2048
Exportable = FALSE
MachineKeySet = True
KeySpec = 0
ProviderName = "Securosys Primus HSM Key Storage Provider"
ProviderType = 0
KeyUsage = 0xA0
RequestType = PKCS10
[EnhancedKeyUsageExtension]
OID=1.3.6.1.5.5.7.3.1
OID=1.3.6.1.5.5.7.3.2
```

2. Open the command prompt and run the following commands to create the certificate request for signing and
encryption certificates.

- Create a CSR for signing:
```
certreq -new request.inf signreq.txt
```
3. Replace the `CN=HGSSigning.relecloud.com` to `CN=HGSEncryption.relecloud.com` within the `request.inf` file before generating the certificate request for Encryption certificate.

- Create a CSR for encryption:
```
certreq -new request.inf encrreq.txt
```

Send the created certificate requests to a public CA to sign or use a self-signed CA to sign the certificates. To sign the CSRs with a self-signed CA with Securosys Primus HSMs see our guide on [Microsoft PKI](/ms-pki-adcs/overview).

## Initializing HGS Server

Launch an elevated Windows PowerShell console on your environment's HGS Server (in our example **HOST_GS_AD**), and execute the following commands to configure the HGS server using the generated encryption and signing certificates.

1. Add the thumbprints of the certificates previously created to the HGS:
```
$Certs = Get-ChildItem Cert:\LocalMachine\My\ -dnsname HGS*
$Certs
$Signing = $Certs[0].Thumbprint
$Encryption = $Certs[1].Thumbprint
```

2. Initialize the server with the following command, replace `TestHGS` with your name of HGS Service.
```
Initialize-HgsServer -HgsServiceName TestHGS -SigningCertificateThumbprint $Signing -EncryptionCertificateThumbprint $Encryption -TrustActiveDirectory -Http -HttpPort 80
```
3. Run the Local Machine Certificate Management Console `certlm.msc` and locate the encryption and
signing certificates under the **Personal folder**, right click each of them in turn and verify, if required also add permission to the service account (e.g. HGSSVC_276CF$) to the list of Groups and Users permitted to manage the private keys. 

Allow **Read** is the only required permission.

To confirm KPS has access to the private keys of your encryption and signing certificates, run the
HGS diagnostics using `Get-HgsTrace` command. If any tests fail, be sure to remedy the identified problem(s)
before continuing to configure any additional nodes.

Run the following command in PowerShell:

```
Get-HgsTrace -RunDiagnostics
```

Follow the next steps to add the certificates to the GMSA users and initalize the KPS.

1. Open the command prompt and type `certlm.msc`, press Enter.
2. Expand `Certificates - Local Computer`, select `Personal` -> `Certificates`.
3. Right click on the signing certificate. Select `All Tasks` -> `Manage Private Keys...`
4. Select `Add` -> `Object Types...` and select `Service Accounts`. Complete by selecting `OK`.
5. Type **HGSSVC** in the object name and select Check Names. It will display the GMSA user name. Select `OK`.
6. Select GMSA user in **Group** or **User Names** and select Read check box after that select `OK`.
7. Repeat the same steps for the `Encryption certificate` to provide the Read permission to GMSA user.


Open an Internet Browser and access the metadata. If the metadata is accessible then you have
successfully initialized the Key Protection service using certificates backed by the HSM.