
/*
 * Copyright (C) 2016-2019, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.UUID;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusProviderException;

/**
 * Sample code manipulating key attributes
 * with the Securosys Primus HSM JCE provider.
 *
 * The sample goes through the following steps:
 * 1) HSM Login
 * 2) Create a key
 * 3) Display default attributes
 * 4) Sign and verify with the key
 * 5) Disable signature and verification with the key
 * 6) Attempt at signature and verification
 * 7) Re-enable the capabilities
 */
public class KeyAttributesSample {

	public static void main(final String... args) throws Exception {

		// 1 -- HSM AND PROVIDER CONFIGURATION

		// HSM configuration
		PrimusHelper.setup(args);

		// 2 -- CREATE A KEY WITH DEFAULT ATTRIBUTES

		System.out.println("creating RSA key pair of 2048 bits ...");
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", PrimusProvider.getProviderName());
		keyPairGenerator.initialize(2048);
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// 3 -- DISPLAY THE DEFAULT ATTRIBUTES

		System.out.print("default private capability flags:");
		final int cf = PrimusKeyAttributes.getKeyCapabilityFlags(keyPair.getPrivate());
		for (int f = 1; f <= cf; f <<= 1) {
			if ((cf & f) != 0) {
				System.out.print(" " + PrimusKeyAttributes.getCapabilityFlagName(f));
			}
		}
		System.out.println();

		System.out.print("default public capability flags:");
		final int cf2 = PrimusKeyAttributes.getKeyCapabilityFlags(keyPair.getPublic());
		for (int f = 1; f <= cf2; f <<= 1) {
			if ((cf2 & f) != 0) {
				System.out.print(" " + PrimusKeyAttributes.getCapabilityFlagName(f));
			}
		}
		System.out.println();

		System.out.print("default private access flags:");
		final int af = PrimusKeyAttributes.getKeyAccessFlags(keyPair.getPrivate());
		for (int f = 1; f <= af; f <<= 1) {
			if ((af & f) != 0) {
				System.out.print(" " + PrimusKeyAttributes.getAccessFlagName(f));
			}
		}
		System.out.println();

		System.out.print("default public access flags:");
		final int af2 = PrimusKeyAttributes.getKeyAccessFlags(keyPair.getPublic());
		for (int f = 1; f <= af2; f <<= 1) {
			if ((af2 & f) != 0) {
				System.out.print(" " + PrimusKeyAttributes.getAccessFlagName(f));
			}
		}
		System.out.println();

		// 4 -- USE THE KEY TO SIGN A PAYLOAD AND VERIFY THE SIGNATURE

		System.out.print("signing ...");
		final Signature signature = Signature.getInstance("SHA256withRSA", PrimusProvider.getProviderName());
		signature.initSign(keyPair.getPrivate());
		final byte[] msg = UUID.randomUUID().toString().getBytes();
		signature.update(msg);
		byte[] sig = signature.sign();
		System.out.println(" OK");

		System.out.print("verifying ...");
		signature.initVerify(keyPair.getPublic());
		signature.update(msg);
		System.out.println(signature.verify(sig));

		// 5 -- MODIFY CAPABILITY FLAGS TO DISABLE SIGNATURE AND VERIFICATION WITH THE KEY

		System.out.println("disable verify ...");
		PrimusKeyAttributes.setKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_VERIFY, false);

		signature.initSign(keyPair.getPrivate());
		signature.update(msg);
		sig = signature.sign();

		// 6 -- ATTEMPT AT SIGNATURE AND VERIFICATION

		try {
			signature.initVerify(keyPair.getPublic());
			signature.update(msg);
			signature.verify(sig);
			System.out.println("UNEXPECTED");
			throw new AssertionError();
		} catch (PrimusProviderException e) {
			System.out.println("error verifying as expected: " + e);
		}

		System.out.println("disable sign ...");
		PrimusKeyAttributes.setKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_SIGN, false);

		try {
			signature.initSign(keyPair.getPrivate());
			signature.update(msg);
			sig = signature.sign();
			System.out.println("UNEXPECTED");
			throw new AssertionError();
		} catch (PrimusProviderException e) {
			System.out.println("error signing as expected: " + e);
		}

		// 7 - RE-ENABLE THE CAPABILITIES

		System.out.println("reenable signing and verification capability...");
		PrimusKeyAttributes.setKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_VERIFY, true);
		PrimusKeyAttributes.setKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_SIGN, true);

		signature.initSign(keyPair.getPrivate());
		signature.update(msg);
		sig = signature.sign();

		signature.initVerify(keyPair.getPublic());
		signature.update(msg);
		signature.verify(sig);

		System.out.println("signature and verification successful");

	}

}

