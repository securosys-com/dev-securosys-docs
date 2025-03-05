
/*
 * Copyright (C) 2020,2021, Securosys SA
 */

import java.util.UUID;

import com.securosys.primus.jce.PrimusAttestation;
import com.securosys.primus.jce.PrimusEncoding;

/**
 * Sample getting an RFC 3161 timestamp from a Primus HSM.
 *
 * A set up root key store (RKS) is required.
 * see https://www.rfc-editor.org/rfc/rfc3161.txt
 */
public class TimestampSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		String timestampkeyname = UUID.randomUUID().toString();
		PrimusAttestation.createRsaTimestampKey(timestampkeyname);

		byte[] data = new byte[0];

		byte[] ts = PrimusAttestation.getTimestamp(timestampkeyname, data, "SHA256");
		assert ts != null;

		boolean verified = PrimusAttestation.verifyTimestamp(timestampkeyname, data, "SHA256", ts);
		assert verified;

		System.out.println(PrimusEncoding.dumpDER(ts));

	}

}

