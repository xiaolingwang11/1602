import GC1602_configuration.Groups;
spec Wvt_wvt_Bridge {
  setup digInOut gDigital {
    result.capture.enabled = false;
    wavetable wvt_Bridge {
      xModes = 1;
      0: d1:0;
      1: d1:1;
      L: d1:Z r1:L;
      H: d1:Z r1:H;
      X: d1:Z r1:X;
      Z: d1:Z;
      p: d1:1 d2:0;
      C: d1:Z r1:C;
      D: d1:Z r1:X;
      Q: d1:N;
    }
  }
}
