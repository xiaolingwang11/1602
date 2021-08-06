package GC1602_tml.AC_TML;

import java.util.Arrays;
import java.util.List;

import tmlCommon.Msg;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dta.ParameterGroupCollection;
import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.MultiSiteLong;
import xoc.dta.datatypes.MultiSiteLongArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDigInOutCaptureResults;

public class RingOSC_FDMA extends TMLBase {

    public IMeasurement measurement;

    public List<String> capSignalGroup = //
            Arrays.asList("U1TXD", "U1RXD", "NF_DATA_1", "NF_DQS", "U0TXD", "U0RXD", "U0CTS",  "U0RTS");

    public ParameterGroupCollection<TST_NAME_ParaGroup> tsValidColl = new ParameterGroupCollection<>();
    public ParameterGroupCollection<TST_NAME_ParaGroup> tsFreqColl = new ParameterGroupCollection<>();
    public ParameterGroupCollection<TST_NAME_ParaGroup> tsCntColl = new ParameterGroupCollection<>();

    public class RingOSCResult{
        public String tstObject;
        public MultiSiteLong valid;
        public MultiSiteLong cnt;
        public MultiSiteDouble freq;
    }

    @Override
    public void setup() {

        tsName = getTestSuiteName();
        IDeviceSetup ds = newDevSetup(importSpec);

        ds.sequentialBegin();
        ds.patternCall(pattern_name);
        ds.sequentialEnd();

        measurement.setSetups(ds);

    }

    @Override
    public void execute() {

        measurement.execute();

        // NemoL_AnalogIP_ROSC_scc_all_chain_avc1_AA_20191205
        // Vector Variable Pins U1TXD,U1RXD,NF_DATA_1,NF_DQS,U0TXD,U0RXD,U0CTS,U0RTS
        // Transfer Mode Parallel
        // Frame Length in Samples 64
        // Frame Count 1
        // Samples per Word 1
        // Defined Capture Pins    U1TXD,U1RXD,NF_DATA_1,NF_DQS,U0TXD,U0RXD,U0CTS,U0RTS
        // --------------------------------------------
        // there 16 capture block and per block has 4 capture vectors
        // and there are 16 different capture verable in smart7 setting
        // smart7 VECTOR(VecVar_list[i]).getVectors().size() = 64

        IDigInOutCaptureResults digCapture1;
        digCapture1 = measurement.digInOut(toSigStr(capSignalGroup)).preserveCaptureResults();


        // resultWave1
        MultiSiteLongArray iCapData = digCapture1.getParallelBitsAsLongArray(capSignalGroup);

        // -------------------- tmp code for debug
        // TODO: do i need to parse the bit this way ?
        // long val = iCapData.get(1)[0];
        // long bit = (val & 0b0010) >> 1;

        int capCnt = iCapData.length() / 4; // captured 16 times

        releaseTester();


        if (tsValidColl.size() != capCnt || tsValidColl.size() !=  tsFreqColl.size() ||tsFreqColl.size() != tsCntColl.size()) {
            Msg.error(" unexpected limit setting count");
        }


        for (int k = 0; k < capCnt; k++) {

            RingOSCResult rslt  = new RingOSCResult();

            rslt.tstObject = "SCC_ROSC_Z" + (k/2 + 1) + ((k % 2 == 0) ? "_Y0_X0" : "_Y0_X1");

            MultiSiteLong val4k0 = processCapData(iCapData.getElement(k * 4 + 0));
            MultiSiteLong val4k1 = processCapData(iCapData.getElement(k * 4 + 1));
            MultiSiteLong val4k2 = processCapData(iCapData.getElement(k * 4 + 2));
            MultiSiteLong val4k3 = processCapData(iCapData.getElement(k * 4 + 3));

            iCapData.setElement(k * 4 + 0, val4k0);
            iCapData.setElement(k * 4 + 1, val4k1);
            iCapData.setElement(k * 4 + 2, val4k2);
            iCapData.setElement(k * 4 + 3, val4k3);

            rslt.valid = val4k0.and(0x40).rightShift(6);
            rslt.cnt = val4k1.leftShift(16).add(val4k2.leftShift(8).add(val4k3.and(0xFFFFF)));
            rslt.freq = rslt.cnt.divide(39.3846);

            tsValidColl.get(rslt.tstObject).ptd.evaluate(rslt.valid);
            tsFreqColl.get(rslt.tstObject).ptd.evaluate(rslt.freq);
            tsCntColl.get(rslt.tstObject).ptd.evaluate(rslt.cnt);

        }

    } // execute()



    public MultiSiteLong processCapData(MultiSiteLong tmpData) {

        MultiSiteLong valB7 = tmpData.and(0x1).leftShift(7);
        MultiSiteLong valB6 = tmpData.rightShift(1).and(0x1).leftShift(6);
        MultiSiteLong valB5 = tmpData.rightShift(2).and(0x1).leftShift(5);
        MultiSiteLong valB4 = tmpData.rightShift(3).and(0x1).leftShift(4);
        MultiSiteLong valB3 = tmpData.rightShift(4).and(0x1).leftShift(3);
        MultiSiteLong valB2 = tmpData.rightShift(5).and(0x1).leftShift(2);
        MultiSiteLong valB1 = tmpData.rightShift(6).and(0x1).leftShift(1);
        MultiSiteLong valB0 = tmpData.rightShift(7).and(0x1);

        return sum(valB7, valB6, valB5, valB4, valB3, valB2, valB1, valB0);

    }

    public MultiSiteLong sum(MultiSiteLong v1, MultiSiteLong v2, MultiSiteLong v3, MultiSiteLong v4,
            MultiSiteLong v5, MultiSiteLong v6, MultiSiteLong v7, MultiSiteLong v8) {
        return v1.add(v2.add(v3.add(v4.add(v5.add(v6.add(v7.add(v8)))))));
    }

    public MultiSiteLong sum(MultiSiteLong v1, MultiSiteLong v2, MultiSiteLong v3) {
        return v1.add(v2.add(v3));
    }

}
