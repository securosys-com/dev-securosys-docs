---
sidebar_position: 3
title: CloudHSM - Load Balancing & Recovery
sidebar_label: Load Balancing & Recovery
description: To ensure robust redundancy and load balancing, Securosys utilizes HSM clusters across multiple data centers, including a fortified disaster recovery site in the Swiss Alps.
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

# High Availability, Load Balancing & Disaster Recovery

To ensure the highest levels of redundancy, failover, and load balancing, Securosys employs HSM clusters consisting of a minimum of two and a maximum of four HSMs. The specific cluster configuration is tailored to the chosen CloudHSM [service package](../Packages/overview.md), with dedicated HSM services requiring a customized setup.

### Load Balancing

The Primus API Provider’s intelligent design distributes cryptographic operations randomly across all HSMs in the cluster, ensuring even distribution. This approach optimizes performance and resource utilization while maintaining a strong security posture.

### Data Center Redundancy

Securosys strategically locates data centers in distinct risk zones, ensuring a distance of at least **20** kilometers between them. Each data center is equipped with multiple redundant internet connections to safeguard against network disruptions and maintain uninterrupted service availability.

### Disaster Recovery

For disaster recovery purposes, productive HSM clusters (i.e., [CloudHSM Economy (ECO)](../Packages/economy)) replicate their data to an additional fortified data center housed within a former military bunker deep within the Swiss Alps. This facility, classified as EMS zone 2 (BSI) and NATO zone 3, offers exceptional resilience against natural disasters and extreme environmental conditions. The CloudHSM disaster recovery procedures are exclusively applicable to [service packages](../Packages/overview.md) with Disaster Recovery site setup, ensuring comprehensive protection against unforeseen events.