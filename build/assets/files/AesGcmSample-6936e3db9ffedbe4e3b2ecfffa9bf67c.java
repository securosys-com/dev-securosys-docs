
/*
 * Copyright (C) 2023, Securosys SA
 */

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusProvider;

public class AesGcmSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		final int inputSize = 128;
		System.out.println("generating " + inputSize + " bytes input");
		final byte[] input = new byte[inputSize];
		(new SecureRandom()).nextBytes(input);
		System.out.println(PrimusEncoding.base64Encode(input) + " (" + input.length + " bytes)");

		// generate an AES key
		final KeyGenerator kg = KeyGenerator.getInstance("AES", primusProvider);
		kg.init(256);
		final SecretKey k = kg.generateKey();

		// encryption/decryption cipher
		final Cipher gcm = Cipher.getInstance("AES/GCM/NoPadding", primusProvider);
		final int ivSize = 12;
		System.out.println("generating " + ivSize + " bytes IV");
		final byte[] iv = new byte[ivSize];
		(new SecureRandom()).nextBytes(iv);
		System.out.println(PrimusEncoding.base64Encode(iv) + " (" + iv.length + " bytes)");
		final int tagLength = 16;
		final GCMParameterSpec gcmp = new GCMParameterSpec(tagLength * 8, iv);

		// encryption
		System.out.println("encrypting");
		gcm.init(Cipher.ENCRYPT_MODE, k, gcmp);
		final byte[] gcmEnc = gcm.doFinal(input);
		System.out.println(PrimusEncoding.base64Encode(gcmEnc) + " (" + gcmEnc.length + " bytes)");
		assert gcmEnc.length == input.length + tagLength;
		final byte[] gcmEncTagFree = Arrays.copyOfRange(gcmEnc, 0, gcmEnc.length - tagLength);
		final byte[] gcmEncTag = Arrays.copyOfRange(gcmEnc, gcmEnc.length - tagLength, gcmEnc.length);
		System.out.println(PrimusEncoding.base64Encode(gcmEncTagFree) + " (" + gcmEncTagFree.length + " bytes)");
		System.out.println(PrimusEncoding.base64Encode(gcmEncTag) + " (" + gcmEncTag.length + " bytes)");

		// decryption
		System.out.println("decrypting");
		gcm.init(Cipher.DECRYPT_MODE, k, gcmp);
		final byte[] gcmDec = gcm.doFinal(gcmEnc);
		System.out.println(PrimusEncoding.base64Encode(gcmDec) + " (" + gcmDec.length + " bytes)");
		assert gcmDec.length == input.length;
		assert Arrays.equals(gcmDec, input);

	}

}

