---
sidebar_position: 2
title: What is Microsoft SQL Always Encrypted?
sidebar_label: MS Always Encrypted
description: What is Microsoft SQL Always Encrypted? with Securosys Hardware Security Modules (HSMs)
keywords: [cloud hsm, hsm key management, hsm cloud]
---

# What is “Always Encrypted”?

Data protected by Always Encrypted remains encrypted until it has reached the on-premises client application. This allows clients to encrypt sensitive data inside the client application and never reveal the encryption keys to the database engine. Therefore, it effectively mitigates man-in-the-middle attacks and protects against unauthorized activity of rogue database administrators. AE provides a separation between those who own the data and those who manage the data.

Unlike Transparent Data Encryption (TDE), which encrypts the data and log files on disk but allows the data to be read by any application that queries the database, Always Encrypted requires your client application to use an AE-enabled driver to communicate with the database (currently Microsoft .NET Framework 4.6.1, JDBC 6, ODBC 13.1 or later).

Always Encrypted can be configured for individual database columns containing sensitive data, by defining the encryption algorithm and cryptographic keys used to protect the data in the column. There are two types of AE keys:

- A **Column Master Key (CMK)**, an asymmetric RSA encryption key (2048 bits), used to encrypt the Column Encryption Keys (CEK). The CMK can be protected by Hardware Security Modules and Clouds HSM (HSM as a service), using the CNG/KSP Provider.

- One or more **Column Encryption Keys (CEK)**, a symmetric AES key (256 bits), used to encrypt database columns.

The database engine stores encryption configuration for each column in the database metadata. It also stores encrypted values of CEKs and the information about the location of CMKs, which are stored in external trusted key stores, such as hardware security modules, Windows Certificate Store on a client machine or Azure Key Vault.

Always Encrypted supports two types of encryption:

- **Deterministic** encryption: always generates the same encrypted value for any given plain text value. Using deterministic encryption allows point lookups, equality joins, grouping and indexing on encrypted columns. However, but may also allow unauthorized users to guess information about encrypted values by examining patterns in the encrypted column, especially if there is a small set of possible encrypted values, such as True/False, Yes/No etc.

- **Randomized** encryption: is more secure as it produces different cipher text values from the same plaintext every time the data is encrypted. However, prevents searching, grouping, indexing, and joining on encrypted columns.

The initial setup of Always Encrypted in a database involves generating AE keys, creating key metadata, configuring encryption properties of selected database columns, and/or encrypting data that may already exist in columns that need to be encrypted. 
Please note: the database engine cannot be involved in key provisioning (AE keys) or data encryption/decryption operations. Some of the initial tasks are not supported in Transact-SQL and require the use of client-side tools (e.g. SQL Server Management Studio or PowerShell):

| Task | SSMS | PowerShell | T-SQL |
| :-------- | ------- | ------- | ------- |
| Provisioning column master keys, column encryption keys and encrypted column encryption keys with their corresponding column master keys. | Yes | Yes | No | 
| Creating key metadata in the database. | Yes | Yes | Yes | 
| Creating new tables with encrypted columns. | Yes | Yes | Yes | 
| Encrypting existing data in selected database columns | Yes | Yes | No |
