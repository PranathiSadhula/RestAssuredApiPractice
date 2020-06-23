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

public class RegisterUser extends UiBankBasics {
	
	
	@Test
	public void registerUser () {
		RestAssured.baseURI = "https://uibank-api.azurewebsites.net/api/";
				// step 2 : get method to retrive data
				Response resp = RestAssured
						.given()
						.log()
						.all()
						.contentType(ContentType.JSON)
						.body("{\r\n" + 
								"\"firstName\": \"lakshmi\",\r\n" + 
								"\"title\": \"mrs\",\r\n" + 
								"\"lastName\": \"Sadhula\",\r\n" + 
								"\"gender\": \"female\",\r\n" + 
								"\"age\": \"30/11/91\",\r\n" + 
								"\"email\": \"pranathisadhula91@gmail.com\",\r\n" + 
								"\"employmentStatus\": \"Full-time\",\r\n" + 
								"\"firstName\": \"lakshmi\",\r\n" + 
								"\"gender\": \"female\",\r\n" + 
								"\"lastName\": \"Sadhula\",\r\n" + 
								"\"maritalStatus\": \"Married\",\r\n" + 
								"\"password\": \"Pranathi75#\",\r\n" + 
								"\"title\": \"mrs\",\r\n" + 
								"\"type\": \"customer\",\r\n" + 
								"\"username\": \"pranathisadhula1\"\r\n" + 
								"}")
						
						.post("users");

		// step 3 convert response into json response
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();	
		// step 5 : verify status code : 201
				assertTrue(resp.getStatusCode() == 200, "Expected status code : 200 and actual :" + resp.getStatusCode());

			
	}


}
