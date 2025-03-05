---
sidebar_position: 1
title: Import Locally Generated Keys
sidebar_label: Import Locally Generated Keys
description: Learn how to import keys using Cloud Hardware Security Modules (HSM).
keywords: [cybersecurity, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module, BIP32 seed, BTC address generation, key import, HSM key import, ECC cryptography, elliptic curve cryptography, key attributes, HSM policies]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

When working with Cloud Hardware Security Modules (HSM), you may need to import cryptographic keys for secure operations such as encryption, decryption, signing, or key wrapping. This guide walks you through the process of generating and importing RSA keys—both public and private—into the HSM.

For scenarios where only encryption or key wrapping is required, you can import the **public key** to the HSM, allowing you to encrypt data or symmetric keys securely without exposing private key information. Alternatively, if decryption or signing is required, you can import a full key pair (private and public keys) into the HSM to leverage its secure key management and cryptographic capabilities.

This page details how to:

- Generate RSA key pairs locally.
- Extract and clean keys for compatibility with the HSM.
- Import keys (both public-only and key pairs) into the HSM using the appropriate API.

## Generate KeyPair (Locally)
To create a new RSA key pair locally, use the following command. This will generate a private key (private_key.pem) with a length of 4096 bits.
```sh
openssl genrsa -out private_key.pem 4096
```

## Get the private key
:::note Get Private Key
After generating the private key, it needs to be base64 encoded and cleaned up by removing the header/footer and newline characters. This step ensures compatibility with the HSM API.
:::

<Tabs groupId="prepare-key">
  <TabItem value="bash" label="Linux (Bash)" default>
```sh
sed '/-----BEGIN PRIVATE KEY-----/d; /-----END PRIVATE KEY-----/d' private_key.pem | tr -d '\n'
```
  </TabItem>
  <TabItem value="powershell" label="Windows (PowerShell)">
```sh
(Get-Content private_key.pem | Where-Object {$_ -notmatch "-----BEGIN PRIVATE KEY-----|-----END PRIVATE KEY-----"} | Out-String).Replace("`n", "").Replace("`r", "")
```
  </TabItem>
</Tabs>

**Response**:

The output will be a single-line, base64-encoded private key string.

