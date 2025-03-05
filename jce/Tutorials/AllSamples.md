---
sidebar_position: 4
title: JCE/JCA Samples for Hardware Security Modules
sidebar_label: All Samples
description: Sample Java Cryptography Extension (JCE) and Java Cryptography Architecture (JCA) with Securosys Hardware Security Modules.
keywords: [hsm, cloud hsm]
---

# All JCE/JCA Samples

## Login

#### (On-Premise & DevProgram)
| Filename | Description |
|----------|-------------|
| [UserSecret.java](../ressources/samples/UserSecret.java) | Sample for user secret. |
| [UserSecretSample.java](../ressources/samples/UserSecretSample.java) | Sample for user secret. |

#### Proxy and Provider Services (CloudHSM)

| Filename | Description |
|----------|-------------|
| [ProxyConfigFileSample.java](../ressources/samples/ProxyConfigFileSample.java) | Sample for proxy configuration from a file. |
| [ProxyConfigSample.java](../ressources/samples/ProxyConfigSample.java) | Sample for proxy configuration. |
| [ProxySample.java](../ressources/samples/ProxySample.java) | Sample for using a proxy. |
| [ProviderServicesSample.java](../ressources/samples/ProviderServicesSample.java) | Sample for provider services. |

## Cryptographic Operations Samples

#### AES Encryption and GCM

| Filename | Description |
|----------|-------------|
| [AesGcmSample.java](../ressources/samples/AesGcmSample.java) | Sample for AES-GCM encryption. |
| [AesGcmSampleWithVariableTagLengths.java](../ressources/samples/AesGcmSampleWithVariableTagLengths.java) | Sample for AES-GCM with variable tag lengths. |
| [AesGcmTagSplitSample.java](../ressources/samples/AesGcmTagSplitSample.java) | Sample for splitting AES-GCM tags. |
| [AesGcmTagSplitSample2.java](../ressources/samples/AesGcmTagSplitSample2.java) | Another sample for splitting AES-GCM tags. |
| [AesLocalPerfSample.java](../ressources/samples/AesLocalPerfSample.java) | Sample for local AES performance. |


#### RSA (Rivest–Shamir–Adleman)
| Filename | Description |
|----------|-------------|
| [RsaPssSample.java](../ressources/samples/RsaPssSample.java) | Sample for RSA-PSS. |
| [RsaSample.java](../ressources/samples/RsaSample.java) | Sample for generic RSA operations. |
| [RsaSampleExternal.java](../ressources/samples/RsaSampleExternal.java) | Sample for external RSA operations. |
| [RsaSampleInternal.java](../ressources/samples/RsaSampleInternal.java) | Sample for internal RSA operations. |

#### EC (Elliptic Curve)
| Filename | Description |
|----------|-------------|
| [BIP32_HD_Sample.java](../ressources/samples/BIP32_HD_Sample.java) | Sample for BIP32 hierarchical deterministic keys. |
| [EcCkdDsaSample.java](../ressources/samples/EcCkdDsaSample.java) | Sample for ECCKD DSA. |
| [EcCustomDsaSample.java](../ressources/samples/EcCustomDsaSample.java) | Sample for custom ECDSA. |
| [EcDhSample.java](../ressources/samples/EcDhSample.java) | Sample for elliptic curve Diffie-Hellman. |
| [EcDsaParallel2Sample.java](../ressources/samples/EcDsaParallel2Sample.java) | Sample for parallel ECDSA. |
| [EcDsaParallelSample.java](../ressources/samples/EcDsaParallelSample.java) | Sample for parallel ECDSA. |
| [EcDsaSample.java](../ressources/samples/EcDsaSample.java) | Sample for ECDSA operations. |
| [EcUnwrapSample.java](../ressources/samples/EcUnwrapSample.java) | Sample for EC unwrap. |
| [EcWrapSample.java](../ressources/samples/EcWrapSample.java) | Sample for EC wrap. |

