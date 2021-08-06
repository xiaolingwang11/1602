package GC1602_tml.EFUSE;

import pa.NemoLPA;
import pa.PAWVar;
import pa.TransactSeq_Efuse;
import tmlCommon.GVar;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupProtocolInterface;
import xoc.dsa.SetupVariableType;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.MultiSiteBoolean;
import xoc.dta.datatypes.MultiSiteLong;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDigInOutResults;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;
import xoc.dta.testdescriptor.IParametricTestDescriptor;

public class EfuseWriteDebug extends TMLBase {

    public IParametricTestDescriptor ptd_THM1_Offset_Code_W;
    public IParametricTestDescriptor ptd_THM1_Ratio_Code_W;
    public IParametricTestDescriptor ptd_THM1_Ratio_Sign_W;
    public IParametricTestDescriptor ptd_THM_CP_Offset_Code_W;
    public IParametricTestDescriptor ptd_THM_CP_Ratio_Code_W;
    public IParametricTestDescriptor ptd_THM_CP_Ratio_Sign_W;

    public IParametricTestDescriptor ptd_FLG_W;
    public IParametricTestDescriptor ptd_USB20_TUNEOTG_W;
    public IParametricTestDescriptor ptd_USB20_TFREGRES_W;
    public IParametricTestDescriptor ptd_USB20_TFHSRES_W;
    public IParametricTestDescriptor ptd_LVDSRF_MIPI_W;
    public IParametricTestDescriptor ptd_LVDSRF_RX_VMode_W;

    public IParametricTestDescriptor ptd_K_APCOUT_W;
    public IParametricTestDescriptor ptd_B_APCOUT_W;

    public IParametricTestDescriptor ptd_Bounding_30_W;
    public IParametricTestDescriptor ptd_Bounding_28_W;
    public IParametricTestDescriptor ptd_Bounding_0_W;

    public IParametricTestDescriptor ptd_chip_version_W;
    public IParametricTestDescriptor ptd_PRG_Type_W;
    public IParametricTestDescriptor ptd_PRG_Version_W;

    public IParametricTestDescriptor ptd_L_LDOREF_BT_VCO_W;

    public IParametricTestDescriptor ptd_LDOREF_ABB_W;
    public IParametricTestDescriptor ptd_LDOREF_FM_VCO_W;

    public IParametricTestDescriptor ptd_R_CAL_W;
    public IParametricTestDescriptor ptd_LDOREF_ADC_W;
    public IParametricTestDescriptor ptd_BT_POWER_CAL1_W;
    public IParametricTestDescriptor ptd_BT_POWER_CAL2_W;
    public IParametricTestDescriptor ptd_BT_POWER_CAL_W;

    public IFunctionalTestDescriptor ftd;

    public IMeasurement measurement1;


