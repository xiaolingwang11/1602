package pa;

import tmlCommon.TMLBase;
import xoc.dsa.ISetupProtocolInterface;

public class TransactSeq_SPI  extends TransactSeq {

    public TransactSeq_SPI(ISetupProtocolInterface paInterface, String name) {
        super(paInterface, name);
        cyclePerWrite = 37;
        cyclePerRead = 37;
        Period = default_per_ns_SPI * TMLBase.ns;
    }

}
