---
sidebar_position: 5
title: CloudHSM - List of algorithms & functions
sidebar_label: Algorithms and Functions
description: Explore CloudHSM supported algorithms and functions as of April 2024, including AES with various modes and key sizes, AES-CMAC, AES-GCM, and AES-KW.
keywords: [cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, what is cloud hsm, hsm signing, hsm pki, hsm encryption, code signing hsm, hsm key, code signing service, hsm code signing, cloud code signing, cloud encryption key management, cloud hardware security module, cloudhsm vs kms, code signing certificate, key management hsm, microsoft encryption key management, hsm aws, document signing services, code signing, hsm providers, code signing as a service, aws cloudhsm documentation, hsm pricing]
---

# Supported Algorithms & Functions

**Primus HSM** & **CloudHSM** support a wide variety of cryptographic algorithms and functions essential for secure data management. This list includes fundamental standards like AES and RSA, as well as advanced techniques such as ChaCha20–Poly1305 and BLS12-381.

Some algorithms are enabled by default for basic security needs, while others are optional and can be activated based on specific application requirements and API configurations.

*List valid as of July 2024.*

## Standard

All the following elements are enabled by default for all the HSM & CloudHSM services.

### Asymmetric

| Algorithm | Description |
| --- | --- |
| DSA | [FIPS 186-4] <br/> Functions: PQG Generation, Key Pair Generation, Signature Generation, Signature Verification <br/> Key sizes: 2048, 3072 bits |
| ECC operations with non-NIST curves. | [FIPS IG A.2] <br/> Elliptic Curve operations with non-NIST curves, as follows: <br/> <table> <tr> <td>**Curve:**</td> <td>**Security Strength:**</td> </tr> <tr> <td>Brainpool 224r1, 256r1, 320r1, 384r1, 512r1</td> <td>112, 128, 160, 192, 256</td> </tr> <tr> <td>Frp 256v1</td> <td>128</td> </tr> <tr> <td>X9.62p239v1, v2, v3</td> <td>119</td> </tr> <tr> <td>secp224k1, 256k1</td> <td>112, 128</td> </tr> </table> |
| ECDSA | [FIPS 186-4] <br/> Functions: Key Pair Generation, Signature Generation, Signature Verification, Public Key Validation <br/> Curves/Key sizes: P-224, P-256, P-384, P-521 (Strength: 112, 128, 192, 260) |
| ECDSA SigGen Component | [FIPS 186-4] <br/> Curves/Key sizes: P-224, P-256, P-384, P-521 |
| KAS (FFC, ECC) | [SP 800-56Ar1] <br/> Parameter sets/Key sizes: FC, EB, EC, ED, EE <br/> Modes: dhStatic responder, Static Unified responder <br/> Scheme: SHA2 <br/> Note: Key establishment methodology provides between 112 and 256 bits of encryption strength |
| KAS Component | [SP 800-56A Section 5.7.1.2 ECC CDH Primitive] <br/> Parameter sets/Key sizes: EB, EC, ED, EE |
| KTS (RSA) | [SP 800-56B] <br/> Functions: Key Wrap, Key Unwrap <br/> Key sizes: 2048, 3072, 4096 bits <br/> Key \{Agreement \| Transport\} – Provides 112 to 150 bits of encryption strength. <br/> Wrap Methods: RSASVE, RSA-OAEP |
| RSA | [FIPS 186-4, ANSI X9.31-1998, and PKCS #1 v2.1 (PSS and PKCS1.5)] <br/> Functions: Key Pair Generation, Signature Generation, Signature Verification, Key Wrap, Key Unwrap, Encrypt, Decrypt <br/> Key sizes: 512, 1024 (non-FIPS mode only) <br/> Key sizes: 2048, 3072, 4096, 7680, 8192 bits <br/> Some RSA-4096 functions are listed here but not displayed on RSA Cert. #2946. These are vendor-affirmed, as CAVP does not provide testing for these functions. |
| RSA DP | [SP 800-56B] <br/> Key sizes: 2048 bits |
| RSA SP | [FIPS 186-4, ANSI X9.31-1998, and PKCS #1 v2.1 (PSS and PKCS1.5)] <br/> Key sizes: 2048 bits |
| DH | PKCS3 <br/> Function: Key agreement, superseded by KAS (FFC) |
| EcDH | [RFC7748] <br/> Function: Elliptic Curve Diffie-Hellman (general, may use P-256, P-384, etc.) | 
| EdDH | [RFC8031] <br/> Function: Edwards-curve Diffie-Hellman using X25519 (Curve25519) |
| EdDSA | [RFC8032] <br/> Function: EC digital signature algorithm using Edwards curve (ED25519) |


### Symmetric

| Algorithm | Description |
| --- | --- |
| AES | [FIPS 197, SP 800-38A] <br/> Functions: Encryption, Decryption; Modes: ECB, CBC, CTR <br/> Key sizes: 128, 192, 256 bits |
| AES-CMAC | [SP 800-38B] <br/> Functions: MAC Generation, MAC Verification <br/> Key sizes: 128, 192, 256 bits |
| AES-GCM | [FIPS 197, SP 800-38D] <br/> Functions: Authenticated Encryption, Authenticated Decryption, GMAC Generation, GMAC Verification <br/> Key sizes: 128, 192, 256 bits |
| AES-KW | [SP 800-38F] <br/> Functions: Key Wrap, Key Unwrap <br/> Key sizes: 128, 192, 256 bits |
| Camellia | [Technical specifications](https://info.isl.ntt.co.jp/crypt/eng/camellia/dl/01espec.pdf) <br/> Function: Encryption, Decryption <br/> Key sizes: 128, 192, 256 bits |
| ChaCha | [Technical specifications](https://cr.yp.to/chacha/chacha-20080128.pdf) <br/> Function: Stream cipher |
| ChaCha20-Poly1305 | [RFC 7905] <br/> Function: Authenticated Encryption, Authenticated Decryption |
| Poly1305 | [Technical specifications](https://cr.yp.to/mac/poly1305-20050329.pdf) <br/> Function: Message Authentication Code |
| KTS (Symmetric) | [SP800-38F] <br/> Functions: Key Wrap, Key Unwrap <br/> Variants: <br/> 38D: AES-GCM (256 bits) <br/> 38F: AES-KW, AES-KWP <br/> Key Transport – Provides between 128 and 256 bits of encryption strength. |
| Triple-DES (TDES) | [SP 800-67] <br/> Functions: Encryption, Decryption; Modes: TECB, TCBC <br/> Key sizes: 168 bits (effective 112 bits) |
| Double-DES (DDES) | [SP 800-20] <br/> Functions: Encryption, Decryption; Modes: CBC, ECB <br/> Key sizes: 128 bits (effective 112 bits) |


### Hashes

| Algorithm | Description |
| --- | --- |
| CBC-MAC | FIPS PUB 113 <br/> Function: Message authentication (superseded by AES-CMAC) |
| HMAC | [FIPS 198-1] <br/> Functions: Generation, Verification <br/> SHA sizes: SHA-1, SHA-224, SHA-256, SHA-384, SHA-512, SHA3-224, SHA3-256, SHA3-384, SHA3-512 |
| Keccak 1600 | [FIPS 202] <br/> Function: Hash |
| Kerl | Function: Hash <br/> [Iota.org](https://iota.org) |
| MD5 | [RFC1321] <br/> Function: 128-bit hash |
| RIPEMD160 | ISO/IEC 10118-3:2018 <br/> Function: Hash |
| SHA | [FIPS 180-4, FIPS 202] <br/> Functions: Digital Signature Generation, Digital Signature Verification, component of HMAC and HMAC_DRBG, general hashing <br/> SHA sizes: SHA-1 verification only, <br/> SHA-224, SHA-256, SHA-384, SHA-512, <br/> SHA3-224, SHA3-256, SHA3-384, SHA3-512 |
| SHA-1 | [FIPS 180-4, FIPS 202] <br/> Function: Hash, for other operations than verification |
| SHAKE | [FIPS 202] <br/> Function: Extendable output <br/> Modes: SHAKE-128, SHAKE-256 |

### Key Derivation

| Algorithm | Description |
| --- | --- |
| CKG | [SP800-133] <br/> Asymmetric Key Generation (SP800-133 §6) <br/> Symmetric Key Generation (SP800-133 §7: Direct output from DRBG) |
| DRBG | [SP 800-90A] <br/> HMAC DRBG with internal function SHA-512 <br/> CTR DRBG with internal function AES-256 |
| HKDF | [RFC5869]<br/> Function: Key Derivation <br/> Modes: extract, expand, extract&expand |
| KDF | [SP 800-108] <br/> Modes: Counter, Feedback, Double Pipeline Iteration Mode <br/> PRFs: CMAC(AES-128/192/256), HMAC (SHA-1, 224, 256, 384, 512) |
| KDFs, Password-based | [SP 800-132] <br/> PRFs: HMAC (SHA-1, SHA2 224/256/384/512, SHA3 224/256/384/512) |
| NDRNG | [FIPS IG G.13] <br/> The NDRNG sole purpose is an entropy source for the DRBG built according to SP800-90A. |
| Securosys TRNG | Securosys hardware specification <br/> Function: Non-deterministic random number generation (NDRNG) |
| Securosys RNG | Securosys hardware specification <br/> Function: Performant deterministic random number generation (AES-128) |


## Optional

The following elements might require a specific license to be used on HSM devices & CloudHSM services.

### Blockchain

| Algorithm | Description |
| --- | --- |
| BLS12-381 | RFC draft-irtf-cfrg-bls-signature-04 - draft-irtf-cfrg-bls-signature-02 (ietf.org) <br/> Function: Sign & Verify according with ETH 2.0 |
| Cardano ED key derivation | Function: Authenticated encryption / decryption <br/> [Documentation](https://docs.cardano.org/projects/cardano-wallet/en/latest/About-Address-Derivation.html) |
| ISS | Function: IOTA Signature Scheme <br/> [Iota.org](https://iota.org) |
| SLIP-0010 | Function: Seed import, Key derivation <br/> Curves: SECP256k1, NIST P-256 <br/> [GitHub](https://github.com/satoshilabs/slips/blob/master/slip-0010.md) |

:::tip[Looking for compatible cryptocurrencies?]
[Browse](/tsb/Tutorials/Blockchain/supported-currency) the list of 100 cryptocurrencies, including their symbols, signing algorithms, and curves.
:::

### Post-Quantum

| Algorithm | Description |
| --- | --- |
| CRYSTALS-Kyber | [FIPS 203] (FIPS Round-3 Submission)<br/> Function: Key Pair Generation, Key encapsulation <br/> Modes: KYBER512, KYBER768, KYBER1024 |
| CRYSTALS-Dilithium | [FIPS 204] (FIPS Round-3 Submission)<br/> Function: Key Pair Generation, Signature Generation, Signature Verification <br/> Modes: DILITHIUM_L2, DILITHIUM_L3, DILITHIUM_L5 |
| SPHINCS+ | [FIPS 205] (FIPS Round-3 Submission)<br/> Function: Key Pair Generation, Signature Generation, Signature Verification <br/> Modes: SPHINCS_PLUS_SHAKE_L1, SPHINCS_PLUS_SHAKE_L3, SPHINCS_PLUS_SHAKE_L5 |

#### PQC Algorithms as from Release 3.1 onwards (coming soon)
| Algorithm | Description |
| --- | --- |
| ML-KEM | [FIPS 203] [(Module-Lattice-Based Key-Encapsulation Mechanism Standard (nist.gov))](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.203.pdf) <br/> Function: Key pair generation, key encapsulation, key decapsulation <br/> Modes: ML-KEM-512, ML-KEM-768, ML-KEM-1024 <br/>(formerly CRYSTALS-Kyber)|
| ML-DSA | [FIPS 204] [(Module-Lattice-Based Digital Signature Standard (nist.gov))](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.204.pdf)<br/> Functions: Key pair generation, deterministic signature generation, randomized signature generation, signature verification <br/> Modes: ML-DSA-44, ML-DSA-65, ML-DSA-87 <br/>(formerly CRYSTALS-Dilithium)|
| SLH-DSA | [FIPS 205] [((Stateless Hash-Based Digital Signature Standard (nist.gov))](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.205.pdf)<br/> Functions: Key pair generation, deterministic signature generation, randomized signature generation, signature verification <br/> Modes: SLH-DSA-SHA2-128s, SLH-DSA-SHAKE-128s, SLH-DSA-SHA2-128f, SLH-DSA-SHAKE-128f, SLH-DSA-SHA2-192s, SLH-DSA-SHAKE-192s, SLH-DSA-SHA2-192f, SLH-DSA-SHAKE-192f, SLH-DSA-SHA2-256s, SLH-DSA-SHAKE-256s, SLH-DSA-SHA2-256f, SLH-DSA-SHAKE-256f <br/>(formerly SPHINCS+) |
| HSS-LMS | [NIST SP 800-208] [(Recommendation for Stateful Hash-Based Signature Schemes (nist.gov))](https://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-208.pdf)<br/> Functions: Key pair generation, signature generation, signature verification <br/> Modes: SHA-256, SHA-256(192), SHAKE-256(256), SHAKE-256(192) |
| XMSS | [NIST SP 800-208] [(Recommendation for Stateful Hash-Based Signature Schemes (nist.gov))](https://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-208.pdf)<br/> Functions: Key pair generation, signature generation, signature verification <br/> Modes: XMSS-SHA2_10_256, XMSS-SHA2_16_256, XMSS-SHA2_20_256, XMSS-SHAKE256_10_256, XMSS-SHAKE256_16_256, XMSS-SHAKE256_20_256 |

**Further content:**
- [Compliance & Certifications](./compliance)
- [List of supported cryptocurrencies](/tsb/Tutorials/Blockchain/supported-currency)