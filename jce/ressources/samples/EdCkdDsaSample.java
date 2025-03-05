
/*
 * Copyright (C) 2021, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs;

/**
 * Simple Ed-CKD-DSA samples
 * for the Securosys Primus HSM JCE provider.
 */
public class EdCkdDsaSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// generate a base key ; use PrimusSpecs.CkdEnabled to allow CKD key derivals
		final KeyPairGenerator primusKeyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdEnabled(new ECGenParameterSpec("ed25519")));
		final KeyPair baseKeyPair = primusKeyPairGenerator.generateKeyPair();

		// generate a derived key ; use PrimusSpecs.CkdGenParameterSpec and a derivation string
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdGenParameterSpec(baseKeyPair.getPrivate(), "0/0/0"));
		final KeyPair derivedKeyPair = primusKeyPairGenerator.generateKeyPair();

		// sign with derived private key
		final byte[] message = "test".getBytes();
		final Signature signature = Signature.getInstance("EdDSA", PrimusProvider.getProviderName());
		signature.initSign(derivedKeyPair.getPrivate());
		signature.update(message);
		byte[] signatureBytes = signature.sign();

		// verify with derived public key
		signature.initVerify(derivedKeyPair.getPublic());
		signature.update(message);
		boolean verified = signature.verify(signatureBytes);
		assert verified;

		// derived-sign directly with base private key and derivation string
		signature.initSign(new PrimusSpecs.CkdDerivedPrivateKey(baseKeyPair.getPrivate(), "2/2/2"));
		signature.update(message);
		signatureBytes = signature.sign();

		// derived-verify directly with base public key and derivation string
		signature.initVerify(new PrimusSpecs.CkdDerivedPublicKey(baseKeyPair.getPublic(), "2/2/2"));
		signature.update(message);
		verified = signature.verify(signatureBytes);
		assert verified;

	}

}

