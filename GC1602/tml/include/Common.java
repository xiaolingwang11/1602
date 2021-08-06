package include;

import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDcVI;
import xoc.dsa.ISetupDigInOut;
import xoc.dsa.ISetupDigInOut.ILevel;
import xoc.dsa.ISetupDigInOut.ITiming;
import xoc.dsa.ISetupWavetable;
import xoc.dta.datatypes.MultiSiteDouble;
import xoc.dta.datatypes.MultiSiteLong;
import xoc.dta.datatypes.dsp.MultiSiteSpectrum;
import xoc.dta.datatypes.dsp.MultiSiteWaveLong;
import xoc.dta.datatypes.dsp.SpectrumUnit;
import xoc.dta.datatypes.dsp.WindowFunction;

public class Common {

//    public static String LB_type="old";
//
//    public static MultiSiteLong R_Cal_code=new MultiSiteLong();
////    public   static MultiSiteDouble Leakage_VDDARM_C_temp=new MultiSiteDouble() ;
////    public   static MultiSiteDouble Leakage_VDDCORE_D_temp=new MultiSiteDouble() ;
//    public static MultiSiteLong MBIST_Repair_flag=new MultiSiteLong(0);
//    public static MultiSiteLong vddarm_lv=new MultiSiteLong(1);
//    public static MultiSiteLong LIT_FF_Pass=new MultiSiteLong(0);
//    public static MultiSiteLong LIT_LEAK_4=new MultiSiteLong(0);
//
//    public static MultiSiteLong LIT_LEAK_5=new MultiSiteLong(0);
//    public static MultiSiteLong LIT_LEAK_6=new MultiSiteLong(0);
//
//    public static MultiSiteLong LIT_SS_Pass=new MultiSiteLong(0);
//    public static MultiSiteLong LIT_TT_Pass=new MultiSiteLong(0);
//
//    public static MultiSiteLong BIG_FF_Pass=new MultiSiteLong(0);
//    public static MultiSiteLong BIG_SS_Pass=new MultiSiteLong(0);
//    public static MultiSiteLong BIG_TT_Pass=new MultiSiteLong(0);
//
//    public static MultiSiteLong Leakage_Pass=new MultiSiteLong(0);
//
////    public static MultiSiteLong LIT_BINNING=new MultiSiteLong(0);
//    public   static MultiSiteDouble Slope=new MultiSiteDouble() ;
//    public   static MultiSiteDouble Intercept=new MultiSiteDouble() ;
//    public  static    MultiSiteLong[] DdieEFUSE_BLOCK=new MultiSiteLong[96];    //DdieEFUSE_BLOCK[24]
////    public  static    MultiSiteLongArray DdieEFUSE_BLOCK=new MultiSiteLongArray();    //DdieEFUSE_BLOCK[24]
//  //MultiSiteLong EFUSE_BLOCK[32];    //DdieEFUSE_BLOCK[24]
//
//  // *************** UID *************
//    public   static MultiSiteLong Y_loc_R=new MultiSiteLong() ;
//    public   static MultiSiteLong X_loc_R=new MultiSiteLong();
//  public static MultiSiteLong Wafer_ID_R=new MultiSiteLong();
//  public static MultiSiteLong LotID0_R=new MultiSiteLong();
//  public static MultiSiteLong LotID1_R=new MultiSiteLong();
//  public static MultiSiteLong LotID2_R=new MultiSiteLong();
//  public static MultiSiteLong LotID3_R=new MultiSiteLong();
//  public static MultiSiteLong LotID4_R=new MultiSiteLong();
//  public static MultiSiteLong LotID5_R=new MultiSiteLong();
//  // *************** THM *************
//  public static MultiSiteDouble T_ext=new MultiSiteDouble(0.0);
//
//  public static MultiSiteLong THM0_Ratio_Sign=new MultiSiteLong();
//  public static MultiSiteLong THM0_Ratio_Code=new MultiSiteLong();
//  public static MultiSiteLong THM0_Offset_Code=new MultiSiteLong();
//  public static MultiSiteLong THM1_Ratio_Sign=new MultiSiteLong();
//  public static MultiSiteLong THM1_Ratio_Code=new MultiSiteLong();
//  public static MultiSiteLong THM1_Offset_Code=new MultiSiteLong();
//
//  // *************** MIPI TRIM *************
//
//  public static MultiSiteLong CSI2P2_TRIMBG=new MultiSiteLong();
//  public static MultiSiteLong CSI2P2_RXRCTL=new MultiSiteLong();
//  public static MultiSiteLong DSI_TRIMBG=new MultiSiteLong();
//  public static MultiSiteLong DSI_TXRCTL=new MultiSiteLong();
//  public static MultiSiteLong CSI_4L_RXRCTL=new MultiSiteLong();
//
//
//  public static MultiSiteLong DSI_TRIMBG_bit0=new MultiSiteLong();
//  public static MultiSiteLong DSI_TRIMBG_bit1=new MultiSiteLong();
//  public static MultiSiteLong DSI_TRIMBG_bit2=new MultiSiteLong();
//  public static MultiSiteLong DSI_TRIMBG_bit3=new MultiSiteLong();
//
//  public static MultiSiteLong DSI_TXRCTL_bit0=new MultiSiteLong();
//  public static MultiSiteLong DSI_TXRCTL_bit1=new MultiSiteLong();
//  public static MultiSiteLong DSI_TXRCTL_bit2=new MultiSiteLong();
//  public static MultiSiteLong DSI_TXRCTL_bit3=new MultiSiteLong();
//
//  public static MultiSiteLong CSI2P2_TRIMBG_bit0=new MultiSiteLong();
//  public static MultiSiteLong CSI2P2_TRIMBG_bit1=new MultiSiteLong();
//  public static MultiSiteLong CSI2P2_TRIMBG_bit2=new MultiSiteLong();
//  public static MultiSiteLong CSI2P2_TRIMBG_bit3=new MultiSiteLong();
//
//  public static MultiSiteLong CSI2P2_RXRCTL_bit0=new MultiSiteLong();
//  public static MultiSiteLong CSI2P2_RXRCTL_bit1=new MultiSiteLong();
//  public static MultiSiteLong CSI2P2_RXRCTL_bit2=new MultiSiteLong();
//  public static MultiSiteLong CSI2P2_RXRCTL_bit3=new MultiSiteLong();
//
//  public static MultiSiteLong CSI_4L_RXRCTL_bit0=new MultiSiteLong();
//  public static MultiSiteLong CSI_4L_RXRCTL_bit1=new MultiSiteLong();
//  public static MultiSiteLong CSI_4L_RXRCTL_bit2=new MultiSiteLong();
//  public static MultiSiteLong CSI_4L_RXRCTL_bit3=new MultiSiteLong();
//
//  // *************** LVDSRF TRIM *************
//  //public static MultiSiteLong LVDSRF_RX_VMode=new MultiSiteLong();
//  public static MultiSiteLong LVDSRF_RX_IMP=new MultiSiteLong();
//  //public static MultiSiteLong LVDSRF_RX_IMode=new MultiSiteLong();
////  public static MultiSiteLong LVDSRF_MIPI=new MultiSiteLong();
//  public static MultiSiteLong LVDSRF_TX_IMP=new MultiSiteLong();
//
//  public static MultiSiteLong LVDSRF_RX_VMode_bit0=new MultiSiteLong();
//  public static MultiSiteLong LVDSRF_RX_VMode_bit1=new MultiSiteLong();
//  public static MultiSiteLong LVDSRF_RX_VMode_bit2=new MultiSiteLong();
//  public static MultiSiteLong LVDSRF_RX_VMode_bit3=new MultiSiteLong();
//
////  TODO: these 3 var may not nessary
//  public static MultiSiteLong LVDSRF_RX_CMode_bit0=new MultiSiteLong();
//  public static MultiSiteLong LVDSRF_RX_CMode_bit1=new MultiSiteLong();
//  public static MultiSiteLong LVDSRF_RX_CMode_bit2=new MultiSiteLong();
//
//  public static MultiSiteLong LVDSRF_MIPI_bit0=new MultiSiteLong();
//  public static MultiSiteLong LVDSRF_MIPI_bit1=new MultiSiteLong();
//  public static MultiSiteLong LVDSRF_MIPI_bit2=new MultiSiteLong();
//  public static MultiSiteLong LVDSRF_MIPI_bit3=new MultiSiteLong();
//
//  // *************** USB20 TRIM *************
//  //MultiSiteLong USB_BGCode=new MultiSiteLong(), USB_TermCode=new MultiSiteLong(), USB_12KCode=new MultiSiteLong();
//  public static MultiSiteLong USB20_TUNEOTG=new MultiSiteLong(), USB20_TFHSRES=new MultiSiteLong(), USB20_TFREGRES=new MultiSiteLong();
//
//  public static MultiSiteLong iUSB_BG_bit0=new MultiSiteLong();
//  public static MultiSiteLong iUSB_BG_bit1=new MultiSiteLong();
//  public static MultiSiteLong iUSB_BG_bit2=new MultiSiteLong();
//
//  public static MultiSiteLong iUSB_ODT_bit0=new MultiSiteLong();
//  public static MultiSiteLong iUSB_ODT_bit1=new MultiSiteLong();
//  public static MultiSiteLong iUSB_ODT_bit2=new MultiSiteLong();
//  public static MultiSiteLong iUSB_ODT_bit3=new MultiSiteLong();
//  public static MultiSiteLong iUSB_ODT_bit4=new MultiSiteLong();
//
//  public static MultiSiteLong iUSB_6K_bit0=new MultiSiteLong();
//  public static MultiSiteLong iUSB_6K_bit1=new MultiSiteLong();
//  public static MultiSiteLong iUSB_6K_bit2=new MultiSiteLong();
//  public static MultiSiteLong iUSB_6K_bit3=new MultiSiteLong();
//  public static MultiSiteLong iUSB_6K_bit4=new MultiSiteLong();
//  public static MultiSiteLong iUSB_6K_bit5=new MultiSiteLong();
//
//  // *************** BINNING TRIM *************
//  public static MultiSiteLong BINNING_RESLT=new MultiSiteLong();
//  public static MultiSiteLong CPU_OSC=new MultiSiteLong(), GPU_OSC=new MultiSiteLong(), PUB_OSC=new MultiSiteLong();
//  public static MultiSiteLong LIT_BIN=new MultiSiteLong();
//  public static MultiSiteLong GPU_BIN=new MultiSiteLong();
//
//
//  //leakage
//  public static MultiSiteLong VDDCORE_LEAK=new MultiSiteLong(), VDDARM_LEAK=new MultiSiteLong(), GPU_LEAK=new MultiSiteLong();
//
//  public static MultiSiteLong VDDCORE_GPU=new MultiSiteLong();
//  public static MultiSiteLong LIGHT_ARM_CORE=new MultiSiteLong();
//  public static MultiSiteLong DEEP_CORE=new MultiSiteLong();
//  public static MultiSiteLong VDDCORE_ALLON=new MultiSiteLong();
//  public static MultiSiteLong VDDARM_ALLON=new MultiSiteLong();
//
//  public static MultiSiteLong MP_ES=new MultiSiteLong();
//
//  public static MultiSiteLong Power_switch_leak=new MultiSiteLong();
//  public static MultiSiteLong FT_tem=new MultiSiteLong();
//
//  // *************** WCN_RF TRIM *************
//  public static MultiSiteLong K_APCOUT_bit11_5=new MultiSiteLong(), K_APCOUT_bit4_0=new MultiSiteLong();
//  public static MultiSiteLong B_APCOUT_bit9=new MultiSiteLong(), B_APCOUT_bit8_0=new MultiSiteLong();
//
//
//  public static MultiSiteLong LDOREF_GNSS_ADC=new MultiSiteLong(), LDOREF_GNSS_VCO=new MultiSiteLong();   //LDOREF_GNSS_ADC : TSEN_LDO_TRIM
//  public static MultiSiteLong LDOREF_FM_VCO=new MultiSiteLong();
//  public static MultiSiteLong LDOREF_ISM_ADDA=new MultiSiteLong(), H_LDOREF_ISM_VCO=new MultiSiteLong(), L_LDOREF_ISM_VCO=new MultiSiteLong();
//  public static MultiSiteLong  H_LDOREF_ISM_VCO_code=new MultiSiteLong(), L_LDOREF_ISM_VCO_code=new MultiSiteLong();//WCN Code
//  public static MultiSiteLong BT_TX_power_Efuse_code=new MultiSiteLong();
//  public static MultiSiteLong BT_TX_power_Efuse_code_before_efuse=new MultiSiteLong();
//  public static MultiSiteLong BT_POWER_CAL=new MultiSiteLong();
//  public static MultiSiteLong BT_POWER_CAL_before_efuse=new MultiSiteLong();
////  public static MultiSiteLong WLAN_TX_power_Efuse_code=new MultiSiteLong();
//
//  public static MultiSiteLong R_CAL=new MultiSiteLong();
//  public static MultiSiteLong SLOPE=new MultiSiteLong();
//
//  public static MultiSiteLong Intercept_Power_Det=new MultiSiteLong();
//
//  public static MultiSiteLong TSEN_LDO_TRIM=new MultiSiteLong();
//
//  // *************** Other *************
//  public static MultiSiteLong cp_mass=new MultiSiteLong();
//  public static MultiSiteLong efuse_mem_repair=new MultiSiteLong();
//
//  public static MultiSiteLong Disable_CSI_PHY=new MultiSiteLong();   //block92=new MultiSiteLong(),93 bit15
//  public static MultiSiteLong CA53_2Core=new MultiSiteLong();   //block92=new MultiSiteLong(),93 bit16
//
//  public static MultiSiteLong FLG=new MultiSiteLong();
//  public static MultiSiteLong W317_flag=new MultiSiteLong();   //add 20191122
//
//  // ***********************************
// public static Map<String, MultiSiteDouble>  B_Overflow= new HashMap<String,MultiSiteDouble>();// for 1.0v overflow flag
//
//
//
//  // *************** OLD *************
//  public static MultiSiteLong USB_BGCode=new MultiSiteLong(), USB_TermCode=new MultiSiteLong(), USB_12KCode=new MultiSiteLong();
//  public static MultiSiteLong THM_Ratio0_Sign=new MultiSiteLong(), THM_Ratio0_Code=new MultiSiteLong(), THM_Offset0_Code=new MultiSiteLong(), THM_Offset0R_Code=new MultiSiteLong(), THM_Offset1_Code=new MultiSiteLong(), THM_Offset2_Code=new MultiSiteLong(), THM_Offset3_Code=new MultiSiteLong(), THM_Offset4_Code=new MultiSiteLong();
//
//  public static MultiSiteLong BB_LDO=new MultiSiteLong(), USB_TFHSRES=new MultiSiteLong(), USB_TF12KRES=new MultiSiteLong(), USB_TUNEOTG=new MultiSiteLong();
//  public static MultiSiteLong SPEED=new MultiSiteLong(), SIDD_PRE=new MultiSiteLong(), COMP=new MultiSiteLong();
//
//  public static MultiSiteLong BUCKET=new MultiSiteLong(), LIT_BINNING=new MultiSiteLong(), BIG_BINNING=new MultiSiteLong(), DVFS_T=new MultiSiteLong(), VER_TYPE=new MultiSiteLong(), LIT_LEAK=new MultiSiteLong(), BIG_LEAK=new MultiSiteLong(), CORE_LEAK=new MultiSiteLong();
////  public static MultiSiteLong  vddarm_lv=new MultiSiteLong();
//  public static MultiSiteLong DSI_TX_CTL=new MultiSiteLong(), DSI_TRIM_BG=new MultiSiteLong(), CSI_RX_CTL=new MultiSiteLong();
//
//  public static MultiSiteLong DSI_M_BanGapCode=new MultiSiteLong(), DSI_M_ODTCode=new MultiSiteLong(), CSI0_ODTCode=new MultiSiteLong(); //mipi
//  public static MultiSiteLong DSI_S_BanGapCode=new MultiSiteLong(), DSI_S_ODTCode=new MultiSiteLong(), CSI1_ODTCode=new MultiSiteLong(); //mipi
//  public static MultiSiteLong CSI2_ODTCode=new MultiSiteLong(); //mipi
//
//  public static MultiSiteLong iDSI_M_BGTrim_PinA=new MultiSiteLong();
//  public static MultiSiteLong iDSI_M_BGTrim_PinB=new MultiSiteLong();
//  public static MultiSiteLong iDSI_M_BGTrim_PinC=new MultiSiteLong();
//  public static MultiSiteLong iDSI_M_BGTrim_PinD=new MultiSiteLong();
//
//  public static MultiSiteLong iDSI_M_ODTTrim_PinA=new MultiSiteLong();
//  public static MultiSiteLong iDSI_M_ODTTrim_PinB=new MultiSiteLong();
//  public static MultiSiteLong iDSI_M_ODTTrim_PinC=new MultiSiteLong();
//  public static MultiSiteLong iDSI_M_ODTTrim_PinD=new MultiSiteLong();
//
//  public static MultiSiteLong iCSI0_ODTTrim_PinA=new MultiSiteLong();
//  public static MultiSiteLong iCSI0_ODTTrim_PinB=new MultiSiteLong();
//  public static MultiSiteLong iCSI0_ODTTrim_PinC=new MultiSiteLong();
//  public static MultiSiteLong iCSI0_ODTTrim_PinD=new MultiSiteLong();
//
//  public static MultiSiteLong iDSI_S_BGTrim_PinA=new MultiSiteLong();
//  public static MultiSiteLong iDSI_S_BGTrim_PinB=new MultiSiteLong();
//  public static MultiSiteLong iDSI_S_BGTrim_PinC=new MultiSiteLong();
//  public static MultiSiteLong iDSI_S_BGTrim_PinD=new MultiSiteLong();
//
//  public static MultiSiteLong iDSI_S_ODTTrim_PinA=new MultiSiteLong();
//  public static MultiSiteLong iDSI_S_ODTTrim_PinB=new MultiSiteLong();
//  public static MultiSiteLong iDSI_S_ODTTrim_PinC=new MultiSiteLong();
//  public static MultiSiteLong iDSI_S_ODTTrim_PinD=new MultiSiteLong();
//
//  public static MultiSiteLong iCSI1_ODTTrim_PinA=new MultiSiteLong();
//  public static MultiSiteLong iCSI1_ODTTrim_PinB=new MultiSiteLong();
//  public static MultiSiteLong iCSI1_ODTTrim_PinC=new MultiSiteLong();
//  public static MultiSiteLong iCSI1_ODTTrim_PinD=new MultiSiteLong();
//
//  //MultiSiteLong LVDS_CalCode=new MultiSiteLong();
//  public static MultiSiteLong LVDSRF_TXIMP=new MultiSiteLong(), LVDSRF_RXIMP=new MultiSiteLong();
//
//  public static MultiSiteLong M_LVDSRF_RX0_OUTDATA=new MultiSiteLong(), S_LVDSRF_RX0_OUTDATA=new MultiSiteLong();
//
//  public static void intial_all_var()
//  {
//         LB_type="old";
//
//        R_Cal_code=new MultiSiteLong();
////        Leakage_VDDARM_C_temp=new MultiSiteDouble() ;
////        Leakage_VDDCORE_D_temp=new MultiSiteDouble() ;
//        MBIST_Repair_flag=new MultiSiteLong(0);//be careful
//        vddarm_lv=new MultiSiteLong(1);//be careful
//        LIT_FF_Pass=new MultiSiteLong(0);//be careful
//        LIT_LEAK_4=new MultiSiteLong(0);
//
//        LIT_LEAK_5=new MultiSiteLong(0);
//        LIT_LEAK_6=new MultiSiteLong(0);
//
//        LIT_SS_Pass=new MultiSiteLong(0);//be careful
//        LIT_TT_Pass=new MultiSiteLong(0);//be careful
//
//        BIG_FF_Pass=new MultiSiteLong(0);
//        BIG_SS_Pass=new MultiSiteLong(0);
//        BIG_TT_Pass=new MultiSiteLong(0);
//
//        Leakage_Pass=new MultiSiteLong(0);
//
//
//          Slope=new MultiSiteDouble() ;
//          Intercept=new MultiSiteDouble() ;
//         DdieEFUSE_BLOCK=new MultiSiteLong[96];    //DdieEFUSE_BLOCK[24]
//
//
//    // *************** UID *************
//          Y_loc_R=new MultiSiteLong() ;
//          X_loc_R=new MultiSiteLong();
//      Wafer_ID_R=new MultiSiteLong();
//      LotID0_R=new MultiSiteLong();
//      LotID1_R=new MultiSiteLong();
//      LotID2_R=new MultiSiteLong();
//      LotID3_R=new MultiSiteLong();
//      LotID4_R=new MultiSiteLong();
//      LotID5_R=new MultiSiteLong();
//    // *************** THM *************
//      T_ext=new MultiSiteDouble(0.0);
//
//      THM0_Ratio_Sign=new MultiSiteLong();
//      THM0_Ratio_Code=new MultiSiteLong();
//      THM0_Offset_Code=new MultiSiteLong();
//      THM1_Ratio_Sign=new MultiSiteLong();
//      THM1_Ratio_Code=new MultiSiteLong();
//      THM1_Offset_Code=new MultiSiteLong();
//
//    // *************** MIPI TRIM *************
//
//      CSI2P2_TRIMBG=new MultiSiteLong();
//      CSI2P2_RXRCTL=new MultiSiteLong();
//      DSI_TRIMBG=new MultiSiteLong();
//      DSI_TXRCTL=new MultiSiteLong();
//      CSI_4L_RXRCTL=new MultiSiteLong();
//
//
//      DSI_TRIMBG_bit0=new MultiSiteLong();
//      DSI_TRIMBG_bit1=new MultiSiteLong();
//      DSI_TRIMBG_bit2=new MultiSiteLong();
//      DSI_TRIMBG_bit3=new MultiSiteLong();
//
//      DSI_TXRCTL_bit0=new MultiSiteLong();
//      DSI_TXRCTL_bit1=new MultiSiteLong();
//      DSI_TXRCTL_bit2=new MultiSiteLong();
//      DSI_TXRCTL_bit3=new MultiSiteLong();
//
//      CSI2P2_TRIMBG_bit0=new MultiSiteLong();
//      CSI2P2_TRIMBG_bit1=new MultiSiteLong();
//      CSI2P2_TRIMBG_bit2=new MultiSiteLong();
//      CSI2P2_TRIMBG_bit3=new MultiSiteLong();
//
//      CSI2P2_RXRCTL_bit0=new MultiSiteLong();
//      CSI2P2_RXRCTL_bit1=new MultiSiteLong();
//      CSI2P2_RXRCTL_bit2=new MultiSiteLong();
//      CSI2P2_RXRCTL_bit3=new MultiSiteLong();
//
//      CSI_4L_RXRCTL_bit0=new MultiSiteLong();
//      CSI_4L_RXRCTL_bit1=new MultiSiteLong();
//      CSI_4L_RXRCTL_bit2=new MultiSiteLong();
//      CSI_4L_RXRCTL_bit3=new MultiSiteLong();
//
//    // *************** LVDSRF TRIM *************
////      LVDSRF_RX_VMode=new MultiSiteLong();
////      LVDSRF_RX_IMode=new MultiSiteLong();
//      LVDSRF_RX_IMP=new MultiSiteLong();
//      LVDSRF_TX_IMP=new MultiSiteLong();
////      LVDSRF_MIPI=new MultiSiteLong();
//
//      LVDSRF_RX_VMode_bit0=new MultiSiteLong();
//      LVDSRF_RX_VMode_bit1=new MultiSiteLong();
//      LVDSRF_RX_VMode_bit2=new MultiSiteLong();
//
//      LVDSRF_RX_CMode_bit0=new MultiSiteLong();
//      LVDSRF_RX_CMode_bit1=new MultiSiteLong();
//      LVDSRF_RX_CMode_bit2=new MultiSiteLong();
//
//      LVDSRF_MIPI_bit0=new MultiSiteLong();
//      LVDSRF_MIPI_bit1=new MultiSiteLong();
//      LVDSRF_MIPI_bit2=new MultiSiteLong();
//      LVDSRF_MIPI_bit3=new MultiSiteLong();
//
//    // *************** USB20 TRIM *************
//    //USB_BGCode=new MultiSiteLong(), USB_TermCode=new MultiSiteLong(), USB_12KCode=new MultiSiteLong();
//      USB20_TUNEOTG=new MultiSiteLong();
//      USB20_TFHSRES=new MultiSiteLong();
//      USB20_TFREGRES=new MultiSiteLong();
//
//      iUSB_BG_bit0=new MultiSiteLong();
//      iUSB_BG_bit1=new MultiSiteLong();
//      iUSB_BG_bit2=new MultiSiteLong();
//
//      iUSB_ODT_bit0=new MultiSiteLong();
//      iUSB_ODT_bit1=new MultiSiteLong();
//      iUSB_ODT_bit2=new MultiSiteLong();
//      iUSB_ODT_bit3=new MultiSiteLong();
//      iUSB_ODT_bit4=new MultiSiteLong();
//
//      iUSB_6K_bit0=new MultiSiteLong();
//      iUSB_6K_bit1=new MultiSiteLong();
//      iUSB_6K_bit2=new MultiSiteLong();
//      iUSB_6K_bit3=new MultiSiteLong();
//      iUSB_6K_bit4=new MultiSiteLong();
//      iUSB_6K_bit5=new MultiSiteLong();
//
//    // *************** BINNING TRIM *************
//      BINNING_RESLT=new MultiSiteLong();
//      CPU_OSC=new MultiSiteLong();
//      GPU_OSC=new MultiSiteLong();
//      PUB_OSC=new MultiSiteLong();
//      LIT_BIN=new MultiSiteLong();
//      GPU_BIN=new MultiSiteLong();
//
//
//    //leakage
//      VDDCORE_LEAK=new MultiSiteLong();
//      VDDARM_LEAK=new MultiSiteLong();
//      GPU_LEAK=new MultiSiteLong();
//
//      VDDCORE_GPU=new MultiSiteLong();
//      LIGHT_ARM_CORE=new MultiSiteLong();
//      DEEP_CORE=new MultiSiteLong();
//      VDDCORE_ALLON=new MultiSiteLong();
//      VDDARM_ALLON=new MultiSiteLong();
//
//      MP_ES=new MultiSiteLong();
//
//      Power_switch_leak=new MultiSiteLong();
//      FT_tem=new MultiSiteLong();
//
//    // *************** WCN_RF TRIM *************
//      K_APCOUT_bit11_5=new MultiSiteLong();
//      K_APCOUT_bit4_0=new MultiSiteLong();
//      B_APCOUT_bit9=new MultiSiteLong();
//      B_APCOUT_bit8_0=new MultiSiteLong();
//
//
//      LDOREF_GNSS_ADC=new MultiSiteLong();
//      LDOREF_GNSS_VCO=new MultiSiteLong();   //LDOREF_GNSS_ADC : TSEN_LDO_TRIM
//      LDOREF_FM_VCO=new MultiSiteLong();
//      LDOREF_ISM_ADDA=new MultiSiteLong();
//      H_LDOREF_ISM_VCO=new MultiSiteLong();
//      L_LDOREF_ISM_VCO=new MultiSiteLong();
//       H_LDOREF_ISM_VCO_code=new MultiSiteLong();
//       L_LDOREF_ISM_VCO_code=new MultiSiteLong();//WCN Code
//      BT_TX_power_Efuse_code=new MultiSiteLong();
//      BT_TX_power_Efuse_code_before_efuse=new MultiSiteLong();
//      BT_POWER_CAL=new MultiSiteLong();
//      BT_POWER_CAL_before_efuse=new MultiSiteLong();
//  //    WLAN_TX_power_Efuse_code=new MultiSiteLong();
//
//      R_CAL=new MultiSiteLong();
//      SLOPE=new MultiSiteLong();
//
//      Intercept_Power_Det=new MultiSiteLong();
//
//      TSEN_LDO_TRIM=new MultiSiteLong();
//
//    // *************** Other *************
//      cp_mass=new MultiSiteLong();
//      efuse_mem_repair=new MultiSiteLong();
//
//      Disable_CSI_PHY=new MultiSiteLong();   //block92=new MultiSiteLong(),93 bit15
//      CA53_2Core=new MultiSiteLong();   //block92=new MultiSiteLong(),93 bit16
//      W317_flag=new MultiSiteLong();   //b20191122
//      FLG=new MultiSiteLong();
//
//
//    // ***********************************
////     Map<String, MultiSiteDouble>  B_Overflow= new HashMap<String,MultiSiteDouble>();// for 1.0v overflow flag
//
//
//
//    // *************** OLD *************
//      USB_BGCode=new MultiSiteLong();
//      USB_TermCode=new MultiSiteLong();
//      USB_12KCode=new MultiSiteLong();
//      THM_Ratio0_Sign=new MultiSiteLong();
//      THM_Ratio0_Code=new MultiSiteLong();
//      THM_Offset0_Code=new MultiSiteLong();
//      THM_Offset0R_Code=new MultiSiteLong();
//      THM_Offset1_Code=new MultiSiteLong();
//      THM_Offset2_Code=new MultiSiteLong();
//      THM_Offset3_Code=new MultiSiteLong();
//      THM_Offset4_Code=new MultiSiteLong();
//
//      BB_LDO=new MultiSiteLong();
//      USB_TFHSRES=new MultiSiteLong();
//      USB_TF12KRES=new MultiSiteLong();
//      USB_TUNEOTG=new MultiSiteLong();
//      SPEED=new MultiSiteLong();
//      SIDD_PRE=new MultiSiteLong();
//      COMP=new MultiSiteLong();
//
//      BUCKET=new MultiSiteLong();
//      LIT_BINNING=new MultiSiteLong();
//      BIG_BINNING=new MultiSiteLong();
//      DVFS_T=new MultiSiteLong();
//      VER_TYPE=new MultiSiteLong();
//      LIT_LEAK=new MultiSiteLong();
//      BIG_LEAK=new MultiSiteLong();
//      CORE_LEAK=new MultiSiteLong();
//  //     vddarm_lv=new MultiSiteLong();
//      DSI_TX_CTL=new MultiSiteLong();
//      DSI_TRIM_BG=new MultiSiteLong();
//      CSI_RX_CTL=new MultiSiteLong();
//
//      DSI_M_BanGapCode=new MultiSiteLong();
//      DSI_M_ODTCode=new MultiSiteLong();
//      CSI0_ODTCode=new MultiSiteLong(); //mipi
//      DSI_S_BanGapCode=new MultiSiteLong();
//      DSI_S_ODTCode=new MultiSiteLong();
//      CSI1_ODTCode=new MultiSiteLong(); //mipi
//      CSI2_ODTCode=new MultiSiteLong(); //mipi
//
//      iDSI_M_BGTrim_PinA=new MultiSiteLong();
//      iDSI_M_BGTrim_PinB=new MultiSiteLong();
//      iDSI_M_BGTrim_PinC=new MultiSiteLong();
//      iDSI_M_BGTrim_PinD=new MultiSiteLong();
//
//      iDSI_M_ODTTrim_PinA=new MultiSiteLong();
//      iDSI_M_ODTTrim_PinB=new MultiSiteLong();
//      iDSI_M_ODTTrim_PinC=new MultiSiteLong();
//      iDSI_M_ODTTrim_PinD=new MultiSiteLong();
//
//      iCSI0_ODTTrim_PinA=new MultiSiteLong();
//      iCSI0_ODTTrim_PinB=new MultiSiteLong();
//      iCSI0_ODTTrim_PinC=new MultiSiteLong();
//      iCSI0_ODTTrim_PinD=new MultiSiteLong();
//
//      iDSI_S_BGTrim_PinA=new MultiSiteLong();
//      iDSI_S_BGTrim_PinB=new MultiSiteLong();
//      iDSI_S_BGTrim_PinC=new MultiSiteLong();
//      iDSI_S_BGTrim_PinD=new MultiSiteLong();
//
//      iDSI_S_ODTTrim_PinA=new MultiSiteLong();
//      iDSI_S_ODTTrim_PinB=new MultiSiteLong();
//      iDSI_S_ODTTrim_PinC=new MultiSiteLong();
//      iDSI_S_ODTTrim_PinD=new MultiSiteLong();
//
//      iCSI1_ODTTrim_PinA=new MultiSiteLong();
//      iCSI1_ODTTrim_PinB=new MultiSiteLong();
//      iCSI1_ODTTrim_PinC=new MultiSiteLong();
//      iCSI1_ODTTrim_PinD=new MultiSiteLong();
//
//    //LVDS_CalCode=new MultiSiteLong();
//      LVDSRF_TXIMP=new MultiSiteLong();
//      LVDSRF_RXIMP=new MultiSiteLong();
//
//      M_LVDSRF_RX0_OUTDATA=new MultiSiteLong();
//      S_LVDSRF_RX0_OUTDATA=new MultiSiteLong();
//  }
//


