import GC1602_timing.EQNSET95.Tim_variables_eqn95;
import GC1602_configuration.Groups;
// EQNSET95 => "EQN_PLL_IO";
// TIMSET1 => "AVC_Standard";
spec Tim_eqn95_set1 {
  set timingSet_1_EQNSET95;
  setup digInOut gDigital {
    set timing timingSet_1_EQNSET95 {
      period = (numer_eqn95 / denom_eqn95 * var_eqn95) * 1 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0 ns;
      d2 = 0.5 * per_eqn95;
      r1 = (0.0 + offset_eqn95) * per_eqn95;
      r2 = (0.125 + offset_eqn95) * per_eqn95;
      r3 = (0.25 + offset_eqn95) * per_eqn95;
      r4 = (0.375 + offset_eqn95) * per_eqn95;
      r5 = (0.5 + offset_eqn95) * per_eqn95;
      r6 = (0.625 + offset_eqn95) * per_eqn95;
      r7 = (0.75 + offset_eqn95) * per_eqn95;
      r8 = (0.875 + offset_eqn95) * per_eqn95;
    }
  }
}
