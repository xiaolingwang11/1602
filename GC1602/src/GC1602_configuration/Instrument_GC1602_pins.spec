import GC1602_configuration.Groups;
spec Instrument_GC1602_pins {
  setup digInOut ADI_D {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut ADI_SCLK {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut AUD_ADD0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut AUD_ADSYNC {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut AUD_DAD0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut AUD_DAD1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut AUD_DASYNC {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut AUD_SCLK {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut BUA_DET {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CCIRD0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CCIRD1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CCIRD2 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CCIRD3 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CCIRD4 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CCIRD5 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CCIRD6 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CCIRD7 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CCIRHS {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CCIRVS {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CLK_AUX0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CMMCLK {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CMPCLK {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CMPD0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CMRST0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut DSI_TE {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut EXTINT0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut EXTINT1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut EXTINT5 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut EXTINT6 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut EXT_RST_B {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut IIS1CLK {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut IIS1DI {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut IIS1DO {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut IIS1LRCK {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KEYIN_0_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KEYIN_1_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KEYIN_2_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KEYIN_3_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KEYIN_4_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KEYOUT_0_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KEYOUT_1_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KEYOUT_2_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KEYOUT_3_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KEYOUT_4_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut LCM_RSTN {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut MTCK_ARM {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut MTMS_ARM {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_ALE {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_CEN0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_CEN1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_CLE {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_DATA_0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_DATA_1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_DATA_2 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_DATA_3 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_DATA_4 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_DATA_5 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_DATA_6 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_DATA_7 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_DQS {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_RBN {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_RE_T {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_WEN_T {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut NF_WPN {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut PTEST {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_0_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_10_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_11_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_12_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_13_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_14_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_15_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_16_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_17_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_18_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_19_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_1_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_2_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_3_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_4_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_5_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_6_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_7_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_8_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFCTL_9_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFFE0_SCK {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFFE0_SDA {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFSCK0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFSDA0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RFSEN0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SCK {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SCL0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SCL1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SD0_CLK0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SD0_CMD {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SD0_D_0_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SD0_D_1_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SD0_D_2_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SD0_D_3_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SD2_CLK {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SD2_CMD {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SD2_D_0_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SD2_D_1_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SD2_D_2_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SD2_D_3_ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SDA0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SDA1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SDI0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SDI1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SDI2 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SDI3 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SIMCLK0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SIMCLK1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SIMDA0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SIMDA1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SIMRST0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SIMRST1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SPI0_CD {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SPI0_CLK {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SPI0_CSN {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SPI0_DI {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SPI0_DO {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SSN {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut TF_DET {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut U0CTS {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut U0RTS {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut U0RXD {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut U0TXD {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut U1RXD {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut U1TXD {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut XTL_BUF_EN1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut ANA_INT {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CHIP_SLEEP {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CLK_32K {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CLK26M_SINE0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CLK26M_SINEOUT {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CLKOUT_TEST_MPLL {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut LVDSRF_RXD0N_CAL {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut LVDSRF_RXD0P {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut LVDSRF_RXD0P_CAL {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut LVDSRF_TXD0N_CAL {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut LVDSRF_TXD0P {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut LVDSRF_TXD0P_CAL {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut PAD_LVDSRF_REXT {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut PAD_USB20_VREXT_1P8 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut USB20_DM {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut USB20_DP {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut VREF_DQ_CA {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut EMZQ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut TEMP_CS {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut TEMP_DIN {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut TEMP_DOUT {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut TEMP_SCLK {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut AWG_PU_IQ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut AWG_VOCM_IQ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut DAC_CM_IQ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut DGT_PU_IQ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RX_VOCM_IQ {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut APCOUT0 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut APCOUT1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SCL {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut SDA {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RF_Source_32417 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RF_Source_32317 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RF_Source_31913 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RF_Source_32013 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RF_Source_31917 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut RF_Source_32017 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CT1_M1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut CT2_M1 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KK17 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KK18 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KK19 {
    result.cyclePassFail.enabled = true;
  }
  setup digInOut KK20 {
    result.cyclePassFail.enabled = true;
  }
  setup dcVI VDDCORE {
    config.pos = VDDCORE;
  }
  setup dcVI VDDMEM {
    config.pos = VDDMEM;
  }
  setup dcVI AVDD12 {
    config.pos = AVDD12;
  }
  setup dcVI AVDD18 {
    config.pos = AVDD18;
  }
  setup dcVI AVDD33_BT_PA {
    config.pos = AVDD33_BT_PA;
  }
  setup dcVI VDD1 {
    config.pos = VDD1;
  }
  setup dcVI VIO {
    config.pos = VIO;
  }
  setup dcVI VSD0 {
    config.pos = VSD0;
  }
  setup dcVI VSIM {
    config.pos = VSIM;
  }
  setup dcVI AVDD33_USB20 {
    config.pos = AVDD33_USB20;
  }
  setup dcVI USB20_DP_DPS {
    config.pos = USB20_DP_DPS;
  }
  setup dcVI USB20_DM_DPS {
    config.pos = USB20_DM_DPS;
  }
}
