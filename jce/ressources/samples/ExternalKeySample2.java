
/*
 * Copyright (C) 2020-2023, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAttestation;
import com.securosys.primus.jce.PrimusExternal;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusKeyHelper;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;

public class ExternalKeySample2 {

	static String name = null;
	static char[] password = null;

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final String attestationKeyName = "attestation-key-" + UUID.randomUUID().toString();
		PrimusAttestation.createEcAttestationKey(attestationKeyName);

		// create an external key (pair)
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTERNAL_OBJECT, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_TOKEN, false);

		name = "sample_name"; // optional
		// password = "password".toCharArray(); // optional
		PrimusName.presetName(name, password);

		// RSA, size 2048 bits
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName());
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		assert keyPair != null;

		PrivateKey privateKey = keyPair.getPrivate();
		assert privateKey != null;
		assert privateKey.getEncoded() == null;
		assert PrimusKeyHelper.isPrimusKey(privateKey);
		System.out.println();
		System.out.println(privateKey.getClass().getName());
		System.out.println(PrimusKeyHelper.getPrimusKeyName(privateKey));
		System.out.println(PrimusKeyHelper.getPrimusUuid(privateKey));
		System.out.println(PrimusAttestation.getSignedAttributes(attestationKeyName, privateKey, (byte[][])null));

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

		// reconstruct key object and reference encrypted blob (keyData)
		if (password == null) {
			privateKey = (PrivateKey)PrimusExternal.fromData(keyData);
		} else {
			privateKey = (PrivateKey)PrimusExternal.fromData(keyData, password);
		}
		assert privateKey != null;
		assert privateKey.getEncoded() == null;
		assert PrimusKeyHelper.isPrimusKey(privateKey);
		System.out.println();
		System.out.println(privateKey.getClass().getName());
		System.out.println(PrimusKeyHelper.getPrimusKeyName(privateKey));
		System.out.println(PrimusKeyHelper.getPrimusUuid(privateKey));
		System.out.println(PrimusAttestation.getSignedAttributes(attestationKeyName, privateKey, (byte[][])null));

		// sign something with the external key
		System.out.println();
		final byte[] message = "message".getBytes();
		final String signAlgorithm = "SHA256withRSA";

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

