
/*
 * Copyright (C) 2020-2022, Securosys SA
 */

import com.securosys.primus.jce.PrimusDevice;
import com.securosys.primus.jce.PrimusKeyTypes;

public class DeviceAndPartitionInfoSample {

	public static void main(final String... args) throws Exception {

		PrimusHelper.setup(args);

		System.out.println();
		System.out.println("HSM Host Name: " + PrimusHelper.host);
		System.out.println("HSM TCP Port Number: " + PrimusHelper.port);
		System.out.println("HSM Partition/User Name: " + PrimusHelper.user);

		System.out.println();
		System.out.println("HSM Device Name: " + PrimusDevice.getDeviceName());
		System.out.println("HSM Device Info: " + PrimusDevice.getDeviceInfo());
		System.out.println("HSM Version Info: " + PrimusDevice.getVersionInfo());

		System.out.println();
		System.out.println("HSM Time: " + PrimusDevice.getUserInfo().get("GeneralizedTime"));

		System.out.println();
		System.out.println("HSM User Counter Serial: " + PrimusDevice.getUserCounterSerial());
		System.out.println("HSM User Counter Value: " + PrimusDevice.getUserCounterValue());

		System.out.println();
		System.out.println("Partition Info: " + PrimusDevice.getUserInfo());
		System.out.println("Partition Flags: " + PrimusDevice.getUserFlags());

		System.out.println();
		System.out.println("Partition User: " + PrimusDevice.getUserInfo().get("UserId"));
		System.out.println("Key IMPORT enabled: " + PrimusDevice.getUserFlags().contains("IMPORT"));

		System.out.println();
		System.out.println(PrimusKeyTypes.getKeyStoreStatisticsHsmData());

	}

}

// the sample will create following information:

/*

HSM Host Name: julier.securosys.ch
HSM TCP Port Number: 2300
HSM Partition/User Name: JENKINS

HSM Device Name: JULIER
HSM Device Info: HSM JULIER, Version RX-2.20.0-T
HSM Version Info: RX-2.20.0-T

HSM Time: 20221221132853Z

HSM User Counter Serial: 18376148.331c8f35-c2b3-ddc3-6702-0ac1f557189e.2AEC849C-16BEF93B-9311C922-B58C67C8
HSM User Counter Value: 595

Partition Info: {UserId=JENKINS, UserSerial=67020ac1f557189e, UserFlags=1048567, GeneralizedTime=20221221132853Z}
Partition Flags: [IMPORT, EXPORT, EXTRACT, KEY_AUTH, REST_API, TSB_ENGINE, EXTERNAL_STORAGE, DESTROY_OBJECTS, EXPORT_LOGS, EXTENDED_KEY_ATTRIBUTES, BTC, ETH, RIPPLE, IOTA, ROOT_KEY_STORE, TIMESTAMP_SERVICE, USEABLE_OBJECTS, CARDANO, PERSISTENT_EXTERNAL_OBJ]

Partition User: JENKINS
Key IMPORT enabled: true

user JENKINS, private keys 924, public keys 778, secret keys 262, certificates 374, data objects 4, invalidated keys 0, used size 9650176 bytes, max size 314572800 bytes, remaining objects 508638

*/

