
/*
 * Copyright (C) 2018-2020, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs;

/**
 * Sample code showing how to derive keys using BIP32 standard.
 * At the end it also derives a hardened account and address using BIP44 standard
 *
 * The sample goes throught the following steps:
 * 1) HSM Login
 * 2) Get a payload
 * 3) Basic derivation example
 * 4) Different derivation approaches
 * 5) BIP44 hardened Bitcoin account and address keys
 */
public class BIP32_HD_Sample { // BIP32_HD_Sample [was: CkdSample]

	public static void main(final String... args) throws Exception {

		// 1 -- HSM AND PROVIDER CONFIGURATION

		// HSM configuration
		PrimusHelper.setup(args);

		// 2 -- GET A PAYLOAD

		// random payload - in real life it would be e.g. a cryptocurrency transaction
		final int payloadSize = 250; // to test below code: use something %4!=0
		final byte[] payload = new byte[payloadSize];
		Arrays.fill(payload, (byte)'A');

		// 3 -- BASIC DERIVATION EXAMPLE

		// generate an EC secp256k1 key which allows to derive keys from (i.e. master key)
		final KeyPairGenerator primusKeyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdEnabled(new ECGenParameterSpec("secp256k1")));
		final KeyPair keyPair = primusKeyPairGenerator.generateKeyPair();

		// derive an EC secp256k1 key, of sample index 4
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdGenParameterSpec(keyPair.getPrivate(), 4));
		final KeyPair derivedKeyPair = primusKeyPairGenerator.generateKeyPair();

		// sample sign
		final Signature signature = Signature.getInstance("SHA256withECDSA", PrimusProvider.getProviderName());
		signature.initSign(derivedKeyPair.getPrivate());
		signature.update(payload);
		byte[] sig = signature.sign();

		// derive another EC secp256k1 key, of sample index 4 - this to show the key will be the same and the second can verify the signature
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdGenParameterSpec(keyPair.getPrivate(), 4));
		final KeyPair derivedKeyPair2 = primusKeyPairGenerator.generateKeyPair();

		// sign the same message with the newly created key with the same index
		signature.initVerify(derivedKeyPair2.getPublic());
		signature.update(payload);
		// verify that the signature is the same as with the previous key
		boolean v = signature.verify(sig);
		assert v;

		// 4 -- MORE DERIVATION OPTIONS

		// derive a chain of keys from the master key
		// no need for PrimusSpecs.CkdEnabled here -- derived keys remain implicitly derivable

		// /0
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdGenParameterSpec(keyPair.getPrivate(), 0));
		final KeyPair keyPair0 = primusKeyPairGenerator.generateKeyPair();

		// /0/0
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdGenParameterSpec(keyPair0.getPrivate(), 0));
		final KeyPair keyPair00 = primusKeyPairGenerator.generateKeyPair();

		// /0/0/0
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdGenParameterSpec(keyPair00.getPrivate(), 0));
		final KeyPair keyPair000 = primusKeyPairGenerator.generateKeyPair();

		// sign and verify with derived key
		signature.initSign(keyPair000.getPrivate());
		signature.update(payload);
		sig = signature.sign();
		signature.initVerify(keyPair000.getPublic());
		signature.update(payload);
		v = signature.verify(sig);
		assert v;

		// sign with implicit derive specification
		// the follwing is the same as signature.initSign(new PrimusSpecs.CkdDerivedPrivateKey(keyPair0.getPrivate(), "0/0/0"));

		signature.initSign(
				new PrimusSpecs.CkdPseudoPrivateKey(new PrimusSpecs.CkdGenParameterSpec(
						new PrimusSpecs.CkdPseudoPrivateKey(new PrimusSpecs.CkdGenParameterSpec(
								new PrimusSpecs.CkdPseudoPrivateKey(new PrimusSpecs.CkdGenParameterSpec(
										keyPair.getPrivate(),
										0)),
								0)),
						0)));
		signature.update(payload);
		sig = signature.sign();
		signature.initVerify(keyPair000.getPublic());
		signature.update(payload);
		v = signature.verify(sig);
		assert v;

		// CkdDerivedPrivateKey with derive chain specification is supported from Primus JCE provider version 1.7.7 on

		signature.initSign(new PrimusSpecs.CkdDerivedPrivateKey(keyPair.getPrivate(), "0/0/0"));
		signature.update(payload);
		sig = signature.sign();
		signature.initVerify(keyPair000.getPublic());
		signature.update(payload);
		v = signature.verify(sig);
		assert v;

		signature.initVerify(new PrimusSpecs.CkdDerivedPublicKey(keyPair.getPublic(), "0/0/0"));
		signature.update(payload);
		v = signature.verify(sig);
		assert v;

		// 5 -- HARDENED DERIVATION OF BITCOIN ACCOUNT AND ADDRESS KEYS

		// Generate a Bitcoin BIP44 account
		// Note: the HSM is agnostic about the standard used - it can derive any BIP32-based structure
		// Note: JCE provider 1.8.5 and older doesn't support "m/" prefix - only newer versions cut the prefix off.
		// When using such older version, ommit the prefix

		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdGenParameterSpec(keyPair.getPrivate(), "m/44'/0'/0'/0"));
		final KeyPair accountKeyPair = primusKeyPairGenerator.generateKeyPair();

		// Generate a key at index 0 - this will be equivalent of m/44'/0'/0'/0/0
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdGenParameterSpec(accountKeyPair.getPrivate(), 0));
		// derivedAddressKeyPair
		final KeyPair anotherDerivedKeyPair = primusKeyPairGenerator.generateKeyPair();

		// Sign the payload using the key
		signature.initSign(anotherDerivedKeyPair.getPrivate());
		signature.update(payload);
		sig = signature.sign();
		signature.initVerify(anotherDerivedKeyPair.getPublic());
		signature.update(payload);
		v = signature.verify(sig);
		assert v;

	}

}

