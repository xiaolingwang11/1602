package common_Code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.complex.Complex;

import xoc.dsa.ISetupDigitizer;
import xoc.dsa.ISetupDigitizer.IMeasureWaveform.SetupInputImpedance;
import xoc.dsa.ISetupRfMeas;
import xoc.dsa.ISetupRfMeas.IModPower;
import xoc.dsa.ISetupRfMeas.IModPower.SetupLoSide;
import xoc.dsa.ISetupRfMeas.IModPower.SetupWindowFunction;
import xoc.dta.datatypes.MultiSiteBoolean;
import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.datatypes.MultiSiteLong;
import xoc.dta.datatypes.MultiSiteLongArray;
import xoc.dta.datatypes.MultiSiteString;
import xoc.dta.datatypes.dsp.MultiSiteLoSide;
import xoc.dta.datatypes.dsp.MultiSiteSine;
import xoc.dta.datatypes.dsp.MultiSiteSpectrum;
import xoc.dta.datatypes.dsp.MultiSiteSpectrumComplex;
import xoc.dta.datatypes.dsp.MultiSiteWaveComplex;
import xoc.dta.datatypes.dsp.MultiSiteWaveDouble;
import xoc.dta.datatypes.dsp.MultiSiteWaveLong;
import xoc.dta.datatypes.dsp.SpectrumUnit;
import xoc.dta.datatypes.dsp.WaveComplex;
import xoc.dta.datatypes.dsp.WaveDouble;
import xoc.dta.datatypes.dsp.WaveformFileType;
import xoc.dta.datatypes.dsp.WindowFunction;
import xoc.dta.datatypes.dsp.WindowScaling;
import xoc.dta.datatypes.dsp.demod.Demodulation;
import xoc.dta.resultaccess.datatypes.BitSequence;
import xoc.dta.resultaccess.datatypes.MultiSiteBitSequence;

public class UserCommon {

    private static final boolean hiddenUpload = true;

    public static boolean hiddenupload() {
        return hiddenUpload;
    }

    public static final boolean hasgeneratepara = true; // define testmethod
    public static final boolean debug_flag = false; // define testmethod

    public static MultiSiteBoolean specsearch_flag = new MultiSiteBoolean(false);// define testflow

    public static MultiSiteBoolean stop_flag = new MultiSiteBoolean(true);// define testflow

    // private static final Double Path_Loss=0.0;
    // private static final MultiSiteDouble Path_Loss=new MultiSiteDouble();
    // private static final boolean foregroundUpload = false;

    // public static boolean doforegroundupload() {
    // return foregroundUpload;
    // }
    public static MultiSiteDouble Path_Loss(String mode, String testFrequency, String TX_RX) {

        MultiSiteDouble Path_Loss = new MultiSiteDouble(0.0);
        MultiSiteDouble RX_Path_Loss_2402 = new MultiSiteDouble(0.0);
        MultiSiteDouble RX_Path_Loss_2441 = new MultiSiteDouble(0.0);
        MultiSiteDouble RX_Path_Loss_2480 = new MultiSiteDouble(0.0);

        MultiSiteDouble TX_Path_Loss_2402 = new MultiSiteDouble(0.0);
        MultiSiteDouble TX_Path_Loss_2441 = new MultiSiteDouble(0.0);
        MultiSiteDouble TX_Path_Loss_2480 = new MultiSiteDouble(0.0);

        MultiSiteDouble RX_Path_Loss_FM = new MultiSiteDouble(0.0);
        MultiSiteDouble RX_Path_Loss_GPS = new MultiSiteDouble(0.0);

        MultiSiteDouble TX_BT_Cal_2441 = new MultiSiteDouble(0.0);
        MultiSiteDouble TX_WLAN_Cal_2441 = new MultiSiteDouble(0.0);

        // ***************old LB****************
        double RX_Path_Loss_2402_old[] = { 0.0, 0.0, 0.0, 0.0 };
        double RX_Path_Loss_2441_old[] = { 0.5, 0.5, 0.5, 0.5 };
        double RX_Path_Loss_2480_old[] = { 0.2, 0.2, 0.2, 0.2 };

        double TX_Path_Loss_2402_old[] = { 0.3, 0.3, 0.3, 0.3 };
        double TX_Path_Loss_2441_old[] = { 0.3, 0.3, 0.3, 0.3 };
        double TX_Path_Loss_2480_old[] = { 0.3, 0.3, 0.3, 0.3 };
        // double RX_Path_Loss_FM_old[] = {0.75,0.75,0.75,0.0}; 20180210
        double RX_Path_Loss_FM_old[] = { 0.0, 0.0, 0.0, 0.0 };
        double RX_Path_Loss_GPS_old[] = { 0.0, 0.0, 0.0, 0.0 };

        // double TX_BT_Cal_2441_old[] = {0.95,0.95,0.95,0.95};//multibin AD old lb 20180425
        double TX_BT_Cal_2441_old[] = { 0.75, 0.75, 0.75, 0.75 };// multibin AD

        // double TX_BT_Cal_2441_old[] = {-0.85,-0.85,-0.85,-0.85};//single bin AD

        // double TX_BT_Cal_2441_old[] = {-0.86,-0.817,-0.72,-0.6};//reference old lb site 3

        // double TX_BT_Cal_2441_old[] = -0.95,-0.95,-0.95,-0.7}:-0.6,-0.6,-0.5,-0.5};//reference
        // old lb site 3 20180210
        // double TX_BT_Cal_2441_old[] =
        // {-0.75,-0.75,-0.75,-0.75};//{-1.05,-1.05,-1.05,-0.8};//reference old lb site 3 20180210
        // double TX_BT_Cal_2441_old[] =
        // {-0.0,-0.0,-0.0,-0.0};//{-1.05,-1.05,-1.05,-0.8};//reference old lb site 3 20180210
        // double TX_WLAN_Cal_2441_old[] = {22.4,22.4,22.4,24.4};//AD

        double TX_WLAN_Cal_2441_old[] = { 22.4, 22.4, 22.4, 22.4 };// AD
        // double TX_WLAN_Cal_2441_old[] = {25.4,25.4,25.4,25.4};

        // ***************new LB****************
        double RX_Path_Loss_2402_new[] = { 0.0, 0.0, 0.0, 0.0 };
        double RX_Path_Loss_2441_new[] = { 0.5, 0.5, 0.5, 0.5 };
        double RX_Path_Loss_2480_new[] = { 0.2, 0.2, 0.2, 0.2 };

        double TX_Path_Loss_2402_new[] = { 0.3, 0.3, 0.3, 0.3 };
        double TX_Path_Loss_2441_new[] = { 0.3, 0.3, 0.3, 0.3 };
        double TX_Path_Loss_2480_new[] = { 0.3, 0.3, 0.3, 0.3 };
        // double RX_Path_Loss_FM_new[] = {0.75,0.75,0.75,0.0}; 20180210
        double RX_Path_Loss_FM_new[] = { 0.0, 0.0, 0.0, 0.0 };
        double RX_Path_Loss_GPS_new[] = { 0.0, 0.0, 0.0, 0.0 };

        // double TX_BT_Cal_2441_new[] = {1.05,1.05,1.05,1.05};//multibin //AD
        double TX_BT_Cal_2441_new[] = { -0.55, -0.55, -0.55, -0.55 };// single bin //AD

        // double TX_BT_Cal_2441_new[] = {-0.86,-0.817,-0.72,-0.6};//AC reference old lb site 3

        // double TX_BT_Cal_2441_new[] ={-0.75,-0.717,-0.62,-0.5}; // AC reference old lb site 3
        // 20180210

        // double TX_WLAN_Cal_2441_new[] = {22.4,22.4,22.4,24.4}; //AC new LB

        double TX_WLAN_Cal_2441_new[] = { 26.4, 25.4, 27.4, 29.4 }; // new LB AD DI 20180425 offset

        // double TX_WLAN_Cal_2441_new[] = {25.4,25.4,25.4,25.4}; //new LB AD

        // double TX_WLAN_Cal_2441_new[] = {27.4,26.4,29.4,28.4}; //new LB AD zefeng lb 20180425
        // offset

        switch (common_Code.LB_Switch.LBMode) {
        case old_LB: {
            if (UserCommon.debug_flag) {
                System.out.println("========================old_lb===========================");
            }

            RX_Path_Loss_2402.set(RX_Path_Loss_2402_old);
            RX_Path_Loss_2441.set(RX_Path_Loss_2441_old);
            RX_Path_Loss_2480.set(RX_Path_Loss_2480_old);

            TX_Path_Loss_2402.set(TX_Path_Loss_2402_old);
            TX_Path_Loss_2441.set(TX_Path_Loss_2441_old);
            TX_Path_Loss_2480.set(TX_Path_Loss_2480_old);

            RX_Path_Loss_FM.set(RX_Path_Loss_FM_old);
            RX_Path_Loss_GPS.set(RX_Path_Loss_GPS_old);

            TX_BT_Cal_2441.set(TX_BT_Cal_2441_old);
            TX_WLAN_Cal_2441.set(TX_WLAN_Cal_2441_old);

            break;
        }

        case new_LB: {
            if (UserCommon.debug_flag) {
                System.out.println("========================new_lb===========================");
            }

            RX_Path_Loss_2402.set(RX_Path_Loss_2402_new);
            RX_Path_Loss_2441.set(RX_Path_Loss_2441_new);
            RX_Path_Loss_2480.set(RX_Path_Loss_2480_new);

            TX_Path_Loss_2402.set(TX_Path_Loss_2402_new); // 2402 2412
            TX_Path_Loss_2441.set(TX_Path_Loss_2441_new);
            TX_Path_Loss_2480.set(TX_Path_Loss_2480_new); // 2480 2484

            RX_Path_Loss_FM.set(RX_Path_Loss_FM_new);
            RX_Path_Loss_GPS.set(RX_Path_Loss_GPS_new);

            TX_BT_Cal_2441.set(TX_BT_Cal_2441_new);
            TX_WLAN_Cal_2441.set(TX_WLAN_Cal_2441_new);

            break;
        }
        default:
            throw new RuntimeException("Unexpected LB Switch Mode!");
        }

        if (mode.equals("WLAN") || mode.equals("BT")) {
            if (TX_RX.equals("TX_WLAN_CAL")) {
                if (testFrequency.equals("2441")) {
                    Path_Loss.set(TX_WLAN_Cal_2441);
                }

                else {
                    // cerr << "Invalid testFrequency" << endl;
                }
            }

            if (TX_RX.equals("TX_BT_CAL")) {
                if (testFrequency.equals("2441")) {

                    Path_Loss.set(TX_BT_Cal_2441);

                }

                else {
                    // cerr << "Invalid testFrequency" << endl;
                }
            }

            if (TX_RX.equals("TX")) {
                if (testFrequency.equals("2402") || testFrequency.equals("2412")) {
                    Path_Loss.set(TX_Path_Loss_2402);
                } else if (testFrequency.equals("2441")) {
                    Path_Loss.set(TX_Path_Loss_2441);
                } else if (testFrequency.equals("2480") || testFrequency.equals("2484")) {
                    Path_Loss.set(TX_Path_Loss_2480);
                } else {
                    // cerr << "Invalid testFrequency" << endl;
                }
            } else if (TX_RX.equals("RX")) {
                if (testFrequency.equals("2402") || testFrequency.equals("2412")) {
                    Path_Loss.set(RX_Path_Loss_2402);
                } else if (testFrequency.equals("2441")) {
                    Path_Loss.set(RX_Path_Loss_2441);
                } else if (testFrequency.equals("2480") || testFrequency.equals("2484")) {
                    Path_Loss.set(RX_Path_Loss_2480);
                } else {
                    // cerr << "Invalid testFrequency" << endl;
                }
            }
        }

        else if (mode.equals("GPS")) {
            Path_Loss.set(RX_Path_Loss_GPS);
        } else if (mode.equals("FM")) {
            Path_Loss.set(RX_Path_Loss_FM);
        } else {
            // cerr << "Invalid Input Parameters mode!!!!!" << endl;
        }
        return Path_Loss;
    }

    public static int Caculate_DeltaGain(int gain_reg) {
        int Gain_delta;
        int[] RX_LNA_mapping = { 48, 45, 42, 36, 30, 24, 18, 6 };
        int[] RX_VGA_mapping = { 13, 11, 9, 7, 5, 3, 1, -1, -3, -5, -7, -9, -11, -13, -13, -13 };

        int LNA_gain_reg = (gain_reg >> 12) & 0x7;
        int VGA_gain_reg = (gain_reg >> 8) & 0xf;

        Gain_delta = RX_LNA_mapping[0] + RX_VGA_mapping[0] - RX_LNA_mapping[LNA_gain_reg] - RX_VGA_mapping[VGA_gain_reg];

        return (Gain_delta);
    }

    public static MultiSiteDouble RX_IQ_PowerDsp(MultiSiteWaveComplex capWave, Double freqOut,
            int debugLevel) {
        MultiSiteDouble iqPower = new MultiSiteDouble();

        long sampleSize = capWave.getSize().get();
        double sampleRate = capWave.getSampleRate().get();

        int stdBin = (int) (sampleSize * freqOut / sampleRate + sampleSize / 2 - 1);

        MultiSiteSpectrum spect = capWave.spectrum(SpectrumUnit.dBm);

        iqPower = spect.getValue(stdBin);
        if (debugLevel >= 2) {
            spect.plot("spectrum", "rxIIP_Cal");
            System.out.println("rxIIP_Cal sample size = " + sampleSize);
            System.out.println("rxIIP_Cal sample rate = " + sampleRate);
            System.out.println("rxIIP_Cal std bin = " + stdBin);
            System.out.println("rxIIP_Cal iq power = " + iqPower);
        }

        return (iqPower);
    }

