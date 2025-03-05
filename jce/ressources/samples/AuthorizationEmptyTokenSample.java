
/*
 * Copyright (C) 2020, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;

public class AuthorizationEmptyTokenSample {

	/**
	 * Test SKA (smart key attributes) with empty access.
	 */
	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// empty access: blobs of one empty token
		final PrimusAccessToken token = new PrimusAccessToken();
		final PrimusAccessBlob blob = new PrimusAccessBlob(token);
		final PrimusAccess access = new PrimusAccess(blob, blob, blob, blob);

		// crate SKA key
		final String signKeyName = UUID.randomUUID().toString();
		final KeyPair signKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			// new ECGenParameterSpec("secp256k1"), // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
			new ECGenParameterSpec("secp256r1"),
			signKeyName,
			access
		);
		final PrivateKey signKey = signKeyPair.getPrivate();
		final PublicKey signKeyPublic = signKeyPair.getPublic();

		final byte[] payload = new byte[256];
		final SecureRandom localSecureRandom = new SecureRandom();
		localSecureRandom.nextBytes(payload);

		// empty authorization
		// [PrimusAuthorization is needed to let the SKA key type be treated as such]
		final PrimusAuthorization primusAuthorization = new PrimusAuthorization();
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);

		// sign
		final String signAlgorithm = "SHA256withECDSA";
		final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initSign(signKey);
		signature.update(payload);
		final byte[] sig = signature.sign();

		// verify locally
		final Signature localVerifyer = Signature.getInstance(signAlgorithm);
		localVerifyer.initVerify(signKeyPublic);
		localVerifyer.update(payload);
		final boolean signSignatureOk = localVerifyer.verify(sig);
		System.out.println("sign signature is " + (signSignatureOk ? "OK" : "NOT OK"));

	}

}

