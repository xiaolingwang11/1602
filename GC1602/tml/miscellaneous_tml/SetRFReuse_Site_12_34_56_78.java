package miscellaneous_tml;

import tmlCommon.Msg;
import tmlCommon.Relay;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dta.measurement.IMeasurement;

/**
 * @if (activeSite.equals("12")) { relayList_On = ""; relayList_Off = "KK5,KK7,KK21,KK23"; }
 *
 * @if (activeSite.equals("34")) { relayList_On = "KK21,KK23"; relayList_Off = "KK9,KK11"; }
 *
 * @if (activeSite.equals("56")) { relayList_On = "KK5,KK7"; relayList_Off = "KK21,KK23"; }
 *
 * @if (activeSite.equals("78")) { relayList_On = "KK9,KK11,KK21,KK23"; relayList_Off = ""; }
 */
public class SetRFReuse_Site_12_34_56_78 extends TMLBase {

    public IMeasurement measurement;

    @Override
    public void setup() {

        tsName = getTestSuiteName();
        String relayList_On = "";
        String relayList_Off = "";

        if (enabledSite.equals("12")) {

            relayList_On = "";
            relayList_Off = "KK5+KK7+KK21+KK23";

        } else if (enabledSite.equals("34")) {

            relayList_On = "KK21+KK23";
            relayList_Off = "KK9+KK11";

        } else if (enabledSite.equals("56")) {

            relayList_On = "KK5+KK7";
            relayList_Off = "KK21+KK23";

        } else if (enabledSite.equals("78")) {

            relayList_On = "KK9+KK11+KK21+KK23";
            relayList_Off = "";

        } else {
            Msg.error("activeSite setting error: please only enable site 12/34/56/78");
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
