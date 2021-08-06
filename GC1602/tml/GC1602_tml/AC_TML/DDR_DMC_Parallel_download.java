package GC1602_tml.AC_TML;

import java.util.Arrays;
import java.util.List;

import tmlCommon.Msg;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupPattern;
import xoc.dta.datatypes.MultiSiteLong;
import xoc.dta.datatypes.MultiSiteLongArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDigInOutCaptureResults;
import xoc.dta.setupaccess.IPattern;
import xoc.dta.setupaccess.IStateCharBuffer;
import xoc.dta.testdescriptor.IParametricTestDescriptor;

// >>>>>>>>>>>>>>>>>>>>>>>>>  DDR_DMC_BIST :
// Vector Variable Pins: "U1TXD", "U1RXD", "NF_DATA_1", "NF_DQS", "U0TXD", "U0RXD", "U0CTS", "U0RTS"
// Frame LEngth in Samples: 537
// FRame Count: 1
// Transfer Mode: Parallel
// Samples per Word: 1

public class DDR_DMC_Parallel_download extends TMLBase {

    public IParametricTestDescriptor ptd_DDR_D2;
    public IParametricTestDescriptor ptd_DDR_D3;
    public IParametricTestDescriptor ptd_flag1;
    public IParametricTestDescriptor ptd_flag2;
    public IParametricTestDescriptor ptd_flag3;
    // public IFunctionalTestDescriptor ptd_flag4;

    private static final String mylebel = "vectors.dmc_bist_avc_pattern_20200513_p2_Copy";
    private static final String mylebel1 = "vectors.dmc_bist_avc_pattern_20200513_p3";
    private static final String mylebel2 = "vectors.dmc_bist_avc_pattern_20200513_p3";
    private static final String mylebel3 = "vectors.dmc_bist_avc_pattern_20200513_p3";

    private static final String primaryLabel1 = "vectors.dmc_bist_avc_pattern_20200513_p1";
    private static final String primaryLabel2 = "vectors.dmc_bist_avc_pattern_20200513_p2";
    private static final String primaryLabel3 = "vectors.dmc_bist_avc_pattern_20200513_p3";
    private static final String primaryLabel4 = "vectors.dmc_bist_avc_pattern_20200513_p4";
    private static final String primaryLabel5 = "vectors.dmc_bist_avc_pattern_20200513_p5";
    private static final String primaryLabel6 = "vectors.dmc_bist_avc_pattern_20200513_p6";

    public List<String> capSignalGroup = Arrays.asList("U0RTS", "U0CTS", "U0RXD", "U0TXD", "NF_DQS",
            "NF_DATA_1", "U1RXD", "U1TXD");
    // public List<String> capSignalGroup = Arrays.asList("U1TXD", "U1RXD", "NF_DATA_1", "NF_DQS",
    // "U0TXD", "U0RXD", "U0CTS", "U0RTS");

    private Ptn4DDR label1, label2, label3, label4, label5, label6;
    private IMeasurement measurement1, measurement2, measurement3, measurement4, measurement5,
            measurement6;

    @Override
    public void setup() {

        tsName = getTestSuiteName();
        label1 = new Ptn4DDR(measurement1, importSpec, primaryLabel1);
        label2 = new Ptn4DDR(measurement2, importSpec, primaryLabel2);
        label3 = new Ptn4DDR(measurement3, importSpec, primaryLabel3);
        label4 = new Ptn4DDR(measurement4, importSpec, primaryLabel4);
        label5 = new Ptn4DDR(measurement5, importSpec, primaryLabel5);
        label6 = new Ptn4DDR(measurement6, importSpec, primaryLabel6);

        label1.setupPatternCall();
        // DIGITAL_CAPTURE_TEST();
        // get result and edit next label;
        label2.setupPatternCall();
        // DIGITAL_CAPTURE_TEST();
        // get result and edit next label;
        label3.setupPatternCall();
        // DIGITAL_CAPTURE_TEST();
        // get result and edit next label;
        label4.setupPatternCall();
        // DIGITAL_CAPTURE_TEST();
        // ......................
        label5.setupPatternCall();
        // DIGITAL_CAPTURE_TEST();
        // get result and edit next label;
        label6.setupPatternCall();
        // DIGITAL_CAPTURE_TEST();
        // get d2, d3 result to be juded

    }

    @Override
    public void execute() {

        Msg.blankLine(2);
        Msg.info(tsName);

        label1.measurement.execute();

        MultiSiteLong drvn = process_label1_and_edit_label2(label1, label2);

        label2.measurement.execute();

        MultiSiteLong drvp = process_label2_and_edit_label3(drvn, label2, label3);

        label3.measurement.execute();

        process_label3_and_edit_label4(drvn, drvp, label3, label4);

        label4.measurement.execute();

        process_label4(label4);

        label5.measurement.execute();

        process_label5_and_edit_label6(label5, label6);

        label6.measurement.execute();

        process_label6(label6);

//        releaseTester();
//************************************************************
        label2.restoreStateChars();
        label3.restoreStateChars();
        label4.restoreStateChars();
        label6.restoreStateChars();

        releaseTester();
//        label2.clear();
//        label3.clear();
//        label4.clear();
//        label6.clear();
//****************************************************************
        ptd_DDR_D2.evaluate(d2);
        ptd_DDR_D3.evaluate(d3);
        ptd_flag1.evaluate(flag1);
        ptd_flag2.evaluate(flag2);
        ptd_flag3.evaluate(flag3);

    }

    private MultiSiteLong d2;
    private MultiSiteLong d3;
    private MultiSiteLong flag1;
    private MultiSiteLong flag2;
    private MultiSiteLong flag3;

