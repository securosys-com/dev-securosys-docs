
/*
 * Copyright (C) 2017,2022, Securosys SA
 * Author Eric Laroche <eric@securosys.ch>
 */

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.UUID;

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs;

import com.securosys.primus.jce.spec.EdPrivateKeyImpl;

/**
 * Simple ed25519 DSA sample
 * for the Securosys Primus HSM JCE provider.
 *
 * The curve is called "ed25519",
 * the sign algorithm is called "EdDSA".
 */
public class EdDsaSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// for later export/extract tests, set extractable/sensitive flags on to be generated keys
		// NOTE: remember that the Primus HSM needs the export feature enabled too [by SO; hsm_sec_set_config key_export=true]
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, false);

		// generate a ed25519 key [on HSM]
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", primusProvider);
		keyPairGenerator.initialize(new ECGenParameterSpec("ed25519"));
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		final PrimusSpecs.EdPublicKey pub0 = (PrimusSpecs.EdPublicKey)keyPair.getPublic();
		final PrimusSpecs.EdPrivateKey prv0 = (PrimusSpecs.EdPrivateKey)keyPair.getPrivate();

		// public key encoding representations
		System.out.println();
		System.out.println("public key: " + PrimusEncoding.base64Encode(pub0.getEncoded()));
		System.out.println(PrimusEncoding.hexEncode(pub0.getEncoded()));
		System.out.println(PrimusEncoding.dumpDER(pub0.getEncoded()));
		// public key: MCowBQYDK2VwAyEAoB0qmhhNQRg1GeiGDoKwH5+EjlFOvUya4Iuxt2lYaLo=
		// 302a300506032b6570032100a01d2a9a184d41183519e8860e82b01f9f848e514ebd4c9ae08bb1b7695868ba (length 44)
		// DER encoding with public part as bitstring (00: no padded bits)
		// [ [ OID 2b6570 ] BITSTRING 00a01d2a9a184d41183519e8860e82b01f9f848e514ebd4c9ae08bb1b7695868ba ]

		System.out.println("public key raw part: " + PrimusEncoding.base64Encode(pub0.getRaw()));
		System.out.println(PrimusEncoding.hexEncode(pub0.getRaw()));
		// assert Arrays.equals(pub0.getRaw(), DERObject.valueOf(pub0.getEncoded()).getNested(1).getBitstring());
		assert pub0.getRaw().length == 32;

		// sign test
		final byte[] message = ("message-" + UUID.randomUUID().toString()).getBytes(StandardCharsets.US_ASCII);
		final Signature signature = Signature.getInstance("EdDSA", primusProvider);
		signature.initSign(prv0);
		signature.update(message);
		final byte[] signed = signature.sign();

		// signature encoding representations
		System.out.println();
		System.out.println("signature: " + PrimusEncoding.base64Encode(signed));
		System.out.println(PrimusEncoding.hexEncode(signed));
		System.out.println(PrimusEncoding.dumpDER(signed));
		// signature: MEUCIExMTpx2gS+HTIAnYNTE49dGtSmr6PPwiNMrvG3BDenvAiEA0aVY68FDGM8/TwSHxGGyX91B2DVEC9HCZHwqzqfiOwc=
		// 304502204c4c4e9c76812f874c802760d4c4e3d746b529abe8f3f088d32bbc6dc10de9ef022100d1a558ebc14318cf3f4f0487c461b25fdd41d835440bd1c2647c2acea7e23b07 (length 71)
		// DER encoding of R and S as padded big integers (here S' leading 'signed' d1 is padded by 00):
		// [ INTEGER 4c4c4e9c76812f874c802760d4c4e3d746b529abe8f3f088d32bbc6dc10de9ef INTEGER 00d1a558ebc14318cf3f4f0487c461b25fdd41d835440bd1c2647c2acea7e23b07 ]

		// verify test
		signature.initVerify(pub0);
		signature.update(message);
		final boolean verified = signature.verify(signed);
		System.out.println("verified: " + verified);
		assert verified;

		// export private key (needs ACCESS_EXTRACTABLE and ACCESS_SENSITIVE set initially, above)
		final KeyFactory keyFactory = KeyFactory.getInstance("EC", primusProvider);
		final PrimusSpecs.EdPrivateKey prv = (PrimusSpecs.EdPrivateKey)keyFactory.translateKey(prv0);

		// private key encoding representations
		System.out.println();
		System.out.println("private key: " + PrimusEncoding.base64Encode(prv.getEncoded()));
		System.out.println(PrimusEncoding.hexEncode(prv.getEncoded()));
		System.out.println(PrimusEncoding.dumpDER(prv.getEncoded()));
		// private key: ME4CAQAwBQYDK2VwBEIEQAYwkGAluqTa3pyP5vpq3mFg/cz00NRucdQ3eTGYH1I6oB0qmhhNQRg1GeiGDoKwH5+EjlFOvUya4Iuxt2lYaLo=
		// 304e020100300506032b6570044204400630906025baa4dade9c8fe6fa6ade6160fdccf4d0d46e71d4377931981f523aa01d2a9a184d41183519e8860e82b01f9f848e514ebd4c9ae08bb1b7695868ba (length 80)
		// DER encoding with raw key embedded as octet (04) of octet:
		// [ INTEGER 00 [ OID 2b6570 ] OCTET 04400630906025baa4dade9c8fe6fa6ade6160fdccf4d0d46e71d4377931981f523aa01d2a9a184d41183519e8860e82b01f9f848e514ebd4c9ae08bb1b7695868ba ]

		System.out.println("private raw key: " + PrimusEncoding.base64Encode(prv.getRaw()));
		System.out.println(PrimusEncoding.hexEncode(prv.getRaw()));
		// private raw key: BjCQYCW6pNrenI/m+mreYWD9zPTQ1G5x1Dd5MZgfUjqgHSqaGE1BGDUZ6IYOgrAfn4SOUU69TJrgi7G3aVhoug==
		// 0630906025baa4dade9c8fe6fa6ade6160fdccf4d0d46e71d4377931981f523aa01d2a9a184d41183519e8860e82b01f9f848e514ebd4c9ae08bb1b7695868ba (length 64)
		// first half, 0630906025baa4dade9c8fe6fa6ade6160fdccf4d0d46e71d4377931981f523a, is the private key
		// second half, aa01d2a9a184d41183519e8860e82b01f9f848e514ebd4c9ae08bb1b7695868ba, is the public key
		assert prv.getRaw().length == 64;
		assert Arrays.equals(Arrays.copyOfRange(prv.getRaw(), 32, 64), pub0.getRaw());

		// import private key from bytes
		final PrimusSpecs.EdPrivateKey prv2 = (PrimusSpecs.EdPrivateKey)keyFactory.translateKey(EdPrivateKeyImpl.fromBytes(prv.getEncoded()));
		assert Arrays.equals(keyFactory.translateKey(prv2).getEncoded(), prv.getEncoded());

		// import private key from raw bytes
		final PrimusSpecs.EdPrivateKey prv3 = (PrimusSpecs.EdPrivateKey)keyFactory.translateKey(new EdPrivateKeyImpl(prv.getRaw()));
		assert Arrays.equals(keyFactory.translateKey(prv3).getEncoded(), prv.getEncoded());

		// import private key from raw bytes, private part
		assert prv.getRaw().length == 64;
		assert Arrays.equals(Arrays.copyOfRange(prv.getRaw(), 32, 64), pub0.getRaw());
		final byte[] prvpart = Arrays.copyOfRange(prv.getRaw(), 0, 32);
		final PrimusSpecs.EdPrivateKey prv4 = (PrimusSpecs.EdPrivateKey)keyFactory.translateKey(new EdPrivateKeyImpl(prvpart));
		// the export may not be normalized to 64 bytes
		assert Arrays.equals(keyFactory.translateKey(prv4).getEncoded(), prv.getEncoded()) || Arrays.equals(((PrimusSpecs.EdPrivateKey)keyFactory.translateKey(prv4)).getRaw(), prvpart);

		// [wait for 2.3.3 for this]
		// keyFactory.translateKey(EdPrivateKeyImpl.fromBytes((new EdPrivateKeyImpl(prvpart)).getEncoded()));

		// sign test with the imported key
		signature.initSign(prv4);
		signature.update(message);
		final byte[] signed2 = signature.sign();

		// verify test
		signature.initVerify(pub0);
		signature.update(message);
		final boolean verified2 = signature.verify(signed2);
		System.out.println("verified: " + verified2);
		assert verified2;

	}

}