    public String testSignals = "RFCTL_2_+RFCTL_3_+RFCTL_4_+RFCTL_9_+RFCTL_10_+NF_DATA_0+NF_DATA_2";
    private static final int Efs_W_Idx_Start = 46;
    private static final int Efs_W_Idx_End = 57;
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
            // println("dsVar:" + paWriteConfig[i].dsVarName);
            efuse.dyWrite((i + (i << 8)), paWriteConfig[i].transactVarName);
            devSetup1.addVariable(SetupVariableType.UnsignedLong, paWriteConfig[i].dsVarName, 0);
        }

        String args[] = PAWVar.getArguments(paWriteConfig,Efs_W_Idx_Start,Efs_W_Idx_End);
        devSetup1.sequentialBegin();
        devSetup1.transactionSequenceCall(transactSeqName,efuse.trseqDef, args);
        devSetup1.sequentialEnd();
        measurement1.setSetups(devSetup1);
    }

    @Override
    public void update() {
//        measurement1.digInOut(testSignals).result().capture().setEnabled(false);
//        measurement1.digInOut(testSignals).result().cyclePassFail().setEnabled(true);
    }

    @Override
    public void execute() {

        GVar.EFUSE_CAL(context.getActiveSites());

        for (int i = Efs_W_Idx_Start; i <= Efs_W_Idx_End; i++) {
            measurement1.spec().setVariable(paWriteConfig[i].dsVarName,
                    GVar.DdieEFUSE_BLOCK.getElement(i));
        }

        // selective execution
        int[] activeSite = new int[1];
        for(int site : context.getActiveSites()) {
            if(!stop.get(site)) {
                activeSite[0] = site;
                context.execution().setActiveSites(activeSite);
                measurement1.execute();
                context.execution().restoreActiveSites();
            }
        }


        IDigInOutResults passFailResults = measurement1.digInOut().preserveResults(ftd);

        passFailResults.hasPassed();

        MultiSiteLong THM1_Offset_Code_W = new MultiSiteLong();
        MultiSiteLong THM1_Ratio_Code_W = new MultiSiteLong();
        MultiSiteLong THM1_Ratio_Sign_W = new MultiSiteLong();
        MultiSiteLong THM_CP_Offset_Code_W = new MultiSiteLong();
        MultiSiteLong THM_CP_Ratio_Code_W = new MultiSiteLong();
        MultiSiteLong THM_CP_Ratio_Sign_W = new MultiSiteLong();

        MultiSiteLong FLG_W = new MultiSiteLong();
        MultiSiteLong USB20_TUNEOTG_W = new MultiSiteLong();
        MultiSiteLong USB20_TFREGRES_W = new MultiSiteLong();
        MultiSiteLong USB20_TFHSRES_W = new MultiSiteLong();
        MultiSiteLong LVDSRF_MIPI_W = new MultiSiteLong();
        MultiSiteLong LVDSRF_RX_VMode_W = new MultiSiteLong();

        MultiSiteLong K_APCOUT_W = new MultiSiteLong();
        MultiSiteLong B_APCOUT_W = new MultiSiteLong();

        MultiSiteLong Bounding_30_W = new MultiSiteLong();
        MultiSiteLong Bounding_28_W = new MultiSiteLong();
        MultiSiteLong Bounding_0_W = new MultiSiteLong();

        MultiSiteLong chip_version_W = new MultiSiteLong();
        MultiSiteLong PRG_Type_W = new MultiSiteLong();
        MultiSiteLong PRG_Version_W = new MultiSiteLong();

        MultiSiteLong L_LDOREF_BT_VCO_W = new MultiSiteLong();

        MultiSiteLong LDOREF_ABB_W = new MultiSiteLong();
        MultiSiteLong LDOREF_FM_VCO_W = new MultiSiteLong();

        MultiSiteLong R_CAL_W = new MultiSiteLong();
        MultiSiteLong LDOREF_ADC_W = new MultiSiteLong();
        MultiSiteLong BT_POWER_CAL1_W = new MultiSiteLong();
        MultiSiteLong BT_POWER_CAL2_W = new MultiSiteLong();
        MultiSiteLong BT_POWER_CAL_W = new MultiSiteLong();

        for (int site : context.getActiveSites()) {
            THM1_Offset_Code_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    50) | GVar.DdieEFUSE_BLOCK.getElement(site, 51)) & 0x7F000000) >> 24)); // block50,51
                                                                                            // bit[30:16]
            THM1_Ratio_Code_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    50) | GVar.DdieEFUSE_BLOCK.getElement(site, 51)) & 0xFE0000) >> 17));

            THM1_Ratio_Sign_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    50) | GVar.DdieEFUSE_BLOCK.getElement(site, 51)) & 0x10000) >> 16));

            THM_CP_Offset_Code_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    50) | GVar.DdieEFUSE_BLOCK.getElement(site, 51)) & 0x7F00) >> 9)); // block50,51
                                                                                       // bit[15:1]
            THM_CP_Ratio_Code_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    50) | GVar.DdieEFUSE_BLOCK.getElement(site, 51)) & 0xFE) >> 2)); // block50,51

            THM_CP_Ratio_Sign_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    50) | GVar.DdieEFUSE_BLOCK.getElement(site, 51)) & 0x1) >> 1)); // block50,51

            FLG_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site, 52) | GVar.DdieEFUSE_BLOCK
                    .getElement(site, 53)) & 0x40000000) >> 30));

            USB20_TUNEOTG_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    52) | GVar.DdieEFUSE_BLOCK.getElement(site, 53)) & 0x38000000) >> 27));

            USB20_TFREGRES_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    52) | GVar.DdieEFUSE_BLOCK.getElement(site, 53)) & 0x7E00000) >> 21));

            USB20_TFHSRES_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    52) | GVar.DdieEFUSE_BLOCK.getElement(site, 53)) & 0x1F0000) >> 16));

            LVDSRF_MIPI_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    52) | GVar.DdieEFUSE_BLOCK.getElement(site, 53)) & 0xF000) >> 12));

            LVDSRF_RX_VMode_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    52) | GVar.DdieEFUSE_BLOCK.getElement(site, 53)) & 0xE00) >> 9));

            K_APCOUT_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site, 48) | GVar.DdieEFUSE_BLOCK
                    .getElement(site, 49)) & 0x7FF80000) >> 19));

            B_APCOUT_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site, 48) | GVar.DdieEFUSE_BLOCK
                    .getElement(site, 49)) & 0xFFC0) >> 6));

            Bounding_30_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    54) | GVar.DdieEFUSE_BLOCK.getElement(site, 55)) & 0xC0000000) >> 30));

            Bounding_28_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    54) | GVar.DdieEFUSE_BLOCK.getElement(site, 55)) & 0x30000000) >> 28));

            Bounding_0_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    54) | GVar.DdieEFUSE_BLOCK.getElement(site, 55)) & 0x3FF)));

            chip_version_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    56) | GVar.DdieEFUSE_BLOCK.getElement(site, 57)) & 0xF000) >> 12));

            PRG_Type_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site, 56) | GVar.DdieEFUSE_BLOCK
                    .getElement(site, 57)) & 0xC00) >> 10));

            PRG_Version_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    56) | GVar.DdieEFUSE_BLOCK.getElement(site, 57)) & 0x3FF)));

            L_LDOREF_BT_VCO_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    46) | GVar.DdieEFUSE_BLOCK.getElement(site, 47)) & 0x7800000) >> 23));

            LDOREF_ABB_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    46) | GVar.DdieEFUSE_BLOCK.getElement(site, 47)) & 0x70000) >> 16)); // 4.23
                                                                                         // update
            LDOREF_FM_VCO_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    46) | GVar.DdieEFUSE_BLOCK.getElement(site, 47)) & 0xF000) >> 12));

            R_CAL_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site, 46) | GVar.DdieEFUSE_BLOCK
                    .getElement(site, 47)) & 0xF)));

            LDOREF_ADC_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    52) | GVar.DdieEFUSE_BLOCK.getElement(site, 53)) & 0x1E0) >> 5));

            BT_POWER_CAL1_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    46) | GVar.DdieEFUSE_BLOCK.getElement(site, 47)) & 0x78000000) >> 27));

            BT_POWER_CAL2_W.set(site, (((GVar.DdieEFUSE_BLOCK.getElement(site,
                    46) | GVar.DdieEFUSE_BLOCK.getElement(site, 47)) & 0x780000) >> 19));

            BT_POWER_CAL_W.set(site, BT_POWER_CAL2_W.get(site) * 16 + BT_POWER_CAL1_W.get(site));
        }

        ptd_THM1_Offset_Code_W.evaluate(THM1_Offset_Code_W);
        ptd_THM1_Ratio_Code_W.evaluate(THM1_Ratio_Code_W);
        ptd_THM1_Ratio_Sign_W.evaluate(THM1_Ratio_Sign_W);
        ptd_THM_CP_Offset_Code_W.evaluate(THM_CP_Offset_Code_W);
        ptd_THM_CP_Ratio_Code_W.evaluate(THM_CP_Ratio_Code_W);
        ptd_THM_CP_Ratio_Sign_W.evaluate(THM_CP_Ratio_Sign_W);

        ptd_FLG_W.evaluate(FLG_W);
        ptd_USB20_TUNEOTG_W.evaluate(USB20_TUNEOTG_W);
        ptd_USB20_TFREGRES_W.evaluate(USB20_TFREGRES_W);
        ptd_USB20_TFHSRES_W.evaluate(USB20_TFHSRES_W);
        ptd_LVDSRF_MIPI_W.evaluate(LVDSRF_MIPI_W);
        ptd_LVDSRF_RX_VMode_W.evaluate(LVDSRF_RX_VMode_W);

        ptd_K_APCOUT_W.evaluate(K_APCOUT_W);
        ptd_B_APCOUT_W.evaluate(B_APCOUT_W);

        ptd_Bounding_30_W.evaluate(Bounding_30_W);
        ptd_Bounding_28_W.evaluate(Bounding_28_W);
        ptd_Bounding_0_W.evaluate(Bounding_0_W);

        ptd_chip_version_W.evaluate(chip_version_W);
        ptd_PRG_Type_W.evaluate(PRG_Type_W);
        ptd_PRG_Version_W.evaluate(PRG_Version_W);

        ptd_L_LDOREF_BT_VCO_W.evaluate(L_LDOREF_BT_VCO_W);

        ptd_LDOREF_ABB_W.evaluate(LDOREF_ABB_W);
        ptd_LDOREF_FM_VCO_W.evaluate(LDOREF_FM_VCO_W);

        ptd_R_CAL_W.evaluate(R_CAL_W);
        ptd_LDOREF_ADC_W.evaluate(LDOREF_ADC_W);
        ptd_BT_POWER_CAL1_W.evaluate(BT_POWER_CAL1_W);
        ptd_BT_POWER_CAL2_W.evaluate(BT_POWER_CAL2_W);
        ptd_BT_POWER_CAL_W.evaluate(BT_POWER_CAL_W);

    }

}
