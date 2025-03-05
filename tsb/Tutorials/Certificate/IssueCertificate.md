---
sidebar_position: 2
title: Creating Certificates with CloudHSM APIs
sidebar_label: 3. Issue Certificate
description: Learn about issuing, importing, and parsing certificates using Cloud HSM APIs.
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, sign csr, import certificate, parse certificate, x509 certificate, openssl]
---


# 3. Issue a Certificate

## Sign the CSR
**POST**: [/v1/certificate/synchronous/sign](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Certificate/signCertificate)

**Description**: Generates a valid x509 Certificate out of a csr-string.

:::tip Signing CSR

The key `CA.approver.securosys.com` signs the CSR.

:::

Setting the `certificateAuthority` to `false`, should be set if it is a leaf-certificate.

```js {6}
{
  "signKeyName": "certificate_key",
  "keyPassword": null,
  "signatureAlgorithm": "SHA256_WITH_RSA",
  "commonName": "SUB-Securosys-001",
  "certificateAuthority": false,
  "certificateSigningRequest": "replace-me_with_csr_value",
  "keyUsage": [
    "DIGITAL_SIGNATURE"
  ],
  "extendedKeyUsage": [
    "ANY_EXTENDED_KEY_USAGE"
  ]
}
```

**Response**

```
{
  "label": "certificate_key",
  "certificate": "-----BEGIN CERTIFICATE-----\nMIIC9zCCAd+gAwIBAgIETBcQkTANBgkqhkiG9w0BAQsFADAbMRkwFwYDVQQDExBDQS1TZWN1cm9zeXMtMDAxMB4XDTI0MDEyMDE0MjU0MloXDTM0MDExNzE0MjU0MlowHDEaMBgGA1UEAxMRU1VCLVNlY3Vyb3N5cy0wMDEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCjNKLTKM24oiywv8ALv9UdN5Qoxxo09jPG9qZGNSoDIR/Zuzw3el4oJE/vftWXPOjD9s3shAg93NBWJjjJJu7cebeT+MLKC4Qvr6cS+1UPxfbfHl2IlF8/t+Sn1D0c+rqrceHbOp9T0xv29zIjUtZLKYukNrTCFpLyX2u72ImTLbATcRGHAAK5prgySTKoSzN1urtMb5O/nJ7a+YfdJl+mA0PTmL8XJo3gotj6MfrvS2lt2xKXZhCqB+R9/6Iub7h27T2DwRE9SzuQyG+IDQBSy6JGXiYK7+4NlBuJ0h8sDQocXOGkVayTauiLf3wfbuM6ZBTFOapXRXYtkW1RgTGlAgMBAAGjQjBAMB8GA1UdIwQYMBaAFARxSaz/WH2FZW8bcdt4M0OUiLakMB0GA1UdDgQWBBQxO5tiHgh1S4saI+DNU0/aZ69nVzANBgkqhkiG9w0BAQsFAAOCAQEAgPJLslvzonKYe87sej+2i3rkIjTeO4h7pWD9QrqZAZ8fVO4j+c+fTH5rJGwuNLgT+fI5VqyAlLbV5ZCwKFaTGU7LOnY9X5gWOQaNZAtJq51I62eL5YrZJSo0XiLm8ezyC6ctmK3b7/2AS+COjRWsQuL1LdNqlJ6ki7a+Z3ie/ZRRJRKWQvIkqrBk8nQeQp/mGSqHMk9KhSllKUG3AxLE6kSv/F9nIFhjc2PP5QhFF/ShrJGfnbkBBeGGvHgeI0XdcLqTV8h0loYiNHkGANDH/Bg/gC5XmFo71cFyI1vsuvbHUHvCy839OkgIEpvApSKEye2vMgbQNHDVA34k5ooLUQ==\n-----END CERTIFICATE-----"
}
```

## Import certificate

