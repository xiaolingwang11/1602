package GC1602_tml.EFUSE;

import pa.NemoLPA;
import pa.PAWVar;
import pa.TransactSeq_Efuse;
import tmlCommon.GVar;
import tmlCommon.Msg;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupProtocolInterface;
import xoc.dsa.SetupVariableType;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.MultiSiteBoolean;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDigInOutPassFailResults;

public class Ddie_Efuse_Write_debug extends TMLBase {

    public IMeasurement measurement1;

    public String testSignals = "RFCTL_2_+RFCTL_3_+RFCTL_4_+RFCTL_9_+RFCTL_10_+NF_DATA_0+NF_DATA_2";
    public int Efs_W_Idx_Start = 46;
    public int Efs_W_Idx_End = 57;
    private static final String protInterfaceName = "EFUSE";
    private static final String transactSeqName = "EFUSE_transaction1";

    private PAWVar paWriteConfig[];

    @In
    public MultiSiteBoolean stop;

    @Override
    public void setup() {

        tsName = getTestSuiteName();

        paWriteConfig = new PAWVar[GVar.EfuseReadCnt];
        for (int i = 0; i < GVar.EfuseReadCnt; i++) {
            paWriteConfig[i] = new PAWVar();
        }

        IDeviceSetup devSetup1 = newDevSetup(importSpec);

        ISetupProtocolInterface EFUSE_wr = NemoLPA.addInterface_EFUSE(devSetup1, protInterfaceName);

        TransactSeq_Efuse efuse = new TransactSeq_Efuse(EFUSE_wr, transactSeqName);

        for (int i = Efs_W_Idx_Start; i <= Efs_W_Idx_End; i++) {
            paWriteConfig[i].transactVarName = "Block" + i;
            paWriteConfig[i].dsVarName = "dsVar" + i;
            efuse.dyWrite((i + (i << 8)), paWriteConfig[i].transactVarName);
            devSetup1.addVariable(SetupVariableType.UnsignedLong, paWriteConfig[i].dsVarName, 0);
        }

        String args[] = PAWVar.getArguments(paWriteConfig, Efs_W_Idx_Start, Efs_W_Idx_End);


        devSetup1.transactionSequenceCall(transactSeqName, efuse.trseqDef, args);

        measurement1.setSetups(devSetup1);
    }

    String dSig = "RFCTL_9_";

    @Override
    public void execute() {


        for (int i = Efs_W_Idx_Start; i <= Efs_W_Idx_End; i++) {
            measurement1.spec().setVariable(paWriteConfig[i].dsVarName, i);
        }


        measurement1.execute();


        IDigInOutPassFailResults passFailResults = measurement1.digInOut(dSig).preservePassFailResults();

        Msg.debug("Pass Flag\t" + passFailResults.hasPassed(dSig));
        Msg.debug("Efs_W_Idx_Start\t" + Efs_W_Idx_Start);
        Msg.debug("Efs_W_Idx_End\t" + Efs_W_Idx_End);

    }

}
