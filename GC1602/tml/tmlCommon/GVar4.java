package tmlCommon;

import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.MultiSiteLong;
import xoc.dta.datatypes.MultiSiteLongArray;

public class GVar4 {
    public static final Integer EfuseReadCnt = 96;

    // &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    // &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    // &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    // &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    // &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    // &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

    // **********************************
    public static MultiSiteLongArray DdieEFUSE_BLOCK;// [96]; //DdieEFUSE_BLOCK[24][8]

    // *************** version *************
    public static MultiSiteLong chip_version = new MultiSiteLong(1); // 0:AA,1:AB,2:AC,3:AD...
    public static MultiSiteLong PRG_Type = new MultiSiteLong(1); // 0:A,1:B,2:R,3:E
    public static MultiSiteLong PRG_Version = new MultiSiteLong(201);

    public static MultiSiteLong PRG_Version_E = new MultiSiteLong(0);
    public static MultiSiteLong PRG_Type_E = new MultiSiteLong(0);

    public static MultiSiteLong Bounding_30 = new MultiSiteLong(3);
    public static MultiSiteLong Bounding_28 = new MultiSiteLong(); // For 191016_04 2'b11, other
                                                                   // 2'b00
    public static MultiSiteLong Bounding_0 = new MultiSiteLong(257); // 0x101

    // *************** UID *************
    public static MultiSiteLong Y_loc_R, X_loc_R, Wafer_ID_R, LotID0_R, LotID1_R, LotID2_R,
            LotID3_R, LotID4_R, LotID5_R;

    // *************** THM *************
    public static MultiSiteDouble T_ext;
    public static MultiSiteLong THM0_Ratio_Sign, THM0_Ratio_Code, THM0_Offset_Code, THM1_Ratio_Sign,
            THM1_Ratio_Code, THM1_Offset_Code; // SharkLE

    // *************** MIPI TRIM *************
    public static MultiSiteLong CSI2P2_TRIMBG;
    public static MultiSiteLong CSI2P2_RXRCTL; // CSI2P2_BG,CSI2P2_ODT
    public static MultiSiteLong DSI_TRIMBG;
    public static MultiSiteLong DSI_TXRCTL; // DSI_BG,DSI_ODT
    public static MultiSiteLong CSI_4L_RXRCTL; // CSI_4L_ODT
    public static MultiSiteLong CSI_2L_RXRCTL; // CSI_2L_ODT

    public static MultiSiteLong DSI_TRIMBG_bit0;
    public static MultiSiteLong DSI_TRIMBG_bit1;
    public static MultiSiteLong DSI_TRIMBG_bit2;
    public static MultiSiteLong DSI_TRIMBG_bit3;

    public static MultiSiteLong DSI_TXRCTL_bit0;
    public static MultiSiteLong DSI_TXRCTL_bit1;
    public static MultiSiteLong DSI_TXRCTL_bit2;
    public static MultiSiteLong DSI_TXRCTL_bit3;

    public static MultiSiteLong CSI2P2_TRIMBG_bit0;
    public static MultiSiteLong CSI2P2_TRIMBG_bit1;
    public static MultiSiteLong CSI2P2_TRIMBG_bit2;
    public static MultiSiteLong CSI2P2_TRIMBG_bit3;

    public static MultiSiteLong CSI2P2_RXRCTL_bit0;
    public static MultiSiteLong CSI2P2_RXRCTL_bit1;
    public static MultiSiteLong CSI2P2_RXRCTL_bit2;
    public static MultiSiteLong CSI2P2_RXRCTL_bit3;

    public static MultiSiteLong CSI_4L_RXRCTL_bit0;
    public static MultiSiteLong CSI_4L_RXRCTL_bit1;
    public static MultiSiteLong CSI_4L_RXRCTL_bit2;
    public static MultiSiteLong CSI_4L_RXRCTL_bit3;