#### ED (EdDSA)
| Filename | Description |
|----------|-------------|
| [EdCkdDsaSample.java](../ressources/samples/EdCkdDsaSample.java) | Sample for EDCKD DSA. |
| [EdCkdSkaDsaSample.java](../ressources/samples/EdCkdSkaDsaSample.java) | Sample for EDCKD SKA DSA. |
| [EdDhSample.java](../ressources/samples/EdDhSample.java) | Sample for EdDSA Diffie-Hellman. |
| [EdDsaSample.java](../ressources/samples/EdDsaSample.java) | Sample for EdDSA operations. |
| [EdExportSample.java](../ressources/samples/EdExportSample.java) | Sample for exporting EdDSA keys. |
| [EdSha3DsaSample.java](../ressources/samples/EdSha3DsaSample.java) | Sample for EdDSA with SHA3 DSA. |
| [EdWrapExportSample.java](../ressources/samples/EdWrapExportSample.java) | Sample for exporting wrapped EdDSA keys. |
| [EdWrapSample.java](../ressources/samples/EdWrapSample.java) | Sample for wrapped EdDSA keys. |
| [EdWrapSample2.java](../ressources/samples/EdWrapSample2.java) | Another sample for wrapped EdDSA keys. |
| [EdWrapSample3.java](../ressources/samples/EdWrapSample3.java) | Yet another sample for wrapped EdDSA keys. |

#### DH (Diffie-Hellman)
| Filename | Description |
|----------|-------------|
| [DhSample.java](../ressources/samples/DhSample.java) | Sample for Diffie-Hellman key exchange. |

#### DSA
| Filename | Description |
|----------|-------------|
| [DsaSample.java](../ressources/samples/DsaSample.java) | Sample for DSA operations. |


## HSM Key Management

#### KeyStore and Enumeration

| Filename | Description |
|----------|-------------|
| [GenerateDirectSample.java](../ressources/samples/GenerateDirectSample.java) | Sample for direct key generation. |
| [GenerateWithRenameSample.java](../ressources/samples/GenerateWithRenameSample.java) | Sample for key generation with renaming. |
| [KeyStoreEnumerationSample.java](../ressources/samples/KeyStoreEnumerationSample.java) | Sample for KeyStore aliases enumeration. |
| [KeyStoreEnumerationSample2.java](../ressources/samples/KeyStoreEnumerationSample2.java) | Another sample for KeyStore aliases enumeration. |
| [KeyToolXSample.java](../ressources/samples/KeyToolXSample.java) | Sample for KeyToolX utility. |
| [ExternalKeySample.java](../ressources/samples/ExternalKeySample.java) | External keystore sample |
| [ExternalKeySample2.java](../ressources/samples/ExternalKeySample.java) | External keystore sample |

#### Key Attributes and Export

| Filename | Description |
|----------|-------------|
| [KeyAttributesSample.java](../ressources/samples/KeyAttributesSample.java) | Sample for key attributes. |
| [KeyAttributesSingleSample.java](../ressources/samples/KeyAttributesSingleSample.java) | Sample for single key attributes. |
| [KeyExportEcCkdSample.java](../ressources/samples/KeyExportEcCkdSample.java) | Sample for EC CKD key export. |
| [KeyExportEcSample.java](../ressources/samples/KeyExportEcSample.java) | Sample for EC key export. |
| [KeyExportEdSample.java](../ressources/samples/KeyExportEdSample.java) | Sample for EdDSA key export. |
| [KeyExportRsaSample.java](../ressources/samples/KeyExportRsaSample.java) | Sample for RSA key export. |
| [KeyFlagsSample.java](../ressources/samples/KeyFlagsSample.java) | Sample for key flags. |

#### Attestation

| Filename | Description |
|----------|-------------|
| [AttestationSample.java](../ressources/samples/AttestationSample.java) | Sample for key attestation. |
| [AttestationSampleAes.java](../ressources/samples/AttestationSampleAes.java) | Sample for attestation with AES. |
| [AttestationSampleEc.java](../ressources/samples/AttestationSampleEc.java) | Sample for attestation with EC. |
| [AttestationSampleEcCkdSka.java](../ressources/samples/AttestationSampleEcCkdSka.java) | Sample for attestation with EC CKD SKA. |
| [AttestationSampleEcId.java](../ressources/samples/AttestationSampleEcId.java) | Sample for attestation with EC ID. |
| [AttestationSampleEcSka.java](../ressources/samples/AttestationSampleEcSka.java) | Sample for attestation with EC SKA. |

