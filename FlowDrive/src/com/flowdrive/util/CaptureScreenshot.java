package com.flowdrive.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;


public class CaptureScreenshot {

	public String captureScreenshot(WebDriver webdriver, String screenShotPath){

		String screenShotName="";

		WebDriver augmentedDriver = new Augmenter().augment(webdriver);
		File scrFile = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere

		try {
			screenShotName = createTimeStampStr() + ".PNG";
			screenShotPath+= "\\\\\\\\"+ screenShotName;
			FileUtils.copyFile(scrFile, new File(screenShotPath));
			scrFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return screenShotName;
	}

	public String createTimeStampStr() throws Exception {
		Calendar mycalendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_hhmmssS");
		String timeStamp = formatter.format(mycalendar.getTime());

		return timeStamp;
	}
	
}
