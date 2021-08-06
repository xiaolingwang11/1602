package GC1602_tml.AC_TML;

import java.util.Arrays;
import java.util.List;

import tmlCommon.Msg;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dta.datatypes.MultiSiteLong;
import xoc.dta.datatypes.MultiSiteLongArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDigInOutCaptureResults;
import xoc.dta.testdescriptor.IParametricTestDescriptor;

public class PLL_BIST_CNT extends TMLBase {

    public boolean debug_on = true;

    public IMeasurement measurement;

    public IParametricTestDescriptor ptd_itwpll_cap;
    public IParametricTestDescriptor ptd_ilpll_cap;
    public IParametricTestDescriptor ptd_irpll_cap;
    public IParametricTestDescriptor ptd_impll_cap;
    public IParametricTestDescriptor ptd_idpll0_cap;
    public IParametricTestDescriptor ptd_im_lvdsrf_cap;

    public String capSignals = "U1TXD+U1RXD+NF_DATA_1+NF_DQS+U0TXD+U0RXD+U0CTS+U0RTS";

    private List<String> capSignalGroup = Arrays.asList("U1TXD", "U1RXD", "NF_DATA_1", "NF_DQS",
            "U0TXD", "U0RXD", "U0CTS", "U0RTS");

    @Override
    public void setup() {
        tsName = getTestSuiteName();
        capSignalGroup = Arrays.asList(capSignals.split("\\+"));
        IDeviceSetup deviceSetup1 = newDevSetup(importSpec);

        deviceSetup1.sequentialBegin();
        deviceSetup1.waitCall("5 ms");
        deviceSetup1.patternCall(pattern_name);
        deviceSetup1.sequentialEnd();

        measurement.setSetups(deviceSetup1);

    }

    @Override
    public void update() {
        // add code here
    }

    @Override
    public void execute() {

        measurement.execute();
        Msg.blankLine(2);
        Msg.info(tsName);
        IDigInOutCaptureResults digCapture = measurement.digInOut(capSignals)
                .preserveCaptureResults();

        MultiSiteLongArray msCapData = digCapture.getParallelBitsAsLongArray(capSignalGroup);

        releaseTester();

        long initVal = -999999;
        MultiSiteLong itwpll_cap = new MultiSiteLong(initVal);
        MultiSiteLong ilpll_cap = new MultiSiteLong(initVal);
        MultiSiteLong irpll_cap = new MultiSiteLong(initVal);
        MultiSiteLong impll_cap = new MultiSiteLong(initVal);
        MultiSiteLong idpll_cap = new MultiSiteLong(initVal);
        MultiSiteLong imlvdsrf_cap = new MultiSiteLong(initVal);

        for (int site : context.getActiveSites()) {
            long[] iCapData = msCapData.get(site);
            long _itwpll_cap = ((iCapData[2] << 8) + iCapData[3]) & 0xFFFF;
            long _ilpll_cap = ((iCapData[6] << 8) + iCapData[7]) & 0xFFFF;
            long _irpll_cap = ((iCapData[10] << 8) + iCapData[11]) & 0xFFFF;
            long _impll_cap = ((iCapData[14] << 8) + iCapData[15]) & 0xFFFF;
            long _idpll_cap = ((iCapData[17] << 14) + (iCapData[18] << 6) + (iCapData[19] >> 2))
                    & 0xFFFF;
            long _im_lvdsrf_cap = ((iCapData[22] << 8) + iCapData[23]) & 0xFFFF;
            itwpll_cap.set(site, _itwpll_cap);
            ilpll_cap.set(site, _ilpll_cap);
            irpll_cap.set(site, _irpll_cap);
            impll_cap.set(site, _impll_cap);
            idpll_cap.set(site, _idpll_cap);
            imlvdsrf_cap.set(site, _im_lvdsrf_cap);
        }

        Msg.debug("itwpll_cap" + " " + itwpll_cap);
        Msg.debug("ilpll_cap" + " " + ilpll_cap);
        Msg.debug("irpll_cap" + " " + irpll_cap);
        Msg.debug("impll_cap" + " " + impll_cap);
        Msg.debug("idpll_cap" + " " + idpll_cap);
        Msg.debug("imlvdsrf_cap" + " " + imlvdsrf_cap);

        ptd_itwpll_cap.evaluate(itwpll_cap);
        ptd_ilpll_cap.evaluate(ilpll_cap);
        ptd_irpll_cap.evaluate(irpll_cap);
        ptd_impll_cap.evaluate(impll_cap);
        ptd_idpll0_cap.evaluate(idpll_cap);
        ptd_im_lvdsrf_cap.evaluate(imlvdsrf_cap);

    }

    public void executeBak() {

        measurement.execute();

        IDigInOutCaptureResults digCapture = measurement.digInOut(capSignals)
                .preserveCaptureResults();

        //if (Common_Lib_Folder.setup_Folder.RF_UserCommon.hiddenUpload()) {
            releaseTester();
        //}

        MultiSiteLongArray iCapData = digCapture.getParallelBitsAsLongArray(capSignalGroup);

        for (int j = 0; j < iCapData.length(); j++) {
            Msg.debug("Cap_Data " + j + " " + iCapData.getElement(j));
        }

        MultiSiteLong itwpll_cap = (((iCapData.getElement(2).leftShift(8))
                .add(iCapData.getElement(3))).and(0xFFFF));
        MultiSiteLong ilpll_cap = (((iCapData.getElement(6).leftShift(8))
                .add(iCapData.getElement(7))).and(0xFFFF));
        MultiSiteLong irpll_cap = (((iCapData.getElement(10).leftShift(8))
                .add(iCapData.getElement(11))).and(0xFFFF));
        MultiSiteLong impll_cap = (((iCapData.getElement(14).leftShift(8))
                .add(iCapData.getElement(15))).and(0xFFFF));
        MultiSiteLong idpll_cap = (((iCapData.getElement(17).leftShift(14))
                .add(iCapData.getElement(18).leftShift(6)))
                        .add(iCapData.getElement(19).rightShift(2))).and(0xFFFF);
        MultiSiteLong imlvdsrf_cap = (((iCapData.getElement(22).leftShift(8))
                .add(iCapData.getElement(23))).and(0xFFFF));

        Msg.debug("itwpll_cap" + " " + itwpll_cap);
        Msg.debug("ilpll_cap" + " " + ilpll_cap);
        Msg.debug("irpll_cap" + " " + irpll_cap);
        Msg.debug("impll_cap" + " " + impll_cap);
        Msg.debug("idpll_cap" + " " + idpll_cap);
        Msg.debug("imlvdsrf_cap" + " " + imlvdsrf_cap);

        ptd_itwpll_cap.evaluate(itwpll_cap);
        ptd_ilpll_cap.evaluate(ilpll_cap);
        ptd_irpll_cap.evaluate(irpll_cap);
        ptd_impll_cap.evaluate(impll_cap);
        ptd_idpll0_cap.evaluate(idpll_cap);
        ptd_im_lvdsrf_cap.evaluate(imlvdsrf_cap);

    }

}
