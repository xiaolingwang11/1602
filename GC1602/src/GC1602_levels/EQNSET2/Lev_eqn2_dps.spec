import GC1602_levels.EQNSET2.Lev_variables_eqn2;
// EQNSET2 => "EQN_Normal";
spec Lev_eqn2_dps {
  setup dcVI VDDCORE {
    connect = true;
    level.vforce = v_VDDCORE;
    // different range of iclamp for ganged DPS between HC and HV, DPS migration currently supports DPS128, AVI64, UHC4T and FVI16 cards, please modify manually!
    level.iclamp = 3500 mA;
    level.waitTime = 0 ms;
    level.vrange = 2 V; // <- 1.0 V; by eddy
    level.irange = 3500 mA;
    disconnectMode = loz;
  }
  setup dcVI VDDMEM {
    connect = true;
    level.vforce = v_VDDMEM;
    level.iclamp = 800 mA;
    level.waitTime = 0 ms;
    level.vrange = 1.5 V;
    level.irange = 800 mA;
    disconnectMode = loz;
  }
  setup dcVI AVDD12 {
    connect = true;
    level.vforce = v_AVDD12;
    level.iclamp = 200 mA;
    level.waitTime = 0 ms;
    level.vrange = 1.5 V;
    level.irange = 200 mA;
    disconnectMode = loz;
  }
  setup dcVI AVDD18 {
    connect = true;
    level.vforce = v_AVDD18;
    level.iclamp = 100 mA;
    level.waitTime = 0 ms;
    level.vrange = 2.0 V;
    level.irange = 100 mA;
    disconnectMode = loz;
  }
  setup dcVI AVDD33_BT_PA {
    connect = true;
    level.vforce = v_AVDD33_BT_PA;
    level.iclamp = 400 mA;
    level.waitTime = 0 ms;
    level.vrange = 3.5 V;
    level.irange = 400 mA;
    disconnectMode = loz;
  }
  setup dcVI VDD1 {
    connect = true;
    level.vforce = v_VDD1;
    level.iclamp = 800 mA;
    level.waitTime = 0 ms;
    level.vrange = 2.5 V;
    level.irange = 800 mA;
    disconnectMode = loz;
  }
  setup dcVI VIO {
    connect = true;
    level.vforce = v_VIO;
    level.iclamp = 800 mA;
    level.waitTime = 0 ms;
    level.vrange = 2.0 V;
    level.irange = 800 mA;
    disconnectMode = loz;
  }
  setup dcVI VSD0 {
    connect = true;
    level.vforce = v_VSD0;
    level.iclamp = 100 mA;
    level.waitTime = 0 ms;
    level.vrange = 3.5 V;
    level.irange = 100 mA;
    disconnectMode = loz;
  }
  setup dcVI VSIM {
    connect = true;
    level.vforce = v_VSIM;
    level.iclamp = 100 mA;
    level.waitTime = 0 ms;
    level.vrange = 2.0 V;
    level.irange = 100 mA;
    disconnectMode = loz;
  }
  setup dcVI AVDD33_USB20 {
    connect = true;
    level.vforce = v_AVDD33_USB20;
    level.iclamp = 100 mA;
    level.waitTime = 0 ms;
    level.vrange = 3.5 V;
    level.irange = 100 mA;


    disconnectMode = loz;
  }
  setup dcVI USB20_DP_DPS+USB20_DM_DPS {
    connect = true;
    level.vforce = 0 V;
    level.iclamp = 10 mA;
    level.waitTime = 0 ms;
    level.vrange = 0 V;
    level.irange = 10 mA;
    disconnect =  true;
    disconnectMode = hiz;
  }
}
