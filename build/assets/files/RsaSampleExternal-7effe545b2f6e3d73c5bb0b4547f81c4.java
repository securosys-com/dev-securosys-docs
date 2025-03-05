
/*
 * Copyright (C) 2020, Securosys SA
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.UUID;

import com.securosys.primus.jce.PrimusExternal;
import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusProvider;

/**
 * RSA sign sample
 * with the Securosys Primus HSM JCE provider
 * using an external key.
 */
public class RsaSampleExternal {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);
		final PrimusProvider primusProvider = PrimusHelper.primusProvider;

		// key creation

		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_EXTERNAL_OBJECT, true);
		PrimusKeyAttributes.setKeyAccessFlag(PrimusKeyAttributes.ACCESS_TOKEN, false);
		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", primusProvider);
		keyPairGenerator.initialize(2048);
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		final byte[] keyData = PrimusExternal.toData(keyPair.getPrivate());

		// key use

		final byte[] message = UUID.randomUUID().toString().getBytes();

		final RSAPrivateKey key = (RSAPrivateKey)PrimusExternal.fromData(keyData);

		final Signature signature = Signature.getInstance("SHA256withRSA", primusProvider);
		signature.initSign(key);
		signature.update(message);
		final byte[] sig = signature.sign();

		// check

		final Signature verify = Signature.getInstance("SHA256withRSA");
		verify.initVerify(keyPair.getPublic());
		verify.update(message);
		assert verify.verify(sig);

	}

}

