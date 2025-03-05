---
sidebar_position: 3
title: CloudHSM T&C - Services description
sidebar_label: Service Overview
description: Gain insights into the comprehensive service offerings and features of HSMaaS, TSBaaS, and RESTaaS solutions provided by Securosys CloudHSM.
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

# Appendix II: Service Description – HSMaaS / TSBaaS / RESTaaS

Edition: December 2024

## 1. Product – What is CloudHSM

CloudHSM is a managed HSM service offered by Securosys. It provides the functionality of Hardware Security Modules (HSM) via API to Subscriber connecting remotely via Internet. Subscriber benefits from the security and advantages of physical HSM operated by Securosys without the burden of operating the devices.

An HSM is a physical computing device used to generate, secure, and manage cryptographic keys and perform cryptographic operations.

CloudHSM offers a range of cryptographic tasks:

- Generate cryptographically secure random data
- Generate, store, import, export, and manage cryptographic keys, including symmetric keys and asymmetric key pairs
- Use symmetric and asymmetric algorithms to encrypt and decrypt data
- Use cryptographic hash functions to compute message digests and hash-based message authentication codes (HMACs)
- Cryptographically sign data (including code signing) and verify signatures
- Attestation of keys to provide proof or their origin and attributes

The functionalities listed above are not exhaustive and depend on the service package and options subscribed to and the defined security policy.

A dedicated API description and an overview of the supported cryptographic functions and algorithms are provided on the Securosys Support Portal. The Securosys Support Portal means an online helpdesk and knowledge base system that is available to Registered Support User (requires login) at: https://support.securosys.com.

CloudHSM comprises the following services:

- HSM as a Service (HSMaaS), including
  - a shared HSM; or
  - a dedicated HSM owned and operated by Securosys; or
  - a customer owned HSM operated by Securosys; or
  - a shared HSM for Bring Your Own Key purpose (BYOKaaS)
- Transaction Security Broker as a Service (TSBaaS)
- REST API as a Service (RESTaaS)

## 2. Concepts

As part of the subscription, Subscriber gets sole access to their partition (user space) on a dedicated or, depending on the service package, a shared HSM cluster. The HSM cluster is only accessible over the Internet to authenticated users. Subscriber creates, manages, and uses cryptographic keys and functions. Only Subscriber has access to their user space and key data. The CloudHSM service infrastructure and the underlying HSM cluster is operated, monitored, and maintained by Securosys.

<figure class="image">
  ![](./resources/img/system_overview.png)
  <figcaption>Figure 1 CloudHSM simplified System Overview</figcaption>
</figure>

### 2.1. CloudHSM Access

Subscriber establishes a secure partition access on CloudHSM by installing a crypto provider on the application side or by using the restful API option. The Primus API provider handles communication encryption and automatic failover. Access and usage of the Primus API provider is granted to Subscriber as part of the subscription.

### 2.2. Transaction Security Broker as a Service (TSBaaS) and REST as a Service (RESTaaS)

TSBaaS or RESTaaS are supplementary services to HSMaaS. Besides communicating with the Service using the native HSM APIs, RESTaaS provides a RESTful API middleware managed by Securosys. RESTaaS is based on TSBaaS.

In addition to RESTful API the TSBaaS provides a workflow engine for multi-authorization schemes based on the HSM enforced Smart Key Attribute (SKA) feature. TSBaaS is managed by Securosys.

### 2.3. HSMaaS - Cluster Synchronization

CloudHSM is set up with high availability (HA) synchronized HSMs. Subscriber does not need to replace objects manually between the devices. The synchronization is automatically performed by the HSM cluster using a master / clone concept.

Cryptographic operations can be performed on any HSM in the cluster. However, the creation of new objects (keys) requires the master HSM be available to maintain the data. If, for any reason (e.g., maintenance), the master HSM is unavailable to the members of the cluster cryptographic operations can be performed, but new encryption keys cannot be created.

### 2.4. HSMaaS - High Availability, Load Balancing and Disaster Recovery

