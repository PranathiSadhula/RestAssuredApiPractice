package week4.day1.tomtom;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RegenerateAdminKey extends TomTomBasics{
	@Test
	public void registerAdminKey() {
		RestAssured.baseURI = "https://api.tomtom.com/geofencing/1/";
			// step 2 : get method to retrive data
		Response resp = RestAssured
				.given()
				.log()
				.all()
				.queryParam("key",apikey)
				.contentType(ContentType.JSON)
				.body("{\r\n" + 
						"  \"secret\": \"MyRAGeofencingSecret\"\r\n" + 
						"}")
				
				.post("regenerateKey");

// step 3 convert response into json response
JsonPath jsonresp = resp.jsonPath();
jsonresp.prettyPrint();	
this.adminKey = jsonresp.get("adminKey");
System.out.println(this.adminKey);
// step 5 : verify status code : 201
assertTrue(resp.getStatusCode() == 200, "Expected status code : 200 and actual :" + resp.getStatusCode());
	}

}
