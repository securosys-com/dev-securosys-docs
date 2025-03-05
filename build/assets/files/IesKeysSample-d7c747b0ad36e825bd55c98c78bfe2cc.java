
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusIes;
import com.securosys.primus.jce.PrimusKeyHelper;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Primus IES (Integrated Encryption Scheme) sample
 * for IES key agreement generated keys.
 */
public class IesKeysSample {

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

		// instantiate a source/sender-IES, with local ephemeral key pair
		final PrimusIes sourceIes = new PrimusIes(PrimusIes.Mode.SPLIT);
		final PublicKey sourceKey = sourceIes.getPublicSenderKey();
		// init for target/receiver public key
		sourceIes.initKey(targetKey.getPublic());

		// print source/sender IES-relevant keys
		System.out.println("public sender key: " + PrimusEncoding.hexEncode(sourceKey.getEncoded()));
		System.out.println("sender cipher key material: " + PrimusEncoding.hexEncode(sourceIes.getCipherKeyMaterial()));
		System.out.println("sender mac key material: " + PrimusEncoding.hexEncode(sourceIes.getMacKeyMaterial()));

		// instantiate a target/receiver-IES
		final PrimusIes targetIes = new PrimusIes(PrimusIes.Mode.SPLIT);
		// init for target/receiver private jey and source/sender public key
		targetIes.initKeys(targetKey.getPrivate(), sourceKey);

		// print target/receiver IES-relevant keys
		System.out.println("receiver cipher key material: " + PrimusEncoding.hexEncode(targetIes.getCipherKeyMaterial()));
		System.out.println("receiver mac key material: " + PrimusEncoding.hexEncode(targetIes.getMacKeyMaterial()));

		assert Arrays.equals(targetIes.getCipherKeyMaterial(), sourceIes.getCipherKeyMaterial());
		assert Arrays.equals(targetIes.getMacKeyMaterial(), sourceIes.getMacKeyMaterial());

	}

}

