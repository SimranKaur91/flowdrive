package com.flowdrive.activities;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class JourneyActivity {

	private AndroidDriver driver;
	
	public JourneyActivity(AndroidDriver driver) {
		this.driver=driver;
	}
	
	public boolean validateJourneyDetails()
	{
		boolean status=true;
		
		try{
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.TextView[@text='19/02/2018']")).click();
			Thread.sleep(2000);
			
			if(driver.findElement(By.id("com.thefloow.flo:id/text_view_duration")).getText().equals("10m"))
			{
				System.out.println("Journey Time Validated");
			}
			else
				status=false;
			
			if(driver.findElement(By.id("com.thefloow.flo:id/text_view_distance")).getText().equals("3.1"))
			{
				System.out.println("Journey Distance Validated");
			}
			else
				status=false;
			
		}catch(Exception e){
			status=false;
			e.printStackTrace();
		}
		return status;
	}
	
}
