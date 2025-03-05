
/*
 * Copyright (C) 2017-2023, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessGroup;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusApprovalToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusAuthorizationToken;
import com.securosys.primus.jce.PrimusExternal;
import com.securosys.primus.jce.PrimusKey;
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
 * 3) assembles an access
 * 4) creates a sign key that needs approvals
 * 5) attempts to use the key with insufficient quorum
 * 6) modifies access
 * 7) tests modification
 */
public class ApprovalModifySampleExternal {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final ECGenParameterSpec curve = new ECGenParameterSpec("secp256k1");
		final String signAlgorithm = "SHA256withECDSA";

		// 2 - GENERATE APPROVAL KEYS AND GROUPS
		// Note: this would normally be done elsewhere and only public keys would be imported.

		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(curve);

		final KeyPair andrea = localKeyPairGenerator.generateKeyPair();
		final KeyPair beryl = localKeyPairGenerator.generateKeyPair();
		final KeyPair chantal = localKeyPairGenerator.generateKeyPair();
		final KeyPair debby = localKeyPairGenerator.generateKeyPair();
		final PublicKey[] operationPublicKeys = new PublicKey[] {andrea.getPublic(), beryl.getPublic(), chantal.getPublic(), debby.getPublic(),};

		final KeyPair erin = localKeyPairGenerator.generateKeyPair();
		final KeyPair florence = localKeyPairGenerator.generateKeyPair();
		final KeyPair gabrielle = localKeyPairGenerator.generateKeyPair();
		final PublicKey[] riskOfficersPublicKeys = new PublicKey[] {erin.getPublic(), florence.getPublic(), gabrielle.getPublic(),};

		final KeyPair helene = localKeyPairGenerator.generateKeyPair();
		final PublicKey[] superBossPublicKey = new PublicKey[] {helene.getPublic(),};

		// 3 - ASSEMBLE ACCESS RULES USING GROUPS OF APPROVAL KEYS

		// operation, block, and unblock rules defined as previously
		final PrimusAccessGroup operationGroup = new PrimusAccessGroup(operationPublicKeys, operationPublicKeys.length / 2);
		final PrimusAccessBlob operationBlob = new PrimusAccessBlob(new PrimusAccessToken(operationGroup));
		final PrimusAccessGroup blockGroup = new PrimusAccessGroup(riskOfficersPublicKeys, 1);
		final PrimusAccessBlob blockBlob = new PrimusAccessBlob(new PrimusAccessToken(blockGroup));
		final PrimusAccessGroup unblockGroup = new PrimusAccessGroup(riskOfficersPublicKeys, riskOfficersPublicKeys.length);
		final PrimusAccessBlob unblockBlob = new PrimusAccessBlob(new PrimusAccessToken(unblockGroup));

		// only one key is allowed to modify these rules
		final PrimusAccessGroup modifyGroup = new PrimusAccessGroup(superBossPublicKey, 1);
		final PrimusAccessBlob modifyBlob = new PrimusAccessBlob(new PrimusAccessToken(modifyGroup));

		// add the rules to the access definition
		final PrimusAccess access = new PrimusAccess(operationBlob, blockBlob, unblockBlob, modifyBlob);

		// 4 - CREATE A KEY WITH RULES USING APPROVAL PUBLIC KEY GROUPS

		// create a signature (asset) key using the rules in the access definition
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTERNAL_OBJECT, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_TOKEN, false);

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
		PrivateKey signKey = signKeyPair.getPrivate();

		// get external key data
		// this data would be written to disk or database etc, and read back when needed
		final String signKeyUuid = ((PrimusKey)signKey).getUuid();
		System.out.println(signKeyUuid);
		final byte[] keyDataBeforeModify = PrimusExternal.toData(signKey);
		assert keyDataBeforeModify != null && keyDataBeforeModify.length > 0;
		signKey = null;

		// reconstructed private key from data
		signKey = (PrivateKey) PrimusExternal.fromData(keyDataBeforeModify, PrivateKey.class, signKeyPassword);
		assert signKey != null;
		assert signKey.getEncoded() == null;

		// 4 - PREPARE PAYLOAD

		// this would normally be a crypto transaction - here its just random data
		final byte[] payload = new byte[256];
		final SecureRandom localSecureRandom = new SecureRandom();
		localSecureRandom.nextBytes(payload);

		// 5 - ATTEMPT TO SIGN WITH INSUFFICIENT QUORUM

		final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());

		// create an approval locally - this would normally be done at approval terminals using their local private keys
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyUuid)).getEncoding();
		final PrimusAuthorizationToken token = new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, debby.getPrivate(), debby.getPublic());
		final PrimusAuthorization primusAuthorization1 = new PrimusAuthorization(token);
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization1);
		try {
			signature.initSign(signKey);
			signature.sign();
			System.out.println("UNEXPECTED");
		} catch (PrimusProviderException e) {
			System.out.println("signature failed as expected: " + e);
		}

		// 6 - MODIFY ACCESS

		// generate a changed access with the lower quorum
		final PrimusAccessBlob superblob = new PrimusAccessBlob(new PrimusAccessToken(new PrimusAccessGroup(superBossPublicKey, 1)));
		final PrimusAccessBlob subsuperblob = new PrimusAccessBlob(new PrimusAccessToken(new PrimusAccessGroup(new PublicKey[] {helene.getPublic(), debby.getPublic(),}, 1)));
		final PrimusAccess superAccess = new PrimusAccess(subsuperblob, superblob, superblob, superblob);
		final byte[] payload2 = PrimusAccess.getModifyAccessPayload(signKey, superAccess);

		// approval for modify operation
		final byte[] tokenChallenge2 = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_MODIFY, payload2, signKeyUuid)).getEncoding();
		final PrimusAuthorization authorization2 = new PrimusAuthorization(new PrimusAuthorizationToken(tokenChallenge2, signAlgorithm, helene.getPrivate(), helene.getPublic()));
		PrimusAuthorization.setAuthorization(signKey, authorization2);
		// modify
		PrimusAccess.modifyAccess(signKey, superAccess);
		System.out.println("modification successful");

		// get external key data of the modified key
		// this data would be updated with the blob of the previously stored key
		final byte[] keyDataAfterModify = PrimusExternal.toData(signKey);
		assert keyDataAfterModify != null && keyDataAfterModify.length > 0;

		signKey = null;

		//System.out.println("new blob with modified key retrieved. Comparing blobs now: " + keyDataBeforeModify + ", " + keyDataAfterModify);
		//assert keyDataBeforeModify != keyDataAfterModify;
		assert !Arrays.equals(keyDataBeforeModify, keyDataAfterModify);

		// reconstructed private key from data
		signKey = (PrivateKey) PrimusExternal.fromData(keyDataAfterModify, PrivateKey.class, signKeyPassword);
		assert signKey != null;
		assert signKey.getEncoded() == null;

		// 7 - REPEAT ATTEMPT TO SIGN

		// this time the original signature attempt should go through
		final byte[] tokenChallenge3 = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, signKeyUuid)).getEncoding();
		final PrimusAuthorizationToken token2 = new PrimusAuthorizationToken(tokenChallenge3, signAlgorithm, debby.getPrivate(), debby.getPublic());
		final PrimusAuthorization primusAuthorization2 = new PrimusAuthorization(token2);
		PrimusAuthorization.setAuthorization(signKey, primusAuthorization2);
		signature.initSign(signKey);
		signature.sign();
		System.out.println("signature successful");

	}

}

