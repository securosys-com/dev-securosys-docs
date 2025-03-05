---
sidebar_position: 1
title: Architecture
sidebar_label: Architecture
---

# Transaction Security Broker

![](../../../img/TSB/TSB-Diagram.png)


Transaction Security Broker integrates the REST API with the Securosys Smart Key Attributes (SKA) workflow engine. It makes integration of SKAs simple by providing the language-agnostic interface and maintaining the state of the requests and approvals lifecycle.

Securosys Smart Key Attributes allow for the level of control over the private key operations required by some of the most security critical systems dealing with crypto- asset custody, large payment and trade processing, or personal data and digital identity protection. To achieve this, SKAs wrap private keys with policies defining the rules based on which the key operations can be performed, blocked, or unblocked, or which define how these policies can be changed. These attributes are stored and enforced by the Securosys Primus HSM in the same secure container in which the keys themseves are protected.
