---
sidebar_position: 2
title: CloudHSM T&C - Support, maintenance & SLA
sidebar_label: Support Details
description: Explore detailed service level agreements and support offerings for HSMaaS, TSBaaS, and RESTaaS solutions with Securosys CloudHSM.
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

# Appendix I: Service Level and Support Services – HSMaaS / TSBaas / RESTaaS

Edition: December 2024

## 1. Service Level

### 1.1. Service Availability, Uptime, Downtime and Exclusion from Downtime

During the term of the Agreement Securosys will provide the Covered Service available 24 hours a day, 7 days a week to Subscriber with Monthly Uptime Percentage as follows:

| Covered Service | Monthly Uptime Percentage |
| --- | --- |
| HSMaaS | 99.9% |
| TSBaaS/RESTaaS | 99.9% |

The Monthly Uptime Percentage target applies only to services with geo-redundant setup.

Covered Service means the provisioning of the Basic Functionality of using key objects and cryptographic algorithms and functions, in particular signing requests are answered. The Basic Functionality explicitly excludes the generation or import of objects (e.g., keys). Monthly Uptime Percentage is defined as the availability of the Basic Functionality during a calendar month, i.e., Uptime = 1 – (sum of monthly Downtime in minutes) / (minutes per calendar month).

Downtime is defined as an interruption of one (1) minute during which the Basic Functionality of the Service is impaired.

Downtime does not include: (i) any Scheduled Downtime, (ii) Suspension or termination of Subscription in accordance with the T&C, (iii) Critical, emergency or security patches (iv) factors outside Securosys’ control, including any Force Majeure event, Internet connectivity problems beyond Securosys’ ISP environment, or Subscriber’s or any third party’s network, software, equipment or other technology or service.

Scheduled Downtime means Downtime resulting from Securosys maintenance on the Covered Service. Scheduled Downtime is announced at least 8 weeks in advance. Scheduled Downtime shall not exceed 8 hours and is limited to one intervention per 6 months. No seasonal restrictions apply. Securosys shall take commercial reasonable effort to plan Scheduled Downtime during low load times.

### 1.2. Maintenance Windows, Service Updates and Announcement

Maintenance Windows are time slots during which Securosys performs system maintenance. Maintenance activities are usually scheduled to affect only one data center or HSM at a time, so that subscribers can switch to the redundant site or HSM and Basic Functionality is maintained.

Regular system maintenance that is not expected to have a negative impact on the use of the service (e.g., weekly perimeter infrastructure/server updates) will not be announced.

Maintenance windows for service updates (e.g., HSM firmware or TSB updates) that provide new functionality will be announced with commercially reasonable effort at least two (2) weeks in advance for test and integration services (e.g., Sandbox (SBX)) or four (4) weeks for production services (e.g., ECO, Platinum), providing in general six (6) weeks of lead time. The service update is performed in the following order: First, the update is installed on the test and integration services. Four (4) weeks after the update of the test and integration services, the update will be applied to the productive service. During this period, Subscriber may verify the correct operation of the test and integration services. If Subscriber finds any negative impact on its application, they may submit a written request to Securosys. Securosys will then, if economically justifiable, postpone the update for a maximum of another four (4) weeks.

Emergency and security updates or patches can be implemented at any time without following the above-mentioned order. These updates will be announced without undue delay.

Maintenance windows will not exceed eight (8) hours per window. No seasonal restrictions for maintenance windows apply.

### 1.3. Service Reviews

Securosys may contact the Subscriber representative on a quarterly basis to review the service experience and to obtain Subscriber feedback to optimize Subscriber satisfaction.

## 2. Support Services

As part of the Service, Securosys will provide support to Subscriber. Subscriber will receive access to documentation, white papers, user guides, support for billing inquiries, API provider libraries, and access to the Securosys Helpdesk.

Subscribers of Securosys CloudHSM Service obtain access to the Securosys Support Portal.

Support does not include troubleshooting in case of failure in Internet, Internet connectivity at Subscriber, or third-party services and components.

### 2.1. Helpdesk Support Services

