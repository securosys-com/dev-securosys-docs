
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessGroup;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code using an old approach to get attestations.
 *
 * See AttestationSample.java for the new approach (which requires a setup rook key store).
 */
public class PreAttestationSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		// 2 -- CREATE AN INTEGRITY KEY

		final ECGenParameterSpec curve = new ECGenParameterSpec("secp256k1");
		final ECGenParameterSpec curve2 = new ECGenParameterSpec("secp256r1");
		final String signAlgorithm = "SHA256withECDSA";

		// create an integrity key, used by HSM to sign attribute retrievals and time stamps
		// This is something typically done only once or ocasionally
		// and when needed the key is retrieved by its name (as shown below)
		final String integrityKeyName = "integrityKeyName-" + UUID.randomUUID().toString(); // (unique name to avoid test environment clashes)
		final char[] integrityKeyPassword = null; // have no key password with this key; alternatively UUID.randomUUID().toString().toCharArray();
		// typically one wants to directly name generated keys (instead of later use setKeyEntry)
		PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			curve2,
			integrityKeyName,
			integrityKeyPassword,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_INTEGRITY, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true)
		);

		// 3 -- DEFINE APPROVAL RULES INCLUDING TIMELOCK AND TIMEOUT

		// Timelock - minimum time between asking for a transaction request (payload) timestamp
		// and submitting an approval
		final long operationTimelock = TimeUnit.MINUTES.toMillis(1);

		// Timeout - maximum time between asking for payload timestamp and submitting the approval
		final long operationTimeout = TimeUnit.MINUTES.toMillis(10);

		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(curve2);
		final KeyPair andrea = localKeyPairGenerator.generateKeyPair();
		final KeyPair beryl = localKeyPairGenerator.generateKeyPair();

		// rules definition similar to previous examples with one major difference - adding timelock
		// and timeout to the access token
		final PrimusAccessBlob operationBlob = new PrimusAccessBlob(new PrimusAccessToken(
				operationTimelock, operationTimeout, new PrimusAccessGroup(new PublicKey[] {andrea.getPublic(), beryl.getPublic(),}, 1)));

		final PrimusAccessBlob blockBlob = new PrimusAccessBlob(new PrimusAccessToken(new PrimusAccessGroup(new PublicKey[] {localKeyPairGenerator.generateKeyPair().getPublic(),}, 1)));
		final PrimusAccessBlob unblockBlob = new PrimusAccessBlob(new PrimusAccessToken(new PrimusAccessGroup(new PublicKey[] {localKeyPairGenerator.generateKeyPair().getPublic(),}, 1)));
		final PrimusAccessBlob modifyBlob = new PrimusAccessBlob(new PrimusAccessToken(new PrimusAccessGroup(new PublicKey[] {localKeyPairGenerator.generateKeyPair().getPublic(),}, 1)));
		final PrimusAccess access = new PrimusAccess(operationBlob, blockBlob, unblockBlob, modifyBlob);

		// 4 -- GENERATE A SIGNATURE KEY USING THESE RULES

		final String signKeyName = UUID.randomUUID().toString();
		final char[] signKeyPassword = UUID.randomUUID().toString().toCharArray();
		PrimusName.generate(
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

		// 5 -- RETRIEVE ACCESS DATA FROM ATTRIBUTES

		System.out.println("attestation data: " + PrimusEncoding.base64Encode(PrimusKeyAttributes.getAllKeyAttributes(signKeyName, signKeyPassword, integrityKeyName, integrityKeyPassword, signAlgorithm)));

	}

}
