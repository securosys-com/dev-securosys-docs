---
sidebar_position: 2
title: PKCS#11 - Configuration
sidebar_label: Configuration
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Configuration

- First adapt the configuration file /etc/primus/primus.cfg (or
  %ProgramFiles%\\Securosys\\Primus P11) as described [below][1].

- In case you are using a service proxy (CloudHSM) [configure the
  service proxy details][2].

- After that, establish HSM connectivity, [fetching the HSM permanent
  secret][3] using the ppin command line tool.

## Configuration Files
<Tabs groupId="os">
<TabItem value="unix" label="Unix" default>
### primus.cfg
Possible configuration locations:

1. /etc/primus/primus.cfg

   The **primary location** lookup when initializing the PKCS#11
   library. The configuration file name is labeled 'primus.cfg', in
   lower case. When not found, the configuration initialization falls
   back to the fallback location.  Initially the directory and files
   don't exist. Add the directory and copy the files from the fallback
   location and adjust the access rights. It is recommended to use the
   primary location.

1. /usr/local/primus/etc/primus.cfg

   The **fallback location[^1]** lookup when initializing the PKCS#11
   library. The configuration file name is labeled 'primus.cfg', in
   lower case. Note, that a provider update will overwrite the file!

1. `SECUROSYS_PKCS11_CONF`

	The environment variable pointing to the fully qualified
    configuration file name.  When set, the environment variable
    **supersedes** the primary and fallback configuration locations.

If the file cannot be located, the initialization fails and returns an
initialization error.

### .secrets.cfg
Possible configuration locations:

1. /etc/primus/.secrets.cfg
   
   The **primary location** lookup when accessing the permanent
   secrets information.  The file name is labelled '.secrets.cfg', in
   lower case.  When not found, the initialization falls back to the
   fallback location.
   
   Initially the directory and file don't exist. Add the directory and
   copy the files from the fallback location and adjust the access
   rights. It is recommended to use the primary location.

1. /usr/local/primus/etc/.secrets.cfg

   The **fallback[^1] location** lookup when accessing the permanent
   secrets information.  The file name is labeled '.secrets.cfg', in
   lower case. Note, that a provider update will overwrite the file!

1. `SECUROSYS_SECRETS_CONF`

   The environment variable pointing to the fully qualified permanent
   secrets file name.  When set, the environment variable
   **supersedes** the primary and fallback configuration locations.

If the file cannot be located, the initialization fails and returns an
initialization error.

### syslog
Configure 'rsyslog.conf' and 'logrotate' to log the Primus PKCS#11
events. Add the lines in /etc/rsyslog.conf:

```text
# Save primus messages also to primus.log
syslog.* /var/log/primus.log
```

And add the line in /etc/logrotate.d/syslog:

```text
/var/log/primus
```

Restart the syslog daemon.
</TabItem>
<TabItem value="windows" label="Microsoft Windows">
### primus.cfg
Possible configuration locations:

1. HKCU\\Software\\Primus\\configFilename
   - The **primary location** lookup when initializing the PKCS#11
     library. Contains the complete pathname of the configuration
     file. When not found, the configuration initialization falls back
     to HKLM\\Software\\Primus\\configFilename
	 
1. HKLM\\Software\\Primus\\configFilename
   - The **secondary location** lookup when initializing the PKCS#11
	 library.  Contains the complete pathname of the configuration
	 file.

1. %ProgramFiles%\\Securosys\\Primus P11\\primus.cfg
   - The **fallback location[^2]** lookup when initializing the PKCS#11
     library. The configuration file name is labeled 'primus.cfg', in
     lower case. Note, that a provider update will overwrite the file!

1. `SECUROSYS_PKCS11_CONF`
   - The environment variable[^2] pointing to the fully qualified
     configuration file name. When set, the environment variable
     **supersedes** the primary, secondary, and fallback configuration
     locations.

