import GC1602_configuration.Groups;

spec Wvt_wvt_PLL_IO {
    setup digInOut gDigital - MTCK_ARM - SCK - SDI0 {
        result.capture.enabled = false;
        wavetable wvt_PLL_IO {
            xModes = 1;
            0 : d1:0;
            1 : d1:1;
            L : d1:Z r1:L;
            H : d1:Z r1:H;
            X : d1:Z r1:X;
            Z : d1:Z;
            p : d1:1 d2:0;
            D : d1:Z r1:X r2:X r3:X r4:X r5:X r6:X r7:X r8:X;
            C : d1:Z r1:C r2:C r3:C r4:C r5:C r6:C r7:C r8:C;
            Q : d1:N;
        }
    }

    setup digInOut MTCK_ARM + SCK + SDI0 {
        wavetable wvt_PLL_IO {
            xModes = 1;
            1 : d1:1 d2:0;
            brk 1;
        }
    }

    setup digInOut CLK26M_SINEOUT + CLK_AUX0{
        result.capture.enabled = true;
    }

}
