---
title: PKCS#11 - Specifications
sidebar_label: Specifications
---

# Specifications
## PKCS#11 Version

| Library                        | PKCS#11 Version |
|--------------------------------|-----------------|
| lipprimusP11.so, primusP11.dll | 3.0             |


## Supported Mechanisms

| Mechanism                        | Key Size Min | Key Size Max | Flags                                                                                                                                            |
|----------------------------------|--------------|--------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| CKM_RSA_PKCS                        | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY \| CKF_WRAP \| CKF_UNWRAP    |
| CKM_RSA_PKCS_KEY_PAIR_GEN           | 1024 | 8192 | CKF_HW \| CKF_GENERATE_KEY_PAIR                                                                                                                                                     |
| CKM_RSA_PKCS_OAEP                   | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT \| CKF_WRAP \| CKF_UNWRAP                                                                        |
| CKM_RSA_PKCS_PSS                    | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                              |
| CKM_RSA_X_509                       | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY \| CKF_WRAP \| CKF_UNWRAP    |
| CKM_MD5_RSA_PKCS                    | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_RIPEMD160_RSA_PKCS              | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA1_RSA_PKCS                   | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA1_RSA_PKCS_PSS               | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA224_RSA_PKCS                 | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA224_RSA_PKCS_PSS             | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA256_RSA_PKCS                 | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA256_RSA_PKCS_PSS             | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA384_RSA_PKCS                 | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA384_RSA_PKCS_PSS             | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA512_RSA_PKCS                 | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA512_RSA_PKCS_PSS             | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA3_224_RSA_PKCS               | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA3_224_RSA_PKCS_PSS           | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA3_256_RSA_PKCS               | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA3_256_RSA_PKCS_PSS           | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA3_384_RSA_PKCS               | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA3_384_RSA_PKCS_PSS           | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA3_512_RSA_PKCS               | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA3_512_RSA_PKCS_PSS           | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_DSA_KEY_PAIR_GEN                | 1024 | 8192 | CKF_HW \| CKF_GENERATE_KEY_PAIR                                                                                                                                                     |
| CKM_DSA_PARAMETER_GEN               | 1024 | 3072 | CKF_HW \| CKF_GENERATE                                                                                                                                                              |
| CKM_DSA_SHA1                        | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_DSA_SHA224                      | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_DSA_SHA256                      | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_DSA_SHA384                      | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_DSA_SHA512                      | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_DSA_SHA3_224                    | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_DSA_SHA3_256                    | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_DSA_SHA3_384                    | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_DSA_SHA3_512                    | 1024 | 8192 | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_EC_KEY_PAIR_GEN                 | 224  | 521  | CKF_HW \| CKF_GENERATE_KEY_PAIR \| CKF_EC_F_P \| CKF_EC_ECPARAMETERS \| CKF_EC_NAMEDCURVE \| CKF_EC_UNCOMPRESS \| CKF_EC_COMPRESS                                                   |
| CKM_ECDSA                           | 224  | 521  | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_ECDSA_SHA1                      | 224  | 521  | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_ECDSA_SHA224                    | 224  | 521  | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_ECDSA_SHA256                    | 224  | 521  | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_ECDSA_SHA384                    | 224  | 521  | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_ECDSA_SHA512                    | 224  | 521  | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_ECDSA_SHA3_224                  | 224  | 521  | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_ECDSA_SHA3_256                  | 224  | 521  | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_ECDSA_SHA3_384                  | 224  | 521  | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_ECDSA_SHA3_512                  | 224  | 521  | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_EC_EDWARDS_KEY_PAIR_GEN         | 256  | 448  | CKF_HW \| CKF_GENERATE_KEY_PAIR                                                                                                                                                     |
| CKM_EDDSA                           | 256  | 448  | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_DH_PKCS_KEY_PAIR_GEN            | 1024 | 8192 | CKF_HW \| CKF_GENERATE_KEY_PAIR                                                                                                                                                     |
| CKM_DH_PKCS_PARAMETER_GEN           | 1024 | 1024 | CKF_HW \| CKF_GENERATE                                                                                                                                                              |
| CKM_DH_PKCS_DERIVE                  | 1024 | 8192 | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_X9_42_DH_KEY_PAIR_GEN           | 1024 | 8192 | CKF_HW \| CKF_GENERATE_KEY_PAIR                                                                                                                                                     |
| CKM_X9_42_DH_PARAMETER_GEN          | 1024 | 3072 | CKF_HW \| CKF_GENERATE                                                                                                                                                              |
| CKM_X9_42_DH_DERIVE                 | 1024 | 8192 | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_ECDH1_DERIVE                    | 224  | 521  | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_AES_KEY_GEN                     | 16   | 32   | CKF_HW \| CKF_GENERATE                                                                                                                                                              |
| CKM_AES_ECB                         | 16   | 32   | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT \| CKF_WRAP \| CKF_UNWRAP                                                                        |
| CKM_AES_CBC                         | 16   | 32   | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT \| CKF_WRAP \| CKF_UNWRAP                                                                        |
| CKM_AES_CBC_PAD                     | 16   | 32   | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT \| CKF_WRAP \| CKF_UNWRAP                                                                        |
| CKM_AES_GCM                         | 16   | 32   | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT                                                                                                  |
| CKM_AES_CTR                         | 16   | 32   | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT                                                                                                  |
| CKM_AES_MAC                         | 16   | 32   | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_AES_CMAC                        | 16   | 32   | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_AES_GMAC                        | 16   | 32   | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_AES_KEY_WRAP                    | 16   | 32   | CKF_HW \| CKF_WRAP \| CKF_UNWRAP                                                                                                                                                    |
| CKM_AES_KEY_WRAP_PAD                | 16   | 32   | CKF_HW \| CKF_WRAP \| CKF_UNWRAP                                                                                                                                                    |
| CKM_AES_ECB_ENCRYPT_DATA            | 16   | 32   | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_AES_CBC_ENCRYPT_DATA            | 16   | 32   | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_CAMELLIA_KEY_GEN                | 16   | 32   | CKF_HW \| CKF_GENERATE                                                                                                                                                              |
| CKM_CAMELLIA_ECB                    | 16   | 32   | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT                                                                                                  |
| CKM_CAMELLIA_CBC                    | 16   | 32   | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT                                                                                                  |
| CKM_CAMELLIA_CBC_PAD                | 16   | 32   | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT                                                                                                  |
| CKM_CAMELLIA_MAC                    | 16   | 32   | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_DES2_KEY_GEN                    | 16   | 16   | CKF_HW \| CKF_GENERATE                                                                                                                                                              |
| CKM_DES3_KEY_GEN                    | 24   | 24   | CKF_HW \| CKF_GENERATE                                                                                                                                                              |
| CKM_DES3_ECB                        | 16   | 24   | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT \| CKF_WRAP \| CKF_UNWRAP                                                                        |
| CKM_DES3_CBC                        | 16   | 24   | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT \| CKF_WRAP \| CKF_UNWRAP                                                                        |
| CKM_DES3_CBC_PAD                    | 16   | 24   | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT \| CKF_WRAP \| CKF_UNWRAP                                                                        |
| CKM_DES3_CMAC                       | 16   | 24   | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_DES3_ECB_ENCRYPT_DATA           | 16   | 24   | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_DES3_CBC_ENCRYPT_DATA           | 16   | 24   | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_MD5                             | 0    | 0    | CKF_HW \| CKF_DIGEST                                                                                                                                                                |
| CKM_RIPEMD160                       | 0    | 0    | CKF_HW \| CKF_DIGEST                                                                                                                                                                |
| CKM_SHA_1                           | 0    | 0    | CKF_HW \| CKF_DIGEST                                                                                                                                                                |
| CKM_SHA224                          | 0    | 0    | CKF_HW \| CKF_DIGEST                                                                                                                                                                |
| CKM_SHA256                          | 0    | 0    | CKF_HW \| CKF_DIGEST                                                                                                                                                                |
| CKM_SHA384                          | 0    | 0    | CKF_HW \| CKF_DIGEST                                                                                                                                                                |
| CKM_SHA512                          | 0    | 0    | CKF_HW \| CKF_DIGEST                                                                                                                                                                |
| CKM_SHA3_224                        | 0    | 0    | CKF_HW \| CKF_DIGEST                                                                                                                                                                |
| CKM_SHA3_256                        | 0    | 0    | CKF_HW \| CKF_DIGEST                                                                                                                                                                |
| CKM_SHA3_384                        | 0    | 0    | CKF_HW \| CKF_DIGEST                                                                                                                                                                |
| CKM_SHA3_512                        | 0    | 0    | CKF_HW \| CKF_DIGEST                                                                                                                                                                |
| CKM_MD5_HMAC                        | 0    | 0    | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFYs \| CKF_SIGN \| CKF_VERIFY                                                                                                         |
| CKM_RIPEMD160_HMAC                  | 0    | 0    | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA_1_HMAC                      | 0    | 0    | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA224_HMAC                     | 0    | 0    | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA256_HMAC                     | 0    | 0    | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA384_HMAC                     | 0    | 0    | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA512_HMAC                     | 0    | 0    | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA3_224_HMAC                   | 0    | 0    | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA3_256_HMAC                   | 0    | 0    | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA3_384_HMAC                   | 0    | 0    | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_SHA3_512_HMAC                   | 0    | 0    | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_CHACHA20                        | 32   | 32   | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT                                                                                                  |
| CKM_CHACHA20_KEY_GEN                | 32   | 32   | CKF_HW \| CKF_GENERATE                                                                                                                                                              |
| CKM_POLY1305                        | 32   | 32   | CKF_HW \| CKF_MESSAGE_SIGN \| CKF_MESSAGE_VERIFY \| CKF_SIGN \| CKF_VERIFY                                                                                                          |
| CKM_POLY1305_KEY_GEN                | 32   | 32   | CKF_HW \| CKF_GENERATE                                                                                                                                                              |
| CKM_CHACHA20_POLY1305               | 32   | 32   | CKF_HW \| CKF_MESSAGE_ENCRYPT \| CKF_MESSAGE_DECRYPT \| CKF_ENCRYPT \| CKF_DECRYPT                                                                                                  |
| CKM_GENERIC_SECRET_KEY_GEN          | 16   | 8192 | CKF_HW \| CKF_GENERATE                                                                                                                                                              |
| CKM_KEY_SPLIT                       | 0    | 0    | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_SHA1_KEY_DERIVATION             | 0    | 0    | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_SHA224_KEY_DERIVATION           | 0    | 0    | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_SHA256_KEY_DERIVATION           | 0    | 0    | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_SHA384_KEY_DERIVATION           | 0    | 0    | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_SHA512_KEY_DERIVATION           | 0    | 0    | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_SP800_108_COUNTER_KDF           | 16   | 4096 | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_SP800_108_FEEDBACK_KDF          | 16   | 4096 | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_SP800_108_DOUBLE_PIPELINE_KDF   | 16   | 4096 | CKF_HW \| CKF_DERIVE                                                                                                                                                                |
| CKM_PKCS5_PBKD2                     | 0    | 0    | CKF_HW \| CKF_GENERATE                                                                                                                                                              |



