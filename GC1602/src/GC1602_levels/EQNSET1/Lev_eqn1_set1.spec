import GC1602_levels.EQNSET1.Lev_variables_eqn1;
import GC1602_configuration.Groups;
// EQNSET1 => "EQN_CONT";
// LEVSET1 => "LEV_CONT_OS";
spec Lev_eqn1_set1 {
  set levelSet_1;
  setup digInOut gCONT {
    set level levelSet_1 {
      vil = 0 V;
      vih = 0.8 V;
      vol = 0.4 V;
      voh = 0.4 V;
      term = hiz;
    }
  }
  setup digInOut EMZQ+TEMP_CS+TEMP_DIN+TEMP_DOUT+TEMP_SCLK+AWG_PU_IQ+AWG_VOCM_IQ+DAC_CM_IQ+DGT_PU_IQ+RX_VOCM_IQ+APCOUT0+APCOUT1+SCL+SDA+RF_Source_32417+RF_Source_32317+RF_Source_31913+RF_Source_32013+RF_Source_31917+RF_Source_32017+CT1_M1+CT2_M1+KK17+KK18+KK19+KK20 {
    set level levelSet_1 { }
  }
}