#### Key Password and Signature

| Filename | Description |
|----------|-------------|
| [KeyPasswordSample.java](../ressources/samples/KeyPasswordSample.java) | Sample for key password. |
| [KeySignatureAndVerificationSample.java](../ressources/samples/KeySignatureAndVerificationSample.java) | Sample for key signature and verification. |



## Secure Key Exchange and Wrapping

| Filename | Description |
|----------|-------------|
| [RsaDoubleWrapSample.java](../ressources/samples/RsaDoubleWrapSample.java) | Sample for double wrapping RSA. |
| [RsaSampleWrap.java](../ressources/samples/RsaSampleWrap.java) | Sample for wrapping RSA. |
| [RsaSampleWrapAtomic.java](../ressources/samples/RsaSampleWrapAtomic.java) | Sample for atomic RSA wrapping. |


## Smart Key Attributes

#### Authorization and Certificates

| Filename | Description |
|----------|-------------|
| [AuthorizationBlsSample.java](../ressources/samples/AuthorizationBlsSample.java) | Sample for Bls authorization. |
| [AuthorizationCertificatesMixedSample.java](../ressources/samples/AuthorizationCertificatesMixedSample.java) | Sample for mixed certificate authorization. |
| [AuthorizationCertificatesSample.java](../ressources/samples/AuthorizationCertificatesSample.java) | Sample for certificate authorization. |
| [AuthorizationDsaSample.java](../ressources/samples/AuthorizationDsaSample.java) | Sample for DSA authorization. |
| [AuthorizationEcCkdDerivedSignSample.java](../ressources/samples/AuthorizationEcCkdDerivedSignSample.java) | Sample for EC CKD derived signature authorization. |
| [AuthorizationEdSample.java](../ressources/samples/AuthorizationEdSample.java) | Sample for EdDSA authorization. |
| [AuthorizationEmptyTokenEcCkdDerivedSignSample.java](../ressources/samples/AuthorizationEmptyTokenEcCkdDerivedSignSample.java) | Sample for EC CKD derived signature with an empty token. |
| [AuthorizationEmptyTokenSample.java](../ressources/samples/AuthorizationEmptyTokenSample.java) | Sample for authorization with an empty token. |
| [AuthorizationIllustrateTokenAssemblySample.java](../ressources/samples/AuthorizationIllustrateTokenAssemblySample.java) | Sample for illustrating token assembly in authorization. |
| [AuthorizationMultiGroupSample.java](../ressources/samples/AuthorizationMultiGroupSample.java) | Sample for multi-group authorization. |
| [AuthorizationMultiTokenSample.java](../ressources/samples/AuthorizationMultiTokenSample.java) | Sample for multi-token authorization. |
| [AuthorizationNoTimestampSample.java](../ressources/samples/AuthorizationNoTimestampSample.java) | Sample for authorization without timestamp. |
| [AuthorizationNoTokenSample.java](../ressources/samples/AuthorizationNoTokenSample.java) | Sample for authorization without token. |
| [AuthorizationRsaAndEcSample.java](../ressources/samples/AuthorizationRsaAndEcSample.java) | Sample for RSA and EC authorization. |
| [AuthorizationRsaDecryptSample.java](../ressources/samples/AuthorizationRsaDecryptSample.java) | Sample for RSA decryption authorization. |
| [AuthorizationRsaSample.java](../ressources/samples/AuthorizationRsaSample.java) | Sample for RSA authorization. |
| [AuthorizationSample.java](../ressources/samples/AuthorizationSample.java) | Sample for generic authorization. |
| [AuthorizationSampleCurveWithBC.java](../ressources/samples/AuthorizationSampleCurveWithBC.java) | Sample for authorization with specific curves using BC provider. |
| [AuthorizationSampleDifferentCurves.java](../ressources/samples/AuthorizationSampleDifferentCurves.java) | Sample for authorization with different curves. |
| [AuthorizationSampleMultiple.java](../ressources/samples/AuthorizationSampleMultiple.java) | Sample for multiple authorizations. |
| [AuthorizationSerializationSample.java](../ressources/samples/AuthorizationSerializationSample.java) | Sample for authorization serialization. |
| [AuthorizationBlsSample.java](../ressources/samples/AuthorizationBlsSample.java) | Sample for authorization with BLS. |
| [AuthorizationCertificatesMixedSample.java](../ressources/samples/AuthorizationCertificatesMixedSample.java) | Sample for authorization with mixed certificates. |
| [AuthorizationCertificatesSample.java](../ressources/samples/AuthorizationCertificatesSample.java) | Sample for authorization with certificates. |
| [AuthorizationDsaSample.java](../ressources/samples/AuthorizationDsaSample.java) | Sample for authorization with DSA. |
| [AuthorizationEcCkdDerivedSignSample.java](../ressources/samples/AuthorizationEcCkdDerivedSignSample.java) | Sample for authorization with ECCKD-derived signatures. |
| [AuthorizationEdSample.java](../ressources/samples/AuthorizationEdSample.java) | Sample for authorization with EdDSA. |
| [AuthorizationEmptyTokenEcCkdDerivedSignSample.java](../ressources/samples/AuthorizationEmptyTokenEcCkdDerivedSignSample.java) | Sample for authorization with empty token and ECCKD-derived signatures. |
| [AuthorizationEmptyTokenSample.java](../ressources/samples/AuthorizationEmptyTokenSample.java) | Sample for authorization with empty token. |
| [AuthorizationIllustrateTokenAssemblySample.java](../ressources/samples/AuthorizationIllustrateTokenAssemblySample.java) | Sample illustrating token assembly for authorization. |
| [AuthorizationMultiGroupSample.java](../ressources/samples/AuthorizationMultiGroupSample.java) | Sample for authorization with multiple groups. |
| [AuthorizationMultiTokenSample.java](../ressources/samples/AuthorizationMultiTokenSample.java) | Sample for authorization with multiple tokens. |
| [AuthorizationNoTimestampSample.java](../ressources/samples/AuthorizationNoTimestampSample.java) | Sample for authorization without timestamp. |
| [AuthorizationNoTokenSample.java](../ressources/samples/AuthorizationNoTokenSample.java) | Sample for authorization without token. |
| [AuthorizationRsaAndEcSample.java](../ressources/samples/AuthorizationRsaAndEcSample.java) | Sample for authorization with RSA and EC. |
| [AuthorizationRsaDecryptSample.java](../ressources/samples/AuthorizationRsaDecryptSample.java) | Sample for RSA decryption in authorization. |
| [AuthorizationRsaSample.java](../ressources/samples/AuthorizationRsaSample.java) | Sample for authorization with RSA. |
| [AuthorizationSample.java](../ressources/samples/AuthorizationSample.java) | Sample for generic authorization. |
| [AuthorizationSampleCurveWithBC.java](../ressources/samples/AuthorizationSampleCurveWithBC.java) | Sample for authorization with a curve using BC provider. |
| [AuthorizationSampleDifferentCurves.java](../ressources/samples/AuthorizationSampleDifferentCurves.java) | Sample for authorization with different curves. |
| [AuthorizationSampleMultiple.java](../ressources/samples/AuthorizationSampleMultiple.java) | Sample for multiple authorizations. |
| [AuthorizationSerializationSample.java](../ressources/samples/AuthorizationSerializationSample.java) | Sample for serialized authorization. |
| [AuthorizedUnwrapSample.java](../ressources/samples/AuthorizedUnwrapSample.java) | Sample for authorized key unwrapping. |
| [AuthorizedUnwrapSampleExt.java](../ressources/samples/AuthorizedUnwrapSampleExt.java) | Extended sample for authorized key unwrapping. |

