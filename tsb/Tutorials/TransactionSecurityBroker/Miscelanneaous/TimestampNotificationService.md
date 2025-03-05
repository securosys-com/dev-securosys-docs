---
sidebar_position: 4
title: Creating Timestamp-Signer & SKA Keys
sidebar_label: Approval Notification Service
---
import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';


# Approval Notification Service

The Timestamp Notification service is employed to sign timestamp upon fetching approval tasks `/v1/filteredSignApprovalTask`. This proves beneficial when aiming for a centralized approach to handle and distibuting approval tasks for various approvers.

However, it's important to note that the Timestamp Notification Service cannot sign the 'approvalToBeSigned' tasks directly, but can fetch the tasks for approvers.

## Creating a timestamp-signer certificate

The command below generates a `RSA:4096` key and a self-signed certificate, which is being used to verify the signed timestamp by the timestamp-signer-key.

<Tabs groupId="key-provider">
    <TabItem value="OpenSSL" label="OpenSSL" default>
        ```sh
            openssl req -new -x509 -nodes -sha256 -newkey rsa:4096 -days 3650 -subj '/CN=TSB-Timestamp-Signer' -keyout tsb-timestamp-signer.key -out tsb-timestamp-signer.crt
        ```
        Generated files:
        - tsb-timestamp-signer.crt
        - tsb-timestamp-signer.key
    </TabItem>
    <TabItem value="HSM" label="PrimusHSM" default>
        ## Create key
        **POST**: /v1/key

        ```js {12}
        {
            "label": "tsb-timestamp-signer",
            "password": null,
            "algorithm": "RSA",
            "keySize": 4096,
            "attributes": {
                "decrypt": false,
                "sign": true,
                "unwrap": false,
                "extractable": false,
                "modifiable": true,
                "destroyable": true,
                "copyable": false
            },
            "policy": null
        }
        ```

        ## Create a self-signed certificate
        **POST**: /v1/certificate/synchronous/selfsign

        Setting the `certificateAuthority` to `true` adds the BasicConstraint `BC:CA:TRUE`, should be set for intermediate and CA Authorities.
        ```js {7,9}
        {
            "signKeyName": "tsb-timestamp-signer",
            "keyPassword": null,
            "validity": 3650,
            "signatureAlgorithm": "SHA256_WITH_RSA",
            "commonName": "TSB-Timestamp-Signer",
            "certificateAuthority": true,
            "keyUsage": [ "DIGITAL_SIGNATURE" ],
            "extendedKeyUsage": [ "ANY_EXTENDED_KEY_USAGE" ]
        }
        ```
        **Response:**
        ```js
        {
            "label": "tsb-timestamp-signer",
            "certificate": "-----BEGIN CERTIFICATE-----\nMIIFBDCCAuygAwIBAgIBADANBgkqhkiG9w0BAQsFADAbMRkwFwYDVQQDExBDQS1TZWN1cm9zeXMtMDAxMB4XDTI0MDIwNzAwMDAwMFoXDTM0MDIwNDAwMDAwMFowGzEZMBcGA1UEAxMQQ0EtU2VjdXJvc3lzLTAwMTCCAiIwDQYJKoZIhvcNAQEBBQADggIPADCCAgoCggIBAK22q8jAJLTSY0qwKOZQd6srgC+04NzbP211aLNaE9VSa0jYgmTeCbE2UkDthElTtnfX/HtTO/brTVJYi354e7kjOGpt65ILDYr8h0+QZmovhGPXqjV0r8c+2bLYd14LB7n32MeRXUjCszb2JD5WEvpiFLORqcDYgRrZT8csWAl445TDfFr0uRE7OCSsyNVTfoDh4w6GpNv++xNs9iFVyOOlLikwBBdXFaUQh67SDqWxvMSDv9Ihg91yECe1VPrF1UvlHST486TrgC7TAg1XD9GPY51Gh/FA9GHoefCVGMQtNjRkWJdTHX3P0x8xiVj5enWuHRTlheUJQkrjPp4mPvC3ImZUOBgKm6qMEYaOqncOs2aVMYLiWJ9YNZlMUzrUbHzYZiGt0K6b7z5tZP8V00zAFhjoyc7Y8+kX+rLy0EYdUWKU1QwjeK7i9bfHt/rr/eKDXaJmekIPD3SrbyxmjzZMzjWVdDE7b0pzl+DJmiyvheqdkXMuWlzNbo4kvrKu1Q7duEYz1I20EWiZn6qJ1lDu/+tP7XBzAgbyEItzwZfvkNe9Cew9kPRRfgbD9DsPx7KsHmszcGOiJQG4ptuz92OX875e9ExtfzkgDIHLgyDrVh63VDE3DjQS/yqUmEcdUZjd94OvsOF+hSBX4ig9sBhz15WdvxfpgRGM8lESdq21AgMBAAGjUzBRMAwGA1UdEwQFMAMBAf8wEgYDVR0lAQH/BAgwBgYEVR0lADAOBgNVHQ8BAf8EBAMCAoQwHQYDVR0OBBYEFAYu0YvvQ+bTdV2YMa760e/TwOpxMA0GCSqGSIb3DQEBCwUAA4ICAQCDGjhIyK5o+skscqjqzknTV/wO5jiYXz2ikLQdtU8n8FfV8zm/+/pXtG/K+7+UNCdtXWAzRMKVtkgKLh8pKl4qxb6KvDKsWtaximX7iEYKAlCGBGhs3pt1JwktirYrZ35NdclCj4WWmJhoLdIG2ao4EIaRi1KsU6ExmB1IB7yWJymKdjJkWollFCHdAfUyCpjMX1fg6B1P+JUkQYmnvP74xGzKmB1eq2ofdsTOrYnTaNGrcayGn+1DuwwcmBPQ/qAfDgTrmcbEEeXiOwl6Rca26M8yBriQzoMTkYga7EGrLyfFkQLyPLzXY852wkqHqae7g3ZMzRbf5aC9ACTxDRT/JFfyFTanNcdD9UeEBs0lf8v1BFtRdpVQjSYiWKnxRK5QzmBiYCESZS//kqItU80Kfqi/ZwALwb7D0+yAZuBwO4zSTM8+/R0q7yk9F+zw9OjcV3v4e0Jq57GHcqCpGJlIuj437azQLtmjCTlWe8p5DOfsGR8/xCbKtepu2NYoLrrb0a+5jU8tyovoUHxexQDttMq49Mi+RMUg5jTlVLN7viGk78nwL9WkARQHpVimjEDLVdRW1Q05fMzR8sFUjtc87FcwKOhyKhdIvxq65+spyQ0oTE/DguF65m0x182Z1HrHZOLZQ7+C8+HPkOUjQjvM0Bg47o8qL+X68Pa9VeGMSg==\n-----END CERTIFICATE-----"
        }
        ```
    </TabItem>
