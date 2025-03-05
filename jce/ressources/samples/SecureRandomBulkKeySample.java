
/*
 * Copyright (C) 2020,2021, Securosys SA
 * Author Eric Laroche <eric@securosys.ch>
 */

import java.security.SecureRandom;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code illustrating the use of Primus JCE's SecureRandom,
 * using the Primus HSM as random bytes source.
 */
public class SecureRandomBulkKeySample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final int keySizeBytes = 256 / Byte.SIZE; // typical AES size
		final int numberOfKeys = 1000; // reasonable chunk: keeps the return data well below 60kbytes

		// retrieve Primus random bits
		final SecureRandom secureRandom = SecureRandom.getInstance(PrimusProvider.getSecureRandomTypeName(), PrimusProvider.getProviderName());
		final byte[] randomBytes = new byte[numberOfKeys * keySizeBytes];
		secureRandom.nextBytes(randomBytes);

		// make local AES keys
		for (int i = 0; i < numberOfKeys; i++) {
			final int offset = i * keySizeBytes;
			final SecretKey aesKey = new SecretKeySpec(randomBytes, offset, keySizeBytes, "AES");
			assert aesKey != null;
		}

	}

}

