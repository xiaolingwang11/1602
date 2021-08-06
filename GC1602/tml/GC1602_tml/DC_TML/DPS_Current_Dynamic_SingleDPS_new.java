package GC1602_tml.DC_TML;

import java.util.HashMap;
import java.util.Map;

import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDcVI.IImeas;
import xoc.dsa.ISetupDcVI.IImeas.SetupUngangMode;
import xoc.dsa.ISetupPattern;
import xoc.dta.ParameterGroupCollection;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.datatypes.dsp.MultiSiteWaveDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIProfileResults;
import xoc.dta.resultaccess.IDcVIResults;

public class DPS_Current_Dynamic_SingleDPS_new extends TMLBase {

//    @In
//    public String importSpec = "";
//    @In
//    public String importSpec1 = "";
//    @In
//    public String importSpec2 = "";
//    @In
//    public String ModePins = "";
//    @In
//    public String ClkPins = "";
//    @In
//    public String checkTotalCurr = "";
//    @In
//    public String checkFunctional = "";
//    @In
//    public String resultPinlist = "gCONT";

    @In
    public String DpsPins_UnGanged = "VDDCORE";
//
//    String DPSmeas_action = "DPS_meas";
//    public String pattern_name = new String();
//    String DpsPins = "";
    // String DpsPins_UnGanged="VDDCORE+VDDARM";

    double wait_time = 10e-6;
    long samples = 4;// 16 can make the value is close smt7 value,; //SMT7 64 points
    double Irange = 30e-3;
    boolean unganged = false;

    String sVectorNumbers[] = {};

    private static final  String measuredVecNum = "21331,21916,22526,23109,23736,24898,25293,25880,26446,27060,27671,";

    public ParameterGroupCollection<TST_NAME_ParaGroup> tsCollection = new ParameterGroupCollection<>();

    public IMeasurement measurement;

    @Override
    public void setup() {
        tsName = getTestSuiteName();
        IDeviceSetup deviceSetup1 = newDevSetup(measurement.getSpecificationName());

        ISetupPattern LeakagePattern = deviceSetup1.getPatternRef(measurement.getPatternName());

        sVectorNumbers = measuredVecNum.split(",");
        for (String sVecNr : sVectorNumbers) {

            IImeas imeas = deviceSetup1.addDcVI(DpsPins_UnGanged).imeas("imeas" + sVecNr);

            if (unganged) {
                imeas.setWaitTime(wait_time).setAverages(samples).setIrange(Irange)
                        .setUngangMode(SetupUngangMode.fast).setRestoreIrange(true);// .setDownRanging(true)
                                                                                    // not support
            } else {
                imeas.setWaitTime(wait_time).setAverages(samples).setIrange(Irange)
                        .setRestoreIrange(true);// .setDownRanging(true) not support
            }
            LeakagePattern.addActions(Integer.valueOf(sVecNr), imeas);
        }


        // TODO: do i need to setup the following code??
        deviceSetup1.sequentialBegin("DPS_Current_Dynamic");
        deviceSetup1.waitCall("2 ms");
        deviceSetup1.patternCall(LeakagePattern);
        deviceSetup1.sequentialEnd();

        measurement.setSetups(deviceSetup1);

    }

    @Override
    public void update() {
        // add code here
        message(1, "[Power_Switch_Leakage] " + tsName + "::update() is called on "
                + context.getActiveSites().length + " sites");

        // modify the property of the level
        // measurement.digInOut("gCONT").setDisconnect(true).setConnect(false);
        // measurement.digInOut(ModePins).setDisconnect(false).setConnect(true);

        message(1,
                "Test suite: " + tsName + "::update end < < < < < < < < < < < < < < < < < < < < <");
    }

    @Override
    public void execute() {

        measurement.execute();

        // String DpsPins_UnGanged1="VDDCORE+VDDARM";
        String arPins[] = { "aa", "bb" };
        arPins = DpsPins_UnGanged.split("\\+");
        boolean bProfiling = false;
        if (bProfiling) {
            Map<String, MultiSiteWaveDoubleArray> result_perpin = new HashMap<String, MultiSiteWaveDoubleArray>();
            IDcVIProfileResults currentProfile = measurement.dcVI(DpsPins_UnGanged)
                    .preserveProfileResults();
            for (String s : arPins) {
                MultiSiteWaveDoubleArray currentwaveform = currentProfile.getWaveform(s);
                currentwaveform.getElement(0).plot("current profile of" + s);
                result_perpin.put(s, currentwaveform);
            }
        } else {
            IDcVIResults dcResultsAcc = measurement.dcVI(DpsPins_UnGanged).preserveResults();

//            if (Common_Lib_Folder.setup_Folder.RF_UserCommon.hiddenUpload()) {
                releaseTester();
//            }

            MultiSiteDoubleArray result_perpin = new MultiSiteDoubleArray();
            Map<String, MultiSiteDouble> result_perpin_vect = new HashMap<String, MultiSiteDouble>();

            String var_vddcore[] = { "D", "O" };
            String var_vddarm[] = { "C", "F" };
            for (String signal : arPins) {
                // println(" signal= "+signal);
                int i = 0;
                for (String sVecNr : sVectorNumbers) {
                    String sId = "imeas" + sVecNr;
                    result_perpin = dcResultsAcc.imeas(sId).getCurrent(signal);// get per pin result

                    // result_perpin_vect.put("Leakage_"+signal+"_"+var[i], result_perpin);
                    if (tsName.equals("Power_Switch_Leakage_VDDCORE_0p9V")) {
                        result_perpin_vect.put("Leakage_" + signal + "_" + var_vddcore[i],
                                result_perpin.getElement(0));
                        if (i == 0) {
//                            include.Common.Leakage_VDDCORE_D_temp.set(result_perpin.getElement(0));
                        }
                    }
                    if (tsName.equals("Power_Switch_Leakage_VDDARM_0p9V")) {
                        result_perpin_vect.put("Leakage_" + signal + "_" + var_vddarm[i],
                                result_perpin.getElement(0));
                        if (i == 0) {
//                            include.Common.Leakage_VDDARM_C_temp.set(result_perpin.getElement(0));
                        }
                    }

                    i++;
                    if (messageLogLevel > 3) {

                        // println(" current value= "+result_perpin_vect.keySet());
                    }
                }

                if (tsName.equals("Power_Switch_Leakage_VDDARM_0p9V")) {
//                    result_perpin_vect.put("VDDCORE_D+_VDDARM_C",
//                            (include.Common.Leakage_VDDCORE_D_temp
//                                    .add(include.Common.Leakage_VDDARM_C_temp)));
                    // total_mA.evaluate(include.Common.Leakage_VDDCORE_D_temp.add(include.Common.Leakage_VDDARM_C_temp));
                }

                for (TST_NAME_ParaGroup tmp : tsCollection.values()) {
                    // if(tmp.getId().equals(("Leakage_"+signal+"_"+i)))
                    // {
                    tmp.ptd.evaluate(result_perpin_vect.get(tmp.getId()));
                    // }

                }

            }

        }

        message(1, "Test suite: " + tsName
                + "::execute end < < < < < < < < < < < < < < < < < < < < <");
    }
}