For redundancy, failover, and load balancing HSM clusters are formed by at least two (2) HSM. The specific cluster configuration depends on the chosen CloudHSM package. For dedicated HSM services the setup must be selected accordingly.

The logic of the Primus API provider distributes cryptographic operations across all HSMs in the cluster randomly and therefore evenly.

Data centers are located in different risk regions (distance >20km). Each data center is equipped with redundant Internet connections.

Productive HSM clusters (i.e., ECO) replicate the data to an additional fortified data center in a former military bunker deep in the Swiss Alps (EMS zone 2 (BSI) / zone 3 (NATO)) for disaster recovery purposes. The HSMaaS disaster recovery procedures apply only to services with Disaster Recovery site setup.

### 2.5. HSMaaS - Partition Administration

CloudHSM partition parameter changes are performed by Securosys based on customer request (i.e., Order Form, service desk). Optionally, Subscriber can remote manage their partition using the Decanus Terminal (Partition Administration).

### 2.6. HSMaaS - Backups

Subscriber key data is automatically synchronized in the HSM cluster and is kept redundantly. Key invalidation is enabled (if there is no opt-out by Subscriber) on partitions preventing customers from accidental deletion of keys via the API.

For services with HSMs deployed at the Business Continuity (backup) site, automated daily backups are created via WebDAV. These backups are encrypted, can only be restored to the original HSM, and have a retention period of 10 days.

Securosys does not make backups of HSM partition data (i.e., key objects) outside of the HSM cluster except for backups of data to USB storage device before certain maintenance activities. Such backups are encrypted, only restorable to the HSM, stored offline in a vault, and are deleted at latest after 180 days.

Backup of partition data outside the CloudHSM platform can be performed by the Subscriber via the partition management option or via the API. Export via API is possible, provided that the keys are declared exportable when created and the partition configuration allows for key export. It is Subscriber's responsibility to secure such backups appropriately.

### 2.7. HSMaaS - Compliance

The CloudHSM service operation is ISO/IEC 27001 certified.
All data centers are ISO/IEC 27001 certified and are equivalent tier 3 or better.

CloudHSM is based on Primus HSM devices. Primus HSM comply with FIPS 140-2 Level 3 and Common Criteria EAL4+, EN 419 221-5. In all service packages except ECO-CC, the HSMs are operated in standard mode (hence supporting a wider set of cryptographic algorithms than, e.g., in FIPS mode) with the current firmware.

### 2.8. HSMaaS - Exit

When terminating the Subscription, whether by the Subscriber or by Securosys, or in the event of Securosys’ insolvency, dissolution, or cessation of business operations, the Subscriber has the option to, (i) transfer of data (partition) to on-prem Primus HSM, (ii) export (i.e. wrapped/unwrapped) and delete keys via API (depending on key flags), (iii) migrate data (partition) to on-prem Primus HSM as Partition Administration SO, or (iv) request deletion of partition by Securosys. Subscriber informs Securosys in writing using the cancellation order about the selected exit option. Upon termination, Subscriber’s HSM key data (partition) is deleted by Securosys. Any data in backup are kept confidential and will be deleted in the regular back-up cycles.

In case Subscriber requests a transfer of the user data (partition) to on-prem Primus HSM appliance. Cost of HSM and copy service will be billed separately. In case the termination is caused by Securosys, Securosys will actively support Subscriber in the migration of Subscriber’s data to a Subscriber owned Primus HSM without service charge.

## 3. Roles

In the operation and usage of the CloudHSM Service the following roles are defined:

### 3.1. Subscriber-Side Roles

#### a) Technical User (API Data Interface User)

The Technical User is the Subscriber’s business application accessing the API of the service. The Technical User is communicating with Subscriber’s partition in the Service to perform operations (e.g., create key, sign, etc.).

#### b) Partition Security Officer (PSO)

The Partition Security Officer (PSO) role for partition administration is a service option and requires a Decanus terminal device and the necessary licensing. The PSO option allows the Subscriber to take full control over the partition security configuration and access credential generation.

The PSO can additionally exclude the Security Officer role on device level (held by Securosys) from any intervention (e.g., password reset) on Subscriber’s partition. Note that by doing so, the PSO (Subscriber) takes over full responsibility for the risk of losing PSO access credentials.

