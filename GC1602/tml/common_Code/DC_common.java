package common_Code;

import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDcVI;
import xoc.dsa.ISetupDcVI.IImeas;
import xoc.dsa.ISetupDcVI.IImeas.SetupUngangMode;
import xoc.dsa.ISetupDcVI.IVforce;
import xoc.dsa.ISetupDcVI.SetupConnectMode;
import xoc.dsa.ISetupDigInOut;





public class DC_common {

    public   static IDeviceSetup DC_Vf_dyn (IDeviceSetup dvc_stp, String pinName, double force_V,double clamp_mA,double vRange,double iRange)
    {
        iRange= clamp_mA;

        if ((force_V+0.5)<2.5)
        {
            vRange=2.5;
        }
        else if (force_V<4)
        {
            vRange=4;
        }
        else
        {
            vRange=7;
        }
        dvc_stp.addDcVI(pinName).setConnect(true).level().setVforce(force_V).setWaitTime(0).setIrange(iRange);//.setIclamp(clamp_mA)
        //       dvc_stp.addDcVI(pinName).setKeepAlive(false);
        //dvc_stp.addDcVI(pinName).vforce().setForceValue(force_V).setIrange(iRange).setIclamp(clamp_mA);


        return dvc_stp;
    }


    public   static IDeviceSetup DC_Vf_dyn_keep (IDeviceSetup dvc_stp, String pinName, double force_V,double clamp_mA,double vRange,double iRange,String connect_on,String keep_on)
    {
        iRange= clamp_mA;

        if ((force_V+0.5)<2.5)
        {
            vRange=2.5;
        }
        else if (force_V<4)
        {
            vRange=4;
        }
        else
        {
            vRange=7;
        }
        dvc_stp.addDcVI(pinName).setConnect("true").setKeepAlive(false).vforce().setForceValue(force_V).setIclamp(clamp_mA); //setKeepAlive("false")




        return dvc_stp;
    }


    public   static IDeviceSetup DC_Vf_fix (IDeviceSetup dvc_stp, String pinName, double force_V)
    {

//*************************************************************************************************************
      //  IVforceImeas Vf_set = dvc_stp.addDcVI(pinName).vforceImeas(pinName+"imeas_fix");
      //  Vf_set.setForceValue(force_V).setIrange(40);
        IVforce Vf_set = dvc_stp.addDcVI(pinName).vforce(pinName+"imeas_fix");
        Vf_set.setForceValue(force_V).setIrange(40);
        IImeas Im_set = dvc_stp.addDcVI(pinName).imeas(pinName+"imeas_fix");
//*************************************************************************************************************


        return dvc_stp;
    }

    public   static IDeviceSetup DC_Vf_off (IDeviceSetup dvc_stp, String pinName, double force_V,double clamp_mA,double Discharge_time_ms)
    {



        //        IVforceImeas Vf_set = dvc_stp.addDcVI(pinName).vforceImeas(pinName+"imeas_off");
        //        Vf_set.setForceValue(0).setWaitTime(Discharge_time_ms).setIrange(clamp_mA);

        dvc_stp.addDcVI(pinName).setConnect(false).level().setVforce(0).setWaitTime(1e-3);


        return dvc_stp;
    }

    public   static IDeviceSetup PMU_FIX (IDeviceSetup dvc_stp, String pinName, double force_V,double clamp_mA,double Discharge_time_ms)
    {

        dvc_stp.addDcVI(pinName).setConnect(true).level().setVforce(force_V).setIrange(clamp_mA);
        // dvc_stp.addDigInOut(pinName).setConnect(true).setKeepAlive(false).vforceImeas().setForceValue(force_V).setIrange(clamp_mA);

        //       dvc_stp.addDcVI(pinName).vforceImeas(pinName+"PMU_FIX");





        return dvc_stp;
    }

    public   static IDeviceSetup PMU_FIX_Var (IDeviceSetup dvc_stp, String pinName, String force_V,double clamp_mA,double Discharge_time_ms)
    {

        dvc_stp.addDcVI(pinName).setConnect(true).level().setVforce(force_V).setIrange(clamp_mA);
        // dvc_stp.addDigInOut(pinName).setConnect(true).setKeepAlive(false).vforceImeas().setForceValue(force_V).setIrange(clamp_mA);

        //       dvc_stp.addDcVI(pinName).vforceImeas(pinName+"PMU_FIX");





        return dvc_stp;
    }

    public   static IDeviceSetup PMU_OFF (IDeviceSetup dvc_stp, String pinName, double force_V,double clamp_mA,double Discharge_time_ms)
    {

        dvc_stp.addDcVI(pinName).setConnect(true).setKeepAlive(false).vforce().setForceValue(0).setIrange(clamp_mA);
        // dvc_stp.addDigInOut(pinName).setConnect(true).setKeepAlive(false).vforceImeas().setForceValue(force_V).setIrange(clamp_mA);

        //       dvc_stp.addDcVI(pinName).vforceImeas(pinName+"PMU_FIX");





        return dvc_stp;
    }

