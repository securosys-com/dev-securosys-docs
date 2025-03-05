
/*
 * Copyright (C) 2019,2023, Securosys SA
 */

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusKey;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusKeyHelper;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs;

/**
 * Sample code for CKD/BIP32 seed import
 * with the Securosys Primus HSM JCE provider.
 */
public class CkdSeedImportSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		final byte[] seed = new byte[32];

		final PrimusSpecs.CkdSeedKey key = new PrimusSpecs.CkdSeedKey(seed, new ECGenParameterSpec("secp256k1"));

		PrimusName.presetName(UUID.randomUUID().toString());
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTERNAL_OBJECT, false);
		final Key internal = KeyFactory.getInstance("EC", primusProvider).translateKey(key);
		assert PrimusKeyHelper.isPrimusKey(internal);
		assert !((PrimusKey)internal).isExternal();

		PrimusName.presetName(UUID.randomUUID().toString());
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTERNAL_OBJECT, true);
		final Key external = KeyFactory.getInstance("EC", primusProvider).translateKey(key);
		assert PrimusKeyHelper.isPrimusKey(external);
		assert ((PrimusKey)external).isExternal();

	}

}