    public static MultiSiteLong CSI_2L_RXRCTL_bit0;
    public static MultiSiteLong CSI_2L_RXRCTL_bit1;
    public static MultiSiteLong CSI_2L_RXRCTL_bit2;
    public static MultiSiteLong CSI_2L_RXRCTL_bit3;
    // *************** LVDSRF TRIM *************
    public static MultiSiteLong LVDSRF_RX_IMP;
    public static MultiSiteLong LVDSRF_RX_IMode;
    public static MultiSiteLong LVDSRF_TX_IMP;

    public static MultiSiteLong LVDSRF_RX_VMode_bit0;
    public static MultiSiteLong LVDSRF_RX_VMode_bit1;
    public static MultiSiteLong LVDSRF_RX_VMode_bit2;
    public static MultiSiteLong LVDSRF_RX_VMode_bit3;

    public static MultiSiteLong LVDSRF_RX_CMode_bit0;
    public static MultiSiteLong LVDSRF_RX_CMode_bit1;
    public static MultiSiteLong LVDSRF_RX_CMode_bit2;

    public static MultiSiteLong LVDSRF_MIPI_bit0;
    public static MultiSiteLong LVDSRF_MIPI_bit1;
    public static MultiSiteLong LVDSRF_MIPI_bit2;
    public static MultiSiteLong LVDSRF_MIPI_bit3;

    // *************** LVDSRF PHASE BIST 24 bit *************
    // ARRAY_I LVDSRF_phase_bist_NG0,LVDSRF_phase_bist_NG1;
    // ARRAY_I LVDSRF_phase_bist_NG0_Master,LVDSRF_phase_bist_NG1_Master;
    // ARRAY_I LVDSRF_phase_bist_NG0_Slave,LVDSRF_phase_bist_NG1_Slave;

    // *************** USB20 TRIM *************
    public static MultiSiteLong USB20_TUNEOTG, USB20_TFHSRES, USB20_TFREGRES;

    public static MultiSiteLong iUSB_BG_bit0;
    public static MultiSiteLong iUSB_BG_bit1;
    public static MultiSiteLong iUSB_BG_bit2;

    public static MultiSiteLong iUSB_ODT_bit0;
    public static MultiSiteLong iUSB_ODT_bit1;
    public static MultiSiteLong iUSB_ODT_bit2;
    public static MultiSiteLong iUSB_ODT_bit3;
    public static MultiSiteLong iUSB_ODT_bit4;

    public static MultiSiteLong iUSB_6K_bit0;
    public static MultiSiteLong iUSB_6K_bit1;
    public static MultiSiteLong iUSB_6K_bit2;
    public static MultiSiteLong iUSB_6K_bit3;
    public static MultiSiteLong iUSB_6K_bit4;
    public static MultiSiteLong iUSB_6K_bit5;

    // *************** BINNING TRIM *************
    public static MultiSiteLong BINNING_RESLT;
    public static MultiSiteLong CPU_OSC, GPU_OSC, PUB_OSC;
    public static MultiSiteLong VDDCORE_LEAK, VDDARM_LEAK, GPU_LEAK;

    public static MultiSiteLong MP_ES;

    public static MultiSiteLong Power_switch_leak;
    public static MultiSiteLong FT_tem;

    // *************** WCN_RF TRIM *************
    public static MultiSiteLong K_APCOUT_bit11_5;
    public static MultiSiteLong K_APCOUT_bit4_0;
    public static MultiSiteLong B_APCOUT_bit9;
    public static MultiSiteLong B_APCOUT_bit8_0;
    public static MultiSiteLong LDOREF_GNSS_ADC;
    public static MultiSiteLong LDOREF_GNSS_VCO; // LDOREF_GNSS_ADC : TSEN_LDO_TRIM
    public static MultiSiteLong LDOREF_FM_VCO;
    public static MultiSiteLong LDOREF_ISM_ADDA;
    public static MultiSiteLong H_LDOREF_ISM_VCO;
    public static MultiSiteLong L_LDOREF_ISM_VCO;
    public static MultiSiteLong LDOREF_ADC;
    public static MultiSiteLong VCM_ABB;
    public static MultiSiteLong THM_CPU;
    public static MultiSiteLong THM_CP;
    public static MultiSiteLong H_LDOREF_ISM_VCO_code;
    public static MultiSiteLong L_LDOREF_ISM_VCO_code; // WCN Code
    public static MultiSiteLong H_LDOREF_BT_VCO;
    public static MultiSiteLong L_LDOREF_BT_VCO;
    public static MultiSiteLong K_APCOUT;
    public static MultiSiteLong B_APCOUT;
    public static MultiSiteLong LDOREF_ABB;
    public static MultiSiteLong BT_POWER_CAL;
    public static MultiSiteLong BT_POWER_CAL1;
    public static MultiSiteLong BT_POWER_CAL2;
    public static MultiSiteLong R_CAL;
    public static MultiSiteLong R_Cal_code; // WCN Code
    public static MultiSiteLong SLOPE;

