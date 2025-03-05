
/*
 * Copyright (C) 2021, Securosys SA
 */

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessGroup;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusApprovalToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusAuthorizationToken;
import com.securosys.primus.jce.PrimusPrimitives;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs.BlsPrivateKey;
import com.securosys.primus.jce.PrimusSpecs.BlsPublicKey;
import com.securosys.primus.jce.PrimusWrap;

/**
 * BLS unwrap-import SKA sample.
 */
public class BlsUnwrapImportSkaSample {

	public static void main(final String... args) throws Exception {

		// for later signature check (Signature.initVerify with exported public key)
		// do this early, as it's retrieved in a static (at class loading time) way
		System.setProperty("com.securosys.primus.jce.adhocPublicKeyImport", "true");

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// final byte[] blsPrivateKeyEncoding = Base64.getDecoder().decode("NAkLaEmKt/XiqVC48grSwUviHXxAjwEdk27FBfrap3s=");
		final byte[] blsPublicKeyEncoding = Base64.getDecoder().decode("MFEwHAYMKwYBBAGC3HwFAwEBBgwrBgEEAYLcfAUDAgEDMQC0V55z5AbJXpvrdqeDsFo8C+OibZj2gVuy+YsR0oOAELQVW3pM7pNddwEYTg+6Wtw=");
		final byte[] aesKeyEncoding = Base64.getDecoder().decode("VG8Rw0sGICtCFbIags0nHPTvJnwNQes8tQg5OykPyug=");
		final byte[] wrapped = Base64.getDecoder().decode("DxAswkVwkQzUVw/IAZVxkv0Q0FNEF+4TktGIyd1h47FUVb78PiW83QKw3DMBUuRjqN9Pp6HIZlkGzIXJ773zfvCjDCt72sg4T/a5v8Zs26e8BJc6QSbHfQ==");

		final SecretKey aeskey = (SecretKey)KeyFactory.getInstance("AES", primusProvider).translateKey(new SecretKeySpec(aesKeyEncoding, "AES"));

		// create authorization keys
		final int n = 4;
		final KeyPair[] authorizationKeys = new KeyPair[n];
		final PublicKey[] authorizationPublicKeys = new PublicKey[n];
		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		for (int i = 0; i < authorizationKeys.length; i++) {
			authorizationKeys[i] = localKeyPairGenerator.generateKeyPair();
			authorizationPublicKeys[i] = authorizationKeys[i].getPublic();
		}
		final PrimusAccessToken accessToken = new PrimusAccessToken(new PrimusAccessGroup(authorizationPublicKeys, n));
		final PrimusAccessBlob accessBlob = new PrimusAccessBlob(accessToken);
		final PrimusAccess access = new PrimusAccess(accessBlob, accessBlob, accessBlob, accessBlob);
		PrimusAccess.setAccess(access);

		final BlsPrivateKey key2 = PrimusWrap.aesUnwrapPadBls(aeskey, wrapped); // unwrap-import
		final BlsPublicKey puk2 = (BlsPublicKey)PrimusPrimitives.toPublicKey(blsPublicKeyEncoding); // restore public key from its encoding

		final byte[] message = UUID.randomUUID().toString().getBytes(); // random payload to sign
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, message)).getEncoding();

		final PrimusAuthorizationToken[] tokens = new PrimusAuthorizationToken[n];
		for (int i = 0; i < n; i++) {
			tokens[i] = new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", authorizationKeys[i].getPrivate(), authorizationKeys[i].getPublic());
		}
		final PrimusAuthorization primusAuthorization = new PrimusAuthorization();
		for (int i = 0; i < n; i++) {
			primusAuthorization.add(tokens[i]);
		}
		PrimusAuthorization.setAuthorization(primusAuthorization);

		// sign operation
		final Signature signature = Signature.getInstance("BLS", primusProvider);
		signature.initSign(key2);
		signature.update(message);
		final byte[] sig = signature.sign();

		// check/verify
		final Signature verify = Signature.getInstance("BLS", primusProvider);
		verify.initVerify(puk2);
		verify.update(message);
		assert verify.verify(sig);

	}

}