```
MIIJQQIBADANBgkqhkiG9w0BAQEFAASCCSswggknAgEAAoICAQDV9tcb6EdqwbsL6YqWB33Gf5WwIlMmAhzMYZIQ+K8YYxbnLUZGc9tl8w59FUkaYxIs+e0VLstrOZvdTa/IK9YHSi9G1FM5awicuvwUpoXUJviPZHRGt+HhlU6/Ip0ha8WjmME+oIyZPsOicon9OsXH8iVHV7B4ueor6ZKxZSBuBN+ra09EkF5XeYcNETOX74K0boFEEGfOLexvXaXE6ygamhcZRys6l354OMjMPrri5jPt5Xxv6zyfA1bDfyOIamqK78xT/B1pYUaG33W5YTQ02FXlVvzxFv4Z73RI/hxqkNtg3fdDmZIcEsDvIYXr4CBcUaNB8cDyntxXLmOEq1qtUUI3WKfQNiPxophFb5Bs9EHC95TfqiCD+BkS+gZFNUjnlG4scdHYS0vJG5Ufh/RswFwwPncIQkwFJOuuSDjZuavv05Oh6ToyfHBgwhGDzTdjrLodM680Lv5tRMJ01VvsPaQpmB5tKoraqDW+gfGeYwt4x/8KyLqxL07XwNSNqGi1+jv1JBXs9ruHbWfcbkrQE6tRYxNofi9jciw4eOjd8XCv+4nQoxcYVmAFVzDuWQRnLgDunSEqGQcH2V2UUyryNPuVdHPcmjxXEaYg5YLMOuu7VVGDzSciplr9E+57ssXr6lC95krKFmDcDzS2DJ14OIkvlYn0DxqgEA49EQdqywIDAQABAoICAAxnAgIJR/Z9Y1EQnlVi+sdCRv7OMTap9wRhKtKBWm5AntJop+2r3ThUIJoJ3VJtErUjtDqURftMUWdrD447/ZcvlXz2usTrGjUyNiwg7kSlZlFdgnXvm7rJPlF1pAO3bQiLcWF8H0TqGUn1zLTZLsoPvwRwe0y22MbnLkx1Ga5MPwYp3WQ9NWgUUHU78tsoPtHO+VQ6L+IzfESE+k0R+nb28/F6/sZR2LqxmUYUOXNZ4lcJry8HmjHMw5SSbpCuO8hw7PhN9rXQiyzZ5XIzmw9DHlDXEX4MwKQ9N+bb6pwXaHf9dc3n0ssoynaHYW1nm53uUix04yPNJzrbIq6ihh9xoLG3XwkAdq429h+kRzeNVv/G16yCIayfMb7j1dSa7B4VmwaQPeEi2aG2W1VLJuLquPud/Q7WqLaZ7c2oXNg+1XbIsCxU9ext3lFdyu95UjfC4VmO+yqS8ZNilkP5dDCxV2zeXT88fDg7GdUwQh/yV8A+KKho9sD9+HCr7PoUYC12q+xBjSRspxBNkcX5BzejgVQwDscTQjMr9a2yeXBtZ0wR++8VSHq2irdNFqzLm0sBYlHzsKHm4DaDnQ7nQBOgoSdtqB3ptou5JAWEr4cEN+91w1AJMU8R2ngROBEUw541XIrhOCeCIejhTTDR4mMkU8JEI0aAW1KSFNdVUTblAoIBAQDvles5cS27Shn4ZWms4YU03bD+PH+JllpRNkExLenhk9eaYgcktGCp5FsWOvC8xMzIZynVtEflAcSfTBRkzve5oPckUDzP9iAlmoWFKAsZpZBHx8LTOWvyFdJTJlfIk1mM6iO9mQkgMsNKBHjLxyZ4QiULafzSk2PfgSkbH6ITSKsU8CymxPriUTc2g+6Afw0sEX4a+pfAw8buTZkAW2McGEE/dJIfUOFiMd01tGjbDb9DhBgQfJLtvnHyfcgvnjHZfL1njW/CwZY6nxdfy+GBI7GSHrT3NE6KjYwixeBoVmV2KNGiuxQeBwPSMLbLv2VKRBNvCtzkpOUareUpY1V/AoIBAQDkn4yCAAkdE/QazjeS0vKJiyXl7eNnKZO4UKWVRDHPOk0sadPgjhruuejqveLWAEeGNgharQTUD1M/Hk8tIxsRFAQeiWwNn8MPBuCcQivZbeydG6fLunhuOjPZljC5WDyZPqznJhLGpLtZVg75aQxBBRtQyfBqGL+5K79yjGKlhF+RrH3NOVxu/4+NHAzrNguRoDP7PfL1vTaLbWMuWRpGf8Rg572kEmjXwhNA248ktj8xTYq/R7jc8nsdehsqXmJ7UV/26r6eXrlEwRC68Aba9mmK8aI/CoVGXgqFNvZ1CNwYbwZNqt1ROGKYrrgefw20chA9no1EgaP/G9kc4Ai1AoIBABjN26GBzytBu7iUv0EPxBu6C/X2S3nyH/2icVNnHnylMsAf//9T0WIlayy9A2l3sn/Y9mLSnFLgQPwBA2eAafNE7/FJgVJCeAeMnOAxShzooCxeSRRXMI7KpZDIOnRglPfa0cJyUpiLyN4j4mGuh5QRvQYWff9LFR7xn1DYkI4Qiu3LA8RsRlEh+WZieFHDVZjHYtfmM8nYOwM2820vCO7CsBaiufIWDI9+/BjwSZ7MKI4T4e3rB8cnXWKulQghB6YtnOpYoUZ3hlchW2kmkX0uNdcb6L5jIvKDPwvmkpoav7xg83s2hRmqvkGHm5UyhokOzThE1qOxUqrVcBE94H8CggEAPRfpqz+pL80KOTOMJD8csuatPPFCCGpS4Dkk1mt/x/mzb7/j76jpx4uEk14kxL0UX+gGeXyswqJJw58s10n/TKQFqcddSZK8xEI5OWhxYUv/o8glIcr1rbb97hI1eQH8R5M5Dxfcgpq4udnGbJ3GuYVM1FzyUtfEPA5VvZ2IKuThyQLIJ1RBwCOoz7OEPEKX+tVNrsDSMRrb/YxAqb0DyX0RFDg5NCP7QlTJYlaJvOy6nEk1X4IiA2EEx06+/aZ1OLD5LPlz8MwtRBM3jkzf+BlLAOcrTR9ZmAE/XHPgjkvMzPa2P4D+XVrs9hISf4vCNMsaTe0VoQ3nrh9Z+F5h1QKCAQAd4WDmVg+mg58n2eRqbw/VjC9FxTOaYYenAxDR4ddoNpyt0Da4xP/3sKAMAPEzLgRafSdciYhDr0FMAwlpw1CzOTtX+1y5P4j5QiZDlUi8BxFkbJB5D+JkNoQ9xs+1TXDdCQXIQFC8eboXeqnOc9OtoDPpD3yUt1YZjf1DIsD2z7zRmmVpjR6Rt9dT1F4FQ+bXVoG9u7C+2xebHGCNCmmWje2kBV/Anrr19tSD8sY298IPoFgFa45p/sqMlsbmmK4bOxuLjsXlrBKFVDkuTKQvqPug+jQLSxkQW9Ido0RZtiqiLGAPE7EkAuQKjjOAq82ZmVbSY0PPIBwswQBxv3ZH
```