Securosys Helpdesk enables Subscriber to properly carry out individual application steps and to fix or circumvent problems in connection with the Service. Support only includes troubleshooting of issues originating from the Service.

The Subscription includes the following support services:

- Access to the helpdesk (Securosys Support Portal) in accordance with the agreed availability
- Reception of reported malfunctions in accordance with the ticketing procedure
- Verification of completeness of incoming tickets and follow-up enquiry if incomplete
- Classification of the malfunction as a fault or an enhancement
- Provisioning of competent personnel required for the setup, solving, or bypassing known problems of the CloudHSM service
- Staggered qualified answers within the agreed response times by forwarding to the appropriate support body
- Monitoring and coordinating incoming calls, prioritization, disposition, escalation, and termination of malfunctions
- Maintaining access rights to the helpdesk and the ticketing portal
- Troubleshooting and maintenance of the CloudHSM core service

The following services are not included in the Subscription. All labor for these services are billed by time and materials. Securosys informs Subscriber beforehand about additional cost by e-mail or ticket:

- Troubleshooting of issues which are not caused by a malfunction or failure of the CloudHSM core service (e.g., issues with the API provider setup or Subscriber’s application)
- Education, training, engineering, or consulting
- Assisting users with operation
- On-site intervention at the Subscriber’s location

### 2.2. Subscriber’s Responsibilities

In order for Securosys to provide the required high quality of service, the following activities are expected from Subscriber:

- Provisioning of reasonably skilled personnel required for the setup, solving, or bypassing known problems of the Primus HSM API provider and to support Securosys' interventions.
- Subscriber will reasonably cooperate with Securosys to fix errors, bugs, malfunctions or network connectivity issues within Subscriber’s organization and, if applicable, within Subscriber’s Affiliates involved.
- Support tickets and calls must include a detailed description of the failure and reference the service contract.
- Support tickets and calls may only be sent to Securosys Support by previously-registered Registered Support Users. Subscriber defines and registers at least one Registered Support User.
- Subscriber shall address commercial requests to their designated Securosys account manager.
- Secure handling and storage of access credentials.
- Provisioning of customer-specific parameters (e.g., HSM security policy, client certificates for mTLS (TSBaaS))

### 2.3. Subscriber Support User Roles

#### a) Commercial Contact

The Commercial Contact of the Subscriber is the main contact for commercial matters, like changes on services / options and invoices. Typically, they are submitting the technical order form.

This role can name and approve Registered Support Users. Subscriber must name at least one Commercial Contact and shall inform Securosys about any modifications.

#### b) Registered Support Users

Subscriber’s Registered Support Users receive access to the support portal, are permitted to open support requests and download technical documentation, and will receive technical support announcements. The Subscriber shall define at least one support user who subscribes to the service newsletter (e-mail notifications) to receive update or maintenance announcements.

Different roles of Registered Support Users include:

- Support User: Standard support user role, having permissions to create support requests, access knowledge base, and download documentation or software.
- Privileged Support User: In addition to Support User’s permissions, this user is the point of contact to receive service access credentials or request security policy changes.
- Administrative Support User: In addition to the permissions of the Support User, this user can request and confirm Registered Support User modifications (add, modify, delete, assign role). In case the Administrative Support User is not available, Securosys support will request confirmation from the Commercial Contact.

Registered Support Users can hold different roles. Subscriber must name at least one Administrative Support User and Privileged Support User.

Subscriber is responsible for registering their Registered Support Users with Securosys. Five (5) Registered Support Users are included with the service. More users might be registered for an additional fee. Subscriber shall communicate changes to the Registered Support Users in a timely manner.

Support Users are encouraged to leverage the online self-service support site to update and check the status of their cases, so that the assigned support engineer is alerted straight away, thereby improving general response and resolution time.

### 2.4. Support Language

All support provided by Securosys pursuant to this Agreement will be provided in the English language.

### 2.5. Incident Classification

Incident Classification Incidents are classified according to the following criteria:

