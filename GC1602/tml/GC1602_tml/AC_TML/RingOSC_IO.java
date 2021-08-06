package GC1602_tml.AC_TML;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dta.ParameterGroup;
import xoc.dta.ParameterGroupCollection;
import xoc.dta.TestMethod;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.MultiSiteLong;
import xoc.dta.datatypes.dsp.MultiSiteWaveLong;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDigInOutCaptureResults;
import xoc.dta.resultaccess.datatypes.MultiSiteBitSequence;
import xoc.dta.testdescriptor.IParametricTestDescriptor;
import xoc.dta.workspace.IWorkspace;

public class RingOSC_IO extends TestMethod {

    public IMeasurement measurement;
    @In
    public String importSpec = "";
    @In
    public String importOperatSeq = "";
    String capSignal;
    IWorkspace ws;// = context.workspace();
    String sProjectPath; // = ws.getActiveProjectPath();
    public String tsName = new String();
    public String pattern_name = new String();
    public List<String> capSignalGroup = Arrays.asList("EMMC_D_7_", "EMMC_CMD", "EMMC_D_0_",
            "EMMC_D_3_", "EMMC_D_6_", "EMMC_CLK", "EMMC_DS", "EMMC_D_4_", "EMMC_D_1_", "EMMC_D_5_",
            "EMMC_D_2_", "CMMCLK1", "CMPD0", "CMRST0", "CMMCLK0", "CMRST2", "CMPD2", "SDA1", "SCL1",
            "SDA0", "SCL0");

    String capname = "CAM_PD + CAM_RSTN + U0CTS_DIG + U0RTS_DIG + CCIRD_0_ + CCIRD_2_ + KEYIN_1_";

    public class TST_NAME_ParameterGroup extends ParameterGroup {

        public IParametricTestDescriptor ptd;

    }

    public ParameterGroupCollection<TST_NAME_ParameterGroup> tsCollection = new ParameterGroupCollection<>();

    @Override
    public void setup() {

        tsName = context.getTestSuiteName();
        tsName = tsName.substring(1 + tsName.lastIndexOf("."));
        ws = context.workspace();
        sProjectPath = ws.getActiveProjectPath();

        message(1, "[RF_TX] " + tsName + "::setup() begin is called on "
                + context.getActiveSites().length + " sites");

        IDeviceSetup deviceSetup1 = DeviceSetupFactory.createInstance(context.testProgram()
                .variables().getString("dsa_path").get(context.getActiveSites()[0]));

        // ==============//
        // deviceSetup1 //
        // ==============//

        deviceSetup1.importSpec(importSpec);

        deviceSetup1.parallelBegin();

        deviceSetup1.patternCall("vectors.WCN.CLK_26M");
        deviceSetup1.patternCall("vectors.WCN.CLK_32K");
        deviceSetup1.patternCall("vectors.WCN.CLK13M");

        deviceSetup1.sequentialBegin("ROSC_test");
        deviceSetup1.waitCall("1 ms");// 5 MS
        deviceSetup1.operatingSequenceCall(importOperatSeq);

        deviceSetup1.sequentialEnd();

        deviceSetup1.parallelEnd();

        measurement.setSetups(deviceSetup1);

    }

    @Override
    public void update() {
        // add code here
    }

    @Override
    public void execute() {

        int firstActiveSite = context.getActiveSites()[0];
        int[] activeSite = context.getActiveSites();
        measurement.execute();
        //
        IDigInOutCaptureResults digCapture1 = measurement
                .digInOut(convOrderedListToSignalString(capSignalGroup)).preserveCaptureResults();

       // if (Common_Lib_Folder.setup_Folder.RF_UserCommon.hiddenUpload()) {
            releaseTester();
        //}
        //
        MultiSiteWaveLong resultWave1 = digCapture1.getParallelBitsAsLongArray(capSignalGroup)
                .toMultiSiteWave();

        Map<String, MultiSiteLong> ROSC_cnt = new HashMap<String, MultiSiteLong>();
        Map<String, MultiSiteLong> ROSC_valid = new HashMap<String, MultiSiteLong>();
        Map<String, MultiSiteBitSequence> aa = digCapture1.getSerialBitsAsBitSequence();

        int index = 0;
        // ptd1.evaluate(ROSC_valid);
        for (TST_NAME_ParameterGroup tmp : tsCollection.values()) {

            if (tmp.getId().equals(("valid_" + index).toString())) {
                ROSC_valid.put("valid_" + index,
                        (resultWave1.getValue(index).and(0x100000)).rightShift(20));

                // tmp.ptd.evaluate(ROSC_valid);
                // println("valid_" + ROSC_valid);

            }
            if (tmp.getId().contains(("cnt_" + index).toString())) {

                ROSC_cnt.put("cnt_" + index, resultWave1.getValue(index).and(0xfffff));
                // println("index" + index);
                // println("resultWave1" + resultWave1.getValue(index));
                index++;

            }

        }

        index = 0;
        for (TST_NAME_ParameterGroup tmp : tsCollection.values()) {

            if (tmp.getId().equals(("valid_" + index).toString())) {
                // ROSC_valid.put("valid_"+index,
                // (resultWave1.getValue(index).and(0x100000)).rightShift(20));

                tmp.ptd.evaluate(ROSC_valid.get("valid_" + index));

            }
            if (tmp.getId().contains(("cnt_" + index).toString())) {

                tmp.ptd.evaluate(ROSC_cnt.get("cnt_" + index));
                index++;

            }

        }

    }

    public static String convOrderedListToSignalString(List<String> capSignals) {
        StringBuilder sb = new StringBuilder();
        for (String inSignal : capSignals) {
            if (sb.length() != 0) {
                sb.append(" + ");
            }
            sb.append(inSignal);
        }
        return sb.toString();
    }
}
