package com.flowdrive.activities;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class HomeActivity {

	private String journeyTab="com.thefloow.flo:id/tab_journeys";
	private AndroidDriver driver;
	public HomeActivity(AndroidDriver driver) {
	
		this.driver=driver;
	}
	
	public boolean clickOnJourneyTab()
	{
		boolean status =true;
		try{
			driver.findElement(By.id(journeyTab)).click();
		}catch(Exception e){
			e.printStackTrace();
			status=false;
		}
		
		return status;
	}
}
