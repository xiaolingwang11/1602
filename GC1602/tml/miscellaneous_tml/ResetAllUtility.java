package miscellaneous_tml;

import tmlCommon.Relay;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dta.annotations.In;
import xoc.dta.measurement.IMeasurement;

/*
 * Rly_On_list, Rly_Off_list;
 */
public class ResetAllUtility extends TMLBase {

    public IMeasurement measurement;
    @In
    public String relayList_On;
    @In
    public String relayList_Off;
    @Override
    public void setup() {

        tsName = getTestSuiteName();
        IDeviceSetup devSetup = newDevSetup(measurement.getSpecificationName());

        // ********************* Relay Control Block ***************************
        Relay.setState(devSetup, relayList_On, relayList_Off);

        measurement.setSetups(devSetup);

    }

    @Override
    public void execute() {

        measurement.activate();
    }
}
