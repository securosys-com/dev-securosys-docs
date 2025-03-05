
/*
 * Copyright (C) 2021, Securosys SA
 */

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

import com.securosys.primus.jce.PrimusAccess;
import com.securosys.primus.jce.PrimusApprovalToken;
import com.securosys.primus.jce.PrimusAuthorization;
import com.securosys.primus.jce.PrimusAuthorizationToken;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusProviderException;

/**
 * BLS key import sample for the Securosys Primus HSM JCE provider.
 */
public class BlsImportSkaSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration

		PrimusHelper.setup(args);

		// SKA attributes

		final PrimusAccess access = PrimusSkaHelper.getSampleAccessNoTimeLimit();
		PrimusAccess.setAccess(access);

		// import private and public key

		final byte[] privateKeyEncoding = Base64.getDecoder().decode("LNp+knp9uMQchboGV5m/re6PAdQ4R1m/vml7UFH5Mes=");
		final byte[] publicKeyEncoding = Base64.getDecoder().decode("MFEwHAYMKwYBBAGC3HwFAwEBBgwrBgEEAYLcfAUDAgEDMQClsw9oqZ3/RCTgoO/++z0YWViGBTG1fpfy6tS8IGY+7ohDragv3RhNVVR1KB1uans=");

		// [X509EncodedKeySpec support requites Primus JCE version 2.2.4 or later]

		final KeyFactory keyFactory = KeyFactory.getInstance("BLS", PrimusProvider.getProviderName());

		PrivateKey k;
		PublicKey pk;

		try {
			k = keyFactory.generatePrivate(new X509EncodedKeySpec(privateKeyEncoding));
			pk = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyEncoding));
		} catch (final PrimusProviderException e) {
			// [workaround for before 2.2.4]
			k = (PrivateKey)keyFactory.translateKey(new com.securosys.primus.jce.spec.BlsPrivateKeyImpl(privateKeyEncoding));
			pk = (PublicKey)keyFactory.translateKey(com.securosys.primus.jce.spec.BlsPublicKeyImpl.fromBytes(publicKeyEncoding));
		}

		final byte[] message = UUID.randomUUID().toString().getBytes(); // random payload to sign
		final byte[] tokenChallenge = (new PrimusApprovalToken(PrimusApprovalToken.OPERATION_SIGN, message)).getEncoding();

		final PrimusAuthorization primusAuthorization = new PrimusAuthorization();
		primusAuthorization.add(new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", PrimusSkaHelper.andrea.getPrivate(), PrimusSkaHelper.andrea.getPublic()));
		primusAuthorization.add(new PrimusAuthorizationToken(tokenChallenge, "SHA256withECDSA", PrimusSkaHelper.beryl.getPrivate(), PrimusSkaHelper.beryl.getPublic()));
		PrimusAuthorization.setAuthorization(primusAuthorization);

		// sign
		final Signature signature = Signature.getInstance("BLS", PrimusProvider.getProviderName());
		signature.initSign(k);
		signature.update(message);
		final byte[] signatureBytes = signature.sign();

		// verify signature
		signature.initVerify(pk);
		signature.update(message);
		final boolean verified = signature.verify(signatureBytes);
		assert verified;

	}

}

