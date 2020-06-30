package week4.day2.newsletter;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateNewList extends NewsLetterBasics{
	
	@Test
	public void TC001_CreateNewList() {
		

		RestAssured.baseURI = prop.getProperty("base_uri");
				Response resp = RestAssured
						.given()
						.log()
						.all()
						.headers(accessHeader)
						//.contentType(ContentType.JSON)
						.body("{\r\n" + 
								"  \"name\": \"Psadhula list via RA\",\r\n" + 
								"  \"uses_econda\": false,\r\n" + 
								"  \"uses_googleanalytics\": true,\r\n" + 
								"  \"has_opentracking\": true,\r\n" + 
								"  \"has_clicktracking\": true,\r\n" + 
								"  \"has_conversiontracking\": false,\r\n" + 
								"  \"imprint\": \"http://example.org/imprint\",\r\n" + 
								"  \"header_from_email\": \"from@example.org\",\r\n" + 
								"  \"header_from_name\": \"From Name\",\r\n" + 
								"  \"header_reply_email\": \"reply@example.org\",\r\n" + 
								"  \"header_reply_name\": \"Reply Name\",\r\n" + 
								"  \"tracking_url\": null,\r\n" + 
								"  \"landingpage\": \"http://example.org/unsubscribe-landingpage\",\r\n" + 
								"  \"use_ecg_list\": false\r\n" + 
								"}")
		
						.post("lists");
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();
		list_id = jsonresp.get("value.id");
		
	}

}
