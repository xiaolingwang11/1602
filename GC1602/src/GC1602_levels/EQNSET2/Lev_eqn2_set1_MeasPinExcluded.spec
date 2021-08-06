import GC1602_levels.EQNSET2.Lev_variables_eqn2;
import GC1602_configuration.Groups;
// EQNSET2 => "EQN_Normal";
// LEVSET1 => "LEV_Normal";
spec Lev_eqn2_set1_MeasPinExcluded {
  set levelSet_1;
  setup digInOut gDigital-gMeasDioPin-CLK26M_SINEOUT-TEMP_CS-TEMP_DIN-TEMP_DOUT-TEMP_SCLK {
    set level levelSet_1 {
      vih = 1.8 V;
      vil = 0.0 V;
      vol = 0.9 V;
      voh = 0.9 V;
      term = hiz;
    }
  }
  setup digInOut CLK26M_SINEOUT {
    set level levelSet_1 {
      vih = 1.8 V;
      vil = 0.0 V;
      vol = 0.6 V;
      voh = 0.6 V;
      term = hiz;
    }
  }
  setup digInOut TEMP_CS+TEMP_DIN+TEMP_DOUT+TEMP_SCLK {
    set level levelSet_1 {
      vih = 3.0 V;
      vil = 0.0 V;
      vol = 1.5 V;
      voh = 1.5 V;
      term = hiz;
    }
  }
}
