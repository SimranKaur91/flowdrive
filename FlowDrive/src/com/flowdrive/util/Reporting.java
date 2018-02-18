package com.flowdrive.util;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Reporting {

	String query;
	ResultSet rs;
	private static int testcaseCount;
	DatabaseConnectivity databaseConnectivity = null;
	
	public Reporting(String databaseName){

		try {
			databaseConnectivity=new DatabaseConnectivity(databaseName);

			query="create table IF NOT EXISTS ZT_testcase_desc (ID int not null auto_increment primary key,testCaseID int,stepNo int,step_desc varchar(200),expected_result varchar(200),actual_result varchar(200),status varchar(30),screenshot varchar(200))";
			databaseConnectivity.sendData(query);
			query="create table IF NOT EXISTS ZT_testcases (ID int not null auto_increment primary key,suite_ID int ,test_name varchar(50), module_name varchar(50), node varchar(20), exec_date date, exec_time time, steps int, passed int, failed int, warnings int, status varchar(30))";
			databaseConnectivity.sendData(query);;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void reportValidation(int testcaseID,int stepNo, String stepDesc, String expectedResultDesc, boolean status, String screenShot){

		String actualResult;
		String reportStatus="";
		if(status){
			actualResult="As Expected";
			reportStatus="PASS";
		}
		else{
			actualResult="Not As Expected";
			reportStatus="FAIL";
		}
//		System.out.println(stepNo + "    "+stepDesc+"		"+expectedResultDesc+ "		"+actualResult+ "	"+ status+"	"+ "	"+screenShot);
		try {
			query="insert into ZT_testcase_desc values (null,"+testcaseID+","+stepNo+",'"+stepDesc+"','"+expectedResultDesc+"','"+actualResult+"','"+reportStatus+"', '"+screenShot+"')";
			databaseConnectivity.sendData(query);;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reportTestcase(int testcaseID ,int suiteID,String testcaseName, String modulename , String nodeIP, int steps, int passed, int  failed, int warnings, boolean status){
		System.out.println("Test case ---"+steps);
		DateFormat mObjDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar gCalenderCal_start= Calendar.getInstance();
		String dateTime = mObjDateFormat.format(gCalenderCal_start.getTime());

		String date=dateTime.split(" ")[0];
		String time=dateTime.split(" ")[1];
		
		String reportStatus="";
		if(status){
			reportStatus="PASS";
		}
		else{
			reportStatus="FAIL";
		}
		
		try {
			query="insert into ZT_testcases values ("+testcaseID+","+suiteID+",'"+testcaseName+"','"+modulename+"','"+nodeIP+"','"+date+"','"+time+"',"+steps+","+passed+","+failed+","+warnings+",'"+reportStatus+"')";
			databaseConnectivity.sendData(query);;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getBrowser(String nodeIP){
		
		String browser="";
		query="select browser from system_tab where type = 'node' and machine_ip = '"+nodeIP+"'";
		rs=databaseConnectivity.getData(query);
		
		try {
			browser=rs.getString("browser");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return browser;
	}
	
	public  int testcaseID(){
		int testCaseID=0;
//		testcaseCount++;
		try {
			query="select max(ID) from ZT_testcases";
			rs=databaseConnectivity.getData(query);
			rs.next();
			testCaseID=rs.getInt("max(ID)");
			System.out.println("Noof row in table---"+testCaseID);
			
			System.out.println("testcaseCount---"+testcaseCount);
			testCaseID+= ++testcaseCount;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("TestcaseID---"+testCaseID);
		
		return testCaseID;
	}
	
}
