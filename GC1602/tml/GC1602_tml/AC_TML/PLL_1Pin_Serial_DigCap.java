package GC1602_tml.AC_TML;

import java.util.HashMap;
import java.util.Map;

import include.Common;
import tmlCommon.Msg;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.dsp.MultiSiteWaveLong;
import xoc.dta.datatypes.dsp.WaveLong;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDigInOutCaptureResults;
import xoc.dta.resultaccess.datatypes.BitSequence.BitOrder;
import xoc.dta.resultaccess.datatypes.MultiSiteBitSequence;
import xoc.dta.testdescriptor.IParametricTestDescriptor;

public class PLL_1Pin_Serial_DigCap extends TMLBase {

    // if (tsName.equals("PLL_SINE_DRV")) {
    //
    // sample_period_us = 4.80769230775e-9;
    // capSignal = "CLK26M_SINEOUT";
    //
    // } else if (tsName.equals("PLL_REC_BUF")) {
    //
    // sample_period_us = 4.80769230775e-9;
    // capSignal = "CLK_AUX0";
    //
    // } else {
    // Msg.printUnexpectedCaseMsg();
    // }

    // CLK_AUX0,CLK26M_SINEOUT
    @In
    public double sample_period_ns;
    @In
    public String capSignal;

    public IMeasurement measurement;

    public IParametricTestDescriptor ptd_Freq;

    @Override
    public void setup() {
        tsName = getTestSuiteName();

        if (sample_period_ns <= 0) {
            Msg.error(tsName + " -> sample_period_ns <= 0");
        }
        IDeviceSetup deviceSetup1 = newDevSetup(importSpec);

        deviceSetup1.sequentialBegin();
        deviceSetup1.patternCall(pattern_name);
        deviceSetup1.sequentialEnd();

        measurement.setSetups(deviceSetup1);

    }

    @Override
    public void update() {
        //
    }

    @Override
    public void execute() {

        measurement.execute();

        IDigInOutCaptureResults digCapture = measurement.digInOut(capSignal)
                .preserveCaptureResults();

        // get all results from all outputs
        Map<String, MultiSiteBitSequence> bitsOfAllSignal = new HashMap<String, MultiSiteBitSequence>();

        bitsOfAllSignal = digCapture.getSerialBitsAsBitSequence();

        Map<String, MultiSiteDouble> freq = new HashMap<String, MultiSiteDouble>(0);

        MultiSiteBitSequence pos_CapData2 = bitsOfAllSignal.get(capSignal);

        MultiSiteWaveLong pos_CapData = pos_CapData2.toMultiSiteWaveLong(1, BitOrder.RIGHT_TO_LEFT);// RIGHT_TO_LEFT

        releaseTester();

        freq.put(capSignal,
                Common.DSPcalculateFrequency_byFFT(pos_CapData, sample_period_ns / 1000.0));
        // freq.put(capSignal, DSPcalculateFrequency_byFFT(pos_CapData, sample_period_ns /1000.0 ));
        // freq.put(capSignal, calFreq(pos_CapData, sample_period_ns /1000.0 ));

        Msg.debug(tsName + " -> pos_CapData.size -> " + pos_CapData.getSize());
        Msg.debug(capSignal + " freq -> " + freq.get(capSignal));
        Msg.debugWaveform(pos_CapData);
        // ACTUAL FREQ is about 113.5MHz VS 64MHz expected

//******************************************************************
//      ptd_Freq.evaluate(freq);
        ptd_Freq.evaluate(freq, measurement);
      //******************************************************************
        freq.clear();

    }

    public MultiSiteDouble calFreq(MultiSiteWaveLong wf, double dt) {
        MultiSiteDouble freq = new MultiSiteDouble(-999.0);
        for (int site : context.getActiveSites()) {
            freq.set(site, calFreq(wf.get(site), dt));
        }
        return freq;
    }

    public double calFreq(WaveLong wf, double dt) {

        int size = wf.getSize();

        double count = 0;

        double vTh = 0.5;
        boolean isTrigged = false;

        for (int i = 0; i < size; i++) {

            if (isTrigged) {
                if (wf.getValue(i) < vTh) {
                    isTrigged = false;
                }
            } else {
                if (wf.getValue(i) > vTh) {
                    count++;
                    isTrigged = true;
                }
            }

        }

        double freq = count / (size * dt);
        return freq;

    }

}
