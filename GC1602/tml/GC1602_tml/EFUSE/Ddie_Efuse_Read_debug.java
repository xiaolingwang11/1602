package GC1602_tml.EFUSE;

import pa.NemoLPA;
import pa.TransactSeq_Efuse;
import tmlCommon.GVar;
import tmlCommon.Msg;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupProtocolInterface;
import xoc.dta.datatypes.MultiSiteLong;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IProtocolInterfaceResults;
import xoc.dta.resultaccess.ITransactionSequenceResults;

public class Ddie_Efuse_Read_debug extends TMLBase {

    public IMeasurement measurement;


    private static final String protInterfaceName = "EFUSEinterface";
    private static final String transactSeqName = "EFUSE_transaction1";
    public int readStartIdx = 46;
    public int readStoptIdx = 59;
    TransactSeq_Efuse trseqEfuse = null;
    @Override
    public void setup() {
        tsName = getTestSuiteName();
        IDeviceSetup devSetup1 = newDevSetup(importSpec);

        ISetupProtocolInterface EFUSE_wr = NemoLPA.addInterface_EFUSE(devSetup1, protInterfaceName);
        trseqEfuse = new TransactSeq_Efuse(EFUSE_wr, transactSeqName);

        if (readStoptIdx >= GVar.EfuseReadCnt || readStartIdx > readStoptIdx || readStartIdx < 0) {
            Msg.error("Error:Efusee buff setting error");
        }

        for (int i = readStartIdx; i <= readStoptIdx; i++) {
            trseqEfuse.read((i + (i << 8)), "Block" + i);
        }

        //devSetup1.parallelBegin();
        devSetup1.transactionSequenceCall(trseqEfuse.trseqName, trseqEfuse.trseqDef);
        //devSetup1.patternCall(ptn_Efuse_Ohter_Pins);
        //devSetup1.parallelEnd();

        measurement.setSetups(devSetup1);
    }

    @Override
    public void update() {
        measurement.digInOut().setConnect(true).setDisconnect(true);
        measurement.dcVI().setConnect(true).setDisconnect(true);
    }

    @Override
    public void execute() {
        try {
            Msg.info(tsName);
            measurement.digInOut().setConnect(true).setDisconnect(false);
            measurement.execute();


            IProtocolInterfaceResults paResults1;
            paResults1 = measurement.protocolInterface(trseqEfuse.interfaceName).preserveResults();

            // [0] means result of the first execution of the specified transaction sequence
            ITransactionSequenceResults tsResults1 = paResults1.transactSeq(trseqEfuse.trseqName)[0];

            MultiSiteLong DEfuse_Data[] = new MultiSiteLong[GVar.EfuseReadCnt];
            Msg.info("-------------------------");
            for (int i = readStartIdx; i <= readStoptIdx; i++) {
                DEfuse_Data[i] = tsResults1.getValueAsLong("Block" + i);
                Msg.info("Block" + i + DEfuse_Data[i]);
            }
            Msg.info("-------------------------");

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // **************************************************************
    // **************************************************************


}
