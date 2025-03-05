---
sidebar_position: 1
title: Getting started with Docker Compose
sidebar_label: Docker Quickstart
description: Quickly deploy Transaction Security Broker using Docker Compose, configured for development purposes with default settings. Learn how to download configuration files, configure HSM connection parameters, and start the application.
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, Securosys Docker Quickstart Guide, Docker Compose, Docker installation, Docker Compose installation, Securosys TSB, HSM connection configuration, development setup]
---


# Docker - Quickstart

Docker Compose simplifies the configuration and deployment of multi-container Docker applications. By defining services in the docker-compose.yml file and customizing the parameters to suit your needs, you can easily create and start Transaction Security Broker with a single command. We assume docker and docker-compose to be installed on your machine already, if not please install it using the official userguide. 

 - [Install Docker Engine](https://docs.docker.com/engine/install/)
 - [Overview of installing Docker Compose](https://docs.docker.com/compose/install/)

:::danger Take care

This Quickstart Guide start TSB with default Database-Credentials and without TLS configured and to be used for `development purpose` only. <br />
For productive setup please read the complete guide on setting-up TSB: [On-Premise Installation Guide](/tsb/Installation/On-Premise-Installation.md)
:::


## Download configuration files

Head to the **[Downloads](/tsb/Download/downloads.md)** page to get instructions on how to get the **software** and **credentials**.

## Configure the HSM-Connection Parameters

:::tip Tip

  If you are working on Windows we can simply adjust the 'application-local.yml' with the HSM connection properties.<br />
  You can leave the docker-compose.yml and other file as it is.

  If you are running TSB on Linux, change the securosys_sql image in the `docker-compose.yml` file, from **`image: mysql:8.0`** to **`image: docker.io/mariadb:10.4`**.
:::



```sh
:~$ nano securosys_TSB_1.15.1.1/config-files/application-local.yml
```

Adjust the following properties 
- `hsm.host`
- `hsm.port`
- `hsm.user`
- `hsm.setupPassword`

If you are connecting with Securosys CloudHSM or your own PrimusHSM-Cluster, please replace connection details (hostname & port) from [HSMaaS - Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) or [TSBaaS - Connectivity Details](/connectivity-details/cloudhsm-connectivity-details)

```yml
hsm:
  host: 'nufenen.securosys.ch,grimsel.securosys.ch' # REPLACE with the hsm URL or IP
  port: '2400' # REPLACE with HSM JCE-Port
  user: TEST # REPLACE with your HSM username (PartitionName)
  setupPassword: gwe5p-Y5Lt2-nm4dJ-4SQux-KvLSk # REPLACE with your HSM SetupPassword
  encryptionPassword: G5VbL-R84Qy-XQmyR-8RZ5Z-tDtr4 # REPLACE it with some random value (high entropy). This password is used to encrypt the hsm user secret, stored in the SQL-Ddatabase
```

:::note Config

(`grimsel.securosys.ch, nufenen.securosys.ch` is an HSM-Cluster for Development Purpose only, and require a DEV-Account (e.g. an account starting with PRIMUSDEVXXX issued by Securosys)

:::

### Start the application

```sh
docker login securosys.jfrog.io -u robot.reader.tsb
```

```sh
Password: #<ENTER PASSWORD FROM DOWNLOAD-LINK FILE> # if not know, check the Download page.
```

```
docker-compose up -d
```

### Open Swagger
Open the (Swagger) to interact with the API in your browser: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## What's next?

- Follow the instructions to [ Create a Key and use it to encrypt and decrypt a payload](/tsb/Tutorials/Encryption/EncryptDecrypt.md)
- Full installation Guide [here](/tsb/Installation/On-Premise-Installation.md)