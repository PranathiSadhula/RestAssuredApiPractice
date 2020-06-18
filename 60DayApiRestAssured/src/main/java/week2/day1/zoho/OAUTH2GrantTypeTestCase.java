package week2.day1.zoho;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OAUTH2GrantTypeTestCase {
	
	@Test()
	  public void testOAuthWithAuthorizationCode()  {
		   
	        Response response =
	        RestAssured. given()
	.formParam("grant_type","authorization_code")                                                   
	
	.formParam("redirect_uri","http://www.zoho.com/subscriptions")
	.formParam("client_id", "1000.RDXLRMR7U4GO4ISGGREI3XR4SESMVH")
	.post("https://accounts.zoho.com/oauth/v2/auth?access_type=offline")
	;
	        
	        

	      
	    }
	
}