#### Approval

| Filename | Description |
|----------|-------------|
| [ApprovalBlockSample.java](../ressources/samples/ApprovalBlockSample.java) | Sample for approval block. |
| [ApprovalImportSample.java](../ressources/samples/ApprovalImportSample.java) | Sample for approval import. |
| [ApprovalModifyCkdSample.java](../ressources/samples/ApprovalModifyCkdSample.java) | Sample for modifying approval with CKD. |
| [ApprovalModifyCkdSample2.java](../ressources/samples/ApprovalModifyCkdSample2.java) | Another sample for modifying approval with CKD. |
| [ApprovalModifySample.java](../ressources/samples/ApprovalModifySample.java) | Sample for modifying approval. |
| [ApprovalModifySampleExternal.java](../ressources/samples/ApprovalModifySampleExternal.java) | Sample for modifying approval externally. |
| [ApprovalModifyWithTimestampSample.java](../ressources/samples/ApprovalModifyWithTimestampSample.java) | Sample for modifying approval with a timestamp. |
| [ApprovalSignatureSample.java](../ressources/samples/ApprovalSignatureSample.java) | Sample for approval signature. |
| [ApprovalTimelockSample.java](../ressources/samples/ApprovalTimelockSample.java) | Sample for approval timelock. |
| [ApprovalTimelockSample2.java](../ressources/samples/ApprovalTimelockSample2.java) | Another sample for approval timelock. |

