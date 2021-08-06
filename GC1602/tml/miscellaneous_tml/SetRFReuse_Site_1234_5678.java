package miscellaneous_tml;

import tmlCommon.Relay;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dta.measurement.IMeasurement;

/**
 * @Site1234 <b>set "KK13+KK15" off when site 1234 enabled;
 * @Site5678 <b>set "KK13+KK15" on when site 5678 enabled;
 */
public class SetRFReuse_Site_1234_5678 extends TMLBase {

    public IMeasurement measurement;



    @Override
    public void setup() {

        //public String relayList_On_S1234 = "";
        String relayList_Off_S1234 = "";
        String relayList_On_S5678 = "";
        //public String relayList_Off_S5678 = "";


        relayList_Off_S1234 = "KK13+KK15";
        relayList_On_S5678 = "KK13+KK15";

        tsName = getTestSuiteName();
        String relayList_On = "";
        String relayList_Off = "";

        if (isSite1234On) {

            //relayList_On = relayList_On_S1234;
            relayList_Off = relayList_Off_S1234;
        } else {

            relayList_On = relayList_On_S5678;
            //relayList_Off = relayList_Off_S5678;
        }

        IDeviceSetup devSetup = newDevSetup();

        if (importSpec == null || importSpec.isEmpty() || importSpec.equals("NA")) {
            // no spec imported
        } else {
            devSetup.importSpec(importSpec);
        }

        Relay.setState(devSetup, relayList_On, relayList_Off);

        measurement.setSetups(devSetup);
    }

    @Override
    public void execute() {

        measurement.activate();
    }
}
