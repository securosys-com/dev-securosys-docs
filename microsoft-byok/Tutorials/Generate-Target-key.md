---
sidebar_position: 1
title: Azure BYOK - Generate & Wrap Target Key
sidebar_label: Generate and Wrap Target Key
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Generate and Wrap Target Key

The target key to transfer needs to be created on your Primus HSM or Securosys CloudHSM with the flags `ACCESS_EXTRACTABLE` (and `ACCESS_SENSITIVE`) set. 

Target key creation can be done via:

- JCE (primus-tools, own application, …),
- PKCS#11 (e.g. OpenSC pkcs11-tool, OpenSSL, own application, …),
- MS CNG provider (MS certmgr/certutil tools).

Example target key creation using the Primus Tools is shown below. Visit [Primus Tools User Guide](/primus-tools/overview) for more information on the tools set.

:::note 
Make sure to replace the parameters in the example commands to your own.
:::

## Create Target Key

Create a key with Primus Tools command `CreateKey`. To allow wrapped key export, key flags `ACCESS_EXTRACTABLE` and `ACCESS_SENSITIVE` must be set. Appropriate HSM Security Policy is required (see [Primus HSM Configuration](/microsoft-byok/Installation/Prerequisites.md#primus-hsm-configuration) for more details):
```bash
java -jar primus-tools.jar CreateKey <HSM connection and credentials> -keyname myPrimusByoK -type RSA -size 4096 -flags sensitive,extractable
```

Consult [Primus Tools User Guide](/primus-tools/overview) regarding  `<HSM connection and credentials>` parameter.


## Verify Target Key Flags

Verify the key flags with the Primus Tools command `ListKeyFlags`:
```bash
java -jar primus-tools.jar ListKeyFlags <HSM connection and credentials> -keyname myPrimusByoK
```
Example output:
```bash
myPrimusByoK sensitive extractable modifiable copyable token nonindestructible private nonpublic nonneverextractable alwayssensitive local nonwrapwithtrusted nonunique decrypt sign unwrap notderive notintegrity notsignrecover
```

## Wrap Target Key 

Transfer the KEK public key (`KEKforBYOK.publickey.pem`) file to your HSM Client (offline) computer where you installed the Primus Tools (e.g. C:\Users\Public\byok\). 

Create the `.byok` file using the Primus Tools (connectivity to your HSM is required, see  [Primus Tools User Guide](/primus-tools/overview) regarding  `<HSM connection and credentials>` parameter).

Example:
```bash
java -jar primus-tools.jar AzureByokExport <HSM connection and credentials> -kekidentifier https://myKeyVaultHSM.vault.azure.net/keys/KEKforBYOK/d0dfd74d5bfc494abc572867b20e4260 -kekfile KEKforBYOK.publickey.pem -outfile myPrimusRSA4k.byok -rsakey myPrimusByoK
```

The output should look similar to the following `SecurosysPrimusByokR4k.byok` example:

```bash
{
  "schema_version": "1.0.0",
  "header":
  {
    "kid": "https://securosysprimuskeyvault.vault.azure.net/keys/KekForByokR4k/083cf1b67d314d15b1fa46915cde3217",
    "alg": "dir",
    "enc": "CKM_RSA_AES_KEY_WRAP"
  },
  "ciphertext":"0aIzzpubFHIdw4oQyXfo_Qrj5X8_rwA1r8y3QmIsBtDmbiEOoxpdn6TIk_um7b3yQTYkLjzJX-P5LOHqpqlz2BOLVq_K1I32O8RF5mdbCftcUFYuCR0F7tUiHfah12DzUFng1Tg3At_SiO9v26C4rH9LveBWQOskgY0Fy3Q0KbEB6C5hFe3gCweXLzsGFcRBGCpgtlOYBEeWEpxGzFuyrO-XABN4lYOQXufTQ_ni78S6yMGjw5t6es_ioSuDeROQF83UTJ8m3L1IvGRcGnYzjVl73ZRI7ntMyxEsN1wtztd4wqJWFvjW3MLdr9akX9FBnTnHdQIgAjccQu8xbDYbEeSSaftY9RCPUUssRoZI6mb2CAmKjupwJQ2W8ZF8rpYZgdBICHDvqK8HzfmJsMGFuA37yEzSXT4G-hmIiNfT-KZ7BSlF5pex-5GTxsIz15n-NT3TaQV0DaGOXmGi672idmIFLCu236BUhA2h2y5ENW5_iVyAYsk8IRO-dfkVXrITyXebFrBjbi6eWldb0dZaa8gNynWtRiq5ZOyks8PlR1u7BvGkiqE_ZWVxfL9s7doSxni9wOL3t_Ulk3dMo9vLbnP1bx8qKq9klxfLQXipSKoNAJn6pZsbXsUT589hltwP3Y3i2oRuJjw2YPS0ghAkuFAYKOIQkblpOCfSFfTgUlUKzhNaHLYuEgXwaJ2ac1Xwqspfng5MKhQusX4aQwsyS15AG8w2el8thlCU5d3PXiiOEp983Vkdzyqgtud3zSKkHbmkGBjuCwFf_pg6ZRMX70foGNEHpQjl8dYA9aD69nv_WLDbBM6MK-RiyUDiYJisRCOYdk0VxZLvfZxbZIM8sY1Rk0BhFOwSL3UYOF3H0G4mAPjOEB-S1ZjzFiiRUtqPo2O6e4r1Zvlc-S9m658z_kWXbmFusUOcUvnVl4_24LDwm-8IH3fki8sMBQHl1KUtf3IdvXMr--INj7e2za2CZBjzrPPe76y19ZdaHoIEd43rJDROakApxEZh57WJfyqVH8s4d7RcRGJTXFdlA_mTR4aIEQtCBf5KLe_LnwQBB_uCTCIa1TPpNcLlAfiuXtvXbA-_3wR_bNmBUabs24E4XtSHu6RmWyImIkMGI8-GxnRPrdmA0uDgT4rajxGnw8IcZs0ILof21cfeLifxWO7YJphH0xVBE2jUC-32MgV3RpJDaGRpq0hLmuNo6Nw0pkJ46E016kLzhdVER0Bk5vJwoTYA8u-_5_YXpYyYOL8h_mUg5elvNQbIe6afJEmC9BW2wYFn-PrCbEjdoJIe--2Cwck2BS26BfTOkv0Vza__jg8AmLxoec4330iop-8EaZqhRwiNc3WA3GSZF4MnwpRQWSTnN10BSBlw11rmhUnBxgFmOYI95Re8Gf8TA54WdIsv1EN_WvEhEyMk2x9FJjN1WJU_QLX05bT7aHYH_5L5Xd8GAjKOng_2Eu33NNoI26741-kpFEPszs2dbSY8V9GseDkfJgQrJn66vr5s7hoV4BN-QLOLcL3NFRs9FrdCl3xya-BYKhmsNzuC-MGnOlCUWatcK_gNRkBaAkoLMC2oW6y3ec5hdx5w43YxoCpbzUBmMTtlcrObYdZs8MjhvW6RX8eMCXb-9jE13SFokHdfuh9KDMKEdAJhFZ8CcSEeyVMgrc7GNsVlAjq0dNKbscWstLUWPuF35n8apwWD9L6VbqnEeHeqUfI3QgLevmkwaHAaI3ukkqPzF0ME3ktiIQE2gj2JpAttsnkrI0oBHXIaSuaSwh-lY_IsRbQRbao804IV0MYsn5TmLFsttzFPFYNk4l_gE2ZTY6YpyXm7S6DHG5ez21iHvQ0tsTAbvrGvqU0c5QIn1C3SSB9WdphUOJbpR2R0GoDyE4dCTRxv_yYWo3agYgNeRZkJH0hTgndN1MExEY-dzDusIsZya4ztVzAZV2apo3I7gyu1p9xNZgekX4aOs25-_ys9b-80V5aDdhGR9CBGiBrYbkX4ojzTf2W4_jPPhIsp4ESHEBufqMZCLvKQkBLddaUEUlaioQvGlvN1yr70h8EFLh2I-8IgAlJKVAe_HV2LhEi6Qg2TuMdK4i1xgIR7SThx9Hh5XFJvcw7iIgbAbi4Ww7eVoQOtszOmoUEemYIE50Tsw5IxSR9RMY2SzcWsajbTLc_pFtQDyhchc21-95T2rBbDP3MvJc_4odIRBcqwjF1flwim5qnYeTaA7itstUbsnlogO_3peiVc9ypLQ6cURkKRagq0LYig6AqsJM8iHFnSk678jU2XqEuD4pKjtqUtG5cFUIY6gVw78xw_JVK9PQVdEU_PuzNpmwvRd02nrSstfkeyg4ORzE5AoydkUYYaNvjVhAvLAKXpaZrIguW8pN29_4Z90-8XKPdB6M7zsQAj9eFo6GuqD0EhsxUD09GwxtCl1o6DjUKGXUPPPT7SN_4g1YXC2M_0IP8jN1R2Lqs4ztJrDTiLmB_G7DiHhJpAllO8MOdzDNoXY24wLzqNPzEjSbQ9KgchQSERsyBOv5xbe7n8-bH5o3uQrtuHX2W2paozNslWNOySeGDyKAWk86oM98Vo-RbSnlKgD-DEPoWUa0xIwfNybvnEoWxCHCrPvXt3D4nDhTfL0E3khN69IRyuKsIhZ3v1BmNAxLJW19srLSUfPVifMhfBqt23SFiJvIACpM5q_ypKUJ0v8rDW27WPqGz5jPMHqsGZAxSW_toexqUldwqDIqmtxQavC8_62Zz8ZUKhqE_ORjpRzUCUANz9tZ9fTMZWsipl6LhDEeknjuHSX6G5d27nd3QAojNhLnwncAVd7-p-I5dqcTPYLEdMjRs-eyp-UjrllSeaqy0Aw1JFN2luwSq1jEAEACVvx_tVMeRM-uHsZ3epGqcL6cVbpsGb9yah1DfRGUtSzMrwwN8gAswwkLbXA6O8G3CONoY4uLuSE2TiCZuvB_w1WvEQgeXApOhIwpoIfNpQWSFmA8Djpf4hlVMwC-uXgi964dLCOfRzkiBMctPby_3lAfDOf7dRteNDOPiHOXBn0S_vdMrB_RAvkeoQd4Wc2NQx5-1e-SOUhfGdJYdM7uMc56cRooA7ebQcVxVrAwCKfw_ISs6MIITy7OXvV9wDqYqw4yFg8ZfWgS3K81s2EvS5080rOqFOiHAp0Z0XpBwcOmQFXto3KBWEKaCclQt8NVAXbnElf_zxA2aoomFGDrmteIFSeYd92UuOPHAdHE8FASG2PD4Nm99_ZXMVxITiHwMPxrTYw7AVCbMe5-RmhJm4DsHtwouxnrfHqY9fIV9GKyWUyVGH60lp4G-nnK7aLiY6cMBOF7qp9rYEjvXbvfoM1rTvgeVg-iLwWTAjk5K1X3fmVauN0rvpAt4ViSyUiyvw67uTGYt9BfIgYrDUXkUZwz_-SVaZlqH2VvLMRT4QJJ3bdyItFPNciwIX84sEcbo4VVB5JnhYZzcdEPDla7PQJI_RcqYyPtHXb0Gvg_JupSh9_anexNOS8sP4noXKbrklaXuoVi_rlymq5ZDzt9GOWh_TLynltDCHahe53e6egp7_Si237R-RiNaquEppLFGfVKQCyAyjN1GPZ8eInnf5GLm7GDtiG4xHQM-5gpkodpG2JIlLdAkAgQDQ2Dx-Ywpx_eFtkZd0Lv1sXxcO5zjvYfmDbNzn8qtEbuRxbcaTo1o1eAbv5_pWXfjV-ak1kt6JPOa3fnsduvcwnMPUfY_30lp5OgEKHKhiLYNDdj-6ga3IR66qosQ1LO1wjvwTPQ7ui5MEFTSHGKMENjPiPYSxt1fxW6WeaqkalnPAvm5LMSmUJ2T5-tNkzUt1Fn2ZMu--PyA8R2puYLNcxplH0LZDQr7NrX4MGIYJTg==",
  "generator": "Primus Java BYOK-Tool v1.2; GRIMSEL RX-2.9.0-T"
}
```
Proceed to import the `.byok` package into the Azure key vault.