    /**
     * <b> d2,d3 are from this process
     */
    public void process_label6(Ptn4DDR label6) {

        IDigInOutCaptureResults digCapture = label6.getDCapRsult();
        // digCapture = measurement.digInOut(toSigStr(capSignalGroup)).preserveCaptureResults();
        MultiSiteLongArray iCapData = digCapture.getParallelBitsAsLongArray(capSignalGroup);

        d2 = new MultiSiteLong();
        d3 = new MultiSiteLong();

        int[] activeSite = context.getActiveSites();
        for (int site : activeSite) {
            Msg.debug("iCapData[3]\t->\t" + iCapData.getElement(site, 3));
            d2.set(site, (iCapData.getElement(site, 3) >> 2) & 0x1);
            d3.set(site, (iCapData.getElement(site, 3) >> 3) & 0x1);
        }

        Msg.debug("d2\t->\t" + d2);
        Msg.debug("d3\t->\t" + d3);
    }

    public void process_label5_and_edit_label6(Ptn4DDR label5, Ptn4DDR label6) {

        IDigInOutCaptureResults digCapture = label5.getDCapRsult();
        // digCapture = measurement.digInOut(toSigStr(capSignalGroup)).preserveCaptureResults();
        MultiSiteLongArray iCapData = digCapture.getParallelBitsAsLongArray(capSignalGroup);

      //***************************************************************************************************************
        IPattern patMod = label6.getIPattern();
        IStateCharBuffer patternBuffer = label6.patternBuffer();
      //***************************************************************************************************************
        int[] activeSite = context.getActiveSites();
        for (int site : activeSite) {
            Long read_byte3 = iCapData.getElement(site, 0);
            Long read_byte2 = iCapData.getElement(site, 1);
            Long read_byte1 = iCapData.getElement(site, 2);
            Long read_byte0 = iCapData.getElement(site, 3);

//***************************************************************************************************************


//            patMod.vector(18).writeStateChar(site, toChar(1), "U1TXD");
//            patMod.vector(18).writeStateChar(site, toChar(1), "U1RXD");
//            patMod.vector(18).writeStateChar(site, toChar((read_byte3 >> 2) & 0x1), "NF_DATA_1");
//            patMod.vector(18).writeStateChar(site, toChar((read_byte3 >> 3) & 0x1), "NF_DQS");
//            patMod.vector(18).writeStateChar(site, toChar((read_byte3 >> 4) & 0x1), "U0TXD");
//            patMod.vector(18).writeStateChar(site, toChar((read_byte3 >> 5) & 0x1), "U0RXD");
//            patMod.vector(18).writeStateChar(site, toChar((read_byte3 >> 6) & 0x1), "U0CTS");
//            patMod.vector(18).writeStateChar(site, toChar((read_byte3 >> 7) & 0x1), "U0RTS");
//
//            patMod.vector(19).writeStateChar(site, toChar(read_byte2 & 0x1), "U1TXD");
//            patMod.vector(19).writeStateChar(site, toChar((read_byte2 >> 1) & 0x1), "U1RXD");
//            patMod.vector(19).writeStateChar(site, toChar((read_byte2 >> 2) & 0x1), "NF_DATA_1");
//            patMod.vector(19).writeStateChar(site, toChar((read_byte2 >> 3) & 0x1), "NF_DQS");
//            patMod.vector(19).writeStateChar(site, toChar((read_byte2 >> 4) & 0x1), "U0TXD");
//            patMod.vector(19).writeStateChar(site, toChar((read_byte2 >> 5) & 0x1), "U0RXD");
//            patMod.vector(19).writeStateChar(site, toChar((read_byte2 >> 6) & 0x1), "U0CTS");
//            patMod.vector(19).writeStateChar(site, toChar((read_byte2 >> 7) & 0x1), "U0RTS");
//
//            //patMod.vector(20).writeStateChar(site, toChar(read_byte1), "U1TXD");
//            patMod.vector(20).writeStateChar(site, toChar(read_byte1 & 0x1), "U1TXD");
//            patMod.vector(20).writeStateChar(site, toChar((read_byte1 >> 1) & 0x1), "U1RXD");
//            patMod.vector(20).writeStateChar(site, toChar((read_byte1 >> 2) & 0x1), "NF_DATA_1");
//            patMod.vector(20).writeStateChar(site, toChar((read_byte1 >> 3) & 0x1), "NF_DQS");
//            patMod.vector(20).writeStateChar(site, toChar((read_byte1 >> 4) & 0x1), "U0TXD");
//            patMod.vector(20).writeStateChar(site, toChar((read_byte1 >> 5) & 0x1), "U0RXD");
//            patMod.vector(20).writeStateChar(site, toChar((read_byte1 >> 6) & 0x1), "U0CTS");
//            patMod.vector(20).writeStateChar(site, toChar((read_byte1 >> 7) & 0x1), "U0RTS");
//
//            patMod.vector(21).writeStateChar(site, toChar(read_byte0      & 0x1), "U1TXD");
//            patMod.vector(21).writeStateChar(site, toChar((read_byte0 >> 1) & 0x1), "U1RXD");
//            patMod.vector(21).writeStateChar(site, toChar((read_byte0 >> 2) & 0x1), "NF_DATA_1");
//            patMod.vector(21).writeStateChar(site, toChar((read_byte0 >> 3) & 0x1), "NF_DQS");
//            patMod.vector(21).writeStateChar(site, toChar((read_byte0 >> 4) & 0x1), "U0TXD");
//            patMod.vector(21).writeStateChar(site, toChar((read_byte0 >> 5) & 0x1), "U0RXD");
//            patMod.vector(21).writeStateChar(site, toChar((read_byte0 >> 6) & 0x1), "U0CTS");
//            patMod.vector(21).writeStateChar(site, toChar((read_byte0 >> 7) & 0x1), "U0RTS");

            //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
            char[]  state0 = {'0'};
            char[]  state1 = {'1'};



            patternBuffer.writeStateChar(site,18, "U1TXD",state1);
            patternBuffer.writeStateChar(site,18, "U1RXD",state1);
            patternBuffer.writeStateChar(site,18, "NF_DATA_1", toCharArray((read_byte3 >> 2) & 0x1));
            patternBuffer.writeStateChar(site,18, "NF_DQS", toCharArray((read_byte3 >> 3) & 0x1));
            patternBuffer.writeStateChar(site,18, "U0TXD", toCharArray((read_byte3 >> 4) & 0x1));
            patternBuffer.writeStateChar(site,18, "U0RXD", toCharArray((read_byte3 >> 5) & 0x1));
            patternBuffer.writeStateChar(site,18, "U0CTS", toCharArray((read_byte3 >> 6) & 0x1));
            patternBuffer.writeStateChar(site,18, "U0RTS", toCharArray((read_byte3 >> 7) & 0x1));

            patternBuffer.writeStateChar(site,19, "U1TXD",toCharArray(read_byte2 & 0x1));
            patternBuffer.writeStateChar(site,19, "U1RXD", toCharArray((read_byte2 >> 1) & 0x1));
            patternBuffer.writeStateChar(site,19, "NF_DATA_1", toCharArray((read_byte2 >> 2) & 0x1));
            patternBuffer.writeStateChar(site,19, "NF_DQS", toCharArray((read_byte2 >> 3) & 0x1));
            patternBuffer.writeStateChar(site,19, "U0TXD", toCharArray((read_byte2 >> 4) & 0x1));
            patternBuffer.writeStateChar(site,19, "U0RXD", toCharArray((read_byte2 >> 5) & 0x1));
            patternBuffer.writeStateChar(site,19, "U0CTS", toCharArray((read_byte2 >> 6) & 0x1));
            patternBuffer.writeStateChar(site,19, "U0RTS", toCharArray((read_byte2 >> 7) & 0x1));


            patternBuffer.writeStateChar(site,20, "U1TXD",toCharArray(read_byte1 & 0x1));
            patternBuffer.writeStateChar(site,20, "U1RXD", toCharArray((read_byte1 >> 1) & 0x1));
            patternBuffer.writeStateChar(site,20, "NF_DATA_1", toCharArray((read_byte1 >> 2) & 0x1));
            patternBuffer.writeStateChar(site,20, "NF_DQS", toCharArray((read_byte1 >> 3) & 0x1));
            patternBuffer.writeStateChar(site,20, "U0TXD", toCharArray((read_byte1 >> 4) & 0x1));
            patternBuffer.writeStateChar(site,20, "U0RXD", toCharArray((read_byte1 >> 5) & 0x1));
            patternBuffer.writeStateChar(site,20, "U0CTS", toCharArray((read_byte1 >> 6) & 0x1));
            patternBuffer.writeStateChar(site,20, "U0RTS", toCharArray((read_byte1 >> 7) & 0x1));

            patternBuffer.writeStateChar(site,21, "U1TXD",toCharArray(read_byte0 & 0x1));
            patternBuffer.writeStateChar(site,21, "U1RXD", toCharArray((read_byte0 >> 1) & 0x1));
            patternBuffer.writeStateChar(site,21, "NF_DATA_1", toCharArray((read_byte0 >> 2) & 0x1));
            patternBuffer.writeStateChar(site,21, "NF_DQS", toCharArray((read_byte0 >> 3) & 0x1));
            patternBuffer.writeStateChar(site,21, "U0TXD", toCharArray((read_byte0 >> 4) & 0x1));
            patternBuffer.writeStateChar(site,21, "U0RXD", toCharArray((read_byte0 >> 5) & 0x1));
            patternBuffer.writeStateChar(site,21, "U0CTS", toCharArray((read_byte0 >> 6) & 0x1));
            patternBuffer.writeStateChar(site,21, "U0RTS", toCharArray((read_byte0 >> 7) & 0x1));
           //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&


//***************************************************************************************************************
        }

        // signals:
        // U1TXD, U1RXD, NF_DATA_1, NF_DQS, U0TXD, U0RXD, U0CTS, U0RTS
        // U1TXD_trim_4, U1RXD_trim_4, NFDATA1_trim_4, NFDQS_trim_4, U0TXD_trim_4, U0RXD_trim_4,
        // U0CTS_trim_4, U0RTS_trim_4);

    }

