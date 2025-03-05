
/*
 * Copyright (C) 2019-2022, Securosys SA
 */

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusCryptoCurrencies;
import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs;

/**
 * Simple EC-CKD address sample
 * for the Securosys Primus HSM JCE provider.
 */
public class EcCkdAddrSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		System.out.println();
		System.out.println("creating EC secp256k1 key pair");
		PrimusName.presetName(UUID.randomUUID().toString());
		final KeyPairGenerator primusKeyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdEnabled(new ECGenParameterSpec("secp256k1")));
		final KeyPair keyPair = primusKeyPairGenerator.generateKeyPair();
		System.out.println("address: " + PrimusEncoding.base58CheckEncode(PrimusCryptoCurrencies.getCryptoCurrencyAddress(keyPair.getPrivate(), PrimusCryptoCurrencies.BITCOIN)));

		System.out.println("deriving");
		PrimusName.presetName(UUID.randomUUID().toString());
		final String derivationPath = "1/2/3";
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdGenParameterSpec(keyPair.getPrivate(), derivationPath));
		final KeyPair derivedKeyPair = primusKeyPairGenerator.generateKeyPair();
		System.out.println("address: " + PrimusEncoding.base58CheckEncode(PrimusCryptoCurrencies.getCryptoCurrencyAddress(derivedKeyPair.getPrivate(), PrimusCryptoCurrencies.BITCOIN)));
		System.out.println("by base: " + PrimusEncoding.base58CheckEncode(PrimusCryptoCurrencies.getCryptoCurrencyAddress(keyPair.getPrivate(), derivationPath, PrimusCryptoCurrencies.BITCOIN)));

		System.out.println();
		System.out.println("seed-importing EC secp256k1 key pair");
		PrimusName.presetName(UUID.randomUUID().toString());
		final byte[] seed = new byte[32];
		(new SecureRandom()).nextBytes(seed);
		final PrivateKey k = (PrivateKey)KeyFactory.getInstance("EC", PrimusProvider.getProviderName()).translateKey(new PrimusSpecs.CkdSeedKey(seed, new ECGenParameterSpec("secp256k1")));
		System.out.println("address: " + PrimusEncoding.base58CheckEncode(PrimusCryptoCurrencies.getCryptoCurrencyAddress(k, PrimusCryptoCurrencies.BITCOIN)));

		System.out.println("deriving");
		PrimusName.presetName(UUID.randomUUID().toString());
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdGenParameterSpec(k, derivationPath));
		final KeyPair derivedK = primusKeyPairGenerator.generateKeyPair();
		System.out.println("address: " + PrimusEncoding.base58CheckEncode(PrimusCryptoCurrencies.getCryptoCurrencyAddress(derivedK.getPrivate(), PrimusCryptoCurrencies.BITCOIN)));
		System.out.println("by base: " + PrimusEncoding.base58CheckEncode(PrimusCryptoCurrencies.getCryptoCurrencyAddress(k, derivationPath, PrimusCryptoCurrencies.BITCOIN)));

	}

}

