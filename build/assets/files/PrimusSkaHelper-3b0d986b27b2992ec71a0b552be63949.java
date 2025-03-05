
/*
 * Copyright (C) 2017-2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.concurrent.TimeUnit;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessGroup;
import com.securosys.primus.jce.PrimusAccessToken;

/**
 * Helper functionality for SKA (smart key attributes)
 * for Securosys Primus test code.
 */
public class PrimusSkaHelper {

	// don't instantiate; no use to do so
	private PrimusSkaHelper() {
		//
	}

	/** sample delay */
	public static long delay = 1;

	/** sample delay time unit */
	public static TimeUnit delayTimeUnit = TimeUnit.MINUTES;

	/** sample access */
	public static PrimusAccess access = null;

	/** sample access without time limit (that doesn't require timestamp use) */
	public static PrimusAccess accessNoTimeLimit = null;

	/** sample access with time delay */
	public static PrimusAccess accessWithDelay = null;

	/** get sample access */
	public static PrimusAccess getSampleAccess() throws Exception {
		if (access == null) {
			doSampleAccesses();
		}
		return access;
	}

	/** get access without time limit (that doesn't require timestamp use) */
	public static PrimusAccess getSampleAccessNoTimeLimit() throws Exception {
		if (accessNoTimeLimit == null) {
			doSampleAccesses();
		}
		return accessNoTimeLimit;
	}

	/** get access without time limit (that doesn't require timestamp use) */
	public static PrimusAccess getSampleAccessWithDelay() throws Exception {
		if (accessWithDelay == null) {
			doSampleAccesses();
		}
		return accessWithDelay;
	}

	// operation role: sign/unwrap
	public static KeyPair andrea = null;
	public static KeyPair beryl = null;
	public static KeyPair chantal = null;
	public static KeyPair debby = null;

	// block/unblock role (see other sample for use)
	public static KeyPair erin = null;
	public static KeyPair florence = null;
	public static KeyPair gabrielle = null;

	// modify role (see other sample for use)
	public static KeyPair helene = null;

	/** do sample accesses */
	public static void doSampleAccesses() throws Exception {

		// locally create authorization keys, different persons, for different roles
		final KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		localKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));

		// operation role: sign/unwrap
		andrea = localKeyPairGenerator.generateKeyPair();
		beryl = localKeyPairGenerator.generateKeyPair();
		chantal = localKeyPairGenerator.generateKeyPair();
		debby = localKeyPairGenerator.generateKeyPair();
		final PublicKey[] operationPublicKeys = new PublicKey[] {
			PrimusAccessGroup.getNamedPublicKey("Andrea", andrea.getPublic()),
			PrimusAccessGroup.getNamedPublicKey("Beryl", beryl.getPublic()),
			PrimusAccessGroup.getNamedPublicKey("Chantal", chantal.getPublic()),
			PrimusAccessGroup.getNamedPublicKey("Debby", debby.getPublic()),
		};

		// block/unblock role (see other sample for use)
		erin = localKeyPairGenerator.generateKeyPair();
		florence = localKeyPairGenerator.generateKeyPair();
		gabrielle = localKeyPairGenerator.generateKeyPair();
		final PublicKey[] riskOfficersPublicKeys = new PublicKey[] {
			PrimusAccessGroup.getNamedPublicKey("Erin", erin.getPublic()),
			PrimusAccessGroup.getNamedPublicKey("Florence", florence.getPublic()),
			PrimusAccessGroup.getNamedPublicKey("Gabrielle", gabrielle.getPublic()),
		};

		// modify role (see other sample for use)
		helene = localKeyPairGenerator.generateKeyPair();
		final PublicKey[] superBossPublicKey = new PublicKey[] {
			PrimusAccessGroup.getNamedPublicKey("Helene", helene.getPublic()),
		};

		// rules with groups

		// half the operators to sign with a key
		final PrimusAccessGroup operationGroup = new PrimusAccessGroup("Operation", operationPublicKeys, operationPublicKeys.length / 2);
		final PrimusAccessBlob operationBlob = new PrimusAccessBlob(new PrimusAccessToken(0, TimeUnit.MINUTES, 10, TimeUnit.MINUTES, operationGroup));
		final PrimusAccessBlob operationBlobNoTimeLimit = new PrimusAccessBlob(new PrimusAccessToken(operationGroup));
		final PrimusAccessBlob operationBlobWithDelay = new PrimusAccessBlob(new PrimusAccessToken(delay, delayTimeUnit, delay, delayTimeUnit, operationGroup));

		// one risk officer is sufficient to block a key
		final PrimusAccessGroup blockGroup = new PrimusAccessGroup("Risk Officers", riskOfficersPublicKeys, 1);
		final PrimusAccessBlob blockBlob = new PrimusAccessBlob(new PrimusAccessToken(blockGroup));
		final PrimusAccessBlob blockBlobWithDelay = new PrimusAccessBlob(new PrimusAccessToken(delay, delayTimeUnit, delay, delayTimeUnit, blockGroup));

		// all ROs are required to unblock the key
		final PrimusAccessGroup unblockGroup = new PrimusAccessGroup("Risk Officers", riskOfficersPublicKeys, riskOfficersPublicKeys.length);
		final PrimusAccessBlob unblockBlob = new PrimusAccessBlob(new PrimusAccessToken(unblockGroup));
		final PrimusAccessBlob unblockBlobWithDelay = new PrimusAccessBlob(new PrimusAccessToken(delay, delayTimeUnit, delay, delayTimeUnit, unblockGroup));

		// a single 'super boss'
		final PrimusAccessGroup modifyGroup = new PrimusAccessGroup("Super Boss", superBossPublicKey, 1);
		final PrimusAccessBlob modifyBlob = new PrimusAccessBlob(new PrimusAccessToken(modifyGroup));
		final PrimusAccessBlob modifyBlobWithDelay = new PrimusAccessBlob(new PrimusAccessToken(delay, delayTimeUnit, delay, delayTimeUnit, modifyGroup));

		access = new PrimusAccess(operationBlob, blockBlob, unblockBlob, modifyBlob);
		accessNoTimeLimit = new PrimusAccess(operationBlobNoTimeLimit, blockBlob, unblockBlob, modifyBlob);
		accessWithDelay = new PrimusAccess(operationBlobWithDelay, blockBlobWithDelay, unblockBlobWithDelay, modifyBlobWithDelay);

	}

}