## Cryptocurrency
| Filename | Description |
|----------|-------------|
| [FullCryptocurrencySample.java](../ressources/samples/FullCryptocurrencySample.java) | Sample for full cryptocurrency operations. |
| [EcCkdAddrSample.java](../ressources/samples/EcCkdAddrSample.java) | Sample for ECCKD address generation. |

## Certificate
| Filename | Description |
|----------|-------------|
| [CertExtSample.java](../ressources/samples/CertExtSample.java) | Sample for certificate extension. |
| [CertImpSample.java](../ressources/samples/CertImpSample.java) | Sample for certificate import. |
| [CertSample.java](../ressources/samples/CertSample.java) | Sample for generic certificate operations. |


### Miscellaneous
| Filename | Description |
|----------|-------------|
| [PrimusHelper.java](../ressources/samples/PrimusHelper.java) | Helper class for setting up Primus HSM. |
| [PrimusSkaHelper.java](../ressources/samples/PrimusSkaHelper.java) | Sample for Primus SKA helper functions. |
| [LogFetchSample.java](../ressources/samples/LogFetchSample.java) | Sample for fetching logs. |
| [IesKeysHsmSample.java](../ressources/samples/IesKeysHsmSample.java) | Sample for IES keys in HSM. |
| [IesKeysSample.java](../ressources/samples/IesKeysSample.java) | Sample for IES keys. |
| [IesSample.java](../ressources/samples/IesSample.java) | Sample for IES. |
| [IesUpdateSample.java](../ressources/samples/IesUpdateSample.java) | Sample for updating IES. |
| [IesXmlAsyncStreamSample.java](../ressources/samples/IesXmlAsyncStreamSample.java) | Sample for asynchronous IES XML stream. |
| [IesXmlAsyncStreamSample2.java](../ressources/samples/IesXmlAsyncStreamSample2.java) | Another sample for asynchronous IES XML stream. |
| [IesXmlPerfSample.java](../ressources/samples/IesXmlPerfSample.java) | Sample for IES XML performance. |
| [IesXmlSample.java](../ressources/samples/IesXmlSample.java) | Sample for IES XML. |
| [IesXmlStreamSample.java](../ressources/samples/IesXmlStreamSample.java) | Sample for IES XML stream. |
| [SecureRandomBulkKeySample.java](../ressources/samples/SecureRandomBulkKeySample.java) | Sample for secure random bulk key generation. |
| [SecureRandomSample.java](../ressources/samples/SecureRandomSample.java) | Sample for secure random generation. |
| [GetAlgorithmsSample.java](../ressources/samples/GetAlgorithmsSample.java) | Sample for getting algorithms. |
| [TlsSample.java](../ressources/samples/TlsSample.java) | Sample for establishing secure TLS (Transport Layer Security)|
| [TlsInteropSample.java](../ressources/samples/TlsInteropSample.java) | Sample for establishing secure TLS (Transport Layer Security)|
| [TlsInteropBcSample.java](../ressources/samples/TlsInteropBcSample.java) | Sample for establishing secure TLS (Transport Layer Security)|
