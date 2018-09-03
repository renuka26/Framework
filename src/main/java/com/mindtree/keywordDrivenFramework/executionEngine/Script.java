package com.mindtree.keywordDrivenFramework.executionEngine;
import com.mindtree.keywordDrivenFramework.config.ActionKeywords;
import com.mindtree.keywordDrivenFramework.utility.ExcelUtils;
import com.mindtree.keywordDrivenFramework.utility.Log;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;

import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
public class Script {
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword,sPageObject;
	public static Method method[];
	public static boolean bResult;
	public static FileInputStream fileInput = null;
	public static Properties prop;
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	public static String testcase = "Test Cases";
	public static String teststep = "Test Steps";
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	
	
	
	public static void main( String[] args ) throws Exception{
		 System.out.println("Working");
		 DOMConfigurator.configure("log4j.xml");
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();		
		String sPath = "D:/workspace NEON/keywordDrivenFramework/src/main/java/com/mindtree/keywordDrivenFramework/dataEngine/DataEngine.xlsx";
		ExcelUtils.setExcelFile(sPath);
		
		 File file = new File("D:/workspace NEON/keywordDrivenFramework/src/main/java/com/mindtree/keywordDrivenFramework/config/config.properties");
		 try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			 
			 try {
				 prop = new Properties();
				 prop.load(fileInput);
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
		     //Extent report
			    String workingDir = "D:/workspace NEON/keywordDrivenFramework";
				extent = new ExtentReports(workingDir+"/Test_Execution_Report.html", true);
				extent.loadConfig(new File(workingDir+"/extent-config.xml"));
				extent.addSystemInfo("Environment","QA");
			 			 
	Script script = new Script();
	System.out.println("before testcase");
	script.execute_TestCase();
	extent.flush();
	extent.close();
	
	
}
	
	private void execute_TestCase() throws Exception {
	    	int iTotalTestCases = ExcelUtils.getRowCount(testcase);
			for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){
				
				bResult = true;
				sTestCaseID = ExcelUtils.getCellData(iTestcase,0,testcase); 
				System.out.println(sTestCaseID);
				sRunMode = ExcelUtils.getCellData(iTestcase,2,testcase);
				System.out.println(sRunMode);
				if (sRunMode.equals("Yes")){
					test = extent.startTest("TEST = " + iTestcase);
					test.assignAuthor("Onkar");
					test.assignCategory("Environment","QA");
					iTestStep = ExcelUtils.getRowContains(sTestCaseID, 0 ,teststep);
					System.out.println(iTestStep);
					iTestLastStep = ExcelUtils.getTestStepsCount(teststep, sTestCaseID, iTestStep);
					System.out.println(iTestLastStep);
					Log.startTestCase(sTestCaseID);
					test.log(LogStatus.INFO, "Test started" + sTestCaseID);
					bResult=true;
					for(;iTestStep<iTestLastStep;iTestStep++){
			    		sActionKeyword = ExcelUtils.getCellData(iTestStep, 3 ,teststep);
			    		sPageObject = ExcelUtils.getCellData(iTestStep,2, teststep);
			    		execute_Actions();
							if(bResult==false){
								ExcelUtils.setCellData("FAIL",iTestcase,3,testcase);
								Log.endTestCase(sTestCaseID);
								test.log(LogStatus.FAIL, "Test Failed" + sTestCaseID);
								extent.endTest(test);
								break;
							}

						}	
					if(bResult==true){
					ExcelUtils.setCellData("PASS",iTestcase,3,testcase);
					Log.endTestCase(sTestCaseID);
					test.log(LogStatus.PASS, "Test Passed" + sTestCaseID);
					extent.endTest(test);
						}
					}
				}
 		}		
	

	   private static void execute_Actions() throws Exception {

			for(int i=0;i<method.length;i++){

				if(method[i].getName().equals(sActionKeyword)){
					method[i].invoke(actionKeywords,sPageObject);
					if(bResult==true){
						ExcelUtils.setCellData("PASS", iTestStep,4,teststep);
						break;
					}else{
						ExcelUtils.setCellData("FAIL", iTestStep, 4 , teststep);
						actionKeywords.closeBrowser("");
						break;
						}
					}
				}
	     }		
		        
}
	


