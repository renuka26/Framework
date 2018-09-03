package com.mindtree.keywordDrivenFramework.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mindtree.keywordDrivenFramework.executionEngine.Script;
import com.relevantcodes.extentreports.LogStatus;
import static com.mindtree.keywordDrivenFramework.executionEngine.Script.test;
import org.apache.poi.xssf.usermodel.XSSFRow;


    public class ExcelUtils {
                private static XSSFSheet ExcelWSheet;
                private static XSSFWorkbook ExcelWBook;
                private static org.apache.poi.ss.usermodel.Cell Cell;
                private static XSSFRow Row;
           
            public static void setExcelFile(String Path) throws Exception {
            	try {
                    FileInputStream ExcelFile = new FileInputStream(Path);
                    ExcelWBook = new XSSFWorkbook(ExcelFile);
            	} catch (Exception e){
            		Log.info("Not able to set excel file" + e.getMessage());
            		test.log(LogStatus.FAIL, "Not able to set excel");
        			Script.bResult = false;
                	}
            	}
            
            public static String getCellData(int RowNum, int ColNum, String SheetName ) throws Exception{
//                try{
                	ExcelWSheet = ExcelWBook.getSheet(SheetName);
                   	Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
                    String CellData = Cell.getStringCellValue();
                    return CellData;
//                 }catch (Exception e){
//                	 Log.info("here Not able to get cell data" + e.getMessage());
//                	 test.log(LogStatus.FAIL, "not able to get cell data");
//         			 Script.bResult = false;
//                     return" hii";
//                     }
                 }
            
        	
        	public static int getRowCount(String SheetName){
        		int iNumber=0;
        		try {
        				ExcelWSheet = ExcelWBook.getSheet(SheetName);
        				iNumber=ExcelWSheet.getLastRowNum()+1;
   
        		} catch (Exception e){
        			Log.info("Not able to get row count" + e.getMessage());
        			test.log(LogStatus.FAIL, "Not able to get row");
        			Script.bResult = false;
        			}
        		return iNumber;
        		}
        	
        	public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
        		int iRowNum=0;	
        		try {
        			int rowCount = ExcelUtils.getRowCount(SheetName);
        			for (; iRowNum<rowCount; iRowNum++){
        				if  (ExcelUtils.getCellData(iRowNum,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){
        					break;
        				}
        			}       			
        		} catch (Exception e){
        			
        			Log.info("Not able to get row number " + e.getMessage());
        			test.log(LogStatus.FAIL, "NOt able to get row");
        			Script.bResult = false;
        			}
        		return iRowNum;
        		}
        	
        	public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
        		try {
        			int n = ExcelUtils.getRowCount(SheetName);
        			
	        		for(int i=iTestCaseStart;i<n;i++){
	        			if(!sTestCaseID.equals(ExcelUtils.getCellData(i, 0, SheetName))){
	        				int number = i;
	        				return number;      				
	        				}
	        			}
	        		ExcelWSheet = ExcelWBook.getSheet(SheetName);
	        		int number=ExcelWSheet.getLastRowNum()+1;
	        		return number;
        		} catch (Exception e){
        			Log.info("Not able to get test steps count " + e.getMessage());
        			test.log(LogStatus.FAIL, "Not able to get test steps");
        			Script.bResult = false;
        			return 0;
                }
        	}
        	
        	@SuppressWarnings("static-access")
        	public static void setCellData(String Result,  int RowNum, int ColNum, String SheetName) throws Exception    {
                   try{
                	   
                	   ExcelWSheet = ExcelWBook.getSheet(SheetName);
                       Row  = ExcelWSheet.getRow(RowNum);
                       Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
                       if (Cell == null) {
                    	   Cell = Row.createCell(ColNum);
                    	   Cell.setCellValue(Result);
                        } else {
                            Cell.setCellValue(Result);
                        }
                         FileOutputStream fileOut = new FileOutputStream("D:/workspace NEON/keywordDrivenFramework/src/main/java/com/mindtree/keywordDrivenFramework/dataEngine/DataEngine.xlsx");
                         ExcelWBook.write(fileOut);
 
                         fileOut.close();
                         ExcelWBook = new XSSFWorkbook(new FileInputStream("D:/workspace NEON/keywordDrivenFramework/src/main/java/com/mindtree/keywordDrivenFramework/dataEngine/DataEngine.xlsx"));
                     }catch(Exception e){
                    	 Log.info("Not able to set cell data " + e.getMessage());
                    	 test.log(LogStatus.FAIL, "NOT able to set cell data");
             			 Script.bResult = false;
              
                     }
                }

    	}