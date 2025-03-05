---
sidebar_position: 0
title: Getting started with Vault Community & HSM
sidebar_label: Quickstart
description: Getting started with HashiCorp Vault Community and Hardware Security Modules (HSMs)
keywords: [hsm, cloud hsm]
---

# Getting Started with HC Vault Community
### HSM Integration guide

## Prerequisites

Before getting started, please verify that **Docker** and **Docker Compose** are installed on your machine. If they aren’t, you can install them by following the official guides:
- [Install Docker Engine](https://docs.docker.com/engine/install/)
- [Install Docker Compose](https://docs.docker.com/compose/install/)

## Configuration Steps

Before running the Docker container, follow these steps to prepare the necessary configuration files:

1. Create a file named `docker-compose.yml` ( [View sample file](/hc_vault/Installation/docker#configuration) ).

2. Replace `{$version}` with the [current version](../Downloads/release_notes.md) of the Docker image.

3. Create a file named `config.hcl`. ( [View sample file](/hc_vault/Installation/docker#configuration) ).

4. After configuring your HashiCorp Vault instance, launch the application with the single command:
```bash
docker-compose up
```

:::tip Logging into Docker Registry
- User: `robot.reader.hashicorpvault`,
- Password: `FTTGEcruzB_QUf3LBsq+KVV3wYuSx_`.

Login to the Docker registry using the command:
```docker login securosys.jfrog.io -u robot.reader.hashicorpvault```
:::

:::tip Tips for Dispatched Mode
To start the containers in dispatched mode, use the command:
```docker-compose up -d```
:::