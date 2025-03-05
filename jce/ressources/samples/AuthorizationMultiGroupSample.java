
/*
 * Copyright (C) 2017-2020, Securosys SA
 */

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessGroup;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusApprovalToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusAuthorizationException;
import com.securosys.primus.jce.PrimusAuthorizationToken;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code using PrimusAccess/PrimusAuthorization
 * with the Securosys Primus HSM JCE provider,
 * illustrating PrimusAccessBlob with multiple PrimusAccessTokens,
 * and PrimusAccessToken with PrimusAccessGroups.
 */
public class AuthorizationMultiGroupSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));

		final KeyPair key111 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key112 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key113 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key114 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key121 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key122 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key123 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key124 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key211 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key212 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key213 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key214 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key221 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key222 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key223 = localKeyPairGenerator.generateKeyPair();
		final KeyPair key224 = localKeyPairGenerator.generateKeyPair();
		final PublicKey[] publicKeys11 = new PublicKey[] {key111.getPublic(), key112.getPublic(), key113.getPublic(), key114.getPublic(),};
		final PublicKey[] publicKeys12 = new PublicKey[] {key121.getPublic(), key122.getPublic(), key123.getPublic(), key124.getPublic(),};
		final PublicKey[] publicKeys21 = new PublicKey[] {key211.getPublic(), key212.getPublic(), key213.getPublic(), key214.getPublic(),};
		final PublicKey[] publicKeys22 = new PublicKey[] {key221.getPublic(), key222.getPublic(), key223.getPublic(), key224.getPublic(),};

		final PrimusAccessGroup accessGroup11 = new PrimusAccessGroup(publicKeys11, 2); // 2 of the PublicKeys must be met for the approval
		final PrimusAccessGroup accessGroup12 = new PrimusAccessGroup(publicKeys12, 2);
		final PrimusAccessGroup accessGroup21 = new PrimusAccessGroup(publicKeys21, 2);
		final PrimusAccessGroup accessGroup22 = new PrimusAccessGroup(publicKeys22, 2);
		final PrimusAccessToken accessToken1 = new PrimusAccessToken(accessGroup11, accessGroup12); // all of the PrimusAccessGroups must be met for the approval
		final PrimusAccessToken accessToken2 = new PrimusAccessToken(accessGroup21, accessGroup22);
		final PrimusAccessBlob accessBlob = new PrimusAccessBlob(accessToken1, accessToken2); // one of the PrimusAccessTokens must be met for the approval
		final PrimusAccess access = new PrimusAccess(accessBlob, accessBlob, accessBlob, accessBlob);

		final String signKeyName = UUID.randomUUID().toString();
		final char[] signKeyPassword = UUID.randomUUID().toString().toCharArray();
		final KeyPair signKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			new ECGenParameterSpec("secp256r1"),
			signKeyName,
			signKeyPassword,
			access,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_SIGN, true),
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_DECRYPT, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_MODIFIABLE, false)
		);
		final PrivateKey signKey = signKeyPair.getPrivate();
		final PublicKey signKeyPublic = signKeyPair.getPublic();

		final byte[] payload = "testpayload".getBytes(StandardCharsets.UTF_8);
		final SecureRandom localSecureRandom = new SecureRandom();
		localSecureRandom.nextBytes(payload);
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload)).getEncoding();

		final String signAlgorithm = "SHA256withECDSA";

		// no authorization
		assert signVerify(payload, signKey, signKeyPublic, tokenChallenge, signAlgorithm) == null;

		// sufficient authorization
		assert signVerify(payload, signKey, signKeyPublic, tokenChallenge, signAlgorithm, key111, key112, key122, key124) != null;

		// insufficient authorizations
		assert signVerify(payload, signKey, signKeyPublic, tokenChallenge, signAlgorithm, key111, key112) == null; // (only one group)
		assert signVerify(payload, signKey, signKeyPublic, tokenChallenge, signAlgorithm, key111, key112, key113, key114) == null; // (4 keys, but still only one group)
		assert signVerify(payload, signKey, signKeyPublic, tokenChallenge, signAlgorithm, key111, key112, key113, key124) == null;

		// like above, but with second PrimusAccessToken having sufficient authorization
		assert signVerify(payload, signKey, signKeyPublic, tokenChallenge, signAlgorithm, key111, key112, key113, key114, key211, key212, key222, key224) != null;
	}

	private static PrimusAuthorization buildAuthorization(final byte[] tokenChallenge, final String signAlgorithm, final KeyPair... keys) {
		final PrimusAuthorization authorization = new PrimusAuthorization();
		for (final KeyPair key : keys) {
			authorization.add(new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, key.getPrivate(), key.getPublic()));
		}
		return authorization;
	}

	private static byte[] signVerify(final byte[] payload, final PrivateKey signKey, final PublicKey signKeyPublic, final byte[] tokenChallenge, final String signAlgorithm, final KeyPair... keys) throws Exception {
		final byte[] sig = sign(payload, signKey, tokenChallenge, signAlgorithm, keys);
		verify(sig, payload, signKeyPublic, signAlgorithm);
		return sig;
	}

	private static byte[] sign(final byte[] payload, final PrivateKey signKey, final byte[] tokenChallenge, final String signAlgorithm, final KeyPair... keys) throws Exception {
		if (payload == null || signKey == null || tokenChallenge == null || signAlgorithm == null || keys == null) {
			return null;
		}
		try {
			PrimusAuthorization.setAuthorization(signKey, buildAuthorization(tokenChallenge, signAlgorithm, keys));
			final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
			signature.initSign(signKey);
			signature.update(payload);
			return signature.sign();
		} catch (PrimusAuthorizationException e) {
			return null;
		}
	}

	private static void verify(final byte[] sig, final byte[] payload, final PublicKey signKeyPublic, final String signAlgorithm) throws Exception {
		if (sig == null || payload == null || signKeyPublic == null || signAlgorithm == null) {
			return;
		}
		final Signature localVerifyer = Signature.getInstance(signAlgorithm);
		localVerifyer.initVerify(signKeyPublic);
		localVerifyer.update(payload);
		if (!localVerifyer.verify(sig)) {
			throw new RuntimeException("verify failed");
		}
	}

}

