
/*
 * Copyright (C) 2021, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusBulk;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code illustrating the use of PrimusBulk.
 */
public class BulkKeySample {

	public static void main(final String... args) throws Exception {

		// HSM configuration

		PrimusHelper.setup(args);

		// make AES keys
		final KeyGenerator kg = KeyGenerator.getInstance("AES", PrimusProvider.getProviderName());
		final int n = 1000;
		final int aesKeySize = 256;

		System.out.println(n + " AES keys...");
		long start = System.currentTimeMillis();
		final Object[] aesKeys = (Object[])PrimusBulk.generate(n, kg, aesKeySize);
		long end = System.currentTimeMillis();
		System.out.println((end - start) + " ms");

		// check if all went well
		assert aesKeys != null;
		for (final Object aesKey : aesKeys) {
			assert aesKey != null;
			if (aesKey instanceof Throwable) {
				throw new RuntimeException((Throwable)aesKey);
			}
			assert aesKey instanceof SecretKey;
		}

		// make EX keys
		final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		final ECGenParameterSpec ecCurve = new ECGenParameterSpec("secp256r1");

		System.out.println(n + " EC keys...");
		start = System.currentTimeMillis();
		final Object[] ecKeys = (Object[])PrimusBulk.generate(n, kpg, ecCurve);
		end = System.currentTimeMillis();
		System.out.println((end - start) + " ms");

		// check if all went well
		assert ecKeys != null;
		for (final Object ecKey : ecKeys) {
			assert ecKey != null;
			if (ecKey instanceof Throwable) {
				throw new RuntimeException((Throwable)ecKey);
			}
			assert ecKey instanceof KeyPair;
			assert ((KeyPair)ecKey).getPrivate() instanceof ECPrivateKey;
		}

	}

}

