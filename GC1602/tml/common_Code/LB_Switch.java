package common_Code;

import java.util.HashMap;
import java.util.Map;

public class LB_Switch {

    public static String Mode;
    public  static final String  dgtSigNameI_key="dgtI";
    public static  final String dgtSigNameQ_key="dgtQ";
    public static  final String awgSigNameI_key="awgI";
    public static  final String awgSigNameQ_key="awgQ";
    public static  final String rfStim_key="rfstim";
    public static  final String rfMeas_key="rfmeas";

//    private static final double Path_Loss=1.0;
    public enum MODE {
        old_LB,
        new_LB
    }
//  public static List<String> LB_Switch_APC( String tsName){
    public static MODE LBMode = MODE.old_LB; //default: dps and digIO
//    public static MODE LBMode = MODE.old_LB; //default: dps and digIO

        public static Map<String, String>  LB_Switch_APC( String tsName){


//        List<String> pinlist=new ArrayList<String>() ;
        Map<String, String> pin_map = new HashMap<String, String>();

        String dgtSigNameI="";
        String dgtSigNameQ="";
        String awgSigNameI="";
        String awgSigNameQ="";
        String rfStim="";
        String rfMeas="";
        switch(LBMode){
        case old_LB:{
            if(tsName.equals("APCOUT0_RAMP_syc"))
            {

                dgtSigNameI="APCOUT_old";//APCOUT01
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
            }
            if(tsName.equals("APCOUT0_RAMP"))
            {

                dgtSigNameI="APCOUT_old";//APCOUT01
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
            }
            if(tsName.equals("APCOUT0_DC"))
            {
                dgtSigNameI="APCOUT_old";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);

            }
            if(tsName.equals("APCOUT1_DC"))
            {

                dgtSigNameI="APCOUT_old";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
            }

            if(tsName.equals("ISM_DAC"))
            {

                dgtSigNameI="WCN_RXIP_old";
                dgtSigNameQ="WCN_RXQP_old";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
                pin_map.put(dgtSigNameQ_key, dgtSigNameQ);
            }
            if(tsName.contains("FM_ADC")||tsName.contains("ISM_ADC")||tsName.contains("RSSI_ADC")||tsName.contains("GNSS_ADC"))
            {

                awgSigNameI="WCN_TXIP_old";
                awgSigNameQ="WCN_TXQP_old";
                pin_map.put(awgSigNameI_key, awgSigNameI);
                pin_map.put(awgSigNameQ_key, awgSigNameQ);
            }
            if(tsName.contains("WLAN_TX")||tsName.contains("BT_TX"))
            {
                awgSigNameI="WCN_TXIP_old";
                awgSigNameQ="WCN_TXQP_old";
                rfMeas  = "WF_BT_TX_old";
                pin_map.put(awgSigNameI_key, awgSigNameI);
                pin_map.put(awgSigNameQ_key, awgSigNameQ);
                pin_map.put(rfMeas_key, rfMeas);

            }
            if(tsName.contains("WLAN_RX")||tsName.contains("BT_RX"))
            {
                dgtSigNameI="WCN_RXIP_old";
                dgtSigNameQ="WCN_RXQP_old";
                rfStim = "WF_BT_RX_old";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
                pin_map.put(dgtSigNameQ_key, dgtSigNameQ);
                pin_map.put(rfStim_key, rfStim);

            }
            if(tsName.contains("FM_RX"))
            {
                dgtSigNameI="WCN_RXIP_old";
                dgtSigNameQ="WCN_RXQP_old";
                rfStim = "FM_RX_old";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
                pin_map.put(dgtSigNameQ_key, dgtSigNameQ);
                pin_map.put(rfStim_key, rfStim);

            }
            if(tsName.contains("RX_GPS_BDS_Max_Gain"))
            {

                dgtSigNameI="WCN_RXIP_old";
                dgtSigNameQ="WCN_RXQP_old";
                rfStim = "GPS_RX_old";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
                pin_map.put(dgtSigNameQ_key, dgtSigNameQ);
                pin_map.put(rfStim_key, rfStim);

            }


//            pinlist.add(dgtSigNameI);
            break;
        }
        case new_LB:{
            if(tsName.equals("APCOUT0_RAMP_syc"))
            {

                dgtSigNameI="APCOUT0_new";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
            }
            if(tsName.equals("APCOUT0_RAMP"))
            {

                dgtSigNameI="APCOUT0_new";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
            }
            if(tsName.equals("APCOUT0_DC"))
            {
                dgtSigNameI="APCOUT0_new";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);

            }
            if(tsName.equals("APCOUT1_DC"))
            {

                dgtSigNameI="APCOUT1_new";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
            }
            if(tsName.equals("ISM_DAC"))
            {

                dgtSigNameI="WCN_RXIP_DAC_new";
                dgtSigNameQ="WCN_RXQP_DAC_new";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
                pin_map.put(dgtSigNameQ_key, dgtSigNameQ);
            }
            if(tsName.contains("FM_ADC")||tsName.contains("ISM_ADC")||tsName.contains("RSSI_ADC")||tsName.contains("GNSS_ADC"))
            {

                awgSigNameI="WCN_TXIP_new";
                awgSigNameQ="WCN_TXQP_new";
                pin_map.put(awgSigNameI_key, awgSigNameI);
                pin_map.put(awgSigNameQ_key, awgSigNameQ);
            }
            if(tsName.contains("WLAN_TX")||tsName.contains("BT_TX"))
            {
                awgSigNameI="WCN_TXIP_new";
                awgSigNameQ="WCN_TXQP_new";
                rfMeas  = "WF_BT_TXRX_new";
                pin_map.put(awgSigNameI_key, awgSigNameI);
                pin_map.put(awgSigNameQ_key, awgSigNameQ);
                pin_map.put(rfMeas_key, rfMeas);

            }
            if(tsName.contains("WLAN_RX")||tsName.contains("BT_RX"))
            {
                dgtSigNameI="WCN_RXIP_new";
                dgtSigNameQ="WCN_RXQP_new";
                rfStim = "WF_BT_TXRX_new";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
                pin_map.put(dgtSigNameQ_key, dgtSigNameQ);
                pin_map.put(rfStim_key, rfStim);
            }
            if(tsName.contains("FM_RX"))
            {
                dgtSigNameI="WCN_RXIP_new";
                dgtSigNameQ="WCN_RXQP_new";
                rfStim = "FM_RX_LANT_new";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
                pin_map.put(dgtSigNameQ_key, dgtSigNameQ);
                pin_map.put(rfStim_key, rfStim);

            }
            if(tsName.contains("RX_GPS_BDS_Max_Gain"))
            {
                dgtSigNameI="WCN_RXIP_new";
                dgtSigNameQ="WCN_RXQP_new";
                rfStim = "GPS_RX_new";
                pin_map.put(dgtSigNameI_key, dgtSigNameI);
                pin_map.put(dgtSigNameQ_key, dgtSigNameQ);
                pin_map.put(rfStim_key, rfStim);

            }


//            pinlist.add(dgtSigNameI);
            break;
        }
        default:
            throw new RuntimeException("Unexpected LB Switch Mode!");
        }
        return pin_map;
    }


}
