
/*
 * Copyright (C) 2023, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAttestation;
import com.securosys.primus.jce.PrimusCryptoCurrencies;
import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs;

/**
 * Sample code for key attribute attestation
 * with the Securosys Primus HSM JCE provider.
 */
public class AttestationSampleEcCkdSka {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// generate an attestation key
		// this requires the root key store (RKS)
		final String attestationKeyName = "ec-attestation-key-" + UUID.randomUUID().toString();
		PrimusAttestation.createEcAttestationKey(attestationKeyName);

		// get the attestation key certificate chain (Securosys CA - device key - attestation key)
		final Certificate[] cc = PrimusAttestation.getEcAttestationKeyCertificateChain(attestationKeyName);
		final Certificate ca = cc[cc.length - 1];

		// verifying version of above
		final Certificate[] cc2 = PrimusAttestation.getEcAttestationKeyCertificateChain(attestationKeyName, ca);
		assert Arrays.deepEquals(cc, cc2);

		// generate a base key
		final PrivateKey baseKey = ((KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", primusProvider),
			new PrimusSpecs.CkdEnabled(new ECGenParameterSpec("secp256k1")),
			new PrimusCryptoCurrencies.CryptoCurrency(PrimusCryptoCurrencies.BITCOIN),
			PrimusSkaHelper.getSampleAccess(),
			"ec-base-key-" + UUID.randomUUID().toString()
		)).getPrivate();

		// get the base key attestation, on the real key
		System.out.println();
		System.out.println("base key attestation:");
		System.out.println(PrimusAttestation.getSignedAttributes(attestationKeyName, baseKey, (byte[][])null));
		System.out.println("address: " + PrimusEncoding.hexEncode(PrimusCryptoCurrencies.getCryptoCurrencyAddress(baseKey)).toUpperCase(Locale.US));

		// generate a derived key
		final PrivateKey derivedKey1 = ((KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", primusProvider),
			new PrimusSpecs.CkdGenParameterSpec(baseKey, "1/2/3"),
			"ec-derived-key-" + UUID.randomUUID().toString()
		)).getPrivate();

		// get the derived key attestation, on the real key
		System.out.println();
		System.out.println("derived key attestation:");
		System.out.println(PrimusAttestation.getSignedAttributes(attestationKeyName, derivedKey1, (byte[][])null));
		System.out.println("address: " + PrimusEncoding.hexEncode(PrimusCryptoCurrencies.getCryptoCurrencyAddress(derivedKey1)).toUpperCase(Locale.US));

		// pseudo derived key on the same key
		final PrivateKey derivedKey1b = new PrimusSpecs.CkdDerivedPrivateKey(baseKey, "1/2/3");
		System.out.println(PrimusAttestation.getSignedAttributes(attestationKeyName, derivedKey1b, (byte[][])null));
		System.out.println("address: " + PrimusEncoding.hexEncode(PrimusCryptoCurrencies.getCryptoCurrencyAddress(derivedKey1b)).toUpperCase(Locale.US));

		// pseudo derived key on another key
		final PrivateKey derivedKey2b = new PrimusSpecs.CkdDerivedPrivateKey(baseKey, "4/5/6");
		System.out.println(PrimusAttestation.getSignedAttributes(attestationKeyName, derivedKey2b, (byte[][])null));
		System.out.println("address: " + PrimusEncoding.hexEncode(PrimusCryptoCurrencies.getCryptoCurrencyAddress(derivedKey2b)).toUpperCase(Locale.US));

	}

}

