package com.impact.API.TestUtils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
	"file:src/test/propertyFiles/config.properties"
})
public interface ConfigProperties extends Config {

	@Key("baseURI")
	public String getBaseURI();
	
	@Key("basePath")
	public String getBasePath();
	
	@Key("createCustomerAPIEndPoint")
	public String getcreateCustomerAPIEndPoint();
	
	@Key("age")
	public int getAge();
	
	@Key("testDataSheetName")
	public String getTestDataSheetName();
	
	@Key("updateCustomerAPIEndPoint")
	public String getupdateCustomerAPIEndPoint();
	
	@Key("deleteCustomerAPIEndPoint")
	public String getdeleteCustomerAPIEndPoint();
	
	@Key("ValidAuthKey")
	public String getValidAuthKey();
	
	@Key("InvalidAuthKey")
	public String getInvalidAuthKey();
	
	
}
