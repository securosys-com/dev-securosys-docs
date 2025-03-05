---
title: PKCS#11 - Setting up CloudHSM Access
sidebar_label: Setting up CloudHSM Access
---

# Setting up CloudHSM Access

## Adapt the Configuration File *primus.cfg*

The connection to the CloudHSM service is employing a proxy. So first
you need to modify the primus.cfg to add the cloud SERVICE_USER name
for the proxy and to set the host and port. The **blue** printed
values must be set according to your received CloudHSM
details. Please ensure, that all the **bold** printed values are set
accordingly:

:::tip HSM Connectivity

When utilizing CloudHSM service, refer to [CloudHSM Connectivity Details](/connectivity-details/cloudhsm-connectivity-details) for API-Endpoint URI `host` and `port`. 

:::

```libconfig
...
    hsm0: {
// highlight-note-start
// highlight-next-line-info
      host = "a-api.cloudshsm.com";
      port = "2310";
// highlight-note-end
      slots: {
         slot0: {
            client_id = "CLIENT_ID1";
// highlight-info-start
// highlight-note-start
            user_name = "HSM_USERNAME";
            proxy_user = "SERVICE_USER";
// highlight-note-end
// highlight-info-end
            id = 0;
         };
      }; /* end slots */
    }; /* end hsm0 */
    hsm1: {
// highlight-note-start
// highlight-next-line-info
      host = "b-api.cloudshsm.com";
      port = "2310";
// highlight-note-end
      slots: {
         slot0: {
            client_id = "CLIENT_ID2";
// highlight-info-start
// highlight-note-start
            user_name = "HSM_USERNAME";
            proxy_user = "SERVICE_USER";
// highlight-note-end
// highlight-info-end
            id = 0;
         };
      }; /* end slots */
    }; /* end hsm1 */
...
```
The client_id helps to further identify in the logs the connection and
can be freely chosen by the user. Typically, it is composed of the HSM
username and / or application ID of the VM or user.

## Configure the Service Proxy Password

Then you configure the permanent secret for the service proxy using
ppin:

```bash
ppin -p -e SERVICE_USER
```
```text
********************
Primus Permanent PIN
********************
Provide proxy password for 'SERVICE_USER' : <enter Service Proxy Password, no echo>

********************
Primus Permanent PIN
********************
[01] slot-id 0:    user 'HSM_USERNAME' permanent secret: MISSING
[01] service/proxy user 'SERVICE_USER' permanent secret: Configured
```

Note that CloudHSM services might have whitelisting mechanisms in
place. In such cases you have to take care that the traffic originates
from the registered IP addresses.

## Retrieve the HSM Permanent Secret

Then you retrieve the permanent secret for the HSM partition using ppin:

```bash
ppin -a -e HSM_USERNAME
```
```text
********************
Primus Permanent PIN
********************
Provide setup password for 'HSM_USERNAME': <enter User Setup Password, no echo>
Provide PKCS11 password for 'HSM_USERNAME': <enter PKCS#11 PIN, no echo>

********************
Primus Permanent PIN
********************
[01] slot-id 0: user 'HSM_USERNAME' permanent secret: Configured
[01] service/proxy user 'SERVICE_USER' permanent secret: Configured
```

Now you can use the library. Check your connection using `ppin -t` as
described in [Connection Test][connectivity-test].

[connectivity-test]: /pkcs/Tutorials/ppin_tool#connection-test
