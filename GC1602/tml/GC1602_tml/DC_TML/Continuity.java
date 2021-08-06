package GC1602_tml.DC_TML;

import java.util.Arrays;
import java.util.Map;

import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDigInOut;
import xoc.dta.ParameterGroupCollection;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;

public class Continuity extends TMLBase {

    public ParameterGroupCollection<TST_NAME_ParaGroup> contCollection = new ParameterGroupCollection<>();

    @In
    public String TestPinsNeg = "gCONT";
    @In
    public String TestPinsPos = "gCONT";

//    public IMeasurement measurementDisconnect;
    public IMeasurement measurement_Pos;
    public IMeasurement measurement_Neg;

    // disconnect_pins = allDio - gCONT
    public static final String disconnect_pins = "EMZQ+TEMP_CS+TEMP_DIN+TEMP_DOUT+TEMP_SCLK+AWG_PU_IQ+AWG_VOCM_IQ+DAC_CM_IQ+DGT_PU_IQ+RX_VOCM_IQ+APCOUT0+APCOUT1+SCL+SDA+RF_Source_32417+RF_Source_32317+RF_Source_31913+RF_Source_32013+RF_Source_31917+RF_Source_32017+CT1_M1+CT2_M1+KK17+KK18+KK19+KK20"; //

    private static final String groupDef = "GC1602_configuration.Groups";

    @Override
    public void setup() {

        try {
        // Common.disconnectDigIO(ds_disconnect, disconnect_pins); // TODO
//        IDeviceSetup ds_disconnect = newDevSetup(importSpec);
//        ds_disconnect.importSpec(groupDef);
//        ds_disconnect.addDigInOut(disconnect_pins).setConnect(false).setDisconnect(true);
//        measurementDisconnect.digInOut(disconnect_pins).setConnect(false).setDisconnect(true);
//        measurementDisconnect.setSetups(ds_disconnect);
        }catch(Exception e) {
            e.printStackTrace();
        }
        //
        //
        //

        double waitTime = 2 * ms;

        IDeviceSetup devSetup_Pos = newDevSetup(importSpec);
        IDeviceSetup devSetup_Neg = newDevSetup(importSpec);
        devSetup_Pos.importSpec(groupDef);
        devSetup_Neg.importSpec(groupDef);
        ISetupDigInOut.IIforceVmeas posVmeasurePPMU;
        ISetupDigInOut.IIforceVmeas negVmeasurePPMU;

        posVmeasurePPMU = devSetup_Pos.addDigInOut(TestPinsPos).iforceVmeas("posVmeasurePPMU");
        posVmeasurePPMU.setForceValue("100 uA").setIrange("100 uA").setWaitTime(waitTime)
                .setVexpected("1 V").setVclampHigh("1.5 V").setVclampLow("0 V"); // .setKeepVclamp(SetupKeepVclamp.enabled)

        negVmeasurePPMU = devSetup_Neg.addDigInOut(TestPinsNeg).iforceVmeas("negVmeasurePPMU");
        negVmeasurePPMU.setForceValue("-100 uA").setIrange("100 uA").setWaitTime(waitTime)
                .setVexpected("-1 V").setVclampHigh("-0.0 V").setVclampLow("-1.5 V"); // .setKeepVclamp(SetupKeepVclamp.enabled)

        devSetup_Pos.sequentialBegin();
        devSetup_Pos.waitCall("2 ms"); // wait for connection settle
        devSetup_Pos.actionCall(posVmeasurePPMU);
        devSetup_Pos.sequentialEnd();
        measurement_Pos.setSetups(devSetup_Pos);

        devSetup_Neg.sequentialBegin();
        devSetup_Neg.waitCall("2 ms"); // wait for connection settle
        devSetup_Neg.actionCall(negVmeasurePPMU);
        devSetup_Neg.sequentialEnd();
        measurement_Neg.setSetups(devSetup_Neg);

    }

    @Override
    public void update() {
//        measurementDisconnect.digInOut(disconnect_pins).setConnect(false).setDisconnect(true);
        measurement_Pos.digInOut(TestPinsPos).setConnect(false).setDisconnect(true);
        measurement_Neg.digInOut(TestPinsNeg).setConnect(false).setDisconnect(true);

    }

    @Override
    public void execute() {

//        measurementDisconnect.execute();
        // try {
        // Thread.sleep(2);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }

        measurement_Pos.execute();
//******************************************************************************************
//        IDcVIResults resultPos = measurement_Pos.dcVI(TestPinsPos).preserveResults();
//        Map<String, MultiSiteDoubleArray> measPos = resultPos.iforceVmeas("posVmeasurePPMU")
//                .getVoltage();
//
//        measurement_Neg.execute();
//
//        IDcVIResults resultNeg = measurement_Neg.dcVI(TestPinsNeg).preserveResults();
//        Map<String, MultiSiteDoubleArray> measNeg = resultNeg.iforceVmeas("negVmeasurePPMU")
//                .getVoltage();

        IDcVIResults resultPos = measurement_Pos.dcVI(TestPinsPos).preserveResults();
        Map<String, MultiSiteDoubleArray> measPos = resultPos.vmeas("posVmeasurePPMU")
                .getVoltage();

        measurement_Neg.execute();

        IDcVIResults resultNeg = measurement_Neg.dcVI(TestPinsNeg).preserveResults();
        Map<String, MultiSiteDoubleArray> measNeg = resultNeg.vmeas("negVmeasurePPMU")
                .getVoltage();
//******************************************************************************************
        String[] negSignals = measNeg.keySet().toArray(new String[0]);
        Arrays.sort(negSignals);
        for (String signal : negSignals) {
            for (TST_NAME_ParaGroup tmp : contCollection.values()) {
                if (tmp.getId().equals((signal + "_NEG"))) { // TODO: bug exist here ?? may miss
                                                             // some variable judgement
                    tmp.ptd.evaluate(measNeg.get(signal).getElement(0));
                    break;
                }
            }
        }

        String[] posSignals = measPos.keySet().toArray(new String[0]);
        Arrays.sort(posSignals);
        for (String signal : posSignals) {
            for (TST_NAME_ParaGroup tmp : contCollection.values()) {
                if (tmp.getId().equals((signal + "_POS"))) { // TODO: bug exist here ??
                    tmp.ptd.evaluate(measPos.get(signal).getElement(0));
                    break;
                }
            }
        }

    }
}
