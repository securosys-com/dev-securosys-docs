
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.securosys.primus.jce.PrimusDevice;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusPrimitives;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSignature;
import com.securosys.primus.jce.PrimusTimestamp;

/**
 * Sample getting an proprietray timestamp from a Primus HSM.
 *
 * Using PrimusDevice.getHsmTimestamp;
 * for the RFC 3161 timestamp see PrimusAttestation.getTimestamp/TimestampSample.java.
 *
 * No root key store (RKS) is required for PrimusDevice.getHsmTimestamp.
 */
public class TimestampNoRksSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		String integritykeyname = UUID.randomUUID().toString();
		PrimusName.generate(
			KeyPairGenerator.getInstance("EC", primusProvider),
			new ECGenParameterSpec("secp256r1"),
			integritykeyname,
			new PrimusKeyAttributes.CapabilityAttribute(PrimusKeyAttributes.CAPABILITY_INTEGRITY, true)
		);

		byte[] data = new byte[0];

		PrimusSignature.setWantSignatures(true);
		PrimusSignature.setSignatureKey(integritykeyname);
		byte[] ts = PrimusDevice.getHsmTimestamp(data, integritykeyname, (char[])null, "SHA256withECDSA");
		assert ts != null;
		PrimusSignature tssig = PrimusSignature.getSignature();

		Signature localVerifyer = Signature.getInstance("SHA256withECDSA");
		localVerifyer.initVerify(PrimusPrimitives.getPublicKeyDirectly(integritykeyname));
		localVerifyer.update(ts);
		boolean tssigOk = localVerifyer.verify(tssig.getEncoding());
		assert tssigOk;

		PrimusTimestamp pts = new PrimusTimestamp(ts, tssig);
		System.out.println("timestamp payload: " + Arrays.toString(pts.getPayload()));
		System.out.println("timestamp date: " + new Date(TimeUnit.SECONDS.toMillis(pts.getSecondsSinceEpoch())));
		System.out.println("signature key name: " + pts.getSignatureKeyName());

	}

}

