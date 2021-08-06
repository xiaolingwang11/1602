package pa;

import tmlCommon.TMLBase;
import xoc.dsa.ISetupProtocolInterface;

public class TransactSeq_FDMA extends TransactSeq {

    public TransactSeq_FDMA(ISetupProtocolInterface paInterface, String name) {
        super(paInterface, name);
        cyclePerWrite = 55635;
        cyclePerRead = 55791;
        Period = default_per_ns_FDMA * TMLBase.ns;
    }

}
