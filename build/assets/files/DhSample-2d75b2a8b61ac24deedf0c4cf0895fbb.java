
/*
 * Copyright (C) 2015-2019, Securosys SA
 */

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Arrays;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.auth.Dh;

/**
 * Sample code for Diffie-Hellman (DH) key agreement
 * with the Securosys Primus HSM JCE provider.
 */
public class DhSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// for below easy test of generated HSM key equality: have keys extractable
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, false);

		final KeyPairGenerator kpg = KeyPairGenerator.getInstance("DH", PrimusProvider.getProviderName());
		// use predefined parameters instead of on-the-fly created ones (by size), as that takes long
		kpg.initialize(Dh.DH_RFC_5114_2048_224_parameters);
		final KeyPair kp1 = kpg.generateKeyPair();
		final KeyPair kp2 = kpg.generateKeyPair();

		boolean agreedKeysOnHsm = true;
		PrimusConfiguration.setKeyAgreementCreatesHsmBasedSecretKeys(agreedKeysOnHsm);

		final KeyAgreement ka = KeyAgreement.getInstance("DH", PrimusProvider.getProviderName());
		ka.init(kp1.getPrivate());
		ka.doPhase(kp2.getPublic(), true);
		final SecretKey key1 = ka.generateSecret("AES");

		ka.init(kp2.getPrivate());
		ka.doPhase(kp1.getPublic(), true);
		final SecretKey key2 = ka.generateSecret("AES");

		// test addition: extract and compare
		final Key key1e = KeyFactory.getInstance("AES", PrimusProvider.getProviderName()).translateKey(key1);
		final Key key2e = KeyFactory.getInstance("AES", PrimusProvider.getProviderName()).translateKey(key2);

		assert Arrays.equals(key1e.getEncoded(), key2e.getEncoded());

	}

}

