---
title: PKCS#11 - Building Own Applications
sidebar_label: Building Own Applications
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Building Own Applications
## API Header

Include $PRIMUS\_HOME/include/pkcs11.h file if you plan to build an
application for the Primus HSM with PKCS#11 support. The file
'$PRIMUS\_HOME/include/pkcs11.h' contains revision information we
implement. If you do not use the 'official' version of the pkcs11.h
file, you may consider deleting the revision macro and keep track of
your own versions.

## Linking

<Tabs groupId="os">
<TabItem value="os-unix" label="Unix" default>
$PRIMUS\_HOME/lib/libprimusP11.so

:::info

Validate the library dependencies with the command `ldd /usr/local/primus/lib/libprimusP11.so`

:::

</TabItem>
<TabItem value="os-windows" label="Windows">
$PRIMUS_HOME/primusP11.lib
</TabItem>
</Tabs>
