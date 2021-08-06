package tmlCommon;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import pa.TransactSeq;
import xoc.dsa.DeviceSetupFactory;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupPattern;
import xoc.dta.ParameterGroup;
import xoc.dta.ParameterGroupCollection;
import xoc.dta.TestMethod;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.MultiSiteBoolean;
import xoc.dta.datatypes.MultiSiteCharacter;
import xoc.dta.datatypes.MultiSiteLong;
import xoc.dta.testdescriptor.IParametricTestDescriptor;
import xoc.dta.workspace.IWorkspace;

public abstract class TMLBase extends TestMethod {

    /**
     * int [][] SC11223344 = { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 } };
     */
    protected int[][] SC11223344;

    /**
     * int [][] SC11112222 = { { 1, 2, 3, 4 }, { 5, 6 , 7, 8 } };
     */
    protected int[][] SC11112222;

    @Override
    public void initialize() {
        super.initialize();
        SC11223344 = getSiteControl11223344();
        SC11112222 = getSiteControl11112222();
    }

    // ################################### FIELD ######################################
    protected String specName;
    protected String tsName;
    protected IWorkspace ws;
    protected String sProjectPath;
    protected int isOfflineFlag = -999;
    // tsName = getTestSuiteName();
    // IDeviceSetup deviceSetup1 = newDevSetup(measurement.getSpecificationName());
    // ws = context.workspace();
    // sProjectPath = ws.getActiveProjectPath();
    // MultiSiteBoolean offline = context.testProgram().variables().getBoolean("SYS.OFFLINE");
    // ################################### INPUT PARAMETER ######################################
    // @In
    // public String relayList_On;
    // @In
    // public String relayList_Off;
    @In
    public String importSpec = "NA";
    @In
    public String pattern_name = "";