  // *************** OLD_END *************



  public static MultiSiteDouble DSPcalculateFrequency_byFFT(MultiSiteWaveLong aiCapWave,double sample_period_us) {
      /*
       *----------------------------------------------------------------------*
       * Routine: DSPcalculateFrequency_byFFT
       *
       * Purpose: Use FFT algorithm to calculate the frequncy.
       *
       *----------------------------------------------------------------------*
       */
      //        inline DOUBLE DSPcalculateFrequency_byFFT(ARRAY_I &aiCapWave, DOUBLE dSamplePeriod)
      //        {
      //      // performs interpolation of max bin in spectrum, according to Tabei/Ueda
      //      // will work on both digitally captured clock signals and analog signals
      //
      //      ARRAY_D adSpec ;
      //      INT iMinIndex , iMaxIndex ;
      //      double dMin ,dMax , dR , interpol_index , dFrequency ;
      //
      //      DSP_SPECTRUM ( aiCapWave , adSpec , VOLT , 1.0 , HANNING , 0 ) ;  // Tabei-Ueda algorithm needs power spectrum (no log!) with Hanning filter applied
      MultiSiteSpectrum adSpec = aiCapWave.setTermination(50).setWindowFunction(WindowFunction.HANNING).spectrum(SpectrumUnit.V);


      //
      //      ///////////////////////////////////////////////////////////////////
      //      //  Note: code modified to cancel out potential max bin at DC    //
      //      ///////////////////////////////////////////////////////////////////
      //      adSpec [ 0 ] = 0.0 ;
      //      adSpec [ 1 ] = 0.0 ;
      MultiSiteDouble attenValue = new MultiSiteDouble(1.0e-09);
      adSpec.setValue(0, attenValue);
      adSpec.setValue(1, attenValue);
      //adSpec.plot("spectrum");
      //
      //      DSP_MINMAX  ( adSpec , &dMin , &dMax , &iMinIndex , &iMaxIndex ) ;  // look for max bin
      //
      //
      MultiSiteLong max_index = adSpec.maxIndex();
      MultiSiteDouble dFrequency = new MultiSiteDouble();
      for(int site : adSpec.getActiveSites()) {
          //      // interpolating equations
          double dR;
          double interpol_index;
          Long max_index_plus_one = max_index.get(site)+1;
          double maxIdx_m1_val;

          if(max_index_plus_one<adSpec.getSize(site))
          {
              max_index_plus_one=max_index_plus_one+0;
          }
          else
          {
              max_index_plus_one=max_index_plus_one-1;
          }



          double maxIdx_p1_val = adSpec.get(site).getValue((int)(max_index_plus_one+1));
          double maxIdx_val = adSpec.get(site).getValue((int)(long)max_index_plus_one);

          if ( ( max_index.get(site) > 0 ) && ( adSpec.get(site).getValue(max_index.get(site).intValue()-1)  >  adSpec.get(site).getValue(max_index_plus_one.intValue()) ))
          {
            dR =  adSpec.get(site).getValue( max_index.get(site).intValue() - 1 ) /  adSpec.get(site).getValue( max_index.get(site).intValue() ) ;
            interpol_index = max_index.get(site).doubleValue() +( ( 1.0 - 2.0 * dR ) / ( 1.0 + dR )) ;
          }
          else
          {
            dR =  adSpec.get(site).getValue(max_index_plus_one.intValue()) /  adSpec.get(site).getValue( max_index.get(site).intValue()) ;
            interpol_index = max_index.get(site).intValue() - (( 1.0 - 2.0 * dR ) / ( 1.0 + dR )) ;
          }


          dFrequency.set(site, interpol_index /(sample_period_us * aiCapWave.getSize(site) )  );
//System.out.println("adSpec.getResolutionBandwidth(site) = "+ adSpec.getResolutionBandwidth(site));
//System.out.println("freq = "+ dFrequency.get(site));
//           println("adSpec.getResolutionBandwidth(site) = "+ adSpec.getResolutionBandwidth(site));
      }
      return dFrequency;
  }

