---
sidebar_position: 2
title: Configuring FortiGate with Security HSM
sidebar_label: 3. FortiGate Configuration
description: Configuring Fortinet FortiGate Next Gen Firewall (NGFW) with Securosys Hardware Security Modules (HSMs).
keywords: [fortinet, fortigate, firewall, hsm clusters, integration, primus hsm, securosys]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Configuring FortiGate

The HSM integration is done via the ForteGate `nethsm` module (Command Line Interface).

## 1. HSM Registration
Enable HSM functionality with following command:
```bash
config system nethsm
set status enable
```

## 2. Upload the HSM Configuration File
<Tabs groupId="forti-hsmconfig">

  <TabItem value="forti-cli-uploadconfigfile" label="Upload the HSM Configuration File" default>
  It is recommended to upload the previously tested configuration file (primus.cfg) in raw mode via a tftp server <br/> 
  (e.g. http://tftpd32.jounin.net):
  ```bash
  execute nethsm upload-primus-cfg-raw <configFileName> <tftp-server-ip> e.g.
  ```
  ```text
  execute nethsm upload-primus-cfg-raw primusorigext.cfg 192.168.159.1
  This will apply the new primus.cfg without applying recommended settings. Do you want to continue? (y/n)y


  primus.cfg has been updated.
  ```
  </TabItem>

  <TabItem value="forti-cli-loadconfig" label="Alternatively Transfer Configuration via CLI" default>
  :::warning quotes
  Any quotes in the configuration file have to be escaped by a backslash!
  :::

  ```bash
  config system nethsm
  set status enable
  set primus-cfg \"#-----------------------------
  # Primus PKCS#11 configuration
  #-----------------------------
  version = \"1.0\";

  /* This example configuration template contains 3 slots:                   */
  /* hsm0: standalone hsm with one partition (slot id 0)                     */ 
  /* hsm1,hsm2: redundant hsm cluster with each two partitions (slot id 1+2) */
  /*            e.g. for CloudsHSM service                                   */
  /* Comment/Uncomment the hsm or slot sections to adapt to your setup       */
  /* See PKCS#11 Provider User Guide for details                             */
  # FORTIGATE Integration Test

  /*--- GLOBAL CONFIGURATION SECTION ----------------------------------------*/
  primus:
  {
    wait_delay = 250; /* in ms*/

  …"
  ```
  </TabItem>
</Tabs>


## 3. Prepare and Configure HSM Secrets

The connection permenent secrets(s) and pkcs11-pin(s) have to be configured via CLI (or GUI).

The base-64 encoded *.secrets.cfg* file can be generated without local traces using the ppin tool console output (highlighted part), on the [client machine](/fortigate/Installation/ProviderPreparation):
:::warning
The maximum secrets length supported by FortiGate is 3k bytes.
:::


<Tabs groupId="forti-version">
  <TabItem value="cli" label="Secrets-File fetching and encoding" default>
  Note: for interactive input ommit the optional parameters.
    ```bash
    ppin --fortinet --user <username> [<setupPassword> <PKCS11Password>] [--proxyuser <proxyUserName> [--proxypassword <proxyPassword>]]   
    ```
    ```text
    # Fortinet secret to be loaded:
    // highlight-tip-start
    dmVyc2lvbiA9ICIxLjAiOwpwcmltdXMgOiAKewogIHVzZXJzIDogCiAgewogICAgdXNlcjAgOiAKICAgIHsKICAgICAgbmFtZSA9ICJQUklNVVNERVYzNjgiOwogICAgICBdpY3MgPSAiMzcwYzJj
    ... 
    GUwY2Y4ZjNhNTkwMzE2ZjE4MGI4YWZlNDdiMzY1Nzg1ZWQ3NyI7CiAgICB9OwogIH07Cn07Cg==
    // highlight-tip-end
    ```
  </TabItem>
</Tabs>


The parameter **pkcs11-pin** corresponds to the HSM PKCS#11 password and should be available from the HSM administrator (or configured [previously](/fortigate/Installation/prerequisites#set-pkcs11-password)). 


To configure the prepared connection secrets and pkcs11-pin(s) of the HSM partition(s) via CLI, use the following command sequence:

<Tabs groupId="forti-secrets">
  <TabItem value="forti-cli-syntax" label="Syntax" default>
  ```text
  config system nethsm
    set status enable
    set primus-cfg "<content of primus.cfg escaped, if not already loaded via tftp>"
    set secret-content <base-64 encoded .secrets.cfg file as output of ppin --fortinet>
    config partitions
        edit "<partition name 1>"
            set slot-id <pkcs#11 slot index>
            set pkcs11-pin <PKCS#11 PIN of this partition>
        next
        edit "<partition name 2>"
            set slot-id <pkcs#11 slot index>
            set pkcs11-pin <PKCS#11 PIN of this partition>
        next
    end
  end
  ```
  </TabItem>

  <TabItem value="forti-cli-example" label="Example configuration" default>
  Configuration file primus.cfg already uploaded via tftp.
  ```text
  config system nethsm
      set status enable
      set secret-content dmVyc2lvbiA9ICIxLjAiOwpwcmltdX...9OwogIH07Cn07Cg==
      config partitions
        edit "PRIMUSDEV368"
            set slot-id 0
            set pkcs11-pin PRIMUSDEV
        next
        edit "ALDUROZEP"
            set slot-id 1
            set pkcs11-pin dbZMEmqpMNU7PRIMUSDEV
        next
      end
  end
  ```
  </TabItem>
</Tabs>


<br/>
FortiGate stores and outputs above values in an encrypted format (see FortiGate documentation for details). If previously configured, these values can also be entered in the FortiGate encrypted format (using ENC in front of the value):
```text
config system nethsm
    set status enable
    set secret-content ENC kFR3tNLNuU5y4Lr08RMxx//gGBQznk0vgBiILs/L....
    config partitions
        edit "PRIMUSDEV368"
            set slot-id 0
            set pkcs11-pin ENC Y6f4fDwBaF2GUcT21R8Q9KTbi9Kw8N...
        next
        edit "ALDUROZEP"
            set slot-id 1
            set pkcs11-pin ENC WQw9aQ2qKTyDeWtDgsvujjqBWCoV/E...
        next
    end
end
```

For detailed FortiGate command description, certificate generation and usage refer to the [FortiGate documentation](<https://dlarea.securosys.com/partner/fortinet/FortiOS-7.2.8-HSM Integration with FortiGate.pdf>). 






