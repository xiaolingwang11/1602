package GC1602_tml;

import xoc.dta.TestMethod;
import xoc.dta.measurement.IMeasurement;

public class runPattern extends TestMethod {

    public IMeasurement measurement;

    @Override
    public void execute() {
        measurement.execute();

    }

}