If the file cannot be located, the initialization fails and returns an
initialization error.

### secrets.cfg
Possible configuration locations:

1. HKCU\\Software\\Primus\\secretsConfigFilename
   - The **primary location** lookup when accessing the permanent secrets
     information. Contains the complete pathname of the configuration
     file. When not found, the configuration initialization falls back
     to HKLM \\Software\\Primus\\secretsConfigFilename
 
1. HKLM\\Software\\Primus\\secretsConfigFilename
   - The **secondary location** lookup when accessing the permanent
     secrets information. Contains the complete pathname of the
     configuration file.

1. %ProgramFiles%\\Securosys\\Primus P11\\.secrets.cfg
   - The **fallback location[^2]** lookup when initializing the PKCS#11
     library. The configuration file name is labeled '.secrets.cfg',
     in lower case. Note, that a provider update will overwrite the
     file!

1. `SECUROSYS_SECRETS_CONF`
   - The environment variable[^2] pointing to the fully qualified
     permanent secrets file name. When set, the environment variable
     supersedes the primary, secondary, and fallback configuration
     locations.

If the file cannot be located, the initialization fails and returns an
initialization error.
</TabItem>
</Tabs>

##  "primus.cfg" Configuration File Parameters 

Configure your specific HSM connection details within the
configuration file primus.cfg. Adapt the colored parameters according
to your HSM setup (cluster configuration). Default HSM connection
details (hostnames, ports) can be found at the [bottom][4], if not
provided by your security officer. For setting up your CloudHSM, have
a look at the example on how to configure the [CloudHSM access][2].

