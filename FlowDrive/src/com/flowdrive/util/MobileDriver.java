package com.flowdrive.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

@SuppressWarnings("unused")
public class MobileDriver {

	private AndroidDriver<WebElement> androidDriver;
	private IOSDriver<WebElement> iosDriver;
	private String url;
	private String port;
	private String browserName;
	private String appPath;
	private String appPackage;
	private String appActivity;
	private String orientation;
	private String deviceName;
	private String udid;
	private String platformName;
	private String platformVersion;
	private String executionType;
	private DesiredCapabilities cap;
	
	
	public MobileDriver(String executionType,String deviceName,String url, String port ,String browserName,String platformName,String platformVersion,String udid) {
		
		 this.executionType=executionType;
		 this.deviceName=deviceName;
		 this.browserName=browserName;
		 this.url=url;
		 this.port=port;
		 this.platformName=platformName;
		 this.platformVersion=platformVersion;
		 this.udid=udid;
		 
	}	

	public MobileDriver(String executionType,String deviceName,String url, String port ,String appPath,String appActivity,String appPackage,String platformName,String platformVersion,String udid) {
		
		 this.executionType=executionType;
		 this.deviceName=deviceName;
		 this.appPath=appPath;
		 this.appActivity=appActivity;
		 this.appPackage=appPackage;
		 this.url=url;
		 this.port=port;
		 this.platformName=platformName;
		 this.platformVersion=platformVersion;
		 this.udid=udid;
		 
	}
	
	public AndroidDriver<WebElement> getAndroidDriver() throws MalformedURLException
	{
		
		cap=new DesiredCapabilities();
		cap.setCapability("deviceName",deviceName);
		cap.setCapability("udid",udid);
		cap.setCapability("platformName",platformName);
		cap.setCapability("platformVersion",platformVersion);
		
		if(browserName!=null && appPath==null)
		{
			cap.setCapability("browserName",browserName);	
		}
		else if(appPath!=null && browserName==null)
		{
			cap.setCapability("app",appPath);
			cap.setCapability("appActivity",appActivity);
			cap.setCapability("appParameter",appPackage);
		}
		else
		{
			cap.setCapability("browserName",browserName);
			cap.setCapability("app",appPath);
			cap.setCapability("appActivity",appActivity);
			cap.setCapability("appParameter",appPackage);
		}
		
		androidDriver=new AndroidDriver<>(new URL("http://"+url+":"+port+"/wd/hub"),cap);
		return androidDriver;
	}
	
	
}