**POST**: [/v1/certificate/import/plain](https://rest-api.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Certificate/importPlainCertificate)

**Description**: Import a certificate

:::tip Remove OpenSSL content

Importing a certificate has to be a valid base64 encoded String. Remove `-----BEGIN CERTIFICATE-----\n` and `\n-----END CERTIFICATE-----`

:::

```js
{
  "label": "sub_certificate_key",
  "certificate": "MIIC9zCCAd+gAwIBAgIETBcQkTANBgkqhkiG9w0BAQsFADAbMRkwFwYDVQQDExBDQS1TZWN1cm9zeXMtMDAxMB4XDTI0MDEyMDE0MjU0MloXDTM0MDExNzE0MjU0MlowHDEaMBgGA1UEAxMRU1VCLVNlY3Vyb3N5cy0wMDEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCjNKLTKM24oiywv8ALv9UdN5Qoxxo09jPG9qZGNSoDIR/Zuzw3el4oJE/vftWXPOjD9s3shAg93NBWJjjJJu7cebeT+MLKC4Qvr6cS+1UPxfbfHl2IlF8/t+Sn1D0c+rqrceHbOp9T0xv29zIjUtZLKYukNrTCFpLyX2u72ImTLbATcRGHAAK5prgySTKoSzN1urtMb5O/nJ7a+YfdJl+mA0PTmL8XJo3gotj6MfrvS2lt2xKXZhCqB+R9/6Iub7h27T2DwRE9SzuQyG+IDQBSy6JGXiYK7+4NlBuJ0h8sDQocXOGkVayTauiLf3wfbuM6ZBTFOapXRXYtkW1RgTGlAgMBAAGjQjBAMB8GA1UdIwQYMBaAFARxSaz/WH2FZW8bcdt4M0OUiLakMB0GA1UdDgQWBBQxO5tiHgh1S4saI+DNU0/aZ69nVzANBgkqhkiG9w0BAQsFAAOCAQEAgPJLslvzonKYe87sej+2i3rkIjTeO4h7pWD9QrqZAZ8fVO4j+c+fTH5rJGwuNLgT+fI5VqyAlLbV5ZCwKFaTGU7LOnY9X5gWOQaNZAtJq51I62eL5YrZJSo0XiLm8ezyC6ctmK3b7/2AS+COjRWsQuL1LdNqlJ6ki7a+Z3ie/ZRRJRKWQvIkqrBk8nQeQp/mGSqHMk9KhSllKUG3AxLE6kSv/F9nIFhjc2PP5QhFF/ShrJGfnbkBBeGGvHgeI0XdcLqTV8h0loYiNHkGANDH/Bg/gC5XmFo71cFyI1vsuvbHUHvCy839OkgIEpvApSKEye2vMgbQNHDVA34k5ooLUQ=="
}
```

---


# Parse certificate with OpenSSL

**Create a file**
```
nano SUB-Securosys-001.crt
```

Copy content from `/v1/certificate/synchronous/sign` response into file.

:::tip 

replace '\n' with real newlines

::: 

```
-----BEGIN CERTIFICATE-----
MIIC9zCCAd+gAwIBAgIETBcQkTANBgkqhkiG9w0BAQsFADAbMRkwFwYDVQQDExBDQS1TZWN1cm9zeXMtMDAxMB4XDTI0MDEyMDE0MjU0MloXDTM0MDExNzE0MjU0MlowHDEaMBgGA1UEAxMRU1VCLVNlY3Vyb3N5cy0wMDEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCjNKLTKM24oiywv8ALv9UdN5Qoxxo09jPG9qZGNSoDIR/Zuzw3el4oJE/vftWXPOjD9s3shAg93NBWJjjJJu7cebeT+MLKC4Qvr6cS+1UPxfbfHl2IlF8/t+Sn1D0c+rqrceHbOp9T0xv29zIjUtZLKYukNrTCFpLyX2u72ImTLbATcRGHAAK5prgySTKoSzN1urtMb5O/nJ7a+YfdJl+mA0PTmL8XJo3gotj6MfrvS2lt2xKXZhCqB+R9/6Iub7h27T2DwRE9SzuQyG+IDQBSy6JGXiYK7+4NlBuJ0h8sDQocXOGkVayTauiLf3wfbuM6ZBTFOapXRXYtkW1RgTGlAgMBAAGjQjBAMB8GA1UdIwQYMBaAFARxSaz/WH2FZW8bcdt4M0OUiLakMB0GA1UdDgQWBBQxO5tiHgh1S4saI+DNU0/aZ69nVzANBgkqhkiG9w0BAQsFAAOCAQEAgPJLslvzonKYe87sej+2i3rkIjTeO4h7pWD9QrqZAZ8fVO4j+c+fTH5rJGwuNLgT+fI5VqyAlLbV5ZCwKFaTGU7LOnY9X5gWOQaNZAtJq51I62eL5YrZJSo0XiLm8ezyC6ctmK3b7/2AS+COjRWsQuL1LdNqlJ6ki7a+Z3ie/ZRRJRKWQvIkqrBk8nQeQp/mGSqHMk9KhSllKUG3AxLE6kSv/F9nIFhjc2PP5QhFF/ShrJGfnbkBBeGGvHgeI0XdcLqTV8h0loYiNHkGANDH/Bg/gC5XmFo71cFyI1vsuvbHUHvCy839OkgIEpvApSKEye2vMgbQNHDVA34k5ooLUQ==
-----END CERTIFICATE-----
```

**Read Certificate with OpenSSL**

```sh
openssl x509 -in SUB-Securosys-001.crt --text --noout
```

```sh
Certificate:
    Data:
        Version: 3 (0x2)
        Serial Number: 1276579985 (0x4c171091)
        Signature Algorithm: sha256WithRSAEncryption
        Issuer: CN = CA-Securosys-001
        Validity
            Not Before: Jan 21 08:52:54 2024 GMT
            Not After : Jan 18 08:52:54 2034 GMT
        Subject: CN = SUB-Securosys-001
        Subject Public Key Info:
            Public Key Algorithm: rsaEncryption
                Public-Key: (2048 bit)
                Modulus:
                    00:a3:34:a2:d3:28:cd:b8:a2:2c:b0:bf:c0:0b:bf:
                    d5:1d:37:94:28:c7:1a:34:f6:33:c6:f6:a6:46:35:
                    2a:03:21:1f:d9:bb:3c:37:7a:5e:28:24:4f:ef:7e:
                    d5:97:3c:e8:c3:f6:cd:ec:84:08:3d:dc:d0:56:26:
                    38:c9:26:ee:dc:79:b7:93:f8:c2:ca:0b:84:2f:af:
                    a7:12:fb:55:0f:c5:f6:df:1e:5d:88:94:5f:3f:b7:
                    e4:a7:d4:3d:1c:fa:ba:ab:71:e1:db:3a:9f:53:d3:
                    1b:f6:f7:32:23:52:d6:4b:29:8b:a4:36:b4:c2:16:
                    92:f2:5f:6b:bb:d8:89:93:2d:b0:13:71:11:87:00:
                    02:b9:a6:b8:32:49:32:a8:4b:33:75:ba:bb:4c:6f:
                    93:bf:9c:9e:da:f9:87:dd:26:5f:a6:03:43:d3:98:
                    bf:17:26:8d:e0:a2:d8:fa:31:fa:ef:4b:69:6d:db:
                    12:97:66:10:aa:07:e4:7d:ff:a2:2e:6f:b8:76:ed:
                    3d:83:c1:11:3d:4b:3b:90:c8:6f:88:0d:00:52:cb:
                    a2:46:5e:26:0a:ef:ee:0d:94:1b:89:d2:1f:2c:0d:
                    0a:1c:5c:e1:a4:55:ac:93:6a:e8:8b:7f:7c:1f:6e:
                    e3:3a:64:14:c5:39:aa:57:45:76:2d:91:6d:51:81:
                    31:a5
                Exponent: 65537 (0x10001)
        X509v3 extensions:
            X509v3 Authority Key Identifier:
                04:71:49:AC:FF:58:7D:85:65:6F:1B:71:DB:78:33:43:94:88:B6:A4
            X509v3 Subject Key Identifier:
                31:3B:9B:62:1E:08:75:4B:8B:1A:23:E0:CD:53:4F:DA:67:AF:67:57
    Signature Algorithm: sha256WithRSAEncryption
    Signature Value:
        44:e0:0b:f3:b3:d9:23:eb:28:55:37:2e:46:3f:fa:e1:f1:43:
        6a:fa:87:83:92:ff:fc:51:30:31:38:db:a1:2d:21:6a:b1:ba:
        9d:f3:fa:1d:a3:41:a6:a3:ea:0c:8d:5f:37:47:c1:b6:8a:7e:
        62:a7:10:98:b7:78:31:bd:75:37:2f:79:e9:b5:a2:42:b6:94:
        76:ac:b3:b0:ee:86:a7:79:33:ef:21:b1:2e:52:dc:8e:c8:ba:
        13:b4:84:2c:e0:7c:30:e5:26:c8:04:46:34:d7:8c:16:97:9b:
        22:27:28:ef:5a:09:de:40:1a:31:2f:18:6e:6b:40:2e:20:16:
        d2:da:fc:40:5b:09:93:e4:55:b7:d7:a2:f9:1d:d1:c6:62:d8:
        7e:62:05:2a:58:bd:33:85:1e:47:e7:8c:97:63:f0:a0:bc:b8:
        c8:fd:f2:35:4d:88:09:7d:82:3f:01:3b:ae:0b:c6:fb:c4:c4:
        50:16:a9:3c:ec:fd:d6:e6:08:92:a2:88:4a:17:ad:18:10:96:
        60:42:c1:f4:02:de:fc:64:b8:12:ea:51:72:98:9c:37:79:24:
        51:6a:e0:06:13:70:19:ce:40:a3:7a:0e:3a:a2:cd:70:1b:5c:
        2f:7c:ca:25:2e:83:fa:40:d1:4e:b8:2c:3c:1e:0c:3f:01:5e:
        81:6d:4d:b4
```