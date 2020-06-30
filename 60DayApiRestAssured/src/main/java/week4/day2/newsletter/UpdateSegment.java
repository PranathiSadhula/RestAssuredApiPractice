package week4.day2.newsletter;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateSegment extends NewsLetterBasics{
	
	@Test
	public void TC003_UpdateNewSegment() {
		

		RestAssured.baseURI = prop.getProperty("base_uri");
				Response resp = RestAssured
						.given()
						.log()
						.all()
						.headers(accessHeader)
						//.contentType(ContentType.JSON)
						.body("{\r\n" + 
								"	\"list_id\": \""
								+ list_id
								+ "\",\r\n" + 
								"	\"name\": \"updated segment -RA\"\r\n" + 
								"}")
		
						.post("groups/"+segment_id);
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();
		
		
	}

}
