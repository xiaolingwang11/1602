package GC1602_tml.EFUSE;

import java.util.Map;
import java.util.Map.Entry;

import tmlCommon.Msg;
import xoc.dta.TestMethod;
import xoc.dta.annotations.Out;
import xoc.dta.binning.IBinning;
import xoc.dta.binning.SoftBin;
import xoc.dta.datatypes.MultiSiteBoolean;

public class EfuseStopper extends TestMethod {

    @Out
    public MultiSiteBoolean stop;

    @Override
    public void execute() {

        stop = new MultiSiteBoolean(true);
        IBinning binning = context.binning();

        for (int site : context.getActiveSites()) {

            if (Msg.isDebugEnabled()) {
                int index = 0;
                Map<Integer, SoftBin> swbins = binning.binTable().getAllSoftBins();

                Msg.debug("----------------------------");
                for (Entry<Integer, SoftBin> swbin : swbins.entrySet()) {
                    index++;
                    Msg.debug("swbin:\t" + swbin.getKey() + "\t" + swbin.getValue());
                }
                Msg.debug("total software_index:" + index);
                Msg.debug("----------------------------");



                for (int i = 0; i < binning.getBinList(site).size(); i++) {
                    Msg.debug("binlist=" + i + "   :" + binning.getBinList(site).get(i));
                }
                Msg.debug("----------------------------");

            }


            int flag = 0;
            int size_site = binning.getBinList(site).size();

            int temp[] = new int[size_site];
            for (int i = 0; i < size_site; i++) {
                temp[i] = binning.getBinList(site).get(i);
                Msg.debug(temp[i]);
                if (temp[i] > 0) {
                    flag = 1;
                    break;
                }
            }


            if (binning.getBinList(site).isEmpty()) {
                stop.set(site, false);
            }else {
                if (flag == 1) {
                    stop.set(site, true);
                } else {
                    stop.set(site, false);
                }
            }
        }

    }

}
