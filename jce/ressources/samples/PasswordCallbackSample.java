
/*
 * Copyright (C) 2018, Securosys SA
 */

import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.security.Security;
import java.util.Arrays;

import com.securosys.primus.jce.PrimusClearingLoginCallback;
import com.securosys.primus.jce.PrimusConfiguration;
import com.securosys.primus.jce.PrimusDevice;
import com.securosys.primus.jce.PrimusLogin;
import com.securosys.primus.jce.PrimusLoginCallback;
import com.securosys.primus.jce.PrimusLoginCallbackAdapter;
import com.securosys.primus.jce.PrimusProvider;

/**
 * Login sample with password callback for the Securosys Primus HSM JCE provider.
 */
public class PasswordCallbackSample {

	public static void main(final String... args) throws Exception {

		final String host = "grimsel.securosys.ch"; // externally reachable test/development HSM
		final String port = "2300"; // internally routed TCP port is 2300, externally routed is 2400
		final String user = "TESTUSER";
		final char[] password = "c2d1...".toCharArray();

		Security.addProvider(new PrimusProvider()); // same as PrimusProvider.installProvider();
		PrimusConfiguration.setHsmHost(host, port, user);

		final PrimusLoginCallbackAdapter plca = new PrimusLoginCallbackAdapter(user, password);
		PrimusLogin.login(user, plca, plca.getContext());
		PrimusDevice.getVersionInfo();
		PrimusLogin.logout();

		final PrimusLoginCallback plc = new PrimusLoginCallback() {
			@Override public char[] getPassword(@SuppressWarnings("unused") String userName, @SuppressWarnings("unused") Object context) {
				return password;
			}
		};
		PrimusLogin.login(user, plc, null);
		PrimusDevice.getVersionInfo();
		PrimusLogin.logout();

		final PrimusLoginCallback plct = new PrimusClearingLoginCallback() {
			@Override public char[] getPassword(@SuppressWarnings("unused") String userName, @SuppressWarnings("unused") Object context) {
				try {
					final CharBuffer cb = CharBuffer.allocate(256);
					try (final FileReader fr = new FileReader("/path/to/secure/token/file")) {
						fr.read(cb);
					}
					final char[] cc = new char[cb.flip().limit()];
					Arrays.fill(cb.get(cc).array(), ' ');
					return cc;
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		};
		PrimusLogin.login(user, plct, null);
		PrimusDevice.getVersionInfo();
		PrimusLogin.logout();

	}

}

