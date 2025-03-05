
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;

import com.securosys.primus.jce.rand.LocalSecureRandom;

public class AesLocalPerfSample {

	static void report(final long start, final long stop, final long size, final String what) {
		final long nanos = stop - start;
		final double seconds = nanos / (double)1e9;
		final double megas = size / (double)(1 << 20);
		final double rate = megas / seconds;
		System.out.println(
			what + ": "
			// + seconds + " s, "
			+ rate + " Mbytes/s"
		);
	}

	public static void main(final String... args) throws Exception {

		// local AES key
		final KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(256);
		final SecretKey k = kg.generateKey();

		// local ciphers
		final Cipher cbc = Cipher.getInstance("AES/CBC/PKCS5Padding");
		final Cipher gcm = Cipher.getInstance("AES/GCM/NoPadding");
		final IvParameterSpec ivp = new IvParameterSpec(new byte[16]);
		final GCMParameterSpec gcmp = new GCMParameterSpec(128, new byte[12]);

		// sizes
		final int size = (1 << 28);
		final double megas = (double)size / (1 << 20);
		System.out.println("testing with " + megas + " Mbytes");

		long start, stop;

		start = System.nanoTime();
		final byte[] input = LocalSecureRandom.randomBytes(size);
		stop = System.nanoTime();
		report(start, stop, size, "random data");

		// CBC

		// encryption
		cbc.init(Cipher.ENCRYPT_MODE, k, ivp);
		start = System.nanoTime();
		final byte[] cbcEnc = cbc.doFinal(input);
		stop = System.nanoTime();
		report(start, stop, size, "CBC encrypt");

		// decryption
		cbc.init(Cipher.DECRYPT_MODE, k, ivp);
		start = System.nanoTime();
		final byte[] cbcDec = cbc.doFinal(cbcEnc);
		stop = System.nanoTime();
		report(start, stop, size, "CBC decrypt");

		// GCM

		// encryption
		gcm.init(Cipher.ENCRYPT_MODE, k, gcmp);
		start = System.nanoTime();
		final byte[] gcmEnc = gcm.doFinal(input);
		stop = System.nanoTime();
		report(start, stop, size, "GCM encrypt");

		// decryption
		gcm.init(Cipher.DECRYPT_MODE, k, gcmp);
		start = System.nanoTime();
		final byte[] gcmDec = gcm.doFinal(gcmEnc);
		stop = System.nanoTime();
		report(start, stop, size, "GCM decrypt");

		// [checks]
		assert Arrays.equals(cbcDec, input);
		assert Arrays.equals(gcmDec, input);

	}

}

