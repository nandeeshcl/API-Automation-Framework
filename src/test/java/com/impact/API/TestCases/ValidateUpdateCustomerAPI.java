package com.impact.API.TestCases;

import java.util.HashMap;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.impact.API.TestSetup.TestSetup;
import com.impact.API.TestUtils.Data;
import com.impact.API.TestUtils.TestUtil;
import com.impact.API.api.CustomerAPI;
import com.impact.API.api.UpdateCustomerAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ValidateUpdateCustomerAPI extends TestSetup {
	
	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateUpdateCustomerAPIWithValidSecretKey(Hashtable<String, String> data) {

		// Assign author in report
		testLevelLog.get().assignAuthor("Nandeesh CL");
		
		
	    //Create Hash Map
		HashMap mapOfField=new HashMap();
	
		//Fetch value from excel and store in a key
		mapOfField.put("name",data.get("name"));
		mapOfField.put("balance",Integer.parseInt(data.get("balance")));
		
		
		//Call Post request in API class and store response
		Response response=UpdateCustomerAPI.postRequestToUpdateCustomerAPIwithValidSecretKey("cus_Atc0zs1sCqnaXg", mapOfField);
		
		
		
		
		
		//VALIDATIONS
		// Verify Status Code as 200 : 
		
		Assert.assertEquals(response.statusCode(),Integer.parseInt(data.get("statusCode")));
		testLevelLog.get().info("Response code is as expected");
		
	}

	
	
	

}
