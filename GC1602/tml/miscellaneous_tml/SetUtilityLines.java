package miscellaneous_tml;

import tmlCommon.Relay;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dta.annotations.In;
import xoc.dta.measurement.IMeasurement;

/*
 * Rly_On_list, Rly_Off_list;
 */
public class SetUtilityLines extends TMLBase {

    public IMeasurement measurement;
    @In
    public String relayList_On = "";
    @In
    public String relayList_Off = "";

    @Override
    public void setup() {

        tsName = getTestSuiteName();

        if ((relayList_On == null || relayList_On
                .isEmpty()) && (relayList_Off == null || relayList_Off.isEmpty())) {
            System.out.println("Error: both relayList_On and relayList_Off are empty!  @" + context.getTestSuiteName());
        }

        IDeviceSetup devSetup = newDevSetup();

        // TODO: do i need to import any spec?
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
