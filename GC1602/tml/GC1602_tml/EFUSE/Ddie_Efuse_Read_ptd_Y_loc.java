package GC1602_tml.EFUSE;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;
import xoc.dta.testdescriptor.IFunctionalTestDescriptor;


public class Ddie_Efuse_Read_ptd_Y_loc extends TestMethod {

    public IMeasurement measurement;

    public IFunctionalTestDescriptor ptd_Y_loc;



    @Override
    public void update ()
    {

        // add code here
    }


    @Override
    public void execute ()
    {

        measurement.execute();
    }
}
