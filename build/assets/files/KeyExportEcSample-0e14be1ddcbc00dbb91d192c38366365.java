
/*
 * Copyright (C) 2017-2021, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.spec.ECGenParameterSpec;

import java.util.Base64;
import java.util.UUID;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusX509Certificate;

/**
 * Sample EC key export with Securosys Primus HSM JCE provider.
 */
public class KeyExportEcSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// set 'extractable' flags on to be generated keys
		// NOTE: remember that the Primus HSM needs that enabled [by SO; hsm_sec_set_config key_export=true]
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, Boolean.TRUE);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, Boolean.FALSE);

		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), primusProvider);
		keyStore.load(null);
		final KeyStore exportKeyStore = KeyStore.getInstance(PrimusProvider.getImportExportKeyStoreTypeName(), primusProvider);
		exportKeyStore.load(null);

		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", primusProvider);

		// generate an EC key pair for curve "secp256r1" (a.k.a. NIST P-256, X9.62 prime256v1)
		keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// name/persist the key pair
		final String keyName = "ec-secp256r1-key-" + UUID.randomUUID().toString();
		keyStore.setKeyEntry(keyName, keyPair.getPrivate(), null, new Certificate[] {new PrimusX509Certificate(keyPair.getPublic())});

		// export key pair
		final KeyPair exportedKeyPair = new KeyPair(exportKeyStore.getCertificate(keyName).getPublicKey(), (PrivateKey)exportKeyStore.getKey(keyName, null));

		// delete key pair on HSM
		keyStore.deleteEntry(keyName);

		// typically: exported sun.security.ec.ECPrivateKeyImpl and sun.security.ec.ECPublicKeyImpl, plain old java objects, with key payload
		System.out.println("exported " + exportedKeyPair.getPrivate().getClass().getName() + " and " + exportedKeyPair.getPublic().getClass().getName());

		final byte[] privateEncoded = exportedKeyPair.getPrivate().getEncoded();
		final byte[] publicEncoded = exportedKeyPair.getPublic().getEncoded();

		System.out.println("private key encoded: " + Base64.getEncoder().encodeToString(privateEncoded));
		// System.out.println(com.securosys.primus.jce.encoding.DERObject.valueOf(privateEncoded).toString(true, true, true));
		System.out.println("public key encoded: " + Base64.getEncoder().encodeToString(publicEncoded));
		// System.out.println(com.securosys.primus.jce.encoding.DERObject.valueOf(publicEncoded).toString(true, true, true));

	}

}

