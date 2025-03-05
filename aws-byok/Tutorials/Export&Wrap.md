---
sidebar_position: 6
title: AWS BYOK - Export & Wrap Key Material
sidebar_label: Export & Wrap Key Material
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Export & Wrap Key Material

The Securosys HSM records an entry in the logs when performing actions with keys as well as any established or failed authentication.
Use the `AwsKmsByokExport` command to wrap the specified key on the HSM using the public key that was downloaded from AWS KMS, see chapter [Downloading Public Key and Import Token](/aws-byok/Tutorials/Download-Pub-key.md). The same wrapping algorithm (either `RSAES_OAEP_SHA_256` or `RSAES_OAEP_SHA_1`) must be specified.

`AwsKmsByokExport` sample command:
```bash
java -jar primus-tools.jar AwsKmsByokExport <HSM connection and credentials>\
-aeskey AES-KEY-NAME [-aeskeypassword <HSM AES key password>] \
-kekfile <kek public key file, DER or PEM format> \
[-kekdata <kek public key data, base64 of DER>] \
-outfile <AWS KMS BYOK encrypted key output file>
```
Example command:
```bash
java -jar primus-tools.jar AwsKmsByokExport \
--host a-api.cloudshsm.com -port 2300 \
-user DEMO-TEST -password file:pwsetup \
-primusproxyuser FQO...QQOS -primusproxypassword file:pwproxy -aeskey AWS-ByokAES256 \
-kekdata public_key_b64 -outfile ImportKey
```
|Parameter |Description|
|---|---|
|`-aeskey`|	Replace the `-aeskey` parameter variable `AES-KEY-NAME` with the key name of the key to be extracted from the HSM, wrapped and imported into AWS. Additionally, if the key was protected with a password the `-password` parameter must be added to the command with where the variable `<HSM AES key password>` should be replaced with its correspondent password.|
|`-mode`| The `-mode` parameter defines the mode with which to wrap the key material, default: `RSAES_OAEP_SHA_256`, alternative: `RSAES_OAEP_SHA_1` |
|`-kekfile` | Use the `-kekfile` parameter if the public key was downloaded from the AWS KMS console, alternatively if the AWS KMS API was used to download the base 64 version of the public key the `-kekdata` parameter should be used instead. Replace the variable `<kek public key file, DER or PEM format>` with the downloaded public key file.|
| `- kekdata` | Use the `-kekdata` parameter if the base 64 encoded public key was downloaded with the AWS KMS API, alternatively if the AWS KMS Console was used to download the public key, the `-kekfile` parameter should be used instead. Replace the variable `<kek public key data, base64 of DER>` with the downloaded public key file.|
|`-outfile`| Replace the `-outfile` parameter variable `<AWS KMS BYOK encrypted key output file>` with the file name of the to be exported and wrapped key material. |



