
/*
 * Copyright (C) 2019, Securosys SA
 * Author Eric Laroche <eric@securosys.ch>
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusKeyHelper;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusWrap;

/**
 * Sample code showing wrapping of HSM keys with HSM keys
 * with the Securosys Primus HSM JCE provider.
 *
 * Uses different sets of flags for keys used with different operations (wrapping, or being wrapped),
 * gives the keys names, and retrieves them, runs sign tests with the unwrapped keys.
 */
public class UnwrapSignSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// test unwrap/sign with EC wrapped by AES

		final String wrapName = "aes-wrap-key-" + UUID.randomUUID().toString();

		// 1 -- generate keys

		// generate AES key to wrap with, 256 bit size
		// wrap key does not need to be extractable (sensitive or not)
		SecretKey aesWrapKey = (SecretKey)PrimusName.generate(
			KeyGenerator.getInstance("AES", PrimusProvider.getProviderName()),
			256,
			wrapName,
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true)
		);

		assert aesWrapKey != null; // obviously we want a key generated
		assert PrimusKeyHelper.isPrimusKey(aesWrapKey); // it should be HSM based
		assert aesWrapKey.getEncoded() == null; // no key bits available on the client (only on HSM)

		// retrieve it
		KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keyStore.load(null);
		aesWrapKey = (SecretKey)keyStore.getKey(wrapName, null);

		assert aesWrapKey != null; // key should be retrievable
		assert PrimusKeyHelper.isPrimusKey(aesWrapKey); // it should still be HSM based
		assert aesWrapKey.getEncoded() == null; // no key bits available on the client (only on HSM)

		final String ecName = "ec-sign-key-" + UUID.randomUUID().toString();

		// generate EC key to be wrapped, secp256k1
		// key to be wrapped needs to be extractable (sensitive or not)
		KeyPair ecKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			// new ECGenParameterSpec("secp256k1"), // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
			new ECGenParameterSpec("secp256r1"),
			ecName,
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true)
		);
		ECPrivateKey ecKey = (ECPrivateKey)ecKeyPair.getPrivate();
		ECPublicKey ecPublicKey = (ECPublicKey)ecKeyPair.getPublic();

		assert ecKey != null; // obviously we want a key [pair] generated
		assert PrimusKeyHelper.isPrimusKey(ecKey); // private key should be HSM based
		assert ecKey.getEncoded() == null; // no private key bits available on the client (only on HSM)

		// retrieve it
		ecKey = (ECPrivateKey)keyStore.getKey(ecName, null);

		assert ecKey != null; // private key not null
		assert PrimusKeyHelper.isPrimusKey(ecKey); // private key should still be HSM based
		assert ecKey.getEncoded() == null; // no private key bits available on the client (only on HSM)

		// 2 -- wrap

		// wrap EC by AES
		byte[] wrapped = PrimusWrap.aesWrapPadEc(aesWrapKey, ecKey);
		assert wrapped != null; // expecting bytes
		assert wrapped.length > 0; // with contents

		// delete it
		keyStore.deleteEntry(ecName);

		assert keyStore.getKey(ecName, null) == null;

		// 3 -- use for unwrap/sign

		byte[] message = UUID.randomUUID().toString().getBytes();
		byte[] signed = PrimusWrap.aesUnwrapPadEcSign("SHA256withECDSA", message, aesWrapKey, wrapped);

		assert signed != null; // a signature
		assert signed.length > 0; // with contents

		// verify with ancient material
		Signature signature = Signature.getInstance("SHA256withECDSA");
		signature.initVerify(ecPublicKey);
		signature.update(message);
		boolean verified = signature.verify(signed);

		assert verified; // must verify

	}


}