</Tabs>

### Onboard the Timestamp Signing Certificate on TSB

To check the timestamp signature for approval-task retrieval, add the timestamp signing certificate to the application.yml file in TSB's `application-local.yml` configuration file.

:::tip CloudsHSM - TSB(aaS)

if you are using Securosys TSBaaS, please open a [support-ticket](https://support.securosys.com/external/ticket/create/process?ProcessID=Process-7a3f614acdb3234a286b908170c7bb0e&ServiceID=31&TypeID=18&PriorityID=3&DynamicField_AddAsset=1&Article.Subject=Provider%20REST%3A%20) and send the certificate attached to the ticket, we will onboard the timestamp signing certificate for you.

:::

Locally deployed TSB instances need to configure the Certificate manually, by following these steps:

1. **Open** `application-local.yml`of your TSB-deployment.
2. **Find** or add `general.allowedTimestampSigningCertificates`
3. **Add** the certificate filepath to the list of `allowedTimestampSigningCertificates`
4. **Redeploy** the docker container, make sure, the certificate exists at the given file-location and is properly mapped into your containers volume.

Example:
```yml
general:
  allowedTimestampSigningCertificates: # optional, configured certificates can fetch and delete all tasks for an approver
    - file:/etc/app/config/tsb-timestamp-signer.crt
```

---
---

## Creating a Approver (Key)

Various samples on how to create an Approval key for **`finance-officer-1`** is show here: [Create Approver Key](/tsb/Tutorials/TransactionSecurityBroker/PrimusAuthorizationApp/create-approver-legacy.md)


## Create an SKA-Key with `finance-officer-1` onboarded
        **POST**: /v1/key

        By including the `public-key` of the previously created `finance-officer-1` in the SKA-Key, you designate the officer responsible for approving signing and encryption requests.

        The following request creates a [Elliptic Curve](/tsb/Concepts/key/Algorithms.md) SKA-Key with simple policy, e.g. only `ruleUse` is applied, `ruleBlock`, `ruleUnblock`, `ruleModify` is ignored and stays empty.

        - Adapt the command below with the public-key of the `finance-officer-1` created above.
        ```js {2,16,34}
        {
            "label": "Broker-Transaction-Key-01",
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
                "copyable": true
            },
            "policy": {
                "ruleUse": {
                "tokens": [
                    {
                    "name": "FinanceOfficers",
                    "timelock": 0,
                    "timeout": 600,
                    "groups": [
                        {
                        "name": "finance-officer1",
                        "quorum": 1,
                        "approvals": [
                            {
                            "type": "public_key",
                            "value": "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEBSBisLXR8Lsdpsc9JFeFYzMegPqufyNjk6ncWOFbb9bPMuM3I6SSDzwFNX+XvVO1EkfavxaXQcH43sEuKeYGKw=="
                            }
                        ]
                        }
                    ]
                    }
                ]
                },
                "ruleBlock": null,
                "ruleUnblock": null,
                "ruleModify": null,
                "keyStatus": {
                "blocked": false
                }
            }
        }
        ```

## Create a sign-request
**POST**: /v1/sign

Generate a sign request to be approved by the approver `finance-officer-1`. You can use the metadata-field to provide information to the approver about the contents of the sign request.

```js {3,5}
{
  "signRequest": {
    "payload": "c2VjdXJvc3lz",
    "payloadType": "UNSPECIFIED",
    "signKeyName": "Broker-Transaction-Key-01",
    "keyPassword": null,
    "metaData": "ewoiYXBsaWNhdGlvbl9uYW1lIjoiSENWYXVsdCIsCiJjb3JlcyI6InRyYW5zYWN0aW9uLXNpZ25pbmciCn0=",
    "signatureAlgorithm": "NONE_WITH_ECDSA"
  }
}
```

TSB has created approval tasks for each onboarded approver in the backend. In our case only one task for `finance-officer-1`. These tasks must now be retrieved and approved, as shown below.

**Response**
```js
{
  "signRequestId": "c0743837-cd46-4f3f-8822-b4f256d86bd2"
}
```



## Fetch approval task

The approval task can now be fetched by the `timestamp-signer`, that we previously onboarded in the `application-local.yml`.
- The timestamp `2024-02-08T10:12:46+00:00` is signed by the `timestamp-signer.key` and the signature passed on to the `timestampSignature` field. 
- The `timestampSigningCertificate` is the certificate previously onboarded in the `application-local.yml`
- The `approverPublicKey` is (optional), if specified the task can be fetch for one specific approver.

**POST**: /v1/filteredSignApprovalTask

```js {2-5}
{
  "timestamp": "2024-02-08T11:17:30+00:00",
  "timestampSignature": "XOesAfEV1RiTySjKocOlYxqjZpErMXnV5xOflTM3UnfxkItNsFCpJa1qhhTq8qkOw9hpB2M1DUInfa9xOtjUSiYPh6PkeRxyq4miOPvICU7zRFV6OZsFtzg+oOL0iMY9TiJEDg1fpwfvLXkiuWHYt+vNybWeK8IakYBH6+xtThwJmC3IVXvaNsneRy0oVqLWggRycRWKon9et440XdNCnDh3OnfZc1sODzMv0H0frg5wyEbVtClr0A7x+X8LC3pwHCHgp+ZoVc9TvbkxUeD/eQm+oVHB5x5jbjd1d+LeyEZE8cnQ5THZH0dgHQefZUS002gMx9DGfKPHnsKanAuYoaJNMqHhSUbtTponKyfnqyQG+TAnZstCSoksrKBmHIYlvm/pUFNdE5OUOg53W5rriM/DUNbYAQpzChm+5s+koMvcy/a/ZpifGBFC7JbE8onkNt96Tj8nxqh2SVyouTfoLfv3Wxj3itxtc3/ENSxpj+8qC+oDQCJf5TfGR8mlt2hLzUbNwCi0L12bB8CVTfyn3AbpZJ2O6G8MgjIIz4nWVb4WPbjYUNofN3N7SmcPtoCEkx/0Kw2aoqbyCugylRpa8bFW1KXQCaSA+vWX1up9vnb5YnHwOrl42PSK6YmrkKqguvcSwfGztkNpc9eIMYPy3+aLvZsp4hjd7HAichJNgME=",
  "timestampSigningCertificate": "MIIFJTCCAw2gAwIBAgIUJyZ+70EZshas5hekGw6WTqAc/uowDQYJKoZIhvcNAQELBQAwIjEgMB4GA1UEAwwXcHJpbXVzZGV2LmNsb3Vkc2hzbS5jb20wHhcNMjQwMjAxMDg1MjU2WhcNMzQwMTI5MDg1MjU2WjAiMSAwHgYDVQQDDBdwcmltdXNkZXYuY2xvdWRzaHNtLmNvbTCCAiIwDQYJKoZIhvcNAQEBBQADggIPADCCAgoCggIBAKNKukSs6g+nlVrXKc+TkpCYml0vZQ2OaWwAoC32XIkR+/URf1X2Q+WMPjJkg7bl2VW3SL4GFmohDJm6hag2QBBpgFyCaEQ7g8QoQoopp+tPiCPGTRVYIVsGHctUhcX01SigQ1fYVCinbTdmWEVcGhkT1fFND5sEhgwsMQGDJTg0eKBlVxs6AF/+mMoQxQCdCjUiHzvqlylDgT70RwLbmZy4amTdInzK6VvfW+W5+l3rABNxgnC9fyr2/dAa9SYBApNh8V9098BXEMplyk0AERgLrrcOt4PdxdM/VLLBSmd4VINW3GU467WMvL90lNxGHq3Zi1zievIb7A2Y6vZcsYUAQYOAb5WaUkCl1WtWBxMK9ecrCorqU38PkH9eP3d1NgDnBqZjs58taItLTl/iCuqT9xrAvNu+nSeYvCn5zbgYpmlFx7/nD8GKcKYX9HpiOXvLx3mX6UJczx/Ey7g6OyWSrHPEcE+vcZCtvVrggSzSDrbG0xmF5Z4eyViIm7Q1ZZ3OiZHn8AevBgigwQ5P/lqheS5GrpnnC6oVMWLk5DL0HCZJc3vhd8RjuIWbnO1Sl3Gi3uR97dUbhfpi6Lga23APKRIGo8D2VqrBomgF6WP0wuleVk6y8SoHes27xlYJx2pwpeGp116ZttijJ+aMCIRgeX0QTBH4UENqp/IE7XfRAgMBAAGjUzBRMB0GA1UdDgQWBBQ1c7jH3yKX9xl35nD2Q9KQF1KzNzAfBgNVHSMEGDAWgBQ1c7jH3yKX9xl35nD2Q9KQF1KzNzAPBgNVHRMBAf8EBTADAQH/MA0GCSqGSIb3DQEBCwUAA4ICAQCZ0SOpweNCSUdrFgL15CpwWJfb84EmQ5fx8gLxLpCYKywV+Eh3JpPICPubP0zC1FWPiX9Hiy86orv9OOn5kbEcDn9wEa2452V/tzSK6evTW+/jpY4di+xdqzqPfK5BEKlJ6I199MTT6Wco4KOmwN2KEpojsOM3MjytoDaGwBjRe8jd757P4t6beDMvpwaimlXpjsK9IaQAH2aFQjraDILlcn+T8doZpIvyt77XAn1xJVhT/Su07BKe9/CyHRo9bexxMuFtXX3fw/3KtJM8LgxLDRSEq1jOao4iDA96bij/3E9Nwx1JtusGVge1HMyYZITbQDNmSTz5RR5tN0oUg9shB74hTeI+qNIEQ/MW/kiG0TEL+AY3nu1XfVX/7s75XIBQMwHrjsdYR5J7qi8pgfwVQ5AAjkHA/HdEX5DGIWQoqrKhIPTyLnxzFDCIubLyt2cF23slrTsnulhmCaXrWHoKTYdFMo2wzV25ABUJUJoCZSxgtXjVPkM8VcKJk2AnD3sv9EOkx+U/syzvv6amduRLF5eDgxTPn/O7Gh+WKgify9KvbHFbEPkk3Xq0zv26sWGRDzGe20zxpFeYOaibDkJ/5Ymv2/RHjMYALBMaHixru0PnFSER/+akQxjl0xPoEyJ9GkhulVcPaawyP6aJsIDnOd/hSZLS7wT+H3tvC4pY4A==",
  "approverPublicKey": "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEBSBisLXR8Lsdpsc9JFeFYzMegPqufyNjk6ncWOFbb9bPMuM3I6SSDzwFNX+XvVO1EkfavxaXQcH43sEuKeYGKw==",
  "timestampDigestAlgorithm": "SHA-256",
  "detailLevel": "level1",
  "paging": {
    "pageNumber": 0,
    "pageSize": 1,
    "sortOrder": "CREATION_DATE_ASC"
  }
}
```

:::tip

- The timestamp `timestampSigningCertificate` in the request above is already onboarded on [https://tsb-demo.cloudshsm.com](https://tsb-demo.cloudshsm.com/) instance for testing purposes.
- The private key to sign the `timestamp` can be obtained here: [tsb-demo-timestamp-signer.key](../../../resources/ska/rsa/tsb-demo-timestamp-signer.key)
- The scirpt [sign_timestamp_with_timestamp-signer.sh](../../../resources/ska/rsa/sign_timestamp_with_timestamp-signer.sh) creates the request-body for POST /v1/filteredSignApprovalTask

:::

The response is a challenge `approvalToBeSigned` that needs to be signed by the approver named "finance-office-1."
**Respone:**
```js {6}
{
  "tasks": [
    {
      "detailLevel": "level1",
      "id": "c8dfe0b7-9e4b-4f1b-b792-dcc62eb650f9",
      "approvalToBeSigned": "0AAAADsABAABAAAAAhAZAEJyb2tlci1UcmFuc2FjdGlvbi1LZXktMDEAAABUEDQAMAAAAFcQCQBzZWN1cm9zeXMAAAAHAQgA9bfEZQAAAAACEA0AdGltZXN0YW1wLWtleQAAAFYQWgAwWDAMBggqhkjOPQQDAgUAA0gAMEUCID1N3RL1dIikAHTWiQWWtwLUdFIRHS2ZqAhx46ZsJU8yAiEA6LzgUSw+dVbFUUvOI/XkgZu739yvejXSZcMUvOjPW7YAAFcQCQBzZWN1cm9zeXMAAAA=",
      "approverPublicKey": "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEBSBisLXR8Lsdpsc9JFeFYzMegPqufyNjk6ncWOFbb9bPMuM3I6SSDzwFNX+XvVO1EkfavxaXQcH43sEuKeYGKw=="
    }
  ]
}
```