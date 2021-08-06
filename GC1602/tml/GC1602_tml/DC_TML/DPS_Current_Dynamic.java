package GC1602_tml.DC_TML;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tmlCommon.TMLBase;
import xoc.dsa.ActionAnchoring;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDcVI.IImeas;
import xoc.dsa.ISetupPattern;
import xoc.dta.ParameterGroupCollection;
import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;

public class DPS_Current_Dynamic extends TMLBase {

    public ParameterGroupCollection<TST_NAME_ParaGroup> tsCollection = new ParameterGroupCollection<>();
    public IMeasurement measurement;

    double wait_time = 10e-6;
    long samples = 4;// 16 can make the value is close smt7 value,; //SMT7 64 points
    double Irange = 30e-3;
    boolean unganged = false;

    // private static final String measuredVecNum =
    // "21331,21916,22526,23109,23736,24898,25293,25880,26446,27060,27671";
    int sVectorNumbers[] = { 21331, 21916, 22526, 23109, 23736, 24898, 25293, 25880, 26446, 27060,
            27671 };
    // int sVectorNumbers[] = {21331,27671};
    public static final String DpsPins_UnGanged = "VDDCORE";

    @Override
    public void setup() {
        tsName = getTestSuiteName();
        IDeviceSetup deviceSetup1 = newDevSetup(importSpec);

        ISetupPattern LeakagePattern = deviceSetup1.getPatternRef(pattern_name);

        for (int sVecNr : sVectorNumbers) {

            IImeas imeas = deviceSetup1.addDcVI(DpsPins_UnGanged).imeas("imeas" + sVecNr);

            imeas.setWaitTime(wait_time).setAverages(samples).setIrange(Irange)
                    .setRestoreIrange(true);// .setDownRanging(true) not support

            LeakagePattern.addActions(sVecNr, ActionAnchoring.INTERRUPTIVE, imeas);
        }

        deviceSetup1.sequentialBegin("DPS_Current_Dynamic");
        deviceSetup1.waitCall("2 ms");
        deviceSetup1.patternCall(LeakagePattern);
        deviceSetup1.sequentialEnd();

        measurement.setSetups(deviceSetup1);

    }

    @Override
    public void update() {
        // code
    }

    @Override
    public void execute() {

        measurement.execute();

        IDcVIResults dcResultsAcc = measurement.dcVI(DpsPins_UnGanged).preserveResults();

        MultiSiteDoubleArray result_perpin = new MultiSiteDoubleArray();

        Map<String, MultiSiteDouble> result_perpin_vect = new HashMap<String, MultiSiteDouble>();

        releaseTester();

        List<String> testPara = Arrays.asList("Leakage_VDDCORE_00", "Leakage_VDDCORE_01",
                "Leakage_VDDCORE_02", "Leakage_VDDCORE_03", "Leakage_VDDCORE_04",
                "Leakage_VDDCORE_05", "Leakage_VDDCORE_06", "Leakage_VDDCORE_07",
                "Leakage_VDDCORE_08", "Leakage_VDDCORE_09", "Leakage_VDDCORE_10");

        int i = 0;
        for (int sVecNr : sVectorNumbers) {
            String sId = "imeas" + sVecNr;
            result_perpin = dcResultsAcc.imeas(sId).getCurrent(DpsPins_UnGanged);
            result_perpin_vect.put(testPara.get(i), result_perpin.getElement(0));
            i++;
        }

        for (TST_NAME_ParaGroup tmp : tsCollection.values()) {
            tmp.ptd.evaluate(result_perpin_vect.get(tmp.getId()));
        }

    }
}
