
/*
 * Copyright (C) 2019-2022, Securosys SA
 */

import java.util.Arrays;
import java.util.UUID;

import com.securosys.primus.jce.PrimusKeyAttributes;
import com.securosys.primus.jce.PrimusKeyTypes;
import com.securosys.primus.jce.PrimusName;
import com.securosys.primus.jce.PrimusPrimitives;

public class KeyFlagsSample {

	public static void main(final String... args) throws Exception {

		// HSM configuration
		PrimusHelper.setup(args);

		final String base = UUID.randomUUID().toString();
		final String pri = base + "-pri";
		final String sec = base + "-sec";

		PrimusName.generate(pri, PrimusPrimitives.getKeyPairGenerator("RSA"));
		PrimusName.generate(sec, PrimusPrimitives.getKeyGenerator("AES"));

		printFlags(pri);
		printFlags(sec);

	}

	public static void printFlags(final String alias) {
		System.out.println();
		System.out.println("alias " + alias + ":");
		final String[][] types = PrimusKeyTypes.getKeyTypes(alias);
		System.out.println("key types: " + Arrays.deepToString(types));
		for (final String[] type : types) {
			final String t = type[0]; // PrivateKey/PublicKey/SecretKey
			int flags = PrimusKeyAttributes.getKeyAccessFlags(alias, t);
			System.out.print(t + ": access: " + "0x" + Integer.toHexString(flags) + ":");
			for (int f = 1; flags != 0; f <<= 1) {
				if ((flags & f) != 0) {
					System.out.print(" " + PrimusKeyAttributes.getAccessFlagName(f));
				}
				flags &= ~f;
			}
			System.out.println();
			flags = PrimusKeyAttributes.getKeyCapabilityFlags(alias, t);
			System.out.print(t + ": capability: " + "0x" + Integer.toHexString(flags) + ":");
			for (int f = 1; flags != 0; f <<= 1) {
				if ((flags & f) != 0) {
					System.out.print(" " + PrimusKeyAttributes.getCapabilityFlagName(f));
				}
				flags &= ~f;
			}
			System.out.println();
		}

	}

/* sample output:

alias 8eb76694-3b71-4e50-ae55-ca2d0515f3d2-pri:
key types: [[PrivateKey, RSA], [PublicKey, RSA]]
PrivateKey: access: 0x254a5: sensitive modifiable token private neverextractable alwayssensitive local unique
PrivateKey: capability: 0x26: decrypt sign unwrap
PublicKey: access: 0x412c: modifiable copyable token public local
PublicKey: capability: 0x19: encrypt verify wrap

alias 8eb76694-3b71-4e50-ae55-ca2d0515f3d2-sec:
key types: [[SecretKey, AES]]
SecretKey: access: 0x254a5: sensitive modifiable token private neverextractable alwayssensitive local unique
SecretKey: capability: 0x3f: encrypt decrypt sign verify wrap unwrap

*/

}

