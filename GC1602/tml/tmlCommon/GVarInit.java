package tmlCommon;

import xoc.dta.TestMethod;

public class GVarInit extends TestMethod {

    @Override
    public void execute() {
        GVar.initial_EFUSE(0);

    }

}
