
/*
 * Copyright (C) 2019,2020, Securosys SA
 * Author Eric Laroche <eric@securosys.ch>
 */

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.util.UUID;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusKeyHelper;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusWrap;
import com.securosys.primus.jce.PrimusX509Certificate;

/**
 * Sample code using RSA and AES HSM keys to wrap each other,
 * to securely transfer key material between HSMs,
 * with the Securosys Primus HSM JCE provider.
 */
public class RsaDoubleWrapSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// keystore to get key references (not exports/imports)
		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		assert keyStore != null;
		keyStore.load(null);

		// 1 -- generate RSA and AES wrap keys
		// in a key transfer use case each HSM would generate its own RSA key,
		// and one HSM only would generate a single AES key, which would be unwrap-imported on the other HSM

		// unique key names, to avoid collisions (with later tests)
		final String keyNameBase = "RsaDoubleWrapSample-" + UUID.randomUUID().toString() + "-";
		final String rsaKeyName = keyNameBase + "RSA";
		final String aesKeyName = keyNameBase + "AES";
		final String unwrappedAesKeyName = aesKeyName + "-unwrapped";
		final String unwrappedRsaKeyName = rsaKeyName + "-unwrapped";

		// keys: extractable (wrappable), but sensitive
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, true);

		// keys: able to do wrap and unwrap operations
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_WRAP, true);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_UNWRAP, true);

		// generate RSA key pair
		final KeyPairGenerator rsaKeyPairGenerator = KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName());
		assert rsaKeyPairGenerator != null;
		rsaKeyPairGenerator.initialize(2048);
		KeyPair rsaKeyPair = rsaKeyPairGenerator.generateKeyPair();
		assert rsaKeyPair != null;
		assert rsaKeyPair.getPrivate() != null;
		assert PrimusKeyHelper.isPrimusKey(rsaKeyPair.getPrivate()) == true; // private key on HSM
		assert rsaKeyPair.getPrivate().getEncoded() == null; // private key on HSM only
		assert rsaKeyPair.getPublic() != null;
		assert PrimusKeyHelper.isPrimusKey(rsaKeyPair.getPublic()) == true; // public key also on HSM
		assert rsaKeyPair.getPublic().getEncoded() != null; // public key also implicitly exported

		// name key
		keyStore.setKeyEntry(rsaKeyName, rsaKeyPair.getPrivate(), rsaKeyName.toCharArray(), new Certificate[] {new PrimusX509Certificate(rsaKeyPair.getPublic())});

		// generate AES key
		final KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("AES", PrimusProvider.getProviderName());
		assert aesKeyGenerator != null;
		aesKeyGenerator.init(256);
		SecretKey aesKey = aesKeyGenerator.generateKey();
		assert aesKey != null;
		assert PrimusKeyHelper.isPrimusKey(aesKey) == true; // key on HSM
		assert aesKey.getEncoded() == null; // key on HSM only

		// name key
		keyStore.setKeyEntry(aesKeyName, aesKey, aesKeyName.toCharArray(), null);

		// 2 -- fetch key references

		// fetch key reference
		rsaKeyPair = new KeyPair(keyStore.getCertificate(rsaKeyName).getPublicKey(), (PrivateKey)keyStore.getKey(rsaKeyName, rsaKeyName.toCharArray()));
		assert rsaKeyPair.getPrivate() != null;
		assert PrimusKeyHelper.isPrimusKey(rsaKeyPair.getPrivate()) == true; // private key on HSM
		assert rsaKeyPair.getPrivate().getEncoded() == null; // private key on HSM only
		assert rsaKeyPair.getPublic() != null;
		assert PrimusKeyHelper.isPrimusKey(rsaKeyPair.getPublic()) == false; // public key exported
		assert rsaKeyPair.getPublic().getEncoded() != null; // public key exported

		// fetch key reference
		aesKey = (SecretKey)keyStore.getKey(aesKeyName, aesKeyName.toCharArray());
		assert aesKey != null;
		assert PrimusKeyHelper.isPrimusKey(aesKey) == true; // key on HSM
		assert aesKey.getEncoded() == null; // key on HSM only

		// 3 -- explicitly import RSA public key to wrap with (this would be from another HSM)

		rsaKeyPair = new KeyPair((PublicKey)KeyFactory.getInstance("RSA", PrimusProvider.getProviderName()).translateKey(rsaKeyPair.getPublic()), rsaKeyPair.getPrivate());
		assert rsaKeyPair.getPublic() != null;
		assert PrimusKeyHelper.isPrimusKey(rsaKeyPair.getPublic()) == true; // public key imported
		assert rsaKeyPair.getPublic().getEncoded() != null; // public key also implicitly exported

		// 4 -- wrap-export AES by RSA

		final byte[] aesWrapped = PrimusWrap.rsaWrapPkcs(rsaKeyPair.getPublic(), aesKey);
		assert aesWrapped != null;
		assert aesWrapped.length > 0; // wrapped bytes not empty

		// 5 -- unwrap-import AES by RSA (this would be done on HSM II)

		SecretKey unwrappedAesKey = PrimusWrap.rsaUnwrapPkcs(rsaKeyPair.getPrivate(), aesWrapped);
		assert unwrappedAesKey != null;
		assert PrimusKeyHelper.isPrimusKey(unwrappedAesKey) == true; // key on HSM
		assert unwrappedAesKey.getEncoded() == null; // key on HSM only

		// name / fetch reference
		keyStore.setKeyEntry(unwrappedAesKeyName, unwrappedAesKey, unwrappedAesKeyName.toCharArray(), null);
		unwrappedAesKey = (SecretKey)keyStore.getKey(unwrappedAesKeyName, unwrappedAesKeyName.toCharArray());
		assert unwrappedAesKey != null;
		assert PrimusKeyHelper.isPrimusKey(unwrappedAesKey) == true; // key on HSM
		assert unwrappedAesKey.getEncoded() == null; // key on HSM only

		// 6 -- wrap-export RSA by AES (this shows how to transfer an RSA private key)

		final byte[] rsaWrapped = PrimusWrap.aesWrapRsa(unwrappedAesKey, (RSAPrivateKey)rsaKeyPair.getPrivate());
		assert rsaWrapped != null;
		assert rsaWrapped.length > 0; // wrapped bytes not empty

		// 7 -- unwrap-import RSA by AES

		KeyPair unwrappedRsaKeyPair = new KeyPair(rsaKeyPair.getPublic(), PrimusWrap.aesUnwrapRsa(unwrappedAesKey, rsaWrapped));
		assert unwrappedRsaKeyPair.getPrivate() != null;
		assert PrimusKeyHelper.isPrimusKey(unwrappedRsaKeyPair.getPrivate()) == true; // private key on HSM
		assert unwrappedRsaKeyPair.getPrivate().getEncoded() == null; // private key on HSM only

		// name / fetch reference
		keyStore.setKeyEntry(unwrappedRsaKeyName, unwrappedRsaKeyPair.getPrivate(), unwrappedRsaKeyName.toCharArray(), new Certificate[] {new PrimusX509Certificate(unwrappedRsaKeyPair.getPublic())});
		unwrappedRsaKeyPair = new KeyPair(keyStore.getCertificate(unwrappedRsaKeyName).getPublicKey(), (PrivateKey)keyStore.getKey(unwrappedRsaKeyName, unwrappedRsaKeyName.toCharArray()));
		assert unwrappedRsaKeyPair.getPrivate() != null;
		assert PrimusKeyHelper.isPrimusKey(unwrappedRsaKeyPair.getPrivate()) == true; // private key on HSM
		assert unwrappedRsaKeyPair.getPrivate().getEncoded() == null; // private key on HSM only
		assert unwrappedRsaKeyPair.getPublic() != null;
		assert PrimusKeyHelper.isPrimusKey(unwrappedRsaKeyPair.getPublic()) == false; // public key exported
		assert unwrappedRsaKeyPair.getPublic().getEncoded() != null; // public key exported

	}

}

