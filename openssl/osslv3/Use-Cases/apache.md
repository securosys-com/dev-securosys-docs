---
sidebar_position: 1
title: Configuring Apache HTTP Server for OpenSSL v3
sidebar_label: Apache HTTP Server
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL,PKCS#11,Apache]

---
<!-- KEEP H1 EMPTY TO OVERWRITE THE TITLE WITH NEXT H2 -->
# Apache HTTP Server

The following example shows how to set up Apache2 (httpd) HTTP server to work with the OpenSSLv3 pkcs11-provider.

## Preparation and Prerequisites

1. Make sure that Primus HSM PKCS#11 Provider is installed in your system following the <a href="/pkcs/Installation/pkcs11_provider_installation" target="_blank" rel="noopener noreferrer">PKCS#11 Provider instructions</a>.

    Confirm the successful installation by executing the following test command and checking the output:
    ```bash {9}
    ppin -t

    Load config file: '/etc/primus/primus.cfg'
    
    hsm0: Connect to 'grimsel.securosys.ch' on port 2410, firmware: RX-3.1.0-T
        slot0 (id=0), user=<your partition name>: OK
    
    Number of tested HSMs: 1 (number of partitions: 1)    
    Number of failures: 0    
    ```
   
2. Make sure that the Securosys OpenSSL pkcs11-provider is installed on your system, as it is described on the <a href="/openssl/osslv3/quickstart" target="_blank" rel="noopener noreferrer">Quickstart</a>.

3. Make sure that your OpenSSL version is aligned with the <a href="/openssl/osslv3/Installation/prerequisites" target="_blank" rel="noopener noreferrer">Prerequisites</a>.

