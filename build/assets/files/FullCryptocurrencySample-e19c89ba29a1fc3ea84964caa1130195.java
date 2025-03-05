
/*
 * Copyright (C) 2019-2022, Securosys SA
 */

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessGroup;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusApprovalToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusAuthorizationToken;
import com.securosys.primus.jce.PrimusCryptoCurrencies;
import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSignature;
import com.securosys.primus.jce.PrimusSpecs;

/**
 * This code shows all main cryptocurrency-related capabilities in one sample.
 *
 * It goes throught he following steps:
 * 1) HSM Login
 * 2) Create local approval keys
 * 3) Create an SKA-enabled BIP32-derivable Bitcoin key
 * 4) Derive a key from the master key and export its address
 * 5) Request and authorize a signature
 * 6) Export public key
 */
public class FullCryptocurrencySample {

	public static void main(final String... args) throws Exception {

		// 1 -- LOGIN
		// HSM configuration

		PrimusHelper.setup(args);

		// 2 -- CREATE APPROVAL KEYS LOCALLY

		// This would normally be done elsewhere. The approval keys represent approval clients, e.g. mobile phone,
		// Yubikey, or a smart card. The private keys would be securely stored with these clients and only public keys
		// provided to the Smart Key Attributes.
		// In this case to make this sample work standalone, we create approval keys locally.

		final int numberOfApprovalKeys = 3;
		final KeyPair[] approvalKeys = new KeyPair[numberOfApprovalKeys];
		final PublicKey[] approvalPublicKeys = new PublicKey[numberOfApprovalKeys];
		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		for (int i = 0; i < approvalKeys.length; i++) {
			approvalKeys[i] = localKeyPairGenerator.generateKeyPair();
			approvalPublicKeys[i] = approvalKeys[i].getPublic();
			// mimic public keys coming as byte array
			approvalPublicKeys[i] = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(approvalPublicKeys[i].getEncoded()));
			// give them a name
			approvalPublicKeys[i] = PrimusAccessGroup.getNamedPublicKey("pub#" + i, approvalPublicKeys[i]);
		}

		// 3 - CREATE AN SKA-ENABLED BIP32 MASTER KEY

		// First set attributes of the key

		// prevent extracting public key before the first signature is done (quantum protection for crypto-assets)
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_NO_PUBLIC_KEY, true);

		// make the key non-extractable. The sensitive attribute is in this case redundant
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, true);

		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_MODIFIABLE, false);

		// allow derivation from this key
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_DERIVE, true);

		// attributes are inherited by derived keys, so make sure to allow signing
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_SIGN, true);

		// crypto currency must be specified when ACCESS_NO_PUBLIC_KEY attribute is set to true
		PrimusCryptoCurrencies.setCryptoCurrency(PrimusCryptoCurrencies.BITCOIN);

		// define a simple ska policy with a quorum of m/n where m=n-1. SKA uses groups and tokens to define
		// policies. For details see diagram in Javadoc's homepage
		final int quorum = numberOfApprovalKeys - 1;
		final PrimusAccessGroup accessGroup = new PrimusAccessGroup("group#0", approvalPublicKeys, quorum);
		final PrimusAccessToken accessToken = new PrimusAccessToken("token#0", accessGroup);
		final PrimusAccessBlob primusAccessBlob = new PrimusAccessBlob(accessToken);
		final PrimusAccess primusAccess = new PrimusAccess(primusAccessBlob, primusAccessBlob, primusAccessBlob, primusAccessBlob);

		// make it an EC secp256k1 key [pair], for derivation capability
		final KeyPairGenerator primusKeyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdEnabled(new ECGenParameterSpec("secp256k1")));

		final String keyName = UUID.randomUUID().toString();

		PrimusName.generate(
			// using typical Bitcoin EC algorithm here
			primusKeyPairGenerator,
			//new ECGenParameterSpec("secp256k1"),
			keyName,
			primusAccess
		);

		System.out.println("Master key " + keyName + " generated with " + quorum + "/" + numberOfApprovalKeys + " quorum requirement");

		// 4 - DERIVE A CHILD KEY AND EXPORT ITS ADDRESS

		String derivationPath = "m/44'/0'/0'/0";

		// initiate key store
		final KeyStore primusKeyStore = KeyStore.getInstance("Primus", "SecurosysPrimusXSeries");
		primusKeyStore.load(null);

		final PrivateKey masterKey = (PrivateKey)primusKeyStore.getKey(keyName, null);

		// get cryptocurrency address base - i.e. without a network prefix and a checksum
		final byte[] cryptocurrencyAddressBase = PrimusSpecs.CryptoCurrencyHelper.getCryptoCurrencyAddress(masterKey, derivationPath);

		// convert to final format of Bitcoin mainnet addresses (to change to other network,
		// provide relevant version byte as a second parameter
		final String cryptocurrencyAddress = PrimusEncoding.base58CheckEncode(cryptocurrencyAddressBase);

		System.out.println("Address of the key derived with path " + derivationPath + ": " + cryptocurrencyAddress);

		// 5 - AUTHORIZE A SIGNATURE

		// Generate a transaction payload, in real live e.g. a crypto currency transaction
		final int payloadsize = 250; // to test below code: use something %4!=0
		final byte[] payload = new byte[payloadsize];
		Arrays.fill(payload, (byte)'A');

		// create a token challenge that needs to be signed by the approval clients
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, payload)).getEncoding();

		final PrimusAuthorization authorization = new PrimusAuthorization();

		// sign the token challenges by the required quorum - this would normally be done by the approval terminals who
		// would only return the approval tokens back to the business application, which would submit them to the HSM
		for (int i = 0; i < quorum; i++) {
			PrimusAuthorizationToken token = new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", approvalKeys[i].getPrivate(), approvalKeys[i].getPublic());
			// mimic authorization tokens coming as byte arrays
			token = new PrimusAuthorizationToken(token.getEncoding());

			authorization.add(token);
		}

		// derive a child key from the master key
		primusKeyPairGenerator.initialize(new PrimusSpecs.CkdGenParameterSpec(masterKey, derivationPath));
		final KeyPair derivedKeyPair = primusKeyPairGenerator.generateKeyPair();

		// authorize use of the derived key, providing the authorization quorum compiled above
		PrimusAuthorization.setAuthorization(derivedKeyPair.getPrivate(), authorization);

		// Sign the payload using the derived key
		final Signature signature = Signature.getInstance("SHA256withECDSA", PrimusProvider.getProviderName());
		signature.initSign(derivedKeyPair.getPrivate());
		signature.update(payload);
		final byte[] sig = signature.sign();
		System.out.println("Payload signed with the derived key!");

		// 6 -- EXPORT PUBLIC KEY AND VERIFY SIGNATURE

		// until the first signature, the public key was non-exportable for quantum-protection.
		// After the signature, it is made available by calling PrimusSignature.getNoPubPublicKey() method.
		// We'll use it here to verify the signature

		final Signature localVerifier = Signature.getInstance("SHA256withECDSA");
		localVerifier.initVerify(PrimusSignature.getNoPubPublicKey());
		localVerifier.update(payload);
		final boolean signSignatureOk = localVerifier.verify(sig);
		System.out.println("Sign signature is " + (signSignatureOk ? "OK" : "NOT OK"));

	}

}

