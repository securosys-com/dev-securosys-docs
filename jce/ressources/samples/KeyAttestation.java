
/*
 * Copyright (C) 2019-2022, Securosys SA
 */

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.securosys.primus.jce.PrimusAttestation;
import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusEncoding;
import com.securosys.primus.jce.PrimusLogin;
import com.securosys.primus.jce.PrimusProvider;
import com.securosys.primus.jce.PrimusProviderException;
import com.securosys.primus.jce.file.FileContents;

/**
 * Sample how to retrieve a key attestation
 * from a Securosys Primus HSM,
 * and verify its signature and certificate chain.
 */
public class KeyAttestation {

	public static void main(final String... args) throws Exception {

		// output file; see '-out' for file output
		PrintStream out = System.out;

		// HSM name and credentials
		String host = null;
		String port = "2300";
		String user = null;
		char[] password = null;

		// key names and credentials
		String attestationkeyname = null;
		char[] attestationkeypassword = null;
		String keyname = null;
		char[] keypassword = null;

		// CA PEM file
		String fileca = null;

		// output files base
		String fileout = null;

		for (int i = 0; i < args.length; i++) {
			final String arg = args[i];
			final String nextarg = (i < args.length - 1 ? args[i + 1] : null);
			if (arg.equals("-host")) {
				host = nextarg;
				i++;
			} else if (arg.equals("-port")) {
				port = nextarg;
				i++;
			} else if (arg.equals("-user")) {
				user = nextarg;
				i++;
			} else if (arg.equals("-password")) {
				password = (nextarg == null ? null : nextarg.toCharArray());
				i++;
			} else if (arg.equals("-attestationkeyname")) {
				attestationkeyname = nextarg;
				i++;
			} else if (arg.equals("-attestationkeypassword")) {
				attestationkeypassword = (nextarg == null ? null : nextarg.toCharArray());
				i++;
			} else if (arg.equals("-keyname")) {
				keyname = nextarg;
				i++;
			} else if (arg.equals("-keypassword")) {
				keypassword = (nextarg == null ? null : nextarg.toCharArray());
				i++;
			} else if (arg.equals("-ca")) {
				fileca = nextarg;
				i++;
			} else if (arg.equals("-out")) {
				fileout = nextarg;
				i++;
			}
		}

		// install/configure/login
		Security.addProvider(new PrimusProvider());
		PrimusConfiguration.setHsmHost(host, port, user);
		PrimusLogin.login(user, password);

		// holders for signature and chain
		final byte[][] signature_ = new byte[1][];
		final String[] chain_ = new String[1];

		// get the attestation XML, together with a signature of it, and the certificate chain used for the signature
		final String xml = PrimusAttestation.getSignedAttributes(attestationkeyname, attestationkeypassword, keyname, keypassword, signature_, chain_);

		final byte[] signature = signature_[0];
		final String chain = chain_[0];

		// print data
		if (fileout == null) {
			out.println(xml);
			out.println(PrimusEncoding.base64Encode(signature));
			out.println();
			out.println(chain);
		} else {
			FileContents.putContents(fileout, xml);
			FileContents.putContents(fileout + ".signature", signature);
			FileContents.putContents(fileout + ".certificatechain", chain);
		}

		// decode certificate chain: from list of PEM encoded certificates
		final Certificate[] cc = PrimusAttestation.decodeCertificateChain(chain);
		final Certificate c = cc[0];
		final Certificate ca = cc[cc.length - 1];
		// let check the chain: this only verifies signatures, not expiry nor X.509 flags
		PrimusAttestation.decodeCertificateChain(chain, ca);

		// check the chain against provided CA
		if (fileca != null) {
			PrimusAttestation.decodeCertificateChain(
				chain,
				PrimusAttestation.decodeCertificateChain(
					FileContents.getContentsAsString(fileca))[0]);
		}

		// check X.509 values, such as expiry
		final TrustAnchor ta = new TrustAnchor((X509Certificate)ca, null);
		final Set<TrustAnchor> tas = new HashSet<TrustAnchor>();
		tas.add(ta);
		final List<Certificate> l = new ArrayList<Certificate>();
		for (final Certificate ccc : cc) {
			l.add(ccc);
		}
		final CertPath p = CertificateFactory.getInstance("X.509").generateCertPath(l);
		final PKIXParameters pp = new PKIXParameters(tas);
		pp.setRevocationEnabled(false); // needed to not get the complaint about missing OCSP URL (java.security.cert.CertPathValidatorException: Could not determine revocation status)
		CertPathValidator.getInstance(CertPathValidator.getDefaultType()).validate(p, pp); // may throw GeneralSecurityException
		out.println("certificate path validated");

		// verify attestation data signature
		final String signAlgorithm;
		final PublicKey pk = c.getPublicKey();
		if (pk instanceof RSAPublicKey) {
			signAlgorithm = PrimusAttestation.getRsaSignAlgorithm();
		} else if (pk instanceof ECPublicKey) {
			signAlgorithm = PrimusAttestation.getEcSignAlgorithm();
		} else {
			throw new PrimusProviderException("unexpected sign key type " + pk.getClass().getName());
		}
		final Signature verifyer = Signature.getInstance(signAlgorithm);
		verifyer.initVerify(c);
		verifyer.update(xml.getBytes(StandardCharsets.UTF_8));
		final boolean verified = verifyer.verify(signature);

		if (verified) {
			out.println("signature verified");
		} else {
			throw new PrimusProviderException("signature verification failed");
		}

	}

}

