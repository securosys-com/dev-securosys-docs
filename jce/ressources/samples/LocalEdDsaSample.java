
/*
 * Copyright (C) 2023, Securosys SA
 */

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;

public class LocalEdDsaSample {

	public static void main(final String... args) throws Exception {

		// configure/login
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;
		// to make sure non-explicit getInstance invocations below are finding Sun/Oracle implementations, not Primus'
		Security.removeProvider(primusProvider.getName());

		// for later plain export test: have [private] keys extractable/not-sensitive
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, false);

		// create a HSM based ed25519 key
		// Primus' approach is EC KeyPairGenerator with ed25519 curve
		final KeyPairGenerator primusEdKeyPairGenerator = KeyPairGenerator.getInstance("EC", primusProvider);
		primusEdKeyPairGenerator.initialize(new ECGenParameterSpec("ed25519"));
		final KeyPair primusEdKeyPair = primusEdKeyPairGenerator.generateKeyPair();
		final Key primusEdKey = primusEdKeyPair.getPrivate();
		final Key primusEdPublicKey = primusEdKeyPair.getPublic();

		// export keys
		final Key exportedPrimusEdKey = KeyFactory.getInstance("EC", primusProvider).translateKey(primusEdKey);
		final Key exportedPrimusEdPublicKey = KeyFactory.getInstance("EC", primusProvider).translateKey(primusEdPublicKey);
		final byte[] exportedPrimusEdKeyEncoding = exportedPrimusEdKey.getEncoded();
		final byte[] exportedPrimusEdPublicKeyEncoding = exportedPrimusEdPublicKey.getEncoded();

		// dump
		System.out.println();
		System.out.println("exported Primus Ed key encoding:");
		System.out.println(PrimusEncoding.hexEncode(exportedPrimusEdKeyEncoding));
		System.out.println(PrimusEncoding.dumpDERCompact(exportedPrimusEdKeyEncoding));
		System.out.println();
		System.out.println("exported Primus Ed public key encoding:");
		System.out.println(PrimusEncoding.hexEncode(exportedPrimusEdPublicKeyEncoding));
		System.out.println(PrimusEncoding.dumpDERCompact(exportedPrimusEdPublicKeyEncoding));

		// convert long exported Primus Ed key encoding (private and public key material) to short encoding (only private key material)
		// cut trailling public key part (32 bytes)
		final byte[] exportedPrimusEdKeyShortEncoding = Arrays.copyOf(exportedPrimusEdKeyEncoding, exportedPrimusEdKeyEncoding.length - 32);
		// make DER lengths shorter (3 locations)
		exportedPrimusEdKeyShortEncoding[exportedPrimusEdKeyShortEncoding.length - 32 - 1] -= 32;
		exportedPrimusEdKeyShortEncoding[exportedPrimusEdKeyShortEncoding.length - 32 - 3] -= 32;
		exportedPrimusEdKeyShortEncoding[1] -= 32;

		// dump
		System.out.println();
		System.out.println("exported Primus Ed key short encoding:");
		System.out.println(PrimusEncoding.hexEncode(exportedPrimusEdKeyShortEncoding));
		System.out.println(PrimusEncoding.dumpDERCompact(exportedPrimusEdKeyShortEncoding));

		// let Java 15 on translate from exportedPrimusEdKeyShortEncoding to its key domain
		// Sun/Oracle's KeyFactory is Ed25519
		// this will throw in case of Java 14 and before
		final KeyFactory localEdKeyFactory;
		try {
			localEdKeyFactory = KeyFactory.getInstance("Ed25519");
		} catch (final NoSuchAlgorithmException e) {
			throw new IllegalStateException("sample needs e.g. Java version 17 for Ed25519 support", e);
		}

		// short encoding is needed here
		final Key localEdKey = localEdKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(exportedPrimusEdKeyShortEncoding));
		final Key localEdPublicKey = localEdKeyFactory.generatePublic(new X509EncodedKeySpec(exportedPrimusEdPublicKeyEncoding));

		// test message for sign/verify tests below
		final byte[] testmessage = "testmessage".getBytes(StandardCharsets.UTF_8);

		// sign on HSM
		final Signature primusSignature = Signature.getInstance("EdDSA", primusProvider);
		primusSignature.initSign((PrivateKey)primusEdKey);
		primusSignature.update(testmessage.clone());
		final byte[] primusSignedDer = primusSignature.sign();
		final byte[] primusSigned = PrimusEncoding.underifyRS(primusSignedDer);

		System.out.println();
		System.out.println("Primus test signature:");
		System.out.println(primusSignature.getProvider());
		System.out.println(PrimusEncoding.hexEncode(primusSignedDer));
		System.out.println(PrimusEncoding.dumpDERCompact(primusSignedDer));
		System.out.println(PrimusEncoding.hexEncode(primusSigned));

		// sign locally
		final Signature localSignature = Signature.getInstance("EdDSA");
		localSignature.initSign((PrivateKey)localEdKey);
		localSignature.update(testmessage.clone());
		final byte[] localSigned = localSignature.sign();
		final byte[] localSignedDer = PrimusEncoding.derifyRS(localSigned);

		System.out.println();
		System.out.println("local test signature:");
		System.out.println(localSignature.getProvider());
		System.out.println(PrimusEncoding.hexEncode(localSignedDer));
		System.out.println(PrimusEncoding.dumpDERCompact(localSignedDer));
		System.out.println(PrimusEncoding.hexEncode(localSigned));

		System.out.println();

		// verify on HSM (needs DER signature): HSM signature
		primusSignature.initVerify((PublicKey)primusEdPublicKey);
		primusSignature.update(testmessage.clone());
		System.out.println(primusSignature.verify(primusSignedDer));

		// verify on HSM (needs DER signature): local signature
		primusSignature.initVerify((PublicKey)primusEdPublicKey);
		primusSignature.update(testmessage.clone());
		System.out.println(primusSignature.verify(localSignedDer));

		// verify locally (needs raw signature): local signature
		localSignature.initVerify((PublicKey)localEdPublicKey);
		localSignature.update(testmessage.clone());
		System.out.println(localSignature.verify(localSigned));

		// verify locally (needs raw signature): HSM signature
		localSignature.initVerify((PublicKey)localEdPublicKey);
		localSignature.update(testmessage.clone());
		System.out.println(localSignature.verify(primusSigned));

	}

}

