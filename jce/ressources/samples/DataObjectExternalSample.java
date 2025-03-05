
/*
 * Copyright (C) 2019,2023, Securosys SA
 */

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

import com.securosys.primus.jce.PrimusExternal;
import com.securosys.primus.jce.PrimusKeyFields;

/**
 * Sample code using data objects
 * with the Securosys Primus HSM JCE provider.
 */
public class DataObjectExternalSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final byte[] dataObjectValue = UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8);

		// set
		final PrimusKeyFields.ExternalDataObject externalDataObject = PrimusKeyFields.setDataObject(dataObjectValue);

		// get external object data
		// this data would be written to disk or database etc, and read back when needed
		final byte[] dataData = PrimusExternal.dataToData(externalDataObject);
		assert dataData != null && dataData.length > 0;

		// reconstruct object data
		final PrimusKeyFields.ExternalDataObject externalDataObjectBack = PrimusExternal.dataFromData(dataData);
		assert externalDataObjectBack != null;

		// retrieve
		final byte[] dataObjectValueBack = PrimusKeyFields.getDataObject(externalDataObjectBack);

		assert Arrays.equals(dataObjectValueBack, dataObjectValue);

	}

}