    public static MultiSiteLong Intercept_Power_Det;

    public static MultiSiteLong TSEN_LDO_TRIM;

    // *************** Other *************
    public static MultiSiteLong cp_mass;
    public static MultiSiteLong efuse_mem_repair;

    public static MultiSiteLong Disable_CSI_PHY; // block92,93 bit15
    public static MultiSiteLong CA53_2Core; // block92,93 bit16
    public static MultiSiteLong Enable_Secure_Boot; // block92,93 bit0
    public static MultiSiteLong Forbid_FDMA_Access; // block92,93 bit10
    public static MultiSiteLong Secure_Debug; // block12,13 bit16

    public static MultiSiteLong FLG;
    // public static MultiSiteLongArray Block_null[59][8];

    // ***********************************
    // map<String, Double> B_Overflow;// for 1.0v overflow flag

    // *************** OLD *************
    public static MultiSiteLong USB_BGCode, USB_TermCode, USB_12KCode;
    public static MultiSiteLong THM_Ratio0_Sign, THM_Ratio0_Code, THM_Offset0_Code,
            THM_Offset0R_Code, THM_Offset1_Code, THM_Offset2_Code, THM_Offset3_Code,
            THM_Offset4_Code;

    public static MultiSiteLong BB_LDO, USB_TFHSRES, USB_TF12KRES, USB_TUNEOTG;
    public static MultiSiteLong SPEED, SIDD_PRE, COMP;

    public static MultiSiteLong BUCKET, LIT_BINNING, BIG_BINNING, FCM_BINNING, DVFS_T, VER_TYPE,
            LIT_LEAK, BIG_LEAK, CORE_LEAK;
    public static MultiSiteDouble ROSC_8_cnt_15, dVmin;
    public static MultiSiteLong BIG_HFM_BINNING, BIG_LFM_BINNING, LIT_LFM_BINNING, LIT_HFM_BINNING,
            FCM_LFM_BINNING, FCM_HFM_BINNING;

    public static MultiSiteLong DSI_TX_CTL, DSI_TRIM_BG, CSI_RX_CTL;
    public static MultiSiteLong DSI_M_BanGapCode, DSI_M_ODTCode, CSI0_ODTCode; // mipi
    public static MultiSiteLong DSI_S_BanGapCode, DSI_S_ODTCode, CSI1_ODTCode; // mipi
    public static MultiSiteLong CSI2_ODTCode; // mipi

    public static MultiSiteLong iDSI_M_BGTrim_PinA;
    public static MultiSiteLong iDSI_M_BGTrim_PinB;
    public static MultiSiteLong iDSI_M_BGTrim_PinC;
    public static MultiSiteLong iDSI_M_BGTrim_PinD;

    public static MultiSiteLong iDSI_M_ODTTrim_PinA;
    public static MultiSiteLong iDSI_M_ODTTrim_PinB;
    public static MultiSiteLong iDSI_M_ODTTrim_PinC;
    public static MultiSiteLong iDSI_M_ODTTrim_PinD;

