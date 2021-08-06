package common_Code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import xoc.dsa.IDeviceSetup;

public class GenOSeq {
    public static Sheet patSheet = null; //the sheet to be read from patlist ods file

    private GenOSeq( ){ }
    private static GenOSeq instance = null;

    public static GenOSeq getInstance (){
        if( instance  == null){
            instance = new GenOSeq();
        }
        return instance;
    }

    public void init(String patListFilePath,String sheetName,int maxRowCount, int maxColCount) {
        if (patSheet == null) {
            patSheet = readFromPatListFile(patListFilePath, sheetName,maxRowCount, maxColCount);
        }
    }

    @SuppressWarnings("restriction")
    private Sheet readFromPatListFile(String patListFilePath,String sheetName, int maxRowCount, int maxColCount ){
        Sheet mySheet = null;
        try{
            File file = new File(patListFilePath);
            SpreadSheet document = SpreadSheet.createFromFile(file);
            mySheet = document.getSheet(sheetName);
            if(mySheet.getRowCount() > maxRowCount ){
                mySheet.setRowCount(maxRowCount);
            }
            if (mySheet.getColumnCount() > maxColCount ){
                mySheet.setColumnCount(maxColCount);
            }

        }catch (IOException e) {
            System.out.println("Error! Exception in reading from file: "+patListFilePath);
            e.printStackTrace();
        }

        return mySheet;
    }

    private int getSuiteRowNumber( String suiteNameColTag, String suiteName) {
        int matchedRowNumber =1;

        if (patSheet != null ) {
            int rowCount = patSheet.getRowCount();
            MutableCell<SpreadSheet> cell = null;

            for (int rowIndex= 1; rowIndex < rowCount; rowIndex ++) {
                try {
                    cell = patSheet.getCellAt(suiteNameColTag+String.valueOf(rowIndex)); //"A1","A2"-- suite name
                    if ( cell.getTextValue().equals(suiteName) ) {
                        matchedRowNumber = rowIndex;
                    }
                } catch (Exception e) {
                    System.out.println("Error! Exception in get named suite row number from sheet: "+patSheet.getName() );
                    e.printStackTrace();
                    return -1;
                }
            }
        }else{
            System.out.println("Error: patSheet is NULL! Pls double check your file");
            return -1;
        }
        return matchedRowNumber;
    }