## Supported ECC Curves

| OID name                          | OID hex-value                                                 | OID                     |
|-----------------------------------|---------------------------------------------------------------|-------------------------|
| secp224k1                         | \{0x06,0x05,0x2B,0x81,0x04,0x00,0x20\}                          | 1.3.132.0.32            |
| secp224r1                         | \{0x06,0x05,0x2B,0x81,0x04,0x00,0x21\}                          | 1.3.132.0.33            |
| secp256k1                         | \{0x06,0x05,0x2B,0x81,0x04,0x00,0x0A\}                          | 1.3.132.0.10            |
| secp256r1, prime256v1, NIST P-256 | \{0x06,0x08,0x2A,0x86,0x48,0xCE,0x3D,0x03,0x01,0x07\}           | 1.2.840.10045.3.1.7     |
| secp384r1, NIST P-384             | \{0x06,0x05,0x2B,0x81,0x04,0x00,0x22\}                          | 1.3.132.0.34            |
| secp521r1, NIST P-521             | \{0x06,0x05,0x2B,0x81,0x04,0x00,0x23\}                          | 1.3.132.0.35            |
| x962\_p239v1                      | \{0x06,0x08,0x2A,0x86,0x48,0xCE,0x3D,0x03,0x01,0x04\}           | 1.2.840.10045.3.1.4     |
| x962\_p239v2                      | \{0x06,0x08,0x2A,0x86,0x48,0xCE,0x3D,0x03,0x01,0x05\}           | 1.2.840.10045.3.1.5     |
| x962\_p239v3                      | \{0x06,0x08,0x2A,0x86,0x48,0xCE,0x3D,0x03,0x01,0x06\}           | 1.2.840.10045.3.1.6     |
| brainpool224r1                    | \{0x06,0x09,0x2B,0x24,0x03,0x03,0x02,0x08,0x01,0x01,0x05\}      | 1.3.36.3.3.2.8.1.1.5    |
| brainpool256r1                    | \{0x06,0x09,0x2B,0x24,0x03,0x03,0x02,0x08,0x01,0x01,0x07\}      | 1.3.36.3.3.2.8.1.1.7    |
| brainpool320r1                    | \{0x06,0x09,0x2B,0x24,0x03,0x03,0x02,0x08,0x01,0x01,0x09\}      | 1.3.36.3.3.2.8.1.1.9    |
| brainpool384r1                    | \{0x06,0x09,0x2B,0x24,0x03,0x03,0x02,0x08,0x01,0x01,0x0b\}      | 1.3.36.3.3.2.8.1.1.11   |
| brainpool512r1                    | \{0x06,0x09,0x2B,0x24,0x03,0x03,0x02,0x08,0x01,0x01,0x0d\}      | 1.3.36.3.3.2.8.1.1.13   |
| frp256v1                          | \{0x06,0x0A,0x2A,0x81,0x7A,0x01,0x81,0x5F,0x65,0x82,0x00,0x01\} | 1.2.250.1.223.101.256.1 |