    //**********************add by chunyan***************************
    public   static IDeviceSetup PMU_vForce_relayon (IDeviceSetup dvc_stp, String pinName,String action_name, double force_V,double clamp_mA,double Discharge_time_ms)
    {

        ISetupDcVI bb= dvc_stp.addDcVI(pinName);
        bb.setConnect(true).level().setVforce(0).setIrange(0.04);

        ISetupDcVI.IVforce aa =bb.vforce(action_name).setForceValue(5.0).setIrange(0.04);

        return dvc_stp;
    }

    public   static IDeviceSetup PMU_vForce_relayoff (IDeviceSetup dvc_stp, String pinName, double force_V,double clamp_mA,double Discharge_time_ms)
    {

        dvc_stp.addDcVI(pinName).setConnect(true).level().setVforce(0).setIrange(clamp_mA);


        return dvc_stp;
    }

    public   static IDeviceSetup DCVI_IMeas (IDeviceSetup dvc_stp, String pinName1,String action_name,double Irange,double Iclamp,double wait_time,boolean unganed,long samples)
    {
        ISetupDcVI aa=dvc_stp.addDcVI(pinName1);
        if(unganed)
        {

        ISetupDcVI.IImeas imeas = aa.imeas(action_name).setWaitTime(wait_time).setAverages(samples).setIrange(Irange).setUngangMode(SetupUngangMode.fast).setRestoreIrange(true);//.setDownRanging(true) not support
        }
        else
        {
            ISetupDcVI.IImeas imeas = aa.imeas(action_name).setWaitTime(wait_time).setAverages(samples).setIrange(Irange).setRestoreIrange(true);//.setDownRanging(true) not support
        }
        //        ISetupDcVI.IIclamp iclamp = aa.iclamp(action_name).setIclamp(Iclamp);
        return dvc_stp;
    }
    public   static IDeviceSetup DCVI_VMeas_HZ (IDeviceSetup dvc_stp, String pinName1,String action_name,double wait_time,double min,double max)
    {
        ISetupDcVI aa=dvc_stp.addDcVI(pinName1).setDisconnect(true).setConnectMode(SetupConnectMode.highImpedance);
        ISetupDcVI.IVmeas vmeas = aa.vmeas(action_name).setHighAccuracy(true).setWaitTime(wait_time).setAverages(32);
        ISetupDcVI.IVmeas.ILimits bb=vmeas.limits();
        bb.setLow(min).setHigh(max);
        return dvc_stp;
    }

    public   static IDeviceSetup DCVI_Iforce (IDeviceSetup dvc_stp, String pinName1,String action_name,double iforce)
    {
        ISetupDcVI aa=dvc_stp.addDcVI(pinName1);
        ISetupDcVI.IIforce Iforce= aa.iforce(action_name).setForceValue(iforce);

        return dvc_stp;
    }

    public   static IDeviceSetup DigInOut_IfVm (IDeviceSetup dvc_stp, String pinName1,String action_name,double iforce,double Irange,double wait_time,long samples,double vrange,double exepecd_V,double vclamplow,double vclamphigh,double limit_L,double limit_H)
    {
        ISetupDigInOut.IIforceVmeas Vmeas=dvc_stp.addDigInOut(pinName1).iforceVmeas(action_name);
        Vmeas.setForceValue(iforce).setIrange(Irange).setWaitTime(wait_time).setAverages(samples).setTerminated(false).setVrange(vrange).setVexpected(exepecd_V)
        .setVclampLow(vclamplow).setVclampHigh(vclamphigh).setHighAccuracy(true).limits().setLow(limit_L).setHigh(limit_H);//.setDownRanging(true) not support

        return dvc_stp;
    }
    public   static IDeviceSetup DigInOut_IfVm_ppmu (IDeviceSetup dvc_stp, String pinName1,String action_name,double iforce,double Irange,double wait_time,long samples,double vrange,double exepecd_V,double vclamplow,double vclamphigh,double limit_L,double limit_H)
    {
        ISetupDigInOut.IIforceVmeas Vmeas=dvc_stp.addDigInOut(pinName1).iforceVmeas(action_name);
        Vmeas.setForceValue(iforce).setIrange(Irange).setWaitTime(wait_time).setTerminated(false).setVrange(vrange).setVexpected(exepecd_V)
        .setVclampLow(vclamplow).setVclampHigh(vclamphigh).setHighAccuracy(false).limits().setLow(limit_L).setHigh(limit_H);//.setDownRanging(true) not support

        return dvc_stp;
    }
}

