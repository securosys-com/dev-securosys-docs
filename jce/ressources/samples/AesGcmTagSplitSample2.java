
/*
 * Copyright (C) 2023, Securosys SA
 */

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusGcm;
import com.securosys.primus.jce.PrimusProvider;

public class AesGcmTagSplitSample2 {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		final int inputSize = 128;
		final byte[] input = new byte[inputSize];
		(new SecureRandom()).nextBytes(input);

		// generate an AES key
		final KeyGenerator kg = KeyGenerator.getInstance("AES", primusProvider);
		kg.init(256);
		final SecretKey k = kg.generateKey();

		final int ivSize = 12;
		final byte[] iv = new byte[ivSize];
		(new SecureRandom()).nextBytes(iv);

		final byte[][] tag = new byte[][] {null};
		final byte[] gcmEnc = PrimusGcm.gcmEncryptWithTag(k, iv, input, tag);
		final byte[] gcmDec = PrimusGcm.gcmDecryptWithTag(k, iv, gcmEnc, tag[0]);

		assert Arrays.equals(gcmDec, input);

	}

}

