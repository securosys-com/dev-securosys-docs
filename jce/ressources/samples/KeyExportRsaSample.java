
/*
 * Copyright (C) 2017,2019, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import com.securosys.primus.jce.PrimusCertificate;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample RSA key export
 * with Securosys Primus HSM JCE provider.
 *
 * Note that in order to be exportable the key has to be created
 * with extractable flag true and sensitive flag false,
 * and the 'key export' setting has to be enabled on the HSM.
 */
public class KeyExportRsaSample {

	public static void main(final String... args) throws Exception {

		System.out.println("Securosys Primus HSM JCE provider RSA key export sample");

		// HSM configuration
		PrimusHelper.setup(args);

		// set 'extractable' flags
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, Boolean.TRUE);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, Boolean.FALSE);

		// generate an RSA key pair
		System.out.println("generating RSA key pair");
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName());
		keyPairGenerator.initialize(2048);
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		final RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
		final RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();

		// name/persist the key pair
		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		keyStore.load(null); // no-op, but mandatory for JCA
		final String keyName = UUID.randomUUID().toString(); // unique random name
		final char[] keyPassword = UUID.randomUUID().toString().toCharArray(); // unique random password
		keyStore.setKeyEntry(keyName, privateKey, keyPassword, new Certificate[] {new PrimusCertificate(publicKey)});

		// export key pair
		System.out.println("exporting key pair");
		final KeyStore exportKeyStore = KeyStore.getInstance(PrimusProvider.getImportExportKeyStoreTypeName(), PrimusProvider.getProviderName());
		exportKeyStore.load(null); // no-op, but mandatory for JCA
		final RSAPrivateKey exportedPrivateKey = (RSAPrivateKey)exportKeyStore.getKey(keyName, keyPassword);
		final RSAPublicKey exportedPublicKey = (RSAPublicKey)exportKeyStore.getCertificate(keyName).getPublicKey();
		final KeyPair exportedKeyPair = new KeyPair(exportedPublicKey, exportedPrivateKey);

		// delete key pair on HSM
		keyStore.deleteEntry(keyName);

		// dump modulus, public exponent, private exponent
		System.out.println("key pair @ " + exportedKeyPair);
		System.out.println("RSA modulus = " + exportedPublicKey.getModulus());
		System.out.println("RSA public exponent = " + exportedPublicKey.getPublicExponent());
		System.out.println("RSA private exponent = " + exportedPrivateKey.getPrivateExponent());

		System.out.println("sample done");
	}

}