<Tabs groupId="hsm-configurations">
<TabItem value="standalone-1-part" label="Standalone HSM with one partition">
```libconfig
#-----------------------------
# Primus PKCS#11 configuration
#-----------------------------
version = "1.0";

/* This example configuration template contains 1 slot:                    */
/* hsm0: standalone hsm with one partition (slot id 0)                     */ 

/*--- GLOBAL CONFIGURATION SECTION ----------------------------------------*/
primus:
{
  wait_delay = 250; /* in ms*/
  wait_max_tries = 5;
  connect_on_init = true;

  /*--- HSM CONFIGURATION SECTION -----------------------------------------*/
  hsms:
  {
//highlight-next-line-note
     /*--- HSM0, e.g. standalone hsm with one partition, slot id 0 */
     hsm0:
     {
//highlight-note-start
        host = "HSM0_HostnameOrIpOrProxyURL";
        port = "2310";
//highlight-note-end
        slots:
        {
//highlight-info-start
           slot0:
           {
              client_id = "Client_ID0";
//highlight-next-line-note
              user_name = "PARTITION_NAME0";
//highlight-next-line-note
              id = 0;
           }; /* end slot0 */
//highlight-info-end
        }; /* end slots */
     }; /* end hsm0 */
  }; /* end hsms */

//highlight-next-line-note
  /*--- LOG CONFIGURATION SECTION -----------------------------------------*/
  log:
  {
#    file = "/tmp/primus.log";    /* optional for unix, logs to LOCAL1 syslog */
#    file = "%PUBLIC%\Securosys\Primus P11\primus.log";  /* for windows */
    write_log_file      = true;     /* write to log-file */
    write_syslog        = true;     /* write to syslog */
    trace_linenumber    = false;    /* true or false */
    trace_timestamp     = true;     /* true or false */
    trace_function      = true;     /* true or false */
    trace_inout         = false;    /* true or false */
    trace_pid           = true;     /* true or false */
    trace_filename      = false;    /* true or false */
    trace_mask          = 0x00;
    trace_level         = 4;        /* 0-7 log level details */
  }; /* end log */

}; /* end primus */
```
</TabItem>
<TabItem value="cluster-2-part" label="Cluster with two partitions">
```libconfig
#-----------------------------
# Primus PKCS#11 configuration
#-----------------------------
version = "1.0";

/* This example configuration template contains 3 slots:                   */
/* hsm1,hsm2: redundant hsm cluster with each two partitions (slot id 1+2) */
/*            e.g. for CloudHSM service                                   */

/*--- GLOBAL CONFIGURATION SECTION ----------------------------------------*/
primus:
{
  wait_delay = 250; /* in ms*/
  wait_max_tries = 5;
  connect_on_init = true;

  /*--- HSM CONFIGURATION SECTION -----------------------------------------*/
  hsms:
  {
      /*--- HSM1, e.g. redundant CloudHSM cluster with two partitions (slot id 1+2) */
      hsm1:
      {
//highlight-note-start
         host = "a-api.cloudshsm.com";
         port = "2310";
//highlight-note-end
         slots:
         {
//highlight-info-start
            slot0:
            {
              client_id  = "Client_ID1";
//highlight-note-start
              user_name  = "PARTITION_NAME1";
              proxy_user = "SERVICE_USER";            /* if proxy in use */
              /* proxy_password = "SERVICE_PASSWORD";    optional */
              id = 1;
//highlight-note-end
            }; /* end slot0 */
//highlight-info-end

//highlight-tip-start
            slot1:
            {
              client_id  = "Client_ID2";
//highlight-note-start
              user_name  = "PARTITION_NAME2";
              proxy_user = "SERVICE_USER";            /* if proxy in use */
              id = 2;
//highlight-note-end
            }; /* end slot1 */
//highlight-tip-end
         }; /* end slots */
      }; /* end hsm1 */
    
      /*--- HSM2 of redundant CloudHSM cluster with two partitions (slot id 1+2) */
      hsm2:
      {
//highlight-note-start
         host = "b-api.cloudshsm.com";
         port = "2310";
//highlight-note-end
         priority = 1; /* Optional priority. Default 0, which is highest priority */
         slots:
         {
//highlight-info-start
            slot0:
            {
              client_id  = "Client_ID1";
//highlight-note-start
              user_name  = "PARTITION_NAME1";
              proxy_user = "SERVICE_USER";            /* if proxy in use */
              id = 1;
//highlight-note-end
            }; /* end slot0 */
//highlight-info-end

//highlight-tip-start
            slot1:
            {
              client_id  = "Client_ID2";
//highlight-note-start
              user_name  = "PARTITION_NAME2";
              proxy_user = "SERVICE_USER";            /* if proxy in use */
              id = 2;
//highlight-note-end
            }; /* end slot1 */
//highlight-tip-end
         }; /* end slots */
      }; /* end hsm2 */

  }; /* end hsms */

//highlight-next-line-note
  /*--- LOG CONFIGURATION SECTION -----------------------------------------*/
  log:
  {
#    file = "/tmp/primus.log";    /* optional for unix, logs to LOCAL1 syslog */
#    file = "%PUBLIC%\Securosys\Primus P11\primus.log";  /* for windows */
    write_log_file      = true;     /* write to log-file */
    write_syslog        = true;     /* write to syslog */
    trace_linenumber    = false;    /* true or false */
    trace_timestamp     = true;     /* true or false */
    trace_function      = true;     /* true or false */
    trace_inout         = false;    /* true or false */
    trace_pid           = true;     /* true or false */
    trace_filename      = false;    /* true or false */
    trace_mask          = 0x00;
    trace_level         = 4;        /* 0-7 log level details */
  }; /* end log */

}; /* end primus */
```
</TabItem>
</Tabs>

The `version = "1.0";` line indicates the configuration file
version. [^3]

The top-level `primus:{ ... };` block contains the fields:


| Attributes | Content description |
|-------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| wait\_delay | Duration to wait when incomplete data received \[ms\].  (default: 250 \[ms\]) |
| wait\_max\_tries | Number of tries when waiting for data completion and fallback[^3] to next cluster HSM (default: 5) `net.ipv4.tcp_syn_retries` is reduced (=2) via socket options to reduce failover times |
| connect\_on\_init | Connect to configured HSM(s) on library initialization resulting in shorter connection setup times (default: true) |
| hsms | List of HSMs, in `hsms:{ ... };` block.  |