  public static MultiSiteDouble DSPcalculateFrequency_byFFT_bf(MultiSiteWaveLong aiCapWave,double sample_period_us) {
      /*
       *----------------------------------------------------------------------*
       * Routine: DSPcalculateFrequency_byFFT
       *
       * Purpose: Use FFT algorithm to calculate the frequncy.
       *
       *----------------------------------------------------------------------*
       */
      //        inline DOUBLE DSPcalculateFrequency_byFFT(ARRAY_I &aiCapWave, DOUBLE dSamplePeriod)
      //        {
      //      // performs interpolation of max bin in spectrum, according to Tabei/Ueda
      //      // will work on both digitally captured clock signals and analog signals
      //
      //      ARRAY_D adSpec ;
      //      INT iMinIndex , iMaxIndex ;
      //      double dMin ,dMax , dR , interpol_index , dFrequency ;
      //
      //      DSP_SPECTRUM ( aiCapWave , adSpec , VOLT , 1.0 , HANNING , 0 ) ;  // Tabei-Ueda algorithm needs power spectrum (no log!) with Hanning filter applied
      MultiSiteSpectrum adSpec = aiCapWave.setTermination(50).setWindowFunction(WindowFunction.HANNING).spectrum(SpectrumUnit.V);


      //
      //      ///////////////////////////////////////////////////////////////////
      //      //  Note: code modified to cancel out potential max bin at DC    //
      //      ///////////////////////////////////////////////////////////////////
      //      adSpec [ 0 ] = 0.0 ;
      //      adSpec [ 1 ] = 0.0 ;
      MultiSiteDouble attenValue = new MultiSiteDouble(1.0e-09);
      adSpec.setValue(0, attenValue);
      adSpec.setValue(1, attenValue);
      //adSpec.plot("spectrum");
      //
      //      DSP_MINMAX  ( adSpec , &dMin , &dMax , &iMinIndex , &iMaxIndex ) ;  // look for max bin
      //
      //
      MultiSiteLong maxIdx = adSpec.maxIndex();
      MultiSiteDouble dFrequency = new MultiSiteDouble();
      for(int site : adSpec.getActiveSites()) {
          //      // interpolating equations
          double dR;
          Long interpol_index;
          Long iMaxIndex = maxIdx.get(site);
          double maxIdx_m1_val;
          if(iMaxIndex==0) {
              maxIdx_m1_val = adSpec.get(site).getValue((int)(long)(iMaxIndex));
          }
          else {
              maxIdx_m1_val = adSpec.get(site).getValue((int)(iMaxIndex-1));
          }
          double maxIdx_p1_val = adSpec.get(site).getValue((int)(iMaxIndex+1));
          double maxIdx_val = adSpec.get(site).getValue((int)(long)iMaxIndex);
          //      if ( ( iMaxIndex > 0 ) && ( adSpec [ iMaxIndex - 1 ] > adSpec [ iMaxIndex + 1 ] ) )
          if ( ( iMaxIndex > 0 ) && ( maxIdx_m1_val >maxIdx_p1_val ) )
          {
              //        dR = adSpec [ iMaxIndex - 1 ] / adSpec [ iMaxIndex ] ;
              dR = maxIdx_m1_val / maxIdx_val;
              //        interpol_index = iMaxIndex + ( 1.0 - 2.0 * dR ) / ( 1.0 + dR ) ;
              interpol_index = iMaxIndex  + (long) (( 1.0 - 2.0 * dR ) / ( 1.0 + dR ));
          }
          else
          {
              //        dR = adSpec [ iMaxIndex + 1 ] / adSpec [ iMaxIndex ] ;
              //        interpol_index = iMaxIndex - ( 1.0 - 2.0 * dR ) / ( 1.0 + dR ) ;
              dR = maxIdx_p1_val / maxIdx_val;
              interpol_index = iMaxIndex  - (long) (( 1.0 - 2.0 * dR ) / ( 1.0 + dR ));
          }
          //      dFrequency = interpol_index / ( dSamplePeriod * aiCapWave.size() ) ;
          //      return dFrequency ;
          //            dFrequency.set(site, (double)interpol_index * adSpec.getResolutionBandwidth(site) );
          dFrequency.set(site, (double)interpol_index /(sample_period_us * aiCapWave.getSize(site) )  );
System.out.println("adSpec.getResolutionBandwidth(site) = "+ adSpec.getResolutionBandwidth(site));
System.out.println("freq = "+ dFrequency.get(site));
//           println("adSpec.getResolutionBandwidth(site) = "+ adSpec.getResolutionBandwidth(site));
      }
      return dFrequency;
  }
//  public static void initial_EFUSE()
//  {
//      //Ddie Efuse Value ini ++++++++++++++++++++++
//      //block 92,93
//      cp_mass.set(0);
//      efuse_mem_repair.set(0);
//
//      Disable_CSI_PHY.set(0);
//      CA53_2Core.set(0);
//      W317_flag.set(0);//20191122
//      //block 90,91
//       FLG.set(0);
//
//      USB20_TUNEOTG.set(0);    //Trim BG
//      USB20_TFHSRES.set(0);    //Trim ODT
//      USB20_TFREGRES.set(0);    //Trim inter 6K
//
//      CSI2P2_TRIMBG.set(0);
//      CSI2P2_RXRCTL.set(0);     //CSI2P2_ODT
//      DSI_TRIMBG.set(0);
//      DSI_TXRCTL.set(0);     //DSI_ODT
//
//      //block 88,89
//      THM1_Offset_Code.set(0);
//      THM1_Ratio_Code.set(0);
//      THM1_Ratio_Sign.set(0);
//      THM0_Offset_Code.set(0);
//      THM0_Ratio_Code.set(0);
//      THM0_Ratio_Sign.set(0);
//
//      //block 86,87
//      K_APCOUT_bit11_5.set(0);
//      LDOREF_GNSS_ADC.set(0);   //TSEN_LDO_TRIM
//      LDOREF_GNSS_VCO.set(0);
//      LDOREF_FM_VCO.set(0);
//      LDOREF_ISM_ADDA.set(0);
//      H_LDOREF_ISM_VCO.set(0);
//      L_LDOREF_ISM_VCO.set(0);
//
//      //block 84,85
//      B_APCOUT_bit8_0.set(0);
//      SLOPE.set(0);
//      BT_POWER_CAL.set(0);
//      R_CAL.set(0);
//
//      //block 82,83
//      B_APCOUT_bit9.set(0);
//      CSI_4L_RXRCTL.set(0);     //CSI_4L_ODT / named CSI_2L_RXRCTL in efuse map ??
////      LVDSRF_RX_VMode.set(0);   //named LVDSRF_RX in efuse map  ??
////      LVDSRF_RX_IMode.set(0);   //named LVDSRF_TX in efuse map  ??
//      LVDSRF_RX_IMP=new MultiSiteLong();
////      LVDSRF_MIPI.set(0);       //LVDSRF_TX
//      LVDSRF_TX_IMP=new MultiSiteLong();
//      K_APCOUT_bit4_0.set(0);
//      Intercept_Power_Det.set(0);
//
//      //block 80,81
////      CPU_OSC.set(0);
////      GPU_OSC.set(0);
//      TSEN_LDO_TRIM.set(0);
//
//      //block 78,79
////      BINNING_RESLT.set(0);
////      VDDCORE_LEAK.set(0);
////      VDDARM_LEAK.set(0);
////      GPU_LEAK.set(0);
//      LIT_BINNING.set(0);
//      vddarm_lv.set(0);//change 0->1 because static var can not initial in testflow.
//
//      MP_ES.set(0);
//      GPU_BIN.set(0);
//    LIT_BIN.set(0);
//    VER_TYPE.set(0);
//
//      //block 76,77
//
////      PUB_OSC.set(0);
////      Power_switch_leak.set(0);
////      FT_tem.set(0);
//
//    VDDCORE_GPU.set(0);
//    LIGHT_ARM_CORE.set(0);
//    DEEP_CORE.set(0);
//    VDDCORE_ALLON.set(0);
//    VDDARM_ALLON.set(0);
//      for(int i = 0; i < 96; i++)
//      {
//
////          System.out.println("aa="+ DdieEFUSE_BLOCK[i]);
//          DdieEFUSE_BLOCK[i]= new MultiSiteLong(0);
//
//      }
//
//
//
//  }



//  public static void  EFUSE_CAL()
//  {
//      //Ddie Efuse value cal for each block : ref efuse bitmap: Efuse Map .xlsx
//  //  DdieEFUSE_BLOCK[93].set  (((cp_mass.and( 0x1)).leftShift( 31)).add
////                                    ((efuse_mem_repair.and( 0xFF)).leftShift( 23)));
//  //  DdieEFUSE_BLOCK[92].set  (((cp_mass.and( 0x1)).leftShift( 31)).add
////                                    ((efuse_mem_repair.and( 0xFF)).leftShift( 23)));
//
//      MultiSiteLong var= new MultiSiteLong();
//      var.set(1);
//
//
//      //write all
////      DdieEFUSE_BLOCK[12].set(0x1);
////      DdieEFUSE_BLOCK[13].set(0x0);
//////      for (int site : context.getActiveSites()) {
//////          for(int i=12;i<=13;i++)
//////          {
////              System.out.println("Ddie Efuse BLOCK Common "+" : "+(include.Common.DdieEFUSE_BLOCK[12]));
//////              println("Ddie Efuse BLOCK "+" : "+hexStr(include.Common.DdieEFUSE_BLOCK[i].get(site)));
//////              println("Ddie Efuse BLOCK "+" : "+(include.Common.DdieEFUSE_BLOCK[i].get(site)));
////////              println("Ddie Efuse BLOCK "+ i+" : "+hexStr(65536));
//////          }
//////      }
//
//          DdieEFUSE_BLOCK[93].set  (((Disable_CSI_PHY.and( 0x1) ).leftShift(15)).add
//                                          ((CA53_2Core.and( 0x1) ).leftShift(16)).add ((W317_flag.and( 0xf) ).leftShift(19)));
//          DdieEFUSE_BLOCK[92].set  (((Disable_CSI_PHY.and( 0x1) ).leftShift(15)).add
//                                          ((CA53_2Core.and( 0x1) ).leftShift(16)).add ((W317_flag.and( 0xf) ).leftShift(19)));
//
//          DdieEFUSE_BLOCK[91].set  (((var.and( 0x1)) .leftShift(30) ).add
//                                          ((USB20_TUNEOTG.and( 0x7) ).leftShift(27) ).add
//                                          ((USB20_TFREGRES.and( 0x3F) ).leftShift(21) ).add
//                                          ((USB20_TFHSRES.and( 0x1F) ).leftShift(16) ).add
//                                          ((CSI2P2_TRIMBG.and( 0xF) ).leftShift(12) ).add
//                                          ((CSI2P2_RXRCTL.and( 0xF) ).leftShift(8) ).add
//                                          ((DSI_TRIMBG.and( 0xF) ).leftShift(4) ).add
//                                          (DSI_TXRCTL.and( 0xF)));   //bit30 : FLG = 1
//          DdieEFUSE_BLOCK[90].set  (((var.and( 0x1)).leftShift(30) ).add
//                                          ((USB20_TUNEOTG.and( 0x7) ).leftShift(27) ).add
//                                          ((USB20_TFREGRES.and( 0x3F) ).leftShift(21) ).add
//                                          ((USB20_TFHSRES.and( 0x1F) ).leftShift(16) ).add
//                                          ((CSI2P2_TRIMBG.and( 0xF) ).leftShift(12) ).add
//                                          ((CSI2P2_RXRCTL.and( 0xF) ).leftShift(8) ).add
//                                          ((DSI_TRIMBG.and( 0xF) ).leftShift(4) ).add
//                                          (DSI_TXRCTL.and( 0xF)));
////
//          DdieEFUSE_BLOCK[89].set  (((THM1_Offset_Code.and( 0x7F) ).leftShift(24) ).add
//                                          ((THM1_Ratio_Code.and( 0x7F) ).leftShift(17) ).add
//                                          ((THM1_Ratio_Sign.and( 0x1) ).leftShift(16) ).add
//                                          ((THM0_Offset_Code.and( 0x7F) ).leftShift(9) ).add
//                                          ((THM0_Ratio_Code.and( 0x7F) ).leftShift(2) ).add
//                                          ((THM0_Ratio_Sign.and( 0x1) ).leftShift(1)));
//          DdieEFUSE_BLOCK[88].set  (((THM1_Offset_Code.and( 0x7F) ).leftShift(24) ).add
//                                          ((THM1_Ratio_Code.and( 0x7F) ).leftShift(17) ).add
//                                          ((THM1_Ratio_Sign.and( 0x1) ).leftShift(16) ).add
//                                          ((THM0_Offset_Code.and( 0x7F) ).leftShift(9) ).add
//                                          ((THM0_Ratio_Code.and( 0x7F) ).leftShift(2) ).add
//                                          ((THM0_Ratio_Sign.and( 0x1) ).leftShift(1)));
//
//
//          DdieEFUSE_BLOCK[87].set  (((K_APCOUT_bit11_5.and( 0x7F) ).leftShift(24) ).add
//                                          ((LDOREF_GNSS_ADC.and( 0xF) ).leftShift(20) ).add
//                                          ((LDOREF_GNSS_VCO.and( 0xF) ).leftShift(16) ).add
//                                          ((LDOREF_FM_VCO.and( 0xF) ).leftShift(12) ).add
//                                          ((LDOREF_ISM_ADDA.and( 0xF) ).leftShift(8) ).add
//                                          ((H_LDOREF_ISM_VCO.and( 0xF) ).leftShift(4) ).add
//                                          (L_LDOREF_ISM_VCO.and( 0xF)));
//          DdieEFUSE_BLOCK[86].set  (((K_APCOUT_bit11_5.and( 0x7F) ).leftShift(24) ).add
//                                          ((LDOREF_GNSS_ADC.and( 0xF) ).leftShift(20) ).add
//                                          ((LDOREF_GNSS_VCO.and( 0xF) ).leftShift(16) ).add
//                                          ((LDOREF_FM_VCO.and( 0xF) ).leftShift(12) ).add
//                                          ((LDOREF_ISM_ADDA.and( 0xF) ).leftShift(8) ).add
//                                          ((H_LDOREF_ISM_VCO.and( 0xF) ).leftShift(4) ).add
//                                          (L_LDOREF_ISM_VCO.and( 0xF)));
////
//          DdieEFUSE_BLOCK[85].set  (((B_APCOUT_bit8_0.and( 0x1FF) ).leftShift(22) ).add
//                                          ((SLOPE.and( 0x3FF) ).leftShift(12) ).add
//                                          ((BT_POWER_CAL.and( 0xFF) ).leftShift(4) ).add
//                                          (R_CAL.and( 0xF)));
////      System.out.println("SLOPE com= "+ SLOPE);
////      System.out.println("BT_POWER_CAL com= "+ BT_POWER_CAL);
//          DdieEFUSE_BLOCK[84].set  (((B_APCOUT_bit8_0.and( 0x1FF) ).leftShift(22) ).add
//                                          ((SLOPE.and( 0x3FF) ).leftShift(12) ).add
//                                          ((BT_POWER_CAL.and( 0xFF) ).leftShift(4) ).add
//                                          (R_CAL.and( 0xF)));
//////
////
//        // TODO: name not the same: LVDSRF_RX_VMode/LVDSRF_RX_IMode/LVDSRF_RX_IMP
//        // DdieEFUSE_BLOCK[83].set (((B_APCOUT_bit9.and( 0x1) ).leftShift(30) ).add
//        // ((CSI_4L_RXRCTL.and( 0xF) ).leftShift(26) ).add
//        // ((LVDSRF_RX_VMode.and( 0x7) ).leftShift(23) ).add
//        // ((LVDSRF_RX_IMode.and( 0x7) ).leftShift(20) ).add
//        // ((LVDSRF_MIPI.and( 0xF) ).leftShift(16) ).add
//        // ((K_APCOUT_bit4_0.and( 0x1F) ).leftShift(11)));
//        // DdieEFUSE_BLOCK[82].set (((B_APCOUT_bit9.and( 0x1) ).leftShift(30) ).add
//        // ((CSI_4L_RXRCTL.and( 0xF) ).leftShift(26) ).add
//        // ((LVDSRF_RX_VMode.and( 0x7) ).leftShift(23) ).add
//        // ((LVDSRF_RX_IMode.and( 0x7) ).leftShift(20) ).add
//        // ((LVDSRF_MIPI.and( 0xF) ).leftShift(16) ).add
//        // ((K_APCOUT_bit4_0.and( 0x1F) ).leftShift(11)));
//        // TODO: Rewite this block
//
//          DdieEFUSE_BLOCK[81].set  (TSEN_LDO_TRIM.and( 0xF));
//          DdieEFUSE_BLOCK[80].set  (TSEN_LDO_TRIM.and( 0xF));
//
//
//          DdieEFUSE_BLOCK[79].set  (((vddarm_lv.and( 0x1) ).leftShift(29) ).add
//                  ((LIT_BINNING.and( 0xF) ).leftShift(8) ));
//          DdieEFUSE_BLOCK[78].set  (((vddarm_lv.and( 0x1) ).leftShift(29) ).add
//                  ((LIT_BINNING.and( 0xF) ).leftShift(8) ));
//
//
//
//
//}
//  public static void  EFUSE_CAL_VCAL_RCAL()
//  {
//      //Ddie Efuse value cal for each block : ref efuse bitmap: Efuse Map .xlsx
//  //  DdieEFUSE_BLOCK[93].set  (((cp_mass.and( 0x1)).leftShift( 31)).add
////                                    ((efuse_mem_repair.and( 0xFF)).leftShift( 23)));
//  //  DdieEFUSE_BLOCK[92].set  (((cp_mass.and( 0x1)).leftShift( 31)).add
////                                    ((efuse_mem_repair.and( 0xFF)).leftShift( 23)));
//
//      MultiSiteLong var= new MultiSiteLong();
//      var.set(1);
//
//
//      //write all
//
//          DdieEFUSE_BLOCK[93].set  (((Disable_CSI_PHY.and( 0x1) ).leftShift(15)).add
//                                          ((CA53_2Core.and( 0x1) ).leftShift(16)));
//          DdieEFUSE_BLOCK[92].set  (((Disable_CSI_PHY.and( 0x1) ).leftShift(15)).add
//                                          ((CA53_2Core.and( 0x1) ).leftShift(16)));
//
//
//
//
//  }
  public static  IDeviceSetup connectDigIO(IDeviceSetup ds,String digIoSignals){

      ISetupDigInOut digIO = ds.addDigInOut(digIoSignals);
      digIO.setKeepAlive(false);
      digIO.setDisconnect(false);
      digIO.setDisconnectMode(ISetupDigInOut.SetupDisconnectMode.hiz);

      ISetupWavetable waveTable = digIO.addWavetable("connectWave", 1);
      waveTable.addStateCharDescription('0', ISetupWavetable.DriveAction.F0Z);

      ITiming timing = digIO.timing("connectTiming");
      timing.setPeriod(50E-9); //50 ns
      timing.setD1(0); //0 ns

      ILevel level = digIO.level("connectLevel");
      level.setVil(0); //0 V
      level.setVih(1.0); //0 V
      level.setTerm(ISetupDigInOut.ILevel.SetupTerm.hiz);


      return ds;
  }