4. Configure your OpenSSL according to <a href="/openssl/osslv3/Installation/configuration" target="_blank" rel="noopener noreferrer">Configuration</a>. 

   <details>
     <summary> Click to see a configuration example</summary>
   ```bash {19-35}
   HOME            = .
   
   # Use this in order to automatically load providers.
   openssl_conf = openssl_init
   
   # Comment out the next line to ignore configuration errors
   config_diagnostics = 1
   
   oid_section = new_oids
   
   [ new_oids ]
   
   [openssl_init]
   providers = provider_sect
   
   [random_sect]
   random = PKCS11-RAND
   
   [provider_sect]
   default = default_sect
   base = base_sect
   pkcs11 = pkcs11_section
   
   [base_sect]
   activate = 1
   
   [default_sect]
   activate = 1
   
   [pkcs11_section]
   module = /opt/openssl-3.3.2/lib/ossl-modules/pkcs11.so
   pkcs11-module-path = /usr/local/primus/lib/libprimusP11.so
   pkcs11-module-load-behavior = early
   pkcs11-module-quirks = no-deinit no-operation-state
   activate = 1
   
   ####################################################################
   [ ca ]
   default_ca    = CA_default        # The default ca section
   
   ####################################################################
   [ CA_default ]
   
   dir        = ./demoCA        # Where everything is kept
   certs      = $dir/certs      # Where the issued certs are kept
   crl_dir    = $dir/crl        # Where the issued crl are kept
   database   = $dir/index.txt  # database index file.
   #unique_subject = no          # Set to 'no' to allow creation of
                                # several certs with same subject.
   new_certs_dir = $dir/newcerts # default place for new certs.
   
   certificate  = $dir/cacert.pem   # The CA certificate
   serial       = $dir/serial       # The current serial number
   crlnumber    = $dir/crlnumber    # the current crl number
                                   # must be commented out to leave a V1 CRL
   crl          = $dir/crl.pem      # The current CRL
   private_key  = $dir/private/cakey.pem # The private key
   
   x509_extensions = usr_cert    # The extensions to add to the cert
   
   name_opt    = ca_default      # Subject Name options
   cert_opt    = ca_default      # Certificate field options
   
   default_days    = 365          # how long to certify for
   default_crl_days= 30           # how long before next CRL
   default_md      = default      # use public key default MD
   preserve        = no           # keep passed DN ordering
   
   policy      = policy_match
   
   # For the CA policy
   [ policy_match ]
   countryName             = match
   stateOrProvinceName     = match
   organizationName        = match
   organizationalUnitName  = optional
   commonName              = supplied
   emailAddress            = optional
   
   # For the 'anything' policy
   # At this point in time, you must list all acceptable 'object'
   # types.
   [ policy_anything ]
   countryName             = optional
   stateOrProvinceName     = optional
   localityName            = optional
   organizationName        = optional
   organizationalUnitName  = optional
   commonName              = supplied
   emailAddress            = optional
   
   ####################################################################
   [ req ]
   default_bits        = 2048
   default_keyfile     = privkey.pem
   distinguished_name  = req_distinguished_name
   attributes          = req_attributes
   x509_extensions     = v3_ca   # The extensions to add to the self-signed cert
   
   string_mask = utf8only
   
   # req_extensions = v3_req # The extensions to add to a certificate request
   
   [ req_distinguished_name ]
   countryName            = Country Name (2 letter code)
   countryName_default    = AU
   countryName_min        = 2
   countryName_max        = 2
   
   stateOrProvinceName    = State or Province Name (full name)
   stateOrProvinceName_default = Some-State
   
   localityName           = Locality Name (eg, city)
   
   0.organizationName     = Organization Name (eg, company)
   0.organizationName_default = Internet Widgits Pty Ltd
   
   organizationalUnitName     = Organizational Unit Name (eg, section)
   #organizationalUnitName_default =
   
   commonName           = Common Name (e.g. server FQDN or YOUR name)
   commonName_max       = 64
   
   emailAddress         = Email Address
   emailAddress_max     = 64
   
   # SET-ex3           = SET extension number 3
   
   [ req_attributes ]
   challengePassword        = A challenge password
   challengePassword_min    = 4
   challengePassword_max    = 20
   
   unstructuredName         = An optional company name
   
   [ usr_cert ]
   
   basicConstraints=CA:FALSE
   
   # PKIX recommendations harmless if included in all certificates.
   subjectKeyIdentifier=hash
   authorityKeyIdentifier=keyid,issuer
   
   [ v3_req ]
   
   basicConstraints = CA:FALSE
   keyUsage = nonRepudiation, digitalSignature, keyEncipherment
   
   [ v3_ca ]
   
   # Extensions for a typical CA
   
   # PKIX recommendation.
   subjectKeyIdentifier=hash
   authorityKeyIdentifier=keyid:always,issuer
   basicConstraints = critical,CA:true
   
     ```
   </details>
   :::note
      If you haven't installed natively openssl, you might need to configure the below environment variables:
   ```bash
   export LD_LIBRARY_PATH=path to the OpenSSL libraries:$LD_LIBRARY_PATH
   export OPENSSL_DIR=path to the installation directory for OpenSSL
   ```
   :::

## Install Apache2 (httpd)

Update the package list and install Apache2 if not previously installed:
:::tip 
On RHEL/CentOS the webserver is named httpd instead of apache2. 
The below command sequences are shown for Ubuntu only.
:::


**Ubuntu/Debian**
```bash
sudo apt update
sudo apt install apache2
```


Verify that you have at least Version **2.4.62 (or newer)** on your system:
```bash
apache2 -v
```
```text {1}
Server version: Apache/2.4.62 (Ubuntu)
Server built:   2024-08-15T07:32:14
```

### Preparing the key and certificate to be used

For this example a simple self-signed certificate is created. For real applications you probably want to get your certificate <a href="/openssl/osslv3/Use-Cases/self_signed_certificate" target="_blank" rel="noopener noreferrer">signed by a CA</a> and put the certificate chain into the cert.pem-file.

Again some environment variables are set-up as placeholders.

```bash
export P11_TOKEN=<YOUR_USER_NAME>	# partition name
export P11_PIN=<YOUR_PKCS#11_PIN>	# hsm pkcs11 pin (don't use for production)
export P11_KEY_NAME=TESTING_APACHE2_KEY	# name of Key
```

A RSA-4096 private key is generated

```bash
openssl genpkey -propquery "provider=pkcs11" \
    -algorithm "rsa" -pkeyopt "rsa_keygen_bits:4096" \
    -pkeyopt "pkcs11_uri:pkcs11:token=${P11_TOKEN};object=${P11_KEY_NAME}?pin-value=${P11_PIN}"
```
Given the encoder of the OpenSSL pkcs11-provider is enabled, the key will be outputted as a "PKCS#11 PROVIDER URI" pem file.

