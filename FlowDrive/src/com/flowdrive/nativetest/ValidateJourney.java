package com.flowdrive.nativetest;

import org.testng.annotations.Test;

import com.flowdrive.activities.HomeActivity;
import com.flowdrive.activities.JourneyActivity;
import com.flowdrive.activities.LoginActivity;
import com.flowdrive.util.CaptureScreenshot;
import com.flowdrive.util.Reporting;

import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;




//import org.apache.poi.hssf.model.Workbook;
//import org.apache.poi.hssf.record.formula.functions.Row;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ValidateJourney {
	// WebDriver driver;
	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait;
	// report specific variables

	//Xls_Reader xl= new Xls_Reader("D:\\workspace\\TestNGFramework\\src\\data\\TC_Calculate_EMI.xls");	
	@Parameters({ "Devicename", "url", "platform","tcid","ModuleName"})
	@BeforeTest
	public void beforeClass(String device, String url, String platform,int tcid,String ModuleName)
			throws InterruptedException, MalformedURLException {
		
		
		
		DesiredCapabilities caps = DesiredCapabilities.android();
		String location=System.getProperty("user.dir");
		
		System.out.println(location+"\\app\\flowdrive.apk");
		
		caps.setCapability("deviceName",device);
		caps.setCapability("udid",device);
		caps.setCapability("deviceOrientation", "portrait");
		caps.setCapability("platformVersion",platform);
		caps.setCapability("platformName","Android");
		caps.setCapability("app",location+"\\app\\flowdrive.apk");
		caps.setCapability("appPackage", "com.thefloow.flo");
		caps.setCapability("appActivity", "com.thefloow.flo.activity.LauncherActivity");
		Thread.sleep(5000);
		
		driver = new AndroidDriver(new URL("http://" + url + "/wd/hub"), caps);
		Thread.sleep(5000);
		wait= new WebDriverWait(driver, 20);
	

	}

	@Test
	public void f() throws InterruptedException, IOException {

		LoginActivity loginObj=new LoginActivity(driver);
		HomeActivity homeActivityObj= new HomeActivity(driver);
		JourneyActivity journeyActivityObj=new JourneyActivity(driver);
		
		
		if(loginObj.login())
		{
			Thread.sleep(5000);
			if(driver.findElement(By.id("com.thefloow.flo:id/btn_welcome_close")).isDisplayed())
			{
				driver.findElement(By.id("com.thefloow.flo:id/btn_welcome_close")).click();
				Thread.sleep(2000);
				if(driver.findElement(By.id("com.thefloow.flo:id/image_view_logo")).isDisplayed())
				{
					if(homeActivityObj.clickOnJourneyTab())
					{
						if(journeyActivityObj.validateJourneyDetails())
						{
							System.out.println("Test Case Passed");
						}
						else
							System.out.println("Testcase Failed");
					}
					else
					{
						System.out.println("Test case Failed");
					}
				}
				else
					System.out.println("Failed");
			}
			
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		System.out.println("End of test");
	}

}
