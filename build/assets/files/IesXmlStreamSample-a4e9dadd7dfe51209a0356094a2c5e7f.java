
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
public class IesXmlStreamSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", primusProvider);
		final ECGenParameterSpec curve = new ECGenParameterSpec("secp384r1");
		kpg.initialize(curve);
		final KeyPair targetKey = kpg.generateKeyPair();

		final String base = UUID.randomUUID().toString();
		final String input = base + ".input.out";
		final String encrypted = base + ".encrypted.out";
		final String decrypted = base + ".decrypted.out";

		// write random data to the input file
		try (final OutputStream o = FileStreams.blockingOutputStream(input)) {
			o.write(LocalSecureRandom.randomBytes(1 << 20));
		}

//		// [even higher level API]
//		// encrypt input file with target key
//		// the sender key will be local and ephemeral and encoded into the output
//		PrimusIes.encryptFor(PrimusIes.Mode.XML, targetKey.getPublic(), input, encrypted);

		try (final FileInputStream sourceStream = new FileInputStream(input)) {
			try (final FileOutputStream targetStream = new FileOutputStream(encrypted)) {
				final PrimusIes ies = new PrimusIes(PrimusIes.Mode.XML);
				ies.setCurve(curve);
				ies.initKey(targetKey.getPublic());
				ies.encryptStream(sourceStream, targetStream);
			}
		}

//		// [even higher level API]
//		// decrypt with target key (pair)
//		PrimusIes.decryptFrom(PrimusIes.Mode.XML, targetKey, encrypted, decrypted);

		try (final FileInputStream sourceStream = new FileInputStream(encrypted)) {
			try (final FileOutputStream targetStream = new FileOutputStream(decrypted)) {
				final PrimusIes ies = new PrimusIes(PrimusIes.Mode.XML);
				ies.initKeys(targetKey);
				ies.decryptStream(sourceStream, targetStream);
			}
		}

		// compare decrypted to input
		assert FileContents.equals(decrypted, input);

		// [cleanup]
		FileExistence.deleteIgnore(decrypted);
		FileExistence.deleteIgnore(encrypted);
		FileExistence.deleteIgnore(input);

	}

}