## EC Edwards Parameters

| OID name     | OID hex-value                                                      | OID                     |
|--------------|--------------------------------------------------------------------|-------------------------|
| Ed25519/SHA2 | \{0x06,0x03,0x2B,0x65,0x70\}                                         | 1.3.101.112             |
| Ed448        | \{0x06,0x03,0x2B,0x65,0x71\}                                         | 1.3.101.113             |
| Curve25519   | \{0x06,0x03,0x2B,0x65,0x6E\}                                         | 1.3.101.110             |
| Curve 448    | \{0x06,0x03,0x2B,0x65,0x6F\}                                         | 1.3.101.111             |
| Ed25519/SHA3 | \{0x06,0x0B,0x2B,0x06,0x01,0x04,0x01,0x82,0xDC,0x7C,0x05,0x02,0x01\} | 1.3.6.1.4.1.44668.5.2.1 |


## Firmware Requirements

The following table shows HSM firmware requirements for some
mechanisms and features:

| Firmware    | required for Feature or Mechanism |
|-------------|-----------------------------------|
| latest v2.7 | AES Wrap (CBC/ECB), DSA/DH/DHx942 (export PRIME, SUBPRIME, BASE), Log Export, CKA\_NEVER\_EXTRACTABLE, CKA\_ALWAYS\_SENSITIVE, CKA\_LOCAL, CKA\_TRUSTED, CKA\_WRAP\_WITH\_TRUSTED, CKA\_VERIFY\_RECOVER,  CKA\_SIGN\_RECOVER, retrieve CKA\_EC\_PARAMS the same way as sent to  HSM, CKM\_KEY\_SPLIT |
| latest v2.8 | Session objects, Ed25519, ChaCha/Poly, C_CopyObject |
| latest v2.9 | DES2, DES2/3-Keywrap |
| latest v3.1 | Ed448, Curve448, CK_EDDSA_PARAMS |
| latest v3.2 | CKM_DES3/AES_ECB/CBC_ENCRYPT_DATA |

## Object Label Handling

The provider removes NUL-termination (\\0) of labels before writing to
the HSM (not permitted).

Creating multiple objects using the same object label, the provider
will automatically add or remove an HSM internal label differentiator
("label"@?!\<uid\>) as the HSM does not directly support duplicate
labels. These label markers are visible when using an older or other
provider (e.g. JCE).


## Key Usage Flags
CKA_SIGN, CKA_VERIFY, CKA_ENCRYPT, CKA_DECRYPT, CKA_WRAP, CKA_UNWRAP, CKA_DERIVE default to CK_FALSE, except if none are specified (then HSM defaults applied).

Primus PKCS#11 provider versions < v2.1.3 default the above key usage flags to CK_TRUE. However some applications provide only command options to enable specific key usage but not to disable it, resulting in too many key usage flags set when creating a key.

