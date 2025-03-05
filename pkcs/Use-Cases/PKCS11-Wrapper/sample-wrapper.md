---
sidebar_position: 1
title: PKCS#11 - Wrapper
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

PKCS#11 is a cryptographic token interface standard that defines a platform-independent API for managing cryptographic objects, such as keys and certificates, and performing cryptographic operations, such as encryption and decryption. In this documentation, we'll explore the use case of PKCS#11 AES encryption and decryption using various programming languages and PKCS#11 wrappers.

The use case revolves around securing sensitive data through AES encryption and decryption operations using PKCS#11-compliant cryptographic tokens. This ensures that cryptographic operations are performed securely within the PrimusHSM, protecting the confidentiality and integrity of the data.

## Sample Code
Below is a sample code snippet demonstrating **AES encryption and decryption** using **PKCS#11 wrappers** in different programming languages:

<Tabs groupId="key-provider">
    <TabItem value="Python" label="Python" default>
    In python you can use a high level, "more Pythonic" interface to the PKCS#11 (Cryptoki) standard to support PrimusHSM in Python: [Python-Pkcs11](https://pypi.org/project/python-pkcs11/)

    API Reference can be found here: https://python-pkcs11.readthedocs.io/en/latest/api.html
    ```py
    import pkcs11
    import os
    from base64 import b64encode, b64decode
    import uuid

    SECURDEN_KEY_LABEL_IN_HSM = 'LLB_HSM_TEST'

    lib_path = "C:\\Program Files\\Securosys\\Primus P11\\primusP11.dll"

    slot_id = 0

    slot_password = 'PRIMUSDEV' # the default password when working on grimsel

    def encrypt_decrypt():
        session = get_hsm_session()
        keyName = str(uuid.uuid4())    
        generate_key(session, keyName)
        payload = b'INPUT DATA'
        ciphertext, iv = encrypt_payload(session, keyName, payload)
        decrypt_payload(session, b64decode(ciphertext), b64decode(iv), keyName)
        session.close()


    def generate_key(session, keyName):
        session.generate_key(pkcs11.KeyType.AES, key_length=256, label=keyName, store=True, template={pkcs11.Attribute.EXTRACTABLE: False, pkcs11.Attribute.SENSITIVE: True})
        print("AES key generated with key_length 256 and name: ", keyName)
        print("Encrypt key stored")
        return keyName


    def encrypt_payload(session, keyName, payload):
        encrypt_key = load_key_from_keyName(keyName, session)

        # Get an initialisation vector
        iv = session.generate_random(128)  # AES blocks are fixed at 128 bits
        # Encrypt our data
        ciphertext = encrypt_key.encrypt(payload, mechanism_param=iv)

        print("Encrypt payload '" + str(payload) + "' with Key: " + keyName)    
        return b64encode(ciphertext), b64encode(iv)


    def decrypt_payload(session, ciphertext, iv, keyName):
        decrypt_key = load_key_from_keyName(keyName, session)

        # Encrypt our data
        print("Decrypt ciphertext with Key: " + keyName)
        plaintext = decrypt_key.decrypt(ciphertext, mechanism_param=iv)
        print("Plaintext: " + str(plaintext))


    def load_key_from_keyName(keyName, session):
        key = session.get_key(object_class=pkcs11.ObjectClass.SECRET_KEY, label=keyName)
        return key


    def get_hsm_session():
        try:
            lib = pkcs11.lib(lib_path)
            slots = lib.get_slots(token_present=True)
            actual_slot = None
            actual_slot_id = slot_id
            for each_slot in slots:
                if actual_slot_id == each_slot.slot_id:
                    actual_slot = each_slot
                    break
            token = actual_slot.get_token()
            session = token.open(rw=True, user_pin=slot_password)
            return session
        except Exception as e:
            print("Exception occurred while creating session")

    encrypt_decrypt()
    ```

    Execute the Python code as: **`python hsmop.py`**, make sure you have installed the dependency pkcs11 before.
    ```
    pip install python-pkcs11
    ```
    </TabItem>
</Tabs>