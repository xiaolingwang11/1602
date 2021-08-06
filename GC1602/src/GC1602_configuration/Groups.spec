import GC1602_configuration.Smart7Groups;

spec Groups {
    group gEFUSE = RFCTL_2_ + RFCTL_3_ + RFCTL_4_ + RFCTL_9_ + RFCTL_10_ + NF_DATA_0 +
        NF_DATA_2;
    group gFDMA = U0RTS + U0CTS + U0RXD + U0TXD + NF_DQS + NF_DATA_1 + U1RXD + U1TXD; //+SDI1 ;
    group gSPI = pSPI;
    group gAPCOUT = APCOUT0 + APCOUT1 + KK17 + KK18 + KK19 + KK20;
    //---------
    /**
     * The following PS1600 pin in pattern's SC is Q
     * ---------------------------
     * USB20_DM
     * USB20_DP
     * PAD_USB20_VREXT_1P8
     * ---------------------------
     * LVDSRF_RXD0N_CAL
     * LVDSRF_RXD0P
     * LVDSRF_RXD0P_CAL
     * LVDSRF_TXD0N_CAL
     * LVDSRF_TXD0P
     * LVDSRF_TXD0P_CAL
     * PAD_LVDSRF_REXT
     * ---------------------------
     */
    group gMeasDioPin =
    // for USB20     USB20_DM +
    USB20_DP + PAD_USB20_VREXT_1P8 +
    // for LVDSRF
    LVDSRF_RXD0N_CAL + LVDSRF_RXD0P + LVDSRF_RXD0P_CAL + LVDSRF_TXD0N_CAL + LVDSRF_TXD0P +
        LVDSRF_TXD0P_CAL + PAD_LVDSRF_REXT;
    group gPAVcomPin = DGT_PU_IQ + RX_VOCM_IQ + AWG_VOCM_IQ;
    group gLVDSRF = LVDSRF_RXD0N_CAL + LVDSRF_RXD0P + LVDSRF_RXD0P_CAL + LVDSRF_TXD0N_CAL
        + LVDSRF_TXD0P + LVDSRF_TXD0P_CAL + PAD_LVDSRF_REXT;
}
