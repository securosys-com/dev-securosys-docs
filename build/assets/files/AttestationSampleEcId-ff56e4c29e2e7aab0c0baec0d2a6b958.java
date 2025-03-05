
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAttestation;
import com.securosys.primus.jce.PrimusKeyFields;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code for key attribute attestation
 * with the Securosys Primus HSM JCE provider.
 */
public class AttestationSampleEcId {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// generate an attestation key
		// this requires the root key store (RKS)
		final String attestationKeyName = "attestation-key-" + UUID.randomUUID().toString();
		PrimusAttestation.createEcAttestationKey(attestationKeyName);

		// generate a new key
		final String keyname = "ec-key-" + UUID.randomUUID().toString();
		final Key key = ((KeyPair)PrimusName.generate(KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()), new ECGenParameterSpec("secp256r1"), keyname)).getPrivate();

		// set an id on the key
		PrimusKeyFields.setSharedKeyValue(key, "id".getBytes(StandardCharsets.UTF_8), PrimusKeyFields.ID);

		// get the attestation and its signature
		final byte[][] signature = new byte[1][];
		final String xml = PrimusAttestation.getSignedAttributes(attestationKeyName, key, signature);
		System.out.println();
		System.out.println("key attestation:");
		System.out.println(xml);

	}

}

