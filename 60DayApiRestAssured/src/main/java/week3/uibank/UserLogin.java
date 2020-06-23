package week3.day2.uibank;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserLogin extends UiBankBasics {
	
	@Test
	public void TC001_userLogin () {
			// Step1 : Base url setup
		RestAssured.baseURI = "https://uibank-api.azurewebsites.net/api/";
				// step 2 : get method to retrive data
				Response resp = RestAssured
						.given()
						.log()
						.all()
						.contentType(ContentType.JSON)
						.body("{\"username\":\"lakshmisadhula\",\"password\":\"Pranathi75#\"}")
						
						.post("users/login");

		// step 3 convert response into json response
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();	
		userId = jsonresp.get("userId").toString();
		setUserId(jsonresp.get("userId").toString());
		System.out.println(getUserId());
		// step 5 : verify status code : 201
		assertTrue(resp.getStatusCode() == 200, "Expected status code : 200 and actual :" + resp.getStatusCode());

			
	}
	
	

}