The Partition Security Officer role can be activated alternatively by one or two-out-of-m persons.

### 3.2. Securosys Operation-Side Roles

#### c) CloudHSM Operation

CloudHSM Operation includes all Securosys personnel involved in the operation, including back-office, system administrators, Security Officer (SO), and Genesis Role.

Employees ensuring the CloudHSM Operation are regularly security checked personnel.

#### d) Security Officer (SO)

Certain interventions on the HSM require privileged SO permissions. The SO role is restricted to Securosys employees only and designated CloudHSM service engineers. In productive environments, the SO role adheres to the four-eyes principle.

Without explicit holding of the PASO role by the Subscriber, the partition administration is conducted by the SO role.

#### e) Genesis Role

The HSM Genesis (device owner) Role, which is required for backup / restore activity or SO creation, is held by a dedicated Securosys employee.

#### f) Involvement of Third Parties

Securosys may involve third parties at their own discretion. In any case, Securosys shall be responsible for the services of third parties as for their own services.

## 4. Service Specification - HSMaaS

### 4.1. Capacity & Performance

The capacity and performance of the service is defined by the number of HSM in a cluster and the number and size of partitions on the HSM. The performance is measured as mean value of RSA4096/ECC512 signatures processed per minute, measured over a time window of 24h. Short term performance results may deviate. A partition is defined as the size of user space in megabyte (MB) on each HSM in the cluster.

| Service Tier | Service Type | Performance:<br/>Operations per minute | Partition Size | Level of Redundancy<br/>(Datacenter / HSM) |
| --- | --- | --- | --- | --- |
| Economy (ECO), Economy Certified Mode (ECO CC) | Production | 600 | 10MB | Active: min. 1 /2<br/>BC*: 1 /1 |
| Sandbox (SBX) | Test | n/a | 10MB | Active: 2 /2 <br/> BC*: - /- |
| Platinum | Production | 12000 | 120MB | according Order Form |

Note: * BC site = Business Continuity (backup) site, not accessible via API.

Performance and partition size are as indicated above or as agreed in the Order Form (e.g. upgrades).

Usage Restrictions for Service Type “Test”: Customers expressly acknowledge and agree that service tiers categorized as "Test" (or similarly, e.g. “testing”, “Sandbox”, etc.), are strictly intended for non-productive purposes. Under no circumstances shall these testing service types be utilized for any productive or operational activities. Customers are fully responsible for ensuring compliance with this usage restriction and shall bear any consequences resulting from a violation of this clause.

### 4.2. HSM Locations

| Service | Data center locations | Active DC | Business Continuity (BC) DC |
| --- | --- | --- | --- |
| Economy (ECO), Switzerland | Switzerland | CH01, CH02 | CH03 |
| Economy (ECO), Germany | Germany, Switzerland | DE01, CH02 | CH03 |
| Economy (ECO), USA | USA, Switzerland | US01, US02 | CH03 |
| Economy (ECO), Singapore | Singapore, Switzerland | SG01, CH02 | CH03 |
| Economy (ECO), Global | Germany, USA, Singapore, Switzerland | DE01, US01, US02, SG01 | CH03 |
| Economy Common Criteria (ECO-CC), Switzerland | Switzerland | CH01, CH02 | CH03 |
| Economy Common Criteria (ECO-CC), USA | USA, Switzerland | US01, US02 | CH03 |
| Sandbox (SBX), Global | Switzerland, USA, Singapore | CH01, CH02, US02, SG01 | - |


Note: Location of active site/s is according to cluster definition and subject to change. However, new locations (with the exception of the global cluster) will still be located within the advertised country. The business continuity (BC) site is in Switzerland.

Note: Location of active site/s is according to cluster definition and subject to change. However, new locations (with the exception of the global cluster) will still be located within the advertised country. The business continuity (BC) site is in Switzerland.

### 4.3. Technical Order

