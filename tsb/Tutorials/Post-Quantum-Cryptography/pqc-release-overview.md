---
sidebar_position: 1
title: Post-Quantum Cryptography (PQC) Release Overview
sidebar_label: Compatibility & Release
description: Overview of Post-Quantum Cryptography and its Smart Key Attributes support by Securosys.
keywords: [Post-Quantum Cryptography, PQC-SKA, Securosys, Smart Key Attributes, NIST, Round 3, CRYSTALYS-KYBER, CRYTALS_DILITHIUM, SPHINCS+, Commercial National Security Algorithm Suite, NSA Suite B Cryptography, key management, cloud HSM, HSM key management, HSM cloud, HSM as a service, cloud based HSM, HSM digital signature, HSM services, HSM service, HSM, hardware security module]
---

# Release and Compatibility Overview

Encryption uses math to protect sensitive electronic information, including secure websites and emails. Widely used public-key encryption systems, which rely on math problems that computers find intractable, ensure that these websites and messages are inaccessible to unwelcome third parties. Before making the selections, NIST considered not only the security of the algorithms’ underlying math, but also the best applications for them. 

The new standards are designed for two essential tasks for which encryption is typically used: general encryption, used to protect information exchanged across a public network; and digital signatures, used for identity authentication. NIST announced its selection of four algorithms — CRYSTALS-Kyber, CRYSTALS-Dilithium, Sphincs+ and FALCON — slated for standardization in 2022 and released draft versions of three of these standards in 2023. The fourth draft standard based on FALCON is planned for late 2024.  

While there have been no substantive changes made to the standards since the draft versions, NIST has changed the algorithms’ names to specify the versions that appear in the three finalized standards, which are: 

- [Federal Information Processing Standard (FIPS) 203](https://csrc.nist.gov/pubs/fips/203/final), intended as the primary standard for general encryption. Among its advantages are comparatively small encryption keys that two parties can exchange easily, as well as its speed of operation. The standard is based on the CRYSTALS-Kyber algorithm, which has been renamed **ML-KEM**, short for _Module-Lattice-Based Key-Encapsulation Mechanism_.
- [FIPS 204](https://csrc.nist.gov/pubs/fips/204/final), intended as the primary standard for protecting digital signatures. The standard uses the CRYSTALS-Dilithium algorithm, which has been renamed **ML-DSA**, short for _Module-Lattice-Based Digital Signature Algorithm_.
- [FIPS 205](https://csrc.nist.gov/pubs/fips/205/final), also designed for digital signatures. The standard employs the Sphincs+ algorithm, which has been renamed **SLH-DSA**, short for _Stateless Hash-Based Digital Signature Algorithm_. The standard is based on a different math approach than ML-DSA, and it is intended as a **backup method** in case ML-DSA proves vulnerable.


Similarly, when the draft FIPS 206 standard built around FALCON is released, the algorithm will be dubbed FN-DSA, short for FFT (fast-Fourier transform) over NTRU-Lattice-Based Digital Signature Algorithm. 


|Standard|Algorithm|HSM Version|Rest-API Version|Description|
|--|--|--|--|--|
|[FIPS 203](https://csrc.nist.gov/pubs/fips/203/final)|**ML-KEM-512**, **ML-KEM-768**, and **ML-KEM-1024**|v3.1+|v2.2+|Module-Lattice-Based Key-Encapsulation Mechanism Standard (Kyber)|
|[FIPS 204](https://csrc.nist.gov/pubs/fips/204/final)|**ML-DSA-44**, **ML-DSA-65**, and **ML-DSA-87**|v3.1+|v2.2+|Module-Lattice-Based Digital Signature Standard (Crystals-Dilithium)|
|[FIPS 205](https://csrc.nist.gov/pubs/fips/205/final)|**ML-KEM-512**, **ML-KEM-768**, and **ML-KEM-1024**|v3.1+|v2.2+|Stateless Hash-Based Digital Signature Standard )Sphincs+)|

---
---

## Obsolete (Draft) PQC-Algorithms

:::warning
 The below algorithms are obsoleted since August 13, 2024 by the final FIPS approved algorithms.
:::

|Algorithm|HSM Version|Rest-API Version|Description|
|--|--|--|--|
|[Draft FIPS 203](https://csrc.nist.gov/pubs/fips/203/ipd)|v3.0.8|v2.1.0|Module-Lattice-Based Key-Encapsulation Mechanism Standard (Kyber)|
|[Draft FIPS 204](https://csrc.nist.gov/pubs/fips/204/ipd)|v3.0.8|v2.1.0|Module-Lattice-Based Digital Signature Standard (Crystals-Dilithium)|
|[Draft FIPS 205](https://csrc.nist.gov/pubs/fips/205/ipd)|v3.0.8|v2.1.0|Stateless Hash-Based Digital Signature Standard (Sphincs+)|

:::note PQC-Algorithms

- **Crystals-Dilithium**: DILITHIUM_L2, DILITHIUM_L3, DILITHIUM_L5
- **Crystals-Kyber**: KYBER512_WITH_SHAKE, KYBER768_WITH_SHAKE, KYBER1024_WITH_SHAKE, KYBER512_WITH_SHA2_AES, KYBER768_WITH_SHA2_AES, KYBER1024_WITH_SHA2_AES
- **Sphincs+**: SPHINCS_PLUS_SHAKE_L1, SPHINCS_PLUS_SHAKE_L3, SPHINCS_PLUS_SHAKE_L5

:::

---
---

## History

**August 13, 2024**

The Secretary of Commerce approved three Federal Information Processing Standards (FIPS) for post-quantum cryptography:

* FIPS 203, Module-Lattice-Based Key-Encapsulation Mechanism Standard
* FIPS 204, Module-Lattice-Based Digital Signature Standard
* FIPS 205, Stateless Hash-Based Digital Signature Standard


**August 24, 2023**

Comments Requested on Three Draft FIPS for Post-Quantum Cryptography

* Draft FIPS 203, Module-Lattice-Based Key-Encapsulation Mechanism Standard
* Draft FIPS 204, Module-Lattice-Based Digital Signature Standard
* Draft FIPS 205, Stateless Hash-Based Digital Signature Standard

**December 20, 2016**

Request for Nominations for Public-Key Post-Quantum Cryptographic Algorithms

**August 2, 2016**

Request for Comments on Submission Requirements and Evaluation Criteria