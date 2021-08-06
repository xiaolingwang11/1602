package tmlCommon;

import xoc.dsa.IDeviceSetup;
import xoc.dta.measurement.IMeasurement;

public class Relay {


//    public static IDeviceSetup Relay_act_stp(IDeviceSetup devSetup, String On_pin_list,
//            String Off_pin_list, String relay_act_name) {
//        if (!On_pin_list.isEmpty()) {
//            devSetup.addUtility(On_pin_list).setState(relay_act_name).setValue(1);
//
//        }
//
//        if (!Off_pin_list.isEmpty()) {
//            devSetup.addUtility(Off_pin_list).setState(relay_act_name).setValue(0);
//
//        }
//
//        return devSetup;
//    }


    public static IDeviceSetup setState(IDeviceSetup devSetup, String On_pin_list,
            String Off_pin_list) {
        if (!On_pin_list.isEmpty()) {
            devSetup.addUtility(On_pin_list).setValue(1);

        }

        if (!Off_pin_list.isEmpty()) {
            devSetup.addUtility(Off_pin_list).setValue(0);

        }

        return devSetup;
    }

    public static IDeviceSetup setOn(IDeviceSetup devSetup, String On_pin_list) {
        if (!On_pin_list.isEmpty()) {
            devSetup.addUtility(On_pin_list).setValue(1);

        }

        return devSetup;
    }

    public static IDeviceSetup setOff(IDeviceSetup devSetup,
            String Off_pin_list) {

        if (!Off_pin_list.isEmpty()) {
            devSetup.addUtility(Off_pin_list).setValue(0);

        }

        return devSetup;
    }

    public static void setState(IMeasurement measurement, String On_pin_list,
            String Off_pin_list) {
        if (!On_pin_list.isEmpty()) {
            measurement.utility(On_pin_list).setValue(1);

        }

        if (!Off_pin_list.isEmpty()) {
            measurement.utility(Off_pin_list).setValue(0);

        }

//        return devSetup;
    }

    public static void setOn(IMeasurement measurement, String On_pin_list) {
        if (!On_pin_list.isEmpty()) {
            measurement.utility(On_pin_list).setValue(1);

        }

//        return devSetup;
    }

    public static void setOff(IMeasurement measurement,
            String Off_pin_list) {

        if (!Off_pin_list.isEmpty()) {
            measurement.utility(Off_pin_list).setValue(0);

        }

//        return devSetup;
    }

}
