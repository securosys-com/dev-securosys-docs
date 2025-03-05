
/*
 * Copyright (C) 2019,2021, Securosys SA
 */

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code for Diffie-Hellman (DH) key agreement with EC
 * with the Securosys Primus HSM JCE provider.
 */
public class EcDhSample {

	static PrimusProvider primusProvider;

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		primusProvider = PrimusHelper.primusProvider;

		// test with local and HSM based key generation

		test(false);
		test(true);

	}

	static void test(final boolean agreedKeysOnHsm) throws Exception {

		final String curve = "secp256r1";

		// for below easy test of generated HSM key equality: have keys extractable
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, false);

		// generate two key pairs on HSM
		final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", primusProvider);
		kpg.initialize(new ECGenParameterSpec(curve));
		final KeyPair kp1 = kpg.generateKeyPair();
		final KeyPair kp2 = kpg.generateKeyPair();

		PrimusConfiguration.setKeyAgreementCreatesHsmBasedSecretKeys(agreedKeysOnHsm);

		// do two key agreements, generating AES keys, which should be the same
		final KeyAgreement ka = KeyAgreement.getInstance("ECDH", primusProvider);
		ka.init(kp1.getPrivate());
		ka.doPhase(kp2.getPublic(), true);
		SecretKey key1 = ka.generateSecret("AES");

		ka.init(kp2.getPrivate());
		ka.doPhase(kp1.getPublic(), true);
		SecretKey key2 = ka.generateSecret("AES");

		if (agreedKeysOnHsm) {
			// test addition: extract to compare
			key1 = (SecretKey)KeyFactory.getInstance("AES", primusProvider).translateKey(key1);
			key2 = (SecretKey)KeyFactory.getInstance("AES", primusProvider).translateKey(key2);
		}

		// compare
		assert Arrays.equals(key1.getEncoded(), key2.getEncoded());

	}

}