## Get the public key

To derive the public key from the private key and save it to a file:

**Write the Public-Key to a file**
```sh
openssl rsa -in private_key.pem -pubout -out public_key.pem
```

Next, extract and clean the public key, as done for the private key.


<Tabs groupId="prepare-key">
  <TabItem value="bash" label="Linux (Bash)" default>
```sh
sed '/-----BEGIN PUBLIC KEY-----/d; /-----END PUBLIC KEY-----/d' public_key.pem | tr -d '\n'
```
  </TabItem>
    <TabItem value="powershell" label="Windows (PowerShell)">
```sh
(Get-Content public_key.pem | Where-Object {$_ -notmatch "-----BEGIN PUBLIC KEY-----|-----END PUBLIC KEY-----"} | Out-String).Replace("`n", "").Replace("`r", "")
```
  </TabItem>
</Tabs>

**Response**:

The response is the Public-Key withouth prefix.
```
MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA1fbXG+hHasG7C+mKlgd9xn+VsCJTJgIczGGSEPivGGMW5y1GRnPbZfMOfRVJGmMSLPntFS7Lazmb3U2vyCvWB0ovRtRTOWsInLr8FKaF1Cb4j2R0Rrfh4ZVOvyKdIWvFo5jBPqCMmT7DonKJ/TrFx/IlR1eweLnqK+mSsWUgbgTfq2tPRJBeV3mHDREzl++CtG6BRBBnzi3sb12lxOsoGpoXGUcrOpd+eDjIzD664uYz7eV8b+s8nwNWw38jiGpqiu/MU/wdaWFGht91uWE0NNhV5Vb88Rb+Ge90SP4capDbYN33Q5mSHBLA7yGF6+AgXFGjQfHA8p7cVy5jhKtarVFCN1in0DYj8aKYRW+QbPRBwveU36ogg/gZEvoGRTVI55RuLHHR2EtLyRuVH4f0bMBcMD53CEJMBSTrrkg42bmr79OToek6MnxwYMIRg803Y6y6HTOvNC7+bUTCdNVb7D2kKZgebSqK2qg1voHxnmMLeMf/Csi6sS9O18DUjahotfo79SQV7Pa7h21n3G5K0BOrUWMTaH4vY3IsOHjo3fFwr/uJ0KMXGFZgBVcw7lkEZy4A7p0hKhkHB9ldlFMq8jT7lXRz3Jo8VxGmIOWCzDrru1VRg80nIqZa/RPue7LF6+pQveZKyhZg3A80tgydeDiJL5WJ9A8aoBAOPREHassCAwEAAQ==
```

## Import the Private & Public Key into the HSM

Use the following API to import the generated private and public keys into the HSM. Ensure that the appropriate key strings are inserted into the JSON payload.

**POST**: [/v1/importedKey](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/importPlainKey)

<Tabs groupId="import-private-public-key">  
    <TabItem value="import-private-key" label="Import KeyPair" default>
