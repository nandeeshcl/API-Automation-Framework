package com.impact.API.TestCases;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.impact.API.TestSetup.TestSetup;
import com.impact.API.TestUtils.Data;
import com.impact.API.TestUtils.TestUtil;
import com.impact.API.api.CustomerAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ValidateCreateCustomerAPI extends TestSetup {
	
	
	

	// fetching data from data provider method by name "data"
	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String, String> data) {

		log=Logger.getLogger(ValidateCreateCustomerAPI.class);
		
		// Assign author in report
		testLevelLog.get().assignAuthor("Nandeesh CL");
		testLevelLog.get().assignCategory("Regression Test Case");

		//Logs
		log.info("VALIDATION STARTED");
		// Call Post request in API class and store response
		Response response = CustomerAPI.postRequestToCreateCustomerAPIwithValidSecretKey(data);

		// Logging response to the report
		// whatever the response that will be logg into the report
		TestUtil.logResponseInReport(response);

		// VALIDATIONS
		// Verify Status Code as 200 :
		// response.statusCode(), 200 : get status code from response
		// data.get("statusCode") : get status code from excel test data file and
		// compare it
		Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("statusCode")));

		// Send info to report if this assert pass: can send any text message
		testLevelLog.get().info("Response code is as expected");
		/*
		 * try {
		 * 
		 * 
		 * 
		 * 
		 * 
		 * Assert.assertEquals(response.statusCode(), 201); // print details in report
		 * testLevelLog.get()
		 * .info("Response status code matched: Expetced :- 200 and Actual:- " +
		 * response.getStatusCode()); } catch (Throwable e) { e.printStackTrace(); //
		 * print details in report testLevelLog.get().info(
		 * "Response status code does not matched: Expetced :- 200 and Actual:- " +
		 * response.getStatusCode());
		 * 
		 * }
		 */

		// Verifiy ID is present
		JsonPath responseJson = response.jsonPath();

		// Validate email sent in request and response are same
		// responseJson.get("email"): fetch email key value from response email:
		// jsonpath
		// data.get("email"): fetch value from email key in test data file

		Assert.assertEquals(responseJson.get("email"), data.get("email"));
		// Send info to report if this assert pass: can send any text message
		testLevelLog.get().info("Email is as expected");

		//// Validate description sent in request and response are same
		Assert.assertEquals(responseJson.get("description"), data.get("description"));

		// Send info to report if this assert pass: can send any text message
		testLevelLog.get().info("Description is as expected");

		// Validate name sent in request and response are same
		Assert.assertEquals(responseJson.get("name"), data.get("name"));

		// Send info to report if this assert pass: can send any text message
		testLevelLog.get().info("Name is as expected");

		// Print Customer ID
		// System.out.println("Customer ID is : " + responseJson.get("id"));

		// Validate ID is present is response
		// TestUtil.checkJsonHasKey("id", response): call checkJsonHasKey method created
		// in TestUtil class and pass the key id which has to
		// be checked and also send response
		Assert.assertTrue(TestUtil.checkJsonHasKey("id", response));

		// To check ID is not null
		if (TestUtil.checkJsonHasKey("id", response)) {
			Assert.assertNotNull(responseJson.get("id"));
		} else {
			Assert.fail("ID Field is not available in response");
		}

		/*
		 * try { Assert.assertTrue(TestUtil.checkJsonHasKey("id", response)); // print
		 * details in report testLevelLog.get().info("Response contains id field"); }
		 * catch (Throwable e) { e.printStackTrace(); // print details in report
		 * testLevelLog.get().info("Response not contains id field");
		 * 
		 * }
		 */

	}

	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateCreateCustomerAPIWithInValidSecretKey(Hashtable<String, String> data) {

		// Call Post request in API class and store response
		Response response = CustomerAPI.postRequestToCreateCustomerAPIwithInValidSecretKey(data);

		// Log response to report
		TestUtil.logResponseInReport(response);

		// Verify Status Code is 400
		Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("statusCode")));

		// Verify status code is not 200
		Assert.assertNotEquals(response.statusCode(), 200);

		// Validate ID is not present is response
		Assert.assertFalse(TestUtil.checkJsonHasKey("id", response));

		// Validate response contains error field
		Assert.assertTrue(TestUtil.checkJsonHasKey("error", response));

	}
}
