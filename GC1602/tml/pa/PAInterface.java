package pa;

import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupProtocolInterface;

public abstract class PAInterface {

    public ISetupProtocolInterface paInterface;
    public String name;
    public String protFile;

    public static PAInterface newInstance(IDeviceSetup devSetup, String name, String protFile) {

        PAInterface spiInterface  = null;//new SPIInterface();
        return spiInterface;
    }

}
