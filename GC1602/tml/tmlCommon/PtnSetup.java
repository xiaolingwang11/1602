package tmlCommon;

import pa.TransactSeq;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupPattern;

public class PtnSetup {

    public long rawPtnCycles = -999;

    public PtnSetup(String ptn) {
        ptnName = ptn;
        rngStart = 0;
        rngEnd = -1;
        exeTimes = 1;
        // ref = ds.getPatternRef(ptnName,rngStart,rngEnd);
        setRawPtnCycles();
    }

    public PtnSetup(int executTimes, String ptn) {
        ptnName = ptn;
        rngStart = 0;
        rngEnd = -1;
        exeTimes = executTimes;
        // ref = ds.getPatternRef(ptnName,rngStart,rngEnd);
        setRawPtnCycles();
    }

    public PtnSetup(int executTimes, String ptn, int iEnd) {
        ptnName = ptn;
        rngStart = 0;
        rngEnd = iEnd;
        exeTimes = executTimes;
        // ref = ds.getPatternRef(ptnName,rngStart,rngEnd);
        setRawPtnCycles();
    }

    public PtnSetup(String ptn, int iEnd) {
        ptnName = ptn;
        rngStart = 0;
        rngEnd = iEnd;
        exeTimes = 1;
        // ref = ds.getPatternRef(ptnName,rngStart,rngEnd);
        setRawPtnCycles();
    }

    public PtnSetup setRawPtnCycles(long cycles) {
        rawPtnCycles = cycles;
        return this;
    }

    public PtnSetup setRawPtnCycles() {

        if (ptnName.equals(TMLBase.ptn_BT_DAC_mode)) {
            rawPtnCycles = 145405;
        } else if (ptnName.equals(TMLBase.ptn_BT_DAC_mode_FM)) {
            rawPtnCycles = 145405;
        } else if (ptnName.equals(TMLBase.ptn_BT_IQADC_mode)) {
            rawPtnCycles = 131069;
        } else if (ptnName.equals(TMLBase.ptn_BT_RSSIADC_mode)) {
            rawPtnCycles = 129803;
        } else if (ptnName.equals(TMLBase.ptn_FM_ADC_mode_BT)) {
            rawPtnCycles = 134631;
        } else if (ptnName.equals(TMLBase.ptn_FM_ADC_mode)) {
            rawPtnCycles = 134631;
        } else if (ptnName.equals(TMLBase.ptn_FM_ADC_mode_init)) {
            rawPtnCycles = 5001;
        } else if (ptnName.equals(TMLBase.ptn_BT_RSSIADC_mode_init)) {
            rawPtnCycles = 5001;
        } else if (ptnName.equals(TMLBase.ptn_BT_DAC_mode_init_FM)) {
            rawPtnCycles = 5426;
        } else if (ptnName.equals(TMLBase.ptn_BT_DAC_mode_init)) {
            rawPtnCycles = 5426;
        } else {
            Msg.warning("ptnName not expected @ " + ptnName);
        }

        return this;
    }

    // this will be deprecated
    // public PtnSetup(String ptn, int iEnd, int executTimes) {
    // ptnName = ptn;
    // rngStart = 0;
    // rngEnd = iEnd;
    // exeTimes = executTimes;
    // // ref = ds.getPatternRef(ptnName,rngStart,rngEnd);
    // }

    public ISetupPattern getRef(IDeviceSetup ds) {
        if (exeTimes < 1) {
            System.out.println("Error: PtnSetup -> exeTimes < 1");
        }
        ISetupPattern ref = null;
        if (rngEnd <= 0) {

            ref = ds.getPatternRef(getOptionName(), ptnName);
        } else {
            ref = ds.getPatternRef(getOptionName(), ptnName, rngStart, rngEnd);
        }
        return ref;
    }

    public ISetupPattern getRef(IDeviceSetup ds, double exeTime) {
        if (exeTimes < 1) {
            System.out.println("Error: PtnSetup -> exeTimes < 1");
        }
        if (rawPtnCycles < 0) {
            System.out.println("Error: PtnSetup -> rawPtnCycles < 0");
        }

        double perOther = TransactSeq.default_per_ns_Ohter * TMLBase.ns;

        exeTimes = 1;
        while (true) {
            if (perOther * rawPtnCycles * exeTimes < exeTime) {
                exeTimes = exeTimes + 1;
            } else {
                break;
            }
        }


        ISetupPattern ref = null;
        rngEnd = -1;
        if (rngEnd <= 0) {

            ref = ds.getPatternRef(getOptionName(), ptnName);
        } else {
            ref = ds.getPatternRef(getOptionName(), ptnName, rngStart, rngEnd);
        }
        return ref;
    }

    public String getOptionName() {
        String ptn = ptnName.substring(1 + ptnName.lastIndexOf("."));
        String optName;
        if (rngEnd <= 0) {
            optName = ptn + "_" + exeTimes + "times";
        } else {
            optName = ptn + "_" + rngEnd + "cycles_" + exeTimes + "times";
        }
        return optName;
    }

    public String ptnName = "";
    public int rngStart;
    public int rngEnd;
    public int exeTimes;
    // public ISetupPattern ref;

}