  public static  IDeviceSetup connectDcVI(IDeviceSetup ds, String signal, double clampCurrent,double Vrange,double Vforce){
      ISetupDcVI dcVi = ds.addDcVI(signal);
      dcVi.level().setVrange(Vrange); //1 V --> avoid power budget issues
      dcVi.level().setVforce(Vforce); //0 V

      dcVi.level().setIrange(clampCurrent);
      dcVi.level().setIclamp(clampCurrent);
      dcVi.level().setUngangMode(ISetupDcVI.ILevel.SetupUngangMode.fitClampValue);

      dcVi.level().setWaitTime(10E-3); //10 ms

      dcVi.setDisconnect(false);
      dcVi.setDisconnectMode(ISetupDcVI.SetupDisconnectMode.loz);
      return ds;
  }

  public static  IDeviceSetup  disconnectDigIO(IDeviceSetup ds,String digIoSignals){
      ISetupDigInOut digIO = ds.addDigInOut(digIoSignals);
      digIO.setKeepAlive(false);
      digIO.setConnect(false);
      digIO.setDisconnect(true);
      digIO.setDisconnectMode(ISetupDigInOut.SetupDisconnectMode.hiz);

      ISetupWavetable waveTable = digIO.addWavetable("disconnectWave", 1);
      waveTable.addStateCharDescription('0', ISetupWavetable.DriveAction.F0Z);

      ITiming timing = digIO.timing("disconnectTiming");
      timing.setPeriod(50E-9); //50 ns
      timing.setD1(0); //0 ns

      ILevel level = digIO.level("disconnectLevel");
      level.setVil(0); //0 V
      level.setVih(0); //0 V
      level.setTerm(ISetupDigInOut.ILevel.SetupTerm.hiz);
      return ds;
  }


  public static  IDeviceSetup disconnectDcVI(IDeviceSetup ds, String signal, double clampCurrent){
      ISetupDcVI dcVi = ds.addDcVI(signal);
      dcVi.level().setVrange(1); //1 V --> avoid power budget issues
      dcVi.level().setVforce(0); //0 V

      dcVi.level().setIrange(clampCurrent);
      dcVi.level().setIclamp(clampCurrent);
      dcVi.level().setUngangMode(ISetupDcVI.ILevel.SetupUngangMode.fitClampValue);

      dcVi.level().setWaitTime(10E-3); //10 ms

      dcVi.setDisconnect(true);
      dcVi.setDisconnectMode(ISetupDcVI.SetupDisconnectMode.loz);
      return ds;
  }
}
