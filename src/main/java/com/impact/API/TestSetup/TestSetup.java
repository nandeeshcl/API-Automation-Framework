package com.impact.API.TestSetup;

import java.io.FileInputStream;
import static io.restassured.RestAssured.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.impact.API.TestUtils.ConfigProperties;
import com.impact.API.TestUtils.ExcelReader;
import com.impact.API.TestUtils.Extentmanager;
import com.impact.API.TestUtils.TestUtil;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class TestSetup {

	public  static Logger log;       //FOR LOGS
	
	//Owner API 
	public static ConfigProperties configProperty;
	
	//For excel reader to load excel file
	public static ExcelReader excel=new ExcelReader("./src/test/testData/testDataSheet.xlsx");
	
	//For Report generation
	public static ExtentReports extentReport;
	public static ThreadLocal<ExtentTest> classLevelLog=new ThreadLocal<ExtentTest>(); //LHS Node create
	public static ThreadLocal<ExtentTest> testLevelLog=new ThreadLocal<ExtentTest>();   //Test case Log
	
	//For request specification
	public static RequestSpecification requestSpecification;
	
	
	
	@BeforeSuite
	public void setupFramework() throws IOException
	{
		//Load Property file
		/*Properties prop = new Properties();
		FileInputStream configfile = new FileInputStream(System.getProperty("user.dir") + "\\propertyFiles\\config.properties");
		prop.load(configfile);*/
		
		
		//Keep Base URI and Base Path 
    	/*RestAssured.baseURI=prop.getProperty("baseURI");
    	RestAssured.basePath=prop.getProperty("basePath");*/
		
		//LOG4J PROPERTY FILE LOAD
		//log=Logger.getLogger("JOANNV1-TESTNG");
		PropertyConfigurator.configure("./log4j.properties");
		
		
		
		//Owner API Config Property
		configProperty=ConfigFactory.create(ConfigProperties.class);
		
		
		//Call archieve report
		TestUtil.archiveTestReport();
		
		
		//Keep Base URI and Base Path 
		RestAssured.baseURI=configProperty.getBaseURI();
    	RestAssured.basePath=configProperty.getBasePath();
    	
    	//call getExtent method present in Extentmanager class and send path of report to be generated
    	// Extent Report: Create object for Extent Manager class and in initialize
    			// report -Name...Location..
    	extentReport = Extentmanager
				.GetExtent("C:\\Users\\User\\Desktop\\N\\API_Automation_Framework\\src\\main\\testReport\\TestReport.html");
		
    	
	}
	
	//Create node in LHS class level in extent report
	@BeforeClass
	public void beforeClass()
	{
		//create class at lhs of the report for each class
		ExtentTest test= extentReport.createTest(getClass().getSimpleName());
		classLevelLog.set(test);
	}
	
	//print method info
	@BeforeMethod
	public void beforeMethod(Method method) {
		System.out.println("Execution of method:- "+method.getName()+" STARTED");
		
		//call request specification setting method
		requestSpecification=setRequestSpecification(configProperty.getValidAuthKey());
		/*
		//create node in middle of the report for the method
		ExtentTest test= classLevelLog.get().createNode(method.getName());
		testLevelLog.set(test);
		
		//log info in report
		testLevelLog.get().log(Status.INFO," Execution of Test Case:- " + method.getName() + " started");*/
		
	}
	
	//print method info
	@AfterMethod
	public void afterMethod(ITestResult testCaseResult) {
		
		System.out.println("Execution of method:- "+testCaseResult.getName()+" FINISHED");
		
		//Log details in Extent report
		testLevelLog.get().log(Status.INFO, "<b>" +"Execution of Test Case:- " + testCaseResult.getName() + " finished"+"<b>");
		
		
		//For Logs
		//To log info in report
		/*testLevelLog.get().assignAuthor("NCL");
    	testLevelLog.get().info("EXTENT REPORT");
    	testLevelLog.get().assignCategory("Regression Test case");*/
    	
    	//To send Log to report
	    //testLevelLog.get().log(Status.INFO,"Entered UserName is: "+data.get("UserName"));
    	
    	
	   /* if(testCaseResult.isSuccess())
	    {
	    	testLevelLog.get().pass("This test case got passed");
	    }
	    else
	    {
	    	testLevelLog.get().fail("This test case got Failed");
	    }*/
	    
	    
	    
	}
	
	@AfterSuite
	public void tearDownFramework()
	{
		extentReport.flush();
		//TestUtil.archiveTestReport();
		
	}
	
	//Request specification seting auth key globally
	public static RequestSpecification setRequestSpecification(String key)
	{
		return given().auth().basic(key,"");
	}
}