    public static MultiSiteDouble[] RX_IQ_GainDsp(MultiSiteWaveComplex capWave, Double freqOut,
            Double inputPower, int getPowerFlag, int debugLevel) {
        MultiSiteDouble idBm = new MultiSiteDouble();
        MultiSiteDouble qdBm = new MultiSiteDouble();
        MultiSiteDouble[] iqdBm = new MultiSiteDouble[2];

        long sampleSize = capWave.getSize().get();
        double sampleRate = capWave.getSampleRate().get();
        int stdBin = (int) (sampleSize * freqOut / sampleRate + 0.5);

        MultiSiteSpectrum iABS = capWave.getReal().setWindowFunction(WindowFunction.HANNING)
                .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.V);
        MultiSiteSpectrum qABS = capWave.getImaginary().setWindowFunction(WindowFunction.HANNING)
                .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.V);

        if (stdBin < (sampleSize / 2)) {
            MultiSiteDouble idBm_S = iABS.getValue(stdBin).pow(2);
            MultiSiteDouble idBm_L = iABS.getValue(stdBin - 1).pow(2);
            MultiSiteDouble idBm_H = iABS.getValue(stdBin + 1).pow(2);

            idBm = idBm_S.add(idBm_L).add(idBm_H).log10().multiply(10).add(10);

            MultiSiteDouble qdBm_S = qABS.getValue(stdBin).pow(2);
            MultiSiteDouble qdBm_L = qABS.getValue(stdBin - 1).pow(2);
            MultiSiteDouble qdBm_H = qABS.getValue(stdBin + 1).pow(2);

            qdBm = qdBm_S.add(qdBm_L).add(qdBm_H).log10().multiply(10).add(10);

        } else {
            idBm.set(999);
            qdBm.set(999);
        }
        if (getPowerFlag == 0) {
            idBm = idBm.subtract(inputPower);
            qdBm = qdBm.subtract(inputPower);
        }

        iqdBm[0] = idBm;
        iqdBm[1] = qdBm;

        return (iqdBm);
    }

    public static MultiSiteDouble[] RX_IQ_Complex_GainDsp(MultiSiteWaveComplex capWave,
            Double freqOut, Double inputPower, int getPowerFlag, int debugLevel) {
        MultiSiteDouble[] iqdBm = new MultiSiteDouble[3];
        MultiSiteDouble cpxDBm = new MultiSiteDouble();
        MultiSiteDouble idBm = new MultiSiteDouble();
        MultiSiteDouble qdBm = new MultiSiteDouble();

        long sampleSize = capWave.getSize().get();
        double sampleRate = capWave.getSampleRate().get();

        int stdBin = (int) (sampleSize - (sampleSize * freqOut / sampleRate) + 0.5);
        int stdBinIQ = (int) (sampleSize * freqOut / sampleRate);

        if (debugLevel >= 2) {
            System.out.println("input power is " + inputPower);
            System.out.println("freqOut is " + freqOut);
            System.out.println("sampleRate is " + sampleRate);
            System.out.println("sampleSize is " + sampleSize);
            System.out.println("stdBin is " + stdBin);
            System.out.println("stdBinIQ is " + stdBinIQ);
        }

        // MultiSiteSpectrumComplex spec_complex=
        // capWave.setWindowFunction(WindowFunction.HANNING).setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).fft();
        // MultiSiteSpectrumComplex spec_I =
        // capWave.getReal().setWindowFunction(WindowFunction.HANNING).fft();
        // MultiSiteSpectrumComplex spec_Q =
        // capWave.getImaginary().setWindowFunction(WindowFunction.HANNING).fft();

        MultiSiteSpectrum cpxABS = capWave.setWindowFunction(WindowFunction.HANNING)
                .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).fft().abs();

        MultiSiteDouble cpxDBm_S = cpxABS.getValue(stdBin).pow(2);
        MultiSiteDouble cpxDBm_L = cpxABS.getValue(stdBin - 1).pow(2);
        MultiSiteDouble cpxDBm_H = cpxABS.getValue(stdBin + 1).pow(2);
        cpxDBm = cpxDBm_S.add(cpxDBm_L).add(cpxDBm_H).log10().multiply(10).add(10);

        if (stdBinIQ < (sampleSize / 2)) {
            // MultiSiteSpectrum iABS =
            // capWave.getReal().setWindowFunction(WindowFunction.HANNING).setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).fft().abs();
            // MultiSiteSpectrum qABS =
            // capWave.getImaginary().setWindowFunction(WindowFunction.HANNING).setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).fft().abs();
            MultiSiteSpectrum iABS = capWave.getReal().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.V);
            MultiSiteSpectrum qABS = capWave.getImaginary()
                    .setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.V);

            MultiSiteDouble idBm_S = iABS.getValue(stdBinIQ).pow(2);
            MultiSiteDouble idBm_L = iABS.getValue(stdBinIQ - 1).pow(2);
            MultiSiteDouble idBm_H = iABS.getValue(stdBinIQ + 1).pow(2);
            idBm = idBm_S.add(idBm_L).add(idBm_H).log10().multiply(10).add(10);
            // idBm =
            // (idBm_S.add(idBm_L).add(idBm_H)).divide(100).multiply(1000).log10().multiply(10);

            MultiSiteDouble qdBm_S = qABS.getValue(stdBinIQ).pow(2);
            MultiSiteDouble qdBm_L = qABS.getValue(stdBinIQ - 1).pow(2);
            MultiSiteDouble qdBm_H = qABS.getValue(stdBinIQ + 1).pow(2);
            qdBm = qdBm_S.add(qdBm_L).add(qdBm_H).log10().multiply(10).add(10);
            // qdBm =
            // (qdBm_S.add(qdBm_L).add(qdBm_H)).divide(100).multiply(1000).log10().multiply(10);

        } else {
            idBm.set(999);
            qdBm.set(999);
        }
        if (getPowerFlag == 0) // Gain and not only Power
        {
            cpxDBm = cpxDBm.subtract(inputPower);
            idBm = idBm.subtract(inputPower);
            qdBm = qdBm.subtract(inputPower);
        }
        iqdBm[0] = cpxDBm;
        iqdBm[1] = idBm;
        iqdBm[2] = qdBm;

        if (debugLevel >= 2) {
            System.out.println("idqBm_cpx is " + cpxDBm);
            System.out.println("idqBm_i is " + idBm);
            System.out.println("idqBm_q is " + qdBm);
        }

        return (iqdBm);
    }

    public static MultiSiteWaveComplex removeingDC_RF(MultiSiteWaveComplex inData) {

        MultiSiteWaveDouble temp_data_I = new MultiSiteWaveDouble();
        MultiSiteWaveDouble temp_data_Q = new MultiSiteWaveDouble();
        MultiSiteLoSide loSide = inData.getLoSide();// SMT8.2.5
        // MultiSiteDouble real_mean, image_mean;

        // real_mean = inData.getReal().mean();
        // image_mean = inData.getImaginary().mean();

        temp_data_I = (inData.getReal().subtract(inData.getReal().mean())).multiply(-1);
        temp_data_Q = (inData.getImaginary().subtract(inData.getImaginary().mean())).multiply(-1);

        // out_data = new MultiSiteWaveComplex(temp_data_I, temp_data_Q);

        inData.setArray(new MultiSiteWaveComplex(temp_data_I, temp_data_Q).getArray());

        inData.setLoSide(loSide);// SMT8.2.5

        return inData;

    }

    public static MultiSiteWaveComplex removeingDC_RF_single(MultiSiteWaveComplex inData,
            int[] activeSite) {

        MultiSiteWaveDouble data_i = new MultiSiteWaveDouble();
        MultiSiteWaveDouble data_q = new MultiSiteWaveDouble();
        MultiSiteLoSide loSide = inData.getLoSide();// SMT8.2.5
        for (int sites : activeSite) {

            WaveDouble capWaveI = inData.getReal().get(sites);
            WaveDouble capWaveQ = inData.getImaginary().get(sites);

            WaveDouble temp_data_I = new WaveDouble();
            WaveDouble temp_data_Q = new WaveDouble();

            temp_data_I = (capWaveI.subtract(capWaveI.mean())).multiply(-1);
            temp_data_Q = (capWaveQ.subtract(capWaveQ.mean())).multiply(-1);

            data_i.set(sites, temp_data_I);

            data_q.set(sites, temp_data_Q);

        }
        MultiSiteWaveComplex waveform = new MultiSiteWaveComplex(data_i, data_q);
        waveform.setLoSide(loSide);// SMT8.2.5

        return waveform;

    }

    public static MultiSiteWaveComplex removeingDC_DAC(MultiSiteWaveDouble capWaveI1,
            MultiSiteWaveDouble capWaveQ1, int[] activeSite) {

        MultiSiteWaveDouble data_i = new MultiSiteWaveDouble();
        MultiSiteWaveDouble data_q = new MultiSiteWaveDouble();
        for (int sites : activeSite) {

            WaveDouble capWaveI = capWaveI1.get(sites);
            WaveDouble capWaveQ = capWaveQ1.get(sites);

            WaveDouble temp_data_I = new WaveDouble();
            WaveDouble temp_data_Q = new WaveDouble();

            temp_data_I = (capWaveI.subtract(capWaveI.mean())).multiply(-1);
            temp_data_Q = (capWaveQ.subtract(capWaveQ.mean())).multiply(-1);

            data_i.set(sites, temp_data_I);

            data_q.set(sites, temp_data_Q);

        }
        MultiSiteWaveComplex waveform = new MultiSiteWaveComplex(data_i, data_q);

        return waveform;

    }

    public static MultiSiteLong GetCapInt(Map<String, MultiSiteBitSequence> bitsOfAllSignals,

            int[] activeSites,
            /** Serial Bus PinName (1 pin only) */
            String mPinName, /** Sample length in Bits */
            int mSampleLengthInBits, int debugMode) {

        /** Initial Skip Bits */
        int mInitSkipBits = 0;
        /** Sample Count */
        int mSampleCount = 1;
        /** Skip Bits */
        int mSkipBits = 0;
        /** Inter-Sample Skip Bits */
        int mInterSampleSkipBits = 0;
        /** Functional test descriptor for datalog */
        // public IFunctionalTestDescriptor myFtd;

        MultiSiteLong Capt_int = new MultiSiteLong();

        // Data Array will be extracted in 3 level nested for() loop in Serial Extraction
        // 1st for() loop, most outer loop -> Extracting Data per site
        // 2nd for() loop -> loop through each of frame, defined in frameCount input parameter
        // 3rd for() loop -> Processing bit index, based on calculation from sampleLengthInBits,
        // initSkipBits, interSampleSkipBits, SkipBits
        // Get and set BitSet data

        int numOfPoints = mSampleCount * mSampleLengthInBits;
        int numOfCaptures = numOfPoints;

        for (int iSite : activeSites) {

            // Temporary BitStream data container, per Site data
            BitSequence tmpBitStream = new BitSequence(numOfPoints);
            if (debugMode > 1) {
                System.out.print("Site " + iSite);
                System.out.print(" Bit Array::  \n");
                System.out.println("===================");
                System.out.println("Number of extracted bits = " + numOfPoints);
                System.out.println("Bit Index : Raw Data Index : Capture Data (Bool)");
            }
            for (int sampleIndex = 0; sampleIndex < mSampleCount; sampleIndex++) {

                for (int bitPoint = 0; bitPoint < mSampleLengthInBits; bitPoint++) {

                    int bitIndex = mInitSkipBits + (sampleIndex * (mSampleLengthInBits * (mSkipBits + 1) + mInterSampleSkipBits)) + (bitPoint * (mSkipBits + 1));
                    boolean tmpDataBool = bitsOfAllSignals.get(mPinName).get(iSite).get(bitIndex);

                    int bitLocation = bitPoint + (sampleIndex * mSampleLengthInBits);

                    if (tmpDataBool) {
                        tmpBitStream.set(bitLocation);
                    }
                    if (debugMode > 1) {
                        System.out.println(String.format("%06d", (bitLocation)) + " : " + String
                                .format("%06d", bitIndex) + " : " + (tmpDataBool ? 1 : 0));
                    }
                } // end of 3rd-for() loop -> bitPoint

                /// Convert bits to int...MSB first
                double tmp_int = 0;
                for (int bitPoint = 0; bitPoint < mSampleLengthInBits; bitPoint++) {

                    int bitIndex = mInitSkipBits + (sampleIndex * (mSampleLengthInBits * (mSkipBits + 1) + mInterSampleSkipBits)) + (bitPoint * (mSkipBits + 1));
                    boolean tmpDataBool = bitsOfAllSignals.get(mPinName).get(iSite).get(bitIndex);
                    if (tmpDataBool) {
                        tmp_int = tmp_int + Math.pow(2, 15 - bitPoint);
                    }

                } // end of 3rd-for() loop -> bitPoint
                if (debugMode > 1) {
                    System.out.println("Site " + iSite + "  Val=" + tmp_int);
                }
                Capt_int.set(iSite, (long) tmp_int);

            } // end of 2nd-for() loop -> sample
            if (debugMode > 1) {

                System.out.print("Bitset Data -> ");
                System.out.println(tmpBitStream);
                System.out.println("====================\n\n");
            }

        } // end of 1st-for() loop -> Site

        return Capt_int;

    }

    public static MultiSiteLongArray GetCapInt(Map<String, MultiSiteBitSequence> bitsOfAllSignals,

            int[] activeSites,
            /** Serial Bus PinName (1 pin only) */
            String mPinName, /** Sample length in Bits */
            int mSampleLengthInBits, int mSampleCount, int debugMode) {

        MultiSiteLongArray tmpResult = new MultiSiteLongArray();

        /** Initial Skip Bits */
        int mInitSkipBits = 0;
        /** Sample Count */
        // int mSampleCount=1;
        /** Skip Bits */
        int mSkipBits = 0;
        /** Inter-Sample Skip Bits */
        int mInterSampleSkipBits = 0;
        /** Functional test descriptor for datalog */
        // public IFunctionalTestDescriptor myFtd;

        // MultiSiteLong Capt_int= new MultiSiteLong();
        long[] Capt_int = new long[mSampleCount];

        // Data Array will be extracted in 3 level nested for() loop in Serial Extraction
        // 1st for() loop, most outer loop -> Extracting Data per site
        // 2nd for() loop -> loop through each of frame, defined in frameCount input parameter
        // 3rd for() loop -> Processing bit index, based on calculation from sampleLengthInBits,
        // initSkipBits, interSampleSkipBits, SkipBits
        // Get and set BitSet data

        int numOfPoints = mSampleCount * mSampleLengthInBits;
        int numOfCaptures = numOfPoints;

        for (int iSite : activeSites) {

            // Temporary BitStream data container, per Site data
            BitSequence tmpBitStream = new BitSequence(numOfPoints);
            if (debugMode >= 2) {
                System.out.print("Site " + iSite);
                System.out.print(" Bit Array::  \n");
                System.out.println("===================");
                System.out.println("Number of extracted bits = " + numOfPoints);
                System.out.println("Bit Index : Raw Data Index : Capture Data (Bool)");
            }
            for (int sampleIndex = 0; sampleIndex < mSampleCount; sampleIndex++) {

                for (int bitPoint = 0; bitPoint < mSampleLengthInBits; bitPoint++) {

                    int bitIndex = mInitSkipBits + (sampleIndex * (mSampleLengthInBits * (mSkipBits + 1) + mInterSampleSkipBits)) + (bitPoint * (mSkipBits + 1));
                    boolean tmpDataBool = bitsOfAllSignals.get(mPinName).get(iSite).get(bitIndex);

                    int bitLocation = bitPoint + (sampleIndex * mSampleLengthInBits);

                    if (tmpDataBool) {
                        tmpBitStream.set(bitLocation);
                    }
                    if (debugMode >= 2) {
                        System.out.println(String.format("%06d", (bitLocation)) + " : " + String
                                .format("%06d", bitIndex) + " : " + (tmpDataBool ? 1 : 0));
                    }
                } // end of 3rd-for() loop -> bitPoint

                /// Convert bits to int...MSB first
                double tmp_int = 0;
                for (int bitPoint = 0; bitPoint < mSampleLengthInBits; bitPoint++) {

                    int bitIndex = mInitSkipBits + (sampleIndex * (mSampleLengthInBits * (mSkipBits + 1) + mInterSampleSkipBits)) + (bitPoint * (mSkipBits + 1));
                    boolean tmpDataBool = bitsOfAllSignals.get(mPinName).get(iSite).get(bitIndex);
                    if (tmpDataBool) {
                        tmp_int = tmp_int + Math.pow(2, mSampleLengthInBits - bitPoint - 1);
                    }

                } // end of 3rd-for() loop -> bitPoint
                if (debugMode >= 2) {
                    System.out.println("Site " + iSite + "  Val=" + tmp_int);
                }
                Capt_int[sampleIndex] = (int) tmp_int;

            } // end of 2nd-for() loop -> sample

            if (debugMode >= 2) {
                System.out.print("Bitset Data -> ");
                System.out.println(tmpBitStream);
                System.out.println("====================\n\n");
            }

            tmpResult.set(iSite, Capt_int);

        } // end of 1st-for() loop -> Site

        return tmpResult;

    }

    /// This returns in bits, so each sample is 1 bits
    public static MultiSiteLongArray GetCapbit(Map<String, MultiSiteBitSequence> bitsOfAllSignals,

            int[] activeSites,
            /** Serial Bus PinName (1 pin only) */
            String mPinName, /** Sample length in Bits */

            int mSampleCount, int debugMode) {

        int mSampleLengthInBits = 1;
        MultiSiteLongArray tmpResult = new MultiSiteLongArray();

        /** Initial Skip Bits */
        int mInitSkipBits = 0;
        /** Sample Count */
        // int mSampleCount=1;
        /** Skip Bits */
        int mSkipBits = 0;
        /** Inter-Sample Skip Bits */
        int mInterSampleSkipBits = 0;
        /** Functional test descriptor for datalog */
        // public IFunctionalTestDescriptor myFtd;

        // MultiSiteLong Capt_int= new MultiSiteLong();
        long[] Capt_int = new long[mSampleCount];

        // Data Array will be extracted in 3 level nested for() loop in Serial Extraction
        // 1st for() loop, most outer loop -> Extracting Data per site
        // 2nd for() loop -> loop through each of frame, defined in frameCount input parameter
        // 3rd for() loop -> Processing bit index, based on calculation from sampleLengthInBits,
        // initSkipBits, interSampleSkipBits, SkipBits
        // Get and set BitSet data

        int numOfPoints = mSampleCount * mSampleLengthInBits;
        int numOfCaptures = numOfPoints;

        for (int iSite : activeSites) {

            // Temporary BitStream data container, per Site data
            BitSequence tmpBitStream = new BitSequence(numOfPoints);
            if (debugMode >= 2) {
                System.out.print("Site " + iSite);
                System.out.print(" Bit Array::  \n");
                System.out.println("===================");
                System.out.println("Number of extracted bits = " + numOfPoints);
                System.out.println("Bit Index : Raw Data Index : Capture Data (Bool)");
            }
            for (int sampleIndex = 0; sampleIndex < mSampleCount; sampleIndex++) {

                for (int bitPoint = 0; bitPoint < mSampleLengthInBits; bitPoint++) {

                    int bitIndex = mInitSkipBits + (sampleIndex * (mSampleLengthInBits * (mSkipBits + 1) + mInterSampleSkipBits)) + (bitPoint * (mSkipBits + 1));
                    boolean tmpDataBool = bitsOfAllSignals.get(mPinName).get(iSite).get(bitIndex);

                    int bitLocation = bitPoint + (sampleIndex * mSampleLengthInBits);

                    if (tmpDataBool) {
                        tmpBitStream.set(bitLocation);
                    }
                    if (debugMode >= 2) {
                        System.out.println(String.format("%06d", (bitLocation)) + " : " + String
                                .format("%06d", bitIndex) + " : " + (tmpDataBool ? 1 : 0));
                    }
                } // end of 3rd-for() loop -> bitPoint

                /// Convert bits to int...MSB first
                double tmp_int = 0;
                for (int bitPoint = 0; bitPoint < mSampleLengthInBits; bitPoint++) {

                    int bitIndex = mInitSkipBits + (sampleIndex * (mSampleLengthInBits * (mSkipBits + 1) + mInterSampleSkipBits)) + (bitPoint * (mSkipBits + 1));
                    boolean tmpDataBool = bitsOfAllSignals.get(mPinName).get(iSite).get(bitIndex);
                    tmp_int = 0;
                    if (tmpDataBool) {
                        tmp_int = 1;// tmp_int + Math.pow(2,15-bitPoint);
                    }

                } // end of 3rd-for() loop -> bitPoint
                if (debugMode >= 2) {
                    System.out.println("Site " + iSite + "  Val=" + tmp_int);
                }
                Capt_int[sampleIndex] = (int) tmp_int;

            } // end of 2nd-for() loop -> sample

            if (debugMode >= 2) {
                System.out.print("Bitset Data -> ");
                System.out.println(tmpBitStream);
                System.out.println("====================\n\n");
            }

            tmpResult.set(iSite, Capt_int);

        } // end of 1st-for() loop -> Site

        return tmpResult;

    }

    public static void ShiftWaveform(String path) {

        WaveDouble wave_i = new WaveDouble();
        // wave_i = wave_i.importFromFile(path+"/setups/waveforms/WCDMA_TM4_i.txt",
        // WaveformFileType.CUSTOM_WAVEFORM);
        wave_i = wave_i.importFromFile(path + "/setups/waveforms/i.cwf",
                WaveformFileType.CUSTOM_WAVEFORM);
        WaveDouble wave_q = new WaveDouble();
        // wave_q = wave_q.importFromFile(path+"/setups/waveforms/WCDMA_TM4_q.txt",
        // WaveformFileType.CUSTOM_WAVEFORM);
        wave_q = wave_q.importFromFile(path + "/setups/waveforms/q.cwf",
                WaveformFileType.CUSTOM_WAVEFORM);

        // wavecomplex
        wave_i.plot("i_plot");
        wave_q.plot("q_plot");

        double samplingRate = 2.4576e8;// 160e6;
        double FreqShift = 10e6;// samplingRate / 2;
        double deltat;
        WaveComplex shiftedWave = new WaveComplex(wave_i.getSize());
        deltat = 1 / samplingRate;

        WaveDouble wave_data_i, wave_data_q;

        WaveComplex originalWfm = new WaveComplex(wave_i.getArray(), wave_q.getArray());

        for (int i = 0; i < wave_i.getSize(); i++) {
            float time = (float) (deltat * i);
            float iNum1 = (float) (wave_i.getValue(i) * Math
                    .cos(2 * Math.PI * FreqShift * time) - wave_q
                            .getValue(i) * Math.sin(2 * Math.PI * FreqShift * time));
            float qNum1 = (float) (wave_i.getValue(i) * Math
                    .sin(2 * Math.PI * FreqShift * time) + wave_q
                            .getValue(i) * Math.cos(2 * Math.PI * FreqShift * time));

            shiftedWave.setValue(i, new Complex(iNum1, qNum1));
        }
        shiftedWave.plot("shifted_plot");
        shiftedWave.exportToFile(path + "/setups/waveforms/WCDMA_TM4_shifted.wfm",
                WaveformFileType.SIGNAL_STUDIO);

        // return shiftedWave;
    }

    // *************************************add by chunyan for SPREADTRUM common
    // code**********************************
    public static MultiSiteDouble SNDCalc_2Tones(MultiSiteWaveLong aiCapData, int iFundBin1s,
            int iFundBin2s, int iBegins, int iEndBins, MultiSiteDouble dSND, boolean offline1,
            String tsName, boolean isDebug1) {

        MultiSiteSpectrum spectrum_W = aiCapData.spectrum(SpectrumUnit.mW).multiply(1e3);

        MultiSiteDouble dPwrTotal = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSignal1 = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSignal2 = new MultiSiteDouble(0.0);

        dPwrSignal1 = spectrum_W.getValue(iFundBin1s);
        dPwrSignal2 = spectrum_W.getValue(iFundBin2s);

        // for(int i =iBegins;i<=iEndBins;i++)
        // {
        // dPwrTotal=dPwrTotal.add(spectrum_W.getValue(i));
        // }
        //
        dPwrTotal = spectrum_W.sum(iBegins, iEndBins);

        if (offline1) {
            System.out.println("offline execute");
            dSND.set(999.0);
        } else {
            dSND = ((dPwrSignal1.add(dPwrSignal2))
                    .divide((dPwrTotal.subtract(dPwrSignal2).subtract(dPwrSignal1)))).log10()
                            .multiply(10);

            if (UserCommon.debug_flag) {
                spectrum_W.plot(tsName + "ADC Spectrum");
                System.out.println("iFundBin1s Bin = " + iFundBin1s);
                System.out.println("iFundBin2s Bin = " + iFundBin2s);
                System.out.println("dPwrSignal1=" + dPwrSignal1);
                System.out.println("dPwrSignal2=" + dPwrSignal2);
                System.out.println("dPwrTotal=" + dPwrTotal);
                System.out
                        .println("noise=" + dPwrTotal.subtract(dPwrSignal2).subtract(dPwrSignal1));
                System.out.println("dSND  = " + dSND);

            }
        }

        return dSND;
    }

    public static MultiSiteDouble calc_SNR(MultiSiteWaveLong aiCapData, MultiSiteDouble dSNR,
            int[] activeSites, boolean offline1, String tsName, boolean isDebug1) {

        MultiSiteSpectrum spectrum_W = aiCapData.spectrum(SpectrumUnit.mW).multiply(1e3);

        MultiSiteDouble dPwrTotal = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSignal1 = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSignal2 = new MultiSiteDouble(0.0);

        // dPwrSignal1= spectrum_W.getValue(iFundBin1s);
        // dPwrSignal2= spectrum_W.getValue(iFundBin2s);
        //
        // for(int i =iBegins;i<=iEndBins;i++)
        // {
        // dPwrTotal=dPwrTotal.add(spectrum_W.getValue(i));
        // }

        if (offline1) {

            System.out.println("offline execute");
            dSNR.set(999.0);

        } else {

            for (int site : activeSites) {

                int min_index, max_index;
                double min, max;
                int iSize, iFund = 0;
                double dTotal = 0.0;
                double dNoise = 0.0;
                double dSiganl = 0.0;
                double dHarms = 0.0;
                // System.out.println("==============SITE execute================="+site);
                iSize = spectrum_W.get(site).getSize();
                if (iSize > 2) {
                    spectrum_W.setValue(site, 0, 0);// set index 0 power to 0 mw
                    spectrum_W.setValue(site, 1, 0);// set index 1 power to 0 mw
                }

                max_index = spectrum_W.get(site).maxIndex();

                if (max_index == 0) {
                    iFund = 999;
                    dSiganl = spectrum_W.get(site).getValue(iFund) + spectrum_W.get(site)
                            .getValue(iFund - 1) + spectrum_W.get(site).getValue(iFund + 1);
                } else if (max_index == (iSize - 1)) {

                    iFund = max_index;
                    // set index iFund+1 power to 0 mw,because the index max_index not exit if
                    // max_index=iSize-1;
                    dSiganl = spectrum_W.get(site).getValue(iFund) + spectrum_W.get(site)
                            .getValue(iFund - 1);
                } else {
                    iFund = max_index;
                    dSiganl = spectrum_W.get(site).getValue(iFund) + spectrum_W.get(site)
                            .getValue(iFund - 1) + spectrum_W.get(site).getValue(iFund + 1);
                }

                // get main bin power

                // get total power
                // for(int i =2;i<iSize;i++)
                // {
                // dTotal+= spectrum_W.get(site).getValue(i);
                // }
                dTotal = spectrum_W.get(site).sum(2, iSize);

                // get harms power

                for (int i = 2; (i * iFund) < iSize; i++) {
                    dHarms += spectrum_W.get(site).getValue(i * iFund);
                }

                // get noise power
                dNoise = dTotal - dHarms - dSiganl;

                dSNR.set(site, 10 * (Math.log10(dSiganl / dNoise)));
                // System.out.println("sitenum="+site+"dSNR =" + 10*(Math.log10(dSiganl/dNoise )));
                if (UserCommon.debug_flag)
                // if(true)
                {
                    spectrum_W.plot(tsName + "ADC Spectrum");
                    System.out.println("iFund Bin = " + iFund);

                    System.out.println("dTotal=" + dTotal);
                    System.out.println("dHarms=" + dHarms);
                    System.out.println("dNoise=" + dNoise);
                    System.out.println("dSiganl=" + dSiganl);
                    // System.out.println("dSNR = " +dSNR);

                }
            }

        }

        return dSNR;
    }

    // *************************************add by chunyan for SPREADTRUM common
    // code**********************************
    public static MultiSiteDouble SNDCalc_2Tones_GNSS(MultiSiteWaveLong aiCapData, int iFundBin1s,
            int iFundBin2s, int iBegins, int iEndBins, MultiSiteDouble dSND, boolean offline1,
            String tsName, boolean isDebug1) {

        MultiSiteSpectrum spectrum_W = aiCapData.spectrum(SpectrumUnit.mW).multiply(1e3);

        MultiSiteDouble dPwrTotal = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSignal1 = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSignal2 = new MultiSiteDouble(0.0);
        int offset1 = 50;
        int offset2 = 100;
        dPwrSignal1 = spectrum_W.extractValues(iFundBin1s, offset1 * 2).sum();
        dPwrSignal2 = spectrum_W.extractValues(iFundBin2s, offset2 * 2).sum();

        for (int i = iBegins; i <= iEndBins; i++) {
            dPwrTotal = dPwrTotal.add(spectrum_W.getValue(i));
        }

        if (offline1) {
            System.out.println("offline execute");
            dSND.set(999.0);
        } else {
            dSND = ((dPwrSignal1.add(dPwrSignal2))
                    .divide((dPwrTotal.subtract(dPwrSignal2).subtract(dPwrSignal1)))).log10()
                            .multiply(10);

            if (UserCommon.debug_flag) {
                spectrum_W.plot(tsName + "ADC Spectrum");
                System.out.println("iFundBin1s Bin = " + iFundBin1s);
                System.out.println("iFundBin2s Bin = " + iFundBin2s);
                System.out.println("dPwrSignal1=" + dPwrSignal1);
                System.out.println("dPwrSignal2=" + dPwrSignal2);
                System.out.println("dPwrTotal=" + dPwrTotal);
                System.out
                        .println("noise=" + dPwrTotal.subtract(dPwrSignal2).subtract(dPwrSignal1));
                System.out.println("dSND  = " + dSND);

            }
        }

        return dSND;
    }

    public static MultiSiteWaveDouble RemoveDC_SINFIT(MultiSiteWaveDouble capWave,
            int[] activeSite) {
        MultiSiteSine adSineWave = new MultiSiteSine();

        MultiSiteWaveDouble aiNewdata_final = new MultiSiteWaveDouble();

        MultiSiteSpectrum adSpectrum = new MultiSiteSpectrum();

        // MultiSiteSpectrum adSpectrum_dbm =
        // aiCapData.setWindowFunction(WindowFunction.RECTANGULAR).spectrum(SpectrumUnit.dBm);
        // MultiSiteSpectrum spectrum_W = aiCapData.spectrum(SpectrumUnit.mW).multiply(1e3);
        // adSpectrum_dbm.plot("api_spectrum=========");
        MultiSiteDouble dPwrTotal_new = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSignal = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSpurious = new MultiSiteDouble(0.0);
        MultiSiteDouble dSFDR1 = new MultiSiteDouble(0.0);
        MultiSiteDouble dSND1 = new MultiSiteDouble(0.0);
        List<MultiSiteDouble> result = new ArrayList<MultiSiteDouble>(); // add return can add this
                                                                         // line
        MultiSiteDouble[] result1 = new MultiSiteDouble[2];
        int iSpuriousBin = 0;

        for (int site : activeSite) {
            int size_data = capWave.get(site).getSize();// 1536

            try {
                adSineWave = capWave.sineFit();
                double sineamp = adSineWave.get(site).getAmplitude();
                double sinepha = adSineWave.get(site).getPhase();
                double sineoff = adSineWave.get(site).getOffset();
                double sinperiod = adSineWave.get(site).getPeriods();
                // System.out.println("period="+sinperiod);

                WaveDouble adRawdata = new WaveDouble();
                WaveDouble adSinedata = new WaveDouble();
                WaveDouble adNewdata = new WaveDouble();
                WaveDouble adResidual = new WaveDouble();
                WaveDouble adXaxis = new WaveDouble();
                WaveDouble adCoef = new WaveDouble();
                WaveDouble adTrend = new WaveDouble();
                WaveDouble aiNewdata = new WaveDouble();

                int Nx = 2;

                adRawdata = adRawdata.resize(size_data);
                adSinedata = adSinedata.resize(size_data);
                adNewdata = adNewdata.resize(size_data);
                adXaxis = adXaxis.resize(size_data);
                adResidual = adResidual.resize(size_data);
                adCoef = adCoef.resize(Nx + 1);
                adTrend = adTrend.resize(size_data);

                for (int i = 0; i < size_data; i++) {

                    adRawdata.setValue(i, capWave.get(site).getValue(i));
                    adSinedata.setValue(i, (sineamp * Math
                            .sin(2 * Math.PI * i * sinperiod / size_data + sinepha) + sineoff));
                    adResidual.setValue(i, (adRawdata.getValue(i) - adSinedata.getValue(i)));
                }

                // if (UserCommon.debug_flag) {
                // adRawdata.plot(site, "adRawdata: captured data bits", tstname);
                // adSinedata.plot(site, "adSinedata: captured data bits", tstname);
                //
                // adResidual.plot(site, "adResidual: captured data bits", tstname);
                // }

                for (int i = 0; i < size_data; i++) {
                    double b = i;
                    adXaxis.setValue(i, b);
                }

                int j, k, l, mp1, mp2, m2, n;
                double w1, w2, w3, pivot, aik;
                double[][] a = new double[21][22];
                double[] w = new double[42];
                int order = 2;

                n = adXaxis.getSize();

                // /*** Parameters Check ***/
                //
                // if (order > 20) return(-1);
                // if (order >= n) return(-1);
                //
                // /*** Calculation Start ***/

                mp1 = order + 1;
                mp2 = order + 2;
                m2 = 2 * order;

                adCoef = adCoef.resize(mp1); // order + 1

                for (int i = 0; i < m2; i++) {
                    w1 = 0;
                    for (j = 0; j < n; j++) {
                        w2 = adXaxis.getValue(j);
                        w3 = adXaxis.getValue(j);
                        for (k = 0; k < i; k++) {
                            w2 *= w3;
                        }
                        w1 += w2;
                    }
                    w[i] = w1;
                }

                for (int i = 0; i < mp1; i++) {
                    for (j = 0; j < mp1; j++) {
                        l = i + j - 1;
                        if (l >= 0) {
                            a[i][j] = w[l];
                        }
                    }
                }

                a[0][0] = n;
                w1 = 0.0;
                for (int i = 0; i < n; i++) {
                    w1 += adResidual.getValue(i);
                }

                a[0][mp1] = w1;
                for (int i = 0; i < order; i++) {
                    w1 = 0.0;
                    for (j = 0; j < n; j++) {
                        w2 = w3 = adXaxis.getValue(j);
                        for (k = 0; k < i; k++) {
                            w2 *= w3;
                        }
                        w1 += adResidual.getValue(j) * w2;
                    }
                    a[i + 1][mp1] = w1;
                }

                for (k = 0; k < mp1; k++) {
                    pivot = a[k][k];
                    if (pivot == 0.0) {
                        pivot = 1.0e-99;
                    }
                    for (j = k; j < mp2; j++) {
                        a[k][j] /= pivot;
                    }
                    for (int i = 0; i < mp1; i++) {
                        if (i != k) {
                            aik = a[i][k];
                            for (j = k; j < mp2; j++) {
                                a[i][j] -= aik * a[k][j];
                            }
                        }
                    }
                }

                for (int i = 0; i < mp1; i++) {

                    adCoef.setValue(i, a[i][mp1]);
                }

                for (int i = 0; i < size_data; i++) {
                    adTrend.setValue(i, adCoef.getValue(0) + adCoef.getValue(1) * i + adCoef
                            .getValue(2) * i * i);
                }

                for (int i = 0; i < size_data; i++) {

                    adNewdata.setValue(i, (adRawdata.getValue(i) - adTrend.getValue(i)));
                }

                aiNewdata = aiNewdata.resize(size_data);
                for (int i = 0; i < size_data; i++) {
                    aiNewdata.setValue(i, adNewdata.getValue(i));

                }

                if (UserCommon.debug_flag) {
                    adTrend.plot(site, "adTrend: captured data bits");

                    aiNewdata.plot(site, "aiNewdata11: captured data bits");
                }

                aiNewdata_final.set(site, aiNewdata);

                if (UserCommon.debug_flag) {
                    aiNewdata_final.plot("aiNewdata_final: captured data bits");

                }

            } catch (Exception e) {
                WaveDouble aiNewdata = new WaveDouble(size_data);
                for (int i = 0; i < aiNewdata.getSize(); i++) {
                    aiNewdata.setValue(i, 0.0);
                }

                aiNewdata_final.set(site, aiNewdata);
                // throw new java.lang.RuntimeException("wrong dac waveform !!!!!!!!!!!!!!!! ");

            }

        }
        // calc spectrum

        return aiNewdata_final;

    }

    public static List<MultiSiteDouble> RemoveDC_SINFIT_SFDR(MultiSiteWaveDouble capWave,
            int[] activeSite, String tstname, int iFundBins, int iBeginBins, int iEndBins) {
        MultiSiteSine adSineWave = new MultiSiteSine();

        MultiSiteWaveDouble aiNewdata_final = new MultiSiteWaveDouble();

        MultiSiteSpectrum adSpectrum = new MultiSiteSpectrum();

        // MultiSiteSpectrum adSpectrum_dbm =
        // aiCapData.setWindowFunction(WindowFunction.RECTANGULAR).spectrum(SpectrumUnit.dBm);
        // MultiSiteSpectrum spectrum_W = aiCapData.spectrum(SpectrumUnit.mW).multiply(1e3);
        // adSpectrum_dbm.plot("api_spectrum=========");
        MultiSiteDouble dPwrTotal_new = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSignal = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSpurious = new MultiSiteDouble(0.0);
        MultiSiteDouble dSFDR1 = new MultiSiteDouble(0.0);
        MultiSiteDouble dSND1 = new MultiSiteDouble(0.0);
        List<MultiSiteDouble> result = new ArrayList<MultiSiteDouble>(); // add return can add this
                                                                         // line
        MultiSiteDouble[] result1 = new MultiSiteDouble[2];
        int iSpuriousBin = 0;

        for (int site : activeSite) {

            try {
                adSineWave = capWave.sineFit();
                double sineamp = adSineWave.get(site).getAmplitude();
                double sinepha = adSineWave.get(site).getPhase();
                double sineoff = adSineWave.get(site).getOffset();
                double sinperiod = adSineWave.get(site).getPeriods();
                // System.out.println("period="+sinperiod);

                WaveDouble adRawdata = new WaveDouble();
                WaveDouble adSinedata = new WaveDouble();
                WaveDouble adNewdata = new WaveDouble();
                WaveDouble adResidual = new WaveDouble();
                WaveDouble adXaxis = new WaveDouble();
                WaveDouble adCoef = new WaveDouble();
                WaveDouble adTrend = new WaveDouble();
                WaveDouble aiNewdata = new WaveDouble();
                int size_data = capWave.get(site).getSize();// 1536
                int Nx = 2;

                adRawdata = adRawdata.resize(size_data);
                adSinedata = adSinedata.resize(size_data);
                adNewdata = adNewdata.resize(size_data);
                adXaxis = adXaxis.resize(size_data);
                adResidual = adResidual.resize(size_data);
                adCoef = adCoef.resize(Nx + 1);
                adTrend = adTrend.resize(size_data);

                for (int i = 0; i < size_data; i++) {

                    adRawdata.setValue(i, capWave.get(site).getValue(i));
                    adSinedata.setValue(i, (sineamp * Math
                            .sin(2 * Math.PI * i * sinperiod / size_data + sinepha) + sineoff));
                    adResidual.setValue(i, (adRawdata.getValue(i) - adSinedata.getValue(i)));
                }

                if (UserCommon.debug_flag) {
                    adRawdata.plot(site, "adRawdata: captured data bits", tstname);
                    adSinedata.plot(site, "adSinedata: captured data bits", tstname);

                    adResidual.plot(site, "adResidual: captured data bits", tstname);
                }

                for (int i = 0; i < size_data; i++) {
                    double b = i;
                    adXaxis.setValue(i, b);
                }

                int j, k, l, mp1, mp2, m2, n;
                double w1, w2, w3, pivot, aik;
                double[][] a = new double[21][22];
                double[] w = new double[42];
                int order = 2;

                n = adXaxis.getSize();

                // /*** Parameters Check ***/
                //
                // if (order > 20) return(-1);
                // if (order >= n) return(-1);
                //
                // /*** Calculation Start ***/

                mp1 = order + 1;
                mp2 = order + 2;
                m2 = 2 * order;

                adCoef = adCoef.resize(mp1); // order + 1

                for (int i = 0; i < m2; i++) {
                    w1 = 0;
                    for (j = 0; j < n; j++) {
                        w2 = adXaxis.getValue(j);
                        w3 = adXaxis.getValue(j);
                        for (k = 0; k < i; k++) {
                            w2 *= w3;
                        }
                        w1 += w2;
                    }
                    w[i] = w1;
                }

                for (int i = 0; i < mp1; i++) {
                    for (j = 0; j < mp1; j++) {
                        l = i + j - 1;
                        if (l >= 0) {
                            a[i][j] = w[l];
                        }
                    }
                }

                a[0][0] = n;
                w1 = 0.0;
                for (int i = 0; i < n; i++) {
                    w1 += adResidual.getValue(i);
                }

                a[0][mp1] = w1;
                for (int i = 0; i < order; i++) {
                    w1 = 0.0;
                    for (j = 0; j < n; j++) {
                        w2 = w3 = adXaxis.getValue(j);
                        for (k = 0; k < i; k++) {
                            w2 *= w3;
                        }
                        w1 += adResidual.getValue(j) * w2;
                    }
                    a[i + 1][mp1] = w1;
                }

                for (k = 0; k < mp1; k++) {
                    pivot = a[k][k];
                    if (pivot == 0.0) {
                        pivot = 1.0e-99;
                    }
                    for (j = k; j < mp2; j++) {
                        a[k][j] /= pivot;
                    }
                    for (int i = 0; i < mp1; i++) {
                        if (i != k) {
                            aik = a[i][k];
                            for (j = k; j < mp2; j++) {
                                a[i][j] -= aik * a[k][j];
                            }
                        }
                    }
                }

                for (int i = 0; i < mp1; i++) {

                    adCoef.setValue(i, a[i][mp1]);
                }

                for (int i = 0; i < size_data; i++) {
                    adTrend.setValue(i, adCoef.getValue(0) + adCoef.getValue(1) * i + adCoef
                            .getValue(2) * i * i);
                }

                for (int i = 0; i < size_data; i++) {

                    adNewdata.setValue(i, (adRawdata.getValue(i) - adTrend.getValue(i)));
                }

                aiNewdata = aiNewdata.resize(size_data);
                for (int i = 0; i < size_data; i++) {
                    aiNewdata.setValue(i, adNewdata.getValue(i));

                }

                if (UserCommon.debug_flag) {
                    adTrend.plot(site, "adTrend: captured data bits");

                    aiNewdata.plot(site, "aiNewdata11: captured data bits");
                }

                aiNewdata_final.set(site, aiNewdata);

                if (UserCommon.debug_flag) {
                    aiNewdata_final.plot("aiNewdata_final: captured data bits");

                }

                // calc spectrum

                adSpectrum.set(site,
                        aiNewdata_final.get(site).setWindowFunction(WindowFunction.RECTANGULAR)
                                .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE)
                                .spectrum(SpectrumUnit.mW).divide(1e3));

                double dPwrTotal = 0.0;
                for (int i = iBeginBins; i < 2 * iEndBins + 1; i++) {

                    if ((adSpectrum.getValue(site, i) > dPwrSpurious.get(
                            site)) && (i != iFundBins) && (i != (iFundBins - 1)) && (i != (iFundBins + 1))) {
                        // System.out.println("offline execute");
                        iSpuriousBin = i;
                        if (i != 256) {

                            dPwrSpurious.set(site, adSpectrum.get(site).getValue(iSpuriousBin)); //
                        }
                    }
                    if (i >= iBeginBins && i <= iEndBins) {
                        if (i != 256) {
                            dPwrTotal += adSpectrum.getValue(site, i); // Remove BBPLL spur at Tone
                                                                       // 256
                            dPwrTotal_new.set(site, dPwrTotal);
                        }
                    }

                }

                double psignal = 0.0;
                double sfdr = 0.0;
                double snd = 0.0;

                psignal = adSpectrum.get(site).getValue(iFundBins);

                sfdr = 10 * (Math.log10(psignal / (dPwrSpurious.get(site))));

                snd = 10 * (Math.log10(psignal / (dPwrTotal_new.get(site) - psignal)));

                dPwrSignal = adSpectrum.getValue(iFundBins);// .add(adSpectrum.getValue(iFundBins-1)).add(adSpectrum.getValue(iFundBins+1));
                dSFDR1 = (dPwrSignal.divide(dPwrSpurious)).log10().multiply(10);

                dSND1 = ((dPwrSignal).divide(dPwrTotal_new.subtract(dPwrSignal))).log10()
                        .multiply(10);

                // result1[0]=dSFDR1;
                // result1[1]=dSFDR1;

                if (UserCommon.debug_flag) {
                    adSpectrum.plot(tstname + "DAC Spectrum");

                    System.out.println("iFundBins_power_db=" + dPwrSignal);
                    System.out.println("Spurious_power_db=" + dPwrSpurious);
                    System.out.println("dPwrTotal_new_w = " + dPwrTotal_new);

                    System.out.println("total-signal= " + (dPwrTotal_new.subtract(dPwrSignal)));

                    System.out.println("signal/(total-signal)= " + ((dPwrSignal)
                            .divide(dPwrTotal_new.subtract(dPwrSignal))));

                    System.out.println("iFUndBins=" + iFundBins);
                    System.out.println("Spurious=" + dPwrSpurious);
                    System.out.println("SFDR  = " + dSFDR1);
                    System.out.println("dSND  = " + dSND1);

                }

                dSFDR1.set(site, sfdr);
                dSND1.set(site, snd);

            } catch (Exception e) {
                dSFDR1.set(site, 999);
                dSND1.set(site, 999);

            }

            // System.out.println("max bins------------="+adSpectrum.maxIndex());
            //// System.out.println("iFUndBins="+dPwrSignal);
            // System.out.println("sPurings------------="+dPwrSpurious);
            // System.out.println("dPwrTotal_new--------------="+dPwrTotal_new);

        }
        result.add(dSFDR1);
        result.add(dSND1);
        return result;

    }

    public static List<MultiSiteDouble> SFDRCalc(MultiSiteWaveDouble aiCapData, int iFundBins,
            int iBeginBins, int iEndBins, int[] activeSites, boolean offline1, String tsName,
            boolean isDebug1) {
        // public static MultiSiteDouble[] SFDRCalc (MultiSiteWaveDouble aiCapData, int
        // iFundBins,int iBeginBins,int iEndBins,int[] activeSites ) {

        MultiSiteSpectrum adSpectrum = aiCapData.setWindowFunction(WindowFunction.RECTANGULAR)
                .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.mW)
                .divide(1e3);
        // MultiSiteSpectrum adSpectrum_dbm =
        // aiCapData.setWindowFunction(WindowFunction.RECTANGULAR).spectrum(SpectrumUnit.dBm);
        // MultiSiteSpectrum spectrum_W = aiCapData.spectrum(SpectrumUnit.mW).multiply(1e3);
        // adSpectrum_dbm.plot("api_spectrum=========");
        MultiSiteDouble dPwrTotal_new = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSignal = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSpurious = new MultiSiteDouble(0.0);
        MultiSiteDouble dSFDR1 = new MultiSiteDouble(0.0);
        MultiSiteDouble dSND1 = new MultiSiteDouble(0.0);
        List<MultiSiteDouble> result = new ArrayList<MultiSiteDouble>(); // add return can add this
                                                                         // line
        MultiSiteDouble[] result1 = new MultiSiteDouble[2];
        int iSpuriousBin = 0;

        for (int iSite : activeSites) {
            double dPwrTotal = 0.0;
            for (int i = iBeginBins; i < 2 * iEndBins + 1; i++) {

                if ((adSpectrum.getValue(iSite, i) > dPwrSpurious.get(
                        iSite)) && (i != iFundBins) && (i != (iFundBins - 1)) && (i != (iFundBins + 1))) {
                    // System.out.println("offline execute");
                    iSpuriousBin = i;
                    if (i != 256) {

                        dPwrSpurious.set(iSite, adSpectrum.get(iSite).getValue(iSpuriousBin)); //
                    }
                }
                if (i >= iBeginBins && i <= iEndBins) {
                    if (i != 256) {
                        dPwrTotal += adSpectrum.getValue(iSite, i); // Remove BBPLL spur at Tone 256
                        dPwrTotal_new.set(iSite, dPwrTotal);
                    }
                }

            }

            // System.out.println("max bins------------="+adSpectrum.maxIndex());
            //// System.out.println("iFUndBins="+dPwrSignal);
            // System.out.println("sPurings------------="+dPwrSpurious);
            // System.out.println("dPwrTotal_new--------------="+dPwrTotal_new);
        }
        if (offline1) {
            System.out.println("offline execute");
            dSND1.set(999.0);
            dSFDR1.set(999.0);
            result.add(dSFDR1);
            result.add(dSND1);

        } else {

            dPwrSignal = adSpectrum.getValue(iFundBins);// .add(adSpectrum.getValue(iFundBins-1)).add(adSpectrum.getValue(iFundBins+1));
            dSFDR1 = (dPwrSignal.divide(dPwrSpurious)).log10().multiply(10);

            dSND1 = ((dPwrSignal).divide(dPwrTotal_new.subtract(dPwrSignal))).log10().multiply(10);

            result.add(dSFDR1);
            result.add(dSND1);
            // result1[0]=dSFDR1;
            // result1[1]=dSFDR1;

            if (UserCommon.debug_flag)
            // if(true)
            {
                adSpectrum.plot(tsName + "DAC Spectrum");

                System.out.println("iFundBins_power_db=" + dPwrSignal);
                System.out.println("Spurious_power_db=" + dPwrSpurious);
                System.out.println("dPwrTotal_new_w = " + dPwrTotal_new);

                System.out.println("total-signal= " + (dPwrTotal_new.subtract(dPwrSignal)));

                System.out.println("signal/(total-signal)= " + ((dPwrSignal)
                        .divide(dPwrTotal_new.subtract(dPwrSignal))));

                System.out.println("iFUndBins=" + iFundBins);
                System.out.println("Spurious=" + dPwrSpurious);
                System.out.println("SFDR  = " + dSFDR1);
                System.out.println("dSND  = " + dSND1);

            }

        }
        result.size();
        // System.out.println("result size============="+result.size());
        // System.out.println("tsName============="+tsName);

        return result;
        // return result1;
    }

    public static List<MultiSiteDouble> SFDRCalc_A(MultiSiteWaveDouble aiCapData, int iFundBins,
            int iBeginBins, int iEndBins, List<MultiSiteDouble> result, int[] activeSites,
            boolean offline1, String tsName, boolean isDebug1) {
        // public static MultiSiteDouble[] SFDRCalc (MultiSiteWaveDouble aiCapData, int
        // iFundBins,int iBeginBins,int iEndBins,int[] activeSites ) {

        MultiSiteSpectrum adSpectrum = aiCapData.spectrum(SpectrumUnit.mW);
        // MultiSiteSpectrum spectrum_W = aiCapData.spectrum(SpectrumUnit.mW).multiply(1e-3);
        MultiSiteDouble dPwrTotal_new = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSignal = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSpurious = new MultiSiteDouble(0.0);
        MultiSiteDouble dSFDR1 = new MultiSiteDouble(0.0);
        MultiSiteDouble dSND1 = new MultiSiteDouble(0.0);
        // List<MultiSiteDouble> result = new ArrayList<MultiSiteDouble>(); //add return can add
        // this line
        MultiSiteDouble[] result1 = new MultiSiteDouble[2];
        int iSpuriousBin = 0;
        double dPwrTotal = 0.0;

        for (int iSite : activeSites) {
            for (int i = iBeginBins; i < 2 * iEndBins + 1; i++) {

                if ((adSpectrum.getValue(iSite, i) > dPwrSpurious.get(
                        iSite)) && i != iFundBins && i != (iFundBins - 1) && i != (iFundBins + 1)) {
                    iSpuriousBin = 0;
                    if (i != 256) {

                        dPwrSpurious.set(iSite, adSpectrum.get(iSite).getValue(iSpuriousBin)); //
                    }
                }
                if (i >= iBeginBins && i <= iEndBins) {
                    if (i != 256) {
                        dPwrTotal += adSpectrum.getValue(iSite, i); // Remove BBPLL spur at Tone 256
                        dPwrTotal_new.set(iSite, dPwrTotal);
                    }
                }

            }
        }
        if (offline1) {
            System.out.println("offline execute");
            dSND1.set(999.0);
            dSFDR1.set(999.0);
            result.add(dSFDR1);
            result.add(dSND1);

        } else {

            dPwrSignal = adSpectrum.getValue(iFundBins);
            dSFDR1 = (dPwrSignal.divide(dPwrSpurious)).log10().multiply(10);

            dSND1 = ((dPwrSignal).divide(dPwrTotal_new.subtract(dPwrSignal))).log10().multiply(10);

            result.add(dSFDR1);
            result.add(dSND1);
            result1[0] = dSFDR1;
            result1[1] = dSFDR1;

            if (UserCommon.debug_flag) {
                adSpectrum.plot(tsName + "DAC Spectrum");

                System.out.println("Spurious Bin = " + iSpuriousBin);
                System.out.println("dPwrSpurious Bin = " + dPwrSpurious);
                System.out.println("SFDR  = " + dSFDR1);
                System.out.println("dSND  = " + dSND1);

            }

        }
        result.size();
        // System.out.println("result size============="+result.size());
        // System.out.println("tsName============="+tsName);

        return result;
        // return result1;
    }

    public static void SFDRCalc_C(MultiSiteWaveDouble aiCapData, int iFundBins, int iBeginBins,
            int iEndBins, List<MultiSiteDouble> result, int[] activeSites, boolean offline1,
            String tsName, boolean isDebug1) {
        // public static MultiSiteDouble[] SFDRCalc (MultiSiteWaveDouble aiCapData, int
        // iFundBins,int iBeginBins,int iEndBins,int[] activeSites ) {

        MultiSiteSpectrum adSpectrum = aiCapData.spectrum(SpectrumUnit.mW);
        // MultiSiteSpectrum spectrum_W = aiCapData.spectrum(SpectrumUnit.mW).multiply(1e3);
        MultiSiteDouble dPwrTotal_new = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSignal = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSpurious = new MultiSiteDouble(0.0);
        MultiSiteDouble dSFDR1 = new MultiSiteDouble(0.0);
        MultiSiteDouble dSND1 = new MultiSiteDouble(0.0);
        // List<MultiSiteDouble> result = new ArrayList<MultiSiteDouble>(); //add return can add
        // this line
        MultiSiteDouble[] result1 = new MultiSiteDouble[2];
        int iSpuriousBin = 0;
        double dPwrTotal = 0.0;

        for (int iSite : activeSites) {
            for (int i = iBeginBins; i < 2 * iEndBins + 1; i++) {

                if ((adSpectrum.getValue(iSite, i) > dPwrSpurious.get(
                        iSite)) && i != iFundBins && i != (iFundBins - 1) && i != (iFundBins + 1)) {
                    iSpuriousBin = 0;
                    if (i != 256) {

                        dPwrSpurious.set(iSite, adSpectrum.get(iSite).getValue(iSpuriousBin)); //
                    }
                }
                if (i >= iBeginBins && i <= iEndBins) {
                    if (i != 256) {
                        dPwrTotal += adSpectrum.getValue(iSite, i); // Remove BBPLL spur at Tone 256
                        dPwrTotal_new.set(iSite, dPwrTotal);
                    }
                }

            }
        }
        if (offline1) {
            System.out.println("offline execute!!!!!!!!!!!!!!!!!");
            dSND1.set(999.0);
            dSFDR1.set(999.0);
            result.add(dSFDR1);
            result.add(dSND1);
            // System.out.println("SFDR = " +dSFDR1 );
            // System.out.println("dSND = " +dSND1 );

        } else {

            dPwrSignal = adSpectrum.getValue(iFundBins);
            dSFDR1 = (dPwrSignal.divide(dPwrSpurious)).log10().multiply(10);

            dSND1 = ((dPwrSignal).divide(dPwrTotal_new.subtract(dPwrSignal))).log10().multiply(10);

            result.add(dSFDR1);
            result.add(dSND1);
            result1[0] = dSFDR1;
            result1[1] = dSFDR1;
            // System.out.println("isDebug1 = " +isDebug1 );
            if (UserCommon.debug_flag) {
                adSpectrum.plot(tsName + "DAC Spectrum");

                System.out.println("Spurious Bin = " + iSpuriousBin);
                System.out.println("dPwrSpurious Bin = " + dPwrSpurious);
                System.out.println("SFDR  = " + dSFDR1);
                System.out.println("dSND  = " + dSND1);

            }

        }
        result.size();
        // System.out.println("result size============="+result.size());
        // System.out.println("tsName============="+tsName);

        // return result;
        // return result1;
    }

    public static void ModPower_measure(ISetupRfMeas rfMeas, double rfFreq, long Samples,
            double rfmeas_power, double interestbw, double ifFreq, String rfmeas_action,
            double rfsample_rate, double PARA, String windonw_type) {

        IModPower measModPower = rfMeas.modPower(rfmeas_action);
        measModPower.setFrequency(rfFreq).setExpectedMaxPower(rfmeas_power).setSamples(Samples);
        measModPower.setPeakToAvgPowerRatio(PARA).setResultAveraging(1);
        measModPower.setIfFrequency(ifFreq).setSampleRate(rfsample_rate).downConversion()
                .setIqConversion(true);
        if (windonw_type.equals("rectangular")) {
            measModPower.setWindowFunction(SetupWindowFunction.rectangular);
        } else if (windonw_type.equals("hamming")) {

            measModPower.setWindowFunction(SetupWindowFunction.hamming);
        } else if (windonw_type.equals("flatTop")) {

            measModPower.setWindowFunction(SetupWindowFunction.flatTop);
        } else {

            measModPower.setWindowFunction(SetupWindowFunction.hanning);
        }
        measModPower.setBandwidthOfInterest(interestbw);
        measModPower.setLoSide(SetupLoSide.high);// SMT7 is LOabovesignal is true ,as a reuslt
                                                 // mirrorFrequency spectrum need to set true, this
                                                 // is different SMT8 setup
        // measCwPower.setResultAveraging(100);
        // message(2,"[TX_Power] rfFreq = " + rfFreq);

    }

    public static void ModPower_measure_loaboverffalse(ISetupRfMeas rfMeas, double rfFreq,
            long Samples, double rfmeas_power, double interestbw, double ifFreq,
            String rfmeas_action, double rfsample_rate, double PARA, String windonw_type) {

        IModPower measModPower = rfMeas.modPower(rfmeas_action);
        measModPower.setFrequency(rfFreq).setExpectedMaxPower(rfmeas_power).setSamples(Samples);
        measModPower.setPeakToAvgPowerRatio(PARA);
        measModPower.setIfFrequency(ifFreq).setSampleRate(rfsample_rate);
        if (windonw_type.equals("rectangular")) {
            measModPower.setWindowFunction(SetupWindowFunction.rectangular);
        } else if (windonw_type.equals("hamming")) {

            measModPower.setWindowFunction(SetupWindowFunction.hamming);
        } else if (windonw_type.equals("flatTop")) {

            measModPower.setWindowFunction(SetupWindowFunction.flatTop);
        } else {

            measModPower.setWindowFunction(SetupWindowFunction.hanning);
        }
        measModPower.setBandwidthOfInterest(interestbw);
        measModPower.setLoSide(SetupLoSide.low);// SMT7 is LOabovesignal is true ,as a reuslt
                                                // mirrorFrequency spectrum need to set true, this
                                                // is different SMT8 setup
        // measCwPower.setResultAveraging(100);
        // message(2,"[TX_Power] rfFreq = " + rfFreq);

    }

    public static List<MultiSiteDouble> TX_PwerDsp(MultiSiteWaveComplex waveforms, double binoffset,
            List<MultiSiteDouble> result, int[] activeSites, boolean offline1, String tsName,
            boolean isDebug1, String windonw_type) {

        MultiSiteSpectrum spectrum = waveforms.spectrum(SpectrumUnit.dBm);
        if (UserCommon.debug_flag) {
            // if(true){
            spectrum.plot("TX cw Spectrum_dbm");

        }
        double resBW = spectrum.getSampleRate().get() / spectrum.getSize().get();// spectrum.getResolutionBandwidth();
        // MultiSiteLong maxindex= spectrum.maxIndex();
        // spectrum.get().maxIndex();

        if (UserCommon.debug_flag) {
            // System.out.println("maxindex: " + spectrum.maxIndex());
            // System.out.println("SR: " + spectrum.getSampleRate());
            // System.out.println("Size: " + spectrum.getSize());
            // System.out.println("resBW: " + resBW);
            // System.out.println("binOffset: " + binoffset);
        }
        MultiSiteDouble measuredPower1 = new MultiSiteDouble();
        MultiSiteDouble measuredLOSupp1 = new MultiSiteDouble();
        MultiSiteDouble measuredSSBSupp1 = new MultiSiteDouble();

        int binOffset_index_old = (int) ((binoffset / resBW));
        int cwIndex_old = spectrum.getSize().get().intValue() / 2 - 1; // get LO corresponding index
        int lsbIndex_old = cwIndex_old - binOffset_index_old; // get the lsb bin
        int usbIndex_old = cwIndex_old + binOffset_index_old; // get the usb bin

        // if different has different maxindex, the following is effective
        // MultiSiteLong binOffset_index= new MultiSiteLong();
        // MultiSiteLong cwIndex= new MultiSiteLong();
        // MultiSiteLong lsbIndex= new MultiSiteLong();
        // MultiSiteLong usbIndex= new MultiSiteLong();
        // long aa=(long)(binoffset/resBW);
        // binOffset_index.set(aa);
        // cwIndex.set(maxindex.add(binOffset_index));
        // lsbIndex.set(maxindex);
        // usbIndex.set(cwIndex.add(binOffset_index));

        if (offline1) {
            // System.out.println("offline run: " );
            measuredPower1.set(999);
            measuredLOSupp1.set(999);
            measuredSSBSupp1.set(999);

        } else {
            MultiSiteLong length1 = new MultiSiteLong();
            length1.set(6);
            int leng = 10;
            if (windonw_type.equals("rectangular")) {
                // measuredPower1= spectrum.getValue(lsbIndex);
                // measuredLOSupp1=
                // spectrum.getValue(lsbIndex).subtract(spectrum.getValue(cwIndex));
                // measuredSSBSupp1=
                // spectrum.getValue(lsbIndex).subtract(spectrum.getValue(usbIndex));

                // measuredPower1= spectrum.getValue(maxindex);
                // measuredLOSupp1=
                // spectrum.getValue(maxindex).subtract(spectrum.getValue(cwIndex));
                // measuredSSBSupp1=
                // spectrum.getValue(maxindex).subtract(spectrum.getValue(usbIndex));

                measuredPower1 = spectrum.extractValues(lsbIndex_old - leng, 2 * leng).max();
                measuredLOSupp1 = measuredPower1
                        .subtract(spectrum.extractValues(cwIndex_old - leng, 2 * leng).max());
                measuredSSBSupp1 = measuredPower1
                        .subtract(spectrum.extractValues(usbIndex_old - leng, 2 * leng).max());

                // if(UserCommon.debug_flag)
                if (false) {
                    System.out.println("maxindex: " + spectrum.maxIndex());
                    // System.out.println("SR: " + spectrum.getSampleRate());
                    // System.out.println("Size: " + spectrum.getSize());
                    // System.out.println("resBW: " + resBW);
                    System.out.println("binOffset: " + binOffset_index_old);
                    System.out.println("lsbIndex_old: " + lsbIndex_old);
                    System.out.println("cwIndex_old: " + cwIndex_old);
                    System.out.println("usbIndex_old: " + usbIndex_old);
                    System.out.println("lsb: " + spectrum
                            .extractValues(lsbIndex_old - leng, 2 * leng).maxIndex());
                    System.out.println("cw: " + spectrum.extractValues(cwIndex_old - leng, 2 * leng)
                            .maxIndex());
                    System.out.println("usb: " + spectrum
                            .extractValues(usbIndex_old - leng, 2 * leng).maxIndex());
                }

                // System.out.println("meapower ======== "+measuredPower1);
            } else {
                MultiSiteSpectrum spectrum_mW = waveforms.spectrum(SpectrumUnit.mW);

                // measuredPower1=spectrum_mW.extractValues(lsbIndex-3, 6).sum();
                // measuredPower1 =(measuredPower1.log10().multiply(10.0)); //unit is dbm
                //
                // measuredLOSupp1=spectrum_mW.extractValues(lsbIndex-3,
                // 6).sum().subtract(spectrum_mW.extractValues(cwIndex-3, 6).sum());
                //
                // measuredLOSupp1 =(measuredLOSupp1.log10().multiply(10.0)); //unit is dbm
                //
                //
                // measuredSSBSupp1=spectrum_mW.extractValues(lsbIndex-3,
                // 6).sum().subtract(spectrum_mW.extractValues(usbIndex-3, 6).sum());
                //
                // measuredSSBSupp1 =(measuredSSBSupp1.log10().multiply(10.0)); //unit is dbm

                // measuredPower1=spectrum_mW.extractValues(lsbIndex.subtract(3), length1).sum();
                // measuredPower1 =(measuredPower1.log10().multiply(10.0)); //unit is dbm
                //
                // measuredLOSupp1=measuredPower1.subtract((spectrum_mW.extractValues(cwIndex.subtract(3),
                // length1).sum()).log10().multiply(10.0));
                // measuredSSBSupp1=measuredPower1.subtract((spectrum_mW.extractValues(usbIndex.subtract(3),
                // length1).sum()).log10().multiply(10.0));

            }
        }
        result.add(measuredPower1);
        result.add(measuredLOSupp1);
        result.add(measuredSSBSupp1);

        return result;
    }

    public static List<MultiSiteDouble> TX_WLAN_PwerDsp(MultiSiteWaveComplex waveforms,
            double binoffset, List<MultiSiteDouble> result, int[] activeSites, boolean offline1,
            String tsName, boolean isDebug1, String windonw_type) {

        MultiSiteSpectrum spectrum = waveforms.spectrum(SpectrumUnit.dBm);

        if (UserCommon.debug_flag) {
            spectrum.plot("TX cw Spectrum_dbm");

        }
        double resBW = spectrum.getSampleRate().get() / spectrum.getSize().get();// spectrum.getResolutionBandwidth();
        // MultiSiteLong maxindex= spectrum.maxIndex();
        // spectrum.get().maxIndex();

        if (UserCommon.debug_flag) {
            System.out.println("maxindex: " + spectrum.maxIndex());
            System.out.println("SR: " + spectrum.getSampleRate());
            System.out.println("Size: " + spectrum.getSize());
            System.out.println("resBW: " + resBW);
            System.out.println("binOffset: " + binoffset);
        }
        MultiSiteDouble measuredPower1 = new MultiSiteDouble();
        MultiSiteDouble measuredLOSupp1 = new MultiSiteDouble();
        MultiSiteDouble measuredSSBSupp1 = new MultiSiteDouble();

        int binOffset_index = (int) ((binoffset / resBW));
        binOffset_index = 977;
        int cwIndex = spectrum.getSize().get().intValue() / 2 - 1; // get LO corresponding index
        int lsbIndex = cwIndex - binOffset_index; // get the lsb bin
        int usbIndex = cwIndex + binOffset_index; // get the usb bin

        MultiSiteLong cwIndex_new = new MultiSiteLong();
        MultiSiteLong lsbIndex_new = new MultiSiteLong();
        MultiSiteLong usbIndex_new = new MultiSiteLong();
        int offset1 = 10;
        // int length1=10;
        // int offset2=5;
        // int length2=10;
        if (offline1) {
            cwIndex = 0;
            usbIndex = 0;
        } else {
            for (int sites : activeSites) {
                lsbIndex_new.set(sites, lsbIndex - offset1 + spectrum.get(sites)
                        .extractValues(lsbIndex - offset1, 2 * offset1).maxIndex());
                cwIndex_new.set(sites, cwIndex - offset1 + spectrum.get(sites)
                        .extractValues(cwIndex - offset1, 2 * offset1).maxIndex());
                usbIndex_new.set(sites, usbIndex - offset1 + spectrum.get(sites)
                        .extractValues(usbIndex - offset1, 2 * offset1).maxIndex());

                // cwIndex.set(sites, cwIndex.get(sites).intValue()-offset1 +
                // spectrum.get(sites).extractValues(cwIndex.get(sites).intValue()-offset1,length1).maxIndex());
                // usbIndex.set(sites, usbIndex.get(sites).intValue()-offset2 +
                // spectrum.get(sites).extractValues(usbIndex.get(sites).intValue()-offset2,length2).maxIndex());

            }
        }
        // maxindex= spectrum.extractValues(lsbIndex.subtract(30), 60);

        // System.out.println("binoffset="+binOffset_index);
        // System.out.println("lsbIndex="+lsbIndex);
        // System.out.println("cwIndex="+cwIndex);
        // System.out.println("usbIndex="+usbIndex);
        //
        // System.out.println("lsbIndex="+lsbIndex_new);
        // System.out.println("cwIndex="+cwIndex_new);
        // System.out.println("usbIndex="+usbIndex_new);
        if (offline1) {
            // System.out.println("offline run: " );
            measuredPower1.set(999);
            measuredLOSupp1.set(999);
            measuredSSBSupp1.set(999);

        } else {
            MultiSiteLong length3 = new MultiSiteLong();
            // int leng=2;
            if (windonw_type.equals("rectangular")) {
                length3.set(2);
                // measuredPower1= spectrum.getValue(lsbIndex);
                // measuredLOSupp1=
                // spectrum.getValue(lsbIndex).subtract(spectrum.getValue(cwIndex));
                // measuredSSBSupp1=
                // spectrum.getValue(lsbIndex).subtract(spectrum.getValue(usbIndex));

                // add 6 frequency bins avoid frequency shift,SMT7 has this requriment

                MultiSiteSpectrum spectrum_mW = waveforms.spectrum(SpectrumUnit.mW);

                measuredPower1 = spectrum_mW.extractValues(lsbIndex_new.subtract(0), length3).sum();
                measuredPower1 = (measuredPower1.log10().multiply(10.0)); // unit is dbm

                measuredLOSupp1 = measuredPower1
                        .subtract((spectrum_mW.getValue(cwIndex_new)).log10().multiply(10.0));

                measuredSSBSupp1 = measuredPower1
                        .subtract((spectrum_mW.getValue(usbIndex_new)).log10().multiply(10.0));

                // System.out.println("wlan measuredLOSupp1="+measuredLOSupp1);
                // System.out.println("wlan measuredSSBSupp1="+measuredSSBSupp1);

            } else {
                // MultiSiteSpectrum spectrum_mW = waveforms.spectrum(SpectrumUnit.mW);
                // length3.set(6);
                // measuredPower1=spectrum_mW.extractValues(lsbIndex.subtract(3), length3).sum();
                // measuredPower1 =(measuredPower1.log10().multiply(10.0)); //unit is dbm
                //
                //
                // measuredLOSupp1=measuredPower1.subtract((spectrum_mW.extractValues(cwIndex.subtract(length3.divide(2)),
                // length3).sum()).log10().multiply(10.0));
                //
                //
                // measuredSSBSupp1=measuredPower1.subtract((spectrum_mW.extractValues(usbIndex.subtract(length3.divide(2)),
                // length3).sum()).log10().multiply(10.0));
                //
                //// measuredSSBSupp1=measuredPower1.subtract((spectrum_mW.getValue(usbIndex)).log10().multiply(10.0));

            }
        }
        result.add(measuredPower1);
        result.add(measuredLOSupp1);
        result.add(measuredSSBSupp1);

        return result;
    }

    public static List<MultiSiteDouble> TX_PwerDsp_BT(MultiSiteWaveComplex waveforms,
            double binoffset, List<MultiSiteDouble> result, int[] activeSites, boolean offline1,
            String tsName, boolean isDebug1, String windonw_type) {

        MultiSiteSpectrum spectrum = waveforms.spectrum(SpectrumUnit.dBm);
        // MultiSiteSpectrum spectrum = waveforms.spectrum(SpectrumUnit.mW);
        if (UserCommon.debug_flag) {
            spectrum.plot("TX cw Spectrum_dbm");

        }
        double resBW = spectrum.getSampleRate().get() / spectrum.getSize().get();// spectrum.getResolutionBandwidth();
        MultiSiteLong maxindex = spectrum.maxIndex();
        // spectrum.get().maxIndex();

        if (UserCommon.debug_flag) {
            System.out.println("maxindex: " + spectrum.maxIndex());
            System.out.println("SR: " + spectrum.getSampleRate());
            System.out.println("Size: " + spectrum.getSize());
            System.out.println("resBW: " + resBW);
            System.out.println("binOffset: " + binoffset);
        }

        MultiSiteDouble measuredPower1 = new MultiSiteDouble();
        MultiSiteDouble measuredLOSupp1 = new MultiSiteDouble();
        MultiSiteDouble measuredSSBSupp1 = new MultiSiteDouble();

        // int binOffset_index = (int) ((binoffset/resBW));
        // int cwIndex = spectrum.getSize().get().intValue()/2-1; //get LO corresponding index
        // int lsbIndex = cwIndex - binOffset_index; //get the lsb bin
        // int usbIndex = cwIndex + binOffset_index; //get the usb bin

        // if different has different maxindex, the following is effective
        MultiSiteLong binOffset_index = new MultiSiteLong();
        MultiSiteLong cwIndex = new MultiSiteLong();
        MultiSiteLong lsbIndex = new MultiSiteLong();
        MultiSiteLong usbIndex = new MultiSiteLong();
        long aa = (long) (binoffset / resBW);
        aa = 1016;
        binOffset_index.set(aa);
        cwIndex.set(maxindex.add(binOffset_index));
        lsbIndex.set(maxindex);
        usbIndex.set(cwIndex.add(binOffset_index));

        int offset1 = 5;
        int length1 = 10;
        int offset2 = 5;
        int length2 = 10;
        for (int sites : activeSites) {

            cwIndex.set(sites, cwIndex.get(sites).intValue() - offset1 + spectrum.get(sites)
                    .extractValues(cwIndex.get(sites).intValue() - offset1, length1).maxIndex());
            usbIndex.set(sites, usbIndex.get(sites).intValue() - offset2 + spectrum.get(sites)
                    .extractValues(usbIndex.get(sites).intValue() - offset2, length2).maxIndex());

        }
        // System.out.println("================bt==");
        // System.out.println("lsbIndex=="+lsbIndex);
        // System.out.println("cwindex=="+cwIndex);
        // System.out.println("usbIndex=="+usbIndex);

        if (offline1) {
            System.out.println("offline run: ");
            measuredPower1.set(999);
            measuredLOSupp1.set(999);
            measuredSSBSupp1.set(999);

        } else {
            MultiSiteLong length3 = new MultiSiteLong();

            if (windonw_type.equals("rectangular")) {
                length3.set(2);
                // measuredPower1= spectrum.getValue(lsbIndex);
                // measuredLOSupp1=
                // spectrum.getValue(lsbIndex).subtract(spectrum.getValue(cwIndex));
                // measuredSSBSupp1=
                // spectrum.getValue(lsbIndex).subtract(spectrum.getValue(usbIndex));

                // MultiSiteSpectrum spectrum_mW = waveforms.spectrum(SpectrumUnit.mW);
                // measuredPower1=spectrum_mW.extractValues(lsbIndex.subtract(0), length3).sum();
                // measuredPower1 =(measuredPower1.log10().multiply(10.0)); //unit is dbm
                // measuredLOSupp1= measuredPower1.subtract(spectrum.getValue(cwIndex));
                // measuredSSBSupp1= measuredPower1.subtract(spectrum.getValue(usbIndex));

                measuredPower1 = spectrum.getValue(maxindex);
                measuredLOSupp1 = measuredPower1.subtract(spectrum.getValue(cwIndex));
                measuredSSBSupp1 = measuredPower1.subtract(spectrum.getValue(usbIndex));

                // System.out.println("bt measuredLOSupp1="+measuredLOSupp1);
                // System.out.println("bt measuredSSBSupp1="+measuredSSBSupp1);
                // System.out.println("meapower ======== "+measuredPower1);
            } else {
                MultiSiteSpectrum spectrum_mW = waveforms.spectrum(SpectrumUnit.mW);
                length3.set(6);
                // measuredPower1=spectrum_mW.extractValues(lsbIndex-3, 6).sum();
                // measuredPower1 =(measuredPower1.log10().multiply(10.0)); //unit is dbm
                //
                // measuredLOSupp1=spectrum_mW.extractValues(lsbIndex-3,
                // 6).sum().subtract(spectrum_mW.extractValues(cwIndex-3, 6).sum());
                //
                // measuredLOSupp1 =(measuredLOSupp1.log10().multiply(10.0)); //unit is dbm
                //
                //
                // measuredSSBSupp1=spectrum_mW.extractValues(lsbIndex-3,
                // 6).sum().subtract(spectrum_mW.extractValues(usbIndex-3, 6).sum());
                //
                // measuredSSBSupp1 =(measuredSSBSupp1.log10().multiply(10.0)); //unit is dbm

                measuredPower1 = spectrum_mW.extractValues(lsbIndex.subtract(3), length3).sum();
                measuredPower1 = (measuredPower1.log10().multiply(10.0)); // unit is dbm

                // measuredLOSupp1=measuredPower1.subtract((spectrum_mW.extractValues(cwIndex.subtract(3),
                // length1).sum()).log10().multiply(10.0));
                // measuredSSBSupp1=measuredPower1.subtract((spectrum_mW.extractValues(usbIndex.subtract(3),
                // length1).sum()).log10().multiply(10.0));

                measuredLOSupp1 = measuredPower1.subtract(
                        (spectrum_mW.extractValues(cwIndex.subtract(length3.divide(2)), length3)
                                .sum()).log10().multiply(10.0));

                measuredSSBSupp1 = measuredPower1.subtract(
                        (spectrum_mW.extractValues(usbIndex.subtract(length3.divide(2)), length3)
                                .sum()).log10().multiply(10.0));

            }
        }
        result.add(measuredPower1);
        result.add(measuredLOSupp1);
        result.add(measuredSSBSupp1);

        return result;
    }

    public static List<MultiSiteDouble> TX_PwerDsp_BT_new(MultiSiteWaveComplex waveforms,
            double binoffset, List<MultiSiteDouble> result, int[] activeSites, boolean offline1,
            String tsName, boolean isDebug1, String windonw_type) {

        MultiSiteSpectrum spectrum = waveforms.spectrum(SpectrumUnit.dBm);
        // MultiSiteSpectrum spectrum = waveforms.spectrum(SpectrumUnit.mW);
        if (UserCommon.debug_flag) {
            spectrum.plot("TX cw Spectrum_dbm");

        }
        double resBW = spectrum.getSampleRate().get() / spectrum.getSize().get();// spectrum.getResolutionBandwidth();
        // MultiSiteLong maxindex= spectrum.maxIndex();
        // spectrum.get().maxIndex();

        if (UserCommon.debug_flag) {
            System.out.println("maxindex: " + spectrum.maxIndex());
            System.out.println("SR: " + spectrum.getSampleRate());
            System.out.println("Size: " + spectrum.getSize());
            System.out.println("resBW: " + resBW);
            System.out.println("binOffset: " + binoffset);
        }
        MultiSiteDouble measuredPower = new MultiSiteDouble();
        MultiSiteDouble measuredPower1 = new MultiSiteDouble();
        MultiSiteDouble measuredLOSupp1 = new MultiSiteDouble();
        MultiSiteDouble measuredSSBSupp1 = new MultiSiteDouble();

        int binOffset_index = (int) ((binoffset / resBW));
        binOffset_index = 1016;
        int cwIndex = spectrum.getSize().get().intValue() / 2 - 1; // get LO corresponding index
        int lsbIndex = cwIndex - binOffset_index; // get the lsb bin
        int usbIndex = cwIndex + binOffset_index; // get the usb bin

        MultiSiteLong cwIndex_new = new MultiSiteLong();
        MultiSiteLong lsbIndex_new = new MultiSiteLong();
        MultiSiteLong usbIndex_new = new MultiSiteLong();
        int offset1 = 10;

        if (offline1) {
            cwIndex = 0;
            usbIndex = 0;
        } else {
            for (int sites : activeSites) {
                lsbIndex_new.set(sites, lsbIndex - offset1 + spectrum.get(sites)
                        .extractValues(lsbIndex - offset1, 2 * offset1).maxIndex());
                cwIndex_new.set(sites, cwIndex - offset1 + spectrum.get(sites)
                        .extractValues(cwIndex - offset1, 2 * offset1).maxIndex());
                usbIndex_new.set(sites, usbIndex - offset1 + spectrum.get(sites)
                        .extractValues(usbIndex - offset1, 2 * offset1).maxIndex());

            }
        }

        // System.out.println("binoffset="+binOffset_index);
        // System.out.println("lsbIndex="+lsbIndex);
        // System.out.println("cwIndex="+cwIndex);
        // System.out.println("usbIndex="+usbIndex);
        //
        // System.out.println("lsbIndex="+lsbIndex_new);
        // System.out.println("cwIndex="+cwIndex_new);
        // System.out.println("usbIndex="+usbIndex_new);
        if (offline1) {
            System.out.println("offline run: ");
            measuredPower1.set(999);
            measuredLOSupp1.set(999);
            measuredSSBSupp1.set(999);

        } else {
            MultiSiteLong length3 = new MultiSiteLong();

            if (windonw_type.equals("rectangular")) {
                length3.set(3);
                // measuredPower1= spectrum.getValue(lsbIndex);
                // measuredLOSupp1=
                // spectrum.getValue(lsbIndex).subtract(spectrum.getValue(cwIndex));
                // measuredSSBSupp1=
                // spectrum.getValue(lsbIndex).subtract(spectrum.getValue(usbIndex));

                MultiSiteSpectrum spectrum_mW = waveforms.spectrum(SpectrumUnit.mW);
                measuredPower = spectrum_mW.extractValues(lsbIndex_new.subtract(1), length3).sum();
                measuredPower = (measuredPower.log10().multiply(10.0)); // unit is dbm
                // measuredLOSupp1= measuredPower1.subtract(spectrum.getValue(cwIndex));
                // measuredSSBSupp1= measuredPower1.subtract(spectrum.getValue(usbIndex));

                measuredPower1 = spectrum.getValue(lsbIndex_new);
                measuredLOSupp1 = measuredPower1.subtract(spectrum.getValue(cwIndex_new));
                measuredSSBSupp1 = measuredPower1.subtract(spectrum.getValue(usbIndex_new));

                // System.out.println("bt measuredLOSupp1="+measuredLOSupp1);
                // System.out.println("bt measuredSSBSupp1="+measuredSSBSupp1);
                // System.out.println("meapower ======== "+measuredPower1);
            } else {
                // MultiSiteSpectrum spectrum_mW = waveforms.spectrum(SpectrumUnit.mW);
                // length3.set(6);
                //// measuredPower1=spectrum_mW.extractValues(lsbIndex-3, 6).sum();
                //// measuredPower1 =(measuredPower1.log10().multiply(10.0)); //unit is dbm
                ////
                //// measuredLOSupp1=spectrum_mW.extractValues(lsbIndex-3,
                // 6).sum().subtract(spectrum_mW.extractValues(cwIndex-3, 6).sum());
                ////
                //// measuredLOSupp1 =(measuredLOSupp1.log10().multiply(10.0)); //unit is dbm
                ////
                ////
                //// measuredSSBSupp1=spectrum_mW.extractValues(lsbIndex-3,
                // 6).sum().subtract(spectrum_mW.extractValues(usbIndex-3, 6).sum());
                ////
                //// measuredSSBSupp1 =(measuredSSBSupp1.log10().multiply(10.0)); //unit is dbm
                //
                //
                // measuredPower1=spectrum_mW.extractValues(lsbIndex.subtract(3), length3).sum();
                // measuredPower1 =(measuredPower1.log10().multiply(10.0)); //unit is dbm
                //
                //// measuredLOSupp1=measuredPower1.subtract((spectrum_mW.extractValues(cwIndex.subtract(3),
                // length1).sum()).log10().multiply(10.0));
                //// measuredSSBSupp1=measuredPower1.subtract((spectrum_mW.extractValues(usbIndex.subtract(3),
                // length1).sum()).log10().multiply(10.0));
                //
                // measuredLOSupp1=measuredPower1.subtract((spectrum_mW.extractValues(cwIndex.subtract(length3.divide(2)),
                // length3).sum()).log10().multiply(10.0));
                //
                //
                // measuredSSBSupp1=measuredPower1.subtract((spectrum_mW.extractValues(usbIndex.subtract(length3.divide(2)),
                // length3).sum()).log10().multiply(10.0));

            }
        }
        result.add(measuredPower1);
        result.add(measuredLOSupp1);
        result.add(measuredSSBSupp1);
        result.add(measuredPower);

        return result;
    }

    // the following type is wrong because the para is base type not class type

    public static void SFDRCalc_B(MultiSiteWaveDouble aiCapData, int iFundBins, int iBeginBins,
            int iEndBins, MultiSiteDouble dSFDR1, MultiSiteDouble dSND1, int[] activeSites,
            boolean offline1, String tsName, boolean isDebug1) {
        // public static MultiSiteDouble[] SFDRCalc (MultiSiteWaveDouble aiCapData, int
        // iFundBins,int iBeginBins,int iEndBins,int[] activeSites ) {

        // System.out.println("===================versioon B================");
        MultiSiteSpectrum adSpectrum = aiCapData.spectrum(SpectrumUnit.mW);
        // MultiSiteSpectrum spectrum_W = aiCapData.spectrum(SpectrumUnit.mW).multiply(1e3);
        MultiSiteDouble dPwrTotal_new = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSignal = new MultiSiteDouble(0.0);
        MultiSiteDouble dPwrSpurious = new MultiSiteDouble(0.0);
        // MultiSiteDouble dSFDR1=new MultiSiteDouble(0.0);
        // MultiSiteDouble dSND1=new MultiSiteDouble(0.0);
        // List<MultiSiteDouble> result = new ArrayList<MultiSiteDouble>(); //add return can add
        // this line
        MultiSiteDouble[] result1 = new MultiSiteDouble[2];
        int iSpuriousBin = 0;
        double dPwrTotal = 0.0;

        for (int iSite : activeSites) {
            for (int i = iBeginBins; i < 2 * iEndBins + 1; i++) {

                if ((adSpectrum.getValue(iSite, i) > dPwrSpurious.get(
                        iSite)) && i != iFundBins && i != (iFundBins - 1) && i != (iFundBins + 1)) {
                    iSpuriousBin = 0;
                    if (i != 256) {

                        dPwrSpurious.set(iSite, adSpectrum.get(iSite).getValue(iSpuriousBin)); //
                    }
                }
                if (i >= iBeginBins && i <= iEndBins) {
                    if (i != 256) {
                        dPwrTotal += adSpectrum.getValue(iSite, i); // Remove BBPLL spur at Tone 256
                        dPwrTotal_new.set(iSite, dPwrTotal);
                    }
                }

            }
        }
        // System.out.println("===================versioon B================");
        if (offline1) {
            System.out.println("offline execute");
            dSND1.set(999.0);
            dSFDR1.set(999.0);
            // System.out.println("SFDR = " +dSFDR1 );
            // System.out.println("dSND = " +dSND1 );

            // System.out.println("===================versioon B================");

        } else {

            dPwrSignal = adSpectrum.getValue(iFundBins);
            dSFDR1 = (dPwrSignal.divide(dPwrSpurious)).log10().multiply(10);

            dSND1 = ((dPwrSignal).divide(dPwrTotal_new.subtract(dPwrSignal))).log10().multiply(10);

            // System.out.println("===================versioon B================");
            // result1[0]=dSFDR1;
            // result1[1]=dSFDR1;
            dSND1.set(999.0);
            dSFDR1.set(999.0);
            if (UserCommon.debug_flag) {
                adSpectrum.plot(tsName + "DAC Spectrum");

                System.out.println("Spurious Bin = " + iSpuriousBin);
                System.out.println("dPwrSpurious Bin = " + dPwrSpurious);
                System.out.println("SFDR  = " + dSFDR1);
                System.out.println("dSND  = " + dSND1);

            }

        }
        // System.out.println("===================versioon B================");
        // System.out.println("tsName============="+tsName);
        dSND1.set(999.0);
        dSFDR1.set(999.0);

    }

    public static List<MultiSiteDouble> RX_BT_EVM_new(MultiSiteWaveComplex waveform,
            String packetType, Demodulation demod, List<MultiSiteDouble> result, int[] activeSites,
            boolean offline1, String tsName, boolean isDebug1, String sProjectPath,
            boolean hasgeneratepara) {
        // public static MultiSiteDouble[] SFDRCalc (MultiSiteWaveDouble aiCapData, int
        // iFundBins,int iBeginBins,int iEndBins,int[] activeSites ) {

        MultiSiteDouble dgtSampleRate_Hz = waveform.getSampleRate();
        MultiSiteLong numPoints = waveform.getSize();
        MultiSiteLong payloadStartOffset = new MultiSiteLong();
        MultiSiteLong payloadBits = new MultiSiteLong();
        MultiSiteLong resultLengthInSymbol = new MultiSiteLong();
        MultiSiteLong payloadLength = new MultiSiteLong();
        MultiSiteLong codeWordLength = new MultiSiteLong();
        MultiSiteString flexModulationFormat = new MultiSiteString();
        // System.out.println("samplerate===="+dgtSampleRate_Hz);

        if (UserCommon.debug_flag) {

            waveform.plot("TX_DEVM_FullCapturedWaveform");
            waveform.getReal().plot("TX _DEVM_FullCapturedWaveform_I");
            waveform.getImaginary().plot("TX_DEVM_FullCapturedWaveform_Q");
            // spectrum.plot("TX_DEVM_Full Spectrum:dBm" );

        }
        demod = new Demodulation();
        demod.presetToStandard("Bluetooth");

        MultiSiteString exportpara1 = new MultiSiteString();

        exportpara1.set(sProjectPath + "/rf_wave/WCN_BT_rx_new.para");
        if (hasgeneratepara) {
            demod.readParameterFile(exportpara1);
        } else {

            demod.setInputParameter("ModFormat", flexModulationFormat);// ModFormat
            demod.setInputParameter("FilterAlpha", 0.4); // FilterAlpha
            demod.setInputParameter("MeasurementFilter", 10);// MeasurementFilter ROOT_RAISED_COSINE
            demod.setInputParameter("ReferenceFilter", 8); // ReferenceFilter RAISED_COSINE
            // demod.setInputParameter("CompensateIQOffset", 1); //Added on Jul 5th
            // //CompensateIQOffset
            // demod.setInputParameter("DoDevm", 1); //Added on Jul 5th //DoDevm
            demod.setInputParameter("CodeWordLength", codeWordLength); // CodeWordLength
            demod.setInputParameter("IFFilterBandwidth", (1.0e6)); // IFFilterBandwidth
            demod.setInputParameter("SamplesPerSymbol", 1); // related to constellation plot
                                                            // //SamplesPerSymbol
            demod.setInputParameter("ResultLength", resultLengthInSymbol); // number of symbols
                                                                           // (depends on packet
                                                                           // type) - no trailer //0
            demod.setInputParameter("SyncMode", 0); // origi is 0 continious// 3 continious-syc
                                                    // //SyncMode
            // demod->setInputParameter("bDifferential", FALSE); // must be false - diff done in
            // calculation SMT7 set this para,but SMT8 has no this para

            demod.writeParameterFile(exportpara1);

        }

        waveform.setSampleRate(dgtSampleRate_Hz);
        // *************this part is added for result1***************

        if (UserCommon.debug_flag) {

            waveform.getReal().plot("TX_PayloadWaveform_I");
            waveform.getImaginary().plot("TX_PayloadWaveform_Q");
            waveform.plot("TX_Payload_Cons ");
        }

        demod.execute(waveform);// substract mean value

        MultiSiteDouble devmRms = new MultiSiteDouble(0.0);
        MultiSiteDouble devmPk = new MultiSiteDouble(0.0);

        MultiSiteDouble devm99 = new MultiSiteDouble(0.0);
        MultiSiteDouble devm20 = new MultiSiteDouble(0.0);

        MultiSiteDoubleArray devmRmsBlocks, devmPkBlocks;
        devm99 = demod.getResultParameterDouble("Devm99");
        devm20 = demod.getResultParameterDouble("DevmRmsMax");
        if (true) {

            devmRms = demod.getResultParameterDouble("DevmRmsMax"); // direct get the max value
            devmPk = demod.getResultParameterDouble("DevmPeakMax");

        } else // smt7 adopt this method
        {
            devmRmsBlocks = demod.getResultParameterDoubleArray("DevmRmsPerBlock"); // get each
                                                                                    // block value
            devmPkBlocks = demod.getResultParameterDoubleArray("DevmPeakPerBlock");

            // Find Max RMS and PK from all available blocks (50 symbols per block)
            int i = 0;
            for (int iSite : activeSites) {

                double b = 999;
                double c = 999;
                for (i = 0; i < devmRmsBlocks.length(); ++i) {

                    // cout << "DEVM(RMS) for block " << i << " " << devmRmsBlocks[i] << "%" <<
                    // endl;
                    if (devmRms.get(iSite) < devmRmsBlocks.getElement(iSite, i)) {

                        // b = devmRmsBlocks.getElement(iSite, i);
                        devmRms.set(iSite, devmRmsBlocks.getElement(iSite, i));
                    }
                    if (devmPk.get(iSite) < devmPkBlocks.getElement(iSite, i)) {
                        // cout << "DEVM(Pk) for block " << i << " " << devmPkBlocks[i] << "%" <<
                        // endl;
                        // c = devmPkBlocks.getElement(iSite, i);
                        devmPk.set(iSite, devmPkBlocks.getElement(iSite, i));
                    }
                }

                // devmRms.set(iSite, b);
                // devmPk.set(iSite, c);

            }
        }

        result.add(devmRms);
        result.add(devmPk);
        if (UserCommon.debug_flag) {
            System.out.println("DEVM(RMS,MAX) =" + devmRms);
            System.out.println("DEVM(PK,MAX)   =" + devmPk);
            System.out.println("DEVM(99%)     =" + devm99);
            System.out.println("DEVM(PK,<20%) =" + devm20); // ratio of devmPk < 20%
            demod.plot("EVM_BT" + ": constellation", tsName, "Constellation");
        }

        return result;
    }

    public static MultiSiteWaveComplex RX_BT_EVM(MultiSiteWaveComplex waveform, String packetType,
            int[] activeSites) {
        // public static MultiSiteDouble[] SFDRCalc (MultiSiteWaveDouble aiCapData, int
        // iFundBins,int iBeginBins,int iEndBins,int[] activeSites ) {

        MultiSiteDouble dgtSampleRate_Hz = waveform.getSampleRate();
        MultiSiteLong numPoints = waveform.getSize();
        MultiSiteLong payloadStartOffset = new MultiSiteLong();
        MultiSiteLong payloadBits = new MultiSiteLong();
        MultiSiteLong resultLengthInSymbol = new MultiSiteLong();
        MultiSiteLong payloadLength = new MultiSiteLong();
        MultiSiteLong codeWordLength = new MultiSiteLong();
        MultiSiteString flexModulationFormat = new MultiSiteString();
        // System.out.println("samplerate===="+dgtSampleRate_Hz);

        if (packetType.equals("3DH5")) {
            // cout << "---------------- Packet 3DH5 ---------------" << endl;
            codeWordLength.set(3);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(8136); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");
        } else if (packetType.equals("3DH3")) {
            // cout << "---------------- Packet 3DH3 ---------------" << endl;

            codeWordLength.set(3);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(4392); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");

        } else if (packetType.equals("3DH1")) {
            // cout << "---------------- Packet 3DH1 ---------------" << endl;

            codeWordLength.set(3);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(648); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");

        } else if (packetType.equals("2DH5")) {
            // cout << "---------------- Packet 2DH5 ---------------" << endl;

            codeWordLength.set(2);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(5424); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");

        } else if (packetType.equals("2DH3")) {
            // cout << "---------------- Packet 2DH3 ---------------" << endl;
            codeWordLength.set(2);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(2928); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");

        } else if (packetType.equals("2DH1")) {
            // cout << "---------------- Packet 2DH1 ---------------" << endl;
            codeWordLength.set(2);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(432); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");

        }
        /****************************************************************************************/

        /********** setup for payload-only DEVM measurements **********/
        else if (packetType.equals("3PayloadOnly")) {
            // cout << "---------------- 8DPSK only ---------------" << endl;
            codeWordLength.set(3);
            payloadStartOffset.set(0); // amount of header bits
            payloadBits.set(1500); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");

        } else if (packetType.equals("2PayloadOnly")) {
            // cout << "---------- PI OVER 4 DQPSK only -----------" << endl;

            codeWordLength.set(2);
            payloadStartOffset.set(0); // amount of header bits
            payloadBits.set(10000); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");
        }
        /****************************************************************************************/
        else {
            System.out.println("ERROR: Unsupported Packet Type!");
            // exit;
        }

        if (UserCommon.debug_flag) {

            waveform.plot("TX_DEVM_FullCapturedWaveform");
            waveform.getReal().plot("TX _DEVM_FullCapturedWaveform_I");
            waveform.getImaginary().plot("TX_DEVM_FullCapturedWaveform_Q");
            // spectrum.plot("TX_DEVM_Full Spectrum:dBm" );

        }

        MultiSiteWaveComplex packet = new MultiSiteWaveComplex();

        MultiSiteDoubleArray cData_mag = new MultiSiteDoubleArray(); // numpoints
        MultiSiteDoubleArray cData_avg15 = new MultiSiteDoubleArray(); // numpoints/2
        MultiSiteWaveDouble cData_mag1_new = new MultiSiteWaveDouble();
        MultiSiteWaveDouble cData_avg15_new = new MultiSiteWaveDouble();
        MultiSiteDouble cData_avg = new MultiSiteDouble();
        MultiSiteLong packet_begin = new MultiSiteLong();
        // two different type defines
        packet.resize(payloadLength);
        if (true) {

            cData_mag = waveform.abs().toMultiSiteArray();
            MultiSiteWaveDouble cData_mid = cData_mag.toMultiSiteWave();
            cData_avg = cData_mid.mean();

        } else {

            cData_mag1_new = waveform.abs();
            cData_avg = cData_mag1_new.mean();
        }

        // MultiSiteLong ModPowerLowDetectFlag =new MultiSiteLong();
        // MultiSiteLong tmpI =new MultiSiteLong();

        int ModPowerLowDetectFlag = 0;
        int tmpI = 0;
        double dd = 0.0;
        int i = 0, ii = 0;

        for (int iSite : activeSites) {

            double[] cData_avg15_site = new double[numPoints.get(iSite).intValue() / 2];
            // cData_avg15_site= cData_avg15.get(iSite);

            for (i = ModPowerLowDetectFlag = 0; i < numPoints
                    .get(iSite) / 2 && ModPowerLowDetectFlag != 2;) {

                for (ii = 0, cData_avg15_site[i] = 0.0; ii < 15; ii++) {

                    cData_avg15_site[i] += cData_mag.getElement(iSite, i + ii);
                }

                cData_avg15_site[i] /= 15;

                if (cData_avg15_site[i] < cData_avg.get(iSite) / 2) {
                    if (ModPowerLowDetectFlag == 0) {
                        ModPowerLowDetectFlag = 1;
                    } else {
                        ModPowerLowDetectFlag = 2;
                        continue; // RX section found (no signal around location i)
                    }
                }
                double sr = dgtSampleRate_Hz.get(iSite);
                tmpI = (int) (sr / 100000);
                i += (int) (sr / 100000); // go in 10us steps to be faster

            }

            for (; i < numPoints.get(iSite); i++) {
                if (cData_mag.getElement(iSite, i) > cData_avg.get(iSite) / 2) {
                    break; // find packet
                }
            }

            int packet_begin_new = i + payloadStartOffset.get(iSite).intValue();

            WaveComplex aa1 = new WaveComplex(payloadLength.get().intValue());// intial and specify
                                                                              // the size
            Complex aa[] = new Complex[payloadLength.get(iSite).intValue()];
            if (UserCommon.debug_flag) {
                System.out.println("packet_begin_new=" + packet_begin_new);
                // System.out.println("waveform="+waveform.getSize(iSite));
                System.out.println("payloadlength=" + payloadLength.get(iSite).intValue());
                System.out.println("waveform=" + waveform.getSize(iSite));
            }
            aa1 = waveform.get(iSite).extractValues(packet_begin_new,
                    payloadLength.get().intValue());
            packet.set(iSite, aa1);

        }

        // *************this part is added for result1***************
        MultiSiteWaveDouble packet_i = new MultiSiteWaveDouble();
        MultiSiteWaveDouble packet_q = new MultiSiteWaveDouble();
        MultiSiteDouble packet_iaverage = new MultiSiteDouble();
        MultiSiteDouble packet_qaverage = new MultiSiteDouble();
        packet_i.set(packet.getReal());

        packet_q.set(packet.getImaginary());

        packet_iaverage.set(packet_i.mean());
        packet_qaverage.set(packet_q.mean());

        packet_i.set(packet_i.subtract(packet_iaverage));// substract mean value
        packet_q.set(packet_q.subtract(packet_qaverage));
        MultiSiteWaveComplex result = new MultiSiteWaveComplex(packet_i, packet_q);

        result.setSampleRate(dgtSampleRate_Hz);
        // *************this part is added for result1***************

        if (UserCommon.debug_flag) {

            packet.getReal().plot("TX_PayloadWaveform_I");
            packet.getImaginary().plot("TX_PayloadWaveform_Q");
            packet.plot("TX_Payload_Cons ");
        }

        return result;
    }

    public static List<MultiSiteDouble> RX_BT_EVM(MultiSiteWaveComplex waveform, String packetType,
            Demodulation demod, List<MultiSiteDouble> result, int[] activeSites, boolean offline1,
            String tsName, boolean isDebug1, String sProjectPath, boolean hasgeneratepara) {
        // public static MultiSiteDouble[] SFDRCalc (MultiSiteWaveDouble aiCapData, int
        // iFundBins,int iBeginBins,int iEndBins,int[] activeSites ) {

        MultiSiteDouble dgtSampleRate_Hz = waveform.getSampleRate();
        MultiSiteLong numPoints = waveform.getSize();
        MultiSiteLong payloadStartOffset = new MultiSiteLong();
        MultiSiteLong payloadBits = new MultiSiteLong();
        MultiSiteLong resultLengthInSymbol = new MultiSiteLong();
        MultiSiteLong payloadLength = new MultiSiteLong();
        MultiSiteLong codeWordLength = new MultiSiteLong();
        MultiSiteString flexModulationFormat = new MultiSiteString();
        // System.out.println("samplerate===="+dgtSampleRate_Hz);

        if (packetType.equals("3DH5")) {
            // cout << "---------------- Packet 3DH5 ---------------" << endl;
            codeWordLength.set(3);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(8136); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");
        } else if (packetType.equals("3DH3")) {
            // cout << "---------------- Packet 3DH3 ---------------" << endl;

            codeWordLength.set(3);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(4392); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");

        } else if (packetType.equals("3DH1")) {
            // cout << "---------------- Packet 3DH1 ---------------" << endl;

            codeWordLength.set(3);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(648); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");

        } else if (packetType.equals("2DH5")) {
            // cout << "---------------- Packet 2DH5 ---------------" << endl;

            codeWordLength.set(2);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(5424); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");

        } else if (packetType.equals("2DH3")) {
            // cout << "---------------- Packet 2DH3 ---------------" << endl;
            codeWordLength.set(2);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(2928); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");

        } else if (packetType.equals("2DH1")) {
            // cout << "---------------- Packet 2DH1 ---------------" << endl;
            codeWordLength.set(2);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(432); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");

        }
        /****************************************************************************************/

        /********** setup for payload-only DEVM measurements **********/
        else if (packetType.equals("3PayloadOnly")) {
            // cout << "---------------- 8DPSK only ---------------" << endl;
            codeWordLength.set(3);
            payloadStartOffset.set(0); // amount of header bits
            payloadBits.set(1500); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");

        } else if (packetType.equals("2PayloadOnly")) {
            // cout << "---------- PI OVER 4 DQPSK only -----------" << endl;

            codeWordLength.set(2);
            payloadStartOffset.set(0); // amount of header bits
            payloadBits.set(10000); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");
        }
        /****************************************************************************************/
        else {
            System.out.println("ERROR: Unsupported Packet Type!");
            // exit;
        }

        if (UserCommon.debug_flag) {

            waveform.plot("TX_DEVM_FullCapturedWaveform");
            waveform.getReal().plot("TX _DEVM_FullCapturedWaveform_I");
            waveform.getImaginary().plot("TX_DEVM_FullCapturedWaveform_Q");
            // spectrum.plot("TX_DEVM_Full Spectrum:dBm" );

        }
        demod = new Demodulation();
        demod.presetToStandard("Bluetooth");

        MultiSiteString exportpara1 = new MultiSiteString();
        // exportpara1.set(sProjectPath+"/rf_wave/WCN_80211n_40M.para") ;
        // demod.writeParameterFile(exportpara1);
        // demod.readParameterFile( exportpara1);
        exportpara1.set(sProjectPath + "/rf_wave/WCN_BT_rx.para");
        if (hasgeneratepara) {
            demod.readParameterFile(exportpara1);
        } else {

            demod.setInputParameter("ModFormat", flexModulationFormat);// ModFormat
            demod.setInputParameter("FilterAlpha", 0.4); // FilterAlpha
            demod.setInputParameter("MeasurementFilter", 10);// MeasurementFilter ROOT_RAISED_COSINE
            demod.setInputParameter("ReferenceFilter", 8); // ReferenceFilter RAISED_COSINE
            // demod.setInputParameter("CompensateIQOffset", 1); //Added on Jul 5th
            // //CompensateIQOffset
            // demod.setInputParameter("DoDevm", 1); //Added on Jul 5th //DoDevm
            demod.setInputParameter("CodeWordLength", codeWordLength); // CodeWordLength
            demod.setInputParameter("IFFilterBandwidth", (1.0e6)); // IFFilterBandwidth
            demod.setInputParameter("SamplesPerSymbol", 1); // related to constellation plot
                                                            // //SamplesPerSymbol
            demod.setInputParameter("ResultLength", resultLengthInSymbol); // number of symbols
                                                                           // (depends on packet
                                                                           // type) - no trailer //0
            demod.setInputParameter("SyncMode", 0); // origi is 0 continious// 3 continious-syc
                                                    // //SyncMode
            // demod->setInputParameter("bDifferential", FALSE); // must be false - diff done in
            // calculation SMT7 set this para,but SMT8 has no this para

            demod.writeParameterFile(exportpara1);

        }

        MultiSiteWaveComplex packet = new MultiSiteWaveComplex();

        MultiSiteDoubleArray cData_mag = new MultiSiteDoubleArray(); // numpoints
        MultiSiteDoubleArray cData_avg15 = new MultiSiteDoubleArray(); // numpoints/2
        MultiSiteWaveDouble cData_mag1_new = new MultiSiteWaveDouble();
        MultiSiteWaveDouble cData_avg15_new = new MultiSiteWaveDouble();
        MultiSiteDouble cData_avg = new MultiSiteDouble();
        MultiSiteLong packet_begin = new MultiSiteLong();
        // two different type defines
        packet.resize(payloadLength);
        if (true) {

            cData_mag = waveform.abs().toMultiSiteArray();
            MultiSiteWaveDouble cData_mid = cData_mag.toMultiSiteWave();
            cData_avg = cData_mid.mean();

        } else {

            cData_mag1_new = waveform.abs();
            cData_avg = cData_mag1_new.mean();
        }

        // MultiSiteLong ModPowerLowDetectFlag =new MultiSiteLong();
        // MultiSiteLong tmpI =new MultiSiteLong();

        int ModPowerLowDetectFlag = 0;
        int tmpI = 0;
        double dd = 0.0;
        int i = 0, ii = 0;

        for (int iSite : activeSites) {

            double[] cData_avg15_site = new double[numPoints.get(iSite).intValue() / 2];
            // cData_avg15_site= cData_avg15.get(iSite);

            for (i = ModPowerLowDetectFlag = 0; i < numPoints
                    .get(iSite) / 2 && ModPowerLowDetectFlag != 2;) {

                for (ii = 0, cData_avg15_site[i] = 0.0; ii < 15; ii++) {

                    cData_avg15_site[i] += cData_mag.getElement(iSite, i + ii);
                }

                cData_avg15_site[i] /= 15;

                if (cData_avg15_site[i] < cData_avg.get(iSite) / 2) {
                    if (ModPowerLowDetectFlag == 0) {
                        ModPowerLowDetectFlag = 1;
                    } else {
                        ModPowerLowDetectFlag = 2;
                        continue; // RX section found (no signal around location i)
                    }
                }
                double sr = dgtSampleRate_Hz.get(iSite);
                tmpI = (int) (sr / 100000);
                i += (int) (sr / 100000); // go in 10us steps to be faster

            }

            for (; i < numPoints.get(iSite); i++) {
                if (cData_mag.getElement(iSite, i) > cData_avg.get(iSite) / 2) {
                    break; // find packet
                }
            }

            int packet_begin_new = i + payloadStartOffset.get(iSite).intValue();

            WaveComplex aa1 = new WaveComplex(payloadLength.get().intValue());// intial and specify
                                                                              // the size
            Complex aa[] = new Complex[payloadLength.get(iSite).intValue()];
            if (UserCommon.debug_flag) {
                System.out.println("packet_begin_new=" + packet_begin_new);
                // System.out.println("waveform="+waveform.getSize(iSite));
                System.out.println("payloadlength=" + payloadLength.get(iSite).intValue());
                System.out.println("waveform=" + waveform.getSize(iSite));
            }
            aa1 = waveform.get(iSite).extractValues(packet_begin_new,
                    payloadLength.get().intValue());
            packet.set(iSite, aa1);

        }

        // *************this part is added for result1***************
        MultiSiteWaveDouble packet_i = new MultiSiteWaveDouble();
        MultiSiteWaveDouble packet_q = new MultiSiteWaveDouble();
        MultiSiteDouble packet_iaverage = new MultiSiteDouble();
        MultiSiteDouble packet_qaverage = new MultiSiteDouble();
        packet_i.set(packet.getReal());

        packet_q.set(packet.getImaginary());

        packet_iaverage.set(packet_i.mean());
        packet_qaverage.set(packet_q.mean());

        packet_i.set(packet_i.subtract(packet_iaverage));// substract mean value
        packet_q.set(packet_q.subtract(packet_qaverage));
        MultiSiteWaveComplex result1 = new MultiSiteWaveComplex(packet_i, packet_q);

        result1.setSampleRate(dgtSampleRate_Hz);
        // *************this part is added for result1***************

        if (UserCommon.debug_flag) {

            packet.getReal().plot("TX_PayloadWaveform_I");
            packet.getImaginary().plot("TX_PayloadWaveform_Q");
            packet.plot("TX_Payload_Cons ");
        }

        // demod.setInputParameter("SampleRate", 25e6);
        // demod.execute(packet);//didnot substract mean value dc
        demod.execute(result1);// substract mean value
        // demod.execute(waveform);

        MultiSiteDouble devmRms = new MultiSiteDouble(0.0);
        MultiSiteDouble devmPk = new MultiSiteDouble(0.0);

        MultiSiteDouble devm99 = new MultiSiteDouble(0.0);
        MultiSiteDouble devm20 = new MultiSiteDouble(0.0);

        MultiSiteDoubleArray devmRmsBlocks, devmPkBlocks;
        devm99 = demod.getResultParameterDouble("Devm99");
        devm20 = demod.getResultParameterDouble("DevmRmsMax");
        if (true) {

            devmRms = demod.getResultParameterDouble("DevmRmsMax"); // direct get the max value
            devmPk = demod.getResultParameterDouble("DevmPeakMax");

        } else // smt7 adopt this method
        {
            devmRmsBlocks = demod.getResultParameterDoubleArray("DevmRmsPerBlock"); // get each
                                                                                    // block value
            devmPkBlocks = demod.getResultParameterDoubleArray("DevmPeakPerBlock");

            // Find Max RMS and PK from all available blocks (50 symbols per block)

            for (int iSite : activeSites) {

                double b = 999;
                double c = 999;
                for (i = 0; i < devmRmsBlocks.length(); ++i) {

                    // cout << "DEVM(RMS) for block " << i << " " << devmRmsBlocks[i] << "%" <<
                    // endl;
                    if (devmRms.get(iSite) < devmRmsBlocks.getElement(iSite, i)) {

                        // b = devmRmsBlocks.getElement(iSite, i);
                        devmRms.set(iSite, devmRmsBlocks.getElement(iSite, i));
                    }
                    if (devmPk.get(iSite) < devmPkBlocks.getElement(iSite, i)) {
                        // cout << "DEVM(Pk) for block " << i << " " << devmPkBlocks[i] << "%" <<
                        // endl;
                        // c = devmPkBlocks.getElement(iSite, i);
                        devmPk.set(iSite, devmPkBlocks.getElement(iSite, i));
                    }
                }

                // devmRms.set(iSite, b);
                // devmPk.set(iSite, c);

            }
        }

        result.add(devmRms);
        result.add(devmPk);
        if (UserCommon.debug_flag) {
            System.out.println("DEVM(RMS,MAX) =" + devmRms);
            System.out.println("DEVM(PK,MAX)   =" + devmPk);
            System.out.println("DEVM(99%)     =" + devm99);
            System.out.println("DEVM(PK,<20%) =" + devm20); // ratio of devmPk < 20%
            demod.plot("EVM_BT" + ": constellation", tsName, "Constellation");
        }

        return result;
    }

    public static List<MultiSiteDouble> TX_BT_EVM(MultiSiteWaveComplex waveform, String packetType,
            Demodulation demod, List<MultiSiteDouble> result, int[] activeSites, boolean offline1,
            String tsName, boolean isDebug1, String sProjectPath, boolean hasgeneratepara) {
        // public static MultiSiteDouble[] SFDRCalc (MultiSiteWaveDouble aiCapData, int
        // iFundBins,int iBeginBins,int iEndBins,int[] activeSites ) {

        MultiSiteDouble dgtSampleRate_Hz = waveform.getSampleRate();
        MultiSiteLong numPoints = waveform.getSize();
        MultiSiteLong payloadStartOffset = new MultiSiteLong();
        MultiSiteLong payloadBits = new MultiSiteLong();
        MultiSiteLong resultLengthInSymbol = new MultiSiteLong();
        MultiSiteLong payloadLength = new MultiSiteLong();
        MultiSiteLong codeWordLength = new MultiSiteLong();
        MultiSiteString flexModulationFormat = new MultiSiteString();
        // System.out.println("samplerate===="+dgtSampleRate_Hz);

        if (packetType.equals("3DH5")) {

            // cout << "---------------- Packet 3DH5 ---------------" << endl;
            codeWordLength.set(3);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(8136); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");
        } else if (packetType.equals("3DH3")) {
            // cout << "---------------- Packet 3DH3 ---------------" << endl;

            codeWordLength.set(3);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(4392); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");

        } else if (packetType.equals("3DH1")) {
            // cout << "---------------- Packet 3DH1 ---------------" << endl;

            codeWordLength.set(3);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(648); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");

        } else if (packetType.equals("2DH5")) {
            // cout << "---------------- Packet 2DH5 ---------------" << endl;

            codeWordLength.set(2);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(5424); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");

        } else if (packetType.equals("2DH3")) {
            // cout << "---------------- Packet 2DH3 ---------------" << endl;
            codeWordLength.set(2);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(2928); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");

        } else if (packetType.equals("2DH1")) {
            // cout << "---------------- Packet 2DH1 ---------------" << endl;
            codeWordLength.set(2);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(432); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");

        }
        /****************************************************************************************/

        /********** setup for payload-only DEVM measurements **********/
        else if (packetType.equals("3PayloadOnly")) {
            // cout << "---------------- 8DPSK only ---------------" << endl;
            codeWordLength.set(3);
            payloadStartOffset.set(0); // amount of header bits
            payloadBits.set(1500); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");

        } else if (packetType.equals("2PayloadOnly")) {
            // cout << "---------- PI OVER 4 DQPSK only -----------" << endl;

            codeWordLength.set(2);
            payloadStartOffset.set(0); // amount of header bits
            payloadBits.set(10000); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");
        }
        /****************************************************************************************/
        else {

            System.out.println("ERROR: Unsupported Packet Type!");
            // exit;
        }

        if (UserCommon.debug_flag) {

            waveform.plot("TX_DEVM_FullCapturedWaveform");
            waveform.getReal().plot("TX _DEVM_FullCapturedWaveform_I");
            waveform.getImaginary().plot("TX_DEVM_FullCapturedWaveform_Q");
            // spectrum.plot("TX_DEVM_Full Spectrum:dBm" ,tsName);

        }
        demod = new Demodulation();
        demod.presetToStandard("Bluetooth");
        // demod.setInputParameter("ModFormat", flexModulationFormat);//ModFormat
        // demod.setInputParameter("FilterAlpha", 0.4); //FilterAlpha
        // demod.setInputParameter("MeasurementFilter", 10);//MeasurementFilter ROOT_RAISED_COSINE
        // demod.setInputParameter("ReferenceFilter", 8); //ReferenceFilter RAISED_COSINE
        // demod.setInputParameter("CompensateIQOffset", 1); //Added on Jul 5th //CompensateIQOffset
        // demod.setInputParameter("DoDevm", 1); //Added on Jul 5th //DoDevm
        // demod.setInputParameter("CodeWordLength", codeWordLength); //CodeWordLength
        // demod.setInputParameter("IFFilterBandwidth", (1.0e6)); //IFFilterBandwidth
        // demod.setInputParameter("SamplesPerSymbol", 1); //related to constellation plot
        // //SamplesPerSymbol
        // demod.setInputParameter("ResultLength", resultLengthInSymbol); // number of symbols
        // (depends on packet type) - no trailer //0
        // demod.setInputParameter("SyncMode", 0); //origi is 0 continious// 3 continious-syc
        // //SyncMode
        // // demod->setInputParameter("bDifferential", FALSE); // must be false - diff done in
        // calculation SMT7 set this para,but SMT8 has no this para

        MultiSiteString exportpara1 = new MultiSiteString();
        // exportpara1.set(sProjectPath+"/rf_wave/WCN_80211n_40M.para") ;
        // demod.writeParameterFile(exportpara1);
        // demod.readParameterFile( exportpara1);
        exportpara1.set(sProjectPath + "/rf_wave/WCN_BT_TX.para");
        if (hasgeneratepara) {
            demod.readParameterFile(exportpara1);
        } else {

            demod.writeParameterFile(exportpara1);

        }

        MultiSiteWaveComplex packet = new MultiSiteWaveComplex();

        MultiSiteDoubleArray cData_mag = new MultiSiteDoubleArray(); // numpoints
        MultiSiteDoubleArray cData_avg15 = new MultiSiteDoubleArray(); // numpoints/2
        MultiSiteWaveDouble cData_mag1_new = new MultiSiteWaveDouble();
        MultiSiteWaveDouble cData_avg15_new = new MultiSiteWaveDouble();
        MultiSiteDouble cData_avg = new MultiSiteDouble();
        MultiSiteLong packet_begin = new MultiSiteLong();
        // two different type defines
        packet.resize(payloadLength);
        if (true) {

            cData_mag = waveform.abs().toMultiSiteArray();
            MultiSiteWaveDouble cData_mid = cData_mag.toMultiSiteWave();
            cData_avg = cData_mid.mean();

        } else {

            cData_mag1_new = waveform.abs();
            cData_avg = cData_mag1_new.mean();
        }

        // MultiSiteLong ModPowerLowDetectFlag =new MultiSiteLong();
        // MultiSiteLong tmpI =new MultiSiteLong();

        int ModPowerLowDetectFlag = 0;
        int tmpI = 0;
        double dd = 0.0;
        int i = 0, ii = 0;

        for (int iSite : activeSites) {

            double[] cData_avg15_site = new double[numPoints.get(iSite).intValue() / 2];
            // cData_avg15_site= cData_avg15.get(iSite);

            for (i = ModPowerLowDetectFlag = 0; i < numPoints
                    .get(iSite) / 2 && ModPowerLowDetectFlag != 2;) {

                for (ii = 0, cData_avg15_site[i] = 0.0; ii < 15; ii++) {

                    cData_avg15_site[i] += cData_mag.getElement(iSite, i + ii);
                }

                cData_avg15_site[i] /= 15;

                if (cData_avg15_site[i] < cData_avg.get(iSite) / 2) {
                    if (ModPowerLowDetectFlag == 0) {
                        ModPowerLowDetectFlag = 1;
                    } else {
                        ModPowerLowDetectFlag = 2;
                        continue; // RX section found (no signal around location i)
                    }
                }
                double sr = dgtSampleRate_Hz.get(iSite);
                tmpI = (int) (sr / 100000);
                i += (int) (sr / 100000); // go in 10us steps to be faster

            }

            for (; i < numPoints.get(iSite); i++) {
                if (cData_mag.getElement(iSite, i) > cData_avg.get(iSite) / 2) {
                    break; // find packet
                }
            }

            int packet_begin_new = i + payloadStartOffset.get(iSite).intValue();

            WaveComplex aa1 = new WaveComplex(payloadLength.get().intValue());// intial and specify
                                                                              // the size
            Complex aa[] = new Complex[payloadLength.get(iSite).intValue()];

            if (UserCommon.debug_flag) {
                // System.out.println("packet_begin_new="+packet_begin_new);
                // // System.out.println("waveform="+waveform.getSize(iSite));
                // System.out.println("payloadlength="+payloadLength.get(iSite).intValue());
                // System.out.println("waveform="+waveform.getSize(iSite));

            }

            aa1 = waveform.get(iSite).extractValues(packet_begin_new,
                    payloadLength.get().intValue());
            packet.set(iSite, aa1);

        }

        if (UserCommon.debug_flag) {

            packet.getReal().plot("TX_PayloadWaveform_I");
            packet.getImaginary().plot("TX_PayloadWaveform_Q");
            packet.plot("TX_Payload_Cons ");

        }

        // demod.setInputParameter("SampleRate", 25e6);

        packet.setLoSide(waveform.getLoSide());// SMT8.2.5
        demod.execute(packet);
        // demod.execute(waveform);

        MultiSiteDouble devmRms = new MultiSiteDouble(0.0);
        MultiSiteDouble devmPk = new MultiSiteDouble(0.0);

        MultiSiteDouble devm99 = new MultiSiteDouble(0.0);
        MultiSiteDouble devm20 = new MultiSiteDouble(0.0);

        MultiSiteDoubleArray devmRmsBlocks, devmPkBlocks;
        devm99 = demod.getResultParameterDouble("Devm99");
        devm20 = demod.getResultParameterDouble("DevmRmsMax");
        if (true) {

            devmRms = demod.getResultParameterDouble("DevmRmsMax"); // direct get the max value
            devmPk = demod.getResultParameterDouble("DevmPeakMax");

        } else // smt7 adopt this method
        {
            devmRmsBlocks = demod.getResultParameterDoubleArray("DevmRmsPerBlock"); // get each
                                                                                    // block value
            devmPkBlocks = demod.getResultParameterDoubleArray("DevmPeakPerBlock");

            // Find Max RMS and PK from all available blocks (50 symbols per block)

            for (int iSite : activeSites) {

                double b = 999;
                double c = 999;
                // System.out.println("devmRmsBlocks length="+devmRmsBlocks.length());
                for (i = 0; i < devmRmsBlocks.length(); ++i) {

                    // cout << "DEVM(RMS) for block " << i << " " << devmRmsBlocks[i] << "%" <<
                    // endl;
                    if (devmRms.get(iSite) < devmRmsBlocks.getElement(iSite, i)) {

                        // b = devmRmsBlocks.getElement(iSite, i);
                        devmRms.set(iSite, devmRmsBlocks.getElement(iSite, i));

                    }
                    if (devmPk.get(iSite) < devmPkBlocks.getElement(iSite, i)) {
                        // cout << "DEVM(Pk) for block " << i << " " << devmPkBlocks[i] << "%" <<
                        // endl;
                        // c = devmPkBlocks.getElement(iSite, i);
                        devmPk.set(iSite, devmPkBlocks.getElement(iSite, i));
                    }
                }
                // devmRms.set(iSite,devmRms);
                // devmPk.set(iSite, devmPk);

            }
        }

        result.add(devmRms);
        result.add(devmPk);
        if (UserCommon.debug_flag) {
            // System.out.println( "DEVM(RMS,MAX) ="+devmRms);
            // System.out.println("DEVM(PK,MAX) ="+ devmPk );
            // System.out.println( "DEVM(99%) ="+ devm99 );
            // System.out.println( "DEVM(PK,<20%) ="+ devm20); // ratio of devmPk < 20%
            // demod.plot("EVM_BT"+": constellation", tsName, "Constellation");
        }

        return result;
    }

    public static MultiSiteWaveComplex TX_BT_EVM(MultiSiteWaveComplex waveform, String packetType,
            int[] activeSites) {
        // public static MultiSiteDouble[] SFDRCalc (MultiSiteWaveDouble aiCapData, int
        // iFundBins,int iBeginBins,int iEndBins,int[] activeSites ) {

        MultiSiteDouble dgtSampleRate_Hz = waveform.getSampleRate();
        MultiSiteLong numPoints = waveform.getSize();
        MultiSiteLong payloadStartOffset = new MultiSiteLong();
        MultiSiteLong payloadBits = new MultiSiteLong();
        MultiSiteLong resultLengthInSymbol = new MultiSiteLong();
        MultiSiteLong payloadLength = new MultiSiteLong();
        MultiSiteLong codeWordLength = new MultiSiteLong();
        MultiSiteString flexModulationFormat = new MultiSiteString();
        // System.out.println("samplerate===="+dgtSampleRate_Hz);

        if (packetType.equals("3DH5")) {

            // cout << "---------------- Packet 3DH5 ---------------" << endl;
            codeWordLength.set(3);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(8136); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");
        } else if (packetType.equals("3DH3")) {
            // cout << "---------------- Packet 3DH3 ---------------" << endl;

            codeWordLength.set(3);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(4392); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");

        } else if (packetType.equals("3DH1")) {
            // cout << "---------------- Packet 3DH1 ---------------" << endl;

            codeWordLength.set(3);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(648); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");

        } else if (packetType == "2DH5") {
            // cout << "---------------- Packet 2DH5 ---------------" << endl;

            codeWordLength.set(2);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(5424); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");

        } else if (packetType.equals("2DH3")) {
            // cout << "---------------- Packet 2DH3 ---------------" << endl;
            codeWordLength.set(2);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(2928); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");

        } else if (packetType.equals("2DH1")) {
            // cout << "---------------- Packet 2DH1 ---------------" << endl;
            codeWordLength.set(2);
            MultiSiteDouble aa = dgtSampleRate_Hz.multiply((72 + 54 + 5 + 11 + 16)).divide(1000000);
            MultiSiteLong bb = aa.toMultiSiteLong();

            payloadStartOffset.set(bb); // amount of header bits
            payloadBits.set(432); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");

        }
        /****************************************************************************************/

        /********** setup for payload-only DEVM measurements **********/
        else if (packetType.equals("3PayloadOnly")) {
            // cout << "---------------- 8DPSK only ---------------" << endl;
            codeWordLength.set(3);
            payloadStartOffset.set(0); // amount of header bits
            payloadBits.set(1500); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("D8PSK");

        } else if (packetType.equals("2PayloadOnly")) {
            // cout << "---------- PI OVER 4 DQPSK only -----------" << endl;

            codeWordLength.set(2);
            payloadStartOffset.set(0); // amount of header bits
            payloadBits.set(10000); // max size according to spec
            resultLengthInSymbol = ((payloadBits.divide(codeWordLength)).subtract(100)); // 2 blocks
                                                                                         // are
                                                                                         // ignored
            payloadLength = ((payloadBits.multiply(dgtSampleRate_Hz).divide(1000000))
                    .divide(codeWordLength)).toMultiSiteLong();
            flexModulationFormat.set("PI_OVER4_DQPSK");
        }
        /****************************************************************************************/
        else {
            System.out.println("ERROR: Unsupported Packet Type!");
            // exit;
        }

        if (UserCommon.debug_flag) {

            waveform.plot("TX_DEVM_FullCapturedWaveform");
            waveform.getReal().plot("TX _DEVM_FullCapturedWaveform_I");
            waveform.getImaginary().plot("TX_DEVM_FullCapturedWaveform_Q");
            // spectrum.plot("TX_DEVM_Full Spectrum:dBm" ,tsName);

        }

        MultiSiteWaveComplex packet = new MultiSiteWaveComplex();

        MultiSiteDoubleArray cData_mag = new MultiSiteDoubleArray(); // numpoints
        MultiSiteDoubleArray cData_avg15 = new MultiSiteDoubleArray(); // numpoints/2
        MultiSiteWaveDouble cData_mag1_new = new MultiSiteWaveDouble();
        MultiSiteWaveDouble cData_avg15_new = new MultiSiteWaveDouble();
        MultiSiteDouble cData_avg = new MultiSiteDouble();
        MultiSiteLong packet_begin = new MultiSiteLong();
        // two different type defines
        packet.resize(payloadLength);
        if (true) {

            cData_mag = waveform.abs().toMultiSiteArray();
            MultiSiteWaveDouble cData_mid = cData_mag.toMultiSiteWave();
            cData_avg = cData_mid.mean();

        } else {

            cData_mag1_new = waveform.abs();
            cData_avg = cData_mag1_new.mean();
        }

        // MultiSiteLong ModPowerLowDetectFlag =new MultiSiteLong();
        // MultiSiteLong tmpI =new MultiSiteLong();

        int ModPowerLowDetectFlag = 0;
        int tmpI = 0;
        double dd = 0.0;
        int i = 0, ii = 0;

        for (int iSite : activeSites) {

            double[] cData_avg15_site = new double[numPoints.get(iSite).intValue() / 2];
            // cData_avg15_site= cData_avg15.get(iSite);

            for (i = ModPowerLowDetectFlag = 0; i < numPoints
                    .get(iSite) / 2 && ModPowerLowDetectFlag != 2;) {

                for (ii = 0, cData_avg15_site[i] = 0.0; ii < 15; ii++) {

                    cData_avg15_site[i] += cData_mag.getElement(iSite, i + ii);
                }

                cData_avg15_site[i] /= 15;

                if (cData_avg15_site[i] < cData_avg.get(iSite) / 2) {
                    if (ModPowerLowDetectFlag == 0) {
                        ModPowerLowDetectFlag = 1;
                    } else {
                        ModPowerLowDetectFlag = 2;
                        continue; // RX section found (no signal around location i)
                    }
                }
                double sr = dgtSampleRate_Hz.get(iSite);
                tmpI = (int) (sr / 100000);
                i += (int) (sr / 100000); // go in 10us steps to be faster

            }

            for (; i < numPoints.get(iSite); i++) {
                if (cData_mag.getElement(iSite, i) > cData_avg.get(iSite) / 2) {
                    break; // find packet
                }
            }

            int packet_begin_new = i + payloadStartOffset.get(iSite).intValue();

            WaveComplex aa1 = new WaveComplex(payloadLength.get().intValue());// intial and specify
                                                                              // the size
            Complex aa[] = new Complex[payloadLength.get(iSite).intValue()];

            if (UserCommon.debug_flag) {
                // System.out.println("packet_begin_new="+packet_begin_new);
                // // System.out.println("waveform="+waveform.getSize(iSite));
                // System.out.println("payloadlength="+payloadLength.get(iSite).intValue());
                // System.out.println("waveform="+waveform.getSize(iSite));

            }

            aa1 = waveform.get(iSite).extractValues(packet_begin_new,
                    payloadLength.get().intValue());
            packet.set(iSite, aa1);

        }

        if (UserCommon.debug_flag) {

            packet.getReal().plot("TX_PayloadWaveform_I");
            packet.getImaginary().plot("TX_PayloadWaveform_Q");
            packet.plot("TX_Payload_Cons ");

        }

        // demod.setInputParameter("SampleRate", 25e6);

        packet.setLoSide(waveform.getLoSide());// SMT8.2.5

        return packet;
    }

    public static List<MultiSiteDouble> TX_BT_EVM_new(MultiSiteWaveComplex waveform,
            String packetType, Demodulation demod, List<MultiSiteDouble> result, int[] activeSites,
            boolean offline1, String tsName, boolean isDebug1, String sProjectPath,
            boolean hasgeneratepara) {
        // public static MultiSiteDouble[] SFDRCalc (MultiSiteWaveDouble aiCapData, int
        // iFundBins,int iBeginBins,int iEndBins,int[] activeSites ) {

        MultiSiteDouble dgtSampleRate_Hz = waveform.getSampleRate();
        MultiSiteLong numPoints = waveform.getSize();
        MultiSiteLong payloadStartOffset = new MultiSiteLong();
        MultiSiteLong payloadBits = new MultiSiteLong();
        MultiSiteLong resultLengthInSymbol = new MultiSiteLong();
        MultiSiteLong payloadLength = new MultiSiteLong();
        MultiSiteLong codeWordLength = new MultiSiteLong();
        MultiSiteString flexModulationFormat = new MultiSiteString();
        // System.out.println("samplerate===="+dgtSampleRate_Hz);

        if (UserCommon.debug_flag) {

            waveform.plot("TX_DEVM_FullCapturedWaveform");
            waveform.getReal().plot("TX _DEVM_FullCapturedWaveform_I");
            waveform.getImaginary().plot("TX_DEVM_FullCapturedWaveform_Q");
            // spectrum.plot("TX_DEVM_Full Spectrum:dBm" );

        }
        demod = new Demodulation();
        demod.presetToStandard("Bluetooth");

        MultiSiteString exportpara1 = new MultiSiteString();

        exportpara1.set(sProjectPath + "/rf_wave/WCN_BT_TX_new.para");
        if (hasgeneratepara) {
            demod.readParameterFile(exportpara1);
        } else {
            demod.setInputParameter("ModFormat", flexModulationFormat);// ModFormat
            demod.setInputParameter("FilterAlpha", 0.4); // FilterAlpha
            demod.setInputParameter("MeasurementFilter", 10);// MeasurementFilter ROOT_RAISED_COSINE
            demod.setInputParameter("ReferenceFilter", 8); // ReferenceFilter RAISED_COSINE
            demod.setInputParameter("CompensateIQOffset", 1); // Added on Jul 5th
                                                              // //CompensateIQOffset
            demod.setInputParameter("DoDevm", 1); // Added on Jul 5th //DoDevm
            demod.setInputParameter("CodeWordLength", codeWordLength); // CodeWordLength
            demod.setInputParameter("IFFilterBandwidth", (1.0e6)); // IFFilterBandwidth
            demod.setInputParameter("SamplesPerSymbol", 1); // related to constellation plot
                                                            // //SamplesPerSymbol
            demod.setInputParameter("ResultLength", resultLengthInSymbol); // number of symbols
                                                                           // (depends on packet
                                                                           // type) - no trailer //0
            demod.setInputParameter("SyncMode", 0); // origi is 0 continious// 3 continious-syc
                                                    // //SyncMode
            // demod->setInputParameter("bDifferential", FALSE); // must be false - diff done in
            // calculation SMT7 set this para,but SMT8 has no this para

            demod.writeParameterFile(exportpara1);

        }

        demod.execute(waveform);

        MultiSiteDouble devmRms = new MultiSiteDouble(0.0);
        MultiSiteDouble devmPk = new MultiSiteDouble(0.0);

        MultiSiteDouble devm99 = new MultiSiteDouble(0.0);
        MultiSiteDouble devm20 = new MultiSiteDouble(0.0);

        MultiSiteDoubleArray devmRmsBlocks, devmPkBlocks;
        devm99 = demod.getResultParameterDouble("Devm99");
        devm20 = demod.getResultParameterDouble("DevmRmsMax");
        if (true) {

            devmRms = demod.getResultParameterDouble("DevmRmsMax"); // direct get the max value
            devmPk = demod.getResultParameterDouble("DevmPeakMax");

        } else // smt7 adopt this method
        {
            devmRmsBlocks = demod.getResultParameterDoubleArray("DevmRmsPerBlock"); // get each
                                                                                    // block value
            devmPkBlocks = demod.getResultParameterDoubleArray("DevmPeakPerBlock");

            // Find Max RMS and PK from all available blocks (50 symbols per block)
            int i = 0;
            for (int iSite : activeSites) {

                double b = 999;
                double c = 999;
                // System.out.println("devmRmsBlocks length="+devmRmsBlocks.length());
                for (i = 0; i < devmRmsBlocks.length(); ++i) {

                    // cout << "DEVM(RMS) for block " << i << " " << devmRmsBlocks[i] << "%" <<
                    // endl;
                    if (devmRms.get(iSite) < devmRmsBlocks.getElement(iSite, i)) {

                        // b = devmRmsBlocks.getElement(iSite, i);
                        devmRms.set(iSite, devmRmsBlocks.getElement(iSite, i));

                    }
                    if (devmPk.get(iSite) < devmPkBlocks.getElement(iSite, i)) {
                        // cout << "DEVM(Pk) for block " << i << " " << devmPkBlocks[i] << "%" <<
                        // endl;
                        // c = devmPkBlocks.getElement(iSite, i);
                        devmPk.set(iSite, devmPkBlocks.getElement(iSite, i));
                    }
                }
                // devmRms.set(iSite,devmRms);
                // devmPk.set(iSite, devmPk);

            }
        }

        result.add(devmRms);
        result.add(devmPk);
        if (UserCommon.debug_flag) {
            // System.out.println( "DEVM(RMS,MAX) ="+devmRms);
            // System.out.println("DEVM(PK,MAX) ="+ devmPk );
            // System.out.println( "DEVM(99%) ="+ devm99 );
            // System.out.println( "DEVM(PK,<20%) ="+ devm20); // ratio of devmPk < 20%
            // demod.plot("EVM_BT"+": constellation", tsName, "Constellation");
        }

        return result;
    }

    // ********************RX Setup*******************************************************
    public static void DGT_measure(ISetupDigitizer dgtsetup, String dgtMeasName1,
            double dgtAmplitude1, double dcoffset1, double dgt_samplerate1, long dgt_Samples1,
            double dgtLPF1, String Resistor_Ohm1) {

        ISetupDigitizer.IMeasureWaveform dgtCapWave = dgtsetup.measureWaveform(dgtMeasName1);
        dgtCapWave.setExpectedAmplitude(dgtAmplitude1).setOffset(dcoffset1)
                .setSampleRate(dgt_samplerate1).setSamples(dgt_Samples1).setDecimation(false)
                .setCenterGnd(true);
        dgtCapWave.setLowPassFilterBandwidth(dgtLPF1)
                .setInputImpedance(SetupInputImpedance.R100kOhm); // TODO impedance
        if (Resistor_Ohm1.contains("R100kOhm")) {
            dgtCapWave.setInputImpedance(SetupInputImpedance.R100kOhm);
        } else if (Resistor_Ohm1.contains("R50Ohm")) {
            dgtCapWave.setInputImpedance(SetupInputImpedance.R50Ohm);

        } else if (Resistor_Ohm1.contains("HighZ")) {
            dgtCapWave.setInputImpedance(SetupInputImpedance.HighZ);

        }

    }

    public static void DGT_measure_FM(ISetupDigitizer dgtsetup, String dgtMeasName1,
            double dgtAmplitude1, double dcoffset1, double dgt_samplerate1, long dgt_Samples1,
            double dgtLPF1, String Resistor_Ohm1) {

        ISetupDigitizer.IMeasureWaveform dgtCapWave = dgtsetup.measureWaveform(dgtMeasName1);
        dgtCapWave.setExpectedAmplitude(dgtAmplitude1).setOffset(dcoffset1)
                .setSampleRate(dgt_samplerate1).setSamples(dgt_Samples1).setDecimation(true);// .downConversion().setIqConversion(true);
        dgtCapWave.setLowPassFilterBandwidth(dgtLPF1)
                .setInputImpedance(SetupInputImpedance.R100kOhm).setCenterGnd(true);// .setAveragesPerSample(96);
                                                                                    // //.digitalFilter().setCutOffFrequency1(200e3);
                                                                                    // //TODO
                                                                                    // impedance
        if (Resistor_Ohm1.contains("R100kOhm")) {
            dgtCapWave.setInputImpedance(SetupInputImpedance.R100kOhm);
        } else if (Resistor_Ohm1.contains("R50Ohm")) {
            dgtCapWave.setInputImpedance(SetupInputImpedance.R50Ohm);

        } else if (Resistor_Ohm1.contains("HighZ")) {
            dgtCapWave.setInputImpedance(SetupInputImpedance.HighZ);

        }

    }

    public static List<MultiSiteDouble> RX_IQ_GAINDsp(MultiSiteWaveComplex capWave, double bb_freq,
            double inputPower, boolean IQimbalace_falg, int[] activeSites, boolean offline1,
            String tsName, boolean isDebug1, String mode, String testFrequency, String TX_RX) {
        MultiSiteDouble idBm = new MultiSiteDouble();
        MultiSiteDouble qdBm = new MultiSiteDouble();
        MultiSiteDouble i_gain_db = new MultiSiteDouble();
        MultiSiteDouble q_gain_db = new MultiSiteDouble();
        MultiSiteDouble ampBalance = new MultiSiteDouble();
        MultiSiteDouble phaseI = new MultiSiteDouble();
        MultiSiteDouble phaseQ = new MultiSiteDouble();
        MultiSiteDouble phaseBalance = new MultiSiteDouble();
        MultiSiteLong IF_index_max = new MultiSiteLong();
        List<MultiSiteDouble> result = new ArrayList<MultiSiteDouble>();
        long sampleSize = capWave.getSize().get();

        double sampleRate = capWave.getSampleRate().get();
        double BindWidth = sampleRate / sampleSize;
        // int stdBin = (int)(sampleSize * freqOut /sampleRate +0.5);
        int IF_Index = (int) (bb_freq / BindWidth);
        // System.out.println("sampleSize="+sampleSize);
        // System.out.println("sampleRate="+sampleRate);
        // System.out.println("BindWidth="+BindWidth);
        // System.out.println("IF_Index="+IF_Index);

        MultiSiteSpectrum iABS = new MultiSiteSpectrum();
        MultiSiteSpectrum qABS = new MultiSiteSpectrum();
        MultiSiteSpectrumComplex iABS1 = new MultiSiteSpectrumComplex();
        MultiSiteSpectrumComplex qABS1 = new MultiSiteSpectrumComplex();
        if (IQimbalace_falg) {
            iABS1 = capWave.getReal().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).fft();
            qABS1 = capWave.getImaginary().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).fft();
            iABS = iABS1.abs();
            qABS = qABS1.abs();
            if (UserCommon.debug_flag) {
                capWave.getReal().plot(" Capture_I");
                capWave.getImaginary().plot(" Capture_Q");
            }
            // iABS1.plot(" Capture_I_iABS1" );
            // qABS1.plot(" Capture_Q_qABS1" );
            //
            // System.out.println("i phase="+iABS1.getValue(IF_Index).atan2());
            //
            // System.out.println("q phase="+qABS1.getValue(IF_Index).atan2());
        } else {
            iABS = capWave.getReal().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.V);
            qABS = capWave.getImaginary().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.V);

        }
        MultiSiteDouble imax_value = new MultiSiteDouble();
        MultiSiteLong imax_Index = new MultiSiteLong();
        MultiSiteDouble qmax_value = new MultiSiteDouble();
        MultiSiteLong qmax_Index = new MultiSiteLong();
        if (IF_Index < (sampleSize / 2)) {
            if (false) { // smt 8 calculation method
                MultiSiteDouble idBm_S = iABS.getValue(IF_Index).pow(2); // v*v
                MultiSiteDouble idBm_L = iABS.getValue(IF_Index - 1).pow(2);
                MultiSiteDouble idBm_H = iABS.getValue(IF_Index + 1).pow(2);

                idBm = idBm_S.add(idBm_L).add(idBm_H).log10().multiply(10).add(10);

                MultiSiteDouble qdBm_S = qABS.getValue(IF_Index).pow(2);
                MultiSiteDouble qdBm_L = qABS.getValue(IF_Index - 1).pow(2);
                MultiSiteDouble qdBm_H = qABS.getValue(IF_Index + 1).pow(2);

                qdBm = qdBm_S.add(qdBm_L).add(qdBm_H).log10().multiply(10).add(10);

                // idBm= iABS.extractValues(IF_Index-3, 6).sum().log10().multiply(20).add(10);//this
                // equation is wrong
                //
                // qdBm= qABS.extractValues(IF_Index-3, 6).sum().log10().multiply(20).add(10);

            }

            else {
                // smt 7 calculation method

                imax_value = iABS.extractValues(IF_Index - 3, 6).max(); // unit is V
                imax_Index = iABS.extractValues(IF_Index - 3, 6).maxIndex();
                IF_index_max.set(imax_Index.add(IF_Index - 3));
                // idBm = imax_value.log10().multiply(20).add(10); //convet to dbm
                idBm = iABS.getValue(IF_Index).log10().multiply(20).add(10); // convet to dbm
                // System.out.println("imax_Idex=="+imax_Index+"; IF_index=="+IF_Index);
                // System.out.println("imax_Idex value=="+iABS.getValue(imax_Index)+"; IF_index
                // value=="+iABS.getValue(IF_Index));

                qmax_value = qABS.extractValues(IF_Index - 3, 6).max();
                qmax_Index = qABS.extractValues(IF_Index - 3, 6).maxIndex();
                qdBm = qABS.getValue(IF_Index).log10().multiply(20).add(10); // convet to dbm
                // qdBm = qmax_value.log10().multiply(20).add(10);

            }
        } else {
            idBm.set(999);
            qdBm.set(999);
        }
        // i_gain_db = idBm.subtract(inputPower-10-Path_Loss(mode, testFrequency, TX_RX,
        // phaseBalance));
        MultiSiteDouble Path_Loss = new MultiSiteDouble(0.0);
        Path_Loss = Path_Loss(mode, testFrequency, TX_RX);
        if (UserCommon.debug_flag) {
            System.out.println(
                    "mode=" + mode + " ; testFrequency=" + testFrequency + "  ;TX_RX=" + TX_RX);
            System.out.println("Path_Loss" + Path_Loss);
        }
        // i_gain_db = idBm.subtract(inputPower-10-Path_Loss1);
        // q_gain_db = qdBm.subtract(inputPower-10-Path_Loss);
        i_gain_db = idBm.subtract(Path_Loss.multiply(-1).subtract(10).add(inputPower));
        q_gain_db = qdBm.subtract(Path_Loss.multiply(-1).subtract(10).add(inputPower));

        /////////////////////////////////////////////////
        // IQ Balance calculation //
        /////////////////////////////////////////////////
        result.add(i_gain_db);
        result.add(q_gain_db);
        if (IQimbalace_falg) {
            ampBalance = idBm.subtract(qdBm);

            phaseI = iABS1.getValue(IF_index_max).atan2();
            phaseQ = qABS1.getValue(IF_index_max).atan2();
            phaseBalance = (phaseQ.subtract(phaseI)).divide(Math.PI).multiply(180.0);
            // for (int iSite :activeSites) {
            // phaseI.set(iSite,Math.atan2((iABS1.getImaginary().getValue(IF_Index).get(iSite)),(iABS1.getReal().getValue(IF_Index).get(iSite))));
            // phaseQ.set(iSite,Math.atan2((qABS1.getImaginary().getValue(IF_Index).get(iSite)),(qABS1.getReal().getValue(IF_Index).get(iSite))));
            // }

            // phaseI .set(iABS1.getValue(imax_Index).atan2());
            // phaseQ .set(qABS1.getValue(imax_Index).atan2());
            if (UserCommon.debug_flag) {
                System.out.println("phaseI==" + phaseI.divide(Math.PI).multiply(180.0));
                System.out.println("phaseQ==" + phaseQ.divide(Math.PI).multiply(180.0));
                // qPhs =qCpx.getValue(IF_Index).atan2().divide(Math.PI).multiply(180.0);// in
                // degree

                System.out.println("phaseBalance==" + phaseBalance);
            }
            // for (int iSite :activeSites) {
            // if(phaseBalance.get(iSite) < 0){
            // phaseBalance.set(iSite, phaseBalance.get(iSite)+360.0);
            // }
            // if(phaseBalance.get(iSite) >180.0){
            // phaseBalance.set(iSite, 360-phaseBalance.get(iSite));
            // }
            // }

            for (int iSite : activeSites) {
                if (phaseBalance.get(iSite) > 180.0) {
                    phaseBalance.set(iSite, phaseBalance.get(iSite) - 180.0);
                } else if (phaseBalance.get(iSite) < -180.0) {
                    phaseBalance.set(iSite, phaseBalance.get(iSite) + 180.0);
                }
            }

            // System.out.println("phaseBalance.abs()=="+phaseBalance.abs());
            phaseBalance.set((new MultiSiteDouble(90.0)).subtract(phaseBalance.abs())); // 90-abs(imbalance)
            result.add(ampBalance);
            result.add(phaseBalance);

        }

        return (result);
    }

    public static List<MultiSiteDouble> RX_IQ_GAINDsp_2(MultiSiteWaveComplex capWave,
            double bb_freq, double inputPower, boolean IQimbalace_falg, int[] activeSites,
            boolean offline1, String tsName, boolean isDebug1, String mode, String testFrequency,
            String TX_RX) {
        MultiSiteDouble idBm = new MultiSiteDouble();
        MultiSiteDouble qdBm = new MultiSiteDouble();
        MultiSiteDouble i_gain_db = new MultiSiteDouble();
        MultiSiteDouble q_gain_db = new MultiSiteDouble();
        MultiSiteDouble ampBalance = new MultiSiteDouble();
        MultiSiteDouble phaseI = new MultiSiteDouble();
        MultiSiteDouble phaseQ = new MultiSiteDouble();
        MultiSiteDouble phaseBalance = new MultiSiteDouble();
        List<MultiSiteDouble> result = new ArrayList<MultiSiteDouble>();
        long sampleSize = capWave.getSize().get();
        MultiSiteLong IF_index_max = new MultiSiteLong();
        double sampleRate = capWave.getSampleRate().get();
        double BindWidth = sampleRate / sampleSize;
        // int stdBin = (int)(sampleSize * freqOut /sampleRate +0.5);
        int IF_Index = (int) (bb_freq / BindWidth);
        if (UserCommon.debug_flag) {
            System.out.println("sampleSize=" + sampleSize);
            System.out.println("sampleRate=" + sampleRate);
            System.out.println("BindWidth=" + BindWidth);
            System.out.println("IF_Index=" + IF_Index);
        }
        MultiSiteSpectrum iABS = new MultiSiteSpectrum();
        MultiSiteSpectrum qABS = new MultiSiteSpectrum();
        MultiSiteSpectrum iABS_MW = new MultiSiteSpectrum();
        MultiSiteSpectrum qABS_MW = new MultiSiteSpectrum();
        MultiSiteSpectrumComplex iABS1 = new MultiSiteSpectrumComplex();
        MultiSiteSpectrumComplex qABS1 = new MultiSiteSpectrumComplex();
        if (IQimbalace_falg) {
            iABS1 = capWave.getReal().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).fft();
            qABS1 = capWave.getImaginary().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).fft();
            iABS = iABS1.abs();
            qABS = qABS1.abs();
        } else {
            iABS = capWave.getReal().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.V);
            qABS = capWave.getImaginary().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.V);
            iABS_MW = capWave.getReal().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.mW);
            qABS_MW = capWave.getImaginary().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.mW);

        }
        if (IF_Index < (sampleSize / 2)) {
            if (false) { // smt 8 calculation method
                MultiSiteDouble idBm_S = iABS.getValue(IF_Index).pow(2); // v*v
                MultiSiteDouble idBm_L = iABS.getValue(IF_Index - 1).pow(2);
                MultiSiteDouble idBm_H = iABS.getValue(IF_Index + 1).pow(2);

                idBm = idBm_S.add(idBm_L).add(idBm_H).log10().multiply(10).add(10);

                MultiSiteDouble qdBm_S = qABS.getValue(IF_Index).pow(2);
                MultiSiteDouble qdBm_L = qABS.getValue(IF_Index - 1).pow(2);
                MultiSiteDouble qdBm_H = qABS.getValue(IF_Index + 1).pow(2);

                qdBm = qdBm_S.add(qdBm_L).add(qdBm_H).log10().multiply(10).add(10);
            } else {
                // smt 7 calculation method
                MultiSiteDouble imax_value = new MultiSiteDouble();
                MultiSiteLong imax_Index = new MultiSiteLong();
                MultiSiteDouble qmax_value = new MultiSiteDouble();
                MultiSiteLong qmax_Index = new MultiSiteLong();
                int offset = 4;
                MultiSiteLong offset2 = new MultiSiteLong(6);
                if (UserCommon.debug_flag) {
                    iABS.plot(" iABS unit:v    ");

                    qABS.plot(" iABS unit:v    ");
                }
                imax_value = iABS.extractValues(IF_Index - offset, offset * 2).max(); // unit is V
                imax_Index = iABS.extractValues(IF_Index - offset, offset * 2).maxIndex();
                IF_index_max.set(imax_Index.add(IF_Index - offset));
                // idBm = imax_value.log10().multiply(20).add(10); //convet to dbm
                idBm = iABS.getValue(IF_index_max).log10().multiply(20).add(10); // update 20180116
                // idBm = iABS.getValue(IF_Index).log10().multiply(20).add(10); //convet to dbm

                qmax_value = qABS.extractValues(IF_Index - offset, offset * 2).max();
                qmax_Index = qABS.extractValues(IF_Index - offset, offset * 2).maxIndex();
                IF_index_max.set(qmax_Index.add(IF_Index - offset));
                // qdBm = qmax_value.log10().multiply(20).add(10);
                qdBm = qABS.getValue(IF_index_max).log10().multiply(20).add(10); // update 20180116

                //
                // imax_value= iABS.extractValues(IF_Index-offset, offset*2).max(); //unit is V
                // imax_Index= iABS.extractValues(IF_Index-offset, offset*2).maxIndex();
                // IF_index_max.set(imax_Index.add(IF_Index-offset));
                //// idBm = imax_value.log10().multiply(20).add(10); //convet to dbm
                // idBm = (iABS_MW.extractValues(IF_index_max,offset2).sum()).log10().multiply(10);
                // //update 20180116
                //// idBm = iABS.getValue(IF_Index).log10().multiply(20).add(10); //convet to dbm
                //
                // qmax_value= qABS.extractValues(IF_Index-offset, offset*2).max();
                // qmax_Index= qABS.extractValues(IF_Index-offset, offset*2).maxIndex();
                // IF_index_max.set(qmax_Index.add(IF_Index-offset));
                //// qdBm = qmax_value.log10().multiply(20).add(10);
                //// qdBm = qABS.getValue(IF_index_max).log10().multiply(20).add(10); //update
                // 20180116
                // qdBm =( qABS_MW.extractValues(IF_index_max,offset2).sum()).log10().multiply(10);
                // //update 20180116

                // qdBm = qABS.getValue(IF_Index).log10().multiply(20).add(10); //20171210
                // System.out.println("IF_index_max="+IF_index_max);

            }
        } else {
            idBm.set(999);
            qdBm.set(999);
        }
        MultiSiteDouble Path_Loss = new MultiSiteDouble(0.0);
        Path_Loss = Path_Loss(mode, testFrequency, TX_RX);

        if (UserCommon.debug_flag) {
            System.out.println(
                    "mode=" + mode + " ; testFrequency=" + testFrequency + "  ;TX_RX=" + TX_RX);
            System.out.println("Path_Loss" + Path_Loss);
        }
        // double palos=0.0;
        // i_gain_db = idBm.subtract(inputPower-palos);
        // q_gain_db = qdBm.subtract(inputPower-palos);
        i_gain_db = idBm.subtract(Path_Loss.multiply(-1).add(inputPower));
        q_gain_db = qdBm.subtract(Path_Loss.multiply(-1).add(inputPower));

        /////////////////////////////////////////////////
        // IQ Balance calculation //
        /////////////////////////////////////////////////
        result.add(i_gain_db);
        result.add(q_gain_db);
        if (IQimbalace_falg) {
            ampBalance = idBm.subtract(qdBm);

            phaseI = iABS1.getValue(IF_index_max).atan2();
            phaseQ = qABS1.getValue(IF_index_max).atan2();
            // qPhs =qCpx.getValue(IF_Index).atan2().divide(Math.PI).multiply(180.0);// in degree
            phaseBalance = (phaseQ.subtract(phaseI)).divide(Math.PI).multiply(180.0);

            for (int iSite : activeSites) {
                if (phaseBalance.get(iSite) > 180.0) {
                    phaseBalance.set(iSite, phaseBalance.get(iSite) - 180.0);
                } else if (phaseBalance.get(iSite) < -180.0) {
                    phaseBalance.set(iSite, phaseBalance.get(iSite) + 180.0);
                }
            }

            phaseBalance = (new MultiSiteDouble(90.0)).subtract(phaseBalance.abs()); // 90-abs(imbalance)
            result.add(ampBalance);
            result.add(phaseBalance);

        }

        return (result);
    }

    public static List<MultiSiteDouble> RX_IQ_GAINDsp_3_FM(MultiSiteWaveComplex capWave,
            double bb_freq, double inputPower, boolean IQimbalace_falg, int[] activeSites,
            boolean offline1, String tsName, boolean isDebug1, String mode, String testFrequency,
            String TX_RX) {
        MultiSiteDouble idBm = new MultiSiteDouble();
        MultiSiteDouble qdBm = new MultiSiteDouble();
        MultiSiteDouble i_gain_db = new MultiSiteDouble();
        MultiSiteDouble q_gain_db = new MultiSiteDouble();
        MultiSiteDouble ampBalance = new MultiSiteDouble();
        MultiSiteDouble phaseI = new MultiSiteDouble();
        MultiSiteDouble phaseQ = new MultiSiteDouble();
        MultiSiteDouble phaseBalance = new MultiSiteDouble();
        List<MultiSiteDouble> result = new ArrayList<MultiSiteDouble>();
        long sampleSize = capWave.getSize().get();
        MultiSiteLong IF_index_max = new MultiSiteLong();
        double sampleRate = capWave.getSampleRate().get();
        double BindWidth = sampleRate / sampleSize;
        // int stdBin = (int)(sampleSize * freqOut /sampleRate +0.5);
        int IF_Index = (int) (bb_freq / BindWidth);
        if (UserCommon.debug_flag) {
            System.out.println("sampleSize=" + sampleSize);
            System.out.println("sampleRate=" + sampleRate);
            System.out.println("BindWidth=" + BindWidth);
            System.out.println("IF_Index=" + IF_Index);
        }
        MultiSiteSpectrum iABS = new MultiSiteSpectrum();
        MultiSiteSpectrum qABS = new MultiSiteSpectrum();
        MultiSiteSpectrum iABS_MW = new MultiSiteSpectrum();
        MultiSiteSpectrum qABS_MW = new MultiSiteSpectrum();
        MultiSiteSpectrumComplex iABS1 = new MultiSiteSpectrumComplex();
        MultiSiteSpectrumComplex qABS1 = new MultiSiteSpectrumComplex();
        if (IQimbalace_falg) {
            iABS1 = capWave.getReal().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).fft();
            qABS1 = capWave.getImaginary().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).fft();
            iABS = iABS1.abs();
            qABS = qABS1.abs();
        } else {
            iABS = capWave.getReal().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.V);
            qABS = capWave.getImaginary().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.V);
            iABS_MW = capWave.getReal().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.mW);
            qABS_MW = capWave.getImaginary().setWindowFunction(WindowFunction.HANNING)
                    .setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).spectrum(SpectrumUnit.mW);

            // iABS1 =
            // capWave.getReal().setWindowFunction(WindowFunction.HANNING).setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).fft();
            // qABS1 =
            // capWave.getImaginary().setWindowFunction(WindowFunction.HANNING).setWindowScaling(WindowScaling.SCALE_FOR_AMPLITUDE).fft();
            // iABS = iABS1.abs();
            // qABS = qABS1.abs();

        }
        if (IF_Index < (sampleSize / 2)) {
            if (false) { // smt 8 calculation method
                MultiSiteDouble idBm_S = iABS.getValue(IF_Index).pow(2); // v*v
                MultiSiteDouble idBm_L = iABS.getValue(IF_Index - 1).pow(2);
                MultiSiteDouble idBm_H = iABS.getValue(IF_Index + 1).pow(2);

                idBm = idBm_S.add(idBm_L).add(idBm_H).log10().multiply(10).add(10);

                MultiSiteDouble qdBm_S = qABS.getValue(IF_Index).pow(2);
                MultiSiteDouble qdBm_L = qABS.getValue(IF_Index - 1).pow(2);
                MultiSiteDouble qdBm_H = qABS.getValue(IF_Index + 1).pow(2);

                qdBm = qdBm_S.add(qdBm_L).add(qdBm_H).log10().multiply(10).add(10);
            } else {
                // smt 7 calculation method
                MultiSiteDouble imax_value = new MultiSiteDouble();
                MultiSiteLong imax_Index = new MultiSiteLong();
                MultiSiteDouble qmax_value = new MultiSiteDouble();
                MultiSiteLong qmax_Index = new MultiSiteLong();
                int offset = 2;
                MultiSiteLong offset2 = new MultiSiteLong(3);// 6 20170226
                if (UserCommon.debug_flag) {
                    iABS.plot(" iABS unit:v    ");

                    qABS.plot(" iABS unit:v    ");
                }
                // imax_value= iABS.extractValues(IF_Index-offset, offset*2).max(); //unit is V
                // imax_Index= iABS.extractValues(IF_Index-offset, offset*2).maxIndex();
                // IF_index_max.set(imax_Index.add(IF_Index-offset));
                //// idBm = imax_value.log10().multiply(20).add(10); //convet to dbm
                // idBm = iABS.getValue(IF_index_max).log10().multiply(20).add(10); //update
                // 20180116
                //// idBm = iABS.getValue(IF_Index).log10().multiply(20).add(10); //convet to dbm
                //
                // qmax_value= qABS.extractValues(IF_Index-offset, offset*2).max();
                // qmax_Index= qABS.extractValues(IF_Index-offset, offset*2).maxIndex();
                // IF_index_max.set(qmax_Index.add(IF_Index-offset));
                //// qdBm = qmax_value.log10().multiply(20).add(10);
                // qdBm = qABS.getValue(IF_index_max).log10().multiply(20).add(10); //update
                // 20180116

                //
                // imax_value= iABS.extractValues(IF_Index-offset, offset*2).max(); //unit is V
                imax_Index = iABS.extractValues(IF_Index - offset, offset * 2).maxIndex();
                IF_index_max.set(imax_Index.add(IF_Index - offset));
                // idBm = imax_value.log10().multiply(20).add(10); //convet to dbm
                idBm = (iABS_MW.extractValues(IF_index_max.subtract(1), offset2).sum()).log10()
                        .multiply(10); // update 20180116 unit is mw =>dbm
                // idBm = iABS.getValue(IF_Index).log10().multiply(20).add(10); //convet to dbm

                // qmax_value= qABS.extractValues(IF_Index-offset, offset*2).max();
                qmax_Index = qABS.extractValues(IF_Index - offset, offset * 2).maxIndex();
                IF_index_max.set(qmax_Index.add(IF_Index - offset));
                // qdBm = qmax_value.log10().multiply(20).add(10);
                // qdBm = qABS.getValue(IF_index_max).log10().multiply(20).add(10); //update
                // 20180116
                qdBm = (qABS_MW.extractValues(IF_index_max.subtract(1), offset2).sum()).log10()
                        .multiply(10); // update 20180116

                // qdBm = qABS.getValue(IF_Index).log10().multiply(20).add(10); //20171210
                // System.out.println("IF_index_max="+IF_index_max);

            }
        } else {
            idBm.set(999);
            qdBm.set(999);
        }
        MultiSiteDouble Path_Loss = new MultiSiteDouble(0.0);
        Path_Loss = Path_Loss(mode, testFrequency, TX_RX);

        if (UserCommon.debug_flag) {
            System.out.println(
                    "mode=" + mode + " ; testFrequency=" + testFrequency + "  ;TX_RX=" + TX_RX);
            System.out.println("Path_Loss" + Path_Loss);
        }
        // double palos=0.0;
        // i_gain_db = idBm.subtract(inputPower-palos);
        // q_gain_db = qdBm.subtract(inputPower-palos);
        i_gain_db = idBm.subtract(Path_Loss.multiply(-1).add(inputPower));
        q_gain_db = qdBm.subtract(Path_Loss.multiply(-1).add(inputPower));

        /////////////////////////////////////////////////
        // IQ Balance calculation //
        /////////////////////////////////////////////////
        result.add(i_gain_db);
        result.add(q_gain_db);
        if (IQimbalace_falg) {
            ampBalance = idBm.subtract(qdBm);

            phaseI = iABS1.getValue(IF_index_max).atan2();
            phaseQ = qABS1.getValue(IF_index_max).atan2();
            // qPhs =qCpx.getValue(IF_Index).atan2().divide(Math.PI).multiply(180.0);// in degree
            phaseBalance = (phaseQ.subtract(phaseI)).divide(Math.PI).multiply(180.0);

            for (int iSite : activeSites) {
                if (phaseBalance.get(iSite) > 180.0) {
                    phaseBalance.set(iSite, phaseBalance.get(iSite) - 180.0);
                } else if (phaseBalance.get(iSite) < -180.0) {
                    phaseBalance.set(iSite, phaseBalance.get(iSite) + 180.0);
                }
            }

            phaseBalance = (new MultiSiteDouble(90.0)).subtract(phaseBalance.abs()); // 90-abs(imbalance)
            result.add(ampBalance);
            result.add(phaseBalance);

        }

        return (result);
    }

    // public static long Trim_Code(double Vtest,double Vstep, long TrimCode)
    // {
    // if(Vtest>=0.75)
    // {
    // TrimCode=7-(long)((Vtest-0.75)/Vstep);
    // }
    // else if(Vtest<0.75)
    // {
    // TrimCode=7-(long)((Vtest-0.75)/Vstep)+7+1;
    // }
    // if(TrimCode>15) {
    // TrimCode=15;
    // }
    //
    // return TrimCode;
    //
    //
    // }
    public static long Trim_Code(double Vtest, double Vstep, long TrimCode) {
        if (Vtest >= 0.75) {
            TrimCode = 7 - (long) ((Vtest - 0.75) / Vstep);
        } else if (Vtest < 0.75) {
            TrimCode = (long) ((0.75 - Vtest) / Vstep) + 7 + 1;
        }
        if (TrimCode > 15) {
            TrimCode = 15;
        }
        // println("trimcode============="+TrimCode);
        return TrimCode;

    }

    public static MultiSiteLong Get_ArrayI_Delta(MultiSiteWaveLong aiCapData_I) {
        MultiSiteLong max = aiCapData_I.max();
        MultiSiteLong min = aiCapData_I.min();
        MultiSiteLong delta = max.subtract(min);
        // delta.set(2);
        return delta;

    }

}
