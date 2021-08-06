package pa;

import xoc.dsa.IDeviceSetup;
import xoc.dsa.ISetupProtocolInterface;

public abstract class NemoLPA {



//    public static ISetupProtocolInterface addInterface(IDeviceSetup devSetup1, String protocolInterfaceName, String protFile) {
//
//
//
//    }

    public static ISetupProtocolInterface addInterface_SPI(IDeviceSetup devSetup1, String protocolInterfaceName) {
        ISetupProtocolInterface paInterface = devSetup1.addProtocolInterface(protocolInterfaceName, NemoLPA.prot_SPI);

        paInterface.addSignalRole("SPI_CLK", "EXTINT6");
        paInterface.addSignalRole("SPI_CS", "SDA1");
        paInterface.addSignalRole("SPI_DI", "SCL1");
        paInterface.addSignalRole("SPI_DO", "NF_DATA_3");

        return paInterface;

    }

    public static ISetupProtocolInterface addInterface_FDMA(IDeviceSetup devSetup1, String protocolInterfaceName) {
        ISetupProtocolInterface paInterface = devSetup1.addProtocolInterface(protocolInterfaceName, NemoLPA.prot_FDMA);

        paInterface.addSignalRole("sig_NF_DATA_1", "NF_DATA_1");
        paInterface.addSignalRole("sig_NF_DQS", "NF_DQS");
        paInterface.addSignalRole("sig_U0CTS", "U0CTS");
        paInterface.addSignalRole("sig_U0RTS", "U0RTS");
        paInterface.addSignalRole("sig_U0RXD", "U0RXD");
        paInterface.addSignalRole("sig_U0TXD", "U0TXD");
        paInterface.addSignalRole("sig_U1RXD", "U1RXD");
        paInterface.addSignalRole("sig_U1TXD", "U1TXD");
        paInterface.addSignalRole("sig_SDI1", "SDI1");

        return paInterface;

    }

    public static ISetupProtocolInterface addInterface_EFUSE(IDeviceSetup devSetup1, String protocolInterfaceName) {
        ISetupProtocolInterface paInterface = devSetup1.addProtocolInterface(protocolInterfaceName, NemoLPA.prot_EFUSE);

        paInterface.addSignalRole("sig_RFCTL_2_", "RFCTL_2_");
        paInterface.addSignalRole("sig_RFCTL_3_", "RFCTL_3_");
        paInterface.addSignalRole("sig_RFCTL_4_", "RFCTL_4_");
        paInterface.addSignalRole("sig_RFCTL_9_", "RFCTL_9_");
        paInterface.addSignalRole("sig_RFCTL_10_", "RFCTL_10_");
        paInterface.addSignalRole("sig_NF_DATA_0", "NF_DATA_0");
        paInterface.addSignalRole("sig_NF_DATA_2", "NF_DATA_2");

        return paInterface;

    }



    public static final String prot_SPI = "pa.SPI";
    public static final String prot_FDMA = "pa.FDMA";
    public static final String prot_EFUSE = "pa.EFUSE";



}
