---
sidebar_position: 0
title: HSM Login with JCE/JCA API - Sample
sidebar_label: Login Sample
description: Login Sample with Java Cryptography Extension (JCE) and Java Cryptography Architecture (JCA) with Securosys Hardware Security Modules.
keywords: [hsm, cloud hsm]
---

# Login Sample

This Java code is a sample implementation for logging into the Securosys Primus Hardware Security Module (HSM) using the Java Cryptography Extension (JCE) provider. The code demonstrates the process of installing the Primus JCE provider, configuring it with HSM details, logging in with a setup password, fetching and blinding a permanent secret, writing the blinded secret to a file, and finally, logging in again using the file-based credentials.

- [LoginSample.java](../ressources/samples/LoginWriteSample.java) 

:::note 

This and the remaining samples assume direct connection to the HSM without reverse proxy. 
If you are using Securosys CloudHSM, other HSM as a service using reverse proxy, 
or your own reverse proxy, you will need to apply the lesson from CloudHSMSample in the remaining samples.

:::

## Breakdown
Here is a breakdown of the key functionalities:

1. **HSM Configuration:**
   - The user specifies the HSM host, TCP port, user/partition, and login credentials (setup password) for authentication.
   - Optional configurations include proxy user and password for CloudHSM service.

2. **Primus JCE Provider Installation:**
   - The Primus JCE provider is instantiated (`PrimusProvider`) and registered with the Java Security framework (`Security.addProvider(primusProvider)`).

3. **HSM Configuration and Login:**
   - HSM host, port, and user are configured using `PrimusConfiguration.setHsmHost`.
   - Optionally, CloudHSM proxy credentials are configured.
   - The login state is checked, and the setup password is used for the initial login.
   - Information about the device (HSM) is retrieved, such as the device name and version.

4. **Fetching and Blinding Permanent Secret:**
   - The permanent secret is fetched using `PrimusLogin.getUserSecretChars`.
   - The fetched secret is blinded using the AES blinding algorithm (`PrimusBlinding.blindChars`).

5. **Logging Out:**
   - The user logs out from the HSM using `PrimusLogin.logout`.

6. **Writing Blinded Permanent Secret to File:**
   - The blinded permanent secret is written to a file with owner-only permissions.
   - File permissions are set to read and write for the owner only.
   - The blinded secret is encoded to bytes and written to the file using `Files.write`.

7. **Logging In Again Using File-based Credentials:**
   - The user logs in again using the file-based credentials.
   - Device information is retrieved again after successful login.

8. **Logging Out Again:**
   - The user logs out again to ensure a clean exit.

9. **Sample Completion:**
   - The sample concludes, and a message is printed indicating the completion of the process.

The code is heavily commented, providing detailed explanations for each step. 
It showcases various operations related to HSM interaction, user authentication, and secure handling of credentials.
