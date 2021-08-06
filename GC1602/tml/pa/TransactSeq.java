package pa;

import java.util.HashMap;
import java.util.Map;

import tmlCommon.Msg;
import xoc.dsa.ISetupProtocolInterface;
import xoc.dsa.ISetupTransactionSeqDef;
import xoc.dsa.ISetupTransactionSeqDef.Direction;
import xoc.dsa.ISetupTransactionSeqDef.Type;
import xoc.dta.datatypes.MultiSiteLong;
import xoc.dta.resultaccess.IProtocolInterfaceResults;
import xoc.dta.resultaccess.ITransactionSequenceResults;

public class TransactSeq {

    public static final double default_per_ns_SPI = 8000.0 / 26.0;
    public static final double default_per_ns_FDMA = 1000.0 / 26.0;
    public static final double default_per_ns_Ohter = 1000.0 / 26.0;

    /**
     * @ 0x100000000 @ 4294967296
     */
    private static final long CPLMT = 4294967296L;
    public ISetupTransactionSeqDef trseqDef;
    public String interfaceName;
    public String trseqName;

    public long writeCount = 0;
    public long readCount = 0;
    public long waitCount = 0;
    public long cyclePerWrite = 0;
    public long cyclePerRead = 0;
    public long totalWaitCycles = 0;

    /**
     * <b>the cycle period of the protocol is running, when you call wait(double) function, i will
     * calculate how many cycles to wait based on this parameter. you do not need to spedify this
     * parameter if you do not call wait(double) function
     *
     * @<b>Unit is second
     */
    public Double Period = -1.0;

    public double getExeTime() {
        if (Period <= 0) {
            System.out.println("Error: wait cycle number <= 0  @ " + trseqName);
        }

        double time = Period
                * (writeCount * cyclePerWrite + readCount * cyclePerRead + totalWaitCycles);
        return time;
    }

    public HashMap<String, Long> Read = new HashMap<String, Long>();

    public TransactSeq(ISetupProtocolInterface paInterface, String transactSeqName) {
        trseqName = transactSeqName;
        interfaceName = paInterface.getName();
        trseqDef = paInterface.addTransactionSequenceDef(trseqName);
        writeCount = 0;
        readCount = 0;
        waitCount = 0;
        cyclePerWrite = 0;
        cyclePerRead = 0;
        totalWaitCycles = 0;
    }

    public String getTrseqName() {
        return trseqDef.getName();
    }

    public void write(long addr, long val) {
        if (checkNegativeLong(addr)) {
//             System.out.printf(":0x%08x\n", addr);
            addr = addr + CPLMT;
        }
        if (checkNegativeLong(val)) {
            val = val + CPLMT;
        }
//        System.out.printf("0x%08x\t<-\t", addr);
//        System.out.printf("0x%08x\n", val);
        trseqDef.addTransaction("write", addr, val);

        writeCount = writeCount + 1;
    }

    public void dyWrite(long addr, String varName) {
        if (checkNegativeLong(addr)) {
            addr = addr + CPLMT;
        }
        trseqDef.addParameter(Type.UnsignedLong, Direction.IN, varName);
        trseqDef.addTransaction("write", addr, varName);

        writeCount = writeCount + 1;
    }

    public void waitCycle(long cycleNum) {
        if (cycleNum <= 0) {
            System.out.println("Error: wait cycle number <= 0  @ " + trseqName);
        } else {
            trseqDef.addTransaction("waitCycles", cycleNum);
        }

        totalWaitCycles = totalWaitCycles + cycleNum;
    }

    public void wait(Double waitTime) {
        if (Period <= 0) {
            System.out.println("Error: Period <= 0 @ " + trseqName);
            System.out.println("Warning: Did you specified the < Period > parameter ????");
        }

        if (waitTime <= 0) {
            System.out.println("Error: wait cycle number <= 0  @ " + trseqName);
        }

        int cycleNum = (int) (waitTime / Period + 1.0);

        if (cycleNum <= 0) {
            System.out.println("Error: wait cycle number <= 0  @ " + trseqName);
        } else {
            trseqDef.addTransaction("waitCycles", cycleNum);
        }

    }