```js
{
  "label": "import_keypair",
  "algorithm": "RSA",  
  "privateKey": "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",
  "publicKey": "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA1fbXG+hHasG7C+mKlgd9xn+VsCJTJgIczGGSEPivGGMW5y1GRnPbZfMOfRVJGmMSLPntFS7Lazmb3U2vyCvWB0ovRtRTOWsInLr8FKaF1Cb4j2R0Rrfh4ZVOvyKdIWvFo5jBPqCMmT7DonKJ/TrFx/IlR1eweLnqK+mSsWUgbgTfq2tPRJBeV3mHDREzl++CtG6BRBBnzi3sb12lxOsoGpoXGUcrOpd+eDjIzD664uYz7eV8b+s8nwNWw38jiGpqiu/MU/wdaWFGht91uWE0NNhV5Vb88Rb+Ge90SP4capDbYN33Q5mSHBLA7yGF6+AgXFGjQfHA8p7cVy5jhKtarVFCN1in0DYj8aKYRW+QbPRBwveU36ogg/gZEvoGRTVI55RuLHHR2EtLyRuVH4f0bMBcMD53CEJMBSTrrkg42bmr79OToek6MnxwYMIRg803Y6y6HTOvNC7+bUTCdNVb7D2kKZgebSqK2qg1voHxnmMLeMf/Csi6sS9O18DUjahotfo79SQV7Pa7h21n3G5K0BOrUWMTaH4vY3IsOHjo3fFwr/uJ0KMXGFZgBVcw7lkEZy4A7p0hKhkHB9ldlFMq8jT7lXRz3Jo8VxGmIOWCzDrru1VRg80nIqZa/RPue7LF6+pQveZKyhZg3A80tgydeDiJL5WJ9A8aoBAOPREHassCAwEAAQ==",  
  "attributes": {
    "encrypt": true,
    "decrypt": true,
    "verify": true,
    "sign": true,
    "wrap": true,
    "unwrap": true,
    "derive": false,
    "extractable": false,
    "modifiable": true,
    "destroyable": true,
    "sensitive": true,
    "copyable": false
  }
}
```
  </TabItem>
  <TabItem value="import-public-key" label="Import PublicKey (only)">
```js
{
  "label": "import_public_key",
  "algorithm": "RSA",    
  "publicKey": "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA1fbXG+hHasG7C+mKlgd9xn+VsCJTJgIczGGSEPivGGMW5y1GRnPbZfMOfRVJGmMSLPntFS7Lazmb3U2vyCvWB0ovRtRTOWsInLr8FKaF1Cb4j2R0Rrfh4ZVOvyKdIWvFo5jBPqCMmT7DonKJ/TrFx/IlR1eweLnqK+mSsWUgbgTfq2tPRJBeV3mHDREzl++CtG6BRBBnzi3sb12lxOsoGpoXGUcrOpd+eDjIzD664uYz7eV8b+s8nwNWw38jiGpqiu/MU/wdaWFGht91uWE0NNhV5Vb88Rb+Ge90SP4capDbYN33Q5mSHBLA7yGF6+AgXFGjQfHA8p7cVy5jhKtarVFCN1in0DYj8aKYRW+QbPRBwveU36ogg/gZEvoGRTVI55RuLHHR2EtLyRuVH4f0bMBcMD53CEJMBSTrrkg42bmr79OToek6MnxwYMIRg803Y6y6HTOvNC7+bUTCdNVb7D2kKZgebSqK2qg1voHxnmMLeMf/Csi6sS9O18DUjahotfo79SQV7Pa7h21n3G5K0BOrUWMTaH4vY3IsOHjo3fFwr/uJ0KMXGFZgBVcw7lkEZy4A7p0hKhkHB9ldlFMq8jT7lXRz3Jo8VxGmIOWCzDrru1VRg80nIqZa/RPue7LF6+pQveZKyhZg3A80tgydeDiJL5WJ9A8aoBAOPREHassCAwEAAQ==",  
  "attributes": {
    "encrypt": true,
    "decrypt": true,
    "verify": true,
    "sign": true,
    "wrap": true,
    "unwrap": true,
    "derive": false,
    "extractable": false,
    "modifiable": true,
    "destroyable": true,
    "sensitive": true,
    "copyable": false
  }
}
```
  </TabItem>
</Tabs>

## Usage

Use the following API calls to perform cryptographic operations (encryption or key wrapping) using the imported public keys stored in the HSM.

<Tabs groupId="import-private-public-key">  
    <TabItem value="encrypt-public-key" label="Encrypt with PublicKey" default>

This command encrypts a payload using the public key imported into the HSM. The encrypted output can only be decrypted using the corresponding private key.

**POST**: [/v1/encrypt](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/encrypt)

```js {4}
{
  "encryptRequest": {
    "payload": "cGF5bG9hZCB0byBiZSBlbmNyeXB0ZWQ=",
    "encryptKeyName": "import_public_key",
    "cipherAlgorithm": "RSA_PADDING_OAEP_WITH_SHA512"
  }
}
```

**Response**: 

The response will contain the encryptedPayload, which is the Base64-encoded result of the encryption process.

