---
sidebar_position: 1
title: Configuring HTTPS with REST-API & JKS
sidebar_label: Configure HTTPS
description: Guide to configuring HTTPS (TLS) for Securosys Rest-API using Java Key Store (JKS).
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, TLS configuration, JKS file, OpenSSL, Securosys Rest-API, HTTPS setup, certificate generation, application-local.yml configuration]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Configure HTTPS

The example below shows how to create a JKS (Java Key Store) file to enable TLS (https) for Securosys Rest-API.

:::tip TIP

There are multiple ways to generate a .jks file. In this example we are using openssl to create a certificate for **CN=localhost**.
e.g. https://localhost:8080/v1/licenseInfo

It is required to have these utilities preinstalled on the device where the .jks file will be created.

:::

### Creating a Key and Certificate

Use the following command to generate a key (ca.key) and certificate (ca.crt) for TLS configuration with openssl:

```sh
openssl req -new -x509 -nodes -sha256 -newkey rsa:4096 -days 3650 -subj '/CN=localhost' -keyout ca.key -out ca.crt
```

### Import Key and Certificate into Key Store

For the next steps you will require your `*.key`, `*.crt` files (or simply using a self-signed certificate, as show in the command above).

```sh
openssl pkcs12 -export -in ca.crt -inkey ca.key -out securosys-ska-server.p12
```

### Configure HTTPS on TSB

1) Copy the previously generated `securosys-ska-server.p12` file to the securosys-tsb `config-files/tls` folder. <br />
   (Create a new directory **`tls`** if it does not exist)

2) Adapt the following properties in `application-local.yml` file:
```yml
## HTTPS CONFIGURATION
tls:
  # set to true if you want to use TLS (https)
  enabled: true
  keyStore: 'file:/etc/app/config/tls/securosys-ska-server.p12'
  keyStorePassword: secret  
```

:::tip Public Domain

The example above provides a guide to create a Certificate on localhost. If you want to use a public domain, a prerequisite step for this example is to generate a certificate for your domain. Then you can go to step [Import Key and Certificate into Key Store](#import-key-and-certificate-into-key-store) to import the files into KeyStore.

:::

### Example startup

You should see in the TSB logs, that the server started with http(s). <br />
A sample below: 

> `Tomcat initialized with port(s): 8080 (https)`

> `Connector [https-jsse-nio-8080], TLS virtual host [_default_], certificate type [UNDEFINED] configured from keystore [/root/.keystore] using alias [tomcat] with trust store [null]`

> `Tomcat started on port(s): 8080 (https) with context path ''`


```text
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.6)

2024.01.03 10:13:28.700 INFO [  restartedMain] [ com.securosys.ska.Application]Starting Application using Java 17.0.2 with PID 1 (/opt/app started by root in /)
2024.01.03 10:13:28.706 INFO [  restartedMain] [ com.securosys.ska.Application]No active profile set, falling back to 1 default profile: "default"
2024.01.03 10:13:28.905 INFO [  restartedMain] [sPropertyDefaultsPostProcessor]For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2024.01.03 10:13:31.887 INFO [  restartedMain] [epositoryConfigurationDelegate]Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2024.01.03 10:13:32.096 INFO [  restartedMain] [epositoryConfigurationDelegate]Finished Spring Data repository scanning in 196 ms. Found 10 JPA repository interfaces.
2024.01.03 10:13:36.722 INFO [  restartedMain] [mbedded.tomcat.TomcatWebServer]Tomcat initialized with port(s): 8080 (https)
2024.01.03 10:13:36.805 INFO [  restartedMain] [oyote.http11.Http11NioProtocol]Initializing ProtocolHandler ["https-jsse-nio-8080"]
2024.01.03 10:13:36.819 INFO [  restartedMain] [.catalina.core.StandardService]Starting service [Tomcat]
...
2024.01.03 10:13:53.391 INFO [  restartedMain] [oyote.http11.Http11NioProtocol]Starting ProtocolHandler ["https-jsse-nio-8080"]
2024.01.03 10:13:54.082 INFO [  restartedMain] [il.net.NioEndpoint.certificate]Connector [https-jsse-nio-8080], TLS virtual host [_default_], certificate type [UNDEFINED] configured from keystore [/root/.keystore] using alias [tomcat] with trust store [null]
2024.01.03 10:13:54.099 INFO [  restartedMain] [mbedded.tomcat.TomcatWebServer]Tomcat started on port(s): 8080 (https) with context path ''
2024.01.03 10:13:54.179 INFO [  restartedMain] [ com.securosys.ska.Application]Started Application in 27.365 seconds (process running for 29.127)
2024.01.03 10:13:54.183 INFO [  restartedMain] [.business.BootstrappingProcess]Executing application bootstrapping
...
```
