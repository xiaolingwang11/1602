package GC1602_tml.DC_TML;

import java.util.HashMap;
import java.util.Map;

import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDcVI;
import xoc.dsa.ISetupDcVI.IImeas;
import xoc.dsa.ISetupDcVI.IImeas.SetupUngangMode;
import xoc.dta.ParameterGroup;
import xoc.dta.ParameterGroupCollection;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;
import xoc.dta.testdescriptor.IParametricTestDescriptor;

public class Power_Short extends TMLBase {

    private static final int NrOfSamples = 256;
    private static final double dSettlingTime = 2 * ms;
    private static final String sMeasActionName = "Meas";
    private StringBuilder DPS_PS;
    private String[] arPins;

    @In
    public ParameterGroupCollection<VDDLIMIT> VDDLimit = new ParameterGroupCollection<>();
    public IMeasurement measurement;// , measurement0, measurement2;

    public static class VDDLIMIT extends ParameterGroup {
        public IParametricTestDescriptor ptd;
        //public String vddName;
        public Double iRange;

//        @Override
//        public void setId(String id) {
//            super.setId(id);
//            vddName = id;
//        }
    }

    @Override
    public void setup() {
        tsName = getTestSuiteName();
        IDeviceSetup devSetup = newDevSetup(importSpec);

        int iIndex = 0;

        DPS_PS = new StringBuilder();
        arPins = new String[VDDLimit.size()];
        for (String sVDD : VDDLimit.keySet()) {
            if (iIndex == 0) {
                DPS_PS.append(sVDD);
                arPins[iIndex++] = sVDD;
            } else {
                DPS_PS.append("+" + sVDD);
                arPins[iIndex++] = sVDD;
            }
        }

        IImeas currMeasActions[] = new IImeas[arPins.length];
        int index = 0;
        for (String sPin : arPins) {

            ISetupDcVI DPSSetup = devSetup.addDcVI(sPin);
            currMeasActions[index] = DPSSetup.imeas(sMeasActionName + sPin);
            currMeasActions[index++].setAverages(NrOfSamples).setWaitTime(dSettlingTime)
                    .setIrange(VDDLimit.get(sPin).iRange).setUngangMode(SetupUngangMode.fast)
                    .setRestoreIrange(true);
        }

        devSetup.parallelBegin();
        for (IImeas action : currMeasActions) {
            devSetup.sequentialBegin();
            devSetup.waitCall("0.5 ms");
            devSetup.actionCall(action);
            devSetup.sequentialEnd();
        }
        devSetup.parallelEnd();

        measurement.setSetups(devSetup);

    }

    @Override
    public void update() {
        measurement.digInOut().setConnect(false).setDisconnect(true);// add 20180428
    }

    @Override
    public void execute() {

        // measurement0.execute();// connect dps and digital

        measurement.execute();// keep dps connect and disconnect digital,and then measure the value

        IDcVIResults dcResultsAcc = measurement.dcVI(DPS_PS.toString()).preserveResults();

        Map<String, MultiSiteDoubleArray> results = new HashMap<String, MultiSiteDoubleArray>();
        // get value results for all DC actions defined in spec sheet

        releaseTester();
        for (String sPin : arPins) {
            // TODO: why the return data is an array?????????????????????
            MultiSiteDoubleArray msdResultPerPin = dcResultsAcc.imeas(sMeasActionName + sPin)
                    .getCurrent(sPin);
            results.put(sPin, msdResultPerPin);
            VDDLimit.get(sPin).ptd.evaluate(msdResultPerPin, 0);
        }

        // measurement2.execute();// connect all digital relay

    }
}
