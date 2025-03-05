
/*
 * Copyright (C) 2019,2020 Securosys SA
 * Author Eric Laroche <eric@securosys.ch>
 */

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusLogin;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusX509Certificate;

/**
 * XML key unwrap/import sample.
 */
public class XmlKeyUnwrapImportSample {

	static String host = null;
	static String port = null;
	static String user = null;
	static String password = null;

	public static void main(final String... args) throws Exception {

		String unwrapKey = null;
		String importKey = null;
		int i = 0;
		for (; i < args.length; i++) {
			final String arg = args[i];
			if (arg.equals("-unwrapKey")) {
				if (++i >= args.length) {
					break;
				} else {
					unwrapKey = args[i];
				}
			} else if (arg.equals("-importKey")) {
				if (++i >= args.length) {
					break;
				} else {
					importKey = args[i];
				}
			} else if (arg.equals("-host")) {
				if (++i >= args.length) {
					break;
				} else {
					host = args[i];
				}
			} else if (arg.equals("-port")) {
				if (++i >= args.length) {
					break;
				} else {
					port = args[i];
				}
			} else if (arg.equals("-user")) {
				if (++i >= args.length) {
					break;
				} else {
					user = args[i];
				}
			} else if (arg.equals("-password")) {
				if (++i >= args.length) {
					break;
				} else {
					password = args[i];
				}
			} else {
				break;
			}
		}
		if (
			i < args.length || unwrapKey == null || importKey == null
			|| host == null || port == null || user == null || password == null
		) {
			System.err.println("usage: java " + XmlKeyUnwrapImportSample.class.getName() + " -unwrapKey <unwrap key xml file> -importKey <import key xml file>");
			System.err.println("\t-host <HSM host name/address> -port <HSM TCP port number> -user <HSM user> -password <HSM user password>");
			System.exit(1);
		}

		xmlKeyImportUnwrap(unwrapKey, importKey);
	}

	public static void xmlKeyImportUnwrap(final String unwrapKeyXmlFile, final String importKeyXmlFile) throws Exception {
		final SecretKeySpec unwrapKey = readUnwrapKey(unwrapKeyXmlFile);
		unwrapKeys(unwrapKey, importKeyXmlFile);
	}

	static SecretKeySpec readUnwrapKey(final String unwrapKeyXmlFile) throws Exception {
		// read in unwrap key
		SecretKeySpec unwrapKey = null;
		// expecting '<?xml version="1.0"?><PKCS11>' but actually don't care here
		final Element rootElement = getElementFromFile(unwrapKeyXmlFile);
		final NodeList nodes = rootElement.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			final Node node = nodes.item(i);
			if (node instanceof Element) {
				final Element element = (Element)node;
				final String elementName = element.getNodeName();

				// handling AESSecretKey only
				if (elementName.equals("AESSecretKey")) {
					if (unwrapKey != null) {
						throw new RuntimeException("more than one unwrap key found");
					}
					final String aesKeyEncoding = getElementByTagName(element, "value").getTextContent();
					final byte[] aesKeyValue;
					try {
						aesKeyValue = Base64.getDecoder().decode(aesKeyEncoding);
					} catch (IllegalArgumentException e) {
						throw new RuntimeException("bad base64 encoding of value in AESSecretKey: " + e, e);
					}
					unwrapKey = new SecretKeySpec(aesKeyValue, "AES");

				} else {
					System.err.println(unwrapKeyXmlFile + ": ignoring unsupported element " + elementName);
				}
			}
		}
		if (unwrapKey == null) {
			throw new RuntimeException("no unwrap key found");
		}

