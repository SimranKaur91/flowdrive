package com.flowdrive.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;



public class PropertyHandler {

	private FileInputStream fis;
	Properties p;

	public boolean setFileName(String fileName) 
	{
		String error;

		try{
			fis=new FileInputStream("D:\\Tomcat\\webapps\\Inzenious\\property\\"+fileName+".properties");
			
			 
			p=new Properties();
			p.load(fis);

		} catch(FileNotFoundException fe){
			error="Unable to open file " + fileName + ".properties";
			System.out.println(error);
			return false;
		} catch(IOException io)
		{
			error="Unable to load File " + fileName + ".properties";
			System.out.println(error);
			return false;

		}
		return true;
	}

	public String readProperties(String key)
	{
		String data = null;
		try{
			data=p.getProperty(key);

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}

}
