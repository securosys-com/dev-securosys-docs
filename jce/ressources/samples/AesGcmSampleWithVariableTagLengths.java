
/*
 * Copyright (C) 2023, Securosys SA
 */

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import com.securosys.primus.jce.PrimusProvider;

public class AesGcmSampleWithVariableTagLengths {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		for (int taglength = -256; taglength <= 1024; taglength++) {
			try {
				test(taglength);
				System.out.println("taglength " + taglength + ": OK");
			} catch (final Exception e) {
				//
			}
		}

	}

	static void test(final int taglength) throws Exception {

		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		final byte[] input = new byte[128];
		(new SecureRandom()).nextBytes(input);

		// AES key
		final KeyGenerator kg = KeyGenerator.getInstance("AES", primusProvider);
		kg.init(256);
		final SecretKey k = kg.generateKey();

		// cipher
		final Cipher gcm = Cipher.getInstance("AES/GCM/NoPadding", primusProvider);
		final byte[] iv = new byte[12];
		(new SecureRandom()).nextBytes(iv);
		final GCMParameterSpec gcmp = new GCMParameterSpec(taglength, iv);

		// encryption
		gcm.init(Cipher.ENCRYPT_MODE, k, gcmp);
		final byte[] gcmEnc = gcm.doFinal(input);

		// decryption
		gcm.init(Cipher.DECRYPT_MODE, k, gcmp);
		final byte[] gcmDec = gcm.doFinal(gcmEnc);

		// [checks]
		assert Arrays.equals(gcmDec, input);

	}

}

