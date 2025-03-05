
/*
 * Copyright (C) 2019, Securosys SA
 */

import java.security.Key;
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
 * Sample code for Diffie-Hellman (DH) key agreement with Edwards Curve25519
 * with the Securosys Primus HSM JCE provider.
 */
public class EdDhSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final String curve = "curve25519"; // "curve25519" (alias "x25519") is for EdDH ("ed25519" is for EdDSA)

		// for below easy test of generated HSM key equality: have keys extractable
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, false);

		final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		kpg.initialize(new ECGenParameterSpec(curve));
		final KeyPair kp1 = kpg.generateKeyPair();
		final KeyPair kp2 = kpg.generateKeyPair();

		boolean agreedKeysOnHsm = true;
		PrimusConfiguration.setKeyAgreementCreatesHsmBasedSecretKeys(agreedKeysOnHsm);

		final KeyAgreement ka = KeyAgreement.getInstance("ECDH", PrimusProvider.getProviderName());
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

