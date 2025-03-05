---
sidebar_position: 3
title: Supported ECC Curves & OIDs
sidebar_label: Curve-oid
description: Overview of commonly used elliptic curves including secp224k1, secp224r1, secp256k1, secp256r1, secp384r1, secp521r1, x962p239v1, x962p239v2, x962p239v3, brainpool224r1, brainpool256r1, brainpool320r1, brainpool384r1, brainpool512r1, frp256v1, and Ed25519 with their corresponding curve-oid values.
keywords: [Elliptic Curves, secp224k1, secp224r1, secp256k1, secp256r1, P-256, prime256v1, secp384r1, P-384, secp521r1, P-521, x962p239v1, x962p239v2, x962p239v3, brainpool224r1, brainpool256r1, brainpool320r1, brainpool384r1, brainpool512r1, frp256v1, Ed25519, curve-oid, cryptography, cybersecurity, data security]
---


# Curve Oid
The oid of the curve to curve-oid mapping used for the EC or ED algorithms.

:::note Curve-OID

- If chosen algorithm is set to EC or ED you must provide the curve-oid as shown in the request sample below.
- If custom curve-oid's are required please just [contact support](https://support.securosys.com/external), we will help you.


:::

|Curve-Name|Curve-oid|
|--|--|
|secp224k1|1.3.132.0.32|
|secp224r1|1.3.132.0.33 |
|secp256k1|1.3.132.0.10 |
|secp256r1(also known as P-256 or prime256v1)|1.2.840.10045.3.1.7 |
|secp384r1 (also known as P-384)| 1.3.132.0.34 |
|secp521r1 (also known as P-521)| 1.3.132.0.35 |
|x962p239v1|1.2.840.10045.3.1.1 |
|x962p239v2|1.2.840.10045.3.1.2 |
|x962p239v3|1.2.840.10045.3.1.3 |
|brainpool224r1|1.3.36.3.3.2.8.1.1.1 |
|brainpool256r1|1.3.36.3.3.2.8.1.1.7 |
|brainpool320r1|1.3.36.3.3.2.8.1.1.9 |
|brainpool384r1|1.3.36.3.3.2.8.1.1.11 |
|brainpool512r1|1.3.36.3.3.2.8.1.1.13 |
|frp256v1|1.2.250.1.223.101.256.1 |
|Ed25519|1.3.101.112|

### Sample EC create key request

```js {5}
    {
        "label": "<keyname>",
        "password": [ "R","E","P","L","A","C","E" ],
        "algorithm": "EC",
        "curveOid": "1.3.132.0.10",
        "attributes": {
            "encrypt": true,
            "decrypt": true,
            "verify": true,
            "sign": true,
            "wrap": true,
            "unwrap": true,
            "derive": false,
            "bip32": false,
            "extractable": false,
            "modifiable": true,
            "destroyable": true,
            "sensitive": true,
            "copyable": false
        }
    }
```