Use the key to create a self signed certificate

```bash
sudo openssl req -new -x509 -copy_extensions=copyall \
    -key "pkcs11:type=private;token=${P11_TOKEN};object=${P11_KEY_NAME}?pin-value=${P11_PIN}" \
    -subj "/C=CH/ST=Bern/L=Bern/O=My Example Organisation/OU=IT Department/CN=www.example.com" \
    -addext "subjectAltName = DNS:www.example.com, DNS:*.www.example.com" \
    -sha256 -days 99 -out /etc/apache2/ssl/p11-provider-cert.pem
```
<!-- For long-term usage, the PKCS PIN can be stored on the machine running Nginx. For security reasons, the "pin-value" should be obfuscated when used by the Nginx process.

Create a new file `/run/secrets/securosys_p11_pin` to be called instead, via `pin-source=file:/run/secrets/securosys_p11_pin`. The OpenSSL decoder will then try to read the pkcs11-pin from `/run/secrets/securosys_p11_pin`. 

:::warning
Each time the VM is rebooted, the file `/run/secrets/securosys_p11_pin` containing the P11 PIN, will be deleted. As a workaround, you can create a persistent file at `/etc/primus/securosys_p11_pin` containing the P11 PIN.

Then, modify the `/usr/lib/systemd/system/nginx.service` file by adding the line `ExecStartPre=cp -p /etc/primus/securosys_p11_pin /run/secrets` under the "[Service]" section, when configuring nginx further in this page.
:::
-->

### Adapt the site configuration to use the key from the HSM

:::tip Take care
Ensure that your web-site is running well using a local key/certificate from file before changing to private key loaded from the HSM.
:::

    ```bash
    sudo vi /etc/apache2/sites-available/<your-site.conf>
      ...
      # Certificate from local file
      SSLCertificateFile      /etc/apache2/ssl/p11-provider-cert.pem
      # Private key from Primus HSM or CloudsHSM
      SSLCertificateKeyFile   "pkcs11:token=<your-partition>;pin-value=<pkcs11-pin>;object=<key-label>;type=private"
      ...
  ```
### Test the configuration

Perform a configuration test and verify that no errors are reported.
```bash
apachectl configtest
```

### Start the apache service

```bash
sudo systemctl daemon-reload
sudo systemctl restart apache2
```

## Webserver optimization for HSM access

:::warning
If the network connection to the HSM has high latency (e.g. CloudsHSM), it is important to parallelize HSM requests.

Based on our experience, mpm_prefork achieves better results than mpm_event module. 

Note that Apache2 performance tuning requires profound knowledge of the apache webserver, mechanisms and configuration. 
:::

   <details>
     <summary> Click to expand</summary>

     ***Performance optimization for mpm_event:***
     ```bash
     vi /etc/apache2/mods-enabled/mpm_event.conf

     StartServers            3
     MinSpareThreads         75
     MaxSpareThreads         250
     ThreadLimit             64
     ThreadsPerChild         25
     MaxRequestWorkers       400
     MaxConnectionsPerChild  0
     ```

     ***Performance optimization for mpm_prefork:***
     ```bash
     vi /etc/apache2/mods-enabled/mpm_prefork.conf

     StartServers            5
     MinSpareServers         5
     MaxSpareServers         10
     MaxRequestWorkers       150
     MaxConnectionsPerChild  0
     ```

     To change from mpm_event to mpm_prefork:
     ```bash
     sudo a2dismod mpm_event
     sudo a2dismod mpm_worker
     sudo a2enmod mpm_prefork
     sudo systemctl restart apache2
     apache2ctl -V | grep MPM
     ```


   </details>

<!-- ## Troubleshooting -->

## More resources

- [Generate a key with OpenSSL](/openssl/osslv3/Tutorial/openssl_cli)
- [Troubleshooting](../Tutorial/troubleshooting)
- [OpenSSL documentation](https://docs.openssl.org/master/)
- [Apache documentation](https://httpd.apache.org/docs/)