import GC1602_timing.EQNSET96.Tim_variables_eqn96;
import GC1602_configuration.Groups;
// EQNSET96 => "EQN_AVC_Standard";
// TIMSET1 => "AVC_Standard";
spec Tim_eqn96_set1 {
    set timingSet_1_EQNSET96;
    setup digInOut gDigital {
        set timing timingSet_1_EQNSET96 {
            period = ( numer_eqn96 / denom_eqn96 * var_eqn96 ) * 1ns;
            // Please set "periodDeviation" to adjust the period manually for CTIM.
            // periodDeviation = xxx ns;
            d1 = 0ns;
            d2 = 0.5 * per_eqn96;
            //            d1 = 0.2 * per_eqn96;
            //            d2 = 0.7 * per_eqn96;
            r1 = ( 0.5 + offset_eqn96 ) * per_eqn96;
        }
    }
}
