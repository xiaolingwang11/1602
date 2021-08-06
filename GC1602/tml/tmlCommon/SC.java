package tmlCommon;

import java.util.Vector;

public class SC {

    /**
     * find site from avalable sites and return its value
     * return value < 0 means not find or error
     */
    public static int findSite(int[] base, int target) {
        int findValue = -999;
        if (base == null) {
            return findValue;
        }

        int size = base.length;

        if (size == 0) {
            findValue = findValue - 1;
            return findValue;
        }

        for (int i = 0; i < size; i++) {
            if (base[i] == target) {
                findValue = target;
                break;
            }
        }

        return findValue;
    }


    /**
     * find intersection and return
     * return value.size == 0 if not find
     * return null if error
     */
    public static int[] findSitesIntersection(int[] base, int[] target) {

        if (base == null) {
            return null;
        }

        int size = target.length;

        if (size == 0) {
            return null;
        }



        Vector<Integer> sites = new Vector<Integer>();



        for (int i = 0; i < size; i++) {
            int findIndex = findSite(base, target[i]);
            if (findIndex >= 0) {
                sites.add(findIndex);
            }
        }



        size = sites.size();
        int[] sites2run = new int[size];

        for (int i = 0; i < size; i++) {
            sites2run[i] = sites.get(i);
        }

        return sites2run;
    }

    public static boolean findElement(int[] base, int[] target) {

        if (base == null) {
            return false;
        }

        int size = target.length;

        if (size == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {

            int findIndex = findSite(base, target[i]);
            if (findIndex < 0) {
                return false;
            }

        }

        return true;
    }

}
