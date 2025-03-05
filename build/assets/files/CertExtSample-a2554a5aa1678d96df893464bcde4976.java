
/*
 * Copyright (C) 2017,2023, Securosys SA
 */

import java.io.ByteArrayInputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.UUID;

import com.securosys.primus.jce.PrimusExternal;
import com.securosys.primus.jce.PrimusKeyFields;
import com.securosys.primus.jce.PrimusPrimitives;
import com.securosys.primus.tool2.KeyToolX;

public class CertExtSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.keepPasswords(true);
		PrimusHelper.setup(args);

		// HSM credentials
		final String host = PrimusHelper.host;
		final String port = PrimusHelper.port;
		final String user = PrimusHelper.user;
		final char[] password = PrimusHelper.password;

		// key passwords [Java runtime's keytool requires: a) key passwords, and b) with a minimal size [e.g.: 6 characters]]
		final char[] keypassword = "password".toCharArray();

		// store password (for derived interop code)
		// PKCS#12 store requires store password and key password equivalency
		// final char[] storePassword = keypassword;

		// name: random (to avoid duplicate-object collisions)
		final String name = UUID.randomUUID().toString();
		final String name2 = name + "x";

		// create a self signed key pair and certificate
		KeyToolX.invokeThrow(
			"-genkeypair", "-alias", name, "-keypass", String.valueOf(keypassword), "-keyalg", "EC", "-keysize", "256", "-sigalg", "SHA256withECDSA", "-dname", "CN=" + name,
			"-host", host, "-port", port, "-user", user, "-password", String.valueOf(password)
		);

		final Certificate cert = PrimusPrimitives.getKeyStore().getCertificate(name);
		PrimusPrimitives.getKeyStore().setCertificateEntry(name2, cert);
		final Certificate cert2 = PrimusPrimitives.getKeyStore().getCertificate(name2);
		assert cert2 == cert;

		final byte[] certData = PrimusExternal.dataToData(PrimusKeyFields.setDataObject(cert.getEncoded()));

		final Certificate cert3 = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(PrimusKeyFields.getDataObject(PrimusExternal.dataFromData(certData))));
		assert cert3 == cert;

	}

}

