package pa;

public class PAWVar {
    public String transactVarName = "";
    public String dsVarName = "";

    public String getArgument() {
        return transactVarName + " = " + dsVarName;
    }

    public static String[] getArguments(PAWVar[] paWrite) {
        return getArguments(paWrite, 0, paWrite.length - 1);
    }

    public static String[] getArguments(PAWVar[] paWrite, int sttIdx, int stpIdx) {
        int size = stpIdx - sttIdx + 1;
        String args[] = new String[size];
        for (int i = sttIdx; i <= stpIdx; i++) {
            args[i - sttIdx] = paWrite[i].getArgument();
        }
        return args;
    }
}