    private char[] getChars(long l) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * <b> flag1/2/3 are from this process
     */
    public void process_label4(Ptn4DDR label4) {
        IDigInOutCaptureResults digCapture = label4.getDCapRsult();
        // digCapture = measurement.digInOut(toSigStr(capSignalGroup)).preserveCaptureResults();
        MultiSiteLongArray iCapData = digCapture.getParallelBitsAsLongArray(capSignalGroup);

        flag1 = new MultiSiteLong();
        flag2 = new MultiSiteLong();
        flag3 = new MultiSiteLong();

        int[] activeSite = context.getActiveSites();
        for (int site : activeSite) {

            flag1.set(site, (iCapData.getElement(site, 0) >> 4) & 0x1);
            flag2.set(site, (iCapData.getElement(site, 4) >> 4) & 0x1);
            flag3.set(site, (iCapData.getElement(site, 8) >> 4) & 0x1);
        }

        Msg.debug("flag1\t->\t" + flag1);
        Msg.debug("flag2\t->\t" + flag2);
        Msg.debug("flag4\t->\t" + flag3);

    }

    public void process_label3_and_edit_label4(MultiSiteLong drvn_in, MultiSiteLong drvp_in,
            Ptn4DDR label3, Ptn4DDR label4) {

        IDigInOutCaptureResults digCapture = label3.getDCapRsult();
        // digCapture = measurement.digInOut(toSigStr(capSignalGroup)).preserveCaptureResults();
        MultiSiteLongArray iCapData = digCapture.getParallelBitsAsLongArray(capSignalGroup);

        IPattern patMod = label4.getIPattern();
        IStateCharBuffer patternBuffer = label4.patternBuffer();
        int[] activeSite = context.getActiveSites();
        for (int site : activeSite) {

            Long drvn_set = iCapData.getElement(site, 3) & 0x1F;
            Long drvp_set = ((iCapData.getElement(site, 2) & 0x3) << 3)
                    + ((iCapData.getElement(site, 3) >> 5) & 0x7);
            Long drvp = drvp_in.get(site);
            Long drvn = drvn_in.get(site);
//*********************************************************************************************
//            patMod.vector(18).writeStateChar(site, toChar(0), "U1TXD");
//            patMod.vector(20).writeStateChar(site, toChar(drvp & 0x1), "U1TXD");
//            patMod.vector(21).writeStateChar(site, toChar(drvn & 0x1), "U1TXD");
//            patMod.vector(53).writeStateChar(site, toChar(0), "U1TXD");
//            patMod.vector(54).writeStateChar(site, toChar(0), "U1TXD");
//            patMod.vector(55).writeStateChar(site, toChar(drvp & 0x1), "U1TXD");
//            patMod.vector(56).writeStateChar(site, toChar(drvn & 0x1), "U1TXD");
//
//            patMod.vector(18).writeStateChar(site, toChar(0), "U1RXD");
//            patMod.vector(20).writeStateChar(site, toChar((drvp >> 1) & 0x1), "U1RXD");
//            patMod.vector(21).writeStateChar(site, toChar((drvn >> 1) & 0x1), "U1RXD");
//            patMod.vector(53).writeStateChar(site, toChar(0), "U1RXD");
//            patMod.vector(54).writeStateChar(site, toChar(0), "U1RXD");
//            patMod.vector(55).writeStateChar(site, toChar((drvp >> 1) & 0x1), "U1RXD");
//            patMod.vector(56).writeStateChar(site, toChar((drvn >> 1) & 0x1), "U1RXD");
//
//            patMod.vector(18).writeStateChar(site, toChar(0), "NF_DATA_1");
//            patMod.vector(20).writeStateChar(site, toChar((drvp >> 2) & 0x1), "NF_DATA_1");
//            patMod.vector(21).writeStateChar(site, toChar((drvn >> 2) & 0x1), "NF_DATA_1");
//            patMod.vector(53).writeStateChar(site, toChar(0), "NF_DATA_1");
//            patMod.vector(54).writeStateChar(site, toChar(0), "NF_DATA_1");
//            patMod.vector(55).writeStateChar(site, toChar((drvp >> 2) & 0x1), "NF_DATA_1");
//            patMod.vector(56).writeStateChar(site, toChar((drvn >> 2) & 0x1), "NF_DATA_1");
//
//            patMod.vector(18).writeStateChar(site, toChar(0), "NF_DQS");
//            patMod.vector(20).writeStateChar(site, toChar((drvp >> 3) & 0x1), "NF_DQS");
//            patMod.vector(21).writeStateChar(site, toChar((drvn >> 3) & 0x1), "NF_DQS");
//            patMod.vector(53).writeStateChar(site, toChar(0), "NF_DQS");
//            patMod.vector(54).writeStateChar(site, toChar(0), "NF_DQS");
//            patMod.vector(55).writeStateChar(site, toChar((drvp >> 3) & 0x1), "NF_DQS");
//            patMod.vector(56).writeStateChar(site, toChar((drvn >> 3) & 0x1), "NF_DQS");
//
//            patMod.vector(18).writeStateChar(site, toChar(0), "U0TXD");
//            patMod.vector(20).writeStateChar(site, toChar((drvp >> 4) & 0x1), "U0TXD");
//            patMod.vector(21).writeStateChar(site, toChar((drvn >> 4) & 0x1), "U0TXD");
//            patMod.vector(53).writeStateChar(site, toChar(0), "U0TXD");
//            patMod.vector(54).writeStateChar(site, toChar(0), "U0TXD");
//            patMod.vector(55).writeStateChar(site, toChar((drvp >> 4) & 0x1), "U0TXD");
//            patMod.vector(56).writeStateChar(site, toChar((drvn >> 4) & 0x1), "U0TXD");
//
//            patMod.vector(18).writeStateChar(site, toChar(0), "U0RXD");
//            patMod.vector(20).writeStateChar(site, toChar(0), "U0RXD");
//            patMod.vector(21).writeStateChar(site, toChar(0), "U0RXD");
//            patMod.vector(53).writeStateChar(site, toChar(0), "U0RXD");
//            patMod.vector(54).writeStateChar(site, toChar(1), "U0RXD");
//            patMod.vector(55).writeStateChar(site, toChar(0), "U0RXD");
//            patMod.vector(56).writeStateChar(site, toChar(0), "U0RXD");
//
//            patMod.vector(18).writeStateChar(site, toChar(0), "U0CTS");
//            patMod.vector(20).writeStateChar(site, toChar(0), "U0CTS");
//            patMod.vector(21).writeStateChar(site, toChar(0), "U0CTS");
//            patMod.vector(53).writeStateChar(site, toChar(0), "U0CTS");
//            patMod.vector(54).writeStateChar(site, toChar(0), "U0CTS");
//            patMod.vector(55).writeStateChar(site, toChar(0), "U0CTS");
//            patMod.vector(56).writeStateChar(site, toChar(0), "U0CTS");
//
//            patMod.vector(18).writeStateChar(site, toChar(0), "U0RTS");
//            patMod.vector(20).writeStateChar(site, toChar(0), "U0RTS");
//            patMod.vector(21).writeStateChar(site, toChar(0), "U0RTS");
//            patMod.vector(53).writeStateChar(site, toChar(0), "U0RTS");
//            patMod.vector(54).writeStateChar(site, toChar(0), "U0RTS");
//            patMod.vector(55).writeStateChar(site, toChar(0), "U0RTS");
//            patMod.vector(56).writeStateChar(site, toChar(0), "U0RTS");


            //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
            char[] state0= {'0'};
            char[] state1= {'1'};
            patternBuffer.writeStateChar(site,18, "U1TXD",state0);
            patternBuffer.writeStateChar(site,20, "U1TXD",toCharArray(drvp & 0x1));
            patternBuffer.writeStateChar(site,21, "U1TXD",toCharArray(drvn & 0x1));
            patternBuffer.writeStateChar(site,53, "U1TXD",state0);
            patternBuffer.writeStateChar(site,54, "U1TXD",state0);
            patternBuffer.writeStateChar(site,55, "U1TXD",toCharArray(drvp & 0x1));
            patternBuffer.writeStateChar(site,56, "U1TXD",toCharArray(drvn & 0x1));

            patternBuffer.writeStateChar(site,18, "U1RXD",state0);
            patternBuffer.writeStateChar(site,20, "U1RXD",toCharArray((drvp >> 1) & 0x1));
            patternBuffer.writeStateChar(site,21, "U1RXD",toCharArray((drvn >> 1) & 0x1));
            patternBuffer.writeStateChar(site,53, "U1RXD",state0);
            patternBuffer.writeStateChar(site,54, "U1RXD",state0);
            patternBuffer.writeStateChar(site,55, "U1RXD",toCharArray((drvp >> 1) & 0x1));
            patternBuffer.writeStateChar(site,56, "U1RXD",toCharArray((drvn >> 1) & 0x1));

            patternBuffer.writeStateChar(site,18, "NF_DATA_1",state0);
            patternBuffer.writeStateChar(site,20, "NF_DATA_1",toCharArray((drvp >> 2) & 0x1));
            patternBuffer.writeStateChar(site,21, "NF_DATA_1",toCharArray((drvn >> 2) & 0x1));
            patternBuffer.writeStateChar(site,53, "NF_DATA_1",state0);
            patternBuffer.writeStateChar(site,54, "NF_DATA_1",state0);
            patternBuffer.writeStateChar(site,55, "NF_DATA_1",toCharArray((drvp >> 2) & 0x1));
            patternBuffer.writeStateChar(site,56, "NF_DATA_1",toCharArray((drvn >> 2) & 0x1));

            patternBuffer.writeStateChar(site,18, "NF_DQS",state0);
            patternBuffer.writeStateChar(site,20, "NF_DQS",toCharArray((drvp >> 3) & 0x1));
            patternBuffer.writeStateChar(site,21, "NF_DQS",toCharArray((drvn >> 3) & 0x1));
            patternBuffer.writeStateChar(site,53, "NF_DQS",state0);
            patternBuffer.writeStateChar(site,54, "NF_DQS",state0);
            patternBuffer.writeStateChar(site,55, "NF_DQS",toCharArray((drvp >> 3) & 0x1));
            patternBuffer.writeStateChar(site,56, "NF_DQS",toCharArray((drvn >> 3) & 0x1));

            patternBuffer.writeStateChar(site,18, "U0TXD",state0);
            patternBuffer.writeStateChar(site,20, "U0TXD",toCharArray((drvp >> 4) & 0x1));
            patternBuffer.writeStateChar(site,21, "U0TXD",toCharArray((drvn >> 4) & 0x1));
            patternBuffer.writeStateChar(site,53, "U0TXD",state0);
            patternBuffer.writeStateChar(site,54, "U0TXD",state0);
            patternBuffer.writeStateChar(site,55, "U0TXD",toCharArray((drvp >> 4) & 0x1));
            patternBuffer.writeStateChar(site,56, "U0TXD",toCharArray((drvn >> 4) & 0x1));

            patternBuffer.writeStateChar(site,18, "U0RXD",state0);
            patternBuffer.writeStateChar(site,20, "U0RXD",state0);
            patternBuffer.writeStateChar(site,21, "U0RXD",state0);
            patternBuffer.writeStateChar(site,53, "U0RXD",state0);
            patternBuffer.writeStateChar(site,54, "U0RXD",state1);
            patternBuffer.writeStateChar(site,55, "U0RXD",state0);
            patternBuffer.writeStateChar(site,56, "U0RXD",state0);

            patternBuffer.writeStateChar(site,18, "U0CTS",state0);
            patternBuffer.writeStateChar(site,20, "U0CTS",state0);
            patternBuffer.writeStateChar(site,21, "U0CTS",state0);
            patternBuffer.writeStateChar(site,53, "U0CTS",state0);
            patternBuffer.writeStateChar(site,54, "U0CTS",state0);
            patternBuffer.writeStateChar(site,55, "U0CTS",state0);
            patternBuffer.writeStateChar(site,56, "U0CTS",state0);

            patternBuffer.writeStateChar(site,18, "U0RTS",state0);
            patternBuffer.writeStateChar(site,20, "U0RTS",state0);
            patternBuffer.writeStateChar(site,21, "U0RTS",state0);
            patternBuffer.writeStateChar(site,53, "U0RTS",state0);
            patternBuffer.writeStateChar(site,54, "U0RTS",state0);
            patternBuffer.writeStateChar(site,55, "U0RTS",state0);
            patternBuffer.writeStateChar(site,56, "U0RTS",state0);



//*********************************************************************************************

        }

        // U1TXD, U1RXD, NF_DATA_1, NF_DQS, U0TXD, U0RXD, U0CTS, U0RTS
        // U1TXD_trim_3, U1RXD_trim_3, NFDATA1_trim_3, NFDQS_trim_3, U0TXD_trim_3, U0RXD_trim_3,
        // U0CTS_trim_3, U0RTS_trim_3);

        // signals:
        // U1TXD,U1RXD,NF_DATA_1,NF_DQS,U0TXD,U0RXD,U0CTS,U0RTS
        // datas:
        // U1TXD_trim_3, U1RXD_trim_3, NFDATA1_trim_3, NFDQS_trim_3,
        // U0TXD_trim_3, U0RXD_trim_3, U0CTS_trim_3, U0RTS_trim_3);
    }

