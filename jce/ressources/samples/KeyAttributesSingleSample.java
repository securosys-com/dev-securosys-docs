
/*
 * Copyright (C) 2016-2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Sample code showing key attributes
 * one by one.
 */
public class KeyAttributesSingleSample {

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

		System.out.println();
		System.out.print("default private capability flags:");
//		final int cf = PrimusKeyAttributes.getKeyCapabilityFlags(keyPair.getPrivate());
//		for (int f = 1; f <= cf; f <<= 1) {
//			if ((cf & f) != 0) {
//				System.out.print(" " + PrimusKeyAttributes.getCapabilityFlagName(f));
//			}
//		}
		System.out.println();
		System.out.println("CAPABILITY_ENCRYPT: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_ENCRYPT));
		System.out.println("CAPABILITY_DECRYPT: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_DECRYPT));
		System.out.println("CAPABILITY_SIGN: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_SIGN));
		System.out.println("CAPABILITY_VERIFY: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_VERIFY));
		System.out.println("CAPABILITY_WRAP: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_WRAP));
		System.out.println("CAPABILITY_UNWRAP: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_UNWRAP));
		System.out.println("CAPABILITY_DERIVE: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_DERIVE));
		System.out.println("CAPABILITY_INTEGRITY: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_INTEGRITY));
		System.out.println("CAPABILITY_ATTESTATION: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_ATTESTATION));
		System.out.println("CAPABILITY_TIMESTAMP: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_TIMESTAMP));
		System.out.println("CAPABILITY_VERIFY_RECOVER: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_VERIFY_RECOVER));
		System.out.println("CAPABILITY_SIGN_RECOVER: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPrivate(), PrimusKeyAttributes.CAPABILITY_SIGN_RECOVER));

		System.out.println();
		System.out.print("default public capability flags:");
//		final int cf2 = PrimusKeyAttributes.getKeyCapabilityFlags(keyPair.getPublic());
//		for (int f = 1; f <= cf2; f <<= 1) {
//			if ((cf2 & f) != 0) {
//				System.out.print(" " + PrimusKeyAttributes.getCapabilityFlagName(f));
//			}
//		}
		System.out.println();
		System.out.println("CAPABILITY_ENCRYPT: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_ENCRYPT));
		System.out.println("CAPABILITY_DECRYPT: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_DECRYPT));
		System.out.println("CAPABILITY_SIGN: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_SIGN));
		System.out.println("CAPABILITY_VERIFY: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_VERIFY));
		System.out.println("CAPABILITY_WRAP: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_WRAP));
		System.out.println("CAPABILITY_UNWRAP: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_UNWRAP));
		System.out.println("CAPABILITY_DERIVE: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_DERIVE));
		System.out.println("CAPABILITY_INTEGRITY: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_INTEGRITY));
		System.out.println("CAPABILITY_ATTESTATION: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_ATTESTATION));
		System.out.println("CAPABILITY_TIMESTAMP: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_TIMESTAMP));
		System.out.println("CAPABILITY_VERIFY_RECOVER: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_VERIFY_RECOVER));
		System.out.println("CAPABILITY_SIGN_RECOVER: " + PrimusKeyAttributes.getKeyCapabilityFlag(keyPair.getPublic(), PrimusKeyAttributes.CAPABILITY_SIGN_RECOVER));

		System.out.println();
		System.out.print("default private access flags:");
//		final int af = PrimusKeyAttributes.getKeyAccessFlags(keyPair.getPrivate());
//		for (int f = 1; f <= af; f <<= 1) {
//			if ((af & f) != 0) {
//				System.out.print(" " + PrimusKeyAttributes.getAccessFlagName(f));
//			}
//		}
		System.out.println();
		System.out.println("ACCESS_SENSITIVE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_SENSITIVE));
		System.out.println("ACCESS_EXTRACTABLE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_EXTRACTABLE));
		System.out.println("ACCESS_MODIFIABLE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_MODIFIABLE));
		System.out.println("ACCESS_COPYABLE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_COPYABLE));
		System.out.println("ACCESS_TOKEN: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_TOKEN));
		System.out.println("ACCESS_INDESTRUCTIBLE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_INDESTRUCTIBLE));
		System.out.println("ACCESS_PRIVATE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_PRIVATE));
		System.out.println("ACCESS_PUBLIC: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_PUBLIC));
		System.out.println("ACCESS_BLOCKED: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_BLOCKED));
		System.out.println("ACCESS_NEVER_EXTRACTABLE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_NEVER_EXTRACTABLE));
		System.out.println("ACCESS_NO_PUBLIC_KEY: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_NO_PUBLIC_KEY));
		System.out.println("ACCESS_ALWAYS_SENSITIVE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_ALWAYS_SENSITIVE));
		System.out.println("ACCESS_EXTERNAL_OBJECT: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_EXTERNAL_OBJECT));
		System.out.println("ACCESS_LOCAL: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_LOCAL));
		System.out.println("ACCESS_TRUSTED: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_TRUSTED));
		System.out.println("ACCESS_WRAP_WITH_TRUSTED: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_WRAP_WITH_TRUSTED));
		System.out.println("ACCESS_UNIQUE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPrivate(), PrimusKeyAttributes.ACCESS_UNIQUE));

		System.out.println();
		System.out.print("default public access flags:");
//		final int af2 = PrimusKeyAttributes.getKeyAccessFlags(keyPair.getPublic());
//		for (int f = 1; f <= af2; f <<= 1) {
//			if ((af2 & f) != 0) {
//				System.out.print(" " + PrimusKeyAttributes.getAccessFlagName(f));
//			}
//		}
		System.out.println();
		System.out.println("ACCESS_SENSITIVE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_SENSITIVE));
		System.out.println("ACCESS_EXTRACTABLE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_EXTRACTABLE));
		System.out.println("ACCESS_MODIFIABLE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_MODIFIABLE));
		System.out.println("ACCESS_COPYABLE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_COPYABLE));
		System.out.println("ACCESS_TOKEN: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_TOKEN));
		System.out.println("ACCESS_INDESTRUCTIBLE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_INDESTRUCTIBLE));
		System.out.println("ACCESS_PRIVATE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_PRIVATE));
		System.out.println("ACCESS_PUBLIC: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_PUBLIC));
		System.out.println("ACCESS_BLOCKED: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_BLOCKED));
		System.out.println("ACCESS_NEVER_EXTRACTABLE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_NEVER_EXTRACTABLE));
		System.out.println("ACCESS_NO_PUBLIC_KEY: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_NO_PUBLIC_KEY));
		System.out.println("ACCESS_ALWAYS_SENSITIVE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_ALWAYS_SENSITIVE));
		System.out.println("ACCESS_EXTERNAL_OBJECT: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_EXTERNAL_OBJECT));
		System.out.println("ACCESS_LOCAL: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_LOCAL));
		System.out.println("ACCESS_TRUSTED: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_TRUSTED));
		System.out.println("ACCESS_WRAP_WITH_TRUSTED: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_WRAP_WITH_TRUSTED));
		System.out.println("ACCESS_UNIQUE: " + PrimusKeyAttributes.getKeyAccessFlag(keyPair.getPublic(), PrimusKeyAttributes.ACCESS_UNIQUE));

	}

}