    public static MultiSiteLong iCSI0_ODTTrim_PinA;
    public static MultiSiteLong iCSI0_ODTTrim_PinB;
    public static MultiSiteLong iCSI0_ODTTrim_PinC;
    public static MultiSiteLong iCSI0_ODTTrim_PinD;

    public static MultiSiteLong iDSI_S_BGTrim_PinA;
    public static MultiSiteLong iDSI_S_BGTrim_PinB;
    public static MultiSiteLong iDSI_S_BGTrim_PinC;
    public static MultiSiteLong iDSI_S_BGTrim_PinD;

    public static MultiSiteLong iDSI_S_ODTTrim_PinA;
    public static MultiSiteLong iDSI_S_ODTTrim_PinB;
    public static MultiSiteLong iDSI_S_ODTTrim_PinC;
    public static MultiSiteLong iDSI_S_ODTTrim_PinD;

    public static MultiSiteLong iCSI1_ODTTrim_PinA;
    public static MultiSiteLong iCSI1_ODTTrim_PinB;
    public static MultiSiteLong iCSI1_ODTTrim_PinC;
    public static MultiSiteLong iCSI1_ODTTrim_PinD;

    public static MultiSiteLong M_LVDSRF_RX0_OUTDATA, S_LVDSRF_RX0_OUTDATA;
    // *************** OLD_END *************

    public static void initial_EFUSE(long Device_code_s) {

        // Ddie Efuse Value ini ++++++++++++++++++++++
        // block 92,93
        chip_version = new MultiSiteLong(1); // 0:AA,1:AB,2:AC,3:AD...
        PRG_Type = new MultiSiteLong(); // 0:A,1:B,2:R,3:E
        PRG_Version = new MultiSiteLong(201);

        Bounding_30 = new MultiSiteLong(3);
        Bounding_0 = new MultiSiteLong(257); // 0x101

        if (4 == Device_code_s) {
            Bounding_28 = new MultiSiteLong(3);// For 191016_04 2'b11, other 2'b00
        } else {
            Bounding_28 = new MultiSiteLong(0);
        }

        FLG = new MultiSiteLong(1); // USB calibration done flag, because IP value default=0x80,
        // so Softwave can't diff whether the content=0 if it is no
        // calibration
        // or calibration =0.
        USB20_TUNEOTG = new MultiSiteLong(0); // Trim BG
        USB20_TFHSRES = new MultiSiteLong(0); // Trim ODT
        USB20_TFREGRES = new MultiSiteLong(0); // Trim inter 6K

        THM1_Offset_Code = new MultiSiteLong(0);
        THM1_Ratio_Code = new MultiSiteLong(0);
        THM1_Ratio_Sign = new MultiSiteLong(0);

        SLOPE = new MultiSiteLong(0);
        BT_POWER_CAL = new MultiSiteLong(0);
        R_CAL = new MultiSiteLong(0);

        LVDSRF_RX_IMP = new MultiSiteLong(0); // named LVDSRF_RX in efuse map ??
        LVDSRF_TX_IMP = new MultiSiteLong(0); // LVDSRF_TX

        LDOREF_ADC = new MultiSiteLong(0);
        L_LDOREF_BT_VCO = new MultiSiteLong(0);
        K_APCOUT = new MultiSiteLong(0);
        B_APCOUT = new MultiSiteLong(0);
        LDOREF_ABB = new MultiSiteLong(0);
        LDOREF_FM_VCO = new MultiSiteLong(0);

        DdieEFUSE_BLOCK = new MultiSiteLongArray(new long[EfuseReadCnt]);
        for (int i = 0; i < EfuseReadCnt; i++) {
            DdieEFUSE_BLOCK.setElement(i,  new MultiSiteLong(0));
        }

    }

