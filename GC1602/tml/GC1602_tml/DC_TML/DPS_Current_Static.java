package GC1602_tml.DC_TML;

import java.util.Map;
import java.util.Map.Entry;

import common_Code.DC_common;
import include.Common;
import tmlCommon.Msg;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dta.ParameterGroupCollection;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.MultiSiteBoolean;
import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;
import xoc.dta.resultaccess.IDigInOutResults;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;

public class DPS_Current_Static extends TMLBase {

    @In
    public String importSpec1 = "";
    @In
    public String importSpec2 = "";
    @In
    public String ModePins = "";
    @In
    public String ClkPins = "";
    @In
    public String checkTotalCurr = "";
    @In
    public String checkFunctional = "";
    @In
    public String resultPinlist = "@";
    String DPSmeas_action = "DPS_meas";

    String DpsPins = "";
    @In
    public String DpsPins_UnGanged = "VDDCORE";

    public IFunctionalTestDescriptor myFtd;


    public ParameterGroupCollection<TST_NAME_ParaGroup> tsCollection = new ParameterGroupCollection<>();

    public IMeasurement measurement, measurement2;// ,measurement3;

    private static final String allDioPins = "gDigital";
    private static final String allDioPinsExceptModePins = "allDigitals";

    @Override
    public void setup() {

        tsName = getTestSuiteName();

        if (ClkPins .equals( "") || DpsPins_UnGanged .equals( "")) {
            Msg.error("ClkPins or DpsPins_UnGanged empty");
        }



        // TODO: what is the default connect and disconnect status?
        // to disconnect all dio pins except ModePins
//        measurement.digInOut(allDioPins).setConnect(false).setDisconnect(false);
//        measurement.digInOut(ModePins).setConnect(true); // overlab previous setting



        IDeviceSetup deviceSetup1 = newDevSetup(importSpec);
        deviceSetup1.importSpec(PinGroups);
        deviceSetup1.addDigInOut(allDioPins).setConnect(false).setDisconnect(false);
        deviceSetup1.addDigInOut(ModePins).setConnect(true); // overlab previous setting

        deviceSetup1.sequentialBegin("DPS_Current_Static");
        deviceSetup1.waitCall(2 * ms); // 0.1 ms : wait for the pin settle
        deviceSetup1.patternCall(pattern_name);
        deviceSetup1.sequentialEnd();


        measurement.setSetups(deviceSetup1);


        IDeviceSetup deviceSetup2 = newDevSetup();
        deviceSetup2.importSpec(importSpec1);
        deviceSetup2.importSpec(importSpec2);


        // this is really unnessary because ClkPins was already disconnected
        Common.disconnectDigIO(deviceSetup2, ClkPins);
        //measurement2.digInOut(ClkPins).setConnect(false);

        DC_common.DCVI_IMeas(deviceSetup2, DpsPins_UnGanged, DPSmeas_action, 10 * mA, 10 * mA,
                0.5 * ms, true, 512);

        deviceSetup2.sequentialBegin("DPS_meas");
        deviceSetup2.waitCall(2 * ms); // 0.01
        deviceSetup2.actionCall(DPSmeas_action);
        deviceSetup2.sequentialEnd();

        measurement2.setSetups(deviceSetup2);
    }

    @Override
    public void update() {
        // add code here

        if (messageLogLevel >= 2) {
            measurement.digInOut().result().capture().setEnabled(false);
            measurement.digInOut().result().cyclePassFail().setEnabled(true);
        }

        // modify the property of the level
        // we need to connect AC Relay of ModePins


        if (ModePins != "" && ModePins != "@") {
            measurement.digInOut().setDisconnect(true).setConnect(false);
            measurement.digInOut(ModePins).setDisconnect(false).setConnect(true);
        }
    }

    @Override
    public void execute() {

        IDigInOutResults passFailResults1 = null;
        MultiSiteBoolean passFailResults2 = new MultiSiteBoolean();
        measurement.execute();

        if (resultPinlist != "" && resultPinlist != "@") {
            passFailResults1 = measurement.digInOut(resultPinlist).preserveResults(myFtd);

        } else {
            passFailResults2 = measurement.hasPassed();

        }

        measurement2.execute();

        IDcVIResults dcVIResult2 = measurement2.dcVI(DpsPins_UnGanged).preserveResults();

//        if (Common_Lib_Folder.setup_Folder.RF_UserCommon.hiddenUpload()) {
            releaseTester();
//        }


        Map<String, MultiSiteDoubleArray> IMeasResults = dcVIResult2.imeas(DPSmeas_action)
                .getCurrent();

        printResult_LogLevel_LE3(IMeasResults);



        if (checkFunctional.equals("ON")) {
            if (resultPinlist != "" && resultPinlist != "@") {
                myFtd.evaluate(passFailResults1);
            } else {
                myFtd.evaluate(passFailResults2);
            }
        }

        Msg.debug("tsCollection" + tsCollection.keySet());


        for(String key : DpsPins_UnGanged.split("\\+")) {
            tsCollection.get(key).ptd.evaluate(IMeasResults.get(key), 0);
            Msg.debug("evaluate:" + key);
        }

//        for (Map.Entry<String, TST_NAME_ParaGroup> entry : tsCollection.entrySet()) {
//            entry.getValue().ptd.evaluate(IMeasResults.get(entry.getKey()), 0);
//        }
    }

    private void printResult_LogLevel_LE3(Map<String, MultiSiteDoubleArray> IMeasResults) {
        // int firstActiveSite = context.getActiveSites()[0];
        // int[] activeSite = context.getActiveSites();
        // MultiSiteBoolean offline = context.testProgram().variables().getBoolean("SYS.OFFLINE");
        if (messageLogLevel >= 3) {

            MultiSiteDouble valuesOfActionall = new MultiSiteDouble();

            for (Entry<String, MultiSiteDoubleArray> entry : IMeasResults.entrySet()) {
                String signal = entry.getKey();

                MultiSiteDoubleArray valuesOfSignal = entry.getValue();

                for (int actionCount = 0; actionCount < valuesOfSignal.length(); actionCount++) {

                    MultiSiteDouble valuesOfAction = valuesOfSignal.getElement(actionCount);

                    valuesOfActionall = valuesOfActionall.add(valuesOfAction);
                    for (int site : context.getActiveSites()) {

                        String output = String.format(
                                "Action number \"%d\", signal \"%s\", site \"%d\", value measured %3.6f",
                                actionCount + 1, signal, site, valuesOfAction.get(site));
                        message(5, output);
                    }
                }
            }
        }
    }
}
