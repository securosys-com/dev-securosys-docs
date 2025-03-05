---
sidebar_position: 0
title: Using Certreq with an HSM
sidebar_label: CSR request with Certreq
description: Securing Microsoft Certutil with Securosys Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

`certreq.exe` is a Windows command-line tool for generating, submitting, and managing certificate requests, primarily in Active Directory Certificate Services (ADCS) or external CAs. It allows precise control over CSR attributes via an INF file, making it ideal for automation.

When used with the Securosys HSM, `certreq` ensures private keys are generated and stored securely within the hardware, leveraging the Securosys Primus HSM Key Storage Provider (KSP).

## Certificate Signing Request 

The `certreq` command-line tool can be used to generate a Certificate Signing Request (CSR) on Windows. 

1. Create a file named `request.inf` with the contents of the CSR. 

   Example:
   ```
   [Version]
   Signature="$Windows NT$"

   [NewRequest]
   Subject="CN=yourdomain.com, O=Your Organization, L=YourCity, S=YourState, C=YourCountry"
   KeySpec=1
   KeyLength=2048
   Exportable=TRUE
   MachineKeySet=TRUE
   SMIME=FALSE
   PrivateKeyArchive=FALSE
   UserProtected=FALSE
   UseExistingKeySet=FALSE
   ProviderName="Securosys Primus HSM Key Storage Provider"
   ProviderType=12
   RequestType=PKCS10
   KeyUsage=0xA0

   [Extensions]
   2.5.29.17 = "{text}"
   _continue_ = "dns=yourdomain.com&"
   _continue_ = "dns=www.yourdomain.com"
   ```
- The `ProviderName="Securosys Primus HSM Key Storage Provider"` must not be changed.
- Replace `yourdomain.com` and other fields with your values.
- The if desired `KeyLength=2048` can be changed to `4096` for stronger encryption.
   
2. After saving `request.inf`, Run **Command Prompt** as Administrator and execute:
   ```cmd
   certreq -new -f request.inf request.csr
   ```
   This will generate the CSR file (`request.csr`).

3. The CSR file `request.csr` is now ready to be submitted to a CA for certificate issuance. Optionally submit the CSR with the following command:
```
certreq -submit -config "CA_SERVER\CA_NAME" request.csr
```

4. Optionally verify the content of the generated CSR with `certutil`:
   ```cmd
   certutil -dump request.csr
   ```