    public MultiSiteLong process_label2_and_edit_label3(MultiSiteLong drvn_in, Ptn4DDR label2,
            Ptn4DDR label3) {

        IDigInOutCaptureResults digCapture = label2.getDCapRsult();
        // digCapture = measurement.digInOut(toSigStr(capSignalGroup)).preserveCaptureResults();
        MultiSiteLongArray iCapData = digCapture.getParallelBitsAsLongArray(capSignalGroup);

        // return this value for next step calculation
        MultiSiteLong drvpReturn = new MultiSiteLong();
        IPattern patMod = label3.getIPattern();
        IStateCharBuffer patternBuffer = label3.patternBuffer();
        int[] activeSite = context.getActiveSites();
        for (int site : activeSite) {

            Long drvp_inc = new Long(32);
            for (int i = 0; i < 32; i++) {
                drvp_inc = drvp_inc - ((iCapData.getElement(site, 1 + i * 4) >> 1) & 0x1);
            }

            drvp_inc = drvp_inc - 1;

            Long drvp_deinc = new Long(32);
            for (int i = 32; i < 64; i++) {
                drvp_deinc = drvp_deinc - ((iCapData.getElement(site, 1 + i * 4) >> 1) & 0x1);
            }

            Long drvp = (drvp_inc + drvp_deinc) / 2 + 1;
            drvpReturn.set(site, drvp);


            Long drvn = drvn_in.get(site);
//********************************************************************************************
//            patMod.vector(19).writeStateChar(site, toChar(1), "U1TXD");
//            patMod.vector(20).writeStateChar(site, toChar((drvp >> 3) & 0x1), "U1TXD");
//            patMod.vector(21).writeStateChar(site, toChar(drvn & 0x1), "U1TXD");
//            patMod.vector(20).writeStateChar(site, toChar((drvn >> 4) & 0x1), "U1RXD");
//            patMod.vector(21).writeStateChar(site, toChar((drvn >> 1) & 0x1), "U1RXD");
//            patMod.vector(21).writeStateChar(site, toChar((drvn >> 2) & 0x1), "NF_DATA_1");
//            patMod.vector(21).writeStateChar(site, toChar((drvn >> 3) & 0x1), "NF_DQS");
//            patMod.vector(21).writeStateChar(site, toChar((drvn >> 4) & 0x1), "U0TXD");
//            patMod.vector(21).writeStateChar(site, toChar(drvp & 0x1), "U0RXD");
//            patMod.vector(21).writeStateChar(site, toChar((drvp >> 1) & 0x1), "U0CTS");
//            patMod.vector(21).writeStateChar(site, toChar((drvp >> 2) & 0x1), "U0RTS");


            char[]  state0 = {'0'};
            char[]  state1 = {'1'};


            patternBuffer.writeStateChar(site,19, "U1TXD",state1);
            patternBuffer.writeStateChar(site,20, "U1TXD",toCharArray( (drvp >> 3) & 0x1 ));

            patternBuffer.writeStateChar(site,21, "U1TXD",toCharArray(drvn & 0x1));
            patternBuffer.writeStateChar(site,20, "U1RXD",toCharArray((drvn >> 4) & 0x1));
            patternBuffer.writeStateChar(site,21, "U1RXD",toCharArray((drvn >> 1) & 0x1));
            patternBuffer.writeStateChar(site,21, "NF_DATA_1",toCharArray((drvn >> 2) & 0x1));
            patternBuffer.writeStateChar(site,21, "NF_DQS",toCharArray((drvn >> 3) & 0x1));
            patternBuffer.writeStateChar(site,21, "U0TXD",toCharArray((drvn >> 4) & 0x1));
            patternBuffer.writeStateChar(site,21, "U0RXD",toCharArray(drvp & 0x1));
            patternBuffer.writeStateChar(site,21, "U0CTS",toCharArray((drvp >> 1) & 0x1));
            patternBuffer.writeStateChar(site,21, "U0RTS",toCharArray((drvp >> 2) & 0x1));

//********************************************************************************************
            // signals:
            // U1TXD, U1RXD, NF_DATA_1, NF_DQS, U0TXD, U0RXD, U0CTS, U0RTS"
            // datas:
            // U1TXD_trim_2, U1RXD_trim_2, NFDATA1_trim_2, NFDQS_trim_2, U0TXD_trim_2, U0RXD_trim_2,
            // U0CTS_trim_2, U0RTS_trim_2);

        }
        return drvpReturn;
    }

