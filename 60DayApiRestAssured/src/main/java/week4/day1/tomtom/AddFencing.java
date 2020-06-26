package week4.day1.tomtom;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddFencing extends TomTomBasics {
	@Test
	public void addNewProject() {
			// Step1 : Base url setup
		RestAssured.baseURI = "https://api.tomtom.com/geofencing/1/";
	
		// step 2 : get method to retrive data
				Response resp = RestAssured
						.given()
						.log()
						.all()
						.queryParam("key",apikey)
						.queryParam("adminKey",adminKey)
						.contentType(ContentType.JSON)
						.body("{ \r\n" + 
								"  \"name\": \"MyRAGeofencingProject\"\r\n" + 
								"}")
						
						.post("projects/"+project_id);

		// step 3 convert response into json response
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();
			// step 5 : verify status code : 201
		assertTrue(resp.getStatusCode() == 201, "Expected status code : 201 and actual :" + resp.getStatusCode());

			
	}

}
