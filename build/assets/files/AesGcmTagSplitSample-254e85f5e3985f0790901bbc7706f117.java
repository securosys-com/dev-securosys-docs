
/*
 * Copyright (C) 2023, Securosys SA
 */

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.encoding.Concat;

public class AesGcmTagSplitSample {

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

		assert Arrays.equals(gcmDecrypt(k, iv, gcmEncrypt(k, iv, input)), input);
	}

	public static byte[][] gcmEncrypt(final SecretKey aesKey, final byte[] iv, final byte[] input) throws BadPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		final Cipher gcm = Cipher.getInstance("AES/GCM/NoPadding", PrimusProvider.findInstalledOrLastOrNewProvider());
		final int tagLength = 16;
		final GCMParameterSpec gcmp = new GCMParameterSpec(tagLength * 8, iv);

		gcm.init(Cipher.ENCRYPT_MODE, aesKey, gcmp);
		final byte[] gcmEnc = gcm.doFinal(input);
		final byte[] gcmEncTagFree = Arrays.copyOfRange(gcmEnc, 0, gcmEnc.length - tagLength);
		final byte[] gcmEncTag = Arrays.copyOfRange(gcmEnc, gcmEnc.length - tagLength, gcmEnc.length);

		return new byte[][] {gcmEncTagFree, gcmEncTag,};
	}

	public static byte[] gcmDecrypt(final SecretKey aesKey, final byte[] iv, final byte[][] encryptedAndTag) throws BadPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		final Cipher gcm = Cipher.getInstance("AES/GCM/NoPadding", PrimusProvider.findInstalledOrLastOrNewProvider());
		final int tagLength = 16;
		final GCMParameterSpec gcmp = new GCMParameterSpec(tagLength * 8, iv);

		gcm.init(Cipher.DECRYPT_MODE, aesKey, gcmp);
		final byte[] gcmDec = gcm.doFinal(Concat.cat(encryptedAndTag[0], encryptedAndTag[1]));

		return gcmDec;
	}

}

