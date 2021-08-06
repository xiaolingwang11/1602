import GC1602_configuration.Groups;

spec Wvt_wvt_AVC_EFUSE_breakStateSpecified {

/*
Description Resource    Path    Location    Type
The testflow could not be run successfully: xoc.dta.UncheckedDTAException: Cannot preserve IDigInOutCyclePassFailResults
results needed for result configuration. Result Accessor xoc.ate.ext.result.ZResultAccessorDigCompareData not available:
The requested result type is not supported by [signals: (ADI_D, ADI_SCLK, ANA_INT, APCOUT0, APCOUT1, AUD_ADD0, AUD_ADSYNC, AUD_DAD0, AUD_DAD1, AUD_DASYNC, AUD_SCLK, AWG_PU_IQ, AWG_VOCM_IQ, BUA_DET, CCIRD0, CCIRD1, CCIRD2, CCIRD3, CCIRD4, CCIRD5, CCIRD6, CCIRD7, CCIRHS, CCIRVS, CHIP_SLEEP, CLK26M_SINE0, CLK26M_SINEOUT, CLKOUT_TEST_MPLL, CLK_32K, CMMCLK, CMPCLK, CMPD0, CMRST0, CT1_M1, CT2_M1, DAC_CM_IQ, DGT_PU_IQ, DSI_TE, EMZQ, EXTINT0, EXTINT1, EXTINT5, EXTINT6, EXT_RST_B, IIS1CLK, IIS1DI, IIS1DO, IIS1LRCK, KEYIN_0_, KEYIN_1_, KEYIN_3_, KEYIN_4_, KEYOUT_0_, KEYOUT_1_, KEYOUT_2_, KEYOUT_3_, KEYOUT_4_, KK17, KK18, KK19, KK20, LCM_RSTN, LVDSRF_RXD0N_CAL, LVDSRF_RXD0P, LVDSRF_RXD0P_CAL, LVDSRF_TXD0N_CAL, LVDSRF_TXD0P, LVDSRF_TXD0P_CAL, MTCK_ARM, MTMS_ARM, NF_ALE, NF_CEN0, NF_CEN1, NF_CLE, NF_DATA_0, NF_DATA_1, NF_DATA_2, NF_DATA_3, NF_DATA_4, NF_DATA_5, NF_DATA_6, NF_DATA_7, NF_DQS, NF_RBN, NF_RE_T, NF_WEN_T, NF_WPN, PAD_LVDSRF_REXT, PAD_USB20_VREXT_1P8, PTEST, RFCTL_0_, RFCTL_10_, RFCTL_11_, RFCTL_12_, RFCTL_13_, RFCTL_14_, RFCTL_15_, RFCTL_16_, RFCTL_17_, RFCTL_18_, RFCTL_19_, RFCTL_1_, RFCTL_2_, RFCTL_3_, RFCTL_4_, RFCTL_9_, RFFE0_SCK, RFFE0_SDA, RFSCK0, RFSDA0, RFSEN0, RF_Source_31913, RF_Source_31917, RF_Source_32013, RF_Source_32017, RF_Source_32317, RF_Source_32417, RX_VOCM_IQ, SCK, SCL, SCL0, SCL1, SD0_CLK0, SD0_CMD, SD0_D_0_, SD0_D_1_, SD0_D_2_, SD0_D_3_, SD2_CLK, SD2_CMD, SD2_D_0_, SD2_D_1_, SD2_D_2_, SD2_D_3_, SDA, SDA0, SDA1, SDI0, SDI1, SDI2, SDI3, SIMCLK0, SIMDA0, SPI0_CD, SPI0_CLK, SPI0_CSN, SPI0_DI, SPI0_DO, SSN, TEMP_CS, TEMP_DIN, TEMP_DOUT, TEMP_SCLK, TF_DET, U0CTS, U0RTS, U0RXD, U0TXD, U1TXD, USB20_DM, USB20_DP, VREF_DQ_CA, XTL_BUF_EN1); all sites].: Affected test suite: Main.EFUSE.Defuse_Write.    Ddie_Efuse_Write.java   /NemoL/tml/_191016_device_tml/EFUSE line 119    ATE Java Problem

* Description   Resource    Path    Location    Type
The testflow could not be run successfully: xoc.dta.UncheckedDTAException: Cannot preserve IDigInOutCyclePassFailResults
results needed for result configuration. Result Accessor xoc.ate.ext.result.ZResultAccessorDigCompareData not available:
The requested result type is not supported by [signals: (NF_DATA_0, NF_DATA_2, RFCTL_10_, RFCTL_2_, RFCTL_3_, RFCTL_4_, RFCTL_9_); all sites].: Affected test suite: Main.EFUSE.Defuse_Write. Ddie_Efuse_Write.java   /NemoL/tml/_191016_device_tml/EFUSE line 119    ATE Java Problem

*/

    group gEfuseCaseCLK = MTCK_ARM + SCK + SDI0 + SSN + RFCTL_2_;
    group gEfuse_q_pins = APCOUT0 + APCOUT1 + AWG_PU_IQ + AWG_VOCM_IQ + CLK26M_SINE0 +
        CLK26M_SINEOUT + CLKOUT_TEST_MPLL + CT1_M1 + CT2_M1 + DAC_CM_IQ + DGT_PU_IQ +
        EMZQ + KK17 + KK18 + KK19 + KK20 + LVDSRF_RXD0N_CAL + LVDSRF_RXD0P_CAL +
        LVDSRF_RXD0P + LVDSRF_TXD0N_CAL + LVDSRF_TXD0P_CAL + LVDSRF_TXD0P +
        PAD_USB20_VREXT_1P8 + PAD_LVDSRF_REXT + RF_Source_31913 + RF_Source_31917 +
        RF_Source_32013 + RF_Source_32017 + RF_Source_32317 + RF_Source_32417 + SCL + SDA
        + TEMP_CS + TEMP_DIN + TEMP_DOUT + USB20_DM + USB20_DP +  VREF_DQ_CA + RX_VOCM_IQ + TEMP_SCLK;
    group gEfuse_L_pins = CLK_AUX0 + KEYIN_2_ + RFCTL_5_ + RFCTL_6_ + RFCTL_7_ + RFCTL_8_
        + SIMCLK1 + SIMDA1 + SIMRST0 + SIMRST1;
    group gEfuse_H_pins = U1RXD;
    group gEfuse_1_pins = NF_DATA_0 + PTEST + RFFE0_SCK + SD2_D_1_ + SD2_D_2_ + SPI0_DO + RFCTL_3_ + RFCTL_10_;
    //group gEfuse_0_pins = gDigital-gEfuse_1_pins-gEfuse_q_pins-gEfuse_L_pins-gEfuse_H_pins;
    group gEfuse_0_pins = ADI_D + ADI_SCLK + ANA_INT + AUD_ADD0 + AUD_ADSYNC + AUD_DAD0 +
        AUD_DAD1 + AUD_DASYNC + AUD_SCLK + BUA_DET + CCIRD0 + CCIRD1 + CCIRD2 + CCIRD3 +
        CCIRD4 + CCIRD5 + CCIRD6 + CCIRD7 + CCIRHS + CCIRVS + CHIP_SLEEP + CLK_32K +
        CMMCLK + CMPCLK + CMPD0 + CMRST0 + DSI_TE + EXT_RST_B + EXTINT0 + EXTINT1 +
        EXTINT5 + EXTINT6 + IIS1CLK + IIS1DI + IIS1DO + IIS1LRCK + KEYIN_0_ + KEYIN_1_ +
        KEYIN_3_ + KEYIN_4_ + KEYOUT_0_ + KEYOUT_1_ + KEYOUT_2_ + KEYOUT_3_ + KEYOUT_4_ +
        LCM_RSTN + MTMS_ARM + NF_ALE + NF_CEN0 + NF_CEN1 + NF_CLE + NF_DATA_1 + NF_DATA_2
        + NF_DATA_3 + NF_DATA_4 + NF_DATA_5 + NF_DATA_6 + NF_DATA_7 + NF_DQS + NF_RBN +
        NF_RE_T + NF_WEN_T + NF_WPN + RFCTL_0_ + RFCTL_1_  + RFCTL_11_ +
        RFCTL_12_ + RFCTL_13_ + RFCTL_14_ + RFCTL_15_ + RFCTL_16_ + RFCTL_17_ + RFCTL_18_
        + RFCTL_19_ + RFCTL_4_ + RFFE0_SDA + RFSCK0 + RFSDA0 + RFSEN0 +
        SCL0 + SCL1 + SD0_CLK0 + SD0_CMD + SD0_D_0_ + SD0_D_1_ + SD0_D_2_ +
        SD0_D_3_ + SD2_CLK + SD2_CMD + SD2_D_0_ + SD2_D_3_ + SDA0 + SDA1 + SDI1 + SDI2 +
        SDI3 + SIMCLK0 + SIMDA0 + SPI0_CD + SPI0_CLK + SPI0_CSN + SPI0_DI  +
        TF_DET + U0CTS + U0RTS + U0RXD + U0TXD + U1TXD + XTL_BUF_EN1;
    group gEfuse_X_pins = RFCTL_9_;

    setup digInOut gEfuseCaseCLK {
        result.cyclePassFail.enabled = false;
        wavetable Wvt_wvt_AVC_EFUSE_breakStateSpecified {
            xModes = 1;
            0 : d1:1 d2:0; // eddy add
            1 : d1:1 d2:0;
            brk 1;
        }
    }

    // gDigital-gEfuseCaseCLK
    //          =
    // gEfuse_1_pins+gEfuse_0_pins+gEfuse_L_pins+gEfuse_H_pins+gEfuse_q_pins+gEfuse_X_pins

    //  setup digInOut gDigital-gEfuseCaseCLK{
    //    result.capture.enabled = false;
    //    wavetable wvt_AVC_EFUSE {
    //      xModes = 1;
    //      0: d1:0;
    //      1: d1:1;
    //      L: d1:Z r1:L;
    //      H: d1:Z r1:H;
    //      X: d1:Z r1:X;
    //      Z: d1:Z;
    //      p: d1:1 d2:0;
    //      C: d1:Z r1:C;
    //      D: d1:Z r1:X;
    //    }
    //  }
    setup digInOut gEfuse_1_pins {
        result.cyclePassFail.enabled = false;
        wavetable Wvt_wvt_AVC_EFUSE_breakStateSpecified {
            xModes = 1;
            0 : d1:0;
            1 : d1:1;
            L : d1:Z r1:L;
            H : d1:Z r1:H;
            X : d1:Z r1:X;
            Z : d1:Z;
            p : d1:1 d2:0;
            C : d1:Z r1:C;
            D : d1:Z r1:X;
            brk 1;
        }
    }

    setup digInOut gEfuse_0_pins {
        result.cyclePassFail.enabled = false;
        wavetable Wvt_wvt_AVC_EFUSE_breakStateSpecified {
            xModes = 1;
            0 : d1:0;
            1 : d1:1;
            L : d1:Z r1:L;
            H : d1:Z r1:H;
            X : d1:Z r1:X;
            Z : d1:Z;
            p : d1:1 d2:0;
            C : d1:Z r1:C;
            D : d1:Z r1:X;
            brk 0;
        }
    }

    setup digInOut gEfuse_L_pins + gEfuse_H_pins {
        result.cyclePassFail.enabled = false;
        wavetable Wvt_wvt_AVC_EFUSE_breakStateSpecified {
            xModes = 1;
            0 : d1:0;
            1 : d1:1;
            L : d1:Z r1:L;
            H : d1:Z r1:H;
            X : d1:Z r1:X;
            Z : d1:Z;
            p : d1:1 d2:0;
            C : d1:Z r1:C;
            D : d1:Z r1:X;
            brk Z;
        }
    }

    setup digInOut gEfuse_q_pins {
        result.cyclePassFail.enabled = false;
        wavetable Wvt_wvt_AVC_EFUSE_breakStateSpecified {
            xModes = 1;
            0 : d1:0;
            1 : d1:1;
            L : d1:Z r1:L;
            H : d1:Z r1:H;
            X : d1:Z r1:X;
            Z : d1:Z;
            p : d1:1 d2:0;
            C : d1:Z r1:C;
            D : d1:Z r1:X;
            Q : d1:N;
            brk Q;
        }
    }

    setup digInOut gEfuse_X_pins {
        result.cyclePassFail.enabled = false;
        wavetable Wvt_wvt_AVC_EFUSE_breakStateSpecified {
            xModes = 1;
            0 : d1:0;
            1 : d1:1;
            L : d1:Z r1:L;
            H : d1:Z r1:H;
            X : d1:Z r1:X;
            Z : d1:Z;
            p : d1:1 d2:0;
            C : d1:Z r1:C;
            D : d1:Z r1:X;
            Q : d1:N;
        }
    }

    setup digInOut gEFUSE {
        result.cyclePassFail.enabled = false;
        result.capture.enabled = true;
    }

}

