---
sidebar_position: 1
title: PKCS#11 - CloudHSM Service Proxy Setup
sidebar_label: Service Proxy Setup
---

# Service Proxy Setup

:::warning

If you are not using CloudHSM (or a service proxy), you can skip this section.

:::

In bigger clustered HSM environments you may wish to authenticate the
access against an authentication service (e.g. LDAP) before the
connection to the HSM is opened. The HSM is then used via a so-called
service proxy.

Before validating the service proxy password and fetching the
permanent secrets from the HSM you need to modify the primus.cfg
configuration file, **adding the SERVICE_USER** name for the proxy and
configuring **host and port of the service proxy** (instead of the
HSM):

```libconfig
...
    hsm0: {
// highlight-note-start
      host = "PROXY_URL";
      port = "2310";
// highlight-note-end
      slots: {
         slot0: {
            client_id = "CLIENT_ID";
            user_name = "HSM_USERNAME";
// highlight-next-line-note
            proxy_user = "SERVICE_USER";
            id = 0;
         };
      }; /* end slots */
    }; /* end hsm0 */
...
```

## Validating/Adding a Service Proxy Password

Then you can validate and add the password for the service proxy using
the ppin tool:

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

From now on you can [retrieve the permanent secret][permanent-secret] for 'HSM_USERNAME'
via the service proxy.

## Removing a Service Proxy Password

```bash
ppin -x -e SERVICE_USERNAME
```
```text
********************
Primus Permanent PIN
********************
[01] service/proxy user 'SERVICE_USER' permanent secret: MISSING
```


[permanent-secret]: /pkcs/Installation/permanent_secret_management
