package GC1602_tml.EFUSE;

import java.util.HashMap;
import java.util.Map;

import pa.NemoLPA;
import pa.TransactSeq_Efuse;
import tmlCommon.GVar;
import tmlCommon.Msg;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupProtocolInterface;
import xoc.dta.ParameterGroupCollection;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.MultiSiteLong;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IProtocolInterfaceResults;
import xoc.dta.resultaccess.ITransactionSequenceResults;

public class Ddie_Efuse_Read extends TMLBase {

    public IMeasurement measurement;
    public ParameterGroupCollection<TST_NAME_ParaGroup> tsColl = new ParameterGroupCollection<>();
    public ParameterGroupCollection<TST_NAME_ParaGroup> tsColl1 = new ParameterGroupCollection<>();

    @In
    public boolean Read_all = false;

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

        devSetup1.sequentialBegin();
        devSetup1.transactionSequenceCall(trseqEfuse.trseqName, trseqEfuse.trseqDef);
        devSetup1.sequentialEnd();

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
            measurement.execute();


            IProtocolInterfaceResults paResults1;
            paResults1 = measurement.protocolInterface(trseqEfuse.interfaceName).preserveResults();

            // [0] means result of the first execution of the specified transaction sequence
            ITransactionSequenceResults tsResults1 = paResults1.transactSeq(trseqEfuse.trseqName)[0];

            releaseTester();

            MultiSiteLong DEfuse_Data[] = new MultiSiteLong[GVar.EfuseReadCnt];
            Msg.info("-------------------------");
            for (int i = readStartIdx; i <= readStoptIdx; i++) {
                DEfuse_Data[i] = tsResults1.getValueAsLong("Block" + i);
                Msg.info("Block" + i + DEfuse_Data[i]);
            }
            Msg.info("-------------------------");

            Data_Processing(DEfuse_Data);
            Data_Processing_If_Read_All(DEfuse_Data);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // **************************************************************
    // **************************************************************

    private void Data_Processing(MultiSiteLong[] DEfuse_Data) {
        MultiSiteLong Y_loc = new MultiSiteLong();
        MultiSiteLong X_loc = new MultiSiteLong();
        MultiSiteLong Wafer_ID = new MultiSiteLong();
        MultiSiteLong LotID0 = new MultiSiteLong();
        MultiSiteLong LotID1 = new MultiSiteLong();
        MultiSiteLong LotID2 = new MultiSiteLong();
        MultiSiteLong LotID3 = new MultiSiteLong();
        MultiSiteLong LotID4 = new MultiSiteLong();
        MultiSiteLong LotID5 = new MultiSiteLong();

        // Read UID
        Y_loc.set(DEfuse_Data[58].and(0x7F)); // block59
        X_loc.set((DEfuse_Data[58].and(0x3F80)).rightShift(7)); // block59
        Wafer_ID.set((DEfuse_Data[58].and(0x7C000)).rightShift(14)); // block59
        LotID0.set((DEfuse_Data[58].and(0x1F80000)).rightShift(19)); // block59
        LotID1.set((DEfuse_Data[58].and(0x7E000000)).rightShift(25)); // block59

        LotID2.set(DEfuse_Data[59].and(0x3F)); // block95
        LotID3.set((DEfuse_Data[59].and(0xFC0)).rightShift(6)); // block95
        LotID4.set((DEfuse_Data[59].and(0x3F000)).rightShift(12)); // block95
        LotID5.set((DEfuse_Data[59].and(0xFC0000)).rightShift(18)); // block95

        // transfer UID information to next suite
        GVar.Y_loc_R.set(Y_loc); // block94
        GVar.X_loc_R.set(X_loc); // block94
        GVar.Wafer_ID_R.set(Wafer_ID); // block94
        GVar.LotID0_R.set(LotID0); // block94
        GVar.LotID1_R.set(LotID1); // block94
        GVar.LotID2_R.set(LotID2); // block95
        GVar.LotID3_R.set(LotID3); // block95
        GVar.LotID4_R.set(LotID4); // block95
        GVar.LotID5_R.set(LotID5); // block95

        String Keys21[] = { "LotID5", "LotID4", "LotID3", "LotID2", "LotID1", "LotID0", "WaferID",
                "X_loc", "Y_loc" };
        MultiSiteLong Keys2[] = { LotID5, LotID4, LotID3, LotID2, LotID1, LotID0, Wafer_ID, X_loc,
                Y_loc };

        Map<String, MultiSiteLong> result1 = new HashMap<String, MultiSiteLong>();
        for (int i = 0; i < Keys2.length; i++) {
            result1.put(Keys21[i], Keys2[i]);
        }


        for (TST_NAME_ParaGroup tmp : tsColl.values()) {
            tmp.ptd.evaluate(result1.get(tmp.getId()));
        }

        // ************************************************************************
        MultiSiteLong chip_version_R = new MultiSiteLong();
        MultiSiteLong PRG_Type_R = new MultiSiteLong();
        MultiSiteLong PRG_Version_R = new MultiSiteLong();

        chip_version_R = DEfuse_Data[56].or(DEfuse_Data[57]).and(0xF000).rightShift(12);
        PRG_Type_R = DEfuse_Data[56].or(DEfuse_Data[57]).and(0xC00).rightShift(10); // block90,91
        PRG_Version_R = DEfuse_Data[56].or(DEfuse_Data[57]).and(0x3FF); // block90,91

        GVar.PRG_Version_E.set(PRG_Version_R);
        GVar.PRG_Type_E.set(PRG_Type_R);

    }

