package week4.day2.newsletter;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateNewSegment extends NewsLetterBasics{
	
	@Test
	public void TC002_CreateNewSegment() {
		

		RestAssured.baseURI = prop.getProperty("base_uri");
				Response resp = RestAssured
						.given()
						.log()
						.all()
						.headers(accessHeader)
						//.contentType(ContentType.JSON)
						.body("{\r\n" + 
								"	\"list_id\": \""
								+ "m68l2gvr"
								+ "\",\r\n" + 
								"	\"name\": \"New regular Segment\",\r\n" + 
								"	\"is_dynamic\": false\r\n" + 
								"}")
		
						.post("groups");
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();
		segment_id = jsonresp.get("value.id");
		
	}

}