    public MultiSiteLong process_label1_and_edit_label2(Ptn4DDR label1, Ptn4DDR label2) {

        IDigInOutCaptureResults digCapture = label1.getDCapRsult();
        // digCapture = measurement.digInOut(toSigStr(capSignalGroup)).preserveCaptureResults();
        MultiSiteLongArray iCapData = digCapture.getParallelBitsAsLongArray(capSignalGroup);

        int mCnt = 68 - 1;
        // declare variable
        VecData U1TXD_trim_1[] = new VecData[mCnt];
        VecData U1RXD_trim_1[] = new VecData[mCnt];
        VecData NF_DATA_1_trim_1[] = new VecData[mCnt];
        VecData NF_DQS_trim_1[] = new VecData[mCnt];
        VecData U0TXD_trim_1[] = new VecData[mCnt];
        // init variable
        for (int i = 0; i < mCnt; i++) {
            U1TXD_trim_1[i] = new VecData();
            U1RXD_trim_1[i] = new VecData();
            NF_DATA_1_trim_1[i] = new VecData();
            NF_DQS_trim_1[i] = new VecData();
            U0TXD_trim_1[i] = new VecData();
        }

        // return this value for next step calculation
        MultiSiteLong drvnReturn = new MultiSiteLong();

        int[] activeSite = context.getActiveSites();
        for (int site : activeSite) {

            Long drvn_inc = new Long(0);
            for (int i = 0; i < 32; i++) {
                drvn_inc = drvn_inc + ((iCapData.getElement(site, 1 + i * 4) >> 1) & 0x1);
            }

            Long drvn_deinc = new Long(0);
            for (int i = 32; i < 64; i++) {
                drvn_deinc = drvn_deinc + ((iCapData.getElement(site, 1 + i * 4) >> 1) & 0x1);
            }

            drvn_deinc = drvn_deinc - 1;
            Long drvn = ((drvn_inc + drvn_deinc) / 2) + 1;
            drvnReturn.set(site, drvn);

            U1TXD_trim_1[0].vectorNum = 21;
            U1TXD_trim_1[0].msChar.set(site, toChar(drvn & 0x1));
            U1TXD_trim_1[1].vectorNum = 116;
            U1TXD_trim_1[1].msChar.set(site, toChar(drvn & 0x1));
            U1TXD_trim_1[2].vectorNum = 3152;
            U1TXD_trim_1[2].msChar.set(site, toChar(drvn & 0x1));

            U1RXD_trim_1[0].vectorNum = 21;
            U1RXD_trim_1[0].msChar.set(site, toChar((drvn >> 1) & 0x1));
            U1RXD_trim_1[1].vectorNum = 116;
            U1RXD_trim_1[1].msChar.set(site, toChar((drvn >> 1) & 0x1));
            U1RXD_trim_1[2].vectorNum = 3152;
            U1RXD_trim_1[2].msChar.set(site, toChar((drvn >> 1) & 0x1));

            NF_DATA_1_trim_1[0].vectorNum = 21;
            NF_DATA_1_trim_1[0].msChar.set(site, toChar((drvn >> 2) & 0x1));
            NF_DATA_1_trim_1[1].vectorNum = 116;
            NF_DATA_1_trim_1[1].msChar.set(site, toChar((drvn >> 2) & 0x1));
            NF_DATA_1_trim_1[2].vectorNum = 3152;
            NF_DATA_1_trim_1[2].msChar.set(site, toChar((drvn >> 2) & 0x1));

            NF_DQS_trim_1[0].vectorNum = 21;
            NF_DQS_trim_1[0].msChar.set(site, toChar((drvn >> 3) & 0x1));
            NF_DQS_trim_1[1].vectorNum = 116;
            NF_DQS_trim_1[1].msChar.set(site, toChar((drvn >> 3) & 0x1));
            NF_DQS_trim_1[2].vectorNum = 3152;
            NF_DQS_trim_1[2].msChar.set(site, toChar((drvn >> 3) & 0x1));

            U0TXD_trim_1[0].vectorNum = 21;
            U0TXD_trim_1[0].msChar.set(site, toChar((drvn >> 3) & 0x1));
            U0TXD_trim_1[1].vectorNum = 116;
            U0TXD_trim_1[1].msChar.set(site, toChar((drvn >> 3) & 0x1));
            U0TXD_trim_1[2].vectorNum = 3152;
            U0TXD_trim_1[2].msChar.set(site, toChar((drvn >> 3) & 0x1));

            for (int i = 0; i < 32; i++) {
                U1TXD_trim_1[i + 3].vectorNum = 149 + i * 92;
                U1TXD_trim_1[i + 3].msChar.set(site, toChar(drvn & 0x1));
                U1TXD_trim_1[i + 3 + 32].vectorNum = 3185 + i * 92;
                U1TXD_trim_1[i + 3 + 32].msChar.set(site, toChar(drvn & 0x1));

                U1RXD_trim_1[i + 3].vectorNum = 149 + i * 92;
                U1RXD_trim_1[i + 3].msChar.set(site, toChar((drvn >> 1) & 0x1));
                U1RXD_trim_1[i + 3 + 32].vectorNum = 3185 + i * 92;
                U1RXD_trim_1[i + 3 + 32].msChar.set(site, toChar((drvn >> 1) & 0x1));

                NF_DATA_1_trim_1[i + 3].vectorNum = 149 + i * 92;
                NF_DATA_1_trim_1[i + 3].msChar.set(site, toChar((drvn >> 2) & 0x1));
                NF_DATA_1_trim_1[i + 3 + 32].vectorNum = 3185 + i * 92;
                NF_DATA_1_trim_1[i + 3 + 32].msChar.set(site, toChar((drvn >> 2) & 0x1));

                NF_DQS_trim_1[i + 3].vectorNum = 149 + i * 92;
                NF_DQS_trim_1[i + 3].msChar.set(site, toChar((drvn >> 3) & 0x1));
                NF_DQS_trim_1[i + 3 + 32].vectorNum = 3185 + i * 92;
                NF_DQS_trim_1[i + 3 + 32].msChar.set(site, toChar((drvn >> 3) & 0x1));

                U0TXD_trim_1[i + 3].vectorNum = 149 + i * 92;
                U0TXD_trim_1[i + 3].msChar.set(site, toChar((drvn >> 4) & 0x1));
                U0TXD_trim_1[i + 3 + 32].vectorNum = 3185 + i * 92;
                U0TXD_trim_1[i + 3 + 32].msChar.set(site, toChar((drvn >> 4) & 0x1));
            }
        }

        IPattern patMod = label2.getIPattern();
        IStateCharBuffer patternBuffer = label2.patternBuffer();
        for (int i = 0; i < mCnt; i++) {
            // U1TXD, U1RXD, NF_DATA_1, NF_DQS, U0TXD",
            // U1TXD_trim_1, U1RXD_trim_1, NF_DATA_1_trim_1, NF_DQS_trim_1, U0TXD_trim_1);

//****************************************************************************************************
//            patMod.vector(U1TXD_trim_1[i].vectorNum).writeStateChar(U1TXD_trim_1[i].msChar,"U1TXD");
//            patMod.vector(U1RXD_trim_1[i].vectorNum).writeStateChar(U1RXD_trim_1[i].msChar,"U1RXD");
//            patMod.vector(NF_DATA_1_trim_1[i].vectorNum).writeStateChar(NF_DATA_1_trim_1[i].msChar,"NF_DATA_1");
//            patMod.vector(NF_DQS_trim_1[i].vectorNum).writeStateChar(NF_DQS_trim_1[i].msChar,"NF_DQS");
//            patMod.vector(U0TXD_trim_1[i].vectorNum).writeStateChar(U0TXD_trim_1[i].msChar,"U0TXD");

            for (int site : context.getActiveSites()) {
            patternBuffer.writeStateChar(site,(U1TXD_trim_1[i].vectorNum), "U1TXD",toCharArray1(U1TXD_trim_1[i].msChar.get(site)));
            patternBuffer.writeStateChar(site,(U1TXD_trim_1[i].vectorNum), "U1RXD",toCharArray1(U1TXD_trim_1[i].msChar.get(site)));
            patternBuffer.writeStateChar(site,(U1TXD_trim_1[i].vectorNum), "NF_DATA_1",toCharArray1(U1TXD_trim_1[i].msChar.get(site)));
            patternBuffer.writeStateChar(site,(U1TXD_trim_1[i].vectorNum), "NF_DQS",toCharArray1(U1TXD_trim_1[i].msChar.get(site)));
            patternBuffer.writeStateChar(site,(U1TXD_trim_1[i].vectorNum), "U0TXD",toCharArray1(U1TXD_trim_1[i].msChar.get(site)));
            }
//            patternBuffer.writeStateChar((U1TXD_trim_1[i].vectorNum), "U1TXD",U1TXD_trim_1[i].toString().toCharArray());
//            patternBuffer.writeStateChar((U1RXD_trim_1[i].vectorNum), "U1RXD",U1RXD_trim_1[i].toString().toCharArray());
//            patternBuffer.writeStateChar((NF_DATA_1_trim_1[i].vectorNum), "NF_DATA_1",NF_DATA_1_trim_1[i].toString().toCharArray());
//            patternBuffer.writeStateChar((NF_DQS_trim_1[i].vectorNum), "NF_DQS",NF_DQS_trim_1[i].toString().toCharArray());
//            patternBuffer.writeStateChar((U0TXD_trim_1[i].vectorNum), "U0TXD",U0TXD_trim_1[i].toString().toCharArray());
//*******************************************************************************************************
        }
        return drvnReturn;
    }

