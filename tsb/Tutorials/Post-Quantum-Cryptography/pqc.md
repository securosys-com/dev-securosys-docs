---
sidebar_position: 0
title: Create Post-Quantum Cryptography Keys with Securosys
sidebar_label: Post-Quantum Cryptography
description: Discover how Post-Quantum Cryptography (PQC) secures your data against quantum threats. Learn about cutting-edge algorithms like CRYSTALS-KYBER, DILITHIUM, and SPHINCS+, approved by NIST, and how Securosys' innovative Smart Key Attributes make key management seamless. Explore our comprehensive API examples to safeguard your digital assets today!
keywords: [Post-Quantum Cryptography, PQC-SKA, Securosys, Smart Key Attributes, NIST, Round 3, CRYSTALYS-KYBER, CRYTALS_DILITHIUM, SPHINCS+, Commercial National Security Algorithm Suite, NSA Suite B Cryptography, key management, cloud HSM, HSM key management, HSM cloud, HSM as a service, cloud based HSM, HSM digital signature, HSM services, HSM service, HSM, hardware security module]
---


import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Post-Quantum Cryptography

In the following algorithms are outline approved by the Federal Information Processing Standards (FIPS) for post-quantum cryptography:

* FIPS 203 **(CRYSTALYS-KYBER)**, Module-Lattice-Based Key-Encapsulation Mechanism Standard
* FIPS 204 **(CRYTALS-DILITHIUM)**, Module-Lattice-Based Digital Signature Standard
* FIPS 205 **(SPHINCS+)**, Stateless Hash-Based Digital Signature Standard

These standards specify key establishment and digital signature schemes that are designed to resist future attacks by quantum computers, which threaten the security of current standards. The three algorithms specified in these standards are each derived from different submissions to the NIST Post-Quantum Cryptography Standardization Project. 

