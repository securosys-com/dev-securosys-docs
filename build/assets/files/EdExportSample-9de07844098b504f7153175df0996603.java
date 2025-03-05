
/*
 * Copyright (C) 2022,2023, Securosys SA
 */

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusPrimitives;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs;

public class EdExportSample {

	public static void main(final String... args) throws Exception {

		// configure / log in to HSM
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;
		// to make test artifacts unlikely, to make sure non-explicit getInstance invocations below are finding Sun/Oracle implementations, not Primus'
		Security.removeProvider(primusProvider.getName());

		// generate a [ed25519] key [pair] on the HSM
		final String hsmKeyName = UUID.randomUUID().toString();
		PrimusName.generate(
			hsmKeyName,
			KeyPairGenerator.getInstance("EC", primusProvider),
			new ECGenParameterSpec("ed25519"),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true)
		);
		final PrivateKey hsmKey = PrimusPrimitives.getPrivateKey(hsmKeyName);
		final PublicKey hsmPublicKey = PrimusPrimitives.getPublicKeyDirectly(hsmKeyName);
		System.out.println(hsmKey.getClass().getName() + "/" + hsmPublicKey.getClass().getName());
		// 'com.securosys.primus.jce.object.EdPrivateKeyObject/com.securosys.primus.jce.object.EdPublicKeyObject'

		// export
		final KeyFactory kf = KeyFactory.getInstance("EC", primusProvider);
		final PrivateKey key = (PrivateKey)kf.translateKey(hsmKey);
		final PublicKey publicKey = (PublicKey)kf.translateKey(hsmPublicKey);
		System.out.println(key.getClass().getName() + "/" + publicKey.getClass().getName());
		// 'com.securosys.primus.jce.spec.EdPrivateKeyImpl/com.securosys.primus.jce.spec.EdPublicKeyImpl'

		// convert
		final KeyFactory kf2;
		try {
			kf2 = KeyFactory.getInstance("Ed25519");
		} catch (final NoSuchAlgorithmException e) {
			throw new IllegalStateException("sample needs e.g. Java version 17 for Ed25519 support", e);
		}
		final PrivateKey key2 = kf2.generatePrivate(new PKCS8EncodedKeySpec(((PrimusSpecs.EdPrivateKey)key).getEncodedShort()));
		final PublicKey publicKey2 = kf2.generatePublic(new X509EncodedKeySpec(publicKey.getEncoded()));
		System.out.println(key2.getClass().getName() + "/" + publicKey2.getClass().getName());
		// 'sun.security.ec.ed.EdDSAPrivateKeyImpl/sun.security.ec.ed.EdDSAPublicKeyImpl'

		// test message for sign/verify tests
		final byte[] testmessage = "testmessage".getBytes(StandardCharsets.UTF_8);

		// sign on HSM
		final Signature hsmSignature = Signature.getInstance("EdDSA", primusProvider);
		hsmSignature.initSign(hsmKey);
		hsmSignature.update(testmessage.clone());
		final byte[] hsmSigned = hsmSignature.sign();

		// sign locally
		final Signature localSignature = Signature.getInstance("EdDSA");
		localSignature.initSign(key2);
		localSignature.update(testmessage.clone());
		final byte[] localSigned = localSignature.sign();

		boolean verified;

		// verify on HSM (needs DER signature): HSM signature
		hsmSignature.initVerify(hsmPublicKey);
		hsmSignature.update(testmessage.clone());
		verified = hsmSignature.verify(PrimusEncoding.optionallyDerifyRS(hsmSigned));
		System.out.println(verified);
		assert verified;

		// verify on HSM (needs DER signature): local signature
		hsmSignature.initVerify(hsmPublicKey);
		hsmSignature.update(testmessage.clone());
		verified = hsmSignature.verify(PrimusEncoding.optionallyDerifyRS(localSigned));
		System.out.println(verified);
		assert verified;

		// verify locally (needs raw signature): local signature
		localSignature.initVerify(publicKey2);
		localSignature.update(testmessage.clone());
		verified = localSignature.verify(PrimusEncoding.optionallyUnderifyRS(localSigned));
		System.out.println(verified);
		assert verified;

		// verify locally (needs raw signature): HSM signature
		localSignature.initVerify(publicKey2);
		localSignature.update(testmessage.clone());
		verified = localSignature.verify(PrimusEncoding.optionallyUnderifyRS(hsmSigned));
		System.out.println(verified);
		assert verified;

	}

}