```js
{
  "encryptedPayload": "m7FCNVKIZgN78XQiRUlCZs8OAoSzScYLvm+G0L0WiNsE+E6x9iaCKM0DTkzdhKcnG1X6PHLw4kC0dZ1zoa45PFKSfSd42cL0I8Ha6sv0mu7sMcp0jYNcTJvbfZ3NYZNagFxbfMiYGF0xfMshvs355YXzwGqZNko/r6voOwF3s44gy+n0DZdeA6/MqqettI1FZS2Rvnxlqr/lWF3nHqlr4IS1L6sIXCHc7uYQF0bw/vks7h+u9xwwkBt+N5wgRvmGGB2C/qmVDLKYafq3QgCgWo432Usp1U943WQGgikCi1TNd0/L16QZQ4LN9H7oX17xNrm5Yf6xmlli5LgcvesaJ2Z46dK5CORdCQSALRKQRDHzb8O2N27sExZMBzF/K/14bETT9O0rcgI6p5IUnj3KALL9SBGmKNCKCk/U4ttjNFI7PljTpNqMEPQJE4G4xIr/RHrnLToeL1G+gqw1sTx6ke9jxnyGx2S0mo/x67ruZ41TSwGmI68CudHlpRNpNCHVv7JT/oMpXMVB2UDQZYooYbQag6Fi4OU99PVubIVbFqNFk9BRczLyD8YXLC4k/3FEBSPtSFYDnbnCBy6EgCUdnMgjk4P2BWA7goehEk+aSPGdi7xnjxagpxwFskOsXWL5E95B+ftZHp8oJVRQYBH9Ij5dU+x0DJHSMAIwekJWey4="  
}
```
  </TabItem>
  <TabItem value="wrap-public-key" label="Wrap with PublicKey">

Before wrapping, you need to create a symmetric key (e.g., AES) in the HSM. This key will later be encrypted (wrapped) using the previously imported RSA public key.

**POST**: [/v1/key](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey)

```js
{
    "label": "key_to_be_wrapped",
    "algorithm": "AES",
    "keySize": 256,
    "attributes": {
        "encrypt": true,
        "decrypt": true,
        "verify": true,
        "sign": false,
        "wrap": true,
        "unwrap": true,
        "derive": false,
        "bip32": false,
        "slip10": false,
        "extractable": true,
        "modifiable": true,
        "destroyable": true,
        "sensitive": true,
        "copyable": true
    }
}
```

**Create Wrap Request (RSA Wraps AES)**

This command uses the RSA public key to encrypt (wrap) the AES key. The wrapped key can then be securely transmitted or stored.

**POST**: [/v1/wrap](https://primusdev.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/wrap)

```js
{
  "wrapKeyRequest": {
    "keyToBeWrapped": "key_to_be_wrapped",
    "wrapKeyName": "import_public_key_4",
    "wrapMethod": "RSA_WRAP_OAEP"
  }
}
```

**Response**:

The response will contain the wrappedKey, which is the Base64-encoded encrypted version of the AES key.
```js
{
  "wrappedKey": "1B0/R3QbhBXCf1O6gyVKIovkAMwtJSVnBDnC8euzVGkiAnhVkb4V1wqhbUYos93gY6wnxJOgox+u6er35ctNMnOkzT0V3S8aodDEbR0xerjxHuV3RZM7Nlc0QTYL1pJWct0xEvcet9Uaa4fD4v4mfcpKUjC76HTGkZ/qxJFNPOZ1kMjCQV5vDAEyOyf7kM1WUIZHB+1E7o80GNXaRtLCOWvaxArM/ecft+T/0GUDVMuyCxvNehem4LWkUidxoOMVOKUZ+kgzWvJn8jJHO8sC6oibEmLWUprWaXbgqHmwKCGC6US/EqjREZDtz6UTHtmHpCGTKG8owyYQ8LuU4SmfRNyM46s6cR1wpCmUUqZJz8QotKJ+RFDnK5yTePx1/mWCHjGdh2p7VPT06+Y3BW1n8vwG9ji/QSfxXPV0JAPqu4ZZS25fSKMfCul5UqfXMadKeHcXu3Jn8CPGlKh0V0vlKtDs+y2NvdUpGlb76Z9OKFMlguYAUNYWDqaYkCxEInAsaZkD+wefWk3ryQSMylMt6TubSYZZOLYyeijNtyVfRrFdilHJ5XhRMpwatcaOiXalp094bL6fZm46XaYZK5c3fQ5xnzwQDPzyweQa64Obk27C+H2QubUP5DaNcXY/Jv/RiQBfVbps+LKtuLpRzXfL45rbhqWawcHC2Kt4+0H0law="
}
```
  </TabItem>
</Tabs>