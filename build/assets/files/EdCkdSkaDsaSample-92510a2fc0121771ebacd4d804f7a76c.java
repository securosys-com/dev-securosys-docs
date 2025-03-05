
/*
 * Copyright (C) 2021, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessGroup;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusApprovalToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusAuthorizationToken;
import com.securosys.primus.jce.PrimusDevice;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusKeyHelper;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusPrimitives;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSignature;
import com.securosys.primus.jce.PrimusSpecs;

/**
 * Less simple Ed-CKD-DSA samples
 * using smart key attributes ('SKA')
 * for the Securosys Primus HSM JCE provider.
 */
public class EdCkdSkaDsaSample {

	public static void main(final String... args) throws Exception {

		System.setProperty("com.securosys.primus.jce.adhocPublicKeyImport", "true"); // for later Signature.initVerify with exported public key

		// for later approvals: generate 4 authorization keys
		final KeyPair[] authorizationKeys = new KeyPair[4];
		final PublicKey[] authorizationPublicKeys = new PublicKey[authorizationKeys.length];
		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		for (int i = 0; i < authorizationKeys.length; i++) {
			authorizationKeys[i] = localKeyPairGenerator.generateKeyPair();
			authorizationPublicKeys[i] = authorizationKeys[i].getPublic();
		}

		// define access to the future signature key
		final int quorum = authorizationKeys.length / 2;
		final PrimusAccessGroup accessGroup = new PrimusAccessGroup(authorizationPublicKeys, quorum);
		final int authorizationDelay = 0;
		final int authorizationTimelimit = 5;
		final PrimusAccessToken accessToken = new PrimusAccessToken(authorizationDelay, TimeUnit.MINUTES, authorizationTimelimit, TimeUnit.MINUTES, accessGroup);
		final PrimusAccessBlob primusAccessBlob = new PrimusAccessBlob(accessToken);
		final PrimusAccess access = new PrimusAccess(primusAccessBlob, primusAccessBlob, primusAccessBlob, primusAccessBlob);

		// HSM configuration
		PrimusHelper.setup(args);

		// preliminary: create an integrity key, for the timestamp signature
		final String integrity = UUID.randomUUID().toString();
		PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			// new ECGenParameterSpec("secp256k1"), // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
			new ECGenParameterSpec("secp256r1"),
			integrity,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_INTEGRITY, true)
		);

		// generate a base key ; use PrimusSpecs.CkdEnabled to allow CKD key derivals
		final String base = UUID.randomUUID().toString();
		PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			new PrimusSpecs.CkdEnabled(new ECGenParameterSpec("ed25519")),
			base,
			access,
			new Certificate[0]
		);

		// generate a derived key ; use PrimusSpecs.CkdGenParameterSpec and a derivation string
		// derived SKA key has an *implicit name*
		final String[] derived = new String[1];
		PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			new PrimusSpecs.CkdGenParameterSpec(PrimusPrimitives.getPrivateKey(base), "0/0/0"),
			derived,
			new Certificate[0]
		);

		final byte[] message = "test".getBytes();

		// get timestamp and token challenge
		PrimusSignature.setWantSignatures(true);
		PrimusSignature.setSignatureKey(integrity);
		final byte[] timestamp = PrimusDevice.getHsmTimestamp(message, integrity, "SHA256withECDSA");
		final PrimusSignature timestampSignature = PrimusSignature.getSignature();
		byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, message, derived[0], timestamp, timestampSignature)).getEncoding();

		// taylor an authorization
		PrimusAuthorization authorization = new PrimusAuthorization();
		for (int i = 0; i < quorum; i++) {
			authorization.add(new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", authorizationKeys[i].getPrivate(), authorizationKeys[i].getPublic()));
		}
		PrimusAuthorization.setAuthorization(derived[0], authorization);

		// sign with derived private key
		final Signature signature = Signature.getInstance("EdDSA", PrimusProvider.getProviderName());
		signature.initSign(PrimusPrimitives.getPrivateKey(derived[0]));
		signature.update(message);
		byte[] signatureBytes = signature.sign();

		// verify with derived public key
		signature.initVerify(PrimusPrimitives.getPublicKey(derived[0]));
		signature.update(message);
		boolean verified = signature.verify(signatureBytes);
		assert verified;

		// derived key name is needed in the approvals
		final String derived2 = PrimusKeyHelper.getPrimusKeyName(new PrimusSpecs.CkdDerivedPrivateKey(PrimusPrimitives.getPrivateKey(base), "2/2/2"));

		// next authorization
		tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, message, derived2, timestamp, timestampSignature)).getEncoding();
		authorization = new PrimusAuthorization();
		for (int i = 0; i < quorum; i++) {
			authorization.add(new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", authorizationKeys[i].getPrivate(), authorizationKeys[i].getPublic()));
		}
		PrimusAuthorization.setAuthorization(derived2, authorization);

		// derived-sign directly with base private key and derivation string
		signature.initSign(new PrimusSpecs.CkdDerivedPrivateKey(PrimusPrimitives.getPrivateKey(base), "2/2/2"));
		signature.update(message);
		signatureBytes = signature.sign();

		// derived-verify directly with base public key and derivation string
		signature.initVerify(new PrimusSpecs.CkdDerivedPublicKey(PrimusPrimitives.getPublicKey(base), "2/2/2"));
		signature.update(message);
		verified = signature.verify(signatureBytes);
		assert verified;

	}

}

