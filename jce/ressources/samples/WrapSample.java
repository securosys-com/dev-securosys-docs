
/*
 * Copyright (C) 2019, Securosys SA
 * Author Eric Laroche <eric@securosys.ch>
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusKeyHelper;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusWrap;
import com.securosys.primus.jce.PrimusX509Certificate;

/**
 * Sample code showing wrapping of HSM keys with HSM keys
 * with the Securosys Primus HSM JCE provider.
 *
 * Uses different sets of flags for keys used with different operations (wrapping, or being wrapped),
 * gives the keys names, and retrieves them, runs sign tests with the unwrapped keys.
 */
public class WrapSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// test unwrapping EC by AES

		// 1 -- generate keys

		// generate AES key to wrap with, 256 bit size
		SecretKey aesKey;
		// wrap key does not need to be extractable (sensitive or not)
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, true);
		try {

			KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("AES", PrimusProvider.getProviderName());
			aesKeyGenerator.init(256);
			aesKey = aesKeyGenerator.generateKey();

		} finally {
			PrimusKeyAttributes.clearKeyAccessFlags();
		}

		assert aesKey != null; // obviously we want a key generated
		assert PrimusKeyHelper.isPrimusKey(aesKey); // it should be HSM based
		assert aesKey.getEncoded() == null; // no key bits available on the client (only on HSM)

		// give key a name
		KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keyStore.load(null);
		String keyName = UUID.randomUUID().toString();
		keyStore.setKeyEntry(keyName, aesKey, keyName.toCharArray(), null);
		aesKey = (SecretKey)keyStore.getKey(keyName, keyName.toCharArray());

		assert aesKey != null; // key should be retrievable
		assert PrimusKeyHelper.isPrimusKey(aesKey); // it should still be HSM based
		assert aesKey.getEncoded() == null; // no key bits available on the client (only on HSM)

		// generate an EC key pair to be wrapped, "secp256r1"
		KeyPair ecKeyPair;
		// key to be wrapped must be extractable (sensitive or not) for wrap operation
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, true);
		try {

			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
			keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
			ecKeyPair = keyPairGenerator.generateKeyPair();

		} finally {
			PrimusKeyAttributes.clearKeyAccessFlags();
		}

		assert ecKeyPair != null; // obviously we want a key pair generated
		assert ecKeyPair.getPrivate() != null; // private key not null
		assert PrimusKeyHelper.isPrimusKey(ecKeyPair.getPrivate()); // private key should be HSM based
		assert ecKeyPair.getPrivate().getEncoded() == null; // no private key bits available on the client (only on HSM)
		assert ecKeyPair.getPublic() != null; // public key not null
		assert ecKeyPair.getPublic().getEncoded() != null; // public key bits available on the client too

		// give key a name
		keyName = UUID.randomUUID().toString();
		keyStore.setKeyEntry(keyName, ecKeyPair.getPrivate(), keyName.toCharArray(), new Certificate[] {new PrimusX509Certificate(ecKeyPair.getPublic())});
		ecKeyPair = new KeyPair(keyStore.getCertificate(keyName).getPublicKey(), (PrivateKey)keyStore.getKey(keyName, keyName.toCharArray()));

		assert ecKeyPair != null; // key pair should be retrievable
		assert ecKeyPair.getPrivate() != null; // private key not null
		assert PrimusKeyHelper.isPrimusKey(ecKeyPair.getPrivate()); // private key should still be HSM based
		assert ecKeyPair.getPrivate().getEncoded() == null; // no private key bits available on the client (only on HSM)
		assert ecKeyPair.getPublic() != null; // public key not null
		assert ecKeyPair.getPublic().getEncoded() != null; // public key bits available on the client too

		// 2 -- wrap

		// wrap EC by AES
		byte[] wrapped = PrimusWrap.aesWrapEc(aesKey, (ECPrivateKey)ecKeyPair.getPrivate());
		assert wrapped != null; // expecting bytes
		assert wrapped.length > 0; // with contents

		// 3 -- unwrap

		// unwrap to HSM
		ECPrivateKey unwrappedEcKey;
		// unwrapped key needs neither to be extractable (sensitive or not)
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, true);
		try {

			unwrappedEcKey = PrimusWrap.aesUnwrapEc(aesKey, wrapped);

		} finally {
			PrimusKeyAttributes.clearKeyAccessFlags();
		}

		assert unwrappedEcKey != null; // unwrap should succeed
		assert PrimusKeyHelper.isPrimusKey(unwrappedEcKey); // it should be HSM based
		assert unwrappedEcKey.getEncoded() == null; // no key bits available on the client (only on HSM)

		// 4 -- test

		// sign with unwrapped key
		Signature signature = Signature.getInstance("SHA256withECDSA", PrimusProvider.getProviderName());
		signature.initSign(unwrappedEcKey);
		byte[] message = UUID.randomUUID().toString().getBytes();
		signature.update(message);
		byte[] signed = signature.sign();

		assert signed != null; // a signature
		assert signed.length > 0; // with contents

		// verify with ancient material
		signature = Signature.getInstance("SHA256withECDSA");
		signature.initVerify(ecKeyPair.getPublic());
		signature.update(message);
		boolean verified = signature.verify(signed);

		assert verified; // must verify

	}

}

