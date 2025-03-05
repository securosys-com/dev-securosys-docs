
/*
 * Copyright (C) 2023, Securosys SA
 */

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusWrap;

import com.securosys.primus.jce.PrimusSpecs.EdPrivateKey;

/**
 * Sample code showing wrapping of HSM keys with HSM keys
 * with the Securosys Primus HSM JCE provider.
 */
public class EdWrapExportSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// generate AES key to wrap/unwrap with, 256 bit size
		final SecretKey aesKey = (SecretKey)PrimusName.generate(
			KeyGenerator.getInstance("AES", primusProvider),
			256
		);

		// for plain export test comparing keys before/after wrap/unwrap
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, false);

		// generate an Ed key [pair] to be wrapped/unwrapped
		final EdPrivateKey edKey = (EdPrivateKey)((KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", primusProvider),
			new ECGenParameterSpec("ed25519"),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true)
		)).getPrivate();

		// wrap Ed
		final byte[] wrapped = PrimusWrap.aesWrapEd(aesKey, edKey);

		// unwrap
		final EdPrivateKey unwrappedEdKey = PrimusWrap.aesUnwrapEd(aesKey, wrapped);

		assert Arrays.equals(
			KeyFactory.getInstance("EC", primusProvider).translateKey(edKey).getEncoded(),
			KeyFactory.getInstance("EC", primusProvider).translateKey(unwrappedEdKey).getEncoded()
		);

	}

}

