
/*
 * Copyright (C) 2021, Securosys SA
 */

import java.security.KeyFactory;
import java.security.Signature;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.securosys.primus.jce.PrimusPrimitives;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs.BlsPrivateKey;
import com.securosys.primus.jce.PrimusSpecs.BlsPublicKey;
import com.securosys.primus.jce.PrimusWrap;

/**
 * BLS unwrap-import sample.
 */
public class BlsUnwrapImportSample {

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

		final BlsPrivateKey key2 = PrimusWrap.aesUnwrapPadBls(aeskey, wrapped); // unwrap-import
		final BlsPublicKey puk2 = (BlsPublicKey)PrimusPrimitives.toPublicKey(blsPublicKeyEncoding); // restore public key from its encoding

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

	}

}

