---
sidebar_position: 0
title: Installing Vault Community with Docker
sidebar_label: with Docker
description: Installing HashiCorp Vault Community with Docker
keywords: [hsm, cloud hsm]
---

# Installation using Docker

**Docker** simplifies the configuration and deployment of container applications.

By defining services in the ```docker-compose.yml``` file and customizing the parameters to suit your needs, you can easily create and start **Hashicorp Vault** with a single command.

:::tip prerequisites
Ensure [docker](../Quickstart/docker#prerequisites) and [docker-compose](../Quickstart/docker#prerequisites) are installed on your machine. 
:::

## Configuration

Prepare the additional configurations files for docker image:

:::note note

The ```{$version}``` has to be replaced with the [current version](../Downloads/release_notes.md) of the docker image.

:::

**File** ```docker-compose.yml```:
```yml {14}
version: "3.3"
services:
  run:
    container_name: securosys_hashicorp_vault
    environment:
      - "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
    volumes:
      - securosys_hashicorp_vault_config:/etc/app/config
      - securosys_hashicorp_vault_db:/etc/app/db
      - securosys_hashicorp_vault_plugins:/etc/app/plugins
    ports:
      - "0.0.0.0:8200:8200"
      - "0.0.0.0:8201:8201"
    image: securosys.jfrog.io/hcvault-ce-rest-integration/hcvault-ce-rest-integration:1.2.5.20231207103736
volumes:
  securosys_hashicorp_vault_config:
    driver: local
    driver_opts:
      o: bind
      type: none
      # Local absolute path to directory which contains all config files
      device: ./config/vault
  securosys_hashicorp_vault_db:
    driver: local
    driver_opts:
      o: bind
      type: none
      # Local absolute path to directory where we want to store database
      device: ./config/db
  securosys_hashicorp_vault_plugins:
    driver: local
    driver_opts:
      o: bind
      type: none
      # Local absolute path to directory where are stored custom plugins
      device: ./config/plugins

```


**File** ```config.hcl```:

The configuration file differs slightly from the standalone version.

```sh
//Example of config.hcl for Docker image.
//Addresses or paths are relative to path and addresses inside docker image

storage "raft" {
  path = "/etc/app/db" //Do not change this path
  node_id = "raft_node"
}

listener "tcp" {
  address     = "0.0.0.0:8200" //Do not change this path
  tls_disable = 1
}

disable_mlock=true
plugin_directory="/etc/app/plugins" //Do not change this path
api_addr = "http://0.0.0.0:8200" //Do not change this addr
cluster_addr = "https://127.0.0.1:8201" //Do not change this addr
ui = true


```

_Add below the config section seal "securosys-hsm" as shown in the [auto-unseal chapter](../Concepts/autounseal.md)_

## Run Container

Once your **Hashicorp Vault** instance is fully configured, you can launch the application with the command: `docker-compose up`, execute in the directory where the _docker-compose.yml_ file is located.

Use the command `securosys_hashicorp_vault` to initiate the startup process of the service.

:::tip Transaction Security Broker (TSB) - Dispatched

  - `docker-compose up -d` starts the contianers in dispatched mode<br />
  - Credentials: 
    - User: `robot.reader.hashicorpvault`
    - Password: `FTTGEcruzB_QUf3LBsq+KVV3wYuSx_`
:::

```sh
    :~/$ docker login securosys.jfrog.io -u robot.reader.hashicorpvault
    Password:
    Login Succeeded
```