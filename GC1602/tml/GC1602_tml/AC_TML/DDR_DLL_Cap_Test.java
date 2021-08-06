package GC1602_tml.AC_TML;

import java.util.Arrays;
import java.util.List;

import tmlCommon.Msg;
import tmlCommon.TMLBase;
import xoc.dta.datatypes.MultiSiteBoolean;
import xoc.dta.datatypes.MultiSiteLongArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDigInOutCaptureResults;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;

public class DDR_DLL_Cap_Test extends TMLBase {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_DDR_DLL;

    @Override
    public void setup() {
        tsName = getTestSuiteName();
    }

    // Vector Variable Pins; frame length in samples 17; samples per word
    public List<String> capSignalGroup = Arrays.asList("U0RTS", "U0CTS", "U0RXD", "U0TXD", "NF_DQS",
            "NF_DATA_1", "U1RXD", "U1TXD");

    @Override
    public void execute() {
        Msg.blankLine(2);
        Msg.info(tsName);
        measurement.execute();

        IDigInOutCaptureResults digCapture = measurement
                .digInOut(toSigStr(capSignalGroup))
                .preserveCaptureResults();
        MultiSiteLongArray iCapData = digCapture.getParallelBitsAsLongArray(capSignalGroup);

        if (iCapData.get(context.getActiveSites()[0]).length != 17) {
            Msg.error(tsName + ":iCapData.get(0).length != 17");
        }

        releaseTester();

        MultiSiteBoolean ddr_dll = new MultiSiteBoolean();
        int[] activeSite = context.getActiveSites();
        for (int site : activeSite) {

            if ((iCapData.getElement(site, 0) == 245) && (iCapData.getElement(site, 1) == 127)
                    && (iCapData.getElement(site, 2) == 255)
                    && (iCapData.getElement(site, 3) == 127)
                    && (iCapData.getElement(site, 4) == 127)
                    && (iCapData.getElement(site, 5) == 255)
                    && (iCapData.getElement(site, 6) == 135)
                    && (iCapData.getElement(site, 7) == 253) && (iCapData.getElement(site, 8) == 0)
                    && (iCapData.getElement(site, 9) == 0) && (iCapData.getElement(site, 10) == 0)
                    && (iCapData.getElement(site, 11) == 3) && (iCapData.getElement(site, 12) == 0)
                    && (iCapData.getElement(site, 13) == 0) && (iCapData.getElement(site, 14) == 0)
                    && (iCapData.getElement(site, 15) == 3)
                    && (iCapData.getElement(site, 16) == 170)) {
                ddr_dll.set(site, true);
            }

        }
        ptd_DDR_DLL.evaluate(ddr_dll);
    }
}
