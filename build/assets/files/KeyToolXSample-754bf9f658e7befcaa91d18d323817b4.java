
/*
 * Copyright (C) 2019, Securosys SA
 * Author Eric Laroche <eric@securosys.ch>
 */

import java.io.File;
import java.util.UUID;

import com.securosys.primus.tool2.KeyToolX;

/** Sample code using Securosys Primus HSM JCE provider's KeyToolX keytool adapter. */
public class KeyToolXSample {

	/*

	// the sample Java code below illustrates what would on the command line (shell) look like:

	// create [self signed] server CA [key pair and certificate]
	java -jar keytoolX.jar -host julier -port 2300 -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -genkeypair -alias ca -keypass password -keyalg EC -keysize 256 -sigalg SHA256withECDSA -dname CN=CA -validity 3650 -ext BC:ca:true
	// export the certificate
	java -jar keytoolX.jar -host julier -port 2300 -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -exportcert -alias ca -file ca.crt
	// create key pair
	java -jar keytoolX.jar -host julier -port 2300 -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -genkeypair -alias cs -keypass password -keyalg EC -keysize 256 -sigalg SHA256withECDSA -dname CN=CS -validity 3650
	// create a cert request
	java -jar keytoolX.jar -host julier -port 2300 -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -certreq -alias cs -keypass password -file cs.csr
	// sign the certification request, create a certificate
	java -jar keytoolX.jar -host julier -port 2300 -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -gencert -alias ca -keypass password -infile cs.csr -outfile cs.crt
	// if the ca wasn't on there: import ca
	// java -jar keytoolX.jar -host julier -port 2300 -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -importcert -noprompt -alias ca -keypass password -file ca.crt
	// import the created certificate
	java -jar keytoolX.jar -host julier -port 2300 -user JENKINS -password 694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825 -importcert -alias cs -keypass password -file cs.crt

	*/

	public static void main(final String... args) throws Exception {

		// HSM configuration
		final String host = "julier"; // internal test system
		final String port = "2300";
		final String user = "JENKINS";
		final String password = "694d63f8e0b5116432a2307a552b0eeebd0b86b8960d91b0ba9ebb1d65897825";

		// names of the keys and certs
		final String serverCa = "server-ca-" + UUID.randomUUID().toString();
		final String serverKey = "server-" + UUID.randomUUID().toString();

		// name of the key passwords
		// [Java runtime's keytool requires key passwords, with a minimal size [e.g.: 6]]
		final String keyPassword = "password";

		// create [self signed] server CA [key pair and certificate]
		KeyToolX.invokeThrow("-host", host, "-port", port, "-user", user, "-password", password,
			"-genkeypair", "-alias", serverCa, "-keypass", keyPassword, "-keyalg", "EC", "-keysize", "256", "-sigalg", "SHA256withECDSA", "-dname", "CN=" + serverCa, "-validity", "3650", "-ext", "BC:ca:true");

		// create server key [pair]
		KeyToolX.invokeThrow("-host", host, "-port", port, "-user", user, "-password", password,
			"-genkeypair", "-alias", serverKey, "-keypass", keyPassword, "-keyalg", "EC", "-keysize", "256", "-sigalg", "SHA256withECDSA", "-dname", "CN=" + serverKey, "-validity", "3650");

		// create a cert request
		final String csrFile = File.createTempFile("tmp.", ".csr").getPath();
		KeyToolX.invokeThrow("-host", host, "-port", port, "-user", user, "-password", password,
			"-certreq", "-alias", serverKey, "-keypass", keyPassword, "-file", csrFile);

		// sign the certification request, create a certificate
		final String crtFile = File.createTempFile("tmp.", ".crt").getPath();
		KeyToolX.invokeThrow("-host", host, "-port", port, "-user", user, "-password", password,
			"-gencert", "-alias", serverCa, "-keypass", keyPassword, "-infile", csrFile, "-outfile", crtFile);
		(new File(csrFile)).delete();

		// import the created certificate
		KeyToolX.invokeThrow("-host", host, "-port", port, "-user", user, "-password", password,
			"-importcert", "-alias", serverKey, "-keypass", keyPassword, "-file", crtFile);
		(new File(crtFile)).delete();

	}

}

