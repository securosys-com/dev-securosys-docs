
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;

import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusIes;
import com.securosys.primus.jce.PrimusKeyHelper;
import com.securosys.primus.jce.PrimusProvider;

import com.securosys.primus.jce.encoding.Kcv;

/**
 * Primus IES (Integrated Encryption Scheme) sample
 * for IES key agreement generated keys.
 * Private keys and secret keys remain on HSM.
 */
public class IesKeysHsmSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// generate a target/receiver key pair, on the HSM
		final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", primusProvider);
		final ECGenParameterSpec curve = new ECGenParameterSpec("secp256r1");
		kpg.initialize(curve);
		final KeyPair targetKey = kpg.generateKeyPair();
		assert targetKey != null;
		assert targetKey.getPrivate() != null;
		assert PrimusKeyHelper.isPrimusKey(targetKey.getPrivate());

		// instantiate a source/sender-IES, with HSM ephemeral key pair
		// instantiate for the KDF mode supported by HSM
		final PrimusIes sourceIes = new PrimusIes(PrimusIes.Mode.XML);
		sourceIes.init(null, null, 256, 256, null); // no random IVs
		sourceIes.setCryptoOnHsm(true); // have the ephemeral key pair on HSM, generate HSM based keys
		sourceIes.setCurve(curve);
		final PublicKey sourceKey = sourceIes.getPublicSenderKey();
		// init for target/receiver public key
		sourceIes.initKey(targetKey.getPublic());

		// print source/sender IES-relevant keys
		System.out.println("public sender key: " + PrimusEncoding.hexEncode(sourceKey.getEncoded()));
		// sender cipher and mac key materials not available outside HSM
		assert sourceIes.getCipherKey() != null;
		assert PrimusKeyHelper.isPrimusKey(sourceIes.getCipherKey());
		assert sourceIes.getCipherKey().getEncoded() == null;
		assert sourceIes.getMacKey() != null;
		assert PrimusKeyHelper.isPrimusKey(sourceIes.getMacKey());
		assert sourceIes.getMacKey().getEncoded() == null;

		// as we don't see the generated keys' bits, we use the 'Key Check Value' (KCV)
		System.out.println("sender cipher key KCV: " + Kcv.kcv((SecretKey)sourceIes.getCipherKey()));
		// System.out.println("sender mac key KCV: " + Kcv.kcv((SecretKey)sourceIes.getMacKey())); // KCV only supported for AES keys

		// instantiate a target/receiver-IES
		final PrimusIes targetIes = new PrimusIes(PrimusIes.Mode.XML);
		targetIes.init(null, null, 256, 256, null); // no random IVs
		targetIes.setCryptoOnHsm(true); // generate HSM based keys
		// init for target/receiver private jey and source/sender public key
		targetIes.initKeys(targetKey.getPrivate(), sourceKey);

		// target cipher and mac key materials not available outside HSM
		assert targetIes.getCipherKey() != null;
		assert PrimusKeyHelper.isPrimusKey(targetIes.getCipherKey());
		assert targetIes.getCipherKey().getEncoded() == null;
		assert targetIes.getMacKey() != null;
		assert PrimusKeyHelper.isPrimusKey(targetIes.getMacKey());
		assert targetIes.getMacKey().getEncoded() == null;

		System.out.println("receiver cipher key KCV: " + Kcv.kcv((SecretKey)targetIes.getCipherKey()));
		// System.out.println("receiver mac key KCV: " + Kcv.kcv((SecretKey)targetIes.getMacKey())); // KCV only supported for AES keys

		assert Kcv.kcv((SecretKey)targetIes.getCipherKey()).equals(Kcv.kcv((SecretKey)sourceIes.getCipherKey()));

	}

}

