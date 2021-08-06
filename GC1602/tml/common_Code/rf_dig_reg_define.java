package common_Code;

import xoc.dta.datatypes.MultiSiteDouble;

public class rf_dig_reg_define {

    //    public  static ISetupTransactionSeqDef Register_Init (ISetupTransactionSeqDef protocol_Check,ISetupProtocolInterface spi, String capture_results,String mStandard,String mTX_RX,double spiPeriod)
    //    {
    public static  MultiSiteDouble pathloss= new MultiSiteDouble(19.0);
//    public static MultiSiteLong R_Cal_code=new MultiSiteLong();

    public static final long   RF_CTRL_REG_BASE= 0x00000000;
    public static final long   REG_CAL_R_CTRL  =((RF_CTRL_REG_BASE + 0x0000));


}
