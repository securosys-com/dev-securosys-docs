---
sidebar_position: 1
title: PKCS#11 - Application integration
sidebar_label: Application Integration
---

# Application Integration

## OpenSSL

We recommend using the latest OpenSSL of your Linux distribution. 
For details see <a href="/openssl/overview" target="_blank" rel="noopener noreferrer">OpenSSL Integration</a>. 


## Apache with OpenSSL

We recommend using the latest Apache/Nginx of your Linux distribution based on OpenSSL v3.x and the new pkcs11-provider API. 
For details see <a href="/openssl/osslv3/Use-Cases/apache" target="_blank" rel="noopener noreferrer">Apache HTTP Server Integration</a>.


## Oracle TDE

Oracle Databases use authentication, authorization, and auditing
mechanisms to secure data in the database, but not in the operating
system files where the data is stored. To protect those files, Oracle
Databases provide transparent data encryption (TDE). This feature
enables you to protect sensitive data in database columns or files
stored in operating system files by encrypting it. Then, to prevent
unauthorized decryption, it stores encryption keys in a security
module external to the database. For details consult the application
note [*PrimusAPI_P11-Oracle12_AN-Enn.pdf*][oracle-tde],
downloadable from the Securosys Support Portal.

## Further Applications

We are continuously testing further integrations with other
applications, e.g.

-   Keyfactor / PrimeKey EJBCA Enterprise 8.x and newer, SignServer, and software appliances
-   EJBCA Community Edition 6.x, 7.x
-   Entrust Certificate Authority (Security Manager) 8.3 and newer
-   Venafi Trust Protection Platform
-   WhiteRabbit OpenXPKI Enterprise
-   Versasec
-   CipherMail
-   OwnCloud
-   Nevis Security Solutions
-   PDF Tools Solutions
-   Fornetix VaultCore Encryption Management Solutions
-   HashiCorp Vault Enterprise
-   Securden Unified PAM
-   Fortinet FortiGate firewalls
-   And many more

Please check the <a href="/integrations" target="_blank" rel="noopener noreferrer">Solutions Explorer</a> or the Securosys Support Portal for specific integration notes, 
or contact Securosys sales for more information.


[openssl-pkcs11-provider]: /openssl/overview
[p11-kit-tool]: https://dlarea.securosys.com/document/PrimusHSM_P11-Kit-Tool_AN-E02.pdf
[ossl-p11-kit-apache]: https://dlarea.securosys.com/document/PrimusAPI_P11-OpenSslApacheProv_AN-E03.pdf
[oracle-tde]: https://dlarea.securosys.com/document/PrimusHSM_Oracle12-Integration_AN-E02.pdf
