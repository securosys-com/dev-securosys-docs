---
sidebar_position: 7
title: JCE/JCA API Provider Release Notes
sidebar_label: Release Notes
description: Release Notes of Java Cryptography Extension (JCE) and Java Cryptography Architecture (JCA) API Provider for Securosys Hardware Security Modules.
keywords: [hsm, cloud hsm]
---

# Release Notes - JCE/JCA API Provider

v2.4.3 (2024-09-18)
===================

Bug fixes:
- Cipher.getUpdateOutputSize.
- SKA/SLIP-10 vs. Cardano derivation.

New features:
- ed448/x448.
- Ed Ph.
- Ed SLIP-10 import and seed import.
- Dilithium ML-DSA IPD, Kyber ML-KEM IPD.
- LMS, XMSS.
- New PQC OIDs (ML-DSA, ML-KEM, SHL-DSA).

v2.4.2 (2024-05-07)
===================

Bug fixes:
- AES/GCM wrap/unwrap and single-encrypt/decrypt.

New features:
- Prehashing with deterministic signatures.
- Timestamp prehashing support.
- Parallel clustering.
- SLIP-10.
- Deterministic Keccak signatures.
- KdfSha256/etc.
- CkdGenParameterSpec for PublicKeys.
- PrimusMacSpi verify.

Known issues:
- SLIP-10/Cardano distinction problems.

v2.4.1 (2024-01-22)
===================

Bug fixes:
- EC seed key import with cryptocurrency.

New features:
- Post quantum cryptography (PQC) algorithms Dilithium, Kyber, SPHINCS+.
- Java 17 Ed key support.
- Key rollover.
- Rewrap.
- Extended device info.

v2.3.8 (2023-08-31)
===================

Bug fixes:
- KeyPair import without PublicKey.

New features:
- Polymorphic key unwrap.
- Ed CKD unwrap.
- PSS non-standard salt lengths.
- Signed log fetching.
- AuthorizationCallback.
- Automatic large blocks feature support.
- Deterministic ECDSA and DSA signatures.

Known issues:
- p11 password unblinding attempt in rare cases problematic.

v2.3.7 (2023-07-26)
===================

Bug fixes:
- BYOK functions: fix a cleanup/delete issue with external session keys.

New features:
- Skip whitespaces in PEMs.
- Stream interface for log fetching.
- Mac getIV.
- PBKDF2_SHA3256/etc.
- EC CKD unwrap.

Comments:
- More support for implicit GCM IV.
- Login exception hints specifically on empty and null passwords.

Known issues:
- p11 password unblinding attempt in rare cases problematic.


v2.3.6 (2023-04-26)
===================

Bug fixes:
- Get/set flags on external public keys.
- Fix DDEA key import.
- Support implicit GCM IV.

New features:
- getAuthorization(String) is now thread-local.
- Support for flags with external DataObjects.
- Consider Key split alignment restrictions.
- Prehash all signdata.
- Support p11 provider blindings. (Sample available at: LoginCustomBlindingFileSample.java)
- Avoid enumerating in DerivedKeyStoreSpi.
- ed25519 private key getEncodedShort.
- OID aliases.
- Low level connect interruptor.

Comments:
- More support for external session keys, e.g. CKD.
- Shorter client id hash.
- RSA default key size is now 3072.
- Exception hint about violating sane packet size.

v2.3.4 (2022-11-11)
===================

Bug fixes:
- Fixes for external session keys.

v2.3.3 (2022-10-31)
===================

New features:
- 32 bytes Ed private key import.
- socks5 IPv6 support.
- Support for new keytool code kt.jar.

Comments:
- External key persisting requires now a license.

v2.3.2 (2022-07-15)
===================

Bug fixes:
- Empty SKA / CKD / derived-sign.
- keytool problem with Java 11.

New features:
- x25519 EdDH alias.

v2.3.1 (2022-06-17)
===================

