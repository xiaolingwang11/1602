package GC1602_tml.DC_TML;

import java.util.Arrays;
import java.util.Map;

import include.Common;
import tmlCommon.TMLBase;
import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupDcVI;
import xoc.dsa.ISetupDcVI.IIforce;
import xoc.dsa.ISetupDcVI.IVmeas;
import xoc.dta.ParameterGroupCollection;
import xoc.dta.annotations.In;
import xoc.dta.datatypes.MultiSiteDoubleArray;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.resultaccess.IDcVIResults;

public class Continuity_dcVI<posVmeasurePPMU> extends TMLBase {

    public ParameterGroupCollection<TST_NAME_ParaGroup> contCollection = new ParameterGroupCollection<>();

    @In
    public String TestPinsNeg = "gCONT";
    @In
    public String TestPinsPos = "gCONT";

    public IMeasurement measurementDisconnect;
    public IMeasurement measurement_Pos;
    public IMeasurement measurement_Neg;

    // disconnect_pins = allDio - gCONT
    public static final String disconnect_pins = "EMZQ+TEMP_CS+TEMP_DIN+TEMP_DOUT+TEMP_SCLK+AWG_PU_IQ+AWG_VOCM_IQ+DAC_CM_IQ+DGT_PU_IQ+RX_VOCM_IQ+APCOUT0+APCOUT1+SCL+SDA+RF_Source_32417+RF_Source_32317+RF_Source_31913+RF_Source_32013+RF_Source_31917+RF_Source_32017+CT1_M1+CT2_M1+KK17+KK18+KK19+KK20"; //

    private static final String groupDef = "GC1602_configuration.Groups";

    @Override
    public void setup() {

        IDeviceSetup devSetup3 = newDevSetup(importSpec);
        devSetup3.importSpec(groupDef);
        Common.disconnectDigIO(devSetup3, disconnect_pins);
        measurementDisconnect.setSetups(devSetup3);

        IDeviceSetup devSetup_Pos = newDevSetup(importSpec);
        IDeviceSetup devSetup_Neg = newDevSetup(importSpec);

        devSetup_Pos.importSpec(groupDef);
        devSetup_Neg.importSpec(groupDef);
//****************************************************************************************************
//        IIforceVmeas posVmeasurePPMU;
//        posVmeasurePPMU = devSetup_Pos.addDcVI(TestPinsPos).iforceVmeas("posVmeasurePPMU");
//        posVmeasurePPMU.setForceValue("100 uA").setKeepVclamp(SetupKeepVclamp.enabled)
//        posVmeasurePPMU.setWaitTime("2 ms");

        ISetupDcVI devSetup_Pos1=devSetup_Pos.addDcVI(TestPinsPos).setDisconnect(true);

        IIforce    posIforcePPMU = devSetup_Pos1.iforce("posIforcePPMU");
        posIforcePPMU.setForceValue("100 uA").setKeepVclampEnabled()
        .setIrange("100 uA").setVexpected("1 V").setVclampHigh("1.5 V")
        .setVclampLow("0 V");

        IVmeas  posVmeasurePPMU= devSetup_Pos1.vmeas("posVmeasurePPMU");
        posVmeasurePPMU.setWaitTime("2 ms");

//***************************************************************************************************
//        IIforceVmeas negVmeasurePPMU;
//        negVmeasurePPMU = devSetup_Neg.addDcVI(TestPinsNeg).iforceVmeas("negVmeasurePPMU");
//        negVmeasurePPMU.setForceValue("-100 uA").setKeepVclamp(SetupKeepVclamp.enabled)
//        negVmeasurePPMU.setWaitTime("2 ms");

        ISetupDcVI devSetup_Neg1=devSetup_Neg.addDcVI(TestPinsPos).setDisconnect(true);
        IIforce negIforcePPMU;
        IVmeas  negVmeasurePPMU;
        negIforcePPMU = devSetup_Neg1.iforce("negIforcePPMU");
        negIforcePPMU.setForceValue("-100 uA").setKeepVclampEnabled()
                .setIrange("100 uA").setVexpected("-1 V")
                .setVclampHigh("-0.0 V").setVclampLow("-1.5 V");


        negVmeasurePPMU= devSetup_Neg1.vmeas("negVmeasurePPMU");

        negVmeasurePPMU.setWaitTime("2 ms");
//***************************************************************************************************

        devSetup_Pos.sequentialBegin("Pos");
        devSetup_Pos.waitCall("2 ms"); // wait for connection settle

        devSetup_Pos.actionCall(posIforcePPMU);
        devSetup_Pos.actionCall(posVmeasurePPMU);
        devSetup_Pos.sequentialEnd();
        measurement_Pos.setSetups(devSetup_Pos);

        devSetup_Neg.sequentialBegin("Neg");
        devSetup_Neg.waitCall("2 ms"); // wait for connection settle
        devSetup_Neg.actionCall(negIforcePPMU);
        devSetup_Neg.actionCall(negVmeasurePPMU);
        devSetup_Neg.sequentialEnd();
        measurement_Neg.setSetups(devSetup_Neg);

    }

    @Override
    public void update() {
        measurementDisconnect.digInOut(disconnect_pins).setConnect(false).setDisconnect(true);
        measurement_Pos.dcVI(TestPinsPos).setConnect(true).setDisconnect(true);
        measurement_Neg.dcVI(TestPinsNeg).setConnect(true).setDisconnect(true);
    }

    @Override
    public void execute() {

        // disconnect non continuity pins



        measurementDisconnect.execute();



        measurement_Pos.execute();


        IDcVIResults resultPos = measurement_Pos.dcVI(TestPinsPos).preserveResults();

        measurement_Neg.execute();

        IDcVIResults resultNeg = measurement_Neg.dcVI(TestPinsNeg).preserveResults();


        releaseTester();



      //****************************************************************************************************
//        Map<String, MultiSiteDoubleArray> measPos = resultPos.iforceVmeas("posVmeasurePPMU")
//                .getVoltage();
//
//        Map<String, MultiSiteDoubleArray> measNeg = resultNeg.iforceVmeas("negVmeasurePPMU")
//                .getVoltage();



        Map<String, MultiSiteDoubleArray> measPos = resultPos.vmeas("posVmeasurePPMU")
                .getVoltage();

        Map<String, MultiSiteDoubleArray> measNeg = resultNeg.vmeas("negVmeasurePPMU")
                .getVoltage();

//****************************************************************************************************



        String[] negSignals = measNeg.keySet().toArray(new String[0]);
        Arrays.sort(negSignals);
        for (String signal : negSignals) {
            for (TST_NAME_ParaGroup tmp : contCollection.values()) {
                if (tmp.getId().equals((signal + "_NEG"))) {
                    tmp.ptd.evaluate(measNeg.get(signal).getElement(0));
                    break;
                }
            }
        }

        String[] posSignals = measPos.keySet().toArray(new String[0]);
        Arrays.sort(posSignals);
        for (String signal : posSignals) {
            for (TST_NAME_ParaGroup tmp : contCollection.values()) {
                if (tmp.getId().equals((signal + "_POS"))) {
                    tmp.ptd.evaluate(measPos.get(signal).getElement(0));
                    break;
                }
            }
        }



    }
}