With the subscription to the Service Subscriber provides to Securosys the Technical Order using the “Service Order Form”. The Technical Order contains the contact details and configuration parameters of the service and must be issued by authorized Subscriber’s personnel and shall be in line with the Order Form. Securosys sets up the Service corresponding to the Technical Order. Modification of configuration parameters require SO role.

| Configuration Parameter | Description |
| --- | --- |
|API Providers: | Each service supports API provider: <br/> - JCE/JCA <br/> - MS CNG <br/> - PKCS#11 <br/> - REST available via TSBaaS, RESTaaS <br/> Note 1: API providers must be activated individually. <br/>Note 2: API access can be entirely disabled on request to take the partition offline. |
| Smart Key Attribute: | Enables the SKA feature for HSM enforced multi-signature schemes. <br/> Note: The option is subject to cost. |
| Crypto Currency: | These licensing options enable cryptographic functions relevant for the respective crypto currency. <br/> Note: The options are subject to cost. |
| Key Import: | Allows/blocks on partition level to import keys in plain or wrapped format, e.g. import of existing Subscriber key material or restoration of exported keys, instead of exclusively generating them on the HSM. <br/> Note: It is recommended that key material is generated and hold inside HSM. Certain regulations require “never extractable” keys. |
| Key Export: | Allows/blocks on partition level to extract keys in plain or wrapped format, e.g. for backup. Only keys flagged with ACCESS_EXTRACTABLE=TRUE can be exported. ACCESS_SENSITIVE=FALSE is required in addition to export in plain. <br/> Note: Exporting keys is a sensitive activity and requires cautious handling. The export and secure external storage of user data is Subscriber’s sole responsibility. |
| Key Invalidation: | The key invalidation feature prevents from permanently deleting key objects via API, e.g. accidental deletion. It works as a bin. Key objects deleted via API are marked as invalidated and appear to be deleted to the API but can be restored or deleted permanently by SO/PASO only. <br/> Note: Invalidated key objects still consume partition space and key IDs remain used. |
| Partition Read-Only: | Read only partitions only allow usage of keys via API (no creation, modification, or deletion of keys via API). |
| Session Object: | Support of session objects. Ephemeral keys are used outside of HSM, encrypted with a per partition key (non-extractable, not accessible) and deleted at the end of the client session. These keys are not stored persistently on the HSM cluster. |
| Object Destruction: | IF set to false, keys cannot be deleted (delete will always fail). |
| Object Usage: | This setting controls the availability of secret and private keys. If disabled, these keys cannot be accessed or utilized for any cryptographic operations, ensuring they remain secure and inactive.<br/> Note 1: Cryptographic operations are sign, verify, encrypt, decrypt, derive.<br/> Note 2: Wrap and unwrap are controlled by key import and export settings and not considered cryptographic operations. |
| IP ACL: | Access to CloudHSM HSMaaS or dedicated TSBaaS can be restricted to the source IP-addresses indicated by the customer. The whitelist must be provided as comma-separated list of IP-addresses. |
| Partition Administration: | The Partition Administration feature using Decanus Terminal allows a Subscriber to fully control and manage its partition as a Partition Security Officer (SO), e.g., partition configuration, backup / restore, log review, setting of HSM access data. A Partition SO can even block out the HSM device operator (by Securosys) from performing any task on this partition. <br/> **WARNING**: <br/> Note that if you unselect “device SO access”, no user configuration changes can be applied by the device SO held by Securosys. Thus, setup password or permanent secret reset will only be possible by the Partition SO. If you lose the access credentials of the Partition SO under these conditions, no further modification of the user configuration will be possible. Hence, by disabling “device SO access” the Subscriber is going to accept the residual risk of losing his access data of the Partition SO. Furthermore, he will be responsible for the safe handling and storage of the access credentials. |

### 4.4. Partition Modifications

Subscriber may modify the configuration at any time using the Partition Administration option or by support request to Securosys. The latter is subject to cost.

### 4.5. Backup

According to the definitions in Section 2.6.

### 4.6. Service Continuity

