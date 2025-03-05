
/*
 * Copyright (C) 2017,2019, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.UUID;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs;
import com.securosys.primus.jce.PrimusX509Certificate;

/**
 * Sample EC CKD key export with Securosys Primus HSM JCE provider.
 */
public class KeyExportEcCkdSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// set 'extractable' flags on to be generated keys
		// NOTE: remember that the Primus HSM needs that enabled [by SO; hsm_sec_set_config key_export=true]
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, Boolean.TRUE);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, Boolean.FALSE);

		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keyStore.load(null);
		final KeyStore exportKeyStore = KeyStore.getInstance(PrimusProvider.getImportExportKeyStoreTypeName(), PrimusProvider.getProviderName());
		exportKeyStore.load(null);

		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());

		// generate an EC secp256k1 key which allows to derive keys from
		keyPairGenerator.initialize(new PrimusSpecs.CkdEnabled(new ECGenParameterSpec("secp256k1")));
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// name/persist the key pair
		final String keyName = UUID.randomUUID().toString();
		keyStore.setKeyEntry(keyName, keyPair.getPrivate(), null, new Certificate[] {new PrimusX509Certificate(keyPair.getPublic())});

		// export key pair
		final KeyPair exportedKeyPair = new KeyPair(exportKeyStore.getCertificate(keyName).getPublicKey(), (PrivateKey)exportKeyStore.getKey(keyName, null));

		// delete key pair on HSM
		keyStore.deleteEntry(keyName);

		// typically: exported sun.security.ec.ECPrivateKeyImpl and sun.security.ec.ECPublicKeyImpl, plain old java objects, with key payload
		System.out.println("exported " + exportedKeyPair.getPrivate().getClass().getName() + " and " + exportedKeyPair.getPublic().getClass().getName());

		System.out.println("private key encoded: " + Arrays.toString(exportedKeyPair.getPrivate().getEncoded()));
		System.out.println("public key encoded: " + Arrays.toString(exportedKeyPair.getPublic().getEncoded()));

		if (exportedKeyPair.getPrivate() instanceof PrimusSpecs.CkdKeyWithChainCode) {
			System.out.println("private key encoded, EC part: " + Arrays.toString(((PrimusSpecs.CkdKeyWithChainCode)exportedKeyPair.getPrivate()).getEncodedEC()));
		}
		if (exportedKeyPair.getPublic() instanceof PrimusSpecs.CkdKeyWithChainCode) {
			System.out.println("public key encoded, EC part: " + Arrays.toString(((PrimusSpecs.CkdKeyWithChainCode)exportedKeyPair.getPublic()).getEncodedEC()));
		}

		// re-import
		exportKeyStore.setKeyEntry(keyName, exportedKeyPair.getPrivate(), null, new Certificate[] {new PrimusX509Certificate(exportedKeyPair.getPublic())});
		@SuppressWarnings("unused")
		final KeyPair keyPair2 = new KeyPair(keyStore.getCertificate(keyName).getPublicKey(), (PrivateKey)keyStore.getKey(keyName, null));

	}

}

