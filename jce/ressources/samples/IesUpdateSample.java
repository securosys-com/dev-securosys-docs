
/*
 * Copyright (C) 2023, Securosys SA
 */

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

import javax.crypto.Cipher;

import com.securosys.primus.jce.PrimusIes;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Primus IES (Integrated Encryption Scheme) sample.
 */
public class IesUpdateSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", primusProvider);
		kpg.initialize(new ECGenParameterSpec("secp384r1"));
		final KeyPair targetKey = kpg.generateKeyPair();

		final byte[] input = "input".getBytes(StandardCharsets.UTF_8);

		// encrypt input with target key
		// the sender key will be local and ephemeral and encoded into the output
		final PrimusIes iesEncrypt = new PrimusIes();
		iesEncrypt.setCurve("secp384r1");
		iesEncrypt.initKey(targetKey.getPublic());
		iesEncrypt.initMode(Cipher.ENCRYPT_MODE);
		final byte[] encrypted = iesEncrypt.doFinal(input);

		// decrypt encrypted with target key
		final PrimusIes iesDecrypt = new PrimusIes();
		iesDecrypt.setCurve("secp384r1");
		iesDecrypt.initKeys(targetKey);
		iesDecrypt.initMode(Cipher.DECRYPT_MODE);
		final byte[] decrypted = iesDecrypt.doFinal(encrypted);

		// compare decrypted to input
		assert Arrays.equals(decrypted, input);

	}

}