For shared CloudHSM services, in the event of a disaster where active data centers fails, the service will run on the remaining active data centers of the service cluster. In case no active data center remains available, Securosys will make commercially reasonable efforts to restore the service within a period of five (5) days from the backup data center. Normal service level will be restored within 3 months, to the extent that circumstances permit.

For dedicated CloudHSM services, distinct service continuity approaches apply depending on the architecture chosen by the customer.

## 5. Services Specification - HSMaaS BYOK (BYOKaaS)

### 5.1. Capacity & Performance

The capacity and performance of the CloudHSM BYOK services is according to the definitions of the Economy (ECO) service described in Section 4.1 with a limitation of the number of keys the Subscriber can hold:

| Service | Partition Size | Max. Number of Key Objects |
| --- | --- | --- |
| BYOK Service - Economy (ECO), Entry | 1MB | 3 / 10 / 200 <br/>(according subscription tier) |


### 5.2. Backup

According to the definitions in Section 2.6.

### 5.3. Partition Settings

The initial partition settings, as described in Section 4.3, are fixed for this service type to the following definitions:

| Configuration Parameter | Description (initial default settings) |
| --- | --- |
| API Providers: | JCE/JCA - enabled (all other APIs disabled)|
| Key Import: | Disabled |
| Key Export: | Enabled (“Key Extraction” disabled to allow only wrapped key export) |
| Key Invalidation: | Enabled <br/> Note: Depending on the quantities of objects the partition storage space can be consumed (as no keys can be deleted from the API) and can affect the application. Freeing up storage space requires SO intervention and is subject to additional cost. |
| Partition Read-Only: | Disabled |
| Session Object: | Enabled |
| Object Destruction: | Enabled |
| Object Usage: | Disabled |
| IP ACL: | Set according to Subscriber’s order. |
| Partition Administration: | Set according to Subscriber’s order. |


## 5.4. Partition Modifications

Subscriber may modify the configuration (e.g., activation / deactivation of the API) at any time using the Partition Administration option or by support request to Securosys. The latter is subject to cost.

## 6. Services Specification - TSBaaS / RESTaaS

### 6.1. Capacity & Performance

TSBaaS is operated on GCP Kubernetes Engine (GKE) the cryptographic capacity and performance depends on the underlaying HSMaaS package.

| Service | Type | Operation Sites | Underlaying HSMaaS package |
| --- | --- | --- | --- |
| TSB-ECO | Production | According subscription (Switzerland, USA, …)| ECO |
| TSB-SBX | Test | According subscription (Switzerland, USA, …) | SBX |

Note: Location of active site/s is according to cluster definition and subject to change. However, new locations (with the exception of the global cluster) will still be located within the advertised country.

Service tiers of type “testing” must not be used for productive purposes.

## 6.2. RESTaaS (REST API)

REST API for HSMaaS is a variant of TSBaaS and depends on the subscribed options. RESTaaS runs on the same infrastructure as TSBaaS.

## 6.3. Backup

The TSB is orchestrating the approval workflow of SKA (smart key attributes) rules applied to key objects. The current state of approvals is only relevant and kept until the completion of the transaction in the TSB and is not backed up.

## 6.4. Access Control

Shared TSB Instance:

By default, TSBaaS is provided as a shared instance. Authentication and authorization of subscriber is based on Jason Web Token (JWT) issued by Securosys.

Dedicated TSB Instance:

In combination with Platinum HSMaaS packages the TSB can be ordered as dedicated instance.

In this case the TSB service provides TLS-based authentication and privacy based on public server certificate. 2-way TLS (mTLS) is implemented for mutual authentication. During the deployment process the subscriber agrees to provide the certificates of the applications and approval clients in Java Key Store (jks) format. For any productive system the use of 2-way TLS is mandatory.

| Service | TLS | Customer Client Certificates (Customer) |
| --- | --- | --- |
| TSB-ECO | 2-way TLS | Mandatory (jks) |
| TSB-SBX | 2-way TLS <br/> or <br/> 1-way TLS | Optional (jks) |

## 6.5. Service Continuity

In the event of a disaster at the active service region. Securosys will make commercially reasonable efforts to restore the service in a different region within a period of five (5) days, to the extent that circumstances permit.
