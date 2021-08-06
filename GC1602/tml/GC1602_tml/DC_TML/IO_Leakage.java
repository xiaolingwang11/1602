package GC1602_tml.DC_TML;

import java.util.Map;

import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDigInOut.IVforceImeas;
import xoc.dta.ParameterGroup;
import xoc.dta.ParameterGroupCollection;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDigInOutActionResults;
import xoc.dta.testdescriptor.IParametricTestDescriptor;

public class IO_Leakage extends TMLBase {

    public IMeasurement measurement;

    public class ContGroup extends ParameterGroup {
        public IParametricTestDescriptor ptd;
    }

    public ParameterGroupCollection<ContGroup> tsCollection = new ParameterGroupCollection<>();

    @In
    public String force_value = "0 V";
    @In
    public String TestPinsPos = "allleak";// "gLeak_IO";
    @In
    public String settlingTime = "1 ms";
    @In
    public double iRange = 100e-6; // unit is uA

    private String vfimActionName = "IO_Leakage";

    @Override
    public void setup() {
        tsName = getTestSuiteName();
        IDeviceSetup devSetup = newDevSetup(importSpec);

        IVforceImeas vfimAction = devSetup.addDigInOut(TestPinsPos).vforceImeas(vfimActionName);
        vfimAction.setForceValue(force_value).setTerminated(false).setWaitTime(settlingTime)
                .setIrange(iRange).setVrange(force_value);

        devSetup.sequentialBegin();
        devSetup.patternCall(pattern_name);
        devSetup.waitCall("20 ms");
        devSetup.actionCall(vfimActionName);
        devSetup.sequentialEnd();

        measurement.setSetups(devSetup);
    }

    @Override
    public void execute() {
        // try {
        measurement.execute();

        IDigInOutActionResults result = measurement.digInOut(TestPinsPos).preserveActionResults();
        releaseTester();

        Map<String, MultiSiteDoubleArray> measPos = result.vforceImeas(vfimActionName).getCurrent();



        for (ContGroup tmp : tsCollection.values()) {
            // println("posSignals="+tmp.getId());
            // println("posSignalsvalue="+measPos.get(tmp.getId()).getElement(0));
            tmp.ptd.evaluate(measPos.get(tmp.getId()).getElement(0));
        }
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // println("breakPoint");
    }
}
