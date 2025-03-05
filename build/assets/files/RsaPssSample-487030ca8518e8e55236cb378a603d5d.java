
/*
 * Copyright (C) 2019, Securosys SA
 */

import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;

import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.Arrays;
import java.util.UUID;

/**
 * RSA/PSS sample, code showing how to use RSA/PSS.
 */
public class RsaPssSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// create an RSA key, in this case with randomly set name
		final String signKeyName = UUID.randomUUID().toString();
		PrimusName.generate(
			KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName()), // algorithm specification
			2048, // keySize
			new Certificate[0], // necessary to be able to get public key from key store by means of pseudo-ceritficate
			signKeyName // name to look the key up with from the key store
		);

		// retrieve private key (HSM pointer), and public key for local verification
		final KeyStore primusKeyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		primusKeyStore.load(null);
		final PrivateKey signKey = (PrivateKey)primusKeyStore.getKey(signKeyName, null);
		final PublicKey signKeyPublic = primusKeyStore.getCertificate(signKeyName).getPublicKey();

		// some payload to sign
		final byte[] payload = new byte[123];
		Arrays.fill(payload, (byte)'A');

		// final String signAlgorithm = "SHA256withRSA"; // old, non-PSS, mode
		final String signAlgorithmPss = "SHA256withRSA/PSS";

		// request the payload signature
		final Signature signature = Signature.getInstance(signAlgorithmPss, PrimusProvider.getProviderName());
		signature.initSign(signKey);
		// signature.setParameter(pssParameterSpec);
		signature.update(payload);
		final byte[] sig = signature.sign();

		// Sun's PSS requires a PSSParameterSpec
		final String signAlgorithmPss2 = "RSASSA-PSS";
		// the two digests have to be the same, the salt length has to match the digest output length, only MGF1 and default trailer field length are supported
		final PSSParameterSpec pssParameterSpec = new PSSParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-256"), (256 / Byte.SIZE), PSSParameterSpec.TRAILER_FIELD_BC);

		final Signature localVerifier = Signature.getInstance(signAlgorithmPss2);
		localVerifier.initVerify(signKeyPublic);
		localVerifier.setParameter(pssParameterSpec);
		localVerifier.update(payload);
		final boolean signSignatureOk = localVerifier.verify(sig);
		System.out.println("Sign signature is " + (signSignatureOk ? "OK" : "NOT OK"));

	}

}

