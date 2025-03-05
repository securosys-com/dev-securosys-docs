
/*
 * Copyright (C) 2019, Securosys SA
 */

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.UUID;

import javax.crypto.KeyGenerator;

import com.securosys.primus.jce.PrimusKeyFields;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusProviderException;

/**
 * Sample code using data objects and key fields
 * with the Securosys Primus HSM JCE provider.
 */
public class DataObjectSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// stand-alone DataObject sample:

		final String dataObjectName = UUID.randomUUID().toString();
		final byte[] dataObjectValue = UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8);

		// set
		PrimusKeyFields.setDataObject(dataObjectName, dataObjectValue);

		// retrieve
		byte[] value = PrimusKeyFields.getDataObject(dataObjectName);
		assert Arrays.equals(dataObjectValue, value);

		// delete
		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keyStore.load(null);
		keyStore.deleteEntry(dataObjectName);
		try {
			PrimusKeyFields.getDataObject(dataObjectName);
			assert false;
		} catch (PrimusProviderException e) {
			//
		}

		// key-attached SharedDataObject sample:

		final String keyName = UUID.randomUUID().toString();
		final byte[] keyObjectValue = UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8);

		// create key to attach to
		final Key key = KeyGenerator.getInstance("AES", PrimusProvider.getProviderName()).generateKey();
		keyStore.setKeyEntry(keyName, key, null, null);

		// 0 to 127 (inclusive) are reserved, 128 to 255 (inclusive) can be used by applications
		final int keyObjectIndex = 128;

		// set
		PrimusKeyFields.setKeyValue(key, keyObjectValue, keyObjectIndex);

		// retrieve
		value = PrimusKeyFields.getKeyValue(key, keyObjectIndex);
		assert Arrays.equals(keyObjectValue, value);

		// delete
		keyStore.deleteEntry(keyName);

		try {
			PrimusKeyFields.getKeyValue(key, keyObjectIndex);
			assert false;
		} catch (PrimusProviderException e) {
			//
		}

	}

}

