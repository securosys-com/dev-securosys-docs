
/*
 * Copyright (C) 2021, Securosys SA
 */

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.Signature;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusPrimitives;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs.BlsPrivateKey;
import com.securosys.primus.jce.PrimusSpecs.BlsPublicKey;
import com.securosys.primus.jce.PrimusWrap;

/**
 * BLS wrap sample.
 */
public class BlsWrapSample {

	public static void main(final String... args) throws Exception {

		// for later signature check (Signature.initVerify with exported public key)
		// do this early, as it's retrieved in a static (at class loading time) way
		System.setProperty("com.securosys.primus.jce.adhocPublicKeyImport", "true");

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), primusProvider);
		keyStore.load(null);

		// we want it extractable, for wrapping
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, true);
		// we want to export is as well in this sample/test, to test
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, false);

		final String keyname = UUID.randomUUID().toString();
		PrimusName.presetName(keyname);
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("BLS", primusProvider);
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		final BlsPrivateKey key = (BlsPrivateKey)keyStore.getKey(keyname, null); // HSM based key
		final Key keyout = KeyFactory.getInstance(key.getAlgorithm(), primusProvider).translateKey(key); // exported/local key

		System.out.println("BLS private key: " + Base64.getEncoder().encodeToString(keyout.getEncoded()));
		System.out.println("BLS public key: " + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));

		final String aeskeyname = UUID.randomUUID().toString();
		PrimusName.presetName(aeskeyname);
		final KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", primusProvider);
		keyGenerator.init(256);
		keyGenerator.generateKey(); // generate a named HSM based AES key, to wrap/unwrap with
		final SecretKey aeskey = (SecretKey)keyStore.getKey(aeskeyname, null);

		System.out.println("AES key: " + Base64.getEncoder().encodeToString(KeyFactory.getInstance(aeskey.getAlgorithm(), primusProvider).translateKey(aeskey).getEncoded()));

		final byte[] wrapped = PrimusWrap.aesWrapPadBls(aeskey, key); // wrap-exported key material

		System.out.println("wrapped: " + Base64.getEncoder().encodeToString(wrapped));

		final byte[] puk = keyPair.getPublic().getEncoded();
		keyStore.deleteEntry(keyname); // as a sample: delete the original key
		final BlsPrivateKey key2 = PrimusWrap.aesUnwrapPadBls(aeskey, wrapped); // unwrap-import
		final Key keyout2 = KeyFactory.getInstance(key2.getAlgorithm(), primusProvider).translateKey(key2); // export unwrapped key
		final BlsPublicKey puk2 = (BlsPublicKey)PrimusPrimitives.toPublicKey(puk); // restore public key from its encoding

		// sign operation
		final byte[] message = UUID.randomUUID().toString().getBytes();
		final Signature signature = Signature.getInstance("BLS", primusProvider);
		signature.initSign(key2);
		signature.update(message);
		final byte[] sig = signature.sign();

		// check/verify
		final Signature verify = Signature.getInstance("BLS", primusProvider);
		verify.initVerify(puk2);
		verify.update(message);
		assert verify.verify(sig);

		// compare the initial exported and unwrapped exported
		assert keyout.getEncoded() != null && keyout2.getEncoded() != null;
		assert Arrays.equals(keyout.getEncoded(), keyout2.getEncoded());

	}

}

