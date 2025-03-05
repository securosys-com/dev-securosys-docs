
/*
 * Copyright (C) 2018-2020, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs;

/**
 * Simple EC-CKD-DSA sample
 * for the Securosys Primus HSM JCE provider.
 */
public class EcCkdDsaSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final KeyPairGenerator primusKeyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdEnabled(new ECGenParameterSpec("secp256k1")));
		final KeyPair keyPair = primusKeyPairGenerator.generateKeyPair();

		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdGenParameterSpec(keyPair.getPrivate(), "0/0/0"));
		final KeyPair derivedKeyPair = primusKeyPairGenerator.generateKeyPair();

		final byte[] message = "test".getBytes();
		final Signature signature = Signature.getInstance("SHA256withECDSA", PrimusProvider.getProviderName());
		signature.initSign(derivedKeyPair.getPrivate());
		signature.update(message);
		final byte[] signatureBytes = signature.sign();

		signature.initVerify(derivedKeyPair.getPublic());
		signature.update(message);
		final boolean verified = signature.verify(signatureBytes);
		assert verified;

	}

}

