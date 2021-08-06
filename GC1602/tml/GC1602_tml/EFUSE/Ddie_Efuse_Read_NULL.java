package GC1602_tml.EFUSE;

import pa.NemoLPA;
import pa.TransactSeq_Efuse;
import tmlCommon.GVar;
import tmlCommon.Msg;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupProtocolInterface;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.MultiSiteLong;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IProtocolInterfaceResults;
import xoc.dta.resultaccess.ITransactionSequenceResults;

public class Ddie_Efuse_Read_NULL extends TMLBase {

    @In
    public boolean Read_all = false;

    private static final String protInterfaceName = "EFUSE";
    private static final String transactSeqName = "EFUSE_transaction1";
    private int readStartIdx = -1;
    private int readStoptIdx = -1;

    public IMeasurement measurement;

    TransactSeq_Efuse efuse = null;

    @Override
    public void setup() {
        tsName = getTestSuiteName();
        IDeviceSetup devSetup1 = newDevSetup(importSpec);

        ISetupProtocolInterface EFUSE_wr = NemoLPA.addInterface_EFUSE(devSetup1, protInterfaceName);
        efuse = new TransactSeq_Efuse(EFUSE_wr, transactSeqName);

        readStartIdx = 46;
        readStoptIdx = 59;

        if (readStoptIdx >= GVar.EfuseReadCnt || readStartIdx > readStoptIdx || readStartIdx < 0) {
            Msg.error("Error:Efusee buff setting error");
        }

        for (int i = readStartIdx; i <= readStoptIdx; i++) {
            efuse.read((i + (i << 8)), "Block" + i);
        }

        devSetup1.sequentialBegin();
        devSetup1.transactionSequenceCall(efuse.trseqName, efuse.trseqDef);
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

        // without this setting the next efuse write test suite will not succeed
        measurement.digInOut().setConnect(true).setDisconnect(false);

        measurement.execute();

        IProtocolInterfaceResults paResults1 = measurement.protocolInterface(efuse.interfaceName)
                .preserveResults();

        // [0] means result of the first execution of the specified transaction sequence
        ITransactionSequenceResults tsResults1 = paResults1.transactSeq(efuse.trseqName)[0];

        MultiSiteLong DEfuse_Data[] = new MultiSiteLong[GVar.EfuseReadCnt];
        Msg.debug("-------------------------");
        for (int i = readStartIdx; i <= readStoptIdx; i++) {
            DEfuse_Data[i] = tsResults1.getValueAsLong("Block" + i);
            Msg.debug("Block" + i + DEfuse_Data[i]);
        }
        Msg.debug("-------------------------");
        Data_Processing_null(DEfuse_Data);

    }

    // **************************************************************
    // **************************************************************
    private void Data_Processing_null(MultiSiteLong[] DEfuse_Data) {

        MultiSiteLong Fresh_die = new MultiSiteLong(-9999999);

        for (int site : context.getActiveSites()) {

            int fresh_sum = 0;

            for (int i = readStartIdx; i <= readStoptIdx; i++) {

                // Bit[15..1]:THM_CP; Bit[30..16]:THM_FT
                if ((i == 50) | (i == 51)) {

                    if (0 == (DEfuse_Data[i].get(site) & 0xFFFF0000)) {
                        fresh_sum += 1;
                    } else {
                        GVar.THM1_Offset_Code.set(site, 0);
                        GVar.THM1_Ratio_Code.set(site, 0);
                        GVar.THM1_Ratio_Sign.set(site, 0);
                    }

                } else if (0 == DEfuse_Data[i].get(site)) {

                    fresh_sum += 1;

                } else if (DEfuse_Data[i].get(site) != 0) {

                    // Bit[8..2]:WCN RF; Bit[15..9]:LVDS tuning;
                    // Bit[30..16]:USB tuning
                    if ((i == 52) | (i == 53)) {
                        if (0 == (DEfuse_Data[i].get(site) & 0x1E0)) {
                            GVar.FLG.set(site, 0);
                            GVar.USB20_TUNEOTG.set(site, 0);
                            GVar.USB20_TFREGRES.set(site, 0);
                            GVar.USB20_TFHSRES.set(site, 0);
                            GVar.LVDSRF_TX_IMP.set(site, 0);
                            GVar.LVDSRF_RX_IMP.set(site, 0);
                        } else {
                            GVar.FLG.set(site, 0);
                            GVar.USB20_TUNEOTG.set(site, 0);
                            GVar.USB20_TFREGRES.set(site, 0);
                            GVar.USB20_TFHSRES.set(site, 0);
                            GVar.LVDSRF_TX_IMP.set(site, 0);
                            GVar.LVDSRF_RX_IMP.set(site, 0);
                            GVar.LDOREF_ADC.set(site, 0);
                        }
                    }

                    if ((i == 56) | (i == 57)) // Bit[15..0]:ATE_version;
                    {
                        GVar.chip_version.set(site, 0);
                        GVar.PRG_Type.set(site, 0);
                        GVar.PRG_Version.set(site, 0);
                    }

                    if ((i == 54) | (i == 55)) // Bonding;
                    {
                        GVar.Bounding_30.set(site, 0);
                        GVar.Bounding_28.set(site, 0);
                        GVar.Bounding_0.set(site, 0);
                    }

                    if ((i == 48) | (i == 49)) // APC;Bit[15..6]:B; Bit[30..19]:K;
                    {
                        GVar.K_APCOUT.set(site, 0);
                        GVar.B_APCOUT.set(site, 0);
                    }
                    if ((i == 46) | (i == 47)) // Bit[22..19]:CAL2;Bit[30..27]:CAL1;
                    {
                        if (0 == (DEfuse_Data[i].get(site) & 0x78780000)) {
                            GVar.L_LDOREF_BT_VCO.set(site, 0);
                            GVar.LDOREF_ABB.set(site, 0);
                            GVar.LDOREF_FM_VCO.set(site, 0);
                            GVar.R_CAL.set(site, 0);
                        } else {
                            GVar.L_LDOREF_BT_VCO.set(site, 0);
                            GVar.LDOREF_ABB.set(site, 0);
                            GVar.LDOREF_FM_VCO.set(site, 0);
                            GVar.R_CAL.set(site, 0);
                            GVar.BT_POWER_CAL1.set(site, 0);
                            GVar.BT_POWER_CAL2.set(site, 0);
                        }

                    }
                }
            }

            if (fresh_sum == 10) {
                Fresh_die.set(site, 1);
            } else {
                Fresh_die.set(site, 0);
            }
        }

        Msg.info(tsName + "__isFresh:" + Fresh_die);
        // ptd_Fresh_die.evaluate(Fresh_die);
    }

}
