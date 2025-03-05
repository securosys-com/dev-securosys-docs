
/*
 * Copyright (C) 2016-2021, Securosys SA
 */

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.UUID;

import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.spi2.ECHelper;

/**
 * Simple EC-DSA sample
 * with customized parameters
 * for the Securosys Primus HSM JCE provider.
 */
public class EcCustomDsaSample {

	static boolean runUnknownCurveTestAnyway = false;

	public static void main(final String... args) throws Exception {

//		// [if BouncyCastle should do the custom-curve-parameters public key encoding: this parameters needs to be done ahead, as it's read at load time]
//		System.setProperty("com.securosys.primus.jce.SunObjects.preferBouncyCastleProvider", "true");

		// HSM configuration
		PrimusHelper.setup(args);

		test(
			new ECParameterSpec(
				new EllipticCurve( // curve
					new ECFieldFp( // field
						new BigInteger("883423532389192164791648750360308885314476597252960362792450860609699839") // p
					),
					new BigInteger("883423532389192164791648750360308885314476597252960362792450860609699836"), // a
					new BigInteger("738525217406992417348596088038781724164860971797098971891240423363193866") // b
				),
				new ECPoint( // g
					new BigInteger("110282003749548856476348533541186204577905061504881242240149511594420911"), // x
					new BigInteger("869078407435509378747351873793058868500210384946040694651368759217025454") // y
				),
				new BigInteger("883423532389192164791648750360308884807550341691627752275345424702807307"), // n
				1 // h
			)
		);

		test(
			ECHelper.pemToEcParameterSpec(
				"-----BEGIN EC PARAMETERS-----" + "\n" +
				"MIHgAgEBMCwGByqGSM49AQECIQD/////AAAAAQAAAAAAAAAAAAAAAP//////////" + "\n" +
				"/////zBEBCD/////AAAAAQAAAAAAAAAAAAAAAP///////////////AQgWsY12Ko6" + "\n" +
				"k+ez671VdpiGvGUdBrDMU7D2O848PifSYEsEQQRrF9Hy4SxCR/i85uVjpEDydwN9" + "\n" +
				"gS3rM6D0oTlF2JjClk/jQuL+Gn+bjufrSnwPnhYrzjNXazFezsu2QGg3v1H1AiEA" + "\n" +
				"/////wAAAAD//////////7zm+q2nF56E87nKwvxjJVECAQE=" + "\n" +
				"-----END EC PARAMETERS-----" + "\n"
			)
		);

		// next sample features unnamed (unnamable) parameters
		// the local verify operation needs some provider that can handle unnamed curves (Primus can, BouncyCastle can, Sun/Oracle can't)

		// make this sample not compile-time dependant on BouncyCastle
		try {
			// Security.addProvider(new BouncyCastleProvider());
			Security.addProvider((Provider)Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider").getConstructor().newInstance());
		} catch (final ClassNotFoundException | ClassCastException e) {
			//
		}
		final Provider bc = Security.getProvider("BC");
		if (bc != null || runUnknownCurveTestAnyway) {
			test(
				ECHelper.pemToEcParameterSpec(
					"-----BEGIN EC PARAMETERS-----" + "\n" +
					"MIHgAgEBMCwGByqGSM49AQECIQD/////////////////////////////////////" + "\n" +
					"///9lzBEBCD////////////////////////////////////////9lAQgAAAAAAAA" + "\n" +
					"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKYEQQQAAAAAAAAAAAAAAAAAAAAAAAAA" + "\n" +
					"AAAAAAAAAAAAAAAAAY2R5HHgmJzaJ99QWkU/K3Y1KU8t3yPjsSKsyZyenx4UAiEA" + "\n" +
					"/////////////////////2xhEHCZWtEARYQbCbdhuJMCAQE=" + "\n" +
					"-----END EC PARAMETERS-----" + "\n"
				),
				bc
			);

		}

	}

	static void test(final ECParameterSpec ecParameterSpec) throws Exception {
		test(ecParameterSpec, null);
	}

	static void test(final ECParameterSpec ecParameterSpec, final Provider verifyProvider) throws Exception {

		final String signAlgorithm = "SHA256withECDSA";

		final byte[] message = UUID.randomUUID().toString().getBytes();

		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", PrimusProvider.getProviderName());
		keyPairGenerator.initialize(ecParameterSpec);
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();

		assert keyPair != null;
		assert keyPair.getPrivate() != null; // private key part
		assert keyPair.getPrivate().getEncoded() == null; // private key encoding is not revealed
		assert keyPair.getPublic() != null; // public key part
		assert keyPair.getPublic().getEncoded() != null; // there is a public key encoding
		assert keyPair.getPublic().getEncoded().length != 0;

		// System.out.println("EC key pair generated");
		// System.out.println("public key: " + PrimusEncoding.base64Encode(keyPair.getPublic().getEncoded()));
		// System.out.println(PrimusEncoding.dumpDER(keyPair.getPublic().getEncoded())); // since @2.2.3
		// System.out.println(DERObject.valueOf(keyPair.getPublic().getEncoded()).toString(true, true, true)); // internal

		final Signature signature = Signature.getInstance(signAlgorithm, PrimusProvider.getProviderName());
		signature.initSign(keyPair.getPrivate());
		signature.update(message);
		final byte[] signatureBytes = signature.sign();

		// System.out.println("signed");
		// System.out.println("signature: " + PrimusEncoding.base64Encode(signatureBytes));
		// System.out.println(PrimusEncoding.dumpDER(signatureBytes)); // since @2.2.3
		// System.out.println(DERObject.valueOf(signatureBytes).toString(true, true, true)); // internal

		final Signature verifySignature = (verifyProvider == null ? Signature.getInstance(signAlgorithm) : Signature.getInstance(signAlgorithm, verifyProvider));
		verifySignature.initVerify(keyPair.getPublic());
		verifySignature.update(message);
		final boolean verified = verifySignature.verify(signatureBytes);
		assert verified;

	}

}