    public void read(long addr, String readDataName) {
        if (checkNegativeLong(addr)) {
            addr = addr + CPLMT;
        }
        trseqDef.addParameter(Type.UnsignedLong, Direction.OUT, readDataName);
        trseqDef.addTransaction("read", addr, readDataName);
        Read.put(readDataName, addr);

        readCount = readCount + 1;
    }
    public void read(long addr) {

        if (checkNegativeLong(addr)) {
            addr = addr + CPLMT;
        }
        String readDataName = "_"+toHex(addr);

        if(Read.containsKey(readDataName)) {
            return;
        }

        trseqDef.addParameter(Type.UnsignedLong, Direction.OUT, readDataName);
        trseqDef.addTransaction("read", addr, readDataName);
        Read.put(readDataName, addr);

        readCount = readCount + 1;
    }

    public static String toHex(long val) {
        if (checkNegativeLong(val)) {
            val = val + CPLMT;
        }
//        Msg.debug("0x" + Long.toHexString(val));
        String str = String.format("%8s",Long.toHexString(val).toUpperCase());
        str = str.replaceAll(" ", "0");
        return str;
//        Msg.debug(str);
//        Msg.debug(str.replaceAll(regex, replacement));
    }

    public void printAllReadResult(IProtocolInterfaceResults preservedPARsult, int[] activeSites,
            int exeIndex) {

        if (Read.size() <= 0) {
            System.out.println("Warning: no read transaction recorded!!\n");
            return;
        }

        ITransactionSequenceResults trseqRsult = preservedPARsult.transactSeq(trseqName)[exeIndex];

        System.out.println("\n========= PARead Result =========");
        System.out.println("ProtocolInterface :" + interfaceName);
        System.out.println("TransactSeqName:" + trseqName);

        for (Map.Entry<String, Long> entry : Read.entrySet()) {
            // System.out.printf(entry.getKey() + "\t0x%08x --> ", entry.getValue());

            MultiSiteLong readValue = trseqRsult.getValueAsLong(entry.getKey());

            System.out.printf(entry.getKey() + " --> ");
            for (int site : activeSites) {
                System.out.printf("\tS" + site);
                System.out.printf(":0x%08x", readValue.get(site));
            }
            System.out.print("\n");

        }

        Msg.debug("");
    }

    public void printAllReadResult(IProtocolInterfaceResults preservedPARsult, int exeIndex) {

        ITransactionSequenceResults trseqRsult = preservedPARsult.transactSeq(trseqName)[exeIndex];

        System.out.println("\n========= PARead Result =========");
        System.out.println("ProtocolInterface :" + interfaceName);
        System.out.println("TransactSeqName:" + trseqName);
        if (Read.size() <= 0) {
            System.out.println("Warning: no read transaction recorded!!\n");
            return;
        }

        for (Map.Entry<String, Long> entry : Read.entrySet()) {
            // System.out.printf(entry.getKey() + "\t0x%08x --> ", entry.getValue());
            System.out.printf(entry.getKey() + "\t--> ");
            MultiSiteLong readValue = trseqRsult.getValueAsLong(entry.getKey());
            System.out.print(readValue);
            System.out.printf("\tS3:0x%08x", readValue.get(3));
            System.out.printf("\tS4:0x%08x\n", readValue.get(4));

        }
    }

    public void printAllReadResult(IProtocolInterfaceResults preservedPARsult) {
        printAllReadResult(preservedPARsult, 0);
    }

    public static boolean checkNegativeLong(long val) {
        if (val < 0) {
            // System.out.println("Warning: Negative Long Value @ " + trseqName + " @ " +
            // interfaceName);
            return true;
        }
        return false;
    }

}