    public IDeviceSetup txOSeqGen(IDeviceSetup devSetup,String suiteName, String patPath, String sourceName, String measName,String suiteNameColTag,String patNameColTag,ArrayList<String> measID)
    {
        int rowNumber = getSuiteRowNumber(suiteNameColTag, suiteName); //'columnTag' is the column of suiteName
        MutableCell<SpreadSheet> cell = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber));
        String patName = cell.getTextValue();

        int measIndex=0;
        devSetup.sequentialBegin();

        devSetup.parallelBegin();
        {
            devSetup.actionCall(sourceName); //awg Source IQ waveform

            devSetup.sequentialBegin();
            {
                while(patName!= ""){
                    if(patName.contentEquals("DGT_TRIG")){
                        if( measIndex < measID.size() ){
                            devSetup.waitCall(1.0e-3);
                            devSetup.actionCall(measName + measID.get(measIndex));
                            measIndex++;
                        }
                        //                        else{
                        //                            System.out.println("Error: Number of RF measure states doesn't match number of \'DGT_TRIG\'. "+
                        //                                    "number of measStates= "+measID.size() + "; Only generate "+ measID.size() + " RF mea actions in OSeq");
                        //  break;
                        //                        }
                    }else{
                        devSetup.patternCall(patPath + patName);
                    }
                    rowNumber++;
                    patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();
                }
                //                devSetup.waitCall(10.0e-3);

            }devSetup.sequentialEnd();

        }devSetup.parallelEnd();


        devSetup.sequentialEnd();

        return devSetup;
    }

    @SuppressWarnings("restriction")
    public IDeviceSetup rxOSeqGen(IDeviceSetup _devSetup, String _testSuiteName, String _patPath, String _sourceName, String _measName,String suiteNameColTag,String patNameColTag)
    {
        IDeviceSetup devSetup = _devSetup;
        String patPath = _patPath;
        String testSuiteName = _testSuiteName;

        int rowNumber = getSuiteRowNumber(suiteNameColTag,testSuiteName );

        MutableCell<SpreadSheet> cell = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber));
        String patName = cell.getTextValue();

        String sourceName = _sourceName;
        String measName = _measName;

        int stimId=1;
        devSetup.sequentialBegin();

        while(!patName.equals(""))
        {
            devSetup.parallelBegin();
            {
                devSetup.actionCall(sourceName+ "_" + stimId );

                devSetup.sequentialBegin();
                {
                    loop:
                        while(!patName.equals("RFE_TRIG"))
                        {
                            if (patName.equals(""))
                            {break loop; }
                            if(patName.contains("TRIG"))
                            {
                                if(patName.contentEquals("DGT_TRIG")){
                                    devSetup.waitCall(0.5e-3);
                                    //devSetup.waitCall(5e-3);
                                    devSetup.actionCall(measName); // + "_" + measId)
                                    //devSetup.waitCall(0.2e-3);
                                    //                                    measId++;
                                }
                            }
                            else{
                                devSetup.patternCall(patPath +patName);
                            }

                            rowNumber++;
                            patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

                        }

                }devSetup.sequentialEnd();
            }devSetup.parallelEnd();

            if (patName!= "")
            {
                stimId++;
                //                System.out.println("current stimId is "+ stimId);
                rowNumber++;
                patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

            }
        }
        devSetup.sequentialEnd();
        return devSetup;
    }

    @SuppressWarnings("restriction")
    public IDeviceSetup rxOSeqGen_single_meas(IDeviceSetup _devSetup, String _testSuiteName, String _patPath, String _sourceName, String _measName,String suiteNameColTag,String patNameColTag)
    {
        IDeviceSetup devSetup = _devSetup;
        String patPath = _patPath;
        String testSuiteName = _testSuiteName;

        int rowNumber = getSuiteRowNumber(suiteNameColTag,testSuiteName );

        MutableCell<SpreadSheet> cell = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber));
        String patName = cell.getTextValue();

        String sourceName = _sourceName;
        String measName = _measName;

        int stimId=1;
        devSetup.sequentialBegin();

        while(!patName.equals(""))
        {
            devSetup.parallelBegin();
            {
                devSetup.actionCall(sourceName+ "_" + stimId );
                //devSetup.waitCall(0.005);
                devSetup.sequentialBegin();
                {
                    loop:
                        while(!patName.equals("RFE_TRIG"))
                        {
                            if (patName.equals(""))
                            {break loop; }
                            if(patName.contains("TRIG"))
                            {
                                if(patName.contentEquals("DGT_TRIG")){
                                    //devSetup.waitCall(0.005);
                                    devSetup.actionCall(measName); // + "_" + measId)
                                    //  devSetup.waitCall(0.1);
                                    //                                    measId++;
                                }
                            }
                            else{
                                devSetup.patternCall(patPath +patName);
                            }

                            rowNumber++;
                            patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

                        }

                }devSetup.sequentialEnd();
            }devSetup.parallelEnd();

            if (patName!= "")
            {
                stimId++;
                //                System.out.println("current stimId is "+ stimId);
                rowNumber++;
                patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

            }
        }
        devSetup.sequentialEnd();
        return devSetup;
    }


    @SuppressWarnings("restriction")
    public IDeviceSetup rxOSeqGen_iip(IDeviceSetup _devSetup, String _testSuiteName, String _patPath, String _sourceName, String _measName,String suiteNameColTag,String patNameColTag)
    {
        IDeviceSetup devSetup = _devSetup;
        String patPath = _patPath;
        String testSuiteName = _testSuiteName;

        int rowNumber = getSuiteRowNumber(suiteNameColTag,testSuiteName );

        MutableCell<SpreadSheet> cell = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber));
        String patName = cell.getTextValue();

        String sourceName = _sourceName;
        String measName = _measName;

        int stimId=1;
        devSetup.sequentialBegin();

        while(!patName.equals(""))
        {
            devSetup.parallelBegin();
            {
                devSetup.actionCall(sourceName+ "_" + stimId );

                devSetup.sequentialBegin();
                {

                    loop:
                        while(!patName.equals("RFE_TRIG"))
                        {
                            if (patName.equals(""))
                            {break loop; }
                            if(patName.contains("TRIG"))
                            {
                                if(patName.contentEquals("DGT_TRIG")){
                                    if(stimId==1)
                                    {devSetup.waitCall(0.003);}
                                    // devSetup.waitCall(0.003);
                                    // if(stimId!=1){  devSetup.actionCall(sourceName+ "_" + stimId );}
                                    devSetup.actionCall(measName); // + "_" + measId)

                                    //  devSetup.waitCall(0.1);
                                    //                                    measId++;
                                }
                            }
                            else{
                                devSetup.patternCall(patPath +patName);
                            }

                            rowNumber++;
                            patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

                        }

                }devSetup.sequentialEnd();
            }devSetup.parallelEnd();

            if (patName!= "")
            {
                stimId++;
                //                System.out.println("current stimId is "+ stimId);
                rowNumber++;
                patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

            }


        }
        devSetup.sequentialEnd();
        return devSetup;
    }
    @SuppressWarnings("restriction")
    public IDeviceSetup rxOSeqGen_Filt(IDeviceSetup _devSetup, String _testSuiteName, String _patPath, String _sourceName, String _measName,String suiteNameColTag,String patNameColTag)
    {
        IDeviceSetup devSetup = _devSetup;
        String patPath = _patPath;
        String testSuiteName = _testSuiteName;

        int rowNumber = getSuiteRowNumber(suiteNameColTag,testSuiteName );

        MutableCell<SpreadSheet> cell = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber));
        String patName = cell.getTextValue();

        String sourceName = _sourceName;
        String measName = _measName;

        int stimId=0;
        devSetup.sequentialBegin();

        while(!patName.equals(""))
        {
            devSetup.parallelBegin();
            {
                devSetup.actionCall(sourceName+ "_" + stimId );

                devSetup.sequentialBegin();
                {
                    loop:
                        while(!patName.equals("RFE_TRIG"))
                        {
                            if (patName.equals(""))
                            {break loop; }
                            if(patName.contains("TRIG"))
                            {
                                if(patName.contentEquals("DGT_TRIG")){
                                    //devSetup.waitCall(0.005);
                                    devSetup.actionCall(measName);
                                }
                            }
                            else{
                                devSetup.patternCall(patPath +patName);
                            }

                            rowNumber++;
                            patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

                        }

                }devSetup.sequentialEnd();
            }devSetup.parallelEnd();

            if (patName!= "")
            {
                stimId++;
                //System.out.println("current stimId is "+ stimId);
                rowNumber++;
                patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

            }
        }
        devSetup.sequentialEnd();
        return devSetup;
    }


    public IDeviceSetup txOSeqGen_single_meas(IDeviceSetup devSetup,String suiteName, String patPath, String sourceName, String measName,String suiteNameColTag,String patNameColTag)
    {
        int rowNumber = getSuiteRowNumber(suiteNameColTag, suiteName); //'columnTag' is the column of suiteName
        MutableCell<SpreadSheet> cell = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber));
        String patName = cell.getTextValue();

        //  int measIndex=0;
        devSetup.sequentialBegin();

        devSetup.parallelBegin();
        {
            devSetup.actionCall(sourceName); //awg Source IQ waveform

            devSetup.sequentialBegin();
            {
                while(patName!= ""){
                    if(patName.contentEquals("DGT_TRIG")){
                        //devSetup.waitCall(5.0e-3);
                        devSetup.actionCall(measName);
                    }else{
                        devSetup.patternCall(patPath + patName);
                    }
                    rowNumber++;
                    patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();
                }
                //                devSetup.waitCall(10.0);
                //                devSetup.waitCall(10.0);
                //                devSetup.waitCall(10.0);
                //                devSetup.waitCall(10.0);
            }devSetup.sequentialEnd();

        }devSetup.parallelEnd();


        devSetup.sequentialEnd();

        return devSetup;
    }


    public IDeviceSetup PatSeqGen(IDeviceSetup devSetup,String suiteName, String patPath, String suiteNameColTag,String patNameColTag)
    {
        int rowNumber = getSuiteRowNumber(suiteNameColTag, suiteName); //'columnTag' is the column of suiteName
        MutableCell<SpreadSheet> cell = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber));
        String patName = cell.getTextValue();

        //System.out.println(suiteName + ":"+ patName);
        while(patName!= ""){
            devSetup.patternCall(patPath + patName);
            rowNumber++;
            patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();
            // System.out.println(suiteName + ":"+ patName);

        }

        return devSetup;
    }


    public IDeviceSetup DCPatSeqGen(IDeviceSetup devSetup,String suiteName, String patPath, String DCmeasName, String suiteNameColTag,String patNameColTag)
    {
        int rowNumber = getSuiteRowNumber(suiteNameColTag, suiteName); //'columnTag' is the column of suiteName
        MutableCell<SpreadSheet> cell = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber));
        String patName = cell.getTextValue();

        //System.out.println(suiteName + ":"+ patName);
        while(patName!= ""){
            if(patName.contentEquals("DC_meas")){
                //devSetup.waitCall(5.0e-3);
                devSetup.actionCall("DC1");
            }else{
                devSetup.patternCall(patPath + patName);
            }
            rowNumber++;
            patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();
            // System.out.println(suiteName + ":"+ patName);

        }

        return devSetup;
    }




    public IDeviceSetup VcomPatSeqGen(IDeviceSetup devSetup,String suiteName, String patPath, String dgtMeasName, String suiteNameColTag,String patNameColTag)
    {
        int rowNumber = getSuiteRowNumber(suiteNameColTag, suiteName); //'columnTag' is the column of suiteName
        MutableCell<SpreadSheet> cell = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber));
        String patName = cell.getTextValue();

        //System.out.println(suiteName + ":"+ patName);
        while(patName!= ""){
            if(patName.contentEquals("DGT_TRIG")){
                //devSetup.waitCall(5.0e-3);
                devSetup.actionCall(dgtMeasName);
            }else{
                devSetup.patternCall(patPath + patName);
            }
            rowNumber++;
            patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();
            //System.out.println(suiteName + ":"+ patName);

        }

        return devSetup;
    }


    public IDeviceSetup txIBEOSeqGen_single_meas(IDeviceSetup devSetup,String suiteName, String patPath, String sourceName, String measName,String suiteNameColTag,String patNameColTag)
    {
        int rowNumber = getSuiteRowNumber(suiteNameColTag, suiteName); //'columnTag' is the column of suiteName
        MutableCell<SpreadSheet> cell = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber));
        String patName = cell.getTextValue();

        //  int measIndex=0;
        devSetup.sequentialBegin();

        devSetup.parallelBegin();
        {
            devSetup.sequentialBegin();
            {
                devSetup.actionCall(sourceName); //awg Source IQ waveform
            }devSetup.sequentialEnd();

            devSetup.sequentialBegin();
            {
                while(patName!= ""){
                    if(patName.contentEquals("DGT_TRIG")){
                        devSetup.waitCall(4.8e-3);
                        devSetup.actionCall(measName);
                    }else{
                        devSetup.patternCall(patPath + patName);
                    }
                    rowNumber++;
                    patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();
                }
            }devSetup.sequentialEnd();

        }devSetup.parallelEnd();


        devSetup.sequentialEnd();

        return devSetup;
    }


    @SuppressWarnings("restriction")
    public IDeviceSetup rxIROSeqGen(IDeviceSetup _devSetup, String _testSuiteName, String _patPath, String _sourceName, String _measName,String suiteNameColTag,String patNameColTag)
    {
        IDeviceSetup devSetup = _devSetup;
        String patPath = _patPath;
        String testSuiteName = _testSuiteName;

        int rowNumber = getSuiteRowNumber(suiteNameColTag,testSuiteName );

        MutableCell<SpreadSheet> cell = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber));
        String patName = cell.getTextValue();

        String sourceName = _sourceName;
        String measName = _measName;

        int stimId=1;
        devSetup.sequentialBegin();

        while(!patName.equals(""))
        {
            devSetup.parallelBegin();
            {
                devSetup.actionCall(sourceName+ "_" + stimId );

                devSetup.sequentialBegin();
                {
                    loop:
                        while(!patName.equals("RFE_TRIG"))
                        {
                            if (patName.equals(""))
                            {break loop; }
                            if(patName.contains("TRIG"))
                            {
                                if(patName.contentEquals("DGT_TRIG")){
                                    devSetup.waitCall(0.5e-3);
                                    devSetup.actionCall(measName); // + "_" + measId)
                                    //devSetup.waitCall(0.2e-3);
                                    //                                    measId++;
                                }
                            }
                            else{
                                devSetup.patternCall(patPath +patName);
                            }

                            rowNumber++;
                            patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

                        }

                }devSetup.sequentialEnd();
            }devSetup.parallelEnd();

            if (patName!= "")
            {
                stimId++;
                //                System.out.println("current stimId is "+ stimId);
                rowNumber++;
                patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

            }
        }
        devSetup.sequentialEnd();
        return devSetup;
    }

    @SuppressWarnings("restriction")
    public IDeviceSetup rxEVMOSeqGen_single_meas(IDeviceSetup _devSetup, String _testSuiteName, String _patPath, String _sourceName, String _measName,String suiteNameColTag,String patNameColTag)
    {
        IDeviceSetup devSetup = _devSetup;
        String patPath = _patPath;
        String testSuiteName = _testSuiteName;

        int rowNumber = getSuiteRowNumber(suiteNameColTag,testSuiteName );

        MutableCell<SpreadSheet> cell = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber));
        String patName = cell.getTextValue();

        String sourceName = _sourceName;
        String measName = _measName;

        int stimId=1;
        devSetup.sequentialBegin();

        while(!patName.equals(""))
        {
            devSetup.parallelBegin();
            {
                devSetup.actionCall(sourceName+ "_" + stimId );
                //devSetup.waitCall(0.005);
                devSetup.sequentialBegin();
                {
                    loop:
                        while(!patName.equals("RFE_TRIG"))
                        {
                            if (patName.equals(""))
                            {break loop; }
                            if(patName.contains("TRIG"))
                            {
                                if(patName.contentEquals("DGT_TRIG")){
                                    devSetup.waitCall(2e-3);
                                    devSetup.actionCall(measName); // + "_" + measId)
                                    //  devSetup.waitCall(0.1);
                                    //                                    measId++;
                                }
                            }
                            else{
                                devSetup.patternCall(patPath +patName);
                            }

                            rowNumber++;
                            patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

                        }

                }devSetup.sequentialEnd();
            }devSetup.parallelEnd();

            if (patName!= "")
            {
                stimId++;
                //                System.out.println("current stimId is "+ stimId);
                rowNumber++;
                patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

            }
        }
        devSetup.sequentialEnd();
        return devSetup;
    }


    @SuppressWarnings("restriction")
    public IDeviceSetup rxNFOSeqGen_single_meas(IDeviceSetup _devSetup, String _testSuiteName, String _patPath, String _sourceName, String _measName,String suiteNameColTag,String patNameColTag)
    {
        IDeviceSetup devSetup = _devSetup;
        String patPath = _patPath;
        String testSuiteName = _testSuiteName;

        int rowNumber = getSuiteRowNumber(suiteNameColTag,testSuiteName );

        MutableCell<SpreadSheet> cell = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber));
        String patName = cell.getTextValue();

        String sourceName = _sourceName;
        String measName = _measName;

        int stimId=1;
        int measId=1;
        devSetup.sequentialBegin();

        while(!patName.equals(""))
        {
            //            devSetup.parallelBegin();
            //            {
            devSetup.actionCall(sourceName+ "_" + stimId );
            //devSetup.waitCall(0.005);
            //                devSetup.sequentialBegin();
            //                {
            loop:
                while(!patName.equals("RFE_TRIG"))
                {
                    if (patName.equals(""))
                    {break loop; }
                    if(patName.contains("TRIG"))
                    {
                        if(patName.contentEquals("DGT_TRIG")){
                            devSetup.waitCall(0.005);
                            devSetup.actionCall(measName+"_" + measId);
                            //  devSetup.waitCall(0.1);
                            measId++;
                        }
                    }
                    else{
                        devSetup.patternCall(patPath +patName);
                    }

                    rowNumber++;
                    patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

                }

            //                }devSetup.sequentialEnd();
            //            }devSetup.parallelEnd();

            if (patName!= "")
            {
                stimId++;
                //                System.out.println("current stimId is "+ stimId);
                rowNumber++;
                patName = patSheet.getCellAt(patNameColTag+String.valueOf(rowNumber)).getTextValue();

            }
        }
        devSetup.sequentialEnd();
        return devSetup;
    }



}
