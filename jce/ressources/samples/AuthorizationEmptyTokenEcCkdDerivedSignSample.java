
/*
 * Copyright (C) 2019-2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusKeyHelper;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSpecs;

/**
 * Sample code using PrimusAccess/PrimusAuthorization
 * with the Securosys Primus HSM JCE provider.
 *
 * Sign operation works with a base key and a derivation parameter.
 */
public class AuthorizationEmptyTokenEcCkdDerivedSignSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// empty access: blobs of one empty token
		final PrimusAccessToken token = new PrimusAccessToken();
		final PrimusAccessBlob blob = new PrimusAccessBlob(token);
		final PrimusAccess access = new PrimusAccess(blob, blob, blob, blob);

		// key-pair creation with access and flags set:
		final String signKeyName = UUID.randomUUID().toString();
		final KeyPair keyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			new PrimusSpecs.CkdEnabled(new ECGenParameterSpec("secp256k1")),
			signKeyName,
			access,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_SIGN, true),
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_DECRYPT, false),
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_DERIVE, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_MODIFIABLE, false)
		);
		final PrivateKey signKey = keyPair.getPrivate();

		// generate a transaction payload, in real live e.g. a crypto currency transaction
		final byte[] payload = new byte[256];
		Arrays.fill(payload, (byte)'A');

		final String derivationPath = "0/1/2";

		final PrivateKey derivedKey = new PrimusSpecs.CkdDerivedPrivateKey(signKey, derivationPath);
		// derived key name is needed in the approvals
		final String derivedKeyName = PrimusKeyHelper.getPrimusKeyName(derivedKey);

		// invoke authorized signing operation:
		PrimusAuthorization.setAuthorization(derivedKeyName, new PrimusAuthorization());
		final Signature signature = Signature.getInstance("SHA256withECDSA", PrimusProvider.getProviderName());
		signature.initSign(derivedKey);
		signature.update(payload);
		final byte[] sig = signature.sign();

		// for verifying locally generate/export public key only
		final KeyPair keyPair2 = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			new PrimusSpecs.CkdGenParameterSpec(keyPair.getPrivate(), true, derivationPath)
		);
		final PublicKey derivedKeyPublic = keyPair2.getPublic();

		// verify locally
		final Signature localVerifyer = Signature.getInstance("SHA256withECDSA");
		localVerifyer.initVerify(derivedKeyPublic);
		localVerifyer.update(payload);
		assert localVerifyer.verify(sig);

	}

}

