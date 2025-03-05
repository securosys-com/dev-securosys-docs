
/*
 * Copyright (C) 2022, Securosys SA
 */

import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.Provider;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPublicKeySpec;
import java.util.UUID;

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusProvider;

import com.securosys.primus.jce.encoding.Pem;

/**
 * Sample importing and exporting a certificate with EC parameters in it.
 * Sun JCE provider can't cope with such, so we apply BouncyCastle for that (which has to be in the JVM's classpath).
 */
public class ParamsCertSample {

	public static void main(final String... args) throws Exception {

		// [if BouncyCastle should do the curve-parameters public key encoding/decoding: this parameters needs to be done ahead, as it's read at load time]
		System.setProperty("com.securosys.primus.jce.SunObjects.preferBouncyCastleProvider", "true");

		// make this sample not compile-time dependant on BouncyCastle
		// all the same: we need e.g. bcprov-debug-jdk15on-1.64.jar (or another version) in the JVM's classpath
		try {
			// Security.addProvider(new BouncyCastleProvider());
			Security.addProvider((Provider)Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider").getConstructor().newInstance());
		} catch (final ClassNotFoundException | ClassCastException e) {
			System.err.println(e);
		}

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// this will translate the PEM format into DER, no BC needed yet
		final byte[] certificate0 = Pem.decodeRaw(certificate)[0];
		// dump the DER structure, for information
		System.out.println(PrimusEncoding.dumpDER(certificate0));

		// this tries to construct a cert object by Sun (won't work) or BC (if configured and found)
		final Certificate certificate1 = Pem.decode(certificate)[0];
		System.out.println(certificate1);

		final KeyStore keyStore = KeyStore.getInstance(PrimusProvider.getKeyStoreTypeName(), primusProvider);
		keyStore.load(null);

		// persist X509 to HSM
		final String name = UUID.randomUUID().toString();
		keyStore.setCertificateEntry(name, certificate1);

		// read back
		final Certificate certificate2 = keyStore.getCertificate(name);
		System.out.println(certificate2);

		// public key embedded in the certificate
		final ECPublicKey publickey1 = (ECPublicKey)certificate2.getPublicKey();
		System.out.println(publickey1);
		final ECParameterSpec parameters1 = publickey1.getParams();
		System.out.println(parameters1 + ", " + parameters1.getCurve().getField().getFieldSize() + " bits");

		// translate EC parameters to name/OID
		AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("EC", "SunEC");
		algorithmParameters.init(parameters1);
		final ECGenParameterSpec parameters2 = algorithmParameters.getParameterSpec(ECGenParameterSpec.class);
		System.out.println(parameters2 + ", " + parameters2.getName());

		algorithmParameters = AlgorithmParameters.getInstance("EC", "SunEC"); // [SunEC refuses to be reinitialized]
		algorithmParameters.init(parameters2);
		final ECParameterSpec parameters3 = algorithmParameters.getParameterSpec(ECParameterSpec.class);
		System.out.println(parameters3 + ", " + parameters3.getCurve().getField().getFieldSize() + " bits");

		// make a compatible pbulic key
		final ECPublicKey publickey2 = (ECPublicKey)KeyFactory.getInstance("EC", "SunEC").generatePublic(new ECPublicKeySpec(publickey1.getW(), parameters3));
		System.out.println(publickey2);

	}

	/** X.509 certificate based on an EC public key with EC parameters instead of the normal curve OID for named curves. */
	public final static String certificate =
		"-----BEGIN CERTIFICATE-----" + "\n" +
		"MIIHajCCBRugAwIBAgIRANt9HYlBw6GEC51HiGNh/BQwggG6BggqhkjOPQQDAjCC" + "\n" +
		"AawCAQEwTQYHKoZIzj0BAQJCAf//////////////////////////////////////" + "\n" +
		"////////////////////////////////////////////////MIGIBEIB////////" + "\n" +
		"////////////////////////////////////////////////////////////////" + "\n" +
		"//////////////wEQgBRlT65YY4cmh+SmiGgtoVA7qLacluZsxXzuLSJkY7xCeFW" + "\n" +
		"GTlR7H6TexZSwL07sb8HNXPfiD0sNPHvRR/Ua1A/AASBhQQAxoWOBrcEBOnNnj7L" + "\n" +
		"ZiOVtEKcZIE5BT+1Ifgor2BrTT26oUted+/nWSj+HcEnov+o3jNIs8GFakKb+X5+" + "\n" +
		"McLlvWYBGDkpaniaO8AEXIpftCx9G9mY9URJV5tEaBevvRcnPmYsl+5ymV70JkDF" + "\n" +
		"ULkBP60HYTU8cIaicsJAiL6Udp/RZlACQgH/////////////////////////////" + "\n" +
		"//////////////pRhoeDvy+Wa3/MAUj3CaXQO7XJuImcR667b7cekThkCQIBATBE" + "\n" +
		"MQswCQYDVQQGEwJDSDEPMA0GA1UECBMGWnVyaWNoMRUwEwYDVQQKEwxTZWN1cm9z" + "\n" +
		"eXMgU0ExDTALBgNVBAMTBFVTUjEwHhcNMjEwODExMTMzNzM4WhcNMzEwODA5MTMz" + "\n" +
		"NzM4WjBEMQswCQYDVQQGEwJDSDEPMA0GA1UECBMGWnVyaWNoMRUwEwYDVQQKEwxT" + "\n" +
		"ZWN1cm9zeXMgU0ExDTALBgNVBAMTBFVTUjEwggJGMIIBuQYHKoZIzj0CATCCAawC" + "\n" +
		"AQEwTQYHKoZIzj0BAQJCAf//////////////////////////////////////////" + "\n" +
		"////////////////////////////////////////////MIGIBEIB////////////" + "\n" +
		"////////////////////////////////////////////////////////////////" + "\n" +
		"//////////wEQgBRlT65YY4cmh+SmiGgtoVA7qLacluZsxXzuLSJkY7xCeFWGTlR" + "\n" +
		"7H6TexZSwL07sb8HNXPfiD0sNPHvRR/Ua1A/AASBhQQAxoWOBrcEBOnNnj7LZiOV" + "\n" +
		"tEKcZIE5BT+1Ifgor2BrTT26oUted+/nWSj+HcEnov+o3jNIs8GFakKb+X5+McLl" + "\n" +
		"vWYBGDkpaniaO8AEXIpftCx9G9mY9URJV5tEaBevvRcnPmYsl+5ymV70JkDFULkB" + "\n" +
		"P60HYTU8cIaicsJAiL6Udp/RZlACQgH/////////////////////////////////" + "\n" +
		"//////////pRhoeDvy+Wa3/MAUj3CaXQO7XJuImcR667b7cekThkCQIBAQOBhgAE" + "\n" +
		"AD2cVmzDlUdQRuF9L+Fu8Z5Ch2BXLzOCE7kQsvO6BaFMgVM5maMj3M2A2TbYKMA4" + "\n" +
		"aLes1BwqQP3g30Gwzhks7fuAAHdWI6eLn2pWe+tvHI4+70aNf2HIi4Y8Y/MMBq/r" + "\n" +
		"kG7fbrJZXbBVM4yq+OBAZBT8oT8qq/4i5TrcDFcZzN5ifjplo00wSzAMBgNVHRMB" + "\n" +
		"Af8EAjAAMB0GA1UdDgQWBBTjNVStWnHfVADMuNKGL0T+3lPPGTAcBgNVHREEFTAT" + "\n" +
		"gRFpbmZvQHNlY3Vyb3N5cy5jaDCCAboGCCqGSM49BAMCMIIBrAIBATBNBgcqhkjO" + "\n" +
		"PQEBAkIB////////////////////////////////////////////////////////" + "\n" +
		"//////////////////////////////8wgYgEQgH/////////////////////////" + "\n" +
		"/////////////////////////////////////////////////////////////ARC" + "\n" +
		"AFGVPrlhjhyaH5KaIaC2hUDuotpyW5mzFfO4tImRjvEJ4VYZOVHsfpN7FlLAvTux" + "\n" +
		"vwc1c9+IPSw08e9FH9RrUD8ABIGFBADGhY4GtwQE6c2ePstmI5W0QpxkgTkFP7Uh" + "\n" +
		"+CivYGtNPbqhS1537+dZKP4dwSei/6jeM0izwYVqQpv5fn4xwuW9ZgEYOSlqeJo7" + "\n" +
		"wARcil+0LH0b2Zj1RElXm0RoF6+9Fyc+ZiyX7nKZXvQmQMVQuQE/rQdhNTxwhqJy" + "\n" +
		"wkCIvpR2n9FmUAJCAf//////////////////////////////////////////+lGG" + "\n" +
		"h4O/L5Zrf8wBSPcJpdA7tcm4iZxHrrtvtx6ROGQJAgEBA4GKADCBhgJBVicgajFs" + "\n" +
		"o8VsfSWnrh+mT2g1kWrMuvdSNsMhjTV0IeQyfhoEd4Y0AhS8uw+J6R7aq8gBGX1p" + "\n" +
		"F7U5c2PPqyweaWICQWwO6cen4wrs9Ush2Z95IOQJh7nN7uRhR1Ehv+S7QLmaMZzW" + "\n" +
		"8DmFn7TsKc148GywLr5nZtLYa5BYKWOGzRWuxZL/" + "\n" +
		"-----END CERTIFICATE-----" + "\n" +
		""
	;

}

