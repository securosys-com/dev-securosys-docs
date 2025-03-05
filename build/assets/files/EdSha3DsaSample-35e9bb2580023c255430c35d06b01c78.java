
/*
 * Copyright (C) 2021,2022, Securosys SA
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
 * Simple ed25519sha3 DSA sample
 * for the Securosys Primus HSM JCE provider.
 *
 * The curve is called "ed25519sha3",
 * the sign algorithm is called "EdDSA".
 */
public class EdSha3DsaSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// for later export/extract tests, set extractable/sensitive flags on to be generated keys
		// NOTE: remember that the Primus HSM needs the export feature enabled too [by SO; hsm_sec_set_config key_export=true]
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, false);

		// generate a ed25519sha3 key [on HSM]
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", primusProvider);
		keyPairGenerator.initialize(new ECGenParameterSpec("ed25519sha3"));
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		final PrimusSpecs.EdPublicKey pub0 = (PrimusSpecs.EdPublicKey)keyPair.getPublic();
		final PrimusSpecs.EdPrivateKey prv0 = (PrimusSpecs.EdPrivateKey)keyPair.getPrivate();

		// public key encoding representations
		System.out.println();
		System.out.println("public key: " + PrimusEncoding.base64Encode(pub0.getEncoded()));
		System.out.println(PrimusEncoding.hexEncode(pub0.getEncoded()));
		System.out.println(PrimusEncoding.dumpDER(pub0.getEncoded()));

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

		System.out.println("private raw key: " + PrimusEncoding.base64Encode(prv.getRaw()));
		System.out.println(PrimusEncoding.hexEncode(prv.getRaw()));
		assert prv.getRaw().length == 64;
		assert Arrays.equals(Arrays.copyOfRange(prv.getRaw(), 32, 64), pub0.getRaw());

		// import private key from bytes
		final PrimusSpecs.EdPrivateKey prv2 = (PrimusSpecs.EdPrivateKey)keyFactory.translateKey(EdPrivateKeyImpl.fromBytes(prv.getEncoded()));
		assert Arrays.equals(keyFactory.translateKey(prv2).getEncoded(), prv.getEncoded());

		// import private key from raw bytes
		// [needs 2.3.3]
		// final PrimusSpecs.EdPrivateKey prv3 = (PrimusSpecs.EdPrivateKey)keyFactory.translateKey(new EdPrivateKeyImpl(prv.getRaw(), EdPrivateKeyImpl.edsha3oid()));
		// assert Arrays.equals(keyFactory.translateKey(prv3).getEncoded(), prv.getEncoded());

		// import private key from raw bytes, private part
		assert prv.getRaw().length == 64;
		assert Arrays.equals(Arrays.copyOfRange(prv.getRaw(), 32, 64), pub0.getRaw());
		// final byte[] prvpart = Arrays.copyOfRange(prv.getRaw(), 0, 32);
		// [needs 2.3.3]
		// final PrimusSpecs.EdPrivateKey prv4 = (PrimusSpecs.EdPrivateKey)keyFactory.translateKey(new EdPrivateKeyImpl(prvpart, EdPrivateKeyImpl.edsha3oid()));
		// the export may not be normalized to 64 bytes
		// assert Arrays.equals(keyFactory.translateKey(prv4).getEncoded(), prv.getEncoded()) || Arrays.equals(((PrimusSpecs.EdPrivateKey)keyFactory.translateKey(prv4)).getRaw(), prvpart);
		final PrimusSpecs.EdPrivateKey prv4 = prv2;

		// [needs 2.3.3]
		// keyFactory.translateKey(EdPrivateKeyImpl.fromBytes((new EdPrivateKeyImpl(prvpart, EdPrivateKeyImpl.edsha3oid())).getEncoded()));

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

