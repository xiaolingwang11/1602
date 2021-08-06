package tmlCommon;

import javax.annotation.Nullable;

import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.datatypes.dsp.MultiSiteSpectrum;
import xoc.dta.datatypes.dsp.MultiSiteWaveComplex;
import xoc.dta.datatypes.dsp.MultiSiteWaveDouble;
import xoc.dta.datatypes.dsp.MultiSiteWaveLong;
import xoc.dta.datatypes.dsp.SpectrumUnit;
import xoc.dta.setupaccess.IPattern;

public class Msg {

    public static boolean isDebugEnabled() {
        return true;
    }

    public static boolean isInfoEnabled() {
        return true;
    }

    public static boolean isWarningEnabled() {
        return true;
    }

    public static boolean isErrorEnabled() {
        return true;
    }

    public static boolean isFatalEnabled() {
        return true;
    }

    public static void blankLine() {
        Msg.blankLine(1);
    }

    public static void blankLine(int numOfLines) {

        if(numOfLines <= 0) {
            System.out.print("Error: Msg.blankLine(numOfLines):\tnumOfLines <= 0");
        }

        numOfLines = numOfLines <= 0 ? 1 : numOfLines;
        for (int i = 0; i < numOfLines; i++) {
            System.out.print("\n");
        }
    }

    public static void error(@Nullable Object msg) {
        Msg.error(true, msg);
    }

    /**
     * @msgEnable: <b>only print error message when ( msgEnable == true )
     */
    public static void error(boolean msgEnable, @Nullable Object msg) {
        if (msgEnable) {
            System.out.println("Error:\t" + msg);
        }
    }

    public static void warning(@Nullable Object msg) {
        System.out.println("Warning:\t" + msg);
    }

    public static void info(@Nullable Object msg) {
        System.out.println("Info:\t" + msg);
    }

    public static void debug(@Nullable Object msg) {
        System.out.println("Debug:\t" + msg);
    }
    public static void debug(MultiSiteDoubleArray msDoubleArray) {
        int size = msDoubleArray.length();
        for(int i = 0; i < size; i++) {
            System.out.println("Array_" + i + "\t" +msDoubleArray.getElement(i));
        }

    }

    public static void debugSC(IPattern patMod, String signal, int vectorNumber) {
        System.out.print("Debug:\t");
        System.out.print("Vec" + vectorNumber + " -> ");
        System.out.println(patMod.vector(42282).readStateChar(signal));
    }


    // ###################################################
    // waveform
    // ###################################################

    public static void debugWaveform(MultiSiteWaveComplex capWave, String msg,
            boolean siteOverlay) {
        capWave.plot(msg, siteOverlay);
    }

    public static void debugWaveform(MultiSiteWaveComplex capWave) {
        Msg.debugWaveform(capWave, "MultiSiteWaveComplex", false);
    }

    public static void debugWaveform(MultiSiteWaveDouble capWave, String msg, boolean siteOverlay) {
        capWave.plot(msg, false);
    }

    public static void debugWaveform(MultiSiteWaveDouble capWave) {
        Msg.debugWaveform(capWave, "MultiSiteWaveDouble", false);
    }

    public static void debugWaveform(MultiSiteWaveLong capWave, String msg, boolean siteOverlay) {
        capWave.plot(msg, false);
    }

    public static void debugWaveform(MultiSiteWaveLong capWave) {
        Msg.debugWaveform(capWave, "MultiSiteWaveLong", false);
    }

    // ###################################################
    // spectrum
    // ###################################################

    public static void debugSpectrum(MultiSiteSpectrum spect, String msg, boolean siteOverlay) {
        spect.plot(msg, false);
    }

    public static void debugSpectrum(MultiSiteSpectrum spect, String msg) {
        Msg.debugSpectrum(spect, msg, false);
    }

    public static void debugSpectrum(MultiSiteSpectrum spect) {
        Msg.debugSpectrum(spect, "MultiSiteSpectrum", false);
    }

    // -----------------

    public static void debugSpectrum(MultiSiteWaveComplex capWave, String msg,
            boolean siteOverlay) {
        Msg.debugSpectrum(capWave.spectrum(SpectrumUnit.dBm), msg, siteOverlay);
    }

    public static void debugSpectrum(MultiSiteWaveComplex capWave, String msg) {
        Msg.debugSpectrum(capWave.spectrum(SpectrumUnit.dBm), msg, false);
    }

    public static void debugSpectrum(MultiSiteWaveComplex capWave) {
        Msg.debugSpectrum(capWave.spectrum(SpectrumUnit.dBm), "SpectrumFromWf", false);
    }

}