New features:
- Some certificate persisting interoperability with PKCS#11/CNG.
- Allow larger amounts of random bits at a time.
- Leaner IES file formats.
- Additional IES APIs.

Comments:
- JCE clients version 2.0.4 and older will not be able to read certificate chains written with this version.
- Default mode with IES is XML now.

v2.2.7 (2022-04-08)
===================

New features:
- IES interoperability with p11.
- Preserve key fields when renaming certificates.

v2.2.6 (2022-02-28)
===================

New features:
- Unwrap to SKA key.

v2.2.5 (2022-02-24)
===================

Bug fixes:
- Key attributes were not properly set on Ed key import.

New features:
- Ed key unwrap.

v2.2.4 (2022-02-11)
===================

New features:
- Support BLS X509EncodedKeySpec.
- Support unwrap with SKA keys.
- Support custom password blinding key file.
- Support ephemeral/session keys as external keys.
- Support secp256k1 with Java 17.
- Support socks5 no-auth mode.

v2.2.3 (2021-11-08)
===================

Bug fixes:
- Fix a condition where Java TLS would run in a NPE.
- Fix TlsPremasterSecret size for TLS, for curves of larger size than 256 bits.

New features:
- presetClientId parameter.
- Support RSA OAEP Psource with data load.
- EC custom parameters encoding.
- getDataObjectAccessFlags/setDataObjectAccessFlag.
- Fix cardano user flag.

v2.2.1 (2021-07-14)
===================

Bug fixes:
- Fix ECDH secret length for secp224k1.

New features:
- ECIES, ECIES streaming, ECIES file cryption, GCM IES.
- Support unspecified DER length encoding.
- BLS unwrap.

v2.1.8 (2021-06-11)
===================

Bug fixes:
- Fix CKD public key export in the hardened non-SKA case.

v2.1.7 (2021-05-12)
===================

Bug fixes:
- Fix key import EC Seed with SKA.

v2.1.6 (2021-05-11)
===================

New features:
- Key import with SKA.

v2.1.5 (2021-04-29)
===================

Bug fixes:
- Fix ed25519sha3 export/import.

New features:
- SHA3withRSA(/PSS).
- Additional signature mappings, such as SHA256/RSA, SHA256withRSASSA-PSS.
- Low-S [EC]DSA signature normalization, e.g. for ETH.

v2.1.4 (2021-03-24)
===================

New features:
- CKD and NOPUB.
- Long ping backoff, lessens network traffic in HSM offline mode.

v2.1.3 (2021-03-19)
===================

Bug fixes:
- Convenience certs for EC/ISS/BLS.

New features:
- ED CKD.

v2.1.2 (2021-02-05)
===================

Bug fixes:
- Adaptations for TDEA/ECB wrap, 2DES split wrap import.

New features:
- DDEA support, more wrap modes.

v2.1.1 (2021-01-05)
===================

New features:
- TDEA-[Un]WrapPad.

v2.0.7 (2020-12-11)
===================

Bug fixes:
- Log fetch rollover problem.

New features:
- Include parameter OID in BLS public key encoding.
- User counter.

v2.0.6 (2020-11-24)
===================

New features:
- EC and AES support for BYOK.

v2.0.5 (2020-11-02)
===================

Bug fixes:
- Crypto currency address extraction in non-SKA case.

v2.0.4 (2020-09-18)
===================

New features:
- BLS keys and signatures.

v2.0.3 (2020-09-09)
===================

Bug fixes:
- Fix status InvalidSize on getUserSecretChars.

v2.0.2 (2020-08-26)
===================

Bug fixes:
- Fix a bug in generateKeyParamsAndPairDh.

New features:
- External key storage.
- Weighted cluster list.
- Primus Proxy credentials per thread.
- Ephemeral object refresh.
- RKS/EC support.
- Seconds resolution with PrimusAccessToken.
- RFC timestamp API.

