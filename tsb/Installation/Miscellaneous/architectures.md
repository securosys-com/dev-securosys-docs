---
sidebar_position: 0
title: TSB & HSMaaS - Shared Security Architecture
sidebar_label: Security Architecture
description: Overview of Transaction Security Broker (TSB) architectures with Primus HSM and HSM as a Service (HSMaaS) solutions.
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, TSB architecture, TSB software, on-premise HSM, HSMaaS, Type 1 architecture, Type 2 architecture, Type 3 architecture, mTLS encryption, high availability HSM, cloud HSM, Securosys]
---

# Security Architecture

### TSB with OnPremise Primus HSM (_Type1_)

This architecture provides the flexibility to separate from Securosys's "as a Service" solutions, enabling self-management of the Transaction Security Broker Software and required HSM operations. By keeping everything in your own datacenter, you gain complete control over your architecture, eliminate the need to cross network boundaries, and establish seamless operational inheritance.

### TSB Software with HSMaaS in a Shared Environment (_Type2_)

In this architecture type, a secure environment is established by providing dedicated access to a single partition, even though the HSM is shared among multiple Securosys customers. The advantage of this approach is the reliability, scalability, high availability failover, and load balancing provided by Securosys "as a Service" Solution in managing the HSMs and TSB.

### TSB Software with HSMaaS in a dedicated Environment (_Type3_)

In this architecture, a dedicated HSM per customer (PLATINUM) is operated by Securosys in a Datacenter of the CloudHSM service, offering flexibility in choice. The locally managed REST-API / TSB Software supports end-to-end encryption using mTLS. The CloudHSMaaS solution provides High Availability, cloning mechanisms, and high-quality backups of the key material, ensuring the protection of valuable data. To pursue this architecture, please reach out to our Sales Team to begin the project.

---