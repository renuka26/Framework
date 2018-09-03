package com.mindtree.keywordDrivenFramework.config;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mindtree.keywordDrivenFramework.executionEngine.Script;
import com.mindtree.keywordDrivenFramework.utility.Log;
import com.mindtree.keywordDrivenFramework.utility.ScreenshotUtility;
import com.relevantcodes.extentreports.LogStatus;
import static com.mindtree.keywordDrivenFramework.executionEngine.Script.test;

import java.io.File;
import java.io.IOException;

import  static com.mindtree.keywordDrivenFramework.executionEngine.Script.prop;
public class ActionKeywords {
	
	public  static  WebDriver driver;	 
	public static  void openBrowser(String object){
		try {
			Log.info("opening browser");
			String  path = "D:/workspace NEON/keywordDrivenFramework/driver/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver",path);
			driver = new ChromeDriver();
			 test.log(LogStatus.PASS,"opening browser"+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_openBrowser_pass.png")));
		
		
		} catch (Exception e) {
			Log.info("Not able to open Browser --- " + e.getMessage());
			 try {
				test.log(LogStatus.FAIL,"Not able to open Browser "+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_openBrowser_fail.png")));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Script.bResult = false;
		}
		}
	
	
	
	public static void openurl(String object) throws IOException
	{
		
		try {
			Log.info("opening url");
			driver.get(prop.getProperty(object));
			test.log(LogStatus.PASS,"opening url "+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_url_pass"+object+".png")));
		} catch (Exception e) {
			Log.info("Not able to open"+ object + e.getMessage());
			 try {
				test.log(LogStatus.FAIL,"Not able to open"+ object + test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_url_fail_"+object+".png")));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Script.bResult = false;
		}
			
			
			
			
		}
	
	
	
 
	
	public  static  void waitFor(String object) throws Exception{
		Thread.sleep(2000);
		}
 
	public  static   void sendkeys(String object){
		try {
			Log.info("Entering mindtree in search box");
			driver.findElement(By.xpath(prop.getProperty(object))).sendKeys("MINDTREE");
			 test.log(LogStatus.PASS,"Entering mindtree in search box"+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_"+object+"sendkeys_pass.png")));
		
			
			
			
		} catch (Exception e) {
			Log.info("not able to enter data in search box " + e.getMessage());
			try {
				test.log(LogStatus.PASS,"not able to enter data in search box"+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_"+object+"sendkeys_fail.png")));			
				} catch (IOException e1) {
				e1.printStackTrace();
			}
			 
			Script.bResult = false;
		}
		}
 
	public  static  void search_click(String object){
		try {
			Log.info("clicking on "+ object);
			 test.log(LogStatus.PASS,"clicking on "+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_"+object+"_click_pass.png")));
			driver.findElement(By.xpath(prop.getProperty(object))).click();
		} catch (Exception e) {
			Log.info("Not able to click on search button" + e.getMessage());
			try {
				 test.log(LogStatus.FAIL,"not able to click on search button"+test.addScreenCapture("Screenshot_"+object+"_click_fail.png"));
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			

			Script.bResult = false;
		}
		}
	public  static void closeBrowser(String object){
			try {
				Log.info("closing browser");
				 test.log(LogStatus.PASS,"Closing browser"+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_closebrowser_pass_"+object+".png")));
				 driver.close();
			} catch (Exception e) {
				Log.info("Not able to close " + e.getMessage());
				 try {
					test.log(LogStatus.INFO,"Not able to close browser"+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_closebrowser_fail_"+object+".png")));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Script.bResult = false;
			}
		}
	}