    private void Data_Processing_If_Read_All(MultiSiteLong[] DEfuse_Data) {
        if (!Read_all) {
            return;
        }

        MultiSiteLong THM1_Offset_Code_R = DEfuse_Data[50].or(DEfuse_Data[51]).and(0x7F000000)
                .rightShift(24); // block50,51
        // bit[30:16]
        MultiSiteLong THM1_Ratio_Code_R = DEfuse_Data[50].or(DEfuse_Data[51]).and(0xFE0000)
                .rightShift(17); // block50,51
        MultiSiteLong THM1_Ratio_Sign_R = DEfuse_Data[50].or(DEfuse_Data[51]).and(0x10000)
                .rightShift(16); // block50,51
        MultiSiteLong THM_CP_Offset_Code_R = DEfuse_Data[50].or(DEfuse_Data[51]).and(0x7F00)
                .rightShift(9); // block50,51
        // bit[15:1]
        MultiSiteLong THM_CP_Ratio_Code_R = DEfuse_Data[50].or(DEfuse_Data[51]).and(0xFE)
                .rightShift(2); // block50,51
        MultiSiteLong THM_CP_Ratio_Sign_R = DEfuse_Data[50].or(DEfuse_Data[51]).and(0x1)
                .rightShift(1); // block50,51
        MultiSiteLong FLG_R = DEfuse_Data[52].or(DEfuse_Data[53]).and(0x40000000).rightShift(30);
        MultiSiteLong USB20_TUNEOTG_R = DEfuse_Data[52].or(DEfuse_Data[53]).and(0x38000000)
                .rightShift(27);
        MultiSiteLong USB20_TFREGRES_R = DEfuse_Data[52].or(DEfuse_Data[53]).and(0x7E00000)
                .rightShift(21);
        MultiSiteLong USB20_TFHSRES_R = DEfuse_Data[52].or(DEfuse_Data[53]).and(0x1F0000)
                .rightShift(16);
        MultiSiteLong LVDSRF_MIPI_R = DEfuse_Data[52].or(DEfuse_Data[53]).and(0xF000)
                .rightShift(12);
        MultiSiteLong LVDSRF_RX_VMode_R = DEfuse_Data[52].or(DEfuse_Data[53]).and(0xE00)
                .rightShift(9);
        MultiSiteLong K_APCOUT_R = DEfuse_Data[48].or(DEfuse_Data[49]).and(0x7FF80000)
                .rightShift(19);
        MultiSiteLong B_APCOUT_R = DEfuse_Data[48].or(DEfuse_Data[49]).and(0xFFC0).rightShift(6);
        MultiSiteLong Bounding_30_R = DEfuse_Data[54].or(DEfuse_Data[55]).and(0xC0000000)
                .rightShift(30);
        MultiSiteLong Bounding_28_R = DEfuse_Data[54].or(DEfuse_Data[55]).and(0x30000000)
                .rightShift(28); // block90,91
        MultiSiteLong Bounding_0_R = DEfuse_Data[54].or(DEfuse_Data[55]).and(0x3FF); // block90,91
        MultiSiteLong L_LDOREF_BT_VCO_R = DEfuse_Data[46].or(DEfuse_Data[47]).and(0x7800000)
                .rightShift(23); // V-CAL BT_LLDO
        MultiSiteLong LDOREF_ABB_R = DEfuse_Data[46].or(DEfuse_Data[47]).and(0x70000)
                .rightShift(16);
        MultiSiteLong LDOREF_FM_VCO_R = DEfuse_Data[46].or(DEfuse_Data[47]).and(0xF000)
                .rightShift(12);

        // BT_POWER_CAL_R[site_index]=DEfuse_Data[46].or( DEfuse_Data[47]).and(0xFF0)>>4);//discard
        // @B150
        MultiSiteLong R_CAL_R = DEfuse_Data[46].or(DEfuse_Data[47]).and(0xF);
        MultiSiteLong LDOREF_ADC_R = DEfuse_Data[52].or(DEfuse_Data[53]).and(0x1E0).rightShift(5); // V-CAL,BT,ADC
        MultiSiteLong BT_POWER_CAL1_R = DEfuse_Data[46].or(DEfuse_Data[47]).and(0x78000000)
                .rightShift(27);
        MultiSiteLong BT_POWER_CAL2_R = DEfuse_Data[46].or(DEfuse_Data[47]).and(0x780000)
                .rightShift(19);
        MultiSiteLong BT_POWER_CAL_R = BT_POWER_CAL2_R.multiply(16).add(BT_POWER_CAL1_R); // BT,DAC+TX

        String Keys31[] = { "THM1_Offset_Code_R", "THM1_Ratio_Code_R", "THM1_Ratio_Sign_R",
                "THM_CP_Offset_Code_R", "THM_CP_Ratio_Code_R", "THM_CP_Ratio_Sign_R", "FLG_R",
                "USB20_TUNEOTG_R", "USB20_TFREGRES_R", "USB20_TFHSRES_R", "LVDSRF_MIPI_R",
                "LVDSRF_RX_VMode_R", "K_APCOUT_R", "B_APCOUT_R", "Bounding_30_R", "Bounding_28_R",
                "Bounding_0_R", "L_LDOREF_BT_VCO_R", "LDOREF_ABB_R", "LDOREF_FM_VCO_R", "R_CAL_R",
                "LDOREF_ADC_R", "BT_POWER_CAL1_R", "BT_POWER_CAL2_R", "BT_POWER_CAL_R" };

        MultiSiteLong Keys3[] = { THM1_Offset_Code_R, THM1_Ratio_Code_R, THM1_Ratio_Sign_R,
                THM_CP_Offset_Code_R, THM_CP_Ratio_Code_R, THM_CP_Ratio_Sign_R, FLG_R,
                USB20_TUNEOTG_R, USB20_TFREGRES_R, USB20_TFHSRES_R, LVDSRF_MIPI_R,
                LVDSRF_RX_VMode_R, K_APCOUT_R, B_APCOUT_R, Bounding_30_R, Bounding_28_R,
                Bounding_0_R, L_LDOREF_BT_VCO_R, LDOREF_ABB_R, LDOREF_FM_VCO_R, R_CAL_R,
                LDOREF_ADC_R, BT_POWER_CAL1_R, BT_POWER_CAL2_R, BT_POWER_CAL_R, };

        Map<String, MultiSiteLong> result2 = new HashMap<String, MultiSiteLong>();
        for (int i = 0; i < Keys3.length; i++) {
            result2.put(Keys31[i], Keys3[i]);
            print(Keys31[i] + "\t: " + Keys3[i]);
        }

        for (TST_NAME_ParaGroup tmp : tsColl1.values()) {
            tmp.ptd.evaluate(result2.get(tmp.getId()));
        }
    }
}
