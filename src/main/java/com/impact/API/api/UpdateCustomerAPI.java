package com.impact.API.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Set;

import com.impact.API.TestSetup.TestSetup;
import com.impact.API.TestUtils.TestUtil;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateCustomerAPI extends TestSetup {

	// Valid secret key test
	public static Response postRequestToUpdateCustomerAPIwithValidSecretKey(String customerID, HashMap mapOfField) {

		// Create request
		RequestSpecification requestSpecs = given().auth().basic(configProperty.getValidAuthKey(), "");

		// Create Param

		if (mapOfField.size() > 0) // check map has data
		{
			// read key value from map using keySet
			// Note the keys are String in Stripe API
			Set<String> keySet = mapOfField.keySet();

			// From those keys we read value
			for (String key : keySet) {
				// read value from key and convert to string and store in a variable value
				String value = mapOfField.get(key).toString();

				// pass this value to request as param
				requestSpecs=requestSpecs.param(key, value);

			}

		}
		// Call post method and pass endpoint
		Response response = requestSpecs.post(configProperty.getupdateCustomerAPIEndPoint() + "/" + customerID);

		// Log response to report
		TestUtil.logResponseInReport(response);

		return response;

	}

}

