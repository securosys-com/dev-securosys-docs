
/*
 * Copyright (C) 2019, Securosys SA
 * Author Tomas Forgac <tomas.forgac@vysoka.securosys.ch>
 */

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.UUID;

/**
 * This sample shows how to create private keys for cryptocurrencies based on Bitcoin, Ethereum, and Ripple
 *
 * The code runs the following steps:
 * 1) Logs in to the HSM
 * 2) Generates Bitcoin and Ethereum Private keys
 * 3) Generates Ripple Private key
 */
public class CryptocurrencyPrivateKeysSample {

	public static void main(final String... args) throws Exception {

		// 1 -- HSM AND PROVIDER CONFIGURATION
		// HSM configuration

		PrimusHelper.setup(args);

		// 2 -- GENERATE BITCOIN AND ETHEREUM PRIVATE KEYS

		// In most cases the key would be non-extractable (default option) or at least
		// sensitive (i.e. extractable only wrapped). For the purpose of this sample we will
		// extract the key in plain text so we can print it to the console
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, false);

		// Bitcoin and Ethereum (and other cryptocurrencies derived from them) use the same elliptic curve algorithm for
		// their private keys - EC secp256k1. The difference between them is in hashing algorithm used for their
		// addresses. We'll show that in a later sample
		String curve = "secp256k1";

		final KeyPairGenerator primusKeyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		primusKeyPairGenerator.initialize(new ECGenParameterSpec(curve));

		// generate a named key
		String keyName = "Bitcoin-" + UUID.randomUUID().toString();
		final KeyPair bitcoinKeyPair = PrimusName.generateKeyPair(primusKeyPairGenerator, keyName);
		System.out.println("Bitcoin key generated");

		// extract private key material from the keypair reference;
		// it would also be possible to use PrimusKeyStore.getKey to extract the PrivateKey by its name - see
		// KeySignatureAndVerificationSample.java for an example of that
		final ECPrivateKey bitcoinPrivateKey = (ECPrivateKey)KeyFactory.getInstance(
				"EC", PrimusProvider.getProviderName()).translateKey(bitcoinKeyPair.getPrivate());
		// get uncompressed key material as byte array
		byte[] keyBytes = bitcoinPrivateKey.getS().toByteArray();

		// for Bitcoin private key purposes, leading zero should be omitted
		if (keyBytes[0] == 0) {
			keyBytes = Arrays.copyOfRange(keyBytes, 1, keyBytes.length);
		}

		// print the key material in Hex and WIF format
		// [PrimusEncoding.hexEncode requires Primus JCE provider version 1.8.6 or newer]
		System.out.println("Hex format: " + PrimusEncoding.hexEncode(keyBytes));

		int mainnetPrivateKeyHeader = 128;
		// [PrimusEncoding.base58CheckEncode requires Primus JCE provider version 1.8.6 or newer]
		System.out.println("WIF format: " + PrimusEncoding.base58CheckEncode(keyBytes, mainnetPrivateKeyHeader));

		// create other keys as non-extractable
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false);

		// Ethereum uses the same algorithm to generate private keys. Therefore the same method can be used
		keyName = "Ethereum-" + UUID.randomUUID().toString();
		final KeyPair ethereumKeyPair = PrimusName.generateKeyPair(primusKeyPairGenerator, keyName);

		System.out.println();
		System.out.println("Ethereum key " + keyName + " created");

		try {
			KeyFactory.getInstance("EC", PrimusProvider.getProviderName()).translateKey(ethereumKeyPair.getPrivate());
		} catch (Exception e) {
			System.out.println("Private key non-extractable");
		}

		// 3 -- RIPPLE

		// Ripple uses a different elliptic curve - Curve25519
		curve = "curve25519";
		keyName = "Ripple-" + UUID.randomUUID().toString();

		final KeyPairGenerator rippleKeyGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		rippleKeyGenerator.initialize(new ECGenParameterSpec(curve));
		PrimusName.generateKeyPair(rippleKeyGenerator, keyName);

		System.out.println();
		System.out.println("Ripple key " + keyName + " created");

	}

}

