package week3.day2.uibank;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateAccount extends UiBankBasics {
	@Test
	public void TC002_createAccount() {

			// Step1 : Base url setup
		RestAssured.baseURI = "https://uibank-api.azurewebsites.net/api/";
		
	
				// step 2 : get method to retrive data
		
		Response resp = RestAssured
						.given()
						.headers(hMap)
						.log()
						.all()
						.contentType(ContentType.JSON)
						.body("{\"friendlyName\":\"lakshmisadhula1\",\"type\":\"savings\",\"userId\":\"5eef9c00e29f950044ba3088\",\"balance\":100,\"accountNumber\":22375604}")
						
						.post("accounts");

		// step 3 convert response into json response
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();	
		//userId =  jsonresp.get("userId");
		// step 5 : verify status code : 201
		assertTrue(resp.getStatusCode() == 200, "Expected status code : 200 and actual :" + resp.getStatusCode());

			
	}
}
