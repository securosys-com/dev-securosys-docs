
/*
 * Copyright (C) 2017-2023, Securosys SA
 */

import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.util.UUID;

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusProvider;

/**
 * PKCS#12 key store samples.
 */
public class Pkcs12Sample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// extract key and certificate from PKCS#12 bag (Java PKCS12 keystore, keystore key password 'password', key/certificate names 'name', key password 'password')
		final ByteArrayInputStream keyStoreInputStream = new ByteArrayInputStream(samplePkcs12FileContents);
		// alternatively: use a FileInputStream when reading PKCS#12/keystore from file

		// load bag as Java PKCS12 keystore
		final KeyStore bagKeyStore = KeyStore.getInstance("pkcs12");
		bagKeyStore.load(keyStoreInputStream, "password".toCharArray());

		// retrieve key and certificate
		final PrivateKey key = (PrivateKey)bagKeyStore.getKey("name", "password".toCharArray());
		assert key != null;
		final Certificate certificate = bagKeyStore.getCertificate("name");
		assert certificate != null;

		// key and certificate import to HSM (via 'ImportExportKeyStore')
		final KeyStore hsmImportKeyStore = KeyStore.getInstance(PrimusProvider.getImportExportKeyStoreTypeName(), PrimusProvider.getProviderName());
		hsmImportKeyStore.load(null);
		// on the HSM: use a random name, not to collide with a pre-existing entry
		final String keyName = UUID.randomUUID().toString();
		hsmImportKeyStore.setKeyEntry(keyName, key, null, new Certificate[] {certificate});

		// get reference on the imported key and certificate (via 'normal' KeyStore)
		final KeyStore hsmKeyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		hsmKeyStore.load(null);
		final PrivateKey hsmKey = (PrivateKey)hsmKeyStore.getKey(keyName, null);
		assert hsmKey != null;
		final Certificate hsmCertificate = hsmKeyStore.getCertificate(keyName);
		assert hsmCertificate != null;

		// test signing with HSM
		final Signature signature = Signature.getInstance("SHA256withRSA", PrimusProvider.getProviderName());
		signature.initSign(hsmKey);
		final byte[] message = UUID.randomUUID().toString().getBytes();
		signature.update(message);
		final byte[] signatureBytes = signature.sign();

		// test verifying locally
		final Signature verifySignature = Signature.getInstance("SHA256withRSA");
		verifySignature.initVerify(hsmCertificate);
		verifySignature.update(message);
		final boolean verified = verifySignature.verify(signatureBytes);
		assert verified;

	}

	// sample PKCS#12 data, generated with openssl command line tool,
	// having password as passwords and name as names:
	// openssl genrsa -des3 -out private.pem 2048
	// openssl rsa -in private.pem -outform PEM -pubout -out public.pem
	// openssl rsa -in private.pem -out private_unencrypted.pem -outform PEM
	// openssl req -new -key private.pem -out req
	// openssl x509 -req -days 3650 -in req -signkey private.pem -out cert
	// openssl pkcs12 -keypbe PBE-SHA1-3DES -certpbe PBE-SHA1-3DES -export -in cert -inkey private.pem -out pkcs12 -name name

	static byte[] samplePkcs12FileContents = PrimusEncoding.hexDecode(
		"3082097a0201033082094006092a864886f70d010701a08209310482092d30820929308203c706092a864886f70d010706a08203b8308203b4020100308203ad"+
		"06092a864886f70d010701301c060a2a864886f70d010c0103300e04082acbebc59b44d82b0202080080820380db5f20dfdc2812142e7959919426540bf4a3cc"+
		"26399b12b688a64c6385667724629d38c336f78e3b1726345937ab6f17709656b28b645dc1269f43a67d18756468f7423ced664a60c28da2455b9d10e890a8b8"+
		"c4db1fdf362e663dd201e311fa63071c30f7f35e88237e09f7e191c996828c235d0cf3f68a882acb0e674e397dede69285839970e4a545ef1de371b0acc75de9"+
		"4694770412480502850514b78a8684be741f5f82fd710d9b7386bf7fa316d38a07dcf3c27fb91d41f33f2e2f6cb84101dfb5781cf928ac22051b68cfa8ed6c12"+
		"ab4bc6bd8c0d756ecc0ac66b9ae7221e25c85642a9e73981d5ececdfc6befdbf63a4030f36f17b938f9d0907a8ad4ba4a55d9633a0293587d60c706b7e4e34b1"+
		"8bc882d4e594e458f8c93b4b6ba066e9763ccc87a31c20a9b6e16a90b40984ebbdba4c06b2f833154bb5e388947d7290c6c0ff69f253ea2aa85ce67e5bff1384"+
		"7bc238fa66a1f979b22013c9eda74b69a7942251ef04ed1800d328a69d4be5c573296f51d239ae41210a9708ef4f7d03a30af5d02d6a32592855491977f47a3b"+
		"fa9da912c87abd096acb9b5b700572e1a196332156cebd47bcb251fea424b0a7e9948b762ba7921dd0ee67dd62f4e43d10bb713d93cb8f1a4429c6b63caa3fd6"+
		"95cf0fd5b637f33d3888049fe4dc5a69aeb86391bc22e3263cc61bbdc5826fa3c5f3be99d0b22eaed23c97460d2bcd8614a92bfb2c66774d857ddf1b0f3b35f2"+
		"c1624fc0e8dd9498ea8184c8d094d0bd14fc55607242c90e065dafbccbedf84a6d4290948212120fd45c959deab34ccf8e8302d5441ce30ea3ca32cff4678575"+
		"59a3863229088bc1d0d2678b41db341ebc7eec3d30cd52ab24963f630d39e0e2b69b63551e91e2e6e5cfa2a6c1a5911f5e8a5944d7b7dcf5b6a943da625e4b12"+
		"dd9ae67cad208136d3020c2cab0199479f5295aa8d653f92f47eb987d8f947fe54448aa38bfbaa22617e5bda1a42fd1991e630ce2557caaf9f3e50cc12b29ea3"+
		"22ee7b0ab3dccfd55809122cb1f1ccb6ae453b9dbb289d12fc1b54003d668405e74dea1420f860d787a25adcd57314d78c6149133d7a6abad673b9e954d1a55a"+
		"7b76002bd08344a1fb6f6213f432e9614f9a4b1103413a75a0d74e8fba60646449ca41cd2979775e112e05815a4668c8d6120ed51e483150430eb4455e567f71"+
		"c0b4f1c2804402242342aef06f034d204cfd9023584e969afa3d0fc4f1ad4c56d744ac5b20be60436beb1204973082055a06092a864886f70d010701a082054b"+
		"04820547308205433082053f060b2a864886f70d010c0a0102a08204ee308204ea301c060a2a864886f70d010c0103300e0408c987c7398b2b3cc30202080004"+
		"8204c82ba22650cb5bf7e8b1dcde4a1cd531d78306f82ebafdfbb5ea94f5a55c63cb63f31acc62889c01e575759ef156a59fdb53edff9aff99bb47069dd11e44"+
		"7d308b71f196999585c7ea7c3d3a0cf9ac07469a42e60bc1e7a7799f75f805c88f65b7a0e97589ce221c184f9fb1b13a8eb98e0a211e728aab03ec8b9b97c0f0"+
		"cf17c51bfb1424d14d06d779e8b20c3a9ebea676602713dddde56c180e92ae7e58152f75fc75bd16fac744b673a249b94c842238f8da371588b45f2f04d6b032"+
		"7bffd9d1ef4df254e3a765eaebb1380fa5aa96b1c5b11c5719b14a01a8f62013fde72b3af1cd38f61e436a00541517c8e0e8d7e2882b98f61ee8a14eccc5c5f6"+
		"7d8b58a9f78d55979367ca1f921317a553c8459c2ea6c36e35bcf92944bb36d1d183c430064f9af6cfa4a81286e89e025b27a9d30889107182791cc357fd6f78"+
		"8194e662108c1d32cc86972a55f2d7a4bdba64e4d76d0113b77e60efdb426fd463392a80a7235a55611157a7b2c45909d5965006e608609fa453ca3c2adddf77"+
		"874cac8ce2e22595e497c7a3814395e0fab1d2406266a65b6d8ddd2ccc07160fa83462a8ed47e9a6786c39dcb8788f0b5b1411b2d6cd84a7cc0e748f8ba44648"+
		"6a0a078e7af3b5b3c3b018479792474faccfed2a5b7712c05613805cefe015b1346f05b65b80ba70856ba14b19b5e8c8b93fc1c773404b29f02c45991fa2ec8a"+
		"f78902321d7a9638500ef34c3819da0d333023d46321d6bbe4151f11d2b13487292bcc0b57a670899850360e6c897243b3486d997431fcd8827fcd45e69baad9"+
		"9f0f476962b57d0b8d9ef038d189c1b1a4a671f5f58f202ad6d818c7469c8eef2c0cff72290dbe4e19cd430769f2e1733610e5d739f904f0f9e7be9dcf140c15"+
		"934e36cf2127117215becdbfc141a002d9314e224ce0cb8ffef3edd84c1dc1b39ed2035447b817d293a60ffdd2054eca7bc0bde4efcd23564f1cc5ec42047aef"+
		"fce32d345208851ec7c900d30d4b9e08bbe6fc9c7a156435ab51d62457104d024d8829a1e6b566e44bb7034f1dcf1b56cc60f22eb31a770af8cb29026f02d93c"+
		"bc86be0c4354e6df4e78cf3f135d9129ccbbcf23d87d507abeebe0a128547ed94769e85b30a52dd46be1c8c49f45f0294b369b84bd3c8cee9c1ffc84251faf30"+
		"24c02002875e614023cb1e3b766b7600b26dd40b4b5cfd059657b471d03a56833b03d9e0dc0ad45b3404b95fa6c539a679100dc629a8e82ce53a6356dbb5919a"+
		"8d00657a165a0c1265fdf649b4dc2ad0ee7a990e01572a3e8a83f83972c61c1f67d7b4daaa995c042df2df0d01f737077e138d9665e33071d335d69b26973db8"+
		"b3893710d3ad38f4c84af0a325290985f939aa600ff416a6bb6fc961458c643ab54563c686f0fa7cc427bb179d94832aeffea6b84e373e358e79ece43b5a6d71"+
		"e92d569b37164788e4ff038ff13fa3c09420d449bbf6352b9bfc60049da68f83798ffd998229a30b09bf097631cbcf04d50e89409165b9a066c5aea7e01902c0"+
		"915926271dbf6d429207753d2bd71349782eae509e6257f27994d8651e036c047618b131649bb1de894184cc2a9f96e81f6c737c940911e3e564510b5ae03b20"+
		"2ba1091f352632d0bce64fa826e3382e6d0026d8f421ae9f992f2587ec33b60d422b93a64b6bc811014dcd78ce15d97e876d89e6fb7a9a917f0b230833fc1324"+
		"078ec892f0a2fd1676507c313e301706092a864886f70d010914310a1e08006e0061006d0065302306092a864886f70d0109153116041498c90d289ba1a8d58e"+
		"57fbe78efea791b41f618c30313021300906052b0e03021a05000414a7d4a87b938fee0de88a5e6ee033052bb3ff58850408cb8a85a354bac59a02020800"
	);

}

