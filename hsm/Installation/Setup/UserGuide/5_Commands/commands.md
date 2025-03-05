# Commands

## Firmware update ![](../../../../img/HSM/genesis_card.png)

```
hsm_sec_software_update
```


## License update ![](../../../../img/HSM/genesis_card.png)

```
hsm_sec_lic_update
```

## Export Security Configuration ![](../../../../img/HSM/so_card.png)
```
hsm_sec_export_config
```
=> Exporting the configuration to the USB is successful!

```
hsm_sec_import_config
```
=> Importing the configuration from the USB is successful!

## Export logs to USB ![](../../../../img/HSM/so_card.png)
```
logs
```
=> Logs are written on the USB stick!

## Setup and Install Root Key Store ![](../../../../img/HSM/genesis_card.png)

Please Note: <br />
    - The ***Genesis PIN** is required to install the `Root Key Store`.<br />
    - Ensure that you have copied the obtained **license file** to a USB stick.<br />
<br />
Insert the USB stick into the device before proceeding the following command:
```
hsm_sec_install_rke
hsm_sec_setup_rks
```

## Add new SO-Card ![](../../../../img/HSM/genesis_card.png)

```
hsm_sec_add_so_card
```

## Add new Genesis-Card ![](../../../../img/HSM/genesis_card.png)

```
hsm_sec_add_gen_card
```
