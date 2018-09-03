package com.mindtree.keywordDrivenFramework.utility;
import static com.mindtree.keywordDrivenFramework.config.ActionKeywords.driver;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotUtility {

public static String  getScreenshot	(String name) throws IOException
{
	File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String  imagePath = "D:/workspace NEON/keywordDrivenFramework/screenshots/"+name;
	FileUtils.copyFile(screenshotFile, new File(imagePath));
return imagePath;
}
	
	
	
}
