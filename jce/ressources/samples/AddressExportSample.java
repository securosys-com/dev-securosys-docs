
/*
 * Copyright (C) 2019-2022, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECField;
import java.security.spec.ECFieldFp;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusAccessBlob;
import com.securosys.primus.jce.PrimusAccessToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusCryptoCurrencies;
import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusSignature;
import com.securosys.primus.jce.PrimusSpecs;
import com.securosys.primus.jce.encoding.DERObject;
import com.securosys.primus.jce.encoding.DSAHelper;

/**
 * Sample code for generating a quantum computing safe key pair
 * (public key part only revealed on first sign operation)
 * with the Securosys Primus HSM JCE provider.
 *
 * The sample goes through the following steps:
 * 1) HSM Login
 * 2) Create a Bitcoin Key
 * 3) Export the Address
 * 4) Sign a payload and export Public key
 */
public class AddressExportSample { // [was: NoPublicKeySample]

	public static void main(final String... args) throws Exception {

		// if no test arguments given: run all four modes of the test
		if (args == null || args.length == 0) {
			main("");
			main("-nopub");
			main("-ska");
			main("-nopub", "-ska");
			return;
		}

		// test options parsing
		boolean nopub = false; // no public key / quantum computing safe key
		boolean ska = false; // smart key attributes
		for (final String arg : args) {
			if (arg.equals("-nopub")) {
				nopub = true;
			} else if (arg.equals("-ska")) {
				ska = true;
			}
		}

		// some diagnostics
		System.out.println();
		if (nopub) {
			System.out.println("using nopub");
		}
		if (ska) {
			System.out.println("using ska");
		}

		// 1 -- HSM AND PROVIDER CONFIGURATION

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// 2 -- CREATE A BITCOIN KEY

		// make the key(pair) no-public-key, non-extractable (and sensitive), non-modifiable, and usable for signing only
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_NO_PUBLIC_KEY, nopub);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTRACTABLE, false);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_SENSITIVE, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_MODIFIABLE, false);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_SIGN, true);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_DECRYPT, false);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_UNWRAP, false);
		PrimusKeyAttributes.setKeyCapabilityFlag(PrimusKeyAttributes.CAPABILITY_DERIVE, false);

		// cryptocurrency must be specified with no-public-key
		PrimusCryptoCurrencies.setCryptoCurrency(PrimusCryptoCurrencies.BITCOIN);

		// no-pub works only with smart/extended key attributes, so set a PrimusAccess.
		// You will learn more about this in Approval samples
		final PrimusAccessToken accessToken = new PrimusAccessToken(0, 0);
		final PrimusAccessBlob accessBlob = new PrimusAccessBlob(accessToken);
		final PrimusAccess primusAccess = new PrimusAccess(accessBlob, accessBlob, accessBlob, accessBlob);
		PrimusAccess.setAccess(ska ? primusAccess : null);

		// make it an EC secp256k1 key [pair]
		// final String ecCurve = "secp256k1"; // secp256k1 is not well supported by Sun/Oracle fro Java version 17 on
		final String ecCurve = "secp256r1";
		final KeyPairGenerator primusKeyPairGenerator = KeyPairGenerator.getInstance("EC", primusProvider);
		primusKeyPairGenerator.initialize(new ECGenParameterSpec(ecCurve));

		// generate a named key
		final String keyName = UUID.randomUUID().toString();
		final KeyPair keyPair = PrimusName.generateKeyPair(primusKeyPairGenerator, keyName);

		assert keyPair != null; // [generateKeyPair should return something]
		assert keyPair.getPublic() != null; // [the public part is a reference [on a no-pub key]]
		if (nopub) {
			assert keyPair.getPublic().getEncoded() == null; // [the no-pub encoding is null]
		}

		System.out.println("Key created");

		// 3 -- EXPORT THE ADDRESS

		// get cryptocurrency address base - i.e. without a network prefix and a checksum
		final byte[] cryptocurrencyAddressBase;
		if (keyPair.getPublic() instanceof PrimusSpecs.CryptoCurrencyPublicKey) {
			cryptocurrencyAddressBase = ((PrimusSpecs.CryptoCurrencyPublicKey)keyPair.getPublic()).getCryptoCurrencyAddress();
		} else {
			if (ska) {
				// note: works for the ska case
				cryptocurrencyAddressBase = PrimusCryptoCurrencies.getCryptoCurrencyAddress(keyPair.getPrivate());
			} else {
				// note: have to specify crypto currency type for the non-ska case
				cryptocurrencyAddressBase = PrimusCryptoCurrencies.getCryptoCurrencyAddress(keyPair.getPrivate(), PrimusCryptoCurrencies.BITCOIN);
			}
		}

		// some sanity checks
		assert cryptocurrencyAddressBase != null; // [the crypto currency address is not null]
		assert cryptocurrencyAddressBase.length == 20; // [its length is 160 bits (20 bytes) in the bitcoin case]

		// convert to final format of Bitcoin mainnet addresses (to change to other network,
		// provide relevant version byte as a second parameter
		final String cryptocurrencyAddress = PrimusEncoding.base58CheckEncode(cryptocurrencyAddressBase);

		System.out.println("Address: " + cryptocurrencyAddress);

		// 4 -- SIGN A PAYLOAD AND EXPORT THE PUBLIC KEY

		// as we have a smart/extended key attributes setup (see comments above), set an approval
		final PrimusAuthorization primusAuthorization = new PrimusAuthorization();
		PrimusAuthorization.setAuthorization(keyPair.getPrivate(), ska ? primusAuthorization : null);

		// sign and get the public key afterwards
		final byte[] message = UUID.randomUUID().toString().getBytes();
		final String signAlgorithm = "NONEwithECDSA";
		final Signature signature = Signature.getInstance(signAlgorithm, primusProvider);
		signature.initSign(keyPair.getPrivate());
		signature.update(message);
		final byte[] sig = signature.sign();
		final PublicKey publicKey;
		final String when;
		if (keyPair.getPublic().getEncoded() != null) {
			publicKey = keyPair.getPublic();
			when = "exported earlier (no-no-pub-case)";
		} else {
			// oly non-null with ska mode
			publicKey = PrimusSignature.getNoPubPublicKey();
			when = "exported only after signature";
		}

		// public key printing
		if (publicKey != null) {
			final byte[] encoded = publicKey.getEncoded();
			if (encoded != null) {
				System.out.println();
				System.out.println("Public key (" + when + "):");
				System.out.println("base58: " + PrimusEncoding.base58Encode(encoded));
				System.out.println("hex: " + PrimusEncoding.hexEncode(encoded));
				System.out.println("DER structure: " + DERObject.valueOf(encoded));
				System.out.println("point encoding hex: " + PrimusEncoding.hexEncode(DERObject.valueOf(encoded).getNested(1).getBitstring()));
				System.out.println("point encoded uncompressed hex: " + PrimusEncoding.hexEncode(getEncoded((ECPublicKey)publicKey, false)));
				System.out.println("point encoded compressed hex: " + PrimusEncoding.hexEncode(getEncoded((ECPublicKey)publicKey, true)));
			}
		}

		// signature printing
		if (sig != null) {
			System.out.println();
			System.out.println("Signature:");
			System.out.println("base58: " + PrimusEncoding.base58Encode(sig));
			System.out.println("hex: " + PrimusEncoding.hexEncode(sig));
			System.out.println("DER structure: " + DERObject.valueOf(sig));
			System.out.println("underified hex: " + PrimusEncoding.hexEncode(DSAHelper.underifyRS(sig)));
		}

		// verify, locally
		if (publicKey != null) {
			final Signature verifyer = Signature.getInstance(signAlgorithm);
			if (sig != null) {
				verifyer.initVerify(publicKey);
				verifyer.update(message);
				final boolean verified = verifyer.verify(sig);
				System.out.println("sig verified: " + verified);
			}
		}

	}

	static byte[] getEncoded(final ECPublicKey publicKey, final boolean compressed) {
		final ECField field = publicKey.getParams().getCurve().getField();
		if (!(field instanceof ECFieldFp)) throw new IllegalArgumentException("uncool field " + field.getClass().getName());
		final int fieldSize = field.getFieldSize(); // bits
		final int size = (fieldSize + (Byte.SIZE - 1)) / Byte.SIZE; // bytes, round up
		final ECPoint w = publicKey.getW();
		// pad (if x is small) / unpad (if x is sign-padded)
		final byte[] x = padUnPad(w.getAffineX().toByteArray(), size);
		if (compressed) {
			final byte[] p = new byte[1 + x.length];
			p[0] = (byte)(0x2 + (w.getAffineY().testBit(0) ? 1 : 0));
			System.arraycopy(x, 0, p, 1, x.length);
			return p;
		} else {
			final byte[] y = padUnPad(w.getAffineY().toByteArray(), size);
			final byte[] p = new byte[1 + x.length + y.length];
			p[0] = 0x4;
			System.arraycopy(x, 0, p, 1, x.length);
			System.arraycopy(y, 0, p, 1 + x.length, y.length);
			return p;
		}
	}

	static byte[] padUnPad(final byte[] in, final int size) {
		final int inlength = (in == null ? 0 : in.length);
		if (inlength == 0) {
			return new byte[size];
		} else if (inlength < size) {
			final byte[] out = new byte[size];
			System.arraycopy(in, 0, out, size - inlength, inlength); // leftpad
			return out;
		} else if (inlength > size) {
			final byte[] out = new byte[size];
			System.arraycopy(in, inlength - size, out, 0, size); // unpad
			return out;
		}
		return in;
	}

}

