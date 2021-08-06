import GC1602_timing.EQNSET66.Tim_variables_eqn66;
import GC1602_configuration.Groups;
// EQNSET66 => "EQN_Bridge";
// TIMSET1 => "AVC_Standard";
spec Tim_eqn66_set1 {
  set timingSet_1_EQNSET66;
  setup digInOut gDigital {
    set timing timingSet_1_EQNSET66 {
      period = (numer_eqn66 / denom_eqn66 * var_eqn66) * 1 ns;
      // Please set "periodDeviation" to adjust the period manually for CTIM.
      // periodDeviation = xxx ns;
      d1 = 0 ns;
      d2 = 0.5 * per_eqn66;
      r1 = (0.5 + offset_eqn66) * per_eqn66;
    }
  }
}