|Type | [Public-Key Encryption / KEMs](https://csrc.nist.gov/pubs/fips/203/ipd) | [Digital Signatures](https://csrc.nist.gov/pubs/fips/204/ipd) |
|---|---|---|
| Lattice-based | [CRYSTALYS-KYBER](https://pq-crystals.org/kyber/data/kyber-specification-round3-20210804.pdf) | [CRYTALS_DILITHIUM](https://pq-crystals.org/dilithium/data/dilithium-specification-round3-20210208.pdf)|
| Hash-based | | [SPHINCS+](https://csrc.nist.gov/pubs/fips/205/ipd) |

---
---

# PQC - Rest API (Example)

:::note PQC-SKA

PQC algorithms do support Securosys **Smart Key Attributes**, following the same principles. [Click here](/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker)

:::

### Create PQC-Key
**POST**: [/v1/key](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

**Description**: Create key request.

<Tabs groupId="pqc-algorithm">

  <TabItem value="Dilithium" label="Dilithium (FIPS 204)" default>
```js {4}
{
    "label": "pqc_dilithium_fips204_final",
    "password": null,
    "algorithm": "ML-DSA-44",
    "attributes": {
        "sign": true,
        "decrypt": false,
        "unwrap": false,
        "extractable": false,
        "modifiable": true,
        "destroyable": true,
        "sensitive": true
    }
}
```

**Supported Algorithms**: ML-DSA-44, ML-DSA-65, ML-DSA-87
  </TabItem>
    <TabItem value="Sphincs_plus" label="Sphincs+ (FIPS-205)" default>
```js {4}
{
    "label": "pqc_sphincs_plus_fips205_final",
    "password": null,
    "algorithm": "SLH-DSA-SHA2-128f",
    "attributes": {
        "sign": true,
        "decrypt": false,
        "unwrap": false,
        "extractable": false,
        "modifiable": true,
        "destroyable": true,
        "sensitive": true
    }
}
```

**Supported Algorithms**:SLH-DSA-SHA2-128s, SLH-DSA-SHA2-128f, SLH-DSA-SHA2-192s, SLH-DSA-SHA2-192f, SLH-DSA-SHA2-256s, SLH-DSA-SHA2-256f, SLH-DSA-SHAKE-128s, SLH-DSA-SHAKE-128f, SLH-DSA-SHAKE-192s, SLH-DSA-SHAKE-192f, SLH-DSA-SHAKE-256s, SLH-DSA-SHAKE-256f
  </TabItem>
      <TabItem value="LMS" label="LMS" default>
```js {4}
{
    "label": "pqc_lms",
    "password": null,
    "algorithm": "LMS",
    "attributes": {
        "sign": true,
        "decrypt": false,
        "unwrap": false,
        "extractable": false,
        "modifiable": true,
        "destroyable": true,
        "sensitive": true
    }
}
```

**Supported Algorithms**: LMS
  </TabItem>
</Tabs>


## Sign a Payload
**POST**: [/v1/synchronousSign](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/synchronousSign)

**Description**: Sign request, the payload (message to be signed) must be base64-encoded.

:::note Signing-Algorithms

The `signatureAlgorithm` is: **DILITHIUM, KYBER, SPHINCS_PLUS,** or  **LMS**

:::

<Tabs groupId="pqc-algorithm">

  <TabItem value="Dilithium" label="Dilithium (FIPS 204)" default>
```js {5,7}
{
    "signRequest": {
        "payload": "U29tZVBheWxvYWQ=",
        "payloadType": "UNSPECIFIED",
        "signKeyName": "pqc_dilithium_fips204_final",
        "keyPassword": null,
        "signatureAlgorithm": "DILITHIUM"
    }
}
```

**Supported Algorithms**: DILITHIUM

<details>
<summary>Signature Size</summary>

|Algorithm|RAW signature (Bytes)|Base64-Encoded (Bytes)|
|--|--|--|
|ML-DSA-44|2420|3228|
|ML-DSA-65|3309|4412|
|ML-DSA-87|4627|6172|

</details>

  </TabItem>
    <TabItem value="Sphincs_plus" label="Sphincs+ (FIPS-205)" default>
```js {5,7}
{
    "signRequest": {
        "payload": "U29tZVBheWxvYWQ=",
        "payloadType": "UNSPECIFIED",
        "signKeyName": "pqc_sphincs_plus_fips205_final",
        "keyPassword": null,
        "signatureAlgorithm": "SPHINCS_PLUS"
    }
}
```

**Supported Algorithms**: SPHINCS_PLUS
<details>
<summary>Signature Size</summary>

|Algorithm|RAW signature (Bytes)|Base64-Encoded (Bytes)|
|--|--|--|
|SLH-DSA-SHA2-128f|17088|22784|
|SLH-DSA-SHA2-192f|35664|47552|
|SLH-DSA-SHA2-256f|49856|66476|
|SLH-DSA-SHAKE-128f|17088|22784|
|SLH-DSA-SHAKE-192f|35664|47552|
|SLH-DSA-SHAKE-256f|49856|66476|

</details>

  </TabItem>
      <TabItem value="LMS" label="LMS" default>
```js {5,7}
{
    "signRequest": {
        "payload": "U29tZVBheWxvYWQ=",
        "payloadType": "UNSPECIFIED",
        "signKeyName": "pqc_lms",
        "keyPassword": null,
        "signatureAlgorithm": "LMS"
    }
}
```

**Supported Algorithms**: LMS

<details>
<summary>Signature Size</summary>

|Algorithm|RAW signature (Bytes)|Base64-Encoded (Bytes)|
|--|--|--|
|LMS|8688|11584|

</details>
  </TabItem>
</Tabs>

**Response (Dilithium):**

```js
{
  "signature": "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"
}
```

## Verify
**POST:** [/v1/verify](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/verifySignature)

**Description** Verify signature of a payload

Replace the `signature` from the previous response with the `payload`


<Tabs groupId="pqc-algorithm">

  <TabItem value="Dilithium" label="Dilithium (FIPS 204)" default>
```js
{
  "verifySignatureRequest": {
    "signKeyName": "pqc_dilithium_fips204_final",
    "signatureAlgorithm": "DILITHIUM",
    "payload": "U29tZVBheWxvYWQ=",
    "signature": "m2MX/8Murbk0rCWz5OD/3PtYoZbMDZkdTYH/BgWv7KCu8FVQJ7rhRNp/OlS9aNbCXSYzJmD...."
  }
}
```

  </TabItem>
    <TabItem value="Sphincs_plus" label="Sphincs+ (FIPS-205)" default>
```js
{
  "verifySignatureRequest": {
    "signKeyName": "pqc_sphincs_plus_fips205_final",
    "signatureAlgorithm": "SPHINCS_PLUS",
    "payload": "U29tZVBheWxvYWQ=",
    "signature": "hmWYnbg7zvsW+CCmhDMX7hImTWQmeKmRxTr8UW5SZyRfIpGLvGkixWh2GVi/vMZ1ukJ0KVv...."
  }
}
```
  </TabItem>
      <TabItem value="LMS" label="LMS" default>
```js
{
  "verifySignatureRequest": {
    "signKeyName": "pqc_lms",
    "signatureAlgorithm": "LMS",
    "payload": "U29tZVBheWxvYWQ=",
    "signature": "AAAAAAAAAAAAAAABnKAGK1dKoqzFlzpw8V2Hd1fsZ/Jl1incDsiJXW766AN8MKLP1ANzESVGK..."
  }
}
```

**Supported Algorithms**: LMS
  </TabItem>
</Tabs>



**Response**
```js
{
  "signatureValid": true
}
```

## Local Verify (BouncyCastle)

:::note Public-Key
The `publicKey` can be retrieved by fetching the key-attributes. 

* **POST** /v1/key/attributes
:::

<Tabs groupId="pqc-algorithm">

  <TabItem value="Dilithium" label="Dilithium (FIPS 204)" default>
```java {29,30,31}
/*
 * Copyright (c)2015-2024, Securosys SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 */

import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Illustrates ML-DSA/Dilithium signatures.
 */
public class VerifyMlDsaBcSample {
    public static void main(final String... args) throws Exception {
        final String base64Signature = "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";
        final String payload = "U29tZVBheWxvYWQ=";
        final String publicKey = "MIIFMjALBglghkgBZQMEAxEDggUhAKVRzhxw5evKuIugL7QL+efOGNV7RORMB23pFkkZ2HoHqbPzOhdIUWLEeY1wv2mN7zV7SVdrjaTbuaC5prFfALwVEowqaS+VIZQ4dJj/s7GfhE3dAnZdZv3aGokGduwdMqIZvqh5rSTFXHboFfA88Pzfi8Oq04N49TKvKZga+nQE1wXnKq9XCu5PleBy2jWPIrLuE53wj0WRTqgqma/jeIkHbdZJn3+doLwBy76jLhAFtDOjzXMu5PDKmavUIhuPwksJbwJ+PJWbStRFbCaeCcg5unvkDGqNy3XIqopX/lb+k7YaAAeTjQuJYP2hta0ig0cyIPx8mC2Cwn6uPE+1+OMMMFGdPsCK8EQa6DEkYWb83OYC3Le3GLfB2go0crzT9kBirBZXVZxxCKJemQFjISPuRza3P76zAr5LhrZzbfjehcytfTVfNMAl+EC2/c9Ksv19NYKR5r7JSdnSQTCghO40aRPIjMvTyyfCgMYj8jcVjFoqZJeIAMk8xf3LRFVCQxYV2seXZUvsQIXxo5gEYFGPFmARGJmnqxjoDGGkbBXAW5Q7HEuUG2w9cp8pW+K0u4F0jLyfE3GN/NrIsaBunq1jv2CJUglnf9AgRdGX454BUagB5Ksu5gFraKJ85o2GN/GPA0DynIg4KtM07YBt3U8J66iBXIuNV1/wpmI4I9zHKLUsUnBM6qbY0g6ozOx33XQ2LjBeWWGRA3n83pNjBb7JtYbQOW7PRGv7OmxPQk0ZGND6OBfdRAblLbiLdjKuhuB8KyxXrem2grRFXP1tsRwR9WCI0WNjUj0DbbDtmKiVhOxr0wNKM+5VziabRA1GEaM2Tdnx1vyAQRrLxTinjzDriW/EzbDg57pLQdwyYBBTrA3e3CNyZwo1TQSrYnjXiMzr/yqkYBaHquL7Q0acfwjnOPf532/MMbP8QJ7nyyoIxM7024u3AtnD0lH4vuUN79tCRWUANxTPYB4UfUl0YVmuxBXwTMjUpOmFNMDS3I8CZZQ5+z3dE9oGjEF1IE82/fsEOkNKNRRUNSif4uJ5CRdkBzJNijDRxXZ5hUv460xAbLmxO1DWoyduLnd5ur6/J5He52nFgUBgGB5PHKQfJR2a4JSkB40e9TuUWzdwddIqxkqO/hGHfL//dMqNGBlclAOYMHKTsalg780Uc6wBqL2dU/tSbHlsL2HU47CaAKuAv8q5VFsZ+niNR0s9mqpJX5QDLFJDfPJucClp6oP57LQZZGc6KcnS7azQ5ODWVuBsa4n63VpZVPdtWLGOoLNB1Qz6uvODlwuWRuO3dTNYxp3xSoFGwv67NiVorPATsetVsA6J0BFV48HQCA4SNwZ0r69KdNyEATDV4uXoaXZnNpe3drOZ8cwICzMRIks7QJUYnDXcW5s0dICKCScgChz8r2GiaYulWje1sAd2YikUJwgC9Gz11IxmvBhUh16ydr+KVRbcgzHvf1a09krdnCsnOtWi7fFsSDZG50myUIlsj8b6atEpzobfp1DrL7eHvCrZUroazsmyL9Y4R/gWsAgQ2K53vxzXch45xXzI/5fbcyHsXxe1I6dKs1bySassSlLuE9g3IkR3HFNyTD4OYapywiLn5uWx2rAHVey0JEE3KauNvAT3IMRkbuH6IFj+3lIz35/06agnbWyaCgZb3VlSck0NL0XCIuSj2T8WyFgf3V9RQCm3x7C+NriOu3JypE046Vl8jkMjJfj0x+XbLgfv+qzdZ7COGDclmYxst/o2yoc=";

        Security.addProvider((Provider)Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider").getConstructor().newInstance());
        final Provider provider = Security.getProvider("BC");

        final String mlDsaSignatureAlgorithm = "ML-DSA";
        final KeyFactory mlDsaKeyFactory = KeyFactory.getInstance(mlDsaSignatureAlgorithm, provider);
        PublicKey mlDsaPublicKey = mlDsaKeyFactory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));

        final Signature mlDsaSignature = Signature.getInstance(mlDsaSignatureAlgorithm, provider);
        mlDsaSignature.initVerify(mlDsaPublicKey);
        mlDsaSignature.update(Base64.getDecoder().decode(payload));
        final boolean verified = mlDsaSignature.verify(Base64.getDecoder().decode(base64Signature));

        System.out.println("verified: " + verified);
    }
}
```

  </TabItem>
    <TabItem value="Sphincs_plus" label="Sphincs+ (FIPS-205)" default>
```java {29,30,31}
/*
 * Copyright (c)2015-2024, Securosys SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 */

import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Illustrates SLH-DSA/Sphincs+ signatures.
 */
public class VerifySlhDsaBcSample {
    public static void main(final String... args) throws Exception {
        final String base64Signature = "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";
        final String payload = "U29tZVBheWxvYWQ=";
        final String publicKey = "MDAwCwYJYIZIAWUDBAMVAyEAqglEBiJhMCfqdNC5nWbvCcSItS+EtF9QlD4/fOB9g2s=";

        Security.addProvider((Provider)Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider").getConstructor().newInstance());
        final Provider provider = Security.getProvider("BC");

        final String slhDsaSignatureAlgorithm = "SLH-DSA";
        final KeyFactory slhDsaKeyFactory = KeyFactory.getInstance(slhDsaSignatureAlgorithm, provider);
        PublicKey slhDsaPublicKey = slhDsaKeyFactory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));

        final Signature slhDsaSignature = Signature.getInstance(slhDsaSignatureAlgorithm, provider);
        slhDsaSignature.initVerify(slhDsaPublicKey);
        slhDsaSignature.update(Base64.getDecoder().decode(payload));
        final boolean verified = slhDsaSignature.verify(Base64.getDecoder().decode(base64Signature));

        System.out.println("verified: " + verified);
    }
}
```
  </TabItem>
      <TabItem value="LMS" label="LMS" default>
```java {29,30,31}

/*
 * Copyright (c)2015-2024, Securosys SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 */

import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Illustrates LMS/BC signatures.
 */
public class VerifyLMSBcSample {
    public static void main(final String... args) throws Exception {
        final String base64Signature = "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";
        final String payload = "U29tZVBheWxvYWQ=";
        final String publicKey = "ME4wDQYLKoZIhvcNAQkQAxEDPQAAAAABAAAABQAAAAG/Y2bjcDH4ScYJVpt5nAECBMPkMN9dwtluItIyW7fmNh3Yy1LYYpjquaBxnWs8q6I=";

        Security.addProvider((Provider)Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider").getConstructor().newInstance());
        final Provider provider = Security.getProvider("BC");

        final String lmsSignatureAlgorithm = "LMS";
        final KeyFactory lmsKeyFactory = KeyFactory.getInstance(lmsSignatureAlgorithm, provider);
        PublicKey lmsPublicKey = lmsKeyFactory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));

        final Signature lmsSignature = Signature.getInstance(lmsSignatureAlgorithm, provider);
        lmsSignature.initVerify(lmsPublicKey);
        lmsSignature.update(Base64.getDecoder().decode(payload));
        final boolean verified = lmsSignature.verify(Base64.getDecoder().decode(base64Signature));

        System.out.println("verified: " + verified);
    }
}
```
  </TabItem>
</Tabs>