/*
 Description    Resource    Path    Location    Type
The testflow could not be run successfully: xoc.dta.UncheckedDTAException: Cannot preserve IDigInOutCyclePassFailResults results needed for
* result configuration. Result Accessor xoc.ate.ext.result.ZResultAccessorDigCompareData not available: The requested result type is not
* supported by [signals: (ADI_D, ADI_SCLK, ANA_INT, APCOUT0, APCOUT1, AUD_ADD0, AUD_ADSYNC, AUD_DAD0, AUD_DAD1, AUD_DASYNC, AUD_SCLK, AWG_PU_IQ,
* AWG_VOCM_IQ, BUA_DET, CCIRD0, CCIRD1, CCIRD2, CCIRD3, CCIRD4, CCIRD5, CCIRD6, CCIRD7, CCIRHS, CCIRVS, CHIP_SLEEP, CLK26M_SINE0, CLK26M_SINEOUT,
* CLKOUT_TEST_MPLL, CLK_32K, CLK_AUX0, CMMCLK, CMPCLK, CMPD0, CMRST0, CT1_M1, CT2_M1, DAC_CM_IQ, DGT_PU_IQ, DSI_TE, EMZQ, EXTINT0, EXTINT1, EXTINT5,
*  EXTINT6, EXT_RST_B, IIS1CLK, IIS1DI, IIS1DO, IIS1LRCK, KEYIN_0_, KEYIN_1_, KEYIN_2_, KEYIN_3_, KEYIN_4_, KEYOUT_0_, KEYOUT_1_, KEYOUT_2_, KEYOUT_3_, KEYOUT_4_, KK17, KK18, KK19, KK20, LCM_RSTN, LVDSRF_RXD0N_CAL, LVDSRF_RXD0P, LVDSRF_RXD0P_CAL, LVDSRF_TXD0N_CAL, LVDSRF_TXD0P, LVDSRF_TXD0P_CAL, MTCK_ARM, MTMS_ARM, NF_ALE, NF_CEN0, NF_CEN1, NF_CLE, NF_DATA_1, NF_DATA_3, NF_DATA_4, NF_DATA_5, NF_DATA_6, NF_DATA_7, NF_DQS, NF_RBN, NF_RE_T, NF_WEN_T, NF_WPN, PAD_LVDSRF_REXT, PAD_USB20_VREXT_1P8, PTEST, RFCTL_0_, RFCTL_11_, RFCTL_12_, RFCTL_13_, RFCTL_14_, RFCTL_15_, RFCTL_16_, RFCTL_17_, RFCTL_18_, RFCTL_19_, RFCTL_1_, RFCTL_5_, RFCTL_6_, RFCTL_7_, RFCTL_8_, RFFE0_SCK, RFFE0_SDA, RFSCK0, RFSDA0, RFSEN0, RF_Source_31913, RF_Source_31917, RF_Source_32013, RF_Source_32017, RF_Source_32317, RF_Source_32417, RX_VOCM_IQ, SCK, SCL, SCL0, SCL1, SD0_CLK0, SD0_CMD, SD0_D_0_, SD0_D_1_, SD0_D_2_, SD0_D_3_, SD2_CLK, SD2_CMD, SD2_D_0_, SD2_D_1_, SD2_D_2_, SD2_D_3_, SDA, SDA0, SDA1, SDI0, SDI1, SDI2, SDI3, SIMCLK0, SIMCLK1, SIMDA0, SIMDA1, SIMRST0, SIMRST1, SPI0_CD, SPI0_CLK, SPI0_CSN, SPI0_DI, SPI0_DO, SSN, TEMP_CS, TEMP_DIN, TEMP_DOUT, TEMP_SCLK, TF_DET, U0CTS, U0RTS, U0RXD, U0TXD, U1RXD, U1TXD, USB20_DM, USB20_DP, VREF_DQ_CA, XTL_BUF_EN1); all sites].: Affected test suite: Main.EFUSE.Defuse_Write.   Ddie_Efuse_Write.java   /NemoL/tml/_191016_device_tml/EFUSE line 119    ATE Java Problem

 * */