    public class Ptn4DDR {
        public ISetupPattern ptnSetup;
        public String srcPtnName = "";
        public String exePtnName = "";

        public String exeSpec;
        public IMeasurement measurement;
        public IDeviceSetup devSetup;
        private IPattern patMod;
        private IStateCharBuffer patternBuffer;

        public Ptn4DDR(IMeasurement iMeasurement, String exeSpecName, String ptnName) {
            exeSpec = exeSpecName;
            srcPtnName = ptnName;
            measurement = iMeasurement;
            patMod = null;
        }

        public void setupPatternCall() {

            devSetup = newDevSetup(exeSpec);
            ptnSetup = devSetup.getPatternRef(getPtnName(),srcPtnName);
            devSetup.sequentialBegin();
            devSetup.patternCall(ptnSetup);
            devSetup.sequentialEnd();
            measurement.setSetups(devSetup);

            exePtnName = ptnSetup.getName();
        }

        public IPattern getIPattern() {
            patMod = context.pattern(exePtnName);
            return patMod;

        }

        public IStateCharBuffer patternBuffer() {
            patternBuffer = context.pattern(exePtnName).createStateCharBuffer();
            return patternBuffer;

        }

        public void restoreStateChars() {
            // if (patMod != null) {
            patMod.restoreStateChars();
            // } else {
            // Msg.debug(srcPtnName + " not modified");
            // }
        }

        public void clear() {
            patternBuffer.clear();
        }

        /**
         * measurement.digInOut(toSigStr(capSignalGroup)).preserveCaptureResults();
         */

        public IDigInOutCaptureResults getDCapRsult() {
            // set primaryLabel1
            // run DIGITAL_CAPTURE_TEST() --->:Parallel
            // U0RTS,U0CTS,U0TXD,NF_DQS,NF_DATA_1,U1RXD,U1TXD
            // Frame Length in Samples : 256
            // Samples per Word : 1
            return measurement.digInOut(toSigStr(capSignalGroup)).preserveCaptureResults();
        }

        public String getPtnName() {
            String ptn = srcPtnName.substring(1 + srcPtnName.lastIndexOf(".")) + "_DDR_";
            return ptn;
        }

    }

}