| Priority | Clasification |
| --- | --- |
| Priority 1 = Critical (high) | An incident of priority “critical” is a failure that causes a business-critical software or hardware crash or prevents normal operation. Continued work is no longer possible or is seriously hindered. Only failures in a production environment are critical; they hinder operations in a way which demands immediate attention/response. <br/> **Note:** Critical incidents must first be registered in the ticketing system and 15 minutes later be reported by phone to become valid. |
| Priority 2 = Major (medium) | An incident of priority “major” is an anomaly or a failure which affects the functionality but is not business-critical, because a temporary workaround is available and/or its effects are tolerable. The effects for the Subscriber consist of a temporary solution which permits continued operation even without a permanent solution or causes only a non-critical disturbance or degradation of performance to the business process. |
| Priority 3 = Normal (normal) | A support case of priority “normal" is an anomaly or a failure, which affects or disables a negligible feature or function. The effects for the customer are small or have no effect on the operation. |
| Priority 4 = Minor (low) | A support case of priority "minor" usually covers any kinds of information requests. |


Customer designates P1-P4 priority upon submission of Requests. Securosys will review Customer's priority designation and may change designations that Securosys believes are incorrect. Any such determination made by Securosys is final and binding on Customer.

### 2.6. Request Submission

Securosys accepts support requests from Registered Support Users only.

All requests must be sent to the Support Portal or by phone to the phone numbers listed under Point of Contact, section 2.11. Contacting Securosys support by phone requires a valid ticket case number which you obtain from the Support Portal.

Any P1 priority issue must be confirmed by phone after submission of ticket to become valid.

### 2.7. Support Availability

An incident can be reported to Securosys during the following support service times:
| Service Package | Economy - ECO | Sandbox - SBX | PLATINUM / PLATINUM Enterprise |
| --- | ---| --- | --- |
| Support Service Time | 24x7 | 8x5 <br/> (CET, 09:00-17:00) | 24x7 |

P1 issues and system incidents affecting Service availability are handled during Support Service Times above.

For P2-P4 requests or requests requiring Security Officer intervention Support Service Time is limited to Securosys Office Hours.

### 2.8. Securosys Office Hours

Securosys business hours are Monday through Friday, CET 09:00hrs – 17:00hrs, except on Securosys holidays.

Securosys holidays:

- Public holidays of Zurich city, Switzerland: New Year’s Day (January 1), Berchtold’s Day (January 2), Good Friday, Easter Monday, Sechseläuten, Ascension Day, Pentecost Monday, Swiss National Day (August 1), Knabenschiessen, Christmas Day (December 25), Stephan’s Day (December 26)

### 2.9. Response Time

The response time starts with the notification of an incident. It is suspended outside the Support Service Time and resumes on the next working day.

A qualified answer as to the possible cause (e.g., user or software problems) and the possible solution will be provided within the agreed Response Time according to the classification of the fault.

| Service Package | Economy - ECO | Sandbox - SBX | PLATINUM / PLATINUM Enterprise |
| --- | --- | --- | --- |
| Response Time: <br/> Critical (P1) <br/> Major (P2) <br/> Normal (P3) <br/> Minor (P4) | <br/> +2 hours <br/> +8 hours <br/> +12 hours <br/> +24 hours | <br/> +8 hours <br/> +12 hours <br/> +24 hours <br/> +48 hours | <br/> +2 hours <br/> +8 hours <br/> +12 hours <br/> +24 hours |

### 2.10. Support Rates

Billable support services and support services exceeding the coverage of this Agreement will be invoiced monthly using the support Hourly Base Rate (HBR) or/and base fees as agreed in the Order Form as calculation basis and will include a detailed report of efforts and expenses.

For services on Subscriber’s request performed outside of Securosys installations (e.g., on-site installation support, training), travel time is considered billable working hours.

### 2.11. Point of Contact

All requests must be classified and sent to Securosys support in one of following ways:

- Support Portal: https://support.securosys.com

| Region | Phone number |
| :--- | :---|
| EMEA Switzerland | +41 58 255 0112 |
| EMEA Germany | +49 83414386997 |
| APAC | +852 58033704 |
| AMERICAS | +1 571 619 6378 |

:::warning
Contacting Securosys support by phone requires a valid ticket case number, which you may obtain through the support portal.
:::