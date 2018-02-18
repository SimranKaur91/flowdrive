package com.flowdrive.activities;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class LoginActivity {

	private String email="com.thefloow.flo:id/edit_text_email";
	private String password="com.thefloow.flo:id/edit_text_password";
	private String loginButton="com.thefloow.flo:id/btn_login";
	private AndroidDriver driver;
	private WebDriverWait wait;
	
	public LoginActivity(AndroidDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver, 30);
	}
	
	// enter emailId
	// enter password
	// click on login button
	public boolean login()
	{
		boolean status=true;
		
		try{
			
			driver.findElement(By.id("com.thefloow.flo:id/btn_agree")).click();
			Thread.sleep(2000);
			driver.findElement(By.id(email)).sendKeys("cyber.geek205@gmail.com");
			driver.findElement(By.id(password)).sendKeys("Zensar1234");
			driver.findElement(By.id(loginButton)).click();
			
		}catch(Exception e){
			e.printStackTrace();
			status=false;
		}//catch ends here
		
		return status;
	}//login function ends here 
}
