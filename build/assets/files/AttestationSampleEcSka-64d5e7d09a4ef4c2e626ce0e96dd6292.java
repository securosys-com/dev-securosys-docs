
/*
 * Copyright (C) 2019-2022, Securosys SA
 */

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAttestation;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code for key attribute attestation
 * on an SKA key
 * with the Securosys Primus HSM JCE provider.
 */
public class AttestationSampleEcSka {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// 2 -- GET ATTESTATION KEY CERTIFICATES (CA and attestation public key)
		// This is done on the fly here (below); In actual production the attestation key certificate would come from another
		// trusted source - presumably Securosys support portal.

		// generate an attestation key
		// this requires the root key store (RKS)
		final String attestationKeyName = "attestation-key-" + UUID.randomUUID().toString();
		PrimusAttestation.createEcAttestationKey(attestationKeyName);

		// get the attestation key certificate chain (Securosys CA - device key - attestation key)
		final Certificate[] cc = PrimusAttestation.getEcAttestationKeyCertificateChain(attestationKeyName);
		System.out.println();
		System.out.println("ec attestation key certificate chain length is: " + cc.length);
		final Certificate ca = cc[cc.length - 1];
		final Certificate attestationCertificate = cc[0];

		// verifying version of above
		final Certificate[] cc2 = PrimusAttestation.getEcAttestationKeyCertificateChain(attestationKeyName, ca);
		assert Arrays.deepEquals(cc, cc2);

		final PrimusAccess access = PrimusSkaHelper.getSampleAccess();

		// generate a new key. In real application the key would be retrieved in need of attestation
		final String keyname = "ec-key-" + UUID.randomUUID().toString();
		final Key key = ((KeyPair)PrimusName.generate(KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()), new ECGenParameterSpec("secp256r1"), keyname, access)).getPrivate();

		// get the attestation and its signature
		final byte[][] signature = new byte[1][];
		final String xml = PrimusAttestation.getSignedAttributes(attestationKeyName, key, signature);
		System.out.println();
		System.out.println("key attestation:");
		System.out.println("8<--------");
		System.out.println(xml);
		System.out.println("8<--------");

		// verify attestation data signature
		final Signature verifier = Signature.getInstance(PrimusAttestation.getEcSignAlgorithm());
		verifier.initVerify(attestationCertificate);
		verifier.update(xml.getBytes(StandardCharsets.UTF_8));
		final boolean verified = verifier.verify(signature[0]);
		System.out.println("verified: " + verified);
		assert verified;

		// 4 -- GET SIGNED ATTRIBUTES WITH CERTIFICATE CHAIN

		final byte[][] signature2 = new byte[1][];
		final String[] chain2 = new String[1];
		final String xml2 = PrimusAttestation.getSignedAttributes(attestationKeyName, key, signature2, chain2);
		assert xml2 != null;
		// note: as the xml contain current 'attest_time' xml2 doesn't need to be equal to xml

		// Decode pem format certificate chain with verifying
		final Certificate[] cc3 = PrimusAttestation.decodeCertificateChain(chain2[0], ca);
		assert Arrays.deepEquals(cc, cc3);

	}

}

