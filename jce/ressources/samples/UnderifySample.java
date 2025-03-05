
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusProvider;

public class UnderifySample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		final KeyPairGenerator primusKeyPairGenerator = KeyPairGenerator.getInstance("EC", primusProvider);
		primusKeyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		final KeyPair keyPair = primusKeyPairGenerator.generateKeyPair();

		final byte[] message = "testmessage".getBytes();
		final String signAlgorithm = "NONEwithECDSA";
		final Signature signature = Signature.getInstance(signAlgorithm, primusProvider);
		signature.initSign(keyPair.getPrivate());
		signature.update(message);
		final byte[] sig = signature.sign();

		// DER/ASN.1 encoded signature:
		// for secp256{r|k}1 (field size 256 bits / 32 bytes):
		// 70, 71, or 72 bytes, depending on zero-byte paddings in case of highest-bit set
		// 0x30 (ASN.1 SEQUENCE type) 0x44 (length) 0x02 (ASN.1 INTEGER type) 0x20 (length) ... (32 bytes of R data) 0x02 (ASN.1 INTEGER type) 0x20 (length) ... (32 bytes of S data)

		// output of below (note that /every/ signature is different, due to the random part needed in ECDSA sign):
		// Signature:
		// length: 71 bytes
		// base64: MEUCIQDfLv9WGft6+wYa4Eo9Z1KWT1ch2fXojjSuFDkjCKiavgIgeSfrBeEn2nZiIb/t97+kszJcVQJC0HbQiSd8an6JU3Q=
		// base58: AN1rKvtbCup5FfjkHyR5fz8NkNqY88oVi2fGX6nhaVMyqEY9h44nhoz5ZoKmBwkpFAxipAu1v8Rc2SsuhjGFkaJ73gtE8XsqD
		// hex: 3045022100df2eff5619fb7afb061ae04a3d6752964f5721d9f5e88e34ae14392308a89abe02207927eb05e127da766221bfedf7bfa4b3325c550242d076d089277c6a7e895374
		// DER/ASN.1 structure: [
		//	INTEGER 00df2eff5619fb7afb061ae04a3d6752964f5721d9f5e88e34ae14392308a89abe
		//	INTEGER 7927eb05e127da766221bfedf7bfa4b3325c550242d076d089277c6a7e895374
		// ]

		System.out.println();
		System.out.println("Signature:");
		System.out.println("length: " + sig.length + " bytes");
		System.out.println("base64: " + PrimusEncoding.base64Encode(sig));
		System.out.println("base58: " + PrimusEncoding.base58Encode(sig));
		System.out.println("hex: " + PrimusEncoding.hexEncode(sig));
		System.out.println("DER/ASN.1 structure: " + PrimusEncoding.dumpDER(sig));

		final byte[] sig2 = PrimusEncoding.underifyRS(sig);

		// underified/raw Signature:
		// length: 64 bytes
		// hex: df2eff5619fb7afb061ae04a3d6752964f5721d9f5e88e34ae14392308a89abe7927eb05e127da766221bfedf7bfa4b3325c550242d076d089277c6a7e895374
		// base64: 3y7/Vhn7evsGGuBKPWdSlk9XIdn16I40rhQ5Iwiomr55J+sF4SfadmIhv+33v6SzMlxVAkLQdtCJJ3xqfolTdA==
		// base58: 5TogvdnRQRagXpABcAz6BYFoRkisoxNq7yM6p5as6926gfhPKSgQUKSbjiULfxheXj4r7W98wCEK58oWzxox2hYw

		System.out.println();
		System.out.println("underified/raw Signature:");
		System.out.println("length: " + sig2.length + " bytes");
		System.out.println("hex: " + PrimusEncoding.hexEncode(sig2));
		System.out.println("base64: " + PrimusEncoding.base64Encode(sig2));
		System.out.println("base58: " + PrimusEncoding.base58Encode(sig2));

	}

}