    public static void EFUSE_CAL(int sites[]) {
        // MultiSiteLongArray
        for (int site : sites) {

            DdieEFUSE_BLOCK.setElement(site, 57, ((chip_version.get(site) & 0xF) << 12)
                    + ((PRG_Type.get(site) & 0x3) << 10) + (PRG_Version.get(site) & 0x3FF));

            DdieEFUSE_BLOCK.setElement(site, 56, ((chip_version.get(site) & 0xF) << 12)
                    + ((PRG_Type.get(site) & 0x3) << 10) + (PRG_Version.get(site) & 0x3FF));

            DdieEFUSE_BLOCK.setElement(site, 55, ((Bounding_30.get(site) & 0x3) << 30)
                    + ((Bounding_28.get(site) & 0x3) << 28) + (Bounding_0.get(site) & 0x3FF));

            DdieEFUSE_BLOCK.setElement(site, 54, ((Bounding_30.get(site) & 0x3) << 30)
                    + ((Bounding_28.get(site) & 0x3) << 28) + (Bounding_0.get(site) & 0x3FF));

            DdieEFUSE_BLOCK.setElement(site, 53,
                    ((FLG.get(site) & 0x1) << 30) + ((USB20_TUNEOTG.get(site) & 0x7) << 27)
                            + ((USB20_TFREGRES.get(site) & 0x3F) << 21)
                            + ((USB20_TFHSRES.get(site) & 0x1F) << 16)
                            + ((LVDSRF_TX_IMP.get(site) & 0xF) << 12)
                            + ((LVDSRF_RX_IMP.get(site) & 0x7) << 9)
                            + ((LDOREF_ADC.get(site) & 0xF) << 5));
            DdieEFUSE_BLOCK.setElement(site, 52,
                    ((FLG.get(site) & 0x1) << 30) + ((USB20_TUNEOTG.get(site) & 0x7) << 27)
                            + ((USB20_TFREGRES.get(site) & 0x3F) << 21)
                            + ((USB20_TFHSRES.get(site) & 0x1F) << 16)
                            + ((LVDSRF_TX_IMP.get(site) & 0xF) << 12)
                            + ((LVDSRF_RX_IMP.get(site) & 0x7) << 9)
                            + ((LDOREF_ADC.get(site) & 0xF) << 5));

            // end
            DdieEFUSE_BLOCK.setElement(site, 51,
                    ((THM1_Offset_Code.get(site) & 0x7F) << 24)
                            + ((THM1_Ratio_Code.get(site) & 0x7F) << 17)
                            + ((THM1_Ratio_Sign.get(site) & 0x1) << 16));
            DdieEFUSE_BLOCK.setElement(site, 50,
                    ((THM1_Offset_Code.get(site) & 0x7F) << 24)
                            + ((THM1_Ratio_Code.get(site) & 0x7F) << 17)
                            + ((THM1_Ratio_Sign.get(site) & 0x1) << 16));

            DdieEFUSE_BLOCK.setElement(site, 49,
                    ((K_APCOUT.get(site) & 0xFFF) << 19) + ((B_APCOUT.get(site) & 0x3FF) << 6));
            DdieEFUSE_BLOCK.setElement(site, 48,
                    ((K_APCOUT.get(site) & 0xFFF) << 19) + ((B_APCOUT.get(site) & 0x3FF) << 6));

            //
            DdieEFUSE_BLOCK.setElement(site, 47, ((BT_POWER_CAL1.get(site) & 0xF) << 27)
                    + ((L_LDOREF_BT_VCO.get(site) & 0xF) << 23)
                    + ((BT_POWER_CAL2.get(site) & 0xF) << 19) + ((LDOREF_ABB.get(site) & 0x7) << 16)
                    + ((LDOREF_FM_VCO.get(site) & 0xF) << 12) + (R_CAL.get(site) & 0xF));
            DdieEFUSE_BLOCK.setElement(site, 46, ((BT_POWER_CAL1.get(site) & 0xF) << 27)
                    + ((L_LDOREF_BT_VCO.get(site) & 0xF) << 23)
                    + ((BT_POWER_CAL2.get(site) & 0xF) << 19) + ((LDOREF_ABB.get(site) & 0x7) << 16)
                    + ((LDOREF_FM_VCO.get(site) & 0xF) << 12) + (R_CAL.get(site) & 0xF));

        }

    }

}
