package com.impact.API.api;

import java.util.Hashtable;

import com.impact.API.TestSetup.TestSetup;
import com.impact.API.TestUtils.TestUtil;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class CustomerAPI extends TestSetup {

	// Valid secret key test
	public static Response postRequestToCreateCustomerAPIwithValidSecretKey(Hashtable<String, String> data) {
		// Create Post Request
		// Read auth key, end point from config prop file
		// :configProperty.getValidAuthKey()
		// configProperty.getcreateCustomerAPIEndPoint()
		Response response = requestSpecification.param("email", data.get("email"))
				.param("description", data.get("description")).param("name", data.get("name"))
				.post(configProperty.getcreateCustomerAPIEndPoint());

		// Log response to report
		TestUtil.logResponseInReport(response);

		return response;
	}

	// Invalid secret key test
	public static Response postRequestToCreateCustomerAPIwithInValidSecretKey(Hashtable<String, String> data) {
		// Create Post Request
		Response response = given().auth().basic(configProperty.getInvalidAuthKey(), "")
				.param("email", data.get("email")).param("description", data.get("description"))
				.param("name", data.get("name")).post(configProperty.getcreateCustomerAPIEndPoint());

		// Log response to report
		TestUtil.logResponseInReport(response);

		return response;
	}

}