### Section 'hsms'

The `hsmx:{ ... };` block contains the HSM connection and
partition definitions, where ***x*** is indexed from 0..n.

| Attributes   | Content description                                                                                                                                                                                                                                                                     |
|--------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| host         | The hostname or IP address of the HSM or the service proxy.                                                                                                                                                                                                                             |
| port         | The port of the PKCS#11 API on the HSM or service proxy, as a string.                                                                                                                                                                                                                   |
| priority[^4] | Optional: Priority in selecting a redundant HSM. The smaller the value, the higher priority. Round-robin for equal priority.  Values: 0-127, default priority value is 0 (=highest priority).                                                                                           |
| pin          | Optional, **not recommended**: The setup password for the connection with the HSM. If present, the PKCS#11 Provider will use it to connect to the HSM partition. This setup password has a limited time validity.  Use the 'ppin' utility to [fetch the partition permanent secret][3]. |
| slots        | List of enumerated slots of the HSM.                                                                                                                                                                                                                                                    |

Clustered HSMs (having the same **PKCS#11 Slot ID**, see **id** in
Section [slots][5]) are redundant. The first connection is selected
randomly from the highest priority pool (=lowest value) and after that
round-robin from the same pool. If no HSM of this priority pool is
reachable, the next lower priority pool is considered in the same
manner until all pools are exhausted. In this case the provider
returns a CKR_TOKEN_NOT_PRESENT error.

### Section slots[^5]

The `slots:{ ... };` block contains sub-blocks `slotx:{ ... };` which
each define a slot (partition) with the following fields:


| Attributes     | Content description                                                                                                                                                                                                                                                              |
|----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| user_name      | The name of the HSM user (partition). It is assumed that identical user_names on same or  different HSMs refer to a redundant partition  (cluster), assigning automatically the same user credentials. Therefore not redundant partitions must  have different partition names! |
| client_id      | An ID string chosen by the client to identify itself or the application. It is a part of the complete user Id received and logged on the HSM (partially hashed): myApplication@hash(machine_name@ipd_adress@mac_address) Example: myApplication@d1172dac ...                     |
| proxy_user     | CloudHSM only: the name of the service user (proxy), as provided by Securosys.                                                                                                                                                                                                  |
| proxy_password | CloudHSM only: the password for the service user (proxy)                                                                                                                                                                                                                        |
| id             | **PKCS#11 Slot ID** assigned to the HSM partition, e.g.  id=0                                                                                                                                                                                                                    |

A network HSM differs slightly compared to a card reader (=slot) with
an inserted card (=token):

-   A connected HSM partition corresponds to a PKCS#11 slot with
    inserted token, both having the name of the partition.

-   **Different PKCS#11 Slot IDs** have to be assigned for **different
    partitions** on same and multiple HSMs.

-   **Same PKCS#11 Slot ID** has to be assigned for **redundant
    partitions (cluster).** Cluster redundancy is not visible for the
    applications; partition name and serial number are identical on
    all redundant partitions.

-   A partition may be addressed via multiple slots (using different
    PKCS#11 Slot IDs) to parallelize access (performance
    optimization).

-   The provider assumes that partitions with the same user_name are
    redundant and use the same credentials.

-   A service proxy (CloudHSM) autonomously distributes new
    connections to different redundant HSMs in the cluster.

### Section log

The log info is written to the syslog channel for Unix and to the
debug channel on Windows. The `log:{ ... };` block contains the
fields:

| Attributes        | Content description                                                                                                                                                                                       |
|-------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| file              | Complete pathname to an optional file where a copy of the log information is written.                                                                                                                     |
| write_log_file[^6]| Write log messages to the log file \<false\|**true**\> |
| write_syslog[^6]  | Write log messages to syslog \<false\|**true**\> |
| trace\_linenumber | Add the source line number to the message \<**false**\|true\>                                                                                                                                             |
| trace\_timestamp  | Add a timestamp to the message[^7] \<false\|**true**\>                                                                                                                                                    |
| trace\_function   | Add the function name to the message \<false\|**true**\>                                                                                                                                                  |
| trace\_inout      | Add function's entry/exit messages \<**false**\|true\>                                                                                                                                                    |
| trace\_pid        | Add process name/id to the message \<false\|**true**\>                                                                                                                                                    |
| trace\_filename   | Add source filename to the message \<**false**\|true\>                                                                                                                                                    |
| trace\_mask       | Allow trace messages:<br/>_0x00_ None<br/>_0x01_ log input of each p11 function<br/>_0x02_ log protocol serialization information<br/>_0x04_ log received TCP data information (avail. Bytes, bytes read) |
| trace\_level      | The maximum level of detail allowed in messages (0-7)                                                                                                                                                     |

The trace\_level have the following meaning:
| trace_level | Label        | Severity      | Description                      |
|-------------|--------------|---------------|----------------------------------|
| 0           | LOG\_EMERG   | Emergency     | system is unusable               |
| 1           | LOG\_ALERT   | Alert         | action must be taken immediately |
| 2           | LOG\_CRIT    | Critical      | critical conditions              |
| 3           | LOG\_ERR     | Error         | error conditions                 |
| 4           | LOG\_WARNING | Warning       | warning conditions               |
| 5           | LOG\_NOTICE  | Notice        | normal but significant condition |
| 6           | LOG\_INFO    | Informational | informational messages           |
| 7           | LOG\_DEBUG   | Debug         | debug-level messages             |

On _Unix:_ optionally write log information to 'file' in addition to syslog, default /tmp/primus.log.

On _Windows:_ write log information to 'file'; default %PUBLIC%\\Securosys\\Primus P11\\primus.log[^8]

Please check, that the user has read/write access to this log-file.


## Default Connection Parameters of the PKCS#11 API

The table below shows the default configuration values, if not
specified differently by your Security Officer (SO):

|                   | Hostname or IP Address | Port Number | Using Proxy |
|-------------------|------------------------|-------------|-------------|
| Standard HSM      | _as setup by your SO_  | 2310        | no          |
| Developer program | grimsel.securosys.ch   | 2410        | no          |
|                   | nufenen.securosys.ch   | 2411        | no          |
|                   | oberalp.securosys.ch   | 2412        | yes         |
| CloudHSM CH      | a-api.cloudshsm.com    | 2310        | yes         |
|                   | b-api.cloudshsm.com    | 2310        | yes         |

For other CloudHSM clusters see the application note [CloudHSM - Connectivity details][6].

<!-- Footnotes -->
[^1]: Versions prior to v1.5 have primary and fallback configuration
	location swapped.

[^2]: Environment variable support and default file location fallback
    were introduced with v1.8.4+

[^3]: In version 0.9.xxx the fallback mechanism is disabled.

[^4]: Introduced with v1.7

[^5]: Version prior to v1.0.3 use a different notation for slots block
    and slot id.

[^6]: Introduced with v2.2.2

[^7]: Versions prior to v1.5 do not support timestamp on Linux nor
    environment variable on Windows

[^8]: For productive systems, set the trace_level = 4; and consider
    Linux log-file rotation.


<!-- Links -->
[1]: #primuscfg-configuration-file-parameters
[2]: /pkcs/Tutorials/CloudHSM/cloud_hsm_setup
[3]: /pkcs/Installation/permanent_secret_management
[4]: #default-connection-parameters-of-the-pkcs11-api
[5]: #section-slots
[6]: /connectivity-details/cloudhsm-connectivity-details

