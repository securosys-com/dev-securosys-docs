
/*
 * Copyright (C) 2017-2019, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusApprovalToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusAuthorizationToken;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusProviderException;

/**
 * Sample code using PrimusAccess/PrimusAuthorization
 * with the Securosys Primus HSM JCE provider.
 *
 * The sample:
 *
 * 1) logs into the HSM
 * 2) creates local approval keys
 * 3) creates a sign key that needs approvals
 * 4) gets payload
 * 5) creates approvals
 * 6) executes signature operation
 * 7) blocks the sign key and attempts to run the signature operation leading to an error
 * 8) unblocks the key and successfully runs the operation again
 */
public class ApprovalBlockSample {

	public static void main(final String... args) throws Exception {

		// 1 - HSM AND PROVIDER CONFIGURATION

		// HSM configuration
		PrimusHelper.setup(args);

		// final ECGenParameterSpec curve = new ECGenParameterSpec("secp256k1"); // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
		final ECGenParameterSpec curve = new ECGenParameterSpec("secp256r1");
		final String signAlgorithm = "SHA256withECDSA";

		// 2 - GENERATE APPROVAL KEYS AND GROUPS
		// Note: this would normally be done elsewhere and only public keys would be imported.

		// 3 - CREATE A KEY WITH RULES USING APPROVAL PUBLIC KEY GROUPS

		final PrimusAccess access = PrimusSkaHelper.getSampleAccessNoTimeLimit();

		// create a signature (asset) key using the rules in the access definition
		final String signKeyName = UUID.randomUUID().toString();
		final char[] signKeyPassword = UUID.randomUUID().toString().toCharArray();
		final KeyPair signKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			curve,
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

		// 4 - PREPARE PAYLOAD

		// this would normally be a crypto transaction - here its just random data
		final byte[] payload = new byte[256];
		final SecureRandom localSecureRandom = new SecureRandom();
		localSecureRandom.nextBytes(payload);

		// 5 - CREATE APPROVALS LOCALLY - this would normally be done at approval terminals using their local private keys

		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyName/*, timestamp, timestampSignature*/)).getEncoding();
		PrimusAuthorizationToken token0 = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, PrimusSkaHelper.beryl.getPrivate(), PrimusSkaHelper.beryl.getPublic());
		PrimusAuthorizationToken token1 = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, PrimusSkaHelper.chantal.getPrivate(), PrimusSkaHelper.chantal.getPublic());

		// 6 - REQUEST SIGNATURE OPERATION

		// the signature key is unblocked at this point, so as long as we meet the quorum (1/2 in this case),
		// the operation should go through
		token0 = new PrimusAuthorizationToken(token0.getEncoding());
		token1 = new PrimusAuthorizationToken(token1.getEncoding());
		final PrimusAuthorization primusAuthorization = new PrimusAuthorization();
		primusAuthorization.add(token0);
		primusAuthorization.add(token1);
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);
		final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initSign(signKey);
		signature.update(payload);
		signature.sign();
		System.out.println("Signature successful");

		// 7 - BLOCK THE KEY

		// first confirm the key is unblocked
		System.out.println("sign key is blocked: " + PrimusKeyAttributes.getKeyAccessFlag(signKey, PrimusKeyAttributes.ACCESS_BLOCKED));

		// there's no need for any payload to be signed
		final byte[] nopayload = null;

		// sign a token for blocking operation using only one key from the respective group.
		// This would normally be done on a remote terminal. Doing it here locally for the sample purposes
		PrimusAuthorization.setAuthorization(signKey, new PrimusAuthorization(
			new PrimusAuthorizationToken((
				new PrimusApprovalToken(
					PrimusApprovalToken.OPERATION_BLOCK, nopayload, signKeyName/*, timestamp, timestampSignature*/)).getEncoding(),
					signAlgorithm, PrimusSkaHelper.florence.getPrivate(), PrimusSkaHelper.florence.getPublic()
				)
		));
		PrimusKeyAttributes.setKeyAccessFlag(signKey, PrimusKeyAttributes.ACCESS_BLOCKED, true);
		System.out.println("sign key is blocked: " + PrimusKeyAttributes.getKeyAccessFlag(signKey, PrimusKeyAttributes.ACCESS_BLOCKED));

		// reusing the same authorization as in the section above, but this time it should fail
		System.out.println("trying to sign now ...");
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);
		signature.initSign(signKey);

		try {
			signature.sign();
			System.out.println("UNEXPECTED");
		} catch (PrimusProviderException e) {
			System.out.println("signature failed as expected: " + e);
		}

		// 8 - UNBLOCK THE KEY

		System.out.println("Unblocking the key");
		// All three Risk Officers are required to unblock the key.
		// Thus provide three approval tokens with their approval public keys (You can test it with only two to see the error)
		PrimusAuthorization.setAuthorization(signKey, new PrimusAuthorization(
			new PrimusAuthorizationToken((new PrimusApprovalToken(PrimusApprovalToken.OPERATION_UNBLOCK, nopayload, signKeyName/*, timestamp, timestampSignature*/)).getEncoding(), signAlgorithm, PrimusSkaHelper.florence.getPrivate(), PrimusSkaHelper.florence.getPublic()),
			new PrimusAuthorizationToken((new PrimusApprovalToken(PrimusApprovalToken.OPERATION_UNBLOCK, nopayload, signKeyName/*, timestamp, timestampSignature*/)).getEncoding(), signAlgorithm, PrimusSkaHelper.erin.getPrivate(), PrimusSkaHelper.erin.getPublic()),
			new PrimusAuthorizationToken((new PrimusApprovalToken(PrimusApprovalToken.OPERATION_UNBLOCK, nopayload, signKeyName/*, timestamp, timestampSignature*/)).getEncoding(), signAlgorithm, PrimusSkaHelper.gabrielle.getPrivate(), PrimusSkaHelper.gabrielle.getPublic())
		));
		PrimusKeyAttributes.setKeyAccessFlag(signKey, PrimusKeyAttributes.ACCESS_BLOCKED, false);

		System.out.println("sign key is blocked: " + PrimusKeyAttributes.getKeyAccessFlag(signKey, PrimusKeyAttributes.ACCESS_BLOCKED));

		// retry the operation authorization to confirm the key has been unblocked
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization);
		signature.initSign(signKey);
		signature.sign();

		System.out.println("Signature successful");
	}

}