		return unwrapKey;
	}

	static void unwrapKeys(final SecretKeySpec unwrapKey, final String importKeyXmlFile) throws Exception {
		// process wrapped keys
		// expecting '<?xml version="1.0"?><PKCS11>' but actually don't care here
		final Element rootElement = getElementFromFile(importKeyXmlFile);
		final NodeList nodes = rootElement.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			final Node node = nodes.item(i);
			if (node instanceof Element) {
				final Element element = (Element)node;
				final String elementName = element.getNodeName();

				// handling RSAPrivateKey only
				if (elementName.equals("RSAPrivateKey")) {
					final String label = getElementByTagName(element, "label").getTextContent();

					final String rsaKeyPublicExponentEncoding = getElementByTagName(element, "publicExponent").getTextContent();
					if (rsaKeyPublicExponentEncoding == null || rsaKeyPublicExponentEncoding.length() == 0) {
						throw new RuntimeException("publicExponent empty");
					}
					final byte[] rsaKeyPublicExponent = Base64.getDecoder().decode(rsaKeyPublicExponentEncoding);
					final String rsaKeyWrappedValueEncoding = getElementByTagName(element, "wrappedValue").getTextContent();
					if (rsaKeyWrappedValueEncoding == null || rsaKeyPublicExponentEncoding.length() == 0) {
						throw new RuntimeException("wrappedValue empty");
					}
					final byte[] rsaKeyWrappedValue = Base64.getDecoder().decode(rsaKeyWrappedValueEncoding);
					final String rsaKeyIvEncoding = getElementByTagName(element, "iv").getTextContent();
					if (rsaKeyIvEncoding == null || rsaKeyIvEncoding.length() == 0) {
						throw new RuntimeException("iv empty");
					}
					final byte[] rsaKeyIv = Base64.getDecoder().decode(rsaKeyIvEncoding);

					final KeyPair keyPair = unwrapRsaKey(label, rsaKeyPublicExponent, rsaKeyWrappedValue, unwrapKey, rsaKeyIv);
					importKey(label, keyPair);

				} else {
					System.err.println(importKeyXmlFile + ": ignoring unsupported element " + elementName);
				}
			}
		}
	}

	static KeyPair unwrapRsaKey(final String label, final byte[] rsaKeyPublicExponent, final byte[] rsaKeyWrappedValue, final SecretKeySpec unwrapKey, final byte[] iv) throws Exception {
		// build (unwrap) local RSA key
		final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.UNWRAP_MODE, unwrapKey, new IvParameterSpec(iv));
		final RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)cipher.unwrap(rsaKeyWrappedValue, "RSA", Cipher.PRIVATE_KEY);
		final RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(rsaPrivateKey.getModulus(), new BigInteger(1, rsaKeyPublicExponent));
		final RSAPublicKey rsaPublicKey = (RSAPublicKey)KeyFactory.getInstance("RSA").generatePublic(rsaPublicKeySpec);

		// sign/verify test
		final Signature localSign = Signature.getInstance("SHA256withRSA");
		final Signature localVerify = Signature.getInstance("SHA256withRSA");
		localSign.initSign(rsaPrivateKey);
		localVerify.initVerify(rsaPublicKey);
		final boolean verified = localVerify.verify(localSign.sign());

		if (!verified) {
			System.err.println("note: local sign/verify test failed for label " + label);
		}

		return new KeyPair(rsaPublicKey, rsaPrivateKey);
	}

	static void importKey(final String label, final KeyPair keyPair) throws KeyStoreException, NoSuchProviderException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, InvalidKeyException, SignatureException {

		// install Primus JCE provider, configure, log in to HSM
		Security.addProvider(new PrimusProvider());
		PrimusConfiguration.setHsmHost(host, port, user);
		PrimusLogin.login(user, password.toCharArray());

		// import local RSA key to HSM
		final KeyStore importKeyStore = KeyStore.getInstance(PrimusProvider.getImportExportKeyStoreTypeName(), PrimusProvider.getProviderName());
		importKeyStore.load(null);
		final String keyName = (label == null ? UUID.randomUUID().toString() : label);
		final char[] keyPassword = null;
		importKeyStore.setKeyEntry(keyName, keyPair.getPrivate(), keyPassword, new Certificate[] {new PrimusX509Certificate(keyPair.getPublic())});

		// get a reference to the HSM keys
		final KeyStore hsmKeyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), PrimusProvider.getProviderName());
		hsmKeyStore.load(null);
		final RSAPrivateKey rsaPrivateHsmKey = (RSAPrivateKey)hsmKeyStore.getKey(keyName, keyPassword);
		final RSAPublicKey rsaPublicHsmKey = (RSAPublicKey)hsmKeyStore.getCertificate(keyName).getPublicKey();

		// HSM sign / local verify test
		final Signature hsmSign = Signature.getInstance("SHA256withRSA", PrimusProvider.getProviderName());
		final Signature localVerify = Signature.getInstance("SHA256withRSA");
		hsmSign.initSign(rsaPrivateHsmKey);
		localVerify.initVerify(rsaPublicHsmKey);
		final boolean verified = localVerify.verify(hsmSign.sign());

		if (!verified) {
			System.err.println("note: HSM sign / local verify test failed for label " + label);
		}
	}

	static Element getElementFromFile(final String fileName) throws SAXException, IOException, ParserConfigurationException {
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName).getDocumentElement();
	}

	static Element getElementByTagName(final Element element, final String tagName) {
		final NodeList nodes = element.getElementsByTagName(tagName);
		final int n = nodes.getLength();
		if (n == 0) {
			throw new RuntimeException("tag " + tagName + " not found in element " + element.getNodeName());
		} else if (n > 1) {
			throw new RuntimeException("more than one tag " + tagName + " found in element " + element.getNodeName());
		}
		final Node node = nodes.item(0);
		if (!(node instanceof Element)) {
			throw new RuntimeException("tag " + tagName + " is not an element");
		}
		return (Element)node;
	}

}

