
/*
 * Copyright (C) 2021, Securosys SA
 */

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusApprovalToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusAuthorizationToken;
import com.securosys.primus.jce.PrimusKeyHelper;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code using PrimusAccess/PrimusAuthorization
 * with the Securosys Primus HSM JCE provider
 * and imported keys.
 */
public class ApprovalImportSample {

	public static void main(final String... args) throws Exception {

		// locally create a EC key, to be imported, as SKA key
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
		assert keyPairGenerator != null;
		keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		assert keyPair != null;
		final PrivateKey key = keyPair.getPrivate();
		assert key != null;
		assert key.getEncoded() != null;

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		final PrimusAccess primusAccess = PrimusSkaHelper.getSampleAccessNoTimeLimit();

		assert key != null;
		assert !PrimusKeyHelper.isPrimusKey(key);

		// import as SKA key
		final PrivateKey keyImported;
		final String keyName = UUID.randomUUID().toString();
		PrimusName.presetName(keyName);
		PrimusAccess.setAccess(primusAccess);
		try {
			keyImported = (PrivateKey)KeyFactory.getInstance("EC", primusProvider).translateKey(key);
		} finally {
			PrimusAccess.setAccess(null);
		}
		assert keyImported != null;
		assert PrimusKeyHelper.isPrimusKey(keyImported);

		final int payloadsize = 250;
		final byte[] payload = new byte[payloadsize];
		Arrays.fill(payload, (byte)'A');

		// authorization
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload)).getEncoding();
		final PrimusAuthorization authorization = new PrimusAuthorization();
		authorization.add(new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", PrimusSkaHelper.andrea.getPrivate(), PrimusSkaHelper.andrea.getPublic()));
		authorization.add(new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", PrimusSkaHelper.beryl.getPrivate(), PrimusSkaHelper.beryl.getPublic()));
		PrimusAuthorization.setAuthorization(keyImported, authorization);

		// sign check
		final Signature signature = Signature.getInstance("SHA256withECDSA", primusProvider);
		signature.initSign(keyImported);
		signature.update(payload);
		signature.sign();

	}

}

