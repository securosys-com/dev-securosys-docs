
/*
 * Copyright (C) 2020,2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import com.securosys.primus.jce.PrimusExternal;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;

public class ExternalKeySample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// create an external key (pair)
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTERNAL_OBJECT, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_TOKEN, false);

		// RSA, size 2048 bits
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName());
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		assert keyPair != null;

		PrivateKey privateKey = keyPair.getPrivate();
		assert privateKey != null;
		assert privateKey.getEncoded() == null;

		// public key is already it (Primus reference, with data contents)
		// (can however also be recovered from data, see below)
		PublicKey publicKey = keyPair.getPublic();
		assert publicKey != null;
		assert publicKey.getEncoded() != null && publicKey.getEncoded().length > 0;

		// get external key data
		// this data would be written to disk or database etc, and read back when needed
		final byte[] keyData = PrimusExternal.toData(privateKey);
		assert keyData != null && keyData.length > 0;

		// (forget about the initial data objects)
		keyPair = null;
		privateKey = null;

		// sign something with the external key
		final byte[] message = "message".getBytes();
		final String signAlgorithm = "SHA256withRSA";

		// reconstruct key object and reference encrypted blob (keyData)
		privateKey = (PrivateKey)PrimusExternal.fromData(keyData);
		assert privateKey != null;
		assert privateKey.getEncoded() == null;

		// sign with reconstructed key object by handing over encrypted blob (keyData) to HSM
		Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initSign(privateKey);
		signature.update(message);
		final byte[] signatureBytes = signature.sign();

		// verify with intitial public data, and local provider
		signature = Signature.getInstance(signAlgorithm);
		signature.initVerify(publicKey);
		signature.update(message);
		boolean verified = signature.verify(signatureBytes);

		System.out.println("verified: " + verified);
		assert verified;

		// reconstruct public data
		publicKey = (PublicKey)PrimusExternal.fromData(keyData, PublicKey.class);
		assert publicKey != null;
		assert publicKey.getEncoded() != null && publicKey.getEncoded().length > 0;

		// verify again, with reconstructed public data
		signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initVerify(publicKey);
		signature.update(message);
		verified = signature.verify(signatureBytes);

		System.out.println("verified again: " + verified);
		assert verified;

	}

}

