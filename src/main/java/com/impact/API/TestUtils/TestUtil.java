package com.impact.API.TestUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import com.impact.API.TestSetup.TestSetup;

import io.restassured.response.Response;

public class TestUtil extends TestSetup{
	
	//To check ID is present method
	public static boolean checkJsonHasKey(String key,Response response)
	{
		JSONObject json=new JSONObject(response.asString());     //store response in json
		return json.has(key); //check this key is present in response
	}
	
	
	//To log response of API in the report
	public static void logResponseInReport(Response response)
	{
		testLevelLog.get().info("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Click Here To See API Response"
				+ "</font>" + "</b >" + "</summary>" + response.asString() + "</details>"
				+ " \n");
		
		
	}
	
	//To archieve report
public static void archiveTestReport() {
		
		String reportName = "TestReport.html";

		//Existing test report to be archeved
		String lastTestReportFilePath = "./src/test/TestReport/TestReport.html";
		
		//Location where report is archieved
		String archiveReportPath = System.getProperty("user.dir")+"/src/test/archievedReport/";

		Date date = new Date();
		SimpleDateFormat dateFormate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String formatedDate = dateFormate.format(date);
		String archiveTestReportName = formatedDate + "_" + reportName;

		File oldReport = new File(lastTestReportFilePath);

		File newFile = new File(archiveReportPath + archiveTestReportName);
		
		System.out.println(oldReport.exists());
		
		if (oldReport.exists()) {
			System.out.println("inside if");
			oldReport.renameTo(newFile);
			oldReport.delete();
		}
	

}

}
