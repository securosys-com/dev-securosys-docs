
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.io.OutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusIes;
import com.securosys.primus.jce.PrimusProvider;

import com.securosys.primus.jce.file.FileContents;
import com.securosys.primus.jce.file.FileExistence;
import com.securosys.primus.jce.file.FileStreams;
import com.securosys.primus.jce.rand.LocalSecureRandom;

/**
 * Primus IES (Integrated Encryption Scheme) sample.
 * Using XML/P11 interop mode.
 */
public class IesXmlPerfSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", primusProvider);
		kpg.initialize(new ECGenParameterSpec("secp384r1"));
		final KeyPair targetKey = kpg.generateKeyPair();

		final String base = UUID.randomUUID().toString();
		final String input = base + ".input.out";
		final String encrypted = base + ".encrypted.out";
		final String decrypted = base + ".decrypted.out";

		final long size = (1 << 30);
		final int chunk = (1 << 20);

		System.out.println("writing " + size + " bytes random data");
		long start = System.currentTimeMillis();
		// write random data to the input file
		try (final OutputStream o = FileStreams.blockingOutputStream(input)) {
			for (long n = 0; n < size; n += chunk) {
				o.write(LocalSecureRandom.randomBytes(chunk));
			}
		}
		long delta = System.currentTimeMillis() - start;
		System.out.println("done, " + delta + " ms");

		System.out.println("encrypting");
		start = System.currentTimeMillis();
		// encrypt input file with target key
		// the sender key will be local and ephemeral and encoded into the output
		PrimusIes.encryptFor(PrimusIes.Mode.XML, targetKey.getPublic(), input, encrypted);
		delta = System.currentTimeMillis() - start;
		System.out.println("done, " + delta + " ms");

		System.out.println("decrypting");
		start = System.currentTimeMillis();
		// decrypt with target key (pair)
		PrimusIes.decryptFrom(targetKey, encrypted, decrypted);
		delta = System.currentTimeMillis() - start;
		System.out.println("done, " + delta + " ms");

		// compare decrypted to input
		assert FileContents.equals(decrypted, input);

		// [cleanup]
		FileExistence.deleteIgnore(decrypted);
		FileExistence.deleteIgnore(encrypted);
		FileExistence.deleteIgnore(input);

	}

}

