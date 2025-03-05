---
sidebar_position: 0
title: Transport Layer Security (TLS) & JCE/JCA API
sidebar_label: Transport Layer Security (TLS)
description: Transport Layer Security (TLS) with Java Cryptography Extension (JCE) and Java Cryptography Architecture (JCA) with Securosys Hardware Security Modules.
keywords: [hsm, cloud hsm]
---


# Transport Layer Security (TLS)

The provided code is a Java program that demonstrates the use of the Securosys Primus HSM (Hardware Security Module) and its JCE (Java Cryptography Extension) provider for establishing secure TLS (Transport Layer Security) connections between a client and a server running as separate threads in the same Java Virtual Machine (JVM).


- [TlsSample.java](../ressources/samples/TlsSample.java)

The use case for this code is to demonstrate the integration of the Securosys Primus HSM with Java applications for securing communication using TLS. It showcases the generation of keys and certificates on the HSM, TLS setup for both server and client, and secure communication between them. This can be used as a reference for developers aiming to leverage hardware-based security for TLS connections in Java applications.

:::note note

The code interacts with an HSM through the Securosys Primus JCE provider, indicating that cryptographic operations are performed on the HSM.

:::

## Breakdown
Here is a summary of the code:

1. **Dependencies and Imports:** The code includes necessary Java libraries and imports classes related to networking, security, and the Securosys Primus JCE provider.

2. **HSM Configuration:** The program initializes HSM (Hardware Security Module) credentials such as host, port, user, and password. These credentials are used to establish a connection to the HSM.

3. **Key and Certificate Parameters:** The program sets up parameters for different cryptographic algorithms, including RSA, EC (Elliptic Curve), and DSA (Digital Signature Algorithm). It also defines base names and key aliases for the keys and certificates generated.

4. **Primus JCE Provider Initialization:** The Primus JCE provider is initialized, which includes configuring the HSM host and port. It is added as a security provider, allowing it to handle cryptographic operations.

5. **Key and Certificate Generation:** The program generates key pairs and certificates for a CA (Certificate Authority), server, and client. The CA's certificate is self-signed, and the server and client certificates are signed by the CA.

6. **Server Setup:** The server part is run in a separate thread. It configures SSL parameters, initializes trust and key managers with the appropriate keystores, and listens for incoming TLS connections.

7. **Client Setup:** The client part is run in the main thread. Similar to the server, it configures SSL parameters, initializes trust and key managers, and connects to the server using TLS.

8. **TLS Communication:** The server and client exchange a simple integer message. The server reads an integer from the client, calculates its square, and sends it back. The client verifies that the received value is the square of the originally sent integer.

9. **Additional Configuration:** Various system properties are set to control the behavior of TLS, logging, and debugging.