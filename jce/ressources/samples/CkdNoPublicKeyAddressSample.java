
/*
 * Copyright (C) 2019,2021, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusCryptoCurrencies;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSignature;
import com.securosys.primus.jce.PrimusSpecs;
import com.securosys.primus.jce.spi.PrimusKeyPairGeneratorSpi;

/**
 * Sample code for generating a quantum computing safe key pair
 * (public key part only revealed on first sign operation)
 * with client key derivation capabilities
 * with the Securosys Primus HSM JCE provider.
 */
public class CkdNoPublicKeyAddressSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration

		PrimusHelper.setup(args);

		// make the key [pair] no-public-key, non-extractable [and sensitive], non-modifiable
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_NO_PUBLIC_KEY, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_MODIFIABLE, false);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_DERIVE, true);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_SIGN, true);

		// crypto currency must be specified with no-public-key
		PrimusCryptoCurrencies.setCryptoCurrency(PrimusCryptoCurrencies.BITCOIN);

		// no-pub works only with smart/extended key attributes, so set a PrimusAccess
		final PrimusAccessToken accessToken = new PrimusAccessToken(0, 0);
		final PrimusAccessBlob accessBlob = new PrimusAccessBlob(accessToken);
		final PrimusAccess primusAccess = new PrimusAccess(accessBlob, accessBlob, accessBlob, accessBlob);
		PrimusAccess.setAccess(primusAccess);

		// make it an EC secp256k1 key [pair], for derivation capability
		final KeyPairGenerator primusKeyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdEnabled(new ECGenParameterSpec("secp256k1")));

		final String keyName = UUID.randomUUID().toString();
		final KeyPair keyPair = PrimusName.generateKeyPair(primusKeyPairGenerator, keyName);

		assert keyPair != null; // [generateKeyPair should return something]
		assert keyPair.getPublic() != null; // [the public part is a reference [on a no-pub key]]
		assert keyPair.getPublic().getEncoded() == null; // [the no-pub encoding is null]
		assert keyPair.getPublic() instanceof PrimusSpecs.CryptoCurrencyPublicKey; // [and one with retrievable crypto currency address]

		// get crypto currency address
		final byte[] cryptoCurrencyAddress = ((PrimusSpecs.CryptoCurrencyPublicKey)keyPair.getPublic()).getCryptoCurrencyAddress();

		assert cryptoCurrencyAddress != null; // [the crypto currency address is not null]
		assert cryptoCurrencyAddress.length == 20; // [its length is 160 bits (20 bytes) in the bitcoin case]

		assert PrimusSignature.getNoPubPublicKey() == null;

		final byte[] cryptoCurrencyAddress2 = PrimusKeyPairGeneratorSpi.createCryptoCurrencyAddress0(keyPair.getPrivate());
		assert cryptoCurrencyAddress2 != null;
		assert Arrays.equals(cryptoCurrencyAddress2, cryptoCurrencyAddress);

		// different ways to optain addresses of derived keys
		final byte[] cryptoCurrencyAddress3 = PrimusKeyPairGeneratorSpi.createCryptoCurrencyAddress0(keyPair.getPrivate(), "23/22/21");
		final byte[] cryptoCurrencyAddress3b = PrimusSpecs.CryptoCurrencyHelper.getCryptoCurrencyAddress(keyPair.getPrivate(), "23/22/21");
		final byte[] cryptoCurrencyAddress3c = PrimusSpecs.CryptoCurrencyHelper.getCryptoCurrencyAddress(keyPair.getPublic(), "23/22/21");
		assert Arrays.equals(cryptoCurrencyAddress3b, cryptoCurrencyAddress3); // same
		assert Arrays.equals(cryptoCurrencyAddress3c, cryptoCurrencyAddress3); // and same
		assert cryptoCurrencyAddress3 != null;
		assert !Arrays.equals(cryptoCurrencyAddress3, cryptoCurrencyAddress); // different to base address

	}

}

