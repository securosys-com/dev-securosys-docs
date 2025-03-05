
/*
 * Copyright (C) 2017-2021, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
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
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSignature;

public class ApprovalModifyWithTimestampSample {

	public static void main(final String... args) throws Exception {

		PrimusHelper.setup(args);

		final String integrityKeyName = UUID.randomUUID().toString();
		PrimusName.generate(
			KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName()),
			2048,
			integrityKeyName,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_INTEGRITY, true)
		);
		PrimusSignature.setWantSignatures(true);
		PrimusSignature.setSignatureKey(integrityKeyName);

		// final ECGenParameterSpec curve = new ECGenParameterSpec("secp256k1"); // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
		final ECGenParameterSpec curve = new ECGenParameterSpec("secp256r1");
		final String signAlgorithm = "SHA256withECDSA";

		final PrimusAccess access = PrimusSkaHelper.getSampleAccessWithDelay();

		final String signKeyName = UUID.randomUUID().toString();
		final KeyPair signKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			curve,
			signKeyName,
			access
		);
		final PrivateKey signKey = signKeyPair.getPrivate();

		final PrimusAccessBlob superblob = new PrimusAccessBlob(new PrimusAccessToken(1, TimeUnit.MINUTES, 1, TimeUnit.MINUTES, new PrimusAccessGroup(new PublicKey[] {PrimusSkaHelper.helene.getPublic()}, 1)));
		final PrimusAccessBlob subsuperblob = new PrimusAccessBlob(new PrimusAccessToken(1, TimeUnit.MINUTES, 1, TimeUnit.MINUTES, new PrimusAccessGroup(new PublicKey[] {PrimusSkaHelper.helene.getPublic(), PrimusSkaHelper.debby.getPublic(),}, 1)));
		final PrimusAccess superAccess = new PrimusAccess(subsuperblob, superblob, superblob, superblob);

		final byte[] payload = PrimusAccess.getModifyAccessPayload(signKey, superAccess);
		final byte[] timestamp = PrimusDevice.getHsmTimestamp(PrimusApprovalToken.OPERATION_MODIFY, payload, integrityKeyName, (char[])null, "SHA256withRSA");

		final PrimusSignature timestampSignature = PrimusSignature.getSignature();

		Thread.sleep((long)(PrimusSkaHelper.delayTimeUnit.toMillis(PrimusSkaHelper.delay) * 1.1));

		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_MODIFY, payload, signKeyName, timestamp, timestampSignature)).getEncoding();

		final PrimusAuthorization authorization = new PrimusAuthorization(new PrimusAuthorizationToken(tokenChallenge, signAlgorithm, PrimusSkaHelper.helene.getPrivate(), PrimusSkaHelper.helene.getPublic()));
		PrimusAuthorization.setAuthorization(signKey, authorization);
		PrimusAccess.modifyAccess(signKey, superAccess);

	}

}

