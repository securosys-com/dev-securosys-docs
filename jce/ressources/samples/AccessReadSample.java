
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessGroup;
import com.securosys.primus.jce.PrimusAccessNamedBlob;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.spec.NamedPublicKey;

/**
 * Sample code for reading back access attributes of an SKA key
 * with the Securosys Primus HSM JCE provider.
 *
 * See also AttestationSampleEcSka.java for retrieving access attributes
 * in the context of an attestation (XML format).
 */
public class AccessReadSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final PrimusAccess access = PrimusSkaHelper.getSampleAccess();

		// generate a key
		final String keyname = "ec-key-" + UUID.randomUUID().toString();
		final Key key = ((KeyPair)PrimusName.generate(KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()), new ECGenParameterSpec("secp256r1"), keyname, access)).getPrivate();

		// create integrity key, needed by HSM (signing attribute retrievals)
		final String integrityKeyName = "integrityKeyName-" + UUID.randomUUID().toString();
		PrimusName.generate(
			KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName()),
			new ECGenParameterSpec("secp256r1"),
			integrityKeyName,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_INTEGRITY, true)
		);

		// read back the access attributes
		final PrimusAccess access2 = PrimusAccess.getAccess(key, integrityKeyName, "SHA256withECDSA");
		assert access2.equals(access);

		// print its contents (implicit version, useful with >=2.3.3)
		System.out.println();
		System.out.println("access attributes:");
		System.out.println(access2);

		// print its contents (manual version)
		System.out.println();
		System.out.println("access attributes:");
		for (final PrimusAccessNamedBlob blob : access2.getBlobs()) {
			final String blobName = blob.getName();
			System.out.println("blob: " + (blobName == null ? "unnamed" : blobName));

			for (final PrimusAccessToken token : blob.getTokens()) {
				final String tokenName = token.getName();
				System.out.println("\t" + "token: " + (tokenName == null ? "unnamed" : tokenName));

				final long tokenDelaySeconds = token.getDelay(TimeUnit.SECONDS);
				System.out.println("\t\t" + "delay: " + tokenDelaySeconds + " " + "seconds");
				final long tokenLimitSeconds = token.getTimelimit(TimeUnit.SECONDS);
				System.out.println("\t\t" + "limit: " + tokenLimitSeconds + " " + "seconds");
				for (final PrimusAccessGroup group : token.getGroups()) {
					final String groupName = group.getName();
					System.out.println("\t\t" + "group: " + (groupName == null ? "unnamed" : groupName));

					final int groupQuorum = group.getQuorum();
					System.out.println("\t\t\t" + "quorum: " + groupQuorum);
					for (final PublicKey publickey : group.getPublicKeys()) {
						final String publickeyName = (publickey instanceof NamedPublicKey ? ((NamedPublicKey)publickey).getName() : null);
						System.out.println("\t\t\t" + "publickey: " + (publickeyName == null ? "unnamed" : publickeyName));

						// System.out.println("\t\t\t\t" + "key: " + publickey); // useful with >=2.3.3, but anyhow: implementationspecific, and typically containing newlines
						System.out.println("\t\t\t\t" + "key: " + publickey.getAlgorithm() + " " + publickey.getFormat() + " " + PrimusEncoding.base64Encode(publickey.getEncoded()));
					}
				}
			}
		}

	}

}

