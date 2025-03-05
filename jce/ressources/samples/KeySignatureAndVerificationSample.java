
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
import java.util.Arrays;
import java.util.UUID;

/**
 * Sample code showing how to create a key, perform a signature operation with it and verify the signature.
 * The sample goes through the following steps:
 * 1) Logs into the HSM
 * 2) Creates a signature key in the HSM
 * 3) Retrieves the key pair from the HSM
 * 4) Requests the HSM to sign a payload
 * 5) Verifies the signature using the public key
 */
public class KeySignatureAndVerificationSample {

	public static void main(final String... args) throws Exception {

		// 1 -- HSM AND PROVIDER CONFIGURATION

		// HSM configuration
		PrimusHelper.setup(args);

		// 2 -- CREATE A SIGNATURE KEY

		final int keySize = 2048; // keySize (in case of RSA)

		System.out.println("creating RSA key pair of " + keySize + " bits ...");

		// create an RSA key, in this case with randomly set name
		final String signKeyName = UUID.randomUUID().toString();
		PrimusName.generate(
			KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName()), // algorithm specification
			keySize, // keySize
			new Certificate[0], // necessary to be able to get public key from key store
			signKeyName // name to look the key up with from the key store
		);

		System.out.println("Signature key created");

		// 3 -- RETRIEVE THE SIGNATURE KEY

		final KeyStore primusKeyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		primusKeyStore.load(null);

		// retrieve private key (HSM pointer), and public key for local verification
		final PrivateKey signKey = (PrivateKey)primusKeyStore.getKey(signKeyName, null);
		final PublicKey signKeyPublic = primusKeyStore.getCertificate(signKeyName).getPublicKey();

		// 4 -- SIGN A PAYLOAD

		// random payload - in real life it would be e.g. a cryptocurrency transaction
		final int payloadSize = 250;
		final byte[] payload = new byte[payloadSize];
		Arrays.fill(payload, (byte)'A');

		final String signAlgorithm = "SHA256withRSA";
		// final String signAlgorithm2 = "SHA256withRSA/PSS";
		// final String signAlgorithm3 = "RSASSA-PSS";
		// final PSSParameterSpec pssParameterSpec = new PSSParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-256"), (256 / Byte.SIZE), PSSParameterSpec.TRAILER_FIELD_BC);

		// request the payload signature
		final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initSign(signKey);
		// signature.setParameter(pssParameterSpec);
		signature.update(payload);
		final byte[] sig = signature.sign();
		System.out.println("Signature successful");

		// 5 -- VERIFY THE SIGNATURE

		final Signature localVerifier = Signature.getInstance(signAlgorithm);
		localVerifier.initVerify(signKeyPublic);
		// localVerifier.setParameter(pssParameterSpec);
		localVerifier.update(payload);
		final boolean signSignatureOk = localVerifier.verify(sig);
		System.out.println("Sign signature is " + (signSignatureOk ? "OK" : "NOT OK"));

	}

}

