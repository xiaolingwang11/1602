import GC1602_configuration.Groups;

spec Wvt_wvt_AVC_Standard_PLL {
    setup digInOut gDigital - MTCK_ARM - SCK - SDI0 {
        result.capture.enabled = false;
        wavetable wvt_AVC_Standard_PLL {
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

    setup digInOut MTCK_ARM + SCK + SDI0 {
        wavetable wvt_AVC_Standard_PLL {
            xModes = 1;
            1 : d1:1 d2:0;
            brk 1;
        }
    }

    setup digInOut gFDMA {
        result.capture.enabled = true;
    }

}
