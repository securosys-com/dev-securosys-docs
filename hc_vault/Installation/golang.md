---
sidebar_position: 1
title: Installing Vault Community with Golang
sidebar_label: with Golang
description: Installing HashiCorp Vault Community with Docker
keywords: [hsm, cloud hsm]
---

# Installation using Golang

**Prerequisites**: Install Golang 1.21.2 [(download)](https://go.dev/dl/).

On Windows : Add GOPATH and GOROOT manually to the system environment and restart your console.
- GOPATH default %USERPROFILE%\go
- GOROOT defaults to %programfiles%



### Environment Variables 

```export VAULT_CLIENT_TIMEOUT=2000```

This change is necessary, as the **HashiCorp Vault** default value is too low (60 seconds). A higher value is required for some operations (e.g. rekey) when waiting for an approved response.

### Additional prerequisites for UI

For the graphical User Interface, the following packages must be installed on the machine:

- [Node.js](https://nodejs.org/dist/v16.17.1/) (with NPM) - version v16.17.1
- [Yarn](https://classic.yarnpkg.com/en/) - Can be installed with the command ```npm install -g yarn```

### How to build Vault Community Edition 


#### Using pre-built releases 

You can find pre-built releases of the **Vault Community Edition** on the Securosys JFrog artifactory. Download the latest binary file, corresponding to your target OS, configuration files, or docker image.

Further documentation and credentials are available on:
- [Securosys Support Portal](https://support.securosys.com/external/)(account required)
- [Securosys website](https://www.securosys.com/en/hashicorp-vault).


#### Build from sources

There are multiple ways to build and run the Vault application.

To build Vault run either ```go build -o [executable_name]``` or use the command ```make bin```. This will build it using the **Make file** configuration. The Vault executable will be placed in the **bin** directory.

To build Vault with User Interface, run the following commands

	* ```make static-dist```

	* ```make bin```
The Vault executable will be placed in the bin directory.

To build a docker image, run the following command ```make docker VERSION={$VERSION}``` where ```{$VERSION}```will be a version of the Securosys Vault image.

To build Vault executables for different platforms (Windows, MacOS, Linux, FreeBSD, NetBSD and OpenBSD), ```make release VERSION={$VERSION}``` where ```{$VERSION}``` will be a version of build.

To build everything, docker image and all executables for all platforms, ```make release-all VERSION={$VERSION}``` where ```{$VERSION}``` will be a version of build.



### How to run Vault Community Edition

You can run Vault without building it ```go run ./main.go [vault parameters]```

#### Developer mode

To run the server in **dev mode** use either of the following commands ```go run ./main.go server -dev``` or ```./executable_name server -dev```


In dev mode, all data is stored **only in memory** and **keys will be initialized every time**.


#### Production mode

To run the server in **production mode** use either of the following commands ```go run ./main.go server -config=config.hcl``` or ```./executable_name server -config=config.hcl```


Create the directory **"data"** (if it does not already exist).

If the Vault server is not yet initialized, then use either of the following commands ```go run ./main.go operator init -address http://127.0.0.1:8200``` or ```./executable_name operator init -address http://127.0.0.1:8200```

These commands initialize the Vault server with default Vault encryption.
