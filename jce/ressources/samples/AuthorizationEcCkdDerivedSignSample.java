
/*
 * Copyright (C) 2019,2021, Securosys SA
 */

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessGroup;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusApprovalToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusAuthorizationToken;
import com.securosys.primus.jce.PrimusCryptoCurrencies;
import com.securosys.primus.jce.PrimusDevice;
import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusKeyHelper;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSignature;
import com.securosys.primus.jce.PrimusSpecs;

/**
 * Sample code using PrimusAccess/PrimusAuthorization
 * with the Securosys Primus HSM JCE provider.
 *
 * Sign operation works with a base key and a derivation parameter.
 */
public class AuthorizationEcCkdDerivedSignSample {

	public static void main(final String... args) throws Exception {

		boolean ska = true;
		boolean hard = false;

		// HSM configuration
		PrimusHelper.setup(args);

		// create integrity key, which is later used by the HSM to sign timestamps and retrieved attributes
		final String integrityKeyName = "integrityKeyName-" + UUID.randomUUID().toString();
		final KeyPair integrityKeyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			// new ECGenParameterSpec("secp256k1"), // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
			new ECGenParameterSpec("secp256r1"),
			integrityKeyName,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_INTEGRITY, true),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false),
			new PrimusKeyAttributes.AccessAttribute(PrimusKeyAttributes.ACCESS_SENSITIVE, true)
		);

		// public part of integrity key can later be used by the approval parties to check timestamps
		PublicKey integrityKeyPublic = integrityKeyPair.getPublic();
		// mimic integrity public key coming as byte array
		integrityKeyPublic = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(integrityKeyPublic.getEncoded()));

		// create local authorization keys; each approval party would generate/have one
		final int n = 4;
		final KeyPair[] authorizationKeys = new KeyPair[n];
		final PublicKey[] authorizationPublicKeys = new PublicKey[authorizationKeys.length];
		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		for (int i = 0; i < authorizationKeys.length; i++) {
			authorizationKeys[i] = localKeyPairGenerator.generateKeyPair();
			authorizationPublicKeys[i] = authorizationKeys[i].getPublic();
			// mimic public keys coming as byte array
			authorizationPublicKeys[i] = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(authorizationPublicKeys[i].getEncoded()));
		}

		// key-pair creation with access and flags set:
		final int quorum = n / 2; // number of required signatures must not be greater than n
		final PrimusAccessGroup accessGroup = new PrimusAccessGroup(authorizationPublicKeys, quorum);
		// note: time delays/limits require a timestamp
		final int authorizationDelay = 0;
		final int authorizationTimelimit = 5;
		final PrimusAccessToken accessToken = new PrimusAccessToken(authorizationDelay, TimeUnit.MINUTES, authorizationTimelimit, TimeUnit.MINUTES, accessGroup, accessGroup);
		final PrimusAccessBlob primusAccessBlob = new PrimusAccessBlob(accessToken);
		final PrimusAccess primusAccess = new PrimusAccess(primusAccessBlob, primusAccessBlob, primusAccessBlob, primusAccessBlob);
		// create signing key, which needs approvals (specified in primusAccess) for signing, have it with sign and derivation capability
		final String signKeyName = UUID.randomUUID().toString();
		final KeyPair keyPair = (KeyPair)PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			new PrimusSpecs.CkdEnabled(new ECGenParameterSpec("secp256k1")),
			signKeyName,
			(ska ? primusAccess : null),
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

		final String derivationPath = (hard ? "0'/1'/2'" : "0/1/2");

		final byte[] cryptocurrencyDerivedAddressBase = PrimusCryptoCurrencies.getCryptoCurrencyAddress(signKey, derivationPath, PrimusCryptoCurrencies.BITCOIN);
		final String cryptocurrencyDerivedAddress = PrimusEncoding.base58CheckEncode(cryptocurrencyDerivedAddressBase);
		System.out.println("derived key address: " + cryptocurrencyDerivedAddress);

		final PrivateKey derivedKey = new PrimusSpecs.CkdDerivedPrivateKey(signKey, derivationPath);
		// derived key name is needed in the approvals
		// requires JCE provider 1.8.4 or later
		final String derivedKeyName = PrimusKeyHelper.getPrimusKeyName(derivedKey);
		// [pre-1.8.4 version]
		// final String derivedKeyName = signKeyName + "/" + derivationPath;

		// get signed timestamp, to be included with the transaction payload
		PrimusSignature.setWantSignatures(true);
		PrimusSignature.setSignatureKey(integrityKeyName);
		final byte[] timestamp = PrimusDevice.getHsmTimestamp(payload, integrityKeyName, "SHA256withECDSA");
		final PrimusSignature timestampSignature = PrimusSignature.getSignature();

		// verify timestamp locally
		Signature localVerifyer = Signature.getInstance("SHA256withECDSA");
		localVerifyer.initVerify(integrityKeyPublic);
		localVerifyer.update(timestamp);
		final boolean timestampSignatureOk = localVerifyer.verify(timestampSignature.getEncoding());
		System.out.println("timestamp signature is " + (timestampSignatureOk ? "OK" : "NOT OK"));

		// wait for authorizationDelay to pass
		@SuppressWarnings("unused") // [ide/compiler detects authorizationDelay variable as a compile-time constant]
		final long sleep = TimeUnit.MINUTES.toMillis(authorizationDelay) + (authorizationDelay > 0 ? TimeUnit.SECONDS.toMillis(10) : 0);
		if (sleep > 0) {
			System.out.println("waiting for authorization delay to pass, " + TimeUnit.MILLISECONDS.toSeconds(sleep) + " seconds");
			Thread.sleep(sleep);
		}

		// invoke authorized signing operation:
		// operations will throw PrimusAuthorizationExceptions if authorization is insufficient/expired/etc

		// build a test authorization directly in this code
		// PrimusApprovalToken is build-by-Java only in test code
		// PrimusApprovalToken must reference the derived key name, not the base key name
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload, derivedKeyName, timestamp, timestampSignature)).getEncoding();
		final PrimusAuthorizationToken[] tokens = new PrimusAuthorizationToken[quorum];
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", authorizationKeys[i].getPrivate(), authorizationKeys[i].getPublic());
			// mimic authorization tokens coming as byte arrays
			tokens[i] = new PrimusAuthorizationToken(tokens[i].getEncoding());
		}

		// verify approvals locally
		localVerifyer = Signature.getInstance("SHA256withECDSA");
		for (int i = 0; i < tokens.length; i++) {
			localVerifyer.initVerify(tokens[i].getPublicKey());
			localVerifyer.update(tokens[i].getApprovalTokenBytes());
			final boolean signSignatureOk = localVerifyer.verify(tokens[i].getVerifySignatureBytes());
			System.out.println("sign signature #" + i + " is " + (signSignatureOk ? "OK" : "NOT OK"));
		}

		final PrimusAuthorization primusAuthorization = new PrimusAuthorization(tokens);
		// PrimusAuthorization must be set on the the base key, not the derived key
		PrimusAuthorization.setAuthorization(signKey, (ska ? primusAuthorization : null));
		// [could be the derivedKeyName instead, since JCE provider 1.8.4]
		// PrimusAuthorization.setAuthorization(derivedKeyName, primusAuthorization);

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
		localVerifyer = Signature.getInstance("SHA256withECDSA");
		localVerifyer.initVerify(derivedKeyPublic);
		localVerifyer.update(payload);
		final boolean signSignatureOk = localVerifyer.verify(sig);
		System.out.println("sign signature is " + (signSignatureOk ? "OK" : "NOT OK"));

	}

}

