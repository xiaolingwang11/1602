package tmlCommon;

import xoc.dta.datatypes.MultiSiteLongArray;

public class S8Container {

    public static MultiSiteLongArray newMultiSiteLongArray(int len, long initVal) {
        long initValue[] = new long[len];
        for (int i = 0; i < len; i++) {
            initValue[i] = initVal;
        }
        return new MultiSiteLongArray(initValue);
    }

    private int len;

}