    // ################################### FUNC ######################################
    protected static Set<String> SortByOrder(ParameterGroupCollection<?> paraGrpColl) {
        Set<String> sortedKey = new LinkedHashSet<>();
        Set<String> key = paraGrpColl.keySet();

        for (int i = 0; i < key.size(); i++) {
            for (String _key : key) {
                try {
                    Field fieldOrder = Class.forName(paraGrpColl.get(_key).getClass().getName())
                            .getField("order");
                    int order = fieldOrder.getInt(paraGrpColl.get(_key));
                    if (order == i) {
                        sortedKey.add(paraGrpColl.get(_key).getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sortedKey;
    }

    protected String getTestSuiteName() {
        String tsName = context.getTestSuiteName();
        tsName = tsName.substring(1 + tsName.lastIndexOf("."));
        return tsName;

    }

    protected IDeviceSetup newDevSetup(String importSpec) {
        IDeviceSetup devSetup1 = DeviceSetupFactory.createInstance(context.testProgram().variables()
                .getString("dsa_path").get(context.getActiveSites()[0]));
        if (importSpec == null || importSpec.isEmpty() || importSpec.equals("NA")) {
            String tsname = getTestSuiteName();
            Msg.error(" missing timing /level spec @ " + tsname);
        } else {
            devSetup1.importSpec(importSpec);
        }
        return devSetup1;
    }

    protected IDeviceSetup newDevSetup() {
        IDeviceSetup devSetup1 = DeviceSetupFactory.createInstance(context.testProgram().variables()
                .getString("dsa_path").get(context.getActiveSites()[0]));
        return devSetup1;
    }

    /**
     * disconnect VCOM_PIN = "AWG_VOCM_IQ+DGT_PU_IQ+RX_VOCM_IQ+RX_VOCM_IQ+DGT_PU_IQ";
     */
    protected String resetVCOMPin(IDeviceSetup ds) {
        String resetVCOMPin = "resetVCOMPin";
        ds.addDcVI(VCOM_PIN).setConnect(true).setDisconnect(true).vforce("resetVCOMPin")
                .setForceValue(0).setIclamp(50*mA);// .setIclamp(50*mA);
        return resetVCOMPin;
    }

    protected boolean isOffline() {
        if (isOfflineFlag < 0) {
            MultiSiteBoolean offline = context.testProgram().variables().getBoolean("SYS.OFFLINE");
            isOfflineFlag = offline.get() ? 1 : 0;
        }
        return (isOfflineFlag == 1);
    }

    protected boolean isOnline() {
        if (isOfflineFlag < 0) {
            MultiSiteBoolean offline = context.testProgram().variables().getBoolean("SYS.OFFLINE");
            isOfflineFlag = offline.get() ? 1 : 0;
        }
        return (isOfflineFlag != 1);
    }

    /**
     * int [][] SC11223344 = { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 } };
     *
     */
    protected int[][] getSiteControl11223344() {
        int[][] sc11223344 = { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 } };
        return sc11223344;
    }

    /**
     * int [][] SC11112222 = { { 1, 2,3,4 }, { 5, 6 , 7, 8 } };
     */
    protected int[][] getSiteControl11112222() {
        int[][] sc11112222 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
        return sc11112222;
    }

    /**
     * return "sig1+sig2+sig3...."
     */
    public static String toSigStr(List<String> capSignals) {
        StringBuilder sb = new StringBuilder();
        for (String inSignal : capSignals) {
            if (sb.length() != 0) {
                sb.append(" + ");
            }
            sb.append(inSignal);
        }
        return sb.toString();
    }

    /**
     * return "sig1+sig2+sig3...."
     */
    public static String toSigStr(String[] capSignals) {
        try {
            int size = capSignals.length - 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                if (sb.length() != 0) {
                    sb.append(" + ");
                }
                sb.append(capSignals[i]);
            }
            return sb.toString();

        } catch (Exception e) {
            System.out.print("Error: list2SignalString--> maybe input capSignals null");
            e.printStackTrace();
            return "";
        }

    }

    /**
     * return "sig1+sig2+sig3...."
     */
    public static String toSigStr(String sig1, String sig2) {
        List<String> lis = Arrays.asList(sig1, sig2);
        return toSigStr(lis);
    }

    /**
     * return "sig1+sig2+sig3...."
     */
    public static String toSigStr(String sig1, String sig2, String sig3) {
        List<String> lis = Arrays.asList(sig1, sig2, sig3);
        return toSigStr(lis);
    }

    public static String toSigStr(String sig1, String sig2, String sig3, String sig4) {
        List<String> lis = Arrays.asList(sig1, sig2, sig3, sig4);
        return toSigStr(lis);
    }

    public static String toSigStr(String sig1, String sig2, String sig3, String sig4, String sig5) {
        List<String> lis = Arrays.asList(sig1, sig2, sig3, sig4, sig5);
        return toSigStr(lis);
    }

    // ============================================
    protected void PARALLELCALLTSEQANDLABEL(IDeviceSetup ds, TransactSeq trSeq, PtnSetup ptn) {

        String optionName = ptn.getOptionName();

        // ds.waitCall(1*ms);
        ds.parallelBegin("ParrellelCall_" + trSeq.trseqDef.getName() + "_and_" + optionName);
        {
            ds.sequentialBegin();
            {
                ds.transactionSequenceCall(trSeq.trseqDef.getName(), trSeq.trseqDef);
            }
            ds.sequentialEnd();

            ds.sequentialBegin();
            {
                ISetupPattern ptnRef = ptn.getRef(ds, trSeq.getExeTime());

                for (int i = 0; i < ptn.exeTimes; i++) {
                    ds.patternCall(ptnRef, optionName);
                }
            }
            ds.sequentialEnd();
        }
        ds.parallelEnd();

        trSeq = null;
    }

    protected void PARALLELCALLTSEQANDLABEL(IDeviceSetup ds, TransactSeq trSeq, String args,
            PtnSetup ptn) {

        String optionName = ptn.getOptionName();
        ds.parallelBegin("ParrellelCall_" + trSeq.trseqDef.getName() + "_and_" + optionName);
        {
            ds.sequentialBegin();
            {
                ds.transactionSequenceCall(trSeq.trseqDef.getName(), trSeq.trseqDef, args);
            }
            ds.sequentialEnd();

            ds.sequentialBegin();
            {
                ISetupPattern ptnRef = ptn.getRef(ds, trSeq.getExeTime());

                for (int i = 0; i < ptn.exeTimes; i++) {
                    ds.patternCall(ptnRef, optionName);
                }
            }
            ds.sequentialEnd();
        }
        ds.parallelEnd();

        trSeq = null;
    }

    // ============================================
    protected void parallelCallTSeqAndLabel(IDeviceSetup ds, TransactSeq trSeq, PtnSetup ptn) {

        ds.parallelBegin();
        {
            ds.sequentialBegin();
            {
                ds.transactionSequenceCall(trSeq.trseqDef.getName(), trSeq.trseqDef);
            }
            ds.sequentialEnd();

            ds.sequentialBegin();
            {
                ISetupPattern ptnRef = ptn.getRef(ds);
                for (int i = 0; i < ptn.exeTimes; i++) {
                    ds.patternCall(ptnRef);
                }
            }
            ds.sequentialEnd();
        }
        ds.parallelEnd();
    }

    protected void parallelCallTSeqAndLabel(IDeviceSetup ds, TransactSeq trSeq, String labelCall) {

        ds.parallelBegin();
        {
            ds.transactionSequenceCall(trSeq.trseqDef.getName(), trSeq.trseqDef);
            ds.patternCall(labelCall);
        }
        ds.parallelEnd();
    }

    protected void parallelCallTSeqAndLabel(IDeviceSetup ds, TransactSeq trSeq, String labelCall,
            int labelCallTimes) {

        ds.parallelBegin();
        {
            ds.transactionSequenceCall(trSeq.trseqDef.getName(), trSeq.trseqDef);
            ds.sequentialBegin();
            for (int i = 0; i < labelCallTimes; i++) {
                ds.patternCall(labelCall);
            }
            ds.sequentialEnd();
        }
        ds.parallelEnd();
    }

    protected void parallelCallTSeqAndLabel(IDeviceSetup ds, TransactSeq trSeq, String args,
            String labelCall) {

        ds.parallelBegin();
        {
            ds.transactionSequenceCall(trSeq.trseqDef.getName(), trSeq.trseqDef, args);
            ds.patternCall(labelCall);
        }
        ds.parallelEnd();
    }

    protected boolean isDebugging() {
        return messageLogLevel > 3;
    }

    // ################################### CONST ######################################

    public static final String NAStr = "NAStr";

    public static final boolean isSite1234On = true;
    public static final String enabledSite = "34";// "12"/"34"/"56"/"78"

    public static final String PinGroups = "GC1602_configuration.Groups";
    public static final String DGTpin_I = "DGT_IP";
    public static final String DGTpin_Q = "DGT_QP";

    public static final String RX_VOCM_IQ = "RX_VOCM_IQ";
    public static final String DGT_PU_IQ = "DGT_PU_IQ";
    public static final String AWG_VOCM_IQ = "AWG_VOCM_IQ";
    public static final String AWG_PU_IQ = "AWG_PU_IQ";
    public static final String VCOM_PIN = "AWG_VOCM_IQ+DGT_PU_IQ+RX_VOCM_IQ+RX_VOCM_IQ+DGT_PU_IQ";

    public static final String VCOM_AWG_BUF_I = "VCOM_AWG_BUF_I";
    public static final String VCOM_AWG_BUF_Q = "VCOM_AWG_BUF_Q";

    //
    // public static final String DGT_RLY = "KK5+KK7+KK21+KK23+KK9+KK11";

    public static final String PAD_USB20_VREXT_1P8 = "PAD_USB20_VREXT_1P8";
    public static final String USB20_DP = "USB20_DP";
    public static final String USB20_DM = "USB20_DM";
    public static final String USB20_DP_DPS = "USB20_DP_DPS";
    public static final String USB20_DM_DPS = "USB20_DM_DPS";
    public static final String REPOWER_PIN = "VDDCORE+VDDMEM+AVDD12+AVDD18+AVDD33_BT_PA+VDD1+VIO+VSD0+VSIM+AVDD33_USB20+AWG_VOCM_IQ+DGT_PU_IQ+RX_VOCM_IQ";

    public static final String gLVDSRF = "LVDSRF_RXD0N_CAL+LVDSRF_RXD0P+LVDSRF_RXD0P_CAL+LVDSRF_TXD0N_CAL+LVDSRF_TXD0P+LVDSRF_TXD0P_CAL+PAD_LVDSRF_REXT";

    public static final String QPin_LVDSRF_RX =
    "APCOUT0+APCOUT1+AWG_PU_IQ+AWG_VOCM_IQ+CLK26M_SINEOUT+CLKOUT_TEST_MPLL+CT1_M1+CT2_M1+DAC_CM_IQ+DGT_PU_IQ+EMZQ+KK17+KK18+KK19+KK20+PAD_USB20_VREXT_1P8+USB20_DM+USB20_DP+RF_Source_32417+RF_Source_32317+RF_Source_31913+RF_Source_32013+RF_Source_31917+RF_Source_32017+RX_VOCM_IQ+SCL+SDA+TEMP_CS+TEMP_DIN+TEMP_DOUT+TEMP_SCLK+VREF_DQ_CA";
    public static final String QPin_LVDSRF_TX = QPin_LVDSRF_RX;
//    APCOUT0
//    APCOUT1
//    AWG_PU_IQ
//    AWG_VOCM_IQ
//    CLK26M_SINEOUT
//    CLKOUT_TEST_MPLL
//    CT1_M1
//    CT2_M1
//    DAC_CM_IQ
//    DGT_PU_IQ
//    EMZQ
//    KK17
//    KK18
//    KK19
//    KK20
//    PAD_USB20_VREXT_1P8
//    USB20_DM
//    USB20_DP
//    RF_Source_32417
//    RF_Source_32317
//    RF_Source_31913
//    RF_Source_32013
//    RF_Source_31917
//    RF_Source_32017
//    RX_VOCM_IQ
//    SCL
//    SDA
//    TEMP_CS
//    TEMP_DIN
//    TEMP_DOUT
//    TEMP_SCLK
//    VREF_DQ_CA






    // ********************* DC Block ************************************************

    // IVforce vForce_DGT_PU_IQ = devSetup.addDcVI(DGT_PU_IQ).setConnect(true).setDisconnect(false)
    // .vforce("DGT_PU_IQ").setForceValue(0.35).setIclamp(40 * mA);

    // pattern
    protected static final String ptnDir = "vectors.";
    protected static final String ptn_FM_ADC_mode_init = "vectors.FM_ADC_mode_init"; // 5001
    protected static final String ptn_BT_RSSIADC_mode_init = "vectors.BT_RSSIADC_mode_init"; // 5001
    protected static final String ptn_BT_DAC_mode_init_FM = "vectors.BT_DAC_mode_init_FM"; // 5426
    protected static final String ptn_BT_DAC_mode_init = "vectors.BT_DAC_mode_init"; // 5426

    protected static final String ptn_BT_DAC_mode = "vectors.BT_DAC_mode"; // 145405
    protected static final String ptn_BT_DAC_mode_FM = "vectors.BT_DAC_mode_FM";// 145405
    protected static final String ptn_BT_IQADC_mode = "vectors.BT_IQADC_mode";// 131069
    protected static final String ptn_BT_RSSIADC_mode = "vectors.BT_RSSIADC_mode";// 129803
    protected static final String ptn_FM_ADC_mode_BT = "vectors.FM_ADC_mode_BT";// 134631
    protected static final String ptn_FM_ADC_mode = "vectors.FM_ADC_mode";// 134631

    protected static final String ptn_Efuse_Ohter_Pins = "vectors.NemoL_AnalogIP_EFUSE_BIST_Other_Pins";
    // public final class Msg{
    public static final int OFF = 0;
    public static final int ERROR = 1;
    public static final int WARNING = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 10;
    // }

    public static final Double s = 1.0;
    public static final Double V = 1.0;
    public static final Double A = 1.0;
    public static final Double Ohm = 1.0;

    public static final Double ms = 1e-3;
    public static final Double mV = 1e-3;
    public static final Double mA = 1e-3;
    public static final Double mOhm = 1e-3;

    public static final Double us = 1e-6;
    public static final Double uV = 1e-6;
    public static final Double uA = 1e-6;
    public static final Double uOhm = 1e-6;

    public static final Double ns = 1e-9;
    public static final Double nV = 1e-9;
    public static final Double nA = 1e-9;
    public static final Double nOhm = 1e-9;

    public static final Double kHz = 1000.0;
    public static final Double MHz = 1000000.0;

    public static final Double kSps = 1000.0;
    public static final Double MSps = 1000000.0;

    public static final Double AmpScale3dB = 1.4125375446;
    public static final Double AmpScale2p7dB = 1.357;

    // ################################### CLASS ######################################
    public class TST_NAME_ParaGroup extends ParameterGroup {
        public IParametricTestDescriptor ptd;
        public int index = 0;

        public void print() {
            System.out.print("key:" + this.getId());
            System.out.println("\ttstText:" + this.ptd.getTestText());
        }
    }

    public class RxParaGroup extends ParameterGroup {
        public String StimPin = "";
        public String MeasPin = "";
        public int ModulationType = -1;
        public double Freq = -1.0;
        public double Gain = -1.0;
        public int Channel = -1;
        public String PackageType = "";
    }

    public class TxParaGroup extends ParameterGroup {
        public String StimPin = "";
        public String MeasPin = "";
        public int ModulationType = -1;
        public double Freq = -1.0;
        public double Gain = -1.0;
        public int Channel = -1;
        public String PackageType = "";
    }

    public class RF_ParaGroup extends ParameterGroup {
        public String StimPin = "";
        public String MeasPin = "";
        public int ModulationType = -1;
        public double Freq = -1.0;
        public double Gain = -1.0;
        public int Channel = -1;
        public String PackageType = "";
        public long Samples = -1;
        public double ExpectedMaxPower = 100; // -174dBm ~ 30dBm

    }

    public class VecData {

        /**
         * vector index of lebel
         */
        public int vectorNum = -1;

        /**
         * Multi Site Char data
         */
        public MultiSiteCharacter msChar = new MultiSiteCharacter();

    }

    public char[] toCharArray(Long dVal) {
        String strVal = dVal.toString();
        int strSize = strVal.length();
        char[] charArr = new char[strSize];
        for(int i = 0 ; i < strSize; i++ ) {
            charArr[i] = strVal.charAt(i);
        }
        return charArr;
    }

    public char[] toCharArray1(Character dVal) {
        String strVal = dVal.toString();
        int strSize = strVal.length();
        char[] charArr = new char[strSize];
        for(int i = 0 ; i < strSize; i++ ) {
            charArr[i] = strVal.charAt(i);
        }
        return charArr;
    }




    public char toChar(Long dVal) {
        char charVal = dVal.toString().charAt(0);
        if (charVal == '0' || charVal == '1') {
           // Msg.info(charVal);
        } else {
            Msg.error("unexpected charVal:" + charVal);
            Msg.info("value before cvt is:" + dVal);
        }
        return charVal;
    }

    public char toChar(long dVal) {

        char charVal = (new Long(dVal)).toString().charAt(0);
        if (charVal == '0') {
            //
        } else if (charVal == '1') {
            //
        } else {
            Msg.error("unexpected charVal:" + charVal);
            Msg.info("value before cvt is:" + dVal);
        }
        return charVal;
    }

    public char toChar(int dVal) {
        return toChar((long) dVal);
    }

    public char toChar(int site, MultiSiteLong dVal) {

        return toChar(dVal.get(site));

        // return dVal.get(site).toString().charAt(0);
    }

    public MultiSiteCharacter toChar(int[] activeSite, MultiSiteLong dVal) {
        MultiSiteCharacter state = new MultiSiteCharacter();
        for (int site : activeSite) {
            state.set(site, toChar(site, dVal));
        }
        return state;
    }

}
