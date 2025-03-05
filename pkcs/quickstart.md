---
sidebar_position: 1
title: Getting started with PKCS#11
sidebar_label: Quickstart
---

# Quickstart

1. Get started with the [installation][installation]. 

2. If the PKCS#11 API is already configured on your HSM, you can skip ahead to the [provider installation][provider-installation].

Installing the provider is only the first step. The Primus PKCS#11 Provider can be seamlessly integrated into [existing
applications][use-cases-app-integration] as well as provide an easy to use interface to manage cryptographic keys in your [own
application][use-cases-own-app].

:::tip
PKCS#11 Provider libraries, executables, and configuration files are by default assigned to the group "primus". 

Therefore, add all application accounts and users using the PKCS#11 Provider to the group primus (`sudo usermod -a -G primus user`).
Adding a user to a new group may require logout/login or reboot to update permissions (as temporary workaround you may use `newgrp primus` or `sudo login -f <user>`).
:::


[installation]: /pkcs/category/installation
[provider-installation]: /pkcs/Installation/pkcs11_provider_installation
[use-cases-app-integration]: /pkcs/Use-Cases/application_integration
[use-cases-own-app]: /pkcs/Use-Cases/own_application
