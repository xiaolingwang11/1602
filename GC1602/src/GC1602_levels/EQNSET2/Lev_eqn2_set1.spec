import GC1602_levels.EQNSET2.Lev_variables_eqn2;
import GC1602_configuration.Groups;
// EQNSET2 => "EQN_Normal";
// LEVSET1 => "LEV_Normal";
spec Lev_eqn2_set1 {
    set levelSet_1;
    setup digInOut gDigital - CLK26M_SINEOUT - TEMP_CS - TEMP_DIN - TEMP_DOUT
        - TEMP_SCLK {
        set level levelSet_1 {
            vih = 1.8V;
            vil = 0.0V;
            vol = 0.9V;
            voh = 0.9V;
            term = hiz;
        }
    }

    setup digInOut CLK26M_SINEOUT {
        set level levelSet_1 {
            vih = 1.8V;
            vil = 0.0V;
            vol = 0.6V;
            voh = 0.6V;
            term = hiz;
        }
    }

    setup digInOut TEMP_CS + TEMP_DIN + TEMP_DOUT + TEMP_SCLK {
        set level levelSet_1 {
            vih = 3.0V;
            vil = 0.0V;
            vol = 1.5V;
            voh = 1.5V;
            term = hiz;
        }
